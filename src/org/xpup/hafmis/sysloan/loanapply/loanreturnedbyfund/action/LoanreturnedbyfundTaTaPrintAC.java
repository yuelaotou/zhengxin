package org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.bsinterface.ILoanreturnedbyfundBS;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.EmpinfoDto;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.form.LoanreturnedbyfundTaAF;
 
public class LoanreturnedbyfundTaTaPrintAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    LoanreturnedbyfundTaAF loanreturnedbyfundTaAF = new LoanreturnedbyfundTaAF();
    List list =new ArrayList();
    List list_s =new ArrayList();
    ILoanreturnedbyfundBS loanreturnedbyfundBS = (ILoanreturnedbyfundBS) BSUtils.getBusinessService(
        "loanreturnedbyfundBS", this, mapping.getModuleConfig());
    String contract_id =(String)request.getSession().getAttribute("contract_id");
    String Start =(String)request.getSession().getAttribute("Status");

    try {
      list=  loanreturnedbyfundBS.get_loanbyfund_printlist(contract_id,Start);
      if(list.size()>0){
        EmpinfoDto dto= new EmpinfoDto();
        dto=(EmpinfoDto)list.get(0);
        loanreturnedbyfundTaAF.setBorrow_empid(dto.getEmpid());
        loanreturnedbyfundTaAF.setBorrow_orgid(dto.getOrgid());
        loanreturnedbyfundTaAF.setBorrowerName(dto.getEmp_name());
        loanreturnedbyfundTaAF.setBorrower_cardNum(dto.getEmp_card_num());
        
        String a=loanreturnedbyfundBS.get_print_money(dto.getOrgid(),dto.getEmpid());
        loanreturnedbyfundTaAF.setBorrowerMaxMoney(a);
        loanreturnedbyfundTaAF.setXieYinum(dto.getXieyi_id());
        loanreturnedbyfundTaAF.setDate_stop(dto.getDate_stop());
        loanreturnedbyfundTaAF.setBizTime(dto.getDate());
        
        if(new BigDecimal(loanreturnedbyfundTaAF.getBorrowerMaxMoney()).intValue()<0){
          loanreturnedbyfundTaAF.setBorrowerMaxMoney("0");
        }
      }else{
        EmpinfoDto dto_yg = loanreturnedbyfundBS.findBorrowerInfoByContractId(contract_id);
        loanreturnedbyfundTaAF.setBorrowerName(dto_yg.getEmp_name());
        loanreturnedbyfundTaAF.setBorrower_cardNum(dto_yg.getEmp_card_num());
        request.setAttribute("noborrower", "noborrower");
      }
      
      list_s=  loanreturnedbyfundBS.get_loanbyfund_printlist_s(contract_id,Start);
      if(list_s.size()>0){
        for(int i=0;i<list_s.size();i++){
          EmpinfoDto dto2= new EmpinfoDto();
          dto2=(EmpinfoDto)list_s.get(i);
          if(dto2.getPl400_seq().equals("1")){//配偶
            loanreturnedbyfundTaAF.setBorrow_s_empid(dto2.getEmpid_s());
            loanreturnedbyfundTaAF.setBorrow_s_orgid(dto2.getOrgid_s());
            loanreturnedbyfundTaAF.setName(dto2.getEmp_name_s());
            loanreturnedbyfundTaAF.setCardNum(dto2.getEmp_card_num_s());
            
            String a=loanreturnedbyfundBS.get_print_money(dto2.getOrgid_s(),dto2.getEmpid_s());
            loanreturnedbyfundTaAF.setAssiMaxMoney(a);
            loanreturnedbyfundTaAF.setXieYinum(dto2.getXieyi_id());
            loanreturnedbyfundTaAF.setDate_stop(dto2.getDate_stop());
            loanreturnedbyfundTaAF.setBizTime(dto2.getDate());
          }else if(dto2.getPl400_seq().equals("2")){//辅助借款人1
            loanreturnedbyfundTaAF.setBorrow_s_empida(dto2.getEmpid_s());
            loanreturnedbyfundTaAF.setBorrow_s_orgida(dto2.getOrgid_s());
            loanreturnedbyfundTaAF.setBorrow_s_empnamea(dto2.getEmp_name_s());
            loanreturnedbyfundTaAF.setBorrow_s_cardnuma(dto2.getEmp_card_num_s());
            System.out.println("Empid_s:"+dto2.getEmpid_s());
            System.out.println("orgid:"+dto2.getOrgid_s());
            System.out.println("empnamea:"+dto2.getEmp_name_s());
            loanreturnedbyfundTaAF.setXieYinum(dto2.getXieyi_id());
            loanreturnedbyfundTaAF.setDate_stop(dto2.getDate_stop());
            loanreturnedbyfundTaAF.setBizTime(dto2.getDate());
            //yuqf 20091009 查询辅助借款人1的最大金额
            String a=loanreturnedbyfundBS.get_print_money(dto2.getOrgid_s(),dto2.getEmpid_s());
            loanreturnedbyfundTaAF.setF1MaxMoney(a);
          }else{//辅助借款人2
            loanreturnedbyfundTaAF.setBorrow_s_empidb(dto2.getEmpid_s());
            loanreturnedbyfundTaAF.setBorrow_s_orgidb(dto2.getOrgid_s());
            loanreturnedbyfundTaAF.setBorrow_s_empnameb(dto2.getEmp_name_s());
            loanreturnedbyfundTaAF.setBorrow_s_cardnumb(dto2.getEmp_card_num_s());
            
            loanreturnedbyfundTaAF.setXieYinum(dto2.getXieyi_id());
            loanreturnedbyfundTaAF.setDate_stop(dto2.getDate_stop());
            loanreturnedbyfundTaAF.setBizTime(dto2.getDate());
            
//          yuqf 20091009 查询辅助借款人1的最大金额
            String a=loanreturnedbyfundBS.get_print_money(dto2.getOrgid_s(),dto2.getEmpid_s());
            loanreturnedbyfundTaAF.setF2MaxMoney(a);
          }
        }
      }else{
        loanreturnedbyfundTaAF.setBorrow_s_empid("");
        loanreturnedbyfundTaAF.setBorrow_s_orgid("");
        loanreturnedbyfundTaAF.setName("");
        loanreturnedbyfundTaAF.setCardNum("");
        loanreturnedbyfundTaAF.setAssiMaxMoney("");  
      }
      loanreturnedbyfundTaAF.setContractId(contract_id);
      loanreturnedbyfundTaAF.setPrint_status(Start);
      
      if(loanreturnedbyfundTaAF.getDate_stop()==null ||loanreturnedbyfundTaAF.getDate_stop()==""){
        loanreturnedbyfundTaAF.setDate_stop("20080101");
      }
      
      request.setAttribute("loanreturnedbyfundTaAF", loanreturnedbyfundTaAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("loanreturnedbyfundTaTaPrint");
  }
}

