package org.xpup.hafmis.sysfinance.common.biz.queryflow.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;
import org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.form.OrgbusinessflowAF;
import org.xpup.hafmis.sysfinance.common.biz.queryflow.bsinterface.IQueryFlowBS;
import org.xpup.hafmis.sysfinance.common.dao.AccountantCredenceDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryDTO;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form.LoanBusiFlowQueryAF;

public class QueryFlowBS implements IQueryFlowBS {
  
  
  protected AccountantCredenceDAO accountantCredenceDAO = null;
  
  protected LoanFlowHeadDAO loanFlowHeadDAO = null;// 流水头表 PL202
  
  public void setAccountantCredenceDAO(AccountantCredenceDAO accountantCredenceDAO) {
    this.accountantCredenceDAO = accountantCredenceDAO;
  }

  public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
    this.loanFlowHeadDAO = loanFlowHeadDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
  }
  
  public boolean IssettNum(String settNum)throws Exception{
    int i = loanFlowHeadDAO.queryCountBySettNum(settNum);
    if (i>0) {
      return false;
    }else{
      return true;
    }
  }

  public OrgbusinessflowAF findOrgFlowListByCriterions(Pagination pagination,SecurityInfo securityInfo)
      throws Exception, BusinessException {  
    OrgbusinessflowAF af = new OrgbusinessflowAF();
    List list = null;
    BigDecimal debitTotal = new BigDecimal(0.00);
    BigDecimal creditTotal = new BigDecimal(0.00);
    try {
      list = new ArrayList();

      String officecode = (String) pagination.getQueryCriterions().get(
          "officecode");
      String bankcode = (String) pagination.getQueryCriterions()
          .get("bankcode");
      String orgcode = (String) pagination.getQueryCriterions().get("orgid");
      String orgname = (String) pagination.getQueryCriterions().get("orgname");
      String notenum = (String) pagination.getQueryCriterions().get("notenum");
      String docnum = (String) pagination.getQueryCriterions().get("docnum");
      String bsstatus = (String) pagination.getQueryCriterions()
          .get("bsstatus");
      String bstype = (String) pagination.getQueryCriterions().get("bstype");
      String setmonthstart = (String) pagination.getQueryCriterions().get(
          "setmonthstart");
      String setmonthend = (String) pagination.getQueryCriterions().get(
          "setmonthend");
      String setmoneystart = (String) pagination.getQueryCriterions().get(
          "setmoneystart");
      String setmoneyend = (String) pagination.getQueryCriterions().get(
          "setmoneyend");
      String setpeopcountstart = (String) pagination.getQueryCriterions().get(
          "setpeopcountstart");
      String setpeopcountend = (String) pagination.getQueryCriterions().get(
          "setpeopcountend");
      String setdirection = (String) pagination.getQueryCriterions().get(
          "setdirection");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      list = accountantCredenceDAO.findOrgFlowListByCriterions_FYF(officecode,
          bankcode, orgcode, orgname, notenum, docnum, bsstatus, bstype,
          setmonthstart, setmonthend, setmoneystart, setmoneyend,
          setpeopcountstart, setpeopcountend, setdirection, orderBy, order,
          start, pageSize, page, securityInfo);
      OrgHAFAccountFlow orgHAFAccountFlow = null;
      for (int i = 0; i < list.size(); i++) {
        orgHAFAccountFlow = (OrgHAFAccountFlow) list.get(i);
        // 转换
        if (orgHAFAccountFlow.getInterest() == null) {
          orgHAFAccountFlow.setInterest(new BigDecimal(0.00));
        }
        if (!orgHAFAccountFlow.getCredit().toString().equals("0")) {
          orgHAFAccountFlow.setSetdirection(BusiTools.getBusiValue(
              BusiConst.OCCURREDDIRECTION_CREDIT, BusiConst.OCCURREDDIRECTION));
        } else if (!orgHAFAccountFlow.getDebit().toString().equals("0")) {
          orgHAFAccountFlow.setSetdirection(BusiTools.getBusiValue(
              BusiConst.OCCURREDDIRECTION_DEBIT, BusiConst.OCCURREDDIRECTION));
        } else {
          orgHAFAccountFlow.setSetdirection(BusiTools
              .getBusiValue(BusiConst.OCCURREDDIRECTION_PARALLEL,
                  BusiConst.OCCURREDDIRECTION));
        }
        orgHAFAccountFlow.setBis_type(BusiTools
            .getBusiValue_WL(orgHAFAccountFlow.getBiz_Type(),
                BusiConst.CLEARACCOUNTBUSINESSTYPE));
        orgHAFAccountFlow.setStatus(BusiTools.getBusiValue(Integer
            .parseInt(orgHAFAccountFlow.getBizStatus().toString()),
            BusiConst.BUSINESSSTATE));

      }
      af.setList(list);
      List countList = accountantCredenceDAO
          .findOrgFlowListCountByCriterions_FYF(officecode, bankcode, orgcode,
              orgname, notenum, docnum, bsstatus, bstype, setmonthstart,
              setmonthend, setmoneystart, setmoneyend, setpeopcountstart,
              setpeopcountend, setdirection, securityInfo);
      pagination.setNrOfElements(countList.size());
      for (int i = 0; i < countList.size(); i++) {
        OrgHAFAccountFlow orgFlow = (OrgHAFAccountFlow) list.get(i);
        debitTotal = debitTotal.add(orgFlow.getDebit());
        creditTotal = creditTotal.add(orgFlow.getCredit());
      }
      af.setDebitTotal(debitTotal);
      af.setCreditTotal(creditTotal);
    }catch(BusinessException be){
      throw be;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return af;
  }
  
  /**
   * Description 贷款业务流水统计查询列表
   * 
   * @author wangy 2007-10-16
   * @param 根据条件查询列表
   * @param pagination
   * @param securityInfo
   * @param 由LoanBusiFlowQueryShowAC调用
   * @return LoanBusiFlowQueryAF
   * @throws Exception, BusinessException
   */
  public LoanBusiFlowQueryAF queryLoanBusiFlowQueryListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    LoanBusiFlowQueryAF loanBusiFlowQueryAF = new LoanBusiFlowQueryAF();
    String notenum = (String) pagination.getQueryCriterions().get("notenum");
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
    String makePerson = (String) pagination.getQueryCriterions().get(
        "makePerson");
    String bizType = (String) pagination.getQueryCriterions().get("bizType");
    String bizSt = (String) pagination.getQueryCriterions().get("bizSt");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String beginBizDate = (String) pagination.getQueryCriterions().get(
        "beginBizDate");
    String endBizDate = (String) pagination.getQueryCriterions().get(
        "endBizDate");
    List loanBusiFlowQueryList = new ArrayList();
    List printList = new ArrayList();// 打印列表
    List templist = new ArrayList();
    List temptotlelist = new ArrayList();
    templist = accountantCredenceDAO.queryLoanBusiFlowQueryListByCriterions_FYF(start,
        orderBy, order, pageSize, page, securityInfo, docNum, contractId,
        loanKouAcc, borrowerName, makePerson, bizType, bizSt, loanBankName,
        beginBizDate, endBizDate,notenum);
    temptotlelist = accountantCredenceDAO.queryLoanBusiFlowQueryAllByCriterions_FYF(
        securityInfo, docNum, contractId, loanKouAcc, borrowerName, makePerson,
        bizType, bizSt, loanBankName, beginBizDate, endBizDate, notenum);
    count = temptotlelist.size();
    // 贷款业务流水查询列表
    Iterator iterate = templist.iterator();
    Object[] obj = null;
    while (iterate.hasNext()) {
      LoanBusiFlowQueryDTO loanBusiFlowQueryDTO = new LoanBusiFlowQueryDTO();
      obj = (Object[]) iterate.next();
      // 凭证编号PL202.DOC_NUM
      if (obj[0] != null && !obj[0].equals(""))
        loanBusiFlowQueryDTO.setDocNum(obj[0].toString());
      // 贷款账号PL203.LOAN_KOU_ACC
      if (obj[1] != null && !obj[1].equals(""))
        loanBusiFlowQueryDTO.setLoanKouAcc(obj[1].toString());
      // 合同编号PL203.CONTRACT_ID
      if (obj[2] != null && !obj[2].equals(""))
        loanBusiFlowQueryDTO.setContractId(obj[2].toString());
      if (obj[3] != null && !obj[3].equals(""))
        loanBusiFlowQueryDTO.setBorrowerName(obj[3].toString());
      // 业务类型L202.BIZ_TYPE
      if (obj[4] != null && !obj[4].equals("")) {
        loanBusiFlowQueryDTO.setBizType(obj[4].toString());
        loanBusiFlowQueryDTO.setOriginalitybizType(obj[4].toString());
      }
      // 发放金额L202.OCCUR_MONEY(当业务类型=1、11或业务类型=12，WRONG_BIZ_TYPE=1时)
      if (obj[5] != null && !obj[5].equals("")) {
        if (obj[4].equals("1")) {
          loanBusiFlowQueryDTO.setOccurMoney(new BigDecimal(obj[5].toString()));
        } else if (obj[4].equals("12")) {
          if ((obj[15] != null && !obj[15].equals(""))) {
            if (obj[15].equals("1")) {
              loanBusiFlowQueryDTO.setOccurMoney(new BigDecimal(obj[5]
                  .toString()));
            }
          }
        }
      }
      // 回收本金PL202.REAL_CORPUS+PL202.REAL_OVERDUE_CORPUS
      BigDecimal reclaimCorpus = new BigDecimal(0.00);// 回收本金
      if (obj[6] != null && !obj[6].equals("")) {
        if (obj[4].equals("2") || obj[4].equals("3") || obj[4].equals("4")
            || obj[4].equals("5") || obj[4].equals("6") || obj[4].equals("7")
            || obj[4].equals("11") || obj[4].equals("12")) {
          reclaimCorpus = new BigDecimal(obj[6].toString());
          loanBusiFlowQueryDTO.setReclaimCorpus(new BigDecimal(obj[6]
              .toString()));
        }
      }
      // 回收利息PL202.REAL_INTEREST+PL202.REAL_OVERDUE_INTEREST
      BigDecimal reclaimAccrual = new BigDecimal(0.00);// 回收利息
      if (obj[7] != null && !obj[7].equals("")) {
        if (obj[4].equals("2") || obj[4].equals("3") || obj[4].equals("4")
            || obj[4].equals("5") || obj[4].equals("6") || obj[4].equals("7")
            || obj[4].equals("11") || obj[4].equals("12")) {
          reclaimAccrual = new BigDecimal(obj[7].toString());
          loanBusiFlowQueryDTO.setReclaimAccrual(new BigDecimal(obj[7]
              .toString()));
        }
      }
      // 回收罚息PL202.REAL_PUNISH_INTEREST
      BigDecimal realPunishInterest = new BigDecimal(0.00);// 回收罚息
      if (obj[8] != null && !obj[8].equals("")) {
        if (obj[4].equals("2") || obj[4].equals("3") || obj[4].equals("4")
            || obj[4].equals("5") || obj[4].equals("6") || obj[4].equals("7")
            || obj[4].equals("11") || obj[4].equals("12")) {
          realPunishInterest = new BigDecimal(obj[8].toString());
          loanBusiFlowQueryDTO.setRealPunishInterest(new BigDecimal(obj[8]
              .toString()));
        }
      }
      // 呆账核销金额PL202.OCCUR_MONEY
      if (obj[9] != null && !obj[9].equals("")) {
        if (obj[4].equals("6") || obj[4].equals("7") || obj[4].equals("8")
            || obj[4].equals("9")) {
          loanBusiFlowQueryDTO.setBadDebt(new BigDecimal(obj[9].toString()));
        } else if (obj[4].equals("12")) {
          if ((obj[15] != null && !obj[15].equals(""))) {
            if (obj[15].equals("6") || obj[15].equals("7")) {
              loanBusiFlowQueryDTO
                  .setBadDebt(new BigDecimal(obj[9].toString()));
            }
          }
        }
      }
      // 挂账金额PL202.OCCUR_MONEY
      if (obj[10] != null && !obj[10].equals("")) {
        if (obj[4].equals("2") || obj[4].equals("3") || obj[4].equals("4")
            || obj[4].equals("5") || obj[4].equals("13")) {
          loanBusiFlowQueryDTO
              .setPutUpMoney(new BigDecimal(obj[10].toString()));
        } else if (obj[4].equals("12")) {
          if ((obj[15] != null && !obj[15].equals(""))) {
            if (obj[15].equals("2") || obj[15].equals("5")) {
              loanBusiFlowQueryDTO.setPutUpMoney(new BigDecimal(obj[10]
                  .toString()));
            }
          }
        }
      }
      // 保证金PL202.OCCUR_MONEY
      if (obj[11] != null && !obj[11].equals("")) {
        if (obj[4].equals("14")) {
          loanBusiFlowQueryDTO.setBail(new BigDecimal(obj[11].toString()));
        }
      }
      // 保证金利息
      if (obj[12] != null && !obj[12].equals("")) {
        if (obj[4].equals("14")) {
          loanBusiFlowQueryDTO
              .setBailAccrual(new BigDecimal(obj[12].toString()));// bizType=14时，保证金利息=PL202.OTHER_INTEREST
        } else {
          if (obj[11] != null && !obj[11].equals("")) {
            if (obj[4].equals("15")) {
              loanBusiFlowQueryDTO.setBailAccrual(new BigDecimal(obj[11]
                  .toString()));// bizType=15时，保证金利息=PL202.OCCUR_MONEY
            }
          }
        }
      }
      // 业务状态PL202.BIZ_ST
      if (obj[13] != null && !obj[13].equals(""))
        loanBusiFlowQueryDTO.setBizSt(obj[13].toString());
      // 办理日期PL202.BIZ_DATE
      if (obj[14] != null && !obj[14].equals(""))
        loanBusiFlowQueryDTO.setBizDate(obj[14].toString());
      // 制单人PL202.MAKE_PERSON
      if (obj[17] != null && !obj[17].equals(""))
        loanBusiFlowQueryDTO.setMakePerson(obj[17].toString());
      // pl202.flow_head_id
      if (obj[18] != null && !obj[18].equals(""))
        loanBusiFlowQueryDTO.setFlowHeadId(obj[18].toString());
      // 回收总金额=回收本金＋回收利息＋回收罚息
      BigDecimal reclaim = new BigDecimal(0.00);// 回收总金额
      reclaim = reclaimCorpus.add(reclaimAccrual.add(realPunishInterest));
      loanBusiFlowQueryDTO.setReclaim(reclaim);
      // 枚举转换业务状态
      try {
        loanBusiFlowQueryDTO.setBizSt(BusiTools.getBusiValue(Integer
            .parseInt("" + loanBusiFlowQueryDTO.getBizSt()),
            BusiConst.PLBUSINESSSTATE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      // 枚举转换业务类型
      try {
        loanBusiFlowQueryDTO.setBizType(BusiTools.getBusiValue(Integer
            .parseInt("" + loanBusiFlowQueryDTO.getBizType()),
            BusiConst.PLBUSINESSTYPE));
      } catch (Exception e) {
        e.printStackTrace();
      }
      loanBusiFlowQueryList.add(loanBusiFlowQueryDTO);
    }
    loanBusiFlowQueryAF.setList(loanBusiFlowQueryList);
    loanBusiFlowQueryAF.setPrintList(printList);
    pagination.setNrOfElements(count);
    return loanBusiFlowQueryAF;
  }
}
