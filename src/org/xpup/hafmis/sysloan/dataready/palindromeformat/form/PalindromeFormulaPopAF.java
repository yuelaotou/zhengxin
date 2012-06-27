package org.xpup.hafmis.sysloan.dataready.palindromeformat.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public class PalindromeFormulaPopAF extends ActionForm{
  private List list = new ArrayList();
  private String formula = "";
  private String number = "";
  private String hidden = "";
  private String property = "";//¿Ø¼þÃû³Æ
  
  public String getProperty() {
    return property;
  }
  public void setProperty(String property) {
    this.property = property;
  }
  public String getHidden() {
    return hidden;
  }
  public void setHidden(String hidden) {
    this.hidden = hidden;
  }
  public List getList() {
    return list;
  }
  public void setList(List list) {
    this.list = list;
  }
  public String getNumber() {
    return number;
  }
  public void setNumber(String number) {
    this.number = number;
  }
  public String getFormula() {
    return formula;
  }
  public void setFormula(String formula) {
    this.formula = formula;
  }
}
