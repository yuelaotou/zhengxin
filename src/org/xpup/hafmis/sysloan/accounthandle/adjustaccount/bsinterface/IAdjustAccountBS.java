package org.xpup.hafmis.sysloan.accounthandle.adjustaccount.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTaInfoDTO;
import org.xpup.hafmis.sysloan.accounthandle.adjustaccount.dto.AdjustAccountTbListDTO;
import org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF;

public interface IAdjustAccountBS {
  /**
   * 查询错帐调整弹出框List的方法
   * 
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   */
  public List findAdjustAccountPopList(Pagination pagination, List loanbankList)
      throws Exception;

  /**
   * 判断将要调整业务是否符合调整要求(根据错账凭证编号查询)
   * 
   * @param flowHeadId PL202表主键
   * @param loanbankList
   * @throws Exception
   * @throws BusinessException
   */
  public AdjustAccountTaInfoDTO judgeLoanFlowHead(String flowHeadId,
      List loanbankList,SecurityInfo securityInfo) throws Exception, BusinessException;

  /**
   * 判断将要调整业务是否符合调整要求(根据贷款帐号查询)
   * 
   * @param loanKouAcc
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public AdjustAccountTaInfoDTO judgeLoanKouAcc(String loanKouAcc, List loanbankList, SecurityInfo securityInfo)
  throws Exception, BusinessException;

  /**
   * 查询错帐调整的原始信息
   * 
   * @param flowHeadId PL202的主键
   * @param loanKouAcc 贷款帐号
   * @param bizType 业务状态
   * @param autoOverPay是否自动挂账
   * @return 用于打印凭证的，凭证号
   * @throws Exception
   */
  public String saveAdjustAccountInfo(String flowHeadId, String loanKouAcc,
      String bizType, String autoOverPay, SecurityInfo securityInfo,
      AdjustAccountTaInfoDTO adjustAccountTaInfoDTO) throws Exception;

  /**
   * 查询错帐调整维护列表
   * 
   * @param pagination
   * @param loanbankList
   * @return
   * @throws Exception
   */
  public Object[] findAdjustAccountList(Pagination pagination, List loanbankList)
      throws Exception;

  /**
   * 删除错帐调整信息的方法
   * 
   * @param flowHeadId PL202表的id
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void deleteAdjustAccountInfo(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  /**
   * 查询打印信息的方法
   * 
   * @param flowHeadId PL202表的id
   * @return
   * @throws Exception
   */
  public AdjustAccountTbListDTO findPrintInfo(String flowHeadId)
      throws Exception;

  /**
   * 查询PL201中的本金与利息用来进行判断
   * @param yearMonth 还款年月
   * @param loanKouAcc 贷款帐号
   * @return obj[0]本金，obj[1]利息
   * @throws Exception
   * @throws BusinessException
   */
  public Object[] findCorpusAndInterest(String yearMonth, String loanKouAcc, int plLoanReturnType)
      throws Exception, BusinessException;
  
  /**
   * 打印查询方法
   * @param headId 头表id
   * @return
   * @throws Exception
   */
  public LoancallbackTaAF findPrintCallbackInfo(String headId) throws Exception;
}
