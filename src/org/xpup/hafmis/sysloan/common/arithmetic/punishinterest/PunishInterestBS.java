package org.xpup.hafmis.sysloan.common.arithmetic.punishinterest;

import java.math.BigDecimal;

import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;

public abstract class PunishInterestBS {
  /**
   * 临时利息（还款参数，序号为4中的参数值=1） PL003中类型为：还款参数，序号为4中的参数值=1（逐条计算）
   * 临时利息=（会计日期-每月对应的还款日）*参数说明PARAM_EXPLAIN/100*还款表(应还本金-本金+应还利息-利息)
   * 
   * @param bizDate 会计日期
   * @param loanKouYearmonth 还款年月
   * @param loanRepayDay 还款日
   * @param shouldCorpus 应还本金
   * @param realCorpus 实还本金
   * @param shouldInterest 应还利息
   * @param realInterest 实还利息
   * @param paramExplain 参数说明
   * @return
   */
  public static BigDecimal getTempInterestA(String bizDate,
      String loanKouYearmonth, String loanRepayDay, BigDecimal shouldCorpus,
      BigDecimal realCorpus, BigDecimal shouldInterest,
      BigDecimal realInterest, String paramExplain) {
    BigDecimal interest = new BigDecimal(0.00);
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    // 还款日期
    String loanKouDate = loanKouYearmonth.substring(0, 4) + "-"
        + loanKouYearmonth.substring(4, 6) + "-" + loanRepayDay;
    int days = BusiTools.minusDate(bizDate, loanKouDate);
    interest = new BigDecimal(days).multiply(new BigDecimal(paramExplain))
        .multiply(
            shouldCorpus.subtract(realCorpus).add(
                shouldInterest.subtract(realInterest))).divide(
            new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
    return interest;
  }

  /**
   * 临时利息（还款参数，序号为4中的参数值=2） PL003中类型为：还款参数，序号为4中的参数值=2（逐条计算）
   * 临时利息=（会计日期-每月对应的还款日）*参数说明PARAM_EXPLAIN*还款表贷款利率LOAN_RATE/30/100*(应还本金-本金+应还利息-利息)
   * 
   * @param bizDate
   * @param loanKouYearmonth
   * @param loanRepayDay
   * @param shouldCorpus
   * @param realCorpus
   * @param shouldInterest
   * @param realInterest
   * @param paramExplain
   * @param loanRate 月利率
   * @return
   */
  public static BigDecimal getTempInterestB(String bizDate,
      String loanKouYearmonth, String loanRepayDay, BigDecimal shouldCorpus,
      BigDecimal realCorpus, BigDecimal shouldInterest,
      BigDecimal realInterest, String paramExplain, BigDecimal loanRate) {
    BigDecimal interest = new BigDecimal(0.00);
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    // 还款日期
    String loanKouDate = loanKouYearmonth.substring(0, 4) + "-"
        + loanKouYearmonth.substring(4, 6) + "-" + loanRepayDay;
    int days = BusiTools.minusDate(bizDate, loanKouDate);
    interest = new BigDecimal(days).multiply(new BigDecimal(paramExplain))
        .multiply(loanRate).multiply(
            shouldCorpus.subtract(realCorpus).add(
                shouldInterest.subtract(realInterest))).divide(
            new BigDecimal(3000), 2, BigDecimal.ROUND_HALF_UP);
    return interest;
  }

  /**
   * 临时利息（还款参数，序号为4中的参数值=3） PL003中类型为：还款参数，序号为4中的参数值=3（逐条计算)
   * 临时利息=（会计日期-每月对应的还款日）*参数说明PARAM_EXPLAIN
   * 
   * @param bizDate
   * @param loanKouYearmonth
   * @param loanRepayDay
   * @param paramExplain
   * @return
   */
  public static BigDecimal getTempInterestC(String bizDate,
      String loanKouYearmonth, String loanRepayDay, String paramExplain) {
    BigDecimal interest = new BigDecimal(0.00);
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    // 还款日期
    String loanKouDate = loanKouYearmonth.substring(0, 4) + "-"
        + loanKouYearmonth.substring(4, 6) + "-" + loanRepayDay;
    int days = BusiTools.minusDate(bizDate, loanKouDate);
    interest = new BigDecimal(days).multiply(new BigDecimal(paramExplain))
        .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
    return interest;
  }

  /**
   * 临时利息（还款参数，序号为4中的参数值=1） PL003中类型为：还款参数，序号为4中的参数值=1（逐条计算)
   * 临时利息=罚息利息=（会计日期-每月对应的还款日-宽限天数）*参数说明/100*(应还本金-本金+应还利息-利息)
   * 
   * @param bizDate
   * @param loanKouYearmonth
   * @param loanRepayDay
   * @param shouldCorpus
   * @param realCorpus
   * @param shouldInterest
   * @param realInterest
   * @param paramExplain
   * @param allowdays 宽限天数
   * @return
   */
  public static BigDecimal getTempInterestD(String bizDate,
      String loanKouYearmonth, String loanRepayDay, BigDecimal shouldCorpus,
      BigDecimal realCorpus, BigDecimal shouldInterest,
      BigDecimal realInterest, String paramExplain, String allowdays) {
    BigDecimal interest = new BigDecimal(0.00);
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    // 还款日期
    String loanKouDate = loanKouYearmonth.substring(0, 4) + "-"
        + loanKouYearmonth.substring(4, 6) + "-" + loanRepayDay;
    int days = BusiTools.minusDate(bizDate, loanKouDate)
        - Integer.parseInt(allowdays);
    BigDecimal temp_money = shouldCorpus.subtract(realCorpus).add(
        shouldInterest.subtract(realInterest));
    interest = new BigDecimal(days).multiply(new BigDecimal(paramExplain))
        .multiply(temp_money).divide(new BigDecimal(1), 2,
            BigDecimal.ROUND_HALF_UP);
    return interest;
  }

  /**
   * 临时利息（还款参数，序号为4中的参数值=2） PL003中类型为：还款参数，序号为4中的参数值=2（逐条计算)
   * 临时利息=罚息利息=（会计日期-每月对应的还款日-宽限天数）*参数说明*还款表贷款利率LOAN_RATE/30/100*(应还本金-本金+应还利息-利息)
   * 
   * @param bizDate
   * @param loanKouYearmonth
   * @param loanRepayDay
   * @param shouldCorpus
   * @param realCorpus
   * @param shouldInterest
   * @param realInterest
   * @param paramExplain
   * @param loanRate
   * @param allowdays 宽限天数
   * @return
   */
  public static BigDecimal getTempInterestE(String bizDate,
      String loanKouYearmonth, String loanRepayDay, BigDecimal shouldCorpus,
      BigDecimal realCorpus, BigDecimal shouldInterest,
      BigDecimal realInterest, String paramExplain, BigDecimal loanRate,
      String allowdays) {
    BigDecimal interest = new BigDecimal(0.00);
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    // 还款日期
    String loanKouDate = loanKouYearmonth.substring(0, 4) + "-"
        + loanKouYearmonth.substring(4, 6) + "-" + loanRepayDay;
    int days = BusiTools.minusDate(bizDate, loanKouDate)
        - Integer.parseInt(allowdays);
    interest = new BigDecimal(days).multiply(new BigDecimal(paramExplain))
        .multiply(loanRate).multiply(
            shouldCorpus.subtract(realCorpus).add(
                shouldInterest.subtract(realInterest))).divide(
            new BigDecimal(3000), 2, BigDecimal.ROUND_HALF_UP);
    return interest;
  }

  /**
   * 临时利息（还款参数，序号为4中的参数值=3） PL003中类型为：还款参数，序号为4中的参数值=3（逐条计算)
   * 临时利息=罚息利息=（会计日期-每月对应的还款日-宽限天数）*参数说明
   * 
   * @param bizDate
   * @param loanKouYearmonth
   * @param loanRepayDay
   * @param paramExplain
   * @param allowdays
   * @return
   */
  public static BigDecimal getTempInterestF(String bizDate,
      String loanKouYearmonth, String loanRepayDay, String paramExplain,
      String allowdays) {
    BigDecimal interest = new BigDecimal(0.00);
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    // 还款日期
    String loanKouDate = loanKouYearmonth.substring(0, 4) + "-"
        + loanKouYearmonth.substring(4, 6) + "-" + loanRepayDay;
    int days = BusiTools.minusDate(bizDate, loanKouDate)
        - Integer.parseInt(allowdays);
    interest = new BigDecimal(days).multiply(new BigDecimal(paramExplain))
        .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
    return interest;
  }

  /**
   * 临时利息（还款参数，序号为4中的参数值=1） PL003中类型为：还款参数，序号为4中的参数值=1（逐条计算）
   * 临时利息=（会计日期-每月对应的还款日）*参数说明PARAM_EXPLAIN/100*还款表(应还本金-本金+应还利息-利息)
   * 
   * @param bizDate 会计日期
   * @param loanKouYearmonth 还款年月
   * @param loanRepayDay 还款日
   * @param shouldCorpus 应还本金
   * @param realCorpus 实还本金
   * @param shouldInterest 应还利息
   * @param realInterest 实还利息
   * @param paramExplain 参数说明
   * @return
   */
  public static BigDecimal getTempInterestG(String bizDate, String clearDate,
      BigDecimal shouldCorpus, BigDecimal realCorpus,
      BigDecimal shouldInterest, BigDecimal realInterest, String paramExplain) {
    BigDecimal interest = new BigDecimal(0.00);
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    clearDate = clearDate.substring(0, 4) + "-" + clearDate.substring(4, 6)
        + "-" + clearDate.substring(6, 8);
    int days = BusiTools.minusDate(bizDate, clearDate);
    interest = new BigDecimal(days).multiply(new BigDecimal(paramExplain))
        .multiply(
            shouldCorpus.subtract(realCorpus).add(
                shouldInterest.subtract(realInterest))).divide(
            new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
    return interest;
  }

  /**
   * 临时利息（还款参数，序号为4中的参数值=2） PL003中类型为：还款参数，序号为4中的参数值=2（逐条计算）
   * 临时利息=（会计日期-每月对应的还款日）*参数说明PARAM_EXPLAIN*还款表贷款利率LOAN_RATE/30/100*(应还本金-本金+应还利息-利息)
   * 
   * @param bizDate
   * @param loanKouYearmonth
   * @param loanRepayDay
   * @param shouldCorpus
   * @param realCorpus
   * @param shouldInterest
   * @param realInterest
   * @param paramExplain
   * @param loanRate 月利率
   * @return
   */
  public static BigDecimal getTempInterestH(String bizDate, String clearDate,
      BigDecimal shouldCorpus, BigDecimal realCorpus,
      BigDecimal shouldInterest, BigDecimal realInterest, String paramExplain,
      BigDecimal loanRate) {
    BigDecimal interest = new BigDecimal(0.00);
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    clearDate = clearDate.substring(0, 4) + "-" + clearDate.substring(4, 6)
        + "-" + clearDate.substring(6, 8);
    int days = BusiTools.minusDate(bizDate, clearDate);
    interest = new BigDecimal(days).multiply(new BigDecimal(paramExplain))
        .multiply(loanRate).multiply(
            shouldCorpus.subtract(realCorpus).add(
                shouldInterest.subtract(realInterest))).divide(
            new BigDecimal(3000), 2, BigDecimal.ROUND_HALF_UP);
    return interest;
  }

  /**
   * 临时利息（还款参数，序号为4中的参数值=3） PL003中类型为：还款参数，序号为4中的参数值=3（逐条计算)
   * 临时利息=（会计日期-每月对应的还款日）*参数说明PARAM_EXPLAIN
   * 
   * @param bizDate
   * @param loanKouYearmonth
   * @param loanRepayDay
   * @param paramExplain
   * @return
   */
  public static BigDecimal getTempInterestI(String bizDate, String clearDate,
      String paramExplain) {
    BigDecimal interest = new BigDecimal(0.00);
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    clearDate = clearDate.substring(0, 4) + "-" + clearDate.substring(4, 6)
        + "-" + clearDate.substring(6, 8);
    int days = BusiTools.minusDate(bizDate, clearDate);
    interest = new BigDecimal(days).multiply(new BigDecimal(paramExplain))
        .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
    return interest;
  }

  /**
   * 用还款月计算
   * 
   * @param interestMode
   * @param bizDate
   * @param clearDate
   * @param shouldCorpus
   * @param realCorpus
   * @param shouldInterest
   * @param realInterest
   * @param paramExplain
   * @param loanRate
   * @return
   */
  public static BigDecimal getTempInterestByYearMonth(String interestMode,
      String bizDate, String yearMonth, String loanRepayDay,
      BigDecimal shouldCorpus, BigDecimal realCorpus,
      BigDecimal shouldInterest, BigDecimal realInterest, String paramExplain,
      BigDecimal loanRate) {
    BigDecimal temp_interest = new BigDecimal(0.00);
    if (interestMode.equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
      temp_interest = PunishInterestBS.getTempInterestA(bizDate, yearMonth,
          loanRepayDay, shouldCorpus, realCorpus, shouldInterest, realInterest,
          paramExplain);
    } else if (interestMode
        .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE + "")) {
      temp_interest = PunishInterestBS.getTempInterestB(bizDate, yearMonth,
          loanRepayDay, shouldCorpus, realCorpus, shouldInterest, realInterest,
          paramExplain, loanRate);
    } else if (interestMode
        .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE + "")) {
      temp_interest = PunishInterestBS.getTempInterestC(bizDate, yearMonth,
          loanRepayDay, paramExplain);
    }
    return temp_interest;
  }

