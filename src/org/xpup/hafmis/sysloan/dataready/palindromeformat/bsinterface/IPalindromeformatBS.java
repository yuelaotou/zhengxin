package org.xpup.hafmis.sysloan.dataready.palindromeformat.bsinterface;

import java.util.List;
import java.util.Map;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.dataready.palindromeformat.form.PalindromeformatAF;
/**
 * 
 * @author yuqf
 *2007-12-20
 */
public interface IPalindromeformatBS {
  public PalindromeformatAF queryByBank(SecurityInfo securityInfo,Pagination pagination,String bankId)throws Exception,BusinessException;
  public void insertSet(SecurityInfo securityInfo,PalindromeformatAF palindromeformatAF,String bankId)throws Exception,BusinessException;
  public List queryRowNum(String bankId)throws Exception,BusinessException;
}
