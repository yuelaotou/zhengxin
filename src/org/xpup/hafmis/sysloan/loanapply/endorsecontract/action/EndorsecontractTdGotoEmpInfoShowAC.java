package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.bsinterface.IEmpAccountBS;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.form.EmpAccountAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
/**
 * 
 * @author yuqf
 *
 */
public class EndorsecontractTdGotoEmpInfoShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action.ShowEmpAccountListAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    try{
    String id = request.getParameter("id");
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
     IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
    .getBusinessService("endorsecontractBS", this, mapping
        .getModuleConfig());
     IEmpAccountBS empAccountBS = (IEmpAccountBS) BSUtils.getBusinessService("empAccountBS",
         this, mapping.getModuleConfig());
     String empId = endorsecontractBS.selectAssurerEmpIdById(id, securityInfo);
     HashMap criterions = new HashMap();
     String lastDate = securityInfo.getUserInfo().getBizDate();// 会计日期
     int tempDate = Integer.parseInt(lastDate.substring(0, 4))-1;
     String startDate = new Integer(tempDate).toString()+lastDate.substring(4, 8);

     if (!(startDate == null || "".equals(startDate))) {
       criterions.put("startDate", startDate);
     }
     if (!(lastDate == null || lastDate.length() == 0))
       criterions.put("lastDate", lastDate);
     if (!(empId == null || empId.length() == 0))
       criterions.put("empIdaa102", empId);
     Pagination pagination = new Pagination(0, 10, 1, "empHAFAccountFlow.id", "ASC",
         criterions);
     request.getSession().setAttribute(PAGINATION_KEY, pagination);
     
     List list=empAccountBS.findEmpAccountList_sy(pagination,securityInfo);
     EmpAccountAF empAccountAF=new EmpAccountAF();
     //显示本期发生金额
     String temp_credit=(String) pagination.getQueryCriterions().get("temp_credit");
     empAccountAF.setTemp_credit(temp_credit);
     //显示本期发生金额
     String temp_debit=(String) pagination.getQueryCriterions().get("temp_debit");
     empAccountAF.setTemp_debit(temp_debit);
     //显示本期利息
     String temp1_interest=(String) pagination.getQueryCriterions().get("temp1_interest");
     empAccountAF.setCurInterest(temp1_interest);
     empAccountAF.setList(list);
     //打印准备数据
     List printList=(List) pagination.getQueryCriterions().get("printList");
     request.getSession().setAttribute("printList", printList);
     request.setAttribute("empAccountAF", empAccountAF);
     }catch(Exception e){
       e.printStackTrace();
     }
    return mapping.findForward("to_empAccount_list");
  }

}
