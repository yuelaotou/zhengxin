package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;

public class CredenceFillinSerchSuggestAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String search = request.getParameter("search");
    if (!search.equals("")) {
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String search_suggest = credenceFillinBS.findSummayList(search,
          securityInfo);
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter writer = null;
      try {
        writer = response.getWriter();
        writer.write(search_suggest);
      } catch (IOException ex) {
      } finally {
        writer.close();
      }
    }
    return mapping.findForward("to_credencefillinta_show");
  }

}
