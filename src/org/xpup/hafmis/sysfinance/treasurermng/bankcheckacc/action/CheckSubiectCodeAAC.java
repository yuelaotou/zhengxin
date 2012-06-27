package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.bsinterface.IBankCheckAccBS;


public class CheckSubiectCodeAAC extends Action {
  /**
   * 判断该科目是否存在并且是否为末级科目
   * 与现金日记账和银行存款日记账模块公用
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String subjectCode=request.getParameter("subjectCode");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      String credenceType="1";//默认为1，主要是为了在银行对账单中使用
      //用于现金日记账和银行存款日记账模块，‘0’为现金日记账、‘1’为银行存款日记账
      if(request.getSession().getAttribute("credenceType_gjp")!=null){
        credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      }
      
      IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
      .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
      String subjectId=bankCheckAccBS.checkSubjectCode(subjectCode, credenceType, securityInfo);
      String text="show("+subjectId+");";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
}
