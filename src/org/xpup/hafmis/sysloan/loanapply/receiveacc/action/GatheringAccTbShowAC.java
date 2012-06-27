package org.xpup.hafmis.sysloan.loanapply.receiveacc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.bsinterface.IReceiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.receiveacc.form.GatheringAccAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class GatheringAccTbShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.receiveacc.action.GatheringAccTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      GatheringAccAF gatheringAccAF = new GatheringAccAF();
      List list = new ArrayList();
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      String contractId = "";
      String borrowerName = "";
      String cardNum = "";
      List loanbankList2 = null;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      if (pagination.getQueryCriterions().get("contractId") != null) {
        contractId = (String) pagination.getQueryCriterions().get("contractId");
      }
      if (pagination.getQueryCriterions().get("borrowerName") != null) {
        borrowerName = (String) pagination.getQueryCriterions().get(
            "borrowerName");
      }
      if (pagination.getQueryCriterions().get("cardNum") != null) {
        cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      }
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
        List loanbankList = securityInfo.getDkUserBankList();
        loanbankList2 = new ArrayList();
        Userslogincollbank bank = null;
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanbankList2.add(bank.getCollBankId());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      int count = 0;

      PaginationUtils.updatePagination(pagination, request);
      IReceiveaccBS receiveaccBS = (IReceiveaccBS) BSUtils.getBusinessService(
          "receiveaccBS", this, mapping.getModuleConfig());
      list = receiveaccBS.findGathingAccList(pagination, loanbankList2);
      count = receiveaccBS.findGathingAccCount(contractId, borrowerName,
          cardNum, loanbankList2);
      pagination.setNrOfElements(count);
      gatheringAccAF.setList(list);
      request.setAttribute("gatheringAccAF", gatheringAccAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_gathering_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "receiveankmodifyid", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
