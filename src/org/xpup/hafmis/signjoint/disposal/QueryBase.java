package org.xpup.hafmis.signjoint.disposal;

import java.util.List;
import org.xpup.hafmis.signjoint.dto.BaseInfoDTO;
import org.xpup.hafmis.signjoint.entity.Sign;

/**
 * 查询处理类均继承自此类
 * @author yinchao
 */

public class QueryBase {

  protected List list;//请求数据
  public QueryBase(List list)
  {
    this.list=list;
  }
  
  
  /**
   * 将数据Sign中提取，并包装成DTO
   * @param sign签约对象
   * @return 包装后的DTO
   */
  protected BaseInfoDTO getRequest(Sign sign)
  {
    return new BaseInfoDTO(sign.getName(),
               sign.getCard_num(),
               sign.getEmpid(),
               sign.getBank_card());            
  }
  /**
   * 判断查询出的签约信息是否为空
   * @param sign 签约信息对象
   * @return 是否为空
   */
  protected boolean isHaveSign(Sign sign){
    if(sign!=null)
      return true;
    else
      return false;
  }
}
