package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;


/**
 * 
 * @author 李鹏
 *2007-7-1
 */
public class AdjustaccountNewAF extends ValidatorActionForm{

  private static final long serialVersionUID = 0L;

  private AdjustWrongAccountTail adjustWrongAccountTail=new AdjustWrongAccountTail();
  private BigDecimal totalBalance=new BigDecimal(0.00);
  private Map sexMap=new HashMap();
  private String orgId="";
  private String type="";  //下拉列表
  private String typelist="";  //下拉列表
  public Map getSexMap() {
    return sexMap;
  }

  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
  }

  public AdjustWrongAccountTail getAdjustWrongAccountTail() {
    return adjustWrongAccountTail;
  }

  public void setAdjustWrongAccountTail(
      AdjustWrongAccountTail adjustWrongAccountTail) {
    this.adjustWrongAccountTail = adjustWrongAccountTail;
  }

  public BigDecimal getTotalBalance() {
    return totalBalance;
  }

  public void setTotalBalance(BigDecimal totalBalance) {
    this.totalBalance = totalBalance;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTypelist() {
    return typelist;
  }

  public void setTypelist(String typelist) {
    this.typelist = typelist;
  }
  
  
  
}
