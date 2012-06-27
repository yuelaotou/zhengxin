package org.xpup.security.wsf.util;

import org.apache.struts.action.Action;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.security.common.domain.User;
import org.xpup.security.wsf.bizsrvc.IUserControlBS;

public final class UserUtils {

  private IUserControlBS control = null;

  private UserUtils(Action action) {
    control = (IUserControlBS) BSUtils.getBusinessService("securityControlBS",
        action);
  }

  public User findUserByUsername(String username) throws BusinessException {
    return control.findUserbyUsername(username);
  }

  private static UserUtils instance = null;

  public static UserUtils getInstance(Action action) {
    if (instance == null) {
      instance = new UserUtils(action);
    }
    return instance;
  }
}
