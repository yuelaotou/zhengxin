package org.xpup.hafmis.sysloan.loanapply.beforeloanapply.action;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.Convert;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.bsinterface.ILoanConditionsSetBS;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.dto.LoanConditionsSetDTO;
import org.xpup.hafmis.sysloan.loanapply.beforeloanapply.bsinterface.IBeforeLoanApplyBS;

public class BeforeLoanApplyAAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      String houseType = request.getParameter("houseType");// 房屋类型
      String empAge = request.getParameter("empAge");// 借款人年龄
      String empSex = request.getParameter("empSex");// 借款人性别
      String spouseId = request.getParameter("spouseId");// 辅助借款人编号
      String sHouseYear = request.getParameter("sHouseYear");// 二手房房龄
      String houseTotalPrice = request.getParameter("houseTotalPrice");// 房屋总价
      String loanYear = request.getParameter("loanYear");// 贷款年限

      String empSalaryBase = request.getParameter("empSalaryBase");// 借款人工资基数
      String empBalance = request.getParameter("empBalance");// 借款人公积金余额
      String empMonthPay = request.getParameter("empMonthPay");// 借款人月缴存额
      String spouseSalaryBase = request.getParameter("spouseSalaryBase");// 辅助借款工资基数
      String spouseBalance = request.getParameter("spouseBalance");// 配偶公积金余额
      String spouseMonthPay = request.getParameter("spouseMonthPay");// 配偶月缴存额
      String spouseAge = request.getParameter("spouseAge");// 配偶当前年龄
      String spouseSex = request.getParameter("spouseSex");// 配偶性别
      String spouseBalanceUse = request.getParameter("spouseBalanceUse");
      String empBalanceUse = request.getParameter("empBalanceUse");
      ILoanConditionsSetBS loanConditionsSetBS = (ILoanConditionsSetBS) BSUtils
          .getBusinessService("loanConditionsSetBS", this, mapping
              .getModuleConfig());
      IBeforeLoanApplyBS beforeLoanApplyBS = (IBeforeLoanApplyBS) BSUtils
          .getBusinessService("beforeLoanApplyBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String office = securityInfo.getUserInfo().getOfficeId().toString();
      LoanConditionsSetDTO loanConditionsSetDTO = loanConditionsSetBS
          .findLoanConditionsSetInfo(office);
      int maxYear = this.findMaxYear(loanConditionsSetDTO, houseType, empSex,
          empAge);// 最高年限
      if (maxYear < 0) {
        maxYear = 0;
      }
      int maxyear_yg = maxYear;
      if (Integer.parseInt(loanYear) < maxYear) {
        maxyear_yg = Integer.parseInt(loanYear);
      }
      double maxMoney = this.findMaxMoney(loanConditionsSetDTO, houseType,
          spouseId);// 住房公积最高限额
      double fangKuan = this.findFangKuan(loanConditionsSetDTO, houseType,
          sHouseYear, houseTotalPrice, loanYear);// 按购房款计算的贷款额度
      String fangKuana = this.findFangKuana(loanConditionsSetDTO, houseType,
          sHouseYear, houseTotalPrice, loanYear);// 按购房款计算的贷款额度
      double gongZiEdu = this.findGongZiEdu(loanConditionsSetDTO,
          empSalaryBase, spouseSalaryBase, maxyear_yg);// 按月工资总额计算的贷款额度
      String gongZiEdua = this.findGongZiEdua(loanConditionsSetDTO,
          empSalaryBase, spouseSalaryBase, maxyear_yg);// 按月工资总额计算的贷款额度
      double tuiXiuEdu = this.findTuiXiuEdu(loanConditionsSetDTO,
          spouseBalance, spouseMonthPay, empBalance, empMonthPay, empSex,
          empAge, spouseSex, spouseAge);// 按家庭成员退休年龄内所交纳住房公积金数额计算的贷款额度
      String tuiXiuEdua = this.findTuiXiuEdua(loanConditionsSetDTO,
          spouseBalance, spouseMonthPay, empBalance, empMonthPay, empSex,
          empAge, spouseSex, spouseAge);// 按家庭成员退休年龄内所交纳住房公积金数额计算的贷款额度
      String monthRate = beforeLoanApplyBS.findMonthRate(office, maxyear_yg)
          .toString();
      double minMoney = this.findMaxNum(maxMoney, fangKuan, gongZiEdu,
          tuiXiuEdu);
      String minMoneys="";
      if((minMoney+"").indexOf(".")<(minMoney+"").length()-3){
        minMoneys=(minMoney+"").substring(0,(minMoney+"").indexOf(".")+3);
      }else{
        minMoneys=minMoney+"";
      }
      BigDecimal corpusInterest = CorpusinterestBS.getCorpusInterest(
          new BigDecimal(minMoney + ""), new BigDecimal(monthRate), maxyear_yg
              * 12 + "");
      BigDecimal empYue = new BigDecimal(0);
      if (empBalanceUse != null && !empBalanceUse.equals("")
          && !empBalanceUse.equals("0") && !corpusInterest.equals("0")) {
        empYue = new BigDecimal(empBalanceUse).divide(corpusInterest, 0,
            BigDecimal.ROUND_DOWN);
      }
      String spouseYue = "";
      if (spouseBalanceUse != null && !spouseBalanceUse.equals("")
          && !spouseBalanceUse.equals("0") && !corpusInterest.equals("0")) {
        spouseYue = new BigDecimal(spouseBalanceUse).divide(corpusInterest, 0,
            BigDecimal.ROUND_DOWN).toString();
      }
      String text = "";
      text = "sun_display('" + maxYear
          + "','"// 最高年限为
          + getTwoDouble(maxMoney + "")
          + "','"// 住房公积最高限额
          + getTwoDouble(fangKuan + "")
          + "','"// 按购房款计算的贷款额度
          + getTwoDouble(gongZiEdu + "")
          + "','"// 按月工资总额计算的贷款额度
          + getTwoDouble(tuiXiuEdu + "")
          + "','"// 按家庭成员退休年龄内所交纳住房公积金数额计算的贷款额度
          + fangKuana
          + "','"// 按购房款计算的贷款额度
          + gongZiEdua
          + "','"// 按月工资总额计算的贷款额度
          + tuiXiuEdua
          + "','"// 按家庭成员退休年龄内所交纳住房公积金数额计算的贷款额度
          + maxyear_yg + "','" + empYue + "','" + spouseYue + "','" + Float.parseFloat(String.valueOf(new BigDecimal(monthRate).multiply(new BigDecimal(1000))))+"‰"
          + "','" + Float.parseFloat(String.valueOf(new BigDecimal(monthRate).multiply(new BigDecimal(100))))*12+"%" + "','" + minMoneys + "','" + corpusInterest + "')";

      response.getWriter().write(text);
      response.getWriter().close();

    } catch (IOException e) {
      e.printStackTrace();
    } catch (BusinessException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private int findMaxYear(LoanConditionsSetDTO loanConditionsSetDTO,
      String houseType, String empSex, String empAge) {
    int a4 = 0;
    int a5 = 0;
    int a13 = 0;
    int a_max = 0;
    if (houseType.endsWith("0")) {// 商品房
      if (empSex.equals("男")) {
        a4 = (Integer.parseInt(loanConditionsSetDTO.getMaleAge()) - Integer
            .parseInt(empAge));
        a5 = Integer.parseInt(loanConditionsSetDTO.getLoanLimitMax()) / 12;
        a13 = Integer.parseInt(loanConditionsSetDTO.getTimeMax_1());
        if (a4 < a5) {
          if (a4 < a13) {
            a_max = a4;
          } else {
            a_max = a13;
          }
        } else {
          if (a5 < a13) {
            a_max = a5;
          } else {
            a_max = a13;
          }
        }
      } else {
        a4 = (Integer.parseInt(loanConditionsSetDTO.getFemaleAge()) - Integer
            .parseInt(empAge));
        a5 = Integer.parseInt(loanConditionsSetDTO.getLoanLimitMax()) / 12;
        a13 = Integer.parseInt(loanConditionsSetDTO.getTimeMax_1());
        if (a4 < a5) {
          if (a4 < a13) {
            a_max = a4;
          } else {
            a_max = a13;
          }
        } else {
          if (a5 < a13) {
            a_max = a5;
          } else {
            a_max = a13;
          }
        }
      }
    } else {// 二手房
      if (empSex.equals("男")) {
        a4 = (Integer.parseInt(loanConditionsSetDTO.getMaleAge()) - Integer
            .parseInt(empAge));
        a5 = Integer.parseInt(loanConditionsSetDTO.getLoanLimitMax()) / 12;
        a13 = Integer.parseInt(loanConditionsSetDTO.getTimeMax_2());
        if (a4 < a5) {
          if (a4 < a13) {
            a_max = a4;
          } else {
            a_max = a13;
          }
        } else {
          if (a5 < a13) {
            a_max = a5;
          } else {
            a_max = a13;
          }
        }
      } else {
        a4 = (Integer.parseInt(loanConditionsSetDTO.getFemaleAge()) - Integer
            .parseInt(empAge));
        a5 = Integer.parseInt(loanConditionsSetDTO.getLoanLimitMax()) / 12;
        a13 = Integer.parseInt(loanConditionsSetDTO.getTimeMax_2());
        if (a4 < a5) {
          if (a4 < a13) {
            a_max = a4;
          } else {
            a_max = a13;
          }
        } else {
          if (a5 < a13) {
            a_max = a5;
          } else {
            a_max = a13;
          }
        }
      }
    }
    return a_max;
  }

  private double findMaxMoney(LoanConditionsSetDTO loanConditionsSetDTO,
      String houseType, String spouseId) {
    double a7 = 0;
    double a10 = 0;
    double a11 = 0;
    double maxMoney = 0;
    if (houseType.endsWith("0")) {// 商品房
      if (spouseId != null && !spouseId.equals("")) {// 有辅助借款人
        a7 = loanConditionsSetDTO.getOtherLoanMoneyMax().doubleValue();
      } else {
        a7 = loanConditionsSetDTO.getLoanMoneyMax().doubleValue();
      }
      if(loanConditionsSetDTO.getEight().equals("0")){
        a10 = loanConditionsSetDTO.getMerchandiseMoneyMax().doubleValue();
        if (a7 < a10) {
          maxMoney = a7;
        } else {
          maxMoney = a10;
        }
      }else{
        maxMoney = a7;
      }
    } else {// 二手房
      if (spouseId != null && !spouseId.equals("")) {// 有辅助借款人
        a7 = loanConditionsSetDTO.getOtherLoanMoneyMax().doubleValue();
      } else {
        a7 = loanConditionsSetDTO.getLoanMoneyMax().doubleValue();
      }
      a11 = loanConditionsSetDTO.getSecondhandMoneyMax().doubleValue();
      if (a7 < a11) {
        maxMoney = a7;
      } else {
        maxMoney = a11;
      }
    }
    return maxMoney;
  }

  private double findFangKuan(LoanConditionsSetDTO loanConditionsSetDTO,
      String houseType, String sHouseYear, String houseTotalPrice,
      String loanYear) {
    double fangKuan = 0;
    if (houseType.endsWith("0")) {// 商品房
      fangKuan = loanConditionsSetDTO.getMerchandiseRateMax().doubleValue()
          / 100 * Double.parseDouble(houseTotalPrice);
    } else {// 二手房
      if (Integer.parseInt(sHouseYear) <= 5) {
        fangKuan = loanConditionsSetDTO.getSecondhandRateMax().doubleValue()
            / 100 * Double.parseDouble(houseTotalPrice);
      } else if (Integer.parseInt(sHouseYear) <= 10) {
        fangKuan = loanConditionsSetDTO.getSecondhandRateMax_1().doubleValue()
            / 100 * Double.parseDouble(houseTotalPrice);
      } else {
        fangKuan = loanConditionsSetDTO.getSecondhandRateMax_2().doubleValue()
            / 100 * Double.parseDouble(houseTotalPrice);
      }
    }
    return fangKuan;
  }

  private String findFangKuana(LoanConditionsSetDTO loanConditionsSetDTO,
      String houseType, String sHouseYear, String houseTotalPrice,
      String loanYear) {
    String fangKuan = "";
    if (houseType.endsWith("0")) {// 商品房
      fangKuan = loanConditionsSetDTO.getMerchandiseRateMax().doubleValue()
          / 100 + "*" + houseTotalPrice;
    } else {// 二手房
      if (Integer.parseInt(sHouseYear) <= 5) {
        fangKuan = loanConditionsSetDTO.getSecondhandRateMax().doubleValue()
            / 100 + "*" + houseTotalPrice;
      } else if (Integer.parseInt(sHouseYear) <= 10) {
        fangKuan = loanConditionsSetDTO.getSecondhandRateMax_1().doubleValue()
            / 100 + "*" + houseTotalPrice;
      } else {
        fangKuan = loanConditionsSetDTO.getSecondhandRateMax_2().doubleValue()
            / 100 + "*" + houseTotalPrice;
      }
    }
    return fangKuan;
  }

  private double findGongZiEdu(LoanConditionsSetDTO loanConditionsSetDTO,
      String empSalaryBase, String spouseSalaryBase, int maxYear) {
    double gongZiEdu = 0;
    if (spouseSalaryBase != null && !spouseSalaryBase.equals("")) {
      gongZiEdu = (Double.parseDouble(empSalaryBase) + Double
          .parseDouble(spouseSalaryBase))
          * 12
          * loanConditionsSetDTO.getSalaryRate().doubleValue()
          / 100
          * maxYear;
    } else {
      gongZiEdu = Double.parseDouble(empSalaryBase) * 12
          * loanConditionsSetDTO.getSalaryRate().doubleValue() / 100 * maxYear;
    }
    return gongZiEdu;
  }

  private String findGongZiEdua(LoanConditionsSetDTO loanConditionsSetDTO,
      String empSalaryBase, String spouseSalaryBase, int maxYear) {
    String gongZiEdu = "";
    if (spouseSalaryBase != null && !spouseSalaryBase.equals("")) {
      gongZiEdu = "(" + empSalaryBase + "+" + spouseSalaryBase + ")";
      gongZiEdu += "*" + loanConditionsSetDTO.getSalaryRate().doubleValue()
          / 100 + "*12*" + maxYear;
    } else {
      gongZiEdu = empSalaryBase;
      gongZiEdu += "*" + loanConditionsSetDTO.getSalaryRate().doubleValue()
          / 100 + "*12*" + maxYear;
    }
    return gongZiEdu;
  }

  private double findTuiXiuEdu(LoanConditionsSetDTO loanConditionsSetDTO,
      String spouseBalance, String spouseMonthPay, String empBalance,
      String empMonthPay, String empSex, String empAge, String spouseSex,
      String spouseAge) {
    double tuiXiuEdu = 0;
    double a = 0;
    double b = 0;
    if (spouseBalance != null && !spouseBalance.equals("")) {
      if (empSex.equals("男")) {
        a = Double.parseDouble(empBalance)
            + 12
            * Double.parseDouble(empMonthPay)
            * (Double.parseDouble(loanConditionsSetDTO.getMaleAge()) - Double
                .parseDouble(empAge));
        if (spouseSex.equals("男")) {
          b = Double.parseDouble(spouseBalance)
              + 12
              * Double.parseDouble(spouseMonthPay)
              * (Double.parseDouble(loanConditionsSetDTO.getMaleAge()) - Double
                  .parseDouble(spouseAge));
        } else {
          b = Double.parseDouble(spouseBalance)
              + 12
              * Double.parseDouble(spouseMonthPay)
              * (Double.parseDouble(loanConditionsSetDTO.getFemaleAge()) - Double
                  .parseDouble(spouseAge));
        }
        tuiXiuEdu = loanConditionsSetDTO.getBeiShu().doubleValue() * (a + b);
      } else {
        a = Double.parseDouble(empBalance)
            + 12
            * Double.parseDouble(empMonthPay)
            * (Double.parseDouble(loanConditionsSetDTO.getFemaleAge()) - Double
                .parseDouble(empAge));
        if (spouseSex.equals("男")) {
          b = Double.parseDouble(spouseBalance)
              + 12
              * Double.parseDouble(spouseMonthPay)
              * (Double.parseDouble(loanConditionsSetDTO.getMaleAge()) - Double
                  .parseDouble(spouseAge));
        } else {
          b = Double.parseDouble(spouseBalance)
              + 12
              * Double.parseDouble(spouseMonthPay)
              * (Double.parseDouble(loanConditionsSetDTO.getFemaleAge()) - Double
                  .parseDouble(spouseAge));
        }
        tuiXiuEdu = loanConditionsSetDTO.getBeiShu().doubleValue() * (a + b);
      }
    } else {
      if (empSex.equals("男")) {
        a = Double.parseDouble(empBalance)
            + 12
            * Double.parseDouble(empMonthPay)
            * (Double.parseDouble(loanConditionsSetDTO.getMaleAge()) - Double
                .parseDouble(empAge));
        tuiXiuEdu = loanConditionsSetDTO.getBeiShu().doubleValue() * (a + b);
      } else {
        a = Double.parseDouble(empBalance)
            + 12
            * Double.parseDouble(empMonthPay)
            * (Double.parseDouble(loanConditionsSetDTO.getFemaleAge()) - Double
                .parseDouble(empAge));
        tuiXiuEdu = loanConditionsSetDTO.getBeiShu().doubleValue() * (a + b);
      }
    }
    return tuiXiuEdu;
  }

  private String findTuiXiuEdua(LoanConditionsSetDTO loanConditionsSetDTO,
      String spouseBalance, String spouseMonthPay, String empBalance,
      String empMonthPay, String empSex, String empAge, String spouseSex,
      String spouseAge) {
    String tuiXiuEdu = "";
    if (spouseBalance != null && !spouseBalance.equals("")) {
      if (empSex.equals("男")) {
        tuiXiuEdu = empBalance + "+" + empMonthPay + "*" + "("
            + loanConditionsSetDTO.getMaleAge() + "-" + empAge + ")*12";
        if (spouseSex.equals("男")) {
          tuiXiuEdu = tuiXiuEdu + "+" + spouseBalance + "+" + spouseMonthPay
              + "*" + "(" + loanConditionsSetDTO.getMaleAge() + "-" + spouseAge
              + ")*12";
        } else {
          tuiXiuEdu = tuiXiuEdu + "+" + spouseBalance + "+" + spouseMonthPay
              + "*" + "(" + loanConditionsSetDTO.getFemaleAge() + "-"
              + spouseAge + ")*12";
        }
        tuiXiuEdu = loanConditionsSetDTO.getBeiShu() + "*(" + tuiXiuEdu + ")";
      } else {
        tuiXiuEdu = empBalance + "+" + empMonthPay + "*" + "("
            + loanConditionsSetDTO.getFemaleAge() + "-" + empAge + ")*12";
        if (spouseSex.equals("男")) {
          tuiXiuEdu = tuiXiuEdu + "+" + spouseBalance + "+" + spouseMonthPay
              + "*" + "(" + loanConditionsSetDTO.getMaleAge() + "-" + spouseAge
              + ")*12";
        } else {
          tuiXiuEdu = tuiXiuEdu + "+" + spouseBalance + "+" + spouseMonthPay
              + "*" + "(" + loanConditionsSetDTO.getFemaleAge() + "-"
              + spouseAge + ")*12";
        }
        tuiXiuEdu = loanConditionsSetDTO.getBeiShu() + "*(" + tuiXiuEdu + ")";
      }
    } else {
      if (empSex.equals("男")) {
        tuiXiuEdu = empBalance + "+" + empMonthPay + "*" + "("
            + loanConditionsSetDTO.getMaleAge() + "-" + empAge + ")*12";
        tuiXiuEdu = loanConditionsSetDTO.getBeiShu() + "*(" + tuiXiuEdu + ")";
      } else {
        tuiXiuEdu = empBalance + "+" + empMonthPay + "*" + "("
            + loanConditionsSetDTO.getFemaleAge() + "-" + empAge + ")*12";
        tuiXiuEdu = loanConditionsSetDTO.getBeiShu() + "*(" + tuiXiuEdu + ")";
      }
    }
    return tuiXiuEdu;
  }

  private String getTwoDouble(String num) {// 转换小数的方法
    if (num.indexOf(".") != -1) {
      String str;
      str = num.substring(num.indexOf(".") + 1, num.length());// 获取小数点后面的位数
      if (str.length() >= 2) {
        str = str.substring(0, 2);
      }
      if (str.length() == 1) {
        str = str.substring(0, 1) + "0";
      }
      num = num.substring(0, num.indexOf(".")) + "." + str;
    }
    return num;
  }

  private double findMaxNum(double maxMoney, double fangKuan, double gongZiEdu,
      double tuiXiuEdu) {
    double min = 0;
    if (maxMoney < fangKuan) {
      if (maxMoney < gongZiEdu) {
        if (maxMoney < tuiXiuEdu) {
          min = maxMoney;
        } else {
          min = tuiXiuEdu;
        }
      } else {
        if (gongZiEdu < tuiXiuEdu) {
          min = gongZiEdu;
        } else {
          min = tuiXiuEdu;
        }
      }
    } else {
      if (fangKuan < gongZiEdu) {
        if (fangKuan < tuiXiuEdu) {
          min = fangKuan;
        } else {
          min = tuiXiuEdu;
        }
      } else {
        if (gongZiEdu < tuiXiuEdu) {
          min = gongZiEdu;
        } else {
          min = tuiXiuEdu;
        }
      }
    }
    if (min < 0) {
      return 0;
    }
    return min;
  }

}