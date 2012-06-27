package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.form.PastyearscontrasAF;
/**
 * 
 * @author ÓÚÇì·á
 *
 */
public class PastyearscontrastFindAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    PastyearscontrasAF pastyearscontrasAF = (PastyearscontrasAF)form;
    HashMap criterions = makeCriterionsMap(pastyearscontrasAF);
    Pagination pagination = new Pagination(0, 10, 1, "null", "ASC", criterions);
    String paginationKey = getPaginationKey();
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("to_showAC");
  }
  
  protected String getPaginationKey() {
    return PastyearscontrastShowAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(PastyearscontrasAF form) {
    HashMap m = new HashMap();
    
    String office = form.getOfficeCode();
    if(office!=null && !"".equals(office)){
      m.put("office", office);
    }
    String bank = form.getCollectionBankId();
    if(bank!=null && !"".equals(bank)){
      m.put("bank", bank);
    }
    String orgCharacter = form.getOrgChracter();
    if(orgCharacter!=null && !"".equals(orgCharacter)){
      m.put("orgCharacter", orgCharacter);
    }
    String dept = form.getDept();
    if(dept!=null && !"".equals(dept)){
      m.put("dept", dept);
    }
    String ragion = form.getRagion();
    if(ragion != null && !"".equals(ragion)){
      m.put("ragion", ragion);
    }
    String startDate = form.getStartDate();
    if(startDate != null && !"".equals(startDate)){
      m.put("startDate", startDate);
    }
    String endDate = form.getEndDate();
    if(endDate !=null && !"".equals(endDate)){
      m.put("endDate", endDate);
    }
    return m;  
  }
}
