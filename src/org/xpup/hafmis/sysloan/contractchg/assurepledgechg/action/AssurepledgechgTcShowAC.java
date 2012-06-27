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
/**
 * 
 * @author yuqf
 *
 */
public class AssurepledgechgTcShowAC extends Action{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action.AssurepledgechgTcShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    String contractId = (String)request.getSession().getAttribute("contractId");//判断是否为联动
    EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
    List list = new ArrayList();
    IEndorsecontractBS endorsecontractBS = (IEndorsecontractBS) BSUtils
    .getBusinessService("endorsecontractBS", this, mapping
        .getModuleConfig());
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    SecurityInfo securityInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
    if(contractId != null && !"".equals(contractId)){
      endorsecontractTcAF = endorsecontractBS.queryImpawnContractList_Chg(
          contractId.toString(), pagination, securityInfo, request);
      endorsecontractTcAF.setIsReadOnly("1");//修改
//      endorsecontractTcAF.setIsReadOnly("0");
     }
    }catch(Exception e){
      e.printStackTrace();
    }
    Map map = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
    endorsecontractTcAF.setMap(map);
    pagination.getQueryCriterions().put("list", endorsecontractTcAF.getList());// 将列表放在pagination中,点修改按钮的时候取出来
    String tcIsNeedDel=(String)request.getAttribute("tcIsNeedDel");
    endorsecontractTcAF.setIsNeedDel(tcIsNeedDel);
    request.getSession().setAttribute("theEndorsecontractTcAF", endorsecontractTcAF);
    return mapping.findForward("to_assurepledgechgTc");
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
