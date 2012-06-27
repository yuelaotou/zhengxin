package org.xpup.hafmis.sysloan.dataready.develop.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.form.FloorFindAF;
import org.xpup.hafmis.sysloan.dataready.develop.form.FloorNewAF;

public class FloorSaveAC extends DispatchAction {

  /**
   * 添加楼盘信息的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward saveFloorInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        FloorNewAF floorNewAF = (FloorNewAF) form;
        DevelopProject developProject = floorNewAF.getDevelopProject();

        // 从pagination中取出PL005的id
        Pagination pagination = (Pagination) request.getSession().getAttribute(
            FloorShowAC.PAGINATION_KEY);
        String develop_id = (String) pagination.getQueryCriterions().get("id");

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
            "developBS", this, mapping.getModuleConfig());
        // 加入PL005的id
        developProject.setHeadId(develop_id);
        
        developBS.saveFloorInfo(developProject, securityInfo);

      } catch(BusinessException be){
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(be.getLocalizedMessage(),
            false));
        saveErrors(request, messages);
        be.printStackTrace();
        // 当出现异常时会跳转回添加页
        FloorNewAF floorNewAF = new FloorNewAF();
        FloorFindAF floorFindAF = new FloorFindAF();
        Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
        floorNewAF.setRegionMap(regionMap);
        request.setAttribute("floorNewAF", floorNewAF);
        request.setAttribute("floorFindAF", floorFindAF);
        return mapping.findForward("to_floor_new");
      } catch (Exception e) {
        // 解决反复添加失败时由于重复提交控制而引起的不能添加问题
        saveToken(request);
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败！",
            false));
        saveErrors(request, messages);
        e.printStackTrace();
        // 当出现异常时会跳转回添加页
        FloorNewAF floorNewAF = new FloorNewAF();
        FloorFindAF floorFindAF = new FloorFindAF();
        Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
        floorNewAF.setRegionMap(regionMap);
        request.setAttribute("floorNewAF", floorNewAF);
        request.setAttribute("floorFindAF", floorFindAF);
        return mapping.findForward("to_floor_new");
      }
    }
    return mapping.findForward("floorShowAC");
  }

  /**
   * 修改楼盘信息的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward modifyFloorInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    DevelopProject old_developProject = null;
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        // 从session中的到将要修改的楼盘信息
        old_developProject = (DevelopProject) request.getSession()
            .getAttribute("developProject_FYF");

        // 得到表单中修改后的信息
        FloorNewAF floorNewAF = (FloorNewAF) form;
        DevelopProject new_developProject = floorNewAF.getDevelopProject();

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
            "developBS", this, mapping.getModuleConfig());

        developBS.modifyFloorInfo(old_developProject, new_developProject,
            securityInfo);

      } catch (Exception e) {
        // 解决反复修改失败时由于重复提交控制而引起的不能修改问题
        saveToken(request);

        e.printStackTrace();

        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改失败！",
            false));
        saveErrors(request, messages);
        // 当出现异常时会跳转回修改页
        FloorNewAF floorNewAF = new FloorNewAF();
        floorNewAF.setDevelopProject(old_developProject);
        floorNewAF.setType("1");
        Map regionMap = BusiTools.listBusiProperty(BusiConst.INAREA);
        floorNewAF.setRegionMap(regionMap);

        request.setAttribute("floorNewAF", floorNewAF);
        return mapping.findForward("to_floor_new");

      } finally {
        // 修改结束后清空session,在点后退键时会找不到此session的值
        // request.getSession().removeAttribute("developProject_FYF");
      }
    }
    return mapping.findForward("floorShowAC");
  }

  /**
   * 返回楼盘信息列表
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward backFloorInfoList(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    return mapping.findForward("floorShowAC");
  }

}
