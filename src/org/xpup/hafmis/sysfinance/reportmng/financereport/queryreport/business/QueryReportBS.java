package org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.business;

import java.math.BigDecimal;
import java.util.StringTokenizer;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysfinance.common.dao.ReportMngDAO;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;
import org.xpup.hafmis.sysfinance.common.domain.entity.ReportMng;
import org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.bsinterface.IQueryReportBS;
import org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.form.QueryReportAF;

public class QueryReportBS implements IQueryReportBS {

  private ReportMngDAO reportMngDAO = null;

  private AccountantCredenceDAO accountantCredenceDAO = null;
  
  private SubjectDAO subjectDAO = null;

  public void setReportMngDAO(ReportMngDAO reportMngDAO) {
    this.reportMngDAO = reportMngDAO;
  }

  public void setAccountantCredenceDAO(
      AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  /**
   * 查询报表信息
   */
  public QueryReportAF queryReportMessage(QueryReportAF queryReportAF,
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    try {
      String tablename = (String) pagination.getQueryCriterions().get(
          "tablename");
      String year = (String) pagination.getQueryCriterions().get("bizyear");
      String starperiod = (String) pagination.getQueryCriterions().get(
          "starperiod");
      String endperiod = (String) pagination.getQueryCriterions().get(
          "endperiod");
      String office = (String) pagination.getQueryCriterions().get("office");
      if (office.equals("全部")) {
        office = null;
      }
      // 判断会计期间
      String yearmonth = this.getMinYearmonth_WL(securityInfo);
      String yearBydate = yearmonth.substring(0, 4);
      String monthBydate = yearmonth.substring(4, 6);
      if (Integer.parseInt(year) < Integer.parseInt(yearBydate)) {
        return queryReportAF;
      } else if (Integer.parseInt(year) == Integer.parseInt(yearBydate)) {
        if ((Integer.parseInt(starperiod) <= Integer.parseInt(monthBydate))
            && (Integer.parseInt(endperiod) <= Integer.parseInt(monthBydate))) {
          return queryReportAF;
        } else if ((Integer.parseInt(starperiod) <= Integer
            .parseInt(monthBydate))
            && (Integer.parseInt(endperiod) > Integer.parseInt(monthBydate))) {
          starperiod = "" + (Integer.parseInt(monthBydate) + 1);
          if (starperiod.length() == 1) {
            starperiod = "0" + starperiod;
          }
        }
      }
      ReportMng reportMng = reportMngDAO.queryHeadReportMessageByTablename_WL(
          tablename, securityInfo);
      pagination.getQueryCriterions().put("col", reportMng.getColspan());
      pagination.getQueryCriterions().put("row", reportMng.getRowspan());
      queryReportAF.setYearmonth(year + "年" + starperiod + "月");
      queryReportAF.setCol(reportMng.getColspan());
      queryReportAF.setRow(reportMng.getRowspan());
      queryReportAF.setTablename(reportMng.getTailtableNameChinese());

      queryReportAF = reportMngDAO.queryReportMessage_WL(queryReportAF,
          reportMng, securityInfo);
      String flag = "0";// 判断公式中是否有行公式 0:没有；1：有
      String[] colformula = new String[1000];// 上限1000，有可能不够哦！！
      int flag_col = 0;// 标识有多少个是包含行的公式数量
      for (int i = 1; i < (Integer.parseInt(reportMng.getColspan()) + 1); i++) {
        for (int j = 1; j < (Integer.parseInt(reportMng.getRowspan()) + 1); j++) {
          if ((queryReportAF.getValue("" + i + "_" + j + "") != null)
              && (!queryReportAF.getValue("" + i + "_" + j + "").toString()
                  .equals(""))) {
            queryReportAF.setValue("v" + i + "_" + j + "", queryReportAF
                .getValue("" + i + "_" + j + ""));
            if (queryReportAF.getValue("" + i + "_" + j + "").toString()
                .substring(0, 1).equals(BusiConst.REPORTLOGO_FORMULA)) {
              flag = this.getExpressValue_(queryReportAF.getValue(
                  "" + i + "_" + j + "").toString());
              if (flag.equals("1")) {
                colformula[flag_col] = "" + i + "_" + j + "";
                flag_col++;
                continue;
              }
              queryReportAF.setValue("" + i + "_" + j + "", this
                  .getExpressValue(queryReportAF
                      .getValue("" + i + "_" + j + "").toString(), year,
                      starperiod, endperiod, office, securityInfo,
                      queryReportAF, j, flag));
            }
          }
        }
      }
      // 重新计算行的公式
      for (int i = 0; i < flag_col; i++) {
        String formula_temp = colformula[i];
        queryReportAF.setValue(formula_temp, queryReportAF
            .getValue(formula_temp));
        if (queryReportAF.getValue(formula_temp).toString().substring(0, 1)
            .equals(BusiConst.REPORTLOGO_FORMULA)) {
          String[] temp_arr = formula_temp.split("_");
          queryReportAF.setValue(formula_temp, this.getExpressValue(
              queryReportAF.getValue(formula_temp).toString(), year,
              starperiod, endperiod, office, securityInfo, queryReportAF,
              Integer.parseInt(temp_arr[1]), flag));
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return queryReportAF;
  }

  /**
   * 财务报表：根据公式获得表达式的值
   * 
   * @param s
   * @param yearMonth
   * @param starperiod
   * @param endperiod
   * @param office
   * @return
   */
  public BigDecimal getExpressValue(String s, String year, String starperiod,
      String endperiod, String office, SecurityInfo securityInfo,QueryReportAF queryReportAF,int row,String flag) {
    BigDecimal value = new BigDecimal(0.00);
    try {
      BigDecimal val = new BigDecimal(0.00);
      s = s.substring(1);
      StringTokenizer substr = new StringTokenizer(s, "+");
      while (substr.hasMoreTokens()) {
        String expressBig = substr.nextToken();
        StringTokenizer substr2 = new StringTokenizer(expressBig, "-");
        int num = substr2.countTokens();
        if (num > 1) {
          int j = 0;
          while (substr2.hasMoreTokens()) {
            String express = substr2.nextToken();
            j++;
            if (j == 1) {
              val = getField(express, year, starperiod, endperiod, office,
                  securityInfo,queryReportAF,row,flag);
              value = value.add(val);
            } else {
              val = getField(express, year, starperiod, endperiod, office,
                  securityInfo,queryReportAF,row,flag);
              value = value.subtract(val);
            }
          }
        } else {
          val = getField(expressBig, year, starperiod, endperiod, office,
              securityInfo,queryReportAF,row,flag);
          value = value.add(val);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return value;
  }

  /**
   * 财务报表：根据公式获得表达式的值
   * 
   * @param express String
   * @return String
   */
  public BigDecimal getField(String express, String year, String starperiod,
      String endperiod, String office, SecurityInfo securityInfo,QueryReportAF queryReportAF,int row,String flag)
      throws Exception, BusinessException {
    BigDecimal value=null;
    String subjectcode=express.substring(2);
    try {
      if (express.substring(0, 2).equals(BusiConst.REPORTLOGO_BEGBALANCE_DEBIT)) {// 年初余额
        String direction=subjectDAO.getDirectionByCode_WL(subjectcode, securityInfo);
        value=accountantCredenceDAO.queryBgnblance_WL(subjectcode,direction, year, null, null, office, securityInfo);
        
      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_BEGBALANCE_CREDIT)) {// 期初余额
        String direction=subjectDAO.getDirectionByCode_WL(subjectcode, securityInfo);
        value=accountantCredenceDAO.queryBgnblance_WL(subjectcode, direction,year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_ENDBALANCE_DEBIT)) {// 期末余额(不用)
        String direction=subjectDAO.getDirectionByCode_WL(subjectcode, securityInfo);
        value=accountantCredenceDAO.queryEndblance_WL(subjectcode,direction, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_ENDBALANCE_CREDIT)) {// 期末余额
        String direction=subjectDAO.getDirectionByCode_WL(subjectcode, securityInfo);
        value=accountantCredenceDAO.queryEndblance_WL(subjectcode,direction, year, null, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURFIGURES_DEBIT)) {// 本期发生额（借方）
        value=accountantCredenceDAO.queryTermlendmnt_WL(subjectcode, year, starperiod, endperiod, office, securityInfo);
        
      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURFIGURES_CREDIT)) {// 本期发生额（贷方）
        value=accountantCredenceDAO.queryTermloanmnt_WL(subjectcode, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_DEBIT)) {// （原）本年发生额（借方）改为：本年累计发生额（借方）
        value=accountantCredenceDAO.queryYearlendmnt_WL(subjectcode, year, endperiod, office, securityInfo);
        
      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_CREDIT)) {// （原）本年发生额（贷方）改为：本年累计发生额（贷方）
        value=accountantCredenceDAO.queryYearloanmnt_WL(subjectcode, year, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LASTATIVEFIGURES_DEBIT)) {// （原）上年发生额（借方）改为：上年累计发生额（借方）
        value=accountantCredenceDAO.queryLastyearlentmnt_WL(subjectcode, year, "0", office, securityInfo);
        
      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LASTATIVEFIGURES_CREDIT)) {// （原）上年发生额（贷方）改为：上年累计发生额（贷方）
        value=accountantCredenceDAO.queryLastyearloanmnt_WL(subjectcode, year, "0", office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMDEBIT)) {// （原）本年累计发生额（借方）改为：期末累计发生额（借方）
        value=accountantCredenceDAO.queryTermlendmnt_WL(subjectcode, year, null, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_CURCUMULATIVEFIGURES_SUMCREDIT)) {// （原）本年累计发生额（贷方）改为：期末累计发生额（贷方）
        value=accountantCredenceDAO.queryTermloanmnt_WL(subjectcode, year, null, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMDEBIT)) {// 上年累计发生额（借方）（不用）
        value=accountantCredenceDAO.queryLastyearlentmnt_WL(subjectcode, year, "1", office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LASTATIVEFIGURES_SUMCREDIT)) {// 上年累计发生额（贷方）（不用）
        value=accountantCredenceDAO.queryLastyearloanmnt_WL(subjectcode, year, "1", office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_COL)){// 行
        String temp_row=""+row;
        value=new BigDecimal(queryReportAF.getValue("" + subjectcode+ "_" + temp_row + "").toString());

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REPAIR_CURTERMAMOUNT)) {// 提取金额（购买、建造、翻建、大修住房提取）本期发生额                                                                                                                                       ）本期发生额
        String[] pickreason=new String[2];
        pickreason[0]=""+BusiConst.BUYHOUSE;
        pickreason[1]=""+BusiConst.PARTREST;
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REPAIR_CURYEARAMOUNT)) {// 提取金额（购买、建造、翻建、大修住房提取）本年累计发生额                                                                                                                                   ）本期发生额
        String[] pickreason=new String[2];
        pickreason[0]=""+BusiConst.BUYHOUSE;
        pickreason[1]=""+BusiConst.PARTREST;
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REPAIR_CURYEARSUMAMOUNT)) {// 提取金额（购买、建造、翻建、大修住房提取）期末累计发生额                                                                                                                  ）本期发生额
        String[] pickreason=new String[2];
        pickreason[0]=""+BusiConst.BUYHOUSE;
        pickreason[1]=""+BusiConst.PARTREST;
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_RETIREMENT_CURTERMAMOUNT)) {// 提取金额（离退休提取）本期发生额                                                                                                                              ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.BOWOUT;
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_RETIREMENT_CURYEARAMOUNT)) {// 提取金额（离退休提取）本年累计发生额                                                                                                                               ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.BOWOUT;
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_RETIREMENT_CURYEARSUMAMOUNT)) {// 提取金额（离退休提取）期末累计发生额                                                                                                                        ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.BOWOUT;
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LOSEABILITY_CURTERMAMOUNT)) {// 提取金额（完全丧失劳动能力，与单位终止劳动关系提取）本期发生额                                                                                                                     ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.DISTORY;
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LOSEABILITY_CURYEARAMOUNT)) {// 提取金额（完全丧失劳动能力，与单位终止劳动关系提取）本年累计发生额                                                                                                              ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.DISTORY;
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_LOSEABILITY_CURYEARSUMAMOUNT)) {// 提取金额（完全丧失劳动能力，与单位终止劳动关系提取）期末累计发生额                                                                                                                   ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.DISTORY;
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_ABROAD_CURTERMAMOUNT)) {// 提取金额（出国定居提取）本期发生额                                                                                                    ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_ABROAD_CURYEARAMOUNT)) {// 提取金额（出国定居提取）本年累计发生额                                                                                                  ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_ABROAD_CURYEARSUMAMOUNT)) {// 提取金额（出国定居提取）期末累计发生额                                                                                                            ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REIMBURSEMENT_CURTERMAMOUNT)) {// 提取金额（偿还购房贷款本息提取）本期发生额                                                                                              ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARAMOUNT)) {// 提取金额（偿还购房贷款本息提取）本年累计发生额                                                                                      ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_REIMBURSEMENT_CURYEARSUMAMOUNT)) {// 提取金额（偿还购房贷款本息提取）期末累计发生额                                                                                     ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]=""+BusiConst.SETTLE;
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_PAYACCOMMODATION_CURTERMAMOUNT)) {// 提取金额（支付房租提取）本期发生额                                                                                       ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARAMOUNT)) {// 提取金额（支付房租提取）本年累计发生额                                                                                    ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_PAYACCOMMODATION_CURYEARSUMAMOUNT)) {// 提取金额（支付房租提取）期末累计发生额                                                                            ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_OTHERS_CURTERMAMOUNT)) {// 提取金额（其他提取）本期发生额                                                                             ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPicktermamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_OTHERS_CURYEARAMOUNT)) {// 提取金额（其他提取）本年累计发生额                                                                           ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPickyearamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);

      } else if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_OTHERS_CURYEARSUMAMOUNT)) {// 提取金额（其他提取）期末累计发生额                                                                       ）本期发生额
        String[] pickreason=new String[1];
        pickreason[0]="0";
        value=accountantCredenceDAO.queryPickyearsumamount_WL(pickreason, year, starperiod, endperiod, office, securityInfo);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    if(value==null){
      value = new BigDecimal(0.00);
    }
    return value;
  }
  /**
   * 得到数据库中余额初始的年月
   */
  public String getMinYearmonth_WL(SecurityInfo securityInfo) throws Exception, BusinessException {
    String yearmonth="";
    try{
      yearmonth=accountantCredenceDAO.getMinYearmonth_WL(securityInfo);
    }catch(Exception e){
      e.printStackTrace();
    }
    return yearmonth;
  }
  /**
   * 财务报表：判断公式中是否有行公式存在
   * 
   * @param s
   * @param yearMonth
   * @param starperiod
   * @param endperiod
   * @param office
   * @return
   */
  public String getExpressValue_(String s) {
    String value = "0";
    try {
      s = s.substring(1);
      StringTokenizer substr = new StringTokenizer(s, "+");
      while (substr.hasMoreTokens()) {
        String expressBig = substr.nextToken();
        StringTokenizer substr2 = new StringTokenizer(expressBig, "-");
        int num = substr2.countTokens();
        if (num > 1) {
          int j = 0;
          while (substr2.hasMoreTokens()) {
            String express = substr2.nextToken();
            j++;
            if (j == 1) {
              value = getField_(express);
            } else {
              value = getField_(express);
            }
          }
        } else {
          value = getField_(expressBig);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return value;
  }

  /**
   * 财务报表：判断公式中是否有行
   * 
   * @param express String
   * @return String
   */
  public String getField_(String express)
      throws Exception, BusinessException {
    String value="0";
    try {
      if (express.substring(0, 2).equals(
          BusiConst.REPORTLOGO_COL)){// 行
        value="1";
      } 
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value;
  }
}
