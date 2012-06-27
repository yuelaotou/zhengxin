package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryDTO;

/**
 * @author 王野 2007-10-15
 */
public class LoanBusiFlowQueryAF extends ActionForm {

  private static final long serialVersionUID = 2531807195056023196L;

  LoanBusiFlowQueryDTO loanBusiFlowQueryDTO = new LoanBusiFlowQueryDTO();

  List list = null;// 显示列表
  
  List printList = null;// 打印列表

  private Map bizTypeMap = new HashMap();// 业务类型

  private List operList = new ArrayList();// 制单人

  private Map bizStMap = new HashMap();// 业务状态类型
  
  private Map isGjjLoanbackMap = new HashMap();// 是否为公积金还贷
  
  private String isGjjLoanback;// 是否为公积金还贷

  private List loanBankNameList = new ArrayList();// 放款银行

  private String docNum = null;// 凭证编号

  private String loanKouAcc = null;// 贷款账号

  private String contractId = null;// 合同编号

  private String borrowerName = null;// 借款人姓名

  private String bizType = null;// 业务类型

  private String makePerson = null;// 制单人

  private String bizSt = null;// 业务状态

  private String loanBankName = null;// 放款银行

  private String beginBizDate = null;// 起始办理日期

  private String endBizDate = null;// 终止办理日期

  private BigDecimal occurMoneyTotle = new BigDecimal(0.00);// 发放金额-总额

  private BigDecimal reclaimCorpusTotle = new BigDecimal(0.00);// 回收本金-总额

  private BigDecimal reclaimAccrualTotle = new BigDecimal(0.00);// 回收利息-总额总额

  private BigDecimal realPunishInterestTotle = new BigDecimal(0.00);// 回收罚息-总额

  private BigDecimal badDebtTotle = new BigDecimal(0.00);// 呆账核销金额-总额

  private BigDecimal reclaimTotle = new BigDecimal(0.00);// 回收总金额-总额

  private BigDecimal putUpMoneyTotle = new BigDecimal(0.00);// 挂账金额-总额

  private BigDecimal bailTotle = new BigDecimal(0.00);// 保证金-总额

  private BigDecimal bailAccrualTotle = new BigDecimal(0.00);// 保证金利息-总额
  
  private String loanType="";//贷款类型 本地 异地

  public BigDecimal getBadDebtTotle() {
    return badDebtTotle;
  }

  public void setBadDebtTotle(BigDecimal badDebtTotle) {
    this.badDebtTotle = badDebtTotle;
  }

  public BigDecimal getBailAccrualTotle() {
    return bailAccrualTotle;
  }

  public void setBailAccrualTotle(BigDecimal bailAccrualTotle) {
    this.bailAccrualTotle = bailAccrualTotle;
  }

  public BigDecimal getBailTotle() {
    return bailTotle;
  }

  public void setBailTotle(BigDecimal bailTotle) {
    this.bailTotle = bailTotle;
  }

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
  }

  public String getBizSt() {
    return bizSt;
  }

  public void setBizSt(String bizSt) {
    this.bizSt = bizSt;
  }

  public Map getBizStMap() {
    return bizStMap;
  }

  public void setBizStMap(Map bizStMap) {
    this.bizStMap = bizStMap;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public Map getBizTypeMap() {
    return bizTypeMap;
  }

  public void setBizTypeMap(Map bizTypeMap) {
    this.bizTypeMap = bizTypeMap;
  }

  public String getBorrowerName() {
    return borrowerName;
  }

  public void setBorrowerName(String borrowerName) {
    this.borrowerName = borrowerName;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  public String getDocNum() {
    return docNum;
  }

  public void setDocNum(String docNum) {
    this.docNum = docNum;
  }

  public String getEndBizDate() {
    return endBizDate;
  }

  public void setEndBizDate(String endBizDate) {
    this.endBizDate = endBizDate;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getLoanBankName() {
    return loanBankName;
  }

  public void setLoanBankName(String loanBankName) {
    this.loanBankName = loanBankName;
  }

  public List getLoanBankNameList() {
    return loanBankNameList;
  }

  public void setLoanBankNameList(List loanBankNameList) {
    this.loanBankNameList = loanBankNameList;
  }

  public LoanBusiFlowQueryDTO getLoanBusiFlowQueryDTO() {
    return loanBusiFlowQueryDTO;
  }

  public void setLoanBusiFlowQueryDTO(LoanBusiFlowQueryDTO loanBusiFlowQueryDTO) {
    this.loanBusiFlowQueryDTO = loanBusiFlowQueryDTO;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public String getMakePerson() {
    return makePerson;
  }

  public void setMakePerson(String makePerson) {
    this.makePerson = makePerson;
  }

  public BigDecimal getOccurMoneyTotle() {
    return occurMoneyTotle;
  }

  public void setOccurMoneyTotle(BigDecimal occurMoneyTotle) {
    this.occurMoneyTotle = occurMoneyTotle;
  }

  public List getOperList() {
    return operList;
  }

  public void setOperList(List operList) {
    this.operList = operList;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

  public BigDecimal getPutUpMoneyTotle() {
    return putUpMoneyTotle;
  }

  public void setPutUpMoneyTotle(BigDecimal putUpMoneyTotle) {
    this.putUpMoneyTotle = putUpMoneyTotle;
  }

  public BigDecimal getRealPunishInterestTotle() {
    return realPunishInterestTotle;
  }

  public void setRealPunishInterestTotle(BigDecimal realPunishInterestTotle) {
    this.realPunishInterestTotle = realPunishInterestTotle;
  }

  public BigDecimal getReclaimAccrualTotle() {
    return reclaimAccrualTotle;
  }

  public void setReclaimAccrualTotle(BigDecimal reclaimAccrualTotle) {
    this.reclaimAccrualTotle = reclaimAccrualTotle;
  }

  public BigDecimal getReclaimCorpusTotle() {
    return reclaimCorpusTotle;
  }

  public void setReclaimCorpusTotle(BigDecimal reclaimCorpusTotle) {
    this.reclaimCorpusTotle = reclaimCorpusTotle;
  }

  public BigDecimal getReclaimTotle() {
    return reclaimTotle;
  }

  public void setReclaimTotle(BigDecimal reclaimTotle) {
    this.reclaimTotle = reclaimTotle;
  }

  public String getIsGjjLoanback() {
    return isGjjLoanback;
  }

  public void setIsGjjLoanback(String isGjjLoanback) {
    this.isGjjLoanback = isGjjLoanback;
  }

  public Map getIsGjjLoanbackMap() {
    return isGjjLoanbackMap;
  }

  public void setIsGjjLoanbackMap(Map isGjjLoanbackMap) {
    this.isGjjLoanbackMap = isGjjLoanbackMap;
  }

  public String getLoanType() {
    return loanType;
  }

  public void setLoanType(String loanType) {
    this.loanType = loanType;
  }

  
}
