package org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpChangeAF;
import org.xpup.hafmis.syscollection.accountmng.accountopen.form.EmpkhAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public interface IOrgOpenAccountBS {
  public Serializable saveOrgOpenAccount(Org org, SecurityInfo securityInfo,
      String isOrgorcenter) throws Exception;

  public Serializable saveOrgOpenAccount_yg(Org org, SecurityInfo securityInfo,
      String isOrgorcenter) throws Exception;

  public List findOrganizationsDwkh(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException;

  public Org findOPA(Integer id) throws BusinessException;

  public Org findOPA_zl(Integer id, SecurityInfo info) throws BusinessException;

  public Serializable modifyOpen(Org org, SecurityInfo securityInfo)
      throws BusinessException;

  public List findEmployee(Pagination pagination) throws BusinessException;

  public EmpkhAF findEmployee(Pagination pagination, SecurityInfo securityInfo)
      throws BusinessException;

  public int saveEmployee(String orgId, Emp emp, String emppaymonth, int flag)
      throws Exception, BusinessException;

  public Emp findEmp(Integer id) throws BusinessException;

  public boolean modifyEmployee(String id, Emp emp, SecurityInfo securityInfo)
      throws BusinessException;

  public void removeEmp(String empId, SecurityInfo securityInfo)
      throws BusinessException;

  public boolean removeEmpAll(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  public void openOver(String id, SecurityInfo securityInfo) throws Exception;

  public int removeOrg(String orgId) throws BusinessException;

  public void removeOrgEmp(String id, SecurityInfo securityInfo)
      throws BusinessException;

  public List queryEmpListByOrgid(String orgId) throws BusinessException;

  public List modifyOrgOpenBatch(List empOpenImpTitle, List empOpenImpContent,
      String orgId, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  public void ExpInsert(String orgid, SecurityInfo securityInfo)
      throws BusinessException;

  public void saveEmployeeInfo(String orgId, Emp emp, String emppaymonth,
      String empid, SecurityInfo securityInfo) throws Exception;

  public List getEmpFromOthers(String orgid, Emp emp) throws Exception;

  public List queryCollBank(String office);

  public boolean validateOpenStatus(String id);

  public boolean queryEmpCount(String orgID, String empID);

  /**
   * 查询单位代扣导出列表
   * 
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findOrgAgentList(SecurityInfo securityInfo) throws Exception;

  /**
   * 单位代扣导入
   * 
   * @param orgAgentImportList
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List saveOrgAgentList(List orgAgentImportList,
      SecurityInfo securityInfo) throws BusinessException, Exception;

  /**
   * 职工代扣导出
   * 
   * @param orgId
   * @return
   * @throws Exception
   */
  public List findEmpAgentList(String orgId) throws Exception;

  /**
   * 职工代扣导入
   * 
   * @param empAgentImportTitleList
   * @param empAgentImportList
   * @param securityInfo
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List saveEmpAgentList(List empAgentImportTitleList,
      List empAgentImportList, SecurityInfo securityInfo)
      throws BusinessException, Exception;

  /**
   * 查询姓名不同但是身份证号相同的职工信息
   * 
   * @param empName
   * @param cardNum
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List isCardNumSame(String empName, String cardNum)
      throws BusinessException, Exception;

  /**
   * 修改时判断姓名不同但是身份证号相同的职工
   * 
   * @param empName
   * @param cardNum
   * @param empId
   * @param orgId
   * @return
   * @throws BusinessException
   * @throws Exception
   */
  public List isCardNumUpdateSame(String empName, String cardNum, String empId)
      throws BusinessException, Exception;

  /**
   * 中心提取数据
   */
  // 删除的时候多传入一个参数
  public void removeEmp(String empId, SecurityInfo securityInfo, String orgId)
      throws BusinessException;

  public void pickupDateAll(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  // 单位版撤销数据
  public void pprovalDataInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  // 单位版提交数据
  public void referringDataInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  // 单位版撤销数据职工开户页面
  public void pprovalDataOrgInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  // 单位版提交数据职工开户页面
  public void referringDataOrgInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException;

  public EmpChangeAF changeEmpIdInfo(String orgId, SecurityInfo securityInfo,
      String empId) throws BusinessException;

  /**
   * 修改职工编号
   */
  public void changeEmpid(String empId, String orgId, String oldEmpId)
      throws BusinessException;

  // bit add
  public List queryprintlist(String orgid, String orgname)
      throws BusinessException;

  public String querycollbankid(Integer orgid, String orgname)
      throws BusinessException;

  public void updateba001_tijiao(String orgId) throws BusinessException;
  
  public boolean queryEmployee(String orgId, String empid )
  throws BusinessException;
}
