package org.xpup.hafmis.syscollection.querystatistics.collfncomparison.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public interface ICollFnComparisonBS {
  /**
   * 查询职工信息的方法
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws BusinessException
   */
  public Object[] findCollFnComparisonEmpInfo(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException;

  /**
   * 查询职工账的方法
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws BusinessException
   */
  public Object[] findCollFnComparisonEmpAccount(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;

  public Object[] findCollectionuseinfo(SecurityInfo securityInfo,
      Pagination pagination, String orgId_1) throws Exception, BusinessException;

  /**
   * 查询弹出框
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List queryEmpInfoPop(Pagination pagination, SecurityInfo securityInfo)
      throws Exception;
  /**
   * 查询单位名称
   * @param orgId
   * @return
   * @throws Exception
   */
  public String findOrgName(String orgId) throws Exception;
  public List findOrgpopList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  public Org queryOrgById(Integer id,SecurityInfo securityInfo) throws Exception,
  BusinessException;

  public List changePrintList(List list) throws Exception;


  public List findOrgAccountPrintList(String orgidst, String orgidend, String timest, String timeend, SecurityInfo securityInfo) throws Exception;
  public int findOrgpopListCount(String orgid,String orgname,SecurityInfo securityInfo) throws Exception,BusinessException;
  /**
   * 职工账查询弹出框查询list的方法
   * @param pagination
   * @return
   * @throws Exception
   */
  public List findEmpPopList(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  /**
   * 查询职工账打印列表
   * @param orgidst
   * @param orgidend
   * @param empidst
   * @param empidend
   * @param timeSt
   * @param timeEnd
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findEmpAccountPrint(String orgidst, String orgidend,
      String empidst, String empidend, String timeSt,
      String timeEnd,SecurityInfo securityInfo)throws Exception;
  public Object[] findOrgCollInfo(Pagination pagination);
  /**
   * 职工账查询余额的列表
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List queryqcye(Pagination pagination, SecurityInfo securityInfo)
  throws Exception ;
  /**
   * 单位账查询余额列表
   * @param pagination
   * @param securityInfo
   * @param orgId_1
   * @return
   * @throws Exception
   */
  public List queryqcyeorg(Pagination pagination, SecurityInfo securityInfo,
      String orgId_1) throws Exception ;
}
