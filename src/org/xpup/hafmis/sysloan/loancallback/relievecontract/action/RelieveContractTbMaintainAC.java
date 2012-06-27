package org.xpup.hafmis.sysloan.loancallback.relievecontract.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.bsinterface.IRelieveContractBS;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractPrintDTO;

public class RelieveContractTbMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.print", "print");
    return map;
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      IdAF idAF = (IdAF)form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IRelieveContractBS relieveContractBS = (IRelieveContractBS) BSUtils
      .getBusinessService("relieveContractBS", this, mapping.getModuleConfig());
      relieveContractBS.deleteRelieveContractTb(idAF.getId().toString(),securityInfo);
      }catch(Exception e){
        e.printStackTrace();
      }
      return mapping.findForward("relievecontracttb_show");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IdAF idAF = (IdAF)form;
    IRelieveContractBS relieveContractBS = (IRelieveContractBS) BSUtils
    .getBusinessService("relieveContractBS", this, mapping.getModuleConfig());
    RelieveContractPrintDTO relieveContractPrintDTO=relieveContractBS.findRelieveContractTbPrintInfo(idAF.getId().toString());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    request.setAttribute("relieveContractPrintDTO", relieveContractPrintDTO);
    request.setAttribute("bizDate", securityInfo.getUserInfo().getPlbizDate());
    return mapping.findForward("to_relievecontracttb_print");
  }
}
