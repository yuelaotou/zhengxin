package org.xpup.hafmis.sysloan.specialbiz.bailenrol.action;

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
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface.IBailenRolBS;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTbAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class BailenRolTbShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.specialbiz.bailenrol.action.BailenRolTbShowAC";

  /**
   * 保证金登记维护
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      if (securityInfo != null && !securityInfo.equals("")) {
        IBailenRolBS bailenRolBS = (IBailenRolBS) BSUtils.getBusinessService(
            "bailenRolBS", this, mapping.getModuleConfig());
        BailenRolTbAF bailenRolTbAF = new BailenRolTbAF();
        bailenRolTbAF = bailenRolBS.queryBailenRolListByCriterions(pagination,
            securityInfo);
        // 收款银行下拉框
        List loanBankNameList = new ArrayList();
        List bangkList = securityInfo.getDkUserBankList();
        Userslogincollbank userslogincollbank = null;
        Iterator bank = bangkList.iterator();
        while (bank.hasNext()) {
          userslogincollbank = (Userslogincollbank) bank.next();
          loanBankNameList.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName(), userslogincollbank
                  .getCollBankId().toString()));
        }
        request.getSession(true).setAttribute("loanBankNameList",
            loanBankNameList);
        // 业务状态下拉框
        Map bizStMap = BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);
        Map bizStNewMap = new HashMap();
        bizStNewMap.put(new Integer(BusiConst.BUSINESSSTATE_SURE), bizStMap
            .get(new Integer(BusiConst.BUSINESSSTATE_SURE)));// 确认
        bizStNewMap.put(new Integer(BusiConst.BUSINESSSTATE_CHECK), bizStMap
            .get(new Integer(BusiConst.BUSINESSSTATE_CHECK)));// 复核
        bizStNewMap.put(new Integer(BusiConst.BUSINESSSTATE_CLEARACCOUNT),
            bizStMap.get(new Integer(BusiConst.BUSINESSSTATE_CLEARACCOUNT)));// 入账
        bailenRolTbAF.setBizStMap(bizStNewMap);
        request.setAttribute("bailenRolTbAF", bailenRolTbAF);
      }
    } catch (BusinessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_bailenrol_showtb");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p202.flow_head_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
