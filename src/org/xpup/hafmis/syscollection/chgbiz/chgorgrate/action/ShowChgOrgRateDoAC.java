package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.form.ChgOrgRateDoAF;

public class ShowChgOrgRateDoAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      
      ChgOrgRateDoAF af=(ChgOrgRateDoAF)form;
      ChgOrgRateDoAF doMaintain=(ChgOrgRateDoAF)request.getAttribute("chgOrgRateDoFromMaintainAF");
      
//    吴洪涛修改 2008-3-18 单位_汇缴比例调整
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      int isOrgEdition = securityInfo.getIsOrgEdition();
      if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG)// 为单位版{
      {
        if(doMaintain!=null){
          doMaintain.getChgOrgRate().setOrgEdition("yes");
          request.setAttribute("chgOrgRateDoAF", doMaintain);
        }else{
          af.setType("1");
          af=new ChgOrgRateDoAF();
          request.setAttribute("chgOrgRateDoAF", af);
        }
      }else{
      
      
      if(doMaintain!=null){
        request.setAttribute("chgOrgRateDoAF", doMaintain);
      }else{
        af.setType("1");
        af=new ChgOrgRateDoAF();
        request.setAttribute("chgOrgRateDoAF", af);
      }
      }

//    吴洪涛修改 2008-3-18 单位_汇缴比例调整
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_chgorgrateDo_sure");
  }

}
