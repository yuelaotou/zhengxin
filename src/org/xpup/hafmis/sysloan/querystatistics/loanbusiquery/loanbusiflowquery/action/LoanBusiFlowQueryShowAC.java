package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action;

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
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.bsinterface.ILoanBusiFlowQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form.LoanBusiFlowQueryAF;
import org.xpup.security.common.domain.User;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 王野 2007-10-15
 */
public class LoanBusiFlowQueryShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action.LoanBusiFlowQueryShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      if (securityInfo != null && !securityInfo.equals("")) {
        ILoanBusiFlowQueryBS loanBusiFlowQueryBS = (ILoanBusiFlowQueryBS) BSUtils
            .getBusinessService("loanBusiFlowQueryBS", this, mapping
                .getModuleConfig());
        LoanBusiFlowQueryAF loanBusiFlowQueryAF = new LoanBusiFlowQueryAF();
        String type = (String) pagination.getQueryCriterions().get("type");
        if (type == null) {
          type = "1";
        }
        if (!type.equals("0")) {
          loanBusiFlowQueryAF = loanBusiFlowQueryBS
              .queryLoanBusiFlowQueryListByCriterions(pagination, securityInfo);
        }
        String loanBankName = (String)pagination.getQueryCriterions().get("loanBankName");
        loanBusiFlowQueryAF.setLoanBankName(loanBankName);
        // 业务类型下拉框
        Map bizTypeMap = BusiTools.listBusiProperty(BusiConst.PLBUSINESSTYPE);
        Map bizTypeNewMap = new HashMap();
        bizTypeNewMap.put(new Integer(BusiConst.PLBUSINESSTYPE_ISSUED),
            bizTypeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_ISSUED)));// 1发放
        bizTypeNewMap
            .put(new Integer(BusiConst.PLBUSINESSTYPE_SINGLERECOVER),
                bizTypeMap.get(new Integer(
                    BusiConst.PLBUSINESSTYPE_SINGLERECOVER)));// 2单笔回收
        bizTypeNewMap.put(new Integer(BusiConst.PLBUSINESSTYPE_PARTRECOVER),
            bizTypeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_PARTRECOVER)));// 3部分提前还款
        bizTypeNewMap.put(new Integer(BusiConst.PLBUSINESSTYPE_ALLCLEAR),
            bizTypeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_ALLCLEAR)));// 4一次性还清
        bizTypeNewMap.put(new Integer(BusiConst.PLBUSINESSTYPE_SOMERECOVER),
            bizTypeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_SOMERECOVER)));// 5批量回收
        bizTypeNewMap.put(new Integer(
            BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE), bizTypeMap
            .get(new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFCENTRE)));// 6呆账核销（中心）
        bizTypeNewMap.put(
            new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER), bizTypeMap
                .get(new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSOFFOTHER)));// 7呆账核销（其他）
        bizTypeNewMap.put(new Integer(
            BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER), bizTypeMap
            .get(new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER)));// 8核销收回（中心）
        bizTypeNewMap.put(new Integer(
            BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER), bizTypeMap
            .get(new Integer(BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER)));// 9核销收回（其他）
        bizTypeNewMap.put(new Integer(BusiConst.PLBUSINESSTYPE_CARRYPAY),
            bizTypeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_CARRYPAY)));// 11结转余额
        bizTypeNewMap.put(new Integer(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG),
            bizTypeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG)));// 12错账调整
        bizTypeNewMap.put(new Integer(BusiConst.PLBUSINESSTYPE_OVERPAY),
            bizTypeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_OVERPAY)));// 13挂账
        bizTypeNewMap.put(new Integer(BusiConst.PLBUSINESSTYPE_MARGIN),
            bizTypeMap.get(new Integer(BusiConst.PLBUSINESSTYPE_MARGIN)));// 14保证金
        bizTypeNewMap
            .put(new Integer(BusiConst.PLBUSINESSTYPE_CLEARINTEREST),
                bizTypeMap.get(new Integer(
                    BusiConst.PLBUSINESSTYPE_CLEARINTEREST)));// 15结息
        loanBusiFlowQueryAF.setBizTypeMap(bizTypeNewMap);
        // 制单人下拉框
        List operList = securityInfo.getUserList();// 权限中所管的操作员（包括自己）
        List operList1 = new ArrayList();
        User user = null;
        Iterator itr2 = operList.iterator();
        while (itr2.hasNext()) {
          user = (User) itr2.next();
          operList1.add(new org.apache.struts.util.LabelValueBean(user
              .getUsername(), user.getUsername()));
        }
        request.getSession(true).setAttribute("operList", operList1);
        // 业务状态下拉框
        Map bizStMap = BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);
        Map bizStNewMap = new HashMap();
        bizStNewMap.put(new Integer(BusiConst.BUSINESSSTATE_SURE), bizStMap
            .get(new Integer(BusiConst.BUSINESSSTATE_SURE)));// 确认
        bizStNewMap.put(new Integer(BusiConst.BUSINESSSTATE_CHECK), bizStMap
            .get(new Integer(BusiConst.BUSINESSSTATE_CHECK)));// 复核
        bizStNewMap.put(new Integer(BusiConst.BUSINESSSTATE_CLEARACCOUNT),
            bizStMap.get(new Integer(BusiConst.BUSINESSSTATE_CLEARACCOUNT)));// 入账
        loanBusiFlowQueryAF.setBizStMap(bizStNewMap);
        Map m = BusiTools.listBusiProperty(BusiConst.YesNo);
        m.put(new Integer(BusiConst.YES), "是");
        loanBusiFlowQueryAF.setIsGjjLoanbackMap(m);
        // 放款银行下拉框
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
        request.getSession(true).setAttribute("printLoanBusiFlowQueryList",
            loanBusiFlowQueryAF.getPrintList());
        request.setAttribute("loanBusiFlowQueryAF", loanBusiFlowQueryAF);
        loanBusiFlowQueryAF.reset(mapping, request);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_loanbusiflowquery_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("type", "0");
      pagination = new Pagination(0, 10, 1, "bizDate", "ASC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
