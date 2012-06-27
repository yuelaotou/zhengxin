package org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.bsinterface.ICumulativeinfoFindBS;
import org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.dto.Printdto;
import org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.form.CumulativeinfoFindAF;

/**
 * 根据时间，查询住房公积金累计归集情况表
 * 
 *@author 
 *2007-10-05
 */
public class FundbankmonthofyearFindAC extends Action {

  //public static final String PAGINATION_KEY = "org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.action.EmpOperationFlowTaShowAC";
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
    CumulativeinfoFindAF f=(CumulativeinfoFindAF)form;
    List returnList=new ArrayList();
    String officeCode="";
    String collectionBankId="";
    String collectionBankId1=f.getCollectionBankId().trim();//归集银行
    Printdto dto = new Printdto();
    collectionBankId="'"+collectionBankId1+"'";
    String queryTime=f.getQueryTime().trim();
    
    ICumulativeinfoFindBS cumulativeinfoFindBS = (ICumulativeinfoFindBS)BSUtils.getBusinessService("cumulativeinfoFindBS",this,mapping.getModuleConfig());
    returnList=cumulativeinfoFindBS.findFundbankmonthofyear(securityInfo,officeCode,collectionBankId,queryTime);
    
    collectionBankId=cumulativeinfoFindBS.find_bank_realname(collectionBankId);
    
    dto.setBank_name(collectionBankId);
    dto.setDate(securityInfo.getUserInfo().getBizDate());   
    dto.setOperater(securityInfo.getRealName().toString());
    dto.setDba(securityInfo.getRealName().toString());
    request.setAttribute("printList", returnList);
    request.setAttribute("dto", dto);
    request.setAttribute("queryTime", queryTime);
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "fundbankmonthofyear_find";
  }
  
}


