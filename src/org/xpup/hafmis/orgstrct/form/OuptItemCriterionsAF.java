package org.xpup.hafmis.orgstrct.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.form.CriterionsAF;

public class OuptItemCriterionsAF extends CriterionsAF {

  private static final long serialVersionUID = -2245091104517889140L;

  private Serializable ouptId = null;

  private String ouptName = null;

  private String ouptDescription = null;

  private String name = null;

  private int valueType = -1;

  private int type = -1;

  private int nullable = -1;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNullable() {
    return nullable;
  }

  public void setNullable(int nullable) {
    this.nullable = nullable;
  }

  public String getOuptDescription() {
    return ouptDescription;
  }

  public void setOuptDescription(String ouptDescription) {
    this.ouptDescription = ouptDescription;
  }

  public String getOuptName() {
    return ouptName;
  }

  public void setOuptName(String ouptName) {
    this.ouptName = ouptName;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getValueType() {
    return valueType;
  }

  public void setValueType(int valueType) {
    this.valueType = valueType;
  }

  public void reset(ActionMapping mapping, HttpServletRequest request) {
    super.reset(mapping, request);
    this.name = null;
    this.type = -1;
    this.valueType = -1;
    this.nullable = -1;
  }

  public Serializable getOuptId() {
    return ouptId;
  }

  public void setOuptId(Serializable ouptId) {
    this.ouptId = ouptId;
  }

}
