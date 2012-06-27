package org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanbackbyfund.dto.LoanBackByFundDTO;

/**
 * @author ั๎นโ 20090303
 */
public class LoanBackByFundAF extends ActionForm {

  private static final long serialVersionUID = 2531807195056023196L;

  private LoanBackByFundDTO loanBackByFundDTO = new LoanBackByFundDTO();

  private List list = new ArrayList();

  private List printList = new ArrayList();

  private List loanBankNameList = new ArrayList();

  private String loanBankName = "";

  private String beginBizDate = "";

  private String endBizDate = "";

  private BigDecimal data_1 = new BigDecimal("0.00");

  private BigDecimal data_2 = new BigDecimal("0.00");

  private BigDecimal data_3 = new BigDecimal("0.00");

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getBeginBizDate() {
    return beginBizDate;
  }

  public void setBeginBizDate(String beginBizDate) {
    this.beginBizDate = beginBizDate;
  }

  public BigDecimal getData_1() {
    return data_1;
  }

  public void setData_1(BigDecimal data_1) {
    this.data_1 = data_1;
  }

  public BigDecimal getData_2() {
    return data_2;
  }

  public void setData_2(BigDecimal data_2) {
    this.data_2 = data_2;
  }

  public BigDecimal getData_3() {
    return data_3;
  }

  public void setData_3(BigDecimal data_3) {
    this.data_3 = data_3;
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

  public LoanBackByFundDTO getLoanBackByFundDTO() {
    return loanBackByFundDTO;
  }

  public void setLoanBackByFundDTO(LoanBackByFundDTO loanBackByFundDTO) {
    this.loanBackByFundDTO = loanBackByFundDTO;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

}
