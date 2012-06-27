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
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.bsinterface.IAssurepledgechgBS;
import org.xpup.hafmis.sysloan.contractchg.assurepledgechg.form.AssurepledgechgTaAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface.IEndorsecontractBS;
/**
 * 
 * @author yuqf
 *2007-10-08
 */
public class AssurepledgechgTaShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action.AssurepledgechgTaShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    // TODO Auto-generated method stub
    AssurepledgechgTaAF assurepledgechgTaAF = new AssurepledgechgTaAF();
    List list = new ArrayList();
    IAssurepledgechgBS assurepledgechgBS = (IAssurepledgechgBS) BSUtils
    .getBusinessService("assurepledgechgBS", this, mapping
        .getModuleConfig());
    Pagination pagination = getPagination(PAGINATION_KEY, request);
    PaginationUtils.updatePagination(pagination, request);
    SecurityInfo securityInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try{
      Map map = BusiTools.listBusiProperty(BusiConst.PLHOUSETYPE);
      assurepledgechgTaAF.setMap(map);
      list = assurepledgechgBS.defaultQueryBorrowerAccList(pagination, securityInfo);
      assurepledgechgTaAF.setList(list);
    }catch(Exception e){
      e.printStackTrace();
    }
    request.getSession().setAttribute("theAssurepledgechgTaAF",
        assurepledgechgTaAF);
    return mapping.findForward("to_assurepledgechgTa");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "t1.contract_id", "ASC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }

    return pagination;
  }
}
