package org.xpup.hafmis.sysfinance.bookmng.settleincanddec.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.settleincanddec.bsinterface.ISettleincanddecBS;

public class SettleincanddecSaveAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Integer count = new Integer(0);
      String subjectCode = "";
      String bySubjectDirection = "";
      subjectCode = (String) request.getParameter("subjectCode");
      if (subjectCode != null && !"".equals(subjectCode.trim())) {
        subjectCode = (String) request.getParameter("subjectCode");
      }
      String bySubjectCode = "";
      bySubjectCode = (String) request.getParameter("bySubjectCode");
      if (bySubjectCode != null && !"".equals(bySubjectCode.trim())) {
        bySubjectCode = (String) request.getParameter("bySubjectCode");
      }
      BusinessException be = null;
      ISettleincanddecBS settleincanddecBS = (ISettleincanddecBS) BSUtils
          .getBusinessService("settleincanddecBS", this, mapping
              .getModuleConfig());
      settleincanddecBS
          .findSubjectrelationParentId(bySubjectCode, securityInfo);
      settleincanddecBS.findSubjectrelationParentId1(subjectCode, securityInfo);
      count = settleincanddecBS.findSettleIncAndDecInfoExist(bySubjectCode,
          securityInfo);
      if (count.intValue() != 0) {
        be = new BusinessException("待结转科目代码重复，请重新输入！");
        throw be;
      }
      bySubjectDirection = settleincanddecBS
          .findSettleIncAndDecSubjectDirection(bySubjectCode, securityInfo);
      settleincanddecBS.saveSettleIncAndDec(bySubjectCode, subjectCode,
          bySubjectDirection, securityInfo);
      request.setAttribute("type", "1");
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_show_settleincanddec");
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_show_settleincanddec");
  }
}
