package org.xpup.hafmis.syscollection.common.biz.loankoupop.bsinterface;

import java.util.List;

import org.xpup.common.util.Pagination;

public interface ILoanKouPopBS {
  public List findCollLoanbackTaList(String batchNum,Pagination pagination);
  public String querybankname(final String loanKouAcc) throws Exception;
}
