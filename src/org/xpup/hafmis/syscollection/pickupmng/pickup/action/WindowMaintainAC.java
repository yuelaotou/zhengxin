package org.xpup.hafmis.syscollection.pickupmng.pickup.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.PickTail;
import org.xpup.hafmis.syscollection.pickupmng.pickup.bsinterface.IPickupBS;
import org.xpup.hafmis.syscollection.pickupmng.pickup.dto.ApplyBookDTO;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickGetEmployeeInfoAF;
import org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupGetCompanyIdAF;
import org.xpup.hafmis.syscollection.systemmaintain.loanpara.bsinterface.ILoanDocNumDesignBS;

public class WindowMaintainAC extends LookupDispatchAction {
  public ActionForward printdoc(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    // System.out.println("经过打印的jsp");

    String pzempid = "";
    try {
      PickGetEmployeeInfoAF pickGetEmployeeInfoAF = (PickGetEmployeeInfoAF) form;
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      ILoanDocNumDesignBS loanDocNumDesignBS = (ILoanDocNumDesignBS) BSUtils
          .getBusinessService("loanDocNumDesignBS", this, mapping
              .getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      PickupGetCompanyIdAF af = (PickupGetCompanyIdAF) request.getSession()
          .getAttribute("orgList");
      pzempid = pickGetEmployeeInfoAF.getIid().toString();
      ApplyBookDTO dto = (ApplyBookDTO) request.getSession().getAttribute(
          "applys");
      List list = (List) request.getSession().getAttribute("employees");
      Date bizdate = BusiTools.stringToUDate(securityInfo.getUserInfo()
          .getBizDate(), "yyyyMMdd");
      String date = BusiTools.dateToString(bizdate, "yyyy-MM-dd");
      List print = pbs.getPrintAllEmployeeList(af.getId());// 获得打印的集合
      af.setList(print);
      String userName = "";
      try {
        String name = loanDocNumDesignBS.getNamePara();

        if (name.equals("1")) {
          userName = securityInfo.getUserName();
        } else {
          userName = securityInfo.getRealName();
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
      PickTail pick = null;
      if (list.size() > 0) {
        pick = (PickTail) list.get(0);
      }
      dto.setOperater(userName);
      dto.setEmpid(pzempid);
      request.setAttribute("apply", dto);
      request.setAttribute("employee", list);
      request.setAttribute("date", date);
    } catch (Exception s) {
      s.printStackTrace();
    }

    return new ActionForward("/printDoc_yg.jsp");

  }

  public ActionForward scan(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    String path;
    PictureUpload pu = new PictureUpload();
    try {
      String headId = (String) request.getSession().getAttribute("headId");
      request.getSession().setAttribute("headId", headId);
      PickGetEmployeeInfoAF pickGetEmployeeInfoAF = (PickGetEmployeeInfoAF) form;
      ApplyBookDTO dto = (ApplyBookDTO) request.getSession().getAttribute(
          "applys");
      SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
      String pzempid = pickGetEmployeeInfoAF.getIid().toString();
      String serverPath = BusiConst.UPLOAD_SERVER_PATH;
      path = pu.upload(securityInfo.getUserInfo().getUserIp(), "picture",
          serverPath);
      pu.delete(securityInfo.getUserInfo().getUserIp(), "picture");
      IPickupBS pbs = (IPickupBS) BSUtils.getBusinessService("pickupBS", this,
          mapping.getModuleConfig());
      pbs.updatePickHead(dto.getHeadid(), pzempid, path);
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扫描完成",
          false));
      saveErrors(request, messages);
    } catch (IOException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扫描失败",
          false));
      saveErrors(request, messages);
      e.printStackTrace();
    } catch (BusinessException e) {
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("srcan_jsp");
  }

  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.print.doc", "printdoc");
    map.put("button.scan", "scan");

    return map;
  }
}
