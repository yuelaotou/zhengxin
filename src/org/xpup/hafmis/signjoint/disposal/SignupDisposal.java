package org.xpup.hafmis.signjoint.disposal;

import java.util.List;
import org.xpup.hafmis.signjoint.util.Filter;
import org.xpup.hafmis.signjoint.util.SignTools;
import javax.servlet.ServletContext;

import org.xpup.common.util.BSUtils;
import org.xpup.hafmis.signjoint.bsinterface.ISignjointBS;
import org.xpup.hafmis.signjoint.disposalinterface.IDisposal;
import org.xpup.hafmis.signjoint.dto.RequestSignDTO;
/**
 * 签约处理类
 * @author yinchao
 */
public class SignupDisposal implements IDisposal {

  private static final String SIGN_UP_NUMBER="1001";//签约操作号
  
  private List list;
  public SignupDisposal(List list)
  {
    this.list=list;
  }
  /**
   * 包装请求数据
   * @param list 包含请求数据的List
   * @return 请求对象
   */
  private RequestSignDTO getRequest(List list)
  {
    return new RequestSignDTO(
           ((String)list.get(1)).trim(),
           ((String)list.get(2)).trim(),
           ((String)list.get(3)).trim(),
           ((String)list.get(4)).trim());
  }
  /**
   * 处理签约请求，调用业务逻辑方法
   * @return 请求结果
   */
  public String disposal(ServletContext sc)
  {
    
    if(!Filter.doFilter(list, 5)){
      return SignTools.getFailedInfo(SIGN_UP_NUMBER);
    }

    RequestSignDTO req=getRequest(list);
    if((req.getCardnum().length()!=18)&&(req.getCardnum().length()!=15)){//如果身份证位数不合法
      return SignTools.getInfo_06(SIGN_UP_NUMBER);
    }
    ISignjointBS bs=(ISignjointBS)BSUtils.getBusinessService("SignjointBS",sc);
    return bs.saveSign(req);
  }
  

}
