package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountReportDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountShowAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;


/**
 * 
 * @author ÀîÅô
 *2007-6-28
 */    
public class AdjustaccountPrintAC extends Action{
       
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      String adjustaccountids=request.getParameter("headid");
      if(adjustaccountids!=null&&!adjustaccountids.equals("")){
      String temp_url=request.getParameter("url");
      IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
      .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      List list=adjustaccountBS.adjustWrongAccountAllByHID(adjustaccountids,securityInfo);
//    wuht
      AdjustaccountReportDTO adjustaccountReportDTO = new  AdjustaccountReportDTO();
      if(list!=null && list.size()>0){
      adjustaccountReportDTO=(AdjustaccountReportDTO)list.get(0);
      }
     
      String orgId = adjustaccountReportDTO.getOrgid().toString();
      
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
      .getBusinessService("loanDocNumDesignBS", this, mapping.getModuleConfig());
      String userName="";
      SecurityInfo securityInfo1=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        try {
          String name = loanDocNumDesignBS .getNamePara();

          if (name.equals("1")) {
            userName = securityInfo.getUserName();
          } else {
            userName = securityInfo.getRealName();
          }

        } catch (Exception e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        String collectionBankId="";
        String collectionBankName="";
   
        if(orgId!=null && !orgId.equals("")){  
           collectionBankName=loanDocNumDesignBS.queryCollectionBankNameById(orgId, securityInfo);
        }
        String bizDate = securityInfo.getUserInfo().getBizDate();
        request.setAttribute("userName", userName);
        request.setAttribute("bizDate", bizDate);
        request.setAttribute("collectionBankName", collectionBankName);
//      wuht
      
      
      request.setAttribute("URL",temp_url);
      request.setAttribute("printlist", list);
      }else{
      List printlist=(List)pagination.getQueryCriterions().get("adjustprint"); 
      request.setAttribute("printlist", printlist);
      }
    return mapping.findForward("show_cell");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "empHAFAccountFlow.orgHAFAccountFlow.docNum", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
