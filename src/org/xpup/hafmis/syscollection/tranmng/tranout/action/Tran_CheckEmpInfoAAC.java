package org.xpup.hafmis.syscollection.tranmng.tranout.action;

import java.math.BigDecimal;
import java.nio.BufferUnderflowException;
import java.util.List;
import java.util.Map;

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
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.biz.clearinginterest.ClearingInterestBS;
import org.xpup.hafmis.syscollection.common.biz.clearinginterest.ClearingInterestInterface;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.syscollection.dto.ClearingInterestDTO;
import org.xpup.hafmis.syscollection.tranmng.tranout.bsinterface.ItranoutBS;
import org.xpup.hafmis.syscollection.tranmng.tranout.form.TranAddAF;


/**
 * 判断转出职工的身份证和姓名与转入职工的身份证和姓名是否相同
 * @author wangshuang
 *
 */
public class Tran_CheckEmpInfoAAC extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html;charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    try {
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      String inEmpId = formatId(request.getParameter("inempid"));
      String outEmpId = formatId(request.getParameter("outempid"));
      String id = request.getParameter("inoutorgid");
      String inOrgId = formatId(id.split(",")[0]);
      String outOrgId = formatId(id.split(",")[1]);
      ItranoutBS tranoutBS = (ItranoutBS)BSUtils.getBusinessService("tranoutBS",this,mapping.getModuleConfig());
      String name_in = tranoutBS.findEmpInfo(inEmpId, inOrgId, securityInfo).getEmp().getEmpInfo().getName();
      String name_out = tranoutBS.findEmpInfo(outEmpId, outOrgId, securityInfo).getEmp().getEmpInfo().getName();
      String cardNum_in = tranoutBS.findEmpInfo(inEmpId, inOrgId, securityInfo).getEmp().getEmpInfo().getCardNum();
      String cardNum_out = tranoutBS.findEmpInfo(outEmpId, outOrgId, securityInfo).getEmp().getEmpInfo().getCardNum();
      String text=null;
      if(!name_in.equals(name_out)&&!cardNum_in.equals(cardNum_out)){
        text = "conf('different')";
      }else{
        text = "conf('same')";
      }
      response.getWriter().write(text); 
      response.getWriter().close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null; 

  }
  public String formatId(String id){
    String str = "";
    String temp ="";
    for(int i=0;i<6;i++){
      temp = id.substring(i,i+1);
      if(temp.equals("0")){
        continue;
      }else{
        return id.substring(i,id.length());
      }
    }
    return str;
  }
}
