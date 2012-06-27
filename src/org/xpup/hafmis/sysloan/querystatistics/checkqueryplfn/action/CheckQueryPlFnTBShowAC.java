package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.bsinterface.ICheckQueryPlFnBS;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.form.CheckQueryPlFnTBAF;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.bsinterface.IParticularglBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.form.ParticularglTaAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class CheckQueryPlFnTBShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action.CheckQueryPlFnTBShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try{
      CheckQueryPlFnTBAF checkQueryPlFnTBAF=new CheckQueryPlFnTBAF();
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
      "SecurityInfo");
      
      List loanbankList1 = null;
      List loanbankList = securityInfo.getDkUserBankList();
      try {
        // 取出用户权限放款银行,显示在下拉菜单中
        loanbankList1 = new ArrayList();
        Userslogincollbank bank = null;
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
              .getCollBankName(), bank.getCollBankId().toString()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      List officeList1 = null;
      List officeList = securityInfo.getOfficeList();
      try{
        //取出用户权限办事处,显示在下拉菜单中
        officeList1 = new ArrayList();
        OfficeDto officedto = null;
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          officedto = (OfficeDto) itr1.next();
          officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
        }
      }catch(Exception e){
        e.printStackTrace();
      }
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      
      
      if(pagination.getQueryCriterions().get("bizdateB")==null){
        pagination.getQueryCriterions().put("bizdateB", "10000101");
      }
      if(pagination.getQueryCriterions().get("bizdateE")==null){
        pagination.getQueryCriterions().put("bizdateE", "30000101");
      }
      
     
      IParticularglBS particularglBS = (IParticularglBS) BSUtils.getBusinessService("particularglBS", this, mapping.getModuleConfig());
      ParticularglTaAF particularglTaAF=new ParticularglTaAF();
      particularglTaAF=particularglBS.showparticularglListTd(pagination,pagination,securityInfo);
      
      String  borrowercontractid= (String) pagination.getQueryCriterions().get("borrowercontractid");//借款合同编号
      
      
//      ICheckQueryPlFnBS checkQueryPlFnBS = (ICheckQueryPlFnBS) BSUtils.getBusinessService("checkQueryPlFnBS", this, mapping.getModuleConfig());
//      Object[] obj=checkQueryPlFnBS.showCheckQueryPlFnTBList(pagination, securityInfo);
//      List list=(List)obj[0];
//      if(list.size()>0){
//        checkQueryPlFnTBAF.setList(list);
//      }
//      List printList=(List)obj[1];
      //个贷业务状态下拉列表
      checkQueryPlFnTBAF.setEmpName(particularglTaAF.getBorrowername());
      checkQueryPlFnTBAF.setContractId(particularglTaAF.getContractid());
      if(!particularglTaAF.getBizdateB().equals("10000101")){
        checkQueryPlFnTBAF.setStartTime(particularglTaAF.getBizdateB());
      }
      if(!particularglTaAF.getBizdateE().equals("30000101")){
        checkQueryPlFnTBAF.setEndTime(particularglTaAF.getBizdateE());
      }
     
      Map plbizstMap=BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);
      Map fnbizstMap=BusiTools.listBusiProperty(BusiConst.CREDSTATE);
      checkQueryPlFnTBAF.getCheckQueryPlFnTBFindDTO().setPlbizstMap(plbizstMap);
      checkQueryPlFnTBAF.getCheckQueryPlFnTBFindDTO().setFnbizstMap(fnbizstMap);
//      request.getSession().setAttribute(CheckQueryPlFnTBShowAC.PAGINATION_KEY, pagination);
      request.setAttribute("checkQueryPlFnTBAF", checkQueryPlFnTBAF);
      request.setAttribute("loanbankList1", loanbankList1);
      request.setAttribute("officeList1", officeList1);
//      request.getSession().setAttribute("printList", printList);
      request.setAttribute("particularglTaAF",particularglTaAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_checkQueryPlFntb_show");
  }
  private Pagination getPagination(String paginationKey,HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, " pl202.biz_date ", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
