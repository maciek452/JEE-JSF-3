package pl.gda.pg.eti.kask.javaee.jsf.business.boundary.login;

import pl.gda.pg.eti.kask.javaee.jsf.business.entities.security.Role;

import java.security.Principal;
import java.security.acl.Group;
import java.util.*;

public class RolesGroup implements Group {

  Set<Principal> roles = new HashSet<>();

  public RolesGroup(Collection<Role> roleNames) {
    roleNames.forEach(role -> roles.add(role::getRole));
  }

  @Override
  public String getName() {
    return "Roles";
  }

  @Override
  public boolean addMember(Principal principal) {
    return roles.add(principal);
  }

  @Override
  public boolean removeMember(Principal principal) {
    return roles.remove(principal);
  }

  @Override
  public boolean isMember(Principal principal) {
    return roles.contains(principal);
  }

  @Override
  public Enumeration<? extends Principal> members() {
    return Collections.enumeration(roles);
  }
}