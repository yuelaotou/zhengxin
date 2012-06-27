package org.xpup.hafmis.sysloan.loancallback.destoryback.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.CollParaDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowTailDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumCancelDAO;
import org.xpup.hafmis.sysloan.common.dao.PlDocNumMaintainDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowHead;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanFlowTail;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.loancallback.destoryback.bsinterface.IDestoryBackBS;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTaDTO;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTbTotleDTO;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTaAF;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTbAF;


public class DestoryBackBS implements IDestoryBackBS {
  private BorrowerAccDAO borrowerAccDAO = null;// 借款人账户表 PL111

  private LoanFlowHeadDAO loanFlowHeadDAO = null;// 流水头表 PL202

  private LoanFlowTailDAO loanFlowTailDAO = null;//尾表头表 PL203
  
  private PlBizActiveLogDAO plBizActiveLogDAO = null;// 业务活动日志 PL020

  private PlOperateLogDAO plOperateLogDAO = null;// 操作日志 PL021
  
  private PlDocNumCancelDAO plDocNumCancelDAO=null;
 
  private CollBankDAO       collBankDAO=null;
  private CollParaDAO       collParaDAO=null;
  private  PlDocNumMaintainDAO plDocNumMaintainDAO=null;
  
  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
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
  public void setPlDocNumCancelDAO(PlDocNumCancelDAO plDocNumCancelDAO) {
    this.plDocNumCancelDAO = plDocNumCancelDAO;
  }
  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setPlDocNumMaintainDAO(PlDocNumMaintainDAO plDocNumMaintainDAO) {
    this.plDocNumMaintainDAO = plDocNumMaintainDAO;
  }
  /**
   * 办理核销收回- 通过贷款账号带出页面信息
   * 
   * @author 吴迪 2007-10-16
   */
  public DestoryBackTaAF queryContractInfo(String loanKouAcc,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    DestoryBackTaAF destoryBackTaAF = new DestoryBackTaAF();
    // 根据贷款账号在PL111中查询状态为７、８、11、12、13的记录个数
    List temp1 = borrowerAccDAO.queryDestoryBackNumByLoanKouAcc_WU(loanKouAcc,
        securityInfo);
    if (!temp1.isEmpty()) {
      // 根据贷款账号在PL202中查询状态为1、２、３、４、５的记录
      List temp2 = loanFlowHeadDAO.queryDestoryBackByLoanKouAcc_WU(loanKouAcc,
          securityInfo);     
      if (temp2.isEmpty()) {
        // 根据贷款账号在PL111、PL110中查询记录
        DestoryBackTaDTO destoryBackTaDTO = borrowerAccDAO.queryDestoryBackByLoanKouAcc_WU(
            loanKouAcc, securityInfo);

        String contractId = destoryBackTaDTO.getContractId();// 合同编号
        
        String borrowerName = destoryBackTaDTO.getBorrowerName();// 借款人姓名

        String cardKind = destoryBackTaDTO.getCardKind(); // 证件类型

        String cardKindName = destoryBackTaDTO.getCardKindName(); // 显示证件类型对应的名称

        String cardNum = destoryBackTaDTO.getCardNum(); // 证件号码

        BigDecimal overplusLoanMoney = destoryBackTaDTO.getOverplusLoanMoney();// 剩余本金

        String loanMode = destoryBackTaDTO.getLoanMode();// 还款方式

        String loanModeName = destoryBackTaDTO.getLoanModeName();// 还款方式

        BigDecimal noBackMoney = destoryBackTaDTO.getNoBackMoney();// 核销未收回金额

        destoryBackTaAF.setLoanKouAcc(loanKouAcc);
        destoryBackTaAF.setBorrowerName(borrowerName);
        destoryBackTaAF.setContractId(contractId);
        destoryBackTaAF.setCardKind(cardKind);
        destoryBackTaAF.setCardKindName(cardKindName);
        destoryBackTaAF.setCardNum(cardNum);
        destoryBackTaAF.setOverplusLoanMoney(overplusLoanMoney);
        destoryBackTaAF.setLoanMode(loanMode);
        destoryBackTaAF.setLoanModeName(loanModeName);
        destoryBackTaAF.setNoBackMoney(noBackMoney);

      } else {
        throw new BusinessException("该贷款账号下存在未记账的业务，不能进行核销收回!");
      }

    } else {
      throw new BusinessException("合同状态不正确！");
    }
    return destoryBackTaAF;
  }

