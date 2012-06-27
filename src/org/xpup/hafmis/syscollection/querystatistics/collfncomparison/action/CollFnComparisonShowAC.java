package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action;

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
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.bsinterface.IOrgCollInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface.ICollFnComparisonBS;
import org.xpup.hafmis.syscollection.querystatistics.collfncomparison.form.CollFnComparisonAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class CollFnComparisonShowAC extends Action {
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    CollFnComparisonAF collFnComparisonAF = new CollFnComparisonAF();
    
    try {
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      
      List officeList1 = null;
        // 取出用户权限办事处,显示在下拉菜单中
        List officeList = securityInfo.getOfficeList();
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
        }
      
      List collBankList1 = null;
        // 取出用户下归集行
        List collBankList = securityInfo.getCollBankList();
        collBankList1 = new ArrayList();
        Userslogincollbank userslogincollbank = null;
        Iterator itr2 = collBankList.iterator();
        while (itr2.hasNext()) {
          userslogincollbank = (Userslogincollbank) itr2.next();
          collBankList1.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName().toString(), userslogincollbank
                  .getCollBankId().toString()));
        }
        
        String paginationKey = getPaginationKey();
        Pagination pagination = getPagination(paginationKey,request,collFnComparisonAF);
        
        pagination.getQueryCriterions().put("securityInfo", securityInfo);
        PaginationUtils.updatePagination(pagination, request);
        ICollFnComparisonBS collFnComparisonBS = (ICollFnComparisonBS) BSUtils
        .getBusinessService("collFnComparisonBS", this, mapping
            .getModuleConfig());
        
        Object[] orgCollInfoList = collFnComparisonBS.findOrgCollInfo(pagination);
        List resultList = (List)orgCollInfoList[0];
        collFnComparisonAF.setList(resultList);
        request.setAttribute("officeList1", officeList1);
        request.setAttribute("collBankList1", collBankList1);
        request.setAttribute("collFnComparisonAF", collFnComparisonAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_collfncomparison_show");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request, CollFnComparisonAF collFnComparisonAF) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a1.id", "DESC", new HashMap(0));
      pagination.getQueryCriterions().put("searchDTO", collFnComparisonAF.getDto());
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  
  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
  
}
