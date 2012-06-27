package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.business;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBankPara;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.bsinterface.IAheadReturnParamQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.aheadreturnparamquery.dto.AheadReturnParamQueryDTO;

public class AheadReturnParamQueryBS implements IAheadReturnParamQueryBS{
  private LoanBankParaDAO loanBankParaDAO=null;
  private CollBankDAO collBankDAO=null;
  /**
   * 提前还款参数设置查询
   * @author 郭婧平
   * 2007-10-08
   * 根据银行id查pl003表内容
   * 查询条件：loanBankId
   */
  public AheadReturnParamQueryDTO findAheadReturnParamQueryInfo(String loanBankId) throws Exception{
    AheadReturnParamQueryDTO aheadReturnParamQueryDTO=new AheadReturnParamQueryDTO();
    List list=null;
    try{
      if(!loanBankId.equals("")){
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId);
        aheadReturnParamQueryDTO.setTemp_loanBankId(dto.getCollBankName());
        LoanBankPara loanBankPara=null;
        String paramType="B";
        list=loanBankParaDAO.queryParamByLoanBankId(loanBankId,paramType);
        String[] row=new String[7];
        for (int i = 0; i < list.size(); i++) {
          loanBankPara=(LoanBankPara)list.get(i);
          char paramNum=loanBankPara.getParamNum().charAt(0);
          switch(paramNum){
            case '1':{
              aheadReturnParamQueryDTO.setAheadReturnAfter(loanBankPara.getParamValue());
              row[0]="1";
              break;
            }
            case '2':{
              aheadReturnParamQueryDTO.setIsPartAheadReturn(loanBankPara.getParamValue());
              aheadReturnParamQueryDTO.setPartReturnMonthLT(loanBankPara.getParamExplain());
              row[1]="2";
              break;
            }
            case '3':{
              aheadReturnParamQueryDTO.setIsAllReturn(loanBankPara.getParamValue());
              aheadReturnParamQueryDTO.setAllReturnMonthLT(loanBankPara.getParamExplain());
              row[2]="3";
              break;
            }
            case '4':{
              aheadReturnParamQueryDTO.setLeastReturnMoney(new BigDecimal(loanBankPara.getParamExplain()));
              row[3]="4";
              break;
            }
            case '5':{
              aheadReturnParamQueryDTO.setMostAheadReturnYearTimes(loanBankPara.getParamExplain());
              row[4]="5";
              break;
            }
            case '6':{
              aheadReturnParamQueryDTO.setMostAheadReturnTimes(loanBankPara.getParamExplain());
              row[5]="6";
              break;
            }
            case '7':{
              if(!loanBankPara.getParamValue().equals("3")){
                aheadReturnParamQueryDTO.setIsAccept("4");
                aheadReturnParamQueryDTO.setChargeMode(loanBankPara.getParamValue());
                if(loanBankPara.getParamValue().equals("1")){
                  aheadReturnParamQueryDTO.setAheadReturnMoneyPercent(new BigDecimal(loanBankPara.getParamExplain()));
                }
                if(loanBankPara.getParamValue().equals("2")){
                  aheadReturnParamQueryDTO.setMoney(new BigDecimal(loanBankPara.getParamExplain()));
                }
              }else{
                aheadReturnParamQueryDTO.setIsAccept(loanBankPara.getParamValue());
              }
              row[6]="7";
              break;
            }
          }
        }
        aheadReturnParamQueryDTO.setRow(row);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return aheadReturnParamQueryDTO;
  }
  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }
}
