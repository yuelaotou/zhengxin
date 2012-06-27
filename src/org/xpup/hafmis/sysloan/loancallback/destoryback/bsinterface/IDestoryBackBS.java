package org.xpup.hafmis.sysloan.loancallback.destoryback.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loancallback.destoryback.dto.DestoryBackTaDTO;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTaAF;
import org.xpup.hafmis.sysloan.loancallback.destoryback.form.DestoryBackTbAF;

public interface IDestoryBackBS {
  
  /**
   *办理核销收回- 通过贷款账号带出页面信息
   * @param destoryBackTaDTO
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public DestoryBackTaAF queryContractInfo(String loanKouAcc,
      SecurityInfo securityInfo) throws Exception, BusinessException;

 
  /**
   * 办理核销收回-保存页面信息
   * @param destoryBackTaDTO
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void saveDestoryBack(DestoryBackTaDTO destoryBackTaDTO,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  /**
   * 核销收回维护-核销收回维护信息列表
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public DestoryBackTbAF queryDestoryBackTbShowListByCriterions(Pagination pagination,
      SecurityInfo securityInfo) throws Exception, BusinessException;
  /**
   * Tb 核销收回维护删除
   * @param id
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void deleteDestoryBackInfo(String flowHeadId,SecurityInfo securityInfo) throws Exception,BusinessException;

  /**
   * Tb 核销收回维护列表打印
   * @param pagination
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public List findDestoryBackTbPrint(Pagination pagination,SecurityInfo securityInfo) throws Exception;
  /**
   * Tb 核销收回维护弹出眶
   * @param pagination
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public DestoryBackTaDTO  queryDestoryBackTbWindowById(String flowHeadId,
      SecurityInfo securityInfo) throws Exception, BusinessException;
}
