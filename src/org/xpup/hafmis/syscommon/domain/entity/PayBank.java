

package org.xpup.hafmis.syscommon.domain.entity;

public class PayBank {

  /**
   * Ãû³Æ
   */
  private String name = null;

  /**
   * ÕËºÅ
   */
  private String accountNum = null;

  public PayBank() {

  }

  public String getAccountNum() {
    return accountNum;
  }

  public void setAccountNum(String accountNum) {
    this.accountNum = accountNum;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
