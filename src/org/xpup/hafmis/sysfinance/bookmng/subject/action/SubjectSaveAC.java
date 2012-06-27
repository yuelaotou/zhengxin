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
import org.apache.struts.actions.DispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.bsinterface.ISubjectBS;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;

public class SubjectSaveAC extends DispatchAction {

  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages message=null;
    SubjectShowAF af = (SubjectShowAF) form;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ISubjectBS subjectBS = (ISubjectBS) BSUtils.getBusinessService(
          "subjectBS", this, mapping.getModuleConfig());

      SubjectShowAF subjectShowAF=new SubjectShowAF();
      subjectShowAF=(SubjectShowAF)request.getSession().getAttribute("addsubjectloadmessage");
      if(!subjectShowAF.getDirection().equals("")){
        subjectShowAF.setName(af.getName());
        subjectBS.saveSubject(subjectShowAF,securityInfo);
      }else{
        subjectBS.saveSubject(af,securityInfo);
      }

      SubjectShowAF subjectShowAF1=new SubjectShowAF();
      subjectShowAF1.setSortcodeflag(af.getSortcodeflag());
      subjectShowAF1.setActionflag("0");
      Map sortcodeMap=BusiTools.listBusiProperty(BusiConst.SUBCATEGORYCODE);
      subjectShowAF1.setSortcodeMap(sortcodeMap);
      Map propertyMap = new HashMap();
      propertyMap.put(BusiConst.SUBATTRIBUTE_OTHERS,"其他");
      propertyMap.put(BusiConst.SUBATTRIBUTE_CASH,"现金");
      propertyMap.put(BusiConst.SUBATTRIBUTE_BANK,"银行");
      subjectShowAF1.setPropertyMap(propertyMap);
      request.setAttribute("subjectShowAF", subjectShowAF1);
      
    } catch (BusinessException bex) {
      bex.printStackTrace();
      message= new ActionMessages();
      message.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getLocalizedMessage().toString(), false));
      saveErrors(request,message);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return mapping.findForward("to_subject_save");
  }

  public ActionForward modify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    SubjectShowAF af = (SubjectShowAF) form;
    try {
      String sortcodeflag=af.getSortcodeflag();

      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ISubjectBS subjectBS = (ISubjectBS) BSUtils.getBusinessService(
          "subjectBS", this, mapping.getModuleConfig());
      subjectBS.updateSubject(af,securityInfo);
      
      af=subjectBS.findSubject(af.getId(),securityInfo);
      
      Map sortcodeMap=BusiTools.listBusiProperty(BusiConst.SUBCATEGORYCODE);
      af.setSortcodeMap(sortcodeMap);
      Map propertyMap=BusiTools.listBusiProperty(BusiConst.SUBATTRIBUTE);
      af.setPropertyMap(propertyMap);
      af.setActionflag("1");
      af.setSortcodeflag(sortcodeflag);
      
      request.setAttribute("subjectShowAF", af);
      
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_subject_modify");
  }

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    SubjectShowAF af = (SubjectShowAF) form;

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
