package org.xpup.hafmis.sysfinance.bookmng.settleincanddec.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ISettleincanddecBS {
public Integer findSettleIncAndDecInfoExist(String subjectCode,SecurityInfo securityInfo) throws Exception;
public void saveSettleIncAndDec(String bySubjectCode,String subjectCode,String bySubjectDirection,SecurityInfo securityInfo)throws Exception;
public String findSettleIncAndDecSubjectDirection(String bySubjectCode,SecurityInfo securityInfo)throws Exception;
public List querySettleIncAndDecList(SecurityInfo securityInfo, Pagination pagination)throws Exception;
public void deleteSettleIncAndDec(String id,SecurityInfo securityInfo) throws Exception;
public void findSubjectrelationParentId(String bySubjectCode,SecurityInfo securityInfo)throws Exception, BusinessException;
public void findSubjectrelationParentId1(String SubjectCode, SecurityInfo securityInfo) throws Exception, BusinessException;
public void findSubjectRelationFirstCode(String subjectCode,SecurityInfo securityInfo) throws Exception, BusinessException;
}
