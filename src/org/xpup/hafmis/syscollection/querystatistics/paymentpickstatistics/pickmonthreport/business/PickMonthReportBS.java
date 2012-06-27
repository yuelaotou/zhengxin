package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.bsinterface.IPickMonthReportBS;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.dto.PickMonRepBankPopInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.dto.PickMonRepInfoDTO;

public class PickMonthReportBS implements IPickMonthReportBS {

  private PickHeadDAO pickHeadDAO = null;
  
  private CollBankDAO collBankDAO = null;
  
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }
  public void setPickHeadDAO(PickHeadDAO pickHeadDAO) {
    this.pickHeadDAO = pickHeadDAO;
  }
  /**
   * @author wangshuang
   * 查询提取月报表信息
   */
  public List queryPickMonRepInfo(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    
    List list = null;
    List returnList = new ArrayList();
    int personCount_buyhouse = 0;
    BigDecimal pickMoney_buyhouse = new BigDecimal(0.00);
    /*还贷*/
    int personCount_callback = 0;
    BigDecimal pickMoney_callback = new BigDecimal(0.00);
    /*其他*/
    int personCount_other = 0;
    BigDecimal pickMoney_other = new BigDecimal(0.00);
    /*退休*/
    int personCount_retire = 0;
    BigDecimal pickMoney_retire = new BigDecimal(0.00);
    /*失业*/
    int personCount_jobless = 0;
    BigDecimal pickMoney_jobless = new BigDecimal(0.00);
    /*死亡*/
    int personCount_death = 0;
    BigDecimal pickMoney_death = new BigDecimal(0.00);
    /*个贷扣款*/
    int personCount_deduct = 0;
    BigDecimal pickMoney_deduct = new BigDecimal(0.00);
    /*合计(统计每个银行的)*/
    int personCount_total = 0;
    BigDecimal pickMoney_total = new BigDecimal(0.00);
    //总的合计(统计每个原因的)
    //购房
    int personCountSum_buyhouse = 0;
    BigDecimal pickMoneySum_buyhouse = new BigDecimal(0.00);
    //死亡
    int personCountSum_death = 0;
    BigDecimal pickMoneySum_death = new BigDecimal(0.00);
    //退休
    int personCountSum_retire = 0;
    BigDecimal pickMoneySum_retire = new BigDecimal(0.00);
    //失业
    int personCountSum_jobless = 0;
    BigDecimal pickMoneySum_jobless = new BigDecimal(0.00);
    //还贷
    int personCountSum_callback = 0;
    BigDecimal pickMoneySum_callback = new BigDecimal(0.00);
    //其他
    int personCountSum_other = 0;
    BigDecimal pickMoneySum_other = new BigDecimal(0.00);
    //扣款
    int personCountSum_deduct = 0;
    BigDecimal pickMoneySum_deduct = new BigDecimal(0.00);
    //总计
    int personCountSum_total = 0;
    BigDecimal pickMoneySum_total = new BigDecimal(0.00);
    try{
      String office = (String) pagination.getQueryCriterions().get("office");
      String bank = (String) pagination.getQueryCriterions().get("bank");
//      String month = (String) pagination.getQueryCriterions().get("month");
      String startDate = (String) pagination.getQueryCriterions().get("startDate");
      String endDate = (String) pagination.getQueryCriterions().get("endDate");
      list = pickHeadDAO.queryMonReportInfoList(office, bank, startDate, securityInfo,endDate);
      String bank_temp = "";
//      ┏━━━━━━━━━━━┓　 
//      ┃▉▉▉▉▉▉▉▉ 99.9%┃┈記憶正在加載中……　 
//      ┗━━━━━━━━━━━┛ 請稍候……　 
      for(int i=0;i<list.size();){
        PickMonRepInfoDTO dto = (PickMonRepInfoDTO)list.get(i);
        if(i==0){
          bank_temp = dto.getCollBank();
        }
        if(!bank_temp.equals(dto.getCollBank())){
          PickMonRepInfoDTO dto1 = new PickMonRepInfoDTO();
          dto1.setCollBank(collBankDAO.getCollBankByCollBankid(bank_temp).getCollBankName());
          //购房
          dto1.setPersonCount_buyhouse(new Integer(personCount_buyhouse));
          dto1.setPickMoney_buyhouse(pickMoney_buyhouse);
          dto1.setPickMoneyRate_buyhouse(pickMoney_buyhouse.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
              .multiply(new BigDecimal(100)).setScale(2)+"%");
          //死亡
          dto1.setPersonCount_death(new Integer(personCount_death));
          dto1.setPickMoney_death(pickMoney_death);
          dto1.setPickMoneyRate_death(pickMoney_death.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
              .multiply(new BigDecimal(100)).setScale(2)+"%");
          //失业
          dto1.setPersonCount_jobless(new Integer(personCount_jobless));
          dto1.setPickMoney_jobless(pickMoney_jobless);
          dto1.setPickMoneyRate_jobless(pickMoney_jobless.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
              .multiply(new BigDecimal(100)).setScale(2)+"%");
          //退休
          dto1.setPersonCount_retire(new Integer(personCount_retire));
          dto1.setPickMoney_retire(pickMoney_retire);
          dto1.setPickMoneyRate_retire(pickMoney_retire.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
              .multiply(new BigDecimal(100)).setScale(2)+"%");
          //还贷
          dto1.setPersonCount_callback(new Integer(personCount_callback));
          dto1.setPickMoney_callback(pickMoney_callback);
          dto1.setPickMoneyRate_callback(pickMoney_callback.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
              .multiply(new BigDecimal(100)).setScale(2)+"%");
          //扣款
          dto1.setPersonCount_deduct(new Integer(personCount_deduct));
          dto1.setPickMoney_deduct(pickMoney_deduct);
          BigDecimal remainer = new BigDecimal(1.00).subtract(pickMoney_other.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
              .add(pickMoney_buyhouse.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP))
              .add(pickMoney_retire.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP))
              .add(pickMoney_death.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP))
              .add(pickMoney_jobless.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP))
              .add(pickMoney_callback.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)));
          dto1.setPickMoneyRate_deduct(remainer.multiply(new BigDecimal(100))+"%");                       
          //其他
          dto1.setPersonCount_other(new Integer(personCount_other));
          dto1.setPickMoney_other(pickMoney_other);
          dto1.setPickMoneyRate_other(pickMoney_other.divide(pickMoney_total, 2, BigDecimal.ROUND_HALF_UP)
              .multiply(new BigDecimal(100)).setScale(2)+"%");
          //全部
          dto1.setPersonCount_total(new Integer(personCount_total));
          dto1.setPickMoney_total(pickMoney_total);
          //人数归0
          personCount_buyhouse = 0;
          personCount_callback = 0;
          personCount_other = 0;
          personCount_retire = 0;
          personCount_jobless = 0;
          personCount_death = 0;
          personCount_deduct = 0;
          personCount_total = 0;
          //提取金额归0
          pickMoney_buyhouse = new BigDecimal(0.00);
          pickMoney_callback = new BigDecimal(0.00);
          pickMoney_other = new BigDecimal(0.00);
          pickMoney_retire = new BigDecimal(0.00);
          pickMoney_jobless = new BigDecimal(0.00);
          pickMoney_death = new BigDecimal(0.00);
          pickMoney_deduct = new BigDecimal(0.00);
          pickMoney_total = new BigDecimal(0.00);
          dto1.setCollBankId(bank_temp);//银行编号
          returnList.add(dto1);
        }else{
//        1.购房
          if(dto.getPickReason().equals(BusiConst.PICKUPREASON_BUYHOUSE+"")){
            personCount_buyhouse += dto.getPersonCount_temp().intValue();
            pickMoney_buyhouse = pickMoney_buyhouse.add(dto.getPickMoney_temp());
            personCountSum_buyhouse += dto.getPersonCount_temp().intValue();
            pickMoneySum_buyhouse = pickMoneySum_buyhouse.add(dto.getPickMoney_temp());
          }
          //死亡
          if(dto.getPickReason().equals(BusiConst.PICKUPREASON_DEATH+"")){
            personCount_death += dto.getPersonCount_temp().intValue();
            pickMoney_death = pickMoney_death.add(dto.getPickMoney_temp());
            personCountSum_death += dto.getPersonCount_temp().intValue();
            pickMoneySum_death = pickMoneySum_death.add(dto.getPickMoney_temp());
          }
          //失业
          if(dto.getPickReason().equals(BusiConst.PICKUPREASON_JOBLESS+"")){
            personCount_jobless += dto.getPersonCount_temp().intValue();
            pickMoney_jobless = pickMoney_jobless.add(dto.getPickMoney_temp());
            personCountSum_jobless += dto.getPersonCount_temp().intValue();
            pickMoneySum_jobless = pickMoneySum_jobless.add(dto.getPickMoney_temp());
          }
          //退休
          if(dto.getPickReason().equals(BusiConst.PICKUPREASON_BOWOUT+"")){
            personCount_retire += dto.getPersonCount_temp().intValue();
            pickMoney_retire = pickMoney_retire.add(dto.getPickMoney_temp());
            personCountSum_retire += dto.getPersonCount_temp().intValue();
            pickMoneySum_retire = pickMoneySum_retire.add(dto.getPickMoney_temp());
          }
          //还贷
          if(dto.getPickReason().equals(BusiConst.PICKUPREASON_GIVEMONEYClEAR+"")){
            personCount_callback += dto.getPersonCount_temp().intValue();
            pickMoney_callback = pickMoney_callback.add(dto.getPickMoney_temp());
            personCountSum_callback += dto.getPersonCount_temp().intValue();
            pickMoneySum_callback = pickMoneySum_callback.add(dto.getPickMoney_temp());
          }
          //扣款
          if(dto.getPickReason().equals(BusiConst.PICKUPREASON_GIVEMONEYBYMON+"")){
            personCount_deduct += dto.getPersonCount_temp().intValue();
            pickMoney_deduct = pickMoney_deduct.add(dto.getPickMoney_temp());
            personCountSum_deduct += dto.getPersonCount_temp().intValue();
            pickMoneySum_deduct = pickMoneySum_deduct.add(dto.getPickMoney_temp());
          }
          //其他
          if(dto.getPickReason().equals(BusiConst.PICKUPREASON_DISEASE+"")||
             dto.getPickReason().equals(BusiConst.PICKUPREASON_DISTRESS+"")||
             dto.getPickReason().equals(BusiConst.PICKUPREASON_PARTREST+"")||
             dto.getPickReason().equals(BusiConst.PICKUPREASON_DECRUITMENT+"")||
             dto.getPickReason().equals(BusiConst.PICKUPREASON_DISTORY+"")||
             dto.getPickReason().equals(BusiConst.PICKUPREASON_SETTLE+"")){
            personCount_other += dto.getPersonCount_temp().intValue();
            pickMoney_other = pickMoney_other.add(dto.getPickMoney_temp());
            personCountSum_other += dto.getPersonCount_temp().intValue();
            pickMoneySum_other = pickMoneySum_other.add(dto.getPickMoney_temp());
          }
          personCount_total += dto.getPersonCount_temp().intValue();
          pickMoney_total = pickMoney_total.add(dto.getPickMoney_temp());
          personCountSum_total += dto.getPersonCount_temp().intValue();
          pickMoneySum_total = pickMoneySum_total.add(dto.getPickMoney_temp());
          i++;
        }
        bank_temp = dto.getCollBank();
      }
      if(!list.isEmpty()){
        //插入最后一个银行的数据
        PickMonRepInfoDTO endDto = new PickMonRepInfoDTO();
        endDto.setCollBank(collBankDAO.getCollBankByCollBankid(bank_temp).getCollBankName());
        endDto.setPersonCount_buyhouse(new Integer(personCount_buyhouse));
        endDto.setPickMoney_buyhouse(pickMoney_buyhouse);
        endDto.setPickMoneyRate_buyhouse(pickMoney_buyhouse.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
            .multiply(new BigDecimal(100)).setScale(2)+"%");
        
        endDto.setPersonCount_retire(new Integer(personCount_retire));
        endDto.setPickMoney_retire(pickMoney_retire);
        endDto.setPickMoneyRate_retire(pickMoney_retire.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
            .multiply(new BigDecimal(100)).setScale(2)+"%");
        
        endDto.setPersonCount_death(new Integer(personCount_death));
        endDto.setPickMoney_death(pickMoney_death);
        endDto.setPickMoneyRate_death(pickMoney_death.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
            .multiply(new BigDecimal(100)).setScale(2)+"%");
        
        endDto.setPersonCount_jobless(new Integer(personCount_jobless));
        endDto.setPickMoney_jobless(pickMoney_jobless);
        endDto.setPickMoneyRate_jobless(pickMoney_jobless.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
            .multiply(new BigDecimal(100)).setScale(2)+"%");
        
        endDto.setPersonCount_other(new Integer(personCount_other));
        endDto.setPickMoney_other(pickMoney_other);
        endDto.setPickMoneyRate_other(pickMoney_other.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
            .multiply(new BigDecimal(100)).setScale(2)+"%");
        
        endDto.setPersonCount_callback(new Integer(personCount_callback));
        endDto.setPickMoney_callback(pickMoney_callback);
        endDto.setPickMoneyRate_callback(pickMoney_callback.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
            .multiply(new BigDecimal(100)).setScale(2)+"%");
        //扣款
        endDto.setPersonCount_deduct(new Integer(personCount_deduct));
        endDto.setPickMoney_deduct(pickMoney_deduct);
        BigDecimal remainer = new BigDecimal(1.00).subtract(pickMoney_other.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)
            .add(pickMoney_buyhouse.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP))
            .add(pickMoney_retire.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP))
            .add(pickMoney_death.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP))
            .add(pickMoney_jobless.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP))
            .add(pickMoney_callback.divide(pickMoney_total, 4, BigDecimal.ROUND_HALF_UP)));
        if(pickMoney_deduct.compareTo(new BigDecimal(0.00))==0){
          endDto.setPickMoneyRate_deduct("0.00%");
        }else{
          if(remainer.compareTo(new BigDecimal(0.00))<0){
            endDto.setPickMoneyRate_deduct("0.00%");
          }else{
            endDto.setPickMoneyRate_deduct(remainer.multiply(new BigDecimal(100)).setScale(2)+"%");
          }
        }
        
        endDto.setPersonCount_total(new Integer(personCount_total));
        endDto.setPickMoney_total(pickMoney_total);
        endDto.setCollBankId(bank_temp);//银行编号
        returnList.add(endDto);
