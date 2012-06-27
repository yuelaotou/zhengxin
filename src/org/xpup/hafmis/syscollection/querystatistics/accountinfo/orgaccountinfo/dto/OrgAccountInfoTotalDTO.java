package org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.xpup.hafmis.syscommon.domain.entity.DomainObject;

public class OrgAccountInfoTotalDTO extends DomainObject{
  
  private Integer personCounts = new Integer(0);
  private BigDecimal sumMoney = new BigDecimal(0.00);
  private BigDecimal sumInterest = new BigDecimal(0.00);
  public Integer getPersonCounts() {
    return personCounts;
  }
  public void setPersonCounts(Integer personCounts) {
    this.personCounts = personCounts;
  }
  public BigDecimal getSumInterest() {
    return sumInterest;
  }
  public void setSumInterest(BigDecimal sumInterest) {
    this.sumInterest = sumInterest;
  }
  public BigDecimal getSumMoney() {
    return sumMoney;
  }
  public void setSumMoney(BigDecimal sumMoney) {
    this.sumMoney = sumMoney;
  }
  

}