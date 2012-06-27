package org.xpup.hafmis.sysloan.loanapply.loanapply.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.DeveleperNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTbNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTcNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTdNewAF;
import org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyTeNewAF;

public interface ILoanapplyBS {

  // public LoanapplyNewAF showLoanapplyInfo(String empid, SecurityInfo
  // securityInfo)throws BusinessException;

  public LoanapplyNewAF findEmpInfo(String empid, String orgid)
      throws BusinessException;

  public List queryPhotoURLByContractID(String contractId)
      throws BusinessException;

  public LoanapplyNewAF showLoanapplyInfoBycontractid(String contractid,
      SecurityInfo securityInfo) throws BusinessException;

  public String saveBorrowerInfo(LoanapplyNewAF loanapplyaf, String contractid,
      SecurityInfo securityInfo) throws BusinessException, Exception;

  public LoanapplyTbNewAF findAssistanBorrowerInfo(String contractid,
      SecurityInfo securityInfo, String maxauxiliaryid)
      throws BusinessException;

  public void checkContractIdByContractId(String contractId)
      throws BusinessException;

  public LoanapplyTbNewAF findAssistantBorrowerInfoByempId(String empId,
      String orgId, String contractaId) throws BusinessException;

  public void addAssistantBorrowerInfo(LoanapplyTbNewAF loanapplytbnewAF,
      SecurityInfo securityInfo, String temp_addtype) throws BusinessException;

  public void deleteAsistantBorrowerInfo(String idaf, SecurityInfo securityInfo)
      throws BusinessException;

  public LoanapplyTcNewAF showBuyHouseInfoBycontractid(String contractid,
      String buyhouseorgid) throws BusinessException;

  public String checkTCContractIdByContractId(String contractId)
      throws BusinessException;

  public void saveHouseInfo(LoanapplyTcNewAF loanapplytcnewAF,
      SecurityInfo securityInfo) throws BusinessException;

  public LoanapplyTdNewAF showBorrowerLoanInfoByContractid(String contractid,
      SecurityInfo securityInfo) throws BusinessException;

  // 这个方法为录入额度信息的时候用的页面的期限要转化为年
  public LoanapplyTdNewAF showBorrowerLoanInfoByContractid1(String contractid,
      SecurityInfo securityInfo) throws BusinessException;

  public String findMonthRate(String office, String loantimeLimit,
      String loanMood) throws BusinessException;

  public void checkTdContractIdByContractId(String contractId)
      throws BusinessException;

  public void saveBorrowerLoanInfo(LoanapplyTdNewAF loanapplytdnewAF,
      SecurityInfo securityInfo) throws BusinessException;

  public LoanapplyTeNewAF showBorrowerList(Pagination pagination,
      SecurityInfo securityInfo) throws BusinessException, Exception;

  public void deleteBorrowerInfo(String id, SecurityInfo securityInfo)
      throws BusinessException, Exception;

  public void truepproval(String id, SecurityInfo securityInfo)
      throws BusinessException;

  public String findOfficeName(String office) throws Exception;

  public String findFloorMun(String floorId, String buyhouseorgid)
      throws Exception;

  public DeveleperNewAF findDeveleperList(Pagination pagination,
      SecurityInfo securityInfo, String flag) throws Exception;

  public String findNotPassReasons(String contractId, String type)
      throws Exception;

  public LoanapplyTdNewAF printBorrowerLoanInfoByContractid(String contractid,
      SecurityInfo securityInfo) throws BusinessException;

  public void updateBorrowerInfo(String contractId, String path)
      throws Exception;

  public void updateAssistantBorrowe(String idaf, String path) throws Exception;

  public void updateHouseInfo(String contractId, String path) throws Exception;

  public void updateBorrowerLoanInfo(String contractId, String path)
      throws Exception;

  public void deletedBorrowerLoanInfo(String contractId,
      SecurityInfo securityInfo) throws Exception, BusinessException;

  public LoanapplyTcNewAF findSomeInfo(String contractId, String floorId,
      String developerPaybankAAcc) throws Exception;

  public String findOrgRate(String orgid) throws BusinessException;

  public String findEmpRate(String orgid) throws BusinessException;
  /**
   * 根据开发商查询楼盘列表
   * @param developerId
   * @return
   * @throws Exception
   */
  public List findFloorList(final String developerId) throws Exception;
  /**
   * wangshuang 根据开发商和楼盘名称查询该楼盘所有的楼号
   */
  public List findAllBuildNumList(final String developId, final String floorName)
      throws Exception;

  public List findHouseAddListByFloorId(final String floorId) throws Exception;

  public String findFloorIdByCriterions(String developerId, String floorName,
      String floorNum) throws BusinessException;

  public void findEmpIsPayingback(String empName, String cardNum)
      throws BusinessException;

  public void updateRedate(String contractId, SecurityInfo securityInfo)
      throws BusinessException;

  public void updatePrintStatus(String contractId) throws BusinessException;

  public boolean isBothHaveGjj(String contractId, SecurityInfo securityInfo) throws BusinessException;
  public void chexiaoshenqing(String id, SecurityInfo securityInfo)
      throws BusinessException;
}
