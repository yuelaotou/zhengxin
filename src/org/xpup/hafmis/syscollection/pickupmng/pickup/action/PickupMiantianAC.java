/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.syscollection.pickupmng.pickup.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcifs.smb.PictureUpload;

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
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickConvertAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupAddAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupMaintainAF;

public class PickupMiantianAC extends LookupDispatchAction {
  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;// TODO

    request.getSession().removeAttribute("special");
    request.getSession().removeAttribute("distoryPick");
    try {
      PickConvertAF convert = new PickConvertAF();
      convert.setPickupType(BusiTools.listBusiProperty(BusiConst.PICKUPTYPE));
      convert.setSomeList(BusiTools.listBusiProperty(BusiConst.SOMEPICK));
      convert.setDistoryList(BusiTools.listBusiProperty(BusiConst.DISTROYPICK));
      // 这个地方不能用R/只能用S
      request.getSession().setAttribute("pickup", convert);
      request.getSession().setAttribute("somePick", "some");
    } catch (Exception s) {
      // s.printStackTrace();
    }
    request.getSession().setAttribute("noteNumber",
        pickupMaintainAF.getNoteNumber());
    if (pickupMaintainAF.getOther_card_num() == null
        || pickupMaintainAF.getOther_card_num() == "") {
      request.getSession().setAttribute("other_card_num", "");
    } else {
      request.getSession().setAttribute("other_card_num",
          pickupMaintainAF.getOther_card_num());
    }

    request.setAttribute("result", new PickupAddAF());
    request.getSession().setAttribute("addpage", "three");

