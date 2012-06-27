/**
 * 
 */
package org.xpup.security.wsf.acegiex;

import net.sf.acegisecurity.UserDetails;
import net.sf.acegisecurity.providers.dao.AuthenticationDao;
import net.sf.acegisecurity.providers.dao.UsernameNotFoundException;

import org.springframework.dao.DataAccessException;
import org.xpup.security.common.dao.UserDAO;

/**
 * @author $Author$
 * @version $Revision$,$Date$
 */
public class AuthenticationDaoImpl implements AuthenticationDao {

  /*
   * (non-Javadoc)
   * 
   * @see net.sf.acegisecurity.providers.dao.AuthenticationDao#loadUserByUsername(java.lang.String)
   */
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {
    UserDetails user = userDAO.queryByUsername(username);

    if (user == null) {
      // User not found
      throw new UsernameNotFoundException("该用户不存在！");
    }

    if (user.getAuthorities().length == 0) {
      // User has no GrantedAuthority
      throw new UsernameNotFoundException("用户没有被授权!");
    }
    return user;
  }

  private UserDAO userDAO = null;

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }
}