package pl.gda.pg.eti.kask.javaee.jsf.view.security;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.login.AuthAspect;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.Role;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

import static pl.gda.pg.eti.kask.javaee.jsf.view.security.RequestUtils.getRequest;

@Named
@RequestScoped
public class Authentication implements Serializable {
  @Inject private AuthAspect authAspect;

  public boolean isAuthenticated() {
    return authAspect.isAuthenticated();
  }

  public boolean isNotAuthenticated() {
    return !isAuthenticated();
  }

  public boolean isAdmin(){
    return authAspect.hasRole(Role.OWNER);
  }

  public String logout() throws ServletException, IOException {
    HttpServletRequest request = getRequest();
    request.logout();
    request.getSession().invalidate();
    return "/login_form.xhtml?faces-redirect=true";
  }
}
