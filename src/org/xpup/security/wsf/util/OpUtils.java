package org.xpup.security.wsf.util;

import org.apache.struts.action.Action;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.security.wsf.bizsrvc.IOpControlBS;

public final class OpUtils {

  private static OpUtils instance = null;

  private IOpControlBS opControl = null;

  private OpUtils(IOpControlBS opControl) {
    this.opControl = opControl;
  }

  public static OpUtils getInstance(Action action) {
    if (instance == null) {
      IOpControlBS opControl = (IOpControlBS) BSUtils.getBusinessService(
          "securityControlBS", action);
      instance = new OpUtils(opControl);
    }
    return instance;
  }

  public void decide(String username, String opInnerName)
      throws BusinessException {
    opControl.decide(username, opInnerName);
  }
}
