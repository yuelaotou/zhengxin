package org.xpup.hafmis.sysloan.credit.query.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.credit.report.form.CreditAF;

public class CreditQueryFindAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      CreditAF creditAF = (CreditAF) form;
      HashMap criterions = makeCriterionsMap(creditAF);
      Pagination pagination = new Pagination(0, 1000, 1, "credit.id", "ASC",
          criterions);
      request.getSession()
          .setAttribute(CreditQueryShowAC.PAGINATION_KEY, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("creditQueryShowAC");
  }

  protected HashMap makeCriterionsMap(CreditAF form) {
    HashMap m = new HashMap();

    /** 数据提取月份 */
    String shujutiquriqi = form.getShujutiquriqi();
    if (shujutiquriqi != null && !"".equals(shujutiquriqi.trim())) {
      m.put("shujutiquriqi", shujutiquriqi.trim());
    }

    /** 报文生成日期 */
    String baowenshengchengriqi = form.getBaowenshengchengriqi();
    if (baowenshengchengriqi != null && !"".equals(baowenshengchengriqi.trim())) {
      m.put("baowenshengchengriqi", baowenshengchengriqi.trim());
    }

    /** 办事处 */
    String officeCode = form.getOfficeCode();
    if (officeCode != null && !"".equals(officeCode.trim())) {
      m.put("officeCode", officeCode.trim());
    }

    /** 放款银行 */
    String loanBankName = form.getLoanBankName();
    if (loanBankName != null && !"".equals(loanBankName.trim())) {
      m.put("loanBankName", loanBankName.trim());
    }

    /** 合同账号 */
    String yewuhao = form.getYewuhao();
    if (yewuhao != null && !"".equals(yewuhao.trim())) {
      m.put("yewuhao", yewuhao.trim());
    }

    /** 数据状态 */
    String jiluzhuangtai = form.getJiluzhuangtai();
    if (jiluzhuangtai != null && !"".equals(jiluzhuangtai.trim())) {
      m.put("jiluzhuangtai", jiluzhuangtai.trim());
    }
    return m;
  }

}