package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;

/**
 * 凭证录入页调出日期和凭证号的Action
 * 
 * @author 刘冰
 */
public class CredenceFillinGetNumByDateAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String office = "";
    String credenceDate = request.getParameter("credenceDate");
    String button = request.getParameter("button");
    String text = "";
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String bookId = securityInfo.getBookId();
      int settleType = securityInfo.getFnSettleType();
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      if (settleType == 0) {
        office = null;
      } else if (settleType == 1) {
        office = request.getParameter("office");
      }
      if (button == null || button.equals("")) {
        String error = "";
        String maxCredenceDate = credenceFillinBS.getCredenceDate(office,
            securityInfo);
        if (Integer.parseInt(credenceDate) < Integer.parseInt(maxCredenceDate)) {
          error = "日期不可以向前修改";
        } else if (Integer.parseInt(credenceDate.substring(0, 6)) > Integer
            .parseInt(maxCredenceDate.substring(0, 6))
            && Integer.parseInt(credenceDate.substring(0, 6)) > Integer
                .parseInt(securityInfo.getUserInfo().getFbizDate().substring(0,
                    6))) {
          error = "日期不可以跨月向后修改";
        }
        String credenceNum = credenceFillinBS.getCredenceNum(office,
            credenceDate.substring(0, 6), "0", bookId);
        if (credenceNum != null && !"".equals(credenceNum)) {
          credenceNum = BusiTools.convertFourNumber(credenceNum);
        }
        response.setContentType("text/html;charset=UTF-8");
        text = "displaysNumByDate('" + credenceNum + "','" + error + "')";
      }
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (BusinessException bex) {
      System.err.println(bex.getLocalizedMessage().toString());
      text = "reportErrors('" + bex.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
