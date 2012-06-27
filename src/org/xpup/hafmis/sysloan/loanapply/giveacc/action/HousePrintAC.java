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

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loanapply.giveacc.bsinterface.IGiveaccBS;
import org.xpup.security.common.domain.Userslogincollbank;

public class HousePrintAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.giveacc.action.HouseTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      String realName = "";
      List cellList = new ArrayList();
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      List loanbankList2 = null;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
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
      IGiveaccBS giveaccBS = (IGiveaccBS) BSUtils.getBusinessService(
          "giveaccBS", this, mapping.getModuleConfig());
      cellList = giveaccBS.findHouseAccPrintList(pagination, loanbankList2);
      String bizdate = (String) securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("bizDate", bizdate);
      // realName = giveaccBS.getUserRealName(securityInfo.getUserName());
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
          .getBusinessService("loanDocNumDesignBS", this, mapping
              .getModuleConfig());
      try {
        String name = loanDocNumDesignBS.getNamePara();

        if (name.equals("1")) {
          realName = securityInfo.getUserName();
        } else {
          realName = securityInfo.getRealName();
        }

      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      request.setAttribute("username", realName);
      request.setAttribute("empaddpURL", "javascript:history.back();");
      request.setAttribute("cellList", cellList);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("to_houselist_print");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "contractid", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
