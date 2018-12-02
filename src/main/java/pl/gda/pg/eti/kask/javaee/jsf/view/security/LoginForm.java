package pl.gda.pg.eti.kask.javaee.jsf.view.security;

import lombok.Getter;
import lombok.Setter;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

import static pl.gda.pg.eti.kask.javaee.jsf.view.security.RequestUtils.getRequest;

@Named
@ViewScoped
public class LoginForm implements Serializable {
  @Getter
  @Setter
  String login;

  @Getter
  @Setter
  String password;

  public String login() throws IOException {
    try {
      HttpServletRequest request = getRequest();
      request.login(login, password);
      return "/list_shoes.xhtml?faces-redirect=true";

    } catch (ServletException ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
