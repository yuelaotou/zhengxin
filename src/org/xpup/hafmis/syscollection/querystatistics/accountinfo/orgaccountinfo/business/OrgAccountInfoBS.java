package org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.OrgHAFAccountFlowDAO;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.bsinterface.IOrgAccountInfoBS;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.dto.OrgAccountInfoDTO;
import org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.dto.OrgAccountInfoTotalDTO;

public class OrgAccountInfoBS implements IOrgAccountInfoBS{
  
  private OrgHAFAccountFlowDAO orgHAFAccountFlowDAO = null;
  private OrganizationUnitDAO organizationUnitDAO = null;
  private CollBankDAO collBankDAO = null;

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }

  public void setOrgHAFAccountFlowDAO(OrgHAFAccountFlowDAO orgHAFAccountFlowDAO) {
    this.orgHAFAccountFlowDAO = orgHAFAccountFlowDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public List findOrgAccountInfo(Pagination pagination,SecurityInfo securityInfo){
    List officelist=null;
    List banklist=null;
    List orglist=null;
    List list=null;
    List list1=null;
    List list2=null;
    List list3=null;
    List list4=null;
    List list5=null;
    List list6=null;
    List returnlist = new ArrayList();
    String orgId="";
    Serializable orgid=(Serializable) pagination.getQueryCriterions().get("orgid");
    String balance="";
    if(orgid!=null){
      orgId=orgid.toString();
    }
    String orgname=(String) pagination.getQueryCriterions().get("orgname");
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String mode=(String)pagination.getQueryCriterions().get("mode");
    String officecode=(String)pagination.getQueryCriterions().get("officecode");
    String collectionBankId=(String)pagination.getQueryCriterions().get("collBankId");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String type=(String)pagination.getQueryCriterions().get("type");
    if(type==null){
      type="1";
    }
    try{
      if(!type.equals("0")){
      if(mode.equals("1")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.officeCode";
        officelist=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCode(officecode, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!officelist.isEmpty()){
          for(int i=0;i<officelist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)officelist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOfficecode());//办事处
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(orgAccountInfoDTO.getOfficecode());
            dto.setOfficename(organizationUnit.getName());
  
            list = orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeDebit(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int ii=0;ii<list.size();ii++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(ii);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodePreBalance(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int j=0;j<list1.size();j++){
                 OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(j);
                 dto.setPrebalance(dtos1.getPrebalance());//期初余额
               }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCredit(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int k=0;k<list2.size();k++){
                 OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(k);
                 dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                 dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数
               }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeOverMoney(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
               for(int m=0;m<list3.size();m++){
                 OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                 dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
               }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCurBalance(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
               for(int n=0;n<list4.size();n++){
                 OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                 dto.setCurbalance(dtos4.getCurbalance());//期末余额
               }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOfficecodeOverBalance(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
               for(int l=0;l<list5.size();l++){
                 OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(l);
                 dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
               }
            }
           
            list6=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeBalance(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page); // 增加的帐户余额
            if(!list6.isEmpty()){
              for(int l=0;l<list6.size();l++){
                OrgAccountInfoDTO dtos6 =(OrgAccountInfoDTO)list6.get(l);
                dto.setAccountBalance(dtos6.getAccountBalance());
              }
           }
            
            //账面余额期末余额+挂账余额
            dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
            //查询时间
            if(inOpDate!= null && !inOpDate.equals("")&&opDate!=null && !opDate.equals("")){
              dto.setOpTime(inOpDate+"-"+opDate);
            }else if(inOpDate!= null && !inOpDate.equals("")){
              dto.setOpTime(inOpDate);
            }else{
              dto.setOpTime(opDate);
            }
              returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountByOfficeCode(officecode, opDate, inOpDate, securityInfo).size();

      }else if(mode.equals("2")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.moneyBank ";
        banklist=orgHAFAccountFlowDAO.queryOrgAccountByCollBank(collectionBankId, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!banklist.isEmpty()){
          for(int i=0;i<banklist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)banklist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getCollbankid());//银行
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
       
            list = orgHAFAccountFlowDAO.queryOrgAccountByCollBankDebit(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByCollBankPreBalance(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCredit(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverMoney(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCurBalance(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverBalance(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
            
            list6=orgHAFAccountFlowDAO.queryOrgAccountByCollBankBalance(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);// 增加的帐户余额
            if(!list6.isEmpty()){
              for(int l=0;l<list6.size();l++){
                OrgAccountInfoDTO dtos6 =(OrgAccountInfoDTO)list6.get(l);  
           //    balance=orgHAFAccountFlowDAO.findBankBalance(orgAccountInfoDTO.getCollbankid());
             //  dto.setAccountBalance(new BigDecimal(balance));
               dto.setAccountBalance(dtos6.getAccountBalance());
              }
            }
            
            
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             if(inOpDate!= null && !inOpDate.equals("")&&opDate!=null && !opDate.equals("")){
                dto.setOpTime(inOpDate+"-"+opDate);
             }else if(inOpDate!= null && !inOpDate.equals("")){
                dto.setOpTime(inOpDate);
             }else{
                dto.setOpTime(opDate);
             }
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountByCollBank(collectionBankId, opDate, inOpDate, securityInfo).size();

      }else {
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.org.id";
        orglist=orgHAFAccountFlowDAO.queryOrgAccountByOrg(orgId, orgname, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);      
        if(!orglist.isEmpty()){
          for(int i=0;i<orglist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)orglist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOrgid());//单位
            dto.setOrgid(new Integer(orgAccountInfoDTO.getOrgid().toString()));
            dto.setOrgname(orgAccountInfoDTO.getOrgname());
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
            list = orgHAFAccountFlowDAO.queryOrgAccountByOrgDebit(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOrgPreBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOrgCredit(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverMoney(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(), orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOrgCurBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
            
           // list6=orgHAFAccountFlowDAO.queryOrgBalanceByOrgid(orgAccountInfoDTO.getOrgid().toString(), orgname, securityInfo );
            list6=orgHAFAccountFlowDAO.queryOrgAccountByOrgBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate, orgAccountInfoDTO.getCollbankid(), officecode, securityInfo, orderBy, order, start, pageSize, page);
             if(!list6.isEmpty()){
               for(int l=0;l<list6.size();l++){
                OrgAccountInfoDTO dtos6 =(OrgAccountInfoDTO)list6.get(l);  // 增加的帐户余额
                dto.setAccountBalance(dtos6.getAccountBalance());
              }
            }
            
          //   balance=orgHAFAccountFlowDAO.findOrgBalance(orgAccountInfoDTO.getOrgid().toString());
          //   dto.setAccountBalance(new BigDecimal( balance));
            
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             if(inOpDate!= null && !inOpDate.equals("")&&opDate!=null && !opDate.equals("")){
                dto.setOpTime(inOpDate+"-"+opDate);
             }else if(inOpDate!= null && !inOpDate.equals("")){
                dto.setOpTime(inOpDate);
             }else{
                dto.setOpTime(opDate);
             }
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountByOrg(orgId, orgname, opDate, inOpDate, securityInfo).size();
      }
      }
      pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnlist;
  }
  public List findOrgAccountInfoByMonth(Pagination pagination,SecurityInfo securityInfo){
    List returnlist = new ArrayList();
    List officelist=null;
    List banklist=null;
    List orglist=null;
    List list=null;
    List list1=null;
    List list2=null;
    List list3=null;
    List list4=null;
    List list5=null;
    String orgname=(String) pagination.getQueryCriterions().get("orgname");
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String temp_time=(String) pagination.getQueryCriterions().get("opTime");
    String mode=(String)pagination.getQueryCriterions().get("mode");
    String id=(String)pagination.getQueryCriterions().get("id");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    try{
      if(mode.equals("1")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.officeCode";
        officelist=orgHAFAccountFlowDAO.queryOrgAccountMonthByOfficeCode(id, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!officelist.isEmpty()){
          for(int i=0;i<officelist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)officelist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOfficecode());//办事处
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(orgAccountInfoDTO.getOfficecode());
            dto.setOfficename(organizationUnit.getName());
            String tempdate=orgAccountInfoDTO.getOpTime();
            String tempStart=inOpDate.substring(0,6);
            String tempLast=opDate.substring(0,6);
            //期初时间
            String tempStartDate="";
            //期末时间
            String tempLastDate="";
            if(Integer.parseInt(tempdate)>Integer.parseInt(tempStart)){
              tempStartDate=tempdate+"01";
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=opDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            }
            if(Integer.parseInt(tempdate)==Integer.parseInt(tempStart)){
              tempStartDate=inOpDate;
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=inOpDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            } 
            list = orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeDebit(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int ii=0;ii<list.size();ii++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(ii);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodePreBalance(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int j=0;j<list1.size();j++){
                 OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(j);
                 dto.setPrebalance(dtos1.getPrebalance());//期初余额
               }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCredit(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int k=0;k<list2.size();k++){
                 OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(k);
                 dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                 dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数
               }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeOverMoney(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
               for(int m=0;m<list3.size();m++){
                 OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                 dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
               }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCurBalance(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
               for(int n=0;n<list4.size();n++){
                 OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                 dto.setCurbalance(dtos4.getCurbalance());//期末余额
               }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOfficecodeOverBalance(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
               for(int l=0;l<list5.size();l++){
                 OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(l);
                 dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
               }
            }
            //账面余额期末余额+挂账余额
            dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
            //查询时间
              dto.setOpTime(orgAccountInfoDTO.getOpTime());
              returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountMonthByOfficeCode(id,opDate,inOpDate, securityInfo).size();

      }else if(mode.equals("2")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.moneyBank";
        banklist=orgHAFAccountFlowDAO.queryOrgAccountMonthByCollBank(id,opDate,inOpDate,securityInfo, orderBy, order, start, pageSize, page);
        if(!banklist.isEmpty()){
          for(int i=0;i<banklist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)banklist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getCollbankid());//银行
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
            String tempdate=orgAccountInfoDTO.getOpTime();
            String tempStart=inOpDate.substring(0,6);
            String tempLast=opDate.substring(0,6);
            //期初时间
            String tempStartDate="";
            //期末时间
            String tempLastDate="";
            if(Integer.parseInt(tempdate)>Integer.parseInt(tempStart)){
              tempStartDate=tempdate+"01";
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=opDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            }
            if(Integer.parseInt(tempdate)==Integer.parseInt(tempStart)){
              tempStartDate=inOpDate;
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=inOpDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            } 
       
            list = orgHAFAccountFlowDAO.queryOrgAccountByCollBankDebit(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByCollBankPreBalance(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCredit(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverMoney(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCurBalance(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverBalance(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate,securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             dto.setOpTime(orgAccountInfoDTO.getOpTime());
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountMonthByCollBank(id,opDate,inOpDate,securityInfo).size();

      }else {
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.org.id";
        orglist=orgHAFAccountFlowDAO.queryOrgAccountMonthByOrg(id, orgname,opDate,inOpDate,securityInfo, orderBy, order, start, pageSize, page);      
        if(!orglist.isEmpty()){
          for(int i=0;i<orglist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)orglist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOrgid());//单位
            dto.setOrgid(new Integer(orgAccountInfoDTO.getOrgid().toString()));
            dto.setOrgname(orgAccountInfoDTO.getOrgname());
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
            String tempdate=orgAccountInfoDTO.getOpTime();
            String tempStart=inOpDate.substring(0,6);
            String tempLast=opDate.substring(0,6);
            //期初时间
            String tempStartDate="";
            //期末时间
            String tempLastDate="";
            if(Integer.parseInt(tempdate)>Integer.parseInt(tempStart)){
              tempStartDate=tempdate+"01";
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=opDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            }
            if(Integer.parseInt(tempdate)==Integer.parseInt(tempStart)){
              tempStartDate=inOpDate;
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=inOpDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            } 
       
            list = orgHAFAccountFlowDAO.queryOrgAccountByOrgDebit(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOrgPreBalance(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOrgCredit(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverMoney(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOrgCurBalance(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverBalance(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             dto.setOpTime(orgAccountInfoDTO.getOpTime());
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountMonthByOrg(id, orgname,opDate,inOpDate,securityInfo).size();
      }
      pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnlist;
}
 
  public List findOrgAccountInfoByDay(Pagination pagination,SecurityInfo securityInfo){
    List returnlist = new ArrayList();
    List officelist=null;
    List banklist=null;
    List orglist=null;
    List list=null;
    List list1=null;
    List list2=null;
    List list3=null;
    List list4=null;
    List list5=null;
    String orgname=(String) pagination.getQueryCriterions().get("orgname");
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String temp_time=(String) pagination.getQueryCriterions().get("opTime");
    String mode=(String)pagination.getQueryCriterions().get("mode");
    String id=(String)pagination.getQueryCriterions().get("id");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String nowStartDate="";
    String nowLastDate="";
    if(Integer.parseInt(temp_time+"01")>=Integer.parseInt(inOpDate)){
      nowStartDate=temp_time+"01";
      if(Integer.parseInt(temp_time+"31")>Integer.parseInt(opDate)){
        nowLastDate=opDate;
      }else{
        nowLastDate=temp_time+"31";
      }
    }else{
      nowStartDate=inOpDate;
      if(Integer.parseInt(temp_time+"31")>Integer.parseInt(opDate)){
        nowLastDate=opDate;
      }else{
        nowLastDate=temp_time+"31";
      }
    }
    try{
      if(mode.equals("1")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.officeCode";
        officelist=orgHAFAccountFlowDAO.queryOrgAccountDayByOfficeCode(id, nowLastDate, nowStartDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!officelist.isEmpty()){
          for(int i=0;i<officelist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)officelist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOfficecode());//办事处
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(orgAccountInfoDTO.getOfficecode());
            dto.setOfficename(organizationUnit.getName());
            list = orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeDebit(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int ii=0;ii<list.size();ii++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(ii);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodePreBalance(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int j=0;j<list1.size();j++){
                 OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(j);
                 dto.setPrebalance(dtos1.getPrebalance());//期初余额
               }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCredit(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int k=0;k<list2.size();k++){
                 OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(k);
                 dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                 dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数
               }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeOverMoney(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
               for(int m=0;m<list3.size();m++){
                 OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                 dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
               }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCurBalance(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
               for(int n=0;n<list4.size();n++){
                 OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                 dto.setCurbalance(dtos4.getCurbalance());//期末余额
               }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOfficecodeOverBalance(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
               for(int l=0;l<list5.size();l++){
                 OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(l);
                 dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
               }
            }
            //账面余额期末余额+挂账余额
            dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
            //查询时间
              dto.setOpTime(orgAccountInfoDTO.getOpTime());
              returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountDayByOfficeCode(id, nowLastDate, nowStartDate, securityInfo).size();

      }else if(mode.equals("2")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.moneyBank";
        banklist=orgHAFAccountFlowDAO.queryOrgAccountDayByCollBank(id, nowLastDate, nowStartDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!banklist.isEmpty()){
          for(int i=0;i<banklist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)banklist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getCollbankid());//银行
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
       
            list = orgHAFAccountFlowDAO.queryOrgAccountByCollBankDebit(orgAccountInfoDTO.getCollbankid(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByCollBankPreBalance(orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCredit(orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverMoney(orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCurBalance(orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverBalance(orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             dto.setOpTime(orgAccountInfoDTO.getOpTime());
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountDayByCollBank(id, nowLastDate, nowStartDate, securityInfo).size();

      }else {
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.org.id";
        orglist=orgHAFAccountFlowDAO.queryOrgAccountDayByOrg(id, orgname,  nowLastDate, nowStartDate, securityInfo, orderBy, order, start, pageSize, page);      
        if(!orglist.isEmpty()){
          for(int i=0;i<orglist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)orglist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOrgid());//单位
            dto.setOrgid(new Integer(orgAccountInfoDTO.getOrgid().toString()));
            dto.setOrgname(orgAccountInfoDTO.getOrgname());
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
            list = orgHAFAccountFlowDAO.queryOrgAccountByOrgDebit(orgAccountInfoDTO.getOrgid().toString(), orgname,orgAccountInfoDTO.getOpTime(), null, orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOrgPreBalance(orgAccountInfoDTO.getOrgid().toString(), orgname,orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(), orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOrgCredit(orgAccountInfoDTO.getOrgid().toString(), orgname,orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverMoney(orgAccountInfoDTO.getOrgid().toString(), orgname,orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOrgCurBalance(orgAccountInfoDTO.getOrgid().toString(), orgname,orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             dto.setOpTime(orgAccountInfoDTO.getOpTime());
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountDayByOrg(id, orgname, nowLastDate, nowStartDate, securityInfo).size();
      }
      pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnlist;
  }
  
  
  public List findOrgAccountInfoPrint(Pagination pagination,SecurityInfo securityInfo){
    List officelist=null;
    List banklist=null;
    List orglist=null;
    List list=null;
    List list1=null;
    List list2=null;
    List list3=null;
    List list4=null;
    List list5=null;
    List list6=null;
    List returnlist = new ArrayList();
    String orgId="";
    Serializable orgid=(Serializable) pagination.getQueryCriterions().get("orgid");
    String balance="";
    if(orgid!=null){
      orgId=orgid.toString();
    }
    String orgname=(String) pagination.getQueryCriterions().get("orgname");
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String mode=(String)pagination.getQueryCriterions().get("mode");
    String officecode=(String)pagination.getQueryCriterions().get("officecode");
    String collectionBankId=(String)pagination.getQueryCriterions().get("collBankId");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String type=(String)pagination.getQueryCriterions().get("type");
    if(type==null){
      type="1";
    }
    try{
      if(!type.equals("0")){
      if(mode.equals("1")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.officeCode";
        officelist=orgHAFAccountFlowDAO.queryOrgAccountPrintByOfficeCode(officecode, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!officelist.isEmpty()){
          for(int i=0;i<officelist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)officelist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOfficecode());//办事处
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(orgAccountInfoDTO.getOfficecode());
            dto.setOfficename(organizationUnit.getName());
  
            list = orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeDebit(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int ii=0;ii<list.size();ii++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(ii);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodePreBalance(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int j=0;j<list1.size();j++){
                 OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(j);
                 dto.setPrebalance(dtos1.getPrebalance());//期初余额
               }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCredit(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int k=0;k<list2.size();k++){
                 OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(k);
                 dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                 dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数
               }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeOverMoney(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
               for(int m=0;m<list3.size();m++){
                 OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                 dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
               }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCurBalance(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
               for(int n=0;n<list4.size();n++){
                 OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                 dto.setCurbalance(dtos4.getCurbalance());//期末余额
               }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOfficecodeOverBalance(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
               for(int l=0;l<list5.size();l++){
                 OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(l);
                 dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
               }
            }
            
            list6=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeBalance(orgAccountInfoDTO.getOfficecode(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page); // 增加的帐户余额
            if(!list6.isEmpty()){
              for(int l=0;l<list6.size();l++){
                OrgAccountInfoDTO dtos6 =(OrgAccountInfoDTO)list6.get(l);
                dto.setAccountBalance(dtos6.getAccountBalance());
              }
           }
            
            
            //账面余额期末余额+挂账余额
            dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
            //查询时间
            if(inOpDate!= null && !inOpDate.equals("")&&opDate!=null && !opDate.equals("")){
              dto.setOpTime(inOpDate+"-"+opDate);
            }else if(inOpDate!= null && !inOpDate.equals("")){
              dto.setOpTime(inOpDate);
            }else{
              dto.setOpTime(opDate);
            }
              returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountByOfficeCode(officecode, opDate, inOpDate, securityInfo).size();

      }else if(mode.equals("2")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.moneyBank";
        banklist=orgHAFAccountFlowDAO.queryOrgAccountPrintByCollBank(collectionBankId, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!banklist.isEmpty()){
          for(int i=0;i<banklist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)banklist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getCollbankid());//银行
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
       
            list = orgHAFAccountFlowDAO.queryOrgAccountByCollBankDebit(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByCollBankPreBalance(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCredit(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverMoney(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCurBalance(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverBalance(orgAccountInfoDTO.getCollbankid(), opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
           // list6=orgHAFAccountFlowDAO.queryOrgBalanceByBank(orgAccountInfoDTO.getCollbankid(), securityInfo );
            list6=orgHAFAccountFlowDAO.queryOrgAccountByOrgBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate, orgAccountInfoDTO.getCollbankid(), officecode, securityInfo, orderBy, order, start, pageSize, page);
            if(!list6.isEmpty()){
              for(int l=0;l<list6.size();l++){
               OrgAccountInfoDTO dtos6 =(OrgAccountInfoDTO)list6.get(l);  // 增加的帐户余额
               dto.setAccountBalance(dtos6.getAccountBalance());
             }
           }
         //   balance=orgHAFAccountFlowDAO.findBankBalance(orgAccountInfoDTO.getCollbankid());
         //    dto.setAccountBalance(new BigDecimal(balance));
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             if(inOpDate!= null && !inOpDate.equals("")&&opDate!=null && !opDate.equals("")){
                dto.setOpTime(inOpDate+"-"+opDate);
             }else if(inOpDate!= null && !inOpDate.equals("")){
                dto.setOpTime(inOpDate);
             }else{
                dto.setOpTime(opDate);
             }
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountByCollBank(collectionBankId, opDate, inOpDate, securityInfo).size();

      }else {
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.org.id";
        orglist=orgHAFAccountFlowDAO.queryOrgAccountPrintByOrg(orgId, orgname, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);      
        if(!orglist.isEmpty()){
          for(int i=0;i<orglist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)orglist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOrgid());//单位
            dto.setOrgid(orgAccountInfoDTO.getOrgid());
            dto.setOrgname(orgAccountInfoDTO.getOrgname());
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
            list = orgHAFAccountFlowDAO.queryOrgAccountByOrgDebit(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOrgPreBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOrgCredit(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverMoney(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOrgCurBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
            
            list6=orgHAFAccountFlowDAO.queryOrgAccountByOrgBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, opDate, inOpDate, orgAccountInfoDTO.getCollbankid(), officecode, securityInfo, orderBy, order, start, pageSize, page);
            if(!list6.isEmpty()){
              for(int l=0;l<list6.size();l++){
               OrgAccountInfoDTO dtos6 =(OrgAccountInfoDTO)list6.get(l);  // 增加的帐户余额
               dto.setAccountBalance(dtos6.getAccountBalance());
             }
           }
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             if(inOpDate!= null && !inOpDate.equals("")&&opDate!=null && !opDate.equals("")){
                dto.setOpTime(inOpDate+"-"+opDate);
             }else if(inOpDate!= null && !inOpDate.equals("")){
                dto.setOpTime(inOpDate);
             }else{
                dto.setOpTime(opDate);
             }
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountByOrg(orgId, orgname, opDate, inOpDate, securityInfo).size();
      }
      }
      pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnlist;
  }
  
  
  public List findOrgAccountInfoByMonthPrint(Pagination pagination,SecurityInfo securityInfo){
    List returnlist = new ArrayList();
    List officelist=null;
    List banklist=null;
    List orglist=null;
    List list=null;
    List list1=null;
    List list2=null;
    List list3=null;
    List list4=null;
    List list5=null;
    String orgname=(String) pagination.getQueryCriterions().get("orgname");
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String temp_time=(String) pagination.getQueryCriterions().get("opTime");
    String mode=(String)pagination.getQueryCriterions().get("mode");
    String id=(String)pagination.getQueryCriterions().get("id");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    try{
      if(mode.equals("1")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.officeCode";
        officelist=orgHAFAccountFlowDAO.queryOrgAccountMonthPrintByOfficeCode(id, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!officelist.isEmpty()){
          for(int i=0;i<officelist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)officelist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOfficecode());//办事处
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(orgAccountInfoDTO.getOfficecode());
            dto.setOfficename(organizationUnit.getName());
            String tempdate=orgAccountInfoDTO.getOpTime();
            String tempStart=inOpDate.substring(0,6);
            String tempLast=opDate.substring(0,6);
            //期初时间
            String tempStartDate="";
            //期末时间
            String tempLastDate="";
            if(Integer.parseInt(tempdate)>Integer.parseInt(tempStart)){
              tempStartDate=tempdate+"01";
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=opDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            }
            if(Integer.parseInt(tempdate)==Integer.parseInt(tempStart)){
              tempStartDate=inOpDate;
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=inOpDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            } 
            list = orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeDebit(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int ii=0;ii<list.size();ii++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(ii);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodePreBalance(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int j=0;j<list1.size();j++){
                 OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(j);
                 dto.setPrebalance(dtos1.getPrebalance());//期初余额
               }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCredit(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int k=0;k<list2.size();k++){
                 OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(k);
                 dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                 dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数
               }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeOverMoney(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
               for(int m=0;m<list3.size();m++){
                 OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                 dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
               }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCurBalance(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
               for(int n=0;n<list4.size();n++){
                 OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                 dto.setCurbalance(dtos4.getCurbalance());//期末余额
               }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOfficecodeOverBalance(orgAccountInfoDTO.getOfficecode(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
               for(int l=0;l<list5.size();l++){
                 OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(l);
                 dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
               }
            }
            //账面余额期末余额+挂账余额
            dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
            //查询时间
              dto.setOpTime(orgAccountInfoDTO.getOpTime());
              returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountMonthByOfficeCode(id,opDate,inOpDate, securityInfo).size();

      }else if(mode.equals("2")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.moneyBank";
        banklist=orgHAFAccountFlowDAO.queryOrgAccountMonthPrintByCollBank(id,opDate,inOpDate,securityInfo, orderBy, order, start, pageSize, page);
        if(!banklist.isEmpty()){
          for(int i=0;i<banklist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)banklist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getCollbankid());//银行
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
            String tempdate=orgAccountInfoDTO.getOpTime();
            String tempStart=inOpDate.substring(0,6);
            String tempLast=opDate.substring(0,6);
            //期初时间
            String tempStartDate="";
            //期末时间
            String tempLastDate="";
            if(Integer.parseInt(tempdate)>Integer.parseInt(tempStart)){
              tempStartDate=tempdate+"01";
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=opDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            }
            if(Integer.parseInt(tempdate)==Integer.parseInt(tempStart)){
              tempStartDate=inOpDate;
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=inOpDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            } 
       
            list = orgHAFAccountFlowDAO.queryOrgAccountByCollBankDebit(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByCollBankPreBalance(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCredit(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverMoney(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCurBalance(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverBalance(orgAccountInfoDTO.getCollbankid(),tempLastDate, tempStartDate,securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             dto.setOpTime(orgAccountInfoDTO.getOpTime());
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountMonthByCollBank(id,opDate,inOpDate,securityInfo).size();

      }else {
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.org.id";
        orglist=orgHAFAccountFlowDAO.queryOrgAccountMonthPrintByOrg(id, orgname,opDate,inOpDate,securityInfo, orderBy, order, start, pageSize, page);      
        if(!orglist.isEmpty()){
          for(int i=0;i<orglist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)orglist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOrgid());//单位
            dto.setOrgid(orgAccountInfoDTO.getOrgid());
            dto.setOrgname(orgAccountInfoDTO.getOrgname());
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
            String tempdate=orgAccountInfoDTO.getOpTime();
            String tempStart=inOpDate.substring(0,6);
            String tempLast=opDate.substring(0,6);
            //期初时间
            String tempStartDate="";
            //期末时间
            String tempLastDate="";
            if(Integer.parseInt(tempdate)>Integer.parseInt(tempStart)){
              tempStartDate=tempdate+"01";
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=opDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            }
            if(Integer.parseInt(tempdate)==Integer.parseInt(tempStart)){
              tempStartDate=inOpDate;
              if(Integer.parseInt(tempdate)>=Integer.parseInt(tempLast)){
                tempLastDate=inOpDate;
              }else{
                tempLastDate=tempdate+"31";
              }
            } 
       
            list = orgHAFAccountFlowDAO.queryOrgAccountByOrgDebit(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate, orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOrgPreBalance(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOrgCredit(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverMoney(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOrgCurBalance(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverBalance(orgAccountInfoDTO.getOrgid().toString(), orgname,tempLastDate, tempStartDate,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(),securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             dto.setOpTime(orgAccountInfoDTO.getOpTime());
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountMonthByOrg(id, orgname,opDate,inOpDate,securityInfo).size();
      }
      pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnlist;
}
  public List findOrgAccountInfoByDayPrint(Pagination pagination,SecurityInfo securityInfo){
    List returnlist = new ArrayList();
    List officelist=null;
    List banklist=null;
    List orglist=null;
    List list=null;
    List list1=null;
    List list2=null;
    List list3=null;
    List list4=null;
    List list5=null;
    String orgname=(String) pagination.getQueryCriterions().get("orgname");
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String temp_time=(String) pagination.getQueryCriterions().get("opTime");
    String mode=(String)pagination.getQueryCriterions().get("mode");
    String id=(String)pagination.getQueryCriterions().get("id");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String nowStartDate="";
    String nowLastDate="";
    if(Integer.parseInt(temp_time+"01")>=Integer.parseInt(inOpDate)){
      nowStartDate=temp_time+"01";
      if(Integer.parseInt(temp_time+"31")>Integer.parseInt(opDate)){
        nowLastDate=opDate;
      }else{
        nowLastDate=temp_time+"31";
      }
    }else{
      nowStartDate=inOpDate;
      if(Integer.parseInt(temp_time+"31")>Integer.parseInt(opDate)){
        nowLastDate=opDate;
      }else{
        nowLastDate=temp_time+"31";
      }
    }
    try{
      if(mode.equals("1")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.officeCode";
        officelist=orgHAFAccountFlowDAO.queryOrgAccountDayPrintByOfficeCode(id, nowLastDate, nowStartDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!officelist.isEmpty()){
          for(int i=0;i<officelist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)officelist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOfficecode());//办事处
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(orgAccountInfoDTO.getOfficecode());
            dto.setOfficename(organizationUnit.getName());
            list = orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeDebit(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int ii=0;ii<list.size();ii++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(ii);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodePreBalance(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int j=0;j<list1.size();j++){
                 OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(j);
                 dto.setPrebalance(dtos1.getPrebalance());//期初余额
               }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCredit(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int k=0;k<list2.size();k++){
                 OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(k);
                 dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                 dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数
               }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeOverMoney(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
               for(int m=0;m<list3.size();m++){
                 OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                 dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
               }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOfficeCodeCurBalance(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
               for(int n=0;n<list4.size();n++){
                 OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                 dto.setCurbalance(dtos4.getCurbalance());//期末余额
               }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOfficecodeOverBalance(orgAccountInfoDTO.getOfficecode(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
               for(int l=0;l<list5.size();l++){
                 OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(l);
                 dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
               }
            }
            //账面余额期末余额+挂账余额
            dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
            //查询时间
              dto.setOpTime(orgAccountInfoDTO.getOpTime());
              returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountDayByOfficeCode(id, nowLastDate, nowStartDate, securityInfo).size();

      }else if(mode.equals("2")){
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.moneyBank";
        banklist=orgHAFAccountFlowDAO.queryOrgAccountDayPrintByCollBank(id, nowLastDate, nowStartDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!banklist.isEmpty()){
          for(int i=0;i<banklist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)banklist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getCollbankid());//银行
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
       
            list = orgHAFAccountFlowDAO.queryOrgAccountByCollBankDebit(orgAccountInfoDTO.getCollbankid(), orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByCollBankPreBalance(orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCredit(orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverMoney(orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByCollBankCurBalance(orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByCollBankOverBalance(orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOpTime(), null, securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             dto.setOpTime(orgAccountInfoDTO.getOpTime());
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountDayByCollBank(id, nowLastDate, nowStartDate, securityInfo).size();

      }else {
        if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.org.id";
        orglist=orgHAFAccountFlowDAO.queryOrgAccountDayPrintByOrg(id, orgname,  nowLastDate, nowStartDate, securityInfo, orderBy, order, start, pageSize, page);      
        if(!orglist.isEmpty()){
          for(int i=0;i<orglist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)orglist.get(i);
            OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            dto.setId(orgAccountInfoDTO.getOrgid());//单位
            dto.setOrgid(orgAccountInfoDTO.getOrgid());
            dto.setOrgname(orgAccountInfoDTO.getOrgname());
            CollBank collBank = collBankDAO.getCollBankByCollBankid(orgAccountInfoDTO.getCollbankid());
            dto.setCollbankname(collBank.getCollBankName());
            OrganizationUnit organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(collBank.getOffice());
            dto.setOfficename(organizationUnit.getName());
            list = orgHAFAccountFlowDAO.queryOrgAccountByOrgDebit(orgAccountInfoDTO.getOrgid().toString(), orgname,orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos =(OrgAccountInfoDTO)list.get(j);
                dto.setTemp_debit(dtos.getTemp_debit());//本期借方发生额
                dto.setCountDebit(dtos.getCountDebit());//本期借方笔数
              }
            }
            list1=orgHAFAccountFlowDAO.queryOrgAccountByOrgPreBalance(orgAccountInfoDTO.getOrgid().toString(), orgname,orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list1.isEmpty()){
              for(int k=0;k<list1.size();k++){
                OrgAccountInfoDTO dtos1 =(OrgAccountInfoDTO)list1.get(k);
                dto.setPrebalance(dtos1.getPrebalance());//期初余额                
              }
            }
            list2=orgHAFAccountFlowDAO.queryOrgAccountByOrgCredit(orgAccountInfoDTO.getOrgid().toString(), orgname,orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list2.isEmpty()){
              for(int l=0;l<list2.size();l++){
                OrgAccountInfoDTO dtos2 =(OrgAccountInfoDTO)list2.get(l);
                dto.setTemp_credit(dtos2.getTemp_credit());//本期贷方发生额
                dto.setCountCredit(dtos2.getCountCredit());//本期贷方笔数                
              }
            }
            list3=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverMoney(orgAccountInfoDTO.getOrgid().toString(), orgname,orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list3.isEmpty()){
              for(int m=0;m<list3.size();m++){
                OrgAccountInfoDTO dtos3 =(OrgAccountInfoDTO)list3.get(m);
                dto.setOrgOverMoney(dtos3.getOrgOverMoney());//挂账金额
              }
            }
            list4=orgHAFAccountFlowDAO.queryOrgAccountByOrgCurBalance(orgAccountInfoDTO.getOrgid().toString(), orgname,orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list4.isEmpty()){
              for(int n=0;n<list4.size();n++){
                OrgAccountInfoDTO dtos4 =(OrgAccountInfoDTO)list4.get(n);
                dto.setCurbalance(dtos4.getCurbalance());//期末余额
              }
            }
            list5=orgHAFAccountFlowDAO.queryOrgAccountByOrgOverBalance(orgAccountInfoDTO.getOrgid().toString(), orgname, orgAccountInfoDTO.getOpTime(), null,orgAccountInfoDTO.getCollbankid(),orgAccountInfoDTO.getOfficecode(), securityInfo, orderBy, order, start, pageSize, page);
            if(!list5.isEmpty()){
              for(int ii=0;ii<list5.size();ii++){
                OrgAccountInfoDTO dtos5 =(OrgAccountInfoDTO)list5.get(ii);
                dto.setOrgOverPaybalance(dtos5.getOrgOverPaybalance());//挂账余额
              }
            }
             //账面余额期末余额+挂账余额
             dto.setBalance(dto.getCurbalance().add(dto.getOrgOverPaybalance()));
             //查询时间
             dto.setOpTime(orgAccountInfoDTO.getOpTime());
             returnlist.add(dto);
          }
        }
        count = orgHAFAccountFlowDAO.queryOrgAccountCountDayByOrg(id, orgname, nowLastDate, nowStartDate, securityInfo).size();
      }
      pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnlist;
  }
 
  public OrgAccountInfoTotalDTO findOrgAccountInfoTotal(Pagination pagination,SecurityInfo securityInfo){

    String orgId="";
    Serializable orgid=(Serializable) pagination.getQueryCriterions().get("orgid");
    if(orgid!=null){
      orgId=orgid.toString();
    }
    String orgname=(String) pagination.getQueryCriterions().get("orgname");
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String mode=(String)pagination.getQueryCriterions().get("mode");
    String officecode=(String)pagination.getQueryCriterions().get("officecode");
    String collectionBankId=(String)pagination.getQueryCriterions().get("collBankId");
    OrgAccountInfoTotalDTO dto=new OrgAccountInfoTotalDTO();
    List list = null;String type=(String)pagination.getQueryCriterions().get("type");
    if(type==null){
      type="1";
    }
    try{
      if(!type.equals("0")){
        if(mode.equals("1")){
          list=orgHAFAccountFlowDAO.queryOrgAccountTotalByOfficeCode(officecode, opDate, inOpDate, securityInfo);
        }else if(mode.equals("2")){
          list=orgHAFAccountFlowDAO.queryOrgAccountTotalByCollBank(collectionBankId, opDate, inOpDate, securityInfo);
        }else {
          list=orgHAFAccountFlowDAO.queryOrgAccountTotalByOrg(orgId, orgname, opDate, inOpDate, securityInfo);      
        }
        if(!list.isEmpty()){
          dto=(OrgAccountInfoTotalDTO)list.get(0);
        }
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return dto;
  }
  public OrgAccountInfoTotalDTO findOrgAccountInfoMonthTotal(Pagination pagination,SecurityInfo securityInfo){
    String id=(String)pagination.getQueryCriterions().get("id");
    String orgname=(String) pagination.getQueryCriterions().get("orgname");
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String mode=(String)pagination.getQueryCriterions().get("mode");
    OrgAccountInfoTotalDTO dto=new OrgAccountInfoTotalDTO();
    List list = null;
    try{
        if(mode.equals("1")){
          list=orgHAFAccountFlowDAO.queryOrgAccountTotalByOfficeCode(id, opDate, inOpDate, securityInfo);
        }else if(mode.equals("2")){
          list=orgHAFAccountFlowDAO.queryOrgAccountTotalByCollBank(id, opDate, inOpDate, securityInfo);
        }else {
          list=orgHAFAccountFlowDAO.queryOrgAccountTotalByOrg(id, orgname, opDate, inOpDate, securityInfo);      
        }
        if(!list.isEmpty()){
          dto=(OrgAccountInfoTotalDTO)list.get(0);
        }
    }catch(Exception e){
      e.printStackTrace();
    }
    return dto;
  }
  public OrgAccountInfoTotalDTO findOrgAccountInfoDayTotal(Pagination pagination,SecurityInfo securityInfo){

    String orgname=(String) pagination.getQueryCriterions().get("orgname");
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String mode=(String)pagination.getQueryCriterions().get("mode");
    String temp_time=(String) pagination.getQueryCriterions().get("opTime");
    String id=(String)pagination.getQueryCriterions().get("id");
    OrgAccountInfoTotalDTO dto=new OrgAccountInfoTotalDTO();
    List list = null;
    String nowStartDate="";
    String nowLastDate="";
    if(Integer.parseInt(temp_time+"01")>=Integer.parseInt(inOpDate)){
      nowStartDate=temp_time+"01";
      if(Integer.parseInt(temp_time+"31")>Integer.parseInt(opDate)){
        nowLastDate=opDate;
      }else{
        nowLastDate=temp_time+"31";
      }
    }else{
      nowStartDate=inOpDate;
      if(Integer.parseInt(temp_time+"31")>Integer.parseInt(opDate)){
        nowLastDate=opDate;
      }else{
        nowLastDate=temp_time+"31";
      }
    }
    try{
        if(mode.equals("1")){
          list=orgHAFAccountFlowDAO.queryOrgAccountTotalByOfficeCode(id, nowLastDate, nowStartDate, securityInfo);
        }else if(mode.equals("2")){
          list=orgHAFAccountFlowDAO.queryOrgAccountTotalByCollBank(id, nowLastDate, nowStartDate, securityInfo);
        }else {
          list=orgHAFAccountFlowDAO.queryOrgAccountTotalByOrg(id, orgname, nowLastDate, nowStartDate, securityInfo);      
        }
        if(!list.isEmpty()){
          dto=(OrgAccountInfoTotalDTO)list.get(0);
        }
      
    }catch(Exception e){
      e.printStackTrace();
    }
    return dto;
  }
  public List findOrgAccountInfos(Pagination pagination,SecurityInfo securityInfo){
    List returnlist=new ArrayList();
    List list=null;
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String mode=(String)pagination.getQueryCriterions().get("mode");
    String id=(String)pagination.getQueryCriterions().get("id");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String year1="";
    String year2="";
    String month1="";
    String month2="";
    String day1="";
    String day2="";
    int monthCounts=0;
    int yearCounts=0;
    try{
      if(mode.equals("1")){
        pagination.getQueryCriterions().put("officecode",id);
      }else if(mode.equals("2")){
        pagination.getQueryCriterions().put("collBankId",id);
      }else{
        pagination.getQueryCriterions().put("orgid",id);        
      }
    if(inOpDate!=null && !inOpDate.equals("")&&opDate != null && !opDate.equals("")){
       year1=inOpDate.substring(0,4);
       month1=inOpDate.substring(4,6);
       year2=opDate.substring(0,4);
       month2=opDate.substring(4,6);
       day1=inOpDate.substring(6,8);
       day2=opDate.substring(6,8);
       yearCounts=Integer.parseInt(year2)-Integer.parseInt(year1)+1;
       monthCounts=BusiTools.monthInterval(inOpDate.substring(0,6), opDate.substring(0,6))+1;
       for(int y=0;y<yearCounts;y++){
         for(int m=0;m<monthCounts;m++){
           int month = Integer.parseInt(month1)+m;
           if(month==13){
             month1="1";
             monthCounts=monthCounts-m;
             break;
           }
           String months="";
           if(month<10){
             months="0"+month;
           }else{
             months=month+"";
           }
           if(y==0&&m==0){
             pagination.getQueryCriterions().put("inOpDate",inOpDate);
             pagination.getQueryCriterions().put("opDate",Integer.parseInt(year1)+y+""+months+"31");             
           }else if(y==yearCounts-1&&m==monthCounts-1){
             pagination.getQueryCriterions().put("inOpDate",Integer.parseInt(year2)+""+months+"01");
             pagination.getQueryCriterions().put("opDate",opDate);
           }else if(y<yearCounts){
             pagination.getQueryCriterions().put("inOpDate",Integer.parseInt(year1)+y+""+months+"01");
             pagination.getQueryCriterions().put("opDate",Integer.parseInt(year1)+y+""+months+"31");  
           }
           list=findOrgAccountInfo(pagination,securityInfo);
            OrgAccountInfoDTO dto=new OrgAccountInfoDTO(); 
           if(!list.isEmpty()){
             dto=(OrgAccountInfoDTO)list.get(0);
             dto.setOpTime(Integer.parseInt(year1)+y+""+months);
             returnlist.add(dto);
           }
         }
         
       }
    }else if(inOpDate!=null && !inOpDate.equals("")){
       pagination.getQueryCriterions().put("inOpDate",inOpDate);
       list=findOrgAccountInfo(pagination,securityInfo);
       OrgAccountInfoDTO dto=new OrgAccountInfoDTO(); 
      if(!list.isEmpty()){
        dto=(OrgAccountInfoDTO)list.get(0);
        dto.setOpTime(inOpDate.substring(0,6));
        returnlist.add(dto);
      }
    }else if(opDate != null && !opDate.equals("")){
       pagination.getQueryCriterions().put("opDate",opDate);
       list=findOrgAccountInfo(pagination,securityInfo);
       OrgAccountInfoDTO dto=new OrgAccountInfoDTO(); 
      if(!list.isEmpty()){
        dto=(OrgAccountInfoDTO)list.get(0);
        dto.setOpTime(opDate.substring(0,6));
        returnlist.add(dto);
      }
    }
    }catch(Exception e){
      e.printStackTrace();
    }
    return returnlist;
  }
  public List findtemp(Pagination pagination,SecurityInfo securityInfo){
    List officelist=null;
    List banklist=null;
    List orglist=null;
    List returnlist=new ArrayList();
    String id=(String)pagination.getQueryCriterions().get("id");
    String inOpDate=(String) pagination.getQueryCriterions().get("inOpDate");
    String opDate=(String)pagination.getQueryCriterions().get("opDate");
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    List list = findOrgAccountInfos(pagination,securityInfo);
    String mode=(String)pagination.getQueryCriterions().get("mode");
    if(mode.equals("1")){
      if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.org.orgInfo.officecode";
        officelist=orgHAFAccountFlowDAO.queryOrgAccountMonthByOfficeCode(id, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!officelist.isEmpty()){
          for(int i=0;i<officelist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)officelist.get(i);
         //   OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos=(OrgAccountInfoDTO)list.get(j);
                if(orgAccountInfoDTO.getOpTime().equals(dtos.getOpTime())){
                  returnlist.add(dtos);
                }
              }
            }
          }
        }
        count=orgHAFAccountFlowDAO.queryOrgAccountCountMonthByOfficeCode(id, opDate, inOpDate, securityInfo).size();
    }else if(mode.equals("2")){
      if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.org.orgInfo.collectionBankId";
        banklist=orgHAFAccountFlowDAO.queryOrgAccountMonthByCollBank(id, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!banklist.isEmpty()){
          for(int i=0;i<banklist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)banklist.get(i);
        //    OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos=(OrgAccountInfoDTO)list.get(j);
                if(orgAccountInfoDTO.getOpTime().equals(dtos.getOpTime())){
                  returnlist.add(dtos);
                }
              }
            }
          }
        }
        count=orgHAFAccountFlowDAO.queryOrgAccountCountMonthByCollBank(id, opDate, inOpDate, securityInfo).size();
      
    }else if(mode.equals("3")){
      if(orderBy.equals("orgHAFAccountFlow.id"))
        orderBy="orgHAFAccountFlow.org.id";
        orglist=orgHAFAccountFlowDAO.queryOrgAccountMonthByOrg(id,null, opDate, inOpDate, securityInfo, orderBy, order, start, pageSize, page);
        if(!orglist.isEmpty()){
          for(int i=0;i<orglist.size();i++){
            OrgAccountInfoDTO orgAccountInfoDTO =(OrgAccountInfoDTO)orglist.get(i);
        //    OrgAccountInfoDTO dto = new OrgAccountInfoDTO();
            if(!list.isEmpty()){
              for(int j=0;j<list.size();j++){
                OrgAccountInfoDTO dtos=(OrgAccountInfoDTO)list.get(j);
                if(orgAccountInfoDTO.getOpTime().equals(dtos.getOpTime())){
                  returnlist.add(dtos);
                }
              }
            }
          }
        }
        count=orgHAFAccountFlowDAO.queryOrgAccountCountMonthByOrg(id,null,opDate, inOpDate, securityInfo).size();
      
    }

    pagination.setNrOfElements(count);
    return returnlist;
  }
}