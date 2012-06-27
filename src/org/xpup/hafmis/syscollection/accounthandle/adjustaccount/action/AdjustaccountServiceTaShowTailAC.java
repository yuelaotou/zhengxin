package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountReportDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountServiceShowAF;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountShowAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;


/**
 * 
 * @author 石岩
 *2007-7-6
 */
public class AdjustaccountServiceTaShowTailAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountServiceTaShowTailAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
        Pagination pagination = getPagination(PAGINATION_KEY, request); 
    try{
        
        saveToken(request);
        PaginationUtils.updatePagination(pagination, request);  
        IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
        .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
        String report=request.getParameter("report");
        String type =null;
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
        String ids=(String)request.getParameter("adjustWrongAccountHeadTempId");
        if(ids==null){
          ids=(String) request.getSession().getAttribute("ids");
        }
        AdjustaccountShowAF adjustaccountShowAF=adjustaccountBS.findAdjustWrongAccount_sy(ids,pagination);
        //保存头表id用于分页
        request.getSession().setAttribute("ids", ids);
        request.setAttribute("adjustaccountShowAF", adjustaccountShowAF);
       if(report!=null&&!report.equals("")){
        if(report.equals("print")){
          List list=adjustaccountBS.adjustWrongAccountAllByHID(ids,securityInfo);
          
//        wuht
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
            
//          wuht
          
          request.setAttribute("printlist", list);
          request.setAttribute("URL","adjustaccountServiceTaShowTailAC.do");
          pagination.getQueryCriterions().put("adjustaccountList2", new ArrayList());
          return mapping.findForward("show_cell");
        }
       }
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有您要查询的信息！",
          false));
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    List printlist=(List)pagination.getQueryCriterions().get("adjustprint"); 
    request.setAttribute("printlist", printlist);
    return mapping.findForward("to_adjustaccountservice_tail_list");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
   
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) { 
      pagination = new Pagination(0, 10, 1, "adjustWrongAccountHead.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
