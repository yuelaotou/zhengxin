package org.xpup.hafmis.sysfinance.bookmng.credencemodle.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.credencemodle.dto.CredencemodleDTO;

public interface ICredencemodleBS {
public void findCredencemodleExist(String subjectCode,SecurityInfo securityInfo)throws Exception, BusinessException;
public CredencemodleDTO findCredencemodleInfo(String subjectCode,SecurityInfo securityInfo)throws Exception;
public List findCredencemodleSummrayList(SecurityInfo securityInfo)throws Exception, BusinessException;
public Integer findCredencemodleInfoExist(String subjectCode,String bizType,String moneyType,SecurityInfo securityInfo,String subjectDirection)throws Exception, BusinessException;
public void saveCredencemodle(String subjectCode,String bizType,String moneyType,String subjectDirection,String summary,SecurityInfo securityInfo)throws Exception;
public void updateCredencemodle(String subjectCode,String bizType,String moneyType,String subjectDirection,String summary,SecurityInfo securityInfo)throws Exception;
public List queryCredencemodleList(SecurityInfo securityInfo, Pagination pagination);
public void deleteCredencemodle(String id,SecurityInfo securityInfo) throws Exception;
public CredencemodleDTO findCredencemodleUpdateInfo(String subjectCode,SecurityInfo securityInfo,String id)throws Exception;
}
