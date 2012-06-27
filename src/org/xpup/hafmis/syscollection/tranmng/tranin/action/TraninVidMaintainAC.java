package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
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
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showFindTbAC;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TranTbPrintAF;

/**
 * shiyan
 */
public class TraninVidMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninVidListAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.del.tranin", "tranin");
    map.put("button.update", "update");
    map.put("button.delete", "delete");
    map.put("button.print.machine", "printcredence");//机打凭证
    map.put("button.adjust.tranin", "adjust");
    map.put("button.print.renzhengdan", "print");// 认证单打印
    map.put("button.pproval.data", "pprovalDataOrgInfo");
    map.put("button.referring.data", "referringDataOrgInfo");
    map.put("button.printList", "printList");
    return map;
  }
  public ActionForward printList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
    String bizDate=securityInfo.getUserInfo().getBizDate();
    String userName="";
    String name = tranoutBS.queryMakerPara();
    if (name.equals("1")) {
      userName = securityInfo.getUserName();
    } else {
      userName = securityInfo.getRealName();
    } 
    request.setAttribute("bizDate",bizDate );
    request.setAttribute("userName",userName );
    return mapping.findForward("traninlist_yg");
  }
  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {
    ActionMessages messages = null;
    TraninIdAF traninIdAF = (TraninIdAF) form;
    ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
        this, mapping.getModuleConfig());
    String tranInHeadId = (String) traninIdAF.getId();
    TraninVidAF traninVidAF = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      traninVidAF = traninBS.print_sy(tranInHeadId, securityInfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }

    // wuht
    try {
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
          .getBusinessService("loanDocNumDesignBS", this, mapping
              .getModuleConfig());
      String userName = "";
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      try {
        String name = loanDocNumDesignBS.getNamePara();

        if (name.equals("1")) {
          userName = securityInfo.getUserName();
        } else {
          userName = securityInfo.getRealName();
        }

      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      String collectionBankId = "";
      String collectionBankId_out = "";
      String collectionBankName = "";
      String collectionBankName_out = "";
      String collectionBankAcc = "";
      String orgId = traninVidAF.getInOrgId();
      String outorgId = traninVidAF.getOutOrgId();
      if (orgId != null && !orgId.equals("")) {
        collectionBankName = loanDocNumDesignBS.queryCollectionBankNameById(
            orgId, securityInfo);
        collectionBankAcc = loanDocNumDesignBS.queryCollectionBankAccById(
            orgId, securityInfo);
        Org inorg = traninBS.queryOrg_yg(new Integer(orgId));
        collectionBankId = inorg.getOrgInfo().getCollectionBankId();
        request.setAttribute("collectionBankId", collectionBankId);
        request.setAttribute("collectionBankName", collectionBankName);
        request.setAttribute("collectionBankAcc", collectionBankAcc);
      }
      if (outorgId != null && !outorgId.equals("")) {
        collectionBankName_out = loanDocNumDesignBS
            .queryCollectionBankNameById(outorgId, securityInfo);
        Org outorg = traninBS.queryOrg_yg(new Integer(outorgId));
        collectionBankId_out = outorg.getOrgInfo().getCollectionBankId();
        request.setAttribute("collectionBankId_out", collectionBankId_out);
        request.setAttribute("collectionBankName_out", collectionBankName_out);
      }
      String bizDate = securityInfo.getUserInfo().getBizDate();
      request.setAttribute("userName", userName);
      request.setAttribute("bizDate", bizDate);
      // wuht
    } catch (Exception bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }

    request.setAttribute("URL", "showTraninVidListURLAC.do");
    TranInTail traninTail=(TranInTail)traninVidAF.getList().get(0);
    try {
      List lista=traninBS.tranoutTailReason(traninTail.getTranInHead().getTranOutHeadId().toString());
      traninVidAF.setLista(lista);
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("traninVidAF", traninVidAF);
    return mapping.findForward("tranin_tail_cell_yga");
  }

  public ActionForward adjust(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {
    ActionMessages messages = null;
    try {
      // String report = request.getParameter("report");
      TraninIdAF traninIdAF = (TraninIdAF) form;
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      String tranInHeadId = (String) traninIdAF.getId();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      traninBS.adjustTranin_sy(tranInHeadId, securityInfo);
      // if (report.equals("print")) {
      // TraninVidAF traninVidAF = traninBS.print_sy(tranInHeadId,securityInfo);
      // request.setAttribute("traninVidAF", traninVidAF);
      // return mapping.findForward("tranin_tail_cell");
      // }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("showTraninVidListAC");
  }

  public ActionForward tranin(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {
    saveToken(request);
    ActionMessages messages = null;
    try {
      messages = new ActionMessages();
      TraninIdAF traninIdAF = (TraninIdAF) form;
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      String tranInHeadId = (String) traninIdAF.getId();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String noteNum = request.getParameter("noteNum");
      traninBS.updataTranInVidHead(tranInHeadId, securityInfo, noteNum);
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("转入登记成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("showTraninVidListAC");
  }

  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    saveToken(request);
    TraninIdAF traninIdAF = (TraninIdAF) form;
    try {
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      String tranInHeadId = (String) traninIdAF.getId();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String orgId = traninBS
          .modiftHafOperateLog_sy(tranInHeadId, securityInfo);
      request.getSession().setAttribute("orgId", orgId);
      request.getSession().setAttribute("buttonMagssage", "nobutton");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("showTraninListAC");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws BusinessException {
    ActionMessages messages = null;
    try {
      messages = new ActionMessages();
      TraninIdAF traninIdAF = (TraninIdAF) form;
      String tranInHeadById = (String) traninIdAF.getId();
      HttpSession session = request.getSession(false);
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      traninBS.deletePageList(tranInHeadById, securityInfo);
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("showTraninVidListAC");
  }

  public ActionForward pprovalDataOrgInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    TraninIdAF traninIdAF = (TraninIdAF) form;
    String tranInHeadById = (String) traninIdAF.getId();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      traninBS.pprovalDataInfo(tranInHeadById, securityInfo);
      ActionMessages messagesinfo = new ActionMessages();
      messagesinfo.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "撤销提交数据成功", false));
      saveErrors(request, messagesinfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("showTraninVidListAC");
  }

  public ActionForward referringDataOrgInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    TraninIdAF traninIdAF = (TraninIdAF) form;
    String tranInHeadById = (String) traninIdAF.getId();
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      traninBS.referringDataInfo(tranInHeadById, securityInfo);
      ActionMessages messagesinfo = new ActionMessages();
      messagesinfo.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "提交数据成功", false));
      saveErrors(request, messagesinfo);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("showTraninVidListAC");
  }
  //机打凭证
  public ActionForward printcredence(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List list = null;
    org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF tranTbPrintAF=null;
    try {
      IdAF idAF = (IdAF) form;
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      TranInHead tranInHead=traninBS.queryTranInHead(idAF.getId().toString());
      if(tranInHead.getTranOutHeadId()!=null && tranInHead.getTranOutHeadId().intValue()!=0 ){
        tranTbPrintAF = traninBS.printCredence_yg(tranInHead.getTranOutHeadId().toString());
        String bizDate = securityInfo.getUserInfo().getBizDate();
        request.setAttribute("bizDate", bizDate);
        request.setAttribute("tranTbPrintAF",tranTbPrintAF);
        request.setAttribute("tranTailcelllist", tranTbPrintAF.getList());
        request.setAttribute("url", "showTraninVidListURLAC.do");
      }else{
        tranTbPrintAF = traninBS.printCredence_yga(tranInHead.getId().toString());
        String bizDate = securityInfo.getUserInfo().getBizDate();
        request.setAttribute("bizDate", bizDate);
        request.setAttribute("tranTbPrintAF",tranTbPrintAF);
        request.setAttribute("tranTailcelllist", tranTbPrintAF.getList());
        request.setAttribute("url", "showTraninVidListURLAC.do");
        if(tranTbPrintAF.getList().size()==1){
          return mapping.findForward("to_printCredencea");
        }else{
          return mapping.findForward("tranin_tail_cell_ygb");
        }
      }
    
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(tranTbPrintAF.getList().size()==1){
      return mapping.findForward("to_printCredence.jsp");
    }else{
      return mapping.findForward("tranin_tail_cell");
    }
    
    
  }

  public ActionForward printall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List list = null;
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          TraninVidMaintainAC.PAGINATION_KEY);
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      list = traninBS.printAll(pagination, securityInfo);
      request.setAttribute("printList", list);
      request.setAttribute("url", "showTraninVidListURLAC.do");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_printall.jsp");
  }
}
