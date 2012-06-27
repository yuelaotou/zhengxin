package org.xpup.hafmis.sysloan.specialbiz.bailpickup.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlBizActiveLog;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.bsinterface.IBailpickupBS;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.form.BailpickupTaAF;


/**
 * @author yuqf 20007-10-13
 */
public class BailpickupBS implements IBailpickupBS {

  private BorrowerAccDAO borrowerAccDAO = null;// PL111 借款人账户表

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// pl202 贷款流水帐头表

  private PlDocNumMaintainDAO plDocNumMaintainDAO = null;

  private PlDocNumCancelDAO plDocNumCancelDAO = null;

  private LoanFlowTailDAO loanFlowTailDAO = null;// pl203

  private PlBizActiveLogDAO plBizActiveLogDAO = null;// pl020

  private PlOperateLogDAO plOperateLogDAO = null;// pl021

  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }

  public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
    this.plOperateLogDAO = plOperateLogDAO;
  }

  public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
    this.plBizActiveLogDAO = plBizActiveLogDAO;
  }

  public void setLoanFlowTailDAO(LoanFlowTailDAO loanFlowTailDAO) {
    this.loanFlowTailDAO = loanFlowTailDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }

  /**
   * 根据会计日期计算年度结息日 2007-10-15
   * 
   * @param securityInfo
   * @return 算法来源--王菱 方法整理--yuqf
   */
  public String accountantyear(SecurityInfo securityInfo) {
    String accountantyear = null;
     String bizDate = securityInfo.getUserInfo().getPlbizDate();//个贷系统会计日期
//    String bizDate = securityInfo.getUserInfo().getBizDate();// 归集系统会计日期
    String bizYear = bizDate.substring(0, 4);
    String bizMonth = bizDate.substring(4, 6);
    if (bizMonth.compareTo("07") >= 0 && bizMonth.compareTo("12") <= 0) {
      accountantyear = "" + (Integer.parseInt(bizYear) + 1) + "0630";
    } else {
      accountantyear = bizYear+"0630";
    }
    return accountantyear;
  }

  /**
   * yuqf 2007-10-15 yyyymmdd to yyyy-mm-dd
   * 
   * @param predate
   * @return
   */
  public String yyyymmddTOyyyy_mm_dd(String predate) {
    String date = "";
    if (predate != null && !"".equals(predate)) {
      date = predate.substring(0, 4) + "-" + predate.substring(4, 6) + "-"
          + predate.substring(6, 8);
    }
    return date;
  }

  /**
   * AJAX 查询 通过贷款账号查询出提取保证金信息 贷款账号，合同编号，借款人姓名，证件号码，保证金余额，本年基数，往年基数，贷款余额,呆账未回收金额
   * 2007-10-13
   */
  public BailpickupTaDTO ajaxQuery(String loanKouAcc, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BailpickupTaDTO bailpickupTaDTO = new BailpickupTaDTO();
    BigDecimal curIntegral = new BigDecimal(0.00);// 本年基数
    BigDecimal preIntegral = new BigDecimal(0.00);// 往年基数
    BigDecimal bailBalance = new BigDecimal(0.00);// 保证金余额
//    BigDecimal interest = new BigDecimal(0.00);// 提取利息
    BigDecimal paramExplainInterestD = new BigDecimal(0.00);// 定期利息
    BigDecimal paramExplainInterestL = new BigDecimal(0.00);// 活期利息
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 会计日期
    if("0630".equals(bizDate.substring(4, 8))){//如果会计日期为“0630”不能提取
      throw new BusinessException("会计日期为6月30日，不能提取!");
    }
    String accountantyear = this.accountantyear(securityInfo);// 年度结息日
    String contractId = "";//合同编号
    String bankId = "";
    // 年度结息日－会计日期
    int tempDday = BusiTools.minusDate(this
        .yyyymmddTOyyyy_mm_dd(accountantyear), this
        .yyyymmddTOyyyy_mm_dd(bizDate));
    try {
      Object[] obj = new Object[10];
      obj = borrowerAccDAO.queryObjectByLoanKouAccYU(loanKouAcc);
      if (obj[0] != null) {
        bailpickupTaDTO.setLoanKouAcc(obj[0].toString());// 贷款账号
      }
      if (obj[1] != null) {
        bailpickupTaDTO.setContractId(obj[1].toString());// 合同编号
        contractId = obj[1].toString();
        BorrowerAcc borrowerAcc = null;
        borrowerAcc = borrowerAccDAO.queryById(contractId);
        bankId = borrowerAcc.getLoanBankId().toString();
        List list = new ArrayList();
        list = loanFlowHeadDAO.queryBizTypeByContractIdYU(obj[1].toString());
        if(list.size()!=0){// 判读PL202是否存在未记帐业务 
          //不能提取
          bailpickupTaDTO.setType("0");
        }
      }
      if (obj[2] != null) {
        bailpickupTaDTO.setBorrowerName(obj[2].toString());// 借款人姓名s
      }
      if (obj[3] != null) {
        bailpickupTaDTO.setCardNum(obj[3].toString());// 证件号码
      }
      if (obj[4] != null) {
        bailpickupTaDTO.setBailBalance(obj[4].toString());// 保证金余额
        bailBalance = new BigDecimal(obj[4].toString());
      }
      if (obj[5] != null) {
        curIntegral = new BigDecimal(obj[5].toString());// 本年基数
      }
      if (obj[6] != null) {
        preIntegral = new BigDecimal(obj[6].toString());// 往年基数
      }
      if (obj[7] != null) {
        bailpickupTaDTO.setOverplusLoanMoney(obj[7].toString());// 贷款余额
      }
      if (obj[8] != null) {
        bailpickupTaDTO.setNoBackMoney(obj[8].toString());// 呆账未回收金额
      }
      if (obj[9] != null) {
        bailpickupTaDTO.setOvaerLoanRepay(obj[9].toString());// 挂账余额
      }
      BigDecimal b1 = null;
      BigDecimal b2 = null;
      BigDecimal b3 = null;
      BigDecimal b4 = null;
      // 计算提取利息
      if (!"0".equals(curIntegral.toString())) {// 如果本年基数不＝0，利息＝（本年基数－保证金余额×（年度结息日－会计日期））×活期利息/365
        // 活期利息
        String tempInterestL = "";
        tempInterestL = loanFlowHeadDAO
        .queryParamexplainYU_(loanKouAcc);
        if(tempInterestL!=null){
        paramExplainInterestL = new BigDecimal(tempInterestL);
        }
        BigDecimal tempDdate = new BigDecimal(new Integer(tempDday).toString());// 年度结息日－会计日期
        if (tempDdate != null) {
          b1 = bailBalance.multiply(tempDdate);
        }
        if (b1 != null) {
          b2 = curIntegral.subtract(b1);
        }
        if (b2 != null) {
          b3 = b2.multiply(paramExplainInterestL);
        }
        if (b3 != null) {
          b4 = b3.divide(new BigDecimal(365), 2);
        }
        bailpickupTaDTO.setPickUpInterest(b4.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
//        bailpickupTaDTO.setPickUpInterest((b4.divide(tempBigDecimal, 2))
//            .toString());
        // interest = ((curIntegral.subtract(bailBalance.multiply(new
        // BigDecimal(new
        // Integer(tempDday).toString())))).multiply(paramExplainInterestL)).divide(new
        // BigDecimal(365), 5);
        // bailpickupTaDTO.setPickUpInterest(interest.toString());
      }
      if (!"0".equals(preIntegral.toString())) {// 如果往年基数不＝0，利息＝（往年基数－保证金余额×（年度结息日－会计日期））×定期利息/365
        // 定期利息
        String tempInterestD ="";
        tempInterestD = loanFlowHeadDAO
        .queryParamexplainYU(bankId);
        if(tempInterestD!=null){
        paramExplainInterestD = new BigDecimal(tempInterestD);
        }
        BigDecimal tempDdate = new BigDecimal(new Integer(tempDday).toString());// 年度结息日－会计日期
        if (tempDdate != null) {
          b1 = bailBalance.multiply(tempDdate);
        }
        if (b1 != null) {
          // b2 = curIntegral.subtract(b1);//本年基数
          b2 = preIntegral.subtract(b1);// 往年基数
        }
        if (b2 != null) {
          b3 = b2.multiply(paramExplainInterestD);
        }
        if (b3 != null) {
          b4 = b3.divide(new BigDecimal(365),2);
        }
        bailpickupTaDTO.setPickUpInterest(b4.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        // interest = ((preIntegral.subtract(bailBalance.multiply(new
        // BigDecimal(new
        // Integer(tempDday).toString())))).multiply(paramExplainInterestL)).divide(new
        // BigDecimal(365), 5);
        // bailpickupTaDTO.setPickUpInterest(interest.toString());
      }
      // 提取总金额 ＝ 保证金余额＋提取利息
      BigDecimal pickSumMoney = new BigDecimal(0.00);
      if (bailBalance != null && b4 != null) {
        pickSumMoney = bailBalance.add(b4).setScale(2, BigDecimal.ROUND_HALF_UP);
        bailpickupTaDTO.setPickSumMoney(pickSumMoney.toString());
      } else if (bailBalance == null && b4 != null) {
        bailpickupTaDTO.setPickSumMoney(b4.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
      } else if (bailBalance != null && b4 == null) {
        bailpickupTaDTO.setPickSumMoney(bailBalance.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
      }
    } catch (Exception e) {
      throw e;
    }
    return bailpickupTaDTO;
  }
  
  public BailpickupTaDTO ajaxQuery_( String loanKouAcc, Pagination pagination,
      SecurityInfo securityInfo, String flowHeadId) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BailpickupTaDTO bailpickupTaDTO = new BailpickupTaDTO();
    BigDecimal bailBalance = new BigDecimal(0.00);// 保证金余额
    BigDecimal bailBalance_ = new BigDecimal(0.00);
//    BigDecimal interest = new BigDecimal(0.00);// 提取利息
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 会计日期
    if("0630".equals(bizDate.substring(4, 8))){//如果会计日期为“0630”不能提取
      throw new BusinessException("会计日期为6月30日，不能提取!");
    }
   
    try {
      Object[] obj = new Object[10];
      Object[] obj_ = new Object[3];
      obj = borrowerAccDAO.queryObjectByLoanKouAccYU(loanKouAcc);
      if (obj[0] != null) {
        bailpickupTaDTO.setLoanKouAcc(obj[0].toString());// 贷款账号
      }
      if (obj[1] != null) {
        bailpickupTaDTO.setContractId(obj[1].toString());// 合同编号
        List list = new ArrayList();
        list = loanFlowHeadDAO.queryBizTypeByContractIdYU(obj[1].toString());
        if(list.size()!=0){// 判读PL202是否存在未记帐业务 
          //不能提取
          bailpickupTaDTO.setType("0");
        }
      }
      if (obj[2] != null) {
        bailpickupTaDTO.setBorrowerName(obj[2].toString());// 借款人姓名s
      }
      if (obj[3] != null) {
        bailpickupTaDTO.setCardNum(obj[3].toString());// 证件号码
      }
      if (obj[4] != null) {
        bailpickupTaDTO.setBailBalance(obj[4].toString());// 保证金余额
        bailBalance = new BigDecimal(obj[4].toString());
      }
      if (obj[7] != null) {
        bailpickupTaDTO.setOverplusLoanMoney(obj[7].toString());// 贷款余额
      }
      if (obj[8] != null) {
        bailpickupTaDTO.setNoBackMoney(obj[8].toString());// 呆账未回收金额
      }
      if (obj[9] != null) {
        bailpickupTaDTO.setOvaerLoanRepay(obj[9].toString());// 挂账余额
      }
      
      obj_ = borrowerAccDAO.queryObjectByLoanKouAccYU_(loanKouAcc, flowHeadId);
      if (obj_[0] != null) {
        bailpickupTaDTO.setBailBalance(obj_[0].toString());// 保证金余额
        bailBalance = new BigDecimal(obj_[0].toString());
      }
      if (obj_[1] != null) {
        bailpickupTaDTO.setPickUpInterest(obj_[1].toString());// 提取利息
        bailBalance_ = new BigDecimal(obj_[1].toString());
      }
      bailBalance = bailBalance.add(bailBalance_).abs();
      // 提取总金额 ＝ 保证金余额＋提取利息
      bailpickupTaDTO.setPickSumMoney(bailBalance.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    } catch (Exception e) {
      throw e;
    }
    return bailpickupTaDTO;
  }

  /**
   * 确定按钮
   */
  public String save(BailpickupTaDTO bailpickupTaDTO, SecurityInfo securityInfo)
      throws Exception, BusinessException {
    // TODO Auto-generated method stub

    String contractId = bailpickupTaDTO.getContractId();// 合同编号
    String bailBalance = bailpickupTaDTO.getBailBalance();// 保证金
//    String overplusLoanMoney = bailpickupTaDTO.getOverplusLoanMoney();// 贷款余额
//    String noBackMoney = bailpickupTaDTO.getNoBackMoney();// 呆账
//    String ovaerLoanRepay = bailpickupTaDTO.getOvaerLoanRepay();// 挂账金额
    String pickUpInterest = bailpickupTaDTO.getPickUpInterest();// 提取利息
    String loanKouAcc = bailpickupTaDTO.getLoanKouAcc();// 贷款账号

    String docNum = "";
    String bizDate = securityInfo.getUserInfo().getPlbizDate();// 归集业务日期
    // String bizDate = securityInfo.getUserInfo().getPlbizDate();//个贷业务日期
    String loanBankId = loanFlowHeadDAO.queryBankByContractIdYU(contractId);// 111
                                                                            // 下的
    String office = loanFlowHeadDAO.queryOfficeByBank(loanBankId);
    docNum = plDocNumMaintainDAO.getDocNumdocNum(office, bizDate
        .substring(0, 6));

    // 判读PL202是否存在未记帐业务
//    List list = new ArrayList();
//    list = loanFlowHeadDAO.queryBizTypeByContractIdYU(contractId);
//    if (list.size() != 0) {
//      throw new BusinessException("该贷款账号下存在未记账的保证金业务,不可以提取!");
//    }
    // 判读保证金是否为零
//    if (bailBalance.equals("0") || bailBalance == "") {
//      throw new BusinessException("该贷款账号下的保证金已经提取!");
//    }
    // 判断贷款余额,呆账,挂账金额是否等于零
//    if ("0".equals(overplusLoanMoney) || "".equals(overplusLoanMoney)) {
//      throw new BusinessException("该贷款账号下的贷款余额项金额为0，是否继续提取?");
//    }
//    if ("0".equals(noBackMoney) || "".equals(noBackMoney)) {
//      throw new BusinessException("该贷款账号下的呆账未收回金额项金额为0，是否继续提取?");
//    }
//    if ("0".equals(ovaerLoanRepay) || "".equals(ovaerLoanRepay)) {
//      throw new BusinessException("该贷款账号下的挂账余额项金额为0，是否继续提取?");
//    }
    String pl202id = this.addPl202(securityInfo, bailBalance, pickUpInterest,
        contractId, docNum);
    if (pl202id != null && !"".equals(pl202id)) {
      this.addPl203(pl202id, loanKouAcc, contractId, bailBalance,
          pickUpInterest);
      this.addPl020(pl202id, securityInfo);
      this.addPl021(pl202id, contractId, securityInfo);
    }
    return docNum;
  }

  /**
   * 插入PL202
   * 
   * @param securityInfo
   * @param bailBalance
   * @param pickUpInterest
   * @param contractId
   * @return
   */
  public String addPl202(SecurityInfo securityInfo, String bailBalance,
      String pickUpInterest, String contractId, String docNum) {
    String pl202Id = "";
    try {
//      String bizDate = securityInfo.getUserInfo().getBizDate();// 归集业务日期
       String bizDate = securityInfo.getUserInfo().getPlbizDate();//个贷业务日期
      String operateName = securityInfo.getUserInfo().getUsername();// 操作员
      LoanFlowHead loanFlowHead = new LoanFlowHead();
      String loanBankId = loanFlowHeadDAO.queryBankByContractIdYU(contractId);// 111
                                                                              // 下的
//      String office = loanFlowHeadDAO.queryOfficeByBank(loanBankId);
      loanFlowHead.setBizDate(bizDate);
      loanFlowHead.setBizType(new Integer(BusiConst.PLBUSINESSTYPE_MARGIN)
          .toString());
      loanFlowHead.setRealCount(new BigDecimal(1));// 实还人数
      if(bailBalance!=null && !"".equals(bailBalance)){
        loanFlowHead.setOccurMoney(new BigDecimal("-"+bailBalance));
      }
      if(pickUpInterest!=null && !"".equals(pickUpInterest)){
        loanFlowHead.setOtherInterest(new BigDecimal(pickUpInterest));
      }
      loanFlowHead.setDocNum(docNum);
      loanFlowHead.setBizSt(new Integer(BusiConst.BUSINESSSTATE_SURE)
          .toString());
      loanFlowHead.setMakePerson(operateName);
      if(loanBankId!=null && !"".equals(loanBankId)){
        loanFlowHead.setLoanBankId(new BigDecimal(loanBankId));
      }
      loanFlowHead.setIsFinance(new Integer(1));
      pl202Id = loanFlowHeadDAO.insert(loanFlowHead).toString();
      //更新票据号
      loanFlowHead.setNoteNum(pl202Id);
      // 修改记录：向头表插入票据号   2007-11-16 wangy
      // loanFlowHeadDAO.update(loanFlowHead);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pl202Id;
  }

  /**
   * 插入PL203
   * 
   * @param flowHeadId
   * @param loanKouAcc
   * @param contractId
   * @param bailBalance
   * @param pickUpInterest
   * @return
   */
  public String addPl203(String flowHeadId, String loanKouAcc,
      String contractId, String bailBalance, String pickUpInterest) {
    String pl203Id = "";
    try {
      LoanFlowTail loanFlowTail = new LoanFlowTail();
      loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId));
      loanFlowTail.setLoanKouAcc(loanKouAcc);
      loanFlowTail.setContractId(contractId);
      loanFlowTail.setOccurMoney(new BigDecimal("-"+bailBalance));
      loanFlowTail.setOtherInterest(new BigDecimal(pickUpInterest));
      loanFlowTailDAO.insert(loanFlowTail);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pl203Id;
  }

  /**
   * 插入PL020
   * 
   * @param flowHeadId
   * @param securityInfo
   * @return
   */
  public String addPl020(String flowHeadId, SecurityInfo securityInfo) {
    String pl020Id = "";
    try {
      PlBizActiveLog plBizActiveLog = new PlBizActiveLog();
      String operator = securityInfo.getUserInfo().getUsername();// 操作员
      plBizActiveLog.setBizid(new BigDecimal(flowHeadId));
      plBizActiveLog.setAction(BusiConst.BUSINESSSTATE_SURE + "");
      plBizActiveLog.setOpTime(new Date());
      plBizActiveLog.setOperator(operator);
      plBizActiveLog.setType(BusiConst.PLBUSINESSTYPE_MARGIN + "");
      plBizActiveLogDAO.insert(plBizActiveLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pl020Id;
  }

  public String addPl021(String flowHeadId, String contractId,
      SecurityInfo securityInfo) {
    String pl021Id = "";
    try {
      PlOperateLog plOperateLog = new PlOperateLog();
      String operator = securityInfo.getUserInfo().getUsername();// 操作员
      String opIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
      plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
      plOperateLog
          .setOpModel(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_DO + "");
      plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_CONFIRM + "");
      plOperateLog.setOpBizId(new BigDecimal(flowHeadId));
      plOperateLog.setOpIp(opIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operator);
      plOperateLogDAO.insert(plOperateLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pl021Id;
  }

  /**
   * 提取维护查询
   */
  public BailpickupTbDTO tbQuery(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BailpickupTbDTO bailpickupTbDTO = new BailpickupTbDTO();
    List list = new ArrayList();
    List bankList = securityInfo.getCollBankList();
    Map statusmap = new HashMap();
    int count = 0;
    statusmap = BusiTools.listBusiProperty(BusiConst.PLBUSINESSSTATE);
    statusmap.remove(new Integer(BusiConst.PLBUSINESSSTATE_EXP));
    statusmap.remove(new Integer(BusiConst.PLBUSINESSSTATE_IMP));
    statusmap.remove(new Integer(BusiConst.PLBUSINESSSTATE_SIGN));
    bailpickupTbDTO.setBankList(bankList);
    bailpickupTbDTO.setMap(statusmap);
//    String sumOccurMoney = "";// 合计提取保证金金额
//    String sumOtherInterest = "";// 合计提取利息
//    String sumPickupMoney = "";// 合计提取金额

    HashMap map = (HashMap) pagination.getQueryCriterions();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    int page = pagination.getPage();
    Object[] obj = new Object[3];
    if (map.size() != 0) {
      // 有条件查询
      String loanKouAcc = (String) map.get("loanKouAcc");// 贷款账号
      String contractId = (String) map.get("contractId");// 合同编号
      String borrowerName = (String) map.get("borrowerName");// 借款人姓名
      String cardNum = (String) map.get("cardNum");// 证件号码
      String loanBank = (String) map.get("loanBank");// 放款银行
      String bizStatus = (String) map.get("bizStatus");// 业务状态
      list = loanFlowTailDAO.bailpickupTbdefaltQueryByCondition(loanKouAcc,
          contractId, borrowerName, cardNum, loanBank, bizStatus, orderBy,
          order, start, pageSize, page, securityInfo);
      count = loanFlowTailDAO.bailpickupTbdefaltQueryByConditionCount(
          loanKouAcc, contractId, borrowerName, cardNum, loanBank, bizStatus,
          orderBy, order, start, pageSize, page, securityInfo);
      obj = loanFlowTailDAO.querySumYU(loanKouAcc, contractId, borrowerName,
          cardNum, loanBank, bizStatus, orderBy, order, start, pageSize, page,
          securityInfo);
      if (obj[0] != null) {
        bailpickupTbDTO.setSumOccurMoney(obj[0].toString());
      }
      if (obj[1] != null) {
        bailpickupTbDTO.setSumOtherInterest(obj[1].toString());
      }
      if (obj[2] != null) {
        bailpickupTbDTO.setSumPickupMoney(obj[2].toString());
      }

    } else {
      // 默认无条件查询
      list = loanFlowTailDAO.bailpickupTbdefaltQuery(orderBy, order, start,
          pageSize, page, securityInfo);
      count = loanFlowTailDAO.bailpickupTbdefaltQueryCount(orderBy, order,
          start, pageSize, page, securityInfo);
      obj = loanFlowTailDAO.querySumYU_(orderBy, order, start, pageSize, page,
          securityInfo);
      if (obj[0] != null) {
        bailpickupTbDTO.setSumOccurMoney(obj[0].toString());
      }
      if (obj[1] != null) {
        bailpickupTbDTO.setSumOtherInterest(obj[1].toString());
      }
      if (obj[2] != null) {
        bailpickupTbDTO.setSumPickupMoney(obj[2].toString());
      }
    }
    pagination.setNrOfElements(count);
    bailpickupTbDTO.setList(list);
    return bailpickupTbDTO;
  }

  /**
   * 提取维护删除
   */
  public void tbDelete(String flowHeadId, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String bizSt = loanFlowHeadDAO.queryBizStById(flowHeadId, securityInfo);
    if (bizSt != null && !"".equals(bizSt)) {
      if ("4".equals(bizSt)) {// 业务为确认状态，可以删除
        String docNum = loanFlowHeadDAO.queryDocNumById(flowHeadId, securityInfo);
        String contractId = loanFlowTailDAO
            .queryContractByHeadId_YU(flowHeadId);
        String yearMonth = securityInfo.getUserInfo().getPlbizDate().substring(0, 6);// 归集业务日期
        // String bizDate = securityInfo.getUserInfo().getPlbizDate();//个贷业务日期
        String loanBankId = loanFlowHeadDAO.queryBankByContractIdYU(contractId);// 111                                                                             // 下的
        String officeCode = loanFlowHeadDAO.queryOfficeByBank(loanBankId);
        // 删除尾表PL203：deleteFlowTailByHeadId_YU
        loanFlowTailDAO.deleteFlowTailByHeadId_YU(flowHeadId);
        // 删除头表PL202
        LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(
            flowHeadId));
        loanFlowHeadDAO.delete(loanFlowHead);
        // 删除业务活动日至PL020
        plBizActiveLogDAO.deletePlBizActiveLog_LJ(flowHeadId);
        //插入凭证号作废表
        plDocNumCancelDAO.insertPlDocNumCancel(docNum, officeCode, yearMonth);
        // 插入操作日志
        PlOperateLog plOperateLog = new PlOperateLog();
        String operator = securityInfo.getUserInfo().getUsername();// 操作员
        String opIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
        plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));
        plOperateLog
            .setOpModel(BusiLogConst.PL_OP_SPECIALBUSS_BONDREGIST_MAINTAIN + "");
        plOperateLog.setOpButton(BusiLogConst.BIZLOG_ACTION_DELETE + "");
        plOperateLog.setOpBizId(new BigDecimal(flowHeadId));
        plOperateLog.setOpIp(opIp);
        plOperateLog.setContractId(contractId);
        plOperateLog.setOpTime(new Date());
        plOperateLog.setOperator(operator);
        plOperateLogDAO.insert(plOperateLog);
      } else {
        throw new BusinessException("该笔业务的状态不是确认，不可以删除!");
      }
    }
  }

  /*****************************************************************************
   * 打印查询
   */
  public BailpickupTaAF tbPrintQuery(String flowHeadId, Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    BailpickupTaAF bailpickupTaAF = new BailpickupTaAF();
    LoanFlowHead loanFlowHead = new LoanFlowHead();
    loanFlowHead = loanFlowHeadDAO.queryById(new Integer(flowHeadId));
    String loan_kou_acc = loanFlowTailDAO.queryContractId(flowHeadId);// 贷款账号
    BailpickupTaDTO bailpickupTaDTO = new BailpickupTaDTO();
    String bizSt = loanFlowHeadDAO.queryBizStById(flowHeadId, securityInfo);
    if(bizSt.equals("4")){
      bailpickupTaDTO = this.ajaxQuery(loan_kou_acc, pagination,
          securityInfo);
    }else{
      bailpickupTaDTO = this.ajaxQuery_(loan_kou_acc, pagination,
          securityInfo,flowHeadId);
    }
    
    String loanKouAcc = bailpickupTaDTO.getLoanKouAcc();// 贷款账号
    String contractId = bailpickupTaDTO.getContractId();// 合同编号
    String borrowerName = bailpickupTaDTO.getBorrowerName();// 借款人姓名
    String cardNum = bailpickupTaDTO.getCardNum();// 证件号码
    String bailBalance = bailpickupTaDTO.getBailBalance();// 保证金余额
    String pickUpInterest = bailpickupTaDTO.getPickUpInterest();// 提取利息
    String pickSumMoney = bailpickupTaDTO.getPickSumMoney();// 提取总金额
    String overplusLoanMoney = bailpickupTaDTO.getOverplusLoanMoney();// 贷款余额
    String noBackMoney = bailpickupTaDTO.getNoBackMoney();// 呆账未收回金额
    String ovaerLoanRepay = bailpickupTaDTO.getOvaerLoanRepay();// 挂账余额
    String docNum = loanFlowHead.getDocNum();// 凭证号
    bailpickupTaAF.setLoanKouAcc(loanKouAcc);
    bailpickupTaAF.setContractId(contractId);
    bailpickupTaAF.setCardNum(cardNum);
    bailpickupTaAF.setBailBalance(bailBalance);
    bailpickupTaAF.setCardNum(cardNum);
    bailpickupTaAF.setPickUpInterest(pickUpInterest);
    bailpickupTaAF.setOverplusLoanMoney(overplusLoanMoney);
    bailpickupTaAF.setNoBackMoney(noBackMoney);
    bailpickupTaAF.setBorrowerName(borrowerName);
    bailpickupTaAF.setPickSumMoney(pickSumMoney);
    bailpickupTaAF.setDocNum(docNum);
    bailpickupTaAF.setOvaerLoanRepay(ovaerLoanRepay);
    return bailpickupTaAF;
  }

}
