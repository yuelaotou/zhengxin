package org.xpup.hafmis.syscollection.accounthandle.bizcheck.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckAF;
import org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.BizcheckDetailAF;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountDetailAF;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

/**
 * 2007-07-11
 * @author 于庆丰
 *
 */
public interface IBizcheckBS {
  
  //默认查询
  public BizcheckAF findOrgHAFAccountFlowListBydefault(Pagination pagination) throws Exception,
      BusinessException;
///账务处理—业务复核—复核通过
  public void checkthroughOrgHAFAccountFlow(Integer id,String ip,String name,String officeCode,String moneyBank) throws Exception,BusinessException; 
//  账务处理-业务复核-撤消复核
  public void delcheckthroughOrgHAFAccountFlow(Integer id,String ip,String name,String collectionBankId,String officeCode) throws Exception,BusinessException; 
//账务处理—业务复核—批量复核
  public void checkallOrgHAFAccountFlow(Pagination pagination,String moneyBank) throws Exception,BusinessException; 
  // 账务处理—业务复核—批量复核撤消
  public void delcheckallOrgHAFAccountFlow(Pagination pagination) throws Exception,BusinessException; 
//根据流水头表id返回头表
  public BizcheckDetailAF findOrgHAFAccountFlowCellByID(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //详细信息查询
  public BizcheckDetailAF findOrgHAFAccountFlowByID(Pagination pagination,SecurityInfo securityInfo, ILoanDocNumDesignBS loanDocNumDesignBS) throws Exception,
      BusinessException;
  //orgHAFAccountFlow = orgHAFAccountFlowDAO.queryById(id);
  public OrgHAFAccountFlow findOrgHAFAccountFlowByID_(Integer id) throws Exception,
  BusinessException;
  //根据AA101的主键ID返回对应业务的业务ID
  public String queryBizIDByFlowID(String flowID) throws Exception;
  //根据单位id得到所在办事处后根据办事处查询aa412表中的银行修改设置
  public String queruIsBankModify(String orgId)throws Exception;
  //查询批量复核的单位的所在办事处个数
  public int findOfficeCount_wsh(String orgIds)throws Exception;
  //根据aa101主键查询单位id
  public String queryOrgId(String id) throws Exception ;
  //根据单位编号查询单位所在归集银行
  public String queryOrgCollectionBankId(String orgId) throws Exception ;
  //根据AA101的主键ID查询转出业务的转入是否已经入账
  public String isTansInFive(String flowID) throws Exception,BusinessException;
  public String queryAA306ReserveaA(String doc_num,String note_num) throws Exception, BusinessException;
  public String queryAA306ReserveaB(String doc_num,String note_num) throws Exception, BusinessException;
}
