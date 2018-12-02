package pl.gda.pg.eti.kask.javaee.jsf.business.entities.security;

public enum Role {
  OWNER("OWNER"),
  USER("USER");

  private String role;

  Role(String role){
    this.role = role;
  }

  public String getRole() {
    return role;
  }
}
