package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
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
import org.xpup.hafmis.demo.form.DemoAddAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface.IOrgaddpayBS;
import org.xpup.hafmis.syscollection.paymng.paysure.bsinterface.IDocNumBS;
import org.xpup.hafmis.syscollection.paymng.paysure.business.DocNumBS;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.business.tranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAddAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranImportTaAF;

public class Tran_MaintainAC extends LookupDispatchAction {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showAC";

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.exports", "exports");      // 批量导出
    map.put("button.imports", "imports");      // 批量导入
    map.put("button.add", "add");              // 添加
    map.put("button.delete", "delete");        // 删除
    map.put("button.deleteall", "deleteall");  // 删除所有
    map.put("button.over.tranout", "sure");    // 完成转出
    map.put("button.sure", "save");            // 插入
    map.put("button.referring.data", "referringdata");
    map.put("button.pproval.data", "pprovaldata");
    map.put("button.pickup.data", "pickupdata");
    map.put("button.dengji.yangg", "dengji");
    return map;
  }
//===========================================================================
  public ActionForward dengji(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS",
        this, mapping.getModuleConfig());
    IdAF idAF = (IdAF) form;
    String tailPkid  = idAF.getId().toString();
    String headPkid = tranoutBS.queryTranOutHeadByTailId(tailPkid);
    try {
      tranoutBS.updateByTranOutHeadId(headPkid);
    } catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getMessage(), false));
      saveErrors(request, messages);
      e.printStackTrace();
    }
    return mapping.findForward("to_tran_showAC");
  }
  //添加
  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception { 

    ActionMessages messages =null;
    try{
    String outOrgId = request.getParameter("outid");
    String inOrgId = request.getParameter("inid");
    String headId=request.getParameter("headid");
    String noteNum=request.getParameter("noteNum");
//    Pagination pagination = (Pagination) request.getSession().getAttribute(PAGINATION_KEY);
//    pagination.getQueryCriterions().put("outOrgId" ,outOrgId);
//    pagination.getQueryCriterions().put("inOrgId" ,inOrgId);
    TranAddAF tranAddAF = new TranAddAF();
    Map map = new HashMap();
    map.put("2", "否");
    map.put("1", "是");
    tranAddAF.setMap(map);
    tranAddAF.setOutOrgId(outOrgId);
    tranAddAF.setInOrgId(inOrgId);
    tranAddAF.setHeadId(headId);
    tranAddAF.setNoteNum(noteNum);
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    int typetran=securityInfo.getIsOrgEdition();
    request.setAttribute("typetran", typetran+"");
    String type=request.getParameter("types");
    request.setAttribute("type", type);
    request.setAttribute("tranAddAF", tranAddAF);
    }catch(Exception e){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_add_jsp");
  }
//=========================================================================== 
  //批量导出

  public ActionForward exports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    TranAF tranAF = new TranAF();
    HttpSession session=request.getSession(false);
    Pagination pagination=new Pagination();
    try{
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
 
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      
      String orgOutid = request.getParameter("outid");
      String orgOutName = request.getParameter("orgOutName");
      String orgInId = request.getParameter("inid");
      String orgInName = request.getParameter("orgInName");
      String noteNum = request.getParameter("noteNum");
      pagination.getQueryCriterions().put("orgOutid", orgOutid);
      pagination.getQueryCriterions().put("orgOutName", orgOutName);
      pagination.getQueryCriterions().put("orgInId", orgInId);
      pagination.getQueryCriterions().put("orgInName", orgInName);
      pagination.getQueryCriterions().put("noteNum", noteNum);
      pagination.getQueryCriterions().put("name", name);
      pagination.getQueryCriterions().put("ip", ip);
      List expList=tranoutBS.findPaylistBatch(pagination);
      String type=request.getParameter("types");
      request.setAttribute("type", type);
      if(expList.size()>0)
      {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功w！",
            false));
          saveErrors(request, messages);
          request.getSession().setAttribute("explist",expList);
          response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=tranOut_exp");
          return null;
      }else
      {
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }   
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！"+bex.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_tran_showAC");
  }
  
//===========================================================================

  //批量导入
  public ActionForward imports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    String orgOutid = request.getParameter("outid");
    String orgOutName = request.getParameter("orgOutName");
    String orgInId = request.getParameter("inid");
    String orgInName = request.getParameter("orgInName");
    String noteNum = request.getParameter("noteNum");
//    System.out.println(orgOutName+"--orgOutName--"+orgInId+"--orgInId--"+orgOutid+"--orgOutid--"
//        +orgInName+"--orgInName--"+noteNum+"--noteNum-------------------------->");
    TranImportTaAF tranImportTaAF = new TranImportTaAF();
    tranImportTaAF.setOrgOutid(orgOutid);
    tranImportTaAF.setOrgOutName(orgOutName);
    tranImportTaAF.setOrgInid(orgInId);
    tranImportTaAF.setOrgInName(orgInName);
    tranImportTaAF.setNoteNum(noteNum);

    String type=request.getParameter("types");
    request.setAttribute("type", type);
    request.setAttribute("tranImportTaAF", tranImportTaAF);
    return mapping.findForward("to_tranout_imports.jsp");
  }
//===========================================================================
  //插入
  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("to_insertAC.do");
  }
//===========================================================================
  //删除
  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    IdAF idaf = (IdAF) form;  
    ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
    ActionMessages messages =null;
    try{
      String type=request.getParameter("types");
      request.setAttribute("type", type);
        tranoutBS.deleteTailInfo(idaf.getId().toString(),securityInfo);
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
            false));
        saveErrors(request, messages);
     }catch(Exception e){
       messages=new ActionMessages();
       messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessage(),
           false));
       saveErrors(request, messages);
    }
    return mapping.findForward("to_tran_showAC");
  }
  
  //删除所有
  public ActionForward deleteall(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    
    String HeadOutOrgId = request.getParameter("orgOutid");//头表的转出单位
    HttpSession session=request.getSession(false);
    Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
    List list=(List)pagination.getQueryCriterions().get("pageList");  
    String orgid = null;
    IdAF idaf = (IdAF) form;
    String type=request.getParameter("types");
    request.setAttribute("type", type);
    orgid = idaf.getId().toString();// 310 pkid
    ActionMessages messages =null;
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
      try{

        tranoutBS.deleteAll(list,securityInfo);
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
            false));
        saveErrors(request, messages);
      }catch(Exception e){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e.getMessage(),
            false));
        saveErrors(request, messages);
      }
      
    return mapping.findForward("to_tran_showAC");
  }
  
  //完成转出
  public ActionForward sure(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    String report=request.getParameter("report");
    String headid=null;
    try{
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String bizYearmonth=securityInfo.getUserInfo().getBizDate().substring(0, 6);
      IdAF idAF = (IdAF)form;
      String type=request.getParameter("types");
      //自动转入用到
      String tranOutOrgId=request.getParameter("outid");
      String inOrgId=request.getParameter("inid");
      request.setAttribute("type", type);
      String noteNum=request.getParameter("noteNum");
      String tailPkid  = idAF.getId().toString();
      ItranoutBS tranoutBS = (ItranoutBS)BSUtils.getBusinessService("tranoutBS",this,mapping.getModuleConfig());
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",this, mapping.getModuleConfig());
      IDocNumBS DocNumBS = (IDocNumBS)BSUtils.getBusinessService("DocNumBS",this,mapping.getModuleConfig());
      
      String docNumDocument=DocNumBS.getDocNumDesignPara();
      String officeCode="";
      if(docNumDocument.equals("1")){
        officeCode = tranoutBS.GetOfficeCode(tailPkid);
      }else{
        officeCode = tranoutBS.GetOfficeCode_yg(tailPkid); 
      }
      String docNum=DocNumBS.getDocNumdocNum(officeCode, bizYearmonth,securityInfo);
      headid=tranoutBS.updateOrgHafaccountFlow(tailPkid,docNum,noteNum,securityInfo);
//      if(inOrgId!=null&&!"".equals(inOrgId)){
//        traninBS.saveTranin_sy(inOrgId, headid, tranOutOrgId,securityInfo);
//      }
      if(report.equals("print")){
        List list = null;
        TranTbPrintAF tranTbPrintAF = tranoutBS.printCredence(headid);
        list=tranTbPrintAF.getList();
        String bizDate = securityInfo.getUserInfo().getBizDate();
        request.setAttribute("bizDate", bizDate);
        request.setAttribute("docNum", docNum);
        request.setAttribute("tranTbPrintAF",tranTbPrintAF);
        request.setAttribute("url", "tranoutTaForwardURLAC.do");
        request.setAttribute("URL", "tranoutTaForwardURLAC.do");
        if(list.size()==1){
          return mapping.findForward("to_tran_print_yga");//机打
        }else{
          return mapping.findForward("to_tran_print_ygb");//清册
        }
      }
    }catch(BusinessException b){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(b.getMessage(),
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_tran_showAC");
  }
  public ActionForward referringdata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    String pkid=request.getParameter("headid");
    ItranoutBS tranoutBS = (ItranoutBS)BSUtils.getBusinessService("tranoutBS",this,mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    ActionMessages messages = null;
    String temp_P="1";//办理
    String userName = securityInfo.getUserName();
    String ip = securityInfo.getUserIp();
    String dates = securityInfo.getUserInfo().getBizDate();

    try {
      tranoutBS.referringDate(pkid, ip, userName, dates,securityInfo,temp_P);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交成功", false));
      saveErrors(request, messages);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_tran_showAC");
  }
  public ActionForward pprovaldata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {//撤销提交
    String pkid=request.getParameter("headid");
    ItranoutBS tranoutBS = (ItranoutBS)BSUtils.getBusinessService("tranoutBS",this,mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    ActionMessages messages = null;
    String temp_P="1";//办理
    String userName = securityInfo.getUserName();
    String ip = securityInfo.getUserIp();
    String dates = securityInfo.getUserInfo().getBizDate();

    try {
      tranoutBS.pprovalDate(pkid, ip, userName, dates,securityInfo,temp_P);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤销提交成功", false));
      saveErrors(request, messages);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_tran_showAC");
  }
  public ActionForward pickupdata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ItranoutBS tranoutBS = (ItranoutBS)BSUtils.getBusinessService("tranoutBS",this,mapping.getModuleConfig());
    String outOrgId=(String)request.getParameter("outid");
    SecurityInfo securityInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    ActionMessages messages = null;
    try {
      tranoutBS.pickUpData(outOrgId,securityInfo);
    } catch (Exception be) {
      messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
        .getMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_tran_showAC");
  
  }

}


















