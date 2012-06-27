package org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.bsinterface.IPaymntPickBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.form.PaymntPickAF;

public class PaymntPickTaShowAC extends Action {
  /**
   * @author wangshuang
   * 2003-09-23
   * 提取统计月报表
   */
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.action.PaymntPickTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PaymntPickAF af = null;
    try{
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      String url = (String) request.getAttribute("url");
      // 办事处信息显示在下拉列表中
      List officeList = securityInfo.getOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
      }
      request.setAttribute("officeList", officeList1);
      Pagination pagination = (Pagination) request.getSession().getAttribute(PAGINATION_KEY);
      if(pagination == null){
        pagination = getPagination(PAGINATION_KEY, request);
        af = new PaymntPickAF();
      }else{
        IPaymntPickBS paymntPickBS = (IPaymntPickBS)BSUtils
          .getBusinessService("paymntPickBS", this, mapping.getModuleConfig());
        af = paymntPickBS.queryPaymntPickDetail(pagination, securityInfo);
        request.setAttribute("list", af.getList());
        request.getSession().setAttribute("cellList", af.getList());
      }
      
      request.setAttribute("paymntPickAF", af);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_paymntpicktashow");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "", "",
          new HashMap(0));

      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}


