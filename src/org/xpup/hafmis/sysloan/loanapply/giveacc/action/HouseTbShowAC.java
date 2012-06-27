package org.xpup.hafmis.sysloan.loanapply.giveacc.action;

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
import org.xpup.hafmis.sysloan.loanapply.giveacc.bsinterface.IGiveaccBS;
import org.xpup.hafmis.sysloan.loanapply.giveacc.form.HouseAccAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class HouseTbShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.giveacc.action.HouseTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      HouseAccAF houseAccAF = new HouseAccAF();
      List list = new ArrayList();
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String sellerName = (String) pagination.getQueryCriterions().get(
          "sellerName");
      List loanbankList2 = null;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      int count = 0;
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
      PaginationUtils.updatePagination(pagination, request);
      IGiveaccBS giveaccBS = (IGiveaccBS) BSUtils.getBusinessService(
          "giveaccBS", this, mapping.getModuleConfig());
      list = giveaccBS.findHouseAccList(pagination,loanbankList2);
      count = giveaccBS.findHouseAccCount(contractId, borrowerName, cardNum,
          sellerName,loanbankList2);
      pagination.setNrOfElements(count);
      houseAccAF.setList(list);
      request.setAttribute("houseAccAF", houseAccAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_house_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "pokemodifyid", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
