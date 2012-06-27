package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * @author yuqf
 */
public class EndorsecontractTaFindAAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    response.setHeader("Cache-Control", "no-cache");
    response.setContentType("text/html;charset=UTF-8");
   
    ActionMessages messages = null;
    List loanBankNameList = new ArrayList();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    EndorsecontractTaAF endorsecontractTaAF = new EndorsecontractTaAF();
    String text = null;
    String message = "";
    String contractId = "";
    String debitter = "";
    String certificateType = "";
    String certificateNum = "";
    String beentruster = "";
    String debitMoney = "";
    String term = "";
    String entruster = "";
    String monthInterest = "";
    String realMonthInt = "";
    String creditType = "";
    String contractSureDate = "";
    String debitMoneyStaDate = "";
    String debitMoneyEndDate = "";
    String assurer = "";
    String writeType = "";
    String corpusInterest = "";//月还本息
    String hiddenloanMode = "";
    Userslogincollbank userslogincollbank = null;
    String iscontactid="";
    PrintWriter out = null;
    try {
      String id = (String) request.getParameter("contractId");
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
          .getBusinessService("endorsecontractBS", this, mapping
              .getModuleConfig());
      if (id != null) {
//      List sloanBankNameList = endorsecontractBS.queryBankList(id);
//      out = response.getWriter();
//      out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//      out.println("<options>");
     
     
       //for(int i=0;i<sloanBankNameList.size();i++){
         //LabelValueBean labelValueBean=(LabelValueBean)sloanBankNameList.get(i);
        // out.println("<value>"+labelValueBean.getValue()+"</value>");
        // out.println("<text>"+labelValueBean.getLabel()+"</text>");
//         out.println("<value>"+11111+"</value>");
//         out.println("<text>"+222+"</text>");
      // }
//      out.println("</options>");
      
      
      }
      //____________________________________________________________
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      endorsecontractTaAF = endorsecontractBS.queryContractInfo(id, pagination,
          securityInfo, request, null);
      contractId = endorsecontractTaAF.getContractId();
      debitter = endorsecontractTaAF.getDebitter();
      certificateType = endorsecontractTaAF.getCertificateType();
      certificateNum = endorsecontractTaAF.getCertificateNum();
      beentruster = endorsecontractTaAF.getBeentruster();// 受托方
      debitMoney = endorsecontractTaAF.getDebitMoney();// 借款金额
      term = endorsecontractTaAF.getTerm();// 借款期限
      entruster = endorsecontractTaAF.getEntruster(); // 委托方 (甲方 ××中心)
      monthInterest = endorsecontractTaAF.getMonthInterest();// 每月利率
      realMonthInt = endorsecontractTaAF.getRealMonthInt();
      creditType = endorsecontractTaAF.getCreditType();// 还款方式
      contractSureDate = endorsecontractTaAF.getContractSureDate();// 合同签订日期
      debitMoneyStaDate = endorsecontractTaAF.getDebitMoneyStaDate();// 借款起始日期
      debitMoneyEndDate = endorsecontractTaAF.getDebitMoneyEndDate();// 还款终止日期
      assurer = endorsecontractTaAF.getAssurer();// 保证方
      writeType = endorsecontractTaAF.getWriteType();// 是否签订
      iscontactid=endorsecontractTaAF.getIscontactid();
      corpusInterest = endorsecontractTaAF.getCorpusInterest();
      hiddenloanMode = endorsecontractTaAF.getHiddenloanMode();
      request.getSession().setAttribute("contractId", contractId);

    } catch (BusinessException bex) {
      message = bex.getMessage();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString()));
      saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    text = "display('" + contractId + "','" + debitter + "','"
        + certificateType + "','" + certificateNum + "'," + "'" + beentruster
        + "','" + debitMoney + "','" + term + "','" + entruster + "','"
        + monthInterest + "'," + "'" + creditType + "','" + contractSureDate
        + "','" + debitMoneyStaDate + "','" + debitMoneyEndDate + "','"
        + assurer + "','" + writeType +"','" +realMonthInt+ "','" + corpusInterest + "','" + hiddenloanMode+"'" ;
    text += ",'" + message + "','" + iscontactid + "');";
//    out.flush();
//    out.close();
    response.getWriter().write(text);
    response.getWriter().close();
    
    return null;
  }

  protected String getPaginationKey() {
    return EndorsecontractTaShowAC.PAGINATION_KEY;
  }
}
