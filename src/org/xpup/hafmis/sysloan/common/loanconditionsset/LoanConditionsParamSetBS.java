package org.xpup.hafmis.sysloan.common.loanconditionsset;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.CardMunChange;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.ChgPaymentTailDAO;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.dao.OrgDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.sysloan.common.dao.AssistantBorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanConditionsSetDAO;
import org.xpup.hafmis.sysloan.common.dao.SpecialBorrowerDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Houses;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTdNewAF;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class LoanConditionsParamSetBS implements ILoanConditionsParamSetBS {
  // 1.是否有贷款资格 返回boolean
  // 2.可贷最大金额 返回BigDecimal
  private LoanConditionsSetDAO loanConditionsSetDAO = null;

  private EmpDAO empDAO = null;

  private ChgPaymentTailDAO chgPaymentTailDAO = null;

  private BorrowerDAO borrowerDAO = null;

  private HousesDAO housesDAO = null;

  private SpecialBorrowerDAO specialBorrowerDAO = null;

  private OrgDAO orgDAO = null;

  private AssistantBorrowerDAO assistantBorrowerDAO = null;

  public void setSpecialBorrowerDAO(SpecialBorrowerDAO specialBorrowerDAO) {
    this.specialBorrowerDAO = specialBorrowerDAO;
  }

  public void setHousesDAO(HousesDAO housesDAO) {
    this.housesDAO = housesDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setChgPaymentTailDAO(ChgPaymentTailDAO chgPaymentTailDAO) {
    this.chgPaymentTailDAO = chgPaymentTailDAO;
  }

  public void setEmpDAO(EmpDAO empDAO) {
    this.empDAO = empDAO;
  }

  public void setLoanConditionsSetDAO(LoanConditionsSetDAO loanConditionsSetDAO) {
    this.loanConditionsSetDAO = loanConditionsSetDAO;
  }

  // 1.是否有贷款资格 返回boolean
  public boolean isCanSysLoan(LoanapplyNewAF loanapplyaf,
      SecurityInfo securityInfo) throws BusinessException {
    String office = loanapplyaf.getBorrower().getOffice();
    String empid = loanapplyaf.getBorrower().getEmpId().toString();

    // 这个地方加上个判断因为在职工弹出框选择完职工编号时也走这个方法了,但是
    // loanapplyaf的borrower中的empid这个属性并没有赋值
    if (empid.equals("0")) {
      empid = loanapplyaf.getEmpid();
    }
    String orgid = loanapplyaf.getBorrower().getOrgId().toString();
    String empname = loanapplyaf.getBorrower().getBorrowerName().toString()
        .trim();
    String cardnum = loanapplyaf.getBorrower().getCardNum().toString().trim();
    String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(
        empname, cardnum);// 根据借款人姓名证件号查询是否有特殊借款人并处于未启用状态
    int openMonth = 0;
    int qianJiao = 0;
    int lianxuJiao = 0;
    boolean ischeck = true;
    if (specialid == null) {
      try {
        // 职工公积金状态是否正常设置参数
        String param_value1 = loanConditionsSetDAO
            .querySyscollectionState(office);
        // 公积金连续汇缴月数应大于?个月
        LoanConditionsParamSetDTO loanConditionsParamSetDTO2 = loanConditionsSetDAO
            .querySyscollectionMonth(office);
        LoanConditionsParamSetDTO loanConditionsParamSetDTO3 = loanConditionsSetDAO
            .querySyscollectionOpenAccMonth(office);
        List orgNaturelist = loanConditionsSetDAO.queryOrgNature(office);
        LoanConditionsParamSetDTO loanConditionsParamSetDTO5 = loanConditionsSetDAO
            .queryOweMonth(office);
        // 公积金欠缴月数要小于?个月
        LoanConditionsParamSetDTO loanConditionsParamSetDTO6 = loanConditionsSetDAO
            .querySyscollectionOpenAccMonth_wsh(office);
        if ((!empid.equals("") && !empid.equals("0"))
            && ((!orgid.equals("") && !orgid.equals("0")))) {// 说明是从公积金过来的人
          String payState = empDAO.findEmpInfoPayState(empid, orgid);// 该职工的账户状态
          // 根据姓名和身份证号查询该人作为借款人或者辅助借款人有没有状态是还款中的记录
          String empEleven = loanConditionsSetDAO.queryempLoanIsEleven(empname,
              cardnum);
          String empElevena = loanConditionsSetDAO.queryempLoanIsElevena(
              empname, cardnum);

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
            openMonth = BusiTools.monthInterval(opendate.substring(0, 6),
                plbizdate.substring(0, 6));// 开户多少个月
            qianJiao = BusiTools.monthInterval(month_1.substring(0, 6),
                plbizdate.substring(0, 6));// 欠缴多少个月
            lianxuJiao = BusiTools.monthInterval(opendate.substring(0, 6),
                month_1);// 欠缴多少个月
          } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          // 说明该职工充当了借款人或者辅助借款人并且合同状态为还款中
          if (Integer.parseInt(empEleven) > 0) {
            throw new BusinessException("该职工存在合同状态为还款中的贷款");
          } else {
            if (Integer.parseInt(empElevena) > 0) {
              throw new BusinessException("该职工存在异地贷款");
            }
            ischeck = true;
          }
          if (param_value1 != null) {// 说明该限制生效
            if (param_value1.equals("1")) {
              if (payState.equals("1")) {
                ischeck = true;
              } else {
                throw new BusinessException("该职工公积金账户状态不正确");
              }
            }

          }
          // 贷款去掉连续汇缴月数的限制，在购房时判断
          // if (loanConditionsParamSetDTO2 != null
          // && loanConditionsParamSetDTO2.getParamExplain() != null) {//
          // 说明该限制生效
          // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO2
          // .getParamExplain());
          // // boolean flag
          // //
          // =loanConditionsSetDAO.queryMonthCounts(empid,orgid,loanConditionsParamSetDTO2.getParamExplain());
          // // //连续汇缴月数,返回的对错就是是否符合参数设置
          // if (lianxuJiao > monthi2) {
          // ischeck = true;
          // } else {
          // throw new BusinessException("公积金连续汇缴月数应大于" + monthi2 + "月");
          // }
          // }
          // if (loanConditionsParamSetDTO3 != null
          // && loanConditionsParamSetDTO3.getParamExplain() != null) {//
          // 说明该限制生效
          // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO3
          // .getParamExplain());
          // if (openMonth > monthi2) {
          // ischeck = true;
          // } else {
          // throw new BusinessException("公积金开户时间应大于" + monthi2 + "月");
          // }
          // }
          // if (loanConditionsParamSetDTO6 != null
          // && loanConditionsParamSetDTO6.getParamExplain() != null) {//
          // 说明该限制生效
          // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO6
          // .getParamExplain());
          // if (qianJiao < monthi2) {
          // ischeck = true;
          // } else {
          // throw new BusinessException("公积金欠缴月数要小于" + monthi2 + "月");
          // }
          // }
        }
        if (loanConditionsParamSetDTO5 != null
            && loanConditionsParamSetDTO5.getParamExplain() != null) {// 说明该限制生效
          int monthi2 = Integer.parseInt(loanConditionsParamSetDTO5
              .getParamExplain());
          String id = "";
          if (cardnum.length() == 18) {
            id = CardMunChange.get15Id(cardnum);
          }
          if (cardnum.length() == 15) {
            id = CardMunChange.get18Id(cardnum);
          }
          List colist = borrowerDAO.findEmpinborrowByEmpid_wsh(empname,
              cardnum, id);
          for (int k = 0; k < colist.size(); k++) {
            String contractId = (String) colist.get(k);
            String owemonthai = chgPaymentTailDAO
                .countOverdueInfoOweMonth(contractId);
            int owemonthi = Integer.parseInt(owemonthai);
            if (owemonthi <= monthi2) {
              ischeck = true;
            } else {
              throw new BusinessException("曾逾期不能超过" + monthi2 + "月");
            }
          }
        }
        // 根据单位性质判断

        if (!empid.equals("") && !empid.equals("0")) {
          Org org = orgDAO.queryById(new Integer(orgid));
          for (int j = 0; j < orgNaturelist.size(); j++) {
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) orgNaturelist
                .get(j);
            String orgNature = loanConditionsParamSetDTO.getParamExplain();
            String paramNum = loanConditionsParamSetDTO.getParamNum();
            String count = "";
            if (orgNature != null
                && orgNature.equals(org.getOrgInfo().getCharacter())) {
              if (paramNum.equals("17")) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh1(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(orgid);
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("单位最少人数少于 "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "人");
                  } else {
                    ischeck = true;
                  }
                }

                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh2(office);
                // 去掉连续汇缴月数限制，在购房时限制
                // if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                // // boolean flag =loanConditionsSetDAO.queryMonthCounts(empid
                // //
                // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                // // //连续汇缴月数
                // // if(!flag){
                // // throw new BusinessException("单位连续汇缴少于 " +
                // // loanConditionsParamSetDTO_2.getParamExplain()
                // // + "月");
                // // }else{
                // // ischeck=true;
                // // }
                // if (lianxuJiao > Integer.parseInt(loanConditionsParamSetDTO_2
                // .getParamExplain())) {
                // ischeck = true;
                // } else {
                // throw new BusinessException("公积金连续汇缴月数应大于"
                // + loanConditionsParamSetDTO_2.getParamExplain() + "月");
                // }
                // }

              }
              if (paramNum.equals("16")) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh3(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(orgid);
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("单位最少人数少于 "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "人");
                  } else {
                    ischeck = true;
                  }

                }
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh4(office);
                if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                  // boolean flag =loanConditionsSetDAO.queryMonthCounts(empid
                  // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                  // //连续汇缴月数
                  // if(!flag){
                  // throw new BusinessException("单位连续汇缴少于 " +
                  // loanConditionsParamSetDTO_2.getParamExplain()
                  // + "月");
                  // }else{
                  // ischeck=true;
                  // }
                  if (lianxuJiao > Integer.parseInt(loanConditionsParamSetDTO_2
                      .getParamExplain())) {
                    ischeck = true;
                  } else {
                    throw new BusinessException("公积金连续汇缴月数应大于"
                        + loanConditionsParamSetDTO_2.getParamExplain() + "月");
                  }
                }
              }
              if (paramNum.equals("15")) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh5(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(orgid);
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("单位最少人数少于 "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "人");
                  } else {
                    ischeck = true;
                  }
                }
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh6(office);
                if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                  // boolean flag =loanConditionsSetDAO.queryMonthCounts(empid
                  // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                  // //连续汇缴月数
                  // if(!flag){
                  // throw new BusinessException("单位连续汇缴少于 " +
                  // loanConditionsParamSetDTO_2.getParamExplain()
                  // + "月");
                  // }else{
                  // ischeck=true;
                  // }
                  if (lianxuJiao > Integer.parseInt(loanConditionsParamSetDTO_2
                      .getParamExplain())) {
                    ischeck = true;
                  } else {
                    throw new BusinessException("公积金连续汇缴月数应大于"
                        + loanConditionsParamSetDTO_2.getParamExplain() + "月");
                  }
                }
              }
            }
          }
        }
        // 根据单位性质判断
      } catch (BusinessException be) {
        throw be;
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return ischeck;
  }

  // 1.是否有贷款资格 返回boolean
  public boolean isCanSysLoanTb(LoanapplyTbNewAF loanapplyTbNewAF,
      SecurityInfo securityInfo) throws BusinessException {
    String contract_id = loanapplyTbNewAF.getContractId();
    // 办事处用借款人的取
    String office = borrowerDAO.findBorrrowInfoByContractid(contract_id)
        .getOffice();
    Borrower borrower = borrowerDAO.queryById(contract_id);
    String borrowerEmpId = null;
    if (borrower.getEmpId() != null) {
      borrowerEmpId = borrower.getEmpId().toString();
    }
    String empid = loanapplyTbNewAF.getEmpId();

    String orgid = loanapplyTbNewAF.getOrgId();
    String empname = loanapplyTbNewAF.getName();
    String cardnum = loanapplyTbNewAF.getCardNum();
    String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(
        empname, cardnum);// 根据借款人姓名证件号查询是否有特殊借款人并处于未启用状态
    int openMonth = 0;
    int qianJiao = 0;
    int lianxuJiao = 0;
    boolean ischeck = true;
    if (specialid == null) {
      try {
        // 职工公积金状态是否正常设置参数
        String param_value1 = loanConditionsSetDAO
            .querySyscollectionState(office);
        // 公积金连续汇缴月数应大于?个月
        LoanConditionsParamSetDTO loanConditionsParamSetDTO2 = loanConditionsSetDAO
            .querySyscollectionMonth(office);
        LoanConditionsParamSetDTO loanConditionsParamSetDTO3 = loanConditionsSetDAO
            .querySyscollectionOpenAccMonth(office);
        List orgNaturelist = loanConditionsSetDAO.queryOrgNature(office);
        LoanConditionsParamSetDTO loanConditionsParamSetDTO5 = loanConditionsSetDAO
            .queryOweMonth(office);
        // 公积金欠缴月数要小于?个月
        LoanConditionsParamSetDTO loanConditionsParamSetDTO6 = loanConditionsSetDAO
            .querySyscollectionOpenAccMonth_wsh(office);
        if ((!empid.equals("") && !empid.equals("0"))
            && ((!orgid.equals("") && !orgid.equals("0")))) {// 说明是从公积金过来的人
          String payState = empDAO.findEmpInfoPayState(empid, orgid);// 该职工的账户状态
          // 根据姓名和身份证号查询该人作为借款人或者辅助借款人有没有状态是还款中的记录
          String empEleven = loanConditionsSetDAO.queryempLoanIsEleven(empname,
              cardnum);

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
            openMonth = BusiTools.monthInterval(opendate.substring(0, 6),
                plbizdate.substring(0, 6));// 开户多少个月
            qianJiao = BusiTools.monthInterval(month_1.substring(0, 6),
                plbizdate.substring(0, 6));// 欠缴多少个月
            lianxuJiao = BusiTools.monthInterval(opendate.substring(0, 6),
                month_1);// 欠缴多少个月
          } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          // 说明该职工充当了借款人或者辅助借款人并且合同状态为还款中
          if (Integer.parseInt(empEleven) > 0) {
            throw new BusinessException("该职工存在合同状态为还款中的贷款");
          } else {
            ischeck = true;
          }
          if (borrowerEmpId == null) {
            if (param_value1 != null) {// 说明该限制生效
              if (param_value1.equals("1")) {
                if (payState.equals("1")) {
                  ischeck = true;
                } else {
                  throw new BusinessException("该职工公积金账户状态不正确");
                }
              }

            }
            // if (loanConditionsParamSetDTO2 != null
            // && loanConditionsParamSetDTO2.getParamExplain() != null) {//
            // 说明该限制生效
            // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO2
            // .getParamExplain());
            // // boolean flag
            // //
            // =loanConditionsSetDAO.queryMonthCounts(empid,orgid,loanConditionsParamSetDTO2.getParamExplain());
            // // //连续汇缴月数,返回的对错就是是否符合参数设置
            // if (lianxuJiao > monthi2) {
            // ischeck = true;
            // } else {
            // throw new BusinessException("公积金连续汇缴月数应大于" + monthi2 + "月");
            // }
            // }
            // if (loanConditionsParamSetDTO3 != null
            // && loanConditionsParamSetDTO3.getParamExplain() != null) {//
            // 说明该限制生效
            // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO3
            // .getParamExplain());
            // if (openMonth > monthi2) {
            // ischeck = true;
            // } else {
            // throw new BusinessException("公积金开户时间应大于" + monthi2 + "月");
            // }
            // }
            // if (loanConditionsParamSetDTO6 != null
            // && loanConditionsParamSetDTO6.getParamExplain() != null) {//
            // 说明该限制生效
            // int monthi2 = Integer.parseInt(loanConditionsParamSetDTO6
            // .getParamExplain());
            // if (qianJiao < monthi2) {
            // ischeck = true;
            // } else {
            // throw new BusinessException("公积金欠缴月数要小于" + monthi2 + "月");
            // }
            // }
            if (loanConditionsParamSetDTO5 != null
                && loanConditionsParamSetDTO5.getParamExplain() != null) {// 说明该限制生效
              int monthi2 = Integer.parseInt(loanConditionsParamSetDTO5
                  .getParamExplain());
              String id = "";
              if (cardnum.length() == 18) {
                id = CardMunChange.get15Id(cardnum);
              }
              if (cardnum.length() == 15) {
                id = CardMunChange.get18Id(cardnum);
              }
              List colist = borrowerDAO.findEmpinborrowByEmpid_wsh(empname,
                  cardnum, id);
              for (int k = 0; k < colist.size(); k++) {
                String contractId = (String) colist.get(k);
                String owemonthai = chgPaymentTailDAO
                    .countOverdueInfoOweMonth(contractId);
                int owemonthi = Integer.parseInt(owemonthai);
                if (owemonthi <= monthi2) {
                  ischeck = true;
                } else {
                  throw new BusinessException("曾逾期不能超过" + monthi2 + "月");
                }
              }
            }
            // 根据单位性质判断

            if (!empid.equals("") && !empid.equals("0")) {
              Org org = orgDAO.queryById(new Integer(orgid));
              for (int j = 0; j < orgNaturelist.size(); j++) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) orgNaturelist
                    .get(j);
                String orgNature = loanConditionsParamSetDTO.getParamExplain();
                String paramNum = loanConditionsParamSetDTO.getParamNum();
                String count = "";
                if (orgNature != null
                    && orgNature.equals(org.getOrgInfo().getCharacter())) {
                  if (paramNum.equals("17")) {
                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh1(office);
                    count = loanConditionsSetDAO
                        .querySyscollectionState_org_emp_count(orgid);
                    if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                      if (Integer.parseInt(count) < Integer
                          .parseInt(loanConditionsParamSetDTO_1
                              .getParamExplain())) {
                        throw new BusinessException("单位最少人数少于 "
                            + loanConditionsParamSetDTO_1.getParamExplain()
                            + "人");
                      } else {
                        ischeck = true;
                      }
                    }

                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh2(office);
                    if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                      // boolean flag
                      // =loanConditionsSetDAO.queryMonthCounts(empid
                      // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                      // //连续汇缴月数
                      // if(!flag){
                      // throw new BusinessException("单位连续汇缴少于 " +
                      // loanConditionsParamSetDTO_2.getParamExplain()
                      // + "月");
                      // }else{
                      // ischeck=true;
                      // }
                      if (lianxuJiao > Integer
                          .parseInt(loanConditionsParamSetDTO_2
                              .getParamExplain())) {
                        ischeck = true;
                      } else {
                        throw new BusinessException("公积金连续汇缴月数应大于"
                            + loanConditionsParamSetDTO_2.getParamExplain()
                            + "月");
                      }
                    }

                  }
                  if (paramNum.equals("16")) {
                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh3(office);
                    count = loanConditionsSetDAO
                        .querySyscollectionState_org_emp_count(orgid);
                    if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                      if (Integer.parseInt(count) < Integer
                          .parseInt(loanConditionsParamSetDTO_1
                              .getParamExplain())) {
                        throw new BusinessException("单位最少人数少于 "
                            + loanConditionsParamSetDTO_1.getParamExplain()
                            + "人");
                      } else {
                        ischeck = true;
                      }

                    }
                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh4(office);
                    if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                      // boolean flag
                      // =loanConditionsSetDAO.queryMonthCounts(empid
                      // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                      // //连续汇缴月数
                      // if(!flag){
                      // throw new BusinessException("单位连续汇缴少于 " +
                      // loanConditionsParamSetDTO_2.getParamExplain()
                      // + "月");
                      // }else{
                      // ischeck=true;
                      // }
                      if (lianxuJiao > Integer
                          .parseInt(loanConditionsParamSetDTO_2
                              .getParamExplain())) {
                        ischeck = true;
                      } else {
                        throw new BusinessException("公积金连续汇缴月数应大于"
                            + loanConditionsParamSetDTO_2.getParamExplain()
                            + "月");
                      }
                    }
                  }
                  if (paramNum.equals("15")) {
                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh5(office);
                    count = loanConditionsSetDAO
                        .querySyscollectionState_org_emp_count(orgid);
                    if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                      if (Integer.parseInt(count) < Integer
                          .parseInt(loanConditionsParamSetDTO_1
                              .getParamExplain())) {
                        throw new BusinessException("单位最少人数少于 "
                            + loanConditionsParamSetDTO_1.getParamExplain()
                            + "人");
                      } else {
                        ischeck = true;
                      }
                    }
                    LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                        .queryOverHousePriceS_wsh6(office);
                    if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                      // boolean flag
                      // =loanConditionsSetDAO.queryMonthCounts(empid
                      // .toString(),orgid,loanConditionsParamSetDTO_2.getParamExplain());
                      // //连续汇缴月数
                      // if(!flag){
                      // throw new BusinessException("单位连续汇缴少于 " +
                      // loanConditionsParamSetDTO_2.getParamExplain()
                      // + "月");
                      // }else{
                      // ischeck=true;
                      // }
                      if (lianxuJiao > Integer
                          .parseInt(loanConditionsParamSetDTO_2
                              .getParamExplain())) {
                        ischeck = true;
                      } else {
                        throw new BusinessException("公积金连续汇缴月数应大于"
                            + loanConditionsParamSetDTO_2.getParamExplain()
                            + "月");
                      }
                    }
                  }
                }
              }
            }
          }

        }

        // 根据单位性质判断
      } catch (BusinessException be) {
        throw be;
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return ischeck;
  }

  // 2.可贷最大金额 返回BigDecimal
  public BigDecimal canSysLoanMoney(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException {

    String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(
        loanapplytdnewAF.getBorrowerName().trim(), loanapplytdnewAF
            .getCardNum().trim());// 根据借款人姓名证件号查询是否有特殊借款人并处于未启用状态

    try {
      if (specialid == null) {
        int loanlimit = Integer.parseInt(loanapplytdnewAF.getLoantimeLimit());
        int loanmoney = Integer.parseInt(loanapplytdnewAF.getLoanMoney());
        BigDecimal yueHuanBenXi = new BigDecimal(0.0);
        if (loanapplytdnewAF.getCorpusInterest() != null
            && !"".equals(loanapplytdnewAF.getCorpusInterest())) {
          yueHuanBenXi = new BigDecimal(loanapplytdnewAF.getCorpusInterest());
        }
        String contactid = loanapplytdnewAF.getContractId();
        Borrower borrower = borrowerDAO.queryById(contactid);
        String office = borrower.getOffice();
        List agelist = loanConditionsSetDAO.queryManAge(office);
        List orgNaturelist = loanConditionsSetDAO.queryOrgNature(office);
        String sex = borrower.getSex();
        int age = Integer.parseInt(borrower.getAge().toString());
        int tol = 0;
        int sy = loanlimit % 12;
        if (sy == 0) {
          tol = age + loanlimit / 12;
        } else {
          tol = age + loanlimit / 12 + 1;
        }
        for (int i = 0; i < agelist.size(); i++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
              .get(i);
          String sexdto = loanConditionsParamSetDTO.getParamValue();
          int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
              .getParamExplain());
          if (sex.equals("1")) {// 男
            if (sexdto.equals("1")) {
              if (tol > paramExplain) {
                throw new BusinessException("不能贷款,年龄加贷款期限过大");
              }
            }

          }
          if (sex.equals("2")) {// 女

            if (sexdto.equals("2")) {
              if (tol > paramExplain) {
                throw new BusinessException("不能贷款,年龄加贷款期限过大");
              }
            }
          }
        }

        List loanlimitlist = loanConditionsSetDAO.queryLoanLimit(office);
        for (int j = 0; j < loanlimitlist.size(); j++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) loanlimitlist
              .get(j);
          String type = loanConditionsParamSetDTO.getParamValue();
          int loanlimitetime = Integer.parseInt(loanConditionsParamSetDTO
              .getParamExplain());
          if (type.equals("1")) {
            if (loanlimitetime > loanlimit) {
              throw new BusinessException("贷款期限不低于" + loanlimitetime + "月");
            }
          }
          if (type.equals("2")) {
            if (loanlimitetime < loanlimit) {
              throw new BusinessException("贷款期限不高于" + loanlimitetime + "月");
            }
          }
        }

        String num = borrowerDAO.countPeopleNum(contactid);
        if (num.equals("0")) {// 说明没有辅助借款人
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryLoanMoney(office, "0");
          int loanm = Integer.parseInt(loanConditionsParamSetDTO
              .getParamExplain());
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// 启用了
            if (loanmoney > loanm) {
              throw new BusinessException("单方拥有公积金贷款金额不能超过" + loanm + "元");
            }
          }

        } else {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryLoanMoney(office, "1");
          int loanm = Integer.parseInt(loanConditionsParamSetDTO
              .getParamExplain());
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// 启用了
            if (loanmoney > loanm) {
              throw new BusinessException("有辅助贷款人的贷款金额不能超过" + loanm + "元");
            }
          }

        }
        BigDecimal yuE = new BigDecimal(0.00);
        BigDecimal jiaoE = new BigDecimal(0.00);
        BigDecimal sum = new BigDecimal(0.00);
        // 贷款额度(不得超过贷款家庭成员退休年龄内所交纳住房公积金数额)
        if (num.equals("0")) {// 说明没有辅助借款人
          LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
              .queryOverHousePriceS_1(office);
          LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 = loanConditionsSetDAO
              .queryOverHousePriceS_14(office);
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
          borrower = borrowerDAO.queryById(contactid);
          if (borrower.getEmpId() == null && "".equals(borrower.getEmpId())) {
            throw new BusinessException("借款人未缴纳住房公积金！");
          }
          if (borrower.getEmpId() != null || !"".equals(borrower.getEmpId())) {
            Emp emp = empDAO.queryByCriterions(borrower.getEmpId().toString(),
                borrower.getOrgId().toString());

            if (loanConditionsParamSetDTO_14.getParamExplain() != null) {// 启用了
              BigDecimal money = emp.getSalaryBase()
                  .multiply(
                      new BigDecimal(loanConditionsParamSetDTO_14
                          .getParamExplain())).multiply(
                      new BigDecimal(loanlimit));
              if (new BigDecimal(loanmoney).compareTo(money) > 0) {
                throw new BusinessException("借款人月收入还款比例不得超过 "
                    + loanConditionsParamSetDTO_14.getParamExplain() + "%");
              }
            }
            yuE = emp.getPreBalance().add(emp.getCurBalance());
            for (int i = 0; i < agelist.size(); i++) {
              loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sex.equals("1")) {// 借款人性别男
                if (sexdto.equals("1")) {
                  jiaoE = (emp.getOrgPay().add(emp.getEmpPay())).multiply(
                      new BigDecimal(12)).multiply(
                      new BigDecimal(paramExplain
                          - borrower.getAge().intValue()));
                }

              }
              if (sex.equals("2")) {// 女

                if (sexdto.equals("2")) {
                  jiaoE = (emp.getOrgPay().add(emp.getEmpPay())).multiply(
                      new BigDecimal(12)).multiply(
                      new BigDecimal(paramExplain
                          - borrower.getAge().intValue()));
                }
              }
            }
            sum = yuE.add(jiaoE);
          }

          int loanm = Integer.parseInt(loanConditionsParamSetDTO_1
              .getParamExplain());
          sum = sum.multiply(new BigDecimal(loanm));
          if (loanConditionsParamSetDTO_1.getParamExplain() != null) {// 启用了
            if (new BigDecimal(loanmoney).compareTo(sum) > 0) {
              throw new BusinessException("不得超过贷款家庭成员退休年龄内所交纳住房公积金数额 " + loanm
                  + "倍");
            }
          }

        } else {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
              .queryOverHousePriceS_1(office);
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
          LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 = loanConditionsSetDAO
              .queryOverHousePriceS_14(office);
          String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contactid);
          String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contactid);
          String fuzhuSex = borrowerDAO.countPeopleNum_Sex(contactid);
          borrower = borrowerDAO.queryById(contactid);
          if ((borrower.getEmpId() == null || "".equals(borrower.getEmpId()))
              && (fuzhuEmpId == null || "".equals(fuzhuEmpId))) {
            throw new BusinessException("借款人及辅助借款人均未缴纳住房公积金！");
          }
          if (borrower.getEmpId() != null && !"".equals(borrower.getEmpId())) {
            Emp emp = empDAO.queryByCriterions(borrower.getEmpId().toString(),
                borrower.getOrgId().toString());
            if (loanConditionsParamSetDTO_14.getParamExplain() != null) {// 启用了
              // 借款人
              BigDecimal money = emp.getSalaryBase()
                  .multiply(
                      new BigDecimal(loanConditionsParamSetDTO_14
                          .getParamExplain())).multiply(
                      new BigDecimal(loanlimit));
              // 辅助借款人
              Emp emp_fuzhu = empDAO.queryByCriterions(fuzhuEmpId.toString(),
                  fuzhuOrgId.toString());
              money = money.add(emp_fuzhu.getSalaryBase()
                  .multiply(
                      new BigDecimal(loanConditionsParamSetDTO_14
                          .getParamExplain())).multiply(
                      new BigDecimal(loanlimit)));
              if (new BigDecimal(loanmoney).compareTo(money) > 0) {
                throw new BusinessException("借款人月收入还款比例不得超过 "
                    + loanConditionsParamSetDTO_14.getParamExplain() + "%");
              }
            }
            yuE = emp.getPreBalance().add(emp.getCurBalance());// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
            // BigDecimal(12))));

            for (int i = 0; i < agelist.size(); i++) {
              loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sex.equals("1")) {// 借款人性别男
                if (sexdto.equals("1")) {
                  jiaoE = (emp.getOrgPay().add(emp.getEmpPay())).multiply(
                      new BigDecimal(12)).multiply(
                      new BigDecimal(paramExplain
                          - borrower.getAge().intValue()));
                }

              }
              if (sex.equals("2")) {// 女

                if (sexdto.equals("2")) {
                  jiaoE = (emp.getOrgPay().add(emp.getEmpPay())).multiply(
                      new BigDecimal(12)).multiply(
                      new BigDecimal(paramExplain
                          - borrower.getAge().intValue()));
                }
              }
            }
            sum = yuE.add(jiaoE);
          }
          if (fuzhuEmpId != null && !"".equals(fuzhuEmpId)) {
            Emp emp = empDAO.queryByCriterions(fuzhuEmpId.toString(),
                fuzhuOrgId.toString());
            yuE = yuE.add(emp.getPreBalance().add(emp.getCurBalance()));// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
            // BigDecimal(12))));

            for (int i = 0; i < agelist.size(); i++) {
              loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              String fuZhuAge = "";
              fuZhuAge = borrowerDAO.countPeopleNum_Age(contactid);
              if (fuzhuSex.equals("1")) {// 借款人性别男
                if (sexdto.equals("1")) {
                  jiaoE = jiaoE.add((emp.getOrgPay().add(emp.getEmpPay()))
                      .multiply(new BigDecimal(12)).multiply(
                          new BigDecimal(paramExplain
                              - Integer.valueOf(fuZhuAge).intValue())));
                }

              }
              if (fuzhuSex.equals("2")) {// 女

                if (sexdto.equals("2")) {
                  jiaoE = jiaoE.add((emp.getOrgPay().add(emp.getEmpPay()))
                      .multiply(new BigDecimal(12)).multiply(
                          new BigDecimal(paramExplain
                              - Integer.valueOf(fuZhuAge).intValue())));
                }
              }
            }
            sum = yuE.add(jiaoE);
          }

          int loanm = Integer.parseInt(loanConditionsParamSetDTO_1
              .getParamExplain());
          sum = sum.multiply(new BigDecimal(loanm));
          if (loanConditionsParamSetDTO_1.getParamExplain() != null) {// 启用了
            if (new BigDecimal(loanmoney).compareTo(sum) > 0) {
              throw new BusinessException("不得超过贷款家庭成员退休年龄内所交纳住房公积金数额 " + loanm
                  + "倍");
            }
          }

        }
        // 贷款额度(不得超过贷款家庭成员退休年龄内所交纳住房公积金数额)
        // 根据单位性质判断
        borrower = borrowerDAO.queryById(contactid);
        if (borrower.getEmpId() != null) {
          Org org = orgDAO
              .queryById(new Integer(borrower.getOrgId().toString()));
          for (int j = 0; j < orgNaturelist.size(); j++) {
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) orgNaturelist
                .get(j);
            String orgNature = loanConditionsParamSetDTO.getParamExplain();
            String count = "";
            if (orgNature != null
                && orgNature.equals(org.getOrgInfo().getCharacter())) {
              if (j == 0) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh1(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(borrower.getOrgId()
                        .toString());
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("单位最少人数少于 "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "人");
                  }
                }

                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh2(office);
                if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                  boolean flag = loanConditionsSetDAO.queryMonthCounts(borrower
                      .getEmpId().toString(), borrower.getOrgId().toString(),
                      loanConditionsParamSetDTO_2.getParamExplain()); // 连续汇缴月数
                  if (!flag) {
                    throw new BusinessException("单位连续汇缴少于 "
                        + loanConditionsParamSetDTO_2.getParamExplain() + "月");
                  }
                }

              }
              if (j == 1) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh3(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(borrower.getOrgId()
                        .toString());
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("单位最少人数少于 "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "人");
                  }
                }
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh4(office);
                if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                  boolean flag = loanConditionsSetDAO.queryMonthCounts(borrower
                      .getEmpId().toString(), borrower.getOrgId().toString(),
                      loanConditionsParamSetDTO_2.getParamExplain()); // 连续汇缴月数
                  if (!flag) {
                    throw new BusinessException("单位连续汇缴少于 "
                        + loanConditionsParamSetDTO_2.getParamExplain() + "月");
                  }
                }
              }
              if (j == 2) {
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh5(office);
                count = loanConditionsSetDAO
                    .querySyscollectionState_org_emp_count(borrower.getOrgId()
                        .toString());
                if (loanConditionsParamSetDTO_1.getParamExplain() != null) {
                  if (Integer.parseInt(count) < Integer
                      .parseInt(loanConditionsParamSetDTO_1.getParamExplain())) {
                    throw new BusinessException("单位最少人数少于 "
                        + loanConditionsParamSetDTO_1.getParamExplain() + "人");
                  }
                }
                LoanConditionsParamSetDTO loanConditionsParamSetDTO_2 = loanConditionsSetDAO
                    .queryOverHousePriceS_wsh6(office);
                if (loanConditionsParamSetDTO_2.getParamExplain() != null) {
                  boolean flag = loanConditionsSetDAO.queryMonthCounts(borrower
                      .getEmpId().toString(), borrower.getOrgId().toString(),
                      loanConditionsParamSetDTO_2.getParamExplain()); // 连续汇缴月数
                  if (!flag) {
                    throw new BusinessException("单位连续汇缴少于 "
                        + loanConditionsParamSetDTO_2.getParamExplain() + "月");
                  }
                }
              }
            }
          }
        }
        // 根据单位性质判断
        Houses houses = housesDAO.queryById(contactid);
        String housestype = houses.getHouseType();
        if (housestype.equals("01")) {// 商品房
          BigDecimal housetolprice = houses.getHouseTotlePrice();
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryOverHousePriceS(office);
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// 启用了
            BigDecimal ht = new BigDecimal(loanapplytdnewAF.getLoanMoney());
            String housesprice = loanConditionsParamSetDTO.getParamExplain();
            BigDecimal jg = housetolprice.multiply(new BigDecimal(housesprice))
                .divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);

            if (ht.doubleValue() > jg.doubleValue()) {
              throw new BusinessException("不能超过商品房价的" + housesprice + "%");
            }
          }
        }
        if (housestype.equals("02")) {// 二手房
          BigDecimal housetolprice = houses.getBargainorTotlePrice();
          if (houses.getBargainorHouseAge().intValue() >= 1
              && houses.getBargainorHouseAge().intValue() < 5) {
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
                .queryOverHousePriceE(office);
            if (loanConditionsParamSetDTO.getParamExplain() != null) {// 启用了
              BigDecimal ht = new BigDecimal(loanapplytdnewAF.getLoanMoney());
              String housesprice = loanConditionsParamSetDTO.getParamExplain();
              BigDecimal jg = housetolprice.multiply(
                  new BigDecimal(housesprice)).divide(new BigDecimal(100), 4,
                  BigDecimal.ROUND_HALF_UP);

              if (ht.doubleValue() > jg.doubleValue()) {
                throw new BusinessException("不能超过二手房价的" + housesprice + "%");
              }
            }
          }
          if (houses.getBargainorHouseAge().intValue() >= 5
              && houses.getBargainorHouseAge().intValue() < 10) {
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
                .queryOverHousePriceE_wsh(office);
            if (loanConditionsParamSetDTO.getParamExplain() != null) {// 启用了
              BigDecimal ht = new BigDecimal(loanapplytdnewAF.getLoanMoney());
              String housesprice = loanConditionsParamSetDTO.getParamExplain();
              BigDecimal jg = housetolprice.multiply(
                  new BigDecimal(housesprice)).divide(new BigDecimal(100), 4,
                  BigDecimal.ROUND_HALF_UP);

              if (ht.doubleValue() > jg.doubleValue()) {
                throw new BusinessException("不能超过二手房价的" + housesprice + "%");
              }
            }
          }
          if (houses.getBargainorHouseAge().intValue() >= 5
              && houses.getBargainorHouseAge().intValue() < 10) {
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
                .queryOverHousePriceE_wsh_1(office);
            if (loanConditionsParamSetDTO.getParamExplain() != null) {// 启用了
              BigDecimal ht = new BigDecimal(loanapplytdnewAF.getLoanMoney());
              String housesprice = loanConditionsParamSetDTO.getParamExplain();
              BigDecimal jg = housetolprice.multiply(
                  new BigDecimal(housesprice)).divide(new BigDecimal(100), 4,
                  BigDecimal.ROUND_HALF_UP);

              if (ht.doubleValue() > jg.doubleValue()) {
                throw new BusinessException("不能超过二手房价的" + housesprice + "%");
              }
            }
          }

        }
        if (housestype.equals("01")) {// 商品房

          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryMaxLoanMoneyS(office);
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// 启用了

            String housesprice = loanConditionsParamSetDTO.getParamExplain();
            int housesprices = Integer.parseInt(housesprice);
            if (loanmoney > housesprices) {
              throw new BusinessException("商品房价的贷款金额不能超过" + housesprices);
            }
          }
        }
        if (housestype.equals("02")) {// 二手房
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryMaxLoanMoneyE(office);
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// 启用了

            String housesprice = loanConditionsParamSetDTO.getParamExplain();
            int housesprices = Integer.parseInt(housesprice);
            if (loanmoney > housesprices) {
              throw new BusinessException("二手房价的贷款金额不能超过" + housesprices);
            }
          }
        }
        List loanlimitlist_wsh = loanConditionsSetDAO.queryLoanLimit(office);
        for (int j = 0; j < loanlimitlist_wsh.size(); j++) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) loanlimitlist_wsh
              .get(j);
          String type = loanConditionsParamSetDTO.getParamValue();
          int loanlimitetime = Integer.parseInt(loanConditionsParamSetDTO
              .getParamExplain());
          if (type.equals("1") && housestype.equals("01")) {

            if (loanlimitetime < loanlimit) {
              throw new BusinessException("商品房贷款期限得高于" + loanlimitetime + "月");
            }

          }
          if (type.equals("2") && housestype.equals("02")) {
            if (loanlimitetime < loanlimit) {
              throw new BusinessException("二手房贷款期限不得高于" + loanlimitetime + "月");
            }
          }
        }
        // 借款人月收入还款比例
        if (yueHuanBenXi != null
            && yueHuanBenXi.compareTo(new BigDecimal(0.00)) > 0) {
          LoanConditionsParamSetDTO loanConditionsParamSetDTO = loanConditionsSetDAO
              .queryMaxLoanMoneyE_w(office);
          if (loanConditionsParamSetDTO.getParamExplain() != null) {// 启用了

            String salaryRate = loanConditionsParamSetDTO.getParamExplain();
            BigDecimal housesprices = new BigDecimal(salaryRate);
            Borrower borrower_1 = borrowerDAO.queryById(contactid);
            ;
            if (yueHuanBenXi.compareTo(borrower_1.getMonthSalary().multiply(
                new BigDecimal(salaryRate))) > 0) {
              throw new BusinessException("借款人还款比例不能超过月收入的" + housesprices);
            }
          }
        }
        // 借款人月收入还款比例
      }
    } catch (BusinessException be) {
      throw be;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }

  // ３.可贷最大 返回年限
  public int canSysLoanlimit(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException {

    String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(
        loanapplytdnewAF.getBorrowerName().trim(), loanapplytdnewAF
            .getCardNum().trim());// 根据借款人姓名证件号查询是否有特殊借款人并处于未启用状态

    int limit_temp = 0;
    int limit_1 = 0;
    int limit_2 = 0;
    int limit_3 = 0;
    try {

      if (specialid == null) {

        String contactid = loanapplytdnewAF.getContractId();
        // 根据单位性质判断
        Houses houses = housesDAO.queryById(contactid);
        String housestype = houses.getHouseType();
        Borrower borrower = borrowerDAO.queryById(contactid);
        String office = borrower.getOffice();
        List agelist = loanConditionsSetDAO.queryManAge(office);
        String sex = borrower.getSex();
        int age = Integer.parseInt(borrower.getAge().toString());
        // ///////////////////////////////////////////////////////////////////////////////////////
        // 贷款最高年限：商品房
        String paramExplain_13_1 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("13", office, "1");
        // 贷款最高年限：二手房
        String paramExplain_13_2 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("13", office, "2");
        // 最高期限不超过?月
        String paramExplain_5_2 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("5", office, "2");

        if (housestype.equals("01")) {// 商品房
          // 贷款人实际年龄加贷款期限不超过
          if (sex.equals("1")) {// 男
            for (int i = 0; i < agelist.size(); i++) {
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sexdto.equals("1")) {
                limit_1 = (paramExplain - age) * 12;
              }
            }
            if (paramExplain_13_1 != null && !"".equals(paramExplain_13_1)) {
              limit_2 = Integer.parseInt(paramExplain_13_1) * 12;
            }
            if (paramExplain_5_2 != null && !"".equals(paramExplain_5_2)) {
              limit_3 = Integer.parseInt(paramExplain_5_2);
            }
            // 最小年限
            if (limit_1 > limit_2 && limit_2 != 0) {
              limit_temp = limit_2;
            } else {
              limit_temp = limit_1;
            }
            if (limit_temp > limit_3 && limit_3 != 0) {
              limit_temp = limit_3;
            }
            if (limit_temp == 0) {
              limit_temp = 1000;
            }
            // 最小年限
          }
          if (sex.equals("2")) {// 女
            // 贷款人实际年龄加贷款期限不超过
            for (int i = 0; i < agelist.size(); i++) {
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sexdto.equals("2")) {
                limit_1 = (paramExplain - age) * 12;

              }
            }
            if (paramExplain_13_1 != null && !"".equals(paramExplain_13_1)) {
              limit_2 = Integer.parseInt(paramExplain_13_1) * 12;
            }
            if (paramExplain_5_2 != null && !"".equals(paramExplain_5_2)) {
              limit_3 = Integer.parseInt(paramExplain_5_2);
            }
            // 最小年限
            if (limit_1 > limit_2 && limit_2 != 0) {
              limit_temp = limit_2;
            } else {
              limit_temp = limit_1;
            }
            if (limit_temp > limit_3 && limit_3 != 0) {
              limit_temp = limit_3;
            }
            if (limit_temp == 0) {
              limit_temp = 1000;
            }
            // 最小年限
          }
        }
        if (housestype.equals("02")) {// 二手房
          if (sex.equals("1")) {// 男
            for (int i = 0; i < agelist.size(); i++) {
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sexdto.equals("1")) {
                limit_1 = (paramExplain - age) * 12;

              }
            }
            if (paramExplain_13_2 != null && !"".equals(paramExplain_13_2)) {
              limit_2 = Integer.parseInt(paramExplain_13_2) * 12;
            }
            if (paramExplain_5_2 != null && !"".equals(paramExplain_5_2)) {
              limit_3 = Integer.parseInt(paramExplain_5_2);
            }
            // 最小年限
            if (limit_1 > limit_2 && limit_2 != 0) {
              limit_temp = limit_2;
            } else {
              limit_temp = limit_1;
            }
            if (limit_temp > limit_3 && limit_3 != 0) {
              limit_temp = limit_3;
            }
            if (limit_temp == 0) {
              limit_temp = 1000;
            }
            // 最小年限
          }
          if (sex.equals("2")) {// 女
            // 贷款人实际年龄加贷款期限不超过
            for (int i = 0; i < agelist.size(); i++) {
              LoanConditionsParamSetDTO loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                  .get(i);
              String sexdto = loanConditionsParamSetDTO.getParamValue();
              int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                  .getParamExplain());
              if (sexdto.equals("2")) {
                limit_1 = (paramExplain - age) * 12;

              }
            }
            if (paramExplain_13_2 != null && !"".equals(paramExplain_13_2)) {
              limit_2 = Integer.parseInt(paramExplain_13_2) * 12;
            }
            if (paramExplain_5_2 != null && !"".equals(paramExplain_5_2)) {
              limit_3 = Integer.parseInt(paramExplain_5_2);
            }
            // 最小年限
            if (limit_1 > limit_2 && limit_2 != 0) {
              limit_temp = limit_2;
            } else {
              limit_temp = limit_1;
            }
            if (limit_temp > limit_3 && limit_3 != 0) {
              limit_temp = limit_3;
            }
            if (limit_temp == 0) {
              limit_temp = 1000;
            }
            // 最小年限
          }
        }
        // ///////////////////////////////////////////////////////////////////////////////////////
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return limit_temp;
  }

  // ３.可贷最大 返回金额
  public BigDecimal canSysLoanlimit_1(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException {

    String specialid = specialBorrowerDAO.findSpecialByBorrownameCardnum(
        loanapplytdnewAF.getBorrowerName().trim(), loanapplytdnewAF
            .getCardNum().trim());// 根据借款人姓名证件号查询是否有特殊借款人并处于未启用状态

    BigDecimal money_temp = new BigDecimal(0);
    BigDecimal money_1 = new BigDecimal(0);
    BigDecimal money_2 = new BigDecimal(0);
    BigDecimal money_3 = new BigDecimal(0);
    BigDecimal money_4 = new BigDecimal(0);
    BigDecimal money_5 = new BigDecimal(0);
    try {

      if (specialid == null) {

        String contactid = loanapplytdnewAF.getContractId();
        int loanmoney = Integer.parseInt(loanapplytdnewAF.getLoanMoney());
        int loanlimit = Integer.parseInt(loanapplytdnewAF.getLoantimeLimit());
        // 根据单位性质判断
        Houses houses = housesDAO.queryById(contactid);
        String housestype = houses.getHouseType();
        Borrower borrower = borrowerDAO.queryById(contactid);
        AssistantBorrower assistantBorrower = assistantBorrowerDAO
            .findByContractId(contactid);
        String office = borrower.getOffice();
        List agelist = loanConditionsSetDAO.queryManAge(office);
        String sex = borrower.getSex();
        int age = Integer.parseInt(borrower.getAge().toString());
        String num = "0";
        if (borrower.getEmpId() != null && !"".equals(borrower.getEmpId())
            && assistantBorrower != null
            && assistantBorrower.getEmpId() != null
            && !"".equals(assistantBorrower.getEmpId())) {
          num = "1";
        }
        // 异地贷款
        if (borrower.getLoanType() != null
            && borrower.getLoanType().equals("1")) {
          num = "2";
        }
        // String num=borrowerDAO.countPeopleNum(contactid);
        BigDecimal yuE = new BigDecimal(0.00);
        BigDecimal jiaoE = new BigDecimal(0.00);
        BigDecimal sum = new BigDecimal(0.00);
        // ///////////////////////////////////////////////////////////////////////////////////////
        // 单方拥有公积金不得超过？元
        String paramExplain_7_1 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("7", office, "1");
        // 有辅助贷款人的贷款金额不能超过？元
        String paramExplain_7_2 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("7", office, "2");
        // 贷款金额不能超过商品房价的 %
        String paramExplain_8 = loanConditionsSetDAO.queryIsUseParamValue("8",
            office);
        // 商品房贷款最高金额
        String paramExplain_10 = loanConditionsSetDAO.queryIsUseParamValue(
            "10", office);
        // 二手房贷款最高金额
        String paramExplain_11 = loanConditionsSetDAO.queryIsUseParamValue(
            "11", office);
        // 不得超过贷款家庭成员退休年龄内所交纳住房公积金数额 倍
        String paramExplain_12 = loanConditionsSetDAO.queryIsUseParamValue(
            "12", office);
        // 借款人月收入还款比例 %
        String paramExplain_14 = loanConditionsSetDAO.queryIsUseParamValue(
            "14", office);
        // 贷款金额不能超过二手房价的（建筑年限1——5年） %
        String paramExplain_9 = loanConditionsSetDAO.queryIsUseParamValue("9",
            office);
        // 单方拥有公积金不得超过？元
        String paramExplain_9_1 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("9", office, "1");
        // 有辅助贷款人的贷款金额不能超过？元
        String paramExplain_9_2 = loanConditionsSetDAO
            .queryIsUseParamValue_Thirteen_One("9", office, "2");

        if (housestype.equals("01")) {// 商品房
          // 贷款人实际年龄加贷款期限不超过
          if (num.equals("0") && borrower.getEmpId() != null
              && !"".equals(borrower.getEmpId())) {// 单方拥有公积金,借款人拥有公积金
            if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
              money_1 = new BigDecimal(paramExplain_7_1);
            }
            if (paramExplain_8 != null && !"".equals(paramExplain_8)) {
              money_2 = (new BigDecimal(houses.getHouseTotlePrice().toString()))
                  .multiply(new BigDecimal(paramExplain_8)).divide(
                      new BigDecimal(100), 2);
            }
            if (paramExplain_10 != null && !"".equals(paramExplain_10)) {
              money_3 = new BigDecimal(paramExplain_10);
            }

            if (borrower.getEmpId() != null || !"".equals(borrower.getEmpId())) {
              Emp emp = empDAO.queryByCriterions(
                  borrower.getEmpId().toString(), borrower.getOrgId()
                      .toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {
                money_5 = borrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
              }

              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              yuE = borrower.getAccBlnce();
              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                if (sex.equals("1")) {// 借款人性别男
                  if (sexdto.equals("1")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// 女

                  if (sexdto.equals("2")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }

            // 最小金额
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            // 最小金额
            System.out.println("商品房借款人拥有公积金");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("0") && assistantBorrower != null
              && assistantBorrower.getEmpId() != null
              && !"".equals(assistantBorrower.getEmpId())) {// 单方拥有公积金,辅助借款人拥有公积金
            if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
              money_1 = new BigDecimal(paramExplain_7_1);
            }
            if (paramExplain_8 != null && !"".equals(paramExplain_8)) {
              money_2 = (new BigDecimal(houses.getHouseTotlePrice().toString()))
                  .multiply(new BigDecimal(paramExplain_8)).divide(
                      new BigDecimal(100), 2);
            }
            if (paramExplain_10 != null && !"".equals(paramExplain_10)) {
              money_3 = new BigDecimal(paramExplain_10);
            }

            if (assistantBorrower.getEmpId() != null
                || !"".equals(assistantBorrower.getEmpId())) {
              Emp emp = empDAO.queryByCriterions(assistantBorrower.getEmpId()
                  .toString(), assistantBorrower.getOrgId().toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {
                money_5 = assistantBorrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
              }

              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              yuE = assistantBorrower.getAccBlnce();
              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                sex = assistantBorrower.getSex();
                if (sex.equals("1")) {// 借款人性别男
                  if (sexdto.equals("1")) {
                    jiaoE = (assistantBorrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - assistantBorrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// 女

                  if (sexdto.equals("2")) {
                    jiaoE = (assistantBorrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - assistantBorrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }

            // 最小金额
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            // 最小金额
            System.out.println("商品房辅助借款人拥有公积金");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("1")) {// 借款人和辅助借款人都有公积金
            // 贷款人实际年龄加贷款期限不超过
            if (paramExplain_7_2 != null && !"".equals(paramExplain_7_2)) {
              money_1 = new BigDecimal(paramExplain_7_2);
            }
            if (paramExplain_8 != null && !"".equals(paramExplain_8)) {
              money_2 = (new BigDecimal(houses.getHouseTotlePrice().toString()))
                  .multiply(new BigDecimal(paramExplain_8)).divide(
                      new BigDecimal(100), 2);
            }
            if (paramExplain_10 != null && !"".equals(paramExplain_10)) {
              money_3 = new BigDecimal(paramExplain_10);
            }

            LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                .queryOverHousePriceS_1(office);
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
            LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 = loanConditionsSetDAO
                .queryOverHousePriceS_14(office);
            String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contactid);
            String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contactid);
            String fuzhuSex = borrowerDAO.countPeopleNum_Sex(contactid);
            borrower = borrowerDAO.queryById(contactid);

            // if ((borrower.getEmpId()== null ||
            // "".equals(borrower.getEmpId()))&&(fuzhuEmpId==null||"".equals(fuzhuEmpId))){
            // throw new BusinessException("借款人及辅助借款人均未缴纳住房公积金！");
            // }
            if (borrower.getEmpId() != null && !"".equals(borrower.getEmpId())) {
              Emp emp = empDAO.queryByCriterions(
                  borrower.getEmpId().toString(), borrower.getOrgId()
                      .toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {// 启用了
                // 借款人
                BigDecimal money = borrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
                // 辅助借款人

                money_5 = money.add(assistantBorrower.getMonthSalary()
                    .multiply(new BigDecimal(paramExplain_14)).multiply(
                        new BigDecimal(loanlimit)).divide(new BigDecimal(100),
                        2));

              }
              yuE = borrower.getAccBlnce();// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
              // BigDecimal(12))));

              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                if (sex.equals("1")) {// 借款人性别男
                  if (sexdto.equals("1")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// 女

                  if (sexdto.equals("2")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            if (fuzhuEmpId != null && !"".equals(fuzhuEmpId)) {
              Emp emp = empDAO.queryByCriterions(fuzhuEmpId.toString(),
                  fuzhuOrgId.toString());
              yuE = yuE.add(assistantBorrower.getAccBlnce());// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
              // BigDecimal(12))));

              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                String fuZhuAge = "";
                fuZhuAge = borrowerDAO.countPeopleNum_Age(contactid);
                if (fuzhuSex.equals("1")) {// 借款人性别男
                  if (sexdto.equals("1")) {
                    jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
                        .multiply(new BigDecimal(12)).multiply(
                            new BigDecimal(paramExplain
                                - Integer.valueOf(
                                    assistantBorrower.getAge().toString())
                                    .intValue())));
                  }

                }
                if (fuzhuSex.equals("2")) {// 女

                  if (sexdto.equals("2")) {
                    jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
                        .multiply(new BigDecimal(12)).multiply(
                            new BigDecimal(paramExplain
                                - Integer.valueOf(
                                    assistantBorrower.getAge().toString())
                                    .intValue())));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }

            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }

            // 最小金额
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            // 最小金额
            System.out.println("商品房双方都拥有公积金");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("2")) {// 借款人和辅助借款人都有公积金
            // 贷款人实际年龄加贷款期限不超过
            // if(assistantBorrower!=null){
            // if (paramExplain_7_2 != null && !"".equals(paramExplain_7_2)) {
            // money_1 = new BigDecimal(paramExplain_7_2);
            // }
            // }else{
            // if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
            // money_1 = new BigDecimal(paramExplain_7_1);
            // }
            // }

            if (paramExplain_8 != null && !"".equals(paramExplain_8)) {
              money_2 = (new BigDecimal(houses.getHouseTotlePrice().toString()))
                  .multiply(new BigDecimal(paramExplain_8)).divide(
                      new BigDecimal(100), 2);
            }
            if (paramExplain_10 != null && !"".equals(paramExplain_10)) {
              money_3 = new BigDecimal(paramExplain_10);
            }

            // LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 =
            // loanConditionsSetDAO
            // .queryOverHousePriceS_1(office);
            // LoanConditionsParamSetDTO loanConditionsParamSetDTO = new
            // LoanConditionsParamSetDTO();
            // LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 =
            // loanConditionsSetDAO
            // .queryOverHousePriceS_14(office);
            // String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contactid);
            // String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contactid);
            // String fuzhuSex = borrowerDAO.countPeopleNum_Sex(contactid);
            // borrower = borrowerDAO.queryById(contactid);
            // // if (borrower.getEmpId() != null &&
            // !"".equals(borrower.getEmpId())) {
            // if (paramExplain_14 != null && !"".equals(paramExplain_14)) {//
            // 启用了
            // // 借款人
            // BigDecimal money = borrower.getMonthSalary().multiply(
            // new BigDecimal(paramExplain_14)).multiply(
            // new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
            // // 辅助借款人
            // if(assistantBorrower==null){
            // money_5 = money;
            // }else{
            // money_5 = money.add(assistantBorrower.getMonthSalary()
            // .multiply(new BigDecimal(paramExplain_14)).multiply(
            // new BigDecimal(loanlimit)).divide(new BigDecimal(100),
            // 2));
            // }
            //               
            //
            // }
            // yuE = borrower.getAccBlnce();
            // for (int i = 0; i < agelist.size(); i++) {
            // loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
            // .get(i);
            // String sexdto = loanConditionsParamSetDTO.getParamValue();
            // int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
            // .getParamExplain());
            // if (sex.equals("1")) {// 借款人性别男
            // if (sexdto.equals("1")) {
            // jiaoE = (borrower.getMonthPay()).multiply(
            // new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - borrower.getAge().intValue()));
            // }
            //
            // }
            // if (sex.equals("2")) {// 女
            //
            // if (sexdto.equals("2")) {
            // jiaoE = (borrower.getMonthPay()).multiply(
            // new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - borrower.getAge().intValue()));
            // }
            // }
            // }
            // sum = yuE.add(jiaoE);
            // // }
            // // if (fuzhuEmpId != null && !"".equals(fuzhuEmpId)) {
            // if(assistantBorrower!=null){
            // yuE = yuE.add(assistantBorrower.getAccBlnce());
            // // BigDecimal(12))));
            //
            // for (int i = 0; i < agelist.size(); i++) {
            // loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
            // .get(i);
            // String sexdto = loanConditionsParamSetDTO.getParamValue();
            // int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
            // .getParamExplain());
            // if (fuzhuSex.equals("1")) {// 借款人性别男
            // if (sexdto.equals("1")) {
            // jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
            // .multiply(new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - Integer.valueOf(
            // assistantBorrower.getAge().toString())
            // .intValue())));
            // }
            //
            // }
            // if (fuzhuSex.equals("2")) {// 女
            //
            // if (sexdto.equals("2")) {
            // jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
            // .multiply(new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - Integer.valueOf(
            // assistantBorrower.getAge().toString())
            // .intValue())));
            // }
            // }
            // }
            // sum = yuE.add(jiaoE);
            // }
            //              
            // // }
            //
            // int loanm;
            // if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
            // loanm = Integer.parseInt(paramExplain_12);
            // money_4 = sum.multiply(new BigDecimal(loanm));
            // }

            // 最小金额
            if (money_2.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            } else {
              money_temp = money_2;
            }
            // if (money_temp.compareTo(money_3) > 0
            // && money_3.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_3;
            // }
            // if (money_temp.compareTo(money_4) > 0
            // && money_4.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_4;
            // }
            // if (money_temp.compareTo(money_5) > 0
            // && money_5.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_5;
            // }
            // 最小金额
            System.out.println("商品房，异地");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
        }
        if (housestype.equals("02")) {// 二手房
          if (num.equals("0") && borrower.getEmpId() != null
              && !"".equals(borrower.getEmpId())) {// 单方拥有公积金,借款人拥有公积金
            if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
              money_1 = new BigDecimal(paramExplain_7_1);
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(1)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(5)) <= 0) {
              if (paramExplain_9 != null && !"".equals(paramExplain_9)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(6)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(10)) <= 0) {
              if (paramExplain_9_1 != null && !"".equals(paramExplain_9_1)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_1))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(11)) >= 0) {
              if (paramExplain_9_2 != null && !"".equals(paramExplain_9_2)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_2))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (paramExplain_11 != null && !"".equals(paramExplain_11)) {
              money_3 = new BigDecimal(paramExplain_11);
            }
            if (borrower.getEmpId() != null || !"".equals(borrower.getEmpId())) {
              Emp emp = empDAO.queryByCriterions(
                  borrower.getEmpId().toString(), borrower.getOrgId()
                      .toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {
                money_5 = borrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
              }

              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              yuE = borrower.getAccBlnce();
              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                if (sex.equals("1")) {// 借款人性别男
                  if (sexdto.equals("1")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// 女

                  if (sexdto.equals("2")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }
            // 最小金额
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            System.out.println("二手房借款人拥有公积金");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("0") && assistantBorrower != null
              && assistantBorrower.getEmpId() != null
              && !"".equals(assistantBorrower.getEmpId())) {// 单方拥有公积金,辅助借款人拥有公积金
            if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
              money_1 = new BigDecimal(paramExplain_7_1);
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(1)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(5)) <= 0) {
              if (paramExplain_9 != null && !"".equals(paramExplain_9)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(6)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(10)) <= 0) {
              if (paramExplain_9_1 != null && !"".equals(paramExplain_9_1)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_1))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(11)) >= 0) {
              if (paramExplain_9_2 != null && !"".equals(paramExplain_9_2)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_2))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (paramExplain_11 != null && !"".equals(paramExplain_11)) {
              money_3 = new BigDecimal(paramExplain_11);
            }
            if (assistantBorrower.getEmpId() != null
                || !"".equals(assistantBorrower.getEmpId())) {
              // Emp emp = empDAO.queryByCriterions(assistantBorrower.getEmpId()
              // .toString(),assistantBorrower.getOrgId().toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {
                money_5 = assistantBorrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
              }

              LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
              yuE = assistantBorrower.getAccBlnce();
              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                sex = assistantBorrower.getSex();
                if (sex.equals("1")) {// 借款人性别男
                  if (sexdto.equals("1")) {
                    jiaoE = (assistantBorrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - assistantBorrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// 女

                  if (sexdto.equals("2")) {
                    jiaoE = (assistantBorrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - assistantBorrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }
            // 最小金额
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            System.out.println("二手房辅助借款人拥有公积金");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("1")) {// 借款人和辅助借款人都拥有公积金
            // 贷款人实际年龄加贷款期限不超过
            if (paramExplain_7_2 != null && !"".equals(paramExplain_7_2)) {
              money_1 = new BigDecimal(paramExplain_7_2);
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(1)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(5)) <= 0) {
              if (paramExplain_9 != null && !"".equals(paramExplain_9)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(6)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(10)) <= 0) {
              if (paramExplain_9_1 != null && !"".equals(paramExplain_9_1)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_1))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(11)) >= 0) {
              if (paramExplain_9_2 != null && !"".equals(paramExplain_9_2)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_2))
                    .divide(new BigDecimal(100), BigDecimal.ROUND_CEILING);
              }
            }
            if (paramExplain_11 != null && !"".equals(paramExplain_11)) {
              money_3 = new BigDecimal(paramExplain_11);
            }
            LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 = loanConditionsSetDAO
                .queryOverHousePriceS_1(office);
            LoanConditionsParamSetDTO loanConditionsParamSetDTO = new LoanConditionsParamSetDTO();
            LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 = loanConditionsSetDAO
                .queryOverHousePriceS_14(office);
            String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contactid);
            String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contactid);
            String fuzhuSex = borrowerDAO.countPeopleNum_Sex(contactid);
            borrower = borrowerDAO.queryById(contactid);
            // if ((borrower.getEmpId()== null ||
            // "".equals(borrower.getEmpId()))&&(fuzhuEmpId==null||"".equals(fuzhuEmpId))){
            // throw new BusinessException("借款人及辅助借款人均未缴纳住房公积金！");
            // }
            if (borrower.getEmpId() != null && !"".equals(borrower.getEmpId())) {
              Emp emp = empDAO.queryByCriterions(
                  borrower.getEmpId().toString(), borrower.getOrgId()
                      .toString());
              if (paramExplain_14 != null && !"".equals(paramExplain_14)) {// 启用了
                // 借款人
                BigDecimal money = borrower.getMonthSalary().multiply(
                    new BigDecimal(paramExplain_14)).multiply(
                    new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
                // 辅助借款人
                Emp emp_fuzhu = empDAO.queryByCriterions(fuzhuEmpId.toString(),
                    fuzhuOrgId.toString());
                money_5 = money.add(assistantBorrower.getMonthSalary()
                    .multiply(new BigDecimal(paramExplain_14)).multiply(
                        new BigDecimal(loanlimit)).divide(new BigDecimal(100),
                        2));

              }
              yuE = borrower.getAccBlnce();// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
              // BigDecimal(12))));

              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                if (sex.equals("1")) {// 借款人性别男
                  if (sexdto.equals("1")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }

                }
                if (sex.equals("2")) {// 女

                  if (sexdto.equals("2")) {
                    jiaoE = (borrower.getMonthPay()).multiply(
                        new BigDecimal(12)).multiply(
                        new BigDecimal(paramExplain
                            - borrower.getAge().intValue()));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }
            if (fuzhuEmpId != null && !"".equals(fuzhuEmpId)) {
              Emp emp = empDAO.queryByCriterions(fuzhuEmpId.toString(),
                  fuzhuOrgId.toString());
              yuE = yuE.add(assistantBorrower.getAccBlnce());// .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
              // BigDecimal(12))));

              for (int i = 0; i < agelist.size(); i++) {
                loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
                    .get(i);
                String sexdto = loanConditionsParamSetDTO.getParamValue();
                int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
                    .getParamExplain());
                String fuZhuAge = "";
                fuZhuAge = borrowerDAO.countPeopleNum_Age(contactid);
                if (fuzhuSex.equals("1")) {// 借款人性别男
                  if (sexdto.equals("1")) {
                    jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
                        .multiply(new BigDecimal(12)).multiply(
                            new BigDecimal(paramExplain
                                - Integer.valueOf(
                                    assistantBorrower.getAge().toString())
                                    .intValue())));
                  }

                }
                if (fuzhuSex.equals("2")) {// 女

                  if (sexdto.equals("2")) {
                    jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
                        .multiply(new BigDecimal(12)).multiply(
                            new BigDecimal(paramExplain
                                - Integer.valueOf(
                                    assistantBorrower.getAge().toString())
                                    .intValue())));
                  }
                }
              }
              sum = yuE.add(jiaoE);
            }

            int loanm;
            if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
              loanm = Integer.parseInt(paramExplain_12);
              money_4 = sum.multiply(new BigDecimal(loanm));
            }
            // 最小金额
            if (money_1.compareTo(money_2) > 0
                && money_2.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_2;
            } else {
              money_temp = money_1;
            }
            if (money_temp.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            }
            if (money_temp.compareTo(money_4) > 0
                && money_4.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_4;
            }
            if (money_temp.compareTo(money_5) > 0
                && money_5.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_5;
            }
            // 最小金额
            System.out.println("二手房借款人和辅助借款人都拥有公积金");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
          if (num.equals("2")) {// 借款人和辅助借款人都拥有公积金
            // 贷款人实际年龄加贷款期限不超过
            // if(assistantBorrower!=null){
            // if (paramExplain_7_2 != null && !"".equals(paramExplain_7_2)) {
            // money_1 = new BigDecimal(paramExplain_7_2);
            // }
            // }else{
            // if (paramExplain_7_1 != null && !"".equals(paramExplain_7_1)) {
            // money_1 = new BigDecimal(paramExplain_7_1);
            // }
            // }

            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(1)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(5)) <= 0) {
              if (paramExplain_9 != null && !"".equals(paramExplain_9)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(6)) >= 0
                && houses.getBargainorHouseAge().compareTo(new BigDecimal(10)) <= 0) {
              if (paramExplain_9_1 != null && !"".equals(paramExplain_9_1)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_1))
                    .divide(new BigDecimal(100), 2);
              }
            }
            if (houses.getBargainorHouseAge().compareTo(new BigDecimal(11)) >= 0) {
              if (paramExplain_9_2 != null && !"".equals(paramExplain_9_2)) {
                money_2 = (new BigDecimal(houses.getBargainorTotlePrice()
                    .toString())).multiply(new BigDecimal(paramExplain_9_2))
                    .divide(new BigDecimal(100), BigDecimal.ROUND_CEILING);
              }
            }
            if (paramExplain_11 != null && !"".equals(paramExplain_11)) {
              money_3 = new BigDecimal(paramExplain_11);
            }
            // LoanConditionsParamSetDTO loanConditionsParamSetDTO_1 =
            // loanConditionsSetDAO
            // .queryOverHousePriceS_1(office);
            // LoanConditionsParamSetDTO loanConditionsParamSetDTO = new
            // LoanConditionsParamSetDTO();
            // LoanConditionsParamSetDTO loanConditionsParamSetDTO_14 =
            // loanConditionsSetDAO
            // .queryOverHousePriceS_14(office);
            // String fuzhuEmpId = borrowerDAO.countPeopleNum_EmpId(contactid);
            // String fuzhuOrgId = borrowerDAO.countPeopleNum_Id(contactid);
            // String fuzhuSex = borrowerDAO.countPeopleNum_Sex(contactid);
            // borrower = borrowerDAO.queryById(contactid);
            // // if ((borrower.getEmpId()== null ||
            // //
            // "".equals(borrower.getEmpId()))&&(fuzhuEmpId==null||"".equals(fuzhuEmpId))){
            // // throw new BusinessException("借款人及辅助借款人均未缴纳住房公积金！");
            // // }
            // // if (borrower.getEmpId() != null &&
            // !"".equals(borrower.getEmpId())) {
            // //
            // if (paramExplain_14 != null && !"".equals(paramExplain_14)) {//
            // 启用了
            // // 借款人
            // BigDecimal money = borrower.getMonthSalary().multiply(
            // new BigDecimal(paramExplain_14)).multiply(
            // new BigDecimal(loanlimit)).divide(new BigDecimal(100), 2);
            // // 辅助借款人
            //                
            // money_5 = money.add(assistantBorrower.getMonthSalary()
            // .multiply(new BigDecimal(paramExplain_14)).multiply(
            // new BigDecimal(loanlimit)).divide(new BigDecimal(100),
            // 2));
            //
            // }
            // yuE = borrower.getAccBlnce();//
            // .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
            // // BigDecimal(12))));
            //
            // for (int i = 0; i < agelist.size(); i++) {
            // loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
            // .get(i);
            // String sexdto = loanConditionsParamSetDTO.getParamValue();
            // int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
            // .getParamExplain());
            // if (sex.equals("1")) {// 借款人性别男
            // if (sexdto.equals("1")) {
            // jiaoE = (borrower.getMonthPay()).multiply(
            // new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - borrower.getAge().intValue()));
            // }
            //
            // }
            // if (sex.equals("2")) {// 女
            //
            // if (sexdto.equals("2")) {
            // jiaoE = (borrower.getMonthPay()).multiply(
            // new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - borrower.getAge().intValue()));
            // }
            // }
            // }
            // sum = yuE.add(jiaoE);
            // // }
            // // if (fuzhuEmpId != null && !"".equals(fuzhuEmpId)) {
            // yuE = yuE.add(assistantBorrower.getAccBlnce());//
            // .add((emp.getOrgPay().add(emp.getEmpPay()).multiply(new
            // // BigDecimal(12))));
            //
            // for (int i = 0; i < agelist.size(); i++) {
            // loanConditionsParamSetDTO = (LoanConditionsParamSetDTO) agelist
            // .get(i);
            // String sexdto = loanConditionsParamSetDTO.getParamValue();
            // int paramExplain = Integer.parseInt(loanConditionsParamSetDTO
            // .getParamExplain());
            // if (fuzhuSex.equals("1")) {// 借款人性别男
            // if (sexdto.equals("1")) {
            // jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
            // .multiply(new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - Integer.valueOf(
            // assistantBorrower.getAge().toString())
            // .intValue())));
            // }
            //
            // }
            // if (fuzhuSex.equals("2")) {// 女
            //
            // if (sexdto.equals("2")) {
            // jiaoE = jiaoE.add((assistantBorrower.getMonthPay())
            // .multiply(new BigDecimal(12)).multiply(
            // new BigDecimal(paramExplain
            // - Integer.valueOf(
            // assistantBorrower.getAge().toString())
            // .intValue())));
            // }
            // }
            // }
            // sum = yuE.add(jiaoE);
            // // }
            //
            // int loanm;
            // if (paramExplain_12 != null && !"".equals(paramExplain_12)) {
            // loanm = Integer.parseInt(paramExplain_12);
            // money_4 = sum.multiply(new BigDecimal(loanm));
            // }
            // 最小金额
            if (money_2.compareTo(money_3) > 0
                && money_3.compareTo(new BigDecimal(0)) != 0) {
              money_temp = money_3;
            } else {
              money_temp = money_2;
            }
            // if (money_temp.compareTo(money_3) > 0
            // && money_3.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_3;
            // }
            // if (money_temp.compareTo(money_4) > 0
            // && money_4.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_4;
            // }
            // if (money_temp.compareTo(money_5) > 0
            // && money_5.compareTo(new BigDecimal(0)) != 0) {
            // money_temp = money_5;
            // }
            // 最小金额
            System.out.println("二手房借款人和辅助借款人都拥有公积金");
            System.out.println("1" + money_1);
            System.out.println("2" + money_2);
            System.out.println("3" + money_3);
            System.out.println("4" + money_4);
            System.out.println("5" + money_5);
            if (money_temp.compareTo(new BigDecimal(0)) == 0) {
              money_temp = new BigDecimal(100000000);
            }
          }
        }
        // ///////////////////////////////////////////////////////////////////////////////////////
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return money_temp;
  }

  public void canLoancallback_ws(LoancallbackTaAF loancallbackTaAF,
      SecurityInfo securityInfo) throws BusinessException {
    String office = loancallbackTaAF.getBorrowerInfoDTO().getOfficeCode();
    if (office != null) {
      String paramExplain = loanConditionsSetDAO.queryIsUseParamValue("14",
          office);
      if (paramExplain != null && !"".equals(paramExplain)) {
        String contractId = loancallbackTaAF.getBorrowerInfoDTO()
            .getContractId();
        BigDecimal monthSalary = borrowerDAO.findBorrrowInfoByContractid(
            contractId).getMonthSalary();
        BigDecimal corpusInterest = monthSalary.multiply(
            new BigDecimal(paramExplain)).divide(new BigDecimal(100), 4,
            BigDecimal.ROUND_HALF_UP);
        if (loancallbackTaAF.getCorpusInterest().compareTo(corpusInterest) > 0) {
          throw new BusinessException("提前还款后月还本息不得低于月收入还款比例的" + paramExplain
              + "%");
        }
      }

    }
  }

  public void setOrgDAO(OrgDAO orgDAO) {
    this.orgDAO = orgDAO;
  }

  public void setAssistantBorrowerDAO(AssistantBorrowerDAO assistantBorrowerDAO) {
    this.assistantBorrowerDAO = assistantBorrowerDAO;
  }

}