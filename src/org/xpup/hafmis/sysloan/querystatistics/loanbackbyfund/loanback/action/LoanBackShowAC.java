package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.action;

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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.bsinterface.ILoanBackBS;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.dto.LoanBackBankDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.form.LoanBackAF;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryClearDTO;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 杨光 20090512
 */
public class LoanBackShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action.LoanBackShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      List officeList = null;
      if (securityInfo != null && !securityInfo.equals("")) {
        ILoanBackBS loanBackBS = (ILoanBackBS) BSUtils
            .getBusinessService("loanBackBS", this, mapping
                .getModuleConfig());
        LoanBackAF loanBackAF = new LoanBackAF();
        String type = (String) pagination.getQueryCriterions().get("type");
        if (type == null) {
          type = "1";
        }
        if (!type.equals("0")) {
          loanBackAF = loanBackBS
              .queryLoanBusiFlowQueryListByCriterions(pagination, securityInfo);
          String loanBank = (String)pagination.getQueryCriterions().get("loanBank");
          loanBackAF.setLoanBankName(loanBank);
        }

        
        // 取出用户权限办事处,显示在下拉菜单中
        List temp_officeList = securityInfo.getAllOfficeList();
        officeList = new ArrayList();
        OfficeDto officedto = null;
        Iterator it = temp_officeList.iterator();
        while (it.hasNext()) {
          officedto = (OfficeDto) it.next();
          officeList.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
        }
    
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
       
        request.setAttribute("officeList", officeList);
        request.setAttribute("loanBankNameList", loanBankNameList);
        request.getSession().setAttribute("printLoanBusiFlowQueryList", loanBackAF.getPrintList());
        if(loanBackAF.getBankList_yg()!=null){
          List yglist = loanBackAF.getBankList_yg();
          for(int y = 0;y<yglist.size();y++){
            LoanBackBankDTO dtoyg = (LoanBackBankDTO)yglist.get(y);
            LoanBusiFlowQueryClearDTO loanBusiFlowQueryClearDTO=loanBackBS.queryLoanBusiFlowQueryClearByFlowHeadId(dtoyg.getCollbankname(), securityInfo);
            dtoyg.setCollbankname(loanBusiFlowQueryClearDTO.getLoanBankName());
          }
          request.getSession().setAttribute("getBankList_yg", loanBackAF.getBankList_yg());
        }
        request.getSession().setAttribute("loanbackprintdate", loanBackAF.getPrintDate());
        request.setAttribute("loanBackAF", loanBackAF);
        loanBackAF.reset(mapping, request);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_loanBack_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("type", "0");
      pagination = new Pagination(0, 10, 1, "p203.contract_id", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
