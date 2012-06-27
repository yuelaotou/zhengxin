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
import org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.form.DepositCheckAccAF;

public class DepositCheckAccShowAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response) {
    DepositCheckAccAF depositCheckAccAF=new DepositCheckAccAF();
    Integer bdcsize=new Integer(0);
    Integer bcasize=new Integer(0);
    try{
      IDepositCheckAccBS depositCheckAccBS = (IDepositCheckAccBS) BSUtils
      .getBusinessService("depositCheckAccBS", this, mapping.getModuleConfig());
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      Pagination pagination=null;
      if(request.getSession().getAttribute("pagination_gjp")==null){
        pagination=new Pagination();
      }else{
        pagination=(Pagination)request.getSession().getAttribute("pagination_gjp");
        Object[] obj=depositCheckAccBS.findDepositCheckAccList(pagination, securityInfo);
        List bdcList=(List)obj[0];
        if(bdcList!=null){
          depositCheckAccAF.setBankDayClearList(bdcList);
          bdcsize=new Integer(bdcList.size());
        }
        List bcaList=(List)obj[1];
        if(bcaList!=null){
          depositCheckAccAF.setBankCheckAccList(bcaList);
          bcasize=new Integer(bcaList.size());
        }
        depositCheckAccAF.clear();
        request.getSession().setAttribute("bdcList",bdcList);
        request.getSession().setAttribute("bcaList",bcaList);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    request.setAttribute("depositCheckAccAF", depositCheckAccAF);
    request.setAttribute("bdcsize", bdcsize);
    request.setAttribute("bcasize", bcasize);
    return mapping.findForward("to_depositcheckacc_show");
  }
}
