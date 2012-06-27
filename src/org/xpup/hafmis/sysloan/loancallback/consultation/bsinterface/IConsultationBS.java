package org.xpup.hafmis.sysloan.loancallback.consultation.bsinterface;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public interface IConsultationBS{
  public LoancallbackTaAF findShouldLoancallbackInfo(Pagination pagination,
      SecurityInfo securityInfo) throws Exception;
}