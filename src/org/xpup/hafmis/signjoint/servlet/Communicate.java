package org.xpup.hafmis.signjoint.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.net.*;

import org.xpup.hafmis.signjoint.task.ServerTask;
import org.xpup.hafmis.signjoint.util.ServerConnect;
import org.xpup.hafmis.signjoint.util.Service;
import org.xpup.hafmis.signjoint.util.ThreadPool;


public class Communicate extends HttpServlet {




  Service service=null;
  
  /**
   * 随着servlet的初始化启动服务
   */
  
  public void init() throws ServletException{
    super.init();
    ServletContext sc=getServletContext();
    service=new Service(sc);
    service.start();   
  }
  


  /**
   * 释放资源
   */
  public void destroy() {
    
    service.destroyService();
    super.destroy();
  }

  /**
   * The doGet method of the servlet. <br>
   *
   * This method is called when a form has its tag value method equals to get.
   * 
   * @param request the request send by the client to the server
   * @param response the response send by the server to the client
   * @throws ServletException if an error occurred
   * @throws IOException if an error occurred
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
    out.println("<HTML>");
    out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
    out.println("  <BODY>");
    out.print("server is running");
    out.println("  </BODY>");
    out.println("</HTML>");
    out.flush();
    out.close();

    
    
    //doPost(request,response);
  }

  protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
    super.service(arg0, arg1);
  }

  /**
   * The doPost method of the servlet. <br>
   *
   * This method is called when a form has its tag value method equals to post.
   * 
   * @param request the request send by the client to the server
   * @param response the response send by the server to the client
   * @throws ServletException if an error occurred
   * @throws IOException if an error occurred
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


  }

  /**
   * Initialization of the servlet. <br>
   *
   * @throws ServletException if an error occure
   */

}
