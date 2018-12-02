package pl.gda.pg.eti.kask.javaee.jsf.business.control;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.Role;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.User;
import pl.gda.pg.eti.kask.javaee.jsf.business.utils.CryptUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@ApplicationScoped
public class InitialFixture {

  @PersistenceContext EntityManager em;

  @Transactional
  public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
    ShoesCollection shoesCollection1 = new ShoesCollection("SADC56", "Winter 2015");
    ShoesCollection shoesCollection2 = new ShoesCollection("JSFN96", "Summer 2015");

    Brand b1 = new Brand("Adidas");
    Brand b2 = new Brand("Nike");


    Shoe shoe1 = new Shoe("Adidas PLA55", asList(shoesCollection1), b1);
    Shoe shoe2 = new Shoe("Nike Air88", asList(shoesCollection2), b2);

    User admin = new User("admin", CryptUtils.sha256("admin"), asList(Role.OWNER, Role.USER));
    User user = new User("user", CryptUtils.sha256("user"), singletonList(Role.USER));
    User test = new User("test", CryptUtils.sha256("test"), singletonList(Role.USER));

    em.persist(b1);
    em.persist(b2);
    em.persist(shoesCollection1);
    em.persist(shoesCollection2);
    em.persist(shoe1);
    em.persist(shoe2);

    em.persist(test);
    em.persist(admin);
    em.persist(user);
  }
}
