package org.xpup.hafmis.sysfinance.accmng.subjectdayreport.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.hafmis.sysfinance.accmng.subjectdayreport.bsinterface.ISubjectdayreportBS;
import org.xpup.hafmis.sysfinance.accmng.subjectdayreport.dto.SubjectdayreportDTO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;

/**
 * Copy Right Information : 科目日报表 Project : 文件名
 * 
 * @Version : 1.0
 * @author : 张列 生成日期 : 10-26-2007
 */
public class SubjectdayreportBS implements ISubjectdayreportBS {

  private BookParameterDAO bookParameterDAO = null;

  private SubjectDAO subjectDAO = null;

  public BookParameterDAO getBookParameterDAO() {
    return bookParameterDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  public SubjectDAO getSubjectDAO() {
    return subjectDAO;
  }

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  /**
   * 获得账套科目级数
   */
  public String querySubjectdayreportParamValue(String bookId) throws Exception {
    try {
      return this.bookParameterDAO.getParamValue(bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0";
  }

  /**
   * 判断科目代码是否是一级科目
   * 
   * @param bookId
   * @param subjectCode
   * @return
   */
  public boolean isSubjectCodeOneLevel(String bookId, String subjectCode)
      throws Exception {
    try {
      String temp_code = this.subjectDAO.getSubjectCodeOneLevle(bookId,
          subjectCode);
      int temp_num = Integer.parseInt(temp_code);
      if (temp_num > 0) {
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * 根据条件获得subject_code
   */
  public String[] querySubjectdayreportSubjectCodes(String f110_bookId,
      String subjectCodeStart, String subjectCodeEnd, String subjectLevelEnd)
      throws Exception {
    // TODO Auto-generated method stub
    try {
      return subjectDAO.findSubjectCodesInfo(f110_bookId, subjectCodeStart,
          subjectCodeEnd, subjectLevelEnd);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据科目代码和bookid 获得科目名称
   * 
   * @param bookId
   * @param subjectCode
   * @return
   * @throws Exception
   */
  public String querySubjectNameBySubjectCode(String bookId, String subjectCode)
      throws Exception {
    try {
      return subjectDAO.querySubjectNameBySubjectCode(subjectCode, bookId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 根据条件获得 昨日余额
   */
  public String queryYesterdayRemainingSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception {
    try {
      String yesterdayRemainingSum = subjectDAO
          .queryYesterdayRemainingSumBySubjectCode(subjectCode, bookId,
              credenceDate, officeName, "2", "");
      if (yesterdayRemainingSum.equals("0")) {
        return "0.00";
      }
      return yesterdayRemainingSum;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0.00";
  }

  /**
   * 根据条件获得 今日借方
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   * @throws Exception
   */
  public String queryTodayDebitBySubjectCode(String subjectCode, String bookId,
      String credenceDate, String officeName) throws Exception {
    try {
      String todayDebit = subjectDAO.queryTodayDebitBySubjectCode(subjectCode,
          bookId, credenceDate, officeName, "2", "");
      if (todayDebit.equals("0")) {
        return "0.00";
      }
      return todayDebit;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0.00";
  }

  /**
   * 根据条件获得 今日贷方
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   * @throws Exception
   */
  public String queryTodayCreditBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception {
    try {
      String todayCredit = subjectDAO.queryTodayCreditBySubjectCode(
          subjectCode, bookId, credenceDate, officeName, "2", "");
      if (todayCredit.equals("0")) {
        return "0.00";
      }
      return todayCredit;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0.00";
  }

  /**
   * 根据条件获得 今日余额
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   * @throws Exception
   */
  public String queryTodayRemainingSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception {
    try {
      String todayRemainingSum = subjectDAO
          .queryTodayRemainingSumBySubjectCode(subjectCode, bookId,
              credenceDate, officeName, "2", "");
      if (todayRemainingSum.equals("0")) {
        return "0.00";
      }
      return todayRemainingSum;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0.00";
  }

  /**
   * 根据条件获得 借贷方向
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   * @throws Exception
   */
  public String queryDirectionBySubjectCode(String subjectCode, String bookId,
      String credenceDate, String officeName) throws Exception {
    try {
      String direction = subjectDAO.queryDirectionBySubjectCode(subjectCode,
          bookId, credenceDate, officeName, "2", "");
      double temp_direction = Double.parseDouble(direction);
      if (temp_direction > 0) {
        return "借";
      }
      if (temp_direction < 0) {
        return "贷";
      }
      if (temp_direction == 0) {
        return "平";
      }
      return direction;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 根据条件获得 今日借方笔数
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   * @throws Exception
   */
  public String queryTodayDebitSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception {
    try {
      String todayDebitSum = subjectDAO.queryTodayDebitSumBySubjectCode(
          subjectCode, bookId, credenceDate, officeName, "2", "");
      if (todayDebitSum.equals("0")) {
        return "0";
      }
      return todayDebitSum;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0";
  }

  /**
   * 根据条件获得 今日借方笔数
   * 
   * @param subjectCode
   * @param bookId
   * @param credenceDate
   * @param officeName
   * @return
   * @throws Exception
   */
  public String queryTodayCreditSumBySubjectCode(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception {
    try {
      String todayCreditSum = subjectDAO.queryTodayCreditSumBySubjectCode(
          subjectCode, bookId, credenceDate, officeName, "2", "");
      if (todayCreditSum.equals("0")) {
        return "0";
      }
      return todayCreditSum;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "0";
  }

  /**
   * 根据条件获得 一条数据
   */
  public SubjectdayreportDTO findSubjectdayreportInfo(String subjectCode,
      String bookId, String credenceDate, String officeName) throws Exception {
    try {
      return subjectDAO.findSubjectdayreportInfo(subjectCode, bookId,
          credenceDate, officeName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 根据条件获得 一条数据
   */
  public List findSubjectdayreportAllInfo(String bookId, String credenceDate,
      String officeName, String subjectCodeStart, String subjectCodeEnd,
      String subjectLevelEnd) throws Exception {
    List list = new ArrayList();
    try {
      // 根据条件查询科目代码
      String[] temp = this.querySubjectdayreportSubjectCodes(bookId,
          subjectCodeStart, subjectCodeEnd, subjectLevelEnd);
      for (int i = 0; i < temp.length; i++) {
        // 科目代码号
        String subjectCode = temp[i].toString();
        // 放入DTO值
        SubjectdayreportDTO subjectdayreportDTO = new SubjectdayreportDTO();
        // 根据条件获得所有数据
        subjectdayreportDTO = this.findSubjectdayreportInfo(subjectCode,
            bookId, credenceDate, officeName);
        if(subjectdayreportDTO.getYesterdayRemainingSum().compareTo(new BigDecimal(0.00)) == 0 &&
            subjectdayreportDTO.getTodayDebit().compareTo(new BigDecimal(0.00)) == 0 &&
            subjectdayreportDTO.getTodayCredit().compareTo(new BigDecimal(0.00)) == 0 &&
            subjectdayreportDTO.getTodayRemainingSum().compareTo(new BigDecimal(0.00)) == 0 &&
            subjectdayreportDTO.getTodayDebitSum() == 0 &&
            subjectdayreportDTO.getTodayCreditSum() == 0){
          continue;
        }
        subjectdayreportDTO.setSubjectCode(subjectCode);
        subjectdayreportDTO.setBookId(bookId);
        // 根据科目代码 得到科目名称
        String subjectName = this.querySubjectNameBySubjectCode(bookId,
            subjectCode);
        subjectdayreportDTO.setSubjectName(subjectName);
        list.add(subjectdayreportDTO);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}
