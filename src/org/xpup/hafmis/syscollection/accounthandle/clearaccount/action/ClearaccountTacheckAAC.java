package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;
/**
 * 
 * 如果存在未进行转入确认的转出进行扎账，系统应给出提示
 * flag=0--无；flag=1--有
 *
 */
public class ClearaccountTacheckAAC extends Action{

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        try {
          String rowarray = request.getParameter("rowarray");
          String row = request.getParameter("row");
          String text ="";
          
          IclearAccountBS clearaccountBS = (IclearAccountBS) BSUtils.getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
          if(rowarray.equals("wl")){
            HttpSession session = request.getSession();
            List list = (List)session.getAttribute("clearaccountList");
            String[] rowArray=new String[list.size()];
            for(int i=Integer.parseInt(row);i<list.size();i++){
              rowArray[i]=list.get(i).toString();
              String flag=clearaccountBS.checktraininBytrainout(rowArray[i]);
              String trainoutorgid="";
              String traininorgid="";
              if(flag.equals("1")){
                trainoutorgid=clearaccountBS.getTrainoutorgid(rowArray[i]);
                traininorgid=clearaccountBS.getTraininorgid(rowArray[i]);
              }
              text = "display2('"+flag+"','"+trainoutorgid+"','"+traininorgid+"','"+row+"');";
              break;
            }
          }else{
            String flag=clearaccountBS.checktraininBytrainout(rowarray);
            String trainoutorgid="";
            String traininorgid="";
            if(flag.equals("1")){
              trainoutorgid=clearaccountBS.getTrainoutorgid(rowarray);
              traininorgid=clearaccountBS.getTraininorgid(rowarray);
            }
            text = "display('"+flag+"','"+trainoutorgid+"','"+traininorgid+"','"+row+"');";
          }
          response.getWriter().write(text); 
          response.getWriter().close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        return null; 
}

}