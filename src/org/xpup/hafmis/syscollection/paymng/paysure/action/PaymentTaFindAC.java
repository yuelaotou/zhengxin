package org.xpup.hafmis.syscollection.paymng.paysure.action;

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
import org.xpup.hafmis.syscollection.paymng.paysure.bsinterface.IPaymentHeadBS;
import org.xpup.hafmis.syscollection.paymng.paysure.form.PaymentAF;


/**
 * @author 于庆丰 2007-06-29
 */

public class PaymentTaFindAC extends Action {

  /**
   * 根据条件查询
   *
   */
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.paymng.paysure.action.PaymentTaFindAC";
  public BigDecimal sumPayMoney = new BigDecimal(0);
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    try {     
      saveToken(request);
      //获取权限
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
     //分页
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
       IPaymentHeadBS paymentHeadBS = (IPaymentHeadBS) BSUtils
       .getBusinessService("paymentHeadBS", this, mapping.getModuleConfig());

       PaymentAF paymentAF = new PaymentAF();
       paymentAF = paymentHeadBS.findPaymentListByPaymentFindAF(pagination,securityInfo);
       //业务类型下拉框
       Map map = BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
       paymentAF.setMap(map);
       //业务类型下拉框
       Map other_map = BusiTools.listBusiProperty(BusiConst.BUSINESSTYPE);
       paymentAF.setOther_map(other_map);
       request.getSession().setAttribute("thepaymentAF", paymentAF);

    } catch (Exception e) {
     e.printStackTrace();
    }
    return mapping.findForward("to_PaymentHead.jsp");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "paymentHead.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }


}
