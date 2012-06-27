package org.xpup.hafmis.sysloan.loancallback.contractchange.business;

import java.util.ArrayList;
import java.util.List;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.loancallback.contractchange.bsinterface.IContractchangeBS;
import org.xpup.hafmis.sysloan.loancallback.contractchange.dto.ContractchangeDTO;

public class ContractchangeBS implements IContractchangeBS {
  
  private BorrowerAccDAO borrowerAccDAO=null;
  
  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }
  public List queryContractchangeList(Pagination pagination, SecurityInfo securityInfo) throws BusinessException, Exception {
    // TODO Auto-generated method stub
    List list = new ArrayList();
    int count = 0;
    String contractId = (String)pagination.getQueryCriterions().get("contractId");
    String type = (String)pagination.getQueryCriterions().get("type");
    String borrowerName = (String)pagination.getQueryCriterions().get("borrowerName");
    String cardNum = (String)pagination.getQueryCriterions().get("cardNum");
    String startDate = (String)pagination.getQueryCriterions().get("startDate");
    String endDate = (String)pagination.getQueryCriterions().get("endDate");
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String orderBy = (String) pagination.getOrderBy();
    String order = pagination.getOrderother();
    try{
      list = borrowerAccDAO.queryContractchangeList(contractId, borrowerName, cardNum, startDate, endDate, orderBy, order, start, pageSize, page,type,securityInfo);
      count = borrowerAccDAO.queryContractchangeCount(contractId, borrowerName, cardNum, startDate, endDate, orderBy, order, start, pageSize, page,type,securityInfo).size();
      pagination.setNrOfElements(count);
    }catch(Exception e){
      e.printStackTrace();
    }
    return list;
  }
  /**
   * 打印
   */
  public ContractchangeDTO printContractchangeDTO(String id, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    ContractchangeDTO dto = new ContractchangeDTO();
    String tmpeagreementDate = "";
    int month = 0; //变更后剩余期限
    int oldmonth = 0;//原剩余期限
    int loanlimit = 0;//原贷款期限
    try{
      dto = borrowerAccDAO.printContractchangeDTO(id);
      if(dto!=null){
      tmpeagreementDate = dto.getAgreementDate();
      month = Integer.parseInt(dto.getShengyuyue());
      oldmonth= Integer.parseInt(dto.getYuanlimit());
      loanlimit=Integer.parseInt(dto.getLoanlimit());
      String agreementDate="";
//      if(dto.getType().equals("2")||dto.getType().equals("4")){
        month=month-oldmonth+loanlimit;
        agreementDate = BusiTools.addMonth(tmpeagreementDate.substring(0, 6), month)+tmpeagreementDate.substring(6, 8);
//      }
//      if(dto.getType().equals("3")||dto.getType().equals("5")){
//        
//      }
//      if(dto.getType().equals("1")){
//        
//      }
      
      dto.setDaoqidate(agreementDate);
      borrowerAccDAO.updatepl101_1_3(id);
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return dto;
  }
  public void updatePl101_1(String id, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    borrowerAccDAO.updatepl101_1_1(id);
  }
  public void updatePl101_2(String id, SecurityInfo securityInfo) throws Exception, BusinessException {
    // TODO Auto-generated method stub
    borrowerAccDAO.updatepl101_1_2(id);
  }

}
