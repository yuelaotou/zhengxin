package org.xpup.hafmis.sysfinance.bookmng.subject.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.bsinterface.ISubjectBS;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;

public class SubjectModifyAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      SubjectShowAF af=(SubjectShowAF)form;
      String sortcodeflag=request.getParameter("sortcodeflag");
      String subjectId=request.getParameter("subjectId");

      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ISubjectBS subjectBS = (ISubjectBS) BSUtils.getBusinessService(
          "subjectBS", this, mapping.getModuleConfig());
      af=subjectBS.findSubject(subjectId,securityInfo);
      
      Map sortcodeMap=BusiTools.listBusiProperty(BusiConst.SUBCATEGORYCODE);
      af.setSortcodeMap(sortcodeMap);
      Map propertyMap=BusiTools.listBusiProperty(BusiConst.SUBATTRIBUTE);
      af.setPropertyMap(propertyMap);
      af.setActionflag("1");
      af.setSortcodeflag(sortcodeflag);
      
      request.setAttribute("subjectShowAF", af);
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_subject_modify");
  }

}
