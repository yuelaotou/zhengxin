package org.xpup.hafmis.syscollection.accountmng.officeparam.action;

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
import org.xpup.hafmis.syscollection.accountmng.officeparam.bsinterface.IOfficeParaBS;
import org.xpup.hafmis.syscollection.accountmng.officeparam.dto.OfficeParaDTO;
import org.xpup.hafmis.syscollection.accountmng.officeparam.form.OfficeParaAF;


public class OfficeParaShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    OfficeParaAF officeParaAF=new OfficeParaAF();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    List officeList1 = null;
    try {
      // 取出用户权限办事处,显示在下拉菜单中
      List officeList = securityInfo.getOfficeList();
      officeList1 = new ArrayList();
      OfficeDto officedto = null;
      Iterator itr1 = officeList.iterator();
      while (itr1.hasNext()) {
        officedto = (OfficeDto) itr1.next();
        officeList1.add(new org.apache.struts.util.LabelValueBean(officedto
            .getOfficeName(), officedto.getOfficeCode()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    String office="";
    if(request.getParameter("office")!=null){
      office=(String)request.getParameter("office");
    }else{
      if(request.getSession().getAttribute("office")!=null){
        office=(String)request.getSession().getAttribute("office");
      }
      else{
        OfficeDto officedto=(OfficeDto)securityInfo.getOfficeList().get(0);
        office=officedto.getOfficeCode().toString();
      }
    }
    
    try{
      IOfficeParaBS officeParaBS = (IOfficeParaBS) BSUtils
      .getBusinessService("officeParaBS", this, mapping.getModuleConfig());
      OfficeParaDTO officeParaDTO=null;
      officeParaDTO=officeParaBS.findOfficeParaInfo(office);
      if(officeParaDTO!=null){
        officeParaAF.setOfficeParaDTO(officeParaDTO);
      }
      officeParaAF.getOfficeParaDTO().setOffice(office);
    }catch(Exception e){
      e.printStackTrace();
    }
    request.getSession().setAttribute("office", null);
    request.setAttribute("officeList1", officeList1);
    request.setAttribute("officeParaAF", officeParaAF);
    return mapping.findForward("to_officepara_show");
  }
}