  /**
   * 办理核销收回-插入核销收回相关信息（页面信息）
   * 
   * @author 吴迪 2007-10-16
   */
  public void saveDestoryBack(DestoryBackTaDTO destoryBackTaDTO,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String operateName = securityInfo.getUserInfo().getUsername();// 操作员
    String loanKouAcc = destoryBackTaDTO.getLoanKouAcc().trim();
    String contractId = destoryBackTaDTO.getContractId().trim();
    String backunit = destoryBackTaDTO.getBackUnit().trim();
    BigDecimal backMoney = destoryBackTaDTO.getBackMoney();
    String loanassistantorgId = destoryBackTaDTO.getLoanassistantorgId();
    BigDecimal loanBankId=borrowerAccDAO.queryById(contractId).getLoanBankId();
    // 插入贷款流水账头表PL202
    LoanFlowHead loanFlowHead = new LoanFlowHead();
     
    //取凭证号
     CollBank collBank = collBankDAO.getCollBankByCollBankid_(loanBankId.toString());
      String bizYearmonth = securityInfo.getUserInfo().getPlbizDate()
          .substring(0, 6);
      String officeId="";
      String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
      if(loanDocNumDocument.equals("1")){
        officeId=collBank.getOffice();
      }else{
        officeId=loanBankId.toString();
      }
      String docNum = plDocNumMaintainDAO.getDocNumdocNum(officeId,
          bizYearmonth);
    loanFlowHead.setDocNum(docNum);
    loanFlowHead.setBizDate(securityInfo.getUserInfo().getPlbizDate());//业务日期
    if (backunit.equals("中心")) {
      loanFlowHead.setBizType(new Integer(
          BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVERCENTER).toString());// 业务类型
    } else {
      loanFlowHead.setBizType(new Integer(
          BusiConst.PLBUSINESSTYPE_BADDEBTSRECOVEROTHER).toString());// 业务类型
    }
    loanFlowHead.setShouldCount(new BigDecimal(0.00));//应还人数
    loanFlowHead.setShouldCorpus(new BigDecimal(0.00));//应还正常本金
    loanFlowHead.setShouldInterest(new BigDecimal(0.00));//应还利息
    loanFlowHead.setShouldOverdueCorpus(new BigDecimal(0.00));//应还逾期本金
    loanFlowHead.setShouldOverdueInterest(new BigDecimal(0.00));//应还逾期利息
    loanFlowHead.setShouldPunishInterest(new BigDecimal(0.00));//应还罚息
    loanFlowHead.setRealCount(new BigDecimal(0.00));// 实还人数
    loanFlowHead.setRealCorpus(new BigDecimal(0.00));//实还本金
    loanFlowHead.setRealInterest(new BigDecimal(0.00));//实还利息
    loanFlowHead.setRealOverdueCorpus(new BigDecimal(0.00));//实还逾期本金
    loanFlowHead.setRealOverdueInterest(new BigDecimal(0.00));//实还逾期利息
    loanFlowHead.setRealPunishInterest(new BigDecimal(0.00));//实还罚息
    loanFlowHead.setOccurMoney(backMoney);// 发生金额
    loanFlowHead.setOtherInterest(new BigDecimal(0.00));//其他利息
    loanFlowHead.setLoanBankId(loanBankId);
    loanFlowHead.setBizSt(new Integer(BusiConst.BUSINESSSTATE_SURE).toString());// 业务状态 4（确认）            
    loanFlowHead.setMakePerson(operateName);//制单人
    if (backunit.equals("担保公司")) {
      loanFlowHead.setHedaiOrg(loanassistantorgId);
    }
    loanFlowHead.setIsFinance(new Integer(1));
    String flowHeadId = loanFlowHeadDAO.insert(loanFlowHead).toString();// 插入PL202  并返回flow_head_id
    //更新票据号
    loanFlowHead.setNoteNum(flowHeadId);
    loanFlowHeadDAO.update(loanFlowHead);
    
    // 插入贷款流水账尾表PL203
    LoanFlowTail loanFlowTail = new LoanFlowTail();
    loanFlowTail.setFlowHeadId(new BigDecimal(flowHeadId));//头表流水号
    loanFlowTail.setLoanKouAcc(loanKouAcc);//贷款账号
    loanFlowTail.setContractId(contractId);//合同编号
    loanFlowTail.setShouldCorpus(new BigDecimal(0.00));//应还本金
    loanFlowTail.setShouldInterest(new BigDecimal(0.00));//、应还利息
    loanFlowTail.setShouldPunishInterest(new BigDecimal(0.00));//应还罚息
    loanFlowTail.setRealCorpus(new BigDecimal(0.00));//实还本金
    loanFlowTail.setRealInterest(new BigDecimal(0.00));//实还利息
    loanFlowTail.setRealPunishInterest(new BigDecimal(0.00));//实还罚息
    loanFlowTail.setOtherInterest(new BigDecimal(0.00));//其他利息
    loanFlowTail.setLoanType(new Integer(BusiConst.PLRECOVERTYPE_OTHER)
        .toString());// 还款类型
    loanFlowTail.setOccurMoney(backMoney);//发生金额
    loanFlowTailDAO.insert(loanFlowTail);
  }

