package org.xpup.security.common.exception;

import org.xpup.common.exception.BusinessException;

public class OperationDeniedException extends BusinessException {

  private static final long serialVersionUID = -2408336811080598594L;

  public OperationDeniedException(String message) {
    super(message);
  }

  public OperationDeniedException(String message, Throwable cause) {
    super(message, cause);
  }

  public OperationDeniedException(Throwable cause) {
    super(cause);
  }

}
