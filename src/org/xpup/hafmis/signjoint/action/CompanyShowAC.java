package org.xpup.hafmis.signjoint.action;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.form.EmpAllAF;
import org.xpup.hafmis.signjoint.form.SignAF;


public class CompanyShowAC extends Action{
  
  public static final String PAGINATION_KEY = "org.xpup.hafmis.signjoint.action.CompanyShowAC";
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionMessages messages =null;
    try{
      Pagination pagination = getPagination(PAGINATION_KEY, request); 
      PaginationUtils.updatePagination(pagination, request);  
      saveToken(request);
      SignAF af=(SignAF)form;
      ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
      if(af.getOrgid().equalsIgnoreCase("")||(af.getOrgid()==null)){
        //List list=bs.queryAllEmpInfo(pagination); 
        af.setList(new ArrayList());
        request.setAttribute("signAF", af);
      }else{
         //String name=bs.getOrgnameByOrgID(af.getOrgid());
         //if(name.equalsIgnoreCase("")){
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("该单位不存在！",
              false));
          //saveErrors(request, messages);
          //af.getList().clear();
          //}else{

          pagination.getQueryCriterions().put("orgid",af.getOrgid());
          pagination.getQueryCriterions().put("orgname","OK");
          pagination.getQueryCriterions().put("empid",af.getEmpid());
          pagination.getQueryCriterions().put("empname",af.getEmpname());
          List list=bs.queryAllEmpInfo(pagination); 
          af.setList(list);
          af.setOrgname("OK");
          request.setAttribute("signAF", af);
          request.getSession().setAttribute(PAGINATION_KEY, pagination);
          pagination.getQueryCriterions().put("pageList",list);
        //}
      }
    }catch(Exception ex){
      ex.printStackTrace();
    }
    return mapping.findForward("to_list");
  }
  
  private Pagination getPagination(String paginationKey,
      HttpServletRequest request) {
    Pagination pagination = (Pagination) request.getSession().getAttribute(
        paginationKey);
    if(pagination == null){
      pagination = new Pagination(0, 10, 1, "empid", "ASC",new HashMap(0));
      request.getSession().setAttribute(paginationKey, pagination);
    }   
    return pagination;
  }
}
