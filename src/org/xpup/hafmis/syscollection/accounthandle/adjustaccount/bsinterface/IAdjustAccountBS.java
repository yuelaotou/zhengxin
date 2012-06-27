package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.dto.AdjustaccountDTO;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountShowAF;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;


/**
 * 
 * @author 李鹏
 *2007-6-27
 */
public interface IAdjustAccountBS { 
  //办理错账显示列表方法
  public AdjustaccountShowAF findAdjustWrongAccountHeadAndTailIDByPagination(String temp_adjustWrongAccountHead_id,Pagination pagination,String temp_type,SecurityInfo securityInfo) throws Exception,BusinessException;
  //判断员工是否已经被加载到315
  public boolean findHeadAndTailByCriterions(String orgId,String empId) throws Exception,BusinessException;
  //根据根据单位id号和状态为1的查询出有没有完成错账
  public AdjustWrongAccountHead findOrgHAFAccountFlowByOrgAndStatus(String orgId,SecurityInfo securityInfo) throws Exception,BusinessException;
  //根据凭证号查询出返回实例 
  public List findOrgHAFAccountFlowByCriterions(String orgid,String bizdate,String bizDocNum,SecurityInfo securityInfo) throws Exception,BusinessException;
 //根据条件查询错账id
  public AdjustWrongAccountHead findAdjustWrongAccountHeadIDByCriterions(String orgId) throws Exception,BusinessException;
  //根据条件查询错账记录
  public AdjustaccountDTO findAdjustAccountListByCriterions(Pagination pagination) throws Exception,BusinessException;
 //根据单位id返回单位
  public String findOrgInfoById(String orgId,SecurityInfo securityInfo) throws Exception,BusinessException;
 // 根据EmpHAFAccountFlow.orgHAFAccountFlow.id和orgHAFAccountFlow.id关联返回EmpHAFAccountFlow集合
  public AdjustaccountDTO findEmpHAFAccountFlowListById(String orgId,Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //根据员工id查询员工信息
  public Emp findEmpInfoByEmpId(Integer empId) throws Exception,BusinessException;
  //判断员工是否在该单位下 
  public Emp checkEmp(String orgId,String empId) throws Exception,BusinessException;
  //根据条件错账id和员工id 查询出该单位是否已经有过错账调整
  public AdjustWrongAccountTail findAdjustWrongAccountTailByCriterions(String orgId,String empId) throws Exception,BusinessException;
  //单独提出来　　判断有无错账头表AdjustWrongAccountHead
  public AdjustWrongAccountHead findAdjustWrongAccountHeadByOrgId(String orgId,SecurityInfo securityInfo) throws Exception,BusinessException;
  //插入错账尾表
  public Serializable insertAdjustWrongAccountTail(AdjustWrongAccountTail adjustWrongAccountTail,Integer empId,AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo,String orgId) throws Exception,BusinessException;
  //插入错账头尾表  
  public Serializable insertAdjustWrongAccountHeadAndTail(AdjustWrongAccountTail adjustWrongAccountTail,Integer empId,String orgId,String type,SecurityInfo securityInfo,String noteNum) throws Exception,BusinessException;
  //修改错账头表状态
//  public Serializable updateAdjustWrongAccountHeadState(AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo)throws BusinessException, Exception;
  public AdjustWrongAccountHead updateAdjustWrongAccountHeadState(AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo,String noteNum)throws BusinessException, Exception;
  //将101和102里的员工和单位信息--插入到314和315中
//  public Serializable insertAdjustWrongHATByOrgHAT(List list,SecurityInfo securityInfo)throws BusinessException, Exception;
  public AdjustWrongAccountHead insertAdjustWrongHATByOrgHAT(List list,SecurityInfo securityInfo,String noteNum)throws BusinessException, Exception;
  //根据错账头表查出尾表的相应员工
  public List findAdjustWrongAccountTailByHead(String HeadID) throws Exception,BusinessException;
  //将错账头表查出尾表的相应员工插入到业务流水头表
  public void insertOrgHAFAccountFlowByWrongHAT(AdjustWrongAccountHead adjustWrongAccountHead,List empList,SecurityInfo securityInfo);
  //根据错账头表id返回头表
  public AdjustWrongAccountHead findAdjustWrongAccountHeadByID(Serializable id) throws Exception,BusinessException;
  //查询流水通过凭证号
//  public OrgHAFAccountFlow findOrgHAFAccountFlowByDocNumCriterions(String bizDocNum) throws Exception,BusinessException;
  //更改过
  public OrgHAFAccountFlow findOrgHAFAccountFlowByDocNumCriterions(String bizDocNum,String bizdate,String orgid) throws Exception,BusinessException;
  //删除
  public String deleteAdjustWrongAccountTailByID(String id,SecurityInfo securityInfo) throws Exception,BusinessException;
  //查看员工信息 
  public Emp findEmpById(String id) throws Exception,BusinessException;

  //维护 
  //查询出错账头表状态为1和3的都查询出来
  public AdjustaccountDTO findAdjustWrongAccountHeadByStatus(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //根据查询错账头表
  public AdjustaccountDTO findAdjustWrongAccountHeadByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //根据头表第查询
  public AdjustWrongAccountHead findOrgHAFAccountFlowById(String id) throws Exception,BusinessException;
  //删除头尾表第
  public String deleteAdjustWrongAccountHeadAndTailByID(String id) throws Exception,BusinessException;
  //判断是否有同一家公司有不同的状态
  public List findAdjustWrongAccountHeadIDByOrgIdAndStatus(String orgId) throws Exception,BusinessException;
  //修改错账头表
  public void updateAdjustWrongAccountHeadByID(String id,SecurityInfo securityInfo) throws Exception,BusinessException;
  //删除错账头为表
  public void deleteAdjustWrongAccountHeadAndTailByHID(String id,SecurityInfo securityInfo) throws Exception,BusinessException;
  //删除101和102里相关记录
  public void deleteOrgHAFAccountFlowAndEmpByHID(String id,SecurityInfo securityInfo,String bis_id) throws Exception,BusinessException;
  //撤销调整 删除101和102里相关记录
  public void deleteAdjustWrongAccountAllByHID(AdjustWrongAccountHead adjustWrongAccountHead,SecurityInfo securityInfo) throws Exception,BusinessException;
  //通过单位id和业务状态以及业务类型查询出OrgHAFAccountFlow
  public OrgHAFAccountFlow findOrgHAFAccountFlowByCriterions1(String orgId,BigDecimal Status,String bizType,String bizDate) throws Exception,BusinessException;
  //插入日志003
  public void insertHafOperateLog(String bizId,String orgId,String ip,String oper) throws Exception,BusinessException;
  //打印 错账头尾表
  public List adjustWrongAccountAllByHID(String id,SecurityInfo securityInfo) throws Exception,BusinessException;
  //维护明细显示
  public AdjustaccountShowAF findAdjustWrongAccountHeadAndTailIDByPagination_sy(String string, Pagination pagination, String string2, SecurityInfo securityInfo)throws BusinessException;
  //维护明细显示
  public AdjustaccountShowAF findAdjustWrongAccount_sy(String ids,Pagination pagination)throws BusinessException;
  //提示是否有为记账的业务,提取和转出.
  public String querySpecialPickAndTranOutHead(String orgId);
  public String[] queryOfficeBankNames(String orgId,String openStatus,String bizId,String bizType,SecurityInfo securityInfo ) throws Exception;
}

