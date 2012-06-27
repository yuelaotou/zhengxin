package org.xpup.hafmis.sysloan.loanaccord.loanaccord.action;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiTools;

public class LoanaccordInforAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      //获取发放日期
      String tempLoanStartDate=request.getParameter("changeOverTime");
      //获取期限
      String tempLoanTimeLimit=request.getParameter("tempLoanTimeLimit");
//    按户定日还是统一定日
      String loanRepayDayInfo=request.getParameter("loanRepayDayInfo");
      //借款金额
      String temploanMoney=request.getParameter("loanMoney");
      BigDecimal loanMoney=new BigDecimal(0.00);
      if(!temploanMoney.equals(""))loanMoney=new BigDecimal(temploanMoney);
      //新月利率
      String temploanMonthRate=request.getParameter("loanMonthRate"); 
      BigDecimal loanMonthRate=new BigDecimal(0.00);
      if(!temploanMonthRate.equals(""))loanMonthRate=new BigDecimal(temploanMonthRate);
      //新月还本息
      String tempcorpusInterest=request.getParameter("corpusInterest");
      BigDecimal corpusInterest=new BigDecimal(0.00);
      if(!tempcorpusInterest.equals(""))corpusInterest=new BigDecimal(tempcorpusInterest);
      //没有实际意义只用于计算首月还款
      String temploanRepayDay=request.getParameter("temploanRepayDay");
      //还款方式
      String loanMode=request.getParameter("loanMode");
      int temp_addYear=(Integer.parseInt(tempLoanStartDate.substring(4, 6))+Integer.parseInt(tempLoanTimeLimit))/12;
      int temp_moth=(Integer.parseInt(tempLoanStartDate.substring(4, 6))+Integer.parseInt(tempLoanTimeLimit))%12;
      //判断是不是最后一个月的天数，如果是看看你那一个月
      int month_day = BusiTools.daysOfMonth((Integer.parseInt(tempLoanStartDate.substring(0,4))+temp_addYear),temp_moth);
      //还款的最后天数
      String overDay="";
      if(month_day<Integer.parseInt(tempLoanStartDate.substring(6,8))){
        overDay=month_day+"";
      }else{
        overDay=tempLoanStartDate.substring(6,8);
      }
      //到期日期
      String overTime="";
      if(temp_moth<10){
        if(temp_moth==0){
          overTime =(Integer.parseInt(tempLoanStartDate.substring(0,4))+temp_addYear-1)+""+"12"+""+overDay;
        }else{
          overTime =(Integer.parseInt(tempLoanStartDate.substring(0,4))+temp_addYear)+""+"0"+temp_moth+""+overDay;
        } 
      }
      else{
        overTime =(Integer.parseInt(tempLoanStartDate.substring(0,4))+temp_addYear)+""+temp_moth+""+overDay; 
      }
      //还款日
      String loanRepayDay="";
      //按户定日
      if(loanRepayDayInfo.equals("1")){
        loanRepayDay=tempLoanStartDate.substring(6, 8);
        temploanRepayDay=loanRepayDay;
      }
      //计算首月还款金额
      String firstLoanMoney="";
      //计算出发放是月总共多少天:这个月的天数-方法日期+下个还款日
      int tempmonth_day = BusiTools.daysOfMonth((Integer.parseInt(tempLoanStartDate.substring(0, 4))),(Integer.parseInt(tempLoanStartDate.substring(4, 6))));
      int tloanRepayDay=new Integer(temploanRepayDay).intValue();
      if(tloanRepayDay>28){
        //判断第一个月得还款日是不是有这一天。
        int tempnextday=0;
        if(Integer.parseInt(tempLoanStartDate.substring(4, 6))==12){
          tempnextday=BusiTools.daysOfMonth((Integer.parseInt(tempLoanStartDate.substring(0, 4))),Integer.parseInt("01"));   
        }else{
        tempnextday=BusiTools.daysOfMonth((Integer.parseInt(tempLoanStartDate.substring(0, 4))),(Integer.parseInt(tempLoanStartDate.substring(4, 6)))+1);
        }
        if(tloanRepayDay>tempnextday){
          tloanRepayDay=tempnextday;
        }
      }
      int fristday=tempmonth_day-Integer.parseInt(tempLoanStartDate.substring(6,8))+(tloanRepayDay);
      if(loanMode.equals("2")){
        //等额本息
        //按户定日
        if(loanRepayDayInfo.equals("1")){
          //应还利息+应还本金
          firstLoanMoney= (loanMoney.multiply(loanMonthRate)).add(corpusInterest.subtract(loanMoney.multiply(loanMonthRate))).toString();
        }
        //统一定日
        else{
//        应还利息+应还本金
          firstLoanMoney=((loanMoney.multiply(new BigDecimal(fristday+""))).multiply(loanMonthRate).divide(new BigDecimal("30"),2, BigDecimal.ROUND_HALF_UP)).add(corpusInterest.subtract(loanMoney.multiply(loanMonthRate))).toString();
        }
      }else{
        //等额本金
        
        //按户定日
        if(loanRepayDayInfo.equals("1")){
          //等于剩余本金*新利率+月还款金额
          firstLoanMoney=(loanMoney.multiply(loanMonthRate)).add(loanMoney.divide(new BigDecimal(tempLoanTimeLimit),2, BigDecimal.ROUND_HALF_UP)).toString();
        }
        //统一定日
        else{
         
         //
          firstLoanMoney=((loanMoney.multiply(loanMonthRate)).multiply(new BigDecimal(fristday+"")).divide(new BigDecimal("30"), 2, BigDecimal.ROUND_HALF_UP)).add(loanMoney.divide(new BigDecimal(tempLoanTimeLimit),2, BigDecimal.ROUND_HALF_UP)).toString();
        }
      }
      String text = null;
      text = "displays('" + overTime + "','" + loanRepayDay + "','" + firstLoanMoney + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      String text = "backErrors('" + e.getLocalizedMessage() + "')";
      response.getWriter().write(text);
      response.getWriter().close();
    }
    return null;
  }
}
