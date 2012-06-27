package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.bsinterface.IBankCheckAccBS;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.form.BankCheckAccTaAF;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;


public class BankCheckAccTaShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    saveToken(request);
    request.getSession().removeAttribute("credenceType_gjp");
    BankCheckAccTaAF bankCheckAccTaAF=null;
    if(request.getAttribute("bankCheckAccTaAF")!=null){
      bankCheckAccTaAF=(BankCheckAccTaAF)request.getAttribute("bankCheckAccTaAF");
    }else{
      bankCheckAccTaAF=new BankCheckAccTaAF();
    }
    if(bankCheckAccTaAF.getType()=="1"){
      bankCheckAccTaAF=new BankCheckAccTaAF();
      if(request.getAttribute("office")!=null){
        String office=(String)request.getAttribute("office");
        bankCheckAccTaAF.getBankCheckAccTaDTO().setOffice(office);
      }
    }
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    List officeList1 = null;
    List summrayList1=null;
    List settTypeList1=null;
    try {
      // 取出用户权限办事处,显示在下拉菜单中
      List officeList = securityInfo.getOfficeList();
      officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try{
      IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
      .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
      Object[] obj=bankCheckAccBS.findParamExplainList(securityInfo,"");
      if(obj.length>0){
        //从数据库中查出摘要的list，显示在下拉菜单中
        summrayList1=new ArrayList();
        List summrayList=(List)obj[0];
        if(summrayList.size()>0){
          BookParameterDTO bookParameterDTO = null;
          Iterator itr1 = summrayList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTO = (BookParameterDTO) itr1.next();
            summrayList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTO.getBookParameterName()
                , bookParameterDTO.getBookParameterId()));
          }
        }
        //从数据库中查出结算方式的list，显示在下拉菜单中
        settTypeList1=new ArrayList();
        List settTypeList=(List)obj[1];
        if(settTypeList.size()>0){
          BookParameterDTO bookParameterDTO = null;
          Iterator itr1 = settTypeList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTO = (BookParameterDTO) itr1.next();
            settTypeList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTO.getBookParameterName()
                , bookParameterDTO.getBookParameterId()));
          }
        }
      }
      String bookSt=bankCheckAccBS.findBookSt(securityInfo);
      if(!bookSt.equals("")){
        bankCheckAccTaAF.setBookSt(bookSt);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    request.setAttribute("bankCheckAccTaAF", bankCheckAccTaAF);
    request.setAttribute("officeList1", officeList1);
    request.setAttribute("summrayList1", summrayList1);
    request.setAttribute("settTypeList1", settTypeList1);
    return mapping.findForward("to_bankcheckaccta_show");
  }
}
