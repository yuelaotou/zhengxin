/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.action;

import java.math.BigDecimal;
import java.util.HashMap;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.bsinterface.IParticularglBS;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.form.ParticularglTaAF;

/** 
 * MyEclipse Struts
 * Creation date: 10-29-2007
 * hanl
 * 显示发生年月
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ParticularglTbShowAC extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.action.ParticularglTbShowAC";
  
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    Pagination paginationta = getPagination(ParticularglTaShowAC.PAGINATION_KEY, request);//ta_jsp的
    
    Pagination pagination = getPagination(PAGINATION_KEY, request);//tb_jsp的
    PaginationUtils.updatePagination(pagination, request);
    String ocyear=(String)request.getParameter("ocyear");
    if(ocyear!=null){
      pagination.getQueryCriterions().put("ocyear", ocyear);
    }
    IParticularglBS particularglBS = (IParticularglBS) BSUtils.getBusinessService("particularglBS", this, mapping.getModuleConfig());
    ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils.getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    ParticularglTaAF particularglTaAF=new ParticularglTaAF();
    try {
      particularglTaAF=particularglBS.showparticularglListTb(pagination,paginationta,securityInfo);
      if(particularglTaAF.getContractid()!=null&&!"".equals(particularglTaAF.getContractid())){
        pagination.getQueryCriterions().put("contractId",particularglTaAF.getContractid());
        LoancallbackTaAF loancallbackTaAF=loancallbackBS.getLoancallbackTaAF(pagination, securityInfo);
        List ll=loancallbackTaAF.getShouldBackList();
        BigDecimal punishinterest=new BigDecimal(0.00);
        for(int j=0;j<ll.size();j++){
          ShouldBackListDTO dto=(ShouldBackListDTO)ll.get(j);
          BigDecimal pi=dto.getPunishInterest();
          punishinterest=punishinterest.add(pi);
        }
        particularglTaAF.setOwepunishinterest(punishinterest.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    request.setAttribute("particularglTaAF",particularglTaAF);
    request.getSession().setAttribute("toprinttbparticularglTaAF", particularglTaAF);
    return mapping.findForward("particulargl_tb_show");
  }
  
   private Pagination getPagination(String paginationKey,HttpServletRequest request) {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      if (pagination == null) {
        pagination = new Pagination(0, 10, 1, " substr(pl202.biz_date, 0, 6) ", "DESC", new HashMap(0));
        request.getSession().setAttribute(paginationKey, pagination);
      }
      return pagination;
    }
}