package org.xpup.hafmis.syscollection.chgbiz.chgperson.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonHeadFormule;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgpersonEmpInfoDTO;
import org.xpup.hafmis.syscollection.chgbiz.chgperson.form.ChgPersonAutoopenAF;
import org.xpup.hafmis.syscollection.common.domain.entity.ChgPersonTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranInTail;

/**
 * @author 王玲 2007-6-27
 */
public interface IChgpersonDoBS {
  // 根据条件查询人员变更记录
  public List findChgpersonDoListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 根据条件查询人员变更记录合计项
  public ChgPersonHeadFormule findChgpersonHeadByCriterions(
      Pagination pagination, SecurityInfo securityInfo, String headId)
      throws Exception, BusinessException;

  public ChgPersonHeadFormule findChgpersonHeadByCriterions_wsh(
      Pagination pagination, SecurityInfo securityInfo, String headId)
      throws Exception, BusinessException;

  // AJAXAction中用到查询单位
  public Org queryOrgById(Integer id, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 插入人员变更信息
  public String saveChgpersonDO(String orgID, String empID, String chgDate,
      String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      String i, SecurityInfo securityInfo) throws Exception, BusinessException;

  // 插入人员变更信息
  public void insertChgpersonDO(String orgID, String empID, String chgDate,
      String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 查询 :当人员变更类型为启封或封存时，进行数据插入
  public ChgPersonTail getChgPersonTail_WL(String orgID, String empID,
      String chgDate, String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      String i) throws Exception, BusinessException;

  // 查询是否在其他单位存在该职工
  public List getOtherOrgMessage_WL(String orgID, ChgPersonTail chgPersonTail);

  // 根据AA002主键查询职工信息
  public Emp queryEmpByID(String id);

  // 删除记录
  public void deleteChgPersonTail(Integer id, SecurityInfo securityInfo)
      throws BusinessException;

  // 删除当前页
  public void deletePageList(String chgpersonHeadID, SecurityInfo securityInfo)
      throws BusinessException;

  // 根据单位ID 和职工编号查询职工信息
  public Emp getEmpMessage(String orgID, String empID) throws BusinessException;

  // 启用
  public void useChgPerson(String orgID, String chgDate,
      SecurityInfo securityInfo) throws BusinessException;

  // 批量导出
  public List getChgpersonListAll(Pagination pagination, String orgID,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 根据条件查询记录（导入）
  public List modifyChgpersonBatch(List chgpersonExpTitle,
      List chgpersonExpContent, String orgID, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 王菱 人员变更维护 第一次进入时取得列表信息
  public List findChgpersonMaintainList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 变更维护－根据条件查询列表信息
  public List findChgpersonMaintainListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 根据AA204头表ID 查询单位ID 信息
  public String getOrgID(String chgPersonHeadID) throws Exception,
      BusinessException;

  // 根据AA204头表ID查询尾表LIST 然后调用this.deletePageList方法进行维护页面删除
  public void deleteChgPersonALL(String chgPersonHeadID,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 根据AA204头表ID进行数据校验,是否可以进行业务撤销
  public void checkDelUseMessage(String chgorgrateID, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 撤销启用
  public void deluserChgPersonMessage(String chgorgrateID,
      SecurityInfo securityInfo) throws BusinessException;

  // 根据尾表ID查询对应头表ID
  public String queryChgpersonHeadID(String tailID);

  // 根据头表ID查询人员变更记录明细
  public List findChgpersonDoListByHeadID(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 判断在本单位的转入业务中是否存在要新增的职工
  public void checkTranInBusiness(String orgID, String empName,
      String documentNum) throws BusinessException;

  // 判断本年余额和往年余额 是否为0
  public boolean isChgperson(String empid, String orgid) throws Exception;

  // 人员当前的状态
  public String getChgpersonStatus(String empid, String orgid) throws Exception;

  /**
   * 查询姓名不同但是身份证号相同的职工信息
   * 
   * @param empName
   * @param cardNum
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List isCardNumSame(String orgId, String empName, String cardNum)
      throws BusinessException, Exception;

  // 提交记录
  public void PickInDate(String id, SecurityInfo securityInfo, String flag)
      throws Exception, BusinessException;

  // 撤消提交记录
  public void removePickInDate(String id, SecurityInfo securityInfo, String flag)
      throws Exception, BusinessException;

  // 提取数据
  public void pickUpDate(String orgid, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  /**
   * 判断改单位是否可以进行自动变更
   * 
   * @param orgId 单位id
   * @return 旗标0不可以进行自动变更1可以
   * @throws Exception
   * @throws BusinessException
   * @author 付云峰
   */
  public String isAutoChange(String orgId) throws Exception, BusinessException;

  /**
   * 查询自动变更弹出框的方法
   * 
   * @param pagination
   * @return 列表内容
   * @throws Exception
   * @throws BusinessException
   * @author 付云峰
   */
  public List findAutoChangePopList(Pagination pagination) throws Exception,
      BusinessException;

  public List findAutoChangePopListALL(Pagination pagination) throws Exception, BusinessException;

  /**
   * 根据单位编号与职工编号查询准备做变更人的信息
   * 
   * @param orgId
   * @param empId
   * @return
   * @throws Exception
   * @throws BusinessException
   * @author 付云峰
   */
  public ChgpersonEmpInfoDTO findChgpersonEmpInfo(String orgId, String empId)
      throws Exception, BusinessException;
  /**
   * 查询该单位下是否存在相同证件号码和相同职工姓名的职工数量
   * @param orgId
   * @param empName
   * @param cardNum
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public int isExistSameEmpInfo(String orgId, String empName, String cardNum)
      throws Exception, BusinessException;

  // //吴洪涛 2008-6-16
  public List findChgpersonDoListAllByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public String queryCollectionBankNameById(String id, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  public String getNamePara() throws Exception;

  public ChgPersonAutoopenAF findChgPersonAutoopenAF(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public void updateTranInTail(String orgID, String empId, String type)
      throws Exception, BusinessException;

  public String saveChgpersonDO_wsh(String orgID, String empID, String chgDate,
      String chgMap_1, String documentMap_1, String sexMap_1,
      String partInMap_1, String chgreasonMap_1, ChgPersonTail chgPersonTail,
      String i, SecurityInfo securityInfo) throws Exception, BusinessException;

  public List findChgpersonDoListAllByCriterions_wsh(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  public void deleteFnTempTable(String type) throws Exception, BusinessException;
}