package org.xpup.hafmis.sysloan.loancallback.bankexports.action;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.common.util.BusiTools;

public class GetDayListAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
   try{   
     response.setContentType("text/xml; charset=utf-8");
     PrintWriter out = response.getWriter();
     out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
     out.println("<options>");
     String fatherValue=request.getParameter("fatherValue");
     List dayList=BusiTools.getDayList(fatherValue);
     for(int i=0;i<dayList.size();i++){
       out.println("<value>"+dayList.get(i)+"</value>");
       out.println("<text>"+dayList.get(i)+"</text>");
     }

     out.println("</options>");
     out.flush();
     out.close();
   }catch(Exception e){
     e.printStackTrace();
   }
    return null;
  }
}
