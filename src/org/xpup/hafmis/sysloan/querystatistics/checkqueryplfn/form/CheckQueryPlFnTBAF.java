package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto.CheckQueryPlFnTBFindDTO;

public class CheckQueryPlFnTBAF extends ActionForm {
  
  
  
  
  private String empId="";//借款人职工编号
  private String empName="";//借款人职工姓名
  private String startTime="";//发生开始时间
  private String endTime="";//发生结束时间
  private String contractId="";//合同编号
  private CheckQueryPlFnTBFindDTO checkQueryPlFnTBFindDTO = new CheckQueryPlFnTBFindDTO();

  private List list=new ArrayList();

  public CheckQueryPlFnTBFindDTO getCheckQueryPlFnTBFindDTO() {
    return checkQueryPlFnTBFindDTO;
  }

  public void setCheckQueryPlFnTBFindDTO(
      CheckQueryPlFnTBFindDTO checkQueryPlFnTBFindDTO) {
    this.checkQueryPlFnTBFindDTO = checkQueryPlFnTBFindDTO;
  }

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getContractId() {
    return contractId;
  }

  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
}
