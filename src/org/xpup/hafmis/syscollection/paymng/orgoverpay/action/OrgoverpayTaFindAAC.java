package org.xpup.hafmis.syscollection.paymng.orgoverpay.action;

import java.math.BigDecimal;

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
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPayment;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.bsinterface.IOrgoverpayBS;

public class OrgoverpayTaFindAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        String payStatus="";
        try {    
          SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
          BigDecimal banlance=new BigDecimal(0.00);
          String orgId=(String)request.getParameter("orgId");
          IOrgoverpayBS orgoverpayBS = (IOrgoverpayBS) BSUtils
          .getBusinessService("orgoverpayBS", this, mapping.getModuleConfig());
          Org org=null;
          String unitName="";
          String orgexcess="";
          String a="";
          if(orgId!=null&&!orgId.equals("")){
          org=orgoverpayBS.findOrgInfo(orgId,"2",securityInfo);
          unitName=org.getOrgInfo().getName();
          if (org != null && !org.equals("")) {
            a=orgoverpayBS.FindAA103_DayTime(org.getOrgInfo().getCollectionBankId());
            if(securityInfo.getUserInfo().getBizDate().equals(a)){
              a="a";
            }else{
              a="b";
            }
            
          }
          }
          if(unitName!=""){

         //   orgExcessPayment=orgoverpayBS.findOrgoverpayInfo(orgId,null);
       
       //     if(orgExcessPayment!=null){ 
       //     payStatus=orgExcessPayment.getPayStatus().toString();
       //     if(payStatus.equals("5")){
            banlance=orgoverpayBS.queryOrgoverpayBalance(orgId);
        //    }else{
        //      orgexcess="not";
        //    }

          }
         String noteNum= securityInfo.getUserInfo().getBizDate()+orgoverpayBS.queryNoteNum();
          String str1[]=new String [3];
          str1=orgoverpayBS.queryBankInfor(org.getOrgInfo().getCollectionBankId());
          String text=null;
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("orgId", orgId);
          pagination.getQueryCriterions().put("unitName", unitName);
          pagination.getQueryCriterions().put("banlance", banlance);
          String unitNum=BusiTools.convertSixNumber(orgId);
          text="displays('"+orgId+"','"+unitName+"','"+banlance+"','"+orgexcess+"','"+a+"','"+str1[0]+"','"+str1[1]+"','"+str1[2]+"','"+org.getOrgInfo().getPayBank().getName()+"','"+org.getOrgInfo().getPayBank().getAccountNum()+"','"+noteNum+"')";
          response.getWriter().write(text); 
          response.getWriter().close();
          } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

  protected String getPaginationKey() {
    return OrgoverpayTaShowAC.PAGINATION_KEY;
 }
}