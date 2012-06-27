package org.xpup.hafmis.syscollection.tranmng.tranout.action;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbAF;

public class Tran_FindTbAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
   
    String status = request.getParameter("payStatus");
    TranTbAF tranTbAF = (TranTbAF) form;
    
    
    
    
    HashMap criterions = makeCriterionsMap(tranTbAF);
    Pagination  pagination = new Pagination(0, 10, 1, "tot.id", "DESC", criterions);
    
//    Pagination pagination = (Pagination) request.getSession().getAttribute(Tran_showFindTbAC.PAGINATION_KEY);
    String paginationKey = getPaginationKey();

    request.getSession().setAttribute(paginationKey, pagination);
 
    // tranTbAF.reset(mapping, request);
    return mapping.findForward("to_showFindAC");
  }

  
  
  protected String getPaginationKey() {
    return Tran_showFindTbAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(TranTbAF form) {
    HashMap m = new HashMap();
    
    String orgOutid = form.getOutOrgId();
   
    if (orgOutid != null && !"".equals(orgOutid)) {
      m.put("orgOutid", Integer.parseInt(orgOutid.trim())+"");
    }

    String orgOutName = form.getOutOrgname();
    if (orgOutName != null && orgOutName.trim().length() > 0) {
      m.put("orgOutName", form.getOutOrgname().trim());
    }

    String orgInid = form.getInOrgId(); // 转入单位编号
    if (orgInid != null && orgInid.trim().length() > 0) {
      m.put("orgInid", Integer.parseInt(orgInid.trim())+"");
    }
    String orgInName = form.getInOrgName();// 转入单位名称
    if (orgInName != null && orgInName.trim().length() > 0) {
      m.put("orgInName", form.getInOrgName().trim());
    }
    String Note_num = form.getNote_num();
    if (Note_num != null && Note_num.trim().length() > 0) {
      m.put("Note_num", form.getNote_num().trim());// 票据编号
    }
    String Doc_num = form.getDoc_num();
    if (Doc_num != null && Doc_num.trim().length() > 0) {
      m.put("Doc_num", Doc_num.trim());
    }
    String Dates = form.getDates();// 凭证
    if (Dates != null && Dates.trim().length() > 0) {
      m.put("Dates", Dates.trim());
    }
    String Datesa = form.getDatesa();// 凭证
    if (Datesa != null && Datesa.trim().length() > 0) {
      m.put("Datesa", Datesa.trim());
    }
    String status = form.getPayStatus();
    if(status !=null && status.trim().length() > 0) {
      m.put("status", status.trim());
    }
    String collBankId = form.getCollBankId();
    if(!(collBankId == null || collBankId.trim().length() == 0)){
      m.put("collBankId", form.getCollBankId().trim());
    }
    return m;
  }


}
