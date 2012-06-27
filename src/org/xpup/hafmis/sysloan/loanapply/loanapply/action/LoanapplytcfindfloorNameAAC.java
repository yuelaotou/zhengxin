package org.xpup.hafmis.sysloan.loanapply.loanapply.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.FloorListDTO;

public class LoanapplytcfindfloorNameAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");

    String fname = "";
    String floorId = (String) request.getParameter("floorId");

    List floorList = (List) request.getSession().getAttribute("floorListAAC");
    for (int i = 0; i < floorList.size(); i++) {
      FloorListDTO fDto = (FloorListDTO) floorList.get(i);
      if (floorId.equals(fDto.getFloorKey())) {
        fname = fDto.getFloorValue();
      }

    }
    String text = "";
    text = "dispplayfloorName('" + fname + "');";
    try {
      response.getWriter().write(text);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      response.getWriter().close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
}
