package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import java.math.BigDecimal;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;
import org.xpup.common.util.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xpup.hafmis.demo.bsinterface.IDemoBS;
import org.xpup.hafmis.demo.domain.entity.Demo;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAF;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAddAF;


import org.apache.struts.action.Action;
/*
 * @author: wzq
 * 2007-06-28
 */
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import org.xpup.hafmis.syscollection.common.domain.entity.Org;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead;
import org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail;

import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;


public class Tran_FindOutOrgNameAAC extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        ActionMessages messages = null;
        SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
        try {
          String id=(String)request.getParameter("id");//转出单位ID
          ItranoutBS tranoutBS = (ItranoutBS) BSUtils.getBusinessService("tranoutBS", this, mapping.getModuleConfig());
          
          Org org = null;      
          String name="";
          BigDecimal monthIncome = new BigDecimal(0.00);
          boolean b=false;
          boolean c=false;
          String f="";
          String str="";
          String yg="";
          String yg_a="";
          if(id!=null&&!id.equals("")){
            org = tranoutBS.fingOrgInfo(id,securityInfo);
            
            if(org!=null){
              yg=tranoutBS.FindAA103_DayTime(org.getOrgInfo().getCollectionBankId());
              if(securityInfo.getUserInfo().getBizDate().equals(yg)){
                yg_a="a";
              }else{
                yg_a="b";
              }
              name=org.getOrgInfo().getName();
              b=tranoutBS.findAdjustWrongFAccountByOrgid(org.getId().toString(), securityInfo);
              c=tranoutBS.check(org.getId().toString());
              if(b){
                str="此单位存在未记账的错账调整业务！";
              }
              if(c)
              {
                f = "提示";
              }
            }
            String text=null;
            String paginationKey = getPaginationKey();
            Pagination pagination= (Pagination) request.getSession().getAttribute(paginationKey);
            pagination.getQueryCriterions().put("id", id);
            pagination.getQueryCriterions().put("name", name);
            pagination.getQueryCriterions().put("monthIncome", monthIncome);
            
            if(name==null||name.equals("")||name.length()<1)
              name="";
            
            text="displays('"+id+"','"+name+"','"+monthIncome+"','"+str+"','"+f+"','"+yg_a+"')";
            response.getWriter().write(text); 
            response.getWriter().close();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null; 
}

  protected String getPaginationKey() {
    return Tran_showAC.PAGINATION_KEY;
   
 }
  

}
