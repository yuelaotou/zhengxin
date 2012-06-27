package org.xpup.hafmis.syscollection.paymng.orgoverpay.action;

import java.math.BigDecimal;
import java.util.HashMap;
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
import org.xpup.hafmis.syscollection.paymng.orgoverpay.bsinterface.IOrgoverpayBS;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.form.OrgoverpayTbAF;


public class OrgoverpayTbShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.orgoverpay.action.OrgoverpayTbShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      try{ 
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        OrgoverpayTbAF orgoverpayTbAF=new OrgoverpayTbAF();
        Map bizStatusMap=BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
        orgoverpayTbAF.setPayTypeMap(bizStatusMap);
        BigDecimal money=new BigDecimal(0.00);
        Pagination pagination = getPagination(PAGINATION_KEY, request); 
        PaginationUtils.updatePagination(pagination, request);    
        IOrgoverpayBS orgoverpayBS = (IOrgoverpayBS) BSUtils
        .getBusinessService("orgoverpayBS", this, mapping.getModuleConfig());   
        List orgoverpayList = orgoverpayBS.queryOrgoverpayList(pagination,securityInfo);
        money=orgoverpayBS.getEmpaddpayMoney(pagination,securityInfo);
        if(orgoverpayList.size()!=0){
          orgoverpayTbAF.setListCount("1");
        }else{
          orgoverpayTbAF.setListCount("2");
        }
        if(money!=null){
          orgoverpayTbAF.setMoney(money.toString());          
        }
        request.setAttribute("orgoverpayList", orgoverpayList);
        request.setAttribute("orgoverpayTbAF", orgoverpayTbAF);
        }catch(Exception e){
        e.printStackTrace();
        }
 
   return mapping.findForward("to_orgoverpay_lb.jsp");  

  }
  private  Pagination getPagination(String paginationKey, HttpServletRequest request) {
    
    Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);    
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "orgExcessPayment.id", "DESC",new HashMap(0));    
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
