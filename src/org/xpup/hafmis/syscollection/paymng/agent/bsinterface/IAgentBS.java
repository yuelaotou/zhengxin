package org.xpup.hafmis.syscollection.paymng.agent.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface IAgentBS {
  /**
   * 插入导入文件
   * 
   * @param agentImportTitle
   * @param agentImport
   * @param securityInfo
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public Object[] saveAgent(List agentImportTitle, List agentImport,
      SecurityInfo securityInfo) throws BusinessException, Exception;

  /**
   * 根据缴存年月查找是否有未使用的业务
   * 
   * @param agentYearMonth
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public Object findAgentYearMonthCount(String agentYearMonth)
      throws BusinessException, Exception;

  /**
   * 查询导入列表的方法
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public Object[] findAgentInfo(Pagination pagination,
      SecurityInfo securityInfo, String agentDetailId)
      throws BusinessException, Exception;

  /**
   * 删除代扣导入信息的方法
   * 
   * @param agentDetailId
   * @param orgAgentNum
   * @throws BusinessException
   * @throws Exception
   */
  public void deleteAgentInfo(String agentDetailId, String orgAgentNum)
      throws BusinessException, Exception;

  /**
   * 全部删除的方法
   * 
   * @param agentDetailId
   * @param orgAgentNum
   * @throws BusinessException
   * @throws Exception
   */
  public void deleteAllAgentInfo(String agentDetailId, String orgAgentNum)
      throws BusinessException, Exception;

  /**
   * 查询代扣变更列表的方法
   * 
   * @param pagination
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List findAgentChgInfoList(Pagination pagination,SecurityInfo info)
      throws BusinessException, Exception;

  /**
   * 生成代扣变更的方法
   * 
   * @param agentDetailId
   * @param securityInfo
   * @throws BusinessException
   * @throws Exception
   */
  public void createAgentChange(String agentHeadId, SecurityInfo securityInfo)
      throws BusinessException, Exception;

  /**
   * 财政代扣-撤销变更
   * 
   * @param agentHeadId
   * @throws BusinessException
   * @throws Exception
   */
  public void backAgentChagneInfo(String agentHeadId, SecurityInfo securityInfo)
      throws BusinessException, Exception;

  /**
   * 查询单位明细
   * 
   * @param pagination
   * @param agentHeadId
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public Object[] findAgentOrgInfoList(Pagination pagination, String agentHeadId)
      throws BusinessException, Exception;
  /**
   * 删除职工
   * @param empAgentId
   */
  public void deleteEmpAgentInfo(String empAgentId);
  /**
   * 查询职工明细
   * 
   * @param pagination
   * @param orgAgentId
   * @param orgId
   * @param payMode
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public Object[] findAgentEmpInfoList(Pagination pagination,
      String orgAgentId, String orgId, String payMode) throws BusinessException, Exception;

  // 获得代扣信息集合 LIST
  public List queryAgentInfoTaList(Pagination pagination,SecurityInfo securityInfo) throws Exception;

  // 获得代扣信息 count
  public int queryAgentInfoTaListCount(Pagination pagination,SecurityInfo securityInfo) throws Exception;

  // 获得代扣信息(单位)集合 LIST
  public List queryAgentInfoTbList(Pagination pagination) throws Exception;

  // 获得代扣信息(单位) count
  public int queryAgentInfoTbListCount(Pagination pagination) throws Exception;

  // 获得代扣信息(职工)集合 LIST
  public List queryAgentInfoTcList(Pagination pagination) throws Exception;

  // 获得代扣信息(职工) count
  public int queryAgentInfoTcListCount(Pagination pagination) throws Exception;
}
