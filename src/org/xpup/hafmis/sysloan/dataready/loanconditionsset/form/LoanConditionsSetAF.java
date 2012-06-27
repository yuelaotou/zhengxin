package org.xpup.hafmis.sysloan.dataready.loanconditionsset.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.dataready.loanconditionsset.dto.LoanConditionsSetDTO;

public class LoanConditionsSetAF extends ActionForm {
 private LoanConditionsSetDTO loanConditionsSetDTO=new LoanConditionsSetDTO();
 private Map natureofunitsMap=new HashMap();
public LoanConditionsSetDTO getLoanConditionsSetDTO() {
  return loanConditionsSetDTO;
}

public void setLoanConditionsSetDTO(LoanConditionsSetDTO loanConditionsSetDTO) {
  this.loanConditionsSetDTO = loanConditionsSetDTO;
}

public Map getNatureofunitsMap() {
  return natureofunitsMap;
}

public void setNatureofunitsMap(Map natureofunitsMap) {
  this.natureofunitsMap = natureofunitsMap;
}
}
