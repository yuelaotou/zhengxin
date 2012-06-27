package org.xpup.hafmis.syscollection.pickupmng.pickup.action;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;

import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;

import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.ApplyBookDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupGetCompanyIdAF;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;
public class PrintEmployeeAC extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//    System.out.println("经过打印的jsp");
    IdAF idAF = (IdAF)form;
    String pzempid="";
    try{
//      System.out.println("------------------>"+"开始打印喽。。。。。。。");
      IPickupBS pbs = (IPickupBS)BSUtils.getBusinessService("pickupBS",this,mapping.getModuleConfig());
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils.getBusinessService("loanDocNumDesignBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      PickupGetCompanyIdAF af = (PickupGetCompanyIdAF)request.getSession().getAttribute("orgList");
      pzempid = request.getParameter("empid");
      ApplyBookDTO dto = (ApplyBookDTO)request.getSession().getAttribute("applys");
      List list = (List)request.getSession().getAttribute("employees");
      Date bizdate=BusiTools.stringToUDate(securityInfo.getUserInfo().getBizDate(), "yyyyMMdd");
      String date = BusiTools.dateToString(bizdate, "yyyy-MM-dd");
      
      List print = pbs.getPrintAllEmployeeList(af.getId());//获得打印的集合
      af.setList(print);//这个List里面一定有数据....
//      System.out.println("单位编号-->"+af.getId()+",此单位下一共有"+print.size()+"条记录");
      

      String userName="";
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
      PickTail pick=null;
      if(list.size()>0){
       pick = (PickTail) list.get(0);
      }
     // dto.setDocnum(pick.getPickHead().getDocNum());
      dto.setOperater(userName);
      dto.setEmpid(pzempid);
      request.setAttribute("apply", dto);
      request.setAttribute("employee",list);
      request.setAttribute("date", date);
      request.setAttribute("PRINT", "javascript:history.back();");

    }catch(Exception s){
      s.printStackTrace();
    }
    if(pzempid!=null&&!pzempid.equals("")){
     return new ActionForward("/printDoc.jsp"); 
    }else{
     return new ActionForward("/emplist_mx_cell.jsp");
    }
  }
}