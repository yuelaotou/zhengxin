package org.xpup.hafmis.syscollection.common.biz.loankoupop.business;

import java.util.ArrayList;
import java.util.List;

import org.xpup.common.util.Pagination;
import org.xpup.hafmis.syscollection.common.biz.loankoupop.bsinterface.ILoanKouPopBS;
import org.xpup.hafmis.syscollection.common.biz.loankoupop.dto.LoanKouPopDTO;
import org.xpup.hafmis.syscollection.common.dao.CollLoanbackTailDAO;

public class LoanKouPopBS implements ILoanKouPopBS{
  private CollLoanbackTailDAO collLoanbackTailDAO=null;
  /**
   * 公积金还贷--弹出框
   * 
   * @author 郭婧平 2007-12-26 查询列表中的相关数据
   */
  public List findCollLoanbackTaList(String batchNum,Pagination pagination) {
    String orderBy = (String) pagination.getOrderBy();
    String order = (String) pagination.getOrderother();
    int start = pagination.getFirstElementOnPage() - 1;
    int pageSize = pagination.getPageSize();
    int page = pagination.getPage();
    List resultlist = new ArrayList();
    try {
      int count = 0;
      List list = collLoanbackTailDAO.queryBatchNumPopList(batchNum, orderBy, order, start, pageSize, page);
      if (list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          LoanKouPopDTO loanKouPopDTO = (LoanKouPopDTO) list.get(i);
          resultlist.add(loanKouPopDTO);
        }
        count=collLoanbackTailDAO.queryBatchNumPopListCount(batchNum).size();
      }
      pagination.setNrOfElements(count);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultlist;
  }
  public void setCollLoanbackTailDAO(CollLoanbackTailDAO collLoanbackTailDAO) {
    this.collLoanbackTailDAO = collLoanbackTailDAO;
  }
  public String querybankname(final String loanKouAcc) throws Exception{
    String bank="";
    try{
     bank= collLoanbackTailDAO.querybankname(loanKouAcc);
    }catch(Exception e){
      e.printStackTrace();
    }
    return bank;
  }
}
