package pl.gda.pg.eti.kask.javaee.jsf.view.security;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
  public static HttpServletRequest getRequest() {
    HttpServletRequest request =
        (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    return request;
  }
}
