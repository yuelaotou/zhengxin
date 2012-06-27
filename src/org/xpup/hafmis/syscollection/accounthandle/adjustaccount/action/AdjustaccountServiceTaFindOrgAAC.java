package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountServiceFindAF;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountServiceShowAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;

public class AdjustaccountServiceTaFindOrgAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      String id=(String)request.getParameter("id");
    IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
    .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());   
    AdjustWrongAccountHead adjustWrongAccountHead=adjustaccountBS.findOrgHAFAccountFlowById(id);
    List list=adjustaccountBS.findAdjustWrongAccountHeadIDByOrgIdAndStatus(adjustWrongAccountHead.getOrg().getId()+"");
    String text="display('')";
    if(list.size()==0){//该公司只有一个状态
      text="display('ok')";
    }
    else {
      //单位有状态为一的
    }
    response.getWriter().write(text); 
    response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return null;
  }
  
}
