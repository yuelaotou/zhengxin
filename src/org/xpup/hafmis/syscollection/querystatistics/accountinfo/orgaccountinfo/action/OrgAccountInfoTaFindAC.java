package org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.form.OrgAccountInfoAF;

/**
 * 根据单位编号、单位名称查询单位信息
 * 
 *@author 李娟
 *2007-6-27
 */
public class OrgAccountInfoTaFindAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    
    OrgAccountInfoAF f=(OrgAccountInfoAF)form;
    
    HashMap criterions = makeCriterionsMap(f);
    Pagination pagination = new Pagination(0, f.getPageSize(), 1,
        "orgHAFAccountFlow.id", "DESC", criterions);
    String paginationKey = OrgAccountInfoTaShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    
    f.reset(mapping, request);

    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_showOrgAccountInfo";
  }


  protected HashMap makeCriterionsMap(OrgAccountInfoAF form) {
    HashMap m = new HashMap();
    String orgid = form.getOrgid();
    if (!(orgid == null || "".equals(orgid))) {
      try {
        int iid = Integer.parseInt(orgid);
        m.put("orgid", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("orgid", new Integer(-10000));
      }
    }
    String orgname = form.getOrgname();
    if (!(orgname == null || orgname.length() == 0)){
      m.put("orgname", form.getOrgname());
    }

    String inOpDate = form.getInOpDate() ;
    if(!(inOpDate == null || inOpDate.length() == 0)){
      m.put("inOpDate", form.getInOpDate());
    }
    String opDate = form.getOpDate() ;
    if(!(opDate == null || opDate.length() == 0)){
      m.put("opDate", form.getOpDate());
    }

    String mode = form.getMode() ;
    if(!(mode == null || mode.length() == 0)){
      m.put("mode", form.getMode());
    }
    String officecode = form.getOfficecode() ;
    if(!(officecode == null || officecode.length() == 0)){
      m.put("officecode", form.getOfficecode());
    }
    String collBankId = form.getCollBankId() ;
    if(!(collBankId == null || collBankId.length() == 0)){
      m.put("collBankId", form.getCollBankId());
    }
    return m;
  }

}
