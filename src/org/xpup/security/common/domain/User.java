package org.xpup.security.common.domain;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import net.sf.acegisecurity.GrantedAuthority;
import net.sf.acegisecurity.GrantedAuthorityImpl;
import net.sf.acegisecurity.UserDetails;

import org.xpup.common.domain.DomainObject;

public class User extends DomainObject implements UserDetails {

  private static final long serialVersionUID = -6445706423278973893L;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRoles(Set roles) {
    this.roles = roles;
  }

  public Set getRoles() {
    return roles;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public boolean isAccountNonLocked() {
    return !isLocked();
  }

  public GrantedAuthority[] getAuthorities() {
    return auths;
  }

  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void addRole(Role role) {
    roles.add(role);
  }

  public boolean removeRole(Role role) {
    boolean successful = false;
    Iterator it = roles.iterator();
    for (int i = 0; i < roles.size(); i++) {
      Role theRole = (Role) it.next();
      if (theRole.equals(role)) {
        roles.remove(theRole);
        i++;
        successful = true;
        break;
      }
    }
    return successful;
  }

  private String username = null;

  private String password = null;

  private Set roles = new LinkedHashSet();

  private boolean enabled = true;

  private boolean locked = false;

  private boolean accountNonExpired = true;

  private boolean credentialsNonExpired = true;

  private GrantedAuthority[] auths = { new GrantedAuthorityImpl("ROLE_SOME") };
}
