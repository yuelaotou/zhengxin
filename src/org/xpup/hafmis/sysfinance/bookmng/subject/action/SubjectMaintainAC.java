package org.xpup.hafmis.sysfinance.bookmng.subject.action;

import java.util.HashMap;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.bsinterface.ISubjectBS;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;

public class SubjectMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.bookmng.subject.action.SubjectTaShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.delete", "delete");
    map.put("button.canceled", "canceled");
    map.put("button.canceled.quash", "canceledquash");
    return map;
  }

  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SubjectShowAF af = (SubjectShowAF) form;

      Map sortcodeMap = BusiTools.listBusiProperty(BusiConst.SUBCATEGORYCODE);
      af.setSortcodeMap(sortcodeMap);
      // Map propertyMap=BusiTools.listBusiProperty(BusiConst.SUBATTRIBUTE);
      Map propertyMap = new HashMap();
      propertyMap.put(BusiConst.SUBATTRIBUTE_OTHERS, "其他");
      propertyMap.put(BusiConst.SUBATTRIBUTE_CASH, "现金");
      propertyMap.put(BusiConst.SUBATTRIBUTE_BANK, "银行");
      af.setPropertyMap(propertyMap);
      af.setActionflag("0");

      request.setAttribute("subjectShowAF", af);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_subject_add");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages message = null;
    SubjectShowAF af = (SubjectShowAF) form;
    try {
      String id = af.getRadioname();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ISubjectBS subjectBS = (ISubjectBS) BSUtils.getBusinessService(
          "subjectBS", this, mapping.getModuleConfig());
      subjectBS.deleteSubject(id, securityInfo);

    } catch (BusinessException bex) {
      bex.printStackTrace();
      message = new ActionMessages();
      message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, message);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    if (af.getSortcodeflag().equals("0")) {
      return mapping.findForward("to_subject_showTa");
    } else if (af.getSortcodeflag().equals("1")) {
      return mapping.findForward("to_subject_showTb");
    } else if (af.getSortcodeflag().equals("2")) {
      return mapping.findForward("to_subject_showTc");
    } else if (af.getSortcodeflag().equals("3")) {
      return mapping.findForward("to_subject_showTd");
    } else
      return mapping.findForward("to_subject_showTe");
  }

  public ActionForward canceled(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages message = null;
    SubjectShowAF af = (SubjectShowAF) form;
    try {
      String id = af.getRadioname();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ISubjectBS subjectBS = (ISubjectBS) BSUtils.getBusinessService(
          "subjectBS", this, mapping.getModuleConfig());
      subjectBS.canceledSubject(id, securityInfo);

    } catch (BusinessException bex) {
      bex.printStackTrace();
      message = new ActionMessages();
      message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, message);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    if (af.getSortcodeflag().equals("0")) {
      return mapping.findForward("to_subject_showTa");
    } else if (af.getSortcodeflag().equals("1")) {
      return mapping.findForward("to_subject_showTb");
    } else if (af.getSortcodeflag().equals("2")) {
      return mapping.findForward("to_subject_showTc");
    } else if (af.getSortcodeflag().equals("3")) {
      return mapping.findForward("to_subject_showTd");
    } else
      return mapping.findForward("to_subject_showTe");
  }

  public ActionForward canceledquash(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages message = null;
    SubjectShowAF af = (SubjectShowAF) form;
    try {
      String id = af.getRadioname();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ISubjectBS subjectBS = (ISubjectBS) BSUtils.getBusinessService(
          "subjectBS", this, mapping.getModuleConfig());
      subjectBS.canceledquashSubject(id, securityInfo);

    } catch (BusinessException bex) {
      bex.printStackTrace();
      message = new ActionMessages();
      message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, message);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    if (af.getSortcodeflag().equals("0")) {
      return mapping.findForward("to_subject_showTa");
    } else if (af.getSortcodeflag().equals("1")) {
      return mapping.findForward("to_subject_showTb");
    } else if (af.getSortcodeflag().equals("2")) {
      return mapping.findForward("to_subject_showTc");
    } else if (af.getSortcodeflag().equals("3")) {
      return mapping.findForward("to_subject_showTd");
    } else
      return mapping.findForward("to_subject_showTe");
  }

}
