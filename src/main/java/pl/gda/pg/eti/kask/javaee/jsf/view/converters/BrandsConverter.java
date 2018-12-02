package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.BrandsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

@FacesConverter(forClass = Brand.class, managed = true)
@Dependent
public class BrandsConverter implements Converter<Brand> {

  @Inject private BrandsService brandsService;

  @Override
  public Brand getAsObject(FacesContext context, UIComponent component, String value) {
    Brand entity = brandsService.findBrand(Integer.parseInt(value));

    if (entity == null) {
      context.getExternalContext().setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
      context.responseComplete();
    }
    return entity;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Brand brand) {
    final Integer id = brand.getId();
    return id != null ? id.toString() : null;
  }
}
