package org.xpup.hafmis.sysfinance.accmng.subjectdayreport.bsinterface;

import java.util.List;

import org.xpup.hafmis.sysfinance.accmng.subjectdayreport.dto.SubjectdayreportDTO;

/**
 * Copy Right Information   : 科目日报表
 * Project                  : 文件名
 * @Version                 : 1.0
 * @author                  : 张列
 * 生成日期                   : 10-26-2007
 */
public interface ISubjectdayreportBS {
  // 获得FN102表中 最大的科目级次
  public String querySubjectdayreportParamValue(String bookId) throws Exception;

  // 判断科目代码是否是一级科目
  public boolean isSubjectCodeOneLevel(String bookId, String subjectCode)
      throws Exception;

  // 根据条件获得subject_code
  public String[] querySubjectdayreportSubjectCodes(String f110_bookId,
      String subjectCodeStart, String subjectCodeEnd, String subjectLevelEnd)
      throws Exception;

  // 根据科目代码和bookid 获得科目名称
  public String querySubjectNameBySubjectCode(String bookId, String subjectCode)
      throws Exception;

  // 根据条件获得 昨日余额
  public String queryYesterdayRemainingSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;

  // 根据条件获得 今日借方
  public String queryTodayDebitBySubjectCode(String subjectCode, String bookId,
      String credenceDate, String officeName) throws Exception;

  // 根据条件获得 今日贷方
  public String queryTodayCreditBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;

  // 根据条件获得 今日余额
  public String queryTodayRemainingSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;

  // 根据条件获得 借贷方向
  public String queryDirectionBySubjectCode(String subjectCode, String bookId,
      String credenceDate, String officeName) throws Exception;

  // 根据条件获得 今日借方笔数
  public String queryTodayDebitSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;

  // 根据条件获得 今日贷方笔数
  public String queryTodayCreditSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;
  
  //根据条件获得 一条数据
  public SubjectdayreportDTO findSubjectdayreportInfo(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception;
  
  //根据条件获得 全部数据
  public List findSubjectdayreportAllInfo(String bookId, String credenceDate,
      String officeName, String subjectCodeStart, String subjectCodeEnd,
      String subjectLevelEnd) throws Exception;
}
