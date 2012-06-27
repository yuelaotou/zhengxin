package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;

/**
 * shiyan
 */
public class TraninFindInforAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    ActionMessages messages = null;
    try {
      // 取出转入编号
      String inOrgId = (String) request.getParameter("inOrgId");
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      List list = new ArrayList();
      String inOrgName = "";
      String yg="";
      String yg_a="";
      String noteNum = request.getParameter("noteNum");
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      if (inOrgId != null && !inOrgId.equals("")) {
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        Org orgs = traninBS.queryOrgDAO(new Integer(inOrgId),securityInfo);
        if (orgs != null && !orgs.equals("")) {
          yg=traninBS.FindAA103_DayTime(orgs.getOrgInfo().getCollectionBankId());
          if(securityInfo.getUserInfo().getBizDate().equals(yg)){
            yg_a="a";
          }else{
            yg_a="b";
          }
          inOrgName = orgs.getOrgInfo().getName();
        }
      }
      String text = null;
      pagination.getQueryCriterions().put("inOrgName", inOrgName);
      if (noteNum != null && !noteNum.equals("")) {
        pagination.getQueryCriterions().put("noteNum", noteNum);
      } else {
        pagination.getQueryCriterions().put("noteNum", "");
      }
      pagination.getQueryCriterions().put("tranInHead.InOrg.id", inOrgId);
      text = "displays('" + BusiTools.convertTenNumber(inOrgId) + "','" + inOrgName + "','" + noteNum
          + "','"+yg_a+"')";
      response.getWriter().write(text);
      response.getWriter().close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  protected String getPaginationKey() {
    return ShowTraninListAC.PAGINATION_KEY;
  }
}