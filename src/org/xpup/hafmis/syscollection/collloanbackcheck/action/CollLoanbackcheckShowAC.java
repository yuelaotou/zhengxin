package org.xpup.hafmis.syscollection.collloanbackcheck.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.security.common.domain.Userslogincollbank;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.syscollection.collloanbackcheck.bsinterface.ICollLoanbackcheckBS;
import org.xpup.hafmis.syscollection.collloanbackcheck.dto.CollLoanbackcheckDTO;
import org.xpup.hafmis.syscollection.collloanbackcheck.form.CollLoanbackcheckAF;

public class CollLoanbackcheckShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.collloanbackcheck.action.CollLoanbackcheckShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    
    CollLoanbackcheckAF af=(CollLoanbackcheckAF)form;
    
    ICollLoanbackcheckBS collLoanbackcheckBS = (ICollLoanbackcheckBS) BSUtils
        .getBusinessService("collLoanbackcheckBS", this, mapping.getModuleConfig());
    
    try {
      /**
       * 分页
       */   
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      //从数据库读取办事处信息
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      List officeList = securityInfo.getAllOfficeList();
      List officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
      }
      request.getSession(true).setAttribute("officeList1", officeList1);
      //结束
      
      // 取出用户下归集行
      List collBankList = securityInfo.getCollBankList();
      List collBankList1 = new ArrayList();
      Userslogincollbank userslogincollbank = null;
      Iterator itr2 = collBankList.iterator();
      while (itr2.hasNext()) {
        userslogincollbank = (Userslogincollbank) itr2.next();
        collBankList1.add(new org.apache.struts.util.LabelValueBean(userslogincollbank.getCollBankName().toString(), userslogincollbank.getCollBankId().toString()));
      }
      request.getSession(true).setAttribute("collBankList1", collBankList1);
      //结束
      
      //获得业务状态
      af.setMap(BusiTools.listBusiProperty(BusiConst.CLEARACCOUNTSTATUS));

      HttpSession session = request.getSession();
      String flag=(String)session.getAttribute("flag");
      af.setFlag(flag);
      pagination.getQueryCriterions().put("flag", flag);
      List returnList=collLoanbackcheckBS.collLoanbackcheckForwardFind(pagination,securityInfo);
      List returnAllList=collLoanbackcheckBS.collLoanbackcheckForwardFindAll(pagination,securityInfo);
      
      session.setAttribute("checkList", returnAllList);
      
      //计算合计数
      CollLoanbackcheckDTO collLoanbackcheckDTO=null;
      String StatusType0 = "";
      String StatusType1 = "";
      String StatusType2 = "";
      
      String print_people_count ="";
      int people_count = 0;
      for(int i=0;i<returnAllList.size();i++){
        collLoanbackcheckDTO=new CollLoanbackcheckDTO();
        collLoanbackcheckDTO=(CollLoanbackcheckDTO)returnAllList.get(i);
        af.setTotalDcitsum(af.getTotalDcitsum().add(collLoanbackcheckDTO.getMoney()));
        
        if(print_people_count=="" || !print_people_count.equals(collLoanbackcheckDTO.getEmp_id())){
          people_count++;
          print_people_count=collLoanbackcheckDTO.getEmp_id();
        }

        if ("3".equals(collLoanbackcheckDTO.getPick_satatus_num())) {
          StatusType1 = "1";
        }
        if ("4".equals(collLoanbackcheckDTO.getPick_satatus_num())) {
          StatusType2 = "2";
        }
        if ("5".equals(collLoanbackcheckDTO.getPick_satatus_num())) {
          StatusType0 = "0";
        }
      }
      //保存业务状态，按钮禁用中用到
      if (StatusType0 == "0") {
        af.setStatusType("0");
      } else {
        if (StatusType1 == "1" && StatusType2 == "") {
          af.setStatusType("1");
        }
        if (StatusType1 == "" && StatusType2 == "2") {
          af.setStatusType("2");
        }
        if(StatusType1 == "1" && StatusType0 == "0"){
          af.setStatusType("3");
        }
        if (StatusType1 == "1" && StatusType2 == "2") {
          af.setStatusType("0");
        }
      }
      af.setTotalCount(returnAllList.size());
      session.setAttribute("totalDcitsum",af.getTotalDcitsum());
      session.setAttribute("people_count",""+people_count);
      af.setList(returnList);
      
      request.setAttribute("collLoanbackcheckAF", af);
    }catch (Exception e) {
      e.printStackTrace();
    }
    
    return mapping.findForward(getForword());
  }
  protected String getForword() {
    return "collLoanbackcheckList";
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "aa306.BATCH_NUM", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}