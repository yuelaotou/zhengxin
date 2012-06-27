package org.xpup.hafmis.sysloan.dataready.bankpalindrome.dto;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
/**
 * 测试使用
 * @author Administrator
 *
 */
public class TestJDOM {

  public static void main(String[] args) {
    String rowNum = "5";//在此唯一未知可变的
    int intRowNum = Integer.parseInt(rowNum);
    Element rootElement = new Element("root");//(根元素)所有的XML元素都是 Element 的实例。根元素也不例外：）
    Document document = new Document(rootElement);//以根元素作为参数创建Document对象。一个Document只有一个根，即root元素。
    rootElement.setAttribute("file", "1");
    Element rootElement2 = new Element("file");//二级节点
    rootElement.setContent(rootElement2);//将次节点添加到根节点上
    Attribute attribute1 = new Attribute("name","OrgaddpayHeadImport");
    Attribute attribute2 = new Attribute("col",rowNum);//5列
    Attribute attribute3 = new Attribute("space","0");//距离空间
    Attribute attribute4 = new Attribute("row","2");//距离空间
    rootElement2.setAttribute(attribute1);//将属性添加到次节点上
    rootElement2.setAttribute(attribute2);
    rootElement2.setAttribute(attribute3);
    rootElement2.setAttribute(attribute4);
    
    for(int i=0;i<intRowNum;i++){
      Element rootElement3 = new Element("column");//三级节点
      rootElement2.addContent(rootElement3);
      Element rootElement4 = new Element("name");//四级节点
      rootElement3.addContent(rootElement4);
      rootElement4.addContent(new Integer(i+1).toString());
      Element rootElement4_ = new Element("typle");//平行的另一个四级节点
      rootElement3.addContent(rootElement4_);
      rootElement4_.addContent("java.lang.String");
    }
    XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
    try {
      xmlOut.output(document, System.out);//在控制台打印输出XML文件
      xmlOut.output(document, new FileWriter("c:/testJDOM.xml"));//在C盘下生成XML文件
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
