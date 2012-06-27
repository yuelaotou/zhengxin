package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;

/**
 * @author yuqf 2007-10-01
 */
public class EndorsecontractTaXMLFindAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    response.setHeader("Cache-Control", "no-cache");
    response.setContentType("text/xml;charset=UTF-8");
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String id = (String) request.getParameter("contractId");
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
        .getBusinessService("endorsecontractBS", this, mapping
            .getModuleConfig());
    PrintWriter out = null;
    if (id != null) {
      List sloanBankNameList = endorsecontractBS.queryBankList(id, securityInfo);
      out = response.getWriter();
      out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
      out.println("<options>");
      for (int i = 0; i < sloanBankNameList.size(); i++) {
        LabelValueBean labelValueBean = (LabelValueBean) sloanBankNameList
            .get(i);
       
        out.println("<value>" + labelValueBean.getValue() + "</value>");
        out.println("<text>" + labelValueBean.getLabel() + "</text>");
//        out.println("<value>" + 11111 + "</value>");
//        out.println("<text>" + 222 + "</text>");
      }
      out.println("</options>");
    }
    out.flush();
    out.close();
    return null;
  }

}
