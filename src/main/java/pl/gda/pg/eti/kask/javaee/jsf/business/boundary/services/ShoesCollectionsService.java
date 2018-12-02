package pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;

@ApplicationScoped
public class ShoesCollectionsService implements Serializable {

  @PersistenceContext EntityManager em;

  public Collection<ShoesCollection> findAllShoesCollections() {
    TypedQuery<ShoesCollection> query =
        em.createNamedQuery(ShoesCollection.Queries.FIND_ALL, ShoesCollection.class);
    return query.getResultList();
  }

  public ShoesCollection findShoesCollection(int id) {
    return em.find(ShoesCollection.class, id);
  }

  @Transactional
  public void removeShoesCollection(ShoesCollection shoesCollection) {
    shoesCollection = em.merge(shoesCollection);
    em.remove(shoesCollection);
  }

  @Transactional
  public ShoesCollection saveShoesCollection(ShoesCollection shoesCollection) {
    if (shoesCollection.getId() == null) {
      em.persist(shoesCollection);
    } else {
      shoesCollection = em.merge(shoesCollection);
    }
    return shoesCollection;
  }
}
