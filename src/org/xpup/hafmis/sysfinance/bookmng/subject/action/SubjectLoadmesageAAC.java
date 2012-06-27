package org.xpup.hafmis.sysfinance.bookmng.subject.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.bsinterface.ISubjectBS;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;

public class SubjectLoadmesageAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {

      String subjectcode = request.getParameter("subjectcode");
      String sortcodeflag = request.getParameter("sortcodeflag");
      SubjectShowAF af=new SubjectShowAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ISubjectBS subjectBS = (ISubjectBS) BSUtils.getBusinessService("subjectBS", this, mapping.getModuleConfig());
      af.setCode(subjectcode);
      subjectBS.getSubjectMessage(af,securityInfo);
      request.getSession().setAttribute("addsubjectloadmessage", af);

      String text=null;
      text="display('"+ af.getCode()+"','"+ af.getName()+"','"+ af.getDirection() +"','"+ af.getSortcode() +"','"+ af.getProperty()+"','"+ af.getActionflag()+"','"+sortcodeflag+"')";
      response.getWriter().write(text); 
      response.getWriter().close();

    }catch(BusinessException be){
      String text="backErrors('"+be.getLocalizedMessage()+"')";
      response.getWriter().write(text);
      response.getWriter().close();  
      return null;     
    }
    return null;
  }
}