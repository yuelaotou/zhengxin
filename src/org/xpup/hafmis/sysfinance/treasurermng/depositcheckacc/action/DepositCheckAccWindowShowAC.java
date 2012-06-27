package org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.bsinterface.IDepositCheckAccBS;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccWindowDTO;
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.form.DepositCheckAccWindowAF;

public class DepositCheckAccWindowShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    DepositCheckAccWindowAF depositCheckAccWindowAF=new DepositCheckAccWindowAF();
    try{
      List bdcList=(List)request.getSession().getAttribute("bdcList");
      List bcaList=(List)request.getSession().getAttribute("bcaList");
      Pagination pagination=null;
      if(request.getSession().getAttribute("pagination_gjp")==null){
        pagination=new Pagination();
      }else{
        pagination=(Pagination)request.getSession().getAttribute("pagination_gjp");
      }
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      IDepositCheckAccBS depositCheckAccBS = (IDepositCheckAccBS) BSUtils
      .getBusinessService("depositCheckAccBS", this, mapping.getModuleConfig());
      Object[] obj=depositCheckAccBS.findDepositCheckAccWindowInfo(bdcList, bcaList, pagination, securityInfo);
      List bankList=(List)obj[0];//银行已收中心未收
      List officeList=(List)obj[1];//中心已收银行未收
      List bankOutList=(List)obj[2];//银行已付中心未付
      List officeOutList=(List)obj[3];//中心已付银行未付
      DepositCheckAccWindowDTO depositCheckAccWindowDTO=(DepositCheckAccWindowDTO)obj[4];
      request.setAttribute("bankList", bankList);
      request.setAttribute("officeList", officeList);
      request.setAttribute("bankOutList", bankOutList);
      request.setAttribute("officeOutList", officeOutList);
      depositCheckAccWindowAF.setDepositCheckAccWindowDTO(depositCheckAccWindowDTO);
      request.getSession().setAttribute("bankList_gjp", bankList);
      request.getSession().setAttribute("officeList_gjp", officeList);
      request.getSession().setAttribute("bankOutList_gjp", bankOutList);
      request.getSession().setAttribute("officeOutList_gjp", officeOutList);
      request.getSession().setAttribute("depositCheckAccWindowDTO", depositCheckAccWindowDTO);
    }catch(Exception e){
      e.printStackTrace();
    }
    request.setAttribute("depositCheckAccWindowAF", depositCheckAccWindowAF);
    return mapping.findForward("to_depositcheckaccwindow_show");
  }
}
