package org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.dto.ClearAccountBalanceDTO;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountBalanceForm;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountDetailAF;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.ClearAccountShowAF;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;


/**
 * 
 * @author 李鹏
 * 2007-7-10
 */
public interface IclearAccountBS {
  //列表显示扎账(默认)
  public ClearAccountShowAF findOrgHAFAccountFlowDefByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //显示列表扎账
  public ClearAccountShowAF findOrgHAFAccountFlowTotalByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //默认全部可以扎账的列表
  public List queryOrgHAFAccountFlowDefByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //结合条件查询全部可以扎账的列表
  public List queryOrgHAFAccountFlowTotalByPagination(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //根据流水头表id返回头表
  public ClearAccountDetailAF findOrgHAFAccountFlowByID(Pagination pagination,SecurityInfo securityInfo ,ILoanDocNumDesignBS loanDocNumDesignBS) throws Exception,BusinessException;
//根据流水头表id返回头表
  public ClearAccountDetailAF findOrgHAFAccountFlowCellByID(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  //扎账
  public String dealwithClearAccount(String[] rowArray,String busiDate,String oper,String ip,String officeid,Pagination pagination,String flag,SecurityInfo securityInfo) throws BusinessException, HibernateException, SQLException;
  //结算单查询当天
  public ClearAccountBalanceDTO findClearAccountBalanceByDefault(SecurityInfo securityInfo) throws Exception;
  
  public ClearAccountBalanceForm findClearAccountBalance(Pagination pagination,
      SecurityInfo securityInfo) throws Exception ;
  public String findCollBank(String collBankid);
  //根据AA101的主键ID返回对应业务的业务ID
  public String queryBizIDByFlowID(String flowID) throws Exception;
  //存在未进行转入确认的转出进行扎账，系统应给出提示
  public String checktraininBytrainout(String rowarray) throws Exception;
  //存在未进行转入确认的转出进行扎账,得到转出单位编号
  public String getTrainoutorgid(String rowarray) throws Exception;
  //存在未进行转入确认的转出进行扎账,得到转入单位编号
  public String getTraininorgid(String rowarray) throws Exception;
  //修改扎账时的标识状态
  public String updateOrgHAFAccountFlow(String rowarray,String flag) throws Exception;
  //通过主键id查询扎账标识
  public OrgHAFAccountFlow queryIsClearAccountById(String rowarray) throws Exception;
  // 根据业务id与类型查询流水表实体
  public OrgHAFAccountFlow queryIsClearAccountByBizId(String bizId,String type) throws Exception;;
  public String queryBankName(String flowID) throws Exception;
  public String queryMakerPara() throws Exception;
  public String queryUserName(String userName) throws Exception;
}

