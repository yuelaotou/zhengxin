package org.xpup.hafmis.demo.action;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.BusiConst;
public class ChildXmlAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
   try{
    response.setContentType("text/xml; charset=utf-8");
    PrintWriter out = response.getWriter();
    out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    out.println("<options>");
   
    if ("1".equals(request.getParameter("fatherValue"))) {
      Map mapsex=BusiTools.listBusiProperty(BusiConst.SEX);   
        Iterator itsex=mapsex.keySet().iterator();
        while(itsex.hasNext()){
          Object objsex=(Object)itsex.next();
          out.println("<value>"+Integer.parseInt(objsex.toString())+"</value>");
          out.println("<text>"+mapsex.get(new Integer(objsex.toString()))+"</text>");    
      }
    }
    
    if ("2".equals(request.getParameter("fatherValue"))) {
      Map mapmarry=BusiTools.listBusiProperty(BusiConst.MARRY_STATUS);
        Iterator itmarry=mapmarry.keySet().iterator();
        while(itmarry.hasNext()){
          Object objmarry=(Object)itmarry.next();
          out.println("<value>"+Integer.parseInt(objmarry.toString())+"</value>");
          out.println("<text>"+mapmarry.get(new Integer(objmarry.toString()))+"</text>");
        }     
    }

    out.println("</options>");
    
    out.flush();
    out.close();
    
    System.out.println(request.getParameter("fatherValue"));
   }catch(Exception e){
     e.printStackTrace();
   }
    return null;
  }
}
