package org.xpup.hafmis.syscollection.chgbiz.chgpay.bsinterface;



import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgpay.form.ChgpayListAF;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;


public interface IChgpayBS {
  //根据条件查询ChgslarybaseListAF记录
  public ChgpayListAF findChgpayList(Pagination pagination) throws Exception,BusinessException;
 
//根据条件查询ChgPaymentTail记录 
  public ChgPaymentTail findEmpinfo(String empid,String orgid) throws Exception,BusinessException;
  
  //添加记录
  public void addChgPaymentTail(ChgPaymentTail chgPaymentTail,Pagination pagination) throws BusinessException;
  
  //根据条件查询ChgslarybaseListAF记录
  public ChgPaymentTail findChgPaymentTailWuht(String id,Pagination pagination) throws Exception,BusinessException;
  
  //修改记录
  public void updateChgPaymentTail(ChgPaymentTail chgPaymentTail,Pagination pagination) throws BusinessException;
  
  //删除记录
  public void deleteChgPaymentTail(Integer id,String pkid,String orgid,String ip,String nrOfElements,String name,SecurityInfo securityInfo) throws Exception,BusinessException;
//删除记录（全部）
  public void deleteAllChgPaymentTail(Integer id,String pkid,String orgid,String ip,String name,SecurityInfo securityInfo) throws Exception,BusinessException;
  
  //根据条件启用
  public void useChgPaymentPayment(Pagination pagination) throws Exception,BusinessException;
  
  //根据条件查询ChgslarybaseListAF记录
  public ChgpayListAF findOrgChgpayList(Pagination pagination) throws Exception,BusinessException;
  //根据orgid查询Org记录 
  public String findOrgidById(String id)throws Exception,BusinessException;

//删除记录在维护中
  public void deleteAllChgPaymentTailMaintain(Integer id,String ip,String name,SecurityInfo securityInfo) throws Exception,BusinessException;

//根据条件启用在维护中
  public void useChgPaymentPaymentMaintain(Pagination pagination) throws Exception,BusinessException; 

//根据条件撤消启用在维护中
  public boolean deluseChgPaymentPaymentMaintain(Pagination pagination) throws Exception,BusinessException;

//根据条件查询记录（导出）
  public List queryEmpOrgList(Pagination pagination) throws Exception,BusinessException;
//根据条件添加记录（导入）
  public List addChgpayListBatch(List addchgpayHeadImportList,List addchgpayListImportList,String orgId,String chgMonth,String ip,String name,String date,SecurityInfo securityInfo) throws Exception,BusinessException;
// 变更维护弹出框方法
  public ChgpayListAF findChgpayWindowList(Pagination pagination) throws Exception,BusinessException;
//办理和维护的提交
  public void PickInChgPaymentTailMaintain(String id, String orgid, SecurityInfo securityInfo,String flag) throws Exception,BusinessException;
  //办理和维护的撤消提交
  void removePickInChgPaymentTailMaintain(String id, String orgid, SecurityInfo securityInfo,String flag) throws Exception, BusinessException;
  //数据提取
  public void pickUp(String orgid,String chgMonth, SecurityInfo securityInfo) throws Exception, BusinessException;
  //查询单位ID
  public String  queryOrgidByChgPaymentHeadID(String chgPaymentHeadID) throws Exception, BusinessException;
}
