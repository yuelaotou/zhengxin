package org.xpup.hafmis.syscollection.paymng.orgoverpay.action;

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
import org.xpup.hafmis.syscollection.paymng.orgoverpay.form.OrgoverpayTbAF;

public class OrgoverpayTbFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
try{
  OrgoverpayTbAF f = (OrgoverpayTbAF) form;
  OrgoverpayTbAF orgoverpayTbAF=new OrgoverpayTbAF();
    Map payTypeMap=BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
    orgoverpayTbAF.setPayTypeMap(payTypeMap);
    request.setAttribute("orgoverpayTbAF", orgoverpayTbAF);
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,  "orgExcessPayment.id", "DESC", criterions);    
    String paginationKey = OrgoverpayTbShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);  
    f.reset(mapping, request);
}catch(Exception e){
  e.printStackTrace();
}

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "orgoverpayTbShowAC";
  }


  protected HashMap makeCriterionsMap(OrgoverpayTbAF form) {
    HashMap m = new HashMap();
    String orgId = form.getOrgId();
    if (!(orgId == null || orgId.length() == 0)){
      String org="";
      if(orgId.length()==10){
        org=orgId.substring(1, orgId.length());
        m.put("orgId", org);
      }else{
        m.put("orgId", form.getOrgId());
      }
     
      
    }
    String unitName = form.getUnitName();
    if (!(unitName == null || unitName.length() == 0))
      m.put("unitName", form.getUnitName());
    // TODO 需要继续添加查询条件！！！
    String payMoney = form.getPayMoney();
    if(!(payMoney == null || payMoney.length() == 0)){
      m.put("payMoney", form.getPayMoney());
    }
    String bizStatus = form.getBizStatus();
    if(!(bizStatus == null || bizStatus.length() == 0)){
      m.put("bizStatus", form.getBizStatus());  
    }else{
      m.put("bizStatus", "all");
    }


    String opTime = form.getOpTime();
    if(!(opTime == null || opTime.length() == 0)){
      m.put("opTime", form.getOpTime());
    }
    String payMonth = form.getPayMonth();
    if(!(payMonth == null || payMonth.length() == 0)){
      m.put("payMonth", form.getPayMonth());
    }String opTime1 = form.getOpTime1();
    if(!(opTime1 == null || opTime1.length() == 0)){
      m.put("opTime1", form.getOpTime1());
    }
    String payMonth1 = form.getPayMonth1();
    if(!(payMonth1 == null || payMonth1.length() == 0)){
      m.put("payMonth1", form.getPayMonth1());
    }
    return m;
  }

}
