package org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class QueryReportAF extends ActionForm {
  private static final long serialVersionUID = 1L;

  private Map map = new HashMap();

  private String bizyear = "";

  private String starperiod = "";

  private String endperiod = "";

  private String office = "";

  private String col = "";

  private String row = "";

  private String tablename = "";

  private String yearmonth = "";

  public String getYearmonth() {
    return yearmonth;
  }

  public void setYearmonth(String yearmonth) {
    this.yearmonth = yearmonth;
  }

  public String getCol() {
    return col;
  }

  public void setCol(String col) {
    this.col = col;
  }

  public String getRow() {
    return row;
  }

  public void setRow(String row) {
    this.row = row;
  }

  public String getTablename() {
    return tablename;
  }

  public void setTablename(String tablename) {
    this.tablename = tablename;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getBizyear() {
    return bizyear;
  }

  public void setBizyear(String bizyear) {
    this.bizyear = bizyear;
  }

  public String getEndperiod() {
    return endperiod;
  }

  public void setEndperiod(String endperiod) {
    this.endperiod = endperiod;
  }

  public String getStarperiod() {
    return starperiod;
  }

  public void setStarperiod(String starperiod) {
    this.starperiod = starperiod;
  }

  public Object getValue(String key) {
    return map.get(key);
  }

  public void setValue(String key, Object value) {
    map.put(key, value);
  }
}
