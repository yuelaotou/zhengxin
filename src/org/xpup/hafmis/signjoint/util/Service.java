package org.xpup.hafmis.signjoint.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.xpup.hafmis.signjoint.util.SignTools;
import javax.servlet.ServletContext;

import org.xpup.hafmis.signjoint.task.ServerTask;
/**
 * 线程启动类
 * @author yinchao
 *
 */
public class Service extends Thread{

  private IThreadPool pool=null;//线程池
  private ServerSocket server=null;//server套接字
  private ServletContext sc=null;
  public Service(ServletContext sc)
  {
    this.sc=sc;
    SignTools.setServletContext(sc);
    BatchSignTools.setRequestFile(sc.getRealPath("/"));
    BatchSignTools.setResponsepath(sc.getRealPath("/"));
    System.out.println("The realpath is "+sc.getRealPath("/"));
  }
  /**
   * 覆盖Thread类中的方法，调用execute
   */
  public void run() {
    execute();
  }
  
  /**
   * 启动服务的方法
   */
  public void execute()
  {
    server=ServerConnect.getServerConnect();
    pool= new ThreadPool();

    while(true)
    {
      Socket sk=null;
      try{
        if((server!=null)&&(!server.isClosed()))
        {
          sk=server.accept();
          pool.execute(new ServerTask(sk,sc));
        }
        else
        {
          break; 
        }
      }
      catch(IOException ie)
      {
        //ie.printStackTrace();
      } 
    }//while
    

  }
  
  /**
   * 释放资源
   */
  public void destroyService()
  {
    try { 
      pool.join();
      if(!server.isClosed())
       server.close();
    } catch (Exception e) {}
  
  }



}