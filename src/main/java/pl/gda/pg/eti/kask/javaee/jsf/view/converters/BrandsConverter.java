package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.ShoeService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;

import javax.enterprise.context.Dependent;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Brand.class, managed = true)
@Dependent
public class BrandsConverter extends AbstractEntityConverter<Brand> {

  public BrandsConverter() {
    super(Brand::getId, ShoeService::findBrand);
  }
}
