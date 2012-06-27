package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTbAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 根据单位编号、单位名称、单位状态查询单位信息
 * 
 *@author 李娟
 *2007-6-27
 */
public class OrgaddpayTbShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.orgaddpay.action.OrgaddpayTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    try{
      OrgaddpayTbAF orgaddpayTbAF=new OrgaddpayTbAF();
      BigDecimal money = null;
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Map payTypeMap=BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
      orgaddpayTbAF.setPayTypeMap(payTypeMap);
      Pagination pagination = getPagination(OrgaddpayTbShowAC.PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      
      IOrgaddpayBS orgaddpayBS = (IOrgaddpayBS) BSUtils
      .getBusinessService("orgaddpayBS", this, mapping.getModuleConfig());
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
      List orgaddpayList = orgaddpayBS.findOrgaddpayList(pagination,securityInfo);
      money=orgaddpayBS.getOrgaddpayMoney(pagination,securityInfo);
      if(orgaddpayList.size()!=0){
        orgaddpayTbAF.setListCount("1");
      }else{
        orgaddpayTbAF.setListCount("2");
      }
      orgaddpayTbAF.setMoney(money.toString());
      request.setAttribute("collBankList1", collBankList1);
      request.setAttribute("orgaddpayList", orgaddpayList);
      request.setAttribute("orgaddpayTbAF", orgaddpayTbAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "show_orgaddpayList";
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("payType", "0");
      pagination = new Pagination(0, 10, 1, "orgAddPay.id", "DESC",
           m);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
  
}


