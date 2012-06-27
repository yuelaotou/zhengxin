package org.xpup.hafmis.syscollection.chgbiz.autochangeparam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.autochangeparam.bsinterface.IAutoChangeParamBS;
import org.xpup.hafmis.syscollection.chgbiz.autochangeparam.form.AutoChangeParamAF;

/**
 * Copy Right Information : 保存自动变更参数的Action Goldsoft Project :
 * AutoChangeParamSaveAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.3.17
 */
public class AutoChangeParamSaveAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {
      AutoChangeParamAF autoChangeParamAF = (AutoChangeParamAF) form;

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      IAutoChangeParamBS officeParaBS = (IAutoChangeParamBS) BSUtils
          .getBusinessService("autoChangeParamBS", this, mapping
              .getModuleConfig());
      
      officeParaBS.saveAutoChangeParam(autoChangeParamAF
          .getAutoChangeParamDTO(), securityInfo);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_autoChangeParamShowAC");
  }
}
