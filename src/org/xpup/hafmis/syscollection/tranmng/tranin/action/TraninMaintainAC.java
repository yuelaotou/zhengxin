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
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.demo.form.DemoAddAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninAddAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninIdAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninImportAF;
import org.xpup.hafmis.syscollection.tranmng.tranin.form.TraninVidAF;

/**
 * shiyan
 */
public class TraninMaintainAC extends LookupDispatchAction {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninListAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update", "update");
    map.put("button.delete", "delete");
    map.put("button.deleteall", "deleteAll");
    map.put("button.exports", "exportsTranin");
    map.put("button.imports", "importsTranin");
    map.put("button.del.tranin", "maintain");
    map.put("button.pickup.data", "pickupDateAll");
    map.put("button.pproval.data", "pprovalDataInfo");
    map.put("button.referring.data", "referringDataInfo");
    return map;
  }

  public ActionForward maintain(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) throws Exception{
    TraninIdAF traninIdAF = (TraninIdAF) form;
    org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF tranTbPrintAF=null;
    String report = request.getParameter("report");
    String tranInHeadId = request.getParameter("tranInHeadById");
    String inOrgId = traninIdAF.getInOrgId();
    String noteNum = request.getParameter("notenumber");
    if(noteNum!=null&&noteNum.equals("")){
    noteNum =request.getParameter("noteNum");
    }
    ActionMessages messages = null;
    try {
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      traninBS.updataTranInHead(tranInHeadId,securityInfo,noteNum);
      if (report.equals("print")) {
        tranTbPrintAF = traninBS.printCredence_yga(tranInHeadId);
        String bizDate = securityInfo.getUserInfo().getBizDate();
        request.setAttribute("bizDate", bizDate);
        request.setAttribute("tranTbPrintAF",tranTbPrintAF);
        request.setAttribute("tranTailcelllist", tranTbPrintAF.getList());
        request.setAttribute("url", "showTraninVidListURLAC.do");
        if(tranTbPrintAF.getList().size()==1){
          return mapping.findForward("to_printCredence.jsp");
        }else{
          return mapping.findForward("tranin_tail_cell_ygb");
        }
      }
    } catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("showTraninListURLAC");
  }

  public ActionForward importsTranin(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    TraninIdAF traninIdAF = (TraninIdAF) form;
    String inOrgId = traninIdAF.getInOrgId();
    try {
      TraninImportAF forms = new TraninImportAF();
      forms.setInOrgId(inOrgId);
      request.setAttribute("traninImportAF", forms);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_tranin_imports");
  }

  public ActionForward exportsTranin(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)throws BusinessException {
    ActionMessages messages = null;
    try {
      HttpSession session = request.getSession(false);
      Pagination pagination = (Pagination) session.getAttribute(PAGINATION_KEY);
      TraninIdAF traninIdAF = (TraninIdAF) form;
      String inOrgId = traninIdAF.getInOrgId();
      String inOrgName = request.getParameter("inOrgName");
      String noteNum = request.getParameter("noteNum");
      Map map = new HashMap();
      Map mapCriterions = new HashMap();

      String payMode = "";
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      Org orgs = traninBS.queryOrgDAO(new Integer(inOrgId),securityInfo);

      if (orgs != null && !orgs.equals("")) {
        payMode = orgs.getPayMode().toString();
        map.put("payMode", payMode);
      }
      List expList = new ArrayList();
      // 没有数据导出空表
      map.put("inOrgId", inOrgId);
      map.put("inOrgName", inOrgName);
      map.put("noteNum", noteNum);
      expList.add(map);
      if (expList.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=tranin_exp");
        traninBS.insertHafOperateLog_sy(inOrgId,securityInfo);
      }
      return null;

    }catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } 
    catch (Exception bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("showTraninListAC");
  }

  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    saveToken(request);
    TraninIdAF traninIdAF = (TraninIdAF) form;
    ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
        this, mapping.getModuleConfig());
    String inOrgId = traninIdAF.getInOrgId();
    // Pagination pagination=new Pagination();
    // String tranInHeadId=request.getParameter("tranInHeadById");
    String noteNum = request.getParameter("notenumber");
    TraninAddAF traninAddAF = new TraninAddAF();
    try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      traninAddAF = traninBS.findTranInHeadById(new Integer(inOrgId),securityInfo);

    } catch (BusinessException e) {

      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // traninAddAF.setTranInHeadId(tranInHeadId);
    traninAddAF.setNoteNum(noteNum);
    traninAddAF.setType("1");
    request.setAttribute("traninAddAF", traninAddAF);
    return mapping.findForward("to_tranin_add");
  }

  public ActionForward update(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    saveToken(request);
    try {
      TraninIdAF traninIdAF = (TraninIdAF) form;
      // Pagination pagination=new Pagination();
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      String inOrgId = traninIdAF.getInOrgId();
      String tranInTailId = request.getParameter("id");
      String tranInHeadId = request.getParameter("tranInHeadById");
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      TranInTail tranInTail = traninBS.queryTranInTailById(new Integer(
          tranInTailId),securityInfo);
      TraninAddAF traninAddAF = traninBS
          .findTranInHeadById(new Integer(inOrgId),securityInfo);
      traninAddAF.setTraninTailsex(tranInTail.getSex().toString());
      traninAddAF.setTranInHeadId(tranInHeadId);
      traninAddAF.setTranInTail(tranInTail);
      traninAddAF.setType("2");
      request.setAttribute("traninAddAF", traninAddAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_tranin_add");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)throws BusinessException {
    ActionMessages messages = null;
    try {
      TraninIdAF traninIdAF = (TraninIdAF) form;
      String id = (String) traninIdAF.getId();
      String orgId = traninIdAF.getInOrgId();
      String tranInHeadById = request.getParameter("tranInHeadById");
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      try {
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        traninBS.deleteTranInTail_sy(new Integer(id), tranInHeadById, orgId,securityInfo);

        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
            false));
        saveErrors(request, messages);
      } catch (BusinessException bex) {
        messages = new ActionMessages();

        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage().toString(), false));
        saveErrors(request, messages);
      }
    } catch (Exception e) {
      messages = new ActionMessages();

      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("showTraninListAC");
  }

  public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)throws BusinessException {
    ActionMessages messages = null;
    try {
      messages = new ActionMessages();
      String tranInHeadById = request.getParameter("tranInHeadById");
      HttpSession session = request.getSession(false);
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      traninBS.deletePageList_sy(tranInHeadById,securityInfo);
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
    return mapping.findForward("showTraninListAC");
  }
  public ActionForward pickupDateAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    saveToken(request);
    ActionMessages messages = null;
    try {
      TraninIdAF traninIdAF = (TraninIdAF) form;
//      String tranInHeadById = request.getParameter("tranInHeadById");
      String inOrgId = traninIdAF.getInOrgId();
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      traninBS.pickupDateAll(inOrgId, securityInfo);
    }catch(BusinessException bex){
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("showTraninListAC");
  }
  public ActionForward pprovalDataInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
//    TraninIdAF traninIdAF = (TraninIdAF) form;
//    String tranInHeadById = (String) traninIdAF.getId();
    String tranInHeadById = request.getParameter("tranInHeadById");
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      traninBS.pprovalDataFirstInfo(tranInHeadById, securityInfo);
      ActionMessages messagesinfo = new ActionMessages();
      messagesinfo.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤销提交数据成功", false));
      saveErrors(request, messagesinfo);
    }catch(BusinessException bex){
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return mapping.findForward("showTraninListAC");
  }
  public ActionForward referringDataInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
//    TraninIdAF traninIdAF = (TraninIdAF) form;
//    String tranInHeadById = (String) traninIdAF.getId();
    String tranInHeadById = request.getParameter("tranInHeadById");
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      traninBS.referringDataFirstInfo(tranInHeadById, securityInfo);
      ActionMessages messagesinfo = new ActionMessages();
      messagesinfo.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交数据成功", false));
      saveErrors(request, messagesinfo);
    }catch(BusinessException bex){
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return mapping.findForward("showTraninListAC");
  }
}
