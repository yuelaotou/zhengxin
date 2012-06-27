package org.xpup.hafmis.syscollection.paymng.orgoverpay.action;

import java.math.BigDecimal;
import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPayment;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.bsinterface.IOrgoverpayBS;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.form.OrgoverpayAF;


public class OrgoverpayTaShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.orgoverpay.action.OrgoverpayTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
      BigDecimal money=new BigDecimal(0.00);
      OrgoverpayAF  f=(OrgoverpayAF)form;     
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      Map bizStatusMap=BusiTools.listBusiProperty(BusiConst.ORGOVERPAYTYPE);
      f.setPayTypeMap(bizStatusMap);
      f.setType("2");
      saveToken(request);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      OrgExcessPayment orgExcessPayment=(OrgExcessPayment)request.getAttribute("orgExcessPayment");
      IOrgoverpayBS orgoverpayBS = (IOrgoverpayBS) BSUtils
      .getBusinessService("orgoverpayBS", this, mapping.getModuleConfig());   
      if(orgExcessPayment!=null){
        String id=orgExcessPayment.getId().toString();
        String orgId=orgExcessPayment.getOrg().getId().toString();
        String unitName=orgExcessPayment.getOrg().getOrgInfo().getName();
        String noteNum=orgExcessPayment.getNoteNum();
        String amount=orgExcessPayment.getPayMoney().toString();
        String reason=orgExcessPayment.getExcessReason();
        money=orgoverpayBS.queryOrgoverpayBalance(orgId);
        String str1[]=new String [3];
        str1=orgoverpayBS.queryBankInfor(orgExcessPayment.getOrg().getOrgInfo().getCollectionBankId());
        f.setOrgId("0"+orgId);
        f.setUnitName(unitName);
        f.setNoteNum(noteNum);
        f.setBanlance(money.toString());
        f.setAmount(amount);
        f.setReason(reason);
        f.setId(id);
        f.setSign("update");
        f.setOrgName1(str1[0]);
        f.setOrgBank1(str1[1]);
        f.setOrgBankNum1(str1[2]);
        f.setType(orgExcessPayment.getTmpplaceKind());
        f.setOrgName2(orgExcessPayment.getOrg().getOrgInfo().getName());
        if(orgExcessPayment.getOrg().getOrgInfo().getPayBank()!=null){
          f.setOrgBank2(orgExcessPayment.getOrg().getOrgInfo().getPayBank().getName());
          f.setOrgBankNum2(orgExcessPayment.getOrg().getOrgInfo().getPayBank().getAccountNum());
        }
        
      }else{
        f.reset(mapping, request);
          //f.setNoteNum(securityInfo.getUserInfo().getBizDate()+orgoverpayBS.queryNoteNum());
         
   
      }

  
      request.setAttribute("orgoverpayAF", f);
    return mapping.findForward("to_orgoverpay.jsp");
  }
  
  private Pagination getPagination(String paginationKey,HttpServletRequest request) {     
    Pagination pagination = (Pagination) request.getSession().getAttribute( paginationKey);      
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "orgExcessPayment.id", "ASC", new HashMap(0));        
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
