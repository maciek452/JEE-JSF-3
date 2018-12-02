package pl.gda.pg.eti.kask.javaee.jsf.view.security;

import lombok.Getter;
import lombok.Setter;
import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.services.UserService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ChangePassword implements Serializable {

  @Inject private UserService userService;
  @Getter @Setter private String oldPassword;
  @Getter @Setter private String newPassword;

  public String proceed() {
    userService.changePassword(oldPassword, newPassword);
    return "login_form?faces-redirect=true";
  }
}
