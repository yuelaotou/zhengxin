/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.loanapply.specialapply.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface.ILoanapplyBS;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.FloorListDTO;

/**
 * MyEclipse Struts Creation date: 10-05-2007 XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class SpecialapplyTaFindFloorAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    try {
      response.setContentType("text/xml; charset=utf-8");
      PrintWriter out = response.getWriter();
      out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");

      out.println("<options>");

      String headId = request.getParameter("headId");

      ILoanapplyBS loanapplyBS = (ILoanapplyBS) BSUtils.getBusinessService(
          "loanapplyBS", this, mapping.getModuleConfig());

      List list = loanapplyBS.findFloorList(headId);
      for (int i = 0; i < list.size(); i++) {
        FloorListDTO dto = (FloorListDTO) list.get(i);
        out.println("<value>" + dto.getFloorValue() + "</value>");
        out.println("<text>" + dto.getFloorValue() + "</text>");
      }

      out.println("</options>");

      out.flush();
      out.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}