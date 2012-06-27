package org.xpup.hafmis.sysloan.specialbiz.bailenrol.business;

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
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.bsinterface.IBailenRolBS;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTaPrintDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.dto.BailenRolTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTaAF;
import org.xpup.hafmis.sysloan.specialbiz.bailenrol.form.BailenRolTbAF;

/**
 * @author 王野 2007-10-02
 */
public class BailenRolBS implements IBailenRolBS {

  private BorrowerDAO borrowerDAO = null;// 借款人信息表 PL110

  private BorrowerAccDAO borrowerAccDAO = null;// 借款人账户表 PL111

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// 流水头表 PL202

  private LoanFlowTailDAO loanFlowTailDAO = null;// 流水尾表 PL203

  private PlBizActiveLogDAO plBizActiveLogDAO = null;// 业务活动日志 PL020

  private PlOperateLogDAO plOperateLogDAO = null;// 操作日志 PL021

  private CollBankDAO collBankDAO = null;// 转换银行名称

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private LoanBankDAO loanBankDAO = null;

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  /**
   * Description 保证金登记办理
   * 
   * @author wangy 2007-10-02
   * @param 通过合同编号带出页面信息
   * @param 由BailenRolTaFindAAC调用
   * @throws Exception, BusinessException
   */
  public BailenRolTaAF queryContractInfo(String id, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BailenRolTaAF bailenRolTaAF = new BailenRolTaAF();
    boolean isContractId = false;
    try {
      isContractId = borrowerAccDAO.isExistsBorrowerAccByContractId(id,
          securityInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (isContractId) {
      List bailenRolTalist = borrowerAccDAO.queryBailenRolInfoByContractId(id,
          securityInfo);
      if (bailenRolTalist.size() > 0) {
        Iterator iterate = bailenRolTalist.iterator();
        Object[] obj = null;
        while (iterate.hasNext()) {
          obj = (Object[]) iterate.next();
          if (obj[0] != null && !obj[0].equals(""))
            bailenRolTaAF.setContractId(obj[0].toString());
          if (obj[1] != null && !obj[1].equals(""))
            bailenRolTaAF.setBorrowerName(obj[1].toString());
          String cardKind = null;
          if (obj[2] != null && !obj[2].equals(""))
            cardKind = obj[2].toString();
          if (obj[3] != null && !obj[3].equals(""))
            bailenRolTaAF.setCardNum(obj[3].toString());
          if (obj[4] != null && !obj[4].equals("")) {
            bailenRolTaAF.setLoanBankName(obj[4].toString());
          }
          // 贷款账号-放到页面隐藏域中 2007-11-12修改
          if (obj[5] != null && !obj[5].equals(""))
            bailenRolTaAF.setLoanKouAccHidden(obj[5].toString());
          if (obj[6] != null && !obj[6].equals(""))
            bailenRolTaAF.setLoanKouAcc(obj[6].toString());
          // 枚举转换证件类型
          cardKind = BusiTools.getBusiValue(Integer.parseInt(cardKind),
              BusiConst.DOCUMENTSSTATE);
          bailenRolTaAF.setCardKind(cardKind);
        }
      }
    } else {
      throw new BusinessException("该合同编号不存在，请重新输入！");
    }
    return bailenRolTaAF;
  }

  /**
   * Description 保证金登记办理
   * 
   * @author wangy 2007-11-12
   * @param 通过银行ID连带放款银行账号
   * @param 由BailenRolTaFindBankAccAAC调用
   * @throws Exception
   */
  public String queryBailenRolTaBankAccByBankId(String bankId) throws Exception {
    String loanAcc = "";
    try {
      loanAcc = loanBankDAO.queryBailenRolTaBankAccByBankId(bankId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanAcc;
  }

  /**
   * Description 保证金登记办理
   * 
   * @author wangy 2007-10-02
   * @param 插入保证金登记相关信息（页面信息）
   * @param 由BailenRolTaSaveAC调用
   * @throws Exception, BusinessException
   */
  public BailenRolTaPrintDTO saveBailenRol(BailenRolTaDTO bailenRolTaDTO,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    BailenRolTaPrintDTO bailenRolTaPrintDTO = new BailenRolTaPrintDTO();
    String operateName = securityInfo.getUserInfo().getUsername();// 操作员
    String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
    String contractId = bailenRolTaDTO.getContractId();
    String borrowerName = bailenRolTaDTO.getBorrowerName();
    String cardKind = bailenRolTaDTO.getCardKind();
    String cardNum = bailenRolTaDTO.getCardNum();
    String loanBankName = bailenRolTaDTO.getLoanBankName();// 银行ID
    String loanBankIdHidden = bailenRolTaDTO.getLoanBankId();// 银行ID(隐藏域)
                                                              // 2007-11-12修改
    String loanKouAcc = bailenRolTaDTO.getLoanKouAcc();// 收款账号
    String loanKouAccHidden = bailenRolTaDTO.getLoanKouAccHidden();// 贷款账号
    String bizDate = bailenRolTaDTO.getBizDate();
    BigDecimal occurMoney = bailenRolTaDTO.getOccurMoney();// 保证金金额
    // 判断相同的贷款账号下是否存在未记账的保证金登记业务（BIZ_TYPE=14，BIZ_ST！=6）
    boolean flowHead = loanFlowTailDAO
        .isExistsLoanFlowHeadByLoanKouAcc(loanKouAccHidden);// 2007-11-12修改
    if (flowHead) {
      throw new BusinessException("该贷款账号下存在未记账的保证金业务，不可以再办理!");
    } else {
      // 修改记录：保证金登记时页面的放宽银行和放款银行帐号插入到PL110中的备选字段A和B 2007-11-12(经大家讨论后决定)
      // 更新借款人账户表PL110
      Borrower borrower = borrowerDAO.queryById(contractId);
      borrower.setReserveaA(loanBankName);
      borrower.setReserveaB(loanKouAcc);
      // 取凭证号
      CollBank collBank = collBankDAO
          .getCollBankByCollBankid_(loanBankIdHidden);// 2007-11-12修改
      String bizYearmonth = securityInfo.getUserInfo().getPlbizDate()
          .substring(0, 6);
      String docNum = plDocNumMaintainDAO.getDocNumdocNum(collBank.getOffice(),
          bizYearmonth);
      // 插入贷款流水账头表PL202
      LoanFlowHead loanFlowHead = new LoanFlowHead();
      loanFlowHead.setBizDate(bizDate);
      loanFlowHead.setBizType(new Integer(BusiConst.PLBUSINESSTYPE_MARGIN)
          .toString());// 业务类型 14（保证金）
      loanFlowHead.setRealCount(new BigDecimal(1));// 实还人数
      loanFlowHead.setOccurMoney(occurMoney);// 发生金额
      loanFlowHead.setDocNum(docNum);
      loanFlowHead.setBizSt(new Integer(BusiConst.BUSINESSSTATE_SURE)
          .toString());// 业务状态
      // 4（确认）
      loanFlowHead.setLoanBankId(new BigDecimal(loanBankIdHidden));// 2007-11-12修改
      loanFlowHead.setMakePerson(operateName);
      loanFlowHead.setIsFinance(new Integer(1));// PL202中的isFinance插1
      String flowHeadId = loanFlowHeadDAO.insert(loanFlowHead).toString();// 插入PL202
      // 并返回flow_head_id
      // 向流水头表插入票据号
      loanFlowHead.setNoteNum(flowHeadId);
      // 插入贷款流水账尾表PL203
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId));
      loanFlowTail.setLoanKouAcc(loanKouAccHidden);// 插入PL203贷款账号 2007-11-12修改
      loanFlowTail.setContractId(contractId);
      loanFlowTail.setOccurMoney(occurMoney);
      loanFlowTailDAO.insert(loanFlowTail);
      // 插入业务活动日志PL020
      PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
      plBizActiveLog.setBizid(new BigDecimal(flowHeadId));
      plBizActiveLog.setAction(new Integer(BusiConst.BUSINESSSTATE_SURE)
          .toString());// action 4
      plBizActiveLog.setOpTime(new Date());
      plBizActiveLog.setOperator(operateName);
      plBizActiveLog.setType(new Integer(BusiConst.PLBUSINESSTYPE_MARGIN)
          .toString());// type 14
      plBizActiveLogDAO.insert(plBizActiveLog);
      // 插入操作日志PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_DO).toString());// 保证金登记-业务办理
      // 73
      plOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_CONFIRM)
          .toString());// 确认 11
      plOperateLog.setOpBizId(new BigDecimal(flowHeadId));// PL202.FLOW_HEAD_ID
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
      // 获得打印凭证数据
      bailenRolTaPrintDTO.setContractId(contractId);
      bailenRolTaPrintDTO.setBorrowerName(borrowerName);
      bailenRolTaPrintDTO.setCardKind(cardKind);
      bailenRolTaPrintDTO.setCardNum(cardNum);
      bailenRolTaPrintDTO.setLoanBankName(loanBankName);
      bailenRolTaPrintDTO.setLoanKouAcc(loanKouAcc);
      bailenRolTaPrintDTO.setBizDate(bizDate);
      bailenRolTaPrintDTO.setOccurMoney(occurMoney);
      bailenRolTaPrintDTO.setDocNum(docNum);
      bailenRolTaPrintDTO.setNoteNum(flowHeadId);
    }
    return bailenRolTaPrintDTO;
  }

