package org.xpup.hafmis.sysloan.dataready.collloanbackpara.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.collloanbackpara.bsinterface.ICollLoanbackParaBS;
import org.xpup.hafmis.sysloan.dataready.collloanbackpara.dto.CollLoanbackParaDTO;
import org.xpup.hafmis.sysloan.dataready.collloanbackpara.form.CollLoanbackParaAF;


public class CollLoanbackParaSaveAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      CollLoanbackParaAF collLoanbackParaAF=(CollLoanbackParaAF)form;
      CollLoanbackParaDTO collLoanbackParaDTO=collLoanbackParaAF.getCollLoanbackParaDTO();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICollLoanbackParaBS collLoanbackParaBS = (ICollLoanbackParaBS) BSUtils
      .getBusinessService("collLoanbackParaBS", this, mapping.getModuleConfig());
      collLoanbackParaBS.saveCollLoanbackParaInfo(collLoanbackParaDTO, securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("collloanbackpara_show");
  }
}
