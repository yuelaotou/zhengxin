package org.xpup.hafmis.sysloan.dataready.develop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 开发商维护的节点Action，用来进入添加开发商页
 * 
 * @author 付云峰
 */
public class DevelopTaForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    return mapping.findForward("developTaShowAC");
  }

}
