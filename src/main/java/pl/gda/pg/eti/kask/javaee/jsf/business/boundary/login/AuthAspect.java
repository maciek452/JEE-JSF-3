package pl.gda.pg.eti.kask.javaee.jsf.business.boundary.login;

import pl.gda.pg.eti.kask.javaee.jsf.business.boundary.dao.UserJpaDao;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.Role;
import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;

@Named
@SessionScoped
public class AuthAspect implements Serializable {

  @EJB
  private UserJpaDao dao;

  private ExternalContext getExternalContext() {
    return FacesContext.getCurrentInstance().getExternalContext();
  }

  public Principal getUserPrincipal() {
    return getExternalContext().getUserPrincipal();
  }

  public boolean hasRole(Role role) {
    return getExternalContext().isUserInRole(role.getRole());
  }

  public boolean isAuthenticated() {
    return getUserPrincipal() != null;
  }

  public String getUserLogin() {
    return getUserPrincipal().getName();
  }

  public User getCurrentUser() {
    return dao.findCurrentUser();
  }
}
