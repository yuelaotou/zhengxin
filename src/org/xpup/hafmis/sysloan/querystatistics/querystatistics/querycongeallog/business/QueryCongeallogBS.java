package org.xpup.hafmis.sysloan.querystatistics.querystatistics.querycongeallog.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.CongealInfoDAO;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.querycongeallog.bsinterface.IQueryCongeallogBS;
import org.xpup.hafmis.sysloan.querystatistics.querystatistics.querycongeallog.dto.QueryCongeallogDTO;

public class QueryCongeallogBS implements IQueryCongeallogBS {

  private CongealInfoDAO congealInfoDAO = null;

  private CollBankDAO collBankDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  public CongealInfoDAO getCongealInfoDAO() {
    return congealInfoDAO;
  }

  public void setCongealInfoDAO(CongealInfoDAO congealInfoDAO) {
    this.congealInfoDAO = congealInfoDAO;
  }

  /**
   * 获得冻结表信息
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List queryCongeallogList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {

    List list = new ArrayList();
    try {
      String bankId = (String) pagination.getQueryCriterions().get("bankId");
      String officeName = (String) pagination.getQueryCriterions().get(
          "officeName");
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String empName = (String) pagination.getQueryCriterions().get("empName");
      String status = (String) pagination.getQueryCriterions().get("status");
      String type = (String) pagination.getQueryCriterions().get("type");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");

      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      // 冻结表信息
      List list_temp=null;
     list_temp = congealInfoDAO.queryCongeallogList(bankId, officeName, type,
          empName, status, cardNum, contractId, orderBy, orderother, start,
          pageSize, page, securityInfo);
      Iterator it = list_temp.iterator();
      while(it.hasNext()){
        QueryCongeallogDTO queryCongeallogDTO_temp = (QueryCongeallogDTO)it.next();
        //转换办事处
        OrganizationUnit organizationUnit = organizationUnitDAO
            .queryOrganizationUnitListByCriterions(queryCongeallogDTO_temp.getOfficeName());
        queryCongeallogDTO_temp.setOfficeName(organizationUnit.getName());
        //归集银行
        CollBank dto=collBankDAO.getCollBankByCollBankid(queryCongeallogDTO_temp.getBankId());
        queryCongeallogDTO_temp.setBankId(dto.getCollBankName());
        
        list.add(queryCongeallogDTO_temp);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
  
  /**
   * 获得冻结表信息记录数
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public int queryCongeallogListCount(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    try {
      String bankId = (String) pagination.getQueryCriterions().get("bankId");
      String officeName = (String) pagination.getQueryCriterions().get(
          "officeName");
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String empName = (String) pagination.getQueryCriterions().get("empName");
      String status = (String) pagination.getQueryCriterions().get("status");
      String type = (String) pagination.getQueryCriterions().get("type");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");

      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();

      // 冻结表信息记录数
      int list_temp = congealInfoDAO.queryCongeallogListCount(bankId, officeName, type,
          empName, status, cardNum, contractId, orderBy, orderother, start,
          pageSize, page, securityInfo);
      return list_temp;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
  
  
  public List queryCongeallogAllList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    List list=new ArrayList();
    List returnlist=new ArrayList();
    try {
      String bankId = (String) pagination.getQueryCriterions().get("bankId");
      String officeName = (String) pagination.getQueryCriterions().get(
          "officeName");
      String contractId = (String) pagination.getQueryCriterions().get(
          "contractId");
      String empName = (String) pagination.getQueryCriterions().get("empName");
      String status = (String) pagination.getQueryCriterions().get("status");
      String type = (String) pagination.getQueryCriterions().get("type");
      String cardNum = (String) pagination.getQueryCriterions().get("cardNum");

      String orderBy = (String) pagination.getOrderBy();
      String orderother = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      QueryCongeallogDTO queryCongeallogDTO_temp=null;
      // 冻结表信息记录数
       list = congealInfoDAO.queryCongeallogAllList(bankId, officeName, type, empName, status, cardNum, contractId, orderBy, orderother, 
           start, pageSize, page, securityInfo);
        Iterator it = list.iterator();
       while(it.hasNext()){
          queryCongeallogDTO_temp = (QueryCongeallogDTO)it.next();
         //转换办事处
         OrganizationUnit organizationUnit = organizationUnitDAO
             .queryOrganizationUnitListByCriterions(queryCongeallogDTO_temp.getOfficeName());
         queryCongeallogDTO_temp.setOfficeName(organizationUnit.getName());
         //归集银行
         CollBank dto=collBankDAO.getCollBankByCollBankid(queryCongeallogDTO_temp.getBankId());
         queryCongeallogDTO_temp.setBankId(dto.getCollBankName());
         
         returnlist.add(queryCongeallogDTO_temp);
       }  
     return returnlist;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  public CollBankDAO getCollBankDAO() {
    return collBankDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public OrganizationUnitDAO getOrganizationUnitDAO() {
    return organizationUnitDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }
}
