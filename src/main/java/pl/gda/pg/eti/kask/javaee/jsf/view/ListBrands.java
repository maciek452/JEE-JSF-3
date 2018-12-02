package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.BrandsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.ShoesCollectionsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
public class ListBrands implements Serializable {

  @Inject private BrandsService brandsService;

  private Collection<Brand> brands;

  public Collection<Brand> getBrands() {
    return brands != null ? brands : (brands = brandsService.findAllBrands());
  }

  public String removeBrand(Brand brand) {
    brandsService.removeBrand(brand);
    return "list_brands?faces-redirect=true";
  }
}
