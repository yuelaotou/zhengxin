/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysfinance.accmng.subjectdayreport.action;

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
import org.xpup.hafmis.sysfinance.accmng.subjectdayreport.bsinterface.ISubjectdayreportBS;
import org.xpup.hafmis.sysfinance.accmng.subjectdayreport.form.SubjectdayreportAF;

/**
 * MyEclipse Struts Creation date: 10-26-2007 张列 XDoclet definition: Copy Right
 * Information : 科目日报表 Project : 文件名
 * 
 * @Version : 1.0
 * @author : 张列 生成日期 : 10-26-2007
 * @struts.action validate="true"
 */
public class SubjectdayreportShowAC extends Action {
  /*
   * Generated Methods
   */

  /**
   * Method execute
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return ActionForward
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // TODO Auto-generated method stub
    try {
      // 办事处放进 officeList 集合里
      List officeList1 = null;
      // 权限
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");

      ISubjectdayreportBS subjectdayreportBS = (ISubjectdayreportBS) BSUtils
          .getBusinessService("subjectdayreportBS", this, mapping
              .getModuleConfig());
      // bookId
      String bookId = securityInfo.getBookId();
      // 取出用户权限办事处,显示在下拉菜单中
      List officeList = securityInfo.getOfficeList();
      officeList1 = new ArrayList();
      OfficeDto officedto = null;
      List list = new ArrayList();
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
      request.setAttribute("officeList1", officeList1);
      // 取出科目级次，显示在下拉菜单中
      List paramValue1 = new ArrayList();
      int paramValue = Integer.parseInt(subjectdayreportBS
          .querySubjectdayreportParamValue(bookId));
      for (int i = 1; i <= paramValue; i++) {
        paramValue1.add(new org.apache.struts.util.LabelValueBean(i + "", i
            + ""));
      }
      request.setAttribute("paramValue1", paramValue1);

      if (request.getAttribute("subjectdayreportAF") != null) {
        SubjectdayreportAF subjectdayreportAF = (SubjectdayreportAF) request
            .getAttribute("subjectdayreportAF");
        String officeName = subjectdayreportAF.getOfficeName().trim();
        String subjectCodeStart = subjectdayreportAF.getSubjectCodeStart()
            .trim();
        String subjectCodeEnd = subjectdayreportAF.getSubjectCodeEnd().trim();
        String subjectLevelEnd = subjectdayreportAF.getSubjectLevelEnd().trim();
        String credenceDate = subjectdayreportAF.getCredenceDate().trim();

        if (officeName.equals("全部")) {
          officeName = "";
        }
        SubjectdayreportAF subjectdayreportAF1 = new SubjectdayreportAF();
        request.setAttribute("subjectdayreportAF", subjectdayreportAF1);
        //根据条件获得 全部数据
        list = subjectdayreportBS.findSubjectdayreportAllInfo(bookId,
            credenceDate, officeName, subjectCodeStart, subjectCodeEnd,
            subjectLevelEnd);
      }
      request.setAttribute("lists", list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_subjectdayreport_show");
  }
}