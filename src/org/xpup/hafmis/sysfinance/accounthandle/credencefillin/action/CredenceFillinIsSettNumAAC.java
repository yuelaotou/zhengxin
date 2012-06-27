package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
/**
 *判断结算号在FN201表中是否存在
 * @author 刘冰 
 *
 */
public class CredenceFillinIsSettNumAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String settNum=request.getParameter("settNum");
    String bookId=request.getParameter("bookId");
    String text="";
    try {
    ICredenceFillinBS credenceFillinBS=(ICredenceFillinBS) BSUtils.getBusinessService("credenceFillinBS", this, mapping
        .getModuleConfig());
    String cueAlert=credenceFillinBS.queryIsExistSettNum(settNum,bookId);
    response.setContentType("text/html;charset=UTF-8");
     text="displayss('"+cueAlert+"')";   
    response.getWriter().write(text);
    response.getWriter().close();

  }
    catch (BusinessException bex) {
      System.err.println(bex.getLocalizedMessage().toString());
     text="reportErrors('"+bex.getLocalizedMessage()+"')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch(Exception e){
      e.printStackTrace();
    }
    return null; 
  }
}

