package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loancheck.bsinterface.ILoanCheckBS;
import org.xpup.hafmis.sysloan.loanapply.loancheck.form.LoanCheckShowAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 王野 2007-09-22
 */
public class LoanCheckShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.loancheck.action.LoanCheckShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String url = null;
    ActionMessages messages = null;
    try {
      url = (String)request.getAttribute("url");
      if(url!=null){
        request.getSession().setAttribute("url",url);
      }else{
        url = (String)request.getSession().getAttribute("url");
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      if (securityInfo != null && !securityInfo.equals("")) {
        ILoanCheckBS loanCheckBS = (ILoanCheckBS) BSUtils.getBusinessService(
            "loanCheckBS", this, mapping.getModuleConfig());
        LoanCheckShowAF loanCheckShowAF = new LoanCheckShowAF();
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
        // 判断从哪个节点过来的然后传入不同的的合同的状态作为默认的查询条件
        String contractSt = "";
        if(url!=null){
          if(url.equals("redate")){
            contractSt = "4,16,19";
          }else if(url.equals("again")){
            contractSt = "16,20";
          }
        }else{
          contractSt = "15";
        }
        pagination.getQueryCriterions().put("contractSt", contractSt);
        loanCheckShowAF = loanCheckBS.queryBorrowerListByCriterions(pagination,
            securityInfo);
        String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
        loanCheckShowAF.setLoanBankName(loanBankName);
        // 办事处下拉
        List officeList = securityInfo.getOfficeList();
        List officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
        }
        request.getSession(true).setAttribute("officeList1", officeList1);
        // 购房类型下拉框
        Map houseTypeMap = BusiTools.listBusiProperty(BusiConst.PLHOUSETYPE);
        loanCheckShowAF.setHouseTypeMap(houseTypeMap);
        // 合同状态下拉框
        Map contractStMap = BusiTools.listBusiProperty(BusiConst.PLCONTRACTSTATUS);
        loanCheckShowAF.setContractStMap(contractStMap);
        List listPrint=loanCheckShowAF.getListAll();
        request.getSession(true).setAttribute("celllist",
            listPrint);
        request.getSession(true).setAttribute("cellloanCheckShowAF",
            loanCheckShowAF);
        request.setAttribute("loancheckShowAF", loanCheckShowAF);
        String error = (String) request.getAttribute("error");
        if(error!=null){
          messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(error,false));
          saveErrors(request, messages);
        }
      }
    } catch (BusinessException e) {
      e.printStackTrace();
    }
    // 用url来判断是哪个节点过来的
    if(url!=null){
      if(url.equals("redate")){
        return mapping.findForward("to_loanredate_show");
      }else if(url.equals("again")){
        return mapping.findForward("to_loancheckagain_show");
      }
    }
    return mapping.findForward("to_loancheck_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p110.contract_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
