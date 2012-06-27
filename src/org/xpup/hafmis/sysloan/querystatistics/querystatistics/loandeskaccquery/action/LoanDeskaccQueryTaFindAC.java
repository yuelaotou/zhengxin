package org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.form.LoanDeskaccQueryTaAF;

/** 
 * MyEclipse Struts
 * Creation date: 10-19-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoanDeskaccQueryTaFindAC extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    LoanDeskaccQueryTaAF loanDeskaccQueryTaAF=(LoanDeskaccQueryTaAF)form;
    HashMap criterions = makeCriterionsMap(loanDeskaccQueryTaAF);
    Pagination pagination = new Pagination(0, 10, 1, " p11.contract_id ", "DESC",criterions);
    String paginationKey = LoanDeskaccQueryTaShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    return mapping.findForward("loandeskaccquerytashowAC");
  }

  protected HashMap makeCriterionsMap(LoanDeskaccQueryTaAF loanDeskaccQueryTaAF) {
    HashMap map = new HashMap();
    String offic = loanDeskaccQueryTaAF.getOffic().trim();
    if (!(offic == null || "".equals(offic.trim()))) {
      map.put("offic", offic);
    }
    String payBank = loanDeskaccQueryTaAF.getPayBank().trim();
    if (!(payBank == null || "".equals(payBank.trim()))) {
      map.put("payBank", payBank);
    }
    String contactid = loanDeskaccQueryTaAF.getContactid().trim();
    if (!(contactid == null || "".equals(contactid.trim()))) {
      map.put("contactid", contactid);
    }
    String loankouacc = loanDeskaccQueryTaAF.getLoankouacc().trim();
    if (!(loankouacc == null || "".equals(loankouacc.trim()))) {
      map.put("loankouacc", loankouacc);
    }
    String assistantorg = loanDeskaccQueryTaAF.getAssistantorg().trim();
    if (!(assistantorg == null || "".equals(assistantorg.trim()))) {
      map.put("assistantorg", assistantorg);
    }
    String contact_st = loanDeskaccQueryTaAF.getContact_st().trim();
    if (!(contact_st == null || "".equals(contact_st.trim()))) {
      map.put("contact_st", contact_st);
    }
    String borrowerName = loanDeskaccQueryTaAF.getBorrowerName().trim();
    if (!(borrowerName == null || "".equals(borrowerName.trim()))) {
      map.put("borrowerName", borrowerName);
    }
    String cardNum = loanDeskaccQueryTaAF.getCardNum().trim();
    if (!(cardNum == null || "".equals(cardNum.trim()))) {
      map.put("cardNum", cardNum);
    }
    String loanMoneyB = loanDeskaccQueryTaAF.getLoanMoneyB().trim();
    if (!(loanMoneyB == null || "".equals(loanMoneyB.trim()))) {
      map.put("loanMoneyB", loanMoneyB);
    }
    String loanMoneyE = loanDeskaccQueryTaAF.getLoanMoneyE().trim();
    if (!(loanMoneyE == null || "".equals(loanMoneyE.trim()))) {
      map.put("loanMoneyE", loanMoneyE);
    }
    String loanLimitB = loanDeskaccQueryTaAF.getLoanLimitB().trim();
    if (!(loanLimitB == null || "".equals(loanLimitB.trim()))) {
      map.put("loanLimitB", loanLimitB);
    }
    String loanLimitE = loanDeskaccQueryTaAF.getLoanLimitE().trim();
    if (!(loanLimitE == null || "".equals(loanLimitE.trim()))) {
      map.put("loanLimitE", loanLimitE);
    }
    String loanStartDateB = loanDeskaccQueryTaAF.getLoanStartDateB().trim();
    if (!(loanStartDateB == null || "".equals(loanStartDateB.trim()))) {
      map.put("loanStartDateB", loanStartDateB);
    }
    String loanStartDateE = loanDeskaccQueryTaAF.getLoanStartDateE().trim();
    if (!(loanStartDateE == null || "".equals(loanStartDateE.trim()))) {
      map.put("loanStartDateE", loanStartDateE);
    }
    String ageB = loanDeskaccQueryTaAF.getAgeB().trim();
    if (!(ageB == null || "".equals(ageB.trim()))) {
      map.put("ageB", ageB);
    }
    String ageE = loanDeskaccQueryTaAF.getAgeE().trim();
    if (!(ageE == null || "".equals(ageE.trim()))) {
      map.put("ageE", ageE);
    }
  
  // 单位编号
    String orgId = loanDeskaccQueryTaAF.getOrgId().trim();
    if (!(orgId == null || "".equals(orgId.trim()))) {
      try {
        int orgIdd = Integer.parseInt(orgId);
        map.put("orgId", orgIdd + "");
      } catch (Exception e) {
        int orgIdd = -1000;
        map.put("orgId", orgIdd + "");
      }
    }
    // 房屋面积
    String area = loanDeskaccQueryTaAF.getArea().trim();
    if (!(area == null || "".equals(area.trim()))) {
      map.put("area", area);
    }
    // ------------------
    String developer = loanDeskaccQueryTaAF.getBuyhouseorgid().trim();// 开发商的编号
    if (!(developer == null || "".equals(developer.trim()))) {
      map.put("developer", developer);
    }
    String areaB = loanDeskaccQueryTaAF.getAreaB();// 面积起
    if (areaB != null && !"".equals(areaB)) {
      map.put("areaB", areaB.trim());
    }
    String areaE = loanDeskaccQueryTaAF.getAreaE();// 面积止
    if (areaE != null && !"".equals(areaE)) {
      map.put("areaE", areaE.trim());
    }
    String houseType = loanDeskaccQueryTaAF.getHouseType();// 房屋类型
    if (houseType != null && !"".equals(houseType)) {
      map.put("houseType", houseType.trim());
    }
    String loanType = loanDeskaccQueryTaAF.getLoanType();// 房屋类型
    if (loanType != null && !"".equals(loanType)) {
      map.put("loanType", loanType.trim());
    }
    map.put("temp_check", "1");

    return map;
  }
}