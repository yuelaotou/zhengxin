package org.xpup.hafmis.syscollection.paymng.orgoverpay.action;

import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.action.OrgaddpayTbWindowShowAC;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.bsinterface.IOrgoverpayBS;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.form.OrgoverpayAF;

public class OrgoverpayTbMXPrintAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        try{
          Pagination pagination = getPagination(OrgaddpayTbWindowShowAC.PAGINATION_KEY, request); 
          PaginationUtils.updatePagination(pagination, request);  
          String paymentid = (String)pagination.getQueryCriterions().get("paymentid");
          String url=request.getParameter("url");
          saveToken(request);
          if(url!=null){
            paymentid=request.getParameter("paymentid");
          }else{
            url="orgoverpayTbWindowShowAC.do?paymentid="+paymentid;
          }
          
          //OrgoverpayAF orgoverpayAF = (OrgoverpayAF)form;
          IOrgoverpayBS orgoverpayBS = (IOrgoverpayBS) BSUtils
          .getBusinessService("orgoverpayBS", this, mapping.getModuleConfig());   
          OrgoverpayAF  orgoverpayAF=orgoverpayBS.printReport(paymentid);
          SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
          // 付云峰修改：解决挂帐原因不添时显示null问题
          if (orgoverpayAF.getReason()==null) {
            orgoverpayAF.setReason("");
          }
          Org org=orgoverpayBS.findOrgInfo(orgoverpayAF.getOrgId(), "2", securityInfo);
          String office="";
          String collbankname="";
          String bankid="";
          String bankname="";
          String str[]=new String [2];
//        List officlist=securityInfo.getAllCenterList();
//        String collBankid=org.getOrgInfo().getCollectionBankId();
//        if(officlist != null){
//          OfficeDto dto1=(OfficeDto)officlist.get(0);
//          office=dto1.getOfficeName();
//        }
//        if(collBankid != null){
//          collbankname=personAddPayBS.findCollBank(collBankid);
//       }
          str= orgoverpayBS.queryOfficeBankNames(orgoverpayAF.getOrgId(), "2",paymentid, "C", securityInfo);
          if(str[0]!=null){
            office=str[0];
          }
          if(str[1]!=null){
            collbankname=str[1];
          }
         if(org.getOrgInfo().getPayBank()!=null){
           bankid=org.getOrgInfo().getPayBank().getAccountNum();
           bankname=org.getOrgInfo().getPayBank().getName();
         }
          orgoverpayAF.setBankid(bankid);
          orgoverpayAF.setBankname(bankname);
          orgoverpayAF.setOffice(office);
          orgoverpayAF.setCollbankname(collbankname);
          orgoverpayAF.setBizDate(securityInfo.getUserInfo().getBizDate());
          // 取出pagination中的paymentid用来将打印页返回到上级页面
          request.setAttribute("ForwardUrl",url);
          request.setAttribute("orgoverpayAF", orgoverpayAF);          
        }catch(Exception ex){
          ex.printStackTrace();
        }
        return mapping.findForward("to_print.jsp");
      }
      
      private Pagination getPagination(String paginationKey,HttpServletRequest request) {      
        Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);      
        if (pagination == null) {
          pagination = new Pagination(0, 10, 1, "orgExcessPayment.id", "ASC", new HashMap(0));      
          request.getSession().setAttribute(paginationKey, pagination);
        }    
        return pagination;
      }
    }