package org.xpup.hafmis.syscollection.pickupmng.specialpickup.action;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.bsinterface.ISpePickBS;

public class SpePickFindEmpACC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");

    try {
      String empid = request.getParameter("id");
      String orgid = request.getParameter("orgid");
      String text = "";
      ISpePickBS spePickBS = (ISpePickBS) BSUtils.getBusinessService(
          "spePickBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      Org org=spePickBS.queryOrgById(new Integer(orgid),securityInfo);
      Emp emp = spePickBS.findSpeEmp(orgid, empid);
      SpecialPick specialPick = new SpecialPick();
      specialPick = spePickBS.findSpePickMoney(orgid,empid);
      orgid=BusiTools.convertSixNumber(orgid);
      empid=BusiTools.convertSixNumber(empid);
      String empName = "";
      String cardKind = "";
      String cardNum = "";
      BigDecimal curBalance = new BigDecimal(0.00);
      BigDecimal preBalance = new BigDecimal(0.00);
      BigDecimal balance = new BigDecimal(0.00);
      String isPick = "";
      BigDecimal pickCorpus = new BigDecimal(0.00);
      if (emp != null) {
        if (emp.getOrg().getId().toString().equals(org.getId().toString())) {
          empName = emp.getEmpInfo().getName();
          cardKind = emp.getEmpInfo().getTEMP_cardKind();
          cardNum = emp.getEmpInfo().getCardNum();
          curBalance = emp.getCurBalance().setScale(2);
          preBalance = emp.getPreBalance().setScale(2);
          balance = emp.getBalance().setScale(2);
          if (specialPick != null) {
            isPick = specialPick.getIsPick().toString();

          }
          if (isPick.equals("1")) {
            pickCorpus = specialPick.getPickCorpus();
          }
          text = "displays1('"+orgid+"','" + empid + "','" + empName + "','" + cardKind
              + "','" + cardNum + "','" + curBalance + "','" + preBalance
              + "','" + balance + "','" + pickCorpus + "')";
        } else {
          ActionMessages messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
              "该单位不存在该职工", false));
          saveErrors(request, messages);
        }
      }else{
        
        text="showNull1()";
      }
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
