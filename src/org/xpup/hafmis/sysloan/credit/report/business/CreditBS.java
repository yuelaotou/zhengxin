package org.xpup.hafmis.sysloan.credit.report.business;

import java.sql.ResultSet;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.sysloan.common.dao.CreditDAO;
import org.xpup.hafmis.sysloan.credit.report.bsinterface.ICreditBS;

public class CreditBS implements ICreditBS {
  private CreditDAO creditDAO = null;

  public void setCreditDAO(CreditDAO creditDAO) {
    this.creditDAO = creditDAO;
  }

  public List getCredit(Pagination pagination) throws Exception,
      BusinessException {

    String shujutiquriqi = (String) pagination.getQueryCriterions().get(
        "shujutiquriqi");
    if (shujutiquriqi == null || "".equals(shujutiquriqi)) {
      return null;
    }
    String baowenshengchengriqi = (String) pagination.getQueryCriterions().get(
        "baowenshengchengriqi");
    String officeCode = (String) pagination.getQueryCriterions().get(
        "officeCode");
    String loanBankName = (String) pagination.getQueryCriterions().get(
        "loanBankName");
    String yewuhao = (String) pagination.getQueryCriterions().get("yewuhao");
    String jiluzhuangtai = (String) pagination.getQueryCriterions().get(
        "jiluzhuangtai");

    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    List list = creditDAO.getCreditList(shujutiquriqi, baowenshengchengriqi,
        officeCode, loanBankName, yewuhao, jiluzhuangtai, start, orderBy,
        order, pageSize, page);
    int n = creditDAO.getCreditListAll(shujutiquriqi, baowenshengchengriqi,
        officeCode, loanBankName, yewuhao, jiluzhuangtai, start, orderBy,
        order, pageSize, page);
    pagination.setNrOfElements(n);
    return list;
  }

  public void createCredit(String shujutiquriqi) throws Exception,
      BusinessException {
    // 判断本月份是否生成过数据，若生成过，不再处理。
    if (creditDAO.getCreditCountByDate(shujutiquriqi) != 0) {
      throw new BusinessException("此月份已经生成过数据，请直接查询。");
    }
    creditDAO.createCredit(shujutiquriqi);
  }

  public void deleteCredit(String shujutiquriqi) throws Exception,
      BusinessException {
    creditDAO.deleteCredit(shujutiquriqi);
  }

  public ResultSet exportNormal(Pagination pagination) throws Exception,
      BusinessException {
    String shujutiquriqi = (String) pagination.getQueryCriterions().get(
        "shujutiquriqi");
    ResultSet rs = creditDAO.exportNormal(shujutiquriqi);
    creditDAO.isContractExport(shujutiquriqi);
    return rs;
  }

  public ResultSet exportDelete(Pagination pagination) throws Exception,
      BusinessException {
    String shujutiquriqi = (String) pagination.getQueryCriterions().get(
        "shujutiquriqi");
    String[] rowArray = (String[]) pagination.getQueryCriterions().get(
        "rowArray");
    ResultSet rs = creditDAO.exportDelete(shujutiquriqi, rowArray);
    return rs;
  }

  public void dealWithContract(Pagination pagination) throws Exception,
      BusinessException {
    String[] rowArray = (String[]) pagination.getQueryCriterions().get(
        "rowArray");
    String status = (String) pagination.getQueryCriterions().get("status");
    creditDAO.dealWithContract(rowArray, status);
  }

}
