package org.xpup.hafmis.sysloan.loancallback.advancepayloan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.bsinterface.IAdvancepayloanBS;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.dto.AdvancepayloanTaDTO;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.form.RelieveContractTaAF;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.BusiConst;

public class AdvancepayloanTaFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    AdvancepayloanTaDTO advancepayloanTaDTO = null;
    String loanKouAcc = "";//贷款帐号
    String contractId = "";//合同编号
    String borrowerName = "";//借款人姓名
    String cardKind = "";//证件类型
    String cardNum = "";//证件号码
    String corpusInterest = "";//月还本息
    String overplusLoanMoney = "";//剩余本金 OVERPLUS_LOAN_MONEY
    String creditType = "";//还款方式
    String pre_term = "";//原借款期限
    String text = "";
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try{
      String loadKouAcc=request.getParameter("loadKouAcc");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IAdvancepayloanBS advancepayloanBS = (IAdvancepayloanBS) BSUtils
      .getBusinessService("advancepayloanBS", this, mapping
          .getModuleConfig());
      advancepayloanTaDTO = advancepayloanBS.findAdvancepayloanTaDTO(loadKouAcc,securityInfo);
      if(advancepayloanTaDTO!=null){
         loanKouAcc = advancepayloanTaDTO.getLoanKouAcc();//贷款帐号
         contractId = advancepayloanTaDTO.getContractId();//合同编号
         borrowerName = advancepayloanTaDTO.getBorrowerName();//借款人姓名
         cardKind = BusiTools.getBusiValue(Integer.parseInt(advancepayloanTaDTO.getCardKind()), BusiConst.DOCUMENTSSTATE) ;//证件类型
         cardNum = advancepayloanTaDTO.getCardNum();//证件号码
         corpusInterest = advancepayloanTaDTO.getCorpusInterest();//月还本息
         overplusLoanMoney = advancepayloanTaDTO.getOverplusLoanMoney();//剩余本金
         creditType = BusiTools.getBusiValue(Integer.parseInt(advancepayloanTaDTO.getCreditType()), BusiConst.PLRECOVERTYPE);//还款方式
         pre_term = advancepayloanTaDTO.getPre_term();//原借款期限
         
         text="displays('"+loanKouAcc+"','"+contractId+"'" +
        ",'"+borrowerName+"','"+cardKind+"'" +
            ",'"+cardNum+"'" +
                ",'"+corpusInterest+"'" +
                    ",'"+overplusLoanMoney+"'" +
                        ",'"+creditType+"'" +
                            ",'"+pre_term+"')";
        response.getWriter().write(text);
        response.getWriter().close();
      }
     }catch (BusinessException bex) {
        System.err.println(bex.getLocalizedMessage().toString());
        text="reportErrors('"+bex.getLocalizedMessage()+"')";
        response.getWriter().write(text);
        response.getWriter().close();
      } catch(Exception e){
        e.printStackTrace();
      }
    return null;
  }
}
