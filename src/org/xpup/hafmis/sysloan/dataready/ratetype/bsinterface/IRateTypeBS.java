package org.xpup.hafmis.sysloan.dataready.ratetype.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanRateType;
import org.xpup.hafmis.sysloan.dataready.ratetype.form.RateTypeNewAF;

public interface IRateTypeBS {
  public void saveRateType(RateTypeNewAF af,SecurityInfo securityInfo) throws BusinessException;
  
  public void updateRateType(RateTypeNewAF af,SecurityInfo securityInfo) throws BusinessException;
  
  public void deleteById(Integer id,SecurityInfo securityInfo) throws BusinessException;
  
  public List findRateTypeList(Pagination pagination) throws BusinessException;
  
  public LoanRateType findInfoById(Integer id) throws BusinessException;
  
  public List findRateTypeListAll() throws BusinessException;
}
