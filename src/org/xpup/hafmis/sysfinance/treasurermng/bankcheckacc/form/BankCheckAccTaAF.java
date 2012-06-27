package org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.form;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.treasurermng.bankcheckacc.dto.BankCheckAccTaDTO;

public class BankCheckAccTaAF extends ActionForm {
  private BankCheckAccTaDTO bankCheckAccTaDTO=new BankCheckAccTaDTO();
  private String type="";//用来标志保存时是成功还是失败
  /**
   * 用来判断账套是否启用
   */
  private String bookSt="";
  public BankCheckAccTaDTO getBankCheckAccTaDTO() {
    return bankCheckAccTaDTO;
  }
  public void setBankCheckAccTaDTO(BankCheckAccTaDTO bankCheckAccTaDTO) {
    this.bankCheckAccTaDTO = bankCheckAccTaDTO;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getBookSt() {
    return bookSt;
  }
  public void setBookSt(String bookSt) {
    this.bookSt = bookSt;
  }
}
