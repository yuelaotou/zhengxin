package org.xpup.hafmis.syscollection.accountmng.accountopen.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.syscollection.accountmng.accountopen.bsinterface.IOrgOpenAccountBS;

/**
 * Copy Right Information : 判断是否存在姓名不同但是身份证号相同的职工信息Action Goldsoft Project :
 * IsCardNumSameAAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.1.25
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
      IOrgOpenAccountBS orgOpenAccountBS = (IOrgOpenAccountBS) BSUtils
          .getBusinessService("orgOpenAccountBS", this, mapping
              .getModuleConfig());
      List list = orgOpenAccountBS.isCardNumSame(empName, cardNum);
//      for (int i = 0; i < list.size(); i++) {
//        Object[] obj = new Object[2];
//        obj = (Object[]) list.get(i);
//        String name = obj[0].toString();
//        String id = obj[1].toString();
//        message = message+name +"/"+id+"; ";
//      }
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
