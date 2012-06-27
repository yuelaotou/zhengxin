package org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.sysloan.common.dao.LoanBankDAO;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.bsinterface.IBankQueryBS;
import org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.dto.BankQueryDTO;

public class BankQueryBS implements IBankQueryBS {
  private LoanBankDAO loanBankDAO = null;

  private CollBankDAO collBankDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;
  
  private SecurityDAO securityDAO=null;

  /**
   * 统计查询_放款银行查询
   * 
   * @param pagination
   * @param officeList
   * @param bankList
   * @author wsh 2007-10-09 查询列表信息
   */
  public List findBankQueryList(Pagination pagination, List officeList,
      List bankList) throws Exception {
    List list = null;
    List countlist = null;
    String office = "";
    String loanBankId = "";
    String bankPrisident = "";
    String contactPrsn = "";
    try {
      if (pagination.getQueryCriterions().get("office") != null) {
        office = (String) pagination.getQueryCriterions().get("office");
      }
      if (pagination.getQueryCriterions().get("loanBankId") != null) {
        loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
      }
      if (pagination.getQueryCriterions().get("bankPrisident") != null) {
        bankPrisident = (String) pagination.getQueryCriterions().get(
            "bankPrisident");
      }
      if (pagination.getQueryCriterions().get("contactPrsn") != null) {
        contactPrsn = (String) pagination.getQueryCriterions().get(
            "contactPrsn");
      }
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      list = loanBankDAO.queryBankQueryList_wsh(office, loanBankId,
          bankPrisident, contactPrsn, orderBy, order, start, pageSize,
          officeList, bankList);
      if (list.size() != 0) {
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
          BankQueryDTO bankQueryDTO = (BankQueryDTO) iter.next();
          CollBank dto = collBankDAO.getCollBankByCollBankid(bankQueryDTO
              .getLoanBankId().toString());
          bankQueryDTO.setLoanBankId(dto.getCollBankName());// 银行名称
          // 转换办事处
          OrganizationUnit organizationUnit = organizationUnitDAO
              .queryOrganizationUnitListByCriterions(bankQueryDTO.getOffice());
          bankQueryDTO.setOffice(organizationUnit.getName());
          bankQueryDTO.setLoanBnakSt((BusiTools.getBusiValue(Integer
              .parseInt(bankQueryDTO.getLoanBnakSt()),
              BusiConst.APPSTATUS)));
        }
      }
      countlist = loanBankDAO.queryAssistantOrgListCount_wsh(office,
          loanBankId, bankPrisident, contactPrsn, officeList, bankList);
      if (countlist.size() > 0) {
        int count = countlist.size();
        pagination.setNrOfElements(count);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

  /**
   * 统计查询_放款银行查询
   * 
   * @param id
   * @author wsh 2007-10-10 打印单条信息
   */
  public BankQueryDTO findBankQueryInfo(String id) throws Exception {
    // TODO Auto-generated method stub
    BankQueryDTO bankQueryDTO = new BankQueryDTO();
    List list = new ArrayList();
    try {
      list = loanBankDAO.findBankQueryInfo_wsh(id);
      bankQueryDTO = (BankQueryDTO) list.get(0);
      CollBank dto = collBankDAO.getCollBankByCollBankid(bankQueryDTO
          .getLoanBankId().toString());
      bankQueryDTO.setLoanBankId(dto.getCollBankName());// 银行名称
      // 转换办事处
      OrganizationUnit organizationUnit = organizationUnitDAO
          .queryOrganizationUnitListByCriterions(bankQueryDTO.getOffice());
      bankQueryDTO.setOffice(organizationUnit.getName());
      bankQueryDTO
          .setLoanBnakSt((BusiTools.getBusiValue(Integer.parseInt(bankQueryDTO
              .getLoanBnakSt()), BusiConst.APPSTATUS)));
    } catch (Exception e) {
      // TODO: handle exception
    }
    return bankQueryDTO;
  }

  /**
   * 统计查询_放款银行查询
   * 
   * @param pagination
   * @param officeList
   * @param bankList
   * @author wsh 2007-10-10 打印列表信息
   */
  public List queryBankQueryListCount_wsh(Pagination pagination,
      List officeList, List bankList) throws Exception {
    // TODO Auto-generated method stub
    List countlist = null;
    String office = "";
    String loanBankId = "";
    String bankPrisident = "";
    String contactPrsn = "";
    try {
      if (pagination.getQueryCriterions().get("office") != null) {
        office = (String) pagination.getQueryCriterions().get("office");
      }
      if (pagination.getQueryCriterions().get("loanBankId") != null) {
        loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
      }
      if (pagination.getQueryCriterions().get("bankPrisident") != null) {
        bankPrisident = (String) pagination.getQueryCriterions().get(
            "bankPrisident");
      }
      if (pagination.getQueryCriterions().get("contactPrsn") != null) {
        contactPrsn = (String) pagination.getQueryCriterions().get(
            "contactPrsn");
      }
      countlist = loanBankDAO.queryAssistantOrgListCount_wsh(office,
          loanBankId, bankPrisident, contactPrsn, officeList, bankList);
      if (countlist.size() != 0) {
        Iterator iter = countlist.iterator();
        while (iter.hasNext()) {
          BankQueryDTO bankQueryDTO = (BankQueryDTO) iter.next();
          CollBank dto = collBankDAO.getCollBankByCollBankid(bankQueryDTO
              .getLoanBankId().toString());
          bankQueryDTO.setLoanBankId(dto.getCollBankName());// 银行名称
          // 转换办事处
          OrganizationUnit organizationUnit = organizationUnitDAO
              .queryOrganizationUnitListByCriterions(bankQueryDTO.getOffice());
          bankQueryDTO.setOffice(organizationUnit.getName());
          bankQueryDTO.setLoanBnakSt((BusiTools.getBusiValue(Integer
              .parseInt(bankQueryDTO.getLoanBnakSt()),
              BusiConst.APPSTATUS)));
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
    }
    return countlist;
  }

  public void setLoanBankDAO(LoanBankDAO loanBankDAO) {
    this.loanBankDAO = loanBankDAO;
  }

  public void setCollBankDAO(CollBankDAO collBankDAO) {
    this.collBankDAO = collBankDAO;
  }

  public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
    this.organizationUnitDAO = organizationUnitDAO;
  }
  /*获取真实姓名
   * (non-Javadoc)
   * @see org.xpup.hafmis.sysloan.loanapply.issuenotice.bsinterface.IIssuenoticeBS#getUserRealName(java.lang.String)
   */
    public String getUserRealName(String name) throws Exception {
      // TODO Auto-generated method stub
      String realName="";
      try {
       
        realName=securityDAO.queryByUserid(name);
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
      return realName;
    }

    public void setSecurityDAO(SecurityDAO securityDAO) {
      this.securityDAO = securityDAO;
    }
}
