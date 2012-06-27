package org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.business;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckDetailAF;
import org.xpup.hafmis.syscollection.common.dao.AdjustWrongAccountHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.BizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.ChangeAccountBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpAddPayBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.MonthPaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.OfficeParaDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgAddPayBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgExcessPaymentBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgExcessPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowExcessPaymentDAO;
import org.xpup.hafmis.syscollection.common.dao.PaymentHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.PickBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.PickHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInHeadDAO;
import org.xpup.hafmis.syscollection.common.dao.TranInTailDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutBizActivityLogDAO;
import org.xpup.hafmis.syscollection.common.dao.TranOutHeadDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.BizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowExcessPaymentBalanceEndTransfer;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlowPayment;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.bsinterface.IBusinesslogsearchBS;
import org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.dto.BusinesslogsearchDTO;
import org.xpup.hafmis.syscommon.dao.HafOperateLogDAO;
import org.xpup.security.common.dao.UserDAO;

public class BusinesslogsearchBS implements  IBusinesslogsearchBS{
  
  private BizActivityLogDAO bizActivityLogDAO=null;
  
  private UserDAO userDAO=null;
  
  private TranInTailDAO tranInTailDAO = null;
  
  private OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO=null;
  
  private OrgExcessPaymentDAO orgExcessPaymentDAO=null;
  
  private OrgHAFAccountFlowExcessPaymentDAO orgHAFAccountFlowExcessPaymentDAO=null;
 
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;

  private EmpHAFAccountFlowDAO empHAFAccountFlowDAO = null;

  private PaymentHeadDAO paymentHeadDAO = null;

  private PickHeadDAO pickHeadDAO = null;

  private TranOutHeadDAO tranOutHeadDAO = null;

  private TranInHeadDAO tranInHeadDAO = null;

  private AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO = null;

  private HafOperateLogDAO hafOperateLogDAO = null;

  private PickBizActivityLogDAO pickBizActivityLogDAO = null;

  private TranOutBizActivityLogDAO tranOutBizActivityLogDAO = null;

  private TranInBizActivityLogDAO tranInBizActivityLogDAO = null;

  private ChangeAccountBizActivityLogDAO changeAccountBizActivityLogDAO = null;

  private MonthPaymentBizActivityLogDAO monthPaymentBizActivityLogDAO = null;

  private OrgAddPayBizActivityLogDAO orgAddPayBizActivityLogDAO = null;

  private OrgExcessPaymentBizActivityLogDAO orgExcessPaymentBizActivityLogDAO = null;

  private EmpDAO empDAO = null; 

  private CollBankDAO collBankDAO = null;

  private EmpAddPayBizActivityLogDAO empAddPayBizActivityLogDAO = null;
  
  private MonthPaymentHeadDAO monthPaymentHeadDAO = null;
  
  private OfficeParaDAO officeParaDAO=null;
  
  private OrgDAO orgDAO=null;

  public BizActivityLogDAO getBizActivityLogDAO() {
    return bizActivityLogDAO;
  }

  public void setBizActivityLogDAO(BizActivityLogDAO bizActivityLogDAO) {
    this.bizActivityLogDAO = bizActivityLogDAO;
  }

