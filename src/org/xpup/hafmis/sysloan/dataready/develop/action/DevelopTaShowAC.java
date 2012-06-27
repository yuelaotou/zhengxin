package org.xpup.hafmis.sysloan.dataready.develop.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.develop.form.DevelopNewAF;

/**
 * 显示添加开发商页的Action。
 * 
 * @author 付云峰
 */
public class DevelopTaShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub

    DevelopNewAF developNewAF = new DevelopNewAF();
    List officeList = null;
    saveToken(request);
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      // 取出用户权限办事处,显示在下拉菜单中
      List temp_officeList = securityInfo.getAllOfficeList();
      officeList = new ArrayList();
      OfficeDto officedto = null;
      Iterator it = temp_officeList.iterator();
      while (it.hasNext()) {
        officedto = (OfficeDto) it.next();
        officeList.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    request.setAttribute("developNewAF", developNewAF);
    request.setAttribute("officeList", officeList);
    return mapping.findForward("to_develop_new");
  }

}
