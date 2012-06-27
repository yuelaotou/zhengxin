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
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.bsinterface.IAssurepledgechgBS;
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.form.AssurepledgechgTaAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;

public class AssurepledgechgTbShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action.AssurepledgechgTbShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    String contractId = (String)request.getSession().getAttribute("contractId");//判断是否为联动
    EndorsecontractTbAF endorsecontractTbAF = new EndorsecontractTbAF();
    List list = new ArrayList();
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
    .getBusinessService("endorsecontractBS", this, mapping
        .getModuleConfig());
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    SecurityInfo securityInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
    if(contractId != null && !"".equals(contractId)){
      endorsecontractTbAF = endorsecontractBS.queryPledgeContractList_Chg(
          contractId.toString(), pagination, securityInfo, request);
      endorsecontractTbAF.setIsReadOnly("0");//大部分只读,只有三个字段可以修改
     }
    Map map = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    endorsecontractTbAF.setMap(map);
    }catch(Exception e){
      e.printStackTrace();
    }
    pagination.getQueryCriterions().put("list", endorsecontractTbAF.getList());// 将列表放在pagination中,点修改按钮的时候取出来
    String tbIsNeedDel=(String)request.getAttribute("tbIsNeedDel");
    endorsecontractTbAF.setIsNeedDel(tbIsNeedDel);
    request.getSession().setAttribute("theEndorsecontractTbAF", endorsecontractTbAF);
    return mapping.findForward("to_assurepledgechgTb");
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
