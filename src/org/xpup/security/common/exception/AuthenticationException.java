package org.xpup.security.common.exception;

import org.xpup.common.exception.BusinessException;

public class AuthenticationException extends BusinessException {

  private static final long serialVersionUID = 9161538390265387520L;

  public AuthenticationException(String message) {
    super(message);
  }

  public AuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }

  public AuthenticationException(Throwable cause) {
    super(cause);
  }

}
