package org.xpup.hafmis.sysloan.loanapply.giveacc.bsinterface;

import java.util.List;
import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.giveacc.dto.GiveaccModifyDTO;

public interface IGiveaccBS {
  public GiveaccModifyDTO findGiveaccInfo(String contractId, String houseType)
      throws Exception, BusinessException;

  public void modifyGiveAccInfo(String contractId, String oldSellerPayBank,
      String oldPayBankAcc, String newSellerPayBank, String newPayBankAcc,
      String houseType, SecurityInfo securityInfo) throws Exception,
      BusinessException;

  public void findGiveaccInfoExist(String contractId,List loanBankList) throws Exception,
      BusinessException;

  public String findHouseType(String contractId) throws Exception;

  public List findHouseAccList(Pagination pagination, List loanBankList)
      throws Exception;

  public int findHouseAccCount(String contractId, String borrowerName,
      String cardNum, String sellerName, List loanBankList) throws Exception;

  public List findHouseAccPrintList(Pagination pagination, List loanBankList)
      throws Exception;
  public String getUserRealName(String name) throws Exception;
}
