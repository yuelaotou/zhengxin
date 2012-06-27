package org.xpup.hafmis.sysloan.loanapply.beforeloanapply.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.loanapply.beforeloanapply.dto.BeforeLoanApplyDTO;

public class BeforeLoanApplyShowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loanapply.beforeloanapply.action.BeforeLoanApplyShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      
      String uMaxLoanYear = request.getParameter("uMaxLoanYear");//贷款年限
      String uMaxLoanMoney = request.getParameter("uMaxLoanMoney");//您的公积金可用额度
      String monthBackMoney = request.getParameter("monthBackMoney");//月还本息
      String loanMonthRate = request.getParameter("loanMonthRate");//月利率
      String sun = request.getParameter("sun");
      String sunall = request.getParameter("sunall");
      String sun_yg = request.getParameter("sun_yg");
      String beforeYear = "";
      beforeYear = request.getParameter("beforeYear");//提前还款
      String beforeMoney = "";
      beforeMoney = request.getParameter("beforeMoney");//提前还款金额
      if(sunall!=null){
        if(sunall.equals("0")){
          request.setAttribute("sunall",sunall);
          request.getSession().setAttribute("asunall",sunall);
          request.setAttribute("beforeMoney",beforeMoney);
          request.getSession().setAttribute("abeforeMoney",beforeMoney);
        }else{
          if(beforeMoney!=null && !beforeMoney.equals("")){
            request.setAttribute("beforeMoney",beforeMoney);
            request.getSession().setAttribute("abeforeMoney",beforeMoney);
            beforeMoney=new BigDecimal(beforeMoney).multiply(new BigDecimal(10000)).toString();
            request.setAttribute("sunall",sunall);
            request.getSession().setAttribute("asunall",sunall);
          }
        }
      }

        if((sun_yg!=null && sun_yg.equals("b")) || (sun!=null && sun.equals("a"))){//说明点空计算了，但是没输入提前还款年
          SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
          String bizDate=securityInfo.getUserInfo().getBizDate().substring(0,6);
          List list=this.caculatea(uMaxLoanYear, monthBackMoney, loanMonthRate, uMaxLoanMoney, bizDate);
          List temp_list=new ArrayList();
          if(list.size()<=12){
            for(int i=0;i<list.size();i++){
              temp_list.add(list.get(i));
            }
          }else{
            for(int i=0;i<12;i++){
              temp_list.add(list.get(i));
            }
          }
          request.setAttribute("list",temp_list);
          request.getSession().setAttribute("sun_yg_list", list);
          pagination.setPage(1);
          pagination.setNrOfElements(list.size());
          BeforeLoanApplyDTO dto = new BeforeLoanApplyDTO();
          BigDecimal sumShouldCorpus = new BigDecimal(0.00);
          BigDecimal sumShouldInterest = new BigDecimal(0.00);
          BigDecimal sumMonthBackMoney = new BigDecimal(0.00);
          for(int k=0;k<list.size();k++){
            dto = (BeforeLoanApplyDTO)list.get(k);
            sumShouldCorpus=sumShouldCorpus.add(dto.getShouldCorpus());
            sumShouldInterest=sumShouldInterest.add(dto.getShouldInterest());
            sumMonthBackMoney=sumMonthBackMoney.add(dto.getMonthBackMoney());
          }
          request.setAttribute("sumShouldCorpus", sumShouldCorpus.toString());
          request.setAttribute("sumShouldInterest", sumShouldInterest.toString());
          request.setAttribute("sumMonthBackMoney", sumMonthBackMoney.toString());
        }else if(sun_yg!=null && sun_yg.equals("a")){
          SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute(
          "SecurityInfo");
          String bizDate=securityInfo.getUserInfo().getBizDate().substring(0,6);

          //String lessCorpus=this.caculatea(uMaxLoanMoney, monthBackMoney, loanMonthRate, beforeYear).toString();
          List list=caculate(uMaxLoanYear, monthBackMoney, loanMonthRate, beforeYear, beforeMoney, uMaxLoanMoney, bizDate);
          List temp_list=new ArrayList();
          if(list.size()<=12){
            for(int i=0;i<list.size();i++){
              temp_list.add(list.get(i));
            }
          }else{
            for(int i=0;i<12;i++){
              temp_list.add(list.get(i));
            }
          }
          BeforeLoanApplyDTO dto = new BeforeLoanApplyDTO();
          BigDecimal sumShouldCorpus = new BigDecimal(0.00);
          BigDecimal sumShouldInterest = new BigDecimal(0.00);
          BigDecimal sumMonthBackMoney = new BigDecimal(0.00);
          for(int k=0;k<list.size();k++){
            dto = (BeforeLoanApplyDTO)list.get(k);
            sumShouldCorpus=sumShouldCorpus.add(dto.getShouldCorpus());
            sumShouldInterest=sumShouldInterest.add(dto.getShouldInterest());
            sumMonthBackMoney=sumMonthBackMoney.add(dto.getMonthBackMoney());
          }
          request.setAttribute("sumShouldCorpus", sumShouldCorpus.toString());
          request.setAttribute("sumShouldInterest", sumShouldInterest.toString());
          request.setAttribute("sumMonthBackMoney", sumMonthBackMoney.toString());
          request.setAttribute("list",temp_list);
          request.getSession().setAttribute("sun_yg_list", list);
          pagination.setPage(1);
          pagination.setNrOfElements(list.size());
        }else{
          List sun_yg_list=(List)request.getSession().getAttribute("sun_yg_list");
          List temp_list=new ArrayList();
          if(sun_yg_list.size()<=12){
            for(int i=0;i<sun_yg_list.size();i++){
              temp_list.add(sun_yg_list.get(i));
            }
          }else{
            for(int i=(pagination.getPage()-1)*12;i<pagination.getPage()*12;i++){
              if(i==sun_yg_list.size())
                break;
              temp_list.add(sun_yg_list.get(i));
            }
          }
          
          BeforeLoanApplyDTO dto = new BeforeLoanApplyDTO();
          BigDecimal sumShouldCorpus = new BigDecimal(0.00);
          BigDecimal sumShouldInterest = new BigDecimal(0.00);
          BigDecimal sumMonthBackMoney = new BigDecimal(0.00);
          for(int k=0;k<sun_yg_list.size();k++){
            dto = (BeforeLoanApplyDTO)sun_yg_list.get(k);
            sumShouldCorpus=sumShouldCorpus.add(dto.getShouldCorpus());
            sumShouldInterest=sumShouldInterest.add(dto.getShouldInterest());
            sumMonthBackMoney=sumMonthBackMoney.add(dto.getMonthBackMoney());
          }
          request.setAttribute("sumShouldCorpus", sumShouldCorpus.toString());
          request.setAttribute("sumShouldInterest", sumShouldInterest.toString());
          request.setAttribute("sumMonthBackMoney", sumMonthBackMoney.toString());
          
          request.setAttribute("list",temp_list);
          pagination.setNrOfElements(sun_yg_list.size());
          uMaxLoanYear =(String)request.getSession().getAttribute("auMaxLoanYear");
          uMaxLoanMoney =(String)request.getSession().getAttribute("auMaxLoanMoney");
          monthBackMoney =(String)request.getSession().getAttribute("amonthBackMoney");
          loanMonthRate =(String)request.getSession().getAttribute("aloanMonthRate");
          beforeYear =(String)request.getSession().getAttribute("abeforeYear");
          beforeMoney =(String)request.getSession().getAttribute("abeforeMoney");
          sunall =(String)request.getSession().getAttribute("asunall");
          request.setAttribute("sunall",sunall);
//          System.out.println("sunall.."+sunall);
          request.setAttribute("beforeMoney",beforeMoney);
        }
      
      request.setAttribute("uMaxLoanYear",uMaxLoanYear);
      request.setAttribute("uMaxLoanMoney",uMaxLoanMoney);
      request.setAttribute("monthBackMoney",monthBackMoney);
      request.setAttribute("loanMonthRate",loanMonthRate);
      request.setAttribute("beforeYear",beforeYear);
      
      request.getSession().setAttribute("auMaxLoanYear",uMaxLoanYear);
      request.getSession().setAttribute("auMaxLoanMoney",uMaxLoanMoney);
      request.getSession().setAttribute("amonthBackMoney",monthBackMoney);
      request.getSession().setAttribute("aloanMonthRate",loanMonthRate);
      request.getSession().setAttribute("abeforeYear",beforeYear);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("beforeloanapply");
  }
 
  private List caculate(String uMaxLoanYear,String monthBackMoney,String loanMonthRate,String beforeYear,String beforeMoney,String uMaxLoanMoney,String bizDate){

    BeforeLoanApplyDTO dto = null;
    List list=new ArrayList();
    String date=bizDate;
    
    BigDecimal shouldCorpus = new BigDecimal(0.00);
    BigDecimal shouldCorpusa = new BigDecimal(0.00);
    int lessyeara=Integer.parseInt(beforeYear);
    BigDecimal uMaxLoanMoney_temp=new BigDecimal(uMaxLoanMoney);
    for(int i=0;i<lessyeara*12;i++){
      dto = new BeforeLoanApplyDTO();
      BigDecimal shouldInterest = uMaxLoanMoney_temp.multiply(new BigDecimal(loanMonthRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
      shouldCorpus = new BigDecimal(monthBackMoney).subtract(shouldInterest);
      uMaxLoanMoney_temp=uMaxLoanMoney_temp.subtract(shouldCorpus);
      shouldCorpusa=shouldCorpusa.add(shouldCorpus);
      dto.setShouldInterest(shouldInterest);
      dto.setShouldCorpus(shouldCorpus);
      dto.setMonthBackMoney(new BigDecimal(monthBackMoney));
      date=BusiTools.addMonth(date,1);
      dto.setPayMonth(date);
      list.add(dto);
    }
    int lessyear=Integer.parseInt(uMaxLoanYear)-Integer.parseInt(beforeYear);
    BigDecimal lessCorpus_temp=new BigDecimal(uMaxLoanMoney).subtract(shouldCorpusa);//贷款额度-提前还的本金
    
    if(beforeMoney.equals(uMaxLoanMoney)){//说明一次性清还
      dto = new BeforeLoanApplyDTO();
      //BigDecimal shouldInterest = lessCorpus_temp.multiply(new BigDecimal(loanMonthRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
      dto.setShouldInterest(new BigDecimal(0.00));
      dto.setShouldCorpus(lessCorpus_temp);
      dto.setMonthBackMoney(dto.getShouldCorpus().add(dto.getShouldInterest()));
      dto.setPayMonth(date);
      list.add(dto);
    }else{
      dto = new BeforeLoanApplyDTO();
      BigDecimal shouldInterest = lessCorpus_temp.multiply(new BigDecimal(loanMonthRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
      dto.setShouldInterest(new BigDecimal(0.00));
      dto.setShouldCorpus(new BigDecimal(beforeMoney));
      dto.setMonthBackMoney(dto.getShouldCorpus().add(dto.getShouldInterest()));
      dto.setPayMonth(date);
      list.add(dto);
      lessCorpus_temp=lessCorpus_temp.subtract(new BigDecimal(beforeMoney));
      BigDecimal corpusInterest = CorpusinterestBS.getCorpusInterest(new BigDecimal(lessCorpus_temp+""), new BigDecimal(loanMonthRate), lessyear*12+"");
      for(int i=0;i<lessyear*12;i++){
        dto = new BeforeLoanApplyDTO();
        shouldInterest = lessCorpus_temp.multiply(new BigDecimal(loanMonthRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
        dto.setMonthBackMoney(corpusInterest);
        dto.setShouldInterest(shouldInterest);
        dto.setShouldCorpus(dto.getMonthBackMoney().subtract(dto.getShouldInterest()));
        date=BusiTools.addMonth(date,1);
        dto.setPayMonth(date);
        if(i==lessyear*12-1){
          dto.setShouldInterest(shouldInterest);
          dto.setShouldCorpus(lessCorpus_temp);
          dto.setMonthBackMoney(lessCorpus_temp.add(shouldInterest));
        }
        list.add(dto);
        lessCorpus_temp=lessCorpus_temp.subtract(dto.getShouldCorpus());
      }
    }
    return list;
  }
  private List caculatea(String uMaxLoanYear,String monthBackMoney,String loanMonthRate,String uMaxLoanMoney,String bizDate){
    
    BeforeLoanApplyDTO dto = null;
    List list=new ArrayList();
    String date=bizDate;
    
    BigDecimal shouldCorpus = new BigDecimal(0.00);
    BigDecimal shouldCorpusa = new BigDecimal(0.00);
    int lessyeara=Integer.parseInt(uMaxLoanYear);
    BigDecimal uMaxLoanMoney_temp=new BigDecimal(uMaxLoanMoney);
    BigDecimal lastmoney_temp=new BigDecimal(0.00);
    for(int i=0;i<lessyeara*12;i++){
      dto = new BeforeLoanApplyDTO();
      BigDecimal shouldInterest = uMaxLoanMoney_temp.multiply(new BigDecimal(loanMonthRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
      shouldCorpus = new BigDecimal(monthBackMoney).subtract(shouldInterest);
      uMaxLoanMoney_temp=uMaxLoanMoney_temp.subtract(shouldCorpus);
      shouldCorpusa=shouldCorpusa.add(shouldCorpus);
      dto.setShouldInterest(shouldInterest);
      dto.setShouldCorpus(shouldCorpus);
      dto.setMonthBackMoney(new BigDecimal(monthBackMoney));
      date=BusiTools.addMonth(date,1);
      dto.setPayMonth(date);
      if(i==lessyeara*12-1){
        dto.setShouldInterest(shouldInterest);
        dto.setShouldCorpus(lastmoney_temp);
        dto.setMonthBackMoney(lastmoney_temp.add(shouldInterest));
      }
      lastmoney_temp=uMaxLoanMoney_temp;
      list.add(dto);
    }
    return list;
  }
  
  private Pagination getPagination(String paginationKey,//初始化
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {        
      pagination = new Pagination(0, 12, 1, " ", "DESC", new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }
  
  
}