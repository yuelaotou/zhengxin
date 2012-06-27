package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;

/**
 * 得到单笔调整时本金与利息的方法
 * 
 * @author 付云峰
 */
public class AdjustAccountYearMonthAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String text = "";
    /** 错误信息 */
    String error = "";
    String corpus = "";
    String interest = "";
    String punishInterest = "";

    try {

      String yearMonth = request.getParameter("yearMonth");
      String loanKouAcc = request.getParameter("loanKouAcc");
      
      IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
      .getBusinessService("adjustAccountBS", this, mapping
          .getModuleConfig());
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      int plLoanReturnType = 0;
      // 贷款还款类型1.中心为主2.银行为主
      int temp_plLoanReturnType = securityInfo.getPlLoanReturnType();
      // 根据权限中的还款类型判断以哪为主
      if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_CENTER) {
        plLoanReturnType = 1;// 中心为主
      } else if (temp_plLoanReturnType == BusiConst.PLLOANRETURNTYPE_BANK) {
        plLoanReturnType = 2;// 银行为主
      }
      
      Object[] obj = adjustAccountBS.findCorpusAndInterest(yearMonth, loanKouAcc, plLoanReturnType);
      if (obj[0]!=null) {
        corpus=obj[0].toString();
      }
      if(obj[1]!=null){
        interest=obj[1].toString();
      }
      if(obj[2]!=null){
        punishInterest=obj[2].toString();
      }
    } catch (BusinessException bex) {
      error = bex.getLocalizedMessage();
    } catch (Exception e) {
      e.printStackTrace();
    }
    text = "displaysYear('" + error + "','" + corpus + "','" + interest + "','" + punishInterest + "')";
    response.getWriter().write(text);
    response.getWriter().close();
    return null;
  }

}
