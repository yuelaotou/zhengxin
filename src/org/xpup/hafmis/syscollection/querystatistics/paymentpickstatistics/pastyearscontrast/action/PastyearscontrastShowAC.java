package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.action;

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
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.bsinterface.IPastyearscontrastBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.dto.PastyearscontrasDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.form.PastyearscontrasAF;
import org.xpup.security.common.domain.Userslogincollbank;
/**
 * 
 * @author 于庆丰
 *
 */
public class PastyearscontrastShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.action.PastyearscontrastShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    PastyearscontrasAF pastyearscontrasAF = new PastyearscontrasAF();
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    
    //从数据库读取办事处信息
    List officeList = securityInfo.getOfficeList();
    List officeList1 = new ArrayList();
    OfficeDto officedto = null;
    Iterator itr1 = officeList.iterator();
    while (itr1.hasNext()) {
      officedto = (OfficeDto) itr1.next();
      officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
    }
    request.getSession(true).setAttribute("officeList1", officeList1);
    
    //结束
    
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
    request.getSession(true).setAttribute("loanbankList1", loanbankList1);
    //结束
    
    try{
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      IPastyearscontrastBS pastyearscontrastBS = (IPastyearscontrastBS) 
      BSUtils.getBusinessService("pastyearscontrastBS", this, mapping.getModuleConfig());
      pastyearscontrasAF = pastyearscontrastBS.queryList(pagination, securityInfo);
      //单位性质下拉框枚举
      Map orgCharacterMap = BusiTools.listBusiProperty(BusiConst.NATUREOFUNITS);
      pastyearscontrasAF.setOrgCharacterMap(orgCharacterMap);
      //主管部门下拉框枚举
      Map deptMap = BusiTools.listBusiProperty(BusiConst.AUTHORITIES);
      pastyearscontrasAF.setDeptMap(deptMap);
      //所在地区下拉框枚举
      Map ragionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
      pastyearscontrasAF.setRagionMap(ragionMap);
      if(!pagination.getQueryCriterions().isEmpty()){//保留查询信息用于打印
        String office = (String)pagination.getQueryCriterions().get("office");
        String bank = (String)pagination.getQueryCriterions().get("bank");
        String orgCharacter = (String)pagination.getQueryCriterions().get("orgCharacter");
        String dept = (String)pagination.getQueryCriterions().get("dept");
        String ragion = (String)pagination.getQueryCriterions().get("ragion");
        String startDate = (String)pagination.getQueryCriterions().get("startDate");
        String endDate = (String)pagination.getQueryCriterions().get("endDate");
        
        String officeName = "";
        String bankName = "";
        String orgCharacterName = "";
        String deptName = "";
        String ragionName = "";
        
        if(office!=null && !"".equals(office)){
           officeName = pastyearscontrastBS.findOfficeName(office);
        }
        if(bank != null && !"".equals(bank)){
           bankName = pastyearscontrastBS.findBankName(bank);
        }
        if(orgCharacter != null && !"".equals(orgCharacter)){
           orgCharacterName = BusiTools.getBusiValue(Integer.parseInt(""
              + orgCharacter), BusiConst.NATUREOFUNITS);
        }
        if(dept != null && !"".equals(dept)){
           deptName = BusiTools.getBusiValue(Integer.parseInt(""
              + dept), BusiConst.AUTHORITIES);
        }
        if(ragion != null && !"".equals(ragion)){
           ragionName = BusiTools.getBusiValue(Integer.parseInt(""
              + ragion), BusiConst.INAREA);
        }
        

        pastyearscontrasAF.setOfficeCode(officeName);
        pastyearscontrasAF.setCollectionBankId(bankName);
        pastyearscontrasAF.setOrgChracter(orgCharacterName);
        pastyearscontrasAF.setDept(deptName);
        pastyearscontrasAF.setRagion(ragionName);
        pastyearscontrasAF.setStartDate(startDate);
        pastyearscontrasAF.setEndDate(endDate);
      }
      
      request.setAttribute("thePastyearscontrasAF", pastyearscontrasAF);
      request.getSession().setAttribute("thePastyearscontrasAF", pastyearscontrasAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_pastyearscontras.jsp");
  }
  private Pagination getPagination(String paginationKey, HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
