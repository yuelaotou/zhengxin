package org.xpup.hafmis.sysloan.dataready.param.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.param.bsinterface.IParamBS;
import org.xpup.hafmis.sysloan.dataready.param.dto.AheadReturnParamDTO;
import org.xpup.hafmis.sysloan.dataready.param.form.AheadReturnParamAF;
public class AheadReturnParamSaveAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      AheadReturnParamAF aheadReturnParamAF=(AheadReturnParamAF)form;
      AheadReturnParamDTO aheadReturnParamDTO=aheadReturnParamAF.getAheadReturnParamDTO();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IParamBS paramBS = (IParamBS) BSUtils
      .getBusinessService("paramBS", this, mapping.getModuleConfig());
      paramBS.saveAheadReturnParamInfo(aheadReturnParamDTO, securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("aheadreturnparam_show");
  }
}
