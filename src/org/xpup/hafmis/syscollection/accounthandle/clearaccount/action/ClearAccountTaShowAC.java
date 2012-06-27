package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountShowAF;
import org.xpup.security.common.domain.User;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author 李鹏 2007-7-10
 */
public class ClearAccountTaShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      saveToken(request);
      PaginationUtils.updatePagination(pagination, request);
      IclearAccountBS clearaccountBS = (IclearAccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      String temp_type = (String) request.getSession(false).getAttribute(
          "findaclearaccount_type");// 判断是否有瞒足条件-->为1可以到101查询
      ClearAccountShowAF clearAccountShowAF = new ClearAccountShowAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      if (securityInfo == null) {
        request.setAttribute("clearAccountShowAF", clearAccountShowAF);
      } else {

        List bankList = securityInfo.getCollBankList();
        List bankList1 = new ArrayList();
        Userslogincollbank bankdto = null;   
        Iterator itr1 = bankList.iterator();    
        while (itr1.hasNext()) {
          bankdto = (Userslogincollbank) itr1.next();   
          bankList1.add(new org.apache.struts.util.LabelValueBean(bankdto.getCollBankName().toString(), bankdto.getCollBankId().toString()));
        }
        request.getSession(true).setAttribute("bankList1", bankList1);

        List operList = securityInfo.getUserList();
        List operList1 = new ArrayList();
        User user = null;
        Iterator itr2 = operList.iterator();
        while (itr2.hasNext()) {
          user = (User) itr2.next();
          operList1.add(new org.apache.struts.util.LabelValueBean(user
              .getUsername(), user.getUsername()));
        }
        request.getSession(true).setAttribute("operList1", operList1);

        List returnList = null;//可以全部扎账的列表
        
        if (temp_type == null) {//默认情况
          clearAccountShowAF = clearaccountBS
              .findOrgHAFAccountFlowDefByPagination(pagination, securityInfo);
          
          returnList=clearaccountBS.queryOrgHAFAccountFlowDefByPagination(pagination, securityInfo);//默认全部可以扎账的列表

          
        } else if ("1".equals(temp_type)) {//录入条件查询
          clearAccountShowAF = clearaccountBS
              .findOrgHAFAccountFlowTotalByPagination(pagination, securityInfo);
          
          returnList=clearaccountBS.queryOrgHAFAccountFlowTotalByPagination(pagination, securityInfo);//结合条件查询全部可以扎账的列表
          
        } 
        HttpSession session = request.getSession();
        session.setAttribute("clearaccountList", returnList);//点击全部扎账按钮时用到
        
        clearAccountShowAF.setBis_Status(BusiTools
            .listBusiProperty(BusiConst.CLEARACCOUNTSTATUS));
        clearAccountShowAF.setBis_type(BusiTools
            .listBusiProperty(BusiConst.CLEARACCOUNTBUSINESSTYPE_WL));
        
        request.setAttribute("clearAccountShowAF", clearAccountShowAF);
      }
    } catch (BusinessException bex) {
      bex.printStackTrace();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return mapping.findForward("to_clearAccount_list");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "orgHAFAccountFlow.bizStatus",
          "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
