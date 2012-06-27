package org.xpup.signjoint.util;
import java.io.*;
import java.net.*;
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
      out.println("10000|2|");
      int temp;
      //StringBuffer sb =new StringBuffer();


        while(!in.ready())
        {
          
          
        }
        System.out.println(in.readLine());
      
      out.close();
      in.close();

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
