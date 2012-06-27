package org.xpup.hafmis.sysloan.loanaccord.printplan.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanDTO;
import org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanListDTO;

public class PrintplanShowAF extends ActionForm {

  /**
   * 
   */
  private static final long serialVersionUID = -4152999050809070227L;

  private List list = new ArrayList();

  private List printList = new ArrayList();

  private PrintplanDTO printplanDTO = new PrintplanDTO(); // 头信息

  private PrintplanListDTO printplanListDTO = new PrintplanListDTO(); // 循环输出

  private String operson = ""; // 操作员

  private String bizDate = ""; // 操作时间
  
  private BigDecimal interest = new BigDecimal(0.00);

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public void reset(ActionMapping mapping, ServletRequest request) {
    list = new ArrayList();
    printplanDTO = new PrintplanDTO();
  }

  public PrintplanDTO getPrintplanDTO() {
    return printplanDTO;
  }

  public void setPrintplanDTO(PrintplanDTO printplanDTO) {
    this.printplanDTO = printplanDTO;
  }

  public PrintplanListDTO getPrintplanListDTO() {
    return printplanListDTO;
  }

  public void setPrintplanListDTO(PrintplanListDTO printplanListDTO) {
    this.printplanListDTO = printplanListDTO;
  }

  public List getPrintList() {
    return printList;
  }

  public void setPrintList(List printList) {
    this.printList = printList;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getOperson() {
    return operson;
  }

  public void setOperson(String operson) {
    this.operson = operson;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }
}
