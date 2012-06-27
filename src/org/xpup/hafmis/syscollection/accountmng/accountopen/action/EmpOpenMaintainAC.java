package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpChangeAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpKhCriteronsAF;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

public class EmpOpenMaintainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.exports", "exports");
    map.put("button.imports", "imports");
    map.put("button.add", "save");
    map.put("button.update", "modify");
    map.put("button.delete", "delete");
    map.put("button.deleteall", "deleteAll");
    map.put("button.back", "back");
    map.put("button.empAgent.export", "empAgentExport");
    map.put("button.empAgent.import", "empAgentImport");
    map.put("button.pickup.data", "pickupDateAll");
    map.put("button.pproval.data", "pprovalDataOrgInfo");
    map.put("button.referring.data", "referringDataOrgInfo");
    map.put("button.change.empid", "changeEmpIdInfo");
    map.put("button.print.list.open", "printlist");
    return map;
  }
  //bit add-----------------------------------
  public ActionForward printlist(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
    .getBusinessService("orgOpenAccountBS", this, mapping
        .getModuleConfig());
    ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
    .getBusinessService("loanDocNumDesignBS", this, mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    String orgId = String
        .valueOf(request.getSession().getAttribute("org.id"));
    String orgname = String.valueOf(request.getSession().getAttribute(
        "org.name"));
    List printlist=orgOpenAccountBS.queryprintlist(orgId, orgname);
    String collbank=orgOpenAccountBS.querycollbankid(new Integer(orgId), orgname);
    String userName="";
    try {
      String name = loanDocNumDesignBS.getNamePara();

//      if (name.equals("1")) {
//        userName = securityInfo.getUserName();
//      } else {
        userName = securityInfo.getRealName();
 //     }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    String bizdate=securityInfo.getUserInfo().getBizDate();
    request.setAttribute("collbank", collbank);
    request.setAttribute("printperson", userName);
    request.setAttribute("bizdate", bizdate);
    request.setAttribute("printlist", printlist);
    return mapping.findForward("printlist");
  }
  //bit add-----------------------------------
  public ActionForward exports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
      
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      String orgId = String
          .valueOf(request.getSession().getAttribute("org.id"));
      String orgname = String.valueOf(request.getSession().getAttribute(
          "org.name"));
      BigDecimal paymode = (BigDecimal) request.getSession().getAttribute(
          "org.paymode");
      List expList = new ArrayList();
      expList.add(0, BusiTools.convertSixNumber(orgId));
      expList.add(1, orgname);
      expList.add(2, paymode.toString());
      System.out.println(expList.size()+"-----------expList.size()");
      if (expList.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist", expList);
        IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
            .getBusinessService("orgOpenAccountBS", this, mapping
                .getModuleConfig());
        orgOpenAccountBS.ExpInsert(orgId,securityInfo);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=empopen_exp");
      }
      return null;
    } catch (Exception e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("employee_kh_show");
  }

  public ActionForward imports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String orgId = String
    .valueOf(request.getSession().getAttribute("org.id"));
    boolean flag = true;
    try {
       IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
       .getBusinessService("orgOpenAccountBS", this, mapping
           .getModuleConfig());
       flag =orgOpenAccountBS.validateOpenStatus(orgId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (flag) {
      return mapping.findForward("to_empopen_imports");
    }else{
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该单位已经是开户状态不能在进行导入", false));
      saveErrors(request, messages);
      return mapping.findForward("employee_kh_show");
    }

    
  }

  public ActionForward save(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    try {
      String orgId = String
          .valueOf(request.getSession().getAttribute("org.id"));
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      Org org = orgOpenAccountBS.findOPA(new Integer(orgId));
      EmpKhCriteronsAF af = new EmpKhCriteronsAF();
      Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
      af.setSexMap(sexMap);
      Map cardKindMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      af.setCardKindMap(cardKindMap);
      af.setType("1");
      request.getSession().setAttribute("openstatus",org.getOrgInfo().getOpenstatus());
      request.getSession().setAttribute("payMode", org.getPayMode());
      request.getSession().setAttribute("orgRate", org.getOrgRate());
      request.getSession().setAttribute("empRate", org.getEmpRate());
      request.getSession().setAttribute("payPrecision", org.getPayPrecision());
      BigDecimal paymode = (BigDecimal) request.getSession().getAttribute("org.paymode");
      request.getSession().setAttribute("org.paymode", paymode);
      request.setAttribute("empKhCriteronsAF", af);
      request.setAttribute("flag", "0");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("employee_open_detail");
  }

  public ActionForward modify(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      String orgId = String.valueOf(request.getSession().getAttribute("org.id"));
      Org org = orgOpenAccountBS.findOPA(new Integer(orgId));
      String id = request.getParameter("id");
      Emp emp = orgOpenAccountBS.findEmp(new Integer(id));

      EmpKhCriteronsAF af = new EmpKhCriteronsAF();

      af.setEmp(emp);
      Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
      af.setSexMap(sexMap);
      Map cardKindMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      af.setCardKindMap(cardKindMap);
      af.setType("2");
      request.getSession().setAttribute("openstatus",
          af.getEmp().getOrg().getOrgInfo().getOpenstatus());
      af.setEmprate(emp.getOrg().getEmpRate().toString());
      af.setOrgrate(emp.getOrg().getOrgRate().toString());
      af.setPayPrecision(emp.getOrg().getPayPrecision().toString());
      af.getEmp().setEmpId(emp.getEmpId());
      
      BigDecimal paymode = (BigDecimal) request.getSession().getAttribute("org.paymode");
      // request.getSession().setAttribute("payMode", paymode);
      
      // 将缴率传入jsp
      request.getSession().setAttribute("openstatus",org.getOrgInfo().getOpenstatus());
      request.getSession().setAttribute("payMode", org.getPayMode());
      request.getSession().setAttribute("orgRate", org.getOrgRate());
      request.getSession().setAttribute("empRate", org.getEmpRate());
      request.getSession().setAttribute("payPrecision", org.getPayPrecision());
      request.setAttribute("empKhCriteronsAF", af);
      request.setAttribute("flag", "0");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("employee_open_detail");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String empId = request.getParameter("id");
    
      try {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
        IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
        .getBusinessService("orgOpenAccountBS", this, mapping.getModuleConfig());
        // 判断此单位是否为开户状态
        String orgId = (String)request.getSession().getAttribute("org.id").toString();
        // 判断单位中是否存在该职工
        if (orgOpenAccountBS.validateOpenStatus(orgId)) {
          if (orgOpenAccountBS.queryEmpCount(orgId, empId)) {
            try {
              orgOpenAccountBS.removeEmp(empId,securityInfo,orgId);
              ActionMessages messages = new ActionMessages();
              messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
                  false));
              saveErrors(request, messages);
            } catch (BusinessException bex) {
              ActionMessages messages = new ActionMessages();
              messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
                  .getLocalizedMessage().toString(), false));
              saveErrors(request, messages);
            }
          }
        }else{
          ActionMessages messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该单位已经是开户状态不能在删除员工!", false));
          saveErrors(request, messages);
        }
      } 
      catch (Exception e) {
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！",
            false));
        saveErrors(request, messages);
      }
    
    return mapping.findForward("employee_kh_show");
  }

  public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
      String orgId = String.valueOf(request.getSession().getAttribute("org.id"));
      try {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
        IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
        .getBusinessService("orgOpenAccountBS", this, mapping.getModuleConfig());
        if (orgOpenAccountBS.validateOpenStatus(orgId)) {
          boolean flag = orgOpenAccountBS.removeEmpAll(orgId,securityInfo);
          if (flag) {
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
                false));
            saveErrors(request, messages);
          }else{
            ActionMessages messages = new ActionMessages();
            messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！",
                false));
            saveErrors(request, messages);
          }
        }else{
          ActionMessages messages = new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该单位已经是开户状态不能在删除员工!", false));
          saveErrors(request, messages);
        }
      } catch (BusinessException bex) {
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage().toString(), false));
        saveErrors(request, messages);
      }catch(Exception e){
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！",
            false));
        saveErrors(request, messages);
      }
    
    return mapping.findForward("employee_kh_show");
  }

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("to_start_page");
  }
  
  /**
   * 职工代扣导出
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward empAgentExport(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try {
      // 得到单位编号
      String orgId = String.valueOf(request.getSession().getAttribute("org.id"));
      // 单位名称
      String orgName = String.valueOf(request.getSession().getAttribute("org.name"));
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      List expList = new ArrayList();
      expList.add(0,orgId);
      expList.add(1,orgName);
      expList.add(2,orgOpenAccountBS.findEmpAgentList(orgId));
      if (expList.size() > 0) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出成功！",
            false));
        saveErrors(request, messages);
        request.getSession().setAttribute("explist", expList);
        response.sendRedirect(request.getContextPath()
            + "/ExportServlet?ISCANSHU=false&EXP_NAME=empagent_exp");
      } else {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有数据！",
            false));
        saveErrors(request, messages);
      }
      return null;
    } catch (BusinessException bex) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("导出失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("organization_open_show");
  }
  /**
   * 职工代扣导入
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward empAgentImport(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("to_empagent_imports");
  }
  public ActionForward pickupDateAll(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response){
    saveToken(request);
    ActionMessages messages = null;
    try {
      String orgId = String
          .valueOf(request.getSession().getAttribute("org.id"));
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      orgOpenAccountBS.pickupDateAll(orgId, securityInfo);
    }catch(BusinessException bex){
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("employee_kh_show");
  }
  public ActionForward pprovalDataOrgInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    String orgId = String
    .valueOf(request.getSession().getAttribute("org.id"));
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
      .getBusinessService("orgOpenAccountBS", this, mapping
          .getModuleConfig());
      orgOpenAccountBS.pprovalDataOrgInfo(orgId, securityInfo);
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
    
    return mapping.findForward("organization_open_show");
  }
  public ActionForward referringDataOrgInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    String orgId = String
    .valueOf(request.getSession().getAttribute("org.id"));
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
      .getBusinessService("orgOpenAccountBS", this, mapping
          .getModuleConfig());
      orgOpenAccountBS.referringDataOrgInfo(orgId, securityInfo);
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
    
    return mapping.findForward("organization_open_show");
  }
  public ActionForward changeEmpIdInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    String orgId = String
    .valueOf(request.getSession().getAttribute("org.id"));
    String empId = request.getParameter("id");
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
      .getBusinessService("orgOpenAccountBS", this, mapping
          .getModuleConfig());
     EmpChangeAF empChangeAF=orgOpenAccountBS.changeEmpIdInfo(orgId, securityInfo,empId);
    if(!empChangeAF.getList().isEmpty()){
      request.setAttribute("empChangeAF", empChangeAF);
      return mapping.findForward("to_empchange_empId");
    }
    }catch(BusinessException bex){
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return mapping.findForward("employee_kh_show");
  }
}
