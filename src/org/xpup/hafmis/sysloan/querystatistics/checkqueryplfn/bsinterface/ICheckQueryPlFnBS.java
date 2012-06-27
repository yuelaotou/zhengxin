package org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.form.ParticularglTaAF;

public interface ICheckQueryPlFnBS {
  public List showCheckQueryPlFnList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
  public Object[] showCheckQueryPlFnTBList(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
  public List showContrctList(String empId,String empName,String startTime,String endTime,String contracId,
      SecurityInfo securityInfo) throws Exception;
  public List showContrctList_print(String empId,String empName,String startTime,String endTime,String contracId,String contractIdEnd,
      SecurityInfo securityInfo) throws Exception;

}
