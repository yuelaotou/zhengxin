package org.xpup.hafmis.sysfinance.common.biz.credencepop.bsinterface;

import org.xpup.common.exception.BusinessException;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;

public interface ICredencePopBS {
  /**
   * 查询凭证的方法
   * @param docNum 凭证号
   * @param securityInfo
   * @return obj[0]列表，obj[1]凭证信息，obj[2]借款合计，obj[3]贷款合计
   * @throws Exception
   * @throws BusinessException
   */
  public Object[] findAccountantCredence(String docNum,SecurityInfo securityInfo, String credenceDate, String office)throws Exception;
  
  /**
   * 查询凭证字说明说明
   * @param credenceCharacter 凭证字
   * @param securityInfo
   * @return 凭证字说明说明
   * @throws Exception
   */
  public String findParamExplain(String credenceCharacter,SecurityInfo securityInfo) throws Exception;
}
