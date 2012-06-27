/**
 * Copy Right Information   : Goldsoft 
 * Project                  : OrgVerAccountBalanceDTO
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-12-19
 **/
package org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.bsinterface;

import java.io.Serializable;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.form.OrgVerAccountBalanceAF;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;

public interface IOrgVerAccountBalanceBS {

  // 查询单位信息
  public Org queryOrgInfo(String orgId, SecurityInfo securityInfo)
      throws BusinessException;
  
  // 查询单位名称
  public Org findOrgInfo(String orgid, SecurityInfo securityInfo)
      throws BusinessException;

  // 查询单位版余额结转列表
  public OrgVerAccountBalanceAF queryOVAccountBalanceListByCriterions(
      String accYear, Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 结转余额
  public void doOrgVerAccountBalance(Serializable id, String docNum, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

}
