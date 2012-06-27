/**
 * Copy Right Information   : Goldsoft 
 * Project                  : LoanVIPCheckBS
 * @Version                 : 1.0
 * @author                  : wangy
 * 生成日期                   : 2007-09-27
 **/
package org.xpup.hafmis.sysloan.loanapply.othersloan.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.dao.SecurityDAO;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.HousesDAO;
import org.xpup.hafmis.sysloan.common.dao.LoanBankParaDAO;
import org.xpup.hafmis.sysloan.common.dao.OthersLoanDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.OthersLoan;
import org.xpup.hafmis.sysloan.loanapply.loanlastsure.form.LoanLastSureShowAF;
import org.xpup.hafmis.sysloan.loanapply.loanvipcheck.dto.LoanVIPCheckDTO;
import org.xpup.hafmis.sysloan.loanapply.othersloan.bsinterface.IOthersLoanBS;
import org.xpup.hafmis.sysloan.loanapply.othersloan.form.OthersLoanShowAF;
import org.xpup.hafmis.sysloan.loanapply.othersloan.form.OthersLoanTbShowAF;

public class OthersLoanBS implements IOthersLoanBS {

  private OthersLoanDAO othersLoanDAO = null;

  private BorrowerDAO borrowerDAO = null;

  private BorrowerAccDAO borrowerAccDAO = null;

  private HousesDAO housesDAO = null;

  private LoanBankParaDAO loanBankParaDAO = null;

  private CollBankDAO collBankDAO = null;

  private PlOperateLogDAO plOperateLogDAO = null;

  private OrganizationUnitDAO organizationUnitDAO = null;

  private SecurityDAO securityDAO = null;

  public void setSecurityDAO(SecurityDAO securityDAO) {
    this.securityDAO = securityDAO;
  }

  public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
    this.borrowerDAO = borrowerDAO;
  }

  public void setOthersLoanDAO(OthersLoanDAO othersLoanDAO) {
    this.othersLoanDAO = othersLoanDAO;
  }

  public OthersLoanShowAF queryBorrowerListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    String id = (String) pagination.getQueryCriterions().get("id");
    OthersLoanShowAF othersLoanShowAF = new OthersLoanShowAF();
    OthersLoan othersLoan = othersLoanDAO.queryById(new Integer(id));
    othersLoanShowAF.setOthersLoan(othersLoan);
    return othersLoanShowAF;
  }

  public void saveOthersLoanInfo(OthersLoanShowAF othersLoanShowAF,
      SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      if(othersLoanShowAF.getOthersLoan().getId()!=null&&!othersLoanShowAF.getOthersLoan().getId().equals(new Integer(0))){
        othersLoanDAO.updateOthersLoan(othersLoanShowAF.getOthersLoan());
      }else{
      othersLoanShowAF.getOthersLoan().setOpTime(new Date());
        othersLoanDAO.insert(othersLoanShowAF.getOthersLoan());
      }
     
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

  public OthersLoanTbShowAF queryOthersLoanListByCriterions(
      Pagination pagination, SecurityInfo securityInfo) throws Exception,
      BusinessException {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String name = (String) pagination.getQueryCriterions().get("name");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String office = (String) pagination.getQueryCriterions().get("office");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String empId = (String) pagination.getQueryCriterions().get("empId");
    String binDate = (String) pagination.getQueryCriterions().get("beginBizDate");
    String endDate = (String) pagination.getQueryCriterions().get("endBizDate");
    String city = (String) pagination.getQueryCriterions().get("city");
    OthersLoanTbShowAF othersLoanTbShowAF = new OthersLoanTbShowAF();
    List othersloanList = new ArrayList();
    List othersloanAllList = new ArrayList();
    List othersloanAllList_1 = new ArrayList();
    try {
      othersloanList = othersLoanDAO.queryBorrowerListByCriterions(name,
          cardNum, orgId, empId, orgName, office, city, binDate, endDate,
          start, orderBy, order, pageSize, page, securityInfo);

      othersloanAllList = othersLoanDAO.queryBorrowerAllListByCriterions(name,
          cardNum, orgId, empId, orgName, office, city, binDate, endDate,
          securityInfo);
      othersloanAllList_1 = othersLoanDAO.queryBorrowerAllListByCriterions_1(name,
          cardNum, orgId, empId, orgName, office, city, binDate, endDate,
          securityInfo);
      OthersLoan othersloan=new OthersLoan();
      if(othersloanAllList!=null&&othersloanAllList.size()>0){
        for (int i = 0; i < othersloanAllList.size(); i++) {
          othersloan=(OthersLoan)othersloanAllList.get(i);
          if(othersloan.getHOUSEAREA()!=null&&othersloan.getHOUSEAREA().trim().length()>0&&othersloan.getBORROWERADDR()!=null&&othersloan.getBORROWERADDR().trim().length()>0&&"借款人".equals(othersloan.getBORROWERADDR().trim())){
            othersLoanTbShowAF.setTotalHouseArea(othersLoanTbShowAF.getTotalHouseArea().add(new BigDecimal(othersloan.getHOUSEAREA())));
          }
          if(othersloan.getLOANMONEY()!=null&&othersloan.getLOANMONEY().trim().length()>0&&othersloan.getBORROWERADDR()!=null&&othersloan.getBORROWERADDR().trim().length()>0&&"借款人".equals(othersloan.getBORROWERADDR().trim())){
            othersLoanTbShowAF.setTotalLoanMoney(othersLoanTbShowAF.getTotalLoanMoney().add(new BigDecimal(othersloan.getLOANMONEY())));
          }
          if(othersloan.getHOUSETOTALPRICE()!=null&&othersloan.getHOUSETOTALPRICE().trim().length()>0&&othersloan.getBORROWERADDR()!=null&&othersloan.getBORROWERADDR().trim().length()>0&&"借款人".equals(othersloan.getBORROWERADDR().trim())){
            othersLoanTbShowAF.setTotalHousePrice(othersLoanTbShowAF.getTotalHousePrice().add(new BigDecimal(othersloan.getHOUSETOTALPRICE())));
          }
          
          if(othersloan.getBORROWERADDR()!=null&&othersloan.getBORROWERADDR().trim().length()>0&&"借款人".equals(othersloan.getBORROWERADDR().trim())){
            othersLoanTbShowAF.setTotalPerson(othersLoanTbShowAF.getTotalPerson()+1);
          }
        }
      }
      if (othersLoanTbShowAF.getTotalHousePrice()!=null&&othersLoanTbShowAF.getTotalHousePrice().compareTo(new BigDecimal(0))>0) {
        othersLoanTbShowAF.setTotalHousePrice(othersLoanTbShowAF.getTotalHousePrice().divide(
            new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_DOWN));
      }
      if (othersLoanTbShowAF.getTotalLoanMoney()!=null&&othersLoanTbShowAF.getTotalLoanMoney().compareTo(new BigDecimal(0))>0) {
        othersLoanTbShowAF.setTotalLoanMoney(othersLoanTbShowAF.getTotalLoanMoney().divide(
            new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_DOWN));
      }
      count = othersloanAllList_1.size();
    } catch (Exception e) {
      e.printStackTrace();
    }
    pagination.setNrOfElements(count);
    othersLoanTbShowAF.setList(othersloanList);
    othersLoanTbShowAF.setAllList(othersloanAllList);
    return othersLoanTbShowAF;
  }

  public void deleteOthersLoan(String id) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    try {
      othersLoanDAO.deleteById(id);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    
  }

  public OthersLoan queryOthersLoan(String id) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    OthersLoan otherLoan=new OthersLoan();
    try {
      otherLoan = othersLoanDAO.queryById(id);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    };
    return otherLoan;
  }

  public OthersLoanTbShowAF queryOthersLoanListTcByCriterions(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    int count = 0;
    String name = (String) pagination.getQueryCriterions().get("name");
    String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
    String office = (String) pagination.getQueryCriterions().get("office");
    String orgName = (String) pagination.getQueryCriterions().get("orgName");
    String orgId = (String) pagination.getQueryCriterions().get("orgId");
    String empId = (String) pagination.getQueryCriterions().get("empId");
    String binDate = (String) pagination.getQueryCriterions().get("beginBizDate");
    String endDate = (String) pagination.getQueryCriterions().get("endBizDate");
    String city = (String) pagination.getQueryCriterions().get("city");
    OthersLoanTbShowAF othersLoanTbShowAF = new OthersLoanTbShowAF();
    List othersloanList = new ArrayList();
    List othersloanAllList = new ArrayList();
    try {
      othersloanList = othersLoanDAO.queryBorrowerTcListByCriterions(name,
          cardNum, orgId, empId, orgName, office, city, binDate, endDate,
          start, orderBy, order, pageSize, page, securityInfo);

      othersloanAllList = othersLoanDAO.queryBorrowerTcAllListByCriterions(name,
          cardNum, orgId, empId, orgName, office, city, binDate, endDate,
          securityInfo);
      OthersLoan othersloan=new OthersLoan();
      count = othersloanAllList.size();
      if(othersloanAllList!=null&&othersloanAllList.size()>0){
        for (int i = 0; i < othersloanAllList.size(); i++) {
          othersloan=(OthersLoan)othersloanAllList.get(i);
          if(othersloan.getHOUSEAREA()!=null&&othersloan.getHOUSEAREA().trim().length()>0&&othersloan.getBORROWERADDR()!=null&&othersloan.getBORROWERADDR().trim().length()>0){
            othersLoanTbShowAF.setTotalHouseArea(othersLoanTbShowAF.getTotalHouseArea().add(new BigDecimal(othersloan.getHOUSEAREA())));
          }
          if(othersloan.getLOANMONEY()!=null&&othersloan.getLOANMONEY().trim().length()>0&&othersloan.getBORROWERADDR()!=null&&othersloan.getBORROWERADDR().trim().length()>0){
            othersLoanTbShowAF.setTotalLoanMoney(othersLoanTbShowAF.getTotalLoanMoney().add(new BigDecimal(othersloan.getLOANMONEY())));
          }
          if(othersloan.getHOUSETOTALPRICE()!=null&&othersloan.getHOUSETOTALPRICE().trim().length()>0&&othersloan.getBORROWERADDR()!=null&&othersloan.getBORROWERADDR().trim().length()>0){
            othersLoanTbShowAF.setTotalHousePrice(othersLoanTbShowAF.getTotalHousePrice().add(new BigDecimal(othersloan.getHOUSETOTALPRICE())));
          }
          
            othersLoanTbShowAF.setTotalPerson(count);
          
        }
        }
      
      if (othersLoanTbShowAF.getTotalHousePrice()!=null&&othersLoanTbShowAF.getTotalHousePrice().compareTo(new BigDecimal(0))>0) {
        othersLoanTbShowAF.setTotalHousePrice(othersLoanTbShowAF.getTotalHousePrice().divide(
            new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_DOWN));
      }
      if (othersLoanTbShowAF.getTotalLoanMoney()!=null&&othersLoanTbShowAF.getTotalLoanMoney().compareTo(new BigDecimal(0))>0) {
        othersLoanTbShowAF.setTotalLoanMoney(othersLoanTbShowAF.getTotalLoanMoney().divide(
            new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_DOWN));
      }
      // Iterator iterate = loanvipcheckList.iterator();
      // Object[] obj = null;
      // while (iterate.hasNext()) {
      // LoanVIPCheckDTO loanVIPCheckDTO = new LoanVIPCheckDTO();
      // obj = (Object[]) iterate.next();
      // if (obj[14] != null)
      // loanVIPCheckDTO.setHouseAddr(obj[14].toString());
      // loanVIPCheckDTO.setHouseType(BusiTools.getBusiValue_WL(houseType,
      // BusiConst.PLHOUSETYPE));
      // // 枚举转换合同状态
      // loanVIPCheckDTO.setContractSt(BusiTools.getBusiValue(Integer
      // .parseInt("" + contractSt), BusiConst.PLCONTRACTSTATUS));
      //       
      // templist.add(loanVIPCheckDTO);
      // }
    } catch (Exception e) {
      e.printStackTrace();
    }
    pagination.setNrOfElements(count);
    othersLoanTbShowAF.setList(othersloanList);
    othersLoanTbShowAF.setAllList(othersloanAllList);
    return othersLoanTbShowAF;
  }

}
