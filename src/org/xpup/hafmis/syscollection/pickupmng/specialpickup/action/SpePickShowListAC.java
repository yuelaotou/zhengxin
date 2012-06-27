package org.xpup.hafmis.syscollection.pickupmng.specialpickup.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.SpecialPick;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.bsinterface.ISpePickBS;
import org.xpup.hafmis.syscollection.pickupmng.specialpickup.form.SpePickListAF;
import org.xpup.security.common.domain.Userslogincollbank;
public class SpePickShowListAC extends Action {
    public static final String PAGINATION_KEY = " org.xpup.hafmis.syscollection.pickupmng.specialpickup.action.SpePickShowListAC";
    public ActionForward execute(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
      ActionMessages messages =null;
      try{
           /**
            * 分页
            */
           Pagination pagination = getPagination(PAGINATION_KEY, request); 
           PaginationUtils.updatePagination(pagination, request);  
           saveToken(request);
           ISpePickBS spePickBS = (ISpePickBS) BSUtils
           .getBusinessService("spePickBS", this, mapping.getModuleConfig());
           SecurityInfo sInfo = (SecurityInfo)request.getSession().getAttribute("SecurityInfo");
           SpecialPick specialPick=spePickBS.findEmpSpePick(pagination,sInfo);
          
           List list=spePickBS.findEmpSpePickList(pagination,sInfo);
           
           //从数据库读取办事处信息
           SecurityInfo securityInfo = (SecurityInfo) request.getSession()
           .getAttribute("SecurityInfo");
           List officeList = securityInfo.getOfficeList();
           List officeList1 = new ArrayList();
           OfficeDto officedto = null;
           Iterator itr1 = officeList.iterator();
           while (itr1.hasNext()) {
             officedto = (OfficeDto) itr1.next();
             officeList1.add(new org.apache.struts.util.LabelValueBean(officedto.getOfficeName(), officedto.getOfficeCode()));
           }
           request.getSession(true).setAttribute("officeList1", officeList1);
           
           //结束
           
           List loanbankList1 = null;
           try {
             // 取出用户权限放款银行,显示在下拉菜单中
             List loanbankList = securityInfo.getCollBankList();
             loanbankList1 = new ArrayList();
             Userslogincollbank bank = null;
             Iterator itr2 = loanbankList.iterator();
             while (itr2.hasNext()) {
               bank = (Userslogincollbank) itr2.next();
               loanbankList1.add(new org.apache.struts.util.LabelValueBean(bank
                   .getCollBankName(), bank.getCollBankId().toString()));
             }
           } catch (Exception e) {
             e.printStackTrace();
           }
           request.getSession(true).setAttribute("loanbankList1", loanbankList1);
           //结束
           
           SpePickListAF spePickListAF=new SpePickListAF();
           spePickListAF.setList(list);
           spePickListAF.setId((String)pagination.getQueryCriterions().get("id"));
           spePickListAF.setName((String)pagination.getQueryCriterions().get("name"));
           spePickListAF.setOfficeCode((String)pagination.getQueryCriterions().get("officeCode"));
           spePickListAF.setCollectionBankId((String)pagination.getQueryCriterions().get("collectionBankId"));
           spePickListAF.setOperateTime1((String)pagination.getQueryCriterions().get("operateTime1"));
           spePickListAF.setOperateTime2((String)pagination.getQueryCriterions().get("operateTime2"));
           request.setAttribute("specialPick", list);
           request.setAttribute("specialPick1", specialPick);
           request.setAttribute("spePickListAF",spePickListAF);
           spePickListAF.setId("");
           spePickListAF.setName("");
           spePickListAF.setOfficeCode("");
           spePickListAF.setCollectionBankId("");
           spePickListAF.setOperateTime1("");
           spePickListAF.setOperateTime2("");
           pagination.getQueryCriterions().put("specialPick", list);
         }catch(BusinessException bex){
           messages=new ActionMessages();
           messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("没有您要查询的信息！",
               false));
           saveErrors(request, messages);
         }catch(Exception ex){
           ex.printStackTrace();
         }
         return mapping.findForward("spe_pick_list");
    }


    private Pagination getPagination(String paginationKey,
        HttpServletRequest request) {
      Pagination pagination = (Pagination) request.getSession().getAttribute(
          paginationKey);
      if (pagination == null) {
        pagination = new Pagination(0, 10, 1, "id", "DESC",
            new HashMap(0));
        request.getSession().setAttribute(paginationKey, pagination);
      }
      return pagination;
    }

  }

