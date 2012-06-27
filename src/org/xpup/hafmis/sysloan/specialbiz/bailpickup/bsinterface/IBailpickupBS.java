package org.xpup.hafmis.sysloan.specialbiz.bailpickup.bsinterface;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTaDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.dto.BailpickupTbDTO;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.form.BailpickupTaAF;
import org.xpup.hafmis.sysloan.specialbiz.bailpickup.form.BailpickupTbAF;

public interface IBailpickupBS {
  /**
   * 查询
   * @param id
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public BailpickupTaDTO ajaxQuery(String id,Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  /**
   * 确定按钮
   */
  public String save(BailpickupTaDTO bailpickupTaDTO,SecurityInfo securityInfo) throws Exception,BusinessException;
  /**
   * TB维护查询
   * @param id
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public BailpickupTbDTO tbQuery(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  /**
   * Tb 维护删除
   * @param id
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void tbDelete(String flowHeadId,Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  
  public BailpickupTaAF tbPrintQuery(String flowHeadId,Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
}
