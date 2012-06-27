/**
 * Copy Right Information   : Goldsoft 
 * Project                  : EmpAccountPopShowAC
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-11-02
 **/
package org.xpup.hafmis.sysloan.common.biz.empaccountpop.action;

import java.util.HashMap;

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
import org.xpup.hafmis.sysloan.common.biz.empaccountpop.bsinterface.IEmpAccountPopBS;
import org.xpup.hafmis.sysloan.common.biz.empaccountpop.form.EmpAccountPopAF;

public class EmpAccountPopShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.common.biz.empaccountpop.action.EmpAccountPopShowAC";

  /**
   * 显示职工明细账列表
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      // 修改记录：查看职工明细账时，应查看该职工编号相同姓名及身份证号的所有明细账
      String yg=(String)request.getAttribute("yg");
      String borrowerName = null;
      String cardNum = null;
      String sessName = (String) request.getSession().getAttribute(
          "borrowerName");
      borrowerName = (String) request.getParameter("borrowerName");
      if (borrowerName != null && !borrowerName.equals("")) {
        sessName = borrowerName;
        request.getSession().setAttribute("borrowerName", sessName);
      }
      String sessCardNum = (String) request.getSession()
          .getAttribute("cardNum");
      cardNum = (String) request.getParameter("cardNum");
      if (cardNum != null && !cardNum.equals("")) {
        sessCardNum = cardNum;
        request.getSession().setAttribute("cardNum", sessCardNum);
      }
      if (borrowerName != null) {
        request.getSession()
            .removeAttribute(EmpAccountPopShowAC.PAGINATION_KEY);
      }
      
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      request.getSession().setAttribute(PAGINATION_KEY, pagination);
      IEmpAccountPopBS empAccountPopBS = (IEmpAccountPopBS) BSUtils
          .getBusinessService("empAccountPopBS", this, mapping
              .getModuleConfig());
      String bizType = (String)pagination.getQueryCriterions().get("bizType");
      String settDateA=(String)pagination.getQueryCriterions().get("settDateA");
      String settDateB=(String)pagination.getQueryCriterions().get("settDateB");
      
      if(borrowerName!=null){
        if(borrowerName.split("_").length>2){
          String a_yg[]=borrowerName.split("_");
          pagination.getQueryCriterions().put("empId",a_yg[1].toString());
          pagination.getQueryCriterions().put("orgId",a_yg[2].toString());
          EmpAccountPopAF empAccountPopAF = new EmpAccountPopAF();
          empAccountPopAF = empAccountPopBS.queryEmpAccountListByCriterions(
              "", "", pagination);
          empAccountPopAF.setBstypeMap(BusiTools.listBusiProperty(BusiConst.CLEARACCOUNTBUSINESSTYPE));
          request.setAttribute("empAccountPopAF", empAccountPopAF);
        }else{
          EmpAccountPopAF empAccountPopAF = new EmpAccountPopAF();
          empAccountPopAF = empAccountPopBS.queryEmpAccountListByCriterions(
              sessName, sessCardNum, pagination);
          empAccountPopAF.setBstypeMap(BusiTools.listBusiProperty(BusiConst.CLEARACCOUNTBUSINESSTYPE));
          request.setAttribute("empAccountPopAF", empAccountPopAF);
        }
      }else{
        EmpAccountPopAF empAccountPopAF = new EmpAccountPopAF();
        System.out.println("d..."+pagination.getQueryCriterions().get("bizType"));
        System.out.println("e..."+pagination.getQueryCriterions().get("settDateA"));
        System.out.println("f..."+pagination.getQueryCriterions().get("settDateB"));
        if(yg!=null && !yg.equals("") || (bizType!=null && !bizType.equals(""))|| (settDateA!=null && !settDateA.equals(""))|| (settDateB!=null && !settDateB.equals(""))){
          empAccountPopAF = empAccountPopBS.queryEmpAccountListByCriterions_yg(pagination);
          empAccountPopAF.setBstypeMap(BusiTools.listBusiProperty(BusiConst.CLEARACCOUNTBUSINESSTYPE));
          request.setAttribute("empAccountPopAF", empAccountPopAF);
        }else{
          empAccountPopAF = empAccountPopBS.queryEmpAccountListByCriterions(
              sessName, sessCardNum, pagination);
          empAccountPopAF.setBstypeMap(BusiTools.listBusiProperty(BusiConst.CLEARACCOUNTBUSINESSTYPE));
          request.setAttribute("empAccountPopAF", empAccountPopAF);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_empaccountpop_show");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "a101.sett_date", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
}
