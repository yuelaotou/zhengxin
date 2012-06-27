package org.xpup.hafmis.sysloan.loanapply.endorsecontract.bsinterface;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.dto.EndorsecontractTaDTO;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTaChangeLoanMonthRateAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTbAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF;
import org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTeAF;

public interface IEndorsecontractBS {

  public List queryBankList(String contractId,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * 页面显示的查询
   * @param id
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTaAF queryContractInfo(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request,String insert) throws Exception,BusinessException;
  public String queryPL121Contract(String contractid) throws Exception,BusinessException;
  //用于打印和弹出框
  public EndorsecontractTaAF queryContractInfo_(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  public EndorsecontractTaAF queryContractInfo_wy(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * 查询PL003中ParamValue字段
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public String queryParamValue(SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * 分发确认按钮
   * @param form
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void endorsecontractTaMaitainSure(String loanassistantorgId,EndorsecontractTaAF endorsecontractTaAF,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * 分发扫描按钮
   * @param endorsecontractTaAF
   * @param securityInfo
   * @throws Exception
   * @throws BusinessException
   */
  public void endorsecontractTaMaitainScan(EndorsecontractTaAF endorsecontractTaAF,SecurityInfo securityInfo)throws Exception,BusinessException;
  /**
   * 查询抵押合同信息
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTbAF queryPledgeContractList(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  //用于打印
  public EndorsecontractTbAF queryPledgeContractList_(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  //抵押合同变更专用
  public EndorsecontractTbAF queryPledgeContractList_Chg(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * TbTOP分发确定按钮
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void sure(Integer id,String loanassistantorgId,SecurityInfo securityInfo,EndorsecontractTbAF endorsecontractTbAF) throws Exception,BusinessException;
  /**
   * 查询PL121实体后把属性set到AF
   * @param contractId
   * @param id(PL121Id)
   * @param debitter
   * @param office
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTbAF updatePledgeContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * 分发删除Pl121
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public void deletePledgeContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Tc查询
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTcAF queryImpawnContractList(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
 //用于打印
  public EndorsecontractTcAF queryImpawnContractList_(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  //担保抵押变更专用
  public EndorsecontractTcAF queryImpawnContractList_Chg(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Tc 判断Pl121
   * @param id
   * @param securityInfo
   * @param endorsecontractTcAF
   * @throws Exception
   * @throws BusinessException
   */
  public void sureTc(String id,String loanassistantorgId,SecurityInfo securityInfo,EndorsecontractTcAF endorsecontractTcAF) throws Exception,BusinessException;
  /**
   * TC 分发 修改按钮 
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTcAF updateImpawnContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * TC 分发 删除按钮
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void deleteImpawnContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * TD 查询
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTdAF queryAssurerList(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  public EndorsecontractTdAF queryAssurerListByEmpId(String id,String orgId,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  //用于打印
  public EndorsecontractTdAF queryAssurerList_(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  public EndorsecontractTdAF queryAssurerList_Chg(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Td 分发 确定按钮
   * @param id
   * @param securityInfo
   * @param endorsecontractTcAF
   * @throws Exception
   * @throws BusinessException
   */
  public void sureTd(String id,SecurityInfo securityInfo,EndorsecontractTdAF endorsecontractTdAF) throws Exception,BusinessException;
  /**
   * Td 分发 修改按钮
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTdAF updateAssurer(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Td 分发 删除按钮
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void deleteAssurer(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Td 保证人弹出框 通过ID查询PL123 empId
   * @param id
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public String selectAssurerEmpIdById(String id,SecurityInfo securityInfo) throws Exception,BusinessException;
  /**
   * Te 维护查询
   * @param pagination
   * @param securityInfo
   * @return
   * @throws Exception
   * @throws BusinessException
   */
  public EndorsecontractTeAF queryList(Pagination pagination,SecurityInfo securityInfo) throws Exception,BusinessException;
  /**
   * Te 删除
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void deleteContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Te 签订合同
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void sureContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * Te 撤销签订合同
   * @param id
   * @param pagination
   * @param securityInfo
   * @param request
   * @throws Exception
   * @throws BusinessException
   */
  public void delContract(String id,Pagination pagination,SecurityInfo securityInfo,HttpServletRequest request) throws Exception,BusinessException;
  /**
   * 更新ta中的path
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTa_Yqf(String contractId, String path)throws Exception;
  /**
   * 更新tb中的path
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTb_Yqf(String contractId, String path)throws Exception;
  public void updateScanTe_Yqf(String contractId, String path)throws Exception;
  /**
   * 更新tc中的path
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTc_Yqf(String contractId, String path)throws Exception;
  /**
   * 更新td中的path
   * @param contractId
   * @param path
   * @throws Exception
   */
  public void updateScanTd_Yqf(String contractId, String path)throws Exception;
  /**
   *更具银行明确是否采用新利率
   * 查找出最新的利率
   * @param bankId,term,loanMonthRate
   * @return loanMonthRate
   */
  public EndorsecontractTaChangeLoanMonthRateAF queryLoanMonthRate(String bankId,String term,String loanMonthRate,String loanMode,String loanMoney);
  
  /**
   * hanl
   * 更新合同状态
   * @param contractId
   */
  public void updateContractSt(String contractId)throws Exception;
}
