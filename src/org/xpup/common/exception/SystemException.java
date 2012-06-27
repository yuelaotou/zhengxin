package org.xpup.common.exception;

public class SystemException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 7515682258477031581L;

  public SystemException(String message) {
    super(message);
  }

  public SystemException(Throwable throwable) {
    super(throwable);
  }

  public SystemException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
