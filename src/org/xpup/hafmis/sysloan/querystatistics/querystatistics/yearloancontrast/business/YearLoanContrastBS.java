package org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.bsinterface.IYearLoanContrastBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.dto.YearLoanContrastDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.dto.YearloanDTO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.form.YearLoanContrastAF;

public class YearLoanContrastBS implements IYearLoanContrastBS {

  private BorrowerAccDAO borrowerAccDAO = null;// PL111
  
  private OrganizationUnitDAO organizationUnitDAO = null;

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public YearLoanContrastAF queryList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    // TODO Auto-generated method stub
    String year = (String) pagination.getQueryCriterions().get("year");
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    List list = new ArrayList();
    YearLoanContrastAF yearLoanContrastAF = new YearLoanContrastAF();
    String officeName = "";

    int sumb_ffhs = 0;// 本年发放户数
    BigDecimal sumb_ffje = new BigDecimal(0.00);// 本年发放金额
    BigDecimal sumb_jjmj = new BigDecimal(0.00);// 本年建筑面积
    BigDecimal sumb_ffzj = new BigDecimal(0.00);// 本年房屋总价

    int sumw_ffhs = 0;// 往年发放户数
    BigDecimal sumw_ffje = new BigDecimal(0.00);// 往年发放金额
    BigDecimal sumw_jjmj = new BigDecimal(0.00);// 往年建筑面积
    BigDecimal sumw_ffzj = new BigDecimal(0.00);// 往年房屋总价

