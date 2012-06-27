package org.xpup.hafmis.signjoint.util;

import java.util.List;

import org.xpup.hafmis.signjoint.disposal.*;
import org.xpup.hafmis.signjoint.disposalinterface.IDisposal;
public class SignjointFactory {

  /**
   * 获得具体操作对象
   * @param request请求数据
   * @return 操作对象
   */
  public static IDisposal getDisposal(String request)
  {
    String num;//交易码
    List list=SignTools.Compart(request);
    num=((String)list.get(0)).trim();//提取操作码
    return getMod(num,list);
  }
  /**
   * 判断使用哪个IDisposal实现类处理,工厂
   * @param num 交易码
   * @param list 请求数据
   * @return 操作对象
   */
  private static IDisposal getMod(String num,List list)
  {
    if("1001".equalsIgnoreCase(num))
      return new SignupDisposal(list);
    else if("1003".equalsIgnoreCase(num))
      return new QueryBalanceDisposal(list);
    else if("1004".equalsIgnoreCase(num))
      return new QueryListBalanceDisposal(list);
    else if("1005".equalsIgnoreCase(num))
      return new QueryBorrowDisposal(list);
    else if("1006".equalsIgnoreCase(num))
      return new QueryBorrowListDisposal(list);
    else
      return new QueryDefaultDisposal(list);
  }
  
  
}
