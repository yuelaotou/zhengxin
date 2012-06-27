package org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.LoanFlowHeadDAO;
import org.xpup.hafmis.sysloan.common.dao.PlBizActiveLogDAO;
import org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.bsinterface.IQueryOperationgLogBS;
import org.xpup.hafmis.sysloan.querystatistics.queryoperationlog.dto.QueryOperationLogDTO;
import org.xpup.security.common.domain.User;

public class QueryOperationgLogBS  implements IQueryOperationgLogBS{
  private PlBizActiveLogDAO plBizActiveLogDAO = null;
  private LoanFlowHeadDAO loanFlowHeadDAO=null;
  public Map queryListByCriterions(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    Map map=new HashMap();
    try{
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    String bizStatus = (String) pagination.getQueryCriterions().get("bizStatus");
    String bizType = (String) pagination.getQueryCriterions().get(
        "bizType");
    String operator = (String) pagination.getQueryCriterions().get(
        "operator");
    String beginTime = (String) pagination.getQueryCriterions().get(
        "beginTime");
    List userList = (List) pagination.getQueryCriterions().get(
        "userList");
    String endTime = (String) pagination.getQueryCriterions().get("endTime");
    List operatorList=null;

    
    if(operator==null){
      operatorList=userList;
    }else{
      operatorList=new ArrayList();
      operatorList.add(operator);
    }
     
    
    
    List list=plBizActiveLogDAO.queryByCriterions(start, orderBy, order, pageSize, page,bizStatus,bizType,operatorList,beginTime, endTime,securityInfo);
    List printlist=plBizActiveLogDAO.queryPrintListByCriterions(start, orderBy, order, pageSize, page,bizStatus,bizType,operatorList,beginTime, endTime,securityInfo);
   
    for(int i=0;i<list.size();i++){
      QueryOperationLogDTO queryOperationLogDTO=(QueryOperationLogDTO)list.get(i);
      String type=queryOperationLogDTO.getType();
      String bizid=queryOperationLogDTO.getBizid();        
      if(bizid!=null && bizid!=""){
        String contract_id=loanFlowHeadDAO.queryContractId_LP(bizid);
        queryOperationLogDTO.setContract(contract_id);
      }
    }
    pagination.setNrOfElements(printlist.size());
    pagination.getQueryCriterions().put("printlist", printlist);
    map.put("list", list);
    map.put("printlist", printlist);
    }catch(Exception e){
      e.printStackTrace();
    }
    return map;
  }
  
 public List queryCellListByCriterions(Pagination pagination, SecurityInfo securityInfo) throws Exception, BusinessException {
    
    return null;
  }

public void setPlBizActiveLogDAO(PlBizActiveLogDAO plBizActiveLogDAO) {
  this.plBizActiveLogDAO = plBizActiveLogDAO;
}

public void setLoanFlowHeadDAO(LoanFlowHeadDAO loanFlowHeadDAO) {
  this.loanFlowHeadDAO = loanFlowHeadDAO;
}
}