  public UserDAO getUserDAO() {
    return userDAO;
  }

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }
  
  public List findLogFlowList(Pagination pagination, SecurityInfo securityInfo) throws Exception{
    List list=new ArrayList();
    String bizType=(String)pagination.getQueryCriterions().get("bizType");
    String beginMonth=(String)pagination.getQueryCriterions().get("beginMonth");
    String endMonth=(String)pagination.getQueryCriterions().get("endMonth");
    String operator=(String)pagination.getQueryCriterions().get("operator");
    String payStatus=(String)pagination.getQueryCriterions().get("payStatus");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
//    if((!bizType.equals("")&&bizType!=null)||(!operator.equals(""))||(!payStatus.equals(""))){
    if(beginMonth!=null&&(!beginMonth.equals(""))&&endMonth!=null&&(!endMonth.equals(""))){
    list=bizActivityLogDAO.queryBizActivityLog(bizType, operator,payStatus,beginMonth, endMonth, orderBy, order, start, pageSize,securityInfo);
    for(int i=0;i<list.size();i++){
      BusinesslogsearchDTO  dto=(BusinesslogsearchDTO)list.get(i);
      dto.setBizType(BusiTools.getBusiValue_WL(dto.getBizType(),BusiConst.BUSINESSLOGSEARCH));
      dto.setPayStatus(BusiTools.getBusiValue_WL(dto.getPayStatus(),BusiConst.BUSINESSLOGSTATE));      
    }
    int count=0;
    count=bizActivityLogDAO.queryBizActivityLogCount(bizType,operator,payStatus, beginMonth, endMonth,securityInfo).size();
    pagination.setNrOfElements(count);
    }
    return list;
  }
  
  public List findAllLogFlowList(Pagination pagination, SecurityInfo securityInfo) throws Exception{
    List list=new ArrayList();
    String bizType=(String)pagination.getQueryCriterions().get("bizType");
    String beginMonth=(String)pagination.getQueryCriterions().get("beginMonth");
    String endMonth=(String)pagination.getQueryCriterions().get("endMonth");
    String operator=(String)pagination.getQueryCriterions().get("operator");
    String payStatus=(String)pagination.getQueryCriterions().get("payStatus");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
//    if((!bizType.equals("")&&bizType!=null)||(!operator.equals(""))||(!payStatus.equals(""))){

    list=bizActivityLogDAO.queryBizActivityLogCount(bizType,operator,payStatus, beginMonth, endMonth,securityInfo);
    for(int i=0;i<list.size();i++){
      BusinesslogsearchDTO  dto=(BusinesslogsearchDTO)list.get(i);
      dto.setBizType(BusiTools.getBusiValue_WL(dto.getBizType(),BusiConst.BUSINESSLOGSEARCH));
      dto.setPayStatus(BusiTools.getBusiValue_WL(dto.getPayStatus(),BusiConst.BUSINESSLOGSTATE));      
    }
 
    
    return list;
  }
  
  public List findAllUser(Pagination pagination, SecurityInfo securityInfo) throws Exception{
    List list=this.userDAO.findAllUser();
    return list;
  }
  public BizcheckDetailAF findOrgHAFAccountFlowByID(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String bizId = (String) pagination.getQueryCriterions().get("headid");
    String bizType=(String)pagination.getQueryCriterions().get("bizType");
    String headid="";
//    String businessType="";
//    if(bizType.equals("汇缴"))
//      businessType="A";
//    if(bizType.equals("单位补缴"))
//      businessType="M";
//    if(bizType.equals("挂账"))
//      businessType="C";
//    if(bizType.equals("提取"))
//      businessType="D";
//    if(bizType.equals("转出"))
//      businessType="E";
//    if(bizType.equals("转入"))
//      businessType="F";
//    if(bizType.equals("结息"))
//      businessType="H";
    String empId = "";
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    headid=orgHAFAccountFlowDAO.headId(new Integer(bizId), bizType);
    OrgHAFAccountFlow orgHAFAccountFlow = orgHAFAccountFlowDAO.queryById(new Integer(headid + ""));
    BizActivityLog bizActivityLog = new BizActivityLog();
    String idd = orgHAFAccountFlow.getBizId().toString();
    
    String traninId = orgHAFAccountFlow.getOrg().getId().toString();
    String traninName = orgHAFAccountFlow.getOrg().getOrgInfo().getName();
    String biz_type = orgHAFAccountFlow.getBizType();
    String type = "0";// 默认为其他形式的
    String settDate = "";
    BizcheckDetailAF bizcheckDetailAF = new BizcheckDetailAF();
    List list = null;
    list = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowTailList(headid, orderBy,
        order, start, pageSize);

 
    TranInTail tranInTail = null;
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {// 可能出现的问题 员工不在该单位下
        EmpHAFAccountFlow empHAFAccountFlow = (EmpHAFAccountFlow) list.get(i);
         empId = empHAFAccountFlow.getEmpId()
            .toString();
   
         Emp  emp = empDAO.queryByCriterions(empHAFAccountFlow.getEmpId()
            .toString(), orgHAFAccountFlow.getOrg().getId().toString());
        empHAFAccountFlow.setEmp(emp);
        if (!empHAFAccountFlow.getCredit().toString().equals("0")) {
          empHAFAccountFlow.setMoney(empHAFAccountFlow.getCredit());
        } else if (!empHAFAccountFlow.getDebit().toString().equals("0")) {
          empHAFAccountFlow.setMoney(empHAFAccountFlow.getDebit());
        } else {
          empHAFAccountFlow.setMoney(new BigDecimal(0.00));
        }
      }
    }
      if("汇缴".equals(biz_type)){
        type = "5";
        List lists = new ArrayList();
        String monthstr = "";
        String monthend = "";
        lists=monthPaymentHeadDAO.getPayMonthyqf(orgHAFAccountFlow.getBizId().toString());
  
        for(int i=0;i<lists.size();i++){
          Object[] obj=(Object[])lists.get(0);
          monthstr = obj[0].toString();
          monthend = obj[1].toString();
        }
        settDate = monthstr+"-"+monthend;
        
      }
      if("单位补缴".equals(biz_type)){
        type = "6";
        List lists = new ArrayList();
        String monthstr = "";
        String monthend = "";
        lists=monthPaymentHeadDAO.getPayMonthbyyqf(orgHAFAccountFlow.getBizId().toString());
        for(int i=0;i<lists.size();i++){
          Object[] obj=(Object[])lists.get(0);
          monthstr = obj[0].toString();
          monthend = obj[1].toString();
        }
        settDate = monthstr+"-"+monthend;
      }
    if ("转出".equals(biz_type)) {// 转出
      type = "1";
      String id = orgHAFAccountFlow.getBizId().toString();
      TranOutHead tranOutHead = tranOutHeadDAO.queryById(new Integer(id));
     
//      TranInHead tranInHead = tranInHeadDAO
//          .queryTranInOrgIdByOutorgId(tranOutHead.getTranOutOrg().getId()
//              .toString());
//      if (tranInHead != null) {
//        bizcheckDetailAF.setTraninId(tranOutHead.getTranInOrg().getId() + "");
//        bizcheckDetailAF.setTraninName(tranOutHead.getTranInOrg().getOrgInfo()
//            .getName());
//      }
      bizcheckDetailAF.setTranoutId(tranOutHead.getTranOutOrg().getId() + "");
      bizcheckDetailAF.setTranoutName(tranOutHead.getTranOutOrg().getOrgInfo()
          .getName());
      bizcheckDetailAF.setList(list);
    } 
    else if ("转入".equals(biz_type)) {// 转入
      type = "2";
      bizcheckDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      bizcheckDetailAF.setTraninName(orgHAFAccountFlow.getOrg().getOrgInfo()
          .getName());
      //orderBy
      list = tranInTailDAO.queryTraninListByCriterionsAll_yqf(empId, null, order, start, securityInfo);
      if(list.size()!=0){
        for(int i=0;i<list.size();i++){
          tranInTail = (TranInTail)list.get(i);
          BigDecimal money = tranInTail.getPreBalance().add(tranInTail.getCurBalance());
          tranInTail.setReserveaA(money.toString());
          BigDecimal interest = tranInTail.getPreInterest().add(tranInTail.getCurInterest());
          tranInTail.setReserveaB(interest.toString());
        }
      }
      bizcheckDetailAF.setList(list);
    } 
    else if ("提取".equals(biz_type)) {// 提取
      type = "3";
      bizcheckDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      bizcheckDetailAF.setTraninName(orgHAFAccountFlow.getOrg().getOrgInfo()
          .getName());
      bizcheckDetailAF.setList(list);
    } else if("挂账".equals(biz_type)){//挂账
      OrgExcessPayment orgExcessPayment = orgExcessPaymentDAO.queryById(new Integer(idd.toString()));
      Serializable orgId=orgExcessPayment.getOrg().getId().toString();
      BigDecimal banlance=new BigDecimal(0.00);
      banlance=this.queryOrgoverpayBalance(orgId);
//      bizcheckDetailAF.setOrgId(BusiTools.convertSixNumber(orgId.toString()));
//      bizcheckDetailAF.setUnitName(orgExcessPayment.getOrg().getOrgInfo().getName());
//      bizcheckDetailAF.setNoteNum(orgExcessPayment.getNoteNum());
      bizcheckDetailAF.setHeadid(headid);
      bizcheckDetailAF.setBanlance(banlance.toString());
      bizcheckDetailAF.setAmount(orgExcessPayment.getPayMoney().toString());
      bizcheckDetailAF.setReason(orgExcessPayment.getExcessReason());
      bizcheckDetailAF.setOtherList(list);
    
    } else{
      bizcheckDetailAF.setTraninId(orgHAFAccountFlow.getOrg().getId() + "");
      bizcheckDetailAF.setTraninName(orgHAFAccountFlow.getOrg().getOrgInfo()
          .getName());
      bizcheckDetailAF.setOtherList(list);
    }
   
