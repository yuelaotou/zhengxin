package org.xpup.hafmis.syscollection.paymng.monthpay.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.ChgOrgRateDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentSalaryBaseDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.ChgPersonTailDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgExcessPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgExcessPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.SearchLackInfoDAO;
import org.xpup.hafmis.syscollection.common.daoDW.AutoInfoPickDAODW;
import org.xpup.hafmis.syscollection.common.domain.entity.AutoInfoPick;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonHead;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentHead;
import org.xpup.hafmis.syscollection.common.domain.entity.MonthPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPaymentBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.paymng.monthpay.bsinterface.IMonthpayBS;
import org.xpup.hafmis.syscollection.paymng.monthpay.dto.MonthpayMaintainDto;
import org.xpup.hafmis.syscollection.paymng.monthpay.dto.MonthpayTbWindowDto;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.AddpayInfoDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayMonthDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.dto.OrgpaymentstatisticsDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoContentDTO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public class MonthpayBS implements IMonthpayBS{
  private OrgDAO orgDAO = null;
  private EmpDAO empDAO = null;
  private MonthPaymentDAO  monthPaymentDAO = null;
  private MonthPaymentHeadDAO monthPaymentHeadDAO = null;
  private MonthPaymentTailDAO monthPaymentTailDAO = null;
  private ChgPersonHeadDAO chgPersonHeadDAO = null;
  private ChgPersonTailDAO chgPersonTailDAO = null;
  private ChgPaymentHeadDAO chgPaymentHeadDAO = null;
  private ChgPaymentTailDAO chgPaymentTailDAO = null;
  private ChgPaymentPaymentDAO chgPaymentPaymentDAO = null;
  private ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO = null;
  private ChgOrgRateDAO chgOrgRateDAO = null;
  private MonthPaymentBizActivityLogDAO monthPaymentBizActivityLogDAO = null;
  private HafOperateLogDAO hafOperateLogDAO = null;
  private CollBankDAO collBankDAO = null;
  private OrganizationUnitDAO organizationUnitDAO=null;
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO=null;
  private SearchLackInfoDAO searchLackInfoDAO=null;
  private OrgExcessPaymentDAO orgExcessPaymentDAO=null;
  private OrgExcessPaymentBizActivityLogDAO orgExcessPaymentBizActivityLogDAO=null;
  
  private AutoInfoPickDAODW autoInfoPickDAODW = null;

  public void setAutoInfoPickDAODW(AutoInfoPickDAODW autoInfoPickDAODW) {
    this.autoInfoPickDAODW = autoInfoPickDAODW;
  }
  
  public void setOrgExcessPaymentDAO(OrgExcessPaymentDAO orgExcessPaymentDAO) {
    this.orgExcessPaymentDAO = orgExcessPaymentDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }
  public void setChgPaymentSalaryBaseDAO(
      ChgPaymentSalaryBaseDAO chgPaymentSalaryBaseDAO) {
    this.chgPaymentSalaryBaseDAO = chgPaymentSalaryBaseDAO;
  }
  public void setChgPaymentPaymentDAO(ChgPaymentPaymentDAO chgPaymentPaymentDAO) {
    this.chgPaymentPaymentDAO = chgPaymentPaymentDAO;
  }
  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }
  public void setMonthPaymentBizActivityLogDAO(
      MonthPaymentBizActivityLogDAO monthPaymentBizActivityLogDAO) {
    this.monthPaymentBizActivityLogDAO = monthPaymentBizActivityLogDAO;
  }
  public void setChgPersonHeadDAO(ChgPersonHeadDAO chgPersonHeadDAO) {
    this.chgPersonHeadDAO = chgPersonHeadDAO;
  }
  public void setChgPaymentHeadDAO(ChgPaymentHeadDAO chgPaymentHeadDAO) {
    this.chgPaymentHeadDAO = chgPaymentHeadDAO;
  }
  public void setMonthPaymentTailDAO(MonthPaymentTailDAO monthPaymentTailDAO) {
    this.monthPaymentTailDAO = monthPaymentTailDAO;
  }
  public void setMonthPaymentDAO(MonthPaymentDAO monthPaymentDAO) {
    this.monthPaymentDAO = monthPaymentDAO;
  }
  public void setChgOrgRateDAO(ChgOrgRateDAO chgOrgRateDAO) {
    this.chgOrgRateDAO = chgOrgRateDAO;
  }
  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }
  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }
  public void setChgPersonTailDAO(ChgPersonTailDAO chgPersonTailDAO) {
    this.chgPersonTailDAO = chgPersonTailDAO;
  }
  public void setMonthPaymentHeadDAO(MonthPaymentHeadDAO monthPaymentHeadDAO) {
    this.monthPaymentHeadDAO = monthPaymentHeadDAO;
  }
  public void setChgPaymentTailDAO(ChgPaymentTailDAO chgPaymentTailDAO) {
    this.chgPaymentTailDAO = chgPaymentTailDAO;
  }
  // 查询银行
  public String findCollBank(String collBankid) {
    String bankname = "";
    CollBank collBank = collBankDAO.getCollBankByCollBankid(collBankid);
    bankname = collBank.getCollBankName();
    return bankname;
  }
  public String getSeq_aa300() {
    return collBankDAO.get_Seq_aa300();
  }

  //根据单位ID查询单位信息
  public Org findOrgInfo(Serializable id,String status,SecurityInfo securityInfo) throws BusinessException {
    // TODO Auto-generated method stub
    Org org = null;
    String orgid="";
    if(id!=null){
      orgid=id.toString();
    }
    org = orgDAO.queryByCriterions(orgid,status,null,securityInfo);
    return org;
  }
  /**
   * 根据单位ID得到应缴总额
   * 李娟
   */
  public BigDecimal getSumPay(Serializable orgid){
    BigDecimal sumPay=null;
    BigDecimal orgPay = empDAO.queryOrgpay(orgid);
    BigDecimal empPay = empDAO.queryEmppay(orgid);
    sumPay=orgPay.add(empPay);
    return sumPay;
  }
  /**
   * 查询单位的最大缴至年月
   * @param orgid
   * @return
   */
  public String getOrgPayMonth(Serializable orgid){
    String orgPayMonth = "";
    List list = orgDAO.queryOrgaddPaymonth(orgid);
    if(list != null && list.size()>0){
      OrgaddpayMonthDTO dto=(OrgaddpayMonthDTO)list.get(0);
      String orgpayMonth = dto.getOrgPaymonth();
      String emppayMonth = dto.getEmpPaymonth();
      if(Integer.parseInt(orgpayMonth)>=Integer.parseInt(emppayMonth)){
        orgPayMonth = BusiTools.addMonth(orgpayMonth,1);
      }else{
        orgPayMonth = BusiTools.addMonth(emppayMonth,1);
      }
    }
    return orgPayMonth;
  }
  /**
   * 李娟
   * 查询单位汇缴信息初始
   */
  public MonthpayJYAF findMonthpayInfo(Serializable orgid,SecurityInfo securityInfo) throws BusinessException,Exception{
    MonthpayJYAF f = new MonthpayJYAF();
    String [] chgTypeAdd={"1","3"};
    String [] chgTypeLess={"4"};
    String [] payStatus={"1","3","4"};
    String [] payStatus_chg={"3","4"};
    Org org = null;
    //输入单位编号时判断登录日期与单位所在的归集银行的归集银行表的日结日期是否一致，不一致不允许做业务
    String bizDate = securityInfo.getUserInfo().getBizDate();//业务日期
    String bankDate = "";
    //系统自动生成结算号：业务日期+流水号
    String noteNum = bizDate+orgDAO.queryNoteNum();
    org = findOrgInfo(orgid,"2",securityInfo);
    if(org == null && orgid !=null){
      throw new BusinessException("没有此单位信息！");
    }
    bankDate = orgDAO.findAA103_DayTime(org.getOrgInfo().getCollectionBankId());
    if(!bizDate.equals(bankDate)){
      throw new BusinessException("登录日期与银行业务日期不一致，此单位不能做业务！");
    }
    List emplist=empDAO.queryEmpList(new Integer(orgid.toString()), payStatus_chg);
    if(!emplist.isEmpty()){
      f.setPayStatus_chg("1");
    }
    //判断是否存在未启用的人员变更
    if(!chgPersonHeadDAO.getChgStatus(new Integer(orgid.toString()))){
      throw new BusinessException("存在未启用的人员变更！");
    }
    //判断是否存在未启用的汇缴比例调整
    if(!chgOrgRateDAO.getChgStatus(new Integer(orgid.toString()))){
      throw new BusinessException("存在未启用的汇缴比例调整！");
    }
    //判断是否存在未启用的缴额调整
    if(!chgPaymentPaymentDAO.getChgStatus(new Integer(orgid.toString()))){
      throw new BusinessException("存在未启用的缴额调整！");
    }
    //判断是否存在未启用的工资基数调整
    if(!chgPaymentSalaryBaseDAO.getChgStatus(new Integer(orgid.toString()))){
      throw new BusinessException("存在未启用的工资基数调整！");
    }
    //应缴总额
    BigDecimal sumPay = this.getSumPay(orgid);
    f.setOrg(org);
    f.setOrgid(org.getId().toString());
    f.setName(org.getOrgInfo().getName());
    if(org.getOrgInfo().getPayBank().getName()!=null)
    {
    f.setPayment_bank_name(org.getOrgInfo().getPayBank().getName());  // 增加付款单位银行名称
    }
    if(org.getOrgInfo().getPayBank().getAccountNum()!=null)
    {
    f.setPayment_bank_acc(org.getOrgInfo().getPayBank().getAccountNum());// 增加付款单位银行账号
    }
    if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
      f.setPayment_orgname(org.getOrgInfo().getReserveaB());
    }else{
      f.setPayment_orgname(org.getOrgInfo().getName());
    }
    //缴至年月
    String inceptMonth = monthPaymentHeadDAO.queryMaxMonth(orgid);
    String temp=inceptMonth;
    if(inceptMonth==null){
      //inceptMonth = org.getFirstPayMonth();
      inceptMonth = this.getOrgPayMonth(orgid);
    }else{
      //缴至年月+1
      inceptMonth = BusiTools.addMonth(inceptMonth,1);
    }
    //AA203职工增加金额（正为增加，负为减少）
    BigDecimal empPaymentMoney = chgPaymentTailDAO.queryEmpPayMoney(orgid,null, payStatus);
    //AA203单位增加金额（正为增加，负为减少）
    BigDecimal orgPaymentMoney = chgPaymentTailDAO.queryOrgPayMoney(orgid, null, payStatus);
    //单位+职工
    BigDecimal paymentMoney = new BigDecimal(0.00);
     
    //AA201职工增加金额（正为增加，负为减少）
    BigDecimal empRateMoney = chgOrgRateDAO.queryEmpRateMoney(orgid,null);
    //AA201单位增加金额（正为增加，负为减少）
    BigDecimal orgRateMoney = chgOrgRateDAO.queryOrgRateMoney(orgid,null);
    //单位+职工
    BigDecimal rateMoney = new BigDecimal(0.00); //empRateMoney.add(orgRateMoney);
    //单位应缴总额
    BigDecimal orgPay = empDAO.queryOrgpay(orgid);
    //职工应缴总额
    BigDecimal empPay = empDAO.queryEmppay(orgid);
    //汇缴年月
    String payMonth = inceptMonth;
    //本月人数
    Integer personCounts = empDAO.queryEmpCount(orgid);
    //增加人数
    Integer personCountsAdd = chgPersonTailDAO.queryPersonCount(orgid, null, chgTypeAdd);
    //减少人数
    Integer personCountsLess = chgPersonTailDAO.queryPersonCount(orgid, null, chgTypeLess);
    //上月人数
    Integer ultimoPersonCounts = new Integer(personCounts.intValue()+personCountsLess.intValue()-personCountsAdd.intValue());
    //本月金额
    BigDecimal payMoney = this.getSumPay(orgid);
    //增加金额AA205状态为13
    BigDecimal empPayMoneyAdd = chgPersonTailDAO.queryEmpPersonMoney(orgid,null, chgTypeAdd);
    BigDecimal orgPayMoneyAdd = chgPersonTailDAO.queryOrgPersonMoney(orgid,null, chgTypeAdd);
    //单位+职工增加
    BigDecimal payMoneyAdd = empPayMoneyAdd.add(orgPayMoneyAdd);
    //减少金额AA205状态为4
    BigDecimal empPayMoneyLess = chgPersonTailDAO.queryEmpPersonMoney(orgid,null, chgTypeLess);
    BigDecimal orgPayMoneyLess = chgPersonTailDAO.queryOrgPersonMoney(orgid,null, chgTypeLess);
