package org.xpup.hafmis.sysloan.dataready.param.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanContractParaDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanBankPara;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanConditionsSet;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanContractPara;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.param.bsinterface.IParamBS;
import org.xpup.hafmis.sysloan.dataready.param.dto.AheadReturnParamDTO;
import org.xpup.hafmis.sysloan.dataready.param.dto.ParamDTO;
import org.xpup.security.common.domain.Userslogincollbank;


public class ParamBS implements IParamBS{
  private LoanBankParaDAO loanBankParaDAO=null;
  private PlOperateLogDAO plOperateLogDAO=null;
  private LoanContractParaDAO loanContractParaDAO=null;
  private BorrowerAccDAO borrowerAccDAO=null;
  /**
   * 参数维护
   * @author 郭婧平
   * 2007-9-29
   * 根据银行id查pl003表内容
   * 查询条件：loanBankId
   */
  public ParamDTO findParamInfo(String loanBankId,SecurityInfo securityInfo) throws Exception{
    // TODO Auto-generated method stub
    ParamDTO paramDTO=new ParamDTO();
    String paramValue7="";
    List list=null;
    List loanbankList = securityInfo.getDkUserBankList();
    try{
      LoanBankPara loanBankPara=null;
      String paramType="A";
      if(false){
//        Userslogincollbank bank = null;
//        Userslogincollbank collbank=new Userslogincollbank();
//        collbank.setCollBankId(new Integer("100"));
//        collbank.setCollBankName("全部");
//        loanbankList.add(collbank);
//        Iterator itr1 = loanbankList.iterator();
//        
//          for(int m=0;m<loanbankList.size();m++){
//            bank = (Userslogincollbank) itr1.next();
//            loanBankId=bank.getCollBankId().toString();
//            list=loanBankParaDAO.queryParamByLoanBankId(loanBankId,paramType);
//            paramValue7=checkLoanFlow();
//            if(!paramValue7.equals("")){
//              paramDTO.setLoanVipCheck(paramValue7.substring(0,1));
//              paramDTO.setEndorseLoan(paramValue7.substring(1,2));
//            }
//            for (int i = 0; i < list.size(); i++) {
//              loanBankPara=(LoanBankPara)list.get(i);
//              int paramNum=Integer.parseInt(loanBankPara.getParamNum());
//              switch(paramNum){
//                case 1:{
//                  if("".equals(paramDTO.getKouAccMode())){
//                    paramDTO.setKouAccMode(loanBankPara.getParamValue());
//                  }
//                  if(!"false".equals(paramDTO.getKouAccMode())){
//                    if(!paramDTO.getKouAccMode().equals(loanBankPara.getParamValue())){
//                      paramDTO.setKouAccMode("false");
//                    }
//                  }
//                  break;
//                }
//                case 2:{
//                  
//                  if("".equals(paramDTO.getDecideDateMode())){
//                    paramDTO.setDecideDateMode(loanBankPara.getParamValue());
//                  }
//                  if(!"false".equals(paramDTO.getDecideDateMode())){
//                    if(!paramDTO.getDecideDateMode().equals(loanBankPara.getParamValue())){
//                      paramDTO.setDecideDateMode("false");
//                    }
//                  }
//                  if( !paramDTO.getDecideDateMode().equals("false")){
//                    if(loanBankPara.getParamValue().equals("2")){
//                      if("".equals(paramDTO.getUniteDate())){
//                        paramDTO.setUniteDate(loanBankPara.getParamExplain());
//                      }
//                      if(!"false".equals(paramDTO.getUniteDate())){
//                        if(!paramDTO.getUniteDate().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setUniteDate("false");
//                        }
//                      }
//                    }
//                  }
//                  
//                  break;
//                }
//                case 3:{
//                  if(!loanBankPara.getParamValue().equals("")){
//                    for(int j=0;j<5;j++){
//                      char value=loanBankPara.getParamValue().charAt(j);
//                      switch(value){
//                        case 'A':
//                          if("".equals(paramDTO.getCorpus())){
//                            paramDTO.setCorpus(new Integer(j+1).toString());
//                          }
//                          if(!"false".equals(paramDTO.getCorpus())){
//                            if(!paramDTO.getCorpus().equals(new Integer(j+1).toString())){
//                              paramDTO.setCorpus("false");
//                            }
//                          }
//                          break;
//                        case 'B':
//                          if("".equals(paramDTO.getInterest())){
//                            paramDTO.setInterest(new Integer(j+1).toString());
//                          }
//                          if(!"false".equals(paramDTO.getInterest())){
//                            if(!paramDTO.getInterest().equals(new Integer(j+1).toString())){
//                              paramDTO.setInterest("false");
//                            }
//                          }
//                          break;
//                        case 'C':
//                          if("".equals(paramDTO.getOverdueCorpus())){
//                            paramDTO.setOverdueCorpus(new Integer(j+1).toString());
//                          }
//                          if(!"false".equals(paramDTO.getOverdueCorpus())){
//                            if(!paramDTO.getOverdueCorpus().equals(new Integer(j+1).toString())){
//                              paramDTO.setOverdueCorpus("false");
//                            }
//                          }
//                          break;
//                        case 'D':
//                          if("".equals(paramDTO.getOverdueInterest())){
//                            paramDTO.setOverdueInterest(new Integer(j+1).toString());
//                          }
//                          if(!"false".equals(paramDTO.getOverdueInterest())){
//                            if(!paramDTO.getOverdueInterest().equals(new Integer(j+1).toString())){
//                              paramDTO.setOverdueInterest("false");
//                            }
//                          }
//                          break;
//                        case 'E':
//                          if("".equals(paramDTO.getPunishInterest())){
//                            paramDTO.setPunishInterest(new Integer(j+1).toString());
//                          }
//                          if(!"false".equals(paramDTO.getPunishInterest())){
//                            if(!paramDTO.getPunishInterest().equals(new Integer(j+1).toString())){
//                              paramDTO.setPunishInterest("false");
//                            }
//                          }
//                          break;
//                      }
//                    }
//                  }
//                  break;
//                }
//                case 4:{
//                  if("".equals(paramDTO.getPunishInterestRateMode())){
//                    paramDTO.setPunishInterestRateMode(loanBankPara.getParamValue());
//                  }
//                  if(!"false".equals(paramDTO.getPunishInterestRateMode())){
//                    if(!paramDTO.getPunishInterestRateMode().equals(loanBankPara.getParamExplain())){
//                      paramDTO.setPunishInterestRateMode("false");
//                    }
//                  }
//                  if("".equals(paramDTO.getPunishInterestRateMode_1())){
//                    paramDTO.setPunishInterestRateMode_1(loanBankPara.getParamValue());
//                  }
//                  if(!"false".equals(paramDTO.getPunishInterestRateMode_1())){
//                    if(!paramDTO.getPunishInterestRateMode_1().equals(loanBankPara.getParamExplain())){
//                      paramDTO.setPunishInterestRateMode_1("false");
//                    }
//                  }
//                  char paramValue=loanBankPara.getParamValue().charAt(0);
//                  switch (paramValue) {
//                    case '1':{
//                      
//                      if("".equals(paramDTO.getPunishInterestDateRate())){
//                        paramDTO.setPunishInterestDateRate(new BigDecimal(loanBankPara.getParamExplain()));
//                      }
//                      if(paramDTO.getPunishInterestDateRate().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getPunishInterestDateRate().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setPunishInterestDateRate(new BigDecimal(-1));
//                        }
//                      }
//                      if(!itr1.hasNext()&&paramDTO.getPunishInterestDateRate().compareTo(new BigDecimal(-1))==0){
//                        paramDTO.setPunishInterestDateRate(new BigDecimal(0));
//                      }
//                      
//                      if("".equals(paramDTO.getPunishInterestDateRate_1())){
//                        paramDTO.setPunishInterestDateRate_1(new BigDecimal(loanBankPara.getParamExplain()));
//                      }
//                      if(paramDTO.getPunishInterestDateRate_1().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getPunishInterestDateRate_1().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setPunishInterestDateRate_1(new BigDecimal(-1));
//                        }
//                      }
//                      if(!itr1.hasNext()&&paramDTO.getPunishInterestDateRate_1().compareTo(new BigDecimal(-1))==0){
//                        paramDTO.setPunishInterestDateRate_1(new BigDecimal(0));
//                      }
//                      break;
//                    }
//                    case '2':{
//                      
//                      if("".equals(paramDTO.getContractDateRate())){
//                        paramDTO.setContractDateRate(new BigDecimal(loanBankPara.getParamExplain()));
//                      }
//                      if(paramDTO.getContractDateRate().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getContractDateRate().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setContractDateRate(new BigDecimal(-1));
//                        }
//                      }
//                      if(!itr1.hasNext()&&paramDTO.getContractDateRate().compareTo(new BigDecimal(-1))==0){
//                        paramDTO.setContractDateRate(new BigDecimal(0));
//                      }
//                      if("".equals(paramDTO.getContractDateRate_1())){
//                        paramDTO.setContractDateRate_1(new BigDecimal(loanBankPara.getParamExplain()));
//                      }
//                      if(paramDTO.getContractDateRate_1().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getContractDateRate_1().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setContractDateRate_1(new BigDecimal(-1));
//                        }
//                      }
//                      if(!itr1.hasNext()&&paramDTO.getContractDateRate_1().compareTo(new BigDecimal(-1))==0){
//                        paramDTO.setContractDateRate_1(new BigDecimal(0));
//                      }
//                      break;
//                    }
//                    case '3':{
//                      if("".equals(paramDTO.getMoneyDateInterest())){
//                        paramDTO.setMoneyDateInterest(new BigDecimal(loanBankPara.getParamExplain()));
//                      }
//                      if(paramDTO.getMoneyDateInterest().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getMoneyDateInterest().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setMoneyDateInterest(new BigDecimal(-1));
//                        }
//                      }
//                      
//                      if("".equals(paramDTO.getMoneyDateInterest_1())){
//                        paramDTO.setMoneyDateInterest_1(new BigDecimal(loanBankPara.getParamExplain()));
//                      }
//                      if(paramDTO.getMoneyDateInterest_1().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getMoneyDateInterest_1().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setMoneyDateInterest_1(new BigDecimal(-1));
//                        }
//                      }
//                      break;
//                    }
//                  }
//                  break;
//                }
//                case 5:{
//                  if("".equals(paramDTO.getPermitDateNum())){
//                    paramDTO.setPermitDateNum(loanBankPara.getParamExplain());
//                  }
//                  if(!"false".equals(paramDTO.getPermitDateNum())){
//                    if(!paramDTO.getPermitDateNum().equals(loanBankPara.getParamExplain())){
//                      paramDTO.setPermitDateNum("false");
//                    }
//                  }
//                  if(m==4&&paramDTO.getPermitDateNum().equals("false")){
//                    System.out.println("fg");
//                    paramDTO.setPermitDateNum("");
//                  }
//                  if("".equals(paramDTO.getIsRecord())){
//                    paramDTO.setIsRecord(loanBankPara.getParamValue());
//                  }
//                  if(!"false".equals(paramDTO.getIsRecord())){
//                    if(!paramDTO.getIsRecord().equals(loanBankPara.getParamValue())){
//                      paramDTO.setIsRecord("false");
//                    }
//                  }
//                  break;
//                }
//                case 6:{
//                  for (int j = 0; j < 5; j++) {
//                    char paramValue=loanBankPara.getParamValue().charAt(0);
//                    switch (paramValue) {
//                      case '1':{
//                        if("".equals(paramDTO.getCommonMonthNum())){
//                          paramDTO.setCommonMonthNum(loanBankPara.getParamExplain());
//                        }
//                        if(!"false".equals(paramDTO.getCommonMonthNum())){
//                          if(!paramDTO.getCommonMonthNum().equals(loanBankPara.getParamExplain())){
//                            paramDTO.setCommonMonthNum("false");
//                          }
//                        }
//                        break;
//                      }
//                      case '2':{
//                        if("".equals(paramDTO.getAttentionMonthNum())){
//                          paramDTO.setAttentionMonthNum(loanBankPara.getParamExplain());
//                        }
//                        if(!"false".equals(paramDTO.getAttentionMonthNum())){
//                          if(!paramDTO.getAttentionMonthNum().equals(loanBankPara.getParamExplain())){
//                            paramDTO.setAttentionMonthNum("false");
//                          }
//                        }
//                        break;
//                      }
//                      case '3':{
//                        if("".equals(paramDTO.getSubMonthNum())){
//                          paramDTO.setSubMonthNum(loanBankPara.getParamExplain());
//                        }
//                        if(!"false".equals(paramDTO.getSubMonthNum())){
//                          if(!paramDTO.getSubMonthNum().equals(loanBankPara.getParamExplain())){
//                            paramDTO.setSubMonthNum("false");
//                          }
//                        }
//                        break;
//                      }
//                      case '4':{
//                        if("".equals(paramDTO.getShadinessMonthNum())){
//                          paramDTO.setShadinessMonthNum(loanBankPara.getParamExplain());
//                        }
//                        if(!"false".equals(paramDTO.getShadinessMonthNum())){
//                          if(!paramDTO.getShadinessMonthNum().equals(loanBankPara.getParamExplain())){
//                            paramDTO.setShadinessMonthNum("false");
//                          }
//                        }
//                        break;
//                      }
//                      case '5':{
//                        if("".equals(paramDTO.getLossMonthNum())){
//                          paramDTO.setLossMonthNum(loanBankPara.getParamExplain());
//                        }
//                        if(!"false".equals(paramDTO.getLossMonthNum())){
//                          if(!paramDTO.getLossMonthNum().equals(loanBankPara.getParamExplain())){
//                            paramDTO.setLossMonthNum("false");
//                          }
//                        }
//                        break;
//                      }
//                    }
//                  }
//                  break;
//                }
//                case 8:{
//                  for (int j = 0; j < 2; j++) {
//                    if(loanBankPara.getParamValue().equals("1")){
//                      if("".equals(paramDTO.getCurrentRate())){
//                        paramDTO.setCurrentRate(new BigDecimal(loanBankPara.getParamExplain()).multiply(new BigDecimal(100)));
//                      }
//                      if(paramDTO.getCurrentRate().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getCurrentRate().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setCurrentRate(new BigDecimal(-1));
//                        }
//                      }
//                      
//                    }
//                    if(loanBankPara.getParamValue().equals("2")){
//                      if("".equals(paramDTO.getTerminalRate())){
//                        paramDTO.setTerminalRate(new BigDecimal(loanBankPara.getParamExplain()).multiply(new BigDecimal(100)));
//                      }
//                      if(paramDTO.getTerminalRate().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getTerminalRate().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setTerminalRate(new BigDecimal(-1));
//                        }
//                      }
//                    }
//                  }
//                  break;
//                }
//                case 9:{
//                  if("".equals(paramDTO.getIsAdopt())){
//                    paramDTO.setIsAdopt(loanBankPara.getParamValue());
//                  }
//                  if(!"false".equals(paramDTO.getIsAdopt())){
//                    if(!paramDTO.getIsAdopt().equals(loanBankPara.getParamValue())){
//                      paramDTO.setIsAdopt("false");
//                    }
//                  }
//                  break;
//                }
//                case 10:{
//                  if("".equals(paramDTO.getIsAdopt1())){
//                    paramDTO.setIsAdopt1(loanBankPara.getParamValue());
//                  }
//                  if(!"false".equals(paramDTO.getIsAdopt1())){
//                    if(!paramDTO.getIsAdopt1().equals(loanBankPara.getParamValue())){
//                      paramDTO.setIsAdopt1("false");
//                    }
//                  }
//                  break;
//                }
//                case 11:{
//                  if("".equals(paramDTO.getPunishInterestRateMode1())){
//                    paramDTO.setPunishInterestRateMode1(loanBankPara.getParamValue());
//                  }
//                  if(!"false".equals(paramDTO.getPunishInterestRateMode1())){
//                    if(!paramDTO.getPunishInterestRateMode1().equals(loanBankPara.getParamValue())){
//                      paramDTO.setPunishInterestRateMode1("false");
//                    }
//                  }
//                  char paramValue=loanBankPara.getParamValue().charAt(0);
//                  switch (paramValue) {
//                    case '1':{
//                      if("".equals(paramDTO.getPunishInterestDateRate1())){
//                        paramDTO.setTerminalRate(new BigDecimal(loanBankPara.getParamExplain()));
//                      }
//                      if(paramDTO.getPunishInterestDateRate1().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getPunishInterestDateRate1().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setTerminalRate(new BigDecimal(-1));
//                        }
//                      }
//                      break;
//                    }
//                    case '2':{
//                      if("".equals(paramDTO.getContractDateRate1())){
//                        paramDTO.setContractDateRate1(new BigDecimal(loanBankPara.getParamExplain()));
//                      }
//                      if(paramDTO.getContractDateRate1().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getContractDateRate1().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setContractDateRate1(new BigDecimal(-1));
//                        }
//                      }
//                      break;
//                    }
//                    case '3':{
//                      if("".equals(paramDTO.getMoneyDateInterest1())){
//                        paramDTO.setMoneyDateInterest1(new BigDecimal(loanBankPara.getParamExplain()));
//                      }
//                      if(paramDTO.getMoneyDateInterest1().compareTo(new BigDecimal(-1))!=0){
//                        if(!paramDTO.getMoneyDateInterest1().equals(loanBankPara.getParamExplain())){
//                          paramDTO.setMoneyDateInterest1(new BigDecimal(-1));
//                        }
//                      }
//                      break;
//                    }
//                  }
//                  break;
//                }
//                case 12:{
//                  if("".equals(paramDTO.getPermitDateNum1())){
//                    paramDTO.setPermitDateNum1(loanBankPara.getParamExplain());
//                  }
//                  if(!"false".equals(paramDTO.getPermitDateNum1())){
//                    if(!paramDTO.getPermitDateNum1().equals(loanBankPara.getParamExplain())){
//                      paramDTO.setPermitDateNum1("false");
//                    }
//                  }
//                  if("".equals(paramDTO.getIsRecord1())){
//                    paramDTO.setIsRecord1(loanBankPara.getParamValue());
//                  }
//                  if(!"false".equals(paramDTO.getIsRecord1())){
//                    if(!paramDTO.getIsRecord1().equals(loanBankPara.getParamValue())){
//                      paramDTO.setIsRecord1("false");
//                    }
//                  }
//                  break;
//                }
//              }
//            }
//          
//          }
//          
//        loanbankList.remove(loanbankList.size()-1);
      }else{
        list=loanBankParaDAO.queryParamByLoanBankId(loanBankId,paramType);
        paramValue7=checkLoanFlow();
        if(!paramValue7.equals("")){
          paramDTO.setLoanVipCheck(paramValue7.substring(0,1));
          paramDTO.setEndorseLoan(paramValue7.substring(1,2));
        }
        for (int i = 0; i < list.size(); i++) {
          loanBankPara=(LoanBankPara)list.get(i);
          int paramNum=Integer.parseInt(loanBankPara.getParamNum());
          switch(paramNum){
            case 1:{
              paramDTO.setKouAccMode(loanBankPara.getParamValue());
              break;
            }
            case 2:{
              paramDTO.setDecideDateMode(loanBankPara.getParamValue());
              if(loanBankPara.getParamValue().equals("2")){
                paramDTO.setUniteDate(loanBankPara.getParamExplain());
              }
              break;
            }
            case 3:{
              if(!loanBankPara.getParamValue().equals("")){
               
                for(int j=0;j<5;j++){
                  char value=loanBankPara.getParamValue().charAt(j);
                  switch(value){
                    case 'A':paramDTO.setCorpus(new Integer(j+1).toString());break;
                    case 'B':paramDTO.setInterest(new Integer(j+1).toString());break;
                    case 'C':paramDTO.setOverdueCorpus(new Integer(j+1).toString());break;
                    case 'D':paramDTO.setOverdueInterest(new Integer(j+1).toString());break;
                    case 'E':paramDTO.setPunishInterest(new Integer(j+1).toString());break;
                  }
                }
              }
              break;
            }
            case 4:{
              paramDTO.setPunishInterestRateMode(loanBankPara.getParamValue());
              paramDTO.setPunishInterestRateMode_1(loanBankPara.getParamValue());
              if(!"".equals(loanBankPara.getParamValue())){
                char paramValue=loanBankPara.getParamValue().charAt(0);
                switch (paramValue) {
                  case '1':{
                    paramDTO.setPunishInterestDateRate(new BigDecimal(loanBankPara.getParamExplain()));
                    paramDTO.setPunishInterestDateRate_1(new BigDecimal(loanBankPara.getParamExplain()));
                    break;
                  }
                  case '2':{
                    paramDTO.setContractDateRate(new BigDecimal(loanBankPara.getParamExplain()));
                    paramDTO.setContractDateRate_1(new BigDecimal(loanBankPara.getParamExplain()));
                    break;
                  }
                  case '3':{
                    paramDTO.setMoneyDateInterest(new BigDecimal(loanBankPara.getParamExplain()));
                    paramDTO.setMoneyDateInterest_1(new BigDecimal(loanBankPara.getParamExplain()));
                    break;
                  }
                }
              }
              
              break;
            }
            case 5:{
              paramDTO.setPermitDateNum(loanBankPara.getParamExplain());
              paramDTO.setIsRecord(loanBankPara.getParamValue());
              break;
            }
            case 6:{
              paramDTO.setShifouqiyong(loanBankPara.getReserveaA());
              for (int j = 0; j < 5; j++) {
                char paramValue=loanBankPara.getParamValue().charAt(0);
                switch (paramValue) {
                  case '1':{
                    paramDTO.setCommonMonthNum(loanBankPara.getParamExplain());
                    break;
                  }
                  case '2':{
                    paramDTO.setAttentionMonthNum(loanBankPara.getParamExplain());
                    break;
                  }
                  case '3':{
                    paramDTO.setSubMonthNum(loanBankPara.getParamExplain());
                    break;
                  }
                  case '4':{
                    paramDTO.setShadinessMonthNum(loanBankPara.getParamExplain());
                    break;
                  }
                  case '5':{
                    paramDTO.setLossMonthNum(loanBankPara.getParamExplain());
                    break;
                  }
                }
              }
              break;
            }
//            case 8:{
//              for (int j = 0; j < 2; j++) {
//                if(loanBankPara.getParamValue().equals("1")){
//                  paramDTO.setCurrentRate(new BigDecimal(loanBankPara.getParamExplain()).multiply(new BigDecimal(100)));
//                }
//                if(loanBankPara.getParamValue().equals("2")){
//                  paramDTO.setTerminalRate(new BigDecimal(loanBankPara.getParamExplain()).multiply(new BigDecimal(100)));
//                }
//              }
//              break;
//            }
            case 9:{
              paramDTO.setIsAdopt(loanBankPara.getParamValue());
              break;
            }
//            case 10:{
//              paramDTO.setIsAdopt1(loanBankPara.getParamValue());
//              break;
//            }
//            case 11:{
//              paramDTO.setPunishInterestRateMode1(loanBankPara.getParamValue());
//              char paramValue=loanBankPara.getParamValue().charAt(0);
//              switch (paramValue) {
//                case '1':{
//                  paramDTO.setPunishInterestDateRate1(new BigDecimal(loanBankPara.getParamExplain()));
//                  break;
//                }
//                case '2':{
//                  paramDTO.setContractDateRate1(new BigDecimal(loanBankPara.getParamExplain()));
//                  break;
//                }
//                case '3':{
//                  paramDTO.setMoneyDateInterest1(new BigDecimal(loanBankPara.getParamExplain()));
//                  break;
//                }
//              }
//              break;
//            }
//            case 12:{
//              paramDTO.setPermitDateNum1(loanBankPara.getParamExplain());
//              paramDTO.setIsRecord1(loanBankPara.getParamValue());
//              break;
//            }
          }
        }
      }
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return paramDTO;
  }
  /**
   * 参数维护
   * @author 郭婧平
   * 2007-10-06
   * 根据paramNum和paramType和paramValue查pl003中是否有符合条件的数据
   */
  public String checkLoanFlow() throws Exception{
    String paramValue="";
    try{
      paramValue=loanBankParaDAO.queryLoanFlow();
    }catch(Exception e){
      e.printStackTrace();
    }
    return paramValue;
  }
  /**
   * 参数维护
   * 确定按钮
   * @author 郭婧平
   * 2007-9-29
   */
  public void saveParamInfo(ParamDTO paramDTO,SecurityInfo securityInfo) throws Exception{
    try{
      String loanBankId=paramDTO.getLoanBankId();
      List loanbankList = securityInfo.getDkUserBankList();
      
      String paramType="A";
      if("100".equals(loanBankId)){
        Userslogincollbank bank = null;
        Userslogincollbank collbank=new Userslogincollbank();
        collbank.setCollBankId(new Integer("100"));
        collbank.setCollBankName("全部");
        loanbankList.add(collbank);
        Iterator itr1 = loanbankList.iterator();
        while (itr1.hasNext()) {
          bank = (Userslogincollbank) itr1.next();
          loanBankId=bank.getCollBankId().toString();
          loanBankParaDAO.deleteLoanBankPara(loanBankId,paramType);
          //插入pl003
          LoanBankPara loanBankPara=null;
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("1足额扣款2全额扣款");
          loanBankPara.setParamValue(paramDTO.getKouAccMode());
          loanBankPara.setParamNum("1");
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("1按户定日2统一定日");
          loanBankPara.setParamValue(paramDTO.getDecideDateMode());
          loanBankPara.setParamNum("2");
          if(paramDTO.getDecideDateMode().equals("2")){
            loanBankPara.setParamExplain(paramDTO.getUniteDate());
          }
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("A正常本金B正常利息C逾期本金D逾期利息E罚息");
         
          String paramvalue="";
          for(int i=1;i<6;i++){
            if(paramDTO.getCorpus().equals(new Integer(i).toString())){
              paramvalue=paramvalue+"A";
            }
            if(paramDTO.getInterest().equals(new Integer(i).toString())){
              paramvalue=paramvalue+"B";
            }
            if(paramDTO.getOverdueCorpus().equals(new Integer(i).toString())){
              paramvalue=paramvalue+"C";
            }
            if(paramDTO.getOverdueInterest().equals(new Integer(i).toString())){
              paramvalue=paramvalue+"D";
            }
            if(paramDTO.getPunishInterest().equals(new Integer(i).toString())){
              paramvalue=paramvalue+"E";
            }
          }
          loanBankPara.setParamValue(paramvalue);
          loanBankPara.setParamNum("3");
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("1按罚息2按合同3按额");
          loanBankPara.setParamValue(paramDTO.getPunishInterestRateMode());
          loanBankPara.setParamNum("4");
          if(paramDTO.getPunishInterestRateMode().equals("1")){
            loanBankPara.setParamExplain(paramDTO.getPunishInterestDateRate().toString());
          }else if(paramDTO.getPunishInterestRateMode().equals("2")){
            loanBankPara.setParamExplain(paramDTO.getContractDateRate().toString());
          }else{
            loanBankPara.setParamExplain(paramDTO.getMoneyDateInterest().toString());
          }
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("0是1否");
          loanBankPara.setParamValue(paramDTO.getIsRecord());
          loanBankPara.setParamNum("5");
          loanBankPara.setParamExplain(paramDTO.getPermitDateNum());
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("1正常");
          loanBankPara.setParamValue("1");
          loanBankPara.setParamNum("6");
          loanBankPara.setReserveaA(paramDTO.getShifouqiyong());
          if(paramDTO.getShifouqiyong().equals("2")){
            loanBankPara.setParamExplain("0");
          }else{
            loanBankPara.setParamExplain(paramDTO.getCommonMonthNum());
          }
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("2关注");
          loanBankPara.setParamValue("2");
          loanBankPara.setParamNum("6");
          loanBankPara.setReserveaA(paramDTO.getShifouqiyong());
          if(paramDTO.getShifouqiyong().equals("2")){
            loanBankPara.setParamExplain("0");
          }else{
            loanBankPara.setParamExplain(paramDTO.getAttentionMonthNum());
          }
//          loanBankPara.setParamExplain(paramDTO.getAttentionMonthNum());
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("3次级");
          loanBankPara.setParamValue("3");
          loanBankPara.setParamNum("6");
          loanBankPara.setReserveaA(paramDTO.getShifouqiyong());
          if(paramDTO.getShifouqiyong().equals("2")){
            loanBankPara.setParamExplain("0");
          }else{
            loanBankPara.setParamExplain(paramDTO.getSubMonthNum());
          }
//          loanBankPara.setParamExplain(paramDTO.getSubMonthNum());
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("4可疑");
          loanBankPara.setParamValue("4");
          loanBankPara.setParamNum("6");
          loanBankPara.setReserveaA(paramDTO.getShifouqiyong());
          if(paramDTO.getShifouqiyong().equals("2")){
            loanBankPara.setParamExplain("0");
          }else{
            loanBankPara.setParamExplain(paramDTO.getShadinessMonthNum());
          }
//          loanBankPara.setParamExplain(paramDTO.getShadinessMonthNum());
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("5损失");
          loanBankPara.setParamValue("5");
          loanBankPara.setParamNum("6");
          loanBankPara.setReserveaA(paramDTO.getShifouqiyong());
         
          if(paramDTO.getShifouqiyong().equals("2")){
            loanBankPara.setParamExplain("0");
          }else{
            loanBankPara.setParamExplain(paramDTO.getLossMonthNum());
          }
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("A审批贷款B签订贷款");
          loanBankPara.setParamValue(paramDTO.getLoanVipCheck()+paramDTO.getEndorseLoan());
          loanBankPara.setParamNum("7");
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("1活期利率");
          loanBankPara.setParamValue("1");
          loanBankPara.setParamNum("8");
          loanBankPara.setParamExplain(paramDTO.getCurrentRate().divide(new BigDecimal(100),8, BigDecimal.ROUND_UNNECESSARY).toString());
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("2定期利率");
          loanBankPara.setParamValue("2");
          loanBankPara.setParamNum("8");
          loanBankPara.setParamExplain(paramDTO.getTerminalRate().divide(new BigDecimal(100),8, BigDecimal.ROUND_UNNECESSARY).toString());
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("0是1否");
          loanBankPara.setParamValue(paramDTO.getIsAdopt());
          loanBankPara.setParamNum("9");
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("利率启用时，启用前的整年期贷款是否使用新利率：0是1否");
          loanBankPara.setParamValue(paramDTO.getIsAdopt1());
          loanBankPara.setParamNum("10");
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("整年期贷款逾期利率：1按罚息2按合同3按额");
          loanBankPara.setParamValue(paramDTO.getPunishInterestRateMode1());
          loanBankPara.setParamNum("11");
          if(paramDTO.getPunishInterestRateMode1().equals("1")){
            loanBankPara.setParamExplain(paramDTO.getPunishInterestDateRate1().toString());
          }else if(paramDTO.getPunishInterestRateMode1().equals("2")){
            loanBankPara.setParamExplain(paramDTO.getContractDateRate1().toString());
          }else{
            loanBankPara.setParamExplain(paramDTO.getMoneyDateInterest1().toString());
          }
          loanBankParaDAO.insert(loanBankPara);
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("A");
          loanBankPara.setParamDescrip("整年期贷款宽限天数：0是1否");
          loanBankPara.setParamValue(paramDTO.getIsRecord1());
          loanBankPara.setParamNum("12");
          loanBankPara.setParamExplain(paramDTO.getPermitDateNum1());
          loanBankParaDAO.insert(loanBankPara);
          // 插入日志PL021
          PlOperateLog plOperateLog = new PlOperateLog();
          plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
          plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_PARAMETERS));
          plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
          plOperateLog.setOpBizId(new BigDecimal(loanBankId));
          plOperateLog.setOpIp(securityInfo.getUserIp());
          plOperateLog.setOpTime(new Date());
          plOperateLog.setOperator(securityInfo.getUserName());
          plOperateLogDAO.insert(plOperateLog);
        }
        loanbankList.remove(loanbankList.size()-1);
      }else{
        String count ="";
        loanBankParaDAO.deleteLoanBankPara(loanBankId,paramType);
        //插入pl003
        LoanBankPara loanBankPara=null;
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("1足额扣款2全额扣款");
        loanBankPara.setParamValue(paramDTO.getKouAccMode());
        loanBankPara.setParamNum("1");
        count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "1");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          loanBankParaDAO.updatePl003_wsh("", "100","1");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_LJ(new Integer("100"), paramType, "1");
          if(!paramDTO.getKouAccMode().equals(value)){
            loanBankParaDAO.updatePl003_wsh("", "100","1");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("1按户定日2统一定日");
        loanBankPara.setParamValue(paramDTO.getDecideDateMode());
        loanBankPara.setParamNum("2");
        count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "2");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          loanBankParaDAO.updatePl003_Explian_wsh("", "", "100", "2");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_LJ(new Integer("100"), paramType, "2");
          if(!paramDTO.getDecideDateMode().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh("", "", "100", "2");
          }
        }
        if(paramDTO.getDecideDateMode().equals("2")){
          loanBankPara.setParamExplain(paramDTO.getUniteDate());
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("A正常本金B正常利息C逾期本金D逾期利息E罚息");
        loanBankPara.setParamExplain(paramDTO.getShifouqiyong());
        String paramvalue="";
        for(int i=1;i<6;i++){
          if(paramDTO.getCorpus().equals(new Integer(i).toString())){
            paramvalue=paramvalue+"A";
          }
          if(paramDTO.getInterest().equals(new Integer(i).toString())){
            paramvalue=paramvalue+"B";
          }
          if(paramDTO.getOverdueCorpus().equals(new Integer(i).toString())){
            paramvalue=paramvalue+"C";
          }
          if(paramDTO.getOverdueInterest().equals(new Integer(i).toString())){
            paramvalue=paramvalue+"D";
          }
          if(paramDTO.getPunishInterest().equals(new Integer(i).toString())){
            paramvalue=paramvalue+"E";
          }
        }
        loanBankPara.setParamValue(paramvalue);
        loanBankPara.setParamNum("3");
        count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "3");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          loanBankParaDAO.updatePl003_wsh("", "100", "3");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_LJ(new Integer("100"), paramType, "3");
          if(!paramvalue.equals(value)){
            loanBankParaDAO.updatePl003_wsh("", "100", "3");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("1按罚息2按合同3按额");
        loanBankPara.setParamValue(paramDTO.getPunishInterestRateMode());
        loanBankPara.setParamNum("4");
        if(paramDTO.getPunishInterestRateMode().equals("1")){
          loanBankPara.setParamExplain(paramDTO.getPunishInterestDateRate().toString());
        }else if(paramDTO.getPunishInterestRateMode().equals("2")){
          loanBankPara.setParamExplain(paramDTO.getContractDateRate().toString());
        }else{
          loanBankPara.setParamExplain(paramDTO.getMoneyDateInterest().toString());
        }
        count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "4");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          loanBankParaDAO.updatePl003_Explian_wsh("", "", "100", "4");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_LJ(new Integer("100"), paramType, "4");
          if(!paramDTO.getPunishInterestRateMode().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh("", "", "100", "4");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("0是1否");
        loanBankPara.setParamValue(paramDTO.getIsRecord());
        loanBankPara.setParamNum("5");
        loanBankPara.setParamExplain(paramDTO.getPermitDateNum());
        count=loanBankParaDAO.queryParamExpCount_wsh(new Integer(loanBankId), paramType, "5", paramDTO.getIsRecord());
        if("0".equals(count)){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          //loanBankParaDAO.updatePl003_wsh("", "100", "5");
          loanBankParaDAO.updatePl003_Explian_wsh( "", "", "100","5");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "5",paramDTO.getIsRecord());
          if(!paramDTO.getPermitDateNum().equals(value)){
            //loanBankParaDAO.updatePl003_wsh("", "100", "5");
            loanBankParaDAO.updatePl003_Explian_wsh( "", "", "100","5");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("1正常");
        loanBankPara.setParamValue("1");
        loanBankPara.setParamNum("6");
        loanBankPara.setParamExplain(paramDTO.getCommonMonthNum());
        count=loanBankParaDAO.queryParamExpCount_wsh(new Integer(loanBankId), paramType, "6", "1");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          loanBankParaDAO.updatePl003_Explian_wsh_1( "1", "", "100","6");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "6","1");
          if(!paramDTO.getCommonMonthNum().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh_1("1", "", "100", "6");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("2关注");
        loanBankPara.setParamValue("2");
        loanBankPara.setParamNum("6");
        loanBankPara.setParamExplain(paramDTO.getAttentionMonthNum());
        count=loanBankParaDAO.queryParamExpCount_wsh(new Integer(loanBankId), paramType, "6", "2");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          loanBankParaDAO.updatePl003_Explian_wsh_1( "2", "", "100","6");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "6","2");
          if(!paramDTO.getAttentionMonthNum().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh_1("2", "", "100", "6");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("3次级");
        loanBankPara.setParamValue("3");
        loanBankPara.setParamNum("6");
        loanBankPara.setParamExplain(paramDTO.getSubMonthNum());
        count=loanBankParaDAO.queryParamExpCount_wsh(new Integer(loanBankId), paramType, "6", "3");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          loanBankParaDAO.updatePl003_Explian_wsh_1( "3", "", "100","6");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "6","3");
          if(!paramDTO.getSubMonthNum().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh_1("3", "", "100", "6");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("4可疑");
        loanBankPara.setParamValue("4");
        loanBankPara.setParamNum("6");
        loanBankPara.setParamExplain(paramDTO.getShadinessMonthNum());
        count=loanBankParaDAO.queryParamExpCount_wsh(new Integer(loanBankId), paramType, "6", "4");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          loanBankParaDAO.updatePl003_Explian_wsh_1( "4", "", "100","6");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "6","4");
          if(!paramDTO.getShadinessMonthNum().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh_1("4", "", "100", "6");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("5损失");
        loanBankPara.setParamValue("5");
        loanBankPara.setParamNum("6");
        loanBankPara.setParamExplain(paramDTO.getLossMonthNum());
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          loanBankParaDAO.updatePl003_Explian_wsh_1( "5", "", "100","6");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "6","5");
          if(!paramDTO.getLossMonthNum().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh_1("5", "", "100", "6");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("A审批贷款B签订贷款");
        loanBankPara.setParamValue(paramDTO.getLoanVipCheck()+paramDTO.getEndorseLoan());
        loanBankPara.setParamNum("7");
        count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "7");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          loanBankParaDAO.updatePl003_wsh("", "100", "7");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_LJ(new Integer("100"), paramType, "7");
          if(!(paramDTO.getLoanVipCheck()+paramDTO.getEndorseLoan()).equals(value)){
            loanBankParaDAO.updatePl003_wsh("", "100", "7");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
//        loanBankPara=new LoanBankPara();
//        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
//        loanBankPara.setParamType("A");
//        loanBankPara.setParamDescrip("1活期利率");
//        loanBankPara.setParamValue("1");
//        loanBankPara.setParamNum("8");
//        loanBankPara.setParamExplain(paramDTO.getCurrentRate().divide(new BigDecimal(100),8, BigDecimal.ROUND_UNNECESSARY).toString());
//        loanBankParaDAO.insert(loanBankPara);
//        loanBankPara=new LoanBankPara();
//        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
//        loanBankPara.setParamType("A");
//        loanBankPara.setParamDescrip("2定期利率");
//        loanBankPara.setParamValue("2");
//        loanBankPara.setParamNum("8");
//        loanBankPara.setParamExplain(paramDTO.getTerminalRate().divide(new BigDecimal(100),8, BigDecimal.ROUND_UNNECESSARY).toString());
//        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("A");
        loanBankPara.setParamDescrip("0是1否");
        loanBankPara.setParamValue(paramDTO.getIsAdopt());
        loanBankPara.setParamNum("9");
        count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "9");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          //loanBankParaDAO.updatePl003_wsh("", "100","1");
          loanBankParaDAO.updatePl003_wsh("", "100", "9");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_LJ(new Integer("100"), paramType, "9");
          if(!(paramDTO.getIsAdopt()).equals(value)){
            loanBankParaDAO.updatePl003_wsh("", "100", "9");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
//        loanBankPara=new LoanBankPara();
//        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
//        loanBankPara.setParamType("A");
//        loanBankPara.setParamDescrip("利率启用时，启用前的整年期贷款是否使用新利率：0是1否");
//        loanBankPara.setParamValue(paramDTO.getIsAdopt1());
//        loanBankPara.setParamNum("10");
//        loanBankParaDAO.insert(loanBankPara);
//        loanBankPara=new LoanBankPara();
//        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
//        loanBankPara.setParamType("A");
//        loanBankPara.setParamDescrip("整年期贷款逾期利率：1按罚息2按合同3按额");
//        loanBankPara.setParamValue(paramDTO.getPunishInterestRateMode1());
//        loanBankPara.setParamNum("11");
//        if(paramDTO.getPunishInterestRateMode1().equals("1")){
//          loanBankPara.setParamExplain(paramDTO.getPunishInterestDateRate1().toString());
//        }else if(paramDTO.getPunishInterestRateMode1().equals("2")){
//          loanBankPara.setParamExplain(paramDTO.getContractDateRate1().toString());
//        }else{
//          loanBankPara.setParamExplain(paramDTO.getMoneyDateInterest1().toString());
//        }
//        loanBankParaDAO.insert(loanBankPara);
//        loanBankPara=new LoanBankPara();
//        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
//        loanBankPara.setParamType("A");
//        loanBankPara.setParamDescrip("整年期贷款宽限天数：0是1否");
//        loanBankPara.setParamValue(paramDTO.getIsRecord1());
//        loanBankPara.setParamNum("12");
//        loanBankPara.setParamExplain(paramDTO.getPermitDateNum1());
//        loanBankParaDAO.insert(loanBankPara);
        // 插入日志PL021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_PARAMETERS));
        plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        plOperateLog.setOpBizId(new BigDecimal(loanBankId));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * 提前还款参数维护
   * @author 郭婧平
   * 2007-10-03
   * 根据银行id查pl003表内容
   * 查询条件：loanBankId
   */
  public AheadReturnParamDTO findAheadReturnParamInfo(String loanBankId) throws Exception{
    AheadReturnParamDTO aheadReturnParamDTO=new AheadReturnParamDTO();
    List list=null;
    try{
      if(!loanBankId.equals("")){
        LoanBankPara loanBankPara=null;
        String paramType="B";
        list=loanBankParaDAO.queryParamByLoanBankId(loanBankId,paramType);
        for (int i = 0; i < list.size(); i++) {
          loanBankPara=(LoanBankPara)list.get(i);
//          char paramNum=loanBankPara.getParamNum().charAt(0);
          int paramNum=Integer.parseInt(loanBankPara.getParamNum());
          switch(paramNum){
            case 1:{
              aheadReturnParamDTO.setAheadReturnAfter(loanBankPara.getParamValue());
              break;
            }
            case 2:{
              aheadReturnParamDTO.setIsPartAheadReturn(loanBankPara.getParamValue());
              aheadReturnParamDTO.setPartReturnMonthLT(loanBankPara.getParamExplain());
              break;
            }
            case 3:{
              aheadReturnParamDTO.setIsAllReturn(loanBankPara.getParamValue());
              aheadReturnParamDTO.setAllReturnMonthLT(loanBankPara.getParamExplain());
              break;
            }
            case 4:{
              if(loanBankPara.getParamExplain().equals("")){
                aheadReturnParamDTO.setLeastReturnMoney(new BigDecimal(0));
              }else{
                aheadReturnParamDTO.setLeastReturnMoney(new BigDecimal(loanBankPara.getParamExplain()));
              }
              
              break;
            }
            case 5:{
              aheadReturnParamDTO.setMostAheadReturnYearTimes(loanBankPara.getParamExplain());
              break;
            }
            case 6:{
              aheadReturnParamDTO.setMostAheadReturnTimes(loanBankPara.getParamExplain());
              break;
            }
            case 7:{
              if(!loanBankPara.getParamValue().equals("3")){
                aheadReturnParamDTO.setIsAccept("4");
                aheadReturnParamDTO.setChargeMode(loanBankPara.getParamValue());
                if(loanBankPara.getParamValue().equals("1")){
                  aheadReturnParamDTO.setAheadReturnMoneyPercent(new BigDecimal(loanBankPara.getParamExplain()));
                }
                if(loanBankPara.getParamValue().equals("2")){
                  aheadReturnParamDTO.setMoney(new BigDecimal(loanBankPara.getParamExplain()));
                }
              }else{
                aheadReturnParamDTO.setIsAccept("3");
              }
              break;
            }
            case 8:{
              if(loanBankPara.getParamValue().equals("1")){
                aheadReturnParamDTO.setMaleAge(loanBankPara.getParamExplain());
              }else{
                aheadReturnParamDTO.setFemaleAge(loanBankPara.getParamExplain());
              }
              break;
            }
            case 9:{
              if(loanBankPara.getParamValue().equals("1")){
                aheadReturnParamDTO.setTimeMax_1(loanBankPara.getParamExplain());
              }else{
                aheadReturnParamDTO.setTimeMax_2(loanBankPara.getParamExplain());
              }
              break;
            }
            case 10:{
              aheadReturnParamDTO.setSalaryRate(loanBankPara.getParamExplain());
              break;
            }
          }
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return aheadReturnParamDTO;
  }
  /**
   * 提前还款参数维护
   * 确定按钮
   * @author 郭婧平
   * 2007-10-04
   */
  public void saveAheadReturnParamInfo(AheadReturnParamDTO aheadReturnParamDTO,SecurityInfo securityInfo) throws Exception{
    String loanBankId=aheadReturnParamDTO.getLoanBankId();
    String paramType="B";
    List loanbankList = securityInfo.getDkUserBankList();
    if("100".equals(loanBankId)){
      Userslogincollbank bank = null;
      Userslogincollbank collbank=new Userslogincollbank();
      collbank.setCollBankId(new Integer("100"));
      collbank.setCollBankName("全部");
      loanbankList.add(collbank);
      Iterator itr1 = loanbankList.iterator();
      while (itr1.hasNext()) {
        bank = (Userslogincollbank) itr1.next();
        loanBankId=bank.getCollBankId().toString();

//      删除pl003
        loanBankParaDAO.deleteLoanBankPara(loanBankId,paramType);
        //删除pl004
        loanContractParaDAO.deleteLoanBankPara(loanBankId, paramType);
        //插入pl003
        LoanBankPara loanBankPara=null;
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("1保持原来月还款额2保持剩余期限3允许改变剩余期限");
        loanBankPara.setParamValue(aheadReturnParamDTO.getAheadReturnAfter());
        loanBankPara.setParamNum("1");
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("部分提前还款1不允许2允许");
        loanBankPara.setParamValue(aheadReturnParamDTO.getIsPartAheadReturn());
        loanBankPara.setParamNum("2");
        if(aheadReturnParamDTO.getIsPartAheadReturn().equals("1")){
          loanBankPara.setParamExplain(aheadReturnParamDTO.getPartReturnMonthLT());
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("一次性结清还款1不允许2允许");
        loanBankPara.setParamValue(aheadReturnParamDTO.getIsAllReturn());
        loanBankPara.setParamNum("3");
        if(aheadReturnParamDTO.getIsAllReturn().equals("1")){
          loanBankPara.setParamExplain(aheadReturnParamDTO.getAllReturnMonthLT());
        }
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("1提前还款最低金额");
        loanBankPara.setParamValue("1");
        loanBankPara.setParamNum("4");
        loanBankPara.setParamExplain(aheadReturnParamDTO.getLeastReturnMoney().toString());
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("1年度内最多允许提前还款");
        loanBankPara.setParamValue("1");
        loanBankPara.setParamNum("5");
        loanBankPara.setParamExplain(aheadReturnParamDTO.getMostAheadReturnYearTimes());
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("1贷款期限内最多允许提前还款");
        loanBankPara.setParamValue("1");
        loanBankPara.setParamNum("6");
        loanBankPara.setParamExplain(aheadReturnParamDTO.getMostAheadReturnTimes());
        loanBankParaDAO.insert(loanBankPara);
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("1按提前还款额2按额3否");
        if(aheadReturnParamDTO.getIsAccept().equals("3")){
          loanBankPara.setParamValue("3");
        }else{
          loanBankPara.setParamValue(aheadReturnParamDTO.getChargeMode());
          if(aheadReturnParamDTO.getChargeMode().equals("1")){
            loanBankPara.setParamExplain(aheadReturnParamDTO.getAheadReturnMoneyPercent().toString());
          }
          if(aheadReturnParamDTO.getChargeMode().equals("2")){
            loanBankPara.setParamExplain(aheadReturnParamDTO.getMoney().toString());
          }
        }
        loanBankPara.setParamNum("7");
        loanBankParaDAO.insert(loanBankPara);
        if(!aheadReturnParamDTO.getMaleAge().equals("")){
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("B");
          loanBankPara.setParamDescrip("1男2女");
          loanBankPara.setParamValue("1");
          loanBankPara.setParamNum("8");
          loanBankPara.setParamExplain(aheadReturnParamDTO.getMaleAge());
          loanBankParaDAO.insert(loanBankPara);
        }
        if(!aheadReturnParamDTO.getFemaleAge().equals("")){
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("B");
          loanBankPara.setParamDescrip("1男2女");
          loanBankPara.setParamValue("2");
          loanBankPara.setParamNum("8");
          loanBankPara.setParamExplain(aheadReturnParamDTO.getFemaleAge());
          loanBankParaDAO.insert(loanBankPara);
        }
        
        if(!aheadReturnParamDTO.getTimeMax_1().equals("")){
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("B");
          loanBankPara.setParamDescrip("贷款最高年限：1商品房2二手房");
          loanBankPara.setParamValue("1");
          loanBankPara.setParamNum("9");
          loanBankPara.setParamExplain(aheadReturnParamDTO.getTimeMax_1());
          loanBankParaDAO.insert(loanBankPara);
        }
        if(!aheadReturnParamDTO.getTimeMax_2().equals("")){
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("B");
          loanBankPara.setParamDescrip("贷款最高年限：1商品房2二手房");
          loanBankPara.setParamValue("2");
          loanBankPara.setParamNum("9");
          loanBankPara.setParamExplain(aheadReturnParamDTO.getTimeMax_2());
          loanBankParaDAO.insert(loanBankPara);
        }
        if(!(aheadReturnParamDTO.getSalaryRate().equals("")||aheadReturnParamDTO.getSalaryRate().toString().equals("0"))){
          loanBankPara=new LoanBankPara();
          loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
          loanBankPara.setParamType("B");
          loanBankPara.setParamDescrip("借款人月收入还款比例");
          loanBankPara.setParamNum("10");
          loanBankPara.setParamExplain(aheadReturnParamDTO.getSalaryRate());
          loanBankParaDAO.insert(loanBankPara);
        }
        //插入pl004
//        List list=borrowerAccDAO.queryContractIdByLoanBankId(loanBankId);
//        LoanContractPara loanContractPara=null;
//        if(list!=null){
//          for(int i=0;i<list.size();i++){
//            BorrowerAcc borrowerAcc=(BorrowerAcc)list.get(i);
//            String contractId=borrowerAcc.getContractId();
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("1保持原来月还款额2保持剩余期限3允许改变剩余期限");
//            loanContractPara.setParamValue(aheadReturnParamDTO.getAheadReturnAfter());
//            loanContractPara.setParamNum("1");
//            loanContractParaDAO.insert(loanContractPara);
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("部分提前还款1不允许2允许");
//            loanContractPara.setParamValue(aheadReturnParamDTO.getIsPartAheadReturn());
//            loanContractPara.setParamNum("2");
//            loanContractPara.setParamExplain(aheadReturnParamDTO.getPartReturnMonthLT());
//            loanContractParaDAO.insert(loanContractPara);
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("一次性结清还款1不允许2允许");
//            loanContractPara.setParamValue(aheadReturnParamDTO.getIsAllReturn());
//            loanContractPara.setParamNum("3");
//            loanContractPara.setParamExplain(aheadReturnParamDTO.getAllReturnMonthLT());
//            loanContractParaDAO.insert(loanContractPara);
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("1提前还款最低金额");
//            loanContractPara.setParamValue("1");
//            loanContractPara.setParamNum("4");
//            loanContractPara.setParamExplain(aheadReturnParamDTO.getLeastReturnMoney().toString());
//            loanContractParaDAO.insert(loanContractPara);
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("1年度内最多允许提前还款");
//            loanContractPara.setParamValue("1");
//            loanContractPara.setParamNum("5");
//            loanContractPara.setParamExplain(aheadReturnParamDTO.getMostAheadReturnYearTimes());
//            loanContractParaDAO.insert(loanContractPara);
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("1贷款期限内最多允许提前还款");
//            loanContractPara.setParamValue("1");
//            loanContractPara.setParamNum("6");
//            loanContractPara.setParamExplain(aheadReturnParamDTO.getMostAheadReturnTimes());
//            loanContractParaDAO.insert(loanContractPara);
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("1按提前还款额2按额3否");
//            if(aheadReturnParamDTO.getIsAccept().equals("3")){
//              loanContractPara.setParamValue("3");
//            }else{
//              loanContractPara.setParamValue(aheadReturnParamDTO.getChargeMode());
//              if(aheadReturnParamDTO.getChargeMode().equals("1")){
//                loanContractPara.setParamExplain(aheadReturnParamDTO.getAheadReturnMoneyPercent().toString());
//              }
//              if(aheadReturnParamDTO.getChargeMode().equals("2")){
//                loanContractPara.setParamExplain(aheadReturnParamDTO.getMoney().toString());
//              }
//            }
//            loanContractPara.setParamNum("7");
//            loanContractParaDAO.insert(loanContractPara);
//            
//            
//            
//            
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("1男2女");
//            loanContractPara.setParamValue("1");
//            loanContractPara.setParamNum("8");
//            loanContractPara.setParamExplain(aheadReturnParamDTO.getMaleAge());
//            loanContractParaDAO.insert(loanContractPara);
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("1男2女");
//            loanContractPara.setParamValue("2");
//            loanContractPara.setParamNum("8");
//            loanContractPara.setParamExplain(aheadReturnParamDTO.getFemaleAge());
//            loanContractParaDAO.insert(loanContractPara);
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("贷款最高年限：1商品房2二手房");
//            loanContractPara.setParamValue("1");
//            loanContractPara.setParamNum("9");
//            loanContractPara.setParamExplain(aheadReturnParamDTO.getTimeMax_1());
//            loanContractParaDAO.insert(loanContractPara);
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("贷款最高年限：1商品房2二手房");
//            loanContractPara.setParamValue("2");
//            loanContractPara.setParamNum("9");
//            loanContractPara.setParamExplain(aheadReturnParamDTO.getTimeMax_2());
//            loanContractParaDAO.insert(loanContractPara);
//            loanContractPara=new LoanContractPara();
//            loanContractPara.setContractId(contractId);
//            loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//            loanContractPara.setParamType("B");
//            loanContractPara.setParamDescrip("借款人月收入还款比例");
//            loanContractPara.setParamNum("10");
//            loanContractPara.setParamExplain(aheadReturnParamDTO.getSalaryRate());
//            loanContractParaDAO.insert(loanContractPara);
//          }
//        }
      
      }
      loanbankList.remove(loanbankList.size()-1);
      
    }else{
//    删除pl003
      String count ="";
      loanBankParaDAO.deleteLoanBankPara(loanBankId,paramType);
      //删除pl004
      loanContractParaDAO.deleteLoanBankPara(loanBankId, paramType);
      //插入pl003
      LoanBankPara loanBankPara=null;
      loanBankPara=new LoanBankPara();
      loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
      loanBankPara.setParamType("B");
      loanBankPara.setParamDescrip("1保持原来月还款额2保持剩余期限3允许改变剩余期限");
      loanBankPara.setParamValue(aheadReturnParamDTO.getAheadReturnAfter());
      loanBankPara.setParamNum("1");
      count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "1");
      if(!"0".equals(count)&&Integer.parseInt(count)>1){
        //更新对应的ploo3中的数据为""
        loanBankParaDAO.updatePl003_wsh_B("", "100","1");
      }
      if("1".equals(count)){
        String value="";
        value=loanBankParaDAO.queryParamValue_LJ(new Integer("100"), paramType, "1");
        if(!aheadReturnParamDTO.getAheadReturnAfter().equals(value)){
          loanBankParaDAO.updatePl003_wsh_B("", "100","1");
        }
      }
      loanBankParaDAO.insert(loanBankPara);
      loanBankPara=new LoanBankPara();
      loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
      loanBankPara.setParamType("B");
      loanBankPara.setParamDescrip("部分提前还款1不允许2允许");
      loanBankPara.setParamValue(aheadReturnParamDTO.getIsPartAheadReturn());
      loanBankPara.setParamNum("2");
      if(aheadReturnParamDTO.getIsPartAheadReturn().equals("1")){
        loanBankPara.setParamExplain(aheadReturnParamDTO.getPartReturnMonthLT());
      }
      count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "2");
      if(!"0".equals(count)&&Integer.parseInt(count)>1){
        //更新对应的ploo3中的数据为""
        loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","2");
      }
      if("1".equals(count)){
        String value="";
        value=loanBankParaDAO.queryParamValue_LJ(new Integer("100"), paramType, "2");
        if(!aheadReturnParamDTO.getIsPartAheadReturn().equals(value)){
          loanBankParaDAO.updatePl003_Explian_wsh_B("","", "100","2");
        }
      }
      loanBankParaDAO.insert(loanBankPara);
      loanBankPara=new LoanBankPara();
      loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
      loanBankPara.setParamType("B");
      loanBankPara.setParamDescrip("一次性结清还款1不允许2允许");
      loanBankPara.setParamValue(aheadReturnParamDTO.getIsAllReturn());
      loanBankPara.setParamNum("3");
      if(aheadReturnParamDTO.getIsAllReturn().equals("1")){
        loanBankPara.setParamExplain(aheadReturnParamDTO.getAllReturnMonthLT());
      }
      count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "3");
      if(!"0".equals(count)&&Integer.parseInt(count)>1){
        //更新对应的ploo3中的数据为""
        loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","3");
      }
      if("1".equals(count)){
        String value="";
        value=loanBankParaDAO.queryParamValue_LJ(new Integer("100"), paramType, "3");
        if(!aheadReturnParamDTO.getIsAllReturn().equals(value)){
          loanBankParaDAO.updatePl003_Explian_wsh_B("","", "100","3");
        }
      }
      loanBankParaDAO.insert(loanBankPara);
      loanBankPara=new LoanBankPara();
      loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
      loanBankPara.setParamType("B");
      loanBankPara.setParamDescrip("1提前还款最低金额");
      loanBankPara.setParamValue("1");
      loanBankPara.setParamNum("4");
      loanBankPara.setParamExplain(aheadReturnParamDTO.getLeastReturnMoney().toString());
      count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "4");
      if(!"0".equals(count)&&Integer.parseInt(count)>1){
        //更新对应的ploo3中的数据为""
        loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","4");
      }
      if("1".equals(count)){
        String value="";
        value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "4","1");
        if(!aheadReturnParamDTO.getLeastReturnMoney().equals(value)){
          loanBankParaDAO.updatePl003_Explian_wsh_B("","", "100","4");
        }
      }
      loanBankParaDAO.insert(loanBankPara);
      loanBankPara=new LoanBankPara();
      loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
      loanBankPara.setParamType("B");
      loanBankPara.setParamDescrip("1年度内最多允许提前还款");
      loanBankPara.setParamValue("1");
      loanBankPara.setParamNum("5");
      loanBankPara.setParamExplain(aheadReturnParamDTO.getMostAheadReturnYearTimes());
      count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "5");
      if(!"0".equals(count)&&Integer.parseInt(count)>1){
        //更新对应的ploo3中的数据为""
        loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","5");
      }
      if("1".equals(count)){
        String value="";
        value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "5","1");
        if(!aheadReturnParamDTO.getMostAheadReturnYearTimes().equals(value)){
          loanBankParaDAO.updatePl003_Explian_wsh_B("","", "100","5");
        }
      }
      loanBankParaDAO.insert(loanBankPara);
      loanBankPara=new LoanBankPara();
      loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
      loanBankPara.setParamType("B");
      loanBankPara.setParamDescrip("1贷款期限内最多允许提前还款");
      loanBankPara.setParamValue("1");
      loanBankPara.setParamNum("6");
      loanBankPara.setParamExplain(aheadReturnParamDTO.getMostAheadReturnTimes());
      count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "6");
      if(!"0".equals(count)&&Integer.parseInt(count)>1){
        //更新对应的ploo3中的数据为""
        loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","6");
      }
      if("1".equals(count)){
        String value="";
        value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "6","1");
        if(!aheadReturnParamDTO.getMostAheadReturnTimes().equals(value)){
          loanBankParaDAO.updatePl003_Explian_wsh_B("","", "100","6");
        }
      }
      loanBankParaDAO.insert(loanBankPara);
      loanBankPara=new LoanBankPara();
      loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
      loanBankPara.setParamType("B");
      loanBankPara.setParamDescrip("1按提前还款额2按额3否");
      if(aheadReturnParamDTO.getIsAccept().equals("3")){
        loanBankPara.setParamValue("3");
      }else{
        loanBankPara.setParamValue(aheadReturnParamDTO.getChargeMode());
        if(aheadReturnParamDTO.getChargeMode().equals("1")){
          loanBankPara.setParamExplain(aheadReturnParamDTO.getAheadReturnMoneyPercent().toString());
        }
        if(aheadReturnParamDTO.getChargeMode().equals("2")){
          loanBankPara.setParamExplain(aheadReturnParamDTO.getMoney().toString());
        }
      }
      loanBankPara.setParamNum("7");
      count=loanBankParaDAO.queryParamValueCount_wsh(new Integer(loanBankId), paramType, "7");
      if(!"0".equals(count)&&Integer.parseInt(count)>1){
        //更新对应的ploo3中的数据为""
        loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","7");
      }
      if("1".equals(count)){
        String value="";
        value=loanBankParaDAO.queryParamValue_LJ(new Integer("100"), paramType, "7");
        if(!aheadReturnParamDTO.getIsAccept().equals(value)){
          loanBankParaDAO.updatePl003_Explian_wsh_B("","", "100","7");
        }
      }
      loanBankParaDAO.insert(loanBankPara);
      if(!aheadReturnParamDTO.getMaleAge().equals("")){
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("1男2女");
        loanBankPara.setParamValue("1");
        loanBankPara.setParamNum("8");
        loanBankPara.setParamExplain(aheadReturnParamDTO.getMaleAge());
        count=loanBankParaDAO.queryParamExpCount_wsh(new Integer(loanBankId), paramType, "8","1");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","8");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "8","1");
          if(!aheadReturnParamDTO.getMaleAge().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh_B("","", "100","8");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
      }
      if(!aheadReturnParamDTO.getFemaleAge().equals("")){
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("1男2女");
        loanBankPara.setParamValue("2");
        loanBankPara.setParamNum("8");
        loanBankPara.setParamExplain(aheadReturnParamDTO.getFemaleAge());
        count=loanBankParaDAO.queryParamExpCount_wsh(new Integer(loanBankId), paramType, "8","2");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","8");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "8","2");
          if(!aheadReturnParamDTO.getFemaleAge().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh_B("","", "100","8");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
      }
      
      if(!aheadReturnParamDTO.getTimeMax_1().equals("")){
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("贷款最高年限：1商品房2二手房");
        loanBankPara.setParamValue("1");
        loanBankPara.setParamNum("9");
        loanBankPara.setParamExplain(aheadReturnParamDTO.getTimeMax_1());
        count=loanBankParaDAO.queryParamExpCount_wsh(new Integer(loanBankId), paramType, "9","1");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","9");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "9","1");
          if(!aheadReturnParamDTO.getTimeMax_1().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh_B("","", "100","9");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
      }
      if(!aheadReturnParamDTO.getTimeMax_2().equals("")){
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("贷款最高年限：1商品房2二手房");
        loanBankPara.setParamValue("2");
        loanBankPara.setParamNum("9");
        loanBankPara.setParamExplain(aheadReturnParamDTO.getTimeMax_2());
        count=loanBankParaDAO.queryParamExpCount_wsh(new Integer(loanBankId), paramType, "9","2");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","9");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_wsh_1(new Integer("100"), paramType, "9","2");
          if(!aheadReturnParamDTO.getTimeMax_2().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh_B("","", "100","9");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
      }
      if(!(aheadReturnParamDTO.getSalaryRate().equals("")||aheadReturnParamDTO.getSalaryRate().toString().equals("0"))){
        loanBankPara=new LoanBankPara();
        loanBankPara.setLoanBankId(new BigDecimal(loanBankId));
        loanBankPara.setParamType("B");
        loanBankPara.setParamDescrip("借款人月收入还款比例");
        loanBankPara.setParamNum("10");
        loanBankPara.setParamExplain(aheadReturnParamDTO.getSalaryRate());
        count=loanBankParaDAO.queryParamExpCount_wsh(new Integer(loanBankId), paramType, "10");
        if(!"0".equals(count)&&Integer.parseInt(count)>1){
          //更新对应的ploo3中的数据为""
          loanBankParaDAO.updatePl003_wsh_B("", "100","10");
        }
        if("1".equals(count)){
          String value="";
          value=loanBankParaDAO.queryParamValue_LJ_1(new Integer("100"), paramType, "10");
          if(!aheadReturnParamDTO.getSalaryRate().equals(value)){
            loanBankParaDAO.updatePl003_Explian_wsh_B("", "","100","10");
          }
        }
        loanBankParaDAO.insert(loanBankPara);
      }
      //插入pl004
//      List list=borrowerAccDAO.queryContractIdByLoanBankId(loanBankId);
//      LoanContractPara loanContractPara=null;
//      if(list!=null){
//        for(int i=0;i<list.size();i++){
//          BorrowerAcc borrowerAcc=(BorrowerAcc)list.get(i);
//          String contractId=borrowerAcc.getContractId();
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("1保持原来月还款额2保持剩余期限3允许改变剩余期限");
//          loanContractPara.setParamValue(aheadReturnParamDTO.getAheadReturnAfter());
//          loanContractPara.setParamNum("1");
//          loanContractParaDAO.insert(loanContractPara);
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("部分提前还款1不允许2允许");
//          loanContractPara.setParamValue(aheadReturnParamDTO.getIsPartAheadReturn());
//          loanContractPara.setParamNum("2");
//          loanContractPara.setParamExplain(aheadReturnParamDTO.getPartReturnMonthLT());
//          loanContractParaDAO.insert(loanContractPara);
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("一次性结清还款1不允许2允许");
//          loanContractPara.setParamValue(aheadReturnParamDTO.getIsAllReturn());
//          loanContractPara.setParamNum("3");
//          loanContractPara.setParamExplain(aheadReturnParamDTO.getAllReturnMonthLT());
//          loanContractParaDAO.insert(loanContractPara);
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("1提前还款最低金额");
//          loanContractPara.setParamValue("1");
//          loanContractPara.setParamNum("4");
//          loanContractPara.setParamExplain(aheadReturnParamDTO.getLeastReturnMoney().toString());
//          loanContractParaDAO.insert(loanContractPara);
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("1年度内最多允许提前还款");
//          loanContractPara.setParamValue("1");
//          loanContractPara.setParamNum("5");
//          loanContractPara.setParamExplain(aheadReturnParamDTO.getMostAheadReturnYearTimes());
//          loanContractParaDAO.insert(loanContractPara);
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("1贷款期限内最多允许提前还款");
//          loanContractPara.setParamValue("1");
//          loanContractPara.setParamNum("6");
//          loanContractPara.setParamExplain(aheadReturnParamDTO.getMostAheadReturnTimes());
//          loanContractParaDAO.insert(loanContractPara);
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("1按提前还款额2按额3否");
//          if(aheadReturnParamDTO.getIsAccept().equals("3")){
//            loanContractPara.setParamValue("3");
//          }else{
//            loanContractPara.setParamValue(aheadReturnParamDTO.getChargeMode());
//            if(aheadReturnParamDTO.getChargeMode().equals("1")){
//              loanContractPara.setParamExplain(aheadReturnParamDTO.getAheadReturnMoneyPercent().toString());
//            }
//            if(aheadReturnParamDTO.getChargeMode().equals("2")){
//              loanContractPara.setParamExplain(aheadReturnParamDTO.getMoney().toString());
//            }
//          }
//          loanContractPara.setParamNum("7");
//          loanContractParaDAO.insert(loanContractPara);
//          
//          
//          
//          
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("1男2女");
//          loanContractPara.setParamValue("1");
//          loanContractPara.setParamNum("8");
//          loanContractPara.setParamExplain(aheadReturnParamDTO.getMaleAge());
//          loanContractParaDAO.insert(loanContractPara);
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("1男2女");
//          loanContractPara.setParamValue("2");
//          loanContractPara.setParamNum("8");
//          loanContractPara.setParamExplain(aheadReturnParamDTO.getFemaleAge());
//          loanContractParaDAO.insert(loanContractPara);
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("贷款最高年限：1商品房2二手房");
//          loanContractPara.setParamValue("1");
//          loanContractPara.setParamNum("9");
//          loanContractPara.setParamExplain(aheadReturnParamDTO.getTimeMax_1());
//          loanContractParaDAO.insert(loanContractPara);
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("贷款最高年限：1商品房2二手房");
//          loanContractPara.setParamValue("2");
//          loanContractPara.setParamNum("9");
//          loanContractPara.setParamExplain(aheadReturnParamDTO.getTimeMax_2());
//          loanContractParaDAO.insert(loanContractPara);
//          loanContractPara=new LoanContractPara();
//          loanContractPara.setContractId(contractId);
//          loanContractPara.setLoanBankId(new BigDecimal(loanBankId));
//          loanContractPara.setParamType("B");
//          loanContractPara.setParamDescrip("借款人月收入还款比例");
//          loanContractPara.setParamNum("10");
//          loanContractPara.setParamExplain(aheadReturnParamDTO.getSalaryRate());
//          loanContractParaDAO.insert(loanContractPara);
//        }
//      }
    }
    
    //插入日志PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_PREPAYMENTPARAM));
    plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
    plOperateLog.setOpBizId(new BigDecimal(loanBankId));
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLogDAO.insert(plOperateLog);
  }
  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }
  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }
  public void setLoanContractParaDAO(LoanContractParaDAO loanContractParaDAO) {
    this.loanContractParaDAO = loanContractParaDAO;
  }
  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }
}