  /**
   * Description 保证金登记维护
   * 
   * @author wangy 2007-10-03
   * @param 根据条件查询保证金登记维护列表
   * @param 由BailenRolTbShowAC调用
   * @return BailenRolTbAF
   * @throws Exception, BusinessException
   */
  public BailenRolTbAF queryBailenRolListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
    BigDecimal occurTotleMoney = new BigDecimal(0.00);// 保证金金额-总额
    String findType = (String) pagination.getQueryCriterions().get("findType");
    if (findType == null || "".equals(findType)) {
      findType = "(4)";// 默认列表
    } else {
      findType = "(4, 5, 6)";// 查询
    }
    BailenRolTbAF bailenRolTbAF = new BailenRolTbAF();
    List bailerrolTbList = new ArrayList();
    List otherList = new ArrayList();
    List templist = new ArrayList();
    bailerrolTbList = loanFlowHeadDAO.queryBailenRolListByCriterions(
        contractId, borrowerName, cardNum, loanBankName, bizSt, findType,
        start, orderBy, order, pageSize, page, securityInfo);
    // count = loanFlowHeadDAO.queryBailenRolCountByCriterions(contractId,
    // borrowerName, cardNum, loanBankName, bizSt, findType, securityInfo);
    // occurTotleMoney = loanFlowHeadDAO.queryBailenRolSumMoneyByCriterions(
    // contractId, borrowerName, cardNum, loanBankName, bizSt, findType,
    // securityInfo);
    otherList = loanFlowHeadDAO.queryBailenRolSumMoneyByCriterions(contractId,
        borrowerName, cardNum, loanBankName, bizSt, findType, securityInfo);
    if (!otherList.isEmpty()) {
      BailenRolTbDTO dto = (BailenRolTbDTO) otherList.get(0);
      count = dto.getCount().intValue();
      occurTotleMoney = dto.getOccurMoneyTotal();

    }
    Iterator iterate = bailerrolTbList.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      BailenRolTbDTO bailenRolTbDTO = new BailenRolTbDTO();
      obj = (Object[]) iterate.next();
      if (obj[0] != null && !obj[0].equals(""))
        bailenRolTbDTO.setContractId(obj[0].toString());
      if (obj[1] != null && !obj[1].equals(""))
        bailenRolTbDTO.setBorrowerName(obj[1].toString());
      if (obj[2] != null && !obj[2].equals(""))
        bailenRolTbDTO.setCardNum(obj[2].toString());
      String loanBankId = null;
      if (obj[3] != null && !obj[3].equals(""))
        loanBankId = obj[3].toString();
      if (obj[4] != null && !obj[4].equals(""))
        bailenRolTbDTO.setBizDate(obj[4].toString());
      if (obj[5] != null && !obj[5].equals(""))
        bailenRolTbDTO.setOccurMoney(obj[5].toString());
      if (obj[6] != null && !obj[6].equals(""))
        bizSt = obj[6].toString();
      if (obj[7] != null && !obj[7].equals(""))
        bailenRolTbDTO.setFlowHeadId(obj[7].toString());
      // 转换银行名称
      try {
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
            .toString());
        bailenRolTbDTO.setLoanBankName(dto.getCollBankName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      // 枚举转换业务状态
      try {
        bailenRolTbDTO.setBizSt(BusiTools.getBusiValue(Integer.parseInt(""
            + bizSt), BusiConst.PLBUSINESSSTATE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      templist.add(bailenRolTbDTO);
    }
    pagination.setNrOfElements(count);
    if (occurTotleMoney != null && !occurTotleMoney.equals("")) {
      bailenRolTbAF.setOccurTotleMoney(occurTotleMoney);
    }
    bailenRolTbAF.setList(templist);
    bailenRolTbAF.setCount(new Integer(count));
    return bailenRolTbAF;
  }

  /**
   * Description 保证金登记维护
   * 
   * @author wangy 2007-10-04
   * @param 删除保证金登记维护列表
   * @param 由BailenRolTbMaintainAC调用
   * @param flowHeadId
   * @param contractId
   * @param securityInfo
   * @throws Exception, BusinessException
   */
  public void deleteBailenRolInfo(String flowHeadId, String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // try {
    String operateName = securityInfo.getUserInfo().getUsername();// 操作员
    String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
    // 通过贷款流水账头表ID查询业务状态
    String bizSt = loanFlowHeadDAO.queryBizStByFlowHeadId(flowHeadId);
    if (bizSt.equals("4")) {// 业务状态 4 确认
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
      plDocNumCancelDAO.insertPlDocNumCancel(cancelcredenceid, officeCode,
          yearMonth.substring(0, 6));
      // 删除尾表PL203
      loanFlowTailDAO.deleteLoanFlowTailAll(flowHeadId);
      // 删除头表PL202
      loanFlowHeadDAO.deleteLoanFlowHead(flowHeadId);
      // 删除业务活动日志PL020
      plBizActiveLogDAO.deletePlBizActiveLogByFlowHeadId_wy(flowHeadId);
      // 插入操作日志PL021
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// 个贷 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_MAINTAIN).toString());// 保证金登记-业务维护
      // 74
      plOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_DELETE)
          .toString());// 删除 3
      plOperateLog.setOpBizId(new BigDecimal(flowHeadId));// PL202.FLOW_HEAD_ID
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);// PL203 合同编号
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog);
    } else {
      throw new BusinessException("该笔业务的状态不是确认，不可以删除!");
    }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
  }

  /**
   * Description 保证金登记维护
   * 
   * @author wangy 2007-10-04
   * @param 打印保证金登记维护列表
   * @param 由BailenRolTbMaintainAC调用
   * @return List
   * @throws Exception
   */
  public List findBailenRolTbPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception {
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
    String findType = (String) pagination.getQueryCriterions().get("findType");
    if (findType == null || "".equals(findType)) {
      findType = "(4)";// 默认列表
    } else {
      findType = "(4, 5, 6)";// 查询
    }
    List printList = new ArrayList();
    List resultList = new ArrayList();
    // 查询保证金登记维护列表 打印
    printList = loanFlowHeadDAO.queryBailenRolPrintListByCriterions(securityInfo,contractId,
        borrowerName, cardNum, loanBankName, bizSt, findType);
    Iterator iterate = printList.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      BailenRolTbDTO bailenRolTbDTO = new BailenRolTbDTO();
      obj = (Object[]) iterate.next();
      if (obj[0] != null && !obj[0].equals(""))
        bailenRolTbDTO.setContractId(obj[0].toString());
      if (obj[1] != null && !obj[1].equals(""))
        bailenRolTbDTO.setBorrowerName(obj[1].toString());
      if (obj[2] != null && !obj[2].equals(""))
        bailenRolTbDTO.setCardNum(obj[2].toString());
      String loanBankId = null;
      if (obj[3] != null && !obj[3].equals(""))
        loanBankId = obj[3].toString();
      if (obj[4] != null && !obj[4].equals(""))
        bailenRolTbDTO.setBizDate(obj[4].toString());
      if (obj[5] != null && !obj[5].equals(""))
        bailenRolTbDTO.setOccurMoney(obj[5].toString());
      if (obj[6] != null && !obj[6].equals(""))
        bizSt = obj[6].toString();
      // 转换银行名称
      try {
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId
            .toString());
        bailenRolTbDTO.setLoanBankName(dto.getCollBankName());
      } catch (Exception e) {
        e.printStackTrace();
      }
      // 枚举转换业务状态
      try {
        bailenRolTbDTO.setBizSt(BusiTools.getBusiValue(Integer.parseInt(""
            + bizSt), BusiConst.PLBUSINESSSTATE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      resultList.add(bailenRolTbDTO);
    }
    return resultList;
  }

  /**
   * Description 保证金登记
   * 
   * @author wangy 2007-10-29
   * @param 转换银行名称
   * @param 由BailenRolTaSaveAC调用
   * @param loanBankId
   * @return String
   * @throws Exception
   */
  public String changeBank(String loanBankId) throws Exception {
    String loanBankName = "";
    try {
      if (loanBankId != null && !"".equals(loanBankId)) {
        CollBank dto = collBankDAO.getCollBankByCollBankid(loanBankId);
        loanBankName = dto.getCollBankName();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return loanBankName;
  }
}
