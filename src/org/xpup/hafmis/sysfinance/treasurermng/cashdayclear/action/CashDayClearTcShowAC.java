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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.bsinterface.ICashDayClearBS;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.CashDayClearTcFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.form.CashDayClearTcAF;

public class CashDayClearTcShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action.CashDayClearTcShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      CashDayClearTcAF cashDayClearTcAF=new CashDayClearTcAF();
      cashDayClearTcAF.getCashDayClearTcFindDTO().setCredenceStMap(BusiTools.listBusiProperty(BusiConst.CREDSTATE));
      cashDayClearTcAF.getCashDayClearTcFindDTO().getCredenceStMap().remove(new Integer(1));
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      List summrayList1=null;
      ICashDayClearBS cashDayClearBS = (ICashDayClearBS) BSUtils
      .getBusinessService("cashDayClearBS", this, mapping.getModuleConfig());
      Object[] obj=cashDayClearBS.findCredenceCharacterList(securityInfo,"1");
      if(obj.length>0){
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
      }
      Pagination pagination = getPagination(CashDayClearTcShowAC.PAGINATION_KEY, request); 
      Object[] listObj=new Object[3]; 
      PaginationUtils.updatePagination(pagination, request);
      //用credenceType来判断是现金日记账还是银行存款日记账
      String credenceType=(String)request.getSession().getAttribute("credenceType_gjp");
      listObj=cashDayClearBS.findCashDayClearTcList(credenceType,pagination, securityInfo);
      List list=new ArrayList();
      list=(List)listObj[0];
      if(list.size()>0){
        cashDayClearTcAF.setList(list);
        CashDayClearTcFindDTO cashDayClearTcFindDTO=(CashDayClearTcFindDTO)listObj[1];
        CashDayClearTcFindDTO temp_cashDayClearTcFindDTO=new CashDayClearTcFindDTO();
        temp_cashDayClearTcFindDTO.setCredenceStMap(
            BusiTools.listBusiProperty(BusiConst.CREDSTATE));
        temp_cashDayClearTcFindDTO.getCredenceStMap().remove(new Integer(1));
        temp_cashDayClearTcFindDTO.setCreditSum(cashDayClearTcFindDTO.getCreditSum());
        temp_cashDayClearTcFindDTO.setDebitSum(cashDayClearTcFindDTO.getDebitSum());
        cashDayClearTcAF.setCashDayClearTcFindDTO(temp_cashDayClearTcFindDTO);
      }
      //type是为了到页面上做标识的0为现金日记账；1为银行存款日记账
      if(credenceType.equals("0")){
        request.setAttribute("type", "0");
      }
      if(credenceType.equals("1")){
        request.setAttribute("type", "1");
      }
      List countList=(List)listObj[2];
      request.getSession().setAttribute("countList", countList);
      request.setAttribute("summrayList1", summrayList1);
      request.setAttribute("cashDayClearTcAF", cashDayClearTcAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_cashdaycleartc_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap map = new HashMap();
      pagination = new Pagination(0, 10, 1, "fn210.credence_id", "DESC",
           map);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
