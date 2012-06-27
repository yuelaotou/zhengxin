package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.OrgInfoDTO;

public class PersonAddPayTaFindInforAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
      
        try {
          SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
          String orgId=(String)request.getParameter("orgId");
          String orgid=BusiTools.convertSixNumber(orgId);
          IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
          .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
          IMonthpayBS monthpayBS = (IMonthpayBS) BSUtils
          .getBusinessService("monthpayBS", this, mapping.getModuleConfig());
          OrgInfoDTO orgInfoDTO=null;
          String unitName="";
          String docNumber="";
          String paymentHeadId="";
          String orgStatus="";
          
          String  receivables_orgname=""; //收款单位名称
          String receivables_bank_acc="";  //收款单位归集银行账号
          String receivables_bank_name="";  // 收款单位归集银行名称
         
          String payment_orgname="";   //  付款单位名称
          String payment_bank_acc="";  //  付款单位开户银行账号
          String payment_bank_name=""; // 付款单位开户银行名称
          Org org=null;
          if((orgId!=null)&&(!orgId.equals(""))){
            org=personAddPayBS.findOrgInfo(orgId);
            if(org==null){
              orgStatus="abnormal";            
            }
            org = monthpayBS.findOrgInfo(orgId,"2",securityInfo);
            
          }
          if(org!=null){
            unitName=org.getOrgInfo().getName();
            //docNumber=orgInfoDTO.getDocNumber();
           //paymentHeadId=orgInfoDTO.getPaymentHeadId();
            List banklist=new ArrayList();
            banklist=monthpayBS.queryCollBankInfo(org.getOrgInfo().getOfficecode(), org.getOrgInfo().getCollectionBankId());
            if(banklist.size()>0)
            {
              for(int i=0;i<banklist.size();i++)
              {
                Object obj[]=null;
                obj=(Object[])banklist.get(0);
                if(obj[0] != null){
                  receivables_orgname=obj[0].toString();
                }
                if(obj[2] != null){
                  receivables_bank_acc=obj[2].toString();
                }
                if(obj[1] != null){
                  receivables_bank_name=obj[1].toString();
                }
              }
            }
              if(org.getOrgInfo().getPayBank().getName()!=null)
             {
                payment_bank_name=org.getOrgInfo().getPayBank().getName(); // 增加付款单位银行名称
              }
              if(org.getOrgInfo().getPayBank().getAccountNum()!=null)
             {
                payment_bank_acc=org.getOrgInfo().getPayBank().getAccountNum();// 增加付款单位银行账号
              }
            payment_orgname=org.getOrgInfo().getName();
          }
          else
          {
            return mapping.findForward("personaddpayTaForwardUrlAC");
          }
          
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("unitName", unitName);
          pagination.getQueryCriterions().put("docNumber", docNumber);
          pagination.getQueryCriterions().put("paymentHeadId", paymentHeadId);
          pagination.getQueryCriterions().put("orgId", orgId);
          text="displays('"+orgStatus+"','"+orgid+"','"+unitName+"','"+docNumber+"','"+receivables_orgname+"'," +
              "'"+receivables_bank_acc+"','"+receivables_bank_name+"','"+payment_orgname+"','"+payment_bank_acc+"','"+payment_bank_name+"')";
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

  protected String getPaginationKey() {
    return PersonAddPayTaShowAC.PAGINATION_KEY;
 }

}
