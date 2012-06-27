package org.xpup.common.exception;

/**
 * 实体未找到异常。主要用与描述在数据处理过程中，找不到需要的实体数据，以至于无法完成相应的操作。
 */
public class EntityNotFoundException extends BusinessException {

  private static final long serialVersionUID = 8134140267657909357L;

  /**
   * 构造一个实体未找到异常。
   * 
   * @param message 异常的描述信息。
   */
  public EntityNotFoundException(String message) {
    super(message);
  }

  /**
   * 构造一个实体未找到异常。
   * 
   * @param message 异常的描述信息。
   * @param cause 引起该异常的原始异常。
   */
  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * 构造一个实体未找到异常。
   * 
   * @param cause 引起该异常的原始异常。
   */
  public EntityNotFoundException(Throwable cause) {
    super(cause);
  }
}
