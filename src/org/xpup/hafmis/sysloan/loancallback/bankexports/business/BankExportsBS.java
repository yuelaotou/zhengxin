package org.xpup.hafmis.sysloan.loancallback.bankexports.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.common.arithmetic.punishinterest.PunishInterestBS;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loancallback.bankexports.bsinterface.IBankExportsBS;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;

public class BankExportsBS implements IBankExportsBS {

  private LoanFlowTailDAO loanFlowTailDAO = null;

  private CollParaDAO collParaDAO = null;

  private RestoreLoanDAO restoreLoanDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private PlBizActiveLogDAO plBizActiveLogDAO = null;

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private LoanBankDAO loanBankDAO = null;

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setLoanBankParaDAO(LoanBankParaDAO loanBankParaDAO) {
    this.loanBankParaDAO = loanBankParaDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  /**
   * 判断还款日如果大于本月最后一天返回本月最后一天
   * 
   * @param yearMonth
   * @param loanRepayDay
   * @return
   */
  public String getEndDay(String yearMonth, String loanRepayDay) {
    String day = loanRepayDay;
    String year = yearMonth.substring(0, 4);
    String month = yearMonth.substring(4, 6);
    int days = BusiTools.daysOfMonth(Integer.parseInt(year), Integer
        .parseInt(month));
    if (Integer.parseInt(loanRepayDay) > days) {
      day = String.valueOf(days);
    }
    if (day.length() < 2 && Integer.parseInt(day) < 10) {
      day = "0" + day;
    }
    return day;
  }

  /**
   * 查询还至年月列表 会计年月到会计年12月
   * 
   * @param securityInfo
   * @return
   */
  public List getYearMonthList(SecurityInfo securityInfo) {
    List list = new ArrayList();
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    String year = bizDate.substring(0, 4);
    String month = bizDate.substring(4, 6);
    int iMonth = Integer.parseInt(month) - 1;
    int temp_month = iMonth;
    String smonth = "";
    for (int i = iMonth; i < 12; i++) {
      BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
      temp_month += 1;
      if (temp_month < 10) {
        smonth = "0" + temp_month;
      } else {
        smonth = String.valueOf(temp_month);
      }
      dto.setLoanKouYearmonth(year + smonth);
      list.add(dto);
    }
    return list;
  }

  /**
   * 生成银行代扣数据
   */
  public String createDataBankCallbackList(Pagination pagination,
      SecurityInfo securityInfo, String type_gjp) throws Exception {
    String loanBankId = (String) pagination.getQueryCriterions().get(
        "loanBankId");
    String yearMonth = (String) pagination.getQueryCriterions()
        .get("yearMonth");
    String day = (String) pagination.getQueryCriterions().get("day");
    String batchNum = "";
    if (day != null && !day.equals("")) {
      if (Integer.parseInt(day) < 10) {
        day = "0" + day;
      }
    }
    String date = yearMonth + day;
    List temp_list = null;
    List callbackList = null;
    List bizStlist = null;
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 会计日期
    // String day = "";
    BigDecimal overLoanRepay = new BigDecimal(0.00);// 挂账余额
    BigDecimal temp_money = new BigDecimal(0.00);// 跟挂账余额比较判断是否出盘
    int shouldCount = 0;// 应还人数
    int realCount = 0;// 实还人数
    BigDecimal shouldCorpus = new BigDecimal(0.00);// 应还正常本金
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);// 应还逾期本金
    BigDecimal shouldInterest = new BigDecimal(0.00);// 应还正常利息
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);// 应还逾期利息
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);// 应还罚息
    BigDecimal realCorpus = new BigDecimal(0.00);// 应还正常本金
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);// 应还逾期本金
    BigDecimal realInterest = new BigDecimal(0.00);// 应还正常利息
    BigDecimal realOverdueInterest = new BigDecimal(0.00);// 应还逾期利息
    BigDecimal realPunishInterest = new BigDecimal(0.00);// 应还罚息
    BigDecimal occurMoney = new BigDecimal(0.00);// 发生额
    String office = securityInfo.getUserInfo().getOfficeId().toString();
    String loanRepayDay = "";// 还款日
    // day = bizDate.substring(6,8);
    String headId = "";
    String bankDate = "";// 银行日期
    // 判断该银行下在贷款流水账头表PL202中是否存在业务类型BIZ_TYPE=5.批量回收，业务状态=1，导出的记录
    if (loanBankId != null && !loanBankId.equals("")) {
      bankDate = loanBankDAO.queryPL002BizDate_jj(loanBankId.toString());
      if (!bizDate.equals(bankDate)) {
        throw new BusinessException("登录日期与银行业务日期不一致，不能做业务！");
      }
      int iClearYear = Integer.parseInt(bizDate.substring(0, 4)) - 1;
      String clearYear = this.getClearYear(String.valueOf(loanBankId));
      // 会计年份-1如果不等于PL002中的年结年份则不允许回收
      if (iClearYear > Integer.parseInt(clearYear)) {
        throw new BusinessException(iClearYear + "年尚未年结，不允许进行回收业务！");
      }
      List idList = null;
      if (type_gjp.equals("gjj")) {// 生成公积金还贷数据
        List contractIdList = loanFlowHeadDAO.queryContractId_GJP();
        if (contractIdList != null) {
          idList = new ArrayList();
          for (int i = 0; i < contractIdList.size(); i++) {
            String contract = (String) contractIdList.get(i);
            idList.add(contract);
          }
        }
      }
      List temp_list_yga = new ArrayList();
      List temp_list_ygb = new ArrayList();
      if (type_gjp.equals("gjj")) {// 生成公积金还贷数据
        // 查询公积金还贷是否有数据
        temp_list = loanFlowHeadDAO.queryExportListByLoanKouAcc_GJP(loanBankId,
            String.valueOf(BusiConst.PLBUSINESSSTATE_EXP), idList);
        // 查询银行代扣是否有数据
        temp_list_yga = loanFlowHeadDAO.queryExportListByLoanKouAcc_GJP_yg(
            loanBankId, String.valueOf(BusiConst.PLBUSINESSSTATE_EXP), idList);
      } else {// 生成银行代扣数据
        // 查询银行代扣是否有数据
        temp_list = loanFlowHeadDAO.queryExportListByLoanKouAcc_LJ(loanBankId,
            String.valueOf(BusiConst.PLBUSINESSSTATE_EXP), "");
        // 查询公积金还贷是否有数据
        temp_list_ygb = loanFlowHeadDAO.queryExportListByLoanKouAcc_LJ_yg(
            loanBankId, String.valueOf(BusiConst.PLBUSINESSSTATE_EXP), "");
      }
      if (!temp_list.isEmpty()) {
        Object obj[] = null;
        Iterator it = temp_list.iterator();
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          if (obj[1] != null) {
            if (date != null && !date.equals("")) {
              if (!date.equals(obj[1].toString())) {
                pagination.getQueryCriterions().put("yearMonth", null);
                pagination.getQueryCriterions().put("day", null);
                throw new BusinessException("此银行下已经存在未回收的数据，不允许导出！");
              }
            }
          }
        }
      }
      // 如果是生成公积金还贷数据，看看是否有生成银行数据还未记账的！
      if (!temp_list_yga.isEmpty() && type_gjp.equals("gjj")) {
        pagination.getQueryCriterions().put("yearMonth", null);
        pagination.getQueryCriterions().put("day", null);
        pagination.getQueryCriterions().put("batchNum", "sun_no_batch_num");
        throw new BusinessException("此银行下已经存在银行代扣未回收的数据，不允许导出！");
      }
      // 如果是生成银行数据，看看是否有生成公积金还贷数据还未记账的！
      if (!temp_list_ygb.isEmpty() && type_gjp.equals("bank")) {
        pagination.getQueryCriterions().put("yearMonth", null);
        pagination.getQueryCriterions().put("day", null);
        pagination.getQueryCriterions().put("batchNum", null);
        throw new BusinessException("此银行下已经存在公积金还贷未回收的数据，不允许导出！");
      }

      if (temp_list.isEmpty()) {// 如果为空(202中无记录)，取出201的数据插入流水表
        // 判断该银行下在贷款流水账头表PL202中是否存在其它类型未记账的业务
        bizStlist = loanFlowHeadDAO.queryBizStListByLoanBank_LJ(loanBankId);
        if (!bizStlist.isEmpty()) {
          throw new BusinessException("该银行下存在未记账的业务！");
        } else {
          String num = "";
          if (type_gjp.equals("gjj")) {// 生成公积金还贷数据
            List list = this.getNum_yg(loanBankId);
            for (int i = 0; i < list.size(); i++) {
              if (list.get(i) != null && !list.get(i).equals("")) {
                num = (String) list.get(i);
              }
            }
            if (num.equals("")) {
              String num_601 = this
                  .getPL601Num(office, bizDate.substring(0, 4));
              if (num_601.equals("")) {
                num = this.getPL600Num(office, bizDate.substring(0, 4));
                this.updatePL600Num(office, bizDate.substring(0, 4), num);
              } else {
                num = num_601;
                this.deletePL601Num(office, bizDate.substring(0, 4), num);
              }
              Map officeMap = securityInfo.getOfficeInnerCodeMap();
              String officecode = officeMap.get(office).toString();
              if (officecode.length() == 1) {
                officecode = "0" + officecode;
              }
              num = officecode + bizDate.substring(0, 4) + num;
            }
          }
          batchNum = num;
          if (batchNum != null && !batchNum.equals("")) {
            callbackList = restoreLoanDAO.queryRestoreLoanListByBank_LJ_gjj(
                loanBankId, yearMonth, day);
          } else {
            // 选择还至年月 点击生成扣款数据
            callbackList = restoreLoanDAO.queryRestoreLoanListByBank_LJ(
                loanBankId, yearMonth, day);
          }
          if (!callbackList.isEmpty()) {
            // 插入流水头表
            String officeId = "";
            String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
            if (loanDocNumDocument.equals("1")) {
              officeId = loanBankDAO.queryOfficeCodeByBankId_LJ(loanBankId);
            } else {
              officeId = loanBankId.toString();
            }
            String docNum = plDocNumMaintainDAO.getDocNumdocNum(loanBankId,
                bizDate.substring(0, 4));
            Map office_yg = securityInfo.getOfficeInnerCodeMap();
            String officecode = office_yg.get(officeId).toString();
            if (officecode.length() == 1) {
              officecode = "0" + officecode;
            }
            docNum = bizDate.substring(0, 4) + officecode + "0" + loanBankId
                + docNum;
            // office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanBankId);
            LoanFlowHead loanFlowHead = new LoanFlowHead();
            loanFlowHead.setDocNum(docNum);
            loanFlowHead.setBizDate(bizDate);
            loanFlowHead.setBizType(String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER));
            loanFlowHead
                .setBizSt(String.valueOf(BusiConst.PLBUSINESSSTATE_EXP));
            loanFlowHead.setRealCount(new BigDecimal(0.00));
            loanFlowHead.setRealCorpus(new BigDecimal(0.00));
            loanFlowHead.setRealInterest(new BigDecimal(0.00));
            loanFlowHead.setRealOverdueCorpus(new BigDecimal(0.00));
            loanFlowHead.setRealOverdueInterest(new BigDecimal(0.00));
            loanFlowHead.setRealPunishInterest(new BigDecimal(0.00));
            loanFlowHead.setOtherInterest(new BigDecimal(0.00));
            loanFlowHead.setLoanBankId(new BigDecimal(loanBankId));
            loanFlowHead.setMakePerson(securityInfo.getUserName());
            loanFlowHead.setIsFinance(new Integer(1));
            loanFlowHead.setReserveaA(yearMonth + day);
            if (batchNum != null && !batchNum.equals("")) {
              loanFlowHead.setBatchNum(batchNum.trim());
            }
            loanFlowHeadDAO.insert(loanFlowHead);
            headId = loanFlowHead.getFlowHeadId().toString();
            String temp_contractId = "0";
            callbackList = this.getCallbackList(callbackList, new Integer(
                loanBankId), date);
            BigDecimal last_temp_compare_money = new BigDecimal(0.00);// 上次已经set过的应还本金+应还利息+罚息
            // PL003中查询宽限天数
            int allowdays = Integer.parseInt(loanBankParaDAO
                .queryParamExplain_LJ(Integer.valueOf(loanBankId), "A", "5"));
            for (int i = 0; i < callbackList.size(); i++) {
              BatchShouldBackListDTO dto = (BatchShouldBackListDTO) callbackList
                  .get(i);

              // 插入流水尾表
              LoanFlowTail loanFlowTail = new LoanFlowTail();
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail.setContractId(dto.getContractId());
              loanFlowTail.setYearMonth(dto.getLoanKouYearmonth());
              loanFlowTail.setShouldCorpus(dto.getShouldCorpus().subtract(
                  dto.getRealCorpus()));
              loanFlowTail.setShouldInterest(dto.getShouldInterest().subtract(
                  dto.getRealInterest()));
              loanFlowTail.setShouldPunishInterest(dto.getPunishInterest());
              loanFlowTail.setLoanRate(dto.getLoanRate());
              if (!temp_contractId.equals(dto.getContractId())) {
                temp_contractId = loanFlowTail.getContractId();
                overLoanRepay = dto.getOvaerLoanRepay();
                shouldCount++;
              }
              temp_money = loanFlowTail.getShouldCorpus().add(
                  loanFlowTail.getShouldInterest()).add(
                  loanFlowTail.getShouldPunishInterest());
              // 如果挂账余额大于等于应还本金+应还利息+应还罚息，在导出时直接set尾表实还字段，在导出时不导出该条记录，在导入时也不更新。
              if (overLoanRepay.doubleValue() >= temp_money.doubleValue()) {
                loanFlowTail.setRealCorpus(loanFlowTail.getShouldCorpus());
                loanFlowTail.setRealInterest(loanFlowTail.getShouldInterest());
                loanFlowTail.setRealPunishInterest(loanFlowTail
                    .getShouldPunishInterest());
                loanFlowTail.setTempPunishInterest(dto.getTempInterest()
                    .subtract(loanFlowTail.getRealPunishInterest()));
              } else {
                loanFlowTail.setRealCorpus(new BigDecimal(0.00));
                loanFlowTail.setRealInterest(new BigDecimal(0.00));
                loanFlowTail.setRealPunishInterest(new BigDecimal(0.00));
                loanFlowTail.setTempPunishInterest(dto.getTempInterest());
              }
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanRepayDay = dto.getLoanRepayDay();
              String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(),
                  loanRepayDay);
              int days = this.getDays(date, dto.getLoanKouYearmonth(),
                  loanRepayDay1);
              days = days - allowdays;
              if (days <= 0) {
                loanFlowTail.setLoanType("1");
                shouldCorpus = shouldCorpus.add(loanFlowTail.getShouldCorpus());
                shouldInterest = shouldInterest.add(loanFlowTail
                    .getShouldInterest());
                realCorpus = realCorpus.add(loanFlowTail.getRealCorpus());
                realInterest = realInterest.add(loanFlowTail.getRealInterest());
              } else {
                loanFlowTail.setLoanType("2");
                shouldOverdueCorpus = shouldOverdueCorpus.add(loanFlowTail
                    .getShouldCorpus());
                shouldOverdueInterest = shouldOverdueInterest.add(loanFlowTail
                    .getShouldInterest());
                realOverdueCorpus = realOverdueCorpus.add(loanFlowTail
                    .getRealCorpus());
                realOverdueInterest = realOverdueInterest.add(loanFlowTail
                    .getRealInterest());
              }
              shouldPunishInterest = shouldPunishInterest.add(loanFlowTail
                  .getShouldPunishInterest());
              realPunishInterest = realPunishInterest.add(loanFlowTail
                  .getRealPunishInterest());
              BigDecimal temp_compare_money = new BigDecimal(0.00);// 应还本金+应还利息+罚息
              // 逐条分配发生额
              temp_compare_money = loanFlowTail.getShouldCorpus().add(
                  loanFlowTail.getShouldInterest().add(
                      loanFlowTail.getShouldPunishInterest()));
              if (overLoanRepay.intValue() <= temp_compare_money.intValue()) {
                loanFlowTail.setOccurMoney(overLoanRepay
                    .multiply(new BigDecimal(-1.00)));
                overLoanRepay = new BigDecimal(0.00);
              } else {
                loanFlowTail.setOccurMoney(temp_compare_money
                    .multiply(new BigDecimal(-1.00)));
                overLoanRepay = overLoanRepay.subtract(temp_compare_money);
              }
              last_temp_compare_money = temp_compare_money;
              occurMoney = occurMoney.add(loanFlowTail.getOccurMoney());
              loanFlowTailDAO.insert(loanFlowTail);
            }
            // 系统自动生成结算号：业务日期+流水号
            String noteNum = "";
            noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
            // 得到实还人数
            realCount = loanFlowTailDAO.queryRealCountsByHeadId_LJ(headId);
            LoanFlowHead loanFlowHead2 = loanFlowHeadDAO.queryById(loanFlowHead
                .getFlowHeadId());
            loanFlowHead2.setShouldCount(new BigDecimal(shouldCount));
            loanFlowHead2.setShouldCorpus(shouldCorpus);
            loanFlowHead2.setShouldInterest(shouldInterest);
            loanFlowHead2.setShouldOverdueCorpus(shouldOverdueCorpus);
            loanFlowHead2.setShouldOverdueInterest(shouldOverdueInterest);
            loanFlowHead2.setShouldPunishInterest(shouldPunishInterest);
            loanFlowHead2.setNoteNum(noteNum);
            loanFlowHead2.setOccurMoney(occurMoney);
            loanFlowHead2.setRealCorpus(realCorpus);
            loanFlowHead2.setRealCount(new BigDecimal(realCount));
            loanFlowHead2.setRealInterest(realInterest);
            loanFlowHead2.setRealOverdueCorpus(realOverdueCorpus);
            loanFlowHead2.setRealOverdueInterest(realOverdueInterest);
            loanFlowHead2.setRealPunishInterest(realPunishInterest);
            if (!type_gjp.equals("")) {
              loanFlowHead2.setBatchNum(batchNum.trim());
            }
            headId = loanFlowHead.getFlowHeadId().toString();
            // 插入业务日志
            PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
            plBizActiveLog.setAction(BusiConst.PLBUSINESSSTATE_EXP + "");
            plBizActiveLog.setBizid(new BigDecimal(headId));
            plBizActiveLog.setOperator(securityInfo.getUserName());
            plBizActiveLog.setOpTime(new Date());
            plBizActiveLog.setType(loanFlowHead.getBizType());
            plBizActiveLogDAO.insert(plBizActiveLog);

            // 插入操作日志
            PlOperateLog plOperateLog = new PlOperateLog();
            plOperateLog.setOpSys(new BigDecimal(
                BusiLogConst.OP_SYSTEM_TYPE_LOAN));
            plOperateLog.setContractId(headId);
            plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_EXP + "");
            plOperateLog.setOperator(securityInfo.getUserName());
            plOperateLog.setOpIp(securityInfo.getUserIp());
            plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUEXP
                + "");
            plOperateLog.setOpTime(new Date());
            plOperateLogDAO.insert(plOperateLog);
          }
        }
      } else {
        if (type_gjp.equals("gjj")) {// 生成公积金还贷数据
          List list = this.getNum_yg(loanBankId);
          for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null && !list.get(i).equals("")) {
              batchNum = (String) list.get(i);
            }
          }
        }
      }
    }
    System.out.println("batchNum..." + batchNum);
    return batchNum;
  }

  /**
   * 查询列表信息
   */
  public List findBankCallbackList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankId = (String) pagination.getQueryCriterions().get(
        "loanBankId");
    String yearMonth = (String) pagination.getQueryCriterions()
        .get("yearMonth");
    String day = (String) pagination.getQueryCriterions().get("day");
    String batchNum = (String) pagination.getQueryCriterions().get("batchNum");
    // String fund_st = (String)pagination.getQueryCriterions().get("fund_st");
    // String type_gjp =
    // (String)pagination.getQueryCriterions().get("type_gjp");
    String temp_yearMonth = "";
    if (day != null && !day.equals("")) {
      if (day.length() < 2) {
        if (Integer.parseInt(day) < 10) {
          day = "0" + day;
        }
      }
    }
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    List temp_list = null;
    int count = 0;
    String loanRepayDay = "";// 还款日
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    if (loanBankId != null && !loanBankId.equals("")) {
      // PL003中查询宽限天数
      int allowdays = Integer.parseInt(loanBankParaDAO.queryParamExplain_LJ(
          Integer.valueOf(loanBankId), "A", "5"));
      if (batchNum != null && !batchNum.equals("")) {
        temp_list = loanFlowTailDAO.queryFlowTailByLoanBankId_GJP(loanBankId,
            null, loanKouAcc, contractId, borrowerName, cardNum, String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_EXP), orderBy, order, start,
            pageSize, page, yearMonth, day, bizDate, batchNum);
      } else {
        temp_list = loanFlowTailDAO.queryFlowTailByLoanBankId_LJ(loanBankId,
            null, loanKouAcc, contractId, borrowerName, cardNum, String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_EXP), orderBy, order, start,
            pageSize, page, yearMonth, day, bizDate);
      }
      if (!temp_list.isEmpty()) {
        for (int i = 0; i < temp_list.size(); i++) {
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) temp_list
              .get(i);
          headId = dto.getHeadId();
          if (dto.getLoanType().equals("1")) {
            dto.setLoanType("正常");
          } else if (dto.getLoanType().equals("2")) {
            dto.setLoanType("逾期");
          } else {
            dto.setLoanType("其他");
          }
          dto.setOccurMoney(dto.getOccurMoney());
          loanRepayDay = dto.getLoanRepayDay();
          String loanRepayDay1 = this.getEndDay(dto.getLoanKouYearmonth(),
              loanRepayDay);
          temp_yearMonth = dto.getReserveaA();
          int days = this.getDays(yearMonth + day, dto.getLoanKouYearmonth(),
              loanRepayDay1);
          if (days <= allowdays) {
            dto.setDays(String.valueOf(0));
          } else {
            dto.setDays(String.valueOf(days));
          }
          list.add(dto);
        }
        pagination.getQueryCriterions().put("yearMonth",
            temp_yearMonth.substring(0, 6));
        pagination.getQueryCriterions().put("day",
            temp_yearMonth.substring(6, 8));
      }
      pagination.getQueryCriterions().put("headId", headId);
      if (batchNum != null && !batchNum.equals("")) {
        count = loanFlowTailDAO.queryFlowTailCountsByLoanBankId_GJP(loanBankId,
            null, loanKouAcc, contractId, borrowerName, cardNum, String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_EXP), yearMonth, day,
            bizDate, batchNum);
      } else {
        pagination.getQueryCriterions().put("batchNum", null);
        count = loanFlowTailDAO.queryFlowTailCountsByLoanBankId_LJ(loanBankId,
            null, loanKouAcc, contractId, borrowerName, cardNum, String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_EXP), yearMonth, day,
            bizDate);
      }
    }
    yearMonth = (String) pagination.getQueryCriterions().get("yearMonth");
    day = (String) pagination.getQueryCriterions().get("day");
    if (batchNum != null && batchNum.equals("sun_no_batch_num")) {
      pagination.getQueryCriterions().put("batchNum", null);
    }
    pagination.setNrOfElements(count);
    return list;
  }

  /**
   * 列表合计查询
   * 
   * @param pagination
   * @return
   */
  public BatchShouldBackListDTO findTotalBankCallback(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    BatchShouldBackListDTO dto = new BatchShouldBackListDTO();
    try {
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String loanKouAcc = (String) pagination.getQueryCriterions().get(
          "loanKouAcc");
      String borrowerName = (String) pagination.getQueryCriterions().get(
          "borrowerName");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
      String loanBankId = (String) pagination.getQueryCriterions().get(
          "loanBankId");
      String yearMonth = (String) pagination.getQueryCriterions().get(
          "yearMonth");
      String day = (String) pagination.getQueryCriterions().get("day");
      // String type_gjp =
      // (String)pagination.getQueryCriterions().get("type_gjp");
      String batchNum = (String) pagination.getQueryCriterions()
          .get("batchNum");
      String bizDate = securityInfo.getUserInfo().getPlbizDate();
      String date = yearMonth + day;
      if (day != null && !day.equals("")) {
        if (day.length() < 2) {
          if (Integer.parseInt(day) < 10) {
            day = "0" + day;
          }
        }
      }
      if (loanBankId != null && !loanBankId.equals("")) {
        List list = null;
        // System.out.println("total..batchNum="+batchNum);
        if (batchNum != null && !batchNum.equals("")) {
          // System.out.println("if..total..batchNum="+batchNum);
          list = loanFlowTailDAO.queryFlowTailTotalByLoanBankId_GJP(loanBankId,
              null, loanKouAcc, contractId, borrowerName, cardNum, String
                  .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                  .valueOf(BusiConst.PLBUSINESSSTATE_EXP), yearMonth, day,
              bizDate, batchNum);

        } else {
          list = loanFlowTailDAO.queryFlowTailTotalByLoanBankId_LJ(loanBankId,
              null, loanKouAcc, contractId, borrowerName, cardNum, String
                  .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                  .valueOf(BusiConst.PLBUSINESSSTATE_EXP), yearMonth, day,
              bizDate);

        }
        if (!list.isEmpty()) {
          dto = (BatchShouldBackListDTO) list.get(0);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
  }

  /**
   * 得到计算罚息的列表
   * 
   * @param callbackList
   * @param loanBankId
   * @param bizDate
   * @return
   * @throws Exception
   */
  public List getCallbackList(List callbackList, Integer loanBankId,
      String bizDate) throws Exception {
    List temp_list = new ArrayList();
    String isRate = "";// 是否记息
    String accountDate = "";// 记账日期
    String paramType = "A";// 参数类型
    String interestMode = "";// 计算罚息方式
    String paramExplain = "";// 参数说明
    String allowdays = "";// 宽限天数
    BigDecimal temp_interest = new BigDecimal(0.00);// 临时罚息
    String loanRepayDay = "";// 还款日
    // 从PL003中查询宽限天数内是否计息
    isRate = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType, "5");
    // 从PL003中取计算罚息方式（按按罚息日利率、按合同日利率、按额每日XX元计算）
    interestMode = loanBankParaDAO.queryParamValue_LJ(loanBankId, paramType,
        "4");
    // 从PL003中取对应的参数说明
    paramExplain = loanBankParaDAO.queryParamExplain_LJ(loanBankId, paramType,
        "4");
    // PL003中查询宽限天数
    allowdays = loanBankParaDAO
        .queryParamExplain_LJ(loanBankId, paramType, "5");
    if (!callbackList.isEmpty()) {
      for (int i = 0; i < callbackList.size(); i++) {
        BatchShouldBackListDTO dto1 = (BatchShouldBackListDTO) callbackList
            .get(i);
        BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO) callbackList
            .get(i);
        loanRepayDay = this.getEndDay(dto1.getLoanKouYearmonth(), dto1
            .getLoanRepayDay());
        int days = this.getDays(bizDate, dto1.getLoanKouYearmonth(),
            loanRepayDay);// 逾期天数
        // 足条计算每月产生的罚息
        // 判断还款表(应还本金-本金+应还利息-利息)是否=0
        if (dto1.getShouldCorpus().subtract(dto1.getRealCorpus()).add(
            dto1.getShouldInterest().subtract(dto1.getRealInterest()))
            .doubleValue() == 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else if (days <= 0) {
          dto2.setPunishInterest(dto1.getPunishInterest());
        } else {
          // loanRepayDay = dto1.getLoanRepayDay();
          // 不等于0判断是否在宽限天数内计息
          // 条件银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数值PARAM_VALUE是否=0(宽限天数内计息)
          if (isRate.equals(BusiConst.YES + "")) {// 计息
            temp_interest = PunishInterestBS.getTempInterestByYearMonth(
                interestMode, bizDate, dto1.getLoanKouYearmonth(),
                loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
                dto1.getShouldInterest(), dto1.getRealInterest(),
                paramExplain, dto1.getLoanRate());
            // 逐条判断PL201中的记账日期是否小于等于还款日
//            accountDate = dto1.getBizDate();
            // String temp_day = accountDate.substring(6, 8);// 记账日期的日
            // if (Integer.parseInt(temp_day) <= Integer.parseInt(loanRepayDay))
            // {// 小于等于还款日
//            if (accountDate == null || accountDate.equals("")) {// 判断是否有记账日期：没有：减还款年月；有：减记账日期
//              temp_interest = PunishInterestBS.getTempInterestByYearMonth(
//                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                  loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                  dto1.getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//            } else if (Integer.parseInt(accountDate) <= Integer.parseInt(dto1
//                .getLoanKouYearmonth()
//                + loanRepayDay)) {// 小于等于还款日
//              temp_interest = PunishInterestBS.getTempInterestByYearMonth(
//                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                  loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                  dto1.getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//            } else {// 大于还款日
//              temp_interest = PunishInterestBS.getTempInterestByClearDate(
//                  interestMode, bizDate, dto1.getBizDate(), dto1
//                      .getShouldCorpus(), dto1.getRealCorpus(), dto1
//                      .getShouldInterest(), dto1.getRealInterest(),
//                  paramExplain, dto1.getLoanRate());
//              temp_interest = temp_interest.add(dto1.getPunishInterest())
//                  .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);// 加还款表中未还罚息
//            }
            // 先查询该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数说明PARAM_EXPLAIN的值（宽限天数）；判断（会计日期分别减去每个月的还款日）是否<=该值（宽限天数）
            // 判断宽限天数
            if (days <= Integer.parseInt(allowdays)) {// 逾期天数小于等于宽限天数
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(temp_interest);
            } else {
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          } else {// 不计息
            // 先查询该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且参数序号PARAM_NUM=5的参数说明PARAM_EXPLAIN的值（宽限天数）；判断（会计日期分别减去每个月的还款日）是否<=该值（宽限天数）
            // 判断宽限天数
            if (days <= Integer.parseInt(allowdays)) {// 逾期天数小于等于宽限天数
              dto2.setPunishInterest(new BigDecimal(0.00));
              dto2.setTempInterest(new BigDecimal(0.00));
            } else {// 已经逾期
              // 逐条判断PL201中的记账日期是否小于等于还款日+宽限天数
              // String temp_day = dto1.getBizDate().substring(6, 8);// 记账日期的日
              // 还款年月日+宽限天数后生成的年月日
              String temp_loanRepayDay = "";
              temp_loanRepayDay = BusiTools.addDay(dto1.getLoanKouYearmonth()
                  + loanRepayDay, Integer.parseInt(allowdays));
              temp_interest = PunishInterestBS.getTempInterestByAllowdays(
                  interestMode, bizDate, dto1.getLoanKouYearmonth(),
                  loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
                  dto1.getShouldInterest(), dto1.getRealInterest(),
                  paramExplain, allowdays, dto1.getLoanRate());
              // temp_loanRepayDay = temp_loanRepayDay.substring(6, 8);
              // if (Integer.parseInt(temp_day) <=
              // Integer.parseInt(temp_loanRepayDay)) {// 小于等于还款日+宽限天数
//              if (dto1.getBizDate() == null || dto1.getBizDate().equals("")) {// 判断是否有记账日期
//                temp_interest = PunishInterestBS.getTempInterestByAllowdays(
//                    interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                    loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                    dto1.getShouldInterest(), dto1.getRealInterest(),
//                    paramExplain, allowdays, dto1.getLoanRate());
//              } else if (Integer.parseInt(dto1.getBizDate()) <= Integer
//                  .parseInt(temp_loanRepayDay)) {// 小于等于还款日+宽限天数
//                temp_interest = PunishInterestBS.getTempInterestByAllowdays(
//                    interestMode, bizDate, dto1.getLoanKouYearmonth(),
//                    loanRepayDay, dto1.getShouldCorpus(), dto1.getRealCorpus(),
//                    dto1.getShouldInterest(), dto1.getRealInterest(),
//                    paramExplain, allowdays, dto1.getLoanRate());
//              } else {// 大于还款日+宽限天数
//                temp_interest = PunishInterestBS.getTempInterestByClearDate(
//                    interestMode, bizDate, dto1.getBizDate(), dto1
//                        .getShouldCorpus(), dto1.getRealCorpus(), dto1
//                        .getShouldInterest(), dto1.getRealInterest(),
//                    paramExplain, dto1.getLoanRate());
//                // 加还款表中未还罚息
//                temp_interest = temp_interest.add(dto1.getPunishInterest())
//                    .divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP);
//              }
              dto2.setPunishInterest(temp_interest);
              dto2.setTempInterest(temp_interest);
            }
          }
        }
        dto2.setLoanRate(dto1.getLoanRate());// 显示每月利率
        dto2.setDays(String.valueOf(days));
        temp_list.add(dto2);
      }
    }
    return temp_list;
  }

  /**
   * 取出导出数据
   * 
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List findExportList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    List list = null;
    List list_fund = null;
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String loanBankId = (String) pagination.getQueryCriterions().get(
        "loanBankId");
    String yearMonth = (String) pagination.getQueryCriterions()
        .get("yearMonth");
    String day = (String) pagination.getQueryCriterions().get("day");
    String batchNum = (String) pagination.getQueryCriterions().get("batchNum");
    String date = yearMonth.substring(0, 4) + "-" + yearMonth.substring(4, 6)
        + "-" + day;
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8);
    // 判断该贷款账号的所属银行在银行贷款参数PL003中类型为：还款参数，并且序号为1中参数值是否=1:足额扣款
    String paramValue = this.getBackMode(loanBankId);
    List exportlist = new ArrayList();
    paramValue = this.getBackMode(loanBankId);
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    try {
      if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
        // 足额扣款
        // 按月导出每个贷款账号的还款记录
        // 金额为每月应收金额=对应还月的（应还本金SHOULD_CORPUS+应还利息SHOULD_INTEREST+应还罚息SHOULD_PUNISH_INTEREST）+发生额
        list = loanFlowTailDAO.queryExportFlowTail_LJA(headId);
        list_fund = loanFlowTailDAO.queryExportFlowTail_fund(headId);
        BigDecimal money = new BigDecimal(0.00);
        if (!list.isEmpty()) {
          Vector vectorHead = new Vector();
          // bankExportsDTO.setBizDate(bizDate);
          // bankExportsDTO.setLoanBankId(loanBankId);
          vectorHead.add(bizDate.toString());
          vectorHead.add(loanBankId.toString());
          if (batchNum != null && !batchNum.equals("")) {
            vectorHead.add(batchNum);

            vectorHead.add("|");
            vectorHead.add("");
            vectorHead.add("");
            exportlist.add(vectorHead);
            for (int i = 0; i < list_fund.size(); i++) {
              // BankExportsDTO dto1 = new BankExportsDTO();
              Vector vector = new Vector();
              BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO) list_fund
                  .get(i);
              vector.add(dto2.getLoanKouAcc().toString());
              vector.add(dto2.getBorrowerName().toString());
              vector.add(dto2.getLoanKouYearmonth().toString());
              vector.add(dto2.getRealMoney().toString());
              vector.add("0");
              money = money.add(dto2.getRealMoney());
              if (dto2.getRealMoney().doubleValue() > 0) {
                exportlist.add(vector);
              }
            }
            Vector vectorEnd = new Vector();
            vectorEnd.add("人数合计：" + loanFlowHead.getShouldCount().toString()
                + "   ");
            vectorEnd.add("笔数合计：" + list_fund.size() + "   ");
            vectorEnd.add("金额合计：" + money.toString() + "   ");

            vectorEnd.add("");
            vectorEnd.add("");
            exportlist.add(vectorEnd);
          } else {
            vectorHead.add("|");
            vectorHead.add("");
            vectorHead.add("");
            exportlist.add(vectorHead);
            for (int i = 0; i < list.size(); i++) {
              // BankExportsDTO dto1 = new BankExportsDTO();
              Vector vector = new Vector();
              BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO) list
                  .get(i);
              vector.add(dto2.getLoanKouAcc().toString());
              vector.add(dto2.getBorrowerName().toString());
              vector.add(dto2.getLoanKouYearmonth().toString());
              vector.add(dto2.getRealMoney().toString());
              System.out.println(dto2.getRealMoney().toString());
              vector.add("0");
              money = money.add(dto2.getRealMoney());
              if (dto2.getRealMoney().doubleValue() > 0) {
                exportlist.add(vector);
              }
            }
            Vector vectorEnd = new Vector();
            vectorEnd.add("人数合计：" + loanFlowHead.getShouldCount().toString()
                + "   ");
            vectorEnd.add("金额合计：" + money.toString() + "   ");
            vectorEnd.add("");
            vectorEnd.add("");
            vectorEnd.add("");
            exportlist.add(vectorEnd);
          }

        }
      } else {
        // 全额扣款
        // 一个贷款账号导出一条还款记录
        // 金额=该贷款账号本笔业务所有月的实收金额合计=sum（应还本金SHOULD_CORPUS+应还利息SHOULD_INTEREST+应还罚息SHOULD_PUNISH_INTEREST）+发生额
        list = loanFlowTailDAO.queryExportFlowTail_LJB(headId);
        if (!list.isEmpty()) {
          Vector vectorHead = new Vector();
          BigDecimal money = new BigDecimal(0.00);
          // bankExportsDTO.setBizDate(bizDate);
          // bankExportsDTO.setLoanBankId(loanBankId);
          vectorHead.add(bizDate.toString());
          vectorHead.add(loanBankId.toString());
          if (batchNum != null && !batchNum.equals("")) {
            vectorHead.add(batchNum);
          }
          vectorHead.add("|");
          vectorHead.add("|");
          vectorHead.add("|");
          exportlist.add(vectorHead);
          for (int i = 0; i < list.size(); i++) {
            // BankExportsDTO dto1 = new BankExportsDTO();
            Vector vector = new Vector();
            BatchShouldBackListDTO dto2 = (BatchShouldBackListDTO) list.get(i);
            vector.add(dto2.getLoanKouAcc().toString());
            vector.add(dto2.getBorrowerName().toString());
            vector.add(dto2.getRealMoney().toString());
            money = money.add(dto2.getRealMoney());
            if (dto2.getRealMoney().doubleValue() > 0) {
              exportlist.add(vector);
            }
          }
          Vector vectorEnd = new Vector();
          vectorEnd.add("人数合计：" + loanFlowHead.getShouldCount().toString()
              + "   ");
          vectorEnd.add("金额合计：" + money.toString() + "   ");
          vectorEnd.add("");
          exportlist.add(vectorEnd);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("导出失败！");
    }
    return exportlist;
  }

  /**
   * 得到扣款方式
   * 
   * @param loanBankId
   * @return
   */
  public String getBackMode(String loanBankId) throws Exception {
    String paramValue = "";
    try {
      paramValue = loanBankParaDAO.queryParamValue_LJ(new Integer(loanBankId),
          "A", "1");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramValue;
  }

  /**
   * 计算逾期天数
   * 
   * @param bizDate 业务日期
   * @param yearMonth 还款年月
   * @param loanRepayDay 还款日
   * @return
   */
  public int getDays(String bizDate, String yearMonth, String loanRepayDay) {
    int days = 0;
    String temp_date = yearMonth.substring(0, 4) + "-"
        + yearMonth.substring(4, 6) + "-" + loanRepayDay;
    String temp_bizDate = bizDate.substring(0, 4) + "-"
        + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8);
    // 逾期天数
    days = BusiTools.minusDate(temp_bizDate, temp_date);
    return days;
  }

  /**
   * 单选删除
   * 
   * @param tailId
   * @param securityInfo
   * @throws Exception
   */
  public void deleteTailInfo(String tailId, SecurityInfo securityInfo)
      throws Exception {
    // 判断该笔业务在贷款流水账头表PL203中的业务状态BIZ_ST是否=1，导出
    // （用PL202表中FLOW_HEAD_ID关联）??列表中只能显示导出状态的记录，还用判断吗？
    int shouldCount = 0;// 应还人数
    BigDecimal shouldCorpus = new BigDecimal(0.00);// 应还正常本金
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);// 应还逾期本金
    BigDecimal shouldInterest = new BigDecimal(0.00);// 应还正常利息
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);// 应还逾期利息
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);// 应还罚息
    BigDecimal occurMoney = new BigDecimal(0.00);// 发生额
    String temp_contractId = "";
    String office = "";// 办事处代码
    LoanFlowTail loanFlowTail = loanFlowTailDAO.queryById(new Integer(tailId));
    String headId = loanFlowTail.getFlowHeadId().toString();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    String bizSt = loanFlowHead.getBizSt();
    if (!bizSt.equals(String.valueOf(BusiConst.PLBUSINESSSTATE_EXP))) {
      throw new BusinessException("此条记录不是导出状态，不能删除！");
    }
    try {

      loanFlowTailDAO.insertPL601Num(securityInfo.getUserInfo().getOfficeId()
          .toString(), securityInfo.getUserInfo().getBizDate().substring(0, 4),
          loanFlowHead.getBatchNum().substring(6, 10));
      loanFlowTailDAO.delete(loanFlowTail);
      // 判断PL203表中FLOW_HEAD_ID在贷款流水账尾表PL203中是否还存在记录
      List list = loanFlowTailDAO.queryBankLoanFlowTailByHeadId_LJ(headId);
      if (list.isEmpty()) {// 不存在记录
        loanFlowTailDAO.delete(loanFlowTail);
        // 撤销凭证号
        office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead
            .getLoanBankId().toString());
        String officeId = "";
        String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
        if (loanDocNumDocument.equals("1")) {
          officeId = office;
        } else {
          officeId = loanFlowHead.getLoanBankId().toString();
        }
        // office =
        // loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead.getLoanBankId().toString());
        // plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(),
        // officeId, loanFlowHead.getBizDate().substring(0, 6));
        plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum()
            .substring(8, 12), loanFlowHead.getDocNum().substring(7, 8),
            loanFlowHead.getDocNum().substring(0, 4));
        // 删除业务务日志
        plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId, loanFlowHead
            .getBizSt());
        // 删除该笔业务在在贷款流水账头表PL202中记录（关联的条件PL202表中FLOW_HEAD_ID）
        loanFlowHeadDAO.delete(loanFlowHead);
      } else {// 更新头表
        // 更新该笔业务在流水账头表PL202中的应还正常本金、应还逾期本金、应还正常利息、应还逾期利息、应还罚息、应还人数
        for (int i = 0; i < list.size(); i++) {
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) list.get(i);
          if (!temp_contractId.equals(dto.getContractId())) {
            temp_contractId = dto.getContractId();
            shouldCount++;
          }
          if (dto.getLoanType().equals("1")) {
            shouldCorpus = shouldCorpus.add(dto.getShouldCorpus());
            shouldInterest = shouldInterest.add(dto.getShouldInterest());
          } else if (dto.getLoanType().equals("2")) {
            shouldOverdueCorpus = shouldOverdueCorpus
                .add(dto.getShouldCorpus());
            shouldOverdueInterest = shouldOverdueInterest.add(dto
                .getShouldInterest());
          }
          shouldPunishInterest = shouldPunishInterest.add(dto
              .getPunishInterest());
          occurMoney = occurMoney.add(dto.getOccurMoney());
        }
        loanFlowHead.setShouldCount(new BigDecimal(shouldCount));
        loanFlowHead.setShouldCorpus(shouldCorpus);
        loanFlowHead.setShouldInterest(shouldInterest);
        loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
        loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
        loanFlowHead.setShouldPunishInterest(shouldPunishInterest);
        loanFlowHead.setOccurMoney(occurMoney);
      }
      // 插入操作日志
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(headId);
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUEXP + "");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      throw new BusinessException("删除失败！");
    }
  }

  /**
   * 全部删除
   * 
   * @param pagination
   * @param securityInfo
   */
  public void deleteTailList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    int shouldCount = 0;// 应还人数
    BigDecimal shouldCorpus = new BigDecimal(0.00);// 应还正常本金
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);// 应还逾期本金
    BigDecimal shouldInterest = new BigDecimal(0.00);// 应还正常利息
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);// 应还逾期利息
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);// 应还罚息
    BigDecimal occurMoney = new BigDecimal(0.00);// 发生额
    String temp_contractId = "";
    String office = "";// 办事处代码
    try {
      loanFlowTailDAO.deleteFlowTailByLoanBankId_LJ(headId, loanKouAcc,
          contractId, borrowerName, cardNum, String
              .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
              .valueOf(BusiConst.PLBUSINESSSTATE_EXP));
      LoanFlowHead loanFlowHead = loanFlowHeadDAO
          .queryById(new Integer(headId));
      if (loanFlowHead.getBatchNum() != null
          && !loanFlowHead.getBatchNum().equals("")) {
        loanFlowTailDAO.insertPL601Num(securityInfo.getUserInfo().getOfficeId()
            .toString(), securityInfo.getUserInfo().getBizDate()
            .substring(0, 4), loanFlowHead.getBatchNum().substring(6, 10));
      }
      String bizSt = loanFlowHead.getBizSt();
      if (!bizSt.equals(String.valueOf(BusiConst.PLBUSINESSSTATE_EXP))) {
        throw new BusinessException("记录不是导出状态，不能删除！");
      }
      // 判断PL203表中FLOW_HEAD_ID在贷款流水账尾表PL203中是否还存在记录
      List list = loanFlowTailDAO.queryBankLoanFlowTailByHeadId_LJ(headId);
      if (list.isEmpty()) {// 不存在记录
        loanFlowTailDAO.deleteFlowTailByLoanBankId_LJ(headId, loanKouAcc,
            contractId, borrowerName, cardNum, String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_EXP));
        // 撤销凭证号
        office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead
            .getLoanBankId().toString());
        String officeId = "";
        String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
        if (loanDocNumDocument.equals("1")) {
          officeId = office;
        } else {
          officeId = loanFlowHead.getLoanBankId().toString();
        }
        // office =
        // loanBankDAO.queryOfficeCodeByBankId_LJ(loanFlowHead.getLoanBankId().toString());
        // plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(),
        // officeId, loanFlowHead.getBizDate().substring(0, 6));
        plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum()
            .substring(8, 12), loanFlowHead.getDocNum().substring(7, 8),
            loanFlowHead.getDocNum().substring(0, 4));
        // 删除业务务日志
        plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId, loanFlowHead
            .getBizSt());
        // 删除该笔业务在在贷款流水账头表PL202中记录（关联的条件PL202表中FLOW_HEAD_ID）
        loanFlowHeadDAO.delete(loanFlowHead);
      } else {// 更新头表
        // 更新该笔业务在流水账头表PL202中的应还正常本金、应还逾期本金、应还正常利息、应还逾期利息、应还罚息、应还人数
        for (int i = 0; i < list.size(); i++) {
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) list.get(i);
          if (!temp_contractId.equals(dto.getContractId())) {
            temp_contractId = dto.getContractId();
            shouldCount++;
          }
          if (dto.getLoanType().equals("1")) {
            shouldCorpus = shouldCorpus.add(dto.getShouldCorpus());
            shouldInterest = shouldInterest.add(dto.getShouldInterest());
          } else if (dto.getLoanType().equals("2")) {
            shouldOverdueCorpus = shouldOverdueCorpus
                .add(dto.getShouldCorpus());
            shouldOverdueInterest = shouldOverdueInterest.add(dto
                .getShouldInterest());
          }
          shouldPunishInterest = shouldPunishInterest.add(dto
              .getPunishInterest());
          occurMoney = occurMoney.add(dto.getOccurMoney());
        }
        loanFlowHead.setShouldCount(new BigDecimal(shouldCount));
        loanFlowHead.setShouldCorpus(shouldCorpus);
        loanFlowHead.setShouldInterest(shouldInterest);
        loanFlowHead.setShouldOverdueCorpus(shouldOverdueCorpus);
        loanFlowHead.setShouldOverdueInterest(shouldOverdueInterest);
        loanFlowHead.setShouldPunishInterest(shouldPunishInterest);
        loanFlowHead.setOccurMoney(occurMoney);
      }
      // 插入操作日志
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog.setContractId(headId);
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETEALL + "");
      plOperateLog.setOperator(securityInfo.getUserName());
      plOperateLog.setOpIp(securityInfo.getUserIp());
      plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUEXP + "");
      plOperateLog.setOpTime(new Date());
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException("删除失败！");
    }

  }

  /**
   * 打印列表信息
   * 
   * @param pagination
   * @param securityInfo
   * @return
   */
  public List getPrintList(Pagination pagination, SecurityInfo securityInfo)
      throws Exception {
    String headId = (String) pagination.getQueryCriterions().get("headId");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankId = (String) pagination.getQueryCriterions().get(
        "loanBankId");
    String bizDate = securityInfo.getUserInfo().getPlbizDate();
    List list = new ArrayList();
    List temp_list = null;
    String loanRepayDay = "";
    try {
      temp_list = loanFlowTailDAO.queryPrintFlowTailByLoanBankId_LJ(loanBankId,
          headId, loanKouAcc, contractId, borrowerName, cardNum, String
              .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
              .valueOf(BusiConst.PLBUSINESSSTATE_EXP));
      if (!temp_list.isEmpty()) {
        for (int i = 0; i < temp_list.size(); i++) {
          BatchShouldBackListDTO dto = (BatchShouldBackListDTO) temp_list
              .get(i);
          headId = dto.getHeadId();
          if (dto.getLoanType().equals("1")) {
            dto.setLoanType("正常");
          } else if (dto.getLoanType().equals("2")) {
            dto.setLoanType("逾期");
          } else {
            dto.setLoanType("其他");
          }
          dto.setOccurMoney(dto.getOccurMoney());
          loanRepayDay = this.getEndDay(dto.getLoanKouYearmonth(), dto
              .getLoanRepayDay());
          int days = this.getDays(bizDate, dto.getLoanKouYearmonth(),
              loanRepayDay);
          if (days <= 0) {
            days = 0;
          }
          dto.setDays(String.valueOf(days));
          list.add(dto);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public String getPL600Num(String office, String bizDate) throws Exception {
    String num = "";
    String s = "";
    try {
      num = loanFlowTailDAO.queryPL600Num(office, bizDate);
      int len1 = num.length();
      num = (Integer.parseInt(num) + 1) + "";
      int len2 = num.length();
      for (int i = 0; i < len1 - len2; i++) {
        s += "0";
      }
      num = s + num;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return num;
  }

  public String getPL601Num(String office, String bizDate) throws Exception {
    String num = "";
    try {
      num = loanFlowTailDAO.queryPL601Num(office, bizDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return num;
  }

  public List getNum_yg(String bankid) throws Exception {
    List list = new ArrayList();
    try {
      list = loanFlowTailDAO.queryNum_yg(bankid);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public void updatePL600Num(String office, String bizDate, String num)
      throws Exception {
    try {
      loanFlowTailDAO.updatePL600Num(office, bizDate, num);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deletePL601Num(String office, String bizDate, String num)
      throws Exception {
    try {
      loanFlowTailDAO.deletePL601Num(office, bizDate, num);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void insertPL601Num(String office, String bizDate, String num)
      throws Exception {
    try {
      loanFlowTailDAO.insertPL601Num(office, bizDate, num);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 取得年结年份
   * 
   * @param loanBankId
   * @return
   */
  public String getClearYear(String loanBankId) {
    String year = "";// 年结年份
    year = loanBankDAO.queryYearClearByBankId_sy(loanBankId);
    return year;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

  public String getNamePara() throws Exception {
    String name = "";
    name = collParaDAO.getNamePara();
    return name;
  }
}
