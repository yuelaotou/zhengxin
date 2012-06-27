package org.xpup.hafmis.demo.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;
import org.xpup.hafmis.demo.domain.entity.Demo;


public class DemoAddAF extends ValidatorActionForm{

  private static final long serialVersionUID = -7546809440786959772L;
  
  private Demo demo=new Demo();
  private Map sexMap=new HashMap();
  private String type="";
  private String isNeedDel="";
  
  public String getIsNeedDel() {
    return isNeedDel;
  }

  public void setIsNeedDel(String isNeedDel) {
    this.isNeedDel = isNeedDel;
  }

  public Demo getDemo() {
    return demo;
  }

  public void setDemo(Demo demo) {
    this.demo = demo;
  }

  public Map getSexMap() {
    return sexMap;
  }

  public void setSexMap(Map sexMap) {
    this.sexMap = sexMap;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
}
