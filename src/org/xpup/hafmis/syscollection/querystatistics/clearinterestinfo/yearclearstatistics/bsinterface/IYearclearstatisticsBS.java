package org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto.YearclearstatisticsHeadDTO;

public interface IYearclearstatisticsBS {
  // 显示合计信息
  public YearclearstatisticsHeadDTO findYearclearstatisticsListHead(List list) throws Exception, BusinessException;
  // 根据条件查询年终结息全部列表
  public List findYearclearstatisticsAllByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  // 显示职工列表合计信息
  public YearclearstatisticsHeadDTO findYearclearEmpListHead(List list) throws Exception, BusinessException;
  // 根据条件查询职工列表年终结息全部列表
  public List findYearclearEmpAllByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  // 根据条件查询单个职工列表年终结息
  public List findYearclearEmpByCriterions(Pagination pagination,SecurityInfo securityInfo) throws Exception, BusinessException;
  public String findOrg(String orgId) throws Exception, BusinessException;
  public String clearperson(String bizId) throws Exception, BusinessException;
  public String queryMakerPara() throws Exception;
}
