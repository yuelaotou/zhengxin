package org.xpup.hafmis.sysloan.dataready.collloanbackpara.action;

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
import org.xpup.hafmis.sysloan.dataready.collloanbackpara.bsinterface.ICollLoanbackParaBS;
import org.xpup.hafmis.sysloan.dataready.collloanbackpara.dto.CollLoanbackParaDTO;
import org.xpup.hafmis.sysloan.dataready.collloanbackpara.form.CollLoanbackParaAF;


public class CollLoanbackParaShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    CollLoanbackParaAF collLoanbackParaAF=new CollLoanbackParaAF();
    SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
    List officeList1 = null;
    try {
      // 取出用户权限办事处,显示在下拉菜单中
      List officeList = securityInfo.getOfficeList();
      officeList1 = new ArrayList();
      OfficeDto officedto = null;
      officeList1.add(new org.apache.struts.util.LabelValueBean("全部", "100"));
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
      OfficeDto officedto=(OfficeDto)securityInfo.getOfficeList().get(0);
//      office=officedto.getOfficeCode().toString();
      office="100";
    }
    try{
      ICollLoanbackParaBS collLoanbackParaBS = (ICollLoanbackParaBS) BSUtils
      .getBusinessService("collLoanbackParaBS", this, mapping.getModuleConfig());
      CollLoanbackParaDTO collLoanbackParaDTO=null;
      collLoanbackParaDTO=collLoanbackParaBS.findCollLoanbackParaInfo(office);
      if(collLoanbackParaDTO!=null){
        collLoanbackParaAF.setCollLoanbackParaDTO(collLoanbackParaDTO);
      }
      collLoanbackParaAF.getCollLoanbackParaDTO().setOffice(office);
    }catch(Exception e){
      e.printStackTrace();
    }
    request.setAttribute("officeList1", officeList1);
    request.setAttribute("collLoanbackParaAF", collLoanbackParaAF);
    return mapping.findForward("to_collloanbackpara_show");
  }
}