//      再加上条总计
        PickMonRepInfoDTO totalDto = new PickMonRepInfoDTO();
        totalDto.setCollBank("总计");
        totalDto.setPersonCount_buyhouse(new Integer(personCountSum_buyhouse));
        totalDto.setPickMoney_buyhouse(pickMoneySum_buyhouse);
        
        totalDto.setPersonCount_retire(new Integer(personCountSum_retire));
        totalDto.setPickMoney_retire(pickMoneySum_retire);
        
        totalDto.setPersonCount_death(new Integer(personCountSum_death));
        totalDto.setPickMoney_death(pickMoneySum_death);
        
        totalDto.setPersonCount_jobless(new Integer(personCountSum_jobless));
        totalDto.setPickMoney_jobless(pickMoneySum_jobless);
        
        totalDto.setPersonCount_other(new Integer(personCountSum_other));
        totalDto.setPickMoney_other(pickMoneySum_other);
        
        totalDto.setPersonCount_callback(new Integer(personCountSum_callback));
        totalDto.setPickMoney_callback(pickMoneySum_callback);
        
        totalDto.setPersonCount_deduct(new Integer(personCountSum_deduct));
        totalDto.setPickMoney_deduct(pickMoneySum_deduct);
        
        totalDto.setPersonCount_total(new Integer(personCountSum_total));
        totalDto.setPickMoney_total(pickMoneySum_total);
        returnList.add(totalDto);
      }
      
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return returnList;
  }
  /**
   * 银行弹出框
   */
  public List queryPickMonRepBankPopInfo(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    int count = 0;
    String startDate = (String)pagination.getQueryCriterions().get("startDate");
    String endDate = (String)pagination.getQueryCriterions().get("endDate");
    String orgId = (String)pagination.getQueryCriterions().get("orgId");
    String orgName = (String)pagination.getQueryCriterions().get("orgName");
    String empId = (String)pagination.getQueryCriterions().get("empId");
    String empName = (String)pagination.getQueryCriterions().get("empName");
    String bankId = (String)pagination.getQueryCriterions().get("bankId");
    String pickReason = (String)pagination.getQueryCriterions().get("pickReason");
    
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    
//    System.out.println("开始时间:"+startDate);
//    System.out.println("结束时间:"+endDate);
//    System.out.println("单位编号:"+orgId);
//    System.out.println("单位名称:"+orgName);
//    System.out.println("职工编号:"+empId);
//    System.out.println("职工名称:"+empName);
//    System.out.println("银行编号:"+bankId);
//    System.out.println("提取原因:"+pickReason);
    PickMonRepBankPopInfoDTO pickMonRepBankPopInfoDTO = new PickMonRepBankPopInfoDTO();
    list = pickHeadDAO.queryPickBankPopList(orgId, orgName, empId, empName, pickReason, startDate, endDate, bankId, start, pageSize, orderBy, order, securityInfo);
    if(list.size()!=0){
      for(int i=0;i<list.size();i++){
        pickMonRepBankPopInfoDTO = (PickMonRepBankPopInfoDTO)list.get(i);
        String tempreason = pickMonRepBankPopInfoDTO.getPickReason();
        String reason = BusiTools.getBusiValue(Integer.parseInt(""
            + tempreason), BusiConst.PICKUPREASON);
        String tempOrgId = pickMonRepBankPopInfoDTO.getOrgId();
        String orgid = BusiTools.convertTenNumber(tempOrgId);
        pickMonRepBankPopInfoDTO.setPickReason(reason);
        pickMonRepBankPopInfoDTO.setOrgId(orgid);
      }
    }
    List templist = pickHeadDAO.queryPickBankPopCount(orgId, orgName, empId, empName, pickReason, startDate, endDate, bankId, start, pageSize, orderBy, order, securityInfo);
    count = templist.size();
    pagination.setNrOfElements(count);
    return list;
  }

}
