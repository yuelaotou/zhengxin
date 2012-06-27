package org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.bsinterface;



import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.accounthandle.credenceInspection.dto.CredenceInspectionFindDTO;

public interface ICredenceInspectionBS {
  public Object[] findCredenceCharacterList(SecurityInfo securityInfo,String temp);
  public CredenceInspectionFindDTO findCredenceInspectionFindDTO(Pagination pagination,SecurityInfo securityInfo) throws Exception;
}
