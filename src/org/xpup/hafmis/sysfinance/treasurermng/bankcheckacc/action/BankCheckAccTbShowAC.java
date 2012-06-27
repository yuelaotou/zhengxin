package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.action;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.bsinterface.IBankCheckAccBS;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTbFindDTO;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.form.BankCheckAccTbAF;
import org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.dto.BookParameterDTO;


public class BankCheckAccTbShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.action.BankCheckAccTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    BankCheckAccTbAF bankCheckAccTbAF=new BankCheckAccTbAF();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
    "SecurityInfo");
    List summrayList1=null;
    try{
      IBankCheckAccBS bankCheckAccBS = (IBankCheckAccBS) BSUtils
      .getBusinessService("bankCheckAccBS", this, mapping.getModuleConfig());
      Object[] obj=bankCheckAccBS.findParamExplainList(securityInfo, "1");
      if(obj.length>0){
        //从数据库中查出摘要的list，显示在下拉菜单中
        summrayList1=new ArrayList();
        List summrayList=(List)obj[0];
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
      Pagination pagination = getPagination(BankCheckAccTbShowAC.PAGINATION_KEY, request); 
      Object[] listObj=new Object[3]; 
      PaginationUtils.updatePagination(pagination, request);
      listObj=bankCheckAccBS.findBankCheckAccTbList(pagination, securityInfo);
      List list=new ArrayList();
      list=(List)listObj[0];
      if(list.size()>0){
        bankCheckAccTbAF.setList(list);
        BankCheckAccTbFindDTO bankCheckAccTbFindDTO=(BankCheckAccTbFindDTO)listObj[1];
        BankCheckAccTbFindDTO temp_bankCheckAccTbFindDTO=new BankCheckAccTbFindDTO();
        temp_bankCheckAccTbFindDTO.setCreditSum(bankCheckAccTbFindDTO.getCreditSum());
        temp_bankCheckAccTbFindDTO.setDebitSum(bankCheckAccTbFindDTO.getDebitSum());
        bankCheckAccTbAF.setBankCheckAccTbFindDTO(temp_bankCheckAccTbFindDTO);
      }
      List countList=(List)listObj[2];
      request.getSession().setAttribute("countList", countList);
    }catch(Exception e){
      e.printStackTrace();
    }
    request.setAttribute("bankCheckAccTbAF", bankCheckAccTbAF);
    request.setAttribute("summrayList1", summrayList1);
    return mapping.findForward("to_bankcheckacctb_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap map = new HashMap();
      pagination = new Pagination(0, 10, 1, "fn211.credence_id", "DESC",
           map);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
