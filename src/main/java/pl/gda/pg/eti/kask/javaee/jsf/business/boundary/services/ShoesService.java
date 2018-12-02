package pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
public class ShoesService implements Serializable {

  @PersistenceContext EntityManager em;

  @Inject private BrandsService brandsService;
  @Inject private ShoesCollectionsService shoesCollectionsService;

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
            .map(
                shoesCollection ->
                    shoesCollectionsService.findShoesCollection(shoesCollection.getId()))
            .collect(Collectors.toList()));
    shoe.setBrand(brandsService.findBrand(shoe.getBrand().getId()));
    return shoe;
  }

  private Shoe addShoe(Shoe shoe) {
    if (shoe.getId() == null) {
      em.persist(shoe);
    } else {
      shoe = em.merge(shoe);
    }
    return shoe;
  }
}
