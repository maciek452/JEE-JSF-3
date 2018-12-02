package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.ShoesService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Shoe;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

@FacesConverter(forClass = Shoe.class, managed = true)
@Dependent
public class ShoeConverter implements Converter<Shoe> {

  @Inject private ShoesService shoesService;

  @Override
  public Shoe getAsObject(FacesContext context, UIComponent component, String value) {
    Shoe entity = shoesService.findShoe(Integer.parseInt(value));

    if (entity == null) {
      context.getExternalContext().setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
      context.responseComplete();
    }
    return entity;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Shoe shoe) {
    final Integer id = shoe.getId();
    return id != null ? id.toString() : null;
  }
}
