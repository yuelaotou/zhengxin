package org.xpup.hafmis.syscollection.accounthandle.clearaccount.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.common.util.PaginationUtils;
import org.xpup.hafmis.common.form.IdAF;
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.clearaccount.bsinterface.IclearAccountBS;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;


/** 
 * @author 李鹏
 *2007-6-29
 */
public class ClearAccountMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaShowAC";
  
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.in.account", "clearAccount");
    map.put("button.in.accountall", "clearAccountAll");
    return map;
  }
  public ActionForward clearAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionMessages messages = null;
      IdAF idaf=(IdAF)form;
      String[] rowArray=idaf.getRowArray();
      List list = new ArrayList();
      
      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      String bizDate = securityInfo.getUserInfo().getBizDate();
      String ip = securityInfo.getUserInfo().getUserIp();
      String oper = securityInfo.getUserInfo().getUsername();
      String officeid="";
      
      IclearAccountBS clearaccountBS = (IclearAccountBS) BSUtils
      .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      try {
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  

      for(int i=0;i<rowArray.length;i++){
        OrgHAFAccountFlow orgHAFAccountFlow=clearaccountBS.queryIsClearAccountById(rowArray[i]);
        if(orgHAFAccountFlow!=null){
          if(orgHAFAccountFlow.getIsClearAccount()!=null&&!orgHAFAccountFlow.getIsClearAccount().equals("")){
            if(orgHAFAccountFlow.getIsClearAccount().equals(new BigDecimal(1))){
              throw new BusinessException("编号为：" + orgHAFAccountFlow.getOrg().getId()
                  + " 的单位已经扎账或正在扎账！！");
            }
          }
        }
        // 判断是否存在自动挂账业务
        if (orgHAFAccountFlow.getReserveaC() != null
            && !orgHAFAccountFlow.getReserveaC().equals("")) {
          String type = "";
          if (orgHAFAccountFlow.getBiz_Type().equals("A")) {
            type = "C";
          } else if (orgHAFAccountFlow.getBiz_Type().equals("C")) {
            type = "A";
          }
          OrgHAFAccountFlow temp_orgHAFAccountFlow = clearaccountBS
              .queryIsClearAccountByBizId(orgHAFAccountFlow.getReserveaC(),
                  type);
          boolean flag = true;
          for (int j = 0; j < rowArray.length; j++) {
            String temp_str = rowArray[j];
            if (temp_str.equals(temp_orgHAFAccountFlow.getId().toString())) {
              flag = false;
              break;
            }
          }
          if (flag) {
            list.add(temp_orgHAFAccountFlow.getId().toString());
          }
        }
        list.add(rowArray[i]);
      }
      String[] resArray = new String[list.size()];
      for (int i = 0; i < list.size(); i++) {
        resArray[i] = (String) list.get(i);
      }
      for(int i=0;i<resArray.length;i++){
        clearaccountBS.updateOrgHAFAccountFlow(resArray[i],"1");
      }
  
      String valid =clearaccountBS.dealwithClearAccount(resArray,bizDate,oper,ip,officeid,pagination,"1", securityInfo);
//      if(valid.equals("1")){
//        messages=new ActionMessages();
//        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("由于凭证号的问题不能扎账！",
//            false));
//        saveErrors(request, messages);
//        
//        for(int i=0;i<rowArray.length;i++){
//          clearaccountBS.updateOrgHAFAccountFlow(rowArray[i],"2");
//        }
//        return mapping.findForward("showClearAccountAC");
//      } else 
     
        if(valid.equals("2")){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扎账完成！",
            false));
        saveErrors(request, messages);
        return mapping.findForward("showClearAccountAC");
      }else{
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("未能扎账！",
            false));
        saveErrors(request, messages);
        
        for(int i=0;i<rowArray.length;i++){
          clearaccountBS.updateOrgHAFAccountFlow(rowArray[i],"2");
        }
        return mapping.findForward("showClearAccountAC");
      }
  } catch (BusinessException e) {
    e.printStackTrace();
    messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
        .getLocalizedMessage().toString(), false));
    saveErrors(request, messages);
    for(int i=0;i<rowArray.length;i++){
      clearaccountBS.updateOrgHAFAccountFlow(rowArray[i],"2");
    }
    }catch(Exception e){
      e.printStackTrace();
      for(int i=0;i<rowArray.length;i++){
        clearaccountBS.updateOrgHAFAccountFlow(rowArray[i],"2");
      }
    }
    return mapping.findForward("showClearAccountAC");
  } 

  public ActionForward clearAccountAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionMessages messages = null;
      try {
      HttpSession session = request.getSession();
      List list = (List)session.getAttribute("clearaccountList");
      List resList = new ArrayList();
      IclearAccountBS clearaccountBS = (IclearAccountBS) BSUtils
      .getBusinessService("clearaccountBS", this, mapping.getModuleConfig());
      
      for (int i = 0; i < list.size(); i++) {
        OrgHAFAccountFlow orgHAFAccountFlow = clearaccountBS
            .queryIsClearAccountById(list.get(i).toString());
        String type = "";
        // 判断是否存在自动挂账业务
        if (orgHAFAccountFlow.getReserveaC() != null
            && !orgHAFAccountFlow.getReserveaC().equals("")) {
          if (orgHAFAccountFlow.getBiz_Type().equals("A")) {
            type = "C";
          } else if (orgHAFAccountFlow.getBiz_Type().equals("C")) {
            type = "A";
          }
          OrgHAFAccountFlow temp_orgHAFAccountFlow = clearaccountBS
              .queryIsClearAccountByBizId(orgHAFAccountFlow.getReserveaC(),
                  type);
          boolean flag = true;
          // 判断在所选列表中是否存在对应的流水id
          for (int j = 0; j < list.size(); j++) {
            if ((list.get(j).toString()).equals(temp_orgHAFAccountFlow.getId()
                .toString())) {
              flag = false;
              break;
            }
          }
          if (flag) {
            resList.add(temp_orgHAFAccountFlow.getId().toString());
          }
        }
        resList.add(list.get(i).toString());
      }
      String[] rowArray=new String[resList.size()];
      for(int i=0;i<resList.size();i++){
        rowArray[i]=resList.get(i).toString();
      }

      SecurityInfo securityInfo = (SecurityInfo) request.getSession().getAttribute("SecurityInfo");
      String bizDate = securityInfo.getUserInfo().getBizDate();
      String ip = securityInfo.getUserInfo().getUserIp();
      String officeid="";
      String oper = securityInfo.getUserInfo().getUsername();
      
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      
      String valid =clearaccountBS.dealwithClearAccount(rowArray,bizDate,oper,ip,officeid,pagination,"2", securityInfo);

      
//      if(valid.equals("1")){
//        messages=new ActionMessages();
//        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("由于凭证号的问题不能扎账！",
//            false));
//        saveErrors(request, messages);
//        return mapping.findForward("showClearAccountAC");
//      }
//      else 
        if(valid.equals("2")){
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("扎账完成！",
            false));
        saveErrors(request, messages);
        return mapping.findForward("showClearAccountAC");
      }else{
        messages=new ActionMessages();
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("未能扎账！",
            false));
        saveErrors(request, messages);
        return mapping.findForward("showClearAccountAC");
      }
  } catch (BusinessException e) {
    e.printStackTrace();
    messages = new ActionMessages();
    messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(e
        .getLocalizedMessage().toString(), false));
    saveErrors(request, messages);
  }    
    return mapping.findForward("showClearAccountAC");
  } 

  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if (pagination == null) {
      pagination = new Pagination(0, 10, 1, "orgHAFAccountFlow.bizStatus", "ASC",
          new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
  
 }

