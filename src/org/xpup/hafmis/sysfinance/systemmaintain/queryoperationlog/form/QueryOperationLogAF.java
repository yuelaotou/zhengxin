package org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.dto.QueryOperationLogFindDTO;

public class QueryOperationLogAF extends ActionForm {
  private QueryOperationLogFindDTO queryOperationLogFindDTO = new QueryOperationLogFindDTO();

  private List list;
  
  private Map credenceTypeMap= new HashMap();
  private Map operatorMap = new HashMap();
  private Map actionMap = new HashMap();

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public QueryOperationLogFindDTO getQueryOperationLogFindDTO() {
    return queryOperationLogFindDTO;
  }

  public void setQueryOperationLogFindDTO(
      QueryOperationLogFindDTO queryOperationLogFindDTO) {
    this.queryOperationLogFindDTO = queryOperationLogFindDTO;
  }

  public Map getActionMap() {
    return actionMap;
  }

  public void setActionMap(Map actionMap) {
    this.actionMap = actionMap;
  }

  public Map getCredenceTypeMap() {
    return credenceTypeMap;
  }

  public void setCredenceTypeMap(Map credenceTypeMap) {
    this.credenceTypeMap = credenceTypeMap;
  }

  public Map getOperatorMap() {
    return operatorMap;
  }

  public void setOperatorMap(Map operatorMap) {
    this.operatorMap = operatorMap;
  }
}
