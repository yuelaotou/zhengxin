package org.xpup.hafmis.syscollection.peoplebank.param.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.peoplebank.param.bsinterface.IParamBS;
import org.xpup.hafmis.syscollection.peoplebank.param.dto.ParamDTO;
import org.xpup.hafmis.syscollection.peoplebank.param.form.ParamAF;




public class ParamSaveAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ParamAF paramAF=(ParamAF)form;
      ParamDTO paramDTO=paramAF.getParamDTO();
      IParamBS paramBS = (IParamBS) BSUtils
      .getBusinessService("paramBS", this, mapping.getModuleConfig());
      paramBS.saveParamInfo(paramDTO,securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("param_show");
  }
}
