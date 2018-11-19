package pl.gda.pg.eti.kask.javaee.jsf.business.boundary;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
public class ShoeService implements Serializable {

  @PersistenceContext EntityManager em;

  public Collection<Shoe> findAllShoes(String filterParam) {
    TypedQuery<Shoe> query =
        em.createNamedQuery(Shoe.Queries.FIND_ALL_FILTERED, Shoe.class)
            .setParameter("filterParam", filterParam);
    return query.getResultList();
  }

  public Shoe findShoe(int id) {
    return em.find(Shoe.class, id);
  }

  @Transactional
  public void removeShoe(Shoe shoe) {
    shoe = em.merge(shoe);
    em.remove(shoe);
  }

  @Transactional
  public Shoe saveShoe(Shoe shoe) {
    shoe = attachShoesCollections(shoe);
    addShoe(shoe);
    return shoe;
  }

  private Shoe attachShoesCollections(Shoe shoe) {
    shoe.setShoesCollections(
        shoe.getShoesCollections()
            .stream()
            .map(shoesCollection -> findShoesCollection(shoesCollection.getId()))
            .collect(Collectors.toList()));
    return shoe;
  }

  private Shoe addShoe(Shoe shoe){
    if (shoe.getId() == null) {
      em.persist(shoe);
    } else {
      shoe = em.merge(shoe);
    }
    return shoe;
  }

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
