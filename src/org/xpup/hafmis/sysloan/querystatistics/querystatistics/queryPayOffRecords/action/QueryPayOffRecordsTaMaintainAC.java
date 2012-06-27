package org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;

public class QueryPayOffRecordsTaMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonDoListAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
 
    map.put("button.print", "print");
    
    return map;
  }
 
  /**
   * 打印的方法
   * 
   *5、列表增加打印功能
表头：住房公积金人员变更清册
单位名称，单位账号，变更年月
内容
与查询列表内容相同

列表信息下面显示：归集银行，制表人，操作日期（业务日期）

   */
  public ActionForward print(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    List explist =new ArrayList();
    try {
      HttpSession session=request.getSession(false);
      Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");    
      String bizDate = securityInfo.getUserInfo().getBizDate();
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
      request.setAttribute("userName", userName);
      request.setAttribute("celllist", explist);
      request.setAttribute("bizDate", bizDate);
 
    } catch (Exception bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_queryPayOffRecords_tacell");
  }
}