package pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;
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
public class BrandsService implements Serializable {

  @PersistenceContext EntityManager em;

  public Collection<Brand> findAllBrands() {
    TypedQuery<Brand> query =
            em.createNamedQuery(Brand.Queries.FIND_ALL, Brand.class);
    return query.getResultList();
  }

  public Brand findBrand(int id) {
    return em.find(Brand.class, id);
  }

  @Transactional
  public void removeBrand(Brand brand) {
    brand = em.merge(brand);
    em.remove(brand);
  }

  @Transactional
  public Brand saveBrand(Brand brand) {
    if (brand.getId() == null) {
      em.persist(brand);
    } else {
      brand = em.merge(brand);
    }
    return brand;
  }

}
