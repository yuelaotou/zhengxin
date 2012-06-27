package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.action;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.bsinterface.ICollectionstatisticsBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.form.CollectionstatisticsAF;

/**
 * 
 * @author 于庆丰
 * 2007-07-30
 *
 */
public class ShowCollectionstatisticsListAC extends Action {
                                             
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.action.ShowCollectionstatisticsListAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    try{
      /**
       * 分页,获取权限
       */
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      ICollectionstatisticsBS collectionstatisticsBS = (ICollectionstatisticsBS) BSUtils.getBusinessService(
          "collectionstatisticsBS", this, mapping.getModuleConfig());
      CollectionstatisticsAF csAF = new CollectionstatisticsAF();

      csAF = collectionstatisticsBS.findChgorgrateByCriterions(pagination, securityInfo);
//    单位性质下拉框枚举
      Map orgCharacterMap = BusiTools.listBusiProperty(BusiConst.NATUREOFUNITS);
      csAF.setOrgCharacterMap(orgCharacterMap);
      //主管部门下拉框枚举
      Map deptMap = BusiTools.listBusiProperty(BusiConst.AUTHORITIES);
      csAF.setDeptMap(deptMap);
      //所在地区下拉框枚举
      Map ragionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
      csAF.setRagionMap(ragionMap);
      request.setAttribute("theCsAF", csAF);
      request.getSession().setAttribute("printList", csAF.getAlllist());
      csAF.reset(mapping, request);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_theStatCollectCircsList.jsp");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
  
      pagination = new Pagination(0, 10, 1, "aa001.id", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}
