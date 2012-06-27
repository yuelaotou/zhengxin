package org.xpup.hafmis.syscollection.paymng.orgoverpay.bsinterface;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgExcessPayment;
import org.xpup.hafmis.syscollection.paymng.orgoverpay.form.OrgoverpayAF;

public interface IOrgoverpayBS {
 
  public OrgExcessPayment findOrgoverpayInfo( Serializable orgid, Integer payStatus ) throws BusinessException;
  public BigDecimal queryOrgoverpayBalance( Serializable orgid ) throws BusinessException;
  public OrgoverpayAF findOrgoverpayMX(Pagination pagination) throws Exception,BusinessException;
  public void updateOrgoverpayById(String id,String noteNum,String payMoney,String reason,SecurityInfo securityInfo)throws BusinessException;
  public OrgoverpayAF printReport(String id) throws Exception,BusinessException;
  public List queryOrgoverpayList(Pagination pagination,SecurityInfo securityInfo) throws BusinessException,Exception;
  public BigDecimal getEmpaddpayMoney(Pagination pagination,SecurityInfo securityInfo)throws BusinessException,Exception;
  public Org findOrgInfo(Serializable id,String status,SecurityInfo securityInfo) throws BusinessException;
  public void insertPaymentHead( Serializable orgid,String payMoney,String noteNum,String reason,String type,SecurityInfo securityInfo) throws BusinessException;
  public void deleteOrgoverpay(String id,SecurityInfo securityInfo)throws BusinessException;
  public OrgExcessPayment updateOrgoverpay(String id,SecurityInfo securityInfo)throws BusinessException;
  public String findCollBank(String collBankid);
  public boolean isOrgoverpay(String orgId,SecurityInfo securityInfo) throws Exception;
//查询单位的办事处和银行（在录入清册、登记状态为归集信息的办事处银行，在确认、复核、入账状态为流水中的办事处银行）
  public String[] queryOfficeBankNames(String orgId,String openStatus,String bizId,String bizType,SecurityInfo securityInfo ) throws Exception;
  public List querytest(String id) throws Exception;
  public String FindAA103_DayTime(String collbankid) throws Exception;
  public String queryNoteNum() throws Exception;
  public String[] queryBankInfor(String bankId) throws Exception;
  public boolean isOrgoverpay_1(String notenum,String account,SecurityInfo securityInfo) throws Exception;
  public String queryOrgOverPayAccount(String orgId,SecurityInfo securityInfo) throws Exception;
}

