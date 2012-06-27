package org.xpup.hafmis.syscollection.accountmng.officeparam.bsinterface;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.accountmng.officeparam.dto.OfficeParaDTO;

public interface IOfficeParaBS {
  public OfficeParaDTO findOfficeParaInfo(String office) throws Exception;
  public void saveCollLoanbackParaInfo(OfficeParaDTO OfficeParaDTO,SecurityInfo securityInfo) throws Exception;
}
