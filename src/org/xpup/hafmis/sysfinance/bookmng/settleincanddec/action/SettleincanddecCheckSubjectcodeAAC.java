package org.xpup.hafmis.sysfinance.bookmng.settleincanddec.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.settleincanddec.bsinterface.ISettleincanddecBS;

public class SettleincanddecCheckSubjectcodeAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text=null;
    try {
      String subjectCode = request.getParameter("subject");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ISettleincanddecBS settleincanddecBS = (ISettleincanddecBS) BSUtils
      .getBusinessService("settleincanddecBS", this, mapping
          .getModuleConfig());
      settleincanddecBS.findSubjectRelationFirstCode(subjectCode, securityInfo);     
      response.getWriter().close();     
    }catch (BusinessException e) {
      e.printStackTrace();
      text = "backErrors('" + e.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
}