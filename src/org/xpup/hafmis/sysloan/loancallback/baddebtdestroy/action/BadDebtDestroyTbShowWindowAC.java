package org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action; 


import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.bsinterface.IBadDebtDestroyBS;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.dto.BadDebtDestroyTaAFDTO;
import org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.form.BadDebtDestroyTaAF;

public class BadDebtDestroyTbShowWindowAC extends Action {
  public static final String PAGINATION_KEY = "org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action.BadDebtDestroyTbShowWindowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages =null;
    try{
      BadDebtDestroyTaAFDTO badDebtDestroyTaAFDTO=new BadDebtDestroyTaAFDTO();
      BadDebtDestroyTaAF af = new BadDebtDestroyTaAF(); 
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);
      IBadDebtDestroyBS badDebtDestroyBS = (IBadDebtDestroyBS) BSUtils
      .getBusinessService("badDebtDestroyBS", this, mapping.getModuleConfig());
      String contractId = (String)request.getAttribute("contractId");
      String headId = (String)request.getAttribute("headId");
      if(contractId != null){
        pagination.getQueryCriterions().put("contractId", contractId);
      }
      if(headId != null){
        pagination.getQueryCriterions().put("headId",headId);
      }
      badDebtDestroyTaAFDTO = badDebtDestroyBS.findCallbackInfoMX(pagination);
      af.setShouldBackList(badDebtDestroyTaAFDTO.getShouldBackList());
      af.setBorrowerInfoDTO(badDebtDestroyTaAFDTO.getBorrowerInfoDTO());
      af.setSumCorpus(badDebtDestroyTaAFDTO.getSumCorpus());
      af.setSumInterest(badDebtDestroyTaAFDTO.getSumInterest());
      af.setSumMoney(badDebtDestroyTaAFDTO.getSumMoney());
      af.setRealMoney(badDebtDestroyTaAFDTO.getRealMoney());
      af.setMonthYear(badDebtDestroyTaAFDTO.getMonthYear());       
      af.setOrgName(badDebtDestroyTaAFDTO.getOrgName());
      af.setOrgType(badDebtDestroyTaAFDTO.getOrgType());
      request.setAttribute("badDebtDestroyTaAF", af);
      request.setAttribute("headId",badDebtDestroyTaAFDTO.getHeadId());
      }catch(BusinessException bex){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage(bex.getMessage(),
            false));
        saveErrors(request, messages);
    }catch(Exception e){
      e.printStackTrace();
    }
    return mapping.findForward("baddebtdestroy_window");
  }

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination();
      request.getSession().setAttribute(paginationKey, pagination);
    }   

    return pagination;
  }
}