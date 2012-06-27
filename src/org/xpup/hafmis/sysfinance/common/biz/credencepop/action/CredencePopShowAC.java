package org.xpup.hafmis.sysfinance.common.biz.credencepop.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.bsinterface.ICredencePopBS;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopInfoDTO;
import org.xpup.hafmis.sysfinance.common.biz.credencepop.form.CredencePopShowAF;

/**
 * Copy Right Information : 显示凭证弹出框Action Goldsoft Project : CredencePopShowAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2007.11.3
 */
public class CredencePopShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    CredencePopShowAF credencePopShowAF = new CredencePopShowAF();
    try {
      String credenceNum = request.getParameter("docNum");
      String check = request.getParameter("check");
      String credenceId = request.getParameter("credenceId");
      // 得到凭证日期
      String credenceDate = request.getParameter("credenceDate");
      // 得到办事处
      String office = request.getParameter("office");

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ICredencePopBS credencePopBS = (ICredencePopBS) BSUtils
          .getBusinessService("credencePopBS", this, mapping.getModuleConfig());
      // 得到凭证字说明
      String paramExplain = "";
      Object[] obj = credencePopBS.findAccountantCredence(credenceNum,
          securityInfo, credenceDate, office);
      credencePopShowAF.setList((List) obj[0]);
      credencePopShowAF.setCredencePopInfoDTO((CredencePopInfoDTO) obj[1]);
      credencePopShowAF.setSumDebit(new BigDecimal(obj[2].toString()));
      credencePopShowAF.setSumCredit(new BigDecimal(obj[3].toString()));
      credencePopShowAF.getCredencePopInfoDTO().setParamExplain(paramExplain);
      String bookid = securityInfo.getBookId();
      request.setAttribute("Bookid", bookid);
      if (check != null && !check.equals("")) {
        credencePopShowAF.setCheck(check);
      }
      credencePopShowAF.setCredenceId(credenceId);
      request.setAttribute("credencePopShowAF", credencePopShowAF);
      request.getSession().setAttribute("credencePopShowAF_print",
          credencePopShowAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_credencepop_show");
  }

}
