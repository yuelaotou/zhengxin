package org.xpup.hafmis.syscollection.common.biz.clearinginterest;

import org.xpup.hafmis.syscollection.dto.ClearingInterestDTO;
import org.xpup.hafmis.syscollection.dto.InterestDto;
/**
 * @author 王玲
 * 2007-6-26
 * 该方法用于实现计算年终利息的接口（本年利息、往年利息）
 */
public interface ClearingInterestInterface {

  //计算年终利息
  public InterestDto getClearinginterest(ClearingInterestDTO clearingInterestDTO);

}
