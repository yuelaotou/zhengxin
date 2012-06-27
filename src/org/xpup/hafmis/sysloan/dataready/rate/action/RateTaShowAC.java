package org.xpup.hafmis.sysloan.dataready.rate.action;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanRateType;
import org.xpup.hafmis.sysloan.dataready.rate.bsinterface.IRateBS;
import org.xpup.hafmis.sysloan.dataready.rate.form.RateShowAF;
import org.xpup.hafmis.sysloan.dataready.ratetype.bsinterface.IRateTypeBS;

public class RateTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.dataready.rate.action.RateTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    ActionMessages messages = null;
    RateShowAF rateShowAF=new RateShowAF();
    try {
      //启用的时候是由子页面提交的，所以要保存信息在此获取。其他的时候不用。
      String infoRateMassage=(String)request.getSession().getAttribute("ratemassage");
      if(infoRateMassage!=null&&!infoRateMassage.equals("")){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(infoRateMassage,
        false));
        saveErrors(request, messages);
        request.getSession().setAttribute("ratemassage", null);
      }
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);

      saveToken(request);
      IRateBS rateBS = (IRateBS) BSUtils.getBusinessService("rateBS", this,
          mapping.getModuleConfig());
      IRateTypeBS rateTypeBS = (IRateTypeBS) BSUtils.getBusinessService("rateTypeBS", this,
          mapping.getModuleConfig());
      rateShowAF=rateBS.findRateList(pagination);

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      
      List officeList = securityInfo.getAllOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("officeList1", officeList1);
      //利率类型下拉
      List rateTypeList = rateTypeBS.findRateTypeListAll();
      List rateTypeList1 = new ArrayList();
      Iterator iterator = rateTypeList.iterator();
      LoanRateType rateType = null;
      while (iterator.hasNext()) {
        rateType = (LoanRateType) iterator.next();
        rateTypeList1.add(new org.apache.struts.util.LabelValueBean(rateType.getLoanRateExplain(),rateType.getLoanRateType()));
      }
      request.setAttribute("rateTypeList1", rateTypeList1);
      
      }catch(BusinessException bex){
       messages=new ActionMessages();
       messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
       false));
       saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    request.setAttribute("rateShowAF", rateShowAF);
    return mapping.findForward("to_rate_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "loanRate.loanRateId", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}