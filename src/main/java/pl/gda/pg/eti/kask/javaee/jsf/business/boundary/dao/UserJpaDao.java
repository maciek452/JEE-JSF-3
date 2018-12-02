package pl.gda.pg.eti.kask.javaee.jsf.business.boundary.dao;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.User;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class UserJpaDao extends JpaDao<User> {
  private static final String FIND_BY_LOGIN_QUERY = "SELECT u FROM User u WHERE u.login = :login";
  private static final String LOGIN_PARAM = "login";

  @Resource
  private SessionContext sessionCtx;

  public UserJpaDao(){
    super(User.class);
  }

  public User findByLogin(final String login){
    return entityManager.createQuery(FIND_BY_LOGIN_QUERY, User.class)
        .setParameter(LOGIN_PARAM, login)
        .getSingleResult();
  }

  public User findCurrentUser() {
    final String login = sessionCtx.getCallerPrincipal().getName();
    return findByLogin(login);
  }
}
