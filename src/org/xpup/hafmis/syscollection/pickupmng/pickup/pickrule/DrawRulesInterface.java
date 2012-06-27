package org.xpup.hafmis.syscollection.pickupmng.pickup.pickrule;

import java.io.Serializable;
import java.math.BigDecimal;

public interface DrawRulesInterface {//一个提取规则的接口
  public BigDecimal getMaxDarwMoney(int orgId,int empId,String reason);
  public boolean isDraw(String reason,Serializable empid);
}
