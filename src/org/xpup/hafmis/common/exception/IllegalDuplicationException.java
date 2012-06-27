package org.xpup.hafmis.common.exception;

import org.xpup.common.exception.BusinessException;


/**
 * 非法的重复异常。<br>
 * 主要用于描述在数据处理过程中，发生以下情况之一：
 * <li>数据已经存在于系统中
 * <li>数据中的部分不允许重复的项被检查出有重复<br>
 * 则抛出此异常。
 */
public class IllegalDuplicationException extends BusinessException {

  private static final long serialVersionUID = -2872718954679760392L;

  /**
   * 构造一个非法的重复异常。
   * 
   * @param message 异常的描述信息。
   * @param cause 引起该异常的原始异常。
   */
  public IllegalDuplicationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * 构造一个非法的重复异常。
   * 
   * @param message 异常的描述信息。
   */
  public IllegalDuplicationException(String message) {
    super(message);
  }

  /**
   * 构造一个非法的重复异常。
   * 
   * @param cause 引起该异常的原始异常。
   */
  public IllegalDuplicationException(Throwable cause) {
    super(cause);
  }
}
