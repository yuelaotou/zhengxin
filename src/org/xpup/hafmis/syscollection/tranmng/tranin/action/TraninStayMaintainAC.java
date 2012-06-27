package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.form.DemoAddAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninIdAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninImportAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninStayAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF;

/**
 * shiyan
 */
public class TraninStayMaintainAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowStayTraninListAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = null;
    try {
      IdAF idAF = (IdAF) form;
      String report = request.getParameter("report");
      String id = (String) idAF.getId();
      String inOrgId = request.getParameter("tranoutinorgid");
      String tranOutOrgId = request.getParameter("tranoutoutorgid");
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      ItranoutBS tranoutBS = (ItranoutBS)BSUtils.getBusinessService("tranoutBS",this,mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String traninid=traninBS.saveTranin_sy(inOrgId, id, tranOutOrgId,securityInfo);
      if (report.equals("print")) {
        List list = null;
        TranTbPrintAF tranTbPrintAF = tranoutBS.printCredence(id);
        list=tranTbPrintAF.getList();
        String bizDate = securityInfo.getUserInfo().getBizDate();
        request.setAttribute("bizDate", bizDate);
        request.setAttribute("tranTbPrintAF",tranTbPrintAF);
        request.setAttribute("url", "showTraninStayListURLAC.do");
        request.setAttribute("URL", "showTraninStayListURLAC.do");
        if(list.size()==1){
          return mapping.findForward("to_printCredence.jsp");//»ú´ò
        }else{
          return mapping.findForward("tranin_tail_cell");//Çå²á
        }
      }
    }catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("showTraninStayListAC");
  }
}
