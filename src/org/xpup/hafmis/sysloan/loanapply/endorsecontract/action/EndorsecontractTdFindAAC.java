package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class EndorsecontractTdFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    response.setHeader("Cache-Control", "no-cache");
    response.setContentType("text/html;charset=UTF-8");
   
    ActionMessages messages = null;
    List loanBankNameList = new ArrayList();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
        .getAttribute("SecurityInfo");
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    String text = null;
    String message = "";
    String empId = "";//职工编号
    String empName = "";//职工姓名
    String cardKind = "";//证件类型
    String cardNum = "";//证件号码
    String sex = "";//性别
    String birthday = "";//出生日期
    String salary = "";//月工资额
    String monthPay = "";//月缴存额
    String balance = "";//账户余额
    String empSt = "";//账户状态
    String tel = "";//固定电话
    String mobile = "";//行动电话
    String homeTel = "";//家庭电话
    String homeAddr = "";//家庭住址
    String homeMai = "";//家庭邮编
    String orgId = "";//单位编号
    String orgName = "";//单位名称
    String orgAddr = "";//单位地址
    String orgTel = "";//单位电话
    String orgMail = "";//单位邮政编号
    String writeType = "";
    Userslogincollbank userslogincollbank = null;
    PrintWriter out = null;
    try {
      String empid = (String )request.getParameter("empId");//职工编号
      String orgid = request.getParameter("orgId");
      IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
          .getBusinessService("endorsecontractBS", this, mapping
              .getModuleConfig());

      //____________________________________________________________
      String paginationKey = getPaginationKey();
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      endorsecontractTdAF = endorsecontractBS.queryAssurerListByEmpId(empid,orgid, pagination, securityInfo, request);
       empId = endorsecontractTdAF.getEmpId();//职工编号
       empName = endorsecontractTdAF.getEmpName();//职工姓名
       cardKind = endorsecontractTdAF.getCardKind();//证件类型
       cardNum = endorsecontractTdAF.getCardNum();//证件号码
       sex = endorsecontractTdAF.getSex();//性别
       birthday = endorsecontractTdAF.getBirthday();//出生日期
       salary = endorsecontractTdAF.getSalary();//月工资额
       monthPay = endorsecontractTdAF.getMonthPay();//月缴存额
       balance = endorsecontractTdAF.getBalance();//账户余额
       empSt = endorsecontractTdAF.getEmpSt();//账户状态
       tel = endorsecontractTdAF.getTel();//固定电话
       mobile = endorsecontractTdAF.getMobile();//行动电话
       homeTel = endorsecontractTdAF.getHomeTel();//家庭电话
       homeAddr = endorsecontractTdAF.getHomeAddr();//家庭住址
       homeMai = endorsecontractTdAF.getHomeMai();//家庭邮编
       orgId = endorsecontractTdAF.getOrgId();//单位编号
       orgName = endorsecontractTdAF.getOrgName();//单位名称
       orgAddr = endorsecontractTdAF.getOrgAddr();//单位地址
       orgTel = endorsecontractTdAF.getOrgTel();//单位电话
       orgMail = endorsecontractTdAF.getOrgMail();//单位邮政编号
     
    } catch (BusinessException bex) {
      message = bex.getMessage();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString()));
      saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    text = "display('" + empId + "','" + empName + "','"
        + cardKind + "','" + cardNum + "'," + "'" + sex
        + "','" + birthday + "','" + salary + "','" + monthPay + "','"
        + balance + "'," + "'" + empSt + "','" + tel
        + "','" + mobile + "','" + homeTel + "','"
        + homeAddr + "','" + homeMai +"','" +orgId+"','" +orgName+"','" +orgAddr+"','" +orgTel+"','" +orgMail+"'";
    text += ",'" + message + "');";
    
//    text = "display('" + empId + "','" + empName +"'";
//text += ",'" + message + "');";
//    out.flush();
//    out.close();
    response.getWriter().write(text);
    response.getWriter().close();
    
    return null;
  }

  protected String getPaginationKey() {
    return EndorsecontractTaShowAC.PAGINATION_KEY;
  }
}
