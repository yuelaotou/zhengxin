package org.xpup.hafmis.sysloan.contractchg.specialinfochg.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.bsinterface.ISpecialInfoChgBS;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.dto.SpecialInfoChgDTO;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.form.SpecialInfoChgAF;

public class SpecialInfoChgFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    SpecialInfoChgAF specialInfoChgAF=null;
    try {
      String contractId=request.getParameter("contractId");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      ISpecialInfoChgBS specialInfoChgBS = (ISpecialInfoChgBS) BSUtils
      .getBusinessService("specialInfoChgBS", this, mapping.getModuleConfig());
      SpecialInfoChgDTO specialInfoChgDTO=null;
      specialInfoChgAF=new SpecialInfoChgAF();
      specialInfoChgDTO=specialInfoChgBS.findSpecialInfoChgInfo(contractId,securityInfo);
      if(specialInfoChgDTO!=null){
        specialInfoChgAF.setSpecialInfoChgDTO(specialInfoChgDTO);
        String text="displays('"+specialInfoChgAF.getSpecialInfoChgDTO().getBorrowerName()+"'" +
                                ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getLoanMoney()+"'" +
                                  ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getLoanTimeLimit()+"'" +
                                    ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getLoanMonthRate()+"'" +
                                      ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getTemp_loanMode()+"'" +
                                        ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getCorpusInterest()+"'" +
                                          ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getFirstLoanMoney()+"'" +
                                            ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getLoanPoundage()+"'" +
                                              ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getAheadReturnAfter()+"'" +
                                                ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getPartReturnMonthLT()+"'" +
                                                  ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getIsPartAheadReturn()+"'" +
                                                    ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getAllReturnMonthLT()+"'" +
                                                      ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getIsAllReturn()+"'" +
                                                        ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getLeastReturnMoney()+"'" +
                                                          ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getMostAheadReturnYearTimes()+"'" +
                                                            ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getMostAheadReturnTimes()+"'" +
                                                              ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getIsAccept()+"'" +
                                                                ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getChargeMode()+"'" +
                                                                  ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getAheadReturnMoneyPercent()+"'" +
                                                                    ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getMoney()+"'" +
                                                                      ",'"+specialInfoChgAF.getSpecialInfoChgDTO().getLoanBankId()+"')";
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
