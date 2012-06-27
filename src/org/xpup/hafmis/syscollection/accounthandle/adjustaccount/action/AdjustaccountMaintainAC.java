package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

import java.io.Serializable;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountReportDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountNewAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

/**
 * @author 李鹏 2007-6-29
 */
public class AdjustaccountMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountTaShowAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.delete", "delete");
    map.put("button.over.adjust", "adjust");
    return map;
  }

  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    Map bistype = BusiTools.listBusiProperty(BusiConst.CHGBUSINESSTYPE);
    AdjustaccountNewAF adjustaccountNewAF = new AdjustaccountNewAF();
    adjustaccountNewAF.setSexMap(bistype);
    adjustaccountNewAF.setTotalBalance(new BigDecimal(0.00));
    IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
        .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
    String orgId = (String) request.getSession(false).getAttribute(
        "findadjustWrongAccountHead_orgId");
    String noteNum = request.getParameter("noteNumber");
    // Serializable
    // orgid=(Serializable)request.getSession().getAttribute("ORGID");
    // String type=(String)request.getSession().getAttribute("TYPE");
    // if(orgId==null && orgid!=null){
    // orgId=orgid.toString();
    // }
    AdjustWrongAccountHead adjustWrongAccountHead = adjustaccountBS
        .findAdjustWrongAccountHeadIDByCriterions(orgId);
    if (adjustWrongAccountHead != null) {
      adjustaccountNewAF.setType("1");
      adjustaccountNewAF.setTypelist(BusiTools.getBusiKey_WL(
          adjustWrongAccountHead.getBizType(), BusiConst.CHGBUSINESSTYPE));
    } else
      adjustaccountNewAF.setType("2");
    adjustaccountNewAF.setOrgId(orgId);
    request.getSession().setAttribute("noteNumber", noteNum);
    request.setAttribute("adjustaccountNewAF", adjustaccountNewAF);
    return mapping.findForward("to_adjustaccount_add");
  }

  public ActionForward adjust(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      messages = new ActionMessages();
      // if(!isTokenValid(request)){
      // messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要刷新！",
      // false));
      // saveErrors(request, messages);
      // }else
      // {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String noteNum = request.getParameter("noteNumber");
      HttpSession session = request.getSession(false);
      Pagination pagination = (Pagination) session.getAttribute(PAGINATION_KEY);
      List list = (List) pagination.getQueryCriterions().get("pageList");
      String bizDocNum = (String) pagination.getQueryCriterions().get(
          "empHAFAccountFlow.orgHAFAccountFlow.docNum");
      IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
          .getBusinessService("adjustaccountBS", this, mapping
              .getModuleConfig());
      String orgId = (String) request.getSession(false).getAttribute(
          "findadjustWrongAccountHead_orgId");
      AdjustWrongAccountHead adjustWrongAccountHead = adjustaccountBS
          .findOrgHAFAccountFlowByOrgAndStatus(orgId, securityInfo);
      List empList = new ArrayList();
      Serializable id;
      AdjustWrongAccountHead adjustWrongAccountHead1 = null;
      if (adjustWrongAccountHead == null)// 输入错账凭证编号 进入
      {
        EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) list.get(0);
        // id=adjustaccountBS.insertAdjustWrongHATByOrgHAT(list,securityInfo);
        adjustWrongAccountHead1 = adjustaccountBS.insertAdjustWrongHATByOrgHAT(
            list, securityInfo, noteNum);
        // 更改过,
        // adjustWrongAccountHead1=adjustaccountBS.findAdjustWrongAccountHeadByID(id);
        // empList=adjustaccountBS.findAdjustWrongAccountTailByHead(id+"");
        // adjustaccountBS.insertOrgHAFAccountFlowByWrongHAT(adjustWrongAccountHead1,empList,securityInfo);
        request.getSession(false).setAttribute(
            "findadjustWrongAccountHead_type", "11");
        // AdjustWrongAccountHead
        // adjustWrongAccountHead2=adjustaccountBS.findOrgHAFAccountFlowByCriterions(bizDocNum);
        // if(adjustWrongAccountHead2==null)//判断是否已经存在该单位
        // request.getSession(false).setAttribute("findadjustWrongAccountHead_type","1");
        // else
        // request.getSession(false).setAttribute("findadjustWrongAccountHead_type","0");
      } else {// 输入单位编号
        // id=adjustaccountBS.updateAdjustWrongAccountHeadState(adjustWrongAccountHead,securityInfo);
        adjustWrongAccountHead1 = adjustaccountBS
            .updateAdjustWrongAccountHeadState(adjustWrongAccountHead,
                securityInfo, noteNum);
        // adjustWrongAccountHead1=adjustaccountBS.findAdjustWrongAccountHeadByID(id);
        // empList=adjustaccountBS.findAdjustWrongAccountTailByHead(id+"");
        // adjustaccountBS.insertOrgHAFAccountFlowByWrongHAT(adjustWrongAccountHead1,empList,securityInfo);
        // adjustWrongAccountHead=adjustaccountBS.findOrgHAFAccountFlowByOrgAndStatus(orgId);
        // if(adjustWrongAccountHead!=null){
        // request.getSession(false).setAttribute("findadjustWrongAccountHead_type",
        // "2");
        // }else
        request.getSession(false).setAttribute(
            "findadjustWrongAccountHead_type", "11");
      }
      String empid = "";
      List printlist = new ArrayList();
      for (int i = 0; i < empList.size(); i++) {

        AdjustaccountReportDTO adjustaccountReportDTO = new AdjustaccountReportDTO();
        AdjustWrongAccountTail adjustWrongAccountTail = (AdjustWrongAccountTail) empList
            .get(i);

        empid = adjustWrongAccountTail.getEmpId().toString();
        adjustaccountReportDTO.setWrongdocnum(adjustWrongAccountHead1
            .getBizDocNum());
        adjustaccountReportDTO.setEmpid(adjustWrongAccountTail.getEmpId()
            .toString());
        Emp emp = adjustaccountBS.findEmpById(empid);
        adjustaccountReportDTO.setEmpname(emp.getEmpInfo().getName());
        adjustaccountReportDTO.setEmpidcard(emp.getEmpInfo().getCardNum());
        adjustaccountReportDTO.setAdjustaccount(adjustWrongAccountTail
            .getAdjustMoney());
        adjustaccountReportDTO.setWrongaccountdate(adjustWrongAccountTail
            .getSettDate());
        adjustaccountReportDTO
            .setBis_type(adjustWrongAccountHead1.getBizType());

        printlist.add(adjustaccountReportDTO);
      }
      pagination.getQueryCriterions().put("adjustprint", printlist);

      // messages=new ActionMessages();
      // messages.add(ActionMessages.GLOBAL_MESSAGE, new
      // ActionMessage("完成调整成功！",
      // false));
      // saveErrors(request, messages);
      // }
      String report = request.getParameter("report");
      if (report != null && !report.equals("") && report.equals("print")) {
        List templist = adjustaccountBS.adjustWrongAccountAllByHID(
            adjustWrongAccountHead1.getId().toString(), securityInfo);
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
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        String collectionBankId = "";
        String collectionBankName = "";

        if (orgId != null && !orgId.equals("")) {
          collectionBankName = loanDocNumDesignBS.queryCollectionBankNameById(
              orgId, securityInfo);
        }
        String bizDate = securityInfo.getUserInfo().getBizDate();
        request.setAttribute("userName", userName);
        request.setAttribute("bizDate", bizDate);
        request.setAttribute("collectionBankName", collectionBankName);
        request.setAttribute("printlist", templist);
        // wuht
        if (!templist.isEmpty()) {
          if (templist.size() > 1)
            return mapping.findForward("adjustaccount_list_cell");
        }
        request.setAttribute("printlist", templist);
        request.setAttribute("URL", "adjustaccountForwardURLAC.do");
        pagination.getQueryCriterions().put("adjustaccountList2",
            new ArrayList());
        return mapping.findForward("adjustaccount_single_cell");
      }
    } catch (BusinessException bex) {
      bex.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("完成调整失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("showAdjustaccountAC");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      messages = new ActionMessages();
      // if(!isTokenValid(request)){
      // messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要刷新！",
      // false));
      // saveErrors(request, messages);
      // }else
      // {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      IdAF idaf = (IdAF) form;
      String id = idaf.getId().toString();
      IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
          .getBusinessService("adjustaccountBS", this, mapping
              .getModuleConfig());
      String line = adjustaccountBS.deleteAdjustWrongAccountTailByID(id,
          securityInfo);
      if ("noLine".equals(line))
        request.getSession(false).setAttribute(
            "findadjustWrongAccountHead_type", "");
      // messages=new ActionMessages();
      // messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
      // false));
      // saveErrors(request, messages);
      // }
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！",
          false));
      saveErrors(request, messages);
    }

    return mapping.findForward("showAdjustaccountAC");
  }

}
