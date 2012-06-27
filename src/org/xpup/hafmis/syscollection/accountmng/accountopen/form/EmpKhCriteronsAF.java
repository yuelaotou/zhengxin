package org.xpup.hafmis.syscollection.accountmng.accountopen.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.validator.ValidatorForm;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;

public class EmpKhCriteronsAF extends ValidatorForm {
private Emp emp = new Emp();
private String type="";
private Map sexMap = new HashMap();
private Map cardKindMap = new HashMap();
private String orgrate ="";
private String emprate="";
private String payPrecision="";
public String getPayPrecision() {
  return payPrecision;
}

public void setPayPrecision(String payPrecision) {
  this.payPrecision = payPrecision;
}

public String getEmprate() {
  return emprate;
}

public void setEmprate(String emprate) {
  this.emprate = emprate;
}

public String getOrgrate() {
  return orgrate;
}

public void setOrgrate(String orgrate) {
  this.orgrate = orgrate;
}

public Map getCardKindMap() {
  return cardKindMap;
}

public void setCardKindMap(Map cardKindMap) {
  this.cardKindMap = cardKindMap;
}

public String getType() {
  return type;
}

public void setType(String type) {
  this.type = type;
}

public Emp getEmp() {
  return emp;
}

public void setEmp(Emp emp) {
  this.emp = emp;
}

public Map getSexMap() {
  return sexMap;
}

public void setSexMap(Map sexMap) {
  this.sexMap = sexMap;
}


}
