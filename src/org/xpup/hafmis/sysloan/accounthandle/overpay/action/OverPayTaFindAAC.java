package org.xpup.hafmis.sysloan.accounthandle.overpay.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.overpay.bsinterface.IOverPayBS;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTaDTO;
import org.xpup.hafmis.sysloan.accounthandle.overpay.form.OverPayTaAF;


public class OverPayTaFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    OverPayTaAF overPayTaAF=null;
    try {
      String loadkouacc=request.getParameter("loadkouacc");
      IOverPayBS overPayBS = (IOverPayBS) BSUtils
      .getBusinessService("overPayBS", this, mapping.getModuleConfig());
      OverPayTaDTO overPayTaDTO=null;
      overPayTaAF=new OverPayTaAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      overPayTaDTO=overPayBS.findOverPayTaInfo(loadkouacc,securityInfo);
      if(overPayTaDTO!=null){
        overPayTaAF.setOverPayTaDTO(overPayTaDTO);
        String text="displays('"+overPayTaAF.getOverPayTaDTO().getContractId()+"','"+overPayTaAF.getOverPayTaDTO().getBorrowerName()+"'" +
            ",'"+overPayTaAF.getOverPayTaDTO().getTemp_cardKind()+"','"+overPayTaAF.getOverPayTaDTO().getCardNum()+"'" +
                ",'"+overPayTaAF.getOverPayTaDTO().getTemp_loanMode()+"'" +
                    ",'"+overPayTaAF.getOverPayTaDTO().getOvaerLoanRepay()+"'" +
                        ",'"+overPayTaAF.getOverPayTaDTO().getLoanBankId()+"'" +
                            ",'"+overPayTaAF.getOverPayTaDTO().getOfficecode()+"')";
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
