package org.xpup.hafmis.sysfinance.bookmng.credencemodle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.bsinterface.ICredencemodleBS;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.dto.CredencemodleDTO;

public class CredencemodleUpdate1AAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text = null;
    try {
      CredencemodleDTO credencemodleDTO = new CredencemodleDTO();
      String subjectCode = request.getParameter("subjectCode");
      String id = request.getParameter("id");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredencemodleBS credencemodleBS = (ICredencemodleBS) BSUtils
          .getBusinessService("credencemodleBS", this, mapping
              .getModuleConfig());
      credencemodleDTO = credencemodleBS.findCredencemodleUpdateInfo(
          subjectCode, securityInfo, id);
      if (credencemodleDTO != null) {
        text = "displays2('" + credencemodleDTO.getSubjectCode() + "','"
            + credencemodleDTO.getSubjectName() + "','"
            + credencemodleDTO.getSubjectDirection() + "','"
            + credencemodleDTO.getBizType() + "','"
            + credencemodleDTO.getMoneyType() + "','"
            + credencemodleDTO.getSummray() + "')";
      } else {
        text = "backErrors('" + "该条记录不存在！" + "')";
        response.getWriter().write(text);
        response.getWriter().close();
      }
      request.getSession().setAttribute("subjectCode",
          credencemodleDTO.getSubjectCode());
      request.getSession().setAttribute("subjectName",
          credencemodleDTO.getSubjectName());
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (BusinessException e) {
      e.printStackTrace();
      text = "backErrors('" + e.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}