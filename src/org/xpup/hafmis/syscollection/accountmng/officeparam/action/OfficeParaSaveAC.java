package org.xpup.hafmis.syscollection.accountmng.officeparam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.officeparam.bsinterface.IOfficeParaBS;
import org.xpup.hafmis.syscollection.accountmng.officeparam.dto.OfficeParaDTO;
import org.xpup.hafmis.syscollection.accountmng.officeparam.form.OfficeParaAF;


public class OfficeParaSaveAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      OfficeParaAF officeParaAF=(OfficeParaAF)form;
      OfficeParaDTO officeParaDTO=officeParaAF.getOfficeParaDTO();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IOfficeParaBS officeParaBS = (IOfficeParaBS) BSUtils
      .getBusinessService("officeParaBS", this, mapping.getModuleConfig());
      officeParaBS.saveCollLoanbackParaInfo(officeParaDTO, securityInfo);
      request.getSession().setAttribute("office",officeParaDTO.getOffice());
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_officepara_show");
  }
}
