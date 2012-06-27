package org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface;

import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgChg;

/**
 * 
 * @author 王玲
 *2007-6-27
 */
public interface IChgorgrateBS {
  public List queryOrgChgListCheck(Pagination pagination, SecurityInfo securityInfo) throws BusinessException ;
  public Org queryOrgByorgIDYg(String orgID, SecurityInfo securityInfo) throws BusinessException, Exception;
  public int queryOrgChgListAllCheck(Pagination pagination, SecurityInfo securityInfo) throws BusinessException;
  public void submitOrgChgById(String id) throws BusinessException;
  public void delSubmitOrgChgById(String id) throws BusinessException;
  public void passOrgChgById(String id,SecurityInfo securityInfo) throws BusinessException;
  public void delPassOrgChgById(String id) throws BusinessException;
  public void deleteOrgChg(OrgChg orgChg) throws BusinessException;
  public void updateOrgChg(OrgChg orgChg) throws BusinessException;
  public OrgChg queryOrgChgById(String id) throws BusinessException;
  public List queryOrgChgList(Pagination pagination, SecurityInfo securityInfo) throws BusinessException;
  public int queryOrgChgListAll(Pagination pagination, SecurityInfo securityInfo) throws BusinessException;
  public void saveOrgChg(OrgChg orgChg) throws BusinessException;
  public int queryPersonCountByOrgID(String orgID) throws BusinessException, Exception;
  //判断该单位是否是按率缴存
  public ChgOrgRate checkOrgMessage(String orgID,SecurityInfo securityInfo) throws BusinessException,Exception;
  //根据单位ID 查询单位信息
  public Org queryOrgByorgID(String orgID) throws BusinessException, Exception ;
  //根据单位ID 查询单位应缴金额
  public BigDecimal querySumPayByOrgID(String orgID) throws BusinessException, Exception;
  //做汇缴比例调整时：查询变更年月，先查AA201，无就查AA302，无再查AA001的初缴年月
  public String getChgMonth(ChgOrgRate chgOrgRate,String orgID) throws BusinessException, Exception;
  //根据AA201，进行数据录入
  public BigDecimal saveChgOrgRate(ChgOrgRate chgOrgRate,SecurityInfo securityInfo) throws BusinessException, Exception;
  //根据AA201，进行数据录入
  public BigDecimal updateChgOrgRate(ChgOrgRate chgOrgRate,SecurityInfo securityInfo) throws BusinessException, Exception;
  //删除记录
  public void deleteChgOrgRate(Integer id,SecurityInfo securityInfo) throws BusinessException;  
  //进行数据启用
  public void useChgOrgRate(ChgOrgRate chgOrgRate,SecurityInfo securityInfo) throws BusinessException, Exception;
  //王菱 维护 第一次进入时取得列表信息
  public List findChgpersonMaintainList(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  //变更维护－根据条件查询列表信息
  public List findChgorgrateMaintainListByCriterions(Pagination pagination,SecurityInfo securityInfo)  throws Exception, BusinessException;
  //根据头表ID 查询AA201表信息
  public ChgOrgRate queryChgorgrateMessage(String id) throws Exception, BusinessException;  
  //根据AA201头表ID进行数据校验,是否可以进行业务撤销
  public void checkDelUseMessage(String chgorgrateID,SecurityInfo securityInfo) throws Exception, BusinessException ;
  // 撤销启用
  public void deluserChgPersonMessage(String chgorgrateID,SecurityInfo securityInfo)  throws BusinessException;
  //取得职工状态为(1,3,4)的工资基数列表
  public List queryEmpSalaryByTIAOJIAN(String orgID)  throws BusinessException;
  
  
  //吴宏涛修改  2008－3－19
  public ChgOrgRate queryChgorgrate_OrgEdition(String orgId,String chgMonth,SecurityInfo securityInfo) throws Exception, BusinessException;  
  
}