package org.xpup.hafmis.sysloan.dataready.develop.action;

import java.io.IOException;
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
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.form.DevelopNewAF;

/**
 * 添加开发商用到的MainTainAction，主要方法有添加，修改，扫描
 * 
 * @author 付云峰
 */
public class DevelopTaMainTainAC extends LookupDispatchAction {

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.edit", "modifyDevelopInfo");
    map.put("button.sure", "saveDevelopInfo");
    map.put("button.scan", "scan");
    map.put("button.back", "back");
    return map;
  }

  /**
   * 添加开发商信息的Action
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward saveDevelopInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    DevelopNewAF developNewAF = (DevelopNewAF) form;
    // 解决重复提交问题
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      try {
        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
            "developBS", this, mapping.getModuleConfig());
        developBS.saveDevelopInfo(developNewAF.getDeveloper(), securityInfo);

      } catch (Exception e) {

        saveToken(request);
        e.printStackTrace();
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
            "添加开发商失败！", false));
        saveErrors(request, messages);
        return mapping.findForward("developTaShowAC");
      }
    }
    return mapping.findForward("developTbShowAC");
  }

  public ActionForward back(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return mapping.findForward("developTbShowAC");
  }

  /**
   * 修改开发商信息的Action
   * 
   * @param mapping
   * @param form
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public ActionForward modifyDevelopInfo(ActionMapping mapping,
      ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    if (!isTokenValid(request, true)) {
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要重复提交！",
          false));
      saveErrors(request, messages);
      saveToken(request);
    } else {
      DevelopNewAF developNewAF = (DevelopNewAF) form;
      try {

        // 从session中取出被修改的Developer
        Developer old_developer = (Developer) request.getSession()
            .getAttribute("developer_FYF");

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
            "developBS", this, mapping.getModuleConfig());

        developBS.modifyDeveloperInfo(developNewAF.getDeveloper(),
            old_developer, securityInfo);

      } catch (Exception e) {
        saveToken(request);
        e.printStackTrace();
        ActionMessages messages = new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改失败！",
            false));
        saveErrors(request, messages);
        /*
         * 一下代码为保证在修改失败后保存当前页面的状态以便继续修改
         */
        developNewAF = new DevelopNewAF();

        SecurityInfo securityInfo = (SecurityInfo) request.getSession()
            .getAttribute("SecurityInfo");

        // 取出用户权限办事处,显示在下拉菜单中
        List temp_officeList = securityInfo.getOfficeList();
        List officeList = new ArrayList();
        OfficeDto officedto = null;
        Iterator it = temp_officeList.iterator();
        while (it.hasNext()) {
          officedto = (OfficeDto) it.next();
          officeList.add(new org.apache.struts.util.LabelValueBean(officedto
              .getOfficeName(), officedto.getOfficeCode()));
        }

        Developer developer = (Developer) request.getSession().getAttribute(
            "developer_FYF");

        developNewAF.setDeveloper(developer);
        developNewAF.setType_button("1");
        request.setAttribute("officeList", officeList);
        request.setAttribute("developNewAF", developNewAF);

        return mapping.findForward("to_develop_modify");
      } finally {
        // 修改结束后清空session,在点后退键时会找不到此session的值
        // request.getSession().removeAttribute("developer_FYF");
      }
    }
    return mapping.findForward("developTbShowAC");
  }

  public ActionForward scan(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    PictureUpload pu = new PictureUpload();
    String path;
    try {
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String serverPath = BusiConst.UPLOAD_SERVER_PATH;
      path = pu.upload(securityInfo.getUserInfo().getUserIp(), "picture",
          serverPath);
      pu.delete(securityInfo.getUserInfo().getUserIp(), "picture");
      Developer old_developer = (Developer) request.getSession().getAttribute(
          "developer_FYF");
      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      Integer id = new Integer(old_developer.getId().toString());
      developBS.updateDeveloperInfo(id, path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return mapping.findForward("developTbShowAC");
  }

}
