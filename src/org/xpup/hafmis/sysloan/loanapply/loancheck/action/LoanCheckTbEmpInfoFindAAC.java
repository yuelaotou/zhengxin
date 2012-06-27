package org.xpup.hafmis.sysloan.loanapply.loancheck.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;

public class LoanCheckTbEmpInfoFindAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String assitId = (String) request.getParameter("assitId");
    String contractid = (String) request.getSession().getAttribute(
        "contractIdWY");
    ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
        "loanapplyBS", this, mapping.getModuleConfig());
    try {
      LoanapplyTbNewAF loanapplytbnewAF = loanapplyBS.findAssistanBorrowerInfo(
          contractid, securityInfo, assitId);
      // 枚举转换性别
      String sex = BusiTools.getBusiValue(Integer.parseInt(loanapplytbnewAF
          .getSex()), BusiConst.SEX);
      loanapplytbnewAF.setSex(sex);
      // 枚举转换证件类型
      String cardKind = BusiTools.getBusiValue(new Integer(loanapplytbnewAF
          .getCardKind()).intValue(), BusiConst.DOCUMENTSSTATE);
      loanapplytbnewAF.setCardKind(cardKind);

      String relation = "";
      if (loanapplytbnewAF.getRelation() != null) {
        relation = loanapplytbnewAF.getRelation();
      }
      String nation = "";
      if (loanapplytbnewAF.getNation() != null) {
        nation = loanapplytbnewAF.getNation();
      }
      loanapplytbnewAF.setNation(nation);
      String nativePlace = "";
      if (loanapplytbnewAF.getNativePlace() != null) {
        nativePlace = loanapplytbnewAF.getNativePlace();
      }
      loanapplytbnewAF.setNativePlace(nativePlace);
      String business = "";
      if (loanapplytbnewAF.getBusiness() != null) {
        business = loanapplytbnewAF.getBusiness();
      }
      loanapplytbnewAF.setBusiness(business);
      String title = "";
      if (loanapplytbnewAF.getTitle() != null) {
        title = loanapplytbnewAF.getTitle();
      }
      loanapplytbnewAF.setTitle(title);
      String marriageSt = "";
      if (loanapplytbnewAF.getMarriageSt() != null) {
        marriageSt = loanapplytbnewAF.getMarriageSt();
      }
      loanapplytbnewAF.setMarriageSt(marriageSt);
      String degree = "";
      if (loanapplytbnewAF.getDegree() != null) {
        degree = loanapplytbnewAF.getDegree();
      }
      loanapplytbnewAF.setDegree(degree);
      String homeAddr = "";
      if (loanapplytbnewAF.getHomeAddr() != null) {
        homeAddr = loanapplytbnewAF.getHomeAddr();
      }
      loanapplytbnewAF.setHomeAddr(homeAddr);
      String homeMail = "";
      if (loanapplytbnewAF.getHomeMail() != null) {
        homeMail = loanapplytbnewAF.getHomeMail();
      }
      loanapplytbnewAF.setHomeMail(homeMail);
      String homeMobile = "";
      if (loanapplytbnewAF.getHomeMobile() != null) {
        homeMobile = loanapplytbnewAF.getHomeMobile();
      }
      loanapplytbnewAF.setHomeMobile(homeMobile);
      String houseTel = "";
      if (loanapplytbnewAF.getHouseTel() != null) {
        houseTel = loanapplytbnewAF.getHouseTel();
      }
      loanapplytbnewAF.setHouseTel(houseTel);
      String orgId = "";
      if (loanapplytbnewAF.getOrgId() != null) {
        orgId = loanapplytbnewAF.getOrgId();
      }
      loanapplytbnewAF.setOrgId(orgId);
      String orgName = "";
      if (loanapplytbnewAF.getOrgName() != null) {
        orgName = loanapplytbnewAF.getOrgName();
      }
      loanapplytbnewAF.setOrgName(orgName);
      String orgTel = "";
      if (loanapplytbnewAF.getOrgTel() != null) {
        orgTel = loanapplytbnewAF.getOrgTel();
      }
      loanapplytbnewAF.setOrgTel(orgTel);
      String orgAddr = "";
      if (loanapplytbnewAF.getOrgAddr() != null) {
        orgAddr = loanapplytbnewAF.getOrgAddr();
      }
      loanapplytbnewAF.setOrgAddr(orgAddr);
      String orgMail = "";

      if (loanapplytbnewAF.getOrgMail() != null) {
        orgMail = loanapplytbnewAF.getOrgMail();
      }
      loanapplytbnewAF.setOrgMail(orgMail);
      String accBlnce = "";
      if (loanapplytbnewAF.getAccBlnce() != null) {
        accBlnce = loanapplytbnewAF.getAccBlnce();
      }
      loanapplytbnewAF.setAccBlnce(accBlnce);
      String monthSalary = "";
      if (loanapplytbnewAF.getMonthSalary() != null) {
        monthSalary = loanapplytbnewAF.getMonthSalary();
      }
      loanapplytbnewAF.setMonthSalary(monthSalary);
      String monthPay = "";
      if (loanapplytbnewAF.getMonthPay() != null) {
        monthPay = loanapplytbnewAF.getMonthPay();
      }
      loanapplytbnewAF.setMonthPay(monthPay);
      String empSt = "";
      if (loanapplytbnewAF.getEmpSt() != null) {
        empSt = loanapplytbnewAF.getEmpSt();
      }
      loanapplytbnewAF.setEmpSt(empSt);
      String bgnDate = "";

      if (loanapplytbnewAF.getBgnDate() != null) {
        bgnDate = loanapplytbnewAF.getBgnDate();
      }
      loanapplytbnewAF.setBgnDate(bgnDate);
      String endDate = "";
      if (loanapplytbnewAF.getEndDate() != null) {
        endDate = loanapplytbnewAF.getEndDate();
      }
      loanapplytbnewAF.setEndDate(endDate);

      String text = "";
      text = "displayIn('" + loanapplytbnewAF.getEmpId() + "','"
          + loanapplytbnewAF.getName() + "','" + relation + "','"
          + loanapplytbnewAF.getSex() + "','" + loanapplytbnewAF.getCardKind()
          + "','" + loanapplytbnewAF.getCardNum() + "','"
          + loanapplytbnewAF.getBirthday() + "','" + loanapplytbnewAF.getAge()
          + "','" + nation + "','" + nativePlace + "','" + business + "','"
          + title + "','" + marriageSt + "','" + degree + "','" + homeAddr
          + "','" + homeMail + "','" + homeMobile + "','" + houseTel + "','"
          + orgId + "','" + orgName + "','" + orgTel + "','" + orgAddr + "','"
          + orgMail + "','" + accBlnce + "','" + monthSalary + "','" + monthPay
          + "','" + empSt + "','" + bgnDate + "','" + endDate + "')";
      response.getWriter().write(text);
      response.getWriter().close();

    } catch (Exception e) {
      String text = "";
      text = "displayInerror('" + e.getLocalizedMessage().toString() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    }
    return null;
  }
}