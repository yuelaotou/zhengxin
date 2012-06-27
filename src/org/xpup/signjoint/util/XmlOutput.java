package org.xpup.signjoint.util;
import java.io.*;
import javax.xml.parsers.*;
import org.xpup.signjoint.dto.ConfigDTO;
import java.io.*;
import org.w3c.dom.*;
/**
 * 
 * @author yinchao
 * 读取XML文档配置
 */
public class XmlOutput {
  private DocumentBuilderFactory factory = null;
  private DocumentBuilder builder=null;
  private Document doc=null;
  private String port=null;
  private String mark=null;
  private String threadnum=null;
  
  public XmlOutput()
  {
    try {
      factory = DocumentBuilderFactory.newInstance();
      builder = factory.newDocumentBuilder();
      doc = builder.parse(new File("Config.xml"));//加载配置文档
    } catch (Exception e) {
      e.printStackTrace();
    }
    Element root =doc.getDocumentElement();
    NodeList nl=root.getChildNodes();
    port=nl.item(0).getFirstChild().getNodeValue().trim();
    //System.out.println(port);
    mark=nl.item(1).getFirstChild().getNodeValue().trim();
    //System.out.println(mark);
    threadnum=nl.item(2).getFirstChild().getNodeValue().trim();
    //System.out.println(threadnum);
  }
  /**
   * 获取存储着配置信息的DTO对象
   * @return 存放着配置信息DTO
   */
  public ConfigDTO getConfig()
  {
    ConfigDTO content = new ConfigDTO();
    content.setMark(mark);
    content.setPort(Integer.parseInt(port));
    content.setThreadnum(Integer.parseInt(threadnum));
    return content;  
  }

}
