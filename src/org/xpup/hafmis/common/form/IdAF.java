package org.xpup.hafmis.common.form;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class IdAF extends ActionForm {

  private static final long serialVersionUID = 174372043889066259L;

  //µ¥Ñ¡
  private Serializable id = null;
  
  //¸´Ñ¡
  private String[] rowArray=new String[0];

  public Serializable getId() {
    return id;
  }

  public void setId(Serializable id) {
    this.id = id;
  }

  public String[] getRowArray() {
    return rowArray;
  }

  public void setRowArray(String[] rowArray) {
    this.rowArray = rowArray;
  }
  
  

}
