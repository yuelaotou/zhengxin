package org.xpup.hafmis.sysloan.dataready.collloanbackpara.bsinterface;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.collloanbackpara.dto.CollLoanbackParaDTO;

public interface ICollLoanbackParaBS {
  public CollLoanbackParaDTO findCollLoanbackParaInfo(String office) throws Exception;
  public void saveCollLoanbackParaInfo(CollLoanbackParaDTO collLoanbackParaDTO,SecurityInfo securityInfo) throws Exception;
}
