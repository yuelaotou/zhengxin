package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import java.math.BigDecimal;
import java.nio.BufferUnderflowException;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.biz.clearinginterest.ClearingInterestBS;
import org.xpup.hafmis.syscollection.common.biz.clearinginterest.ClearingInterestInterface;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.dto.ClearingInterestDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAddAF;



public class Tran_AddFindempinfoAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String empid=(String)request.getParameter("empid");
      String orgid=(String)request.getParameter("orgid");
      TranAddAF tranAddAF = new TranAddAF();
      String str="";
      ItranoutBS tranoutBS = (ItranoutBS)BSUtils.getBusinessService("tranoutBS",this,mapping.getModuleConfig());
      try{
        tranAddAF=tranoutBS.findEmpInfo(empid, orgid,securityInfo);
      }catch(BusinessException b){
        str=b.getMessage();
      }
      String text=null;
      
      String name=tranAddAF.getEmp().getEmpInfo().getName();
      String card_kind = tranAddAF.getEmp().getEmpInfo().getCardKind().toString();
      String card_num = tranAddAF.getEmp().getEmpInfo().getCardNum();
      String preBalance = tranAddAF.getEmp().getPreBalance().toString();
      String curBalance = tranAddAF.getEmp().getCurBalance().toString();
      String salary = tranAddAF.getSalary();
      String preInterest = tranAddAF.getPreInterest();
      String curInterest = tranAddAF.getCurInterest();
      String sumInterest = tranAddAF.getSumInterest();
      String transum = tranAddAF.getTransum();

      card_kind=BusiTools.getBusiValue(Integer.parseInt(card_kind), BusiConst.DOCUMENTSSTATE);
      text="displays('"+empid+"','"+name+"','"+card_kind+"','"+card_num+"','"+preBalance+"','"+curBalance+"','"+salary+"'" +
          ",'"+preInterest+"','"+curInterest+"','"+sumInterest+"','"+transum+"','"+str+"')";
      response.getWriter().write(text); 
      response.getWriter().close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null; 

  }

  private String getPaginationKey() {
    return Tran_showAC.PAGINATION_KEY;
  }

}
