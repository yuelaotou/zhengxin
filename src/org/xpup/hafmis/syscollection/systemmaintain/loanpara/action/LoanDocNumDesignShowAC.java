package org.xpup.hafmis.syscollection.systemmaintain.loanpara.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.form.LoanDocNumDesignAF;



/**
 * 
 * @author ั๎นโ
 *
 */
public class LoanDocNumDesignShowAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    LoanDocNumDesignAF loanDocNumDesignAF=(LoanDocNumDesignAF)form;

    ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
    .getBusinessService("loanDocNumDesignBS", this, mapping.getModuleConfig());
    
    String loanDocNumDocument=loanDocNumDesignBS.getLoanDocNumDesignPara();
    String name=loanDocNumDesignBS.getNamePara();
    loanDocNumDesignAF.setLoanDocNumDocument(loanDocNumDocument);
    loanDocNumDesignAF.setName(name);
    request.setAttribute("loanDocNumDesignAF",loanDocNumDesignAF);
    request.setAttribute("name",name);
    return mapping.findForward("to_loandocnumdesign");
  }
}
