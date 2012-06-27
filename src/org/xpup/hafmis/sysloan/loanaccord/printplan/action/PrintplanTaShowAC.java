package org.xpup.hafmis.sysloan.loanaccord.printplan.action;
import java.util.HashMap;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loanaccord.printplan.bsinterface.IPrintplanBS;
import org.xpup.hafmis.sysloan.loanaccord.printplan.form.PrintplanShowAF;
public class PrintplanTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanaccord.printplan.action.PrintplanTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      {
    ActionMessages messages = null;
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      PrintplanShowAF printplanShowAF = new PrintplanShowAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IPrintplanBS printplanBS = (IPrintplanBS) BSUtils.getBusinessService(
          "printplanBS", this, mapping.getModuleConfig());
      if(!pagination.getQueryCriterions().isEmpty()){
      printplanShowAF=printplanBS.findPrintplanList(pagination, securityInfo);
      }
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
      .getBusinessService("sysloanloanDocNumDesignBS", this, mapping.getModuleConfig());
      String userName="";
      try {
        String name = loanDocNumDesignBS.getNamePara();
        if (name.equals("1")) {
          printplanShowAF.setOperson(securityInfo.getUserName());
         
        } else {
          printplanShowAF.setOperson(securityInfo.getRealName());
        }
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      if(printplanShowAF.getList().isEmpty()){
        request.setAttribute("buttonInfo", "disabled");
      }
      request.getSession().setAttribute("printplanShowAF", printplanShowAF);
      request.setAttribute("printplanShowAF", printplanShowAF);
      printplanShowAF.reset(mapping, request);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
      false));
      saveErrors(request, messages);
      PrintplanShowAF clearprintplanShowAF = new PrintplanShowAF();
      request.setAttribute("printplanShowAF", clearprintplanShowAF);
   }
    catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_printplan_ta_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "restoreLoan.loanKouYearmonth", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
