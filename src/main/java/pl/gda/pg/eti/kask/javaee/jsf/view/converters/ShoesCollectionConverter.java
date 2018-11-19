package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.enterprise.context.Dependent;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = ShoesCollection.class, managed = true)
@Dependent
public class ShoesCollectionConverter extends AbstractEntityConverter<ShoesCollection> {

  public ShoesCollectionConverter() {
    super(ShoesCollection::getId, ShoeService::findShoesCollection);
  }
}
