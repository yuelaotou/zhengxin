package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.business;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBankPara;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.bsinterface.IParamQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.paramquery.dto.ParamQueryDTO;

public class ParamQueryBS implements IParamQueryBS{
  private LoanBankParaDAO loanBankParaDAO=null;
  private CollBankDAO collBankDAO=null;
  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }
  /**
   * 还款参数设置查询
   * @author 郭婧平
   * 2007-10-10
   * 根据银行id查pl003表内容
   * 查询条件：loanBankId
   */
  public ParamQueryDTO findParamQueryInfo(String loanBankId) throws Exception{
    // TODO Auto-generated method stub
    ParamQueryDTO paramQueryDTO=null;
    String paramValue7="";
    List list=null;
    try{
      if(!loanBankId.equals("")){
        paramQueryDTO=new ParamQueryDTO();
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId);
        paramQueryDTO.setTemp_loanBankId(dto.getCollBankName());
        LoanBankPara loanBankPara=null;
        String paramType="A";
        list=loanBankParaDAO.queryParamByLoanBankId(loanBankId,paramType);
        paramValue7=loanBankParaDAO.queryLoanFlow();
        if(!paramValue7.equals("")){
          paramQueryDTO.setLoanVipCheck(paramValue7.substring(0,1));
          paramQueryDTO.setEndorseLoan(paramValue7.substring(1,2));
        }
        for (int i = 0; i < list.size(); i++) {
          loanBankPara=(LoanBankPara)list.get(i);
          char paramNum=loanBankPara.getParamNum().charAt(0);
          switch(paramNum){
            case '1':{
              paramQueryDTO.setKouAccMode(loanBankPara.getParamValue());
              break;
            }
            case '2':{
              paramQueryDTO.setDecideDateMode(loanBankPara.getParamValue());
              if(loanBankPara.getParamValue().equals("2")){
                paramQueryDTO.setUniteDate(loanBankPara.getParamExplain());
              }
              break;
            }
            case '3':{
              if(!loanBankPara.getParamValue().equals("")){
                for(int j=0;j<5;j++){
                  char value=loanBankPara.getParamValue().charAt(j);
                  switch(value){
                    case 'A':paramQueryDTO.setCorpus(new Integer(j+1).toString());break;
                    case 'B':paramQueryDTO.setInterest(new Integer(j+1).toString());break;
                    case 'C':paramQueryDTO.setOverdueCorpus(new Integer(j+1).toString());break;
                    case 'D':paramQueryDTO.setOverdueInterest(new Integer(j+1).toString());break;
                    case 'E':paramQueryDTO.setPunishInterest(new Integer(j+1).toString());break;
                  }
                }
              }
              break;
            }
            case '4':{
              paramQueryDTO.setPunishInterestRateMode(loanBankPara.getParamValue());
              char paramValue=loanBankPara.getParamValue().charAt(0);
              switch (paramValue) {
                case '1':{
                  paramQueryDTO.setPunishInterestDateRate(new BigDecimal(loanBankPara.getParamExplain()));
                  break;
                }
                case '2':{
                  paramQueryDTO.setContractDateRate(new BigDecimal(loanBankPara.getParamExplain()));
                  break;
                }
                case '3':{
                  paramQueryDTO.setMoneyDateInterest(new BigDecimal(loanBankPara.getParamExplain()));
                  break;
                }
              }
              break;
            }
            case '5':{
              paramQueryDTO.setPermitDateNum(loanBankPara.getParamExplain());
              paramQueryDTO.setIsRecord(loanBankPara.getParamValue());
              break;
            }
            case '6':{
              for (int j = 0; j < 5; j++) {
                char paramValue=loanBankPara.getParamValue().charAt(0);
                switch (paramValue) {
                  case '1':{
                    paramQueryDTO.setCommonMonthNum(loanBankPara.getParamExplain());
                    break;
                  }
                  case '2':{
                    paramQueryDTO.setAttentionMonthNum(loanBankPara.getParamExplain());
                    break;
                  }
                  case '3':{
                    paramQueryDTO.setSubMonthNum(loanBankPara.getParamExplain());
                    break;
                  }
                  case '4':{
                    paramQueryDTO.setShadinessMonthNum(loanBankPara.getParamExplain());
                    break;
                  }
                  case '5':{
                    paramQueryDTO.setLossMonthNum(loanBankPara.getParamExplain());
                    break;
                  }
                }
              }
              break;
            }
            case '8':{
              for (int j = 0; j < 2; j++) {
                if(loanBankPara.getParamValue().equals("1")){
                  paramQueryDTO.setCurrentRate(new BigDecimal(loanBankPara.getParamExplain()).multiply(new BigDecimal(100)));
                }
                if(loanBankPara.getParamValue().equals("2")){
                  paramQueryDTO.setTerminalRate(new BigDecimal(loanBankPara.getParamExplain()).multiply(new BigDecimal(100)));
                }
              }
              break;
            }
            case '9':{
              paramQueryDTO.setIsAdopt(loanBankPara.getParamValue());
              break;
            }
          }
          paramQueryDTO.setType("1");
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return paramQueryDTO;
  }
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }
}
