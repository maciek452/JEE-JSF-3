package pl.gda.pg.eti.kask.javaee.jsf.view;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.BrandsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.UserService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
public class ListUsers implements Serializable {

  @Inject private UserService userService;

  private Collection<User> users;

  public Collection<User> getUsers() {
    return users != null ? users : (users = userService.findAllUsers());
  }

}
