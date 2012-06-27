package org.xpup.hafmis.sysloan.loanaccord.printplan.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanaccord.printplan.form.PrintplanShowAF;

public interface IPrintplanBS {
  // 查找合同编号在pl111的对应的201中的信息。传入条件就是为了分页和排序。
  public PrintplanShowAF findPrintplanList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException;
}
