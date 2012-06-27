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
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.form.DevelopNewAF;

/**
 * 开发商维护的探出框Action
 * 
 * @author 付云峰
 */
public class DevelopTbWindowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    DevelopNewAF developNewAF = new DevelopNewAF();
    List officeList = null;
    try {
      // 得到所选开发商在PL005中的主键
      String id = request.getParameter("id");

      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      // 取出用户权限办事处,显示在下拉菜单中
      List temp_officeList = securityInfo.getOfficeList();
      officeList = new ArrayList();
      OfficeDto officedto = null;
      Iterator it = temp_officeList.iterator();
      while (it.hasNext()) {
        officedto = (OfficeDto) it.next();
        officeList.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      Developer developer = developBS.findDeveloperInfo(new Integer(id));
      developNewAF.setDeveloper(developer);
      developNewAF.setType_window("2");
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    request.setAttribute("officeList", officeList);
    request.setAttribute("developNewAF", developNewAF);
    return mapping.findForward("to_develop_find");
  }

}
