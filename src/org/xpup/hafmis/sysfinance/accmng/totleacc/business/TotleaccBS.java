package org.xpup.hafmis.sysfinance.accmng.totleacc.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.totleacc.bsinterface.ITotleaccBS;
import org.xpup.hafmis.sysfinance.accmng.totleacc.dto.TotleaccDTO;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.BookParameterDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;

public class TotleaccBS implements ITotleaccBS {

  private SubjectDAO subjectDAO = null;

  private AccountantCredenceDAO accountantCredenceDAO = null;

  private BookParameterDAO bookParameterDAO = null;

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setBookParameterDAO(BookParameterDAO bookParameterDAO) {
    this.bookParameterDAO = bookParameterDAO;
  }

  /**
   * 根据条件查询总账列表
   */
  public List querylistaccmngList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List returnlist = new ArrayList();
    try {
      String year = (String) pagination.getQueryCriterions().get("bizyear");
      String starperiod = (String) pagination.getQueryCriterions().get(
          "starperiod");
      String endperiod = (String) pagination.getQueryCriterions().get(
          "endperiod");
      String starsubject = (String) pagination.getQueryCriterions().get(
          "starsubject");
      String endsubject = starsubject;
      String level = "1";
      String office = (String) pagination.getQueryCriterions().get("office");
      if (office.equals("全部")) {
        office = null;
      }
      // 判断会计期间
      String yearmonth = this.getMinYearmonth_WL(securityInfo);
      String yearBydate = yearmonth.substring(0, 4);
      String monthBydate = yearmonth.substring(4, 6);
      if (Integer.parseInt(year) < Integer.parseInt(yearBydate)) {
        return returnlist;
      } else if (Integer.parseInt(year) == Integer.parseInt(yearBydate)) {
        if ((Integer.parseInt(starperiod) <= Integer.parseInt(monthBydate))
            && (Integer.parseInt(endperiod) <= Integer.parseInt(monthBydate))) {
          return returnlist;
        } else if ((Integer.parseInt(starperiod) <= Integer
            .parseInt(monthBydate))
            && (Integer.parseInt(endperiod) > Integer.parseInt(monthBydate))) {
          starperiod = "" + (Integer.parseInt(monthBydate) + 1);
          if (starperiod.length() == 1) {
            starperiod = "0" + starperiod;
          }
        }
      }
      int number = 0;
      // 得到所查询的科目字符串数组
      String subjectcode[] = subjectDAO.findSubjectCodesInfo(securityInfo
          .getBookId(), starsubject, endsubject, level);
      for (int i = 0; i < subjectcode.length; i++) {// 查询明细列表
        BigDecimal value = new BigDecimal(0.00);
        BigDecimal x = new BigDecimal(0.00);
        TotleaccDTO listaccDTO = null;
        TotleaccDTO listaccDTO_bgn = null;
        TotleaccDTO listaccDTO_year = null;
        BigDecimal debit = new BigDecimal(0.00);// 借方金额
        BigDecimal credit = new BigDecimal(0.00);// 贷方金额
        String termsum = "";// 保存期合计的提示
        int size = returnlist.size();
        number = 0;
        if (true) {
          int count = 0;
          listaccDTO_bgn = new TotleaccDTO();
          // 科目代码
          listaccDTO_bgn.setSubjectCode(subjectcode[i]);
          // 科目名称
          listaccDTO_bgn.setSubjectname(subjectDAO
              .querySubjectNameBySubjectCode(subjectcode[i], securityInfo
                  .getBookId()));
          // 日期
          listaccDTO_bgn.setCredenceDate(year.concat("-").concat(starperiod)
              .concat("-01"));
          // 摘要
          if (starperiod.equals("01")) {
            listaccDTO_bgn.setSummay(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_LASTYEARCLEAR, BusiConst.FNSUMMARY));
          } else {
            listaccDTO_bgn.setSummay(BusiTools.getBusiValue_WL(
                BusiConst.FNSUMMARY_BGNBLAN, BusiConst.FNSUMMARY));
          }
          // 期初余额查询方法
          value = accountantCredenceDAO
              .queryBgnblanceBySubjectcode_Wsh(subjectcode[i], year,
                  starperiod, endperiod, office, securityInfo);
          x = value;
          // money = value;// 余额
          // 获得发生方向
          listaccDTO_bgn.setDirtection(this.getDirtection(value));
          // 余额
          if (value.compareTo(new BigDecimal(0.00)) < 0) {
            listaccDTO_bgn.setMoney(value.abs());
          } else {
            listaccDTO_bgn.setMoney(value);
          }
          if (listaccDTO_bgn.getMoney().compareTo(new BigDecimal(0.00)) > 0) {
            number++;
          }
          returnlist.add(listaccDTO_bgn);
          count = Integer.parseInt(endperiod) - Integer.parseInt(starperiod);
          int months = count;
          int month = Integer.parseInt(starperiod) - 1;

          for (int j = 0; j < months + 1; j++) {
            listaccDTO = new TotleaccDTO();
            int number1 = 0;
            debit = new BigDecimal(0.00);// 借方金额
            credit = new BigDecimal(0.00);// 贷方金额
            String date = "10";
            month = month + 1;
            if (month < 10) {
              date = String.valueOf(month);
              date = "0" + date;
            } else {
              date = String.valueOf(month);
            }
            if (true) {
              // ------保存期初余额
              // ------保存逐条信息
              // ----------------------转换--------------------------
              // 本期合计
              // 科目代码
              listaccDTO.setSubjectCode(subjectcode[i]);
              // 科目名称
              listaccDTO.setSubjectname(subjectDAO
                  .querySubjectNameBySubjectCode(listaccDTO.getSubjectCode(),
                      securityInfo.getBookId()));
              // 日期
              termsum = accountantCredenceDAO.findLastDay_WL(year.concat("-")
                  .concat(date).concat("-01"));
              listaccDTO.setCredenceDate(year.concat("-").concat(date).concat(
                  "-").concat(termsum));
              // 凭证字号,要取得最大凭证号和最小凭证号来查询凭证字组合成 "银1—20，转3－40" 形式
              // 取出了fn201中的最小凭证号
              String minNum = accountantCredenceDAO
                  .getDocumentNumberMinOrMax_wsh("min", office, date, date,
                      securityInfo.getBookId(), year, securityInfo,
                      subjectcode[i]);
              // 根据最小凭证号得到最小凭证字的主键

              if (minNum != null) {
                // String minCharacter = accountantCredenceDAO
                // .getDocumentCharacterrMinOrMax_wsh(minNum, office, date,
                // date, securityInfo.getBookId(), year, securityInfo,
                // subjectcode[i]);
                // if ((minCharacter != null) && (!minCharacter.equals(""))) {
                // listaccDTO
                // .setCredenceCharacter(bookParameterDAO
                // .queryParamExplainInfo_WL(minCharacter, securityInfo,
                // "2").concat(" - "));
                // listaccDTO.setCredcharnum(listaccDTO.getCredenceCharacter());
                // }
                listaccDTO.setCredcharnum(minNum);
              }

              // 取出了fn201中的最大凭证号
              String maxNum = accountantCredenceDAO
                  .getDocumentNumberMinOrMax_wsh("max", office, date, date,
                      securityInfo.getBookId(), year, securityInfo,
                      subjectcode[i]);
              // 根据最小凭证号得到最小凭证字的主键

              if (maxNum != null) {
                // String maxCharacter = accountantCredenceDAO
                // .getDocumentCharacterrMinOrMax_wsh(maxNum, office, date,
                // date, securityInfo.getBookId(), year, securityInfo,
                // subjectcode[i]);
                // if ((maxCharacter != null) && (!maxCharacter.equals(""))) {
                // listaccDTO.setCredenceCharacter(","
                // .concat(bookParameterDAO.queryParamExplainInfo_WL(
                // maxCharacter, securityInfo, "2").concat(" - ")));
                // listaccDTO.setCredcharnum(listaccDTO.getCredcharnum().concat(
                // listaccDTO.getCredenceCharacter()));
                // }
                listaccDTO.setCredcharnum(listaccDTO.getCredcharnum()
                    .concat("").concat(maxNum));
              }

              // 摘要
              listaccDTO.setSummay(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_TERMSUM, BusiConst.FNSUMMARY));
              // 借方
              debit = accountantCredenceDAO.queryTermlendmnt_WL(subjectcode[i],
                  year, date, date, office, securityInfo);

              if (debit == null) {
                debit = new BigDecimal(0.00);
              }
              if (debit.compareTo(new BigDecimal(0.00)) == 0) {
                number1++;
              }
              listaccDTO.setDebit(debit);
              // 贷方
              credit = accountantCredenceDAO.queryTermloanmnt_WL(
                  subjectcode[i], year, date, date, office, securityInfo);
              if (credit == null) {
                credit = new BigDecimal(0.00);
              }
              if (credit.compareTo(new BigDecimal(0.00)) == 0) {
                number1++;
              }
              // if (number1 == 2) {
              // continue;
              // }
              x = debit.subtract(credit).add(x);
              listaccDTO.setCredit(credit);
              // 方向
              BigDecimal flag = new BigDecimal(0.00);
              if (listaccDTO_bgn.getDirtection() == "贷") {
                flag = listaccDTO_bgn.getMoney().multiply(new BigDecimal(-1));
              } else {
                flag = listaccDTO_bgn.getMoney();
              }
              listaccDTO.setDirtection(this.getDirtection(debit
                  .subtract(credit).add(flag)));
              // 余额
              listaccDTO.setMoney(debit.subtract(credit).add(flag));
              if (listaccDTO.getMoney().compareTo(new BigDecimal(0.00)) > 0) {
                number++;
              }
              returnlist.add(listaccDTO);
              // -------保存本年累计
              listaccDTO_year = new TotleaccDTO();
              // 科目代码
              listaccDTO_year.setSubjectCode(subjectcode[i]);
              // 科目名称
              listaccDTO_year.setSubjectname(subjectDAO
                  .querySubjectNameBySubjectCode(subjectcode[i], securityInfo
                      .getBookId()));
              // 日期 当期最后一天
              termsum = accountantCredenceDAO.findLastDay_WL(year
                  + "-".concat(date).concat("-01"));
              listaccDTO_year.setCredenceDate(year + "-".concat(date)
                  + "-".concat(termsum));

              // 摘要
              listaccDTO_year.setSummay(BusiTools.getBusiValue_WL(
                  BusiConst.FNSUMMARY_YEARSUM, BusiConst.FNSUMMARY));
              // 借方
              debit = accountantCredenceDAO.queryTermlendmnt_WL(subjectcode[i],
                  year, "01", date, office, securityInfo);
              if (debit == null) {
                debit = new BigDecimal(0.00);
              }
              listaccDTO_year.setDebit(debit);
              // 贷方
              credit = accountantCredenceDAO.queryTermloanmnt_WL(
                  subjectcode[i], year, "01", date, office, securityInfo);
              if (credit == null) {
                credit = new BigDecimal(0.00);
              }
              listaccDTO_year.setCredit(credit);
              // 方向
              flag = new BigDecimal(0.00);
              if (listaccDTO_bgn.getDirtection() == "贷") {

                flag = listaccDTO_bgn.getMoney().multiply(new BigDecimal(-1));
              } else {
                flag = listaccDTO_bgn.getMoney();
              }
              listaccDTO_year.setDirtection(this.getDirtection(x));
              // 方向
              listaccDTO.setDirtection(this.getDirtection(x));

              // 余额
              listaccDTO.setMoney(x.abs());
              listaccDTO_year.setMoney(x.abs());
              if (listaccDTO_year.getMoney().compareTo(new BigDecimal(0.00)) > 0) {
                number++;
              }
              returnlist.add(listaccDTO_year);
            }
          }

        }
        pagination.setNrOfElements(returnlist.size());
        // if (number == 0) {
        // int listCount = 0;
        // listCount = returnlist.size();
        // for (int m = listCount - 1; m >= size; m--) {
        // returnlist.remove(m);
        // }
        // }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return returnlist;
  }

  /**
   * 获得发生方向
   * 
   * @param value
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public String getDirtection(BigDecimal value) throws Exception,
      BusinessException {
    String dirtection = "";
    try {
      if (value.compareTo(new BigDecimal(0.00)) == 0) {
        dirtection = BusiTools.getBusiValue_WL(BusiConst.BALANCEDIRECTION_AVE,
            BusiConst.BALANCEDIRECTION);
      } else if (value.compareTo(new BigDecimal(0.00)) > 0) {
        dirtection = BusiTools.getBusiValue_WL(
            BusiConst.BALANCEDIRECTION_DEBIT, BusiConst.BALANCEDIRECTION);
      } else {
        dirtection = BusiTools.getBusiValue_WL(
            BusiConst.BALANCEDIRECTION_CREDIT, BusiConst.BALANCEDIRECTION);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dirtection;
  }

  /**
   * 检查页面录入的科目是否存在并且为一级科目
   */
  public String checkSubjectcode(String subjectcode, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    String message = "";
    try {
      Integer flag = subjectDAO.findSubjectrelationFirstCode(subjectcode,
          securityInfo);
      if (flag.intValue() == 0) {
        message = "录入的科目代码要求是已经存在并且为一级的科目！！";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return message;
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
   * 得到数据库中余额初始的年月
   */
  public String getMinYearmonth_WL(SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String yearmonth = "";
    try {
      yearmonth = accountantCredenceDAO.getMinYearmonth_WL(securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return yearmonth;
  }
}
