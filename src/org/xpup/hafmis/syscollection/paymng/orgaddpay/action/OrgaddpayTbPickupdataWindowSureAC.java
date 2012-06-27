package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTbPickupdataWindowAF;

public class OrgaddpayTbPickupdataWindowSureAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    try{
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      OrgaddpayTbPickupdataWindowAF af=(OrgaddpayTbPickupdataWindowAF)form;
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
      String addpayDateSt=request.getParameter("addpayDateSt");
      String addpayDateEnd=request.getParameter("addpayDateEnd");
      String noteNumB=request.getParameter("noteNumB");
      af.setAddpayDateSt(addpayDateSt);
      af.setAddpayDateEnd(addpayDateEnd);
      af.setNoteNumB(noteNumB);
      orgaddpayBS.addpayByNotenum(af, securityInfo);
      String paginationKey = getPaginationKey();
      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
      pagination.getQueryCriterions().put("id", af.getOrgId());
      pagination.getQueryCriterions().put("name", af.getOrgName());
    }catch (BusinessException b) {
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
      return mapping.findForward("show_orgaddpaytb");
    }catch(Exception e){
      e.printStackTrace();
      return mapping.findForward("show_orgaddpaytb");
    }
    return mapping.findForward("show_orgaddpayta");
  }
  protected String getPaginationKey() {
    return OrgaddpayTaShowAC.PAGINATION_KEY;
 }
}
