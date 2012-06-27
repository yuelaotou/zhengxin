package org.xpup.hafmis.syscollection.tranmng.tranin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.tranmng.tranin.bsinterface.ITraninBS;
/**
 * Copy Right Information : 判断是否存在姓名不同但是身份证号相同的职工信息Action Goldsoft Project :
 * IsCardNumSameAAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.29
 */
public class IsCardNumSameAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    String flag = "";
    String message = "已经存在姓名不同但是身份证号相同的职工!";
    try {
      String empName = request.getParameter("empName");
      String cardNum = request.getParameter("cardNum");
      ITraninBS traninBS = (ITraninBS) BSUtils.getBusinessService("traninBS",
          this, mapping.getModuleConfig());
      List list = traninBS.isCardNumSame(empName, cardNum);
      if (list.size()>0) {
        flag = "0";
      }else{
        flag = "1";
      }
      String text = "";
      text = "display_FYF('"+ flag+ "','"+ message+"')";
      response.getWriter().write(text);
      response.getWriter().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
