package org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interestgl.form.InterestglTaAF;

public interface IInterestglBS {
  public String getMydate() throws Exception;
  // 获取担保公司信息，用于显示下拉框
  public List getAssistantOrgNameList() throws Exception;
  // 担保方式统计查询列表
  public InterestglTaAF queryListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
//根据月查询
  public InterestglTaAF queryListByMonth(String id,String date,Pagination pagination,
      SecurityInfo securityInfo,String radioValue,String floorname) throws Exception, BusinessException;
//根据日查询
  public InterestglTaAF queryListByDay(String id,String date,Pagination pagination,
      SecurityInfo securityInfo,String radioValue,String floorname) throws Exception, BusinessException;
}
