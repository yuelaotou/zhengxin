package org.xpup.hafmis.sysloan.loanaccord.printplan.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.arithmetic.corpusinterest.CorpusinterestBS;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.loanaccord.printplan.bsinterface.IPrintplanBS;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanDTO;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanListDTO;
import org.xpup.hafmis.sysloan.loanaccord.printplan.form.PrintplanShowAF;


public class PrintplanBS implements IPrintplanBS {
  private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;

  private CollBankDAO collBankDAO = null;

  private RestoreLoanDAO restoreLoanDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
    this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  // 查找合同编号在pl111的对应的201中的信息。传入条件就是为了分页和排序。
  public PrintplanShowAF findPrintplanList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    List printList = new ArrayList();
    PrintplanShowAF printplanShowAF = new PrintplanShowAF();
    BigDecimal interestMoney = new BigDecimal(0.00);
    try {
      List list = new ArrayList();
      List returnlist = new ArrayList();
      List restoreLoanlist = new ArrayList();
      List printplanAllList = new ArrayList();
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      boolean isFindContractid = borrowerLoanInfoDAO
          .isFindContractidByContractid(contractId);
      if (!isFindContractid) {
        throw new BusinessException("此合同编号不存在！");
      }
      interestMoney = borrowerLoanInfoDAO.queryInterest_jj(contractId);
      // 合同的状态
      String contractSt = "10,11";
      list = borrowerLoanInfoDAO.queryBorrowerLoanTbInfo_sy(contractSt,
          contractId, securityInfo);
      if (!list.isEmpty()) {
        PrintplanDTO printplanDTO = (PrintplanDTO) list.get(0);
        // 合同编号
        printplanDTO.setContractId(contractId);
        // 证件类型对应的名称
        printplanDTO.setCardKindName(BusiTools.getBusiValue(Integer
            .parseInt(printplanDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
        // 通过bankId查找银行的名称
        String bankId = printplanDTO.getLoanBankId();
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(bankId);
        printplanDTO.setLoanBankName(collBank.getCollBankName());
        // 还款方式
        printplanDTO.setLoanModeName(BusiTools.getBusiValue(Integer
            .parseInt(printplanDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));
        printplanShowAF.setPrintplanDTO(printplanDTO);
        printplanShowAF.setInterest(interestMoney);
        if (printplanDTO.getContractSt().equals("11")) {
          restoreLoanlist = restoreLoanDAO.queryRestoreLoanListByContractId_sy(contractId, orderBy, order, start, pageSize);
          if (!restoreLoanlist.isEmpty()) {
            for (int j = 0; j < restoreLoanlist.size(); j++) {
              PrintplanListDTO printplanListDTO = new PrintplanListDTO();
              printplanListDTO = (PrintplanListDTO) restoreLoanlist.get(j);
              printplanListDTO.setCiMoney(printplanListDTO.getShouldCorpus()
                  .add(printplanListDTO.getShouldInterest()));
              printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                  .multiply(new BigDecimal(100.00)).toString()
                  + "%");
              returnlist.add(printplanListDTO);
            }
          }
          // 打印准备数据
          printplanAllList = restoreLoanDAO
              .countRestoreLoanListByContractId_sy(contractId);
          if (!printplanAllList.isEmpty()) {
            for (int j = 0; j < printplanAllList.size(); j++) {
              PrintplanListDTO printplanListDTO = new PrintplanListDTO();
              printplanListDTO = (PrintplanListDTO) printplanAllList.get(j);
              printplanListDTO.setCiMoney(printplanListDTO.getShouldCorpus()
                  .add(printplanListDTO.getShouldInterest()));
              printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                  .multiply(new BigDecimal(100.00)).toString()
                  + "%");
              printList.add(printplanListDTO);
            }
          }
        }
        if (printplanDTO.getContractSt().equals("10")) {
          restoreLoanDAO.deleteFnTempTable();
          String loanKouYearMonth = "";// 还款年月
          BigDecimal loanMonthRate = new BigDecimal(0.00);// 新月利率
          // 查询pl115
          Object[] obj = new Object[3];
          obj = borrowerLoanInfoDAO.querybyContractId(printplanDTO
              .getContractId());
          // 查询pl001
          Object[] object = new Object[2];
          object = borrowerLoanInfoDAO.querybyLoanBankId(obj[1].toString(),
              bankId);
          if (object[0].toString().equals("0")) {
            loanMonthRate = new BigDecimal(obj[2].toString());
          } else {
            loanMonthRate = new BigDecimal(object[1].toString());
          }
          // 计算新月还本息
          BigDecimal loanMoney = printplanDTO.getLoanMoney();// 贷款金额&&剩余本金
          BigDecimal newMonthRepay = CorpusinterestBS.getCorpusInterest(
              loanMoney, loanMonthRate, printplanDTO.getLoanTimeLimit());
          String loanRepayDay = printplanDTO.getLoanRepayDay();// 还款日
          String loanStartDate = printplanDTO.getLoanStartDate();// 贷款发放日期
          String startDate = BusiTools.addMonth(loanStartDate.substring(0, 6),
              1);// 开始日期
          int type=13;
          int temp_i=0;
          if(Integer.parseInt(startDate.substring(4, 6))!=12){
            temp_i=Integer.parseInt(startDate.substring(4, 6));
          }
          if (Integer.parseInt(loanRepayDay) > 28) {
            int temp_loanRepayDay = BusiTools.daysOfMonth(Integer
                .parseInt(startDate.substring(0, 4)), Integer
                .parseInt(startDate.substring(4, 6)));
            if (Integer.parseInt(loanRepayDay) > temp_loanRepayDay) {
              loanRepayDay = String.valueOf(temp_loanRepayDay);
            }
          }
          if (Integer.parseInt(printplanDTO.getLoanMode()) > 3) {
            // 整年期贷款
            loanKouYearMonth = BusiTools.addMonth(startDate, Integer
                .parseInt(printplanDTO.getLoanTimeLimit()));
            String corpusInterest = obj[0].toString();
            BigDecimal shouldInterest = new BigDecimal(corpusInterest)
                .subtract(loanMoney);
            PrintplanListDTO printplanListDTO = new PrintplanListDTO();
            printplanListDTO.setLoanKouYearmonth(loanKouYearMonth);
            printplanListDTO.setShouldCorpus(loanMoney);
            printplanListDTO.setShouldInterest(shouldInterest);
            printplanListDTO.setCiMoney(printplanListDTO.getShouldCorpus().add(
                printplanListDTO.getShouldInterest()));
            printplanListDTO.setLoanRate(loanMonthRate);
            printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                .multiply(new BigDecimal(100.00)).toString()
                + "%");
            restoreLoanDAO.insertFnTempTable(printplanListDTO);
//            returnlist.add(printplanListDTO);
          } else if (Integer.parseInt(printplanDTO.getLoanMode()) == 2) {
            // 等额本息
            String tempstartDate = startDate;
            for (int i = temp_i; i < type; i++) {
              // 最多插入13条
              int tempmonth = BusiTools.getDisMonths(tempstartDate.substring(0, 4)+"-"+tempstartDate.substring(4, 6)+"-01",
                  loanStartDate.substring(0, 4)+"-"+loanStartDate.substring(4, 6)+"-01");
              if (tempmonth > Integer.parseInt(printplanDTO.getLoanTimeLimit())) {
                // 开始日期-发放日期（年月）的月数是否大于贷款期限
                break;
              }
              if (i == 0) {
                // 第一个月
                List param = loanBankParaDAO.queryLoanBankPara_sy(bankId, "2",
                    "A");
                if (param.size() > 0) {
                  Object[] value=new Object[2];
                  value = (Object[]) param.get(0);
                  if (value[0].toString().equals("1")) {
                    // 按户定日
                    BigDecimal shouldInterest = loanMoney
                        .multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                    PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                    printplanListDTO.setLoanKouYearmonth(tempstartDate);
                    printplanListDTO.setShouldCorpus(newMonthRepay
                        .subtract(shouldInterest));
                    printplanListDTO.setShouldInterest(shouldInterest);
                    printplanListDTO.setCiMoney(newMonthRepay);
                    printplanListDTO.setLoanRate(loanMonthRate);
                    printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                        .multiply(new BigDecimal(100.00)).toString()
                        + "%");
                    restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                    returnlist.add(printplanListDTO);
                    //剩余本金=剩余本金-SHOULD_CORPUS
                    loanMoney = loanMoney.subtract(newMonthRepay.subtract(shouldInterest));
                  } else {
                    // 统一定日
                    int tempDay = BusiTools.minusDate(startDate.substring(0, 4)+"-"+startDate.substring(4, 6) +"-"+ loanRepayDay,
                        loanStartDate.substring(0, 4)+"-"+loanStartDate.substring(4, 6)+"-"+loanStartDate.substring(6, 8));
                    // 应还利息=剩余本金*月利率*（带月的LOAN_REPAY_DAY-发放日期）/30
                    BigDecimal shouldInterest = loanMoney.multiply(
                        loanMonthRate).multiply(new BigDecimal(tempDay))
                        .divide(new BigDecimal("30"), 2,
                            BigDecimal.ROUND_HALF_UP);
                    // 应还本金=月还本息-剩余本金*月利率
                    BigDecimal temp = loanMoney.multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal shouldCorpus = newMonthRepay.subtract(temp);
                    PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                    printplanListDTO.setLoanKouYearmonth(tempstartDate);
                    printplanListDTO.setShouldCorpus(shouldCorpus);
                    printplanListDTO.setShouldInterest(shouldInterest);
                    printplanListDTO.setCiMoney(shouldCorpus
                        .add(shouldInterest));
                    printplanListDTO.setLoanRate(loanMonthRate);
                    printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                        .multiply(new BigDecimal(100.00)).toString()
                        + "%");
                    restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                    returnlist.add(printplanListDTO);
                    // 剩余本金=剩余本金-SHOULD_CORPUS
                    loanMoney = loanMoney.subtract(shouldCorpus);
                  }
                }
              }
              if ((i>0)&&(tempmonth < Integer.parseInt(printplanDTO.getLoanTimeLimit()))) {
                // 中间月
                BigDecimal shouldInterest = loanMoney.multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                printplanListDTO.setLoanKouYearmonth(tempstartDate);
                printplanListDTO.setShouldCorpus(newMonthRepay
                    .subtract(shouldInterest));
                printplanListDTO.setShouldInterest(shouldInterest);
                printplanListDTO.setCiMoney(newMonthRepay);
                printplanListDTO.setLoanRate(loanMonthRate);
                printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                    .multiply(new BigDecimal(100.00)).toString()
                    + "%");
                restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                returnlist.add(printplanListDTO);
                //剩余本金=剩余本金-SHOULD_CORPUS
                loanMoney = loanMoney.subtract(newMonthRepay.subtract(shouldInterest));
              }
              if ((i>0)&&(tempmonth == Integer.parseInt(printplanDTO.getLoanTimeLimit()))) {
                //最后一个月
                BigDecimal shouldInterest = loanMoney.multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                printplanListDTO.setLoanKouYearmonth(tempstartDate);
                printplanListDTO.setShouldCorpus(loanMoney);
                printplanListDTO.setShouldInterest(shouldInterest);
                printplanListDTO.setCiMoney(loanMoney.add(shouldInterest));
                printplanListDTO.setLoanRate(loanMonthRate);
                printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                    .multiply(new BigDecimal(100.00)).toString()
                    + "%");
                restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                returnlist.add(printplanListDTO);
              }
              tempstartDate = BusiTools.addMonth(tempstartDate.substring(0, 6),
                  1);// 开始日期
            }
          } else if (Integer.parseInt(printplanDTO.getLoanMode()) < 4) {
            // 等额本金和其它
            //计算应还本金
            BigDecimal shouldCorpus=loanMoney.divide(new BigDecimal(printplanDTO.getLoanTimeLimit()), 2,
                BigDecimal.ROUND_HALF_UP);
            String tempstartDate = startDate;
            for (int i = temp_i; i < type; i++) {
              // 最多插入13条
              int tempmonth = BusiTools.getDisMonths(tempstartDate.substring(0, 4)+"-"+tempstartDate.substring(4, 6)+"-01",
                  loanStartDate.substring(0, 4)+"-"+loanStartDate.substring(4, 6)+"-01");
              if (tempmonth > Integer.parseInt(printplanDTO.getLoanTimeLimit())) {
                // 开始日期-发放日期（年月）的月数是否大于贷款期限
                break;
              }
              if (i == 0) {
                // 第一个月
                List param = loanBankParaDAO.queryLoanBankPara_sy(bankId, "2",
                    "A");
                if (param.size() > 0) {
                  Object[] value=new Object[2];
                  value = (Object[]) param.get(0);
                  if (value[0].toString().equals("1")) {
                    // 按户定日
                    BigDecimal shouldInterest = loanMoney
                        .multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                    PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                    printplanListDTO.setLoanKouYearmonth(tempstartDate);
                    printplanListDTO.setShouldCorpus(shouldCorpus);
                    printplanListDTO.setShouldInterest(shouldInterest);
                    printplanListDTO.setCiMoney(shouldCorpus.add(shouldInterest));
                    printplanListDTO.setLoanRate(loanMonthRate);
                    printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                        .multiply(new BigDecimal(100.00)).toString()
                        + "%");
                    restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                    returnlist.add(printplanListDTO);
                    //剩余本金=剩余本金-SHOULD_CORPUS
                    loanMoney = loanMoney.subtract(shouldCorpus);
                  } else {
                    // 统一定日
                    int tempDay = BusiTools.minusDate(startDate.substring(0, 4)+"-"+startDate.substring(4, 6) +"-"+ loanRepayDay,
                        loanStartDate.substring(0, 4)+"-"+loanStartDate.substring(4, 6)+"-"+loanStartDate.substring(6, 8));
                    // 应还利息=剩余本金*月利率*（带月的LOAN_REPAY_DAY-发放日期）/30
                    BigDecimal shouldInterest = loanMoney.multiply(
                        loanMonthRate).multiply(new BigDecimal(tempDay))
                        .divide(new BigDecimal("30"), 2,
                            BigDecimal.ROUND_HALF_UP);
                    PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                    printplanListDTO.setLoanKouYearmonth(tempstartDate);
                    printplanListDTO.setShouldCorpus(shouldCorpus);
                    printplanListDTO.setShouldInterest(shouldInterest);
                    printplanListDTO.setCiMoney(shouldCorpus
                        .add(shouldInterest));
                    printplanListDTO.setLoanRate(loanMonthRate);
                    printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                        .multiply(new BigDecimal(100.00)).toString()
                        + "%");
                    restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                    returnlist.add(printplanListDTO);
                    // 剩余本金=剩余本金-SHOULD_CORPUS
                    loanMoney = loanMoney.subtract(shouldCorpus);
                  }
                }
              }
              if ((i>0)&&(tempmonth < Integer.parseInt(printplanDTO.getLoanTimeLimit()))) {
                // 中间月
                BigDecimal shouldInterest = loanMoney.multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                printplanListDTO.setLoanKouYearmonth(tempstartDate);
                printplanListDTO.setShouldCorpus(shouldCorpus);
                printplanListDTO.setShouldInterest(shouldInterest);
                printplanListDTO.setCiMoney(shouldCorpus.add(shouldInterest));
                printplanListDTO.setLoanRate(loanMonthRate);
                printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                    .multiply(new BigDecimal(100.00)).toString()
                    + "%");
                restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                returnlist.add(printplanListDTO);
                //剩余本金=剩余本金-SHOULD_CORPUS
                loanMoney = loanMoney.subtract(shouldCorpus);
              }
              if ((i>0)&&(tempmonth == Integer.parseInt(printplanDTO.getLoanTimeLimit()))) {
                //最后一个月
                BigDecimal shouldInterest = loanMoney.multiply(loanMonthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
                PrintplanListDTO printplanListDTO = new PrintplanListDTO();
                printplanListDTO.setLoanKouYearmonth(tempstartDate);
                printplanListDTO.setShouldCorpus(loanMoney);
                printplanListDTO.setShouldInterest(shouldInterest);
                printplanListDTO.setCiMoney(loanMoney.add(shouldInterest));
                printplanListDTO.setLoanRate(loanMonthRate);
                printplanListDTO.setTemploanRate(printplanListDTO.getLoanRate()
                    .multiply(new BigDecimal(100.00)).toString()
                    + "%");
                restoreLoanDAO.insertFnTempTable(printplanListDTO);
//                returnlist.add(printplanListDTO);
              }
              tempstartDate = BusiTools.addMonth(tempstartDate.substring(0, 6),
                  1);// 开始日期
            }
          }

          returnlist = restoreLoanDAO.queryFnTempTable(orderBy, order, start, pageSize);
          // 打印准备数据
          printplanAllList = restoreLoanDAO.queryFnTempTableAll();
          if (!printplanAllList.isEmpty()) {
            for (int j = 0; j < printplanAllList.size(); j++) {
              PrintplanListDTO printplanListDTO = new PrintplanListDTO();
              printplanListDTO = (PrintplanListDTO) printplanAllList.get(j);
              printList.add(printplanListDTO);
            }
          }
        
        }
        pagination.setNrOfElements(printplanAllList.size());
        printplanShowAF.setPrintList(printList);
        printplanShowAF.setList(returnlist);
        printplanShowAF.setBizDate(securityInfo.getUserInfo().getPlbizDate());
        printplanShowAF.setOperson(securityInfo.getRealName());
      } else {
        throw new BusinessException("此合同编号还为记账！");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return printplanShowAF;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

}
