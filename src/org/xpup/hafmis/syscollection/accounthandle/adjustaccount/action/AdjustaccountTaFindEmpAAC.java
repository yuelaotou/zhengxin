package org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action;

import java.io.Serializable;
import java.math.BigDecimal;

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
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.syscollection.accounthandle.adjustaccount.bsinterface.IAdjustAccountBS;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountHead;
import org.xpup.hafmis.syscollection.common.domain.entity.AdjustWrongAccountTail;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;


/**
 * 
 * @author 李鹏
 *2007-6-28
 */
public class AdjustaccountTaFindEmpAAC extends Action{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        ActionMessages messages =null;
        String orgId="";
        String empId="";
        String empName="";
        String empCardNum="";
        String empCardKind="";
        BigDecimal empTotalBalance=new BigDecimal(0.00);
        String types="";
        String text="";
        String type="";//下拉列表不可用
        try {
          orgId=(String)request.getSession(false).getAttribute("findadjustWrongAccountHead_orgId");
          Serializable orgid=(Serializable)request.getSession().getAttribute("ORGID");
          if(orgId == null && orgid != null){
            orgId=orgid.toString();
          }
          empId=(String)request.getParameter("empId");
          String paginationKey = getPaginationKey();
          Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
          pagination.getQueryCriterions().put("adjustWrongAccountTail.adjustWrongAccountHead.org.id",orgId);
          IAdjustAccountBS adjustAccountBS = (IAdjustAccountBS) BSUtils
          .getBusinessService("adjustaccountBS", this, mapping.getModuleConfig());
          Emp emp1=adjustAccountBS.checkEmp(orgId, empId);//测试职工是否在该单位下
          if(emp1!=null)
          { 
            Emp emp=adjustAccountBS.findEmpInfoByEmpId(new Integer(emp1.getId()+""));
            empName=emp.getEmpInfo().getName();
            empCardKind=BusiTools.getBusiValue(Integer.parseInt(emp.getEmpInfo().getCardKind().toString()), BusiConst.DOCUMENTSSTATE);
            empCardNum=emp.getEmpInfo().getCardNum();
            empTotalBalance=emp.getPreBalance().add(emp.getCurBalance());   
            boolean checks=adjustAccountBS.findHeadAndTailByCriterions(orgId, emp1.getEmpId().toString());
            if(checks!=false){//该员工不存在未记账的调整业务
              AdjustWrongAccountHead adjustWrongAccountHead=adjustAccountBS.findAdjustWrongAccountHeadIDByCriterions(orgId);
              if(adjustWrongAccountHead!=null){//显示员工信息
                AdjustWrongAccountTail adjustWrongAccountTail=adjustAccountBS.findAdjustWrongAccountTailByCriterions(adjustWrongAccountHead.getId()+"",emp1.getEmpId()+"");
                if(adjustWrongAccountTail==null) //可以插入
                  type="1";  
              }else
                  type="2";
            }
          }
        } catch (BusinessException bex) {
          messages=new ActionMessages();
          messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex.getMessage(),
              false));
          saveErrors(request, messages);
          types=bex.getMessage();
        } 
        text="displayEmp('"+empId+"','"+empName+"','"+empCardKind+"','"+empCardNum+"','"+empTotalBalance+"','"+types+"','"+type+"')";  
        response.getWriter().write(text); 
        response.getWriter().close();
       
        return null; 
}
  protected String getPaginationKey() {
    return AdjustaccountTaShowAC.PAGINATION_KEY;
 }
}
