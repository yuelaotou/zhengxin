package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

import java.io.Serializable;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountReportDTO;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;


/** 
 * @author 李鹏
 *2007-6-29
 */   
public class AdjustaccountServiceMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountServiceTaShowAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.over.adjust", "overAdjust");
    map.put("button.del.adjus", "delAdjus");  
    map.put("button.update", "update");
    map.put("button.delete", "delete");  
    map.put("button.print.machine", "prints");
    return map;
  }
  public ActionForward overAdjust(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    
    try{
      messages=new ActionMessages();
//    if(!isTokenValid(request)){
//      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要灌水！",
//          false));
//      saveErrors(request, messages);
//    }else
//      {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IdAF idaf=(IdAF)form;
      String ids=idaf.getId().toString();
     HttpSession session=request.getSession(false);
     Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
      IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
      .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
      AdjustWrongAccountHead adjustWrongAccountHead=adjustaccountBS.findOrgHAFAccountFlowById(ids);
      List empList=new ArrayList();
      Serializable id;
      String noteNum = "";
      //更改过下列四行,
//        id=adjustaccountBS.updateAdjustWrongAccountHeadState(adjustWrongAccountHead,securityInfo);//修改该头表状态为3 
      AdjustWrongAccountHead adjustWrongAccountHead1=adjustaccountBS.updateAdjustWrongAccountHeadState(adjustWrongAccountHead,securityInfo,noteNum);//修改该头表状态为3 
//        AdjustWrongAccountHead adjustWrongAccountHead1=adjustaccountBS.findAdjustWrongAccountHeadByID(id);
//        empList=adjustaccountBS.findAdjustWrongAccountTailByHead(id+"");             //返回该记录的Tail所有记录
//        adjustaccountBS.insertOrgHAFAccountFlowByWrongHAT(adjustWrongAccountHead1,empList,securityInfo);//完成所有插入工作
     //不是我更改过的.
      //        request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "3");
        
        
        String empid="";
        List printlist=new ArrayList();
        Emp emp = null;
      for(int i=0;i<empList.size();i++)
      {
        
        AdjustaccountReportDTO adjustaccountReportDTO=new AdjustaccountReportDTO();
        AdjustWrongAccountTail adjustWrongAccountTail=(AdjustWrongAccountTail)empList.get(i);

        empid=adjustWrongAccountTail.getEmpId().toString();
        adjustaccountReportDTO.setWrongdocnum(adjustWrongAccountHead1.getBizDocNum());
        adjustaccountReportDTO.setEmpid(adjustWrongAccountTail.getEmpId().toString());
        emp=adjustaccountBS.findEmpById(empid);
        adjustaccountReportDTO.setEmpname(emp.getEmpInfo().getName());
        adjustaccountReportDTO.setEmpidcard(emp.getEmpInfo().getCardNum());
        adjustaccountReportDTO.setAdjustaccount(adjustWrongAccountTail.getAdjustMoney());
        adjustaccountReportDTO.setWrongaccountdate(adjustWrongAccountTail.getSettDate());
        adjustaccountReportDTO.setBis_type(adjustWrongAccountHead1.getBizType());
        
        printlist.add(adjustaccountReportDTO);
      }
      String report=request.getParameter("report");
      request.getSession().setAttribute("URL", "adjustaccountServiceForwardURLAC.do");
      //更改过，完成后不提示打印，页面上有打印。
//      pagination.getQueryCriterions().put("adjustprint",printlist);
      request.setAttribute("PRINT","PRINT");
//        messages=new ActionMessages();
//        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("完成调整成功！",
//            false));
//        saveErrors(request, messages);
//      }
      if(report!=null&&!report.equals("")&&report.equals("print")){
        List list=adjustaccountBS.adjustWrongAccountAllByHID(ids,securityInfo);
        request.setAttribute("printlist", list);
        AdjustaccountReportDTO adjustaccountReportDTO = new  AdjustaccountReportDTO();
        if(list!=null && list.size()>0){
        adjustaccountReportDTO=(AdjustaccountReportDTO)list.get(0);
        }
        String orgId = adjustaccountReportDTO.getOrgid().toString();
        ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
        .getBusinessService("loanDocNumDesignBS", this, mapping.getModuleConfig());
        String userName="";
          try {
            String name = loanDocNumDesignBS .getNamePara();

            if (name.equals("1")) {
              userName = securityInfo.getUserName();
            } else {
              userName = securityInfo.getRealName();
            }

          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          String collectionBankId="";
          String collectionBankName="";
     
          if(orgId!=null && !orgId.equals("")){  
             collectionBankName=loanDocNumDesignBS.queryCollectionBankNameById(orgId, securityInfo);
          }
          String bizDate = securityInfo.getUserInfo().getBizDate();
          request.setAttribute("userName", userName);
          request.setAttribute("bizDate", bizDate);
          request.setAttribute("collectionBankName", collectionBankName);
          
          if(!list.isEmpty()){
            if(list.size()>1)
              return mapping.findForward("adjustaccount_list_cell");
          }
        request.setAttribute("URL","showAdjustaccountServiceAC.do");
        return mapping.findForward("adjustaccount_single_cell");
      }
    }catch(BusinessException bex){
      bex.printStackTrace();
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("完成调整失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("showAdjustaccountServiceAC");
  }

  public ActionForward delAdjus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return mapping.findForward("showAdjustaccountServiceAC");
  }
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpSession session=request.getSession(false);
    Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
    IdAF idaf=(IdAF)form;
    String ids=idaf.getId().toString();
    IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
    .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
    AdjustWrongAccountHead adjustWrongAccountHead1=adjustaccountBS.findOrgHAFAccountFlowById(ids);
    Serializable orgId=(Serializable)adjustWrongAccountHead1.getOrg().getId();
//      pagination.getQueryCriterions().put("empHAFAccountFlow.orgHAFAccountFlow.orgId", adjustWrongAccountHead1.getOrg().getId()+"");
   //更改过。
    request.getSession().setAttribute("findadjustWrongAccountHead_orgId", orgId.toString());
    request.getSession(false).setAttribute("temp_adjustWrongAccountHead_id", adjustWrongAccountHead1.getId()+"");
    request.getSession(false).setAttribute("empHAFAccountFlowOrgId", adjustWrongAccountHead1.getOrg().getId()+"");    
    request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "4");      
    return mapping.findForward("showAdjustaccountAC");
  }
  public ActionForward prints(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception { 
    HttpSession session=request.getSession(false);
    Pagination pagination = (Pagination)session.getAttribute(PAGINATION_KEY);
    IdAF idaf=(IdAF)form;
    String id=idaf.getId().toString();
    IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
    .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    request.setAttribute("PRINT","PRINT");
    List list=adjustaccountBS.adjustWrongAccountAllByHID(id,securityInfo);
    pagination.getQueryCriterions().put("adjustprint",list);
    return mapping.findForward("showAdjustaccountServiceAC");
  }
  public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{messages=new ActionMessages();
//    if(!isTokenValid(request)){
//      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要刷新！",
//          false));
//      saveErrors(request, messages);
//    }else
//    {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      IdAF idaf=(IdAF)form;
      String id=idaf.getId().toString();
      IAdjustAccountBS adjustaccountBS = (IAdjustAccountBS) BSUtils
      .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
      adjustaccountBS.deleteAdjustWrongAccountHeadAndTailByHID(id,securityInfo);
//      messages=new ActionMessages();
//      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除成功！",
//          false));
//      saveErrors(request, messages);
//    }
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("删除失败！",
          false));
      saveErrors(request, messages);
    }
    return mapping.findForward("showAdjustaccountServiceAC");
  }
 }

