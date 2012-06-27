package org.xpup.hafmis.syscollection.pickupmng.pickup.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupMaintainAF;

public class PickupExportTaAC extends Action {
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = new ActionMessages();
    Pagination pagination=new Pagination();
    try{
//      System.out.println("导出");
     
      IPickupBS pbs = (IPickupBS)BSUtils.getBusinessService("pickupBS",this,mapping.getModuleConfig());
      Integer orgId = (Integer)request.getSession().getAttribute("orgId");
//      System.out.println("expoerts:"+orgId);
      //获得IP...就是这样的固定写法
      SecurityInfo sInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String ip = sInfo.getUserIp();
      String empIds=request.getParameter("empIds");
      String empNames=request.getParameter("empNames");
      String cardNums=request.getParameter("cardNums");
      String cardKinds=request.getParameter("cardKinds");
      String tatol=request.getParameter("tatol");
      int finalTatol=(Integer.parseInt(tatol)-1);
      System.out.println(finalTatol);
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
      
      
      List list = pbs.getExportData(orgId.intValue(),ip,sInfo.getUserName(), pagination);
    
      /*
       *if(list!=null && list.isEmpty()){//当操作员点导出的时候,导出没有数据('但是不可能没有数据　王姐说的..')
       *   messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("数据库里没有数据可以导出",false));
       *   saveErrors(request, messages);
       *   PickupGetCompanyIdAF af = new PickupGetCompanyIdAF();
       *   request.setAttribute("af", af);
       *   return new ActionForward("/pickup_transactionlist.jsp");
        *}
       */
      request.getSession().setAttribute("explist",list);//尾表的值......因为根据尾表可以导航到头表
      //EXP_NAME的值就是那个xml文件名的值
      response.sendRedirect(request.getContextPath()+"/ExportServlet?ISCANSHU=false&EXP_NAME=pickup_exp");
      return null;//必须写..因为这个是发送的...
    }catch(Exception s){
      messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("出现错误,导出失败",false));
    }
        
    return mapping.findForward("to_pickup_showAC");
  
  }
   

}
