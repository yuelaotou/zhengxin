package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.OrgkhCriteronsAF;

public class OrgOpenShowListAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accountmng.accountopen.action.OrgOpenShowListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    OrgkhCriteronsAF af = (OrgkhCriteronsAF) form;
    // 清空查询后表单的内容。
    af.reset();
    Map orgstateMap = BusiTools.listBusiProperty(BusiConst.ORGPAYWAY);
    af.setPayModeMap(orgstateMap);
    Map orgpaywayMap = BusiTools.listBusiProperty(BusiConst.ORGSTATE);
    af.setStatusMap(orgpaywayMap);
    request.setAttribute("orgkhCriteronsAF", af);
    // 取得权限
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try {
      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      modifyPagination(pagination);
      PaginationUtils.updatePagination(pagination, request);
      pagination.getQueryCriterions().put("pagetype", "1");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping.getModuleConfig());
      List organizations = orgOpenAccountBS.findOrganizationsDwkh(pagination,securityInfo);
      
      request.setAttribute("organizations", organizations);
      af.setListCount(organizations.size());
      //判断是否是单位版，按钮控制
      request.setAttribute("isType", securityInfo.getIsOrgEdition()+"");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "organizations";
  }

  protected void modifyPagination(Pagination pagination) {
    
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "orgs.id", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

}
