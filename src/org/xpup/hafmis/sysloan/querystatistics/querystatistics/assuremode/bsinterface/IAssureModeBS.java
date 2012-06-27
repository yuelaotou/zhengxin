package org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form.AssureModeAF;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.form.FloorPOPNewAF;

/**
 * @author 王野 2007-10-11
 */
public interface IAssureModeBS {

  // 担保方式统计查询列表
  public AssureModeAF queryAssureModeListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 获取担保公司信息，用于显示下拉框
  public List getAssistantOrgNameList() throws Exception;

  // 获得楼盘名称
  public FloorPOPNewAF findFloorInfoList(Pagination pagination) throws Exception,
      BusinessException;
}
