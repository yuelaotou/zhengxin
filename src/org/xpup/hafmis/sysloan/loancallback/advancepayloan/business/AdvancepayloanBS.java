package org.xpup.hafmis.sysloan.loancallback.advancepayloan.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.bsinterface.IAdvancepayloanBS;
import org.xpup.hafmis.sysloan.loancallback.advancepayloan.dto.AdvancepayloanTaDTO;

public class AdvancepayloanBS implements IAdvancepayloanBS {
  
  private BorrowerAccDAO borrowerAccDAO=null;
  
  public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
    this.borrowerAccDAO = borrowerAccDAO;
  }


  /**
   * AJAX查询
   */
  public AdvancepayloanTaDTO findAdvancepayloanTaDTO(String loadKouAcc, SecurityInfo securityInfo) {
    // TODO Auto-generated method stub
    AdvancepayloanTaDTO advancepayloanTaDTO = null;
    try{
      String bizDate = securityInfo.getUserInfo().getPlbizDate();
      advancepayloanTaDTO = borrowerAccDAO.queryfindAdvancepayloanTaDTO(loadKouAcc,bizDate);
    }catch(Exception e){
      e.printStackTrace();
    }
    return advancepayloanTaDTO;
  }

/**
 * 插入PL112_1
 */
  public void insert(String contractId, String new_term, String type, SecurityInfo securityInfo) throws BusinessException, Exception {
    // TODO Auto-generated method stub
    try{
    String operator = securityInfo.getUserName();
    borrowerAccDAO.insertPl112_1(contractId, new_term, type, operator);
    }catch(Exception e){
      new BusinessException("添加失败");
    }
  }

/**
 * Tb查询
 */
public List queryTbList(Pagination pagination,SecurityInfo securityInfo) throws BusinessException, Exception {
  // TODO Auto-generated method stub
  List list = new ArrayList();
  int count = 0;
  int start = pagination.getFirstElementOnPage() - 1;
  int pageSize = pagination.getPageSize();
  int page = pagination.getPage();
  String orderBy = (String) pagination.getOrderBy();
  String order = pagination.getOrderother();
  String contractId = (String)pagination.getQueryCriterions().get("contractId");//合同编号
  String borrowerName = (String)pagination.getQueryCriterions().get("borrowerName");//借款人姓名
  String cardNum = (String)pagination.getQueryCriterions().get("cardNum");//证件号码
  String status = (String)pagination.getQueryCriterions().get("status");//状态
  try{
    if(pagination.getQueryCriterions().size()>0){
     list = borrowerAccDAO.queryTbList(contractId, borrowerName, cardNum, orderBy, order, start, pageSize,page,status);
     List coutList = borrowerAccDAO.countTbList(contractId, borrowerName, cardNum, orderBy, order, start, pageSize,status);
     if(coutList!=null){
       count = coutList.size();
     }
    }else{
      status = "0";
      list = borrowerAccDAO.queryTbList(contractId, borrowerName, cardNum, orderBy, order, start, pageSize,page,status);
      List coutList = borrowerAccDAO.countTbList(contractId, borrowerName, cardNum, orderBy, order, start, pageSize,status);
      if(coutList!=null){
        count = coutList.size();
      }
    }
    pagination.setNrOfElements(count);
  }catch(Exception e){
    e.printStackTrace();
  }
  return list;
}


public void delete(String id, SecurityInfo securityInfo) throws BusinessException, Exception {
  // TODO Auto-generated method stub
  try{
    String operator = securityInfo.getUserName();
    borrowerAccDAO.deletePl112_1(id, operator);
  }catch(Exception e){
    e.printStackTrace();
  }
}

}
