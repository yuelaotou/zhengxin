package org.xpup.hafmis.sysloan.loancallback.loancallback.action;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class LoancallbackTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.loancallback.action.LoancallbackTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      String param = (String) request.getAttribute("param");
      if (param != null) {
        request.getSession().setAttribute("param", param);
      } else {
        param = (String) request.getSession().getAttribute("param");
      }
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      pagination.getQueryCriterions().put("identifier", null);
      pagination.getQueryCriterions().put("aheadMoney", null);
      PaginationUtils.updatePagination(pagination, request);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
          .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
      LoancallbackTaAF af = new LoancallbackTaAF();
      saveToken(request);
      // 进入页面是要带权限的，从权限中取得是以中心为主还是以银行为主。以此来控制按钮灰显，要在action中设置状态。
      // 贷款还款类型1.中心为主2.银行为主
      int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
      // 根据权限中的还款类型判断以哪为主
      String plLoanReturnType = "";
      if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER) {
        plLoanReturnType = "1";// 中心为主
        String bizType = request.getParameter("bizType");
        pagination.getQueryCriterions().put("bizType", bizType);
        if (bizType != null && !bizType.equals("")) {
          if (bizType.equals("2")) {
            pagination.getQueryCriterions().put("callbackMonth", null);
          }
        }
        af = loancallbackBS
            .findShouldLoancallbackInfo(pagination, securityInfo);
        if (af.getBizType() != null && af.getBizType().equals("2")) {
          if (af.getSumCorpus() != null && !af.getSumCorpus().equals("")) {
            pagination.getQueryCriterions().put("qiankuanbenjin",
                af.getSumCorpus().toString());
          }

        }
      } else if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK) {
        plLoanReturnType = "2";// 银行为主
        af = loancallbackBS.findCallbacklistByLoanBank(pagination);
      }
      pagination.getQueryCriterions().put("shouldBackList",
          af.getShouldBackList());
      List monthYearList = new ArrayList();
      Iterator itr = af.getMonthYearList().iterator();
      while (itr.hasNext()) {
        ShouldBackListDTO dto = (ShouldBackListDTO) itr.next();
        monthYearList.add(new org.apache.struts.util.LabelValueBean(dto
            .getLoanKouYearmonth(), dto.getLoanKouYearmonth()));
      }
      af.setParam(param);
      pagination.getQueryCriterions().put("YearList", af.getMonthYearList());
      request.setAttribute("loancallbackTaAF", af);
      request.getSession(true).setAttribute("monthYearList", monthYearList);
      request
          .setAttribute("plLoanReturnType", String.valueOf(plLoanReturnType));
      af.reset(mapping, request);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("loancallback_jy");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      pagination.getQueryCriterions().put("bizType", "2");
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}