package org.xpup.hafmis.sysloan.loanapply.beforeloanapply.business;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.sysloan.common.dao.BeforeLoanAdvisoryDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanRateDAO;
import org.xpup.hafmis.sysloan.common.loanconditionsset.LoanConditionsParamSetDTO;
import org.xpup.hafmis.sysloan.loanapply.beforeloanadvisory.form.BeforeLoanAdvisoryShowAF;
import org.xpup.hafmis.sysloan.loanapply.beforeloanapply.bsinterface.IBeforeLoanApplyBS;
import org.xpup.hafmis.sysloan.loanapply.beforeloanapply.form.BeforeLoanApplyShowAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyNewDTO;

public class BeforeLoanApplyBS implements IBeforeLoanApplyBS {
  BeforeLoanAdvisoryDAO beforeLoanAdvisoryDAO = null;

  LoanRateDAO loanRateDAO = null;

  private BorrowerDAO borrowerDAO = null;

  private EmpDAO empDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  public void setBeforeLoanAdvisoryDAO(
      BeforeLoanAdvisoryDAO beforeLoanAdvisoryDAO) {
    this.beforeLoanAdvisoryDAO = beforeLoanAdvisoryDAO;
  }

  public void setLoanRateDAO(LoanRateDAO loanRateDAO) {
    this.loanRateDAO = loanRateDAO;
  }

  public BeforeLoanApplyShowAF queryEmpInfo(String empid, String orgid,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    List list = loanRateDAO.findEmpInfo(empid, orgid);
    Emp emp = (Emp) list.get(0);
    String matter = "";
    BeforeLoanApplyShowAF beforeLoanApplyShowAF = new BeforeLoanApplyShowAF();
    beforeLoanApplyShowAF.setEmpId(emp.getEmpId().toString());
    beforeLoanApplyShowAF.setEmpname(emp.getEmpInfo().getName());
    beforeLoanApplyShowAF.setOrgId(BusiTools.convertTenNumber(emp.getOrg().getId().toString()));
    beforeLoanApplyShowAF.setOrgname(emp.getOrg().getOrgInfo().getName());
    int openMonth = 0;
    int qianJiao = 0;
    int lianxuJiao = 0;
    if (emp.getEmpInfo().getSex().toString().equals("1")) {
      beforeLoanApplyShowAF.setEmpSex("男");
    } else if (emp.getEmpInfo().getSex().toString().equals("2")) {
      beforeLoanApplyShowAF.setEmpSex("女");
    }
    if (emp.getEmpInfo().getCardKind().toString().equals("0")) {
      beforeLoanApplyShowAF.setEmpCardkind("身份证");
    } else {
      beforeLoanApplyShowAF.setEmpCardkind("其他");
    }
    beforeLoanApplyShowAF.setEmpCardnum(emp.getEmpInfo().getCardNum());
    if (emp.getEmpInfo().getCardNum().length() == 15) {
      beforeLoanApplyShowAF.setEmpBirthDay("19"
          + emp.getEmpInfo().getCardNum().substring(6, 12));
    } else if (emp.getEmpInfo().getCardNum().length() == 18) {
      beforeLoanApplyShowAF.setEmpBirthDay(emp.getEmpInfo().getCardNum()
          .substring(6, 14));
    }
    System.out.println(beforeLoanApplyShowAF.getEmpBirthDay());
    String today = BusiTools.dateToString(new Date(), "yyyyMMdd");
    int age = Integer.parseInt(beforeLoanAdvisoryDAO.getDaysInteval(today,
        beforeLoanApplyShowAF.getEmpBirthDay())) / 365;
    beforeLoanApplyShowAF.setEmpAge(age + "");
    beforeLoanApplyShowAF.setEmpSalaryBase(emp.getSalaryBase() + "");
    beforeLoanApplyShowAF.setEmpMonthPay(emp.getOrgPay().add(emp.getEmpPay())
        + "");
    beforeLoanApplyShowAF.setEmpBalance(emp.getCurBalance().add(
        emp.getPreBalance())
        + "");

    if (emp.getPayStatus().toString().equals("1")) {
      beforeLoanApplyShowAF.setEmpStatus("正常");
    } else if (emp.getPayStatus().toString().equals("2")) {
      beforeLoanApplyShowAF.setEmpStatus("封存");
    } else if (emp.getPayStatus().toString().equals("3")) {
      beforeLoanApplyShowAF.setEmpStatus("销户");
    } else if (emp.getPayStatus().toString().equals("4")) {
      beforeLoanApplyShowAF.setEmpStatus("调出");
    } else if (emp.getPayStatus().toString().equals("5")) {
      beforeLoanApplyShowAF.setEmpStatus("删除");
    }
    int continus = 0;
    if (Integer.parseInt(emp.getOrgPayMonth()) <= Integer.parseInt(emp
        .getEmpPayMonth())) {
      continus = BusiTools.monthInterval(emp.getEmpInfo().getOpendate()
          .substring(0, 6), emp.getOrgPayMonth());
    } else {
      continus = BusiTools.monthInterval(emp.getEmpInfo().getOpendate()
          .substring(0, 6), emp.getEmpPayMonth());
    }
    beforeLoanApplyShowAF.setEmpContinus(continus + "");

    BigDecimal empBalanceUse = (emp.getCurBalance().add(emp.getPreBalance()))
        .subtract(emp.getPayOldYear().multiply(new BigDecimal("12")));// 公积金可用还贷余额
    // loanreturnedbyfundTaAF.setMaxMonth(String.valueOf(moneyYue_1.add(moneyYue).intValue()/borrowerLoanInfo.getCorpusInterest().intValue()));
    beforeLoanApplyShowAF.setEmpBalanceUse(empBalanceUse.toString());
    List lls = borrowerDAO.findEmpinborrowByEmpid(emp.getEmpInfo().getName(),
        emp.getEmpInfo().getCardNum());// 根据借款人姓名证件号查询合同号
    if (!lls.isEmpty()) {
      boolean b = false;
      for (int i = 0; i < lls.size(); i++) {
        String contrid = (String) lls.get(i);
        b = borrowerAccDAO.isCheckBorrowByContractid(contrid);// 判断该合同是否处于12.13(状态为还款中的合同走前提条件设置的判断)
        if (!b) {
          LoanapplyNewDTO inf = empDAO.findBorrowInfoByEmpid(empid, orgid);
          String empName = inf.getEmpname();
          String cardNum = inf.getCardnum();
          String empEleven = empDAO.queryempLoanIsEleven(empName, cardNum);
          if (Integer.parseInt(empEleven) > 0) {
            matter = "你已经在公积金中心贷款\\r";
          } else {
            matter = "你已经在公积金中心贷款\\r";
          }
        }
      }
    }
    List llst_fu = borrowerDAO.findEmpinborrowByEmpid_wsh(emp.getEmpInfo()
        .getName(), emp.getEmpInfo().getCardNum());// 根据借款人姓名证件号查询合同号
    if (!llst_fu.isEmpty()) {

      matter = matter + "该职工作为辅助借款人的贷款合同尚未结清\\r";

    }
    if ((!empid.equals("") && !empid.equals("0"))
        && ((!orgid.equals("") && !orgid.equals("0")))) {// 说明是从公积金过来的人
      String payState = empDAO.findEmpInfoPayState(empid, orgid);// 该职工的账户状态
      // 根据姓名和身份证号查询该人作为借款人或者辅助借款人有没有状态是还款中的记录
      String opendate = empDAO.findEmpInfoOpenDate_ws(empid, orgid);
      String orgPayMonth = empDAO.findEmpInfoOpenDate_wsh(empid, orgid);
      String empPayMonth = empDAO.findEmpInfoOpenDate_wsh_emp(empid, orgid);
      // String month="";
      // if(Integer.parseInt(orgPayMonth)-Integer.parseInt(empPayMonth)>0){
      // month=orgPayMonth;
      // }else{
      // month=empPayMonth;
      // }
      String month_1 = "";
      if (Integer.parseInt(orgPayMonth) - Integer.parseInt(empPayMonth) > 0) {
        month_1 = empPayMonth;
      } else {
        month_1 = orgPayMonth;
      }
      String plbizdate = securityInfo.getUserInfo().getPlbizDate();

      try {
        openMonth = BusiTools.monthInterval(opendate.substring(0, 6), plbizdate
            .substring(0, 6));// 开户多少个月
        qianJiao = BusiTools.monthInterval(month_1.substring(0, 6), plbizdate
            .substring(0, 6));// 欠缴多少个月
        lianxuJiao = BusiTools.monthInterval(opendate.substring(0, 6), month_1);// 欠缴多少个月
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      if (true) {// 说明该限制生效
        int monthi2 = 6;// Integer.parseInt(loanConditionsParamSetDTO2getParamExplain());
        // boolean flag
        // =loanConditionsSetDAO.queryMonthCounts(empid,orgid,loanConditionsParamSetDTO2.getParamExplain());
        // //连续汇缴月数,返回的对错就是是否符合参数设置
        if (openMonth > monthi2) {
        } else {
          matter = matter + "公积金开户月数为" + openMonth + "个月，>6个月\\r";
          // throw new BusinessException("公积金连续汇缴月数应大于" + monthi2 + "月");
        }
      }

      if (true) {// 说明该限制生效
        int monthi2 = 12;
        if (qianJiao < monthi2) {
        } else {
          matter = matter + "公积金欠缴为" + qianJiao + "个月，>12个月\\r";
          // throw new BusinessException("公积金欠缴月数要小于" + monthi2 + "月");
        }
      }
    }
    beforeLoanApplyShowAF.setMatter(matter);
    return beforeLoanApplyShowAF;
  }

  public BigDecimal findMonthRate(String office, int year) throws Exception,
      BusinessException {
    BigDecimal rate = new BigDecimal(0.00);
    if (year <= 5) {
      rate = loanRateDAO.findMontRate_yg(office, "0");
    } else {
      rate = loanRateDAO.findMontRate_yg(office, "1");
    }
    return rate;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }
}
