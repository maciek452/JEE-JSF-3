package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.ShoesService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
public class ListShoes implements Serializable {

  @Inject private ShoesService shoesService;

  private Collection<Shoe> shoes;

  @Getter @Setter private String filterParam = "";

  public Collection<Shoe> getShoes() {
    return shoes != null ? shoes : (shoes = shoesService.findAllShoes(filterParam));
  }

  public String removeShoe(Shoe shoe) {
    shoesService.removeShoe(shoe);
    return "list_shoes?faces-redirect=true";
  }
}
