/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.loanapply.loanapply.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;

/**
 * @author 韩亮 跳转到借款人信息界面 9月21日
 */
public class LoanapplyTaShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    LoanapplyNewAF loanapplynewAF = new LoanapplyNewAF();
    request.getSession().setAttribute(LoanapplyTeShowAC.PAGINATION_KEY, null);
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
    String setIsNeedDel = (String) request.getSession().getAttribute(
        "setIsNeedDel");
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");

    // 求办事处
    List temp_officeList = securityInfo.getOfficeList();
    List li = new ArrayList();

    for (int i = 0; i < temp_officeList.size(); i++) {
      OfficeDto officedto = null;
      officedto = (OfficeDto) temp_officeList.get(i);
      li.add(new org.apache.struts.util.LabelValueBean(officedto
          .getOfficeName(), officedto.getOfficeCode()));
    }
    request.setAttribute("officeList", li);

    try {
      String contractIdHL = (String) request.getParameter("contractIdHL");
      request.getSession().setAttribute("contractIdHL", contractIdHL);
      String contractid = (String) request.getSession().getAttribute(
          "contractid");
      if (contractIdHL != null) {
        contractid = contractIdHL;
      }
      if (contractid == null || contractid.equals("")) {
        Borrower borrower = new Borrower();
        loanapplynewAF.setBorrower(borrower);
        loanapplynewAF.setType("1");
      } else {
        loanapplynewAF = loanapplyBS.showLoanapplyInfoBycontractid(contractid,
            securityInfo);
        String remend = (String) request.getSession().getAttribute("remend");// 判断是否是维护过来的
        if (remend != null) {
          loanapplynewAF.setRemaind("a");// 从维护过来的
        }
        loanapplynewAF.setType("0");// 在jsp上判断，如果type=1，就让职工弹出框禁用。
        if (contractIdHL != null) {
          loanapplynewAF.setType("15");
        }
      }
      loanapplynewAF.setSexMap(BusiTools.listBusiProperty(BusiConst.SEX));
      loanapplynewAF.setCardkingMap(BusiTools
          .listBusiProperty(BusiConst.DOCUMENTSSTATE));
      // 添加房屋类型
      loanapplynewAF.setHouseTypeMap(BusiTools
          .listBusiProperty(BusiConst.PLHOUSETYPE));
      loanapplynewAF.setLoanWhereTypeMap(BusiTools
          .listBusiProperty(BusiConst.PL_LOANTYPE));
      loanapplynewAF.setIsNeedDel(setIsNeedDel);
      request.getSession().setAttribute("setIsNeedDel", null);
      request.setAttribute("loanapplynewAF", loanapplynewAF);

    } catch (Exception e) {

      e.printStackTrace();
    }
    return mapping.findForward("to_loanapply_new");
  }
}