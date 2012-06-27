package org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.bsinterface;

import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.EmpinfoDto;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.LoanreturnedbyfundSaveDTO;

public interface ILoanreturnedbyfundBS {

	public EmpinfoDto findBorrowerInfoByContractId(String cotractId) throws BusinessException;

	public List queryByBorrowerByEmpId_yg(String empId) throws BusinessException;

	public EmpinfoDto queryEmpInfo_yg(String orgid, String borrowerEmpId, String name, String cardnum)
			throws BusinessException;

	public Borrower showLoanapplyInfoBycontractid(String contractid, SecurityInfo securityInfo)
			throws BusinessException;

	public List findAssistanBorrowerInfo(String contractid) throws BusinessException;

	public LoanreturnedbyfundSaveDTO findLoanreturnedbyfundSaveInfo(String contractid) throws BusinessException;

	public LoanreturnedbyfundSaveDTO findLoanreturnedbyfundSaveInfo1(String contractid) throws BusinessException;

	public void LoanreturnedbyfundSave(LoanreturnedbyfundSaveDTO loanreturnedbyfundSaveDTO, String time, String xieYiNum)
			throws BusinessException;

	public void LoanreturnedbyfundSaveOperLog(SecurityInfo securityInfo, String id, String contractId)
			throws BusinessException;

	public String LoanreturnedbyfundCheck(String office, String paraNum) throws BusinessException;

	public String LoanreturnedbyfundFindID(String office, String paraNum) throws BusinessException;

	public String get_loanKouAcc(String contractId) throws BusinessException;

	public List LoanreturnedbyfundTbFind(Pagination pagination, SecurityInfo securityInfo) throws BusinessException;

	public List LoanreturnedbyfundTbCountListFind(Pagination pagination, SecurityInfo securityInfo)
			throws BusinessException;

	public void deleteLoanreturnedbyfundTbInfo(String id, String id_1) throws BusinessException;

	public void useLoanreturnedbyfundTbInfo(String id, SecurityInfo securityInfo) throws BusinessException;

	public String find_print_status(String contractid) throws BusinessException;

	public void deleteAllLoanreturnedbyfundTbInfo(Pagination pagination) throws BusinessException;

	public Object[] LoanreturnedbyfundTbCountListByContractId(String contractId) throws BusinessException;

	public Integer LoanreturnedbyfundTbCheck(String contractId) throws BusinessException;

	public void updateLoanreturnedbyfund(String contractId) throws Exception;

	public void LoanreturnedbyfundSaveAll(LoanreturnedbyfundSaveDTO loanreturnedbyfundSaveDTO, String id[],
			SecurityInfo securityInfo, String pl009id, String contractId, String time, String xieYiNum, String office)
			throws Exception, BusinessException;

	public String findOfficeName(String contractId) throws Exception;

	public Borrower showLoanapplyInfoByBorrowerEmpId(String borrowerEmpId) throws BusinessException;

	public AssistantBorrower findAssistanBorrower(String contractid) throws BusinessException;

	public BorrowerAcc findBorrowerAcc(String contractid) throws BusinessException;

	public BorrowerLoanInfo findBorrowerLoanInfo(String contractid) throws BusinessException;

	public Emp findEmpInfo(String empId, String orgId) throws BusinessException;

	public void check(String empId, String orgId, String contractid, String type) throws BusinessException;

	public AssistantBorrower findAssistanBorrower_1(String id) throws BusinessException;

	public String findXieYINum(String office, String year) throws BusinessException;

	public String findContractExsit(String contractid) throws BusinessException;

	public void updateLoanreturnedbyfund_1(String contractId, String date_temp) throws Exception;

	public String findOffice(String contractId) throws BusinessException;

	public String[] findCount(Pagination pagination, SecurityInfo securityInfo) throws BusinessException;

	public AssistantBorrower showLoanapplyInfoByAssiBorrowerEmpId(String assiborrowerEmpId) throws BusinessException;

	public EmpinfoDto queryEmpInfo(String orgid, String borrowerEmpId, String name, String cardnum)
			throws BusinessException;

	// ȡpl201��Ӧ�µ��»���Ϣ.
	public String find_pl201_CorpusInterest(String contractid, String biz_date) throws BusinessException;

	public String find_pl201_CorpusInterest_other(final String contractId, final String biz_date)
			throws BusinessException;

	public List get_loanbyfund_printlist(String cotractId, String status) throws BusinessException;

	public List get_loanbyfund_printlist_s(String cotractId, String status) throws BusinessException;

	public String get_print_money(String orgid, String empid) throws BusinessException;

	public EmpinfoDto queryEmpInfo_s(String orgid, String borrowerEmpId, String name, String namea, String nameb,
			String cardnum, String cardnuma, String cardnumb) throws BusinessException;

	public String find(String contractId) throws BusinessException;

	public String findHouseAddr(String contractid) throws BusinessException;

	public String findBankName(String loanbankid) throws BusinessException;

	public EmpinfoDto queryEmpInfo_s_wsh(String orgid, String borrowerEmpId, String name, String namea, String nameb,
			String cardnum, String cardnuma, String cardnumb) throws BusinessException;
}
