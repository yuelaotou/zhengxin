package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;


public class CashDayClearTaFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String office=request.getParameter("office");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      String credenceDate="";
      //用credenceType来判断是现金日记账还是银行存款日记账
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      credenceDate=cashDayClearBS.findCredenceDateByOffice(office,credenceType,securityInfo);
      if(credenceDate==null){
        credenceDate="";
      }
      String text="display('"+credenceDate+"');";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
}