//   if(biz_type.equals("补缴")){
//     bizActivityLog = orgHAFAccountFlowDAO.queryOperatorByBizId_(orgHAFAccountFlow.getBizId() + "");
//    }else if(biz_type.equals("单位补缴")){
//     bizActivityLog = orgHAFAccountFlowDAO.queryOperatorByBizIdOrgAdd(orgHAFAccountFlow.getBizId() + "");
//    }else{
//     bizActivityLog = orgHAFAccountFlowDAO.queryOperatorByBizId(orgHAFAccountFlow.getBizId() + "");
//   }

    List lists = orgHAFAccountFlowDAO.queryOrgHAFAccountFlowTailList_(headid);
    pagination.setNrOfElements(lists.size());
    // clearAccountDetailAF.setHeadid(headid);
    bizcheckDetailAF.setDocNum(orgHAFAccountFlow.getDocNum());
    bizcheckDetailAF.setNoteNum(orgHAFAccountFlow.getNoteNum());
    bizcheckDetailAF.setType(type);
    bizcheckDetailAF.setTraninId(traninId);
    bizcheckDetailAF.setTraninName(traninName);
   // bizcheckDetailAF.setOperator(bizActivityLog.getOperator());securityInfo
    bizcheckDetailAF.setOperator(orgHAFAccountFlow.getReserveaA());
    String collBankid = orgHAFAccountFlow.getOrg().getOrgInfo().getCollectionBankId();
    CollBank collBank = collBankDAO.getCollBankByCollBankid_(collBankid);
    bizcheckDetailAF.setBank(collBank.getCollBankName());
    bizcheckDetailAF.setSettDate(settDate);
    // biz_type
    bizcheckDetailAF.setBiz_type(biz_type);
    bizcheckDetailAF.setList(list);
    bizcheckDetailAF.setOtherList(lists);

    return bizcheckDetailAF;
  }
