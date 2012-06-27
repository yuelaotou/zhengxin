package org.xpup.hafmis.sysloan.loancallback.loancallback.action; 

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loancallback.bsinterface.ILoancallbackBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class LoancallbackTaAheadAAC extends Action{
  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        String text="";
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        String aheadMoney=(String)request.getParameter("aheadMoney");
        String deadLine=(String)request.getParameter("deadLine");
        String type=(String)request.getParameter("type");
        String cIMoney = (String)request.getParameter("cIMoney");
        
        // ÎâºéÌÎÐÞ¸Ä//2007-3-12
        String monthYear=(String)request.getParameter("monthYear");
        String contractId=(String)request.getParameter("contractId");
        String sumcorpus = (String)request.getParameter("sumCorpus");
        String suminterest = (String)request.getParameter("sumInterest");
        String loanMode = (String)request.getParameter("loanMode");
        
        // ÎâºéÌÎÐÞ¸Ä//2007-3-12
        
        String paginationKey = getPaginationKey();
        Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
        pagination.getQueryCriterions().put("aheadMoney", aheadMoney);
        LoancallbackTaAF af = new LoancallbackTaAF(); 
        ILoancallbackBS loancallbackBS = (ILoancallbackBS) BSUtils
        .getBusinessService("loancallbackBS", this, mapping.getModuleConfig());
        try {
          if(!"1".equals(type)&&cIMoney!=null){
            pagination.getQueryCriterions().put("deadLine", deadLine);
            pagination.getQueryCriterions().put("cIMoney", cIMoney);
            BigDecimal corpusInterest = loancallbackBS.getCorpusInterest(pagination, securityInfo);
            
            // ÎâºéÌÎÐÞ¸Ä//2007-3-12
            LoancallbackTaAF loancallbackTaAF = new LoancallbackTaAF(); 
            loancallbackTaAF=loancallbackBS.findShouldLoancallbackInfo_wuht( loanMode,suminterest,corpusInterest.toString(),monthYear,
                 contractId, deadLine, sumcorpus, 
                 securityInfo);
            BigDecimal overplusInterestall=new BigDecimal(0.00);
            BigDecimal interestall=new BigDecimal(0.00);
            if(loancallbackTaAF!=null){
              overplusInterestall=loancallbackTaAF.getOverplusInterestAll();
              interestall=loancallbackTaAF.getInterestAll();
            }
            // ÎâºéÌÎÐÞ¸Ä//2007-3-12
            
            text="display1('"+corpusInterest+"','"+overplusInterestall+"','"+interestall+"','"+"');";
          }else{
             af = loancallbackBS.findShouldLoancallbackInfo(pagination, securityInfo);
             String days = af.getDays();
             String aheadInterest = af.getAheadInterest().toString();
             String loanPoundageMoney = af.getLoanPoundageMoney().toString();
             deadLine = af.getDeadLine();
             String corpusInterest = af.getCorpusInterest().toString();
             String sumCorpus = af.getSumCorpus().toString();
             String sumInterest = af.getSumInterest().toString();
             String sumMoney = af.getSumMoney().toString();
             String ovaerLoanRepay = af.getOvaerLoanRepay().toString();
             String realMoney = af.getRealMoney().toString();
             String overOccurMoney = af.getOverOccurMoney().toString();
             
             // ÎâºéÌÎÐÞ¸Ä//2007-3-11
             String overplusInterestAll = af.getOverplusInterestAll().toString();// Ê£ÓàÀûÏ¢
             String interestAll = af.getInterestAll().toString();// ×ÜÀûÏ¢
             // ÎâºéÌÎÐÞ¸Ä//2007-3-11 

             
            text = "displays('"+aheadMoney+"','"+days+"','"+aheadInterest+"','"+loanPoundageMoney+"','"+deadLine
            +"','"+corpusInterest+"','"+sumCorpus+"','"+sumInterest+"','"+sumMoney+"','"+ovaerLoanRepay+"'," +
                "'"+realMoney+"','"+overplusInterestAll+"','"+interestAll+"','"+overOccurMoney+"');";
          }
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

  protected String getPaginationKey() {
    return LoancallbackTaShowAC.PAGINATION_KEY;
 }
}