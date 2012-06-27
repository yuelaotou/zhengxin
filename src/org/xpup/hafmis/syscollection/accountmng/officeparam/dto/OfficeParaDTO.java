package org.xpup.hafmis.syscollection.accountmng.officeparam.dto;

public class OfficeParaDTO {
  private String office = "";// 办事处

  private String isBankModify = "";// 是否可以修改存款银行

  public String getIsBankModify() {
    return isBankModify;
  }

  public void setIsBankModify(String isBankModify) {
    this.isBankModify = isBankModify;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

}
