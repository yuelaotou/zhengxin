package org.xpup.hafmis.syscollection.chgbiz.chgslarybase.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgslarybase.form.ChgslarybaseListAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPaymentTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public interface IChgslarybaseBS {
  //根据条件查询ChgslarybaseListAF记录
  public ChgslarybaseListAF findChgslarybaseList(Pagination pagination) throws Exception,BusinessException;
  //根据orgid查询Org记录 
  public String findOrgidById(String id)throws Exception,BusinessException;
//删除记录
  public void deleteChgPaymentTail(Integer id,String pkid,String orgid,String ip,String nrOfElements,String name,SecurityInfo securityInfo) throws Exception,BusinessException;
  //删除记录（全部）
  public void deleteAllChgPaymentTail(Integer id,String pkid,String orgid,String ip,String name,SecurityInfo securityInfo) throws Exception,BusinessException;
// 根据条件查询ChgPaymentTail记录 
  public ChgPaymentTail findEmpinfo(String empid,String orgid) throws Exception,BusinessException;
  //添加记录
  public void addChgPaymentTail(ChgPaymentTail chgPaymentTail,Pagination pagination) throws BusinessException;
  //修改记录
  public void updateChgPaymentTail(ChgPaymentTail chgPaymentTail,Pagination pagination) throws BusinessException;
  //根据条件查询ChgslarybaseListAF记录
  public ChgPaymentTail findChgPaymentTailWuht(String id,Pagination pagination) throws Exception,BusinessException;
  //根据条件启用
  public void useChgPaymentSalaryBase(Pagination pagination) throws Exception,BusinessException; 
  public void setAa202(Pagination pagination) throws Exception,BusinessException;
  //根据条件查询ChgslarybaseListAF记录
  public ChgslarybaseListAF findOrgChgslarybaseList(Pagination pagination) throws Exception,BusinessException;
  
//删除记录在维护中
  public void deleteAllChgPaymentTailMaintain(Integer id,String ip,String name,SecurityInfo securityInfo) throws Exception,BusinessException;
//根据条件启用在维护中
  public void useChgPaymentSalaryBaseMaintain(Pagination pagination) throws Exception,BusinessException; 
//根据条件撤消启用在维护中
  public boolean deluseChgPaymentSalaryBaseMaintain(Pagination pagination) throws Exception,BusinessException;
//根据条件查询记录
  public List queryEmpOrgList(Pagination pagination) throws Exception,BusinessException;
//根据条件添加记录
  public List addChgslarybaseListBatch(List addchgslarybaseHeadImportList,List addchgslarybaseListImportList,String orgId,String chgMonth,String ip,String name,String date,SecurityInfo securityInfo) throws Exception,BusinessException;
  public ChgslarybaseListAF findChgslarybaseWindow(Pagination pagination) throws Exception, BusinessException;
  //根据202的id查询Orgid记录 
  public String queryOrgidByChgPaymentHeadID(String chgPaymentHeadID)throws Exception,BusinessException;
 //提交记录
  public void PickInChgPaymentTailMaintain(String id,String orgid,SecurityInfo securityInfo,String flag) throws Exception,BusinessException;
 //撤消提交记录
  public void removePickInChgPaymentTailMaintain(String id,String orgid,SecurityInfo securityInfo,String flag) throws Exception,BusinessException;
 //提取数据
  public void pickUpChgPaymentChgslarybase(String orgid,String chgMonth,SecurityInfo securityInfo) throws Exception,BusinessException;
  //吴洪涛2008616
  public List chgslarybaseCellList(List chgslarybaseCellList,String orgid,String chgMonth,SecurityInfo securityInfo,Pagination pagination) throws Exception,BusinessException;
  public void setAa202_wsh(String pkid) throws Exception,
  BusinessException;
}
