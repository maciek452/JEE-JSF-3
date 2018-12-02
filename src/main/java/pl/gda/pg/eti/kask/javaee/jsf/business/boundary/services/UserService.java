package pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.dao.UserJpaDao;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;

import static java.util.Objects.isNull;
import static pl.gda.pg.eti.kask.javaee.jsf.business.utils.CryptUtils.sha256;

@ApplicationScoped
public class UserService implements Serializable {

  @PersistenceContext EntityManager em;
  @Inject private UserJpaDao userJpaDao;

  public Collection<User> findAllUsers() {
    return em.createNamedQuery(User.Queries.FIND_ALL, User.class).getResultList();
  }

  public User findUser(int id) {
    return em.find(User.class, id);
  }

  @Transactional
  public User saveUser(User user) {
    if (user.getId() == null) {
      em.persist(user);
    } else {
      user = em.merge(user);
    }
    return user;
  }

  public void changePassword(final String oldPassword, final String newPassword) {
    if (!isNull(userJpaDao.findCurrentUser())
        && passwordsMatch(oldPassword, userJpaDao.findCurrentUser().getPassword())) {
      userJpaDao.findCurrentUser().setPassword(sha256(newPassword));
      userJpaDao.update(userJpaDao.findCurrentUser());
    }
  }

  private boolean passwordsMatch(final String oldPassword, final String newPassword) {
    return newPassword.equals(sha256(oldPassword));
  }
}
