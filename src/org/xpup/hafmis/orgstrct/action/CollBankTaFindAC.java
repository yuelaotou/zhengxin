package org.xpup.hafmis.orgstrct.action;

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
import org.xpup.hafmis.orgstrct.bizsrvc.IToSecurityMngBS;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.orgstrct.form.CollBankTaAF;

/**
 * 根据单位编号、单位名称查询单位信息
 * 
 *@author 李娟
 *2007-6-27
 */
public class CollBankTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    CollBankTaAF f = (CollBankTaAF) form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    IToSecurityMngBS toSecurityMngBS = (IToSecurityMngBS) BSUtils
    .getBusinessService("toSecurityMngBS", this, mapping.getModuleConfig());
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "b.office", "ASC", criterions);
    CollBankTaAF collBankTaAF = toSecurityMngBS.findBankListByUser(pagination, securityInfo);
    String paginationKey = CollBankTaShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    

    request.setAttribute("collBankTaAF", collBankTaAF);
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "show_collbank";
  }


  protected HashMap makeCriterionsMap(CollBankTaAF form) {
    HashMap m = new HashMap();
    String officecode = form.getOfficecode();
    if (!(officecode == null || officecode.length() == 0))
      m.put("officecode", form.getOfficecode());
    return m;
  }

}
