package org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.action;

import java.util.ArrayList;
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
import org.xpup.hafmis.orgstrct.dto.OfficeDto;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.bsinterface.ICollectionuseinfoFindBS;
import org.xpup.hafmis.syscollection.querystatistics.collectionuseinfo.form.CollectionuseinfoFindAF;
import org.xpup.security.common.domain.Userslogincollbank;

/**
 * 根据时间，查询住房公积金累计归集情况表
 * 
 *@author 杨光
 *2007-10-05
 */
public class CollectionuseinfoFindAC extends Action {

  
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    ActionMessages messages = null;
    try{
      SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
      CollectionuseinfoFindAF f=(CollectionuseinfoFindAF)form;
      List returnList=new ArrayList();
      List officeCodeList=new ArrayList();
      List collectionBankIdList=new ArrayList();
      String officeCode="";
      String collectionBankId="";
      
      String officeCode1=f.getOfficeCode().trim();//办事处
      if(officeCode1==null || officeCode1.equals("")){
        officeCodeList=securityInfo.getOfficeList();
        OfficeDto officedto = null;
        for(int i=0;i<officeCodeList.size()-1;i++){
          officedto=(OfficeDto) officeCodeList.get(i);
          officeCode=officeCode+"'"+officedto.getOfficeCode()+"',";
        }
        officeCode=officeCode+"'"+((OfficeDto)(officeCodeList.get(officeCodeList.size()-1))).getOfficeCode()+"'";
      }else{
        officeCode="'"+officeCode1+"'";
      }
      String collectionBankId1=f.getCollectionBankId().trim();//归集银行
      if(collectionBankId1==null || collectionBankId1.equals("")){
        collectionBankIdList=securityInfo.getCollBankList();
        Userslogincollbank userslogincollbank = null;
        for(int i=0;i<collectionBankIdList.size()-1;i++){
          userslogincollbank=(Userslogincollbank) collectionBankIdList.get(i);
          collectionBankId=collectionBankId+"'"+userslogincollbank.getCollBankId()+"',";
        }
        collectionBankId=collectionBankId+"'"+((Userslogincollbank)(collectionBankIdList.get(collectionBankIdList.size()-1))).getCollBankId()+"'";
      }else{
        collectionBankId="'"+collectionBankId1+"'";
      }
      String queryTime=f.getQueryTime().trim();
      
      ICollectionuseinfoFindBS collectionuseinfoFindBS = (ICollectionuseinfoFindBS)BSUtils.getBusinessService("collectionuseinfoFindBS",this,mapping.getModuleConfig());
      returnList=collectionuseinfoFindBS.findCollectionuseinfo(securityInfo, officeCode, collectionBankId, queryTime);
      
      request.setAttribute("printList", returnList);
    }catch (BusinessException bex) {
      bex.printStackTrace();
      messages = new ActionMessages();
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(bex
          .getLocalizedMessage().toString(), false));
      saveErrors(request, messages);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
   
    return mapping.findForward(getForword());
  }

  protected String getForword() {
    return "to_collectionuseinfo_find";
  }
  
}


