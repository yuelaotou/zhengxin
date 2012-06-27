package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

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
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.form.AdjustAccountPopFindAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 错帐调整弹出框Forward Action
 * 
 * @author 付云峰
 */
public class AdjustAccountPopShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action.AdjustAccountPopShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    try {

      AdjustAccountPopFindAF adjustAccountPopFindAF = new AdjustAccountPopFindAF();

      String indexs=request.getParameter("indexs");
      if(indexs != null){
        request.getSession().setAttribute("indexs", indexs);
        // 点击按钮进入时清空pagination，以保证在下次进入时不在带有上次的状态
        request.getSession().removeAttribute(AdjustAccountPopShowAC.PAGINATION_KEY);
      }
      
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      List loanbankList1 = null;
      List loanbankList2 = null;
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

      IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
          .getBusinessService("adjustAccountBS", this, mapping
              .getModuleConfig());

      List list = adjustAccountBS.findAdjustAccountPopList(pagination,
          loanbankList2);

      // 业务类型
      Map bizTypeMap = BusiTools.listBusiProperty(BusiConst.PLBUSINESSTYPE);
      adjustAccountPopFindAF.setBizTypeMap(bizTypeMap);
      adjustAccountPopFindAF.setList(list);
      request.setAttribute("loanbankList", loanbankList1);
      request.setAttribute("adjustAccountPopFindAF", adjustAccountPopFindAF);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_adjustaccountpop_find");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "res.loankouacc", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

}
