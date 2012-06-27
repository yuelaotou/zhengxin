package org.xpup.hafmis.sysloan.loanaccord.loanaccord.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO;

public class LoanaccordShowAF extends ActionForm {
  /**
   * 
   */
  private static final long serialVersionUID = 3160433858841124974L;

  private List list = new ArrayList();

  private List printList = new ArrayList();

  private String contractIdf = "";

  private String borrowerNamef = "";

  private String cardNumf = "";

  private String loanBankIdf = "";

  private String bizStf = "";

  private Map statusNewMap = new HashMap();

  private LoanaccordDTO loanaccordDTO = new LoanaccordDTO();

  private BigDecimal sumloanMoney = new BigDecimal(0.00);

  private String loanStartDate = "";

  private String loanEndDate = "";

  public String getLoanEndDate() {
    return loanEndDate;
  }

  public void setLoanEndDate(String loanEndDate) {
    this.loanEndDate = loanEndDate;
  }

  public String getLoanStartDate() {
    return loanStartDate;
  }

  public void setLoanStartDate(String loanStartDate) {
    this.loanStartDate = loanStartDate;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public LoanaccordDTO getLoanaccordDTO() {
    return loanaccordDTO;
  }

  public void setLoanaccordDTO(LoanaccordDTO loanaccordDTO) {
    this.loanaccordDTO = loanaccordDTO;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    loanaccordDTO = new LoanaccordDTO();
    list = new ArrayList();
    contractIdf = "";
    borrowerNamef = "";
    cardNumf = "";
    loanBankIdf = "";
    bizStf = "";
    statusNewMap = new HashMap();
    sumloanMoney = new BigDecimal(0.00);
  }

  public String getBizStf() {
    return bizStf;
  }

  public void setBizStf(String bizStf) {
    this.bizStf = bizStf;
  }

  public String getBorrowerNamef() {
    return borrowerNamef;
  }

  public void setBorrowerNamef(String borrowerNamef) {
    this.borrowerNamef = borrowerNamef;
  }

  public String getCardNumf() {
    return cardNumf;
  }

  public void setCardNumf(String cardNumf) {
    this.cardNumf = cardNumf;
  }

  public String getContractIdf() {
    return contractIdf;
  }

  public void setContractIdf(String contractIdf) {
    this.contractIdf = contractIdf;
  }

  public String getLoanBankIdf() {
    return loanBankIdf;
  }

  public void setLoanBankIdf(String loanBankIdf) {
    this.loanBankIdf = loanBankIdf;
  }

  public Map getStatusNewMap() {
    return statusNewMap;
  }

  public void setStatusNewMap(Map statusNewMap) {
    this.statusNewMap = statusNewMap;
  }

  public BigDecimal getSumloanMoney() {
    return sumloanMoney;
  }

  public void setSumloanMoney(BigDecimal sumloanMoney) {
    this.sumloanMoney = sumloanMoney;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

}
