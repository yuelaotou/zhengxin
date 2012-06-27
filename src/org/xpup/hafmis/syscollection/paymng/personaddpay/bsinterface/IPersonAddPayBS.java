package org.xpup.hafmis.syscollection.paymng.personaddpay.bsinterface;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.AddPayTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.EmpAddPayBizActivityLog;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.PaymentHead;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayHeadPrintDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.EmpaddpayMaintainDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.dto.OrgInfoDTO;
import org.xpup.hafmis.syscollection.paymng.personaddpay.form.PersonAddPayAF;
import org.xpup.hafmis.syscommon.domain.entity.EmpInfo;
import org.xpup.hafmis.syscommon.domain.entity.HafOperateLog;

public interface IPersonAddPayBS {
  
  public List findPersonAddPayListByCriterions(Pagination pagination) throws Exception,BusinessException;
  public OrgInfoDTO findOrgInfoById(String orgId)throws Exception;
  public void deleteAddPayTail(Integer id,Integer paymentHeadId)throws BusinessException;
  public void deleteAll(Integer paymentHeadId)throws BusinessException;
  public List findEmpById(Integer empId,Integer  paymentHeadId) throws BusinessException;
  public EmpInfo findEmpInfoById(Integer empId) throws BusinessException;
  public List findEmpById_lg(Integer empId,Integer  orgId) throws BusinessException;
  public void addPersonAddPayTail(AddPayTail addPayTail) throws BusinessException;
  public List queryPaymentHead_lg(Integer paymentHeadId) throws BusinessException;
  public void addBiz_lg(EmpAddPayBizActivityLog empAddPayBizActivityLog) throws BusinessException;
  public void deletePaymentHead_lg(Integer paymentHeadId) throws BusinessException;
  public void deleteEmpAddPayBizActivityLog_lg(Integer paymentHeadId) throws BusinessException;
  public  PaymentHead queryById_lg(Integer id) throws BusinessException;
  public  void updatePaymentHead(PaymentHead paymentHead) throws BusinessException;
  public List modifyPersonAddPayBatch(List addPayImportList,Pagination pagination) throws Exception,BusinessException;
  public List findPaymentHeadById(String orgId)throws BusinessException;
  public void insertEmpAddPay(EmpAddPay empAddPay) throws Exception,BusinessException;
  public List findEmpaddpayList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  public EmpAddPay findEmpAddPayInfo(Serializable headId) throws Exception;
  public void addHafOperateLog_lg(HafOperateLog hafOperateLog_lg) throws BusinessException;
  public void insertHafOperateLog_lg(Pagination pagination) throws BusinessException;
  public List queryPersonAddPay(Pagination pagination) throws Exception,BusinessException;
  public EmpaddpayMaintainDTO findEmpaddpay(Pagination pagination) throws Exception,BusinessException;
  public List findEmpaddpayListPring(String paymentid) throws Exception;
  public EmpAddPay queryHead(Integer paymentHeadId) throws BusinessException;
  public  void updateHead(EmpAddPay empAddPay) throws BusinessException;
  public void insertHafOperateLog(HafOperateLog hafOperateLog) throws BusinessException;
  public EmpaddpayHeadPrintDTO findEmpaddpayPring(String paymentid) throws Exception;
  public Org findOrgById(Integer orgId,SecurityInfo securityInfo)  throws BusinessException;
  public EmpAddPay findPersonAddPay(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  public void deleteEmpaddpayList(Serializable tailId,Serializable headId,SecurityInfo securityInfo) throws Exception;
  public List findPaylist(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public List empaddPaylist(List addpayHeadImportList,List addpayListImportList,String orgid,String noteNum,SecurityInfo securityInfo,PersonAddPayAF personAddPayAF) throws Exception;
  public void overAddpay(Serializable empaddpayId,SecurityInfo securityInfo) throws Exception;
  public void overAddpay(Serializable tailId,Serializable headId,SecurityInfo securityInfo,String noteNum,Pagination pagination)throws Exception;
  public Emp findEmpInfo(String empId,String orgId)throws Exception;
  public void deleteHead(String paymentHeadId) throws BusinessException;
  public EmpAddPay findHeadId(String orgId) throws Exception;
  public void deleteAddpay(Serializable empaddpayId,SecurityInfo securityInfo) throws Exception;
  public void deleteAddpayDo(Serializable empaddpayId,SecurityInfo securityInfo) throws Exception;
  public void repealAddpay(Serializable empaddpayId,SecurityInfo securityInfo) throws Exception;
  public List findPersonAddPayList(Pagination pagination) throws Exception;
  public List findEmpaddpayListCount(Pagination pagination) throws Exception;
  public BigDecimal getEmpaddpayMoney(Pagination pagination,SecurityInfo securityInfo)throws Exception;
  public boolean queryAddPayTailEmp(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public List findPersonAddPayPrintList(Pagination pagination) throws Exception;
  public List findPersonAddPayPrint(String paymentHeadId,String orgId,String unitName,String noteNumber) throws Exception ;
  public Org findOrgInfo(String orgId);
  public EmpAddPay findEmpAddPay(String id);
  public Org findOrgInfo(Serializable id,String status,SecurityInfo securityInfo) throws BusinessException;
  public String findCollBank(String collBankid);
  // 单位版_提交数据_维护 wangye 2008-02-26
  public void insertEmpAddPayReferringData(Serializable empaddpayId, SecurityInfo securityInfo) throws BusinessException;
  // 单位版_撤销提交数据_维护 wangye 2008-02-26
  public void deleteAddpayPproval(Serializable empaddpayId, SecurityInfo securityInfo) throws BusinessException;
  
  // 单位版_提交数据_办理 wangye 2008-02-26
  public void insertEmpAddPayReferringData(String orgId, String paymentId, SecurityInfo securityInfo) throws BusinessException;
  
  // 单位版_撤销提交数据_办理 wangye 2008-02-26
  public void deleteAddpayPproval(String orgId, String paymentId, SecurityInfo securityInfo) throws BusinessException;
  
  // 单位版_提取数据_办理 wangye 2008-02-26
  public void importsAddpayPickup(String orgId, SecurityInfo securityInfo) throws Exception;
//查询单位的办事处和银行（在录入清册、登记状态为归集信息的办事处银行，在确认、复核、入账状态为流水中的办事处银行）
  public String[] queryOfficeBankNames(String orgId,String openStatus,String bizId,String bizType,SecurityInfo securityInfo ) throws Exception;
 //查询单位下是否有为完成的个人补缴清册 
  public String findOrgOver(String orgId) throws BusinessException;
  //查询单位下是否有为完成的个人补缴清册 
  public void insertEmpAddPay(String orgId,String paymentHeadId,SecurityInfo securityInfo,String addpayDateSt,String addpayDateEnd,String noteNum,String noteNum_1) throws BusinessException;
  public List querytest(String id) throws Exception; //为了控制并发添加的方法
  public List querytest1(String id) throws Exception; //为了控制并发添加的方法
  public List querydeleteall(Pagination pagination) throws Exception; //为了控制并发添加的方法
  public boolean overAddpaya(Serializable empaddpayId, SecurityInfo securityInfo) throws Exception; //为了控制并发添加的方法
  public BigDecimal getAddPayMoney(Integer addPayHeadId)throws BusinessException;
  public List findEmpaddpayList_jj(Pagination pagination, SecurityInfo securityInfo)
  throws Exception ;
  public String getNoteNum(SecurityInfo securityInfo);
  public BigDecimal getOverPay(String noteNum);
  public MonthpayJYAF findPayInfo(String paymentId) throws Exception ;
  public void updatePaymentInfo(Serializable paymentId,String bankName,String bankAcc,SecurityInfo securityInfo) throws Exception;
}
