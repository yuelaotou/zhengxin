package org.xpup.hafmis.sysloan.accounthandle.carryforward.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.carryforward.bsinterface.ICarryforwardBS;
/**
 * 程序修改后变成实现AAC查询验证功能,原程序是年结维护没有必要存在删除，现为以银行年结为主验证页
 * 2007.11.14
 * shiy
 * 
 */
public class CarryforwardTbForwardUrlAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text = null;
    String loanBankId=request.getParameter("loanBankId");
    String massageinfo="";//返回是否准许年结的信息
     try{
      
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      ICarryforwardBS carryforwardBS = (ICarryforwardBS) BSUtils.getBusinessService("carryforwardBS", this,
          mapping.getModuleConfig());
      massageinfo=carryforwardBS.queryCarrayforwardInfo(loanBankId, securityInfo);
    } catch(BusinessException bex){
      massageinfo=bex.getMessage();
     } 
     catch (Exception e) {
     e.printStackTrace();
    }
     text = "displays('" + massageinfo  + "')";
     response.getWriter().write(text);
     response.getWriter().close();
    return null;
  }
}
