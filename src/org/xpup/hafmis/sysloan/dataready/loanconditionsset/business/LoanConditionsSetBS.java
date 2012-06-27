package org.xpup.hafmis.sysloan.dataready.loanconditionsset.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.DeveloperDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanConditionsSetDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanConditionsSet;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.bsinterface.ILoanConditionsSetBS;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.dto.LoanConditionsSetDTO;
import org.xpup.security.common.domain.Userslogincollbank;



public class LoanConditionsSetBS implements ILoanConditionsSetBS{
  private LoanConditionsSetDAO loanConditionsSetDAO=null;
  private PlOperateLogDAO plOperateLogDAO=null;
  protected DeveloperDAO developerDAO = null;
  /**
   * 贷款前提条件设置
   * @author 郭婧平
   * 2007-12-5
   * 查pl008表内容
   */
  public LoanConditionsSetDTO findLoanConditionsSetInfo(String office) throws Exception{
    // TODO Auto-generated method stub
    LoanConditionsSetDTO loanConditionsSetDTO=new LoanConditionsSetDTO();
    List list=null;
    try{
      LoanConditionsSet loanConditionsSet=null;
      list=loanConditionsSetDAO.queryLoanConditionsSet(office);
      for (int i = 0; i < list.size(); i++) {
        loanConditionsSet=(LoanConditionsSet)list.get(i);
        int paramNum=Integer.parseInt(loanConditionsSet.getParamNum());
        switch(paramNum){
          case 1:{
            loanConditionsSetDTO.setIsRegular(loanConditionsSet.getParamValue());
            loanConditionsSetDTO.setOne(loanConditionsSet.getIsUse());
            break;
          }
          case 2:{
            loanConditionsSetDTO.setTwo(loanConditionsSet.getIsUse());
            if(loanConditionsSet.getParamValue().equals("")){
              loanConditionsSetDTO.setChgbizMonth(loanConditionsSet.getParamExplain());
            }
            if(loanConditionsSet.getParamValue().equals("1")){
              loanConditionsSetDTO.setQianJiaoMonth(loanConditionsSet.getParamExplain());
            }
            break;
          }
          case 3:{
            loanConditionsSetDTO.setAccountOpenMonth(loanConditionsSet.getParamExplain());
            loanConditionsSetDTO.setThree(loanConditionsSet.getIsUse());
            break;
          }
          case 4:{
            if(loanConditionsSet.getParamValue().equals("1")){
              loanConditionsSetDTO.setMaleAge(loanConditionsSet.getParamExplain());
            }
            if(loanConditionsSet.getParamValue().equals("2")){
              loanConditionsSetDTO.setFemaleAge(loanConditionsSet.getParamExplain());
            }
            loanConditionsSetDTO.setFour(loanConditionsSet.getIsUse());
            break;
          }
          case 5:{
            if(loanConditionsSet.getParamValue().equals("1")){
              loanConditionsSetDTO.setLoanLimitMin(loanConditionsSet.getParamExplain());
            }
            if(loanConditionsSet.getParamValue().equals("2")){
              loanConditionsSetDTO.setLoanLimitMax(loanConditionsSet.getParamExplain());
            }
            loanConditionsSetDTO.setFive(loanConditionsSet.getIsUse());
            break;
          }
          case 6:{
            loanConditionsSetDTO.setOverTimeMax(loanConditionsSet.getParamExplain());
            loanConditionsSetDTO.setSix(loanConditionsSet.getIsUse());
            break;
          }
          case 7:{
            if(loanConditionsSet.getParamValue().equals("1")){
              if(loanConditionsSet.getParamExplain().equals("")){
                loanConditionsSetDTO.setLoanMoneyMax(new BigDecimal(0));
              }else{
                loanConditionsSetDTO.setLoanMoneyMax(new BigDecimal(loanConditionsSet.getParamExplain()));
              }
              
              
            }
            if(loanConditionsSet.getParamValue().equals("2")){
              if(loanConditionsSet.getParamExplain().equals("")){
                loanConditionsSetDTO.setOtherLoanMoneyMax(new BigDecimal(0));
              }else{
                loanConditionsSetDTO.setOtherLoanMoneyMax(new BigDecimal(loanConditionsSet.getParamExplain()));
              }
              
            }
            loanConditionsSetDTO.setSeven(loanConditionsSet.getIsUse());
            break;
          }
          case 8:{
            if(loanConditionsSet.getParamExplain().equals("")){
              loanConditionsSetDTO.setMerchandiseRateMax(new BigDecimal(0));
            }else{
              loanConditionsSetDTO.setMerchandiseRateMax(new BigDecimal(loanConditionsSet.getParamExplain()));
            }
            
            loanConditionsSetDTO.setEight(loanConditionsSet.getIsUse());
            break;
          }
          case 9:{
//            loanConditionsSetDTO.setSecondhandRateMax(new BigDecimal(loanConditionsSet.getParamExplain()));
//            loanConditionsSetDTO.setNine(loanConditionsSet.getIsUse());
            
            if(loanConditionsSet.getParamValue().equals("")){
              if(loanConditionsSet.getParamExplain().equals("")){
                loanConditionsSetDTO.setSecondhandRateMax(new BigDecimal(0));
              }else{
                loanConditionsSetDTO.setSecondhandRateMax(new BigDecimal(loanConditionsSet.getParamExplain()));
              }
              
              loanConditionsSetDTO.setNine(loanConditionsSet.getIsUse());
            }
            if(loanConditionsSet.getParamValue().equals("1")){
              
              if(loanConditionsSet.getParamExplain().equals("")){
                loanConditionsSetDTO.setSecondhandRateMax_1(new BigDecimal(0));
              }else{
                loanConditionsSetDTO.setSecondhandRateMax_1(new BigDecimal(loanConditionsSet.getParamExplain()));
              }
              loanConditionsSetDTO.setTwive(loanConditionsSet.getIsUse());
            }
            if(loanConditionsSet.getParamValue().equals("2")){
              
              if(loanConditionsSet.getParamExplain().equals("")){
                loanConditionsSetDTO.setSecondhandRateMax_2(new BigDecimal(0));
              }else{
                loanConditionsSetDTO.setSecondhandRateMax_2(new BigDecimal(loanConditionsSet.getParamExplain()));
              }
              loanConditionsSetDTO.setThirteen(loanConditionsSet.getIsUse());
            }
            
            break;
          }
          case 10:{
            if(loanConditionsSet.getParamExplain().equals("")){
              loanConditionsSetDTO.setMerchandiseMoneyMax(new BigDecimal(0));
            }else{
              loanConditionsSetDTO.setMerchandiseMoneyMax(new BigDecimal(loanConditionsSet.getParamExplain()));
            }
            
            loanConditionsSetDTO.setTen(loanConditionsSet.getIsUse());
            break;
          }
          case 11:{
            if(loanConditionsSet.getParamExplain().equals("")){
              loanConditionsSetDTO.setSecondhandMoneyMax(new BigDecimal(0));
            }else{
              loanConditionsSetDTO.setSecondhandMoneyMax(new BigDecimal(loanConditionsSet.getParamExplain()));
            }
           
            loanConditionsSetDTO.setEleven(loanConditionsSet.getIsUse());
            break;
          }
          case 12:{
            if(loanConditionsSet.getParamExplain().equals("")){
              loanConditionsSetDTO.setBeiShu(new BigDecimal(0));
            }else{
              loanConditionsSetDTO.setBeiShu(new BigDecimal(loanConditionsSet.getParamExplain()));
            }
            
            loanConditionsSetDTO.setFourteen(loanConditionsSet.getIsUse());
            break;
          }
          case 13:{
            if(loanConditionsSet.getParamValue().equals("1")){
              if(loanConditionsSet.getParamExplain().equals("")){
                loanConditionsSetDTO.setTimeMax_1("0");
              }else{
                loanConditionsSetDTO.setTimeMax_1(new BigDecimal(loanConditionsSet.getParamExplain()).toString());
              }
              
              loanConditionsSetDTO.setFifteen(loanConditionsSet.getIsUse());
            }
            if(loanConditionsSet.getParamValue().equals("2")){
              if(loanConditionsSet.getParamExplain().equals("")){
                loanConditionsSetDTO.setTimeMax_2("0");
              }else{
                loanConditionsSetDTO.setTimeMax_2(new BigDecimal(loanConditionsSet.getParamExplain()).toString());
              }
              
              loanConditionsSetDTO.setFifteen(loanConditionsSet.getIsUse());
            }
            break;
          }
          case 14:{
            if(loanConditionsSet.getParamExplain().equals("")){
              loanConditionsSetDTO.setSalaryRate(new BigDecimal(0));
            }else{
              loanConditionsSetDTO.setSalaryRate(new BigDecimal(loanConditionsSet.getParamExplain()));
            }
            
            loanConditionsSetDTO.setSixteen(loanConditionsSet.getIsUse());
            break;
          }
          case 15:{
//          loanConditionsSetDTO.setSecondhandRateMax(new BigDecimal(loanConditionsSet.getParamExplain()));
//          loanConditionsSetDTO.setNine(loanConditionsSet.getIsUse());
          
          if(loanConditionsSet.getParamValue().equals("1")){
            loanConditionsSetDTO.setComNature_1(loanConditionsSet.getParamExplain());
            
          }
          if(loanConditionsSet.getParamValue().equals("2")){
            loanConditionsSetDTO.setPersonCount_1(loanConditionsSet.getParamExplain());
            
          }
          if(loanConditionsSet.getParamValue().equals("3")){
            loanConditionsSetDTO.setMonthCount_1(loanConditionsSet.getParamExplain());
            
          }
          loanConditionsSetDTO.setSeventeen(loanConditionsSet.getIsUse());
          break;
        }
          case 16:{
//          loanConditionsSetDTO.setSecondhandRateMax(new BigDecimal(loanConditionsSet.getParamExplain()));
//          loanConditionsSetDTO.setNine(loanConditionsSet.getIsUse());
          
          if(loanConditionsSet.getParamValue().equals("1")){
            loanConditionsSetDTO.setComNature_2(loanConditionsSet.getParamExplain());
            
          }
          if(loanConditionsSet.getParamValue().equals("2")){
            loanConditionsSetDTO.setPersonCount_2(loanConditionsSet.getParamExplain());
            
          }
          if(loanConditionsSet.getParamValue().equals("3")){
            loanConditionsSetDTO.setMonthCount_2(loanConditionsSet.getParamExplain());
            
          }
          loanConditionsSetDTO.setEighteen(loanConditionsSet.getIsUse());
          break;
        }
          case 17:{
//          loanConditionsSetDTO.setSecondhandRateMax(new BigDecimal(loanConditionsSet.getParamExplain()));
//          loanConditionsSetDTO.setNine(loanConditionsSet.getIsUse());
          
          if(loanConditionsSet.getParamValue().equals("1")){
            loanConditionsSetDTO.setComNature_3(loanConditionsSet.getParamExplain());
           
          }
          if(loanConditionsSet.getParamValue().equals("2")){
            loanConditionsSetDTO.setPersonCount_3(loanConditionsSet.getParamExplain());
            
          }
          if(loanConditionsSet.getParamValue().equals("3")){
            loanConditionsSetDTO.setMonthCount_3(loanConditionsSet.getParamExplain());
            
          }
          loanConditionsSetDTO.setNinteen(loanConditionsSet.getIsUse());
          break;
        }
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return loanConditionsSetDTO;
  }
  /**
   * 贷款前提条件设置
   * 确定按钮
   * @author 郭婧平
   * 2007-12-5
   */
  public void saveLoanConditionsSetInfo(LoanConditionsSetDTO loanConditionsSetDTO,SecurityInfo securityInfo) throws Exception{
    try{
      String office=loanConditionsSetDTO.getOffice();
      List officeList = securityInfo.getOfficeList();
      if("100".equals(office)){
        OfficeDto office_1 = null;
        OfficeDto officeDto=new OfficeDto();
        officeDto.setOfficeCode("100");
        officeDto.setOfficeName("全部");
        officeList.add(officeDto);
        Iterator itr1 = officeList.iterator();
        while (itr1.hasNext()) {
          //删除pl008
          office_1 = (OfficeDto) itr1.next();
          office=office_1.getOfficeCode();
          loanConditionsSetDAO.deleteLoanConditionsSet(office);
          //插入pl008
          LoanConditionsSet loanConditionsSet=null;
          if(!loanConditionsSetDTO.getIsRegular().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getOne());
            loanConditionsSet.setParamDescrip("1是0否");
            loanConditionsSet.setParamValue(loanConditionsSetDTO.getIsRegular());
            loanConditionsSet.setParamNum("1");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!loanConditionsSetDTO.getChgbizMonth().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getTwo());
            loanConditionsSet.setParamDescrip("公积金连续汇缴月数大于多少月");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getChgbizMonth());
            loanConditionsSet.setParamNum("2");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!loanConditionsSetDTO.getAccountOpenMonth().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getThree());
            loanConditionsSet.setParamDescrip("公积开户时间大于多少月");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getAccountOpenMonth());
            loanConditionsSet.setParamNum("3");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!loanConditionsSetDTO.getMaleAge().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getFour());
            loanConditionsSet.setParamDescrip("1男2女");
            loanConditionsSet.setParamValue("1");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMaleAge());
            loanConditionsSet.setParamNum("4");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!loanConditionsSetDTO.getFemaleAge().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getFour());
            loanConditionsSet.setParamDescrip("1男2女");
            loanConditionsSet.setParamValue("2");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getFemaleAge());
            loanConditionsSet.setParamNum("4");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!loanConditionsSetDTO.getLoanLimitMin().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getFive());
            loanConditionsSet.setParamDescrip("1最低期限2最高期限");
            loanConditionsSet.setParamValue("1");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getLoanLimitMin());
            loanConditionsSet.setParamNum("5");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!loanConditionsSetDTO.getLoanLimitMax().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getFive());
            loanConditionsSet.setParamDescrip("1最低期限2最高期限");
            loanConditionsSet.setParamValue("2");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getLoanLimitMax());
            loanConditionsSet.setParamNum("5");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!loanConditionsSetDTO.getOverTimeMax().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getSix());
            loanConditionsSet.setParamDescrip("信用情况,曾逾期不超过多少月");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getOverTimeMax());
            loanConditionsSet.setParamNum("6");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!(loanConditionsSetDTO.getLoanMoneyMax().equals("")||loanConditionsSetDTO.getLoanMoneyMax().toString().equals("0"))){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getSeven());
            loanConditionsSet.setParamDescrip("1单方拥有2有辅助贷款人");
            loanConditionsSet.setParamValue("1");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getLoanMoneyMax().toString());
            loanConditionsSet.setParamNum("7");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!(loanConditionsSetDTO.getOtherLoanMoneyMax().equals("")||loanConditionsSetDTO.getOtherLoanMoneyMax().toString().equals("0"))){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getSeven());
            loanConditionsSet.setParamDescrip("1单方拥有2有辅助贷款人");
            loanConditionsSet.setParamValue("2");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getOtherLoanMoneyMax().toString());
            loanConditionsSet.setParamNum("7");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!(loanConditionsSetDTO.getMerchandiseRateMax().equals("")||loanConditionsSetDTO.getMerchandiseRateMax().toString().equals("0"))){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getEight());
            loanConditionsSet.setParamDescrip("贷款金额不能超过商品房价的百分比");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMerchandiseRateMax().toString());
            loanConditionsSet.setParamNum("8");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!(loanConditionsSetDTO.getSecondhandRateMax().equals("")||loanConditionsSetDTO.getSecondhandRateMax().toString().equals("0"))){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getNine());
            loanConditionsSet.setParamDescrip("贷款金额不能超过二手房价的百分比");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getSecondhandRateMax().toString());
            loanConditionsSet.setParamNum("9");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!(loanConditionsSetDTO.getMerchandiseMoneyMax().equals("")||loanConditionsSetDTO.getMerchandiseMoneyMax().toString().equals("0"))){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getTen());
            loanConditionsSet.setParamDescrip("商品房贷款最高金额不超过多少元");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMerchandiseMoneyMax().toString());
            loanConditionsSet.setParamNum("10");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!(loanConditionsSetDTO.getSecondhandMoneyMax().equals("")||loanConditionsSetDTO.getSecondhandMoneyMax().toString().equals("0"))){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getEleven());
            loanConditionsSet.setParamDescrip("二手房贷款最高金额不超过多少元");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getSecondhandMoneyMax().toString());
            loanConditionsSet.setParamNum("11");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          //新增
          if(!loanConditionsSetDTO.getQianJiaoMonth().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getTwo());
            loanConditionsSet.setParamDescrip("公积开户时间欠缴月数小于");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getQianJiaoMonth());
            loanConditionsSet.setParamNum("2");
            loanConditionsSet.setParamValue("1");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!(loanConditionsSetDTO.getSecondhandRateMax_1().equals("")||loanConditionsSetDTO.getSecondhandRateMax_1().toString().equals("0"))){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getTwive());
            loanConditionsSet.setParamDescrip("贷款金额不能超过二手房价的百分比(5--10年)");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getSecondhandRateMax_1().toString());
            loanConditionsSet.setParamNum("9");
            loanConditionsSet.setParamValue("1");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!(loanConditionsSetDTO.getSecondhandRateMax_2().equals("")||loanConditionsSetDTO.getSecondhandRateMax_2().toString().equals("0"))){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getThirteen());
            loanConditionsSet.setParamDescrip("贷款金额不能超过二手房价的百分比(11年以上)");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getSecondhandRateMax_2().toString());
            loanConditionsSet.setParamNum("9");
            loanConditionsSet.setParamValue("2");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!loanConditionsSetDTO.getBeiShu().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getFourteen());
            loanConditionsSet.setParamDescrip("不得超过贷款家庭成员退休年龄内所交纳住房公积金数额倍数");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getBeiShu().toString());
            loanConditionsSet.setParamNum("12");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!loanConditionsSetDTO.getTimeMax_1().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getFifteen());
            loanConditionsSet.setParamDescrip("贷款最高年限：1商品房2二手房");
            loanConditionsSet.setParamValue("1");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getTimeMax_1());
            loanConditionsSet.setParamNum("13");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!loanConditionsSetDTO.getTimeMax_2().equals("")){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getFifteen());
            loanConditionsSet.setParamDescrip("贷款最高年限：1商品房2二手房");
            loanConditionsSet.setParamValue("2");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getTimeMax_2());
            loanConditionsSet.setParamNum("13");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!(loanConditionsSetDTO.getSalaryRate().equals("")||loanConditionsSetDTO.getSalaryRate().toString().equals("0"))){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getSixteen());
            loanConditionsSet.setParamDescrip("借款人月收入还款比例");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getSalaryRate().toString());
            loanConditionsSet.setParamNum("14");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!"".equals(loanConditionsSetDTO.getComNature_1())){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getSeventeen());
            loanConditionsSet.setParamDescrip("贷款单位性质1");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getComNature_1().toString());
            loanConditionsSet.setParamNum("15");
            loanConditionsSet.setParamValue("1");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!"".equals(loanConditionsSetDTO.getComNature_2())){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getEighteen());
            loanConditionsSet.setParamDescrip("贷款单位性质2");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getComNature_2().toString());
            loanConditionsSet.setParamNum("16");
            loanConditionsSet.setParamValue("1");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!"".equals(loanConditionsSetDTO.getComNature_3())){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getNinteen());
            loanConditionsSet.setParamDescrip("贷款单位性质3");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getComNature_3().toString());
            loanConditionsSet.setParamNum("17");
            loanConditionsSet.setParamValue("1");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          
          
          
          
          
          
          if(!"".equals(loanConditionsSetDTO.getPersonCount_1())){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getSeventeen());
            loanConditionsSet.setParamDescrip("贷款单位性质1人数");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getPersonCount_1().toString());
            loanConditionsSet.setParamNum("15");
            loanConditionsSet.setParamValue("2");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!"".equals(loanConditionsSetDTO.getPersonCount_2())){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getEighteen());
            loanConditionsSet.setParamDescrip("贷款单位性质2人数");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getPersonCount_2().toString());
            loanConditionsSet.setParamNum("16");
            loanConditionsSet.setParamValue("2");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!"".equals(loanConditionsSetDTO.getPersonCount_3())){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getNinteen());
            loanConditionsSet.setParamDescrip("贷款单位性质3人数");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getPersonCount_3().toString());
            loanConditionsSet.setParamNum("17");
            loanConditionsSet.setParamValue("2");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          
          
          
          if(!"".equals(loanConditionsSetDTO.getMonthCount_1())){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getSeventeen());
            loanConditionsSet.setParamDescrip("贷款单位性质1月数");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMonthCount_1().toString());
            loanConditionsSet.setParamNum("15");
            loanConditionsSet.setParamValue("3");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!"".equals(loanConditionsSetDTO.getMonthCount_2())){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getEighteen());
            loanConditionsSet.setParamDescrip("贷款单位性质2月数");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMonthCount_2().toString());
            loanConditionsSet.setParamNum("16");
            loanConditionsSet.setParamValue("3");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          if(!"".equals(loanConditionsSetDTO.getMonthCount_3())){
            loanConditionsSet=new LoanConditionsSet();
            loanConditionsSet.setOffice(office);
            loanConditionsSet.setIsUse(loanConditionsSetDTO.getNinteen());
            loanConditionsSet.setParamDescrip("贷款单位性质3月数");
            loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMonthCount_3().toString());
            loanConditionsSet.setParamNum("17");
            loanConditionsSet.setParamValue("3");
            loanConditionsSetDAO.insert(loanConditionsSet);
          }
          //新增
          // 插入日志PL021
          PlOperateLog plOperateLog = new PlOperateLog();
          plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
          plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_PARAMETERS));
          plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
          plOperateLog.setOpIp(securityInfo.getUserIp());
          plOperateLog.setOpTime(new Date());
          plOperateLog.setOperator(securityInfo.getUserName());
          plOperateLogDAO.insert(plOperateLog);
        }
        officeList.remove(officeList.size()-1);
      }else{
        String count ="";
//      删除pl008
        loanConditionsSetDAO.deleteLoanConditionsSet(office);
        //插入pl008
        LoanConditionsSet loanConditionsSet=null;
        //用value
        if(!loanConditionsSetDTO.getIsRegular().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getOne());
          loanConditionsSet.setParamDescrip("1是0否");
          loanConditionsSet.setParamValue(loanConditionsSetDTO.getIsRegular());
          loanConditionsSet.setParamNum("1");
          count=loanConditionsSetDAO.queryParamValueCount_wsh(office, "1");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh("", "100","1");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamValue_LJ("100", "1");
            if(!loanConditionsSetDTO.getIsRegular().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_1("", "100","1");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        //yong  explain
        if(!loanConditionsSetDTO.getChgbizMonth().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getTwo());
          loanConditionsSet.setParamDescrip("公积金连续汇缴月数大于多少月");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getChgbizMonth());
          loanConditionsSet.setParamNum("2");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "2","");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","2","");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "2","");
            if(!loanConditionsSetDTO.getChgbizMonth().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","2","");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!loanConditionsSetDTO.getAccountOpenMonth().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getThree());
          loanConditionsSet.setParamDescrip("公积开户时间大于多少月");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getAccountOpenMonth());
          loanConditionsSet.setParamNum("3");
          count=loanConditionsSetDAO.queryParamExp_LJ(office, "3");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_2("", "100","3");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamValue_wsh_1("100", "3");
            if(!loanConditionsSetDTO.getAccountOpenMonth().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_2("", "100","3");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        //value he explain
        if(!loanConditionsSetDTO.getMaleAge().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getFour());
          loanConditionsSet.setParamDescrip("1男2女");
          loanConditionsSet.setParamValue("1");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMaleAge());
          loanConditionsSet.setParamNum("4");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "4","1");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","4","1");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "4","1");
            if(!loanConditionsSetDTO.getMaleAge().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","4","1");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!loanConditionsSetDTO.getFemaleAge().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getFour());
          loanConditionsSet.setParamDescrip("1男2女");
          loanConditionsSet.setParamValue("2");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getFemaleAge());
          loanConditionsSet.setParamNum("4");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "4","2");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","4","2");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "4","2");
            if(!loanConditionsSetDTO.getFemaleAge().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","4","2");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!loanConditionsSetDTO.getLoanLimitMin().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getFive());
          loanConditionsSet.setParamDescrip("1最低期限2最高期限");
          loanConditionsSet.setParamValue("1");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getLoanLimitMin());
          loanConditionsSet.setParamNum("5");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "5","1");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","5","1");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "5","1");
            if(!loanConditionsSetDTO.getLoanLimitMin().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","5","1");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!loanConditionsSetDTO.getLoanLimitMax().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getFive());
          loanConditionsSet.setParamDescrip("1最低期限2最高期限");
          loanConditionsSet.setParamValue("2");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getLoanLimitMax());
          loanConditionsSet.setParamNum("5");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "5","2");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","5","2");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "5","2");
            if(!loanConditionsSetDTO.getLoanLimitMax().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","5","2");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!loanConditionsSetDTO.getOverTimeMax().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getSix());
          loanConditionsSet.setParamDescrip("信用情况,曾逾期不超过多少月");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getOverTimeMax());
          loanConditionsSet.setParamNum("6");
          count=loanConditionsSetDAO.queryParamExp_LJ(office, "6");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_2("", "100","6");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamValue_wsh_1("100", "6");
            if(!loanConditionsSetDTO.getOverTimeMax().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_2("", "100","6");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!(loanConditionsSetDTO.getLoanMoneyMax().equals("")||loanConditionsSetDTO.getLoanMoneyMax().toString().equals("0"))){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getSeven());
          loanConditionsSet.setParamDescrip("1单方拥有2有辅助贷款人");
          loanConditionsSet.setParamValue("1");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getLoanMoneyMax().toString());
          loanConditionsSet.setParamNum("7");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "7","1");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","7","1");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "7","1");
            if(!loanConditionsSetDTO.getLoanMoneyMax().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","7","1");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!(loanConditionsSetDTO.getOtherLoanMoneyMax().equals("")||loanConditionsSetDTO.getOtherLoanMoneyMax().toString().equals("0"))){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getSeven());
          loanConditionsSet.setParamDescrip("1单方拥有2有辅助贷款人");
          loanConditionsSet.setParamValue("2");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getOtherLoanMoneyMax().toString());
          loanConditionsSet.setParamNum("7");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "7","2");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","7","2");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "7","2");
            if(!loanConditionsSetDTO.getOtherLoanMoneyMax().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","7","2");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!(loanConditionsSetDTO.getMerchandiseRateMax().equals("")||loanConditionsSetDTO.getMerchandiseRateMax().toString().equals("0"))){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getEight());
          loanConditionsSet.setParamDescrip("贷款金额不能超过商品房价的百分比");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMerchandiseRateMax().toString());
          loanConditionsSet.setParamNum("8");
          count=loanConditionsSetDAO.queryParamExp_LJ(office, "8");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_2("", "100","8");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamValue_wsh_1("100", "8");
            if(!loanConditionsSetDTO.getMerchandiseRateMax().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_2("", "100","8");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!(loanConditionsSetDTO.getSecondhandRateMax().equals("")||loanConditionsSetDTO.getSecondhandRateMax().toString().equals("0"))){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getNine());
          loanConditionsSet.setParamDescrip("贷款金额不能超过二手房价的百分比");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getSecondhandRateMax().toString());
          loanConditionsSet.setParamNum("9");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "9","");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","9","");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "9","");
            if(!loanConditionsSetDTO.getSecondhandRateMax().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","9","");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!(loanConditionsSetDTO.getMerchandiseMoneyMax().equals("")||loanConditionsSetDTO.getMerchandiseMoneyMax().toString().equals("0"))){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getTen());
          loanConditionsSet.setParamDescrip("商品房贷款最高金额不超过多少元");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMerchandiseMoneyMax().toString());
          loanConditionsSet.setParamNum("10");
          count=loanConditionsSetDAO.queryParamExp_LJ(office, "10");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_2("", "100","10");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamValue_wsh_1("100", "10");
            if(!loanConditionsSetDTO.getMerchandiseMoneyMax().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_2("", "100","10");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!(loanConditionsSetDTO.getSecondhandMoneyMax().equals("")||loanConditionsSetDTO.getSecondhandMoneyMax().toString().equals("0"))){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getEleven());
          loanConditionsSet.setParamDescrip("二手房贷款最高金额不超过多少元");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getSecondhandMoneyMax().toString());
          loanConditionsSet.setParamNum("11");
          count=loanConditionsSetDAO.queryParamExp_LJ(office, "11");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_2("", "100","11");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamValue_wsh_1("100", "11");
            if(!loanConditionsSetDTO.getSecondhandMoneyMax().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_2("", "100","11");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        //新增
        if(!loanConditionsSetDTO.getQianJiaoMonth().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getTwo());
          loanConditionsSet.setParamDescrip("公积开户时间欠缴月数小于");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getQianJiaoMonth());
          loanConditionsSet.setParamNum("2");
          loanConditionsSet.setParamValue("1");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "2","1");
          Integer i=loanConditionsSetDAO.queryTestCount();
          System.out.println(developerDAO.queryTestCount()+"=================");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","2","1");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "2","1");
            if(!value.equals(loanConditionsSetDTO.getQianJiaoMonth())){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","2","1");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!(loanConditionsSetDTO.getSecondhandRateMax_1().equals("")||loanConditionsSetDTO.getSecondhandRateMax_1().toString().equals("0"))){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getTwive());
          loanConditionsSet.setParamDescrip("贷款金额不能超过二手房价的百分比(5--10年)");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getSecondhandRateMax_1().toString());
          loanConditionsSet.setParamNum("9");
          loanConditionsSet.setParamValue("1");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "9","1");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","9","1");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "9","1");
            if(!loanConditionsSetDTO.getSecondhandRateMax_1().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","9","1");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!(loanConditionsSetDTO.getSecondhandRateMax_2().equals("")||loanConditionsSetDTO.getSecondhandRateMax_2().toString().equals("0"))){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getThirteen());
          loanConditionsSet.setParamDescrip("贷款金额不能超过二手房价的百分比(11年以上)");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getSecondhandRateMax_2().toString());
          loanConditionsSet.setParamNum("9");
          loanConditionsSet.setParamValue("2");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "9","2");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","9","2");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "9","2");
            if(!loanConditionsSetDTO.getSecondhandRateMax_2().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","9","2");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!loanConditionsSetDTO.getBeiShu().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getFourteen());
          loanConditionsSet.setParamDescrip("不得超过贷款家庭成员退休年龄内所交纳住房公积金数额倍数");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getBeiShu().toString());
          loanConditionsSet.setParamNum("12");
          count=loanConditionsSetDAO.queryParamExp_LJ(office, "12");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_2("", "100","12");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamValue_wsh_1("100", "12");
            if(!loanConditionsSetDTO.getBeiShu().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_2("", "100","12");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!loanConditionsSetDTO.getTimeMax_1().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getFifteen());
          loanConditionsSet.setParamDescrip("贷款最高年限：1商品房2二手房");
          loanConditionsSet.setParamValue("1");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getTimeMax_1());
          loanConditionsSet.setParamNum("13");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "13","1");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","13","1");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "13","1");
            if(!loanConditionsSetDTO.getTimeMax_1().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","13","1");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!loanConditionsSetDTO.getTimeMax_2().equals("")){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getFifteen());
          loanConditionsSet.setParamDescrip("贷款最高年限：1商品房2二手房");
          loanConditionsSet.setParamValue("2");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getTimeMax_2());
          loanConditionsSet.setParamNum("13");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "13","2");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","13","2");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "13","2");
            if(!loanConditionsSetDTO.getTimeMax_2().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","13","2");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!(loanConditionsSetDTO.getSalaryRate().equals("")||loanConditionsSetDTO.getSalaryRate().toString().equals("0"))){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getSixteen());
          loanConditionsSet.setParamDescrip("借款人月收入还款比例");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getSalaryRate().toString());
          loanConditionsSet.setParamNum("14");
          count=loanConditionsSetDAO.queryParamExp_LJ(office, "14");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_2("", "100","14");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamValue_wsh_1("100", "14");
            if(!loanConditionsSetDTO.getSalaryRate().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_2("", "100","14");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!"".equals(loanConditionsSetDTO.getComNature_1())){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getSeventeen());
          loanConditionsSet.setParamDescrip("贷款单位性质1");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getComNature_1().toString());
          loanConditionsSet.setParamNum("15");
          loanConditionsSet.setParamValue("1");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "15","1");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","15","1");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "15","1");
            if(!loanConditionsSetDTO.getComNature_1().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","15","1");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!"".equals(loanConditionsSetDTO.getComNature_2())){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getEighteen());
          loanConditionsSet.setParamDescrip("贷款单位性质2");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getComNature_2().toString());
          loanConditionsSet.setParamNum("16");
          loanConditionsSet.setParamValue("1");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "16","1");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","16","1");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "16","1");
            if(!loanConditionsSetDTO.getComNature_2().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","16","1");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!"".equals(loanConditionsSetDTO.getComNature_3())){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getNinteen());
          loanConditionsSet.setParamDescrip("贷款单位性质3");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getComNature_3().toString());
          loanConditionsSet.setParamNum("17");
          loanConditionsSet.setParamValue("1");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "17","1");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","17","1");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "17","1");
            if(!loanConditionsSetDTO.getComNature_3().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","17","1");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        
        
        
        
        
        
        if(!"".equals(loanConditionsSetDTO.getPersonCount_1())){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getSeventeen());
          loanConditionsSet.setParamDescrip("贷款单位性质1人数");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getPersonCount_1().toString());
          loanConditionsSet.setParamNum("15");
          loanConditionsSet.setParamValue("2");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "15","2");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","15","2");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "15","2");
            if(!loanConditionsSetDTO.getPersonCount_1().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","15","2");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!"".equals(loanConditionsSetDTO.getPersonCount_2())){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getEighteen());
          loanConditionsSet.setParamDescrip("贷款单位性质2人数");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getPersonCount_2().toString());
          loanConditionsSet.setParamNum("16");
          loanConditionsSet.setParamValue("2");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "16","2");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","16","2");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "16","2");
            if(!loanConditionsSetDTO.getPersonCount_2().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","16","2");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!"".equals(loanConditionsSetDTO.getPersonCount_3())){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getNinteen());
          loanConditionsSet.setParamDescrip("贷款单位性质3人数");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getPersonCount_3().toString());
          loanConditionsSet.setParamNum("17");
          loanConditionsSet.setParamValue("2");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "17","2");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","17","2");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "17","2");
            if(!loanConditionsSetDTO.getPersonCount_3().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","17","2");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        
        
        
        if(!"".equals(loanConditionsSetDTO.getMonthCount_1())){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getSeventeen());
          loanConditionsSet.setParamDescrip("贷款单位性质1月数");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMonthCount_1().toString());
          loanConditionsSet.setParamNum("15");
          loanConditionsSet.setParamValue("3");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "15","3");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","15","3");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "15","3");
            if(!loanConditionsSetDTO.getMonthCount_1().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","15","3");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!"".equals(loanConditionsSetDTO.getMonthCount_2())){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getEighteen());
          loanConditionsSet.setParamDescrip("贷款单位性质2月数");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMonthCount_2().toString());
          loanConditionsSet.setParamNum("16");
          loanConditionsSet.setParamValue("3");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "16","3");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","16","3");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "16","3");
            if(!loanConditionsSetDTO.getMonthCount_2().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","16","3");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        if(!"".equals(loanConditionsSetDTO.getMonthCount_3())){
          loanConditionsSet=new LoanConditionsSet();
          loanConditionsSet.setOffice(office);
          loanConditionsSet.setIsUse(loanConditionsSetDTO.getNinteen());
          loanConditionsSet.setParamDescrip("贷款单位性质3月数");
          loanConditionsSet.setParamExplain(loanConditionsSetDTO.getMonthCount_3().toString());
          loanConditionsSet.setParamNum("17");
          loanConditionsSet.setParamValue("3");
          count=loanConditionsSetDAO.queryParamExpCount_wsh(office, "17","3");
          if(!"0".equals(count)&&Integer.parseInt(count)>1){
            //更新对应的ploo3中的数据为""
            loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","17","3");
          }
          if("1".equals(count)){
            String value="";
            value=loanConditionsSetDAO.queryParamExpCount_wsh_1("100", "17","3");
            if(!loanConditionsSetDTO.getMonthCount_3().toString().equals(value)){
              loanConditionsSetDAO.updatePl008_wsh_num_value("", "100","17","3");
            }
          }
          loanConditionsSetDAO.insert(loanConditionsSet);
        }
        //新增
        // 插入日志PL021
        PlOperateLog plOperateLog = new PlOperateLog();
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_DATAPREPARATION_PARAMETERS));
        plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));
        plOperateLog.setOpIp(securityInfo.getUserIp());
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(securityInfo.getUserName());
        plOperateLogDAO.insert(plOperateLog);
      }
      
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  public void setLoanConditionsSetDAO(LoanConditionsSetDAO loanConditionsSetDAO) {
    this.loanConditionsSetDAO = loanConditionsSetDAO;
  }
  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }
  public void setDeveloperDAO(DeveloperDAO developerDAO) {
    this.developerDAO = developerDAO;
  }
}
