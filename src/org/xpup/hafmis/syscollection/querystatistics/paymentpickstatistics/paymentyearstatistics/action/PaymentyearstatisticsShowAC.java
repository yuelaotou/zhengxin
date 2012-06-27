package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.action;

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
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.bsinterface.IPaymentyearstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.dto.PaymentyearstatisticsDTO;


/**
 * 统计查询 -- 缴存提取统计 -- 公积金缴存情况年报表
 * @author yqf
 *  20080920
 *
 */
public class PaymentyearstatisticsShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.action.PaymentyearstatisticsShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    IPaymentyearstatisticsBS paymentyearstatisticsBS = (IPaymentyearstatisticsBS) BSUtils
        .getBusinessService("paymentyearstatisticsBS", this, mapping
            .getModuleConfig());
    PaymentyearstatisticsDTO paymentyearstatisticsDTO = new PaymentyearstatisticsDTO();
    //办事处
    List officeList = securityInfo.getOfficeList();
    List officeList1 = new ArrayList();
    String officeCode = "";
    String key = "";// 点击过查询按钮才有值
    String bizDate = "";
    OfficeDto officedto = null;
    Iterator itr1 = officeList.iterator();
    while (itr1.hasNext()) {
      officedto = (OfficeDto) itr1.next();
      officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
          .getOfficeName(), officedto.getOfficeCode()));
    }
    request.getSession(true).setAttribute("officeList", officeList1);
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      officeCode = (String) pagination.getQueryCriterions().get("officeCode");
      key = (String) pagination.getQueryCriterions().get("key");
      bizDate = (String) pagination.getQueryCriterions().get("bizDate");
      System.out.println("---办事处--:"+officeCode);
      // officeCode = "402881fd1ab2e3e0011ab2f548bb000d";
      if ("4028810c120af23c01120b14ed840005".equals(officeCode)) {// 市本级
        paymentyearstatisticsDTO = paymentyearstatisticsBS
            .queryPaymentyearstatisticsDTO1(pagination, securityInfo);
      }
      if ("402881651bde207d011bde2453110002".equals(officeCode)) {// 大石桥
        paymentyearstatisticsDTO = paymentyearstatisticsBS
            .queryPaymentyearstatisticsDTO2(pagination, securityInfo);
      }
      if ("402881651bde207d011bde24dcd20003".equals(officeCode)) {// 盖州
        paymentyearstatisticsDTO = paymentyearstatisticsBS
        .queryPaymentyearstatisticsDTO3(pagination, securityInfo);
      }
      if ("402881651bde207d011bde25f7990019".equals(officeCode)) {// 鲅鱼圈
        paymentyearstatisticsDTO = paymentyearstatisticsBS
        .queryPaymentyearstatisticsDTO4(pagination, securityInfo);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (key != null && !"".equals(key)) {
      paymentyearstatisticsDTO.setKey(key);
    }
    String operator = securityInfo.getRealName();
    paymentyearstatisticsDTO.setOperator(operator);
    paymentyearstatisticsDTO.setDate(bizDate+"年01月01日 " +"至 "+bizDate+"年12月31日");
    request.getSession().setAttribute("paymentyearstatisticsDTO",
        paymentyearstatisticsDTO);
    if (officeCode != null && !"".equals(officeCode)) {
      if ("4028810c120af23c01120b14ed840005".equals(officeCode)) {// 市本级
        return mapping.findForward("to_paymentyearstatistics1.jsp");
      }
      if ("402881651bde207d011bde2453110002".equals(officeCode)) {// 大石桥
        return mapping.findForward("to_paymentyearstatistics2.jsp");
      }
      if ("402881651bde207d011bde24dcd20003".equals(officeCode)) {// 盖州
        return mapping.findForward("to_paymentyearstatistics3.jsp");
      }
      if ("402881651bde207d011bde25f7990019".equals(officeCode)) {// 鲅鱼圈
        return mapping.findForward("to_paymentyearstatistics4.jsp");
      }
    }
    return mapping.findForward("to_paymentyearstatistics1.jsp");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    // TODO Auto-generated method stub
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
