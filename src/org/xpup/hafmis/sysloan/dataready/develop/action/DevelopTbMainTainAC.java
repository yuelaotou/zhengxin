package org.xpup.hafmis.sysloan.dataready.develop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcifs.smb.PictureUpload;

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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO;
import org.xpup.hafmis.sysloan.dataready.develop.form.DevelopNewAF;

/**
 * 开发商维护的分发Action
 * 
 * @author 付云峰
 */
public class DevelopTbMainTainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    // TODO Auto-generated method stub
    Map map = new HashMap();
    map.put("button.update", "showDevelopInfo");
    map.put("button.canceled", "blankOutDevelop");
    map.put("button.canceled.quash", "quashBlankOutDevelop");
    map.put("button.print", "printDevelopInfo");
    map.put("button.floor", "showFloorInfo");
    map.put("button.delete", "delDevelopInfo");
    map.put("button.develop.add", "add");
    map.put("button.scan", "scan");
    return map;
  }

  /**
   * 显示将要修改的开发商信息
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward showDevelopInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    saveToken(request);
    DevelopNewAF developNewAF = new DevelopNewAF();
    List officeList = null;
    Developer developer = null;
    try {
      // 得到所选开发商在PL005中的主键
      IdAF idAF = (IdAF) form;

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

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      developer = developBS.findDeveloperInfo(new Integer(idAF.getId()
          .toString()));
      developNewAF.setDeveloper(developer);
      developNewAF.setType_button("1");
    } catch (Exception e) {

      e.printStackTrace();
    }
    // 将要修改的developer放入session
    request.getSession().setAttribute("developer_FYF", developer);
    request.setAttribute("officeList", officeList);
    request.setAttribute("developNewAF", developNewAF);
    return mapping.findForward("to_develop_modify");
  }

  /**
   * 开发商作废的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward blankOutDevelop(ActionMapping mapping, ActionForm form,
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
        // 得到所选开发商在PL005中的主键
        IdAF idAF = (IdAF) form;

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
            "developBS", this, mapping.getModuleConfig());

        // 将状态变为正常
        developBS.modifyDeveloperST(new Integer(idAF.getId().toString()), "1",
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

    return mapping.findForward("developTbShowAC");
  }

  /**
   * 开发商取消作废的方法
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward quashBlankOutDevelop(ActionMapping mapping,
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
        // 得到所选开发商在PL005中的主键
        IdAF idAF = (IdAF) form;

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");
        IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
            "developBS", this, mapping.getModuleConfig());

        // 将状态变为正常
        developBS.modifyDeveloperST(new Integer(idAF.getId().toString()), "0",
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

    return mapping.findForward("developTbShowAC");
  }

  /**
   * 打印的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward printDevelopInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    List list = null;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
      .getAttribute("SecurityInfo");
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          DevelopTbShowAC.PAGINATION_KEY);

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      list = developBS.findDevelopPrintList_print(pagination, securityInfo);
      
      // 得到操作员
      String userName = securityInfo.getUserInfo().getRealName();
      // 得到会计日期
      String bizDate = securityInfo.getUserInfo().getPlbizDate();
      request.setAttribute("userName", userName);
      request.setAttribute("bizDate", bizDate);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    // 打印返回的URL
    String url = "developTbShowAC.do";
    request.setAttribute("developTbListDTOList", list);
    request.setAttribute("url", url);
    return mapping.findForward("to_develop_print");
  }

  /**
   * 显示楼盘信息的方法
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward showFloorInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      // 得到所选开发商在PL005中的主键
      IdAF idAF = (IdAF) form;

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      FloorDevelopInfoDTO floorDevelopInfoDTO = developBS
          .showFloorDevelopInfo(new Integer(idAF.getId().toString()));

      // 将查出的内容放入session用于在列表中显示
      request.getSession()
          .setAttribute("floorDevelopInfo", floorDevelopInfoDTO);
      // request.getSession().setAttribute("HEAD_ID1", idAF.getId().toString());
      request.setAttribute("HEAD_ID", idAF.getId().toString());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    } finally {
      request.getSession().removeAttribute(FloorShowAC.PAGINATION_KEY);
    }
    return mapping.findForward("floorShowAC");
  }


  public ActionForward delDevelopInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    try {
      // 得到所选开发商在PL005中的主键
      IdAF idAF = (IdAF) form;
      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      developBS.delDevelopInfo(idAF.getId());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    } 
    return mapping.findForward("developTbShowAC");
  }
  public ActionForward add(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("developTaForwardAC");
  }
  public ActionForward scan(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    PictureUpload pu = new PictureUpload();
    String path;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String serverPath = BusiConst.UPLOAD_SERVER_PATH;
      path = pu.upload(securityInfo.getUserInfo().getUserIp(), "picture",
          serverPath);
      pu.delete(securityInfo.getUserInfo().getUserIp(), "picture");
      IdAF idAF = (IdAF) form;
      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      Integer id = Integer.valueOf(idAF.getId().toString());
      developBS.updateDeveloperInfo(id, path);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扫描完成",
          false));
      saveErrors(request, messages);
    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扫描失败", false));
      saveErrors(request, messages);
    }
    return mapping.findForward("developTbShowAC");
  }
}