    return mapping.findForward("add");
  }

  public ActionForward exports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    ActionMessages messages = new ActionMessages();
    try {
      // System.out.println("导出");
      PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      Integer orgId = (Integer) request.getSession().getAttribute("orgId");
      // System.out.println("expoerts:"+orgId);
      // 获得IP...就是这样的固定写法
      SecurityInfo sInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
      String ip = sInfo.getUserIp();
      List list = pbs.getExportData(orgId.intValue(), ip, sInfo.getUserName());
      /*
       * if(list!=null && list.isEmpty()){//当操作员点导出的时候,导出没有数据('但是不可能没有数据
       * 王姐说的..') messages.add(ActionMessages.GLOBAL_MESSAGE,new
       * ActionMessage("数据库里没有数据可以导出",false)); saveErrors(request, messages);
       * PickupGetCompanyIdAF af = new PickupGetCompanyIdAF();
       * request.setAttribute("af", af); return new
       * ActionForward("/pickup_transactionlist.jsp"); }
       */
      request.getSession().setAttribute("explist", list);// 尾表的值......因为根据尾表可以导航到头表
      // EXP_NAME的值就是那个xml文件名的值
      response.sendRedirect(request.getContextPath()
          + "/ExportServlet?ISCANSHU=false&EXP_NAME=pickup_exp");
      return null;// 必须写..因为这个是发送的...
    } catch (Exception s) {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "出现错误,导出失败", false));
    }
    return null;
  }

  public ActionForward imports(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // System.out.println("导入");
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;
    System.out.println("pickupMaintainAF.getOther_card_num()="
        + pickupMaintainAF.getOther_card_num());
    request.getSession().setAttribute("noteNumber",
        pickupMaintainAF.getNoteNumber());
    if (pickupMaintainAF.getOther_card_num() == null
        || pickupMaintainAF.getOther_card_num() == "") {
      request.getSession().setAttribute("other_card_num", "");
    } else {
      request.getSession().setAttribute("other_card_num",
          pickupMaintainAF.getOther_card_num());
    }
    return new ActionForward("/pick_import.jsp");
  }

  public ActionForward delete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;// TODO
    // Auto-generated
    // method stubt
    ActionMessages messages = null;
    try {
      Pagination paginaction = (Pagination) request.getSession().getAttribute(
          PickupShowAC.PAGINACTION_KEY);
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      // String empId = request.getParameter("eemmppid");//获得要删除的职工id;
      String empId = request.getParameter("eeemmmpppid");// 获得要删除的职工id;
      String headId = pickupMaintainAF.getTailHeadId();// 获得此尾表对应头表的id
      // 此id一定存在.. 这个list集合里面最少有一条记录
      List list = pbs.findDataByHeadId(Integer.parseInt(headId));
      String result = "删除一个表";
      if (list.size() == 1) {// 如果只剩下一条记录的时候
        result = "删除头尾表";
        paginaction.setNrOfElements(0);
      }
      SecurityInfo sInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
      String ip = sInfo.getUserIp();
      pbs.deleteEmployee(Integer.parseInt(headId), Integer.parseInt(empId),
          result, ip, sInfo.getUserName(), sInfo);
    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward allDelete(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;// TODO
    // Auto-generated
    // method stub
    ActionMessages messages = null;
    try {
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      String headId = pickupMaintainAF.getTailHeadId();
      SecurityInfo sInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
      String ip = sInfo.getUserIp();
      pbs.allDelete(Integer.parseInt(headId), ip, sInfo.getUserName(), sInfo);
      Pagination paginaction = (Pagination) request.getSession().getAttribute(
          PickupShowAC.PAGINACTION_KEY);
      paginaction.setNrOfElements(0);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception s) {
      PickTail t = null;
      s.printStackTrace();
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward overPickup(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;
    String notenum = request.getParameter("notenum");
    ActionMessages messages = new ActionMessages();
    try {
      // System.out.println("经过完成提取的方法................");
      String report = request.getParameter("report");
      Integer orgId = (Integer) request.getSession().getAttribute("orgId");
      // System.out.println("report->"+report);
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      String headId = null;
      try {
        headId = pbs.findPickHeadSOneByOrgId(orgId.intValue());// 这句话必须放在overPick这个方法的上边..不然完成提取以后就有可能把状态为1改成状态为3的了..那么在数据库中测不到了..会出现null指针异常
      } catch (Exception e) {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "不能完成提取，该笔业务已经完成提取过", false));
        saveErrors(request, messages);
        return new ActionForward("/pickupShowAC.do");
      }
      SecurityInfo s = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
      pbs.overPick(orgId.intValue(), s.getUserIp(), s.getUserName(), s
          .getUserInfo().getBizDate(), notenum, s);

      Pagination paginaction = (Pagination) request.getSession().getAttribute(
          PickupShowAC.PAGINACTION_KEY);
      paginaction.setNrOfElements(0);

      // if(report.equals("print")){
      // // System.out.println("...打印..");
      // // System.out.println("head--->"+headId);
      // NameAF nameAF = new NameAF();
      // NameAF othernameAF = new NameAF();
      // nameAF = pbs.findName(orgId.toString());
      // // othernameAF = pbs.findpickTail(orgId.toString());
      //  
      // List list = pbs.getPrintAllEmployeeList(headId);//获得要打印的集合..
      // // System.out.println("打印的集合长度是......."+list.size());
      //
      // PickTail pick=null;
      // if(list.size()>0){
      // pick = (PickTail) list.get(0);
      // }
      // ApplyBookDTO apply = new ApplyBookDTO();//获取凭证..
      // apply.setSOrgName(nameAF.getOrgName());//收款单位名称
      // apply.setSOrgNumber(nameAF.getPayBankNum());//收款单位账号(发薪银行)
      // apply.setSOrgBank(nameAF.getPayBank());//收款单位银行
      // apply.setFOrgName(nameAF.getOrganizatinUnitName());//付款单位名称(中心)
      // apply.setFOrgNumber(" ");//付款单位账号(中心)
      // apply.setFOrgBank(nameAF.getCentercollBankName());//付款单位(中心归集银行)
      // apply.setPickBalance(pick.getPickHead().getPickBalance().add(pick.getPickHead().getDistroyInterest()).divide(new
      // BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));//必须转换成两位小数
      // request.setAttribute("apply", apply);
      // request.setAttribute("employee", list);
      // request.setAttribute("PRINT", "pickupShowAC.do");
      //          
      // // wuht
      //
      //          
      // 
      //      
      //     
      // ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
      // .getBusinessService("loanDocNumDesignBS", this,
      // mapping.getModuleConfig());
      // String userName="";
      //        
      // SecurityInfo
      // securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      //          
      // try {
      // String name = loanDocNumDesignBS .getNamePara();
      //
      // if (name.equals("1")) {
      // userName = securityInfo.getUserName();
      // } else {
      // userName = securityInfo.getRealName();
      // }
      //
      // } catch (Exception e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
      // String collectionBankName="";
      //       
      // if(orgId!=null && !orgId.equals("")){
      // collectionBankName=pbs.queryCollectionBankNameById(orgId.toString(),
      // securityInfo);
      // }
      // String bizDate = securityInfo.getUserInfo().getBizDate();
      // request.setAttribute("userName", userName);
      // request.setAttribute("bizDate", bizDate);
      // request.setAttribute("collectionBankName", collectionBankName);
      // //wuht
      //          
      //          
      // // request.getSession().removeAttribute("orgidCollnbank");
      // // request.getSession().setAttribute("orgidCollnbank",
      // orgId.toString());
      // // System.out.println("-----------++++++++++++++++++++++++++++-----");
      // //获得打印凭证
      // return new ActionForward("/printDocAndList.jsp");
      // }

    } catch (Exception s) {
      s.printStackTrace();
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward referringdata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;
    String headId = pickupMaintainAF.getTailHeadId();
    IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
        mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ActionMessages messages = null;
    String temp_p = "1";// 办理
    try {
      pbs.referringData(headId, securityInfo, temp_p);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("提交成功",
          false));
      saveErrors(request, messages);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward pprovaldata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {// 撤销提交
    PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;
    String headId = pickupMaintainAF.getTailHeadId();
    IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
        mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ActionMessages messages = null;
    String temp_p = "1";// 办理
    try {
      pbs.pprovalData(headId, securityInfo, temp_p);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("撤销提交成功",
          false));
      saveErrors(request, messages);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward pickupdata(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    String headId = (String) request.getParameter("id");
    IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
        mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    ActionMessages messages = null;
    try {
      pbs.pickUpData(headId, securityInfo);
    } catch (BusinessException be) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return new ActionForward("/pickupShowAC.do");
  }

  public ActionForward scan(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PictureUpload pu = new PictureUpload();
    String path;
    ActionMessages messages = null;

    try {
      PickupMaintainAF pickupMaintainAF = (PickupMaintainAF) form;// TODO

      String headId = pickupMaintainAF.getTailHeadId();

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String pzempid = (String) request.getParameter("eeemmmpppid");// 获得要删除的职工id;
      String serverPath = BusiConst.UPLOAD_SERVER_PATH;
      path = pu.upload(securityInfo.getUserInfo().getUserIp(), "picture",
          serverPath);
      pu.delete(securityInfo.getUserInfo().getUserIp(), "picture");
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      pbs.updatePickHead(headId, pzempid, path);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扫描完成",
          false));
      saveErrors(request, messages);
    } catch (IOException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扫描失败",
          false));
      saveErrors(request, messages);
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return new ActionForward("/pickupShowAC.do");
  }

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.imports", "imports");
    map.put("button.exports", "exports");
    map.put("button.add", "add");
    map.put("button.delete", "delete");
    map.put("button.deleteall", "allDelete");
    map.put("button.over.pickup", "overPickup");
    map.put("button.referring.data", "referringdata");
    map.put("button.pproval.data", "pprovaldata");
    map.put("button.pickup.data", "pickupdata");
    map.put("button.scan", "scan");
    return map;
  }
}