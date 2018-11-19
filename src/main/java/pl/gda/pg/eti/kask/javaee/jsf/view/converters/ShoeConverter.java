package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;

import javax.enterprise.context.Dependent;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Shoe.class, managed = true)
@Dependent
public class ShoeConverter extends AbstractEntityConverter<Shoe> {

  public ShoeConverter() {
    super(Shoe::getId, ShoeService::findShoe);
  }
}
