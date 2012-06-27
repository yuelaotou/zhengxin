package org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credencefillin.bsinterface.ICredenceFillinBS;
/**
 * 判断是否是末级科目代码
 * @author 刘冰 
 *
 */
public class CredenceFillinIsEndSubjectAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String subjectCode=request.getParameter("subjectCode");
    String indexs=request.getParameter("indexs");
    String text="";
    try {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    ICredenceFillinBS credenceFillinBS=(ICredenceFillinBS) BSUtils.getBusinessService("credenceFillinBS", this, mapping
        .getModuleConfig());
    Object[] obj=new Object[3];
    obj=credenceFillinBS.isSubjectCodeEnd(subjectCode, securityInfo);
    String subjectId= obj[0].toString();
    String subjectName= obj[1].toString();
    String subjectCodeCount= obj[2].toString();
    response.setContentType("text/html;charset=UTF-8");
     text="displays('"+indexs+"','"+subjectId+"','"+subjectName+"','"+subjectCodeCount+"')";   
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

