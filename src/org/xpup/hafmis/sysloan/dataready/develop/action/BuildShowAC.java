/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysloan.dataready.develop.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.dto.BuildDTO;
import org.xpup.hafmis.sysloan.dataready.develop.form.BuildAF;
import org.xpup.hafmis.sysloan.dataready.develop.form.BuildFindAF;

/**
 * MyEclipse Struts Creation date: 06-20-2008 XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class BuildShowAC extends Action {
  /*
   * Generated Methods
   */

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.dataready.develop.action.BuildShowAC";

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
    ActionMessages messages = null;
    try {

      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      // pagination.getQueryCriterions().put("id",
      // (String)request.getSession().getAttribute("FLOOR_ID1"));
      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());

      if (request.getAttribute("developerId") != null) {
        String developerId = (String) request.getAttribute("developerId");
        String developerName = (String) request.getAttribute("developerName");
        String floorNum = (String) request.getAttribute("floorNum");
        String floorName = (String) request.getAttribute("floorName");

        // String headId =
        // (String)request.getSession().getAttribute("HEAD_ID1");
        String floorId = (String) request.getSession()
            .getAttribute("FLOOR_ID1");

        floorNum = developBS.findFloornum((String) pagination
            .getQueryCriterions().get("id"));
        floorName = developBS.findFloorname((String) pagination
            .getQueryCriterions().get("id"));
        System.out.println("floorNum==" + floorNum + "----floorName="
            + floorName + "----floorId=" + floorId);

        BuildDTO buildDTO = new BuildDTO();
        buildDTO.setDeveloperId(developerId);
        buildDTO.setDeveloperName(developerName);
        buildDTO.setFloorNum(floorNum);
        buildDTO.setFloorName(floorName);
        buildDTO.setFloorId(floorId);
        request.getSession().setAttribute("buildInfo_1", buildDTO);
      }

      List list = developBS.findBuildList(pagination);

      BuildAF buildAF = new BuildAF();
      BuildFindAF buildFindAF = new BuildFindAF();
      buildAF.setList(list);
      buildFindAF.setBuildDTO((BuildDTO) request.getSession().getAttribute(
          "buildInfo_1"));

      request.setAttribute("buildAF", buildAF);
      request.setAttribute("buildFindAF", buildFindAF);

      if (request.getAttribute("errorMessages1") != null) {
        messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("����ʧ�ܣ�"
            + (String) request.getAttribute("errorMessages1"), false));
        saveErrors(request, messages);
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("buildShowAC");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "t.build_id", "DESC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}