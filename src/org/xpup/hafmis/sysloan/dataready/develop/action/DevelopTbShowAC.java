package org.xpup.hafmis.sysloan.dataready.develop.action;

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
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.form.DevelopFindAF;

/**
 * 显示开发商维护列表页的Action
 * 
 * @author 付云峰
 */
public class DevelopTbShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.dataready.develop.form.DevelopFindAF.DevelopTbShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub

    DevelopFindAF developFindAF = new DevelopFindAF();
    List officeList = null;
    saveToken(request);
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      // 取出用户权限办事处,显示在下拉菜单中
      List temp_officeList = securityInfo.getAllOfficeList();
      officeList = new ArrayList();
      OfficeDto officedto = null;
      Iterator it = temp_officeList.iterator();
      while (it.hasNext()) {
        officedto = (OfficeDto) it.next();
        officeList.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }

      // 从枚举中得到开发商状态
      Map developerStMap = BusiTools
          .listBusiProperty(BusiConst.PLCOMMONSTATUS_1);
      developFindAF.setDeveloperStMap(developerStMap);

      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      // 得到列表中的内容
      List list = developBS.findDevelopList_info(pagination, null);
      developFindAF.setList(list);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    request.setAttribute("developFindAF", developFindAF);
    request.setAttribute("officeList", officeList);
    return mapping.findForward("to_develop_find");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "p5.developer_code", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }

}
