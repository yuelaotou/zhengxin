package org.xpup.hafmis.sysfinance.common.biz.subjectpop.bsinterface;

import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;

public interface ISubjectpopBS {
public List findSubjectpopTree(SubjectShowAF af,Pagination pagination,SecurityInfo securityInfo) throws Exception;
}
