package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
public class ListBrands implements Serializable {

  @Inject private ShoeService shoeService;

  private Collection<Brand> brands;

  public Collection<Brand> getBrands() {
    return brands != null ? brands : (brands = shoeService.findAllBrands());
  }

  public String removeBrand(Brand brand) {
    shoeService.removeBrand(brand);
    return "list_brands?faces-redirect=true";
  }
}
