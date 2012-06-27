package org.xpup.hafmis.sysloan.loancallback.advancepayloan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.bsinterface.IAdvancepayloanBS;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.form.AdvancepayloanTaAF;

public class AdvancepayloanTbForwardURLAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse rsponse) throws Exception {
    // TODO Auto-generated method stub
    request.getSession().setAttribute(AdvancepayloanTbShowAC.PAGINATION_KEY, null);
    return mapping.findForward("to_advancepayloanTbShowAC");
  }
}
