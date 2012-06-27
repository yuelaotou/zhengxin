package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action; 


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
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTaAFDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form.BadDebtDestroyTaAF;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;

public class BadDebtDestroyTaShowAC extends Action {
public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action.BadDebtDestroyTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
        //根据权限中的还款类型判断以哪为主
        String plLoanReturnType = "";
    try{
 
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);  
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
      .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
      BadDebtDestroyTaAFDTO afdto = new BadDebtDestroyTaAFDTO(); 
      BadDebtDestroyTaAF af = new BadDebtDestroyTaAF(); 
      saveToken(request);
      //进入页面是要带权限的，从权限中取得是以中心为主还是以银行为主。以此来控制按钮灰显，要在action中设置状态。
      //贷款还款类型1.中心为主2.银行为主
      int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
      if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER){
        plLoanReturnType = "1";//中心为主
        String bizType=request.getParameter("bizType");
        pagination.getQueryCriterions().put("bizType", bizType);
        afdto = badDebtDestroyBS.findShouldLoancallbackInfo(pagination, securityInfo);
      }else if(temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK){
        plLoanReturnType = "2";//银行为主
        afdto = badDebtDestroyBS.findCallbacklistByLoanBank(pagination);       
        af.setOrgName(afdto.getOrgName());
        af.setOrgType(afdto.getOrgType());
      }
      af.setShouldBackList(afdto.getShouldBackList());
      af.setBorrowerInfoDTO(afdto.getBorrowerInfoDTO());
      af.setSumCorpus(afdto.getSumCorpus());
      af.setSumInterest(afdto.getSumInterest());
      af.setSumMoney(afdto.getSumMoney());
      af.setRealMoney(afdto.getRealMoney());
      af.setMonthYear(afdto.getMonthYear());
       pagination.getQueryCriterions().put("shouldBackList",af.getShouldBackList());
       List monthYearList = new ArrayList();
       Iterator itr = afdto.getMonthYearList().iterator();
       while(itr.hasNext()){
         ShouldBackListDTO dto = (ShouldBackListDTO)itr.next();
         monthYearList.add(new org.apache.struts.util.LabelValueBean(dto.getLoanKouYearmonth(), dto.getLoanKouYearmonth()));
       }
      request.setAttribute("badDebtDestroyTaAF", af);
      request.getSession(true).setAttribute("monthYearList", monthYearList);
      af.reset(mapping, request);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
          false));
      saveErrors(request, messages);
    }catch(Exception ex){
      ex.printStackTrace();
    }
    request.setAttribute("plLoanReturnType", String.valueOf(plLoanReturnType));
    return mapping.findForward("baddebtdestroy_jy");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}