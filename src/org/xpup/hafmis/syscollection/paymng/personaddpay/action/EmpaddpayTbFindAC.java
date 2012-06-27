package org.xpup.hafmis.syscollection.paymng.personaddpay.action;

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
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayMaintainAF;

public class EmpaddpayTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
try{
    PersonAddPayMaintainAF f = (PersonAddPayMaintainAF) form;
    PersonAddPayMaintainAF personAddPayMaintainAF=new PersonAddPayMaintainAF();
    Map payTypeMap=BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
    personAddPayMaintainAF.setBizStatusMap(payTypeMap);
    request.setAttribute("personAddPayMaintainAF", personAddPayMaintainAF);
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "empAddPay.id", "DESC", criterions);
    String paginationKey = PersonAddPayTbShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    
    f.reset(mapping, request);
}catch(Exception e){
  e.printStackTrace();
}

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_personAddPayMaintain_show";
  }


  protected HashMap makeCriterionsMap(PersonAddPayMaintainAF form) {
    HashMap m = new HashMap();

    String orgId = form.getOrgId();
    if (!(orgId == null || "".equals(orgId))) {
      try {
        int iid = Integer.parseInt(orgId);
        m.put("orgId", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("orgId", new Integer(-10000));
      }
    }
    String unitName = form.getUnitName();
    if (!(unitName == null || unitName.length() == 0))
      m.put("unitName", form.getUnitName());
    // TODO 需要继续添加查询条件！！！
    String addPayAmount = form.getAddPayAmount().toString().trim();
    if(!(addPayAmount == null || addPayAmount.length() == 0)){
      m.put("addPayAmount", form.getAddPayAmount().toString().trim());
    }
    String bizStatus = form.getBizStatus();
    if(!(bizStatus == null || bizStatus.length() == 0)){
     m.put("bizStatus", form.getBizStatus());   
    }else{
      m.put("bizStatus", "all");
    }
    //登记、入帐等
    String beginMonth = form.getBeginMonth();
    if(!(beginMonth == null || beginMonth.length() == 0)){
      m.put("beginMonth", form.getBeginMonth());
    } 
    String endMonth = form.getEndMonth();
    if(!(endMonth == null || endMonth.length() == 0)){
      m.put("endMonth", form.getEndMonth());
    }
    String opTime = form.getOpTime();
    if(!(opTime == null || opTime.length() == 0)){
      m.put("opTime", form.getOpTime());
    }
    String payMonth = form.getPayMonth();
    if(!(payMonth == null || payMonth.length() == 0)){
      m.put("payMonth", form.getPayMonth());
    }
    String opTime1 = form.getOpTime1();
    if(!(opTime1 == null || opTime1.length() == 0)){
      m.put("opTime1", form.getOpTime1());
    }
    String payMonth1 = form.getPayMonth1();
    if(!(payMonth1 == null || payMonth1.length() == 0)){
      m.put("payMonth1", form.getPayMonth1());
    }
    String collBankId = form.getCollBankId();
    if(!(collBankId == null || collBankId.length() == 0)){
      m.put("collBankId", form.getCollBankId());
    }
    return m;
  }

}
