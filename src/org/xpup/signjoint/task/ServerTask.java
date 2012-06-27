package org.xpup.signjoint.task;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;
import org.xpup.signjoint.dto.ConfigDTO;
import java.util.List;
import org.xpup.common.util.BSUtils;
import org.xpup.signjoint.bsinterface.ISignjointBS;
import org.xpup.signjoint.entity.Yctest;
import org.xpup.signjoint.util.SignTools;

/**
 * 任务类,调用业务借口
 * @author yinchao
 *
 */
public class ServerTask implements Runnable{
  private Socket sk=null;
  //private XmlOutput xo =null;
  public ServerTask(Socket sk)
  {
    this.sk=sk;
  }
  /**
   * 调用业务逻辑接口
   */
  public void run() { 
    BufferedReader in =null;
    PrintWriter out =null;
    try{
      InputStreamReader isr= new InputStreamReader(sk.getInputStream());
      in=new BufferedReader(isr);
      out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(sk.getOutputStream())),true);
      String temp=in.readLine();
      List list=SignTools.Compart(temp);//转换格式
      String operate=(String)list.get(0);
      if(operate.equalsIgnoreCase("10000"))
      {
        /*
        BSUtils.initApplicationContext("applicationContext-sign.xml");
        
        ISignjointBS bs = (ISignjointBS)BSUtils
        .getBusinessService("yctestBS");
        String id=(String)list.get(1);
        Yctest entity=bs.findDemoById(Integer.getInteger(id));
        System.out.print(entity.getName());*/

        System.out.println("request id is : "+list.get(1));
        System.out.println("Server receiveed"); 
        out.println("Server receiveed");


       /* List l2=new ArrayList();
        l2.add("shou");
        l2.add("dao");
        l2.add("le");
        System.out.println(SignTools.Combination(l2));
       */
      }
      if(out!=null)
       out.close();
      if(in!=null)
       in.close();
    }catch(Exception e)
    {
      e.printStackTrace();
    }


  }
}