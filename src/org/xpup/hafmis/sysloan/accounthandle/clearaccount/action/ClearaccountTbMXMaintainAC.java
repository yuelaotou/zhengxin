package org.xpup.hafmis.sysloan.accounthandle.clearaccount.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.bsinterface.IClearaccountBS;
import org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto.ClearaccountDTO;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;

public class ClearaccountTbMXMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          ClearaccountTbMXShowAC.PAGINATION_KEY);
      Pagination pagination_1 = (Pagination) request.getSession().getAttribute(
          ClearaccountTbShowAC.PAGINATION_KEY);
      String startDate = (String) pagination_1.getQueryCriterions().get(
          "startDate");
      String endDate = (String) pagination_1.getQueryCriterions()
          .get("endDate");
      String loanBankId = (String) pagination_1.getQueryCriterions().get(
          "loanBankId");
      IClearaccountBS clearaccountBS = (IClearaccountBS) BSUtils
          .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      List list = clearaccountBS.findClearaccountMXPrint(pagination,
          securityInfo);
      String loanBankName = clearaccountBS.getLoanBankName(loanBankId);
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
          .getBusinessService("sysloanloanDocNumDesignBS", this, mapping
              .getModuleConfig());
      String userName = "";
      try {
        String name = loanDocNumDesignBS.getNamePara();

        if (name.equals("1")) {
          userName = securityInfo.getUserName();
        } else {
          userName = securityInfo.getRealName();
        }

      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      String makebill = "";
      String checkpsn = "";
      String clearpsn = "";
      for (Iterator iter = list.iterator(); iter.hasNext();) {
        ClearaccountDTO clearaccountDTO = (ClearaccountDTO) iter.next();
        if(makebill.indexOf(clearaccountDTO.getMakeBillPerson())==-1)
          makebill += clearaccountDTO.getMakeBillPerson() + ",";
        if(checkpsn.indexOf(clearaccountDTO.getCheckPerson())==-1)
          checkpsn += clearaccountDTO.getCheckPerson() + ",";
        if(clearpsn.indexOf(clearaccountDTO.getClearAccPerson())==-1)
          clearpsn += clearaccountDTO.getClearAccPerson() + ",";
      }
      if(makebill.length()!=0)
        makebill = makebill.substring(0,makebill.lastIndexOf(","));
      if(checkpsn.length()!=0)
        checkpsn = checkpsn.substring(0,checkpsn.lastIndexOf(","));
      if(clearpsn.length()!=0)
        clearpsn = clearpsn.substring(0,clearpsn.lastIndexOf(","));
      
      request.setAttribute("makebill", makebill);
      request.setAttribute("checkpsn", checkpsn);
      request.setAttribute("clearpsn", clearpsn);
      request
          .setAttribute("bizDate", securityInfo.getUserInfo().getPlbizDate());
      request.setAttribute("printlist", list);
      request.setAttribute("startDate", startDate);
      request.setAttribute("endDate", endDate);
      request.setAttribute("loanBankName", loanBankName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("clearaccountbalanceWindow_cell");
  }

}
