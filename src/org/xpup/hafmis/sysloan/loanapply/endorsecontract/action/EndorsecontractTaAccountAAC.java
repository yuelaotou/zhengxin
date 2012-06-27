package org.xpup.hafmis.sysloan.loanapply.endorsecontract.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author yuqf
 * 2007-10-05
 */
public class EndorsecontractTaAccountAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");

    String text = null;
    String debitMoneyEndDate = "";
    try {
      String term = (String) request.getParameter("term");
      String debitMoneyStaDate = (String) request.getParameter("debitMoneyStaDate");
      int tempDebitMoneyEndDate = 0;
      String postfix = "";
      int intPostfix = 0;
      if (debitMoneyStaDate != null && !"".equals(debitMoneyStaDate)
          && term != null && !"".equals(term)) {
        postfix = debitMoneyStaDate.substring(6, 8);
        int temp_addYear=(Integer.parseInt(debitMoneyStaDate.substring(4, 6))+Integer.parseInt(term))/12;
        int temp_moth=(Integer.parseInt(debitMoneyStaDate.substring(4, 6))+Integer.parseInt(term))%12;
        intPostfix = Integer.parseInt(postfix);
        Integer tempInteger1 = new Integer(debitMoneyStaDate.substring(0, 6));
        Integer tempInteger2 = new Integer(term);
//        int tempInt1 = tempInteger1.intValue();
//        int tempInt2 = tempInteger2.intValue();
//        tempDebitMoneyEndDate = tempInt1 + tempInt2;
        if(temp_moth<10){
          if(temp_moth==0){
            tempDebitMoneyEndDate =new Integer((Integer.parseInt(debitMoneyStaDate.substring(0,4))+temp_addYear-1)+""+"12").intValue();
          }else{
            tempDebitMoneyEndDate =new Integer((Integer.parseInt(debitMoneyStaDate.substring(0,4))+temp_addYear)+""+"0"+temp_moth).intValue();
          } 
        }else{
          tempDebitMoneyEndDate =new Integer((Integer.parseInt(debitMoneyStaDate.substring(0,4))+temp_addYear)+""+temp_moth).intValue(); 
        }
        String subString = new Integer(tempDebitMoneyEndDate).toString().substring(4, 6);
        if("01".equals(subString) || "03".equals(subString) || "05".equals(subString) || 
            "07".equals(subString) || "08".equals(subString) || "10".equals(subString) || "12".equals(subString)){
          if(intPostfix<=31){
          debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()+postfix;
          }
        }else if("04".equals(subString) || "06".equals(subString) || "09".equals(subString) || "11".equals(subString)){
          if(intPostfix<=30){//如果是小月，判断日期是否小于等于30，是就加上默认的，否就加30
            debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()+postfix;
          }else{
            debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()+"30";
          }
        }else if("02".equals(subString)){
          debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()+"28";
        }
//        debitMoneyEndDate = new Integer(tempDebitMoneyEndDate).toString()+postfix;
      }
//      String term = (String) request.getParameter("term");
//      String debitMoneyStaDate = (String) request.getParameter("debitMoneyStaDate");
//      int termInt = Integer.parseInt(term);
//      if(termInt>=12){
//        
//      }
      

    } catch (Exception e) {
      e.printStackTrace();
    }
    text = "display2('" + debitMoneyEndDate + "');";
    response.getWriter().write(text);
    response.getWriter().close();
    return null;
  }
}
