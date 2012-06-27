package org.xpup.hafmis.sysloan.loancallback.relievecontract.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.bsinterface.IRelieveContractBS;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.dto.RelieveContractTaDTO;
import org.xpup.hafmis.sysloan.loancallback.relievecontract.form.RelieveContractTaAF;

public class RelieveContractTaFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    RelieveContractTaAF relieveContractTaAF=null;
    try {
      String loadKouAcc=request.getParameter("loadKouAcc");
      IRelieveContractBS relieveContractBS = (IRelieveContractBS) BSUtils
      .getBusinessService("relieveContractBS", this, mapping.getModuleConfig());
      RelieveContractTaDTO relieveContractTaDTO=null;
      relieveContractTaAF=new RelieveContractTaAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      relieveContractTaDTO=relieveContractBS.findRelieveContractTaInfo(loadKouAcc,securityInfo);
      if(relieveContractTaDTO!=null){
        relieveContractTaAF.setRelieveContractTaDTO(relieveContractTaDTO);
        String text="displays('"+relieveContractTaAF.getRelieveContractTaDTO().getContractId()+"','"+relieveContractTaAF.getRelieveContractTaDTO().getBorrowerName()+"'" +
            ",'"+relieveContractTaAF.getRelieveContractTaDTO().getTemp_cardKind()+"','"+relieveContractTaAF.getRelieveContractTaDTO().getCardNum()+"'" +
                ",'"+relieveContractTaAF.getRelieveContractTaDTO().getOverplusLoanMoney()+"'" +
                    ",'"+relieveContractTaAF.getRelieveContractTaDTO().getNoBackMoney()+"'" +
                        ",'"+relieveContractTaAF.getRelieveContractTaDTO().getOvaerLoanRepay()+"'" +
                            ",'"+relieveContractTaAF.getRelieveContractTaDTO().getBailBalance()+"'" +
                                ",'"+relieveContractTaAF.getRelieveContractTaDTO().getTemp_loanMode()+"')";
        response.getWriter().write(text);
        response.getWriter().close();
      }
    } catch (BusinessException bex) {
      System.err.println(bex.getLocalizedMessage().toString());
      String text="reportErrors('"+bex.getLocalizedMessage()+"')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch(Exception e){
      e.printStackTrace();
    }
    return null; 
  }
}
