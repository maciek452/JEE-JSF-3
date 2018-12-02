package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.BrandsService;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.UserService;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.Brand;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.Role;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.User;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

@Named
@ViewScoped
public class EditUser implements Serializable {

  @Inject private UserService userService;

  @Getter @Setter private User user = new User();

  public Collection<Role> getAvaibleRoles(){
    return Arrays.asList(Role.values());
  }

  public String saveUser() {
    userService.saveUser(user);
    return "list_users?faces-redirect=true";
  }
}
