package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.bsinterface;

import java.io.Serializable;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.form.OrgpaymentAF;

public interface IOrgpaymentstatisticsBS {

  
  //单位缴存情况列表
  public OrgpaymentAF queryOrgpayInfoList(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
  //职工缴存情况列表
  public OrgpaymentAF queryEmppayInfoList(Pagination pagination,SecurityInfo securityInfo)throws Exception,BusinessException;
  public Org findOrgInfo(Serializable id,String status,SecurityInfo securityInfo) throws BusinessException;
  public Emp findEmpInfo(String empid,SecurityInfo securityInfo) throws BusinessException ;
  public List findEmpList(Pagination pagination,SecurityInfo securityInfo) throws Exception ;
}
