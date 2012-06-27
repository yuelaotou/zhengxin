package org.xpup.hafmis.signjoint.util;

/**
 * 连接池接口
 */
public interface IThreadPool {
  /**运行任务*/
  public void execute(Runnable task) ;
  /**获得当前任务*/
  public Runnable getTask()throws InterruptedException;
  /**强制关闭*/
  public void close();
  /**等待全部任务完成后关闭*/
  public void join();
  
}
