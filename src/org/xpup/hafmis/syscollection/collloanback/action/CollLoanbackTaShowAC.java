package org.xpup.hafmis.syscollection.collloanback.action;

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
import org.xpup.hafmis.syscollection.collloanback.bsinterface.ICollLoanbackBS;
import org.xpup.hafmis.syscollection.collloanback.dto.CollLoanbackTaListDTO;
import org.xpup.hafmis.syscollection.collloanback.form.CollLoanbackTaAF;
import org.xpup.security.common.domain.Userslogincollbank;



public class CollLoanbackTaShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.collloanback.action.CollLoanbackTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    CollLoanbackTaAF collLoanbackTaAF=new CollLoanbackTaAF();
    //从数据库读取办事处信息
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    List officeList = securityInfo.getOfficeList();
    List officeList1 = new ArrayList();
    OfficeDto officedto = null;
    Iterator itr1 = officeList.iterator();
    while (itr1.hasNext()) {
      officedto = (OfficeDto) itr1.next();
      officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
    }
    List loanbankList1 = null;
    try {
      // 取出用户权限放款银行,显示在下拉菜单中
      List loanbankList = securityInfo.getCollBankList();
      loanbankList1 = new ArrayList();
      Userslogincollbank bank = null;
      Iterator itr2 = loanbankList.iterator();
      while (itr2.hasNext()) {
        bank = (Userslogincollbank) itr2.next();
        loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
            .getCollBankName(), bank.getCollBankId().toString()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    Pagination pagination = (Pagination)request.getSession().getAttribute(CollLoanbackTaShowAC.PAGINATION_KEY); 
    try{
      if(pagination!=null){
        pagination = getPagination(CollLoanbackTaShowAC.PAGINATION_KEY, request); 
        PaginationUtils.updatePagination(pagination, request);
        ICollLoanbackBS collLoanbackBS = (ICollLoanbackBS) BSUtils
        .getBusinessService("collLoanbackBS", this, mapping.getModuleConfig());
        collLoanbackTaAF = collLoanbackBS.findCollLoanbackTaList(pagination);
//        Object[] obj=collLoanbackBS.findCollLoanbackTaList(pagination);
//        if(obj[0]!=null){
//          collLoanbackTaAF.setBatchNum(obj[0].toString());         
//        }
//        List list=(List)obj[1];
//        if(list.size()!=0){
//          CollLoanbackTaListDTO collLoanbackTaListDTO = new CollLoanbackTaListDTO();
//          collLoanbackTaListDTO=(CollLoanbackTaListDTO)list.get(0);
//          collLoanbackTaAF.setC_count(collLoanbackTaListDTO.getC_count());
//          collLoanbackTaAF.setP_count(collLoanbackTaListDTO.getP_count());
//          collLoanbackTaAF.setM_sum(collLoanbackTaListDTO.getM_sum());
//          collLoanbackTaAF.setList(list);
//        }
      }else{
        pagination=new Pagination();
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    request.getSession().setAttribute(CollLoanbackTaShowAC.PAGINATION_KEY, pagination);
    request.getSession().setAttribute("batchNum", collLoanbackTaAF.getBatchNum());
    request.setAttribute("collLoanbackTaAF", collLoanbackTaAF);
    request.setAttribute("officeList1", officeList1);
    request.setAttribute("loanbankList1", loanbankList1);
    return mapping.findForward("to_collloanbackta_show");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      HashMap map = new HashMap();
      pagination = new Pagination(0, 10, 1, "aa411.loan_kou_acc", "DESC",
           map);
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}
