package org.xpup.hafmis.sysloan.dataready.loanpara.action;

import org.apache.struts.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.dataready.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.sysloan.dataready.loanpara.form.LoanDocNumDesignAF;

/**
 * 
 * @author ั๎นโ
 *
 */
public class LoanDocNumDesignSaveAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    LoanDocNumDesignAF loanDocNumDesignAF=(LoanDocNumDesignAF)form;
    
    ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
    .getBusinessService("sysloanloanDocNumDesignBS", this, mapping.getModuleConfig());
    
    String loanDocNumDocument=loanDocNumDesignAF.getLoanDocNumDocument();
    String name=loanDocNumDesignAF.getName();
    String oldLoanDocNumDocument=loanDocNumDesignBS.getLoanDocNumDesignPara();
    String oldName=loanDocNumDesignBS.getNamePara();
    if(!oldLoanDocNumDocument.equals(loanDocNumDocument))
    loanDocNumDesignBS.updateLoanDocNumDesignPara(loanDocNumDocument);
    if(!oldName.equals(name))
    loanDocNumDesignBS.updateNamePara(name);  
    return mapping.findForward("to_loanDocNumDesignShowAC");
  }
}
