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
 *判断结算号在FN201表中是否存在
 * @author 刘冰 
 *
 */
public class CredenceFillinGetBalaDirAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    String subjectCode=request.getParameter("subjectCode");
    String office=request.getParameter("office");
    String text="";
    try {
    ICredenceFillinBS credenceFillinBS=(ICredenceFillinBS) BSUtils.getBusinessService("credenceFillinBS", this, mapping
        .getModuleConfig());
    Object[] obj = new Object[2];
    obj=credenceFillinBS.getBalanceDir(subjectCode, office, securityInfo);
    String balance=obj[0].toString();
    String direction=obj[1].toString();
    response.setContentType("text/html;charset=UTF-8");
     text="displayBalaDir('"+balance+"','"+direction+"')";   
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

