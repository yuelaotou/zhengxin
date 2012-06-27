package org.xpup.hafmis.signjoint.task;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;
import org.xpup.signjoint.dto.ConfigDTO;
import java.util.List;

import javax.servlet.ServletContext;

import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.util.SignTools;
import org.xpup.hafmis.signjoint.disposalinterface.IDisposal;
import org.xpup.hafmis.signjoint.util.SignjointFactory;
/**
 * 任务类,调用业务借口
 * @author yinchao
 */
public class ServerTask implements Runnable{
  private Socket sk=null;
  private ServletContext sc=null;
  //private XmlOutput xo =null;
  public ServerTask(Socket sk,ServletContext sc)
  {
    this.sk=sk;
    this.sc=sc;
  }
  /**
   * 调用业务逻辑接口
   * @throws IOException 
   */
  public void run() { 
    BufferedReader in =null;
    PrintWriter out =null;
    InputStreamReader isr=null;
    try{
      isr= new InputStreamReader(sk.getInputStream());
      in=new BufferedReader(isr);
      out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(sk.getOutputStream())),true);
      String s=SignTools.ReaderToString(in);              //in.readLine().trim();
      System.out.println("The request data is: "+s);
      System.out.println("The default encode is"+System.getProperty("file.encoding"));
      IDisposal dis=SignjointFactory.getDisposal(s);
      /*
      byte [] by=dis.disposal(sc).getBytes("GBK");
      out.print(new String(by,"ISO8859-1"));//回送结果
      */
      out.print(dis.disposal(sc));
      
    }catch(Exception e)
    {
      e.printStackTrace();
      out.print(SignTools.getInfo_05());//如果发生业务无关异常,返回05返回码
      if(out!=null)
        out.close();
      if(in!=null)
        try {
          in.close();
        } catch (IOException ie) {
          e.printStackTrace();
        }
    }finally
    {
      if(out!=null)
        out.close();
      if(in!=null)
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }//finally


  }//run
}