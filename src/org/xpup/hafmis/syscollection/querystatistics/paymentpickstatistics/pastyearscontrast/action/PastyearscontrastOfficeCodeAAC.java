package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.bsinterface.IPastyearscontrastBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.form.PastyearscontrasAF;

/**
 * 
 * @author 于庆丰
 *
 */
public class PastyearscontrastOfficeCodeAAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.action.PastyearscontrastShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    PastyearscontrasAF pastyearscontrasAF = new PastyearscontrasAF();
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
      request.setAttribute("thePastyearscontrasAF", null);
      String officecode=request.getParameter("officeCode");
      String paginationKey = getPaginationKey();
      Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
      pagination.getQueryCriterions().put("officecode", officecode);
      IPastyearscontrastBS pastyearscontrastBS = (IPastyearscontrastBS) 
      BSUtils.getBusinessService("pastyearscontrastBS", this, mapping.getModuleConfig());
      pastyearscontrasAF = pastyearscontrastBS.fingTree(pagination, securityInfo);
//    单位性质下拉框枚举
      Map orgCharacterMap = BusiTools.listBusiProperty(BusiConst.NATUREOFUNITS);
      pastyearscontrasAF.setOrgCharacterMap(orgCharacterMap);
      //主管部门下拉框枚举
      Map deptMap = BusiTools.listBusiProperty(BusiConst.AUTHORITIES);
      pastyearscontrasAF.setDeptMap(deptMap);
      //所在地区下拉框
      Map ragionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
      pastyearscontrasAF.setRagionMap(ragionMap);
      request.setAttribute("thePastyearscontrasAF", pastyearscontrasAF);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("to_pastyearscontras.jsp");
//      String text = "display();";
//      System.out.println("----->>>"+text);
//      
//      response.getWriter().write(text); 
//      response.getWriter().close();
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//    
//    return null; 
  }
  protected String getPaginationKey() {
    return PastyearscontrastShowAC.PAGINATION_KEY;
 }
}