    try {
      if (officeCode != null && !"".equals(officeCode)) {
        officeName = organizationUnitDAO.queryOrganizationName_LY(officeCode);
      }
      String month = "";// 月份
      String bizDate = "";
      String lastbizDate = "";
      String lastyear = "";
      if (year != null && !"".equals(year)) {
        lastyear = new Integer((Integer.parseInt(year) - 1)).toString();
      }
      String zero = "0";
      BigDecimal hundred = new BigDecimal(100);
      YearloanDTO yearloanDTO = null;
      YearloanDTO lastyearloanDTO = null;
      YearLoanContrastDTO dto = null;
      if (pagination.getQueryCriterions().size() > 0) {
        for (int i = 1; i < 13; i++) {
          if (i > 9) {
            bizDate = year + new Integer(i).toString();
            lastbizDate = lastyear + new Integer(i).toString();
          } else {
            bizDate = year + zero + new Integer(i).toString();
            lastbizDate = lastyear + zero + new Integer(i).toString();
          }
          yearloanDTO = new YearloanDTO();
          lastyearloanDTO = new YearloanDTO();
          dto = new YearLoanContrastDTO();
          // 本年
          yearloanDTO = borrowerAccDAO.queryYearLoanContrast(bizDate,
              officeCode, securityInfo);
          // 上年
          lastyearloanDTO = borrowerAccDAO.queryYearLoanContrast(lastbizDate,
              officeCode, securityInfo);

          int b_ffhs = 0;// 本年发放户数
          BigDecimal b_ffje = new BigDecimal(0.00);// 本年发放金额
          BigDecimal b_jjmj = new BigDecimal(0.00);// 本年建筑面积
          BigDecimal b_ffzj = new BigDecimal(0.00);// 本年房屋总价

          int w_ffhs = 0;// 往年发放户数
          BigDecimal w_ffje = new BigDecimal(0.00);// 往年发放金额
          BigDecimal w_jjmj = new BigDecimal(0.00);// 往年建筑面积
          BigDecimal w_ffzj = new BigDecimal(0.00);// 往年房屋总价

          if (yearloanDTO != null) {
            month = yearloanDTO.getMonth();// 月份
            b_ffhs = yearloanDTO.getFfhs();// 本年发放户数
            b_ffje = yearloanDTO.getFfje();// 本年发放金额
            b_jjmj = yearloanDTO.getJjmj();// 本年建筑面积
            b_ffzj = yearloanDTO.getFfzj();// 本年房屋总价
          }
          sumb_ffhs += b_ffhs;// 本年发放户数
          sumb_ffje = sumb_ffje.add(b_ffje);// 本年发放金额
          sumb_jjmj = sumb_jjmj.add(b_jjmj);// 本年建筑面积
          sumb_ffzj = sumb_ffzj.add(b_ffzj);// 本年房屋总价

          dto.setThisyearmonth(month);
          dto.setMonth(month.substring(4, 6) + "月");
          dto.setB_ffhs(b_ffhs);
          dto.setB_ffje(b_ffje.setScale(2, BigDecimal.ROUND_HALF_UP));
          dto.setB_ffzj(b_ffzj.setScale(2, BigDecimal.ROUND_HALF_UP));
          dto.setB_jjmj(b_jjmj.setScale(3, BigDecimal.ROUND_HALF_UP));

          if (lastyearloanDTO != null) {
            w_ffhs = lastyearloanDTO.getFfhs();// 往年发放户数
            w_ffje = lastyearloanDTO.getFfje();// 往年发放金额
            w_jjmj = lastyearloanDTO.getJjmj();// 往年建筑面积
            w_ffzj = lastyearloanDTO.getFfzj();// 往年房屋总价
          }

          sumw_ffhs += w_ffhs;// 往年发放户数
          sumw_ffje = sumw_ffje.add(w_ffje);// 往年发放金额
          sumw_jjmj = sumw_jjmj.add(w_jjmj);// 往年建筑面积
          sumw_ffzj = sumw_ffzj.add(w_ffzj);// 往年房屋总价

          dto.setLastyearmonth(lastyearloanDTO.getMonth());
          dto.setW_ffhs(w_ffhs);
          dto.setW_ffje(w_ffje.setScale(2, BigDecimal.ROUND_HALF_UP));
          dto.setW_ffzj(w_ffzj.setScale(2, BigDecimal.ROUND_HALF_UP));
          dto.setW_jjmj(w_jjmj.setScale(3, BigDecimal.ROUND_HALF_UP));

          String t_ffhs = "";
          String t_ffje = "";
          String t_jjmj = "";
          String t_ffzj = "";

          BigDecimal bbffhs = new BigDecimal(new Integer(b_ffhs).toString());
          BigDecimal bwffhs = new BigDecimal(new Integer(w_ffhs).toString());
          if (bbffhs != null && !"0".equals(bwffhs.toString())) {
            t_ffhs = (bbffhs.subtract(bwffhs)).divide(bwffhs, 2,
                BigDecimal.ROUND_HALF_UP).multiply(hundred).toString()
                + "%";
          }

          if (b_ffje != null && !"0".equals(w_ffje.toString())) {
            BigDecimal tempffje = b_ffje.subtract(w_ffje);
            if (!"0".equals(tempffje.toString())) {
              t_ffje = ((b_ffje.subtract(w_ffje)).divide(w_ffje, 2,
                  BigDecimal.ROUND_HALF_UP)).multiply(hundred).toString()
                  + "%";// 同比增长发放金额
            }
          }
          if (b_jjmj != null && !"0".equals(w_jjmj.toString())) {
            BigDecimal tempjjmj = b_jjmj.subtract(w_jjmj);
            if (!"0".equals(tempjjmj.toString())) {
              t_jjmj = ((b_jjmj.subtract(w_jjmj)).divide(w_jjmj, 2,
                  BigDecimal.ROUND_HALF_UP)).multiply(hundred).toString()
                  + "%";// 同比增长建筑面积
            }
          }
          if (b_ffzj != null && !"0".equals(w_ffzj.toString())) {
            BigDecimal tempffzj = b_ffzj.subtract(w_ffzj);
            if (!"0".equals(tempffzj.toString())) {
              t_ffzj = ((b_ffzj.subtract(w_ffzj)).divide(w_ffzj, 2,
                  BigDecimal.ROUND_HALF_UP)).multiply(hundred).toString()
                  + "%";// 同比增长房屋总价
            }
          }

          dto.setT_ffhs(t_ffhs);
          dto.setT_ffje(t_ffje);
          dto.setT_ffzj(t_ffzj);
          dto.setT_jjmj(t_jjmj);

          list.add(dto);
        }
        YearLoanContrastDTO dto2 = new YearLoanContrastDTO();
        dto2.setMonth("合计");
        dto2.setB_ffhs(sumb_ffhs);
        dto2.setB_ffje(sumb_ffje.setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB_ffzj(sumb_ffzj.setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB_jjmj(sumb_jjmj.setScale(3, BigDecimal.ROUND_HALF_UP));
        dto2.setW_ffhs(sumw_ffhs);
        dto2.setW_ffje(sumw_ffje.setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setW_ffzj(sumw_ffzj.setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setW_jjmj(sumw_jjmj.setScale(3, BigDecimal.ROUND_HALF_UP));
        //------------------
        String sumt_ffhs = "";
        String sumt_ffje = "";
        String sumt_jjmj = "";
        String sumt_ffzj = "";
        BigDecimal bbffhs = new BigDecimal(new Integer(sumb_ffhs).toString());
        BigDecimal bwffhs = new BigDecimal(new Integer(sumw_ffhs).toString());
        if (bbffhs != null && !"0".equals(bwffhs.toString())) {
          sumt_ffhs = (bbffhs.subtract(bwffhs)).divide(bwffhs, 2,
              BigDecimal.ROUND_HALF_UP).multiply(hundred).toString()
              + "%";
        }

        if (sumb_ffje != null && !"0".equals(sumw_ffje.toString())) {
          BigDecimal tempffje = sumb_ffje.subtract(sumw_ffje);
          if (!"0".equals(tempffje.toString())) {
            sumt_ffje = ((sumb_ffje.subtract(sumw_ffje)).divide(sumw_ffje, 2,
                BigDecimal.ROUND_HALF_UP)).multiply(hundred).toString()
                + "%";// 同比增长发放金额
          }
        }
        if (sumb_jjmj != null && !"0".equals(sumw_jjmj.toString())) {
          BigDecimal tempjjmj = sumb_jjmj.subtract(sumw_jjmj);
          if (!"0".equals(tempjjmj.toString())) {
            sumt_jjmj = ((sumb_jjmj.subtract(sumw_jjmj)).divide(sumw_jjmj, 2,
                BigDecimal.ROUND_HALF_UP)).multiply(hundred).toString()
                + "%";// 同比增长建筑面积
          }
        }
        if (sumb_ffzj != null && !"0".equals(sumw_ffzj.toString())) {
          BigDecimal tempffzj = sumb_ffzj.subtract(sumw_ffzj);
          if (!"0".equals(tempffzj.toString())) {
            sumt_ffzj = ((sumb_ffzj.subtract(sumw_ffzj)).divide(sumw_ffzj, 2,
                BigDecimal.ROUND_HALF_UP)).multiply(hundred).toString()
                + "%";// 同比增长房屋总价
          }
        }

        dto2.setT_ffhs(sumt_ffhs);
        dto2.setT_ffje(sumt_ffje);
        dto2.setT_ffzj(sumt_ffzj);
        dto2.setT_jjmj(sumt_jjmj);
        //------------------
        list.add(dto2);
        yearLoanContrastAF.setOfficeName(officeName);
        yearLoanContrastAF.setThisyear(year);
        yearLoanContrastAF.setLastyear(lastyear);
        yearLoanContrastAF.setList(list);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return yearLoanContrastAF;
  }

}
