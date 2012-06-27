package org.xpup.signjoint.util;
import org.xpup.signjoint.util.SignTools;
import java.util.LinkedList;
/**
 * 线程池类
 */
public class ThreadPool extends ThreadGroup implements IThreadPool {
  private boolean isClosed=false;//线程池是否关闭
  private LinkedList workQueue;//表示工作队列
  private static int threadPoolID;//表示线程池ID
  private int threadID;//表示工作线程ID
  //private XmlOutput xo=new XmlOutput();
  public ThreadPool() { //poolSize指定线程池中的工作线程数目
    
    super("ThreadPool-" + (threadPoolID++));
    setDaemon(true);
    int poolsize=SignTools.xo.getConfig().getThreadnum();
    workQueue = new LinkedList();  //创建工作队列
    for (int i=0; i<poolsize; i++)
     new WorkThread().start();     //创建并启动工作线程}
  }
  
  /** 向工作队列中加入一个新任务，由工作线程去执行该任务 */
  public synchronized void execute(Runnable task) {
    if (isClosed) {      //线程池被关则抛出IllegalStateException异常
      throw new IllegalStateException();
    }
    if (task != null) {
      workQueue.add(task);
      notify();       //唤醒正在getTask()方法中等待任务的工作线程
    }
  }
    

  /** 
   * 从工作队列中取出一个任务，工作线程会调用此方法
   */
  public synchronized Runnable getTask()throws InterruptedException{
    while (workQueue.size() == 0) 
    {
      if (isClosed) return null;
      wait();       //如果工作队列中没有任务，就等待任务}
    }
    return ((Runnable)workQueue.removeFirst());
  }
  /**
   * 强制关闭
   *
   */
  
  public synchronized void close() {
    if (!isClosed) 
    {
      isClosed = true;
      workQueue.clear();//清空工作队列
      interrupt();//中断所有的工作线程，该方法继承自ThreadGroup类
    }
  }

  
  /**
   * 
   * 等待工作线程把所有任务执行完 
   */
  
  public void join() {
    synchronized (this) 
    {
      isClosed = true;
      notifyAll();//唤醒还在getTask()方法中等待任务的工作线程}
    }
  
    Thread[] threads = new Thread[activeCount()];//enumerate()方法继承自ThreadGroup类，获得线程组中当前所有活着的工作线程
    int count = enumerate(threads); 
    for (int i=0; i<count; i++) {//等待所有工作线程运行结束 
      try {
        threads[i].join();     //等待工作线程运行结束
      }catch(InterruptedException ex) { 
        ex.printStackTrace();  
      }
    }
  }
  
  /**
   * 内部工作线程类
   */
 private class WorkThread extends Thread {
    public WorkThread() {
      //加入到当前ThreadPool线程组中
      super(ThreadPool.this,"WorkThread-" + (threadID++));
      }

  public void run() {
    while (!isInterrupted()) {  //isInterrupted()方法继承自Thread类，判断线程是否被中断
      Runnable task = null;
      try {       //取出任务
       task = getTask();
      }catch (InterruptedException ex)
      {
       ex.printStackTrace();
      }        // 如果getTask()返回null或者线程执行getTask()时被中断，则结束此线程
      if (task == null) 
       return;
      try{ //运行任务，异常在catch代码块中捕获
       task.run();
      }catch (Throwable t){
       t.printStackTrace();
      }
    }      //#while
  }       //#run()
 }       //#WorkThread类

  
  
  
}
  
  
  
