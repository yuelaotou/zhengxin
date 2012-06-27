package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import org.apache.struts.action.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbAF;
import org.xpup.security.common.domain.Userslogincollbank;

public class Tran_showFindTbAC extends Action {
  
  /**
   * @author wzq 2007-07-17
   */
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showFindTbAC";

  /**
   * 默认的缴存到帐确认查询
   */
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    TranTbAF tranTbAF = new TranTbAF();
    TranTbAF tranTbAF_print = new TranTbAF();
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    try {
      Pagination pagination = getPagination(PAGINATION_KEY, request);
      PaginationUtils.updatePagination(pagination, request);
      int typetran=securityInfo.getIsOrgEdition();
      request.setAttribute("typetran", typetran+"");
      ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
    
      tranTbAF = tranoutBS.findTranListBydefaultWZQ(pagination, securityInfo); // BS BS
      tranTbAF_print = tranoutBS.findTranListBydefaultWZQ_yg(pagination, securityInfo); // BS BS
      String collBankId = (String) pagination.getQueryCriterions()
      .get("collBankId"); 
      String collBankName = "";
      if(collBankId!=null){
        collBankName = tranoutBS.findCollBank(collBankId);        
      }
      request.getSession().setAttribute("collBankName_yg", collBankName);
      request.getSession().setAttribute("tranTbAF_print",tranTbAF_print);
      if(tranTbAF.getList()==null){
        request.setAttribute("listnum","0");
        request.setAttribute("countsize","0");
      }else{
        request.setAttribute("listnum",tranTbAF.getList().size()+"");
        request.setAttribute("countsize",tranTbAF.getList().size()+"");
      }
      List collBankList1 = null;
      try {
        // 取出用户下归集行
        List collBankList = securityInfo.getCollBankList();
        collBankList1 = new ArrayList();
        Userslogincollbank userslogincollbank = null;
        Iterator itr2 = collBankList.iterator();
        while (itr2.hasNext()) {
          userslogincollbank = (Userslogincollbank) itr2.next();
          collBankList1.add(new org.apache.struts.util.LabelValueBean(
              userslogincollbank.getCollBankName().toString(), userslogincollbank
                  .getCollBankId().toString()));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      request.setAttribute("collBankList1", collBankList1);
      // 业务状态下拉框
      Map map = BusiTools.listBusiProperty(BusiConst.BUSINESSSTATE);
      tranTbAF.setMap(map);
       request.setAttribute("tranTbAF", tranTbAF);
    

    } catch(BusinessException bex){
      
      ActionMessages messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(), false));
      saveErrors(request, messages);
    }
    return mapping.findForward("to_show_jsp");
  }
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(paginationKey);
   
    if (pagination == null) {
      HashMap m = new HashMap();
      m.put("tranType", "0");
      pagination = new Pagination(0, 10, 1, "tot.id", "DESC", m);
      request.getSession().setAttribute(paginationKey, pagination);
    }
    return pagination;
  }

}






