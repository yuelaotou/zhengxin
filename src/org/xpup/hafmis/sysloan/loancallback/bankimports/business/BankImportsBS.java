package org.xpup.hafmis.sysloan.loancallback.bankimports.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BankExportsDTO;
import org.xpup.hafmis.sysloan.loancallback.bankexports.dto.BatchShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.bankimports.bsinterface.IBankImportsBS;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.LoancallbackTaImportDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;

public class BankImportsBS implements IBankImportsBS {
  private LoanFlowTailDAO loanFlowTailDAO = null;

  private CollParaDAO collParaDAO = null;

  private LoanFlowHeadDAO loanFlowHeadDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private PlBizActiveLogDAO plBizActiveLogDAO = null;

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private LoanBankDAO loanBankDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private CollBankDAO collBankDAO = null;

  private SecurityDAO securityDAO = null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

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
   * 根据银行ID查询银行Name
   * 
   * @param loanBankId
   * @return
   * @throws Exception
   */
  public String getLoanBankName(String loanBankId) throws Exception {
    String loanBankName = "";
    try {
      if (loanBankId != null && !loanBankId.equals("")) {
        CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId);
        loanBankName = collBank.getCollBankName();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanBankName;
  }

  /**
   * 查询合同编号
   * 
   * @param loanKouAcc
   * @param contractSt
   * @param securityInfo
   * @return
   */
  public String findBorrowerAcc(String loanKouAcc, String contractSt,
      SecurityInfo securityInfo) {
    String contractId = "";
    contractId = borrowerAccDAO.queryBorrowerAccByLoanKouAcc_LJ(loanKouAcc,
        contractSt, securityInfo);
    return contractId;
  }

  /**
   * 查询该贷款账号的还款日
   * 
   * @param loanKouAcc
   * @param contractSt
   * @param securityInfo
   * @return
   */
  public String getLoanRepayDay(String loanKouAcc, String contractSt,
      SecurityInfo securityInfo) {
    String loanRepayDay = "";
    loanRepayDay = borrowerAccDAO.queryLoanRepayDayByLoanKouAcc_LJ(loanKouAcc,
        contractSt, securityInfo).toString();
    return loanRepayDay;
  }

  /**
   * 查询银行代扣导入列表
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findBankCallbackList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
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
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      List temp_list = null;
      int count = 0;
      int allCount = 0;
      String loanRepayDay = "";
      if (loanBankId != null && !loanBankId.equals("")) {
        temp_list = loanFlowTailDAO.queryImportFlowTailByLoanBankId_LJ(
            loanBankId, null, loanKouAcc, contractId, borrowerName, cardNum,
            String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_IMP), orderBy, order, start,
            pageSize, page, securityInfo);
        // PL003中查询宽限天数
        int allowdays = Integer.parseInt(loanBankParaDAO
            .queryParamExplain_LJ(Integer.valueOf(loanBankId), "A", "5"));
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
            if (dto.getRealMoney().doubleValue() < 0) {
              dto.setRealMoney(new BigDecimal(0.00));
            }
            loanRepayDay = this.getEndDay(dto.getLoanKouYearmonth(), dto
                .getLoanRepayDay());
            int days = this.getDays(dto.getBizDate(),
                dto.getLoanKouYearmonth(), loanRepayDay);
            if(days > allowdays)
              dto.setDays(String.valueOf(days));
            else 
              dto.setDays("0");
            list.add(dto);
          }
        }
        pagination.getQueryCriterions().put("headId", headId);
        count = loanFlowTailDAO.queryImportFlowTailCountsByLoanBankId_LJ(
            loanBankId, headId, loanKouAcc, contractId, borrowerName, cardNum,
            String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_IMP), securityInfo);
        List countList = loanFlowTailDAO
            .queryImportLoanFlowTailIDByHeadId_LJ(headId);
        allCount = countList.size();
      }
      pagination.setNrOfElements(count);
      pagination.getQueryCriterions().put("allCount", String.valueOf(allCount));
    } catch (Exception e) {
      e.printStackTrace();
    }
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
      if (loanBankId != null && !loanBankId.equals("")) {
        List list = loanFlowTailDAO.queryImportFlowTailTotalByLoanBankId_LJ(
            loanBankId, null, loanKouAcc, contractId, borrowerName, cardNum,
            String.valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER), String
                .valueOf(BusiConst.PLBUSINESSSTATE_IMP), securityInfo);
        if (!list.isEmpty()) {
          dto = (BatchShouldBackListDTO) list.get(0);
          if (dto.getRealMoney().intValue() <= 0) {
            dto.setRealMoney(new BigDecimal(0.00));
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return dto;
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
    try {
      String temp_date = yearMonth.substring(0, 4) + "-"
          + yearMonth.substring(4, 6) + "-" + loanRepayDay;
      String temp_bizDate = bizDate.substring(0, 4) + "-"
          + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8);
      // 逾期天数
      days = BusiTools.minusDate(temp_bizDate, temp_date);
      if (days <= 0) {
        days = 0;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return days;
  }

  /**
   * 以银行为主(没有对应的导出文件) 将导入数据插入业务流水表
   * 
   * @param importList
   * @param securityInfo
   */
  public String addBankImportsInfo(List importList, SecurityInfo securityInfo)
      throws Exception {
    String loanBankId = "";// 银行代码
    String office = "";// 办事处代码
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 业务日期
    String bizType = "";// 业务类型
    String contractId = "";// 合同编号
    String loanRepayDay = "";// 还款日
    String contractSt = BusiConst.PLCONTRACTSTATUS_RECOVING + "";// 合同状态 11.还款中
    BigDecimal shouldCorpus = new BigDecimal(0.00);// 应还正常本金
    BigDecimal shouldInterest = new BigDecimal(0.00);// 应还正常利息
    BigDecimal shouldOverdueCorpus = new BigDecimal(0.00);// 应还逾期本金
    BigDecimal shouldOverdueInterest = new BigDecimal(0.00);// 应还逾期利息
    BigDecimal shouldPunishInterest = new BigDecimal(0.00);// 应还罚息
    BigDecimal realCorpus = new BigDecimal(0.00);// 实还本金
    BigDecimal realInterest = new BigDecimal(0.00);// 实还利息
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);// 实还逾期本金
    BigDecimal realOverdueInterest = new BigDecimal(0.00);// 实还逾期利息
    BigDecimal realPunishInterest = new BigDecimal(0.00);// 实还罚息
    BigDecimal temp_realCorpus = new BigDecimal(0.00);// 实还本金
    BigDecimal temp_realInterest = new BigDecimal(0.00);// 实还利息
    BigDecimal temp_realOverdueCorpus = new BigDecimal(0.00);// 实还逾期本金
    BigDecimal temp_realOverdueInterest = new BigDecimal(0.00);// 实还逾期利息
    BigDecimal temp_realPunishInterest = new BigDecimal(0.00);// 实还罚息
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    int realCount = 0;// 实还人数
    String bizDateim = "";
    List bankList = null;
    if (!importList.isEmpty()) {
      try {
        for (int i = 0; i < importList.size(); i++) {
          LoancallbackTaImportDTO dto = (LoancallbackTaImportDTO) importList
              .get(i);
          if (i == 0) {
            loanBankId = dto.getLoanBankId();// 银行代码

            int iClearYear = Integer.parseInt(bizDate.substring(0, 4)) - 1;
            String clearYear = this.getClearYear(String.valueOf(loanBankId));
            // 会计年份-1如果不等于PL002中的年结年份则不允许回收
            if (iClearYear > Integer.parseInt(clearYear)) {
              throw new BusinessException(iClearYear + "年尚未年结，不允许进行回收业务！");
            }
            bizType = dto.getBizType();
            if (bizType == null) {
              throw new BusinessException("业务类型不能为空！");
            } else if (!bizType.equals(String
                .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER))) {
              throw new BusinessException("该业务类型不能在此导入！");
            }
            bizDateim = dto.getBizDate();// 导入文件中的日期
            // 扣款日期是否与会计日期相同
            String temp_bizDate = bizDate.substring(0, 4) + "-"
                + bizDate.substring(4, 6) + "-" + bizDate.substring(6, 8);
            if (!temp_bizDate.equals(bizDateim)) {
              throw new BusinessException("导入文件中的日期与会计日期不一致！");
            }
            bankList = securityDAO.getDkUserBankList_LJ(securityInfo
                .getUserName(), loanBankId);
            // 判断有无操作此银行的权限
            if (bankList.isEmpty()) {
              throw new BusinessException("不具备该银行的权限！");
            }
            // 该银行在贷款流水账头表PL202表中是否存在业务状态为2：导入（关联贷款流水账尾表PL203）
            // List temp_list =
            // loanFlowHeadDAO.queryLoanFlowImportByLoanBankId_LJ(loanBankId,
            // String.valueOf(BusiConst.PLBUSINESSSTATE_IMP));
            // if(!temp_list.isEmpty()){
            // throw new BusinessException("该银行下已经存在未入账的导入记录！不能导入");
            // }
            List temp_list = null;
            // 判断该银行下在贷款流水账头表PL202中是否存在其它类型未记账的业务(
            // 业务类型为2.单笔回收，3.部分提前还款，4.一次性清还，5.批量回收，6.呆账核销（中心），7.呆账核销（其他），8.核销收回（中心），9.核销收回（其他）12.错账调整13.挂账（收提），业务状态=2.导入，3.登记，4.确认，5.复核的业务)
            temp_list = loanFlowHeadDAO.queryBizStListImportByLoanBank_LJ(
                loanBankId, bizType);
            if (!temp_list.isEmpty()) {
              throw new BusinessException("该银行下存在未入账的批量导入记录！不能导入");
            }
            // 插入数据
            office = loanBankDAO.queryOfficeCodeByBankId_LJ(loanBankId);
            // 插入流水头表
            String officeId = "";
            String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
            if (loanDocNumDocument.equals("1")) {
              officeId = office;
            } else {
              officeId = loanBankId;
            }
            loanFlowHead.setDocNum(plDocNumMaintainDAO.getDocNumdocNum(
                officeId, bizDate.substring(0, 6)));
            loanFlowHead.setBizDate(bizDate);
            // 业务类型根据导入文件的业务类型标识符插入（5.批量回收）；
            loanFlowHead.setBizType(bizType);
            loanFlowHead.setOtherInterest(new BigDecimal(0.00));
            loanFlowHead.setOccurMoney(new BigDecimal(0.00));
            loanFlowHead.setLoanBankId(new BigDecimal(loanBankId));
            loanFlowHead
                .setBizSt(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP));
            loanFlowHead.setMakePerson(securityInfo.getUserName());
            loanFlowHead.setIsFinance(new Integer(1));
            loanFlowHeadDAO.insert(loanFlowHead);
          } else {
            contractId = this.findBorrowerAcc(dto.getLoanKouAcc(), contractSt,
                securityInfo);
            loanRepayDay = this.getLoanRepayDay(dto.getLoanKouAcc(),
                contractSt, securityInfo);
            if (contractId == null || contractId.equals("")) {
              throw new BusinessException("该贷款账号不存在！");
            }
            // 插入流水尾表
            LoanFlowTail loanFlowTail = new LoanFlowTail();
            temp_realCorpus = new BigDecimal(dto.getRealCorpus());
            temp_realInterest = new BigDecimal(dto.getRealInterest());
            temp_realOverdueCorpus = new BigDecimal(dto.getRealOverdueCorpus());
            temp_realOverdueInterest = new BigDecimal(dto
                .getRealOverdueInterest());
            temp_realPunishInterest = new BigDecimal(dto
                .getRealPunishInterest());
            shouldCorpus = shouldCorpus.add(new BigDecimal(dto.getRealCorpus())
                .add(new BigDecimal(dto.getNobackCorpus())));
            shouldInterest = shouldInterest.add(new BigDecimal(dto
                .getRealInterest())
                .add(new BigDecimal(dto.getNobackInterest())));
            realCorpus = realCorpus.add(new BigDecimal(dto.getRealCorpus()));
            realInterest = realInterest.add(new BigDecimal(dto
                .getRealInterest()));
            shouldOverdueCorpus = shouldOverdueCorpus.add(new BigDecimal(dto
                .getRealOverdueCorpus()).add(new BigDecimal(dto
                .getNobackOverdueCorpus())));
            shouldOverdueInterest = shouldOverdueInterest.add(new BigDecimal(
                dto.getRealOverdueInterest()).add(new BigDecimal(dto
                .getNobackOverdueInterest())));
            realOverdueCorpus = realOverdueCorpus.add(new BigDecimal(dto
                .getRealOverdueCorpus()));
            realOverdueInterest = realOverdueInterest.add(new BigDecimal(dto
                .getRealOverdueInterest()));
            shouldPunishInterest = shouldPunishInterest.add(new BigDecimal(dto
                .getRealPunishInterest()).add(new BigDecimal(dto
                .getNobackPunishInterest())));
            realPunishInterest = realPunishInterest.add(new BigDecimal(dto
                .getRealPunishInterest()));
            // 如果逾期数据和正常数据都大于0则向流水表中插入两条数据，一条还款类型为1正常，一条为2逾期
            if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0
                && temp_realOverdueCorpus.add(temp_realOverdueInterest).add(
                    temp_realPunishInterest).doubleValue() > 0) {
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
              loanFlowTail
                  .setRealInterest(new BigDecimal(dto.getRealInterest()));
              // loanFlowTail.setRealPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()));
              loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getRealOverdueCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealInterest()).add(new BigDecimal(dto
                  .getRealOverdueInterest())));
              // loanFlowTail.setShouldPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()).add(new
              // BigDecimal(dto.getNobackPunishInterest())));
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setLoanType("1");
              loanFlowTailDAO.insert(loanFlowTail);

              LoanFlowTail loanFlowTail1 = new LoanFlowTail();
              loanFlowTail1.setContractId(contractId);
              loanFlowTail1.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail1.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail1.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail1.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail1.setRealCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()));
              loanFlowTail1.setRealInterest(new BigDecimal(dto
                  .getRealOverdueInterest()));
              loanFlowTail1.setRealPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()));
              loanFlowTail1.setShouldCorpus(new BigDecimal(dto
                  .getNobackCorpus()).add(new BigDecimal(dto
                  .getNobackOverdueCorpus())));
              loanFlowTail1.setShouldInterest(new BigDecimal(dto
                  .getNobackInterest()).add(new BigDecimal(dto
                  .getNobackOverdueInterest())));
              loanFlowTail1.setShouldPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()).add(new BigDecimal(dto
                  .getNobackPunishInterest())));
              loanFlowTail1.setYearMonth(dto.getYearMonth());
              loanFlowTail1.setLoanType("2");
              loanFlowTailDAO.insert(loanFlowTail1);
              // 否则正常数据大于0逾期数据等于0，插入一条还款类型为1正常
            } else if (temp_realCorpus.add(temp_realInterest).doubleValue() > 0) {
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setRealCorpus(new BigDecimal(dto.getRealCorpus()));
              loanFlowTail
                  .setRealInterest(new BigDecimal(dto.getRealInterest()));
              // loanFlowTail.setRealPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()));
              loanFlowTail.setShouldCorpus(new BigDecimal(dto.getRealCorpus())
                  .add(new BigDecimal(dto.getRealOverdueCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getRealInterest()).add(new BigDecimal(dto
                  .getRealOverdueInterest())));
              // loanFlowTail.setShouldPunishInterest(new
              // BigDecimal(dto.getRealPunishInterest()).add(new
              // BigDecimal(dto.getNobackPunishInterest())));
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setLoanType("1");
              loanFlowTailDAO.insert(loanFlowTail);
              // 否则逾期数据大于0正常数据等于0，插入一条还款类型为2逾期
            } else if (temp_realOverdueCorpus.add(temp_realOverdueInterest)
                .add(temp_realPunishInterest).doubleValue() > 0) {
              loanFlowTail.setContractId(contractId);
              loanFlowTail.setFlowHeadId(new BigDecimal(loanFlowHead
                  .getFlowHeadId().toString()));
              loanFlowTail.setLoanKouAcc(dto.getLoanKouAcc());
              loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              loanFlowTail.setOtherInterest(new BigDecimal(0.00));
              loanFlowTail.setRealCorpus(new BigDecimal(dto
                  .getRealOverdueCorpus()));
              loanFlowTail.setRealInterest(new BigDecimal(dto
                  .getRealOverdueInterest()));
              loanFlowTail.setRealPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()));
              loanFlowTail
                  .setShouldCorpus(new BigDecimal(dto.getNobackCorpus())
                      .add(new BigDecimal(dto.getNobackOverdueCorpus())));
              loanFlowTail.setShouldInterest(new BigDecimal(dto
                  .getNobackInterest()).add(new BigDecimal(dto
                  .getNobackOverdueInterest())));
              loanFlowTail.setShouldPunishInterest(new BigDecimal(dto
                  .getRealPunishInterest()).add(new BigDecimal(dto
                  .getNobackPunishInterest())));
              loanFlowTail.setYearMonth(dto.getYearMonth());
              loanFlowTail.setLoanType("2");
              loanFlowTailDAO.insert(loanFlowTail);
            }
            // String loanRepayDay1 = this.getEndDay(dto.getYearMonth(),
            // loanRepayDay);
            // //逾期天数
            // int days = this.getDays(bizDate, dto.getYearMonth(),
            // loanRepayDay1);
            // if(days<=0){
            // loanFlowTail.setLoanType("1");
            // }else{
            // loanFlowTail.setLoanType("2");
            // }
          }
        }
        // 得到实还人数
        realCount = loanFlowTailDAO.queryRealCountsByHeadId_LJ(loanFlowHead
            .getFlowHeadId().toString());
        // 更新头表信息
        LoanFlowHead loanFlowHead1 = loanFlowHeadDAO.queryById(loanFlowHead
            .getFlowHeadId());
        loanFlowHead1.setNoteNum(loanFlowHead1.getFlowHeadId().toString());
        loanFlowHead1.setRealCorpus(realCorpus);
        loanFlowHead1.setRealCount(new BigDecimal(realCount));
        loanFlowHead1.setRealInterest(realInterest);
        loanFlowHead1.setRealOverdueCorpus(realOverdueCorpus);
        loanFlowHead1.setRealOverdueInterest(realOverdueInterest);
        loanFlowHead1.setRealPunishInterest(realPunishInterest);
        loanFlowHead1.setShouldCorpus(shouldCorpus);
        loanFlowHead1.setShouldCount(new BigDecimal(realCount));
        loanFlowHead1.setShouldInterest(shouldInterest);
        loanFlowHead1.setShouldOverdueCorpus(shouldOverdueCorpus);
        loanFlowHead1.setShouldOverdueInterest(shouldOverdueInterest);
        loanFlowHead1.setShouldPunishInterest(shouldPunishInterest);

        // 插入日志
        this.addLog(loanFlowHead1, securityInfo);

      } catch (Exception e) {
        e.printStackTrace();
        throw new BusinessException(e.getMessage());
      }
    } else {
      throw new BusinessException("导入文件为空！");
    }
    return loanBankId;
  }

  /**
   * 以中心为主(有对应的导出文件) 根据导入数据更新业务流水表
   * 
   * @param importList
   * @param securityInfo
   * @throws Exception
   */
  public void updateBankImportsInfo(List importList, SecurityInfo securityInfo)
      throws Exception {
    String loanBankId = "";// 银行代码
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 业务日期
    String bizDateim = "";// 导入文件中的日期
    String batchNum = "";// 批次号
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    String paramValue = "";
    String headId = "";
    List temp_list = null;
    List import_list = null;
    List bankList = null;
    String bankDate = "";// 银行日期
    if (!importList.isEmpty()) {
      // try{
      // 判断导入的数据是否合法，并且导入数据的银行在贷款流水账头表PL202是否存在业务状态=1，导出的记录
      // 1、银行代码是否合法2、扣款日期是否与会计日期相同0
      BankExportsDTO dto = (BankExportsDTO) importList.get(0);
      loanBankId = String.valueOf(Integer.parseInt(dto.getLoanBankId()));
      bizDateim = dto.getBizDate();
      batchNum = dto.getBatchNum();
      bankList = securityDAO.getDkUserBankList_LJ(securityInfo.getUserName(),
          loanBankId);
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
      // 判断有无操作此银行的权限
      if (bankList.isEmpty()) {
        throw new BusinessException("不具备该银行的权限！");
      }
      // 判断有没有导入数据
      import_list = loanFlowHeadDAO.queryExportListByLoanKouAcc_LJ(loanBankId,
          String.valueOf(BusiConst.PLBUSINESSSTATE_IMP), batchNum);
      if (!import_list.isEmpty()) {
        throw new BusinessException("该银行已经存在导入记录！");
      }
      // 导入数据的银行在贷款流水账头表PL202是否存在业务状态=1，导出的记录
      temp_list = loanFlowHeadDAO.queryExportListByLoanKouAcc_LJ(loanBankId,
          String.valueOf(BusiConst.PLBUSINESSSTATE_EXP), batchNum);
      if (temp_list.isEmpty()) {
        throw new BusinessException("该银行没有对应的导出记录！");
      } else {
        // 取出头表ID
        Iterator it = temp_list.iterator();
        Object[] obj = null;
        while (it.hasNext()) {
          obj = (Object[]) it.next();
          headId = obj[0].toString();
        }
      }
      // 2、扣款日期是否与会计日期相同
      bizDate = bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
          + bizDate.substring(6, 8);
      if (!bizDate.equals(bizDateim)) {
        throw new BusinessException("导入文件中的日期与会计日期不一致！");
      }
      // 判断该贷款账号的所属银行在银行贷款参数PL003中类型为：A:还款参数，并且序号为1中参数值是否=1:足额扣款
      paramValue = this.getBackMode(loanBankId);
      List tailList = loanFlowTailDAO.queryLoanFlowTailIDByHeadId_LJ(headId);
      if (paramValue.equals(BusiConst.PLDEBITTYPE_SUFF + "")) {
        // 足额扣款
        loanFlowHead = this.addLoanFlowHeadFull(tailList, importList, headId,
            batchNum);
      } else {
        // 全额扣款
        loanFlowHead = this.addLoanFlowHeadALL(tailList, importList, headId,
            batchNum);
      }

      // 插入日志表
      this.addLog(loanFlowHead, securityInfo);
      // }catch(Exception e){
      // e.printStackTrace();
      // }
    } else {
      throw new BusinessException("导入文件为空！");
    }
  }

  /**
   * 足额扣款
   * 
   * @param tailList
   * @param importList
   * @param headId
   * @throws Exception
   */
  public LoanFlowHead addLoanFlowHeadFull(List tailList, List importList,
      String headId, String batchNum) throws Exception {
    LoanFlowHead loanFlowHead = null;
    BigDecimal realCorpus = new BigDecimal(0.00);// 实还本金
    BigDecimal realInterest = new BigDecimal(0.00);// 实还利息
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);// 实还逾期本金
    BigDecimal realOverdueInterest = new BigDecimal(0.00);// 实还逾期利息
    BigDecimal realPunishInterest = new BigDecimal(0.00);// 实还罚息
    BigDecimal occurMoney = new BigDecimal(0.00);// 发生额
    int realCount = 0;// 实还人数
    // try{
    if (!tailList.isEmpty()) {
      Iterator it = tailList.iterator();
      Object obj = null;
      while (it.hasNext()) {
        obj = (Object) it.next();
        LoanFlowTail loanFlowTail = loanFlowTailDAO.queryById(new Integer(obj
            .toString()));
        if (!importList.isEmpty()) {
          for (int i = 0; i < importList.size(); i++) {
            BankExportsDTO dto = (BankExportsDTO) importList.get(i);
            BankExportsDTO dto1 = (BankExportsDTO) importList.get(0);
            // （用导入文件中扣款标志=1的记录去更新该笔导出业务，关联条件为还款年月、贷款账号）
            if (dto.getLoanKouAcc().equals(loanFlowTail.getLoanKouAcc())
                && dto.getLoanKouYearmonth()
                    .equals(loanFlowTail.getYearMonth())
                && dto.getIdentifier().equals("1")) {
              // 判断导入文件中应扣字段是否与数据库中应还本金+应还利息+应还罚息一致
              if (loanFlowTail.getShouldCorpus().add(
                  loanFlowTail.getShouldInterest()).add(
                  loanFlowTail.getShouldPunishInterest().add(
                      loanFlowTail.getOccurMoney())).doubleValue() != dto
                  .getShouldMoney().doubleValue()) {
                throw new BusinessException("导入文件中应扣金额与数据库中应还金额不一致！");
              }
              loanFlowTail.setRealCorpus(loanFlowTail.getShouldCorpus());
              loanFlowTail.setRealInterest(loanFlowTail.getShouldInterest());
              loanFlowTail.setRealPunishInterest(loanFlowTail
                  .getShouldPunishInterest());
              loanFlowTail.setTempPunishInterest(loanFlowTail
                  .getTempPunishInterest().subtract(
                      loanFlowTail.getRealPunishInterest()));
              if (loanFlowTail.getLoanType().equals("1")) {
                // 正常
                realCorpus = realCorpus.add(loanFlowTail.getRealCorpus());
                realInterest = realInterest.add(loanFlowTail.getRealInterest());
              } else if (loanFlowTail.getLoanType().equals("2")) {
                // 逾期
                realOverdueCorpus = realOverdueCorpus.add(loanFlowTail
                    .getRealCorpus());
                realOverdueInterest = realOverdueInterest.add(loanFlowTail
                    .getRealInterest());
              }
              realPunishInterest = realPunishInterest.add(loanFlowTail
                  .getRealPunishInterest());
            } else if (dto.getLoanKouAcc().equals(loanFlowTail.getLoanKouAcc())
                && dto.getLoanKouYearmonth()
                    .equals(loanFlowTail.getYearMonth())
                && dto.getIdentifier().equals("0")) {
              occurMoney = occurMoney.add(loanFlowTail.getOccurMoney());
              // 如果批次号不为空,那么就走挂账
              if (!batchNum.equals("")) {
                loanFlowTail.setOccurMoney(occurMoney);
              } else {
                loanFlowTail.setOccurMoney(new BigDecimal(0.00));
              }
            }
            if (dto.getBatchNum() != null && !dto.getBatchNum().equals("")) {
              List batchNumList = loanFlowHeadDAO.queryBatchNum_GJP(dto1
                  .getBatchNum());
              if (batchNumList.size() == 0) {
                throw new BusinessException("导入文件中批次号不存在！");
              }
            }
          }
        }
      }
      // 得到实还人数
      realCount = loanFlowTailDAO.queryRealCountsByHeadId_LJ(headId);
      // 更新头表
      loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
      loanFlowHead.setRealCount(new BigDecimal(realCount));
      loanFlowHead.setRealCorpus(loanFlowHead.getRealCorpus().add(realCorpus));
      loanFlowHead.setRealInterest(loanFlowHead.getRealInterest().add(
          realInterest));
      loanFlowHead.setRealOverdueCorpus(loanFlowHead.getRealOverdueCorpus()
          .add(realOverdueCorpus));
      loanFlowHead.setRealOverdueInterest(loanFlowHead.getRealOverdueInterest()
          .add(realOverdueInterest));
      loanFlowHead.setRealPunishInterest(loanFlowHead.getRealPunishInterest()
          .add(realPunishInterest));
      loanFlowHead.setBizSt(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP));
      loanFlowHead.setOccurMoney(loanFlowHead.getOccurMoney().subtract(
          occurMoney));
      loanFlowHead.setBatchNum(batchNum);
    }
    // }catch(Exception e){
    // e.printStackTrace();
    // }
    return loanFlowHead;
  }

  // 全额扣款
  public LoanFlowHead addLoanFlowHeadALL(List tailList, List importList,
      String headId, String batchNum) throws Exception {
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    BigDecimal realCorpus = new BigDecimal(0.00);// 实还本金
    BigDecimal realInterest = new BigDecimal(0.00);// 实还利息
    BigDecimal realOverdueCorpus = new BigDecimal(0.00);// 实还逾期本金
    BigDecimal realOverdueInterest = new BigDecimal(0.00);// 实还逾期利息
    BigDecimal realPunishInterest = new BigDecimal(0.00);// 实还罚息
    BigDecimal realMoney = new BigDecimal(0.00);// 实扣金额
    String paramValue = "";// 参数值
    String loanKouAcc = "";// 贷款账号
    int realCount = 0;// 实还人数
    BigDecimal temp_money = new BigDecimal(0.00);
    BigDecimal sumMoney = new BigDecimal(0.00);// 查询数据库中总应还金额
    BigDecimal temp_occurmonty = new BigDecimal(0.00);// 判断是否要更新此条记录
    // 根据头表ID查询流水头表
    loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    Integer loanBankId = new Integer(loanFlowHead.getLoanBankId().toString());
    paramValue = loanBankParaDAO.queryParamValue_LJ(loanBankId, "A", "3");
    // try{
    if (!importList.isEmpty()) {
      for (int k = 1; k < importList.size(); k++) {
        BigDecimal occurMoney = new BigDecimal(0.00);// 发生额
        BankExportsDTO bankExportsDTO = (BankExportsDTO) importList.get(k);
        realMoney = bankExportsDTO.getRealMoney();// 导入文件中的实扣金额
        loanKouAcc = bankExportsDTO.getLoanKouAcc();// 从导入文件中得到贷款账号
        List list = loanFlowTailDAO.queryExportFlowTailByHeadId_LJ(headId,
            loanKouAcc);
        // 判断应还总额是否小于等于导入文件中实扣金额
        if (!list.isEmpty()) {
          for (int i = 0; i < list.size(); i++) {
            ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
            sumMoney = sumMoney.add(dto.getShouldCorpus().subtract(
                dto.getRealCorpus()).add(
                dto.getShouldInterest().subtract(dto.getRealInterest()).add(
                    dto.getShouldPunishInterest().subtract(
                        dto.getRealPunishInterest()))));
            occurMoney = occurMoney.add(dto.getOccurMoney());
          }
        }
        if (realMoney.add(occurMoney).doubleValue() > sumMoney.doubleValue()) {
          throw new BusinessException("实扣金额不能大于应还金额！");
        }
        realMoney = realMoney.subtract(occurMoney);
        for (int j = 0; j < paramValue.length(); j++) {
          if (BusiConst.PLALLMESS_CORPUS.equals(String.valueOf(paramValue
              .charAt(j)))) {// 正常本金
            if (!list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
                LoanFlowTail loanFlowTail = loanFlowTailDAO
                    .queryById(new Integer(dto.getId()));
                temp_occurmonty = loanFlowTail.getShouldCorpus().add(
                    loanFlowTail.getShouldInterest()).add(
                    loanFlowTail.getShouldPunishInterest());
                if (temp_occurmonty.doubleValue() != loanFlowTail
                    .getOccurMoney().doubleValue()) {
                  if (dto.getLoanKouType().equals("1")) {
                    if (dto.getShouldCorpus().doubleValue() <= realMoney
                        .doubleValue()) {
                      loanFlowTail.setRealCorpus(dto.getShouldCorpus());
                      // realCorpus = realCorpus.add(dto.getShouldCorpus());
                      realMoney = realMoney.subtract(dto.getShouldCorpus());
                    } else {
                      loanFlowTail.setRealCorpus(realMoney);
                      realMoney = new BigDecimal(0.00);
                    }
                    // realCorpus =
                    // realCorpus.add(loanFlowTail.getRealCorpus());
                  }
                }

              }
            }
          } else if (BusiConst.PLALLMESS_INTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// 正常利息
            if (!list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
                LoanFlowTail loanFlowTail = loanFlowTailDAO
                    .queryById(new Integer(dto.getId()));
                temp_occurmonty = loanFlowTail.getShouldCorpus().add(
                    loanFlowTail.getShouldInterest()).add(
                    loanFlowTail.getShouldPunishInterest());
                if (temp_occurmonty.doubleValue() != loanFlowTail
                    .getOccurMoney().doubleValue()) {
                  if (dto.getLoanKouType().equals("1")) {
                    if (dto.getShouldInterest().doubleValue() <= realMoney
                        .doubleValue()) {
                      loanFlowTail.setRealInterest(dto.getShouldInterest());
                      realMoney = realMoney.subtract(dto.getShouldInterest());
                    } else {
                      loanFlowTail.setRealInterest(realMoney);
                      realMoney = new BigDecimal(0.00);
                    }
                    // realInterest =
                    // realInterest.add(loanFlowTail.getRealInterest());
                  }
                }
              }
            }
          } else if (BusiConst.PLALLMESS_OVERDUECORPUS.equals(String
              .valueOf(paramValue.charAt(j)))) {// 逾期本金
            if (!list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
                LoanFlowTail loanFlowTail = loanFlowTailDAO
                    .queryById(new Integer(dto.getId()));
                temp_occurmonty = loanFlowTail.getShouldCorpus().add(
                    loanFlowTail.getShouldInterest()).add(
                    loanFlowTail.getShouldPunishInterest());
                if (temp_occurmonty.doubleValue() != loanFlowTail
                    .getOccurMoney().doubleValue()) {
                  if (dto.getLoanKouType().equals("2")) {
                    if (dto.getShouldCorpus().doubleValue() <= realMoney
                        .doubleValue()) {
                      loanFlowTail.setRealCorpus(dto.getShouldCorpus());
                      realMoney = realMoney.subtract(dto.getShouldCorpus());
                    } else {
                      loanFlowTail.setRealCorpus(realMoney);
                      realMoney = new BigDecimal(0.00);
                    }
                    // realOverdueCorpus =
                    // realOverdueCorpus.add(loanFlowTail.getRealCorpus());
                  }
                }
              }
            }
          } else if (BusiConst.PLALLMESS_OVERDUEINTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// 逾期利息
            if (!list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
                LoanFlowTail loanFlowTail = loanFlowTailDAO
                    .queryById(new Integer(dto.getId()));
                temp_occurmonty = loanFlowTail.getShouldCorpus().add(
                    loanFlowTail.getShouldInterest()).add(
                    loanFlowTail.getShouldPunishInterest());
                if (temp_occurmonty.doubleValue() != loanFlowTail
                    .getOccurMoney().doubleValue()) {
                  if (dto.getLoanKouType().equals("2")) {
                    if (dto.getShouldInterest().doubleValue() <= realMoney
                        .doubleValue()) {
                      loanFlowTail.setRealInterest(dto.getShouldInterest());
                      realMoney = realMoney.subtract(dto.getShouldInterest());
                    } else {
                      loanFlowTail.setRealInterest(realMoney);
                      realMoney = new BigDecimal(0.00);
                    }
                    // realOverdueInterest =
                    // realOverdueInterest.add(loanFlowTail.getRealInterest());
                  }
                }
              }
            }
          } else if (BusiConst.PLALLMESS_PUNISHINTEREST.equals(String
              .valueOf(paramValue.charAt(j)))) {// 罚息
            if (!list.isEmpty()) {
              for (int i = 0; i < list.size(); i++) {
                ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
                LoanFlowTail loanFlowTail = loanFlowTailDAO
                    .queryById(new Integer(dto.getId()));
                temp_occurmonty = loanFlowTail.getShouldCorpus().add(
                    loanFlowTail.getShouldInterest()).add(
                    loanFlowTail.getShouldPunishInterest());
                if (temp_occurmonty.doubleValue() != loanFlowTail
                    .getOccurMoney().doubleValue()) {
                  if (dto.getShouldPunishInterest().doubleValue() <= realMoney
                      .doubleValue()) {
                    loanFlowTail.setRealPunishInterest(dto
                        .getShouldPunishInterest());
                    realMoney = realMoney.subtract(dto
                        .getShouldPunishInterest());
                  } else {
                    loanFlowTail.setRealPunishInterest(realMoney);
                    realMoney = new BigDecimal(0.00);
                  }
                }
                // realPunishInterest =
                // realPunishInterest.add(loanFlowTail.getRealPunishInterest());
                loanFlowTail.setTempPunishInterest(loanFlowTail
                    .getTempPunishInterest().subtract(
                        loanFlowTail.getRealPunishInterest()));
              }
            }
          }
        }
        List occur_list = loanFlowTailDAO.queryExportFlowTailByHeadId_LJ(
            headId, loanKouAcc);
        BigDecimal temp_occurMoney = occurMoney.multiply(new BigDecimal(-1.00));
        if (!occur_list.isEmpty()) {
          for (int i = 0; i < occur_list.size(); i++) {
            ShouldBackListDTO dto = (ShouldBackListDTO) list.get(i);
            LoanFlowTail loanFlowTail = loanFlowTailDAO.queryById(new Integer(
                dto.getId()));
            temp_money = loanFlowTail.getRealCorpus().add(
                loanFlowTail.getRealInterest()).add(
                loanFlowTail.getRealPunishInterest());
            if (temp_money.doubleValue() <= temp_occurMoney.doubleValue()) {
              loanFlowTail.setOccurMoney(temp_money.multiply(new BigDecimal(
                  -1.00)));
              temp_occurMoney = temp_occurMoney.subtract(temp_money);
            } else {
              loanFlowTail.setOccurMoney(temp_occurMoney
                  .multiply(new BigDecimal(-1.00)));
              temp_occurMoney = new BigDecimal(0.00);
            }
          }
        }
      }
    }
    // }catch(Exception e){
    // e.printStackTrace();
    // }
    List tail_list = loanFlowTailDAO.queryRealLoanFlowTailByHeadId_LJ(headId);
    if (!tail_list.isEmpty()) {
      for (int i = 0; i < tail_list.size(); i++) {
        ShouldBackListDTO dto = (ShouldBackListDTO) tail_list.get(i);
        if (dto.getLoanKouType().equals("1")) {
          realCorpus = realCorpus.add(dto.getShouldCorpus());
          realInterest = realInterest.add(dto.getShouldInterest());
        } else if (dto.getLoanKouType().equals("2")) {
          realOverdueCorpus = realOverdueCorpus.add(dto.getShouldCorpus());
          realOverdueInterest = realOverdueInterest
              .add(dto.getShouldInterest());
        }
        realPunishInterest = realPunishInterest.add(dto.getPunishInterest());
      }
    }
    // 得到实还人数
    realCount = loanFlowTailDAO.queryRealCountsByHeadId_LJ(headId);
    // 再通过尾表更新头表
    loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    loanFlowHead.setRealCount(new BigDecimal(realCount));
    loanFlowHead.setRealCorpus(realCorpus);
    loanFlowHead.setRealInterest(realInterest);
    loanFlowHead.setRealOverdueCorpus(realOverdueCorpus);
    loanFlowHead.setRealOverdueInterest(realOverdueInterest);
    loanFlowHead.setRealPunishInterest(realPunishInterest);
    loanFlowHead.setBizSt(String.valueOf(BusiConst.PLBUSINESSSTATE_IMP));
    loanFlowHead.setBatchNum(batchNum);
    return loanFlowHead;
  }

  /**
   * 插入日志
   * 
   * @param loanFlowHead
   * @param securityInfo
   */
  public void addLog(LoanFlowHead loanFlowHead, SecurityInfo securityInfo) {
    // 插入业务日志
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiConst.PLBUSINESSSTATE_IMP + "");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId()
        .toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    // 插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(loanFlowHead.getFlowHeadId().toString());
    plOperateLog.setOpButton(BusiLogConst.BIZBLOG_ACTION_IMP + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUIMP + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
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
   * 回收更新流水表状态
   * 
   * @param tailId
   * @param securityInfo
   * @throws Exception
   */
  public void loanCallback(String tailId, SecurityInfo securityInfo)
      throws Exception {
    // 判断该笔业务在贷款流水账头表PL203中的业务状态BIZ_ST是否=2，导入（用PL202表中FLOW_HEAD_ID关联）
    LoanFlowTail loanFlowTail = loanFlowTailDAO.queryById(new Integer(tailId));
    String headId = loanFlowTail.getFlowHeadId().toString();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    if (!loanFlowHead.getBizSt().equals(
        String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))) {
      throw new BusinessException("该笔业务不是导入状态，不能进行回收操作！");
    }
    // 更新该笔业务的贷款流水账头表PL202中的业务状态BIZ_ST=4.确认（关联的条件PL202表中FLOW_HEAD_ID）
    loanFlowHead.setBizSt(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
    // 插入日志表
    // 插入业务日志
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setAction(BusiConst.BUSINESSSTATE_SURE + "");
    plBizActiveLog.setBizid(new BigDecimal(loanFlowHead.getFlowHeadId()
        .toString()));
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setType(loanFlowHead.getBizType());
    plBizActiveLogDAO.insert(plBizActiveLog);
    // 插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(loanFlowHead.getFlowHeadId().toString());
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_CONFIRM + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUIMP + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
  }

  /**
   * 全部删除
   * 
   * @param tailId
   * @param securityInfo
   * @throws Exception
   */
  public void deleteAllCallbackInfo(String tailId, SecurityInfo securityInfo)
      throws Exception {
    // 判断该笔业务在贷款流水账头表PL203中的业务状态BIZ_ST是否=2，导入（用PL202表中FLOW_HEAD_ID关联）
    LoanFlowTail loanFlowTail = loanFlowTailDAO.queryById(new Integer(tailId));
    String headId = loanFlowTail.getFlowHeadId().toString();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    String office = "";// 办事处代码
    if (!loanFlowHead.getBizSt().equals(
        String.valueOf(BusiConst.PLBUSINESSSTATE_IMP))) {
      throw new BusinessException("该笔业务不是导入状态，不能进行全部删除操作！");
    }
    // 删除该笔业务在贷款流水账头表PL202及贷款流水账头尾PL203的记录（关联的条件PL202表中FLOW_HEAD_ID与PL203表中FLOW_HEAD_ID中该贷款号的业务记录）
    // 删除尾表
    loanFlowTailDAO.deleteLoanFlowTailAll(headId);
    // 删除业务日志，关联条件为贷款流水账头表PL202中FLOW_HEAD_ID与业务活动日志PL020表中BIZID and
    // ACTION=（2.导入）
    // 删除业务日志
    plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId, loanFlowHead.getBizSt());
    plBizActiveLogDAO.deletePlBizActiveLog_LJ(headId, String
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
    plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(), officeId,
        loanFlowHead.getBizDate().substring(0, 6));
    // 删除头表
    loanFlowHeadDAO.delete(loanFlowHead);

    // 插入操作日志
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setContractId(loanFlowHead.getFlowHeadId().toString());
    plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");
    plOperateLog.setOperator(securityInfo.getUserName());
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpModel(BusiLogConst.PL_OP_LOANRECOVER_LOANKOUIMP + "");
    plOperateLog.setOpTime(new Date());
    plOperateLogDAO.insert(plOperateLog);
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
}