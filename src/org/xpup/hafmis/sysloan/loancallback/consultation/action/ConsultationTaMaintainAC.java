package org.xpup.hafmis.sysloan.loancallback.consultation.action; 


import java.util.HashMap;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.loanconditionsset.ILoanConditionsParamSetBS;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class ConsultationTaMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print", "print");
    return map;
  }

  public ActionForward print(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    LoancallbackTaAF af = (LoancallbackTaAF)form;
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");//进入页面是要带权限的，从权限中取得是以中心为主还是以银行为主。以此来控制按钮灰显，要在action中设置状态。
    try{
      Pagination pagination=(Pagination)request.getSession().getAttribute(ConsultationTaShowAC.PAGINATION_KEY);
      List list = (List)pagination.getQueryCriterions().get("shouldBackList");
      List yearMonthList = (List)pagination.getQueryCriterions().get("YearList");
      String loanKouAcc = (String)pagination.getQueryCriterions().get("loanKouAcc");
      af.setShouldBackList(list);
      af.setMonthYearList(yearMonthList);
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
      .getBusinessService("sysloanloanDocNumDesignBS", this, mapping.getModuleConfig());
      String userName="";
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
      request.setAttribute("loanKouAcc", loanKouAcc);
      request.setAttribute("name", af.getBorrowerInfoDTO().getBorrowerName());
      request.setAttribute("makePerson",userName);
      request.setAttribute("bizDate",securityInfo.getUserInfo().getPlbizDate());
      request.setAttribute("ConsultationList", list);
    }catch(Exception b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    af.reset(mapping,request);
    return mapping.findForward("consultation_cell");
  }
}