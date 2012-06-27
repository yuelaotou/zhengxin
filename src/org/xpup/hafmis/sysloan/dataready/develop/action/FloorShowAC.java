package org.xpup.hafmis.sysloan.dataready.develop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.sysloan.dataready.develop.bsinterface.IDevelopBS;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO;
import org.xpup.hafmis.sysloan.dataready.develop.dto.FloorListDTO;
import org.xpup.hafmis.sysloan.dataready.develop.form.FloorFindAF;

/**
 * 显示楼盘信息的Action
 * 
 * @author 付云峰
 */
public class FloorShowAC extends Action {

  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.dataready.develop.action.FloorShowAC";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub

    FloorFindAF floorFindAF = new FloorFindAF();
    saveToken(request);
    try {
      // 取出PL005的主键
      String id = (String) request.getAttribute("HEAD_ID");
      // 取出开发商的信息
      FloorDevelopInfoDTO floorDevelopInfoDTO = (FloorDevelopInfoDTO) request
          .getSession().getAttribute("floorDevelopInfo");

      String paginationKey = getPaginationKey();
      Pagination pagination = getPagination(paginationKey, request);
      PaginationUtils.updatePagination(pagination, request);
      if (id != null) {
        // 在添加楼盘信息时也会用到此id
        pagination.getQueryCriterions().put("id", id);
      }

      IDevelopBS developBS = (IDevelopBS) BSUtils.getBusinessService(
          "developBS", this, mapping.getModuleConfig());
      List totalList = developBS.findFloorList(pagination);
      if (totalList.size() != 0) {
        FloorListDTO dto = (FloorListDTO) totalList.get(0);
        if(dto.getFloorFlag().equals("1")) {
          floorFindAF.setFlag("1");
        }
      }
      floorFindAF.setList(totalList);
      floorFindAF.setFloorDevelopInfoDTO(floorDevelopInfoDTO);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    request.setAttribute("floorFindAF", floorFindAF);
    return mapping.findForward("to_floor_find");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 200, 1, "(p6.floor_addr||'-'||p6.floor_num)", "", new HashMap(
          0));
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

  protected String getPaginationKey() {
    return PAGINATION_KEY;
  }
}
