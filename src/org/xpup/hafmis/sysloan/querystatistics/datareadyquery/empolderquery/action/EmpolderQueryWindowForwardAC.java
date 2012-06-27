package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.empolderquery.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO;

/**
 * 开发商查询弹出框ForwardAction
 * 
 * @author 付云峰
 */
public class EmpolderQueryWindowForwardAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    
    try {
      // 得到所选开发商在PL005中的主键
      String id = request.getParameter("id");

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      FloorDevelopInfoDTO floorDevelopInfoDTO = developBS
          .showFloorDevelopInfo(new Integer(id));

      // 将查出的内容放入session用于在列表中显示
      request.getSession()
          .setAttribute("floorDevelopInfo", floorDevelopInfoDTO);
      request.setAttribute("HEAD_ID", id);
      
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return mapping.findForward("empolderQueryWindowShowAC");
  }

}
