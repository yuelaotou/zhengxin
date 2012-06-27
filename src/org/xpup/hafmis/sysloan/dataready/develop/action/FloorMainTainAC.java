package org.xpup.hafmis.sysloan.dataready.develop.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO;
import org.xpup.hafmis.sysloan.dataready.develop.form.FloorFindAF;
import org.xpup.hafmis.sysloan.dataready.develop.form.FloorNewAF;

/**
 * 楼盘的分发Action
 * 
 * @author 付云峰
 */
public class FloorMainTainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub

    Map map = new HashMap();
    map.put("button.add", "showFloorInfoAddPage");
    map.put("button.update", "showFloorInfoModifyPage");
    map.put("button.back", "backDevelopList");
    map.put("button.canceled", "blankOutFloor");
    map.put("button.canceled.quash", "quashBlankOutFloor");
    map.put("button.delete", "delFloorInfo");
    map.put("button.print", "printFloorInfo");
    map.put("button.build", "build");
    return map;
  }

  public ActionForward delFloorInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      // 得到所选开发商在PL005中的主键
      IdAF idAF = (IdAF) form;
      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      System.out.println("猎取楼盘号=" + idAF.getId().toString());
      developBS.delFloorInfo(new Integer(idAF.getId().toString()));
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("floorShowAC");
  }

  public ActionForward build(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {

      IdAF idAF = (IdAF) form;
      // request.getSession().setAttribute("HEAD_ID1", idAF.getId().toString());
      // request.getSession().setAttribute("FLOOR_ID1", idAF.getId().toString());

      String developerId = (String) request.getParameter("developerId");
      String developerName = (String) request.getParameter("developerName");
      String floorNum = (String) request.getParameter("floorNum");
      String floorName = (String) request.getParameter("floorName");
      request.setAttribute("developerId", developerId);
      request.setAttribute("developerName", developerName);
      request.setAttribute("floorNum", floorNum);
      request.setAttribute("floorName", floorName);

      request.getSession().removeAttribute(BuildShowAC.PAGINATION_KEY);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("buildShowAC");
  }

  /**
   * 进入添加楼盘信息页的Action
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward showFloorInfoAddPage(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    saveToken(request);
    FloorNewAF floorNewAF = new FloorNewAF();
    FloorFindAF floorFindAF = new FloorFindAF();
    try {
      IdAF idAF = (IdAF) form;
      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      DevelopProject developProject = developBS
          .findDevelopProjectInfo(new Integer(idAF.getId().toString()));
      FloorDevelopInfoDTO floorDevelopInfoDTO = (FloorDevelopInfoDTO) request
          .getSession().getAttribute("floorDevelopInfo");
      floorFindAF.setFloorDevelopInfoDTO(floorDevelopInfoDTO);
      floorNewAF.getDevelopProject().setFloorName(developProject.getFloorName());
      Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
      floorNewAF.setRegionMap(regionMap);

    } catch (Exception e) {
      e.printStackTrace();
    }

    request.setAttribute("floorFindAF", floorFindAF);
    request.setAttribute("floorNewAF", floorNewAF);
    return mapping.findForward("to_floor_new");
  }

  /**
   * 进入修改页的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward showFloorInfoModifyPage(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    saveToken(request);
    try {
      IdAF idAF = (IdAF) form;
      FloorNewAF floorNewAF = new FloorNewAF();
      FloorFindAF floorFindAF = new FloorFindAF();

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      DevelopProject developProject = developBS
          .findDevelopProjectInfo(new Integer(idAF.getId().toString()));
      developProject.setBuildingAreas(developProject.getBuildingArea()
          .toString());
      Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
      floorNewAF.setRegionMap(regionMap);

      floorNewAF.setDevelopProject(developProject);
      floorNewAF.setType("1");

      FloorDevelopInfoDTO floorDevelopInfoDTO = (FloorDevelopInfoDTO) request
          .getSession().getAttribute("floorDevelopInfo");
      floorFindAF.setFloorDevelopInfoDTO(floorDevelopInfoDTO);

      request.setAttribute("floorFindAF", floorFindAF);
      request.setAttribute("floorNewAF", floorNewAF);
      // 将得到的开发商信息放到session中留做修改。
      request.getSession().setAttribute("developProject_FYF", developProject);
    } catch (Exception e) {

      e.printStackTrace();
    }
    return mapping.findForward("to_floor_new");
  }

  /**
   * 楼盘作废的Action
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward blankOutFloor(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        IdAF idAF = (IdAF) form;

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
            "developBS", this, mapping.getModuleConfig());

        developBS.modifyFloorST(new Integer(idAF.getId().toString()), "1",
            securityInfo);
      } catch (BusinessException bex) {

        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage(), false));
        saveErrors(request, messages);

      } catch (Exception e) {

        e.printStackTrace();
      }
    }
    return mapping.findForward("floorShowAC");
  }

  /**
   * 楼盘取消作废的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward quashBlankOutFloor(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        IdAF idAF = (IdAF) form;

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
            "developBS", this, mapping.getModuleConfig());

        developBS.modifyFloorST(new Integer(idAF.getId().toString()), "0",
            securityInfo);
      } catch (BusinessException bex) {

        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
            .getLocalizedMessage(), false));
        saveErrors(request, messages);

      } catch (Exception e) {

        e.printStackTrace();
      }
    }
    return mapping.findForward("floorShowAC");
  }

  /**
   * 打印楼盘信息
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printFloorInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String id = "";
    try {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          FloorShowAC.PAGINATION_KEY);

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      List list = developBS.findFloorList(pagination);
      request.setAttribute("floorListDTOList", list);

      // 得到PL005的主键
      if (pagination.getQueryCriterions().get("id") != null) {
        id = (String) pagination.getQueryCriterions().get("id");
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    // 打印返回的URL
    String url = "floorShowAC.do?HEAD_ID=" + id;
    request.setAttribute("url", url);
    return mapping.findForward("to_floor_print");
  }

  /**
   * 返回开发商列表的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward backDevelopList(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    return mapping.findForward("developTbShowAC");
  }

}
