package org.xpup.hafmis.syscollection.tranmng.tranout.action;

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
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbAF;

public class TranExportTaAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
   
//    String status = request.getParameter("payStatus");
//    TranTbAF tranTbAF = (TranTbAF) form;
//    
//    
//    
//    
//    HashMap criterions = makeCriterionsMap(tranTbAF);
//    Pagination  pagination = new Pagination(0, 10, 1, "tot.id", "DESC", criterions);
//    
////    Pagination pagination = (Pagination) request.getSession().getAttribute(Tran_showFindTbAC.PAGINATION_KEY);
//    String paginationKey = getPaginationKey();
//
//    request.getSession().setAttribute(paginationKey, pagination);
// 
//    // tranTbAF.reset(mapping, request);
//    return mapping.findForward("to_showFindAC");
    
    
    
    
    
    
    
    
    ActionMessages messages =null;
   
    Pagination pagination=new Pagination();
    try{
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip=securityInfo.getUserInfo().getUserIp();
      String name=securityInfo.getUserInfo().getUsername();
      String outOrgId = request.getParameter("outOrgId");
      String outOrgname = request.getParameter("outOrgname");
      String inOrgId = request.getParameter("inOrgId");
      String inOrgName = request.getParameter("inOrgName");
      String noteNum = request.getParameter("noteNum");
      
     // outOrgname = java.net.URLDecoder.decode(outOrgname, "UTF-8");
      // inOrgName = java.net.URLDecoder.decode(inOrgName, "UTF-8");
      String empIds=request.getParameter("empIds");
      String empNames=request.getParameter("empNames");
      String cardNums=request.getParameter("cardNums");
      String cardKinds=request.getParameter("cardKinds");
      String tatol=request.getParameter("tatol");
      int finalTatol=(Integer.parseInt(tatol)-1);
      String order[]=new String [finalTatol] ;
      
      if((Integer.parseInt(empIds))!=0){
        order[(Integer.parseInt(empIds)-1)]="emp.empId";
      }
      if((Integer.parseInt(empNames))!=0){
        order[(Integer.parseInt(empNames)-1)]="emp.empInfo.name";
      } 
      if((Integer.parseInt(cardNums))!=0){
        order[(Integer.parseInt(cardNums)-1)]="emp.empInfo.cardNum";
      }   
      if((Integer.parseInt(cardKinds))!=0){
        order[(Integer.parseInt(cardKinds)-1)]="emp.empInfo.cardKind";
      }   
      pagination.getQueryCriterions().put("orderArray", order);
      
      
      pagination.getQueryCriterions().put("orgOutid", outOrgId);
      pagination.getQueryCriterions().put("orgOutName", outOrgname);
      pagination.getQueryCriterions().put("orgInId", inOrgId);
      pagination.getQueryCriterions().put("orgInName", inOrgName);
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

  
  
  protected String getPaginationKey() {
    return Tran_showFindTbAC.PAGINATION_KEY;
  }

  protected HashMap makeCriterionsMap(TranTbAF form) {
    HashMap m = new HashMap();
    
    String orgOutid = form.getOutOrgId();
   
    if (orgOutid != null && !"".equals(orgOutid)) {
      m.put("orgOutid", orgOutid);
    }

    String orgOutName = form.getOutOrgname();
    if (orgOutName != null && orgOutName.length() > 0) {
      m.put("orgOutName", form.getOutOrgname());
    }

    String orgInid = form.getInOrgId(); // 转入单位编号
    if (orgInid != null && orgInid.length() > 0) {
      m.put("orgInid", orgInid);
    }
    String orgInName = form.getInOrgName();// 转入单位名称
    if (orgInName != null && orgInName.length() > 0) {
      m.put("orgInName", form.getInOrgName());
    }
    String Note_num = form.getNote_num();
    if (Note_num != null && Note_num.length() > 0) {
      m.put("Note_num", form.getNote_num());// 票据编号
    }
    String Doc_num = form.getDoc_num();
    if (Doc_num != null && Doc_num.length() > 0) {
      m.put("Doc_num", Doc_num);
    }
    String Dates = form.getDates();// 凭证
    if (Dates != null && Dates.length() > 0) {
      m.put("Dates", Dates);
    }
    String status = form.getPayStatus();
    if(status !=null && status.length() > 0) {
      m.put("status", status);
    }
    return m;
  }


}