//    单位+职工减少
//    BigDecimal payMoneyLess = empPayMoneyLess.add(orgPayMoneyLess);
//    if(empPaymentMoney.doubleValue()>0){
//      payMoneyAdd = payMoneyAdd.add(empPaymentMoney);
//    }else if(orgPaymentMoney.doubleValue()<0){
//        orgPaymentMoney = orgPaymentMoney.multiply(new BigDecimal(-1));
//        payMoneyLess = payMoneyLess.add(orgPaymentMoney);
//      }
//    if(orgPaymentMoney.doubleValue()>0){
//      payMoneyAdd = payMoneyAdd.add(orgPaymentMoney);
//    }else if(empPaymentMoney.doubleValue()<0){
//      empPaymentMoney = empPaymentMoney.multiply(new BigDecimal(-1));
//      payMoneyLess = payMoneyLess.add(empPaymentMoney);
//    }
//    if(empRateMoney.doubleValue()>0){
//      payMoneyAdd = payMoneyAdd.add(empRateMoney);
//    }else  if(empRateMoney.doubleValue()<0){
//        empRateMoney = empRateMoney.multiply(new BigDecimal(-1));
//        payMoneyLess = payMoneyLess.add(empRateMoney);
//      }
//    if(orgRateMoney.doubleValue()>0){
//      payMoneyAdd = payMoneyAdd.add(orgRateMoney);
//    }else if(orgRateMoney.doubleValue()<0){
//        orgRateMoney = orgRateMoney.multiply(new BigDecimal(-1));
//        payMoneyLess = payMoneyLess.add(orgRateMoney);
//      }
//  单位+职工减少
    BigDecimal payMoneyLess = empPayMoneyLess.add(orgPayMoneyLess);
    if(empPaymentMoney.doubleValue()>0){
      payMoneyAdd = payMoneyAdd.add(empPaymentMoney);
    }if(orgPaymentMoney.doubleValue()<0){
      BigDecimal orgPaymentMoney1 = orgPaymentMoney.multiply(new BigDecimal(-1));
        payMoneyLess = payMoneyLess.add(orgPaymentMoney1);
      }
    if(orgPaymentMoney.doubleValue()>0){
      payMoneyAdd = payMoneyAdd.add(orgPaymentMoney);
    }if(empPaymentMoney.doubleValue()<0){
      BigDecimal empPaymentMoney1 = empPaymentMoney.multiply(new BigDecimal(-1));
      payMoneyLess = payMoneyLess.add(empPaymentMoney1);
    }
    if(empRateMoney.doubleValue()>0){
      payMoneyAdd = payMoneyAdd.add(empRateMoney);
    } if(empRateMoney.doubleValue()<0){
      BigDecimal empRateMoney1 = empRateMoney.multiply(new BigDecimal(-1));
        payMoneyLess = payMoneyLess.add(empRateMoney1);
      }
    if(orgRateMoney.doubleValue()>0){
      payMoneyAdd = payMoneyAdd.add(orgRateMoney);
    }if(orgRateMoney.doubleValue()<0){
      BigDecimal orgRateMoney1 = orgRateMoney.multiply(new BigDecimal(-1));
        payMoneyLess = payMoneyLess.add(orgRateMoney1);
      }
    //上月金额
    BigDecimal ultimoPayMoney = payMoney.add(payMoneyLess).subtract(payMoneyAdd);
    if(temp == null){
      ultimoPayMoney = new BigDecimal(0.00);
      ultimoPersonCounts = new Integer(0);  
    }
    f.setEmpPay(empPay);
    f.setOrgPay(orgPay);
    //本月金额
    f.setPayMoney(payMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
    f.setPayMoneyAdd(payMoneyAdd.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
    f.setPayMoneyLess(payMoneyLess.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
    f.setInceptMonth(inceptMonth);
    f.setPayMonth(payMonth);
    f.setPersonCounts(personCounts);
    f.setPersonCountsAdd(personCountsAdd);
    f.setPersonCountsLess(personCountsLess);
    f.setSumPay(sumPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
    f.setUltimoPayMoney(ultimoPayMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
    f.setUltimoPersonCounts(ultimoPersonCounts);
    f.setUntilMonth(payMonth);
    f.setNoteNum(noteNum);
    List banklist=new ArrayList();
    banklist=this.queryCollBankInfo(org.getOrgInfo().getOfficecode(), org.getOrgInfo().getCollectionBankId());
    if(banklist.size()>0)
    {
      for(int i=0;i<banklist.size();i++)
      {
        Object obj[]=null;
        obj=(Object[])banklist.get(0);
        if(obj[0]!=null)
          f.setReceivables_orgname(obj[0].toString());
        if(obj[1]!=null)
          f.setReceivables_bank_name(obj[1].toString());
        if(obj[2]!=null)
          f.setReceivables_bank_acc(obj[2].toString());
      }
    }
    
    
    
    //新加的代码，用于获取单位欠缴信息
//    BigDecimal lackMoney=new BigDecimal(0.00);
//    List list=searchLackInfoDAO.queryOrgLackMoney(orgid.toString(), securityInfo.getUserInfo().getBizDate().substring(0, 6));
//    for(int i=0;i<list.size();i++){
//      OrgpaymentstatisticsDTO dto=(OrgpaymentstatisticsDTO)list.get(i);
//      lackMoney=lackMoney.add(dto.getEmpPay().add(dto.getOrgPay()));
//    }
//    f.setOrgLackMoney(lackMoney.toString());
    /**
     * 单位缴至年月
     */
    String orgPayMonth = "";
    orgPayMonth = this.getOrgPayMonth(orgid, "");
    if(orgPayMonth == null || "".equals(orgPayMonth)){
      orgPayMonth = this.getOrgPaymentMonth(orgid.toString(), "org"); 
    }
    f.setOrgPayMonth(orgPayMonth);
    /**
     * 职工缴至年月
     */
    String empPayMonth = "";
    empPayMonth = this.getEmpPayMonth(orgid);
    if(empPayMonth == null || "".equals(empPayMonth)){
      empPayMonth = this.getOrgPaymentMonth(orgid.toString(), "");
    }
    f.setEmpPayMonth(empPayMonth);
    /**
     * 单位欠缴月数
     */
    String lackMonths = "";
    if(new Integer(orgPayMonth).intValue()>new Integer(empPayMonth).intValue()){
      lackMonths = orgPayMonth;
    }else{
      lackMonths = empPayMonth;
    }
    int month = BusiTools.getDisMonths(bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+"01", 
        lackMonths.substring(0,4)+"-"+lackMonths.substring(4,6)+"-"+"01");
    f.setLackMonths(String.valueOf(month));
    /**
     * 单位欠缴金额
     */
    BigDecimal orgLackMoney = new BigDecimal(0.00);
    orgLackMoney = orgPay.add(empPay).multiply(new BigDecimal(month)).divide(new BigDecimal(1),2, BigDecimal.ROUND_HALF_UP);
    f.setOrgLackMoney(orgLackMoney.toString());
    return f;
  }
  
  public String getOrgPayMonth(Serializable orgid,String status){
    String orgPayMonth = "";
    orgPayMonth = monthPaymentHeadDAO.queryOrgPayMonth(orgid);
    return orgPayMonth;
  }
  public String getEmpPayMonth(Serializable orgid){
    String empPayMonth = "";
    empPayMonth = monthPaymentHeadDAO.queryEmpPayMonth(orgid);
    return empPayMonth;
  }
  public List findSearchLackInfoAllByCriterions(String orgId,SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    List list=searchLackInfoDAO.findSearchLackInfoListByCriterions_WL(null,null,null,null,orgId,null,null,null,null,null,null,0,0,0,securityInfo);
    return list;
  }
  /**
   * 获取单位职工的缴至年月
   * @return
   */
  
  public String getOrgPaymentMonth(String orgId,String status)throws Exception{
    String orgPaymentMonth="";
    try{
     orgPaymentMonth=monthPaymentDAO.getOrgPayMonth(orgId, status);
    }catch(Exception e){
      e.printStackTrace();
    }
 
    return orgPaymentMonth;
  }
  /**
   * 李娟
   * 查询单位汇缴信息
   * 核定
   * 改变缴存方式或汇缴年月
   */
  public MonthpayJYAF findMonthpayInfoCheck(MonthpayJYAF monthpayJYAF,SecurityInfo securityInfo) throws BusinessException,Exception{
    MonthpayJYAF f = new MonthpayJYAF();
    String [] chgTypeAdd={"1","3"};
    String [] chgTypeLess={"4"};
    String [] payStatus={"1","3","4"};
    String paymentid = null;
    String orgid = monthpayJYAF.getOrgid();
    //应缴总额
    BigDecimal sumPay = new BigDecimal(0);
    Org org = null;
    org = findOrgInfo(orgid,"2",securityInfo);
    if(org == null && orgid !=null){
      throw new BusinessException("没有此单位信息！");
    }
    String bizDate = securityInfo.getUserInfo().getBizDate();
    // 得到挂账金额
    f.setOverPay(monthpayJYAF.getOverPay());
    f.setOrg(org);
    f.setOrgid(org.getId().toString());
    f.setName(org.getOrgInfo().getName());
    
    List banklist=new ArrayList();
    banklist=this.queryCollBankInfo(org.getOrgInfo().getOfficecode(), org.getOrgInfo().getCollectionBankId());
    if(banklist.size()>0)
    {
      for(int i=0;i<banklist.size();i++)
      {
        Object obj[]=null;
        obj=(Object[])banklist.get(0);
        if(obj[0] != null){
          f.setReceivables_orgname(obj[0].toString());
        }
        if(obj[1] != null){
          f.setReceivables_bank_name(obj[1].toString());
        }
        if(obj[2] != null){
          f.setReceivables_bank_acc(obj[2].toString());
        }
      }
    }
    
    f.setPayment_bank_name(org.getOrgInfo().getPayBank().getName());
    f.setPayment_bank_acc(org.getOrgInfo().getPayBank().getAccountNum());
    if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
      f.setPayment_orgname(org.getOrgInfo().getReserveaB());
    }else{
      f.setPayment_orgname(org.getOrgInfo().getName());
    }
//  新加的代码，用于获取单位欠缴信息
    BigDecimal lackMoney=new BigDecimal(0.00);
    List lists=searchLackInfoDAO.queryOrgLackMoney(orgid.toString(), securityInfo.getUserInfo().getBizDate().substring(0, 6));
    for(int i=0;i<lists.size();i++){
      OrgpaymentstatisticsDTO dto=(OrgpaymentstatisticsDTO)lists.get(i);
      lackMoney=lackMoney.add(dto.getEmpPay().add(dto.getOrgPay()));
    }
    f.setOrgLackMoney(lackMoney.toString());
//    List lackList=this.findSearchLackInfoAllByCriterions(orgid,securityInfo);
//    if(lackList.size()>0){
//      SearchLackInfoContentDTO searchLackInfoContentDTO=(SearchLackInfoContentDTO)lackList.get(0);
//      if (searchLackInfoContentDTO!=null){
//        f.setLackMonths(searchLackInfoContentDTO.getLackMonths());
//      }   
//    }
    String orgPaymentMonth="";
    String empPaymentMonth="";
    orgPaymentMonth=this.getOrgPaymentMonth(orgid, "org"); 
    f.setOrgPayMonth(orgPaymentMonth);
    empPaymentMonth=this.getOrgPaymentMonth(orgid, "");
    f.setEmpPayMonth(empPaymentMonth);
    //新加的代码结束
    //缴至年月
    String inceptMonth = monthPaymentHeadDAO.queryMaxMonth(orgid);
    String temp=inceptMonth;
    if(inceptMonth==null){
      //inceptMonth = org.getFirstPayMonth();
      inceptMonth = this.getOrgPayMonth(orgid);
    }else{
      //缴至年月+1
      inceptMonth = BusiTools.addMonth(inceptMonth,1);
    }
    //单位应缴总额
    BigDecimal orgPay = empDAO.queryOrgpay(orgid);
    //职工应缴总额
    BigDecimal empPay = empDAO.queryEmppay(orgid);
    String str1=monthpayJYAF.getInceptMonth().substring(0,4);
    String str2=monthpayJYAF.getInceptMonth().substring(4,monthpayJYAF.getInceptMonth().length());
    String str3 = str1+"-"+str2;
    int month=BusiTools.minusDate(str3+"-01", inceptMonth.substring(0,4)+"-"+inceptMonth.substring(4,inceptMonth.length())+"-01");
    //缴存月数
    int monthCounts=BusiTools.monthInterval(monthpayJYAF.getInceptMonth(), monthpayJYAF.getPayMonth())+1;
    //往后缴
    if(month>=0){
      //AA203职工增加金额（正为增加，负为减少）
      BigDecimal empPaymentMoney = chgPaymentTailDAO.queryEmpPayMoney(orgid,null, payStatus);
      //AA203单位增加金额（正为增加，负为减少）
      BigDecimal orgPaymentMoney = chgPaymentTailDAO.queryOrgPayMoney(orgid, null, payStatus);
      BigDecimal paymentMoney = new BigDecimal(0.00); //empPaymentMoney.add(orgPaymentMoney);
      //AA201职工增加金额（正为增加，负为减少）
      BigDecimal empRateMoney = chgOrgRateDAO.queryEmpRateMoney(orgid,null);
      //AA201单位增加金额（正为增加，负为减少）
      BigDecimal orgRateMoney = chgOrgRateDAO.queryOrgRateMoney(orgid,null);
      BigDecimal rateMoney = new BigDecimal(0.00); //empRateMoney.add(orgRateMoney);
      //汇缴年月
      String payMonth = inceptMonth;
      //本月人数
      Integer personCounts = empDAO.queryEmpCount(orgid);
      //增加人数
      Integer personCountsAdd = chgPersonTailDAO.queryPersonCount(orgid, null, chgTypeAdd);
      //减少人数
      Integer personCountsLess = chgPersonTailDAO.queryPersonCount(orgid, null, chgTypeLess);
      //上月人数
      Integer ultimoPersonCounts = new Integer(personCounts.intValue()+personCountsLess.intValue()-personCountsAdd.intValue());
      //本月金额
      BigDecimal payMoney = this.getSumPay(orgid);
      //增加金额AA205状态为13
      BigDecimal empPayMoneyAdd = chgPersonTailDAO.queryEmpPersonMoney(orgid,null, chgTypeAdd);
      BigDecimal orgPayMoneyAdd = chgPersonTailDAO.queryOrgPersonMoney(orgid,null, chgTypeAdd);
      BigDecimal payMoneyAdd = empPayMoneyAdd.add(orgPayMoneyAdd);
      //减少金额AA205状态为4
      BigDecimal empPayMoneyLess = chgPersonTailDAO.queryEmpPersonMoney(orgid,null, chgTypeLess);
      BigDecimal orgPayMoneyLess = chgPersonTailDAO.queryOrgPersonMoney(orgid,null, chgTypeLess);
      BigDecimal payMoneyLess = empPayMoneyLess.add(orgPayMoneyLess);
     //上月金额
      BigDecimal ultimoPayMoney = new BigDecimal(0.00);
      if(monthpayJYAF.getPayStatus()== 1){
        //应缴总额
        sumPay = this.getSumPay(orgid);

//        if(empPaymentMoney.doubleValue()>0){
//          payMoneyAdd = payMoneyAdd.add(empPaymentMoney);
//        }else if(orgPaymentMoney.doubleValue()<0){
//            orgPaymentMoney = orgPaymentMoney.multiply(new BigDecimal(-1));
//            payMoneyLess = payMoneyLess.add(orgPaymentMoney);
//          }
//        if(orgPaymentMoney.doubleValue()>0){
//          payMoneyAdd = payMoneyAdd.add(orgPaymentMoney);
//        }else if(empPaymentMoney.doubleValue()<0){
//          empPaymentMoney = empPaymentMoney.multiply(new BigDecimal(-1));
//          payMoneyLess = payMoneyLess.add(empPaymentMoney);
//        }
//        if(empRateMoney.doubleValue()>0){
//          payMoneyAdd = payMoneyAdd.add(empRateMoney);
//        }else  if(empRateMoney.doubleValue()<0){
//            empRateMoney = empRateMoney.multiply(new BigDecimal(-1));
//            payMoneyLess = payMoneyLess.add(empRateMoney);
//          }
//        if(orgRateMoney.doubleValue()>0){
//          payMoneyAdd = payMoneyAdd.add(orgRateMoney);
//        }else if(orgRateMoney.doubleValue()<0){
//            orgRateMoney = orgRateMoney.multiply(new BigDecimal(-1));
//            payMoneyLess = payMoneyLess.add(orgRateMoney);
//          }
        if(empPaymentMoney.doubleValue()>0){
          payMoneyAdd = payMoneyAdd.add(empPaymentMoney);
        } if(orgPaymentMoney.doubleValue()<0){
            BigDecimal orgPaymentMoney1 = orgPaymentMoney.multiply(new BigDecimal(-1));
            payMoneyLess = payMoneyLess.add(orgPaymentMoney1);
          }
        if(orgPaymentMoney.doubleValue()>0){
          payMoneyAdd = payMoneyAdd.add(orgPaymentMoney);
        } if(empPaymentMoney.doubleValue()<0){
          BigDecimal empPaymentMoney1 = empPaymentMoney.multiply(new BigDecimal(-1));
          payMoneyLess = payMoneyLess.add(empPaymentMoney1);
        }
        if(empRateMoney.doubleValue()>0){
          payMoneyAdd = payMoneyAdd.add(empRateMoney);
        }  if(empRateMoney.doubleValue()<0){
            empRateMoney = empRateMoney.multiply(new BigDecimal(-1));
            payMoneyLess = payMoneyLess.add(empRateMoney);
          }
        if(orgRateMoney.doubleValue()>0){
          payMoneyAdd = payMoneyAdd.add(orgRateMoney);
        } if(orgRateMoney.doubleValue()<0){
            orgRateMoney = orgRateMoney.multiply(new BigDecimal(-1));
            payMoneyLess = payMoneyLess.add(orgRateMoney);
          }
        ultimoPayMoney= payMoney.add(payMoneyLess).subtract(payMoneyAdd);
      }else if(monthpayJYAF.getPayStatus()==3){
        payMoneyLess = empPayMoneyLess;
        sumPay = empDAO.queryEmppay(orgid);
        payMoney = empPay;   
        if(empPaymentMoney.doubleValue()>=0){
          payMoneyAdd = empPayMoneyAdd.add(empPaymentMoney);
        }else{
          empPaymentMoney = empPaymentMoney.multiply(new BigDecimal(-1));
          payMoneyLess = payMoneyLess.add(empPaymentMoney);
        }
        if(empRateMoney.doubleValue()>=0){
          payMoneyAdd = payMoneyAdd.add(empRateMoney);
        }else{
          empRateMoney = empRateMoney.multiply(new BigDecimal(-1));
          payMoneyLess = payMoneyLess.add(empRateMoney);
        }
        ultimoPayMoney = payMoney.add(payMoneyLess).subtract(payMoneyAdd);
      }else if(monthpayJYAF.getPayStatus()==2){
        sumPay = empDAO.queryOrgpay(orgid);
        payMoney = orgPay;
        payMoneyLess = orgPayMoneyLess;
        if(orgPaymentMoney.doubleValue()>=0){
          payMoneyAdd = orgPayMoneyAdd.add(orgPaymentMoney);
        }else{
          orgPaymentMoney = orgPaymentMoney.multiply(new BigDecimal(-1));
          payMoneyLess = payMoneyLess.add(orgPaymentMoney);
        }
        if(orgRateMoney.doubleValue()>=0){
          payMoneyAdd = payMoneyAdd.add(orgRateMoney);
        }else{
          orgRateMoney = orgRateMoney.multiply(new BigDecimal(-1));
          payMoneyLess = payMoneyLess.add(orgRateMoney);
        }
        ultimoPayMoney = payMoney.add(payMoneyLess).subtract(payMoneyAdd);
      }
      sumPay = sumPay.multiply(new BigDecimal(monthCounts));

      if(temp == null){
        ultimoPayMoney = new BigDecimal(0.00);
        ultimoPersonCounts = new Integer(0);
      }
      f.setEmpPay(empPay);
      f.setOrgPay(orgPay);
      //系统自动生成结算号：业务日期+流水号
      String noteNum = bizDate+orgDAO.queryNoteNum();
      //本月金额
      f.setPayMoney(payMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
      f.setPayMoneyAdd(payMoneyAdd.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
      f.setPayMoneyLess(payMoneyLess.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
      f.setInceptMonth(monthpayJYAF.getInceptMonth());
      f.setUntilMonth(payMonth);
      f.setPersonCounts(personCounts);
      f.setPersonCountsAdd(personCountsAdd);
      f.setPersonCountsLess(personCountsLess);
      f.setSumPay(sumPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
      f.setUltimoPayMoney(ultimoPayMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
      f.setUltimoPersonCounts(ultimoPersonCounts);
      f.setPayStatus(monthpayJYAF.getPayStatus());
      f.setPayMonth(monthpayJYAF.getPayMonth());
      f.setOrgid(orgid);
      f.setName(monthpayJYAF.getName());
      f.setNoteNum(monthpayJYAF.getNoteNum());
      if(f.getNoteNum().equals("")){
        f.setNoteNum(noteNum);
      }
    }
    //往前缴
     if(month<0){
        List list1=monthPaymentHeadDAO.queryMonthPaymentHeadAccount_yg(orgid, monthpayJYAF.getInceptMonth());
        if(list1 != null && list1.size()>0){
          if(list1.size()>=2){
            throw new BusinessException("本月不能做部份汇缴！");
          }else if(list1.size()==1){
            MonthPaymentHead monthPaymentHead1=(MonthPaymentHead)list1.get(0); 
            MonthPayment monthPayment=monthPaymentDAO.queryById(new Integer(monthPaymentHead1.getPaymentHead().getId().toString()));
            if(monthPayment.getPayModel().equals("1")){
              throw new BusinessException("本月已经均缴过！");
            }else{
              MonthPaymentHead monthPaymentHead=(MonthPaymentHead)list1.get(0);
              paymentid=monthPaymentHead.getPaymentHead().getId().toString();
              //AA303职工实缴
              BigDecimal empRealPay = monthPaymentTailDAO.queryEmpRealPay_yg(orgid, monthPaymentHead.getPayMonth());
              //单位实缴
              BigDecimal orgRealPay = monthPaymentTailDAO.queryOrgRealPay_yg(orgid, monthPaymentHead.getPayMonth());
              //AA303职工应缴
              BigDecimal empShouldPay = monthPaymentTailDAO.queryEmpShouldPayLJ_yg(orgid, monthPaymentHead.getPayMonth());
              //单位应缴
              BigDecimal orgShouldPay = monthPaymentTailDAO.queryOrgShouldPayLJ_yg(orgid, monthPaymentHead.getPayMonth());
              if(empRealPay.doubleValue()!=0 && orgRealPay.doubleValue()!=0){
                throw new BusinessException("本月不可以进行部份汇缴！已经均缴过!");
              }
                //AA203职工增加金额（正为增加，负为减少）
                BigDecimal empPaymentMoney = chgPaymentTailDAO.queryEmpPayMoney(orgid,paymentid, payStatus);
                //AA203单位增加金额（正为增加，负为减少）
                BigDecimal orgPaymentMoney = chgPaymentTailDAO.queryOrgPayMoney(orgid, paymentid, payStatus);
                //AA201职工增加金额（正为增加，负为减少）
                BigDecimal empRateMoney = chgOrgRateDAO.queryEmpRateMoney(orgid,paymentid);
                //AA201单位增加金额（正为增加，负为减少）
                BigDecimal orgRateMoney = chgOrgRateDAO.queryOrgRateMoney(orgid,paymentid);
                //本月人数303中的人数
                Integer personCounts = monthPaymentTailDAO.queryPaymentPersonCountsLJ(monthPaymentHead.getId());
                //增加人数
                Integer personCountsAdd = chgPersonTailDAO.queryPersonCount(orgid, paymentid, chgTypeAdd);
                //减少人数
                Integer personCountsLess = chgPersonTailDAO.queryPersonCount(orgid, paymentid, chgTypeLess);
                //上月人数
                Integer ultimoPersonCounts = new Integer(personCounts.intValue()+personCountsLess.intValue()-personCountsAdd.intValue());
                //本月金额
                BigDecimal payMoney = this.getSumPay(orgid);
                //增加金额AA205状态为13
                BigDecimal empPayMoneyAdd = chgPersonTailDAO.queryEmpPersonMoney(orgid,paymentid, chgTypeAdd);
                BigDecimal orgPayMoneyAdd = chgPersonTailDAO.queryOrgPersonMoney(orgid,paymentid, chgTypeAdd);
                BigDecimal payMoneyAdd = empPayMoneyAdd.add(orgPayMoneyAdd);
                //减少金额AA205状态为4
                BigDecimal empPayMoneyLess = chgPersonTailDAO.queryEmpPersonMoney(orgid,paymentid, chgTypeLess);
                BigDecimal orgPayMoneyLess = chgPersonTailDAO.queryOrgPersonMoney(orgid,paymentid, chgTypeLess);
                BigDecimal payMoneyLess = empPayMoneyLess.add(orgPayMoneyLess);
                //上月金额
                BigDecimal ultimoPayMoney = payMoney.add(payMoneyLess).subtract(payMoneyAdd);
                if(empRealPay.doubleValue()!=0){
                  sumPay = orgShouldPay;
                  if(orgPaymentMoney.doubleValue()>=0){
                    orgPayMoneyAdd = orgPayMoneyAdd.add(orgPaymentMoney);
                  }else{
                    orgPaymentMoney = orgPaymentMoney.multiply(new BigDecimal(-1));
                    orgPayMoneyLess = orgPayMoneyLess.add(orgPaymentMoney);
                  }
                  if(orgRateMoney.doubleValue()>=0){
                    orgPayMoneyAdd = orgPayMoneyAdd.add(orgRateMoney);
                  }else{
                    orgRateMoney = orgRateMoney.multiply(new BigDecimal(-1));
                    orgPayMoneyLess = orgPayMoneyLess.add(orgRateMoney);
                  }
                  ultimoPayMoney = orgShouldPay.add(orgPayMoneyLess).subtract(orgPayMoneyAdd);
                  //本月金额
                  f.setPayMoney(orgShouldPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                  f.setPayMoneyAdd(orgPayMoneyAdd.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                  f.setPayMoneyLess(orgPayMoneyLess.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                  f.setInceptMonth(monthpayJYAF.getInceptMonth());
                  f.setPayMonth(monthpayJYAF.getInceptMonth());
                  f.setPersonCounts(personCounts);
                  f.setUltimoPayMoney(ultimoPayMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                  f.setPayStatus(2);
                  f.setUntilMonth(inceptMonth);
                }else if(orgRealPay.doubleValue()!=0){
                  sumPay = empShouldPay;
                  if(empPaymentMoney.doubleValue()>=0){
                    empPayMoneyAdd = empPayMoneyAdd.add(empPaymentMoney);
                  }else{
                    empPaymentMoney = empPaymentMoney.multiply(new BigDecimal(-1));
                    empPayMoneyLess = empPayMoneyLess.add(empPaymentMoney);
                  }
                  if(empRateMoney.doubleValue()>=0){
                    empPayMoneyAdd = empPayMoneyAdd.add(empRateMoney);
                  }else{
                    empRateMoney = empRateMoney.multiply(new BigDecimal(-1));
                    empPayMoneyLess = empPayMoneyLess.add(empRateMoney);
                  }
                  ultimoPayMoney = empShouldPay.add(empPayMoneyLess).subtract(empPayMoneyAdd);
                  //本月金额
                  f.setPayMoney(empShouldPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                  f.setPayMoneyAdd(empPayMoneyAdd.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                  f.setPayMoneyLess(empPayMoneyLess.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                  f.setInceptMonth(monthpayJYAF.getInceptMonth());
                  f.setPayMonth(monthpayJYAF.getInceptMonth());
                  f.setPersonCounts(personCounts);
                  f.setUltimoPayMoney(ultimoPayMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                  f.setPayStatus(3);
                  f.setUntilMonth(inceptMonth);
                }
                //应缴总额
                f.setSumPay(sumPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
                f.setPersonCountsAdd(personCountsAdd);
                f.setPersonCountsLess(personCountsLess);
                f.setUltimoPersonCounts(ultimoPersonCounts);
                f.setUntilMonth(inceptMonth);
            }
          }
        }else{
          throw new BusinessException("未进行过汇缴，不能做欠缴！");
        }
        
//      List list = monthPaymentHeadDAO.queryMonthPaymentHead(orgid, monthpayJYAF.getInceptMonth());
//      if(list != null && list.size()>0){
//        throw new BusinessException("本月部份汇缴尚未入账，不可以进行汇缴！");
//      }else{
//        List list1=monthPaymentHeadDAO.queryMonthPaymentHeadAccount(orgid, monthpayJYAF.getInceptMonth());
//        if(list1 != null && list1.size()>0){
//          if(list1.size()>=2){
//            throw new BusinessException("本月不能做部份汇缴！");
//          }else if(list1.size()==1){
//            MonthPaymentHead monthPaymentHead1=(MonthPaymentHead)list1.get(0); 
//            MonthPayment monthPayment=monthPaymentDAO.queryById(new Integer(monthPaymentHead1.getPaymentHead().getId().toString()));
//            if(monthPayment.getPayModel().equals("1")){
//              throw new BusinessException("本月已经均缴过！");
//            }else{
//              MonthPaymentHead monthPaymentHead=(MonthPaymentHead)list1.get(0);
//              paymentid=monthPaymentHead.getPaymentHead().getId().toString();
//              //AA303职工实缴
//              BigDecimal empRealPay = monthPaymentTailDAO.queryEmpRealPay(orgid, monthPaymentHead.getPayMonth());
//              //单位实缴
//              BigDecimal orgRealPay = monthPaymentTailDAO.queryOrgRealPay(orgid, monthPaymentHead.getPayMonth());
//              //AA303职工应缴
//              BigDecimal empShouldPay = monthPaymentTailDAO.queryEmpShouldPayLJ(orgid, monthPaymentHead.getPayMonth());
//              //单位应缴
//              BigDecimal orgShouldPay = monthPaymentTailDAO.queryOrgShouldPayLJ(orgid, monthPaymentHead.getPayMonth());
//              if(empRealPay.doubleValue()!=0 && orgRealPay.doubleValue()!=0){
//                throw new BusinessException("本月不可以进行部份汇缴！已经均缴过!");
//              }
//              //AA203职工增加金额（正为增加，负为减少）
//              BigDecimal empPaymentMoney = chgPaymentTailDAO.queryEmpPayMoney(orgid,paymentid, payStatus);
//              //AA203单位增加金额（正为增加，负为减少）
//              BigDecimal orgPaymentMoney = chgPaymentTailDAO.queryOrgPayMoney(orgid, paymentid, payStatus);
//              //AA201职工增加金额（正为增加，负为减少）
//              BigDecimal empRateMoney = chgOrgRateDAO.queryEmpRateMoney(orgid,paymentid);
//              //AA201单位增加金额（正为增加，负为减少）
//              BigDecimal orgRateMoney = chgOrgRateDAO.queryOrgRateMoney(orgid,paymentid);
//              //本月人数303中的人数
//              Integer personCounts = monthPaymentTailDAO.queryPaymentPersonCountsLJ(monthPaymentHead.getId());
//              //增加人数
//              Integer personCountsAdd = chgPersonTailDAO.queryPersonCount(orgid, paymentid, chgTypeAdd);
//              //减少人数
//              Integer personCountsLess = chgPersonTailDAO.queryPersonCount(orgid, paymentid, chgTypeLess);
//              //上月人数
//              Integer ultimoPersonCounts = new Integer(personCounts.intValue()+personCountsLess.intValue()-personCountsAdd.intValue());
//              //本月金额
//              BigDecimal payMoney = this.getSumPay(orgid);
//              //增加金额AA205状态为13
//              BigDecimal empPayMoneyAdd = chgPersonTailDAO.queryEmpPersonMoney(orgid,paymentid, chgTypeAdd);
//              BigDecimal orgPayMoneyAdd = chgPersonTailDAO.queryOrgPersonMoney(orgid,paymentid, chgTypeAdd);
//              BigDecimal payMoneyAdd = empPayMoneyAdd.add(orgPayMoneyAdd);
//              //减少金额AA205状态为4
//              BigDecimal empPayMoneyLess = chgPersonTailDAO.queryEmpPersonMoney(orgid,paymentid, chgTypeLess);
//              BigDecimal orgPayMoneyLess = chgPersonTailDAO.queryOrgPersonMoney(orgid,paymentid, chgTypeLess);
//              BigDecimal payMoneyLess = empPayMoneyLess.add(orgPayMoneyLess);
//              //上月金额
//              BigDecimal ultimoPayMoney = payMoney.add(payMoneyLess).subtract(payMoneyAdd);
//              if(empRealPay.doubleValue()!=0){
//                sumPay = orgShouldPay;
//                if(orgPaymentMoney.doubleValue()>=0){
//                  orgPayMoneyAdd = orgPayMoneyAdd.add(orgPaymentMoney);
//                }else{
//                  orgPaymentMoney = orgPaymentMoney.multiply(new BigDecimal(-1));
//                  orgPayMoneyLess = orgPayMoneyLess.add(orgPaymentMoney);
//                }
//                if(orgRateMoney.doubleValue()>=0){
//                  orgPayMoneyAdd = orgPayMoneyAdd.add(orgRateMoney);
//                }else{
//                  orgRateMoney = orgRateMoney.multiply(new BigDecimal(-1));
//                  orgPayMoneyLess = orgPayMoneyLess.add(orgRateMoney);
//                }
//                ultimoPayMoney = orgShouldPay.add(orgPayMoneyLess).subtract(orgPayMoneyAdd);
//                //本月金额
//                f.setPayMoney(orgShouldPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
//                f.setPayMoneyAdd(orgPayMoneyAdd.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
//                f.setPayMoneyLess(orgPayMoneyLess.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
//                f.setInceptMonth(monthpayJYAF.getInceptMonth());
//                f.setPayMonth(monthpayJYAF.getInceptMonth());
//                f.setPersonCounts(personCounts);
//                f.setUltimoPayMoney(ultimoPayMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
//                f.setPayStatus(2);
//                f.setUntilMonth(inceptMonth);
//              }else if(orgRealPay.doubleValue()!=0){
//                sumPay = empShouldPay;
//                if(empPaymentMoney.doubleValue()>=0){
//                  empPayMoneyAdd = empPayMoneyAdd.add(empPaymentMoney);
//                }else{
//                  empPaymentMoney = empPaymentMoney.multiply(new BigDecimal(-1));
//                  empPayMoneyLess = empPayMoneyLess.add(empPaymentMoney);
//                }
//                if(empRateMoney.doubleValue()>=0){
//                  empPayMoneyAdd = empPayMoneyAdd.add(empRateMoney);
//                }else{
//                  empRateMoney = empRateMoney.multiply(new BigDecimal(-1));
//                  empPayMoneyLess = empPayMoneyLess.add(empRateMoney);
//                }
//                ultimoPayMoney = empShouldPay.add(empPayMoneyLess).subtract(empPayMoneyAdd);
//                //本月金额
//                f.setPayMoney(empShouldPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
//                f.setPayMoneyAdd(empPayMoneyAdd.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
//                f.setPayMoneyLess(empPayMoneyLess.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
//                f.setInceptMonth(monthpayJYAF.getInceptMonth());
//                f.setPayMonth(monthpayJYAF.getInceptMonth());
//                f.setPersonCounts(personCounts);
//                f.setUltimoPayMoney(ultimoPayMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
//                f.setPayStatus(3);
//                f.setUntilMonth(inceptMonth);
//              }
//              //应缴总额
//              f.setSumPay(sumPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
//              f.setPersonCountsAdd(personCountsAdd);
//              f.setPersonCountsLess(personCountsLess);
//              f.setUltimoPersonCounts(ultimoPersonCounts);
//              f.setUntilMonth(inceptMonth);
//            }
//          }
//        }else{
//          throw new BusinessException("未进行过汇缴，不能做欠缴！");
//        }
//      }
     }
     /**
      * 单位缴至年月
      */
     String orgPayMonth = "";
     orgPayMonth = this.getOrgPayMonth(orgid, "");
     if(orgPayMonth == null || "".equals(orgPayMonth)){
       orgPayMonth = this.getOrgPaymentMonth(orgid.toString(), "org"); 
     }
     f.setOrgPayMonth(orgPayMonth);
     /**
      * 职工缴至年月
      */
     String empPayMonth = "";
     empPayMonth = this.getEmpPayMonth(orgid);
     if(empPayMonth == null || "".equals(empPayMonth)){
       empPayMonth = this.getOrgPaymentMonth(orgid.toString(), "");
     }
     f.setEmpPayMonth(empPayMonth);
     /**
      * 单位欠缴月数
      */
     String lackMonths = "";
     if(new Integer(orgPayMonth).intValue()>new Integer(empPayMonth).intValue()){
       lackMonths = orgPayMonth;
     }else{
       lackMonths = empPayMonth;
     }
     int months = BusiTools.getDisMonths(bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+"01", 
         lackMonths.substring(0,4)+"-"+lackMonths.substring(4,6)+"-"+"01");
     f.setLackMonths(String.valueOf(months));
     /**
      * 单位欠缴金额
      */
     BigDecimal orgLackMoney = new BigDecimal(0.00);
     orgLackMoney = orgPay.add(empPay).multiply(new BigDecimal(months)).divide(new BigDecimal(1),2, BigDecimal.ROUND_HALF_UP);
     f.setOrgLackMoney(orgLackMoney.toString());
    return f;
  }
  /**
   * 添加缴存及其关联表的信息
   */
  public List addPaymentInfo(MonthpayJYAF f,SecurityInfo securityInfo) throws Exception, BusinessException{
    String orgId=f.getOrgid();
    String [] payStatus = {"1","3","4"};
    int status = f.getPayStatus();
    Serializable paymentHeadId = null;
    Org org = orgDAO.queryById(new Integer(orgId));
    //缴至年月
    String inceptMonth = monthPaymentHeadDAO.queryMaxMonth(orgId);
        List list1=monthPaymentHeadDAO.queryMonthPaymentHeadAccounts(orgId,f.getInceptMonth());
        if(list1 != null && list1.size()>0){
          if(list1.size()>=2){
            throw new BusinessException("已经做过此月汇缴，不能重复汇缴！");
          }else if(list1.size()==1){
            MonthPaymentHead monthPaymentHead1=(MonthPaymentHead)list1.get(0); 
            MonthPayment monthPayment=monthPaymentDAO.queryById(new Integer(monthPaymentHead1.getPaymentHead().getId().toString()));
            if(monthPayment.getPayModel().toString().equals(f.getPayStatus()+"")){
              throw new BusinessException("已经做过此月汇缴，不能重复汇缴！");
            }
          }
        }
    if(inceptMonth==null){
      //inceptMonth = org.getFirstPayMonth();
      inceptMonth = this.getOrgPayMonth(orgId);
    }else{
      inceptMonth = BusiTools.addMonth(inceptMonth,1);
    }
    //缴至年月+1
    String str1=f.getInceptMonth().substring(0,4);
    String str2=f.getInceptMonth().substring(4,f.getInceptMonth().length());
    String str3 = str1+"-"+str2;
     int month=BusiTools.minusDate(str3+"-01", inceptMonth.substring(0,4)+"-"+inceptMonth.substring(4,inceptMonth.length())+"-01");
    System.out.println(str3+"-01");
    System.out.println(inceptMonth.substring(0,4)+"-"+inceptMonth.substring(4,inceptMonth.length())+"-01");
    //  缴存月数
    int monthCounts=BusiTools.monthInterval(f.getInceptMonth(), f.getPayMonth())+1;
    MonthPayment monthPayment = new MonthPayment();
    monthPayment.setReceivables_bank_name(f.getReceivables_bank_name());
    monthPayment.setReceivables_bank_acc(f.getReceivables_bank_acc());
    monthPayment.setPayment_bank_name(f.getPayment_bank_name());
//    if(f.getPayment_bank_name()!=null&&!f.getPayment_bank_name().equals(""))
//    {
//      String bankname=this.queryPaymentBankName(f.getPayment_bank_name());
//      monthPayment.setPayment_bank_name(bankname);
//    }
    monthPayment.setPayment_bank_acc(f.getPayment_bank_acc());
    monthPayment.setPay_way(f.getPayWay());
    monthPayment.setPay_kind(f.getPayKind());
    
    if(org.getOrgInfo().getReserveaB()!=null && !org.getOrgInfo().getReserveaB().equals("")){
      if(!org.getOrgInfo().getReserveaB().equals(f.getPayment_orgname())){
        org.getOrgInfo().setReserveaB(f.getPayment_orgname());
      }
    }else{
      if(!org.getOrgInfo().getName().equals(f.getPayment_orgname())){
        org.getOrgInfo().setReserveaB(f.getPayment_orgname());
      }
    }
    org.getOrgInfo().getPayBank().setAccountNum(f.getPayment_bank_acc());
    org.getOrgInfo().getPayBank().setName(f.getPayment_bank_name());
    List list=empDAO.queryEmpList(new Integer(orgId), payStatus);
    if(status == 1){
      //均缴
      //插入缴存头表A
      monthPayment.setPayMoney(f.getSumPay());
      monthPayment.setNoteNum(f.getNoteNum());
      monthPayment.setPayStatus(new Integer(2));
      monthPayment.setOrg(org);
      monthPayment.setPayModel(new Integer(status));
      paymentHeadId = monthPaymentDAO.insert(monthPayment);
      for(int i=0;i<monthCounts;i++){
      //插入汇缴头表
//        String mm = monthPaymentHeadDAO.queryMaxMonth(orgId);
        String mm = f.getInceptMonth();
        MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
        monthPaymentHead.setPayMonth(BusiTools.addMonth(mm,i));
        monthPaymentHead.setPaymentHead(monthPayment);
        monthPaymentHeadDAO.insert(monthPaymentHead);
        //插入汇缴尾表
        addMonthPaymentTail(list,monthPaymentHead);
      }
    }else if(status == 3){
      System.out.println("f..."+month);
        //只缴职工
        //插入缴存头表A
        monthPayment.setPayMoney(f.getSumPay());
        monthPayment.setNoteNum(f.getNoteNum());
        monthPayment.setPayStatus(new Integer(2));
        monthPayment.setOrg(org);
        monthPayment.setPayModel(new Integer(status));
        paymentHeadId = monthPaymentDAO.insert(monthPayment);
        MonthPayment mp=monthPaymentDAO.queryById(monthPayment.getId());
        if(month>=0){
          for(int j=0;j<monthCounts;j++){
            //插入汇缴头表
//          String mm = monthPaymentHeadDAO.queryMaxMonth(orgId);
            String mm = f.getInceptMonth();
//            if(mm==null){
//              mm = org.getFirstPayMonth();
//            }
            MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
            monthPaymentHead.setPayMonth(BusiTools.addMonth(mm,j));
            monthPaymentHead.setPaymentHead(mp);
            monthPaymentHeadDAO.insert(monthPaymentHead);
            //插入汇缴尾表
            for(int i=0;i<list.size();i++){
              Emp m = (Emp)list.get(i);
              MonthPaymentTail monthPaymentTail = new MonthPaymentTail();
              monthPaymentTail.setEmpId(m.getEmpId());
              monthPaymentTail.setMonthPaymentHead(monthPaymentHead);
              monthPaymentTail.setOrgShouldPay(m.getOrgPay());
              monthPaymentTail.setEmpShouldPay(m.getEmpPay());
              monthPaymentTail.setOrgRealPay(new BigDecimal(0.00));
              monthPaymentTail.setEmpRealPay(m.getEmpPay());
              monthPaymentTail.setSalaryBase(m.getSalaryBase());
              monthPaymentTail.setOrgRate(m.getOrg().getOrgRate());
              monthPaymentTail.setEmpRate(m.getOrg().getEmpRate());
              monthPaymentTailDAO.insert(monthPaymentTail);
            }
          }
        }else{
          //插入汇缴头表
          MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
          monthPaymentHead.setPayMonth(f.getPayMonth());
          monthPaymentHead.setPaymentHead(mp);
          monthPaymentHeadDAO.insert(monthPaymentHead);
          List emplist=monthPaymentTailDAO.queryEmpList_yg(orgId, f.getInceptMonth());
          //插入汇缴尾表
          addMonthPaymentTailEmp(emplist, monthPaymentHead);
        }
   
    }else if(status == 2 ){
      System.out.println("f..."+month);
        //只缴单位
        //插入缴存头表A
        monthPayment.setPayMoney(f.getSumPay());
        monthPayment.setNoteNum(f.getNoteNum());
        monthPayment.setPayStatus(new Integer(2));
        monthPayment.setOrg(org);
        monthPayment.setPayModel(new Integer(status));
        paymentHeadId = monthPaymentDAO.insert(monthPayment);
        MonthPayment mp=monthPaymentDAO.queryById(monthPayment.getId());
        if(month>=0){
          for(int j=0;j<monthCounts;j++){
            //插入汇缴头表
//          String mm = monthPaymentHeadDAO.queryMaxMonth(orgId);
            String mm = f.getInceptMonth();
//            if(mm==null){
//              mm = org.getFirstPayMonth();
//            }
            MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
            monthPaymentHead.setPayMonth(BusiTools.addMonth(mm,j));
            monthPaymentHead.setPaymentHead(mp);
            monthPaymentHeadDAO.insert(monthPaymentHead);
            //插入汇缴尾表
            for(int i=0;i<list.size();i++){
              Emp m = (Emp)list.get(i);
              MonthPaymentTail monthPaymentTail = new MonthPaymentTail();
              monthPaymentTail.setEmpId(m.getEmpId());
              monthPaymentTail.setMonthPaymentHead(monthPaymentHead);
              monthPaymentTail.setOrgShouldPay(m.getOrgPay());
              monthPaymentTail.setEmpShouldPay(m.getEmpPay());
              monthPaymentTail.setOrgRealPay(m.getOrgPay());
              monthPaymentTail.setEmpRealPay(new BigDecimal(0.00));
              monthPaymentTail.setSalaryBase(m.getSalaryBase());
              monthPaymentTail.setOrgRate(m.getOrg().getOrgRate());
              monthPaymentTail.setEmpRate(m.getOrg().getEmpRate());
              monthPaymentTailDAO.insert(monthPaymentTail);
            }
          }
        }else{
          //插入汇缴头表
          MonthPaymentHead monthPaymentHead = new MonthPaymentHead();
          monthPaymentHead.setPayMonth(f.getPayMonth());
          monthPaymentHead.setPaymentHead(mp);
          monthPaymentHeadDAO.insert(monthPaymentHead);
          List emplist=monthPaymentTailDAO.queryEmpList_yg(orgId, f.getInceptMonth());
          addMonthPaymentTailOrg(emplist, monthPaymentHead);
        }
    }
    //向AA201中插入缴存ID
    updateChgOrgRate(org.getId(),monthPayment); 
    // 修改记录：单位版_更新DA001 wangy 2008-02-27
    int isOrgEdition = securityInfo.getIsOrgEdition();
    if ( isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {// 单位版
      // 向AA202中插入缴存ID
      updateChgPaymentHead_org(org.getId(),monthPayment);
      // 向AA204中插入缴存ID
      updateChgPersonHead_org(org.getId(),monthPayment);
    } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
      //向AA202中插入缴存ID
      updateChgPaymentHead(org.getId(),monthPayment);
      //向AA204中插入缴存ID
      updateChgPersonHead(org.getId(),monthPayment);
    }
    // 判断是否有自动挂账
    if(f.getType().equals("2")){
      OrgExcessPayment orgExcessPayment = new OrgExcessPayment();
      orgExcessPayment.setOrg(org);
      orgExcessPayment.setPayMoney(f.getRealPay().subtract(f.getSumPay()));
      orgExcessPayment.setNoteNum(f.getNoteNum());
      orgExcessPayment.setPayStatus(new Integer(2));
      orgExcessPayment.setExcessReason("自动挂账");
      orgExcessPayment.setReserveaA(paymentHeadId.toString());
      Serializable id = orgExcessPaymentDAO.insert(orgExcessPayment);
      // 将挂账业务的id插入汇缴业务的备选C中
      monthPayment.setReserveaA(id.toString());
      // 插入319
      OrgExcessPaymentBizActivityLog orgExcessPaymentBizActivityLog=new OrgExcessPaymentBizActivityLog();
      orgExcessPaymentBizActivityLog.setBizid(new Integer(id.toString()));
      orgExcessPaymentBizActivityLog.setAction(new Integer(2));
      orgExcessPaymentBizActivityLog.setOpTime(new Date());
      orgExcessPaymentBizActivityLog.setOperator(securityInfo.getUserName());
      orgExcessPaymentBizActivityLogDAO.insert(orgExcessPaymentBizActivityLog);
    }
    //插入319
    addMonthPaymentBizLog(monthPayment,securityInfo);
    //插入BA003
    addHafOperateLog(monthPayment,orgId,securityInfo);
//    List empTailList = monthPaymentTailDAO.queryPaymentTailListLJ(monthPayment.getId());
    List returnlist=new ArrayList();
//    for(int i=0;i<empTailList.size();i++){
//      MonthPaymentTail m = (MonthPaymentTail)empTailList.get(i);
//      Emp emp=empDAO.getChgPersonEmp_WL(org.getId().toString(), m.getEmpId().toString());
//      m.setSex(BusiTools.getBusiValue(Integer.parseInt(emp.getEmpInfo().getSex().toString()), BusiConst.SEX));
//      m.setEmp(emp);
//      m.setSumPay(m.getEmpRealPay().add(m.getOrgRealPay()));
//      returnlist.add(m);
//    }
    return returnlist;
  }

//插入汇缴尾表
  public void addMonthPaymentTail(List emplist,MonthPaymentHead monthPaymentHead){
    for(int i=0;i<emplist.size();i++){
      System.out.println("d.."+i);
      Emp emp=(Emp)emplist.get(i);
      MonthPaymentTail monthPaymentTail = new MonthPaymentTail();
      monthPaymentTail.setEmpId(emp.getEmpId());
      monthPaymentTail.setMonthPaymentHead(monthPaymentHead);
      monthPaymentTail.setOrgShouldPay(emp.getOrgPay());
      monthPaymentTail.setEmpShouldPay(emp.getEmpPay());
      monthPaymentTail.setOrgRealPay(emp.getOrgPay());
      monthPaymentTail.setEmpRealPay(emp.getEmpPay());
      monthPaymentTail.setSalaryBase(emp.getSalaryBase());
      monthPaymentTail.setOrgRate(emp.getOrg().getOrgRate());
      monthPaymentTail.setEmpRate(emp.getOrg().getEmpRate());
      monthPaymentTailDAO.insert(monthPaymentTail);
    }
  }
//插入汇缴尾表只缴职工
  public void addMonthPaymentTailEmp(List emplist,MonthPaymentHead monthPaymentHead){
    for(int i=0;i<emplist.size();i++){
      MonthPaymentTail m = (MonthPaymentTail)emplist.get(i);
      //Emp emp = empDAO.queryByCriterions(m.getEmpId().toString(), monthPaymentHead.getPaymentHead().getOrg().getId().toString());
      MonthPaymentTail monthPaymentTail = new MonthPaymentTail();
      monthPaymentTail.setEmpId(m.getEmpId());
      monthPaymentTail.setMonthPaymentHead(monthPaymentHead);
      monthPaymentTail.setOrgShouldPay(m.getOrgShouldPay());
      monthPaymentTail.setEmpShouldPay(m.getEmpShouldPay());
      monthPaymentTail.setOrgRealPay(new BigDecimal(0.00));
      monthPaymentTail.setEmpRealPay(m.getEmpShouldPay());
      monthPaymentTail.setSalaryBase(m.getSalaryBase());
      monthPaymentTail.setOrgRate(m.getOrgRate());
      monthPaymentTail.setEmpRate(m.getEmpRate());
      monthPaymentTailDAO.insert(monthPaymentTail);
    }
  }
//插入汇缴尾表只缴单位
  public void addMonthPaymentTailOrg(List emplist,MonthPaymentHead monthPaymentHead){
    for(int i=0;i<emplist.size();i++){
      MonthPaymentTail m = (MonthPaymentTail)emplist.get(i);
      //Emp emp = empDAO.queryByCriterions(m.getEmpId().toString(), monthPaymentHead.getPaymentHead().getOrg().getId().toString());
      MonthPaymentTail monthPaymentTail = new MonthPaymentTail();
      monthPaymentTail.setEmpId(m.getEmpId());
      monthPaymentTail.setMonthPaymentHead(monthPaymentHead);
      monthPaymentTail.setOrgShouldPay(m.getOrgShouldPay());
      monthPaymentTail.setEmpShouldPay(m.getEmpShouldPay());
      monthPaymentTail.setEmpRealPay(new BigDecimal(0.00));
      monthPaymentTail.setOrgRealPay(m.getOrgShouldPay());
      monthPaymentTail.setSalaryBase(m.getSalaryBase());
      monthPaymentTail.setOrgRate(m.getOrgRate());
      monthPaymentTail.setEmpRate(m.getEmpRate());
      monthPaymentTailDAO.insert(monthPaymentTail);
    }
  }
//向AA201中插入缴存ID
  public void updateChgOrgRate(Serializable orgid,MonthPayment monthPayment){
    List chgOrgRate = chgOrgRateDAO.queryChgOrgRateByPayHeadId(orgid,null,new Integer(2));
    for(int i=0;i<chgOrgRate.size();i++){
      ChgOrgRate c = (ChgOrgRate)chgOrgRate.get(i);
      c.setPaymentHead(monthPayment);
    }
  }
//向AA202中插入缴存ID
  public void updateChgPaymentHead(Serializable orgid,MonthPayment monthPayment){
    List chgPaymentHead = chgPaymentHeadDAO.queryChgPaymentHeadByPayHeadId(orgid,null,new Integer(2));
    for(int i=0;i<chgPaymentHead.size();i++){
      ChgPaymentHead c = (ChgPaymentHead)chgPaymentHead.get(i);
      c.setPaymentHead(monthPayment);
    }
  }
//向AA204中插入缴存ID
  public void updateChgPersonHead(Serializable orgid,MonthPayment monthPayment){
    List chgPersonHead = chgPersonHeadDAO.queryChgPersonHeadByPayHeadId(orgid,null,new Integer(2));
    for(int i=0;i<chgPersonHead.size();i++){
      ChgPersonHead c = (ChgPersonHead)chgPersonHead.get(i);
      c.setPaymentHead(monthPayment);
    }
  }
  
  // 向AA202中插入缴存ID_单位版 wangy 2008-02-27
  public void updateChgPaymentHead_org(Serializable orgid,MonthPayment monthPayment) throws BusinessException {
    List chgPaymentHead = chgPaymentHeadDAO.queryChgPaymentHeadByPayHeadId(orgid,null,new Integer(2));
    for(int i=0;i<chgPaymentHead.size();i++){
      ChgPaymentHead c = (ChgPaymentHead)chgPaymentHead.get(i);
      // 判断DA001中是否存在状态为0(未提取)的变更记录
      String orgHeadId = c.getId().toString();
//      int count = autoInfoPickDAODW.queryNoPickUpListbyOrgHeadId_M_N(orgHeadId);
//      if (count != 0) {
        // 更新单位库变更表的同时更新DA001
        AutoInfoPick autoInfoPick = autoInfoPickDAODW.updateAutoInfoPickByMonthPayment_M_N(orgHeadId);
        autoInfoPick.setPayHeadId(new Integer(monthPayment.getId().toString()));
        // monthPayment.getId();// 缴存ID(AA301.ID)
//      }
      c.setPaymentHead(monthPayment);
    }
  }
  
  // 向AA204中插入缴存ID_单位版 wangy 2008-02-27
  public void updateChgPersonHead_org(Serializable orgid,MonthPayment monthPayment) throws BusinessException {
    List chgPersonHead = chgPersonHeadDAO.queryChgPersonHeadByPayHeadId(orgid,null,new Integer(2));
    for(int i=0;i<chgPersonHead.size();i++){
      ChgPersonHead c = (ChgPersonHead)chgPersonHead.get(i);
      // 判断DA001中是否存在状态为0(未提取)的变更记录
      String orgHeadId = c.getId().toString();
//      int count = autoInfoPickDAODW.queryNoPickUpListbyOrgHeadId_O(orgHeadId);
//      if (count != 0) {
        // 更新单位库变更表的同时更新DA001
        AutoInfoPick autoInfoPick = autoInfoPickDAODW.updateAutoInfoPickByMonthPayment_O(orgHeadId);
        autoInfoPick.setPayHeadId(new Integer(monthPayment.getId().toString()));
//        throw new BusinessException("qqqqqq");
        // monthPayment.getId();// 缴存ID(AA301.ID)
//      }      
      c.setPaymentHead(monthPayment);
    }
  }
  
//插入319
  public void addMonthPaymentBizLog(MonthPayment monthPayment,SecurityInfo securityInfo){
    MonthPaymentBizActivityLog monthPaymentBizActivityLog = new MonthPaymentBizActivityLog();
    monthPaymentBizActivityLog.setBizid(new Integer(monthPayment.getId().toString()));
    monthPaymentBizActivityLog.setAction(new Integer(2));
    monthPaymentBizActivityLog.setOperator(securityInfo.getUserName());
    monthPaymentBizActivityLog.setOpTime(new Date());
    monthPaymentBizActivityLogDAO.insert(monthPaymentBizActivityLog);
  }
  //插入BA003
  public void addHafOperateLog(MonthPayment monthPayment,String orgId,SecurityInfo securityInfo){
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_DO));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_ADD));
    hafOperateLog.setOpBizId(new Integer(monthPayment.getId().toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(new Integer(orgId));
    hafOperateLogDAO.insert(hafOperateLog);
  }
  /**
   * 查询汇缴维护列表信息
   */
  public List findPaymentList(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    List list=new ArrayList();
    List returnlist=new ArrayList();     
    try{
    Serializable id=(Serializable)pagination.getQueryCriterions().get("id");
    if(pagination.getQueryCriterions().get("id") != null){
      id=pagination.getQueryCriterions().get("id").toString();
    }
    String name=(String) pagination.getQueryCriterions().get("name");
    String status = (String) pagination.getQueryCriterions().get("status");
    String inceptMonth = (String) pagination.getQueryCriterions().get("inceptMonth");
    String payMonth = (String) pagination.getQueryCriterions().get("payMonth");
    //登记、入帐等
    String payType = (String) pagination.getQueryCriterions().get("payType");
    String inceptPayMoney = (String) pagination.getQueryCriterions().get("inceptPayMoney");
    String payMoney = (String) pagination.getQueryCriterions().get("payMoney");
    String inceptSettlementDate = (String) pagination.getQueryCriterions().get("inceptSettlementDate") ;
    String settlementDate = (String) pagination.getQueryCriterions().get("settlementDate");
    String settDate = (String) pagination.getQueryCriterions().get("settDate") ;
    String settDate1 = (String) pagination.getQueryCriterions().get("settDate1") ;
    String collBankId = (String) pagination.getQueryCriterions()
    .get("collBankId");
    String orderBy=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    int page=pagination.getPage();
    list=monthPaymentTailDAO.queryPaymentListByCriterionsLJ(id, name, status,inceptMonth,payMonth,payType,inceptPayMoney,
        payMoney,inceptSettlementDate,settlementDate,orderBy, order, start, pageSize,securityInfo,page,settDate,settDate1,collBankId);  
    //转换业务状态
    if(list!=null){
      for(int i=0;i<list.size();i++){
        MonthpayMaintainDto dto1=(MonthpayMaintainDto)list.get(i);
        Integer tempCount=this.queryPayCount(new Integer(dto1.getId()));
        dto1.setPayCount(tempCount);
        MonthpayMaintainDto dto2=new MonthpayMaintainDto();
        BeanUtils.copyProperties(dto2, dto1);    
        dto2.setPayStatus(BusiTools.getBusiValue(Integer.parseInt(dto1.getPayStatus()), BusiConst.BUSINESSSTATE));
        returnlist.add(dto2);
      }
    }
    int count = monthPaymentTailDAO.queryPaymentCountByCriterionsLJ(id, name,status,inceptMonth,payMonth,payType,inceptPayMoney,
        payMoney,inceptSettlementDate,settlementDate,securityInfo,settDate,settDate1,collBankId);
    pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
//    if(list.size()==0){
//      throw new BusinessException("不存在记录");
//    }
    return returnlist;
  }
  /**
   * 维护列表打印List
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findPaymentListPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception{
    List list=new ArrayList();
    List returnlist=new ArrayList();     
    try{
    Serializable id=(Serializable)pagination.getQueryCriterions().get("id");
    if(pagination.getQueryCriterions().get("id") != null){
      id=pagination.getQueryCriterions().get("id").toString();
    }
    String name=(String) pagination.getQueryCriterions().get("name");
    String status = (String) pagination.getQueryCriterions().get("status");
    String inceptMonth = (String) pagination.getQueryCriterions().get("inceptMonth");
    String payMonth = (String) pagination.getQueryCriterions().get("payMonth");
    //登记、入帐等
    String payType = (String) pagination.getQueryCriterions().get("payType");
    String inceptPayMoney = (String) pagination.getQueryCriterions().get("inceptPayMoney");
    String payMoney = (String) pagination.getQueryCriterions().get("payMoney");
    String inceptSettlementDate = (String) pagination.getQueryCriterions().get("inceptSettlementDate") ;
    String settlementDate = (String) pagination.getQueryCriterions().get("settlementDate");
    String settDate = (String) pagination.getQueryCriterions().get("settDate") ;
    String settDate1 = (String) pagination.getQueryCriterions().get("settDate1") ;
    String collBankId = (String) pagination.getQueryCriterions()
    .get("collBankId");
    String orderBy=(String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother(); 
    list=monthPaymentTailDAO.queryPaymentListByCriterionsPrint_jj(id, name, status,inceptMonth,payMonth,payType,inceptPayMoney,
        payMoney,inceptSettlementDate,settlementDate,orderBy, order,securityInfo,settDate,settDate1,collBankId);  
    //转换业务状态
    if(list!=null){
      for(int i=0;i<list.size();i++){
        MonthpayMaintainDto dto1=(MonthpayMaintainDto)list.get(i);
        Integer tempCount=this.queryPayCount(new Integer(dto1.getId()));
        dto1.setPayCount(tempCount);
        MonthpayMaintainDto dto2=new MonthpayMaintainDto();
        BeanUtils.copyProperties(dto2, dto1);    
        dto2.setPayStatus(BusiTools.getBusiValue(Integer.parseInt(dto1.getPayStatus()), BusiConst.BUSINESSSTATE));
        returnlist.add(dto2);
      }
    }
    }catch(Exception e){
      e.printStackTrace();
    }

    return returnlist;
  }
  public BigDecimal findMonthpayTotalmoney(Pagination pagination,SecurityInfo securityInfo)throws Exception{
      BigDecimal totalmoney = new BigDecimal(0);
      Serializable id=(Serializable)pagination.getQueryCriterions().get("id");
      if(pagination.getQueryCriterions().get("id") != null){
        id=pagination.getQueryCriterions().get("id").toString();
      }
      String name=(String) pagination.getQueryCriterions().get("name");
      String status = (String) pagination.getQueryCriterions().get("status");
      String inceptMonth = (String) pagination.getQueryCriterions().get("inceptMonth");
      String payMonth = (String) pagination.getQueryCriterions().get("payMonth");
      //登记、入帐等
      String payType = (String) pagination.getQueryCriterions().get("payType");
      String inceptPayMoney = (String) pagination.getQueryCriterions().get("inceptPayMoney") ;
      String payMoney = (String) pagination.getQueryCriterions().get("payMoney");
      String inceptSettlementDate = (String) pagination.getQueryCriterions().get("inceptSettlementDate") ;
      String settlementDate = (String) pagination.getQueryCriterions().get("settlementDate");
      String settDate = (String) pagination.getQueryCriterions().get("settDate") ;
      String settDate1 = (String) pagination.getQueryCriterions().get("settDate1") ;
      String collBankId = (String) pagination.getQueryCriterions()
      .get("collBankId");
//      totalmoney = monthPaymentTailDAO.queryPaymentListTotalLJ(id, name, status, inceptMonth, payMonth, payType, 
//          inceptPayMoney, payMoney, inceptSettlementDate, settlementDate,securityInfo,settDate);
      List tempList=monthPaymentTailDAO.queryPaymentListByCriterions_wxg(id, name, status, inceptMonth, payMonth, payType, inceptPayMoney, payMoney,
          inceptSettlementDate, settlementDate, securityInfo, settDate,settDate1,collBankId);
      for(int i=0;i<tempList.size();i++)
      {
        MonthpayMaintainDto dto=(MonthpayMaintainDto)tempList.get(i);
        totalmoney=totalmoney.add(dto.getPay());
      }
      totalmoney=totalmoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN);
      return totalmoney;
  }
  /**
   * 根据汇缴ID删除缴存及其相关信息
   * @param paymentId
   */
  public void deletePaymentInfo(Serializable paymentId,SecurityInfo securityInfo) throws Exception{
    try{
    //判断是状态是否为2
    Integer payStatus=monthPaymentDAO.getPayStatusLJ(paymentId);
    String status = BusiTools.getBusiValue(Integer.parseInt(payStatus.toString()), BusiConst.BUSINESSSTATE);
    if(Integer.parseInt(payStatus.toString())!= 2 ){
      throw new BusinessException("状态为"+status+"!不能删除！");
    }
    Integer orgId = monthPaymentDAO.getOrgIdLJ(new Integer(paymentId.toString()));
    //查询最大汇缴ID
    Integer maxPaymentId = monthPaymentDAO.getPaymentIdLJ(orgId);
    // 判断是否存在自动挂账
    Object obj = monthPaymentDAO.getReservea_a(new Integer(paymentId.toString()));
    //判断是否为最大一条
//    System.out.println("maxPaymentId---------"+maxPaymentId);
//    System.out.println("paymentId---------"+paymentId);
    if(!paymentId.toString().equals(maxPaymentId.toString())){
      //查询状态为2的最大的缴存年月
      String date = monthPaymentHeadDAO.getPayMonthLJ(maxPaymentId);
      throw new BusinessException("请先删除汇缴年月为"+date+"的缴存信息！");
    }
    //缴存状态为2的最后一条记录，可以删除了。
    //删除303
    List taillist=monthPaymentTailDAO.queryPaymentTailListLJ(paymentId);
    monthPaymentTailDAO.deleteList(taillist);
    //删除302
    List headlist=monthPaymentHeadDAO.queryMonthPaymentHeadLJ(paymentId);
    monthPaymentHeadDAO.deleteList(headlist);
    //删除301
    MonthPayment monthPayment = monthPaymentDAO.queryById(new Integer(paymentId.toString()));
    monthPaymentDAO.delete(monthPayment);
    //更新201
    List chgOrgRate = chgOrgRateDAO.queryChgOrgRateByPayHeadId(monthPayment.getOrg().getId(),paymentId, null);
    if(chgOrgRate.size()>0){
      for(int i=0;i<chgOrgRate.size();i++){
        ChgOrgRate c = (ChgOrgRate)chgOrgRate.get(i);
        c.setPaymentHead(null);
      }
    }
    /*//更新202
    List chgPaymentHead = chgPaymentHeadDAO.queryChgPaymentHeadByPayHeadId(monthPayment.getOrg().getId(),paymentId, null);
    if(chgPaymentHead.size()>0){
      for(int i=0;i<chgPaymentHead.size();i++){
        ChgPaymentHead c=(ChgPaymentHead)chgPaymentHead.get(i);
        c.setPaymentHead(null);
      }
    }
    //更新204
    List chgPersonHead = chgPersonHeadDAO.queryChgPersonHeadByPayHeadId(monthPayment.getOrg().getId(),paymentId,null);
    if(chgPersonHead != null){
      for(int i=0;i<chgPersonHead.size();i++){
        ChgPersonHead c = (ChgPersonHead)chgPersonHead.get(i);
        c.setPaymentHead(null);
      }
    }*/
    // 修改记录：单位版_更新DA001 wangy 2008-02-27
    int isOrgEdition = securityInfo.getIsOrgEdition();
    if ( isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_ORG) {
      
    } else if (isOrgEdition == BusiConst.ORG_OR_CENTER_INFO_CENTER) {// 中心版
      //更新202
      List chgPaymentHead = chgPaymentHeadDAO.queryChgPaymentHeadByPayHeadId(monthPayment.getOrg().getId(),paymentId, null);
      if(chgPaymentHead.size()>0){
        for(int i=0;i<chgPaymentHead.size();i++){
          ChgPaymentHead c=(ChgPaymentHead)chgPaymentHead.get(i);
          c.setPaymentHead(null);
        }
      }
      //更新204
      List chgPersonHead = chgPersonHeadDAO.queryChgPersonHeadByPayHeadId(monthPayment.getOrg().getId(),paymentId,null);
      if(chgPersonHead != null){
        for(int i=0;i<chgPersonHead.size();i++){
          ChgPersonHead c = (ChgPersonHead)chgPersonHead.get(i);
          c.setPaymentHead(null);
        }
      }
    }
    // 删除自动挂账业务
    if (obj != null) {
        OrgExcessPayment temp_orgExcessPayment = orgExcessPaymentDAO
            .queryById(new Integer(obj.toString()));
        if (temp_orgExcessPayment!=null) {
          orgExcessPaymentDAO.delete(temp_orgExcessPayment);
          // 删除挂账的业务日志
          orgExcessPaymentBizActivityLogDAO.delete(new Integer(temp_orgExcessPayment.getId().toString()), new Integer(2));
        }
     }
    //删除319
    MonthPaymentBizActivityLog monthPaymentBizActivityLog = monthPaymentBizActivityLogDAO.
    queryMonthPaymentLogByPayHeadIdLJ(paymentId,new Integer(2));
    monthPaymentBizActivityLogDAO.delete(monthPaymentBizActivityLog);
    //插入BA003
    HafOperateLog hafOperateLog = new HafOperateLog();
    hafOperateLog.setOpSys(new Integer(BusiLogConst.OP_SYSTEM_TYPE_COLLECTION));
    hafOperateLog.setOpModel(Integer.toString(BusiLogConst.OP_MODE_PAYMENTMANAGE_PAYMENT_MAINTAIN));
    hafOperateLog.setOpButton(Integer.toString(BusiLogConst.BIZLOG_ACTION_DELETE));
    hafOperateLog.setOpBizId(new Integer(paymentId.toString()));
    hafOperateLog.setOperator(securityInfo.getUserName());
    hafOperateLog.setOpIp(securityInfo.getUserIp());
    hafOperateLog.setOpTime(new Date());
    hafOperateLog.setOrgId(orgId);
    hafOperateLogDAO.insert(hafOperateLog);
    } catch(BusinessException e) {
      throw e;
    }
  }
  public MonthpayJYAF findPringInfo(String paymentId) throws Exception {
    MonthpayJYAF f = new MonthpayJYAF();
    String [] chgTypeAdd={"1","3"};
    String [] chgTypeLess={"4"};
    String [] payStatus={"1","3","4"};
    MonthPayment monthPayment = monthPaymentDAO.queryById(new Integer(paymentId.toString()));
    List list1 = monthPaymentHeadDAO.queryMonthPaymentHeadLJ(paymentId);
    MonthPaymentHead monthPaymentHead = (MonthPaymentHead)list1.get(0);
    MonthPaymentHead monthPaymentHead1 = (MonthPaymentHead)list1.get(list1.size()-1);
    MonthPaymentHead monthPaymentHeadMX=null;
    String month1=monthPaymentHead.getPayMonth();
    String month2=monthPaymentHead1.getPayMonth();
    Serializable orgid = monthPayment.getOrg().getId();
    String noteNum = monthPayment.getNoteNum();
    BigDecimal payMoney = new BigDecimal(0.00);
    payMoney = monthPaymentDAO.getPayMoney_jj(noteNum, "D");//减掉挂账的钱
    //应缴总额
    //BigDecimal sumPay = new BigDecimal(0);
    //AA303职工实缴
    BigDecimal empRealPay =monthPaymentHead.getEmpSumpay(); //monthPaymentTailDAO.queryEmpRealPay(orgid, monthPaymentHead.getPayMonth());
    //单位实缴
    BigDecimal orgRealPay =monthPaymentHead.getOrgSumpay(); //monthPaymentTailDAO.queryOrgRealPay(orgid, monthPaymentHead.getPayMonth());
    //AA303职工应缴
    //BigDecimal empShouldPay = monthPaymentTailDAO.queryEmpShouldPayLJ(orgid, monthPaymentHead.getPayMonth());
    //单位应缴
    //BigDecimal orgShouldPay = monthPaymentTailDAO.queryOrgShouldPayLJ(orgid, monthPaymentHead.getPayMonth());
//  AA203职工增加金额（正为增加，负为减少）
    BigDecimal empPaymentMoney = chgPaymentTailDAO.queryEmpPayMoney(orgid,paymentId, payStatus);
    //AA203单位增加金额（正为增加，负为减少）
    BigDecimal orgPaymentMoney = chgPaymentTailDAO.queryOrgPayMoney(orgid, paymentId, payStatus);
    BigDecimal paymentMoney = empPaymentMoney.add(orgPaymentMoney);
    //AA201职工增加金额（正为增加，负为减少）
    BigDecimal empRateMoney = chgOrgRateDAO.queryEmpRateMoney(orgid,paymentId);
    //AA201单位增加金额（正为增加，负为减少）
    BigDecimal orgRateMoney = chgOrgRateDAO.queryOrgRateMoney(orgid,paymentId);
    BigDecimal rateMoney = empRateMoney.add(orgRateMoney);
    //单位应缴总额
    //BigDecimal orgPay = empDAO.queryOrgpay(orgid);
    //职工应缴总额
    //BigDecimal empPay = empDAO.queryEmppay(orgid);
    //本月人数
    //Integer personCounts = empDAO.queryEmpCount(orgid);
    Integer personCounts = monthPaymentTailDAO.queryPaymentPersonCountsMXLJ(monthPaymentHead.getId());
    //增加人数
    Integer personCountsAdd = chgPersonTailDAO.queryPersonCount(orgid, paymentId, chgTypeAdd);
    //减少人数
    Integer personCountsLess = chgPersonTailDAO.queryPersonCount(orgid, paymentId, chgTypeLess);
    //上月人数
    //Integer ultimoPersonCounts = new Integer(personCounts.intValue()+personCountsLess.intValue()-personCountsAdd.intValue());
    Integer ultimoPersonCounts=null;
    //本月金额
  //  BigDecimal payMoney = this.getSumPay(orgid);
    //增加金额AA205状态为13
    BigDecimal empPayMoneyAdd = chgPersonTailDAO.queryEmpPersonMoney(orgid,paymentId, chgTypeAdd);
    BigDecimal orgPayMoneyAdd = chgPersonTailDAO.queryOrgPersonMoney(orgid,paymentId, chgTypeAdd);
    BigDecimal payMoneyAdd = empPayMoneyAdd.add(orgPayMoneyAdd);
    //减少金额AA205状态为4
    BigDecimal empPayMoneyLess = chgPersonTailDAO.queryEmpPersonMoney(orgid,paymentId, chgTypeLess);
    BigDecimal orgPayMoneyLess = chgPersonTailDAO.queryOrgPersonMoney(orgid,paymentId, chgTypeLess);
    BigDecimal payMoneyLess = empPayMoneyLess.add(orgPayMoneyLess);
    //上月金额 
    //先找年月小于代入年月的AA302中的年月
    String monthMX = monthPaymentHeadDAO.queryMaxMonthMnothpayMX(orgid, month1);
    BigDecimal ultimoPayMoney=null;
    if(monthMX == null){
      ultimoPayMoney=new BigDecimal(0.00);
      ultimoPersonCounts=new Integer(0);
    }else{
      //根据此年月查询AA302
      List headlist=monthPaymentHeadDAO.queryMnothpaymentHeadMX(orgid,monthMX);
      if(headlist != null){
        monthPaymentHeadMX=(MonthPaymentHead)headlist.get(0);
        ultimoPersonCounts=monthPaymentHeadMX.getPersonCount();
      }else{
        ultimoPayMoney=new BigDecimal(0.00);
        ultimoPersonCounts=new Integer(0);        
      }
    }
    //  BigDecimal ultimoPayMoney = monthPayment.getPayMoney().add(payMoneyLess).subtract(payMoneyAdd);
    if(empRealPay.doubleValue() >0 && orgRealPay.doubleValue()>0){
      //sumPay = orgRealPay.add(empRealPay);
      if(empPaymentMoney.doubleValue()>0){
        payMoneyAdd = payMoneyAdd.add(empPaymentMoney);
      }else if(orgPaymentMoney.doubleValue()<0){
          orgPaymentMoney = orgPaymentMoney.multiply(new BigDecimal(-1));
          payMoneyLess = payMoneyLess.add(orgPaymentMoney);
        }
      if(orgPaymentMoney.doubleValue()>0){
        payMoneyAdd = payMoneyAdd.add(orgPaymentMoney);
      }else if(empPaymentMoney.doubleValue()<0){
        empPaymentMoney = empPaymentMoney.multiply(new BigDecimal(-1));
        payMoneyLess = payMoneyLess.add(empPaymentMoney);
      }
      if(empRateMoney.doubleValue()>0){
        payMoneyAdd = payMoneyAdd.add(empRateMoney);
      }else  if(empRateMoney.doubleValue()<0){
          empRateMoney = empRateMoney.multiply(new BigDecimal(-1));
          payMoneyLess = payMoneyLess.add(empRateMoney);
        }
      if(orgRateMoney.doubleValue()>0){
        payMoneyAdd = payMoneyAdd.add(orgRateMoney);
      }else if(orgRateMoney.doubleValue()<0){
          orgRateMoney = orgRateMoney.multiply(new BigDecimal(-1));
          payMoneyLess = payMoneyLess.add(orgRateMoney);
        }
      //上月金额
   //   System.out.println("monthMX----"+monthMX);
      if(monthMX == null){
        ultimoPayMoney=new BigDecimal(0.00);
        ultimoPersonCounts=new Integer(0);
      }else{
        ultimoPayMoney=monthPaymentTailDAO.queryMnothpaymentTailMXAll(monthPaymentHeadMX.getId());
      }
      //本月金额
      f.setPayMoney(empRealPay.add(orgRealPay).divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
      f.setPayMoneyAdd(payMoneyAdd.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
      f.setPayMoneyLess(payMoneyLess.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
      f.setInceptMonth(month1);
      f.setPayMonth(month2);
      f.setPersonCounts(personCounts);
      f.setUltimoPayMoney(ultimoPayMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
      f.setPayStatus(1);
    }else{
      if(empRealPay.doubleValue()>0){
        //sumPay = empRealPay;
        if(empPaymentMoney.doubleValue()>=0){
          empPayMoneyAdd = empPayMoneyAdd.add(empPaymentMoney);
        }else{
          empPaymentMoney = empPaymentMoney.multiply(new BigDecimal(-1));
          empPayMoneyLess = empPayMoneyLess.add(empPaymentMoney);
        }
        if(empRateMoney.doubleValue()>=0){
          empPayMoneyAdd = empPayMoneyAdd.add(empRateMoney);
        }else{
          empRateMoney = empRateMoney.multiply(new BigDecimal(-1));
          empPayMoneyLess = empPayMoneyLess.add(empRateMoney);
        }
        if(monthMX == null){
          ultimoPayMoney=new BigDecimal(0.00);
          ultimoPersonCounts=new Integer(0);
        }else{
          ultimoPayMoney = monthPaymentTailDAO.queryMnothpaymentTailMXEmp(monthPaymentHeadMX.getId());
        }
        //本月金额
        f.setPayMoney(empRealPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
        f.setPayMoneyAdd(empPayMoneyAdd.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
        f.setPayMoneyLess(empPayMoneyLess.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
        f.setInceptMonth(month1);
        f.setPayMonth(month2);
        f.setPersonCounts(personCounts);
        f.setUltimoPayMoney(ultimoPayMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
        f.setPayStatus(3);
      }else if(orgRealPay.doubleValue()>0){
        //sumPay = orgRealPay;
        if(orgPaymentMoney.doubleValue()>=0){
          orgPayMoneyAdd = orgPayMoneyAdd.add(orgPaymentMoney);
        }else{
          orgPaymentMoney = orgPaymentMoney.multiply(new BigDecimal(-1));
          orgPayMoneyLess = orgPayMoneyLess.add(orgPaymentMoney);
        }
        if(orgRateMoney.doubleValue()>=0){
          orgPayMoneyAdd = orgPayMoneyAdd.add(orgRateMoney);
        }else{
          orgRateMoney = orgRateMoney.multiply(new BigDecimal(-1));
          orgPayMoneyLess = orgPayMoneyLess.add(orgRateMoney);
        }if(monthMX == null){
          ultimoPayMoney=new BigDecimal(0.00);
          ultimoPersonCounts=new Integer(0);
        }else{
          ultimoPayMoney = monthPaymentTailDAO.queryMnothpaymentTailMXOrg(monthPaymentHeadMX.getId());
        }
        //本月金额
        f.setPayMoney(orgRealPay.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
        f.setPayMoneyAdd(orgPayMoneyAdd.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
        f.setPayMoneyLess(orgPayMoneyLess.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
        f.setInceptMonth(month1);
        f.setPayMonth(month2);
        f.setPersonCounts(personCounts);
        f.setUltimoPayMoney(ultimoPayMoney.divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
        f.setPayStatus(2);
      }
    }
    if(monthPayment.getOrg().getOrgInfo().getReserveaB()!=null && !monthPayment.getOrg().getOrgInfo().getReserveaB().equals("")){
      f.setName(monthPayment.getOrg().getOrgInfo().getReserveaB());
    }else{
      f.setName(monthPayment.getOrg().getOrgInfo().getName());
    }
    f.setPersonCountsAdd(personCountsAdd);
    f.setPersonCountsLess(personCountsLess);
    f.setUltimoPersonCounts(ultimoPersonCounts);
    f.setSumPay(monthPayment.getPayMoney().divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
    f.setRealPay(monthPayment.getPayMoney().divide(new BigDecimal(1),2,BigDecimal.ROUND_HALF_DOWN));
    f.setOrgid(monthPayment.getOrg().getId().toString());
    f.setNoteNum(monthPayment.getDocNum());
    f.setPaymentid(paymentId);
    
    f.setPayment_bank_acc(monthPayment.getPayment_bank_acc());
    f.setPayment_bank_name(monthPayment.getPayment_bank_name());
    f.setPayment_orgname(f.getName());
    
    f.setReceivables_bank_acc(monthPayment.getReceivables_bank_acc());
    f.setReceivables_bank_name(monthPayment.getReceivables_bank_name());
    f.setReceivables_orgname(this.queryCeterName(monthPayment.getOrg().getOrgInfo().getOfficecode(), monthPayment.getReceivables_bank_acc(),monthPayment.getOrg().getOrgInfo().getCollectionBankId()));
    
    f.setPayWay(monthPayment.getPay_way());
    f.setPayKind(monthPayment.getPay_kind());
    
    f.setDocNum(monthPayment.getDocNum());
    if(payMoney != null){
      f.setSumPay(f.getSumPay().add(payMoney));
      f.setRealPay(f.getRealPay().add(payMoney));
      f.setPayMoney(f.getPayMoney().add(payMoney));
    }
//    List empTailList = monthPaymentTailDAO.queryPaymentTailListLJ(monthPayment.getId());
    List returnlist=new ArrayList();
//    for(int i=0;i<empTailList.size();i++){
//      MonthPaymentTail m = (MonthPaymentTail)empTailList.get(i);
//      Emp emp=empDAO.getChgPersonEmp_WL(orgid.toString(), m.getEmpId().toString());
//      m.setSex(BusiTools.getBusiValue(Integer.parseInt(emp.getEmpInfo().getSex().toString()), BusiConst.SEX));
//      m.setEmp(emp);
//      m.setSumPay(m.getEmpRealPay().add(m.getOrgRealPay()));
//      returnlist.add(m);
//    }
    f.setList(returnlist);
    return f;
  }
  public List findTaillistMX(Pagination pagination)throws Exception{
    List list = new ArrayList();
    List list1 = new ArrayList();
    String paymentid =(String)pagination.getQueryCriterions().get("paymentid");
    String orderBy=(String) pagination.getOrderBy();;
    String order = (String) pagination.getOrderother(); 
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize(); 
    MonthPayment monthPayment = monthPaymentDAO.queryById(new Integer(paymentid));
    list = monthPaymentTailDAO.queryPaymentTailListMXLJ(paymentid, orderBy, order, start, pageSize);
    if(list.size()>0){
      for(int i=0;i<list.size();i++){
        MonthpayTbWindowDto m = (MonthpayTbWindowDto)list.get(i);
        Emp emp = empDAO.queryByCriterions(m.getEmpid().toString(), monthPayment.getOrg().getId().toString());
        m.setEmpStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
        list1.add(m);
      }
    }
    int count = monthPaymentTailDAO.queryPaymentTailCountMXLJ(paymentid);
    pagination.setNrOfElements(count);
    return list1;
  }
  /**
   * 导出单位补缴信息
   */
  public List findTaillistMXExport(Pagination pagination,String[] orderby)throws Exception{
    List list = new ArrayList();
    List list1 = new ArrayList();
    String paymentid =(String)pagination.getQueryCriterions().get("paymentid");
    MonthPayment monthPayment = monthPaymentDAO.queryById(new Integer(paymentid));
    list = monthPaymentTailDAO.queryPaymentTailListMXExport_jj(paymentid,orderby);
    if(list.size()>0){
      for(int i=0;i<list.size();i++){
        MonthpayTbWindowDto m = (MonthpayTbWindowDto)list.get(i);
        AddpayInfoDto addpayInfoDto = new AddpayInfoDto();
        Emp emp = empDAO.queryByCriterions(m.getEmpid().toString(), monthPayment.getOrg().getId().toString());
        m.setEmpStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
        addpayInfoDto.setOrgid(emp.getOrg().getId().toString());
        addpayInfoDto.setOrgName(emp.getOrg().getOrgInfo().getName());
        addpayInfoDto.setAddpayMonth(m.getPayMonth().substring(7,m.getPayMonth().length()));
        addpayInfoDto.setAddStartPayMonth(m.getPayMonth().substring(0,6));
        addpayInfoDto.setEmpAddpayMoney("");
        addpayInfoDto.setEmpId(emp.getEmpId().toString());
        addpayInfoDto.setEmpName(emp.getEmpInfo().getName());
        addpayInfoDto.setEmpShouldpay(m.getEmppay().toString());
        addpayInfoDto.setNoteNum("");
        addpayInfoDto.setOrgAddpayMoney("");
        addpayInfoDto.setOrgShouldpay(m.getOrgpay().toString());
        addpayInfoDto.setEmpPayStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
        addpayInfoDto.setSalaryBase(m.getSalaryBase().toString());
        addpayInfoDto.setOrgRate(m.getOrgRate().toString());
        addpayInfoDto.setEmpRate(m.getEmpRate().toString());
        list1.add(addpayInfoDto);
      }
    }

    return list1;
  }
  public List findTaillistMXPrint(Pagination pagination)throws Exception{
    List list = new ArrayList();
    List list1 = new ArrayList();
    String paymentid =(String)pagination.getQueryCriterions().get("paymentid");
    String orderBy=(String) pagination.getOrderBy();;
    String order = (String) pagination.getOrderother(); 
  //  String maker=this.queryMaker(paymentid);
    MonthPayment monthPayment = monthPaymentDAO.queryById(new Integer(paymentid));
    list = monthPaymentTailDAO.queryPaymentTailListMXPrintLJ(paymentid, orderBy, order);
    if(list.size()>0){
      for(int i=0;i<list.size();i++){
        MonthpayTbWindowDto m = (MonthpayTbWindowDto)list.get(i);
        if(i==0)
        {
          m.setCollectionBank( orgDAO.findCollBanknameByOrgInfoCollectionBankId(monthPayment.getOrg().getOrgInfo().getCollectionBankId()));
         // m.setMaker(maker);
        }
        Emp emp = empDAO.queryByCriterions(m.getEmpid().toString(), monthPayment.getOrg().getId().toString());
        m.setOrgid(monthPayment.getOrg().getId().toString());
        m.setOrgname(monthPayment.getOrg().getOrgInfo().getName());
        m.setEmpStatus(BusiTools.getBusiValue(Integer.parseInt(emp.getPayStatus().toString()), BusiConst.OLDPAYMENTSTATE));
        list1.add(m);
      }
    }
    return list1;
  }
  public List findTailTotal(String paymentid) throws Exception{
    List list = null;
    list = monthPaymentTailDAO.queryPaymentTailListMXHJLJ(paymentid);
    return list;
    
  }
  public String[] queryOfficeBankNames(String orgId, String openStatus,
      String bizId, String bizType, SecurityInfo securityInfo) throws Exception {
    // TODO Auto-generated method stub
    // 查询办事处银行开始
    String officeName = "";
    String bankName = "";
    String str[]=new String[2];
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
        .queryByBizId_wsh(bizId, bizType);
    if (orgHAFAccountFlow != null) {
      if (orgHAFAccountFlow.getOfficeCode() != null) {
        try {
          OrganizationUnit organizationUnit = new OrganizationUnit();
          organizationUnit = organizationUnitDAO.queryById(orgHAFAccountFlow
              .getOfficeCode());
          if (organizationUnit != null) {
            if (organizationUnit.getName() != null) {
              officeName = organizationUnit.getName();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

      }
      if (orgHAFAccountFlow.getMoneyBank() != null) {
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(orgHAFAccountFlow.getMoneyBank());
        bankName = collBank.getCollBankName();
      }
    } else {
      Org org = null;
      String orgid = "";
      if (orgId != null) {
        orgid = orgId;
      }
      org = orgDAO.queryByCriterions(orgid, "2", null, securityInfo);
      if (org == null && orgid != null) {
        org = new Org();
      }
      if (org.getOrgInfo().getOfficecode() != null) {
        try {
          OrganizationUnit organizationUnit = new OrganizationUnit();
          organizationUnit = organizationUnitDAO.queryById(org.getOrgInfo()
              .getOfficecode());
          if (organizationUnit != null) {
            if (organizationUnit.getName() != null) {
              officeName = organizationUnit.getName();
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (org.getOrgInfo().getCollectionBankId() != null) {
        CollBank collBank = collBankDAO
            .getCollBankByCollBankid(org.getOrgInfo().getCollectionBankId());
        bankName = collBank.getCollBankName();
      }
    }
    //查询办事处银行结束
    str[0]=officeName;
    str[1]=bankName;
    return str;
  }

  public BigDecimal queryOverPay(String orgId) throws Exception{
    BigDecimal overPay = orgHAFAccountFlowDAO.queryOverPay(orgId);
    if (overPay==null) {
      overPay = new BigDecimal(0.00);
    }
    return overPay;
  }
  
  public boolean isOverPay(String orgId) throws Exception{
    boolean flag = true;
    List list = orgHAFAccountFlowDAO.queryIsOverPay(orgId);
    if (list.size()>0) {
      flag = false;
    }else{
      flag = true;
    }
    return flag;
  }
  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }
////查询复核人和记账人
//  public String[] queryCheckkNames(String bizId, String bizType) throws Exception {
//    // TODO Auto-generated method stub
//    String checkPerson = "";
//    String clearPerson = "";
//    String str[]=new String[2];
//    List list=new ArrayList();
//    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO
//        .queryByBizId_wsh1(bizId, bizType);
//    if (orgHAFAccountFlow != null) {
//      
//        try {
//          list=monthPaymentBizActivityLogDAO.queryMonthPaymentLogByPayHeadId(bizId,"A");
//          if(list.size()==1){
//            MonthPaymentBizActivityLog monthPaymentBizActivityLog=new MonthPaymentBizActivityLog();
//            monthPaymentBizActivityLog=(MonthPaymentBizActivityLog)list.get(0);
//            checkPerson=monthPaymentBizActivityLog.getOperator();
//          }
//          if(list.size()==2){
//            MonthPaymentBizActivityLog monthPaymentBizActivityLog=new MonthPaymentBizActivityLog();
//            monthPaymentBizActivityLog=(MonthPaymentBizActivityLog)list.get(0);
//            checkPerson=monthPaymentBizActivityLog.getOperator();
//            monthPaymentBizActivityLog=(MonthPaymentBizActivityLog)list.get(1);
//            clearPerson=monthPaymentBizActivityLog.getOperator();
//          }
//          
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//    } 
//    str[0]=checkPerson;
//    str[1]=clearPerson;
//    return str;
//  }

  public List queryPaymentBankNameList (Integer orgid) throws Exception
  {
    List list=new ArrayList();
    list=monthPaymentHeadDAO.queryPaymentBankNameList(orgid);
    return list;
    
  }
  
  public List queryCollBankInfo(String officecode,String bankid) throws Exception
  {
    List list=new ArrayList();
    list=monthPaymentHeadDAO.queryCollectionBankInfo(officecode, bankid);
    return list;
  }
  
  public String queryPaymentBankName(String id)  throws Exception
  {
    String bankname="";
    bankname=monthPaymentHeadDAO.queryPaymentBankName(new Integer(id));
    return bankname;
    
  }
  
  public Integer queryPayCount(Integer monthPayHeadid)
  {
    Integer tempCount= new Integer(0);
    tempCount=monthPaymentTailDAO.queryPaymentPersonCountsYQF(monthPayHeadid);
    return tempCount;
  }
  
  
  public String queryCeterName(String officecode,String bankCode,String collbankid) throws Exception
  {
    String officename=monthPaymentHeadDAO.queryCentername(officecode, bankCode,collbankid);
    return officename;
    
  }
  
  public String queryMaker(String monthPaymentHeadid)  throws Exception
  {
    String maker="";
    maker=monthPaymentHeadDAO.queryMothPaymentMaker(monthPaymentHeadid);
    return maker;
  }
  
  public String queryMakerPara() throws Exception
  {
    String name="";
    name=monthPaymentDAO.getNamePara();
    return name;
    
  }
  public void setSearchLackInfoDAO(SearchLackInfoDAO searchLackInfoDAO) {
    this.searchLackInfoDAO = searchLackInfoDAO;
  }

  public void setOrgExcessPaymentBizActivityLogDAO(
      OrgExcessPaymentBizActivityLogDAO orgExcessPaymentBizActivityLogDAO) {
    this.orgExcessPaymentBizActivityLogDAO = orgExcessPaymentBizActivityLogDAO;
  }
  public MonthpayJYAF findPayInfo(String paymentId) throws Exception {
    MonthpayJYAF f = new MonthpayJYAF();
    MonthPayment monthPayment = monthPaymentDAO.queryById(new Integer(paymentId.toString()));
    f.setPayment_bank_acc(monthPayment.getPayment_bank_acc());
    f.setPayment_bank_name(monthPayment.getPayment_bank_name());
    f.setOrgid(monthPayment.getOrg().getId().toString());
    f.setName(monthPayment.getOrg().getOrgInfo().getName());
    f.setMonthpayHeadId(monthPayment.getId().toString());
    return f;
  }
  public void updatePaymentInfo(Serializable paymentId,String bankName,String bankAcc,SecurityInfo securityInfo) throws Exception{
    //删除301
    MonthPayment monthPayment = monthPaymentDAO.queryById(new Integer(paymentId.toString()));
    monthPayment.setPayment_bank_name(bankName);
    monthPayment.setPayment_bank_acc(bankAcc);
  }
}