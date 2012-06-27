package org.xpup.hafmis.sysfinance.reportmng.financereport.definereport.form;

import org.apache.struts.action.ActionForm;
import java.util.HashMap;
import java.util.Map;

public class DefineReportAF extends ActionForm {
  private static final long serialVersionUID = 1L;
  
  private Map map = new HashMap();
  private String col="";
  private String row="";
  private String tablename="";

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

  public Object getValue(String key) {
    return map.get(key);
  }

  public void setValue(String key, Object value) {
    map.put(key, value);
  }

  public String getTablename() {
    return tablename;
  }

  public void setTablename(String tablename) {
    this.tablename = tablename;
  }
}
