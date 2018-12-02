package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.ShoesCollectionsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.ShoesCollection;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

@FacesConverter(forClass = ShoesCollection.class, managed = true)
@Dependent
public class ShoesCollectionConverter implements Converter<ShoesCollection> {
  @Inject private ShoesCollectionsService shoesCollectionsService;

  @Override
  public ShoesCollection getAsObject(FacesContext context, UIComponent component, String value) {
    ShoesCollection entity = shoesCollectionsService.findShoesCollection(Integer.parseInt(value));

    if (entity == null) {
      context.getExternalContext().setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
      context.responseComplete();
    }
    return entity;
  }

  @Override
  public String getAsString(
      FacesContext context, UIComponent component, ShoesCollection shoesCollection) {
    final Integer id = shoesCollection.getId();
    return id != null ? id.toString() : null;
  }
}
