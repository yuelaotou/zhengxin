package org.xpup.signjoint.util;
import java.io.IOException;
import java.net.*;
import org.xpup.signjoint.dto.ConfigDTO;
/**
 * 单态类,维护一个单态的serversocket
 * @author yinchao
 *  
 */
public class ServerConnect {

  private static ServerSocket server = null;

  private ServerConnect()
  {}
  public static ServerSocket getServerConnect() 
  {
    ConfigDTO dto=SignTools.xo.getConfig();

    try
    {
     if(server==null){
      server=new ServerSocket(dto.getPort());
      System.out.println("ServerSocket is Openning...");
      System.out.println("The Socket's port is : "+dto.getPort());
      return server;
     }
     else{
      return server; 
     }
    }
    catch(IOException e)
    {
      //e.printStackTrace();  
    }
    return null;
  }
}
