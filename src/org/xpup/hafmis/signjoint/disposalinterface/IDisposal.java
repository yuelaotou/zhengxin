package org.xpup.hafmis.signjoint.disposalinterface;

import javax.servlet.ServletContext;

/**
 * 处理接口
 * 所有处理类都要依赖此接口
 * @author yinchao
 */
public interface IDisposal {
  //处理方法
  public String disposal(ServletContext sc);
}
