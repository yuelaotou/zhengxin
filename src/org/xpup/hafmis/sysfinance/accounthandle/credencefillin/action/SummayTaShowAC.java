package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.form.CredenceFillinTaAF;

/**
 * 显示摘要弹出列表的Action
 * 
 * @author 刘冰
 */
public class SummayTaShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    CredenceFillinTaAF  credenceFillinTaAF=new CredenceFillinTaAF();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
        .getBusinessService("credenceFillinBS", this, mapping.getModuleConfig());
    String summayi=request.getParameter("summayi");
    request.getSession().setAttribute("summayi", summayi);
    List summaylist = credenceFillinBS.getSummayList(securityInfo);
    credenceFillinTaAF.setSummaylist(summaylist);
    request.setAttribute("summaylist", summaylist);
    request.setAttribute("credenceFillinTaAF", credenceFillinTaAF);
    return mapping.findForward("to_summay_show");

  }
}
