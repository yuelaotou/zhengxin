package org.xpup.hafmis.syscollection.chgbiz.chgperson.action;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface.IChgpersonDoBS;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;

public class ChgpersonFindEmpAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        
        try {
          String empID = request.getParameter("empID");
          String chgMap_1=request.getParameter("chgType");//变更类型
          
          HttpSession session = request.getSession();
          String orgID=(String)session.getAttribute("orgID");//单位编号
          String chgDate=(String)session.getAttribute("chgDate");//变更年月
          
          IChgpersonDoBS chgpersonDoBS = (IChgpersonDoBS) BSUtils
          .getBusinessService("chgpersonDoBS", this, mapping.getModuleConfig());

          
          //查询 :当人员变更类型为启封或封存时，进行数据校验

          chgpersonDoBS.getChgPersonTail_WL(orgID, empID, chgDate, chgMap_1, null, null, null,null, null,null);
        
          //查询出可以办理变更的职工信息
          Emp returnEmp = chgpersonDoBS.getEmpMessage(orgID, empID);
          Integer id = new Integer(returnEmp.getEmpId().toString());
          String name = returnEmp.getEmpInfo().getName();
          String cardNum = returnEmp.getEmpInfo().getCardNum();

          String payStatus = BusiTools.getBusiValue(Integer
              .parseInt(returnEmp.getPayStatus().toString()),
              BusiConst.OLDPAYMENTSTATE);
          
          String payMode = BusiTools.getBusiValue(Integer
              .parseInt(returnEmp.getOrg().getPayMode().toString()),
              BusiConst.ORGPAYWAY);
          String salaryBase = returnEmp.getSalaryBase().toString();
          String orgPay = returnEmp.getOrgPay().toString();
          String empPay = returnEmp.getEmpPay().toString();
          String sumPay = (new BigDecimal(orgPay).add(new BigDecimal(empPay))).toString();  
          
          //本年余额和往年余额 是否为0
          String status = "0";
          String temp_status = chgpersonDoBS.getChgpersonStatus(id.toString(), orgID);
          if(temp_status.equals("3") || temp_status.equals("4")){
            boolean temp = chgpersonDoBS.isChgperson(id.toString(), orgID);
            if(!temp){
              status = "1";
            }
          }
          
          String text=null;
          text="displays('"+id+"','"+name+"','"+cardNum+"','"+payStatus+"','"+payMode+"','"+salaryBase+"','"+orgPay+"','"+empPay+"','"+sumPay+"','"+status+"')";
          response.getWriter().write(text); 
          response.getWriter().close();
          
          
        }catch(BusinessException be){
          
          String text="reportErrors('"+be.getLocalizedMessage()+"')";
          response.getWriter().write(text);
          response.getWriter().close();
          
          
        }
        
        return null; 
  }
}