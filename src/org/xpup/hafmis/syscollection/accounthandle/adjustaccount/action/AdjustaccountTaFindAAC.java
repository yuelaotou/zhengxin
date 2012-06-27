package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

import java.io.Serializable;
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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.OrgHAFAccountFlow;


/**
 * 
 * @author 李鹏
 *2007-6-28
 */
public class AdjustaccountTaFindAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        ActionMessages messages =null;
        String text="displays('','','','','','')";
        String bizDocNum="";
        String dates="";
        String orgId="";
        String orgName="";
        String temp_type="";
        String bizNoteNum="";
        try {
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          bizDocNum=(String)request.getParameter("bizDocNum");
          orgId=(String)request.getParameter("orgId");
      //    bizNoteNum=(String)request.getParameter("bizNoteNum");
          pagination.getQueryCriterions().put("empHAFAccountFlow.orgHAFAccountFlow.docNum",bizDocNum);
          dates=(String)request.getParameter("date");
          IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
          .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
          AdjustWrongAccountHead adjustWrongAccountHead=null; 
          SecurityInfo securityInfo = (SecurityInfo) request.getSession()
          .getAttribute("SecurityInfo");
          
          List list=adjustAccountBS.findOrgHAFAccountFlowByCriterions(orgId,dates,bizDocNum,securityInfo);//只有头表没有记录才可以进行操作
          if(list.size()<=0){
//            OrgHAFAccountFlow orgHAFAccountFlow=adjustAccountBS.findOrgHAFAccountFlowByDocNumCriterions(bizDocNum);
            //修改过
            OrgHAFAccountFlow orgHAFAccountFlow=adjustAccountBS.findOrgHAFAccountFlowByDocNumCriterions(bizDocNum,dates,orgId);
            if(orgHAFAccountFlow!=null){
           //   orgId= orgHAFAccountFlow.getOrg().getId();
              bizNoteNum=orgHAFAccountFlow.getNoteNum();              
              orgName=adjustAccountBS.findOrgInfoById(orgId,securityInfo);
              pagination.getQueryCriterions().put("empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.id", orgId);
              pagination.getQueryCriterions().put("empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name", orgName);
              pagination.getQueryCriterions().put("empHAFAccountFlow.orgHAFAccountFlow.docNum", bizDocNum);
              pagination.getQueryCriterions().put("empHAFAccountFlow.orgHAFAccountFlow.noteNum", bizNoteNum);
              pagination.getQueryCriterions().put("empHAFAccountFlow.orgHAFAccountFlow.settDate", dates);
              request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "1");
              if("汇缴".equals(orgHAFAccountFlow.getBizType()) || "补缴".equals(orgHAFAccountFlow.getBizType()) || "单位补缴".equals(orgHAFAccountFlow.getBizType())){
                request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "1");    
              }else
                request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "6");  
            }else //在315中没有该凭证号所对应的记录    //在101里没有该凭证号
              request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "7");  
          }else//在314中存在错账
            request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "5");   
          response.getWriter().write(text); 
          response.getWriter().close();
          
        } catch(BusinessException bex){
          bex.printStackTrace();
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
              false));
          saveErrors(request, messages);
          temp_type=bex.getMessage();
          System.out.println(temp_type);
          request.getSession(false).setAttribute("findadjustWrongAccountHead_type", "0");    
        }
        text="displays('"+dates+"','"+bizDocNum+"','"+bizNoteNum+"','"+orgId+"','"+temp_type+"','"+orgName+"')";
        request.setAttribute("adjustaccountShowAF", "");
        response.getWriter().write(text); 
        response.getWriter().close();
        return null;
}
  protected String getPaginationKey() {
    return AdjustaccountTaShowAC.PAGINATION_KEY;
 }
}
