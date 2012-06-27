package org.xpup.hafmis.syscollection.paymng.monthpay.action;

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
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayTbFindAF;

/**
 * 根据单位编号、单位名称查询单位信息
 * 
 *@author 李娟
 *2007-6-27
 */
public class MonthpayTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    MonthpayTbFindAF f = (MonthpayTbFindAF) form;
    MonthpayTbFindAF monthpayTbFindAF=new MonthpayTbFindAF();
    Map payTypeMap=BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
    monthpayTbFindAF.setPayTypeMap(payTypeMap);
    request.setAttribute("monthpayTbFindAF", monthpayTbFindAF);
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "monthPayment.id", "DESC", criterions);
    String paginationKey = MonthpayTbShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "show_payment";
  }


  protected HashMap makeCriterionsMap(MonthpayTbFindAF form) {
    //以下带trim()的，是经过修改的。
    HashMap m = new HashMap();
    String id = form.getId().trim();
    if (!(id == null || "".equals(id))) {
      try {
        int iid = Integer.parseInt(id);
        m.put("id", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("id", new Integer(-10000));
      }
    }
    String name = form.getName().trim();
    if (!(name == null || name.length() == 0))
      m.put("name", form.getName().trim());
    // TODO 需要继续添加查询条件！！！
    String status = form.getStatus().trim();
    if(!(status == null || status.length() == 0)){
      m.put("status", form.getStatus().trim());
    }
    String inceptMonth = form.getInceptMonth().trim();
    if(!(inceptMonth == null || inceptMonth.length() == 0)){
      m.put("inceptMonth", form.getInceptMonth().trim());
    }
    String payMonth = form.getPayMonth().trim();
    if(!(payMonth == null || payMonth.length() == 0)){
      m.put("payMonth", form.getPayMonth().trim());
    }
    //登记、入帐等
    String payType = form.getPayType().trim();
    if(!(payType == null || payType.length() == 0)){
      m.put("payType", form.getPayType().trim());
    }
    String inceptPayMoney = form.getInceptPayMoney().trim() ;
    if(!(inceptPayMoney == null || inceptPayMoney.length() == 0)){
      m.put("inceptPayMoney", form.getInceptPayMoney().trim());
    }
    String payMoney = form.getPayMoney().trim();
    if(!(payMoney == null || payMoney.length() == 0)){
      m.put("payMoney", form.getPayMoney().trim());
    }
    String inceptSettlementDate = form.getInceptSettlementDate().trim() ;
    if(!(inceptSettlementDate == null || inceptSettlementDate.length() == 0)){
      m.put("inceptSettlementDate", form.getInceptSettlementDate().trim());
    }
    String settlementDate = form.getSettlementDate().trim();
    if(!(settlementDate == null || settlementDate.length() == 0)){
      m.put("settlementDate", form.getSettlementDate().trim());
    }  
    String settDate = form.getSettDate().trim();
    if(!(settDate == null || settDate.length() == 0)){
      m.put("settDate", form.getSettDate().trim());
    }  
    String settDate1 = form.getSettDate1().trim();
    if(!(settDate1 == null || settDate1.length() == 0)){
      m.put("settDate1", form.getSettDate1().trim());
    }
    String collBankId = form.getCollBankId();
    if(!(collBankId == null || collBankId.length() == 0)){
      m.put("collBankId", form.getCollBankId());
    }
    
    return m;
  }

}
