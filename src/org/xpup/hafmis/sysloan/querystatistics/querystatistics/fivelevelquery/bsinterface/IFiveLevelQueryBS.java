/**
 * Copy Right Information   : Goldsoft 
 * Project                  : IFiveLevelQueryBS
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   :2007-10-19
 **/
package org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.form.FiveLevelQueryAF;

public interface IFiveLevelQueryBS {

  // 五级分类统计查询列表
  public FiveLevelQueryAF queryFiveLevelQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  // 获取担保公司信息，用于显示下拉框
  public List getAssistantOrgNameList() throws Exception;
}
