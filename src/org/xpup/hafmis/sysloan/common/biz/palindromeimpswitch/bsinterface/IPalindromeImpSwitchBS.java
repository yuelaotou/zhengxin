package org.xpup.hafmis.sysloan.common.biz.palindromeimpswitch.bsinterface;

import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;

public interface IPalindromeImpSwitchBS {
  public List switchImpList(String bankId,List headList,List impList,String type)throws Exception,BusinessException;
}
