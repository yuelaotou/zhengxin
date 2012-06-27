package org.xpup.hafmis.sysloan.loanapply.specialapply.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.sysloan.common.domain.entity.DevelopProject;
import org.xpup.hafmis.sysloan.common.domain.entity.Developer;
import org.xpup.hafmis.sysloan.common.domain.entity.SpecialBorrower;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.FloorListDTO;
import org.xpup.hafmis.sysloan.loanapply.loanapply.dto.FloorNumListDTO;
import org.xpup.hafmis.sysloan.loanapply.specialapply.bsinterface.ISpecialapplyBS;
import org.xpup.hafmis.sysloan.loanapply.specialapply.form.SpecialapplyNewAF;

public class SpecialapplyTaShowAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // TODO Auto-generated method stub
    SpecialapplyNewAF specialapplyNewAF = new SpecialapplyNewAF();
    try {
      // 证件类型，下拉列表
      Map map = BusiTools.listBusiProperty(BusiConst.DOCUMENTSSTATE);
      specialapplyNewAF.setMap(map);
      ISpecialapplyBS specialapplyBS = (ISpecialapplyBS) BSUtils
          .getBusinessService("specialapplyBS", this, mapping.getModuleConfig());
      String id = "";
      SpecialBorrower specialBorrower = null;
      if (request.getAttribute("id") != null) {
        id = (String) request.getAttribute("id");
        specialBorrower = specialapplyBS
            .findSpecialBorrowerById(new Integer(id));
        specialapplyNewAF.setBorrowerName(specialBorrower.getBorrowerName());
        specialapplyNewAF.setCardKind(specialBorrower.getCardKind());
        specialapplyNewAF.setCardNum(specialBorrower.getCardNum());
        specialapplyNewAF.setLoanMoney(specialBorrower.getLoanMoney());
        specialapplyNewAF.setLoanTimeLimit(specialBorrower.getLoanTimeLimit());
        System.out.println(specialBorrower.getReserveaB()+"==============>");
        specialapplyNewAF.setSfbrzh(specialBorrower.getReserveaB());
        if (specialBorrower.getOrgId() != null
            && !"".equals(specialBorrower.getOrgId().toString())) {
          specialapplyNewAF.setOrgId(new Integer(specialBorrower.getOrgId()
              .toString()));
        }
        if (specialBorrower.getOrgName() != null
            && !"".equals(specialBorrower.getOrgName())) {
          specialapplyNewAF.setOrgName(specialBorrower.getOrgName());
        }
        specialapplyNewAF.setFloorId(specialBorrower.getFloorId());
        specialapplyNewAF.setHeadId(specialBorrower.getHeadId());
        List floorOList = new ArrayList();
        List floorOListnum = new ArrayList();
        if(specialBorrower.getHeadId() != null) {
          Developer developer = specialapplyBS.queryDeveloperInfo(specialBorrower.getHeadId());
          specialapplyNewAF.setDeveloperName(developer.getDeveloperName());
          List floorList = specialapplyBS.queryFloorListByHeadid(specialBorrower.getHeadId());// 楼盘
          request.getSession().setAttribute("floorListAAC", floorList);
          for (int i = 0; i < floorList.size(); i++) {
            FloorListDTO fDto = (FloorListDTO) floorList.get(i);
            floorOList.add(new org.apache.struts.util.LabelValueBean(fDto
                .getFloorValue(), fDto.getFloorValue()));
          }
        }
        if(specialBorrower.getFloorId() != null) {
          DevelopProject pro = specialapplyBS.queryFloorInfo(specialBorrower.getFloorId());
          List floorNumList = specialapplyBS.queryFloorNumList(specialBorrower.getHeadId(), pro.getFloorName());
          
          for (int i = 0; i < floorNumList.size(); i++) {
            org.xpup.hafmis.sysloan.dataready.develop.dto.FloorListDTO dto = (org.xpup.hafmis.sysloan.dataready.develop.dto.FloorListDTO) floorNumList
                .get(i);
            String temp_floornum = dto.getFloorNum();
            if (temp_floornum != null) {
              FloorNumListDTO floornumlistDto = new FloorNumListDTO();
              floornumlistDto.setFloornumKey(dto.getFloorId());
              floornumlistDto.setFloornumValue(dto.getFloorNum());
              floorOListnum.add(new org.apache.struts.util.LabelValueBean(
                  floornumlistDto.getFloornumValue(), floornumlistDto
                      .getFloornumKey()));
            }
          }
          specialapplyNewAF.setFloorName(pro.getFloorName());
          specialapplyNewAF.setFloorNum(pro.getFloorId().toString());
        }
        if (specialBorrower.getEmpId() != null
            && !"".equals(specialBorrower.getEmpId())) {
          specialapplyNewAF.setPrivilegeBorrowerId(BusiTools
              .convertSixNumber(specialBorrower.getEmpId().toString()));
        }
        if (specialBorrower.getReserveaA() != null
            && !"".equals(specialBorrower.getReserveaA())) {
          specialapplyNewAF.setRemark(specialBorrower.getReserveaA());
        }
        request.setAttribute("modify_wsh", "1");
        request.setAttribute("floorOList", floorOList);
        request.setAttribute("floorOListnum", floorOListnum);
      }
      
      
      request.setAttribute("SpecialapplyNewAF", specialapplyNewAF);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return mapping.findForward("to_specialapply_new");
  }

}
