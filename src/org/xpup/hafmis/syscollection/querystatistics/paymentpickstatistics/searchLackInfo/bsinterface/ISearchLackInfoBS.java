package org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoContentDTO;
import org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.SearchLackInfoHeadDTO;

public interface ISearchLackInfoBS {
  // 根据条件查询欠缴列表
  public List findSearchLackInfoByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 根据条件查询欠缴全部列表
  public List findSearchLackInfoAllByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 根据条件查询欠缴单笔信息
  public SearchLackInfoContentDTO findSearchLackInfoOneByCriterions(
      String orgid, Pagination pagination, SecurityInfo securityInfo)
      throws Exception, BusinessException;

  // 显示合计信息
  public SearchLackInfoHeadDTO findSearchLackInfoListHead(List list)
      throws Exception, BusinessException;

  public void createSearchLackInfo();

  public List findSearchLackInfoListByCriterions_oldsys(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  // 导出(老数据)
  public List findOrgAddPayExpList_old(String id, String orgId, String orgName,
      String addPayMonth) throws Exception, BusinessException;
}
