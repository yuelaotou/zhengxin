package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.bsinterface.ISubjectrelationBS;


public class SubjectRelationTbMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.issuenotice.action.IssuenoticeTbShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.delete", "delete");
    map.put("button.deleteall", "deleteall");
    return map;
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF idAF = (IdAF) form;
      String subjectreleid = idAF.getId().toString();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ISubjectrelationBS subjectrelationBS = (ISubjectrelationBS) BSUtils
          .getBusinessService("subjectrelationBS", this, mapping
              .getModuleConfig());
      subjectrelationBS.deleteISubjectrelationTb(subjectreleid,securityInfo.getBookId());
      subjectrelationBS.subjectrelationTaFnOperateLog(securityInfo,String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_show_subjectrelationtb");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_show_subjectrelationtb");
  }

  public ActionForward deleteall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List subejectRelationTbCountList=null;
    subejectRelationTbCountList=(List)request.getSession().getAttribute("subejectRelationTbCountList");
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    try {
      ISubjectrelationBS subjectrelationBS = (ISubjectrelationBS) BSUtils
      .getBusinessService("subjectrelationBS", this, mapping
          .getModuleConfig());      
      subjectrelationBS.deleteAllSubjectrelationTb(subejectRelationTbCountList,securityInfo.getBookId());
      subjectrelationBS.subjectrelationTaFnOperateLog(securityInfo,String.valueOf(BusiLogConst.BIZLOG_ACTION_DELETE));
    } catch (BusinessException e) {
      e.printStackTrace();
      ActionMessages messages = null;
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
      return mapping.findForward("to_show_subjectrelationtb");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_show_subjectrelationtb");
  }

}
