package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountPopFindDTO;

import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountPopListDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaSaveDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTbFindDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTbListDTO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
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
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.BorrowerInfoDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public class AdjustAccountBS implements IAdjustAccountBS {
  // PL202表DAO
  protected LoanFlowHeadDAO loanFlowHeadDAO = null;

  // PL203表DAO
  protected LoanFlowTailDAO loanFlowTailDAO = null;

  // 凭证号
  protected PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  // PL111表DAO
  protected BorrowerAccDAO borrowerAccDAO = null;

  // PL020表DAO
  protected PlBizActiveLogDAO plBizActiveLogDAO = null;

  // PL021表DAO
  protected PlOperateLogDAO plOperateLogDAO = null;

  // 撤销凭证号
  protected PlDocNumCancelDAO plDocNumCancelDAO = null;

  // PL201的DAO
  protected RestoreLoanDAO restoreLoanDAO = null;

  protected CollParaDAO collParaDAO = null;

  protected CollBankDAO collBankDAO = null;

  protected LoanBankDAO loanBankDAO = null;

  protected SecurityDAO securityDAO = null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public List findAdjustAccountPopList(Pagination pagination, List loanbankList)
      throws Exception {
    // TODO Auto-generated method stub
    AdjustAccountPopFindDTO adjustAccountPopFindDTO = null;
    if (pagination.getQueryCriterions().get("adjustAccountPopFindDTO") == null) {
      adjustAccountPopFindDTO = new AdjustAccountPopFindDTO();
    } else {
      adjustAccountPopFindDTO = (AdjustAccountPopFindDTO) pagination
          .getQueryCriterions().get("adjustAccountPopFindDTO");
    }

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();

    List adjustAccountPopListDTOList = loanFlowHeadDAO
        .queryAdjustAccountPopList(adjustAccountPopFindDTO, orderBy, order,
            start, pageSize, loanbankList);

    for (int i = 0; i < adjustAccountPopListDTOList.size(); i++) {
      AdjustAccountPopListDTO adjustAccountPopListDTO = (AdjustAccountPopListDTO) adjustAccountPopListDTOList
          .get(i);
      // 临时存放发生金额，根据不同状态输出
      BigDecimal temp_Money = adjustAccountPopListDTO.getOccurMoney();

      // 判断是否有发放金额
      if (!adjustAccountPopListDTO.getBizType().equals("1")) {
        adjustAccountPopListDTO.setOccurMoney(new BigDecimal(0.00));
      }
      // 判断是否有挂账发生金额
      if (adjustAccountPopListDTO.getBizType().equals("2")
          || adjustAccountPopListDTO.getBizType().equals("5")) {
        adjustAccountPopListDTO.setOverPay(temp_Money);
      } else {
        adjustAccountPopListDTO.setOverPay(new BigDecimal(0.00));
      }

      // 转换业务类型
      adjustAccountPopListDTO.setBizType(BusiTools.getBusiValue(Integer
          .parseInt(adjustAccountPopListDTO.getBizType()),
          BusiConst.PLBUSINESSTYPE));
      // 计算实收金额
      adjustAccountPopListDTO.setFactPay(adjustAccountPopListDTO.getOverPay()
          .add(adjustAccountPopListDTO.getLoanBackPay()));

    }

    int count = loanFlowHeadDAO.queryAdjustAccountPopCount(
        adjustAccountPopFindDTO, orderBy, order, start, pageSize, loanbankList);

    pagination.setNrOfElements(count);

    return adjustAccountPopListDTOList;
  }

  public AdjustAccountTaInfoDTO judgeLoanFlowHead(String flowHeadId,
      List loanbankList, SecurityInfo securityInfo) throws Exception,
      BusinessException {

    AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = null;

    // 根据PL202的主键查找业务状态
    Object[] obj = loanFlowHeadDAO.queryBizTypeByFlowHeadId(flowHeadId);
    String bizType = obj[0].toString();

    String bankDate = "";// 银行日期
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 会计日期
    bankDate = loanBankDAO.queryPL002BizDate_jj(obj[2].toString());
    if (!bizDate.equals(bankDate)) {
      throw new BusinessException("登录日期与银行业务日期不一致，不能做业务！");
    }
    if (obj[1].toString().equals("1")) {
      throw new BusinessException("该笔业务已做过错账调整，不可以再调整!");
    }

    // 单笔回收业务由此判断
    if (bizType.equals("2") || bizType.equals("6") || bizType.equals("7")) {
      String maxflowHeadId = loanFlowTailDAO.queryMaxFlowHeadId(flowHeadId);
      LoanFlowHead head = loanFlowHeadDAO.queryById(Integer
          .valueOf(maxflowHeadId));
      // 如果不所要调整的业务不是最后一笔则抛出异常
      if (flowHeadId.equals(maxflowHeadId)) {
        // 状态验证成功可以进行调整业务
        adjustAccountTaInfoDTO = loanFlowHeadDAO
            .queryAdjustAccountSingle(flowHeadId);
        adjustAccountTaInfoDTO.setStrBizType(BusiTools.getBusiValue(Integer
            .parseInt(adjustAccountTaInfoDTO.getBizType()),
            BusiConst.PLBUSINESSTYPE));
      } else {
        throw new BusinessException("该笔业务不是最后一笔回收业务，不可以调整!\\r最后一笔业务的凭证号是"
            + head.getDocNum() + " 业务日期是" + head.getBizDate());
      }
    }

    // 批量回收由此判断
    if (bizType.equals("5")) {
      // 状态是批量回收中头表id最大值
      String maxflowHeadId = loanFlowHeadDAO.queryMaxFlowHeadId(new Integer(
          obj[2].toString()));
      LoanFlowHead head = loanFlowHeadDAO.queryById(Integer
          .valueOf(maxflowHeadId));
      if (flowHeadId.equals(maxflowHeadId)) {
        List list = loanFlowTailDAO.queryIsNotMaxFlowHeadId(flowHeadId,
            loanbankList);
        // 判断该笔里面的人是否都是最后一笔
        if (list.size() > 0) {
          throw new BusinessException("该笔业务中某些人不是最后一笔回收，不可以调整!");
        } else {
          // 状态验证成功可以进行调整业务
          adjustAccountTaInfoDTO = loanFlowHeadDAO
              .queryAdjustAccountBatch(flowHeadId);
          adjustAccountTaInfoDTO.setStrBizType(BusiTools.getBusiValue(Integer
              .parseInt(adjustAccountTaInfoDTO.getBizType()),
              BusiConst.PLBUSINESSTYPE));
        }

      } else {
        throw new BusinessException("该笔业务不是该放款银行下的最后一笔批量回收业务，不可以调整!\\r"
            + "最后一笔业务的凭证号是" + head.getDocNum() + " 业务日期是" + head.getBizDate());
      }
    }

    // 发放业务由此判断
    if (bizType.equals("1")) {
      // 判断该帐号是否做过除发放与保证金以外的业务,当i=0时则没有做过其他业务
      int i = loanFlowTailDAO.queryIsExistFlowHeadId(flowHeadId);
      if (i == 0) {
        // 状态验证成功可以进行调整业务
        adjustAccountTaInfoDTO = loanFlowHeadDAO
            .queryAdjustAccountPutout(flowHeadId);
        adjustAccountTaInfoDTO.setStrBizType(BusiTools.getBusiValue(Integer
            .parseInt(adjustAccountTaInfoDTO.getBizType()),
            BusiConst.PLBUSINESSTYPE));
      } else {
        throw new BusinessException("该笔放款业务已做回收，不可以调整!");
      }
    }

    return adjustAccountTaInfoDTO;
  }

  public AdjustAccountTaInfoDTO judgeLoanKouAcc(String loanKouAcc,
      List loanbankList, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    AdjustAccountTaInfoDTO adjustAccountTaInfoDTO = new AdjustAccountTaInfoDTO();
    String borrowerName = loanFlowTailDAO.queryBorrowerNameByLoanKouAcc(
        loanKouAcc, loanbankList);
    String bankDate = "";// 银行日期
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 会计日期
    String bankId = loanFlowTailDAO.queryBorrowerBankIdByLoanKouAcc(loanKouAcc,
        loanbankList);
    if (borrowerName == null) {
      throw new BusinessException("您输入的贷款帐号可能不存在!");
    } else {
      bankDate = loanBankDAO.queryPL002BizDate_jj(bankId);
      if (!bizDate.equals(bankDate)) {
        throw new BusinessException("登录日期与银行业务日期不一致，不能做业务！");
      }
      adjustAccountTaInfoDTO.setBorrowerName(borrowerName);
    }
    return adjustAccountTaInfoDTO;
  }

  public String saveAdjustAccountInfo(String flowHeadId, String loanKouAcc,
      String bizType, String autoOverPay, SecurityInfo securityInfo,
      AdjustAccountTaInfoDTO adjustAccountTaInfoDTO) throws Exception {

    Serializable new_flowHeadId = null;
    String contractId = "";
    // 查询出将要调整的帐务信息,如果要是批量的帐务则查询出对应尾表的信息
    if (bizType != null && !bizType.equals("")) {
      /** -------------批量调整------------ */
      if (bizType.equals("5")) {
        /** 批量回收 */
        AdjustAccountTaSaveDTO adjustAccountTaSaveDTO = loanFlowHeadDAO
            .queryAdjustAccountInfoBatch(flowHeadId);
        // 插入PL202
        String[] str = saveLoanFlowHeadInfo(autoOverPay, securityInfo,
            adjustAccountTaSaveDTO);
        // 查询对应PL203的纪录
        List list = loanFlowTailDAO.queryLoanFlowTailByFlowHeadId(flowHeadId);
        // 循环插入PL203
        for (int i = 0; i < list.size(); i++) {
          AdjustAccountTaSaveDTO temp_adjustAccountTaSaveDTO = (AdjustAccountTaSaveDTO) list
              .get(i);
          // 查询是否为最后一次换款
          Object obj = loanFlowTailDAO
              .queryLoanMoney(temp_adjustAccountTaSaveDTO.getContractId());
          if (new BigDecimal(obj.toString()).intValue() == 0) {
            loanFlowTailDAO.updateBorrowerAcc(temp_adjustAccountTaSaveDTO
                .getContractId());
            loanFlowTailDAO.updateCongealInfo(temp_adjustAccountTaSaveDTO
                .getContractId());
          }
          saveLoanFlowTailInfo(autoOverPay, temp_adjustAccountTaSaveDTO, str[0]);
        }
        new_flowHeadId = str[0];
        contractId = str[0];
      } else if (bizType.equals("2") || bizType.equals("6")
          || bizType.equals("7")) {
        /** 单笔回收 */
        AdjustAccountTaSaveDTO adjustAccountTaSaveDTO = loanFlowHeadDAO
            .queryAdjustAccountInfoSingleHead(flowHeadId);
        // 插入PL202
        String[] str = saveLoanFlowHeadInfo(autoOverPay, securityInfo,
            adjustAccountTaSaveDTO);
        // 查询对应PL203的纪录
        List list = loanFlowTailDAO.queryLoanFlowTailByFlowHeadId(flowHeadId);
        String temp_contractId = ((AdjustAccountTaSaveDTO) list.get(0))
            .getContractId();
        // 查询是否为最后一次换款
        Object obj = loanFlowTailDAO.queryLoanMoney(temp_contractId);
        if (new BigDecimal(obj.toString()).intValue() == 0) {
          loanFlowTailDAO.updateBorrowerAcc(temp_contractId);
          loanFlowTailDAO.updateCongealInfo(temp_contractId);
        }
        // 循环插入PL203
        for (int i = 0; i < list.size(); i++) {
          AdjustAccountTaSaveDTO temp_adjustAccountTaSaveDTO = (AdjustAccountTaSaveDTO) list
              .get(i);
          saveLoanFlowTailInfo(autoOverPay, temp_adjustAccountTaSaveDTO, str[0]);
        }
        // 插入PL203
        // saveLoanFlowTailInfo(autoOverPay, adjustAccountTaSaveDTO, str[0]);
        // 得到插入PL201的合同编号
        contractId = adjustAccountTaSaveDTO.getContractId();
        new_flowHeadId = str[0];
      } else if (bizType.equals("1")) {
        /** 发放业务 */
        AdjustAccountTaSaveDTO adjustAccountTaSaveDTO = loanFlowHeadDAO
            .queryAdjustAccountInfoSingle(flowHeadId);
        // OCCUR_MONEY = 页面上的发放金额
        adjustAccountTaSaveDTO.setOccurMoney(adjustAccountTaInfoDTO
            .getPutOutMoney());
        // 插入PL202
        String[] str = saveLoanFlowHeadInfo(autoOverPay, securityInfo,
            adjustAccountTaSaveDTO);
        // 插入PL203
        saveLoanFlowTailInfo(autoOverPay, adjustAccountTaSaveDTO, str[0]);
        // 得到插入PL201的合同编号
        contractId = adjustAccountTaSaveDTO.getContractId();
        new_flowHeadId = str[0];
      }
      // 将被调整业务修改成以做过错帐调整业务状态
      loanFlowHeadDAO.updateAdjustAccountIs_adjust(flowHeadId, "1");
    } else {
      /** -------------单笔调整------------ */
      // 插入PL202表
      LoanFlowHead loanFlowHead = new LoanFlowHead();
      loanFlowHead.setBizDate(securityInfo.getUserInfo().getPlbizDate());
      loanFlowHead.setBizType(String
          .valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG));
      loanFlowHead.setRealCount(new BigDecimal(1));
      loanFlowHead.setRealCorpus(adjustAccountTaInfoDTO.getCallbackMoney());
      loanFlowHead
          .setRealInterest(adjustAccountTaInfoDTO.getCallbackInterest());
      loanFlowHead.setRealPunishInterest(adjustAccountTaInfoDTO
          .getPunishInterest());
      loanFlowHead.setOccurMoney(new BigDecimal(0.00));
      loanFlowHead.setIsFinance(new Integer(1));

      Object[] obj = borrowerAccDAO
          .queryLoanBankIdByLoanKouAcc(adjustAccountTaInfoDTO.getLoanKouAcc());

      // 得到插入PL201的合同编号
      contractId = obj[1].toString();
      String officeId = "";
      String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
      if (loanDocNumDocument.equals("1")) {
        officeId = loanFlowHeadDAO.queryOfficeByBank(obj[0].toString());
      } else {
        officeId = obj[0].toString();
      }
      String docNum = plDocNumMaintainDAO.getDocNumdocNum(officeId,
          securityInfo.getUserInfo().getPlbizDate().substring(0, 6));
      Map office = securityInfo.getOfficeInnerCodeMap();
      String officecode = office.get(officeId).toString();
      if (officecode.length() == 1) {
        officecode = "0" + officecode;
      }
      String loanbankid = obj[0].toString();
      docNum = securityInfo.getUserInfo().getPlbizDate().substring(0, 4)
          + officecode
          + (loanbankid.length() < 2 ? "0" + loanbankid : loanbankid) + docNum;
      loanFlowHead.setLoanBankId(new BigDecimal(obj[0].toString()));
      loanFlowHead.setDocNum(docNum);
      loanFlowHead.setMakePerson(securityInfo.getUserName());
      loanFlowHead.setBizSt(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
      new_flowHeadId = loanFlowHeadDAO.insert(loanFlowHead);

      // 插入PL203
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      loanFlowTail.setFlowHeadId(new BigDecimal(new_flowHeadId.toString()));
      loanFlowTail.setLoanKouAcc(adjustAccountTaInfoDTO.getLoanKouAcc());
      loanFlowTail.setContractId(obj[1].toString());
      loanFlowTail.setRealCorpus(adjustAccountTaInfoDTO.getCallbackMoney());
      loanFlowTail
          .setRealInterest(adjustAccountTaInfoDTO.getCallbackInterest());
      loanFlowTail.setRealPunishInterest(adjustAccountTaInfoDTO
          .getPunishInterest());
      loanFlowTail.setOccurMoney(new BigDecimal(0.00));
      loanFlowTail.setYearMonth(adjustAccountTaInfoDTO.getYearMonth());

      loanFlowTailDAO.insert(loanFlowTail);

    }
    // 插入PL020
    PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
    plBizActiveLog.setBizid(new BigDecimal(new_flowHeadId.toString()));
    plBizActiveLog.setAction(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
    plBizActiveLog.setOpTime(new Date());
    plBizActiveLog.setOperator(securityInfo.getUserName());
    plBizActiveLog.setType(String
        .valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG));

    plBizActiveLogDAO.insert(plBizActiveLog);

    // 插入PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog.setOpModel(String
        .valueOf(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_DO));
    plOperateLog
        .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_CONFIRM));
    plOperateLog.setOpBizId(new BigDecimal(new_flowHeadId.toString()));
    plOperateLog.setContractId(contractId);
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);
    return new_flowHeadId.toString();

  }

  /**
   * 插入PL202的方法
   * 
   * @param autoOverPay 是否自动挂账
   * @param securityInfo 权限
   * @param adjustAccountTaSaveDTO 被调整业务的信息
   * @return obj[0] PL202的主键,obj[1]新生成的凭证号
   * @throws Exception
   * @throws BusinessException
   */
  private String[] saveLoanFlowHeadInfo(String autoOverPay,
      SecurityInfo securityInfo, AdjustAccountTaSaveDTO adjustAccountTaSaveDTO)
      throws Exception, BusinessException {
    String[] str = new String[2];
    // String office = loanFlowHeadDAO.queryOfficeByBank(adjustAccountTaSaveDTO
    // .getLoanBankId());
    String officeId = "";
    String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
    if (loanDocNumDocument.equals("1")) {
      officeId = loanFlowHeadDAO.queryOfficeByBank(adjustAccountTaSaveDTO
          .getLoanBankId());
    } else {
      officeId = adjustAccountTaSaveDTO.getLoanBankId().toString();
    }
    String bizYearmonth = securityInfo.getUserInfo().getPlbizDate().substring(
        0, 6);
    String loanbankid = adjustAccountTaSaveDTO.getLoanBankId();
    String docNum = plDocNumMaintainDAO.getDocNumdocNum(loanbankid,
        bizYearmonth.substring(0, 4));
    Map office = securityInfo.getOfficeInnerCodeMap();
    String officecode = office.get(officeId).toString();
    if (officecode.length() == 1) {
      officecode = "0" + officecode;
    }
    docNum = bizYearmonth.substring(0, 4) + officecode + "0" + loanbankid
        + docNum;
    // 插入PL202表
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    loanFlowHead.setBizDate(securityInfo.getUserInfo().getPlbizDate());
    loanFlowHead.setBizType(String
        .valueOf(BusiConst.PLBUSINESSTYPE_MISDIRECTCHG));

    loanFlowHead.setShouldCount(adjustAccountTaSaveDTO.getShouldCount());
    loanFlowHead.setRealCount(adjustAccountTaSaveDTO.getRealCount());

    loanFlowHead.setShouldCorpus(adjustAccountTaSaveDTO.getShouldCorpus());
    loanFlowHead.setShouldInterest(adjustAccountTaSaveDTO.getShouldInterest());
    loanFlowHead.setShouldOverdueCorpus(adjustAccountTaSaveDTO
        .getShouldOverdueCorpus());
    loanFlowHead.setShouldOverdueInterest(adjustAccountTaSaveDTO
        .getShouldOverdueInterest());
    loanFlowHead.setShouldPunishInterest(adjustAccountTaSaveDTO
        .getShouldPunishInterest());

    loanFlowHead.setNoteNum(adjustAccountTaSaveDTO.getFlowHeadId());
    loanFlowHead.setRealCorpus(adjustAccountTaSaveDTO.getRealCorpus().multiply(
        new BigDecimal(-1)));
    loanFlowHead.setRealInterest(adjustAccountTaSaveDTO.getRealInterest()
        .multiply(new BigDecimal(-1)));
    loanFlowHead.setRealOverdueCorpus(adjustAccountTaSaveDTO
        .getRealOverdueCorpus().multiply(new BigDecimal(-1)));
    loanFlowHead.setRealOverdueInterest(adjustAccountTaSaveDTO
        .getRealOverdueInterest().multiply(new BigDecimal(-1)));
    loanFlowHead.setRealPunishInterest(adjustAccountTaSaveDTO
        .getRealPunishInterest().multiply(new BigDecimal(-1)));
    loanFlowHead.setIsFinance(new Integer(1));
    // 如果为发放状态，发放金额为页面输入的金额
    if (adjustAccountTaSaveDTO.getBizType().equals("1")) {
      loanFlowHead.setOccurMoney(adjustAccountTaSaveDTO.getOccurMoney());
    } else {
      // 判断是否制动挂账
      if (autoOverPay == null || autoOverPay.equals("")
          || autoOverPay.equals("1")) {
        loanFlowHead.setOccurMoney(adjustAccountTaSaveDTO.getOccurMoney()
            .multiply(new BigDecimal(-1)));
      } else {
        loanFlowHead.setOccurMoney(adjustAccountTaSaveDTO.getRealCorpus().add(
            adjustAccountTaSaveDTO.getRealInterest()).add(
            adjustAccountTaSaveDTO.getRealOverdueCorpus().add(
                adjustAccountTaSaveDTO.getRealOverdueInterest().add(
                    adjustAccountTaSaveDTO.getRealPunishInterest()))));
      }
    }
    loanFlowHead.setDocNum(docNum);
    loanFlowHead.setBizSt(String.valueOf(BusiConst.BUSINESSSTATE_SURE));
    loanFlowHead.setLoanBankId(new BigDecimal(adjustAccountTaSaveDTO
        .getLoanBankId()));
    loanFlowHead.setWrongFlowNum(new BigDecimal(adjustAccountTaSaveDTO
        .getFlowHeadId()));
    loanFlowHead.setWrongBizType(adjustAccountTaSaveDTO.getBizType());
    loanFlowHead.setMakePerson(securityInfo.getUserName());

    Serializable temp_flowHeadId = loanFlowHeadDAO.insert(loanFlowHead);
    // 插入票据编号
    // 系统自动生成结算号：业务日期+流水号
    String noteNum = "";
    String bizDate = securityInfo.getUserInfo().getBizDate();
    noteNum = bizDate + loanFlowHeadDAO.queryNoteNum();
    loanFlowHead.setNoteNum(noteNum);// 将流水号插到票据编号中
    str[0] = temp_flowHeadId.toString();
    str[1] = docNum;
    return str;
  }

  /**
   * 插入PL203的方法
   * 
   * @param autoOverPay 是否自动挂账
   * @param adjustAccountTaSaveDTO 被调整业务的信息
   * @param temp_flowHeadId 头表的主键
   */
  private void saveLoanFlowTailInfo(String autoOverPay,
      AdjustAccountTaSaveDTO adjustAccountTaSaveDTO,
      Serializable temp_flowHeadId) {
    LoanFlowTail loanFlowTail = new LoanFlowTail();

    loanFlowTail.setFlowHeadId(new BigDecimal(temp_flowHeadId.toString()));
    loanFlowTail.setContractId(adjustAccountTaSaveDTO.getContractId());
    loanFlowTail.setLoanKouAcc(adjustAccountTaSaveDTO.getLoanKouAcc());
    loanFlowTail.setYearMonth(adjustAccountTaSaveDTO.getYearMonth());

    loanFlowTail.setShouldCorpus(adjustAccountTaSaveDTO.getShouldCorpusTail());
    loanFlowTail.setShouldInterest(adjustAccountTaSaveDTO
        .getShouldInterestTail());
    loanFlowTail.setShouldPunishInterest(adjustAccountTaSaveDTO
        .getShouldPunishInterestTail());

    loanFlowTail.setRealCorpus(adjustAccountTaSaveDTO.getRealCorpusTail()
        .multiply(new BigDecimal(-1)));
    loanFlowTail.setRealInterest(adjustAccountTaSaveDTO.getRealInterestTail()
        .multiply(new BigDecimal(-1)));
    loanFlowTail.setRealPunishInterest(adjustAccountTaSaveDTO
        .getRealPunishInterestTail().multiply(new BigDecimal(-1)));
    loanFlowTail.setLoanType(adjustAccountTaSaveDTO.getLoanType());
    loanFlowTail.setTempPunishInterest(adjustAccountTaSaveDTO
        .getTempPunishInterest().add(
            adjustAccountTaSaveDTO.getRealPunishInterestTail()));
    // 如果为发放状态，发放金额为页面输入的金额
    if (adjustAccountTaSaveDTO.getBizType().equals("1")) {
      loanFlowTail.setOccurMoney(adjustAccountTaSaveDTO.getOccurMoney());
    } else {
      // 判断是否制动挂账
      if (autoOverPay == null || autoOverPay.equals("")
          || autoOverPay.equals("1")) {
        loanFlowTail.setOccurMoney(adjustAccountTaSaveDTO.getOccurMoneyTail()
            .multiply(new BigDecimal(-1)));
      } else {
        loanFlowTail.setOccurMoney(adjustAccountTaSaveDTO.getRealCorpusTail()
            .add(
                adjustAccountTaSaveDTO.getRealInterestTail().add(
                    adjustAccountTaSaveDTO.getRealPunishInterestTail())));
      }
    }
    loanFlowTailDAO.insert(loanFlowTail);
  }

  public Object[] findAdjustAccountList(Pagination pagination, List loanbankList)
      throws Exception {
    Object[] temp_array = new Object[5];
    List list = null;
    AdjustAccountTbFindDTO adjustAccountTbFindDTO = (AdjustAccountTbFindDTO) pagination
        .getQueryCriterions().get("adjustAccountTbFindDTO");
    String type = "";
    if (pagination.getQueryCriterions().get("type") != null) {
      type = (String) pagination.getQueryCriterions().get("type");
    }
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();

    if (adjustAccountTbFindDTO == null) {
      adjustAccountTbFindDTO = new AdjustAccountTbFindDTO();
    }

    list = loanFlowHeadDAO.queryAdjustAccountList(adjustAccountTbFindDTO, type,
        orderBy, order, start, pageSize, loanbankList, page);

    for (int i = 0; i < list.size(); i++) {
      AdjustAccountTbListDTO adjustAccountTbListDTO = (AdjustAccountTbListDTO) list
          .get(i);
      String bizType = adjustAccountTbListDTO.getBizType();
      if (!bizType.equals("1")) {
        adjustAccountTbListDTO.setOccurMoney(new BigDecimal(0.00));
      }
      // 转换业务类型
      if (adjustAccountTbListDTO.getBizType() == null
          || adjustAccountTbListDTO.getBizType().equals("")) {
        adjustAccountTbListDTO.setBizType("");
      } else {
        adjustAccountTbListDTO.setBizTypeStr(BusiTools.getBusiValue(Integer
            .parseInt(adjustAccountTbListDTO.getBizType()),
            BusiConst.PLBUSINESSTYPE));
      }

    }

    BigDecimal sumOccurMoney = new BigDecimal(0.00);
    BigDecimal sumCallbackCorpus = new BigDecimal(0.00);
    BigDecimal sumCallbackInterest = new BigDecimal(0.00);
    BigDecimal sumPunishInterest = new BigDecimal(0.00);

    // 求出合计与count
    List countList = loanFlowHeadDAO.queryAdjustAccountCount(
        adjustAccountTbFindDTO, type, loanbankList);
    for (int i = 0; i < countList.size(); i++) {
      AdjustAccountTbListDTO adjustAccountTbListDTO = (AdjustAccountTbListDTO) countList
          .get(i);
      if (adjustAccountTbListDTO.getBizType().equals("1")) {
        sumOccurMoney = sumOccurMoney.add(adjustAccountTbListDTO
            .getOccurMoney());
      }
      sumCallbackCorpus = sumCallbackCorpus.add(adjustAccountTbListDTO
          .getCallbackCorpus());
      sumCallbackInterest = sumCallbackInterest.add(adjustAccountTbListDTO
          .getCallbackInterest());
      sumPunishInterest = sumPunishInterest.add(adjustAccountTbListDTO
          .getPunishInterest());

    }
    pagination.setNrOfElements(countList.size());

    // 将查询的结果封装到数组
    temp_array[0] = list;
    temp_array[1] = sumOccurMoney;
    temp_array[2] = sumCallbackCorpus;
    temp_array[3] = sumCallbackInterest;
    temp_array[4] = sumPunishInterest;

    return temp_array;
  }

  public void deleteAdjustAccountInfo(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException {

    Object[] obj = loanFlowHeadDAO.queryBizStAndWrongBizType(flowHeadId);
    // 判断被删除的业务状态
    if (obj == null) {
      throw new BusinessException("您所删除的错账调整业务不存在！");
    } else if (!obj[0].toString().equals("4")) {
      throw new BusinessException("该笔业务的状态不是确认，不可以删除！");
    }
    // 判断是批量调整还是单笔调整
    if (obj[1] != null && !obj[1].toString().equals("")) {
      if (obj[2].toString() != null) {
        // 如果是批量业务则更新被调整业务Is_adjust状态
        loanFlowHeadDAO.updateAdjustAccountIs_adjust(obj[2].toString(), "");
        if (obj[1].toString().equals("2") || obj[1].toString().equals("5")) {
          try {
            loanFlowTailDAO.updateCongealInfoInDelete(flowHeadId);
          } catch (BusinessException e) {
            throw new BusinessException("删除失败！");
          }

        }
      }
    }

    // 查找要删除流水头尾表
    List temp_FlowHeadInfo = new ArrayList();
    temp_FlowHeadInfo = loanFlowTailDAO.queryLoanFlowHeadInfo(flowHeadId);
    Object[] info = (Object[]) temp_FlowHeadInfo.get(0);
    // 撤销的凭证号
    String cancelcredenceid = info[1] + "";
    // 凭证号生成日期
    String yearMonth = info[2] + "";
    // 办事处
    String officeCode = info[3] + "";
    // 撤销凭证号
    String officeId = "";
    String loanDocNumDocument = collParaDAO.getLoanDocNumDesignPara();
    if (loanDocNumDocument.equals("1")) {
      officeId = officeCode;
    } else {
      officeId = info[4] + "";
    }
    // plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid, officeId,
    // yearMonth.substring(0, 6));
    System.out.println(cancelcredenceid);
    plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid.substring(8, 12),
        cancelcredenceid.substring(7, 8), cancelcredenceid.substring(0, 4));
    // 删除尾表内容
    loanFlowTailDAO.deleteLoanFlowTailAll(flowHeadId);
    // 删除头表内容
    loanFlowHeadDAO.deleteLoanFlowHead(flowHeadId);
    // 删除PL020
    plBizActiveLogDAO.deletePlBizActiveLogByFlowHeadId_FYF(flowHeadId, "12");

    // 插入PL021
    PlOperateLog plOperateLog = new PlOperateLog();
    plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
    plOperateLog
        .setOpModel(String
            .valueOf(BusiLogConst.PL_OP_MODE_ACCOUNTMANAGE_BUSSINESSCENSOR_MAINTAIN));
    plOperateLog
        .setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_CONFIRM));
    plOperateLog.setOpBizId(new BigDecimal(flowHeadId));
    plOperateLog.setContractId(flowHeadId);
    plOperateLog.setOpIp(securityInfo.getUserIp());
    plOperateLog.setOpTime(new Date());
    plOperateLog.setOperator(securityInfo.getUserName());

    plOperateLogDAO.insert(plOperateLog);

  }

  public AdjustAccountTbListDTO findPrintInfo(String flowHeadId)
      throws Exception {

    AdjustAccountTbListDTO adjustAccountTbListDTO = loanFlowHeadDAO
        .queryAdjustAccountPrint(flowHeadId);

    // 转换业务类型
    adjustAccountTbListDTO.setBizType(BusiTools.getBusiValue(Integer
        .parseInt(adjustAccountTbListDTO.getBizType()),
        BusiConst.PLBUSINESSTYPE));

    return adjustAccountTbListDTO;
  }

  public Object[] findCorpusAndInterest(String yearMonth, String loanKouAcc,
      int plLoanReturnType) throws Exception, BusinessException {
    Object[] obj = null;
    if (plLoanReturnType == 1) {
      // 如果是中心为主，去查询pl201
      obj = restoreLoanDAO.queryCorpusAndInterest(yearMonth, loanKouAcc);
    } else {
      // 否则查询pl203
      obj = restoreLoanDAO.queryCorpusAndInterest_bank(yearMonth, loanKouAcc);
    }

    if (obj == null) {
      throw new BusinessException("还款年月填写错误!");
    }
    return obj;
  }

  public LoancallbackTaAF findPrintCallbackInfo(String headId) throws Exception {
    LoancallbackTaAF af = new LoancallbackTaAF();
    LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(headId));
    List taillist = loanFlowTailDAO.queryPrintLoanFlowTailByHeadId_FYF(headId);
    String contractId = "";
    BorrowerInfoDTO borrowerInfoDTO = null;
    String yearMonth = "";
    List borrowerList = null;
    if (!taillist.isEmpty()) {
      ShouldBackListDTO dto = (ShouldBackListDTO) taillist.get(0);
      ShouldBackListDTO dto1 = (ShouldBackListDTO) taillist
          .get(taillist.size() - 1);
      contractId = dto.getContractId();
      yearMonth = dto1.getLoanKouYearmonth();
      // 从PL110及PL111取信息
      borrowerList = borrowerAccDAO
          .queryBorrowerAccInfoByLoanKouAcc_LJ(contractId);
      if (!borrowerList.isEmpty()) {
        borrowerInfoDTO = (BorrowerInfoDTO) borrowerList.get(0);
      }
    }
    String loanBankId = loanFlowHead.getLoanBankId().toString();
    CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId);
    List acclist = loanBankDAO.queryLoanBankAccByBankId_LJ(loanBankId);
    Iterator it = acclist.iterator();
    Object obj[] = null;
    while (it.hasNext()) {
      obj = (Object[]) it.next();
      if (obj[0] != null) {
        af.setLoanAcc(obj[0].toString());
      }
      if (obj[1] != null) {
        af.setInterestAcc(obj[1].toString());
      }
    }
    af.setRealCorpus(loanFlowHead.getRealCorpus());
    af.setRealOverduCorpus(loanFlowHead.getRealOverdueCorpus());
    af.setSumCorpus(loanFlowHead.getRealCorpus().add(
        loanFlowHead.getRealOverdueCorpus()));
    af.setSumInterest(loanFlowHead.getRealInterest().add(
        loanFlowHead.getRealOverdueInterest()).add(
        loanFlowHead.getRealPunishInterest()));
    af.setOverOccurMoney(loanFlowHead.getOccurMoney());
    af.setInterest(loanFlowHead.getRealInterest());
    af.setOverdueInterest(loanFlowHead.getRealOverdueInterest());
    af.setPunishInterest(loanFlowHead.getRealPunishInterest());
    af.setMakeOP(securityDAO.queryByUserid(loanFlowHead.getMakePerson()));
    af.setClearOP(loanFlowHead.getCheckPerson());
    af.setBankName(collBank.getCollBankName());
    af.setShouldBackList(taillist);
    String bizType = loanFlowHead.getBizType();
    if (bizType.equals(String.valueOf(BusiConst.PLBUSINESSTYPE_SINGLERECOVER))) {
      af.setBizType("正常还款，");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setMonthYear(yearMonth.substring(0, 4) + "年"
          + yearMonth.substring(4, 6) + "月");
    } else if (bizType.equals(String
        .valueOf(BusiConst.PLBUSINESSTYPE_PARTRECOVER))) {
      af.setBizType("部份提前还款，");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setMonthYear(yearMonth.substring(0, 4) + "年"
          + yearMonth.substring(4, 6) + "月");
    } else if (bizType
        .equals(String.valueOf(BusiConst.PLBUSINESSTYPE_ALLCLEAR))) {
      af.setBizType("一次性清还，");
      af.setBorrowerInfoDTO(borrowerInfoDTO);
      af.setMonthYear(yearMonth.substring(0, 4) + "年"
          + yearMonth.substring(4, 6) + "月");
    } else if (bizType.equals(String
        .valueOf(BusiConst.PLBUSINESSTYPE_SOMERECOVER))) {
      af.setBizType("批量回收，");
      af.setMonthYear(loanFlowHead.getBizDate().substring(0, 4) + "年"
          + loanFlowHead.getBizDate().substring(4, 6) + "月");
    } else {
      af.setBorrowerInfoDTO(borrowerInfoDTO);
    }
    // 得到错帐调整类型
    String wrongBizType = loanFlowHead.getWrongBizType();
    if (wrongBizType.equals("6") || wrongBizType.equals("7")) {
      af.setRealMoney(af.getSumCorpus().add(af.getSumInterest()));
    } else {
      af.setRealMoney(af.getSumCorpus().add(af.getSumInterest()).add(
          af.getOverOccurMoney()));
    }
    af.setMonths(taillist.size() + "");
    String bizDate = loanFlowHead.getBizDate();
    af.setBizDate(bizDate.substring(0, 4) + "-" + bizDate.substring(4, 6) + "-"
        + bizDate.substring(6, 8));
    af.setDocNum(loanFlowHead.getDocNum());
    af.setNoteNum(loanFlowHead.getNoteNum());
    return af;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
    this.restoreLoanDAO = restoreLoanDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }

}
