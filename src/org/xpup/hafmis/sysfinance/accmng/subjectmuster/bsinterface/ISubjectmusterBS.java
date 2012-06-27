package org.xpup.hafmis.sysfinance.accmng.subjectmuster.bsinterface;

import java.util.List;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

/**
 * Copy Right Information : 凭证汇总 Project : 文件名
 * 
 * @Version : 1.0
 * @author : 张列 生成日期 : 11-06-2007
 */
public interface ISubjectmusterBS {

  // 获得凭证汇总所有信息
  public List findSubjectmusterInfo(String bookId, String credenceDateStart,
      String credenceDateEnd, String officeName, String credenceNumStart,
      String credenceNumEnd,String subjectLevel,SecurityInfo securityInfo) throws Exception;
  public String queryMaxCredenceNum(String office, String yearmonth, String bookid)
  throws Exception ;
  //获得账套科目级数
  public String querySubjectbalanceParamValue(String bookId) throws Exception;
  public String getNamePara() throws Exception ;
}
