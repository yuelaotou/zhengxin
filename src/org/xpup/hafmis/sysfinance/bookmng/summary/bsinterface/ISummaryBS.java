package org.xpup.hafmis.sysfinance.bookmng.summary.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.summary.dto.SummaryDTO;

/**
 * Copy Right Information   : 常用摘要
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-25-2007
 */
public interface ISummaryBS {
  //返回查询结果(List) 常用摘要信息
  public List findSummaryList(Pagination pagination, String bookId) throws Exception;
  //返回查询结果记录数
  public int querySummaryCount(String bookId) throws Exception;
  //判断输入的常用摘要在FN102.PARAM_NUM=4的记录的PARAM_EXPLAIN是否存在(用于插入)
  public boolean is_SummaryParamExplainInsert(SummaryDTO summaryDTO) throws Exception;
  //插入FN311   插入FN102
  public void insertSummaryInfo(SummaryDTO summaryDTO,SecurityInfo securityInfo)throws Exception;
  //根据ID判断记录是否存在
  public boolean isSummaryById(String paraId) throws Exception;
  //判断输入的常用摘要在FN102.PARAM_NUM=4的记录的PARAM_EXPLAIN是否存在(修改用)
  public boolean is_SummaryParamExplainUpdate(SummaryDTO summaryDTO) throws Exception ;
  public boolean querySummaryInFn201(String bookId,String  summaryId) throws Exception ;
  //插入FN311   更新FN102
  public void updateSummaryInfo(SummaryDTO summaryDTO, SecurityInfo securityInfo) throws Exception;
  //根据ID 查询常用摘要
  public SummaryDTO querySummaryParamExplainInfo(String paraId) throws Exception;
  //判断该记录的FN102.PARA_ID在FN201.SUMMAY or FN210.SUMMAY中是否存在
  public boolean isSummaryByParamValue(String paraId,String bookId) throws Exception;
  //删除 FN102   插入FN311日志
  public void deleteSummaryInfo(String paraId,SecurityInfo securityInfo) throws Exception;
}
