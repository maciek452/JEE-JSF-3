package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeService;
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

  @Inject private ShoeService shoeService;

  @Getter @Setter private Shoe shoe = new Shoe();

  public Collection<ShoesCollection> getAvailableShoesCollections() {
    return shoeService.findAllShoesCollections();
  }

  public String saveShoe() {
    shoeService.saveShoe(shoe);
    return "list_shoes?faces-redirect=true";
  }
}