//查询单位挂账余额
  public BigDecimal queryOrgoverpayBalance( Serializable orgid ) throws BusinessException {
    // TODO Auto-generated method stub
    BigDecimal orgoverpayBalance=new BigDecimal(0.00);
    if((orgid!=null)){
      List  list=orgHAFAccountFlowExcessPaymentDAO.queryOrgoverpayHAFAccountFlow(orgid, new Integer(5));
      for(int i=0;i<list.size();i++){
        OrgHAFAccountFlowExcessPayment orgHAFAccountFlowExcessPayment=(OrgHAFAccountFlowExcessPayment)list.get(i);
        orgoverpayBalance=orgoverpayBalance.add(orgHAFAccountFlowExcessPayment.getCredit().subtract(orgHAFAccountFlowExcessPayment.getDebit()));       
      }
      List  lists=orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO.orgHAFAccountFlowExcessPaymentBalanceEndTransfer(orgid, new Integer(5)); 
      for(int i=0;i<lists.size();i++){
        OrgHAFAccountFlowExcessPaymentBalanceEndTransfer orgHAFAccountFlowExcessPaymentBalanceEndTransfer=(OrgHAFAccountFlowExcessPaymentBalanceEndTransfer)lists.get(i);
        orgoverpayBalance=orgoverpayBalance.add(orgHAFAccountFlowExcessPaymentBalanceEndTransfer.getCredit().subtract(orgHAFAccountFlowExcessPaymentBalanceEndTransfer.getDebit()));
      }
    }
    
    return orgoverpayBalance;
}

  public AdjustWrongAccountHeadDAO getAdjustWrongAccountHeadDAO() {
    return adjustWrongAccountHeadDAO;
  }

  public void setAdjustWrongAccountHeadDAO(
      AdjustWrongAccountHeadDAO adjustWrongAccountHeadDAO) {
    this.adjustWrongAccountHeadDAO = adjustWrongAccountHeadDAO;
  }

  public ChangeAccountBizActivityLogDAO getChangeAccountBizActivityLogDAO() {
    return changeAccountBizActivityLogDAO;
  }

  public void setChangeAccountBizActivityLogDAO(
      ChangeAccountBizActivityLogDAO changeAccountBizActivityLogDAO) {
    this.changeAccountBizActivityLogDAO = changeAccountBizActivityLogDAO;
  }

  public CollBankDAO getCollBankDAO() {
    return collBankDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public EmpAddPayBizActivityLogDAO getEmpAddPayBizActivityLogDAO() {
    return empAddPayBizActivityLogDAO;
  }

  public void setEmpAddPayBizActivityLogDAO(
      EmpAddPayBizActivityLogDAO empAddPayBizActivityLogDAO) {
    this.empAddPayBizActivityLogDAO = empAddPayBizActivityLogDAO;
  }

  public EmpDAO getEmpDAO() {
    return empDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public EmpHAFAccountFlowDAO getEmpHAFAccountFlowDAO() {
    return empHAFAccountFlowDAO;
  }

  public void setEmpHAFAccountFlowDAO(EmpHAFAccountFlowDAO empHAFAccountFlowDAO) {
    this.empHAFAccountFlowDAO = empHAFAccountFlowDAO;
  }

  public HafOperateLogDAO getHafOperateLogDAO() {
    return hafOperateLogDAO;
  }

  public void setHafOperateLogDAO(HafOperateLogDAO hafOperateLogDAO) {
    this.hafOperateLogDAO = hafOperateLogDAO;
  }

  public MonthPaymentBizActivityLogDAO getMonthPaymentBizActivityLogDAO() {
    return monthPaymentBizActivityLogDAO;
  }

  public void setMonthPaymentBizActivityLogDAO(
      MonthPaymentBizActivityLogDAO monthPaymentBizActivityLogDAO) {
    this.monthPaymentBizActivityLogDAO = monthPaymentBizActivityLogDAO;
  }

  public MonthPaymentHeadDAO getMonthPaymentHeadDAO() {
    return monthPaymentHeadDAO;
  }

  public void setMonthPaymentHeadDAO(MonthPaymentHeadDAO monthPaymentHeadDAO) {
    this.monthPaymentHeadDAO = monthPaymentHeadDAO;
  }

  public OfficeParaDAO getOfficeParaDAO() {
    return officeParaDAO;
  }

  public void setOfficeParaDAO(OfficeParaDAO officeParaDAO) {
    this.officeParaDAO = officeParaDAO;
  }

  public OrgAddPayBizActivityLogDAO getOrgAddPayBizActivityLogDAO() {
    return orgAddPayBizActivityLogDAO;
  }

  public void setOrgAddPayBizActivityLogDAO(
      OrgAddPayBizActivityLogDAO orgAddPayBizActivityLogDAO) {
    this.orgAddPayBizActivityLogDAO = orgAddPayBizActivityLogDAO;
  }

  public OrgDAO getOrgDAO() {
    return orgDAO;
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public OrgExcessPaymentBizActivityLogDAO getOrgExcessPaymentBizActivityLogDAO() {
    return orgExcessPaymentBizActivityLogDAO;
  }

  public void setOrgExcessPaymentBizActivityLogDAO(
      OrgExcessPaymentBizActivityLogDAO orgExcessPaymentBizActivityLogDAO) {
    this.orgExcessPaymentBizActivityLogDAO = orgExcessPaymentBizActivityLogDAO;
  }

  public OrgExcessPaymentDAO getOrgExcessPaymentDAO() {
    return orgExcessPaymentDAO;
  }

  public void setOrgExcessPaymentDAO(OrgExcessPaymentDAO orgExcessPaymentDAO) {
    this.orgExcessPaymentDAO = orgExcessPaymentDAO;
  }

  public OrgHAFAccountFlowDAO getOrgHAFAccountFlowDAO() {
    return orgHAFAccountFlowDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO getOrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO() {
    return orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO;
  }

  public void setOrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO(
      OrgHAFAccountFlowExcessPaymentBalanceEndTransferDAO orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO) {
    this.orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO = orgHAFAccountFlowExcessPaymentBalanceEndTransferDAO;
  }

  public OrgHAFAccountFlowExcessPaymentDAO getOrgHAFAccountFlowExcessPaymentDAO() {
    return orgHAFAccountFlowExcessPaymentDAO;
  }

  public void setOrgHAFAccountFlowExcessPaymentDAO(
      OrgHAFAccountFlowExcessPaymentDAO orgHAFAccountFlowExcessPaymentDAO) {
    this.orgHAFAccountFlowExcessPaymentDAO = orgHAFAccountFlowExcessPaymentDAO;
  }

  public PaymentHeadDAO getPaymentHeadDAO() {
    return paymentHeadDAO;
  }

  public void setPaymentHeadDAO(PaymentHeadDAO paymentHeadDAO) {
    this.paymentHeadDAO = paymentHeadDAO;
  }

  public PickBizActivityLogDAO getPickBizActivityLogDAO() {
    return pickBizActivityLogDAO;
  }

  public void setPickBizActivityLogDAO(PickBizActivityLogDAO pickBizActivityLogDAO) {
    this.pickBizActivityLogDAO = pickBizActivityLogDAO;
  }

  public PickHeadDAO getPickHeadDAO() {
    return pickHeadDAO;
  }

  public void setPickHeadDAO(PickHeadDAO pickHeadDAO) {
    this.pickHeadDAO = pickHeadDAO;
  }

  public TranInBizActivityLogDAO getTranInBizActivityLogDAO() {
    return tranInBizActivityLogDAO;
  }

  public void setTranInBizActivityLogDAO(
      TranInBizActivityLogDAO tranInBizActivityLogDAO) {
    this.tranInBizActivityLogDAO = tranInBizActivityLogDAO;
  }

  public TranInHeadDAO getTranInHeadDAO() {
    return tranInHeadDAO;
  }

  public void setTranInHeadDAO(TranInHeadDAO tranInHeadDAO) {
    this.tranInHeadDAO = tranInHeadDAO;
  }

  public TranInTailDAO getTranInTailDAO() {
    return tranInTailDAO;
  }

  public void setTranInTailDAO(TranInTailDAO tranInTailDAO) {
    this.tranInTailDAO = tranInTailDAO;
  }

  public TranOutBizActivityLogDAO getTranOutBizActivityLogDAO() {
    return tranOutBizActivityLogDAO;
  }

  public void setTranOutBizActivityLogDAO(
      TranOutBizActivityLogDAO tranOutBizActivityLogDAO) {
    this.tranOutBizActivityLogDAO = tranOutBizActivityLogDAO;
  }

  public TranOutHeadDAO getTranOutHeadDAO() {
    return tranOutHeadDAO;
  }

  public void setTranOutHeadDAO(TranOutHeadDAO tranOutHeadDAO) {
    this.tranOutHeadDAO = tranOutHeadDAO;
  }
}