  /**
   * 核销收回维护-核销收回维护信息列表
   * 
   * @author 吴迪 2007-10-16
   */
  public DestoryBackTbAF queryDestoryBackTbShowListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    DestoryBackTbAF destoryBackTbAF = new DestoryBackTbAF();
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    List destoryBackShowList = new ArrayList();
   
    destoryBackShowList = loanFlowHeadDAO.queryDestoryBackTbShowListByCriterions(start,
        orderBy, order, pageSize, page, securityInfo, loanKouAcc, contractId,
        borrowerName, cardNum, loanBankName, bizSt, docNum);
    if(!destoryBackShowList.isEmpty())
    destoryBackTbAF.setList(destoryBackShowList);
    
    // 合计信息
    BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// 回收金额-总额
    DestoryBackTbTotleDTO destoryBackTbTotleDTO = loanFlowHeadDAO
        .queryDestoryBackTbShowListTotleByCriterions(securityInfo, loanKouAcc,
            contractId, borrowerName, cardNum, loanBankName, bizSt, docNum);
    count=destoryBackTbTotleDTO.getCount();
    reclaimCorpusTotle=destoryBackTbTotleDTO.getReclaimCorpusTotle();
    destoryBackTbAF.setReclaimCorpusTotle(reclaimCorpusTotle);
    pagination.setNrOfElements(count);
    return destoryBackTbAF;
  }

  /**
   * 核销收回维护-核销收回维护删除
   * 
   * @author 吴迪 2007-10-16
   */
  public void deleteDestoryBackInfo(String flowHeadId,SecurityInfo securityInfo)
      throws Exception, BusinessException {
   
    try {
      
      //根据flowHeadId取合同编号
      String  contractId= loanFlowTailDAO.queryContractByHeadId_YU(flowHeadId);
        
      //删除尾表PL203：
      loanFlowTailDAO.deleteLoanFlowTailAll(flowHeadId);   
      
      //根据flowHeadID取业务状态
      LoanFlowHead loanFlowHead = loanFlowHeadDAO.queryById(new Integer(flowHeadId));
      String bizSt=loanFlowHead.getBizSt();    
      
      //删除头表PL202
      loanFlowHeadDAO.deleteLoanFlowHead(flowHeadId);
      
      // 插入凭证编号PL221
      String officeCode = loanFlowHeadDAO.queryOfficeByBank(loanFlowHead.getLoanBankId().toString());
      String yearMonth = securityInfo.getUserInfo().getBizDate().substring(0, 6);// 归集业务日期
      String officeId="";
      String loanDocNumDocument=collParaDAO.getLoanDocNumDesignPara();
      if(loanDocNumDocument.equals("1")){
        officeId=officeCode;
      }else{
        officeId=loanFlowHead.getLoanBankId().toString();
      }
      plDocNumCancelDAO.insertPlDocNumCancel(loanFlowHead.getDocNum(), officeId,yearMonth);          
      
      //  插入日志 pl021  
      String operateName = securityInfo.getUserInfo().getUsername();// 操作员
      String userIp = securityInfo.getUserInfo().getUserIp();// 操作员IP
      PlOperateLog plOperateLog = new PlOperateLog();
      plOperateLog.setOpSys(new BigDecimal(new Integer(BusiLogConst.OP_SYSTEM_TYPE_LOAN).toString()));// 个贷 2
      plOperateLog.setOpModel(new Integer(
          BusiLogConst.PL_OP_LOANRECOVER_CANRECOVER_MAINTAIN).toString());// 核销收回-核销收回维护

      plOperateLog.setOpButton(new Integer(BusiLogConst.BIZLOG_ACTION_DELETE)
              .toString());// 删除 3
      plOperateLog
      .setOpBizId(new BigDecimal(flowHeadId));
      plOperateLog.setOpIp(userIp);
      plOperateLog.setContractId(contractId);
      plOperateLog.setOpTime(new Date());
      plOperateLog.setOperator(operateName);
      plOperateLogDAO.insert(plOperateLog); 
      //删除该笔业务活动日至PL020
      plBizActiveLogDAO.deletePlBizActiveLogByFlowHeadId_WD(flowHeadId,bizSt);
    } catch (Exception e) {
      throw new BusinessException("删除失败!");
    }

  }

  /**
   * 核销收回维护-核销收回维护打印
   * 
   * @author 吴迪　2007-10-19
   */
  public List findDestoryBackTbPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception {
    String docNum = (String) pagination.getQueryCriterions().get("docNum");
    String contractId = (String) pagination.getQueryCriterions().get(
        "contractId");
    String loanKouAcc = (String) pagination.getQueryCriterions().get(
        "loanKouAcc");
    String borrowerName = (String) pagination.getQueryCriterions().get(
        "borrowerName");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
    
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    
    List resultList = new ArrayList();
    // 核销收回维护打印列表
    resultList = loanFlowHeadDAO.queryDestoryBackTbShowListCountByCriterions(
        securityInfo, loanKouAcc, contractId, borrowerName, cardNum,
        loanBankName, bizSt, docNum);
   
    return  resultList;
  }
  /**
   *核销收回维护-核销收回弹出眶打印
   * @param pagination
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public DestoryBackTaDTO queryDestoryBackTbWindowById(String flowHeadId, SecurityInfo securityInfo) throws Exception, BusinessException {

    DestoryBackTaDTO destoryBackTaDTO = new DestoryBackTaDTO();
    try {
      List list = loanFlowHeadDAO.querydestoryBackTbWindowById(flowHeadId, securityInfo);
      if (!list.isEmpty()) {
        destoryBackTaDTO = (DestoryBackTaDTO) list.get(0);
        // 证件类型对应的名称
        destoryBackTaDTO.setCardKindName(BusiTools.getBusiValue(Integer
            .parseInt(destoryBackTaDTO.getCardKind()), BusiConst.DOCUMENTSSTATE));
        // 还款方式
        destoryBackTaDTO.setLoanModeName(BusiTools.getBusiValue(Integer
            .parseInt(destoryBackTaDTO.getLoanMode()), BusiConst.PLRECOVERTYPE));       
      } else {
        throw new BusinessException("此合同不存在核销收回信息");
      }
    } catch (BusinessException bx) {
      throw bx;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return destoryBackTaDTO;
  }

  public CollParaDAO getCollParaDAO() {
    return collParaDAO;
  }

  public void setCollParaDAO(CollParaDAO collParaDAO) {
    this.collParaDAO = collParaDAO;
  }
}

