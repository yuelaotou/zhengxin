package org.xpup.hafmis.signjoint.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.signjoint.dto.TempDTO;



public class SignAddAF extends ValidatorActionForm{

  private TempDTO userinfo=new TempDTO();
  private TempDTO olduserinfo=new TempDTO();
  private String type="";
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public TempDTO getUserinfo() {
    return userinfo;
  }

  public void setUserinfo(TempDTO userinfo) {
    this.userinfo = userinfo;
  }

  public TempDTO getOlduserinfo() {
    return olduserinfo;
  }

  public void setOlduserinfo(TempDTO olduserinfo) {
    this.olduserinfo = olduserinfo;
  }

}
