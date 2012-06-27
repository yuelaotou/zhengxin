package org.xpup.hafmis.syscollection.paymng.personaddpay.dto;

import org.xpup.common.util.exp.domn.interfaces.ExpDto;

public class ExplainExpDTO implements ExpDto{
 
  private String explain = "";

  public String getExplain() {
    return explain;
  }

  public void setExplain(String explain) {
    this.explain = explain;
  }

  public String getInfo(String s) {
    // TODO Auto-generated method stub
    if(s.equals("explain"))
      return explain;
    else 
        return null;
  }
  
}
