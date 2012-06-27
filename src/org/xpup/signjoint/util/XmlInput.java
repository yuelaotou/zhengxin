package org.xpup.signjoint.util;

import java.io.*;
import javax.xml.parsers.*;
import org.xpup.signjoint.dto.ConfigDTO;
import java.io.*;
import org.w3c.dom.*;
/**
 * 
 * @author yinchao
 * 设置XML文档配置
 */
public class XmlInput {

  private DocumentBuilderFactory factory = null;
  private DocumentBuilder builder=null;
  private Document doc=null;
  private String port=null;
  private String mark=null;
  public XmlInput()
  {
    try {
      factory = DocumentBuilderFactory.newInstance();
      builder = factory.newDocumentBuilder();
      doc = builder.parse(new File("Config.xml"));//加载配置文档
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  /**
   * 
   * @param content
   * 设置配置文档信息
   */
  public void setConfig(ConfigDTO content)
  {
    Element root =doc.getDocumentElement();
    NodeList nl=root.getChildNodes();
    nl.item(0).setNodeValue(Integer.toString(content.getPort()));
    nl.item(1).setNodeValue(content.getMark());
    nl.item(2).setNodeValue(Integer.toString(content.getThreadnum()));
  }

}
