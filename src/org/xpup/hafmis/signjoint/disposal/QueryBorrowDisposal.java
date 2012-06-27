package org.xpup.hafmis.signjoint.disposal;

import java.util.List;
import javax.servlet.ServletContext;
import org.xpup.hafmis.signjoint.util.Filter;
import org.xpup.hafmis.signjoint.util.SignTools;
import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.disposalinterface.IDisposal;
import org.xpup.hafmis.signjoint.dto.BaseInfoDTO;
import org.xpup.hafmis.signjoint.entity.Sign;
/**
 * 查询贷款余额处理类
 * @author yinchao
 *
 */
public class QueryBorrowDisposal extends QueryBase implements IDisposal{
  
  private static final String QUERY_BORROW_BALANCE_NUMBER="1005";//贷款余额操作号

  public QueryBorrowDisposal(List list){
    super(list);
  }
  /**
   * 处理方法
   */
  public String disposal(ServletContext sc){
    if(!Filter.doFilter(list, 2)){
      return SignTools.getInfo_06(QUERY_BORROW_BALANCE_NUMBER);
    }
    String sign=((String)list.get(1)).trim();
    if(!SignTools.isRigntSignid(sign)){//如果是非法签约ID
      return SignTools.getInfo_06(QUERY_BORROW_BALANCE_NUMBER);
    }
    ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",sc);
    Sign signed=bs.querySignBySignNum(sign);
    if(isHaveSign(signed)){//如果已经签约
      BaseInfoDTO req=getRequest(signed);
      return bs.queryBorrowBalance(req);
    }
    else{
      return SignTools.getInfo_04(QUERY_BORROW_BALANCE_NUMBER);
    }

  }
}
