package org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;

public class AssurepledgechgTdShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action.AssurepledgechgTdShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    request.setCharacterEncoding("GB2312");
    Object contractId = (Object)request.getSession().getAttribute("contractId");//判断是否为联动
    EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
    List list = new ArrayList();
    String empId = "";
    empId = (String )request.getParameter("empId");//职工编号
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
    .getBusinessService("endorsecontractBS", this, mapping
        .getModuleConfig());
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    SecurityInfo securityInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
    if(contractId != null && !"".equals(contractId)){
      endorsecontractTdAF = endorsecontractBS.queryAssurerList_Chg(contractId.toString(), pagination, securityInfo, request);
      endorsecontractTdAF.setIsReadOnly("1");
     }
//    if(empId != null && !"".equals(empId)){
//      String preContractId = request.getParameter("contractId");//合同ID
//      String preDebitter = request.getParameter("debitter");//借款人姓名 PL110 
//      preDebitter = new String(preDebitter.getBytes("ISO-8859-1"),"UTF-8");
//      String orgId = request.getParameter("orgId");
//      endorsecontractTdAF = endorsecontractBS.queryAssurerListByEmpId(empId,orgId, pagination, securityInfo, request);
//      endorsecontractTdAF.setIsReadOnly("2");
//      endorsecontractTdAF.setContractId(preContractId);
//      endorsecontractTdAF.setDebitter(preDebitter);
//    }
    }catch(Exception e){
      e.printStackTrace();
    }
    // 证件类型下拉框,性别下拉框
    Map cardMap = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    Map sexMap = BusiTools.listBusiProperty(BusiConst.SEX);
    endorsecontractTdAF.setCardMap(cardMap);
    endorsecontractTdAF.setSexMap(sexMap);
    pagination.getQueryCriterions().put("list", endorsecontractTdAF.getList());// 将列表放在pagination中,点修改按钮的时候取出来
    String tdIsNeedDel=(String)request.getAttribute("tdIsNeedDel");
    endorsecontractTdAF.setIsNeedDel(tdIsNeedDel);
    request.getSession().setAttribute("theEndorsecontractTdAF", endorsecontractTdAF);
    return mapping.findForward("to_assurepledgechgTd");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "null", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
