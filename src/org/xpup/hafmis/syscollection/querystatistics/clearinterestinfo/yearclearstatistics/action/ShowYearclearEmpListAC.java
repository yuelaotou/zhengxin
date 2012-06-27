package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.bsinterface.IYearclearstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto.YearclearstatisticsHeadDTO;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.form.YearclearstatisticsListAF;

public class ShowYearclearEmpListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action.ShowYearclearEmpListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    ActionMessages messages = null;
    try {
      /**
       * 分页
       */
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      YearclearstatisticsListAF af = (YearclearstatisticsListAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      IYearclearstatisticsBS yearclearstatisticsBS = (IYearclearstatisticsBS) BSUtils
          .getBusinessService("yearclearstatisticsBS", this, mapping.getModuleConfig());

      String headid=(String)request.getParameter("headid");
      String orgid=(String)request.getParameter("orgid");
      String money=(String)request.getParameter("money");
      if((headid!=null&&!headid.equals(""))&&(orgid!=null&&!orgid.equals(""))){
        pagination.getQueryCriterions().put("headid", headid);
        pagination.getQueryCriterions().put("orgid", orgid);
        if(money!=null){
          if(new BigDecimal (money).compareTo(new BigDecimal("0.00"))>0){
            pagination.getQueryCriterions().put("isZero", "0");
          }else{
            pagination.getQueryCriterions().put("isZero", "1");
          }
        }
      }

      List list = null;
      YearclearstatisticsHeadDTO dto = new YearclearstatisticsHeadDTO();

      if(!pagination.getQueryCriterions().isEmpty()){
        list = yearclearstatisticsBS.findYearclearEmpAllByCriterions(pagination,securityInfo);
        
        if(list!=null&&list.size()!=0){
          List listcount = (List)pagination.getQueryCriterions().get("listcount_yearclear");
          dto = yearclearstatisticsBS.findYearclearEmpListHead(listcount);// 查询合计项
        }
        af.setList(list);
      }
      request.setAttribute("yearclearstatisticsListAF", af);
      request.setAttribute("yearclearstatisticsHeadDTO", dto);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_yearclearEmp_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "aa001.id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }

}
