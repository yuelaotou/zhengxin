package org.xpup.hafmis.sysfinance.bookmng.subjectrelation.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subjectrelation.dto.SubjectrelationInfoTaDTO;

public interface ISubjectrelationBS {
public String checkSubjectcode(String subjectcode, SecurityInfo securityInfo) throws Exception, BusinessException;
public Integer findSubjectrelationExist(String subjectCode,SecurityInfo securityInfo)throws Exception;
public SubjectrelationInfoTaDTO findSubejectRelationTaInfo(String subjectCode,String bookId)throws Exception;
public SubjectrelationInfoTaDTO findSubejectRelationTa1Info(String subjectCode,String bookId)throws Exception;
public List querySubejectRelationTaList(String subjectCode,String calculRelaType,Pagination pagination,String bookId)throws Exception;
public List querySubejectRelationTaPop1List(Pagination pagination,SecurityInfo securityInfo)throws Exception;
public List querySubejectRelationTaPop1CountList(SecurityInfo securityInfo)throws Exception;
public List querySubejectRelationTaPop2List(Pagination pagination,SecurityInfo securityInfo)throws Exception;
public List querySubejectRelationTaPop2CountList(SecurityInfo securityInfo)throws Exception;
public List querySubejectRelationTaPop3List(Pagination pagination,SecurityInfo securityInfo ,List officeList2,List loanbankList2)throws Exception;
public List querySubejectRelationTaPop3CountList(Pagination pagination,SecurityInfo securityInfo)throws Exception;
public List querySubjectrelationTbAllBank()throws Exception;
public List querySubejectRelationTbList(Pagination pagination,String bookId,List officeList,List bankList,List orgIdList)throws Exception;
public List querySubejectRelationTbCountList(Pagination pagination,String bookId) throws Exception;
public List querySubejectRelationOrgIdList()throws Exception;
public void findSubjectrelationInfoExist(String firstsubjectCode,String calculRelaType,String calculRelaValue,String bookId) throws Exception, BusinessException;
public void findSubejectRelationTaExist2(String collBankId,String firstsubjectCode,String bookId) throws Exception, BusinessException;
public void findSubejectRelationTaOrgExist(String collBankId,String firstsubjectCode,String bookId) throws Exception, BusinessException;
public void subjectrelationTaSaveBank(String calculRelaType,String calculRelaValue[],String firstSubjectCode,String subjectCode, SecurityInfo securityInfo)throws Exception;
public void findSubejectRelationTaBankExist(String officeId, String firstsubjectCode,String bookId,SecurityInfo securityInfo) throws Exception, BusinessException;
public void findSubejectRelationTaOOrgExist(String officeId, String firstsubjectCode,String bookId) throws Exception, BusinessException;
public void findSubejectRelationTaOrgBankExist(String orgId, String firstsubjectCode,String bookId) throws Exception, BusinessException;
public void findSubejectRelationTaOrgOExist(String orgId, String firstsubjectCode,String bookId) throws Exception, BusinessException;
public void findSubjectrelationParentId(String subjectCode,SecurityInfo securityInfo)throws Exception, BusinessException;
public void findSubjectrelationAllOExist(List list,String firstsubjectCode,String bookId) throws Exception, BusinessException;
public void findSubjectrelationAllBankExist(List bankList,String firstsubjectCode,String bookId) throws Exception, BusinessException;
public void findSubjectrelationAllOrgExist(List list,String firstsubjectCode,String bookId) throws Exception, BusinessException;
public void deleteISubjectrelationTb(String subjectreleid,String bookId)throws Exception, BusinessException;
public void deleteAllSubjectrelationTb(List subejectRelationTbCountList,String bookId)throws Exception, BusinessException;
public void subjectrelationTaFnOperateLog(SecurityInfo securityInfo,String type);
public void findSubjectRelationFirstCode(String subjectCode,SecurityInfo securityInfo)throws Exception, BusinessException;
public void findSubjectrelationInfoExist_wsh(String firstsubjectCode,String calculRelaType, String calculRelaValue,String bookId) throws Exception, BusinessException ;
public void findSubejectRelationTaExist2_bank_banshichu(String collBankId,String firstsubjectCode,String bookId) throws Exception, BusinessException ;
public void findSubejectRelationTaOrgOExistorg_banshichu(String orgId,String firstsubjectCode,String bookId) throws Exception, BusinessException ;
}
