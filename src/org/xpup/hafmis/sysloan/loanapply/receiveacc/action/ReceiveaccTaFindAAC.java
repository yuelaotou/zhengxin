package org.xpup.hafmis.sysloan.loanapply.receiveacc.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.bsinterface.IReceiveaccBS;
import org.xpup.security.common.domain.Userslogincollbank;

public class ReceiveaccTaFindAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List loanbankList1 = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    try {
      // 取出用户权限放款银行,显示在下拉菜单中
      List loanbankList = securityInfo.getDkUserBankList();
      loanbankList1 = new ArrayList();
      Userslogincollbank bank = null;
      Iterator itr1 = loanbankList.iterator();
      while (itr1.hasNext()) {
        bank = (Userslogincollbank) itr1.next();
        loanbankList1.add(bank.getCollBankId());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("Cache-Control", "no-cache");
      String contractId = (String) request.getParameter("contractId");
      if(contractId!=null&&!"".equals(contractId.trim())){
        contractId = (String) request.getParameter("contractId").trim();
        request.getSession().setAttribute("contractId", contractId.toString());
      }      
      String text = null;
      IReceiveaccBS receiveaccBS = (IReceiveaccBS) BSUtils.getBusinessService(
          "receiveaccBS", this, mapping.getModuleConfig());
      receiveaccBS.findReceiveaccInfoExist(contractId,loanbankList1);
      receiveaccBS.findReceiveaccAvailable(contractId);      
      text = "displays('" + contractId + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (BusinessException e) {
      e.printStackTrace();
      String text = "backErrors('" + e.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
