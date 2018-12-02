package pl.gda.pg.eti.kask.javaee.jsf.view.converters;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.UserService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.User;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

@FacesConverter(forClass = User.class, managed = true)
@Dependent
public class UsersConverter implements Converter<User> {

  @Inject private UserService userService;

  @Override
  public User getAsObject(FacesContext context, UIComponent component, String value) {
    User entity = userService.findUser(Integer.parseInt(value));
    if (entity == null) {
      context.getExternalContext().setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
      context.responseComplete();
    }
    return entity;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, User user) {
    final Integer id = user.getId();
    return id != null ? id.toString() : null;
  }
}
