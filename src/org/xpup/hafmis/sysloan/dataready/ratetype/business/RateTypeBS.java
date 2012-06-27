package org.xpup.hafmis.sysloan.dataready.ratetype.business;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.dao.LoanRateTypeDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.LoanRateType;
import org.xpup.hafmis.sysloan.dataready.ratetype.bsinterface.IRateTypeBS;
import org.xpup.hafmis.sysloan.dataready.ratetype.form.RateTypeNewAF;

public class RateTypeBS implements IRateTypeBS {
  
  private LoanRateTypeDAO loanRateTypeDAO;

  public void setLoanRateTypeDAO(LoanRateTypeDAO loanRateTypeDAO) {
    this.loanRateTypeDAO = loanRateTypeDAO;
  }
  /**
   * 插入一条记录
   */
  public void saveRateType(RateTypeNewAF af,SecurityInfo securityInfo) throws BusinessException {
    try {
      LoanRateType loanRateType = new LoanRateType();
      loanRateType.setLoanRateType(af.getRateType());
      loanRateType.setLoanRateExplain(af.getRateExplain());
      System.out.println(af.getRateDate());
      loanRateType.setLoanRateDate(af.getRateDate());
      loanRateTypeDAO.insert(loanRateType);
      //插入操作日志
      
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }
  /**
   * 更新的方法
   */
  public void updateRateType(RateTypeNewAF af,SecurityInfo securityInfo) throws BusinessException {
    String rateId = af.getRateId();
    try {
      LoanRateType loanRateType = loanRateTypeDAO.queryById(new Integer(rateId));
      loanRateType.setLoanRateType(af.getRateType());
      loanRateType.setLoanRateExplain(af.getRateExplain());
      loanRateType.setLoanRateDate(af.getRateDate());
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 删除的方法
   */
  public void deleteById(Integer id,SecurityInfo securityInfo) throws BusinessException {
    try {
      LoanRateType loanRateType = loanRateTypeDAO.queryById(id);
      loanRateTypeDAO.delete(loanRateType);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  public List findRateTypeListAll() throws BusinessException {
    List list = null;
    try {
      list = loanRateTypeDAO.queryRateTypeInfoAllList(null,null,null);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }
  
  public List findRateTypeList(Pagination pagination) throws BusinessException {
    List list = null;
    try {
      String rateType = (String) pagination.getQueryCriterions().get("rateType");
      String rateExplain = (String) pagination.getQueryCriterions().get("rateExplain");
      String rateDate = (String) pagination.getQueryCriterions().get("rateDate");
      String orderBy = (String) pagination.getOrderBy();
      String order = (String) pagination.getOrderother();
      int start = pagination.getFirstElementOnPage() - 1;
      int pageSize = pagination.getPageSize();
      int page = pagination.getPage();
      list = loanRateTypeDAO.queryRateTypeInfoList(rateType, rateExplain,
          rateDate, orderBy, order, start, pageSize, page);
      int count = loanRateTypeDAO.queryRateTypeInfoAllList(rateType, rateExplain, rateDate).size();
      pagination.setNrOfElements(count);
      
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return list;
  }
  
  public LoanRateType findInfoById(Integer id) throws BusinessException {
    LoanRateType loanRateType = loanRateTypeDAO.queryById(id);
    if(loanRateType == null) {
      throw new BusinessException("该记录不存在");
    }
    return loanRateType;
  }  
}
