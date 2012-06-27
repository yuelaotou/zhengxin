/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysfinance.accmng.subjectmuster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accmng.subjectmuster.bsinterface.ISubjectmusterBS;
import org.xpup.hafmis.sysfinance.accmng.subjectmuster.form.SubjectmusterAF;

/** 
 * MyEclipse Struts
 * Creation date: 11-06-2007
 * Copy Right Information : 凭证汇总
 * Project : 文件名
 * @Version : 1.0
 * @author : 张列 生成日期 : 11-06-2007
 * XDoclet definition:
 * @struts.action path="/subjcetmusterFindAC" name="subjectmusterAF" scope="request" validate="true"
 */
public class CredenceMaxAAC extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("Cache-Control", "no-cache");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      ISubjectmusterBS subjectmusterBS = (ISubjectmusterBS) BSUtils
      .getBusinessService("subjectmusterBS", this, mapping
          .getModuleConfig());
      String type = request.getParameter("type");
      String yearmonth = request.getParameter("date");
      if(yearmonth.length()==5){
        yearmonth=yearmonth.substring(0,4)+"0"+yearmonth.substring(4,5);
      }
     
      String bookid = securityInfo.getBookId();
      String office = securityInfo.getUserInfo().getOfficeId().toString();
      String credenceNumStart = request.getParameter("credenceNumStart");
      String credenceNumEnd = request.getParameter("credenceNumEnd");
      String credenceMax=subjectmusterBS.queryMaxCredenceNum(office, yearmonth, bookid);
      if(type.equals("1")){
        if(Integer.parseInt(credenceMax)<Integer.parseInt(credenceNumStart)){
          String text="displays('1','"+credenceMax+"')";
          response.getWriter().write(text);
          response.getWriter().close();
        }else{
          String text="displays('4')";
          response.getWriter().write(text);
          response.getWriter().close();
        }
      }else{
        if(Integer.parseInt(credenceMax)<Integer.parseInt(credenceNumEnd)){
          String text="displays('2','"+credenceMax+"')";
          response.getWriter().write(text);
          response.getWriter().close();
        }else{
          String text="displays('4')";
          response.getWriter().write(text);
          response.getWriter().close();
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
		return mapping.findForward("subjectmusterShowAC");
	}
}