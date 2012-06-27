package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountServiceFindAF;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountServiceShowAF;



public class AdjustaccountServiceTaFindAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    AdjustaccountServiceFindAF forms = (AdjustaccountServiceFindAF) form;
    AdjustaccountServiceShowAF adjustaccountServiceShowAF=new AdjustaccountServiceShowAF();
    Map bisMap=BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
    adjustaccountServiceShowAF.setBisMap(bisMap);
    request.setAttribute("adjustaccountServiceShowAF", adjustaccountServiceShowAF);
    HashMap criterions = makeCriterionsMap(forms);
    // 付云峰修：改美页10条数据
    Pagination pagination = new Pagination(0, 10, 1,
        "adjustWrongAccountHead.id", "DESC", criterions);
    String paginationKey = AdjustaccountServiceTaShowAC.PAGINATION_KEY;
    request.getSession().setAttribute(paginationKey, pagination);
    pagination.getQueryCriterions().put("type", "1");
    request.getSession().setAttribute("type", "1");
    forms.reset(mapping, request);
    return mapping.findForward("showAdjustaccountServiceAC");
  }
  
  protected HashMap makeCriterionsMap(AdjustaccountServiceFindAF form) {
    HashMap m = new HashMap();
    String id = form.getOrgId();
    if (!(id == null || "".equals(id))) {
      try {
        int iid = Integer.parseInt(id);
        m.put("id", new Integer(iid));
      } catch (NumberFormatException e) {
        m.put("id", new Integer(-10000));
      }
    }
    String name = form.getOrgName();
    if (!(name == null || name.length() == 0))
      m.put("name", form.getOrgName());
    // TODO 需要继续添加查询条件！！！
    String status = form.getBis_Status();
    if(!(status == null || status.length() == 0)){
      m.put("bis_status", form.getBis_Status());
    }
    String inceptMonth = form.getBizDocNum();
    if(!(inceptMonth == null || inceptMonth.length() == 0)){
      m.put("bizDocNum", form.getBizDocNum());
    }
    String payMonth = form.getDate();
    if(!(payMonth == null || payMonth.length() == 0)){
      m.put("date", form.getDate());
    }
    String payMonth1 = form.getDate1();
    if(!(payMonth1 == null || payMonth1.length() == 0)){
      m.put("date1", form.getDate1());
    }
    return m;
  }
}
