package org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTbFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form.CashDayClearTbAF;



public class CashDayClearTbShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action.CashDayClearTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      CashDayClearTbAF cashDayClearTbAF=new CashDayClearTbAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      List officeList1 = null;
      List credenceCharacterList1=null;
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
      
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      Object[] obj=cashDayClearBS.findCredenceCharacterList(securityInfo,"");
      if(obj.length>0){
        //从数据库中查出凭证字的list，显示在下拉菜单中
        credenceCharacterList1=new ArrayList();
        List credenceCharacterList=(List)obj[0];
        if(credenceCharacterList.size()>0){
          BookParameterDTO bookParameterDTODTO = null;
          Iterator itr1 = credenceCharacterList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTODTO = (BookParameterDTO) itr1.next();
            credenceCharacterList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTODTO.getBookParameterName()
                , bookParameterDTODTO.getBookParameterId()));
          }
        }
        //从数据库中查出摘要的list，显示在下拉菜单中
        summrayList1=new ArrayList();
        List summrayList=(List)obj[1];
        if(summrayList.size()>0){
          BookParameterDTO bookParameterDTODTO = null;
          Iterator itr1 = summrayList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTODTO = (BookParameterDTO) itr1.next();
            summrayList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTODTO.getBookParameterName()
                , bookParameterDTODTO.getBookParameterId()));
          }
        }
        //从数据库中查出结算方式的list，显示在下拉菜单中
        settTypeList1=new ArrayList();
        List settTypeList=(List)obj[2];
        if(settTypeList.size()>0){
          BookParameterDTO bookParameterDTODTO = null;
          Iterator itr1 = settTypeList.iterator();
          while (itr1.hasNext()) {
            bookParameterDTODTO = (BookParameterDTO) itr1.next();
            settTypeList1.add(new org.apache.struts.util.LabelValueBean(bookParameterDTODTO.getBookParameterName()
                , bookParameterDTODTO.getBookParameterId()));
          }
        }
      }
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      Pagination pagination = getPagination(CashDayClearTbShowAC.PAGINATION_KEY, request); 
      Object[] listObj=new Object[3]; 
      PaginationUtils.updatePagination(pagination, request);
      listObj=cashDayClearBS.findCashDayClearTbList(pagination,credenceType,securityInfo);
      List list=new ArrayList();
      list=(List)listObj[0];
      if(list.size()>0){
        cashDayClearTbAF.setList(list);
        CashDayClearTbFindDTO cashDayClearTbFindDTO=(CashDayClearTbFindDTO)listObj[1];
        CashDayClearTbFindDTO temp_cashDayClearTbFindDTO=new CashDayClearTbFindDTO();
        temp_cashDayClearTbFindDTO.setCreditSum(cashDayClearTbFindDTO.getCreditSum());
        temp_cashDayClearTbFindDTO.setDebitSum(cashDayClearTbFindDTO.getDebitSum());
        cashDayClearTbAF.setCashDayClearTbFindDTO(temp_cashDayClearTbFindDTO);
      }
      List countList=(List)listObj[2];
      request.getSession().setAttribute("countList", countList);
      //type是为了到页面上做标识的0为现金日记账；1为银行存款日记账
      if(credenceType.equals("0")){
        request.setAttribute("type", "0");
      }
      if(credenceType.equals("1")){
        request.setAttribute("type", "1");
      }
      request.setAttribute("cashDayClearTbAF", cashDayClearTbAF);
      request.setAttribute("officeList1", officeList1);
      request.setAttribute("credenceCharacterList1", credenceCharacterList1);
      request.setAttribute("summrayList1", summrayList1);
      request.setAttribute("settTypeList1", settTypeList1);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_cashdaycleartb_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap map = new HashMap();
      pagination = new Pagination(0, 10, 1, "fn201.credence_id", "DESC",
           map);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
