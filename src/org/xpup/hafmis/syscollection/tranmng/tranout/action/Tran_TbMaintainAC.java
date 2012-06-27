package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.paymng.paysure.bsinterface.IDocNumBS;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF;

public class Tran_TbMaintainAC extends LookupDispatchAction {
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.search", "search"); // 查询走Tran_FindTbAC.do
    map.put("button.over.tranout", "over");// 完成转出
    map.put("button.del.tranout", "del_over");// 撤消转出
    map.put("button.update", "update");// 修改
    map.put("button.delete", "delete");// 删除
    map.put("button.print.machine_1", "printcredence"); // 机打凭证
    map.put("button.print.tao_1", "print");// 打印凭证
    map.put("button.printall", "printall"); // // 批量打印
    map.put("button.referring.data", "referringdata");
    map.put("button.pproval.data", "pprovaldata");
    map.put("button.printList", "printList");
    map.put("button.dengji.yangg", "dengji");
    map.put("button.dengji.chexiao.yangg", "chexiaodengji");
    return map;
  }

  public ActionForward dengji(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS",
        this, mapping.getModuleConfig());
    IdAF idAF = (IdAF) form;
    String headPkid = idAF.getId().toString();
    try {
      tranoutBS.updateByTranOutHeadId(headPkid);
    } catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      e.printStackTrace();
    }
    return mapping.findForward("to_tran_showTbAC");
  }

  public ActionForward chexiaodengji(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS",
        this, mapping.getModuleConfig());
    IdAF idAF = (IdAF) form;
    String headPkid = idAF.getId().toString();
    try {
      tranoutBS.updateByTranOutHeadId(headPkid);
    } catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      e.printStackTrace();
    }
    return mapping.findForward("to_tran_showTbAC");
  }

  public ActionForward printList(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS",
        this, mapping.getModuleConfig());
    String bizDate = securityInfo.getUserInfo().getBizDate();
    String userName = "";
    String name = tranoutBS.queryMakerPara();
    if (name.equals("1")) {
      userName = securityInfo.getUserName();
    } else {
      userName = securityInfo.getRealName();
    }
    request.setAttribute("bizDate", bizDate);
    request.setAttribute("userName", userName);
    return mapping.findForward("tranoutlist");
  }

  public ActionForward over(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    ActionMessages messages = null;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String bizYearmonth = securityInfo.getUserInfo().getBizDate().substring(0,
        6);
    IdAF idAF = (IdAF) form;
    String headPkid = idAF.getId().toString();// 309 pkid

    ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS",
        this, mapping.getModuleConfig());
    IDocNumBS DocNumBS = (IDocNumBS) BSUtils.getBusinessService("DocNumBS",
        this, mapping.getModuleConfig());

    String report = request.getParameter("report");
    String docNum = "";
    try {
      String docNumDocument = DocNumBS.getDocNumDesignPara();
      String officeCode = "";
      if (docNumDocument.equals("1")) {
        officeCode = tranoutBS.GetOfficeCodeTb(headPkid);
      } else {
        officeCode = tranoutBS.GetOfficeCodeTb_yg(headPkid);
      }
      docNum = DocNumBS.getDocNumdocNum(officeCode, bizYearmonth, securityInfo);
      tranoutBS.updateOrgHafaccountFlowTb(headPkid, docNum, securityInfo);
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);

    }
    if (report.equals("print")) {
      List list = null;
      list = tranoutBS.getTranTailListPrint(idAF.getId().toString());

      TranOutTail tranOutTail = (TranOutTail) list.get(0);
      String orgId = tranOutTail.getTranOutHead().getTranOutOrg().getId()
          .toString();

      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
          .getBusinessService("loanDocNumDesignBS", this, mapping
              .getModuleConfig());
      String userName = "";
      try {
        String name = loanDocNumDesignBS.getNamePara();

        if (name.equals("1")) {
          userName = securityInfo.getUserName();
        } else {
          userName = securityInfo.getRealName();
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
      String collectionBankName = "";

      if (orgId != null && !orgId.equals("")) {
        collectionBankName = loanDocNumDesignBS.queryCollectionBankNameById(
            orgId, securityInfo);
      }
      String bizDate = securityInfo.getUserInfo().getBizDate();
      request.setAttribute("userName", userName);
      request.setAttribute("bizDate", bizDate);
      request.setAttribute("collectionBankName", collectionBankName);
      // wuht
      request.setAttribute("tranTailcelllist", list);
      if (list.size() == 1) {
        return mapping.findForward("to_tran_print_yg");
      } else {
        TranTbPrintAF tranTbPrintAF = tranoutBS.printCredence(headPkid);
        request.setAttribute("tranTbPrintAF", tranTbPrintAF);
        return mapping.findForward("to_tran_print");
      }

    }
    return mapping.findForward("to_tran_showTbAC");

  }

  // -----------------------------------------------------------------------------------------------------
  // 撤消转出
  public ActionForward del_over(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String userName = securityInfo.getUserName();
      String ip = securityInfo.getUserIp();
      String setDate = securityInfo.getUserInfo().getBizDate();
      IdAF idaf = (IdAF) form;
      request.getSession().setAttribute("tranoutheadid", idaf.getId());
      String pkid = idaf.getId().toString();// 309 pk
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService(
          "tranoutBS", this, mapping.getModuleConfig());
      if (pkid.length() > 0) {// 判断传来的Pkid 是否为空
        tranoutBS.UpdateTranHead(pkid, userName, ip, setDate);
      }
    } catch (BusinessException b) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_tran_showTbAC");
  }

  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IdAF f = (IdAF) form;
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService(
          "tranoutBS", this, mapping.getModuleConfig());

      TranOutHead tranOutHead = tranoutBS.FindOrgTb(f.getId().toString());

      Pagination pagination = new Pagination();
      request.setAttribute("type", "0");
      pagination.getQueryCriterions().put("id",
          tranOutHead.getTranOutOrg().getId().toString());// 转出单位
      pagination.getQueryCriterions().put("name",
          tranOutHead.getTranOutOrg().getOrgInfo().getName());// 转出单位名称
      request.getSession().setAttribute(Tran_showAC.PAGINATION_KEY, pagination);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return mapping.findForward("to_tran_showAC");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    String nrOfElements = request.getParameter("nrOfElements");// 共计项
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String userName = securityInfo.getUserName();
    String ip = securityInfo.getUserIp();
    String dates = securityInfo.getUserInfo().getBizDate();

    ActionMessages messages = null;
    try {
      String pkid = null;
      IdAF idaf = (IdAF) form;
      pkid = idaf.getId().toString();// 得到 AA309 Primary key

      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService(
          "tranoutBS", this, mapping.getModuleConfig());
      tranoutBS.DeleteOrg(pkid, ip, userName, dates, securityInfo);

      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
          false));
      saveErrors(request, messages);
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_tran_showTbAC");
  }

  public ActionForward print(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List list = null;
    IdAF idAF = (IdAF) form;
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS",
        this, mapping.getModuleConfig());
    list = tranoutBS.getTranTailListPrint(idAF.getId().toString());
    TranOutTail tranOutTail = (TranOutTail) list.get(0);
    String orgId = tranOutTail.getTranOutHead().getTranOutOrg().getId()
        .toString();
    String orgIdin = tranOutTail.getTranOutHead().getTranInOrg().getId()
        .toString();
    String[] str = tranoutBS.queryOfficeBankNames(orgId, "2", idAF.getId()
        .toString(), "E", securityInfo);
    String bank = str[1];
    // wuht
    ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
        .getBusinessService("loanDocNumDesignBS", this, mapping
            .getModuleConfig());
    String userName = "";
    SecurityInfo securityInfo1 = (SecurityInfo) request.getSession()
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
    String collectionBankName = "";
    String collectionBankNamein = "";

    if (orgId != null && !orgId.equals("")) {
      collectionBankName = loanDocNumDesignBS
          .queryCollectionBankNameById_yg(orgId);
    }
    if (orgIdin != null && !orgIdin.equals("")) {
      collectionBankNamein = loanDocNumDesignBS
          .queryCollectionBankNameById_yg(orgIdin);
    }
    String bizDate = securityInfo.getUserInfo().getBizDate();
    request.setAttribute("userName", userName);
    request.setAttribute("bizDate", bizDate);
    request.setAttribute("collectionBankName", collectionBankName);
    request.setAttribute("collectionBankNamein", collectionBankNamein);
    // wuht

    request.setAttribute("AA101bank", bank);
    request.setAttribute("tranTailcelllist", list);
    return mapping.findForward("to_tran_print_yg");
  }

  public ActionForward referringdata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String userName = securityInfo.getUserName();
    String ip = securityInfo.getUserIp();
    String dates = securityInfo.getUserInfo().getBizDate();

    ActionMessages messages = null;
    try {
      String pkid = null;
      IdAF idaf = (IdAF) form;
      pkid = idaf.getId().toString();// 得到 AA309 Primary key
      String temp_P = "2";
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService(
          "tranoutBS", this, mapping.getModuleConfig());
      tranoutBS.referringDate(pkid, ip, userName, dates, securityInfo, temp_P);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_tran_showTbAC");
  }

  public ActionForward pprovaldata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {// 撤销提交
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    String userName = securityInfo.getUserName();
    String ip = securityInfo.getUserIp();
    String dates = securityInfo.getUserInfo().getBizDate();

    ActionMessages messages = null;
    try {
      String pkid = null;
      IdAF idaf = (IdAF) form;
      pkid = idaf.getId().toString();// 得到 AA309 Primary key
      String temp_P = "2";
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService(
          "tranoutBS", this, mapping.getModuleConfig());
      tranoutBS.pprovalDate(pkid, ip, userName, dates, securityInfo, temp_P);

    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_tran_showTbAC");
  }

  public ActionForward printcredence(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List list = null;
    try {
      IdAF idAF = (IdAF) form;
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService(
          "tranoutBS", this, mapping.getModuleConfig());
      TranTbPrintAF tranTbPrintAF = tranoutBS.printCredence(idAF.getId()
          .toString());
      list = tranTbPrintAF.getList();
      String bizDate = securityInfo.getUserInfo().getBizDate();
      request.setAttribute("bizDate", bizDate);
      request.setAttribute("tranTbPrintAF", tranTbPrintAF);
      request.setAttribute("url", "tran_showFindTbAC.do");
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (list.size() == 1) {
      return mapping.findForward("to_printCredence.jsp");
    } else {
      return mapping.findForward("to_tran_print");
    }
  }

  public ActionForward printall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List list = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          Tran_showFindTbAC.PAGINATION_KEY);
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService(
          "tranoutBS", this, mapping.getModuleConfig());
      list = tranoutBS.printAll(pagination, securityInfo);
      String bizDate = securityInfo.getUserInfo().getBizDate();
      request.setAttribute("bizDate", bizDate);
      request.setAttribute("printList", list);
      request.setAttribute("url", "tran_showFindTbAC.do");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_printall.jsp");
  }
}
