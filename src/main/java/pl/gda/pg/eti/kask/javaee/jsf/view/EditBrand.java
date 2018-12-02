package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.BrandsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.ShoesCollectionsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class EditBrand implements Serializable {

  @Inject private BrandsService brandsService;

  @Getter @Setter private Brand brand = new Brand();

  public String saveBrand() {
    brandsService.saveBrand(brand);
    return "list_brands?faces-redirect=true";
  }
}
