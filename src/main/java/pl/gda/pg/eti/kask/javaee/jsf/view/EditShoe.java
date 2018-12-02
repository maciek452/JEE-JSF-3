package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.BrandsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.ShoesCollectionsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.ShoesService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@ViewScoped
public class EditShoe implements Serializable {

  @Inject private ShoesService shoesService;
  @Inject private ShoesCollectionsService shoesCollectionsService;
  @Inject private BrandsService brandsService;

  @Getter @Setter private Shoe shoe = new Shoe();

  public Collection<ShoesCollection> getAvailableShoesCollections() {
    return shoesCollectionsService.findAllShoesCollections();
  }

  public Collection<Brand> getAvailableBrands() {
    return brandsService.findAllBrands();
  }

  public String saveShoe() {
    shoesService.saveShoe(shoe);
    return "list_shoes?faces-redirect=true";
  }
}
