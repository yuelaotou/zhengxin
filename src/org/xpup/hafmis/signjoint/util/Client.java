package org.xpup.hafmis.signjoint.util;
import java.io.*;
import java.net.*;
/**
 * ≤‚ ‘π§æﬂ¿‡
 * @author Administrator
 *
 */

public class Client extends Thread{

  public Client(InetAddress addr) throws IOException
  {
    //BufferedReader in =null;
    PrintWriter out =null;
    BufferedReader in=null;
    Socket sk=null;
    try
    {
      sk= new Socket(addr,10003);
      InputStreamReader isr;
      isr=new InputStreamReader(sk.getInputStream());
      in=new BufferedReader(isr);
      out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(sk.getOutputStream())),true);
      out.println("1001|3|yinchao|21020000000|12345|");
      System.out.println("post: 1001|3|yinchao|21020000000|12345|");
      
      int temp;


        while(!in.ready())
        {
          
          
        }
        String str=in.readLine();      
        System.out.println(str);

        File f =new File("aa.txt");
        FileOutputStream o=new FileOutputStream(f);
        BufferedOutputStream bout=new BufferedOutputStream(o);
       // while((temp=in.read())!=-1) bout.write(temp);
        ;
        bout.write(str.getBytes());
        bout.close();

    }
    catch(IOException e)
    {
      e.printStackTrace();
    } 
    
  }

  public static void main(String [] args) throws UnknownHostException, IOException
  {

    InetAddress addr = InetAddress.getLocalHost();
    new Client(addr);

  }
}
