package org.xpup.hafmis.signjoint.action;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jcifs.smb.PictureUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.dto.TempDTO;
import org.xpup.hafmis.signjoint.form.SignAddAF;
import org.xpup.hafmis.syscollection.chgbiz.chgorgrate.bsinterface.IChgorgrateBS;



public class SignEditMaintainAC extends LookupDispatchAction{
  public static final String PAGINATION_KEY = "org.xpup.hafmis.signjoint.action.CompanyShowAC";
  protected Map getKeyMethodMap() {
    Map map = new HashMap();
    map.put("button.add", "add");
    map.put("button.update","update");
    map.put("button.back", "back");
    return map;
  }

  /**
   * 添加
   */
  public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      messages=new ActionMessages();
      if(!isTokenValid(request))
      {
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("请不要灌水！",
            false));
        saveErrors(request, messages);
      }else{
        resetToken(request);
        Pagination pagination=(Pagination)request.getSession().getAttribute(PAGINATION_KEY);
        SignAddAF signAddAF=(SignAddAF)form;
        TempDTO dto=signAddAF.getUserinfo();
        dto.setOrgid((String)pagination.getQueryCriterions().get("orgid"));
        dto.setOrgname((String)pagination.getQueryCriterions().get("orgname"));
        ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
        bs.addUserInfo(dto);
        messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加成功！",
            false));
        saveErrors(request, messages);
      }
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("添加失败！",
          false)); 
      saveErrors(request, messages);
      request.setAttribute("signAddAF", form);
      return mapping.findForward("to_add");
    } 
    return mapping.findForward("showSignListAC");
  }
  
  /**
   * 修改
   */
  public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    ActionMessages messages =null;
    try{
      SignAddAF signAddAF=(SignAddAF)form;
      TempDTO newdto=signAddAF.getUserinfo();
      ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",this, mapping.getModuleConfig());
      TempDTO olddto=signAddAF.getOlduserinfo();
      bs.modifyUserInfo(newdto,olddto);
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改成功！",
          false));
      saveErrors(request, messages);
    }catch(BusinessException bex){
      messages=new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("修改失败！",
          false));
      saveErrors(request, messages);
      request.setAttribute("signAddAF", form);
      return mapping.findForward("to_add");  
    }
    return mapping.findForward("showSignListAC");
  }

  public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return mapping.findForward("showSignListAC");
  }
  
}
