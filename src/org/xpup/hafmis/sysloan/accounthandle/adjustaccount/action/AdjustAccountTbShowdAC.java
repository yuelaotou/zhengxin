package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form.AdjustAccountFindAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 显示错帐调整列表的Action
 * 
 * @author 付云峰
 */
public class AdjustAccountTbShowdAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action.AdjustAccountTbShowdAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    AdjustAccountFindAF adjustAccountFindAF = new AdjustAccountFindAF();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      // 取出用户权限放款银行,显示在下拉菜单中
      List loanbankList1 = null;
      List loanbankList2 = null;
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
        List loanbankList = securityInfo.getDkUserBankList();
        loanbankList1 = new ArrayList();
        loanbankList2 = new ArrayList();
        Userslogincollbank bank = null;
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
              .getCollBankName(), bank.getCollBankId().toString()));
          loanbankList2.add(bank.getCollBankId());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      Map bizTypeMap = BusiTools.listBusiProperty(BusiConst.PLBUSINESSTYPE);
      bizTypeMap.remove(new Integer("3"));
      bizTypeMap.remove(new Integer("4"));
      bizTypeMap.remove(new Integer("8"));
      bizTypeMap.remove(new Integer("9"));
      bizTypeMap.remove(new Integer("10"));
      bizTypeMap.remove(new Integer("11"));
      bizTypeMap.remove(new Integer("12"));
      bizTypeMap.remove(new Integer("13"));
      bizTypeMap.remove(new Integer("14"));
      bizTypeMap.remove(new Integer("15"));
      Map bizStMap = BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);  
      bizStMap.remove(new Integer("1"));
      bizStMap.remove(new Integer("2"));
      bizStMap.remove(new Integer("3"));
      adjustAccountFindAF.setBizTypeMap(bizTypeMap);
      adjustAccountFindAF.setBizStMap(bizStMap);
      
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      
      IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
      .getBusinessService("adjustAccountBS", this, mapping
          .getModuleConfig());
      // 查询出列表
      Object[] obj = adjustAccountBS.findAdjustAccountList(pagination, loanbankList2);

      adjustAccountFindAF.setList((List)obj[0]);
      adjustAccountFindAF.setSumOccurMoney((BigDecimal)obj[1]);
      adjustAccountFindAF.setSumCallbackCorpus((BigDecimal)obj[2]);
      adjustAccountFindAF.setSumCallbackInterest((BigDecimal)obj[3]);
      adjustAccountFindAF.setSumPunishInterest((BigDecimal)obj[4]);
      
      request.setAttribute("adjustAccountFindAF", adjustAccountFindAF);
      request.setAttribute("loanBankIdList", loanbankList1);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return mapping.findForward("to_adjustaccounttb_show");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "res.flowheadid", "DESC", new HashMap(
          0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

}
