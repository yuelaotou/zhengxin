package org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.bsinterface.IRatemngBS;
import org.xpup.hafmis.syscollection.accounthandle.ratemng.form.RatemngAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInOrg;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutOrg;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.bsinterface.IEmpAccountBS;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.form.EmpAccountAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;

/**
 * shiyan
 */
public class ShowEmpAccountListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action.ShowEmpAccountListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      saveToken(request);
      IEmpAccountBS empAccountBS = (IEmpAccountBS) BSUtils.getBusinessService("empAccountBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
     if(!pagination.getQueryCriterions().isEmpty()){
      List list=empAccountBS.findEmpAccountList_sy(pagination,securityInfo);
     EmpAccountAF empAccountAF=new EmpAccountAF();
     //显示本期发生金额
     String temp_credit=(String) pagination.getQueryCriterions().get("temp_credit");
     empAccountAF.setTemp_credit(temp_credit);
     //显示本期发生金额
     String temp_debit=(String) pagination.getQueryCriterions().get("temp_debit");
     empAccountAF.setTemp_debit(temp_debit);
     //显示本期利息
     String temp1_interest=(String) pagination.getQueryCriterions().get("temp1_interest");
     empAccountAF.setCurInterest(temp1_interest);
     empAccountAF.setList(list);
     //打印准备数据
     List printList=(List) pagination.getQueryCriterions().get("printList");
     request.getSession().setAttribute("printList", printList);
     request.setAttribute("empAccountAF", empAccountAF);
    } 
     }catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_empAccount_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "empHAFAccountFlow.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
