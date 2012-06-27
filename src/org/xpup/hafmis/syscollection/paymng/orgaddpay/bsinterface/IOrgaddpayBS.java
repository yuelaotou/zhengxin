package org.xpup.hafmis.syscollection.paymng.orgaddpay.bsinterface;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgAddPay;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.dto.OrgaddpayHeadPrintDto;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTaAF;
import org.xpup.hafmis.syscollection.paymng.orgaddpay.form.OrgaddpayTbPickupdataWindowAF;

public interface IOrgaddpayBS {
  public String findIDld(String head_ID)throws Exception;//刘聃修改：根据302payheadid查询302里的id
  public boolean overAddpaya(Serializable empaddpayId, SecurityInfo securityInfo) throws Exception;//ld_add控制并发用
  public boolean findld1(Serializable orgaddpayId,SecurityInfo securityInfo) throws Exception;//ld_add
  public boolean findld(Serializable tailId,Serializable headId,SecurityInfo securityInfo) throws Exception;//刘聃添加：查询数据库中是否有要删除的记录
  public List findOrgaddpayList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public void repealAddpay(Serializable orgaddpayId,SecurityInfo securityInfo) throws Exception;
  public void deleteAddpay(Serializable orgaddpayId,SecurityInfo securityInfo) throws Exception;
  public BigDecimal getOrgaddpayMoney(Pagination pagination,SecurityInfo securityInfo);
  public Org findOrgInfo(Serializable id,String status,SecurityInfo securityInfo) throws BusinessException;
  public OrgaddpayTaAF findOrgaddpay(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  public void deleteOrgaddpayListing(Serializable tailId,Serializable headId,SecurityInfo securityInfo) throws Exception;
  public void deleteAllOrgaddpayListing(List list,Serializable headId,SecurityInfo securityInfo) throws Exception;
  public void overAddpay(Serializable tailId,Serializable headId,SecurityInfo securityInfo,String noteNum,Pagination pagination)throws Exception;
  public OrgAddPay findOrgaddpayInfo(Serializable headId) throws Exception;
  public List findPaylistBatch(Serializable orgid,String startPayMonth,String endPayMonth,String noteNum,SecurityInfo securityInfo) throws Exception;
  public OrgaddpayTaAF findOrgaddpayMX(Pagination pagination) throws Exception,BusinessException;
  public OrgaddpayHeadPrintDto findOrgaddpayPring(String paymentid) throws Exception;
  public List findOrgaddpayListPring(String paymentid) throws Exception;
  public List addPaylistBatch(List addpayHeadImportList,List addpayListImportList,String orgid,String payMonth,String startPayMonth,
      String noteNum,SecurityInfo securityInfo,Pagination pagination) throws Exception;
  public String findCollBank(String collBankid);
  public void commit_Do(Serializable headid,SecurityInfo securityInfo)throws Exception,BusinessException;
  public void commit_Maintain(String orgid,String headid,SecurityInfo securityInfo)throws Exception,BusinessException;
  public void overAddpayCommitData(String orgid,String headid,String type) throws Exception;
  public void cancel_Do(Serializable headid,SecurityInfo securityInfo) throws Exception;
  public void cancel_Maintain(String orgid,String headid,SecurityInfo securityInfo) throws Exception;
  public void pickupdata(String orgid,String payMonth,String startPayMonth,String noteNum,SecurityInfo securityInfo,Pagination pagination) throws Exception;
  public String queryOfficeName(String officeCode) throws Exception;
  //通过业务主键与业务类型查询流水
  public OrgHAFAccountFlow queryOrgHAFAccountFlow(String id,String bizType) throws Exception;
  //查询流水
  public OrgHAFAccountFlow queryOrgHAFAccountFlow(String id) throws Exception;
  //查询单位的办事处和银行（在录入清册、登记状态为归集信息的办事处银行，在确认、复核、入账状态为流水中的办事处银行）
  public String[] queryOfficeBankNames(String orgId,String openStatus,String bizId,String bizType,SecurityInfo securityInfo ) throws Exception;
  
  public List findPaylistBatch(Serializable orgid,String payMonth,String startPayMonth,String noteNum,SecurityInfo securityInfo,String[] orderby) throws Exception;
  public String queryInfoByOrgId(String orgId) throws Exception;
  public void addpayByNotenum(OrgaddpayTbPickupdataWindowAF af,SecurityInfo securityInfo) throws Exception;
  public String queryCollectionBankNameById(String id,SecurityInfo securityInfo) throws Exception,  BusinessException;
  public List findOrgaddpayListPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  public MonthpayJYAF findPayInfo(String paymentId) throws Exception ;
  public void updatePaymentInfo(Serializable paymentId,String bankName,String bankAcc,SecurityInfo securityInfo) throws Exception;
}