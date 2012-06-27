package org.xpup.hafmis.syscollection.paymng.orgaddpay.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTbAF;

/**
 * 根据单位编号、单位名称查询单位信息
 * 
 *@author 李娟
 *2007-6-27
 */
public class OrgaddpayTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
try{
    OrgaddpayTbAF f = (OrgaddpayTbAF) form;
    OrgaddpayTbAF orgaddpayTbAF=new OrgaddpayTbAF();
    Map payTypeMap=BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
    orgaddpayTbAF.setPayTypeMap(payTypeMap);
    request.setAttribute("orgaddpayTbAF", orgaddpayTbAF);
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "orgAddPay.id", "DESC", criterions);
    String paginationKey = OrgaddpayTbShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    
    f.reset(mapping, request);
}catch(Exception e){
  e.printStackTrace();
}

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "show_orgaddpay";
  }


  protected HashMap makeCriterionsMap(OrgaddpayTbAF form) {
    HashMap m = new HashMap();
    String id = form.getId();
    if (!(id == null || "".equals(id))) {
      try {
        int iid = Integer.parseInt(id);
        m.put("id", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("id", new Integer(-10000));
      }
    }
    String name = form.getName();
    if (!(name == null || name.length() == 0))
      m.put("name", form.getName());
    // TODO 需要继续添加查询条件！！！
    String status = form.getStatus();
    if(!(status == null || status.length() == 0)){
      m.put("status", form.getStatus());
    }
    String payMonth = form.getPayMonth();
    if(!(payMonth == null || payMonth.length() == 0)){
      m.put("payMonth", form.getPayMonth());
    }
    //登记、入帐等
    String payType = form.getPayType();
    if(!(payType == null || payType.length() == 0)){
      m.put("payType", form.getPayType());
    } 
    String payMoney = form.getPayMoney();
    if(!(payMoney == null || payMoney.length() == 0)){
      m.put("payMoney", form.getPayMoney());
    }
    String settlementDate = form.getSettlementDate();
    if(!(settlementDate == null || settlementDate.length() == 0)){
      m.put("settlementDate", form.getSettlementDate());
    }
    String settlementDate1 = form.getSettlementDate1();
    if(!(settlementDate1 == null || settlementDate1.length() == 0)){
      m.put("settlementDate1", form.getSettlementDate1());
    }
    String compare = form.getCompare();
    if(!(compare == null || compare.length() == 0)){
      m.put("compare", form.getCompare());
    }
    String settDate = form.getSettDate();
    if(!(settDate == null || settDate.length() == 0)){
      m.put("settDate", form.getSettDate());
    }
    String settDate1 = form.getSettDate1();
    if(!(settDate1 == null || settDate1.length() == 0)){
      m.put("settDate1", form.getSettDate1());
    }
    String collBankId = form.getCollBankId();
    if(!(collBankId == null || collBankId.length() == 0)){
      m.put("collBankId", form.getCollBankId());
    }
    return m;
  }

}
