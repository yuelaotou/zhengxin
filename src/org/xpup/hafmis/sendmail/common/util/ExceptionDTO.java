package org.xpup.hafmis.sendmail.common.util;

import java.io.IOException;
import java.sql.SQLException;

import org.xpup.common.exception.BusinessException;

public class ExceptionDTO {

  Exception excep = null;

  BusinessException bx = null;

  SQLException sqe = null;

  IOException ioe = null;

  public BusinessException getBx() {
    return bx;
  }

  public void setBx(BusinessException bx) {
    this.bx = bx;
  }

  public Exception getExcep() {
    return excep;
  }

  public void setExcep(Exception excep) {
    this.excep = excep;
  }

  public IOException getIoe() {
    return ioe;
  }

  public void setIoe(IOException ioe) {
    this.ioe = ioe;
  }

  public SQLException getSqe() {
    return sqe;
  }

  public void setSqe(SQLException sqe) {
    this.sqe = sqe;
  }
}
