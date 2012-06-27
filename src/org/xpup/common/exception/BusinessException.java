package org.xpup.common.exception;

/**
 * 业务异常使用方法 对于因业务规则需要，由开发人员直接创建的业务异常，应由相关人员维护一个业务异常代码表。
 * 业务异常代码表主要包含错误代码及对应的信息,其中业务异常代码应为正值。
 * 
 * @author wangyh
 * @version 1.0
 */
public class BusinessException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 5087741407144981924L;

  /**
   * 构造一个业务异常。
   * 
   * @param message 异常的描述信息。
   */
  public BusinessException(String message) {
    super(message);
  }

  /**
   * 构造一个业务异常。
   * 
   * @param message 异常的描述信息。
   * @param cause 引起该异常的原始异常。
   */
  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * 构造一个业务异常。
   * 
   * @param cause 引起该异常的原始异常。
   */
  public BusinessException(Throwable cause) {
    super(cause);
  }
}
