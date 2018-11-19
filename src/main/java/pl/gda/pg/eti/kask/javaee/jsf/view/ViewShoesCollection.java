package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class ViewShoesCollection implements Serializable {

  @Getter @Setter private ShoesCollection shoesCollection;
}
