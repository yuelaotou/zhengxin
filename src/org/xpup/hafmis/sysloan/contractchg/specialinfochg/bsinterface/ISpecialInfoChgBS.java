package org.xpup.hafmis.sysloan.contractchg.specialinfochg.bsinterface;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.contractchg.specialinfochg.dto.SpecialInfoChgDTO;

public interface ISpecialInfoChgBS {
  public SpecialInfoChgDTO findSpecialInfoChgInfo(String contractId,SecurityInfo securityInfo) throws Exception;
  public void saveSpecialInfoChgInfo(SpecialInfoChgDTO specialInfoChgDTO,SecurityInfo securityInfo) throws Exception;
}
