package org.xpup.hafmis.sysloan.dataready.bankpalindrome.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.bankpalindrome.form.BankpalindromeAF;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public interface IBankpalindromeBS {
  public BankpalindromeAF queryRowNumByBank(SecurityInfo securityInfo,Pagination pagination)throws Exception,BusinessException;
  public void insertRowNum(SecurityInfo securityInfo,String bankId,String rowNum)throws Exception,BusinessException;
}
