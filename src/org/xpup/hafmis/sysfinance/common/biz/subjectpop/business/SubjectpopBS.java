package org.xpup.hafmis.sysfinance.common.biz.subjectpop.business;

import java.util.ArrayList;
import java.util.List;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysfinance.bookmng.subject.form.SubjectShowAF;
import org.xpup.hafmis.sysfinance.common.biz.subjectpop.bsinterface.ISubjectpopBS;
import org.xpup.hafmis.sysfinance.common.dao.SubjectDAO;

public class SubjectpopBS implements ISubjectpopBS {

  private SubjectDAO subjectDAO = null;

  public void setSubjectDAO(SubjectDAO subjectDAO) {
    this.subjectDAO = subjectDAO;
  }

  public List findSubjectpopTree(SubjectShowAF af, Pagination pagination,
      SecurityInfo securityInfo) throws Exception {
    List list = new ArrayList();
    try {
      List subjectSt = (List) pagination.getQueryCriterions().get("status");
      list = subjectDAO.findSubjectpopTree_WL(af.getSortcodeflag(), subjectSt,
          securityInfo);
    } catch (Exception be) {
      be.printStackTrace();
    }
    return list;
  }

}
