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
import org.xpup.hafmis.sysfinance.bookmng.summary.bsinterface.ISummaryBS;
import org.xpup.hafmis.sysfinance.bookmng.summary.dto.SummaryDTO;
/**
 * 判断是否是末级科目代码
 * @author 刘冰 
 *
 */
public class CredenceFillinIsSummayAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo = (SecurityInfo) request.getSession()
    .getAttribute("SecurityInfo");
    String summay=request.getParameter("summay");
    String i=request.getParameter("i");
    String text="";
    try {
      ICredenceFillinBS credenceFillinBS = (ICredenceFillinBS) BSUtils
          .getBusinessService("credenceFillinBS", this, mapping
              .getModuleConfig());
      ISummaryBS summaryBS = (ISummaryBS) BSUtils.getBusinessService(
          "summaryBS", this, mapping.getModuleConfig());
      boolean cueAlert = credenceFillinBS.isExistSummay(summay, securityInfo);
      String bookId = securityInfo.getBookId();
      SummaryDTO summaryDTO = new SummaryDTO();
      summaryDTO.setBookId(bookId);
      summaryDTO.setParamExplain(summay);
      summaryDTO.setParamExplainPY(request.getParameter("pinyin").trim());
      if (!cueAlert) {
        summaryBS.insertSummaryInfo(summaryDTO, securityInfo);
      }
      cueAlert = true;
      response.setContentType("text/html;charset=UTF-8");
      text = "displaySummay('" + cueAlert + "','" + i + "')";
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

