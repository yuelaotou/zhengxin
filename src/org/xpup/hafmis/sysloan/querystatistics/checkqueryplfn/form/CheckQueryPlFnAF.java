package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto.CheckQueryPlFnFindDTO;

public class CheckQueryPlFnAF extends ActionForm {
  private CheckQueryPlFnFindDTO checkQueryPlFnFindDTO = new CheckQueryPlFnFindDTO();

  private List list=new ArrayList();

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public CheckQueryPlFnFindDTO getCheckQueryPlFnFindDTO() {
    return checkQueryPlFnFindDTO;
  }

  public void setCheckQueryPlFnFindDTO(CheckQueryPlFnFindDTO checkQueryPlFnFindDTO) {
    this.checkQueryPlFnFindDTO = checkQueryPlFnFindDTO;
  }
}
