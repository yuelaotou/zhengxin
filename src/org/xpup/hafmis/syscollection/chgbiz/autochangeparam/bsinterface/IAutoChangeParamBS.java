package org.xpup.hafmis.syscollection.chgbiz.autochangeparam.bsinterface;

import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.chgbiz.autochangeparam.dto.AutoChangeParamDTO;

/**
 * Copy Right Information : 自动变更参数设置BS的借口 Goldsoft Project :
 * AutoChangeParamSaveAC
 * 
 * @Version : v1.0
 * @author : 付云峰
 * @Date : 2008.3.17
 */
public interface IAutoChangeParamBS {

  /**
   * 自动变更参数设置
   * @param autoChangeParamDTO
   * @param securityInfo
   * @throws Exception
   * @author 付云峰
   */
  public void saveAutoChangeParam(AutoChangeParamDTO autoChangeParamDTO,
      SecurityInfo securityInfo) throws Exception;
  /**
   * 查询自动变更参数值
   * @return 参数值0或1
   * @throws Exception
   * @author 付云峰
   */
  public String findAutoChangeParam() throws Exception;
}
