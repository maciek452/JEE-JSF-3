package pl.gda.pg.eti.kask.javaee.jsf.business.boundary.login;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.dao.UserJpaDao;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.User;
import pl.gda.pg.eti.kask.javaee.jsf.business.utils.CryptUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Map;
import java.util.Set;

import static java.lang.String.valueOf;
import static java.util.Objects.isNull;

@ApplicationScoped
public class CustomLoginModule implements LoginModule {
  private static final String NULL_CALLBACK_ERROR = "Callback handler is null.";
  private static final String CALLBACK_HANDLING_ERROR = "Error during callback handling";
  private static final String LOOKUP_ERROR = "Failed looking up CDI Bean";
  private static final String INVALID_CREDENTIALS_ERROR = "Invalid credentials";
  private static final String USER_NOT_EXISTS_ERROR =
      "User with provided username does not exists.";

  private Subject subject;
  private CallbackHandler callbackHandler;

  private Principal identity;
  private Group roles;

  @Override
  public void initialize(
      final Subject subject,
      final CallbackHandler callbackHandler,
      final Map<String, ?> sharedState,
      final Map<String, ?> options) {
    this.subject = subject;
    this.callbackHandler = callbackHandler;
  }

  @Override
  public boolean login() throws LoginException {
    if (callbackHandler == null) {
      throw new LoginException(NULL_CALLBACK_ERROR);
    }

    final Callback[] callbacks = handleCallbacks();

    final NameCallback nameCallback = (NameCallback) callbacks[0];
    final PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];

    final String password = valueOf(passwordCallback.getPassword());
    final UserJpaDao userJpaDao = lookupDao();
    final User user = userJpaDao.findByLogin(nameCallback.getName());
    return isAuthenticated(password, user);
  }

  private Callback[] handleCallbacks() throws LoginException {
    final Callback[] callbacks = new Callback[2];
    callbacks[0] = new NameCallback("name:");
    callbacks[1] = new PasswordCallback("password:", false);

    try {
      callbackHandler.handle(callbacks);
    } catch (IOException | UnsupportedCallbackException e) {
      throw new LoginException(CALLBACK_HANDLING_ERROR);
    }
    return callbacks;
  }

  private boolean isAuthenticated(final String password, final User user)
      throws FailedLoginException {
    if (isNull(user)) {
      throw new FailedLoginException(USER_NOT_EXISTS_ERROR);
    }
    if (user.getPassword().equals(CryptUtils.sha256(password))) {
      identity = user::getLogin;
      roles = new RolesGroup(user.getRoles());
      return true;
    } else {
      throw new FailedLoginException(INVALID_CREDENTIALS_ERROR);
    }
  }

  @Override
  public boolean commit() throws LoginException {
    final Set<Principal> principals = subject.getPrincipals();
    principals.add(identity);
    principals.add(roles);

    return true;
  }

  @Override
  public boolean abort() throws LoginException {
    identity = null;
    roles = null;
    return true;
  }

  @Override
  public boolean logout() throws LoginException {
    identity = null;
    roles = null;
    return true;
  }

  private UserJpaDao lookupDao() {
    try {
      final BeanManager beanManager = InitialContext.doLookup("java:comp/BeanManager");
      final Set<Bean<?>> beans = beanManager.getBeans(UserJpaDao.class);
      if (beans.isEmpty()) {
        throw new RuntimeException(LOOKUP_ERROR);
      }
      final Bean<?> bean = beans.iterator().next();
      final CreationalContext<?> context = beanManager.createCreationalContext(bean);
      return (UserJpaDao) beanManager.getReference(bean, UserJpaDao.class, context);
    } catch (NamingException e) {
      throw new RuntimeException(e);
    }
  }
}
