package org.xpup.hafmis.sysloan.dataready.loanconditionsset.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.bsinterface.ILoanConditionsSetBS;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.dto.LoanConditionsSetDTO;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.form.LoanConditionsSetAF;


public class LoanConditionsSetShowAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try{
      LoanConditionsSetAF loanConditionsSetAF=new LoanConditionsSetAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      List officeList1=new ArrayList();
      //取出办事处
      List officeList = securityInfo.getOfficeList();
      OfficeDto officedto = null;
      officeList1.add(new org.apache.struts.util.LabelValueBean("全部", "100"));
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
      String office="";
      if(request.getParameter("office")!=null){
        office=(String)request.getParameter("office");
      }else{
        //OfficeDto officedto1=(OfficeDto)securityInfo.getOfficeList().get(0);
        office="100";//officedto1.getOfficeCode().toString();
      }
      ILoanConditionsSetBS loanConditionsSetBS = (ILoanConditionsSetBS) BSUtils
      .getBusinessService("loanConditionsSetBS", this, mapping.getModuleConfig());
      LoanConditionsSetDTO loanConditionsSetDTO=null;
      loanConditionsSetDTO=loanConditionsSetBS.findLoanConditionsSetInfo(office);
      if(loanConditionsSetDTO!=null){
        loanConditionsSetAF.setLoanConditionsSetDTO(loanConditionsSetDTO);
      }
      Map natureofunitsMap = BusiTools.listBusiProperty(BusiConst.NATUREOFUNITS);
      loanConditionsSetAF.setNatureofunitsMap(natureofunitsMap);
      loanConditionsSetAF.getLoanConditionsSetDTO().setOffice(office);
      request.setAttribute("officeList1", officeList1);
      request.setAttribute("loanConditionsSetAF", loanConditionsSetAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_loanconditionsset_show");
  }
}
