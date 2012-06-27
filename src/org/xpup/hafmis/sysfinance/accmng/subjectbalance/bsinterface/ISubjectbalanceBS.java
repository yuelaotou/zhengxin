package org.xpup.hafmis.sysfinance.accmng.subjectbalance.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information : 科目余额表 Project : 文件名
 * 
 * @Version : 1.0
 * @author : 张列 生成日期 : 11-02-2007
 */
public interface ISubjectbalanceBS {
  // 获得账套科目级数
  public String querySubjectbalanceParamValue(String bookId) throws Exception;

  // 判断科目代码是否是一级科目
  public boolean isSubjectCodeOneLevel(String bookId, String subjectCode)
      throws Exception;

  // 根据科目类别查询 科目代码
  public String[] findSubjectCodesInfoBySubjectSortCode(String bookId,
      String subjectCodeStart, String subjectCodeEnd, String subjectLevel)
      throws Exception;

  // 根据条件 返回科目余额表信息
  public List findSubjectbalanceInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;

  public String queryMakerPara() throws Exception;

  public String queryCountCredenceNum(String bookId, String yearMonth);
}
