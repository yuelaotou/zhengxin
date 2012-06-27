package org.xpup.hafmis.sysloan.accounthandle.overpay.bsinterface;

import java.util.List;


import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.overpay.dto.OverPayTaDTO;

public interface IOverPayBS {
  public OverPayTaDTO findOverPayTaInfo(String loadkouacc,SecurityInfo securityInfo) throws Exception;
  public void saveOverPayTa(OverPayTaDTO overPayTaDTO,SecurityInfo securityInfo) throws Exception;
  public Object[] findOverPayTbList(Pagination pagination,List loanbankList) throws Exception;
  public void deleteOverPayTbList(String flowHeadId,String contractId,SecurityInfo securityInfo) throws Exception;
}
