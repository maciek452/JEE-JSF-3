package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.ShoesCollectionsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class EditShoesCollection implements Serializable {

  @Inject private ShoesCollectionsService shoesCollectionsService;

  @Getter @Setter private ShoesCollection shoesCollection = new ShoesCollection();

  public String saveShoesCollection() {
    shoesCollectionsService.saveShoesCollection(shoesCollection);
    return "list_shoes_collections?faces-redirect=true";
  }
}
