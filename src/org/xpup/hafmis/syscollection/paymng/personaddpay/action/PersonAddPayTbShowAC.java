package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

import java.math.BigDecimal;
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
import org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface.IPersonAddPayBS;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayMaintainAF;
import org.xpup.security.common.domain.Userslogincollbank;


public class PersonAddPayTbShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTbShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      try{
        SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
        PersonAddPayMaintainAF personAddPayMaintainAF=new PersonAddPayMaintainAF();
        personAddPayMaintainAF.reset();
        Map bizStatusMap=BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
        personAddPayMaintainAF.setBizStatusMap(bizStatusMap);
        BigDecimal money=new BigDecimal(0.00);
        Pagination pagination = getPagination(PAGINATION_KEY, request); 
        PaginationUtils.updatePagination(pagination, request);    
        IPersonAddPayBS personAddPayBS = (IPersonAddPayBS) BSUtils
        .getBusinessService("personAddPayBS", this, mapping.getModuleConfig());
        List empaddpayList = personAddPayBS.findEmpaddpayList(pagination,securityInfo);
        money=personAddPayBS.getEmpaddpayMoney(pagination,securityInfo);
        if(empaddpayList.size()!=0){
          personAddPayMaintainAF.setListCount("1");
        }else{
          personAddPayMaintainAF.setListCount("2");
        }
        personAddPayMaintainAF.setMoney(money.toString());
        // 在页面判断是否单位版用到_wangy
        int type=securityInfo.getIsOrgEdition();
        List collBankList1 = null;
        try {
          // 取出用户下归集行
          List collBankList = securityInfo.getCollBankList();
          collBankList1 = new ArrayList();
          Userslogincollbank userslogincollbank = null;
          Iterator itr2 = collBankList.iterator();
          while (itr2.hasNext()) {
            userslogincollbank = (Userslogincollbank) itr2.next();
            collBankList1.add(new org.apache.struts.util.LabelValueBean(
                userslogincollbank.getCollBankName().toString(), userslogincollbank
                    .getCollBankId().toString()));
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        request.setAttribute("collBankList1", collBankList1);
        request.setAttribute("type", type+"");
        request.getSession().setAttribute("empaddpayList", empaddpayList);
        request.setAttribute("personAddPayMaintainAF", personAddPayMaintainAF);       
        }catch(Exception e){
        e.printStackTrace();
        }
 
   return mapping.findForward("to_personAddPayMaintain_show.jsp");  

  }
  private  Pagination getPagination(String paginationKey, HttpServletRequest request) {   
    Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);    
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "empAddPay.id", "DESC",  new HashMap(0));      
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