  /**
   * 不计息 记账日期为空或记账日期小于等于还款日+宽限天数
   * 
   * @param interestMode
   * @param bizDate
   * @param yearMonth
   * @param loanRepayDay
   * @param shouldCorpus
   * @param realCorpus
   * @param shouldInterest
   * @param realInterest
   * @param paramExplain
   * @param allowdays
   * @param loanRate
   * @return
   */
  public static BigDecimal getTempInterestByAllowdays(String interestMode,
      String bizDate, String yearMonth, String loanRepayDay,
      BigDecimal shouldCorpus, BigDecimal realCorpus,
      BigDecimal shouldInterest, BigDecimal realInterest, String paramExplain,
      String allowdays, BigDecimal loanRate) {
    BigDecimal temp_interest = new BigDecimal(0.00);
    if (interestMode.equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
      temp_interest = PunishInterestBS.getTempInterestD(bizDate, yearMonth,
          loanRepayDay, shouldCorpus, realCorpus, shouldInterest, realInterest,
          paramExplain, allowdays);
    } else if (interestMode
        .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE + "")) {
      temp_interest = PunishInterestBS.getTempInterestE(bizDate, yearMonth,
          loanRepayDay, shouldCorpus, realCorpus, shouldInterest, realInterest,
          paramExplain, loanRate, allowdays);
    } else if (interestMode
        .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE + "")) {
      temp_interest = PunishInterestBS.getTempInterestF(bizDate, yearMonth,
          loanRepayDay, paramExplain, allowdays);
    }
    return temp_interest;
  }

  /**
   * 记账日期大于还款日+宽限天数
   * 
   * @param interestMode
   * @param bizDate
   * @param clearDate
   * @param shouldCorpus
   * @param realCorpus
   * @param shouldInterest
   * @param realInterest
   * @param paramExplain
   * @param loanRate
   * @return
   */
  public static BigDecimal getTempInterestByClearDate(String interestMode,
      String bizDate, String clearDate, BigDecimal shouldCorpus,
      BigDecimal realCorpus, BigDecimal shouldInterest,
      BigDecimal realInterest, String paramExplain, BigDecimal loanRate) {
    BigDecimal temp_interest = new BigDecimal(0.00);
    if (interestMode.equals(BusiConst.PLPUNISHINTERESTTYPE_PUNISHDAYRATE + "")) {
      temp_interest = PunishInterestBS.getTempInterestG(bizDate, clearDate,
          shouldCorpus, realCorpus, shouldInterest, realInterest, paramExplain);
    } else if (interestMode
        .equals(BusiConst.PLPUNISHINTERESTTYPE_CONTRACTDAYRATE + "")) {
      temp_interest = PunishInterestBS.getTempInterestH(bizDate, clearDate,
          shouldCorpus, realCorpus, shouldInterest, realInterest, paramExplain,
          loanRate);
    } else if (interestMode
        .equals(BusiConst.PLPUNISHINTERESTTYPE_PAYMENTDAYRATE + "")) {
      temp_interest = PunishInterestBS.getTempInterestI(bizDate, clearDate,
          paramExplain);
    }
    return temp_interest;
  }
}