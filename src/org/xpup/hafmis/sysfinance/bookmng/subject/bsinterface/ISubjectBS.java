package org.xpup.hafmis.sysfinance.bookmng.subject.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;

public interface ISubjectBS {
public void saveSubject(SubjectShowAF af,SecurityInfo securityInfo) throws Exception, BusinessException;
public String is_Book_CodeStructure(String code,SecurityInfo securityInfo) throws Exception, BusinessException;
public String is_Code_In(String code, SecurityInfo securityInfo) throws Exception, BusinessException;
public boolean is_CodeLevel_One(String code, SecurityInfo securityInfo) throws Exception, BusinessException;
public int getCodeLevel_up(String code, SecurityInfo securityInfo) throws Exception, BusinessException;
public String is_ParentCode_Normal(String code,String states, SecurityInfo securityInfo) throws Exception, BusinessException;
public List is_ParentCodeRelation_In(String code,SecurityInfo securityInfo) throws Exception, BusinessException;
public List findSubjectTree(SubjectShowAF af,SecurityInfo securityInfo) throws Exception, BusinessException;
public void deleteSubject(String codeid, SecurityInfo securityInfo) throws Exception, BusinessException;
public List is_ParentCodeIn_WL(String code, SecurityInfo securityInfo) throws Exception, BusinessException;
public List is_CredenceModle_In(String code,SecurityInfo securityInfo) throws Exception, BusinessException;
public String is_SettleIncAndDec_In(String code,SecurityInfo securityInfo) throws Exception, BusinessException;
public List is_AccountantCredence_In(String code,SecurityInfo securityInfo) throws Exception, BusinessException;
public List is_TreasurerCredence_In(String code,SecurityInfo securityInfo) throws Exception, BusinessException;
public void canceledSubject(String codeid, SecurityInfo securityInfo) throws Exception, BusinessException;
public void canceledquashSubject(String codeid, SecurityInfo securityInfo) throws Exception, BusinessException;
public String is_ParentCodeInByCode_WL(String code,String states, SecurityInfo securityInfo) throws Exception, BusinessException;
public SubjectShowAF findSubject(String subjectId,SecurityInfo securityInfo) throws Exception, BusinessException;
public void updateSubject(SubjectShowAF af,SecurityInfo securityInfo) throws Exception, BusinessException;
public SubjectShowAF getSubjectMessage(SubjectShowAF af,SecurityInfo securityInfo) throws Exception,BusinessException;
}
