package org.xpup.hafmis.sysloan.accounthandle.clearaccount.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.form.ClearAccountBalanceAF;

/**
 * @author jj 2007-11-01
 */
public class ClearaccountTbFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {    
    ClearAccountBalanceAF f = (ClearAccountBalanceAF)form;
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, 10, 1,
        null, "", criterions);
    String paginationKey = ClearaccountTbShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    f.reset(mapping, request);
    return mapping.findForward(getForword());
  }
  protected String getForword() {
    return "clearaccountTbShowAC";
  }
  protected HashMap makeCriterionsMap(ClearAccountBalanceAF form) {
    HashMap m = new HashMap();  
    String bizType = form.getBizType();
    if (!(bizType == null || bizType.length() == 0))
      m.put("bizType", form.getBizType());
    // TODO 需要继续添加查询条件！！！
    String loanBankId = form.getLoanBankId();
    if(!(loanBankId == null || loanBankId.length() == 0)){
      m.put("loanBankId", form.getLoanBankId().trim());
    }
    String makePerson = form.getMakePerson();
    if(!(makePerson == null || makePerson.length() == 0)){
      m.put("makePerson", form.getMakePerson().trim());
    }
//    String startDate = form.getStartDate();
//    if(!(startDate == null || startDate.length() == 0)){
//      m.put("startDate", form.getStartDate().trim());
//    }
//    String endDate = form.getEndDate();
//    if(!(endDate == null || endDate.length() == 0)){
//      m.put("endDate", form.getEndDate().trim());
//    }
//    if(startDate != null && (endDate == null||endDate.length() == 0)){
//      m.put("endDate", startDate.trim());
//    } 
    
    
    String startday = form.getStartDate();
    if(!(startday == null || startday.length() == 0)){
      if(startday.length() == 6){
        String a=startday;
        startday=startday+"01";
        m.put("startDate", startday);
        m.put("endDate", a+"31");
        m.put("specialType", "1");
      }else{
        m.put("startDate", form.getStartDate());
        if(form.getEndDate()==null || form.getEndDate().equals("")){
          m.put("endDate", form.getStartDate());
        }
      }
    }
    String untilday = form.getEndDate();
    if(!(untilday == null || untilday.length() == 0)){
      m.put("endDate", form.getEndDate());
    }
    return m;
  }

}
