package org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;
import org.xpup.hafmis.common.util.BusiConst;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.common.util.CardMunChange;
import org.xpup.hafmis.common.util.bizlog.BusiLogConst;
import org.xpup.hafmis.orgstrct.dao.CollBankDAO;
import org.xpup.hafmis.orgstrct.dao.OrganizationUnitDAO;
import org.xpup.hafmis.orgstrct.domain.CollBank;
import org.xpup.hafmis.orgstrct.domain.OrganizationUnit;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.syscollection.common.dao.EmpDAO;
import org.xpup.hafmis.syscollection.common.domain.entity.Emp;
import org.xpup.hafmis.sysloan.common.dao.AssistantBorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerAccDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerDAO;
import org.xpup.hafmis.sysloan.common.dao.BorrowerLoanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.CollLoanbackParaDAO;
import org.xpup.hafmis.sysloan.common.dao.FundloanInfoDAO;
import org.xpup.hafmis.sysloan.common.dao.PlOperateLogDAO;
import org.xpup.hafmis.sysloan.common.dao.RestoreLoanDAO;
import org.xpup.hafmis.sysloan.common.dao.XieYiNumDAO;
import org.xpup.hafmis.sysloan.common.domain.entity.AssistantBorrower;
import org.xpup.hafmis.sysloan.common.domain.entity.Borrower;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerAcc;
import org.xpup.hafmis.sysloan.common.domain.entity.BorrowerLoanInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.FundloanInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.PlOperateLog;
import org.xpup.hafmis.sysloan.common.domain.entity.XieYiNum;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.bsinterface.ILoanreturnedbyfundBS;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.EmpinfoDto;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.LoanreturnedbyfundSaveDTO;

/**
 * @author ���� 2007-9-21
 */
public class LoanreturnedbyfundBS implements ILoanreturnedbyfundBS {

	private BorrowerDAO borrowerDAO = null;

	private AssistantBorrowerDAO assistantBorrowerDAO = null;

	private PlOperateLogDAO plOperateLogDAO = null;

	private FundloanInfoDAO fundloanInfoDAO = null;

	private CollLoanbackParaDAO collLoanbackParaDAO = null;

	private OrganizationUnitDAO organizationUnitDAO = null;

	private BorrowerAccDAO borrowerAccDAO = null;

	private BorrowerLoanInfoDAO borrowerLoanInfoDAO = null;

	private EmpDAO empDAO = null;

	private RestoreLoanDAO restoreLoanDAO = null;

	private CollBankDAO collBankDAO = null;

	private XieYiNumDAO xieYiNumDAO = null;

	public void setAssistantBorrowerDAO(AssistantBorrowerDAO assistantBorrowerDAO) {
		this.assistantBorrowerDAO = assistantBorrowerDAO;
	}

	public void setPlOperateLogDAO(PlOperateLogDAO plOperateLogDAO) {
		this.plOperateLogDAO = plOperateLogDAO;
	}

	public void setBorrowerDAO(BorrowerDAO borrowerDAO) {
		this.borrowerDAO = borrowerDAO;
	}

	public void setFundloanInfoDAO(FundloanInfoDAO fundloanInfoDAO) {
		this.fundloanInfoDAO = fundloanInfoDAO;
	}

	public void setCollLoanbackParaDAO(CollLoanbackParaDAO collLoanbackParaDAO) {
		this.collLoanbackParaDAO = collLoanbackParaDAO;
	}

	/**
	 * wsh ��ʾ�������Ϣ
	 */
	@Override
	public Borrower showLoanapplyInfoBycontractid(String contractid, SecurityInfo securityInfo)
			throws BusinessException {
		Borrower borrower = borrowerDAO.findBorrrowInfoByContractid(contractid);// ��ѯpl110
		try {
			if (borrower == null) {
				throw new BusinessException("�ú�ͬ������");
			}
			if (borrower.getEmpSt() == null) {
				borrower.setEmpSt_("");
			} else {
				borrower.setEmpSt_(BusiTools.getBusiValue(Integer.parseInt(borrower.getEmpSt()),
						BusiConst.OLDPAYMENTSTATE));
			}
		} catch (BusinessException be) {
			throw be;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrower;
	}

	/**
	 * wsh ��ʾ����������Ϣ�б�
	 */
	@Override
	public List findAssistanBorrowerInfo(String contractid) throws BusinessException {
		List list = null;
		try {
			list = assistantBorrowerDAO.findAssistanBorrowerListByContractid_wsh(contractid);// �������б�
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public LoanreturnedbyfundSaveDTO findLoanreturnedbyfundSaveInfo(String id) throws BusinessException {
		// TODO Auto-generated method stub
		LoanreturnedbyfundSaveDTO loanreturnedbyfundSaveDTO = new LoanreturnedbyfundSaveDTO();
		try {
			loanreturnedbyfundSaveDTO = assistantBorrowerDAO.findAssistanBorrowerSaveListByContractid_wsh(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return loanreturnedbyfundSaveDTO;
	}

	/*
	 * wsh ��Ӽ�¼��ȷ��
	 */
	@Override
	public void LoanreturnedbyfundSave(LoanreturnedbyfundSaveDTO loanreturnedbyfundSaveDTO, String time, String xieYiNum)
			throws BusinessException {
		// TODO Auto-generated method stub
		try {
			FundloanInfo fundloanInfo = new FundloanInfo();
			fundloanInfo.setCardKind(loanreturnedbyfundSaveDTO.getCardKind());
			fundloanInfo.setCardNum(loanreturnedbyfundSaveDTO.getCardNum());
			fundloanInfo.setContractId(loanreturnedbyfundSaveDTO.getContractId());
			fundloanInfo.setEmpId(loanreturnedbyfundSaveDTO.getEmpId());
			fundloanInfo.setEmpName(loanreturnedbyfundSaveDTO.getEmpName());
			fundloanInfo.setOrgId(loanreturnedbyfundSaveDTO.getOrgId());
			fundloanInfo.setLoanKouAcc(loanreturnedbyfundSaveDTO.getLoanKouAcc());
			fundloanInfo.setSeq(loanreturnedbyfundSaveDTO.getSeq());
			fundloanInfo.setStatus(loanreturnedbyfundSaveDTO.getStatus());
			fundloanInfo.setReserveaA(time);
			fundloanInfo.setReserveaC(xieYiNum);
			fundloanInfo.setReserveaB("1");
			fundloanInfoDAO.insert(fundloanInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/*
	 * ��������
	 */
	@Override
	public LoanreturnedbyfundSaveDTO findLoanreturnedbyfundSaveInfo1(String contractid) throws BusinessException {
		// TODO Auto-generated method stub
		LoanreturnedbyfundSaveDTO loanreturnedbyfundSaveDTO = new LoanreturnedbyfundSaveDTO();
		try {
			loanreturnedbyfundSaveDTO = assistantBorrowerDAO
					.findAssistanBorrowerSaveBorrowertByContractid_wsh(contractid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return loanreturnedbyfundSaveDTO;
	}

	/*
	 * ������־ (non-Javadoc)
	 * @see
	 * org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.bsinterface.ILoanreturnedbyfundBS#LoanreturnedbyfundSaveOperLog
	 * (org.xpup.hafmis.orgstrct.dto.SecurityInfo, java.lang.String, java.lang.String)
	 */
	@Override
	public void LoanreturnedbyfundSaveOperLog(SecurityInfo securityInfo, String id, String contractId)
			throws BusinessException {
		// TODO Auto-generated method stub
		try {
			PlOperateLog plOperateLog = new PlOperateLog();// ������־����
			plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ���ϵͳ
			plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_LOANRETURN_MAINTAIN));// ά��
			plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));// ��ʾɾ��
			plOperateLog.setOpBizId(new BigDecimal(id));
			plOperateLog.setOpIp(securityInfo.getUserIp());
			plOperateLog.setOpTime(new Date());
			plOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
			plOperateLog.setContractId(contractId);
			plOperateLogDAO.insert(plOperateLog);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/*
	 * ��ѯ���´��Ƿ����Ϊ�������˿ۿ�
	 */
	@Override
	public String LoanreturnedbyfundCheck(String office, String paraNum) throws BusinessException {
		// TODO Auto-generated method stub
		String paraValue = "";
		try {
			paraValue = collLoanbackParaDAO.queryParamValueByOffice_wsh(paraNum, office);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return paraValue;
	}

	/*
	 * ��ѯpl009���
	 */
	@Override
	public String LoanreturnedbyfundFindID(String office, String paraNum) throws BusinessException {
		// TODO Auto-generated method stub
		String id = "";
		try {
			id = collLoanbackParaDAO.queryParamValueByOffice_wsh1(paraNum, office);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public String get_loanKouAcc(String contractId) throws BusinessException {
		String id = "";
		try {
			id = collLoanbackParaDAO.get_loanKouAcc(contractId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	/*
	 * ��ѯ��ά���б��list
	 */
	@Override
	public List LoanreturnedbyfundTbFind(Pagination pagination, SecurityInfo securityInfo) throws BusinessException {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		try {
			String contractId = (String) pagination.getQueryCriterions().get("contractId");
			String borrowerName = (String) pagination.getQueryCriterions().get("borrowerName");
			String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
			String xieYiNum = (String) pagination.getQueryCriterions().get("xieYiNUM");
			String assiBorrowerName = (String) pagination.getQueryCriterions().get("assiBorrowerName");
			String assiBorrowerCardNum = (String) pagination.getQueryCriterions().get("assiBorrowerCardNum");
			String orgId = (String) pagination.getQueryCriterions().get("orgId");
			String empId = (String) pagination.getQueryCriterions().get("empId");
			String startDate = (String) pagination.getQueryCriterions().get("startDate");
			String endDate = (String) pagination.getQueryCriterions().get("endDate");
			String begstop = (String) pagination.getQueryCriterions().get("begstop");
			String endstop = (String) pagination.getQueryCriterions().get("endstop");
			String loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
			String sta = (String) pagination.getQueryCriterions().get("sta");
			String orderBy = pagination.getOrderBy();
			String orderother = pagination.getOrderother();
			int start = pagination.getFirstElementOnPage() - 1;
			int pageSize = pagination.getPageSize();
			int page = pagination.getPage();
			list = fundloanInfoDAO.findFundloanList(contractId, borrowerName, cardNum, orderBy, orderother, start,
					pageSize, page, xieYiNum, assiBorrowerName, assiBorrowerCardNum, orgId, empId, startDate, endDate,
					loanBankId, sta, securityInfo, begstop, endstop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/*
	 * ��ѯȫ����ά���е�countlist
	 */
	@Override
	public List LoanreturnedbyfundTbCountListFind(Pagination pagination, SecurityInfo securityInfo)
			throws BusinessException {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		try {
			String contractId = (String) pagination.getQueryCriterions().get("contractId");
			String borrowerName = (String) pagination.getQueryCriterions().get("borrowerName");
			String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
			String xieYiNum = (String) pagination.getQueryCriterions().get("xieYiNum");
			String assiBorrowerName = (String) pagination.getQueryCriterions().get("assiBorrowerName");
			String assiBorrowerCardNum = (String) pagination.getQueryCriterions().get("assiBorrowerCardNum");
			String orgId = (String) pagination.getQueryCriterions().get("orgId");
			String empId = (String) pagination.getQueryCriterions().get("empId");
			String startDate = (String) pagination.getQueryCriterions().get("startDate");
			String endDate = (String) pagination.getQueryCriterions().get("endDate");
			String begstop = (String) pagination.getQueryCriterions().get("begstop");
			String endstop = (String) pagination.getQueryCriterions().get("endstop");
			String loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
			String sta = (String) pagination.getQueryCriterions().get("sta");
			String orderBy = pagination.getOrderBy();
			String orderother = pagination.getOrderother();
			list = fundloanInfoDAO.findFundloanCountList(contractId, borrowerName, cardNum, xieYiNum, assiBorrowerName,
					assiBorrowerCardNum, orgId, empId, startDate, endDate, loanBankId, orderBy, orderother, sta,
					securityInfo, begstop, endstop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List get_loanbyfund_printlist(String cotractId, String status) throws BusinessException {
		List list = new ArrayList();
		try {
			list = fundloanInfoDAO.get_loanbyfund_printlist(cotractId, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List get_loanbyfund_printlist_s(String cotractId, String status) throws BusinessException {
		List list = new ArrayList();
		try {
			list = fundloanInfoDAO.get_loanbyfund_printlist_s(cotractId, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public EmpinfoDto findBorrowerInfoByContractId(String cotractId) throws BusinessException {
		EmpinfoDto empinfoDto = null;
		try {
			empinfoDto = fundloanInfoDAO.findBorrowerInfoByContractId(cotractId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empinfoDto;
	}

	@Override
	public String get_print_money(String orgid, String empid) throws BusinessException {
		// TODO Auto-generated method stub
		String list = "";
		try {
			list = fundloanInfoDAO.get_print_money(orgid, empid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/*
	 * ɾ��ά���б��еļ�¼ ��ݺ�ͬ���ɾ���(����˺͸�������
	 */
	@Override
	public void deleteLoanreturnedbyfundTbInfo(String contractid, String id) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			String xieyiNum = "";
			xieyiNum = fundloanInfoDAO.find_xieyiNum(contractid, id);
			String str[] = xieyiNum.split(",");
			String number = "";
			if (str[0].equals("")) {
				number = "";
			} else {
				number = "1" + str[0].substring(6, 10);
			}
			number = str[0];

			if (str[0].equals("")) {
				// ����F_P_NUM�в�����ϼ�¼
			} else {
				// ��F_P_NUM�в�����ϼ�¼
				XieYiNum xieYiNum = new XieYiNum();
				xieYiNum.setOffice(str[1]);
				xieYiNum.setType("1");
				xieYiNum.setXieyiNum_id("1" + str[0].substring(6, 10));
				xieYiNum.setOfficeid(str[0].substring(4, 6));
				xieYiNum.setYear(str[0].substring(0, 4));
				xieYiNumDAO.insert(xieYiNum);
			}
			fundloanInfoDAO.deleteLoanreturnedbyfundTbInfo(contractid, number);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// ����
	@Override
	public void useLoanreturnedbyfundTbInfo(String contractid, SecurityInfo securityInfo) throws BusinessException {
		String xieyi_id = "";
		boolean bool = true;
		try {
			xieyi_id = fundloanInfoDAO.find_xieyi_zuofei_id(contractid, securityInfo.getUserInfo().getPlbizDate()
					.substring(0, 4));
			if (!xieyi_id.equals("")) {
				fundloanInfoDAO.delete_f_p_num(contractid, "1" + xieyi_id.substring(6, 10));
				bool = false;
			}
			if (xieyi_id.equals("")) {
				xieyi_id = fundloanInfoDAO.find_xieyi_id(contractid, securityInfo.getUserInfo().getPlbizDate()
						.substring(0, 4));
			}
			fundloanInfoDAO.useLoanreturnedbyfundTbInfo(contractid, xieyi_id);
			if (bool) {
				fundloanInfoDAO.add_f_p_num(xieyi_id.substring(4, 6), securityInfo.getUserInfo().getPlbizDate()
						.substring(0, 4));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public String find_print_status(String contractid) throws BusinessException {

		String status = "";
		try {

			status = fundloanInfoDAO.find_print_status(contractid);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	/*
	 * ��ݲ�ѯ���ɾ��ά��ҳ���ȫ����¼
	 */
	@Override
	public void deleteAllLoanreturnedbyfundTbInfo(Pagination pagination) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			String contractId = (String) pagination.getQueryCriterions().get("contractId");
			String borrowerName = (String) pagination.getQueryCriterions().get("borrowerName");
			String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
			fundloanInfoDAO.deleteAllLoanreturnedbyfundTbInfo(contractId, borrowerName, cardNum);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/*
	 * ��ݺ�ͬ��Ų�ѯpl400z �еļ�¼
	 */
	@Override
	public Object[] LoanreturnedbyfundTbCountListByContractId(String contractId) throws BusinessException {
		// TODO Auto-generated method stub
		Object[] obj = new Object[2];
		List list = new ArrayList();
		try {
			list = fundloanInfoDAO.LoanreturnedbyfundTbCountListByContractId(contractId);
			String office = fundloanInfoDAO.queryOfficeByContractId(contractId);
			obj[0] = list;
			obj[1] = office;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return obj;
	}

	/*
	 * ��ݺ�ͬ��Ų�ѯ��pl400�Ƿ���ڼ�¼��������ڰ���ʱ�ֽ���״̬����Ϊ��ϣ�����Ӽ�¼�������ֱ����Ӽ�¼
	 */
	@Override
	public Integer LoanreturnedbyfundTbCheck(String contractId) throws BusinessException {
		// TODO Auto-generated method stub
		Integer count = new Integer(0);
		try {
			count = fundloanInfoDAO.LoanreturnedbyfundTbCheck_wsh(contractId);
			if (count.intValue() > 0) {
				throw new BusinessException("ѡ��ĺ�ͬ�����ڰ����л����Ѿ���ǩ!");
			}
		} catch (BusinessException be) {
			throw be;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	/*
	 * ��ݺ�ͬ��Ž�pl400�еļ�¼����Ϊ���
	 */
	@Override
	public void updateLoanreturnedbyfund(String contractId) throws Exception {
		// TODO Auto-generated method stub
		try {
			fundloanInfoDAO.updateFundloanInfoStatus(contractId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/*
	 * �������˼�����������Ϣ (non-Javadoc)
	 * @see
	 * org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.bsinterface.ILoanreturnedbyfundBS#LoanreturnedbyfundSaveAll
	 * ()
	 */
	@Override
	public void LoanreturnedbyfundSaveAll(LoanreturnedbyfundSaveDTO loanreturnedbyfundSaveDTO, String id[],
			SecurityInfo securityInfo, String pl009id, String contractId, String time, String xieYiNum, String office)
			throws Exception, BusinessException {
		try {
			if (loanreturnedbyfundSaveDTO.getEmpId() != null && !loanreturnedbyfundSaveDTO.getEmpId().equals("")) {

				FundloanInfo fundloanInfo = new FundloanInfo();
				fundloanInfo.setCardKind("0");
				fundloanInfo.setCardNum(loanreturnedbyfundSaveDTO.getCardNum());
				fundloanInfo.setContractId(loanreturnedbyfundSaveDTO.getContractId());
				fundloanInfo.setEmpId(new Integer(loanreturnedbyfundSaveDTO.getEmpId()) + "");
				fundloanInfo.setEmpName(loanreturnedbyfundSaveDTO.getEmpName());
				fundloanInfo.setOrgId(new Integer(loanreturnedbyfundSaveDTO.getOrgId()) + "");
				fundloanInfo.setLoanKouAcc(loanreturnedbyfundSaveDTO.getLoanKouAcc());
				fundloanInfo.setSeq("0");
				fundloanInfo.setStatus("0");
				fundloanInfo.setReserveaA(time);
				fundloanInfo.setReserveaB("2");
				fundloanInfoDAO.insert(fundloanInfo);
			}

			if (loanreturnedbyfundSaveDTO.getEmpId_s() != null && !loanreturnedbyfundSaveDTO.getEmpId_s().equals("")) {

				FundloanInfo fundloanInfo_s = new FundloanInfo();
				fundloanInfo_s.setCardKind("0");
				fundloanInfo_s.setCardNum(loanreturnedbyfundSaveDTO.getCardNum_s());
				fundloanInfo_s.setContractId(loanreturnedbyfundSaveDTO.getContractId());
				fundloanInfo_s.setEmpId(new Integer(loanreturnedbyfundSaveDTO.getEmpId_s()) + "");
				fundloanInfo_s.setEmpName(loanreturnedbyfundSaveDTO.getEmpName_s());
				fundloanInfo_s.setOrgId(new Integer(loanreturnedbyfundSaveDTO.getOrgId_s()) + "");
				fundloanInfo_s.setLoanKouAcc(loanreturnedbyfundSaveDTO.getLoanKouAcc());
				fundloanInfo_s.setSeq("1");
				fundloanInfo_s.setStatus("0");
				fundloanInfo_s.setReserveaA(time);
				fundloanInfo_s.setReserveaB("2");
				fundloanInfoDAO.insert(fundloanInfo_s);
			}
			if (loanreturnedbyfundSaveDTO.getEmpId_sa() != null && !loanreturnedbyfundSaveDTO.getEmpId_sa().equals("")) {

				FundloanInfo fundloanInfo_sa = new FundloanInfo();
				fundloanInfo_sa.setCardKind("0");
				fundloanInfo_sa.setCardNum(loanreturnedbyfundSaveDTO.getCardNum_sa());
				fundloanInfo_sa.setContractId(loanreturnedbyfundSaveDTO.getContractId());
				fundloanInfo_sa.setEmpId(new Integer(loanreturnedbyfundSaveDTO.getEmpId_sa()) + "");
				fundloanInfo_sa.setEmpName(loanreturnedbyfundSaveDTO.getEmpName_sa());
				fundloanInfo_sa.setOrgId(new Integer(loanreturnedbyfundSaveDTO.getOrgId_sa()) + "");
				fundloanInfo_sa.setLoanKouAcc(loanreturnedbyfundSaveDTO.getLoanKouAcc());
				fundloanInfo_sa.setSeq("2");
				fundloanInfo_sa.setStatus("0");
				fundloanInfo_sa.setReserveaA(time);
				fundloanInfo_sa.setReserveaB("2");
				fundloanInfoDAO.insert(fundloanInfo_sa);
			}
			if (loanreturnedbyfundSaveDTO.getEmpId_sb() != null && !loanreturnedbyfundSaveDTO.getEmpId_sb().equals("")) {

				FundloanInfo fundloanInfo_sb = new FundloanInfo();
				fundloanInfo_sb.setCardKind("0");
				fundloanInfo_sb.setCardNum(loanreturnedbyfundSaveDTO.getCardNum_sb());
				fundloanInfo_sb.setContractId(loanreturnedbyfundSaveDTO.getContractId());
				fundloanInfo_sb.setEmpId(new Integer(loanreturnedbyfundSaveDTO.getEmpId_sb()) + "");
				fundloanInfo_sb.setEmpName(loanreturnedbyfundSaveDTO.getEmpName_sb());
				fundloanInfo_sb.setOrgId(new Integer(loanreturnedbyfundSaveDTO.getOrgId_sb()) + "");
				fundloanInfo_sb.setLoanKouAcc(loanreturnedbyfundSaveDTO.getLoanKouAcc());
				fundloanInfo_sb.setSeq("3");
				fundloanInfo_sb.setStatus("0");
				fundloanInfo_sb.setReserveaA(time);
				fundloanInfo_sb.setReserveaB("2");
				fundloanInfoDAO.insert(fundloanInfo_sb);
			}
			PlOperateLog plOperateLog = new PlOperateLog();// ������־����
			plOperateLog.setOpSys(new BigDecimal(BusiLogConst.OP_SYSTEM_TYPE_LOAN));// ���ϵͳ
			plOperateLog.setOpModel(String.valueOf(BusiLogConst.PL_OP_LOANRETURN_MAINTAIN));// ά��
			plOperateLog.setOpButton(String.valueOf(BusiLogConst.BIZLOG_ACTION_ADD));// ��ʾɾ��
			plOperateLog.setOpBizId(new BigDecimal(pl009id));
			plOperateLog.setOpIp(securityInfo.getUserIp());
			plOperateLog.setOpTime(new Date());
			plOperateLog.setOperator(securityInfo.getUserInfo().getUsername());
			plOperateLog.setContractId(contractId);
			plOperateLogDAO.insert(plOperateLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setOrganizationUnitDAO(OrganizationUnitDAO organizationUnitDAO) {
		this.organizationUnitDAO = organizationUnitDAO;
	}

	/*
	 * ͨ����´�id��ȡ���´���� (non-Javadoc)
	 * @see
	 * org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.bsinterface.ILoanreturnedbyfundBS#findOfficeName(java.lang
	 * .String)
	 */
	@Override
	public String findOfficeName(String office) throws Exception {
		// TODO Auto-generated method stub
		OrganizationUnit organizationUnit = new OrganizationUnit();
		try {
			organizationUnit = organizationUnitDAO.queryOrganizationUnitListByCriterions(office);
			return organizationUnit.getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return organizationUnit.getName();
	}

	@Override
	public Borrower showLoanapplyInfoByBorrowerEmpId(String borrowerEmpId) throws BusinessException {
		// TODO Auto-generated method stub
		Borrower borrower = borrowerDAO.queryByBorrowerByEmpId(borrowerEmpId);// ��ѯpl110
		try {
			if (borrower == null) {
				throw new BusinessException("���˺�ͬ�����ڻ��ͬ״̬����ȷ��");
			}
			if (borrower.getEmpSt() == null) {
				borrower.setEmpSt_("");
			} else {
				borrower.setEmpSt_(BusiTools.getBusiValue(Integer.parseInt(borrower.getEmpSt()),
						BusiConst.OLDPAYMENTSTATE));
			}
		} catch (BusinessException be) {
			throw be;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrower;

	}

	@Override
	public EmpinfoDto queryEmpInfo(String orgid, String borrowerEmpId, String name, String cardnum)
			throws BusinessException {
		EmpinfoDto borrower = borrowerDAO.queryEmpInfo(orgid, borrowerEmpId);
		String id = "";
		try {
			if (cardnum.length() == 18) {
				id = CardMunChange.get15Id(cardnum);
			}
			if (cardnum.length() == 15) {
				id = CardMunChange.get18Id(cardnum);
			}
			if (!borrower.getEmp_name().equals(name)
					|| (!borrower.getEmp_card_num().equals(id) && !borrower.getEmp_card_num().equals(cardnum))) {
				throw new BusinessException("ѡ��ְ�������� " + borrower.getEmp_name() + " �����֤ "
						+ borrower.getEmp_card_num() + " ���ͬ����������֤�Ų�һ��,�Ƿ����");
			}
		} catch (BusinessException be) {
			throw be;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return borrower;
	}

	@Override
	public EmpinfoDto queryEmpInfo_yg(String orgid, String borrowerEmpId, String name, String cardnum)
			throws BusinessException {
		return borrowerDAO.queryEmpInfo(orgid, borrowerEmpId);// ��ѯpl110
	}

	@Override
	public EmpinfoDto queryEmpInfo_s(String orgid, String borrowerEmpId, String name, String namea, String nameb,
			String cardnum, String cardnuma, String cardnumb) throws BusinessException {
		EmpinfoDto borrower = borrowerDAO.queryEmpInfo_s(orgid, borrowerEmpId);// ��ѯpl110
		String id = "";
		String ida = "";
		String idb = "";
		try {
			if (cardnum.length() == 18) {
				id = CardMunChange.get15Id(cardnum);
			}
			if (cardnum.length() == 15) {
				id = CardMunChange.get18Id(cardnum);
			}
			if (cardnuma.length() == 18) {
				ida = CardMunChange.get15Id(cardnuma);
			}
			if (cardnuma.length() == 15) {
				ida = CardMunChange.get18Id(cardnuma);
			}
			if (cardnumb.length() == 18) {
				idb = CardMunChange.get15Id(cardnumb);
			}
			if (cardnumb.length() == 15) {
				idb = CardMunChange.get18Id(cardnumb);
			}
			// if((borrower.getEmp_name_s().equals(name) &&
			// (borrower.getEmp_card_num_s().equals(id) ||
			// borrower.getEmp_card_num_s().equals(cardnum)))
			// || (borrower.getEmp_name_s().equals(namea) &&
			// (borrower.getEmp_card_num_s().equals(ida) ||
			// borrower.getEmp_card_num_s().equals(cardnuma)))
			// || (borrower.getEmp_name_s().equals(nameb) &&
			// (borrower.getEmp_card_num_s().equals(idb) ||
			// borrower.getEmp_card_num_s().equals(cardnumb)))){
			return borrower;
			// }else{
			// throw new BusinessException("ѡ��ְ������������֤���ͬ����������֤�Ų�һ��!");
			// }

		}
		// catch (BusinessException be) {
		// throw be;
		// }
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrower;

	}

	@Override
	public EmpinfoDto queryEmpInfo_s_wsh(String orgid, String borrowerEmpId, String name, String namea, String nameb,
			String cardnum, String cardnuma, String cardnumb) throws BusinessException {
		EmpinfoDto borrower = borrowerDAO.queryEmpInfo_s(orgid, borrowerEmpId);// ��ѯpl110
		String id = "";
		String ida = "";
		String idb = "";
		try {
			if (cardnum.length() == 18) {
				id = CardMunChange.get15Id(cardnum);
			}
			if (cardnum.length() == 15) {
				id = CardMunChange.get18Id(cardnum);
			}
			if (cardnuma.length() == 18) {
				ida = CardMunChange.get15Id(cardnuma);
			}
			if (cardnuma.length() == 15) {
				ida = CardMunChange.get18Id(cardnuma);
			}
			if (cardnumb.length() == 18) {
				idb = CardMunChange.get15Id(cardnumb);
			}
			if (cardnumb.length() == 15) {
				idb = CardMunChange.get18Id(cardnumb);
			}
			if ((borrower.getEmp_name_s().equals(name) && (borrower.getEmp_card_num_s().equals(id) || borrower
					.getEmp_card_num_s().equals(cardnum)))
					|| (borrower.getEmp_name_s().equals(namea) && (borrower.getEmp_card_num_s().equals(ida) || borrower
							.getEmp_card_num_s().equals(cardnuma)))
					|| (borrower.getEmp_name_s().equals(nameb) && (borrower.getEmp_card_num_s().equals(idb) || borrower
							.getEmp_card_num_s().equals(cardnumb)))) {
				return borrower;
			} else {
				throw new BusinessException("ѡ��ְ�������� " + borrower.getEmp_name_s() + " �����֤ "
						+ borrower.getEmp_card_num_s() + " ���ͬ����������֤�Ų�һ��,�Ƿ����?");
			}
		} catch (BusinessException be) {
			throw be;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return borrower;
	}

	@Override
	public AssistantBorrower findAssistanBorrower(String contractid) throws BusinessException {
		AssistantBorrower assistantBorrower = assistantBorrowerDAO.queryByBorrowerByEmpId(contractid);// ��ѯpl110
		return assistantBorrower;
	}

	@Override
	public List queryByBorrowerByEmpId_yg(String contractid) throws BusinessException {
		List list = assistantBorrowerDAO.queryByBorrowerByEmpId_yg(contractid);// ��ѯpl110
		return list;
	}

	@Override
	public BorrowerAcc findBorrowerAcc(String contractid) throws BusinessException {
		// TODO Auto-generated method stub
		BorrowerAcc borrowerAcc = new BorrowerAcc();
		try {
			borrowerAcc = borrowerAccDAO.queryById(contractid);// ��ѯpl110
			if (borrowerAcc == null) {
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrowerAcc;
	}

	public void setBorrowerAccDAO(BorrowerAccDAO borrowerAccDAO) {
		this.borrowerAccDAO = borrowerAccDAO;
	}

	public void setBorrowerLoanInfoDAO(BorrowerLoanInfoDAO borrowerLoanInfoDAO) {
		this.borrowerLoanInfoDAO = borrowerLoanInfoDAO;
	}

	@Override
	public BorrowerLoanInfo findBorrowerLoanInfo(String contractid) throws BusinessException {
		// TODO Auto-generated method stub
		BorrowerLoanInfo borrowerLoanInfo = new BorrowerLoanInfo();
		try {
			borrowerLoanInfo = borrowerLoanInfoDAO.queryById(contractid);// ��ѯpl110
			if (borrowerLoanInfo == null) {
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrowerLoanInfo;
	}

	@Override
	public Emp findEmpInfo(String empId, String orgId) throws BusinessException {
		// TODO Auto-generated method stub
		Emp emp = new Emp();
		try {
			emp = empDAO.queryByCriterions(empId, orgId);// ��ѯpl110
			if (emp == null) {
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	public void setEmpDAO(EmpDAO empDAO) {
		this.empDAO = empDAO;
	}

	public Borrower chec1(String borrowerEmpId) throws BusinessException {
		// TODO Auto-generated method stub
		Borrower borrower = borrowerDAO.queryByBorrowerByEmpId(borrowerEmpId);// ��ѯpl110
		try {
			if (borrower == null) {
				throw new BusinessException("���˺�ͬ�����ڻ��ͬ״̬����ȷ��");
			}
			if (borrower.getEmpSt() == null) {
				borrower.setEmpSt_("");
			} else {
				borrower.setEmpSt_(BusiTools.getBusiValue(Integer.parseInt(borrower.getEmpSt()),
						BusiConst.OLDPAYMENTSTATE));
			}
		} catch (BusinessException be) {
			throw be;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrower;

	}

	@Override
	public void check(String empId, String orgId, String contractid, String type) throws BusinessException {
		// TODO Auto-generated method stub
		Borrower borrower = borrowerDAO.queryById(contractid);// ��ѯpl110
		Emp emp = new Emp();
		try {
			emp = empDAO.queryByCriterions(empId, orgId);// ��ѯpl110
			if (emp.getEmpInfo().getCardNum() != null && emp.getEmpInfo().getName() != null) {
				if (!emp.getEmpInfo().getCardNum().equals(borrower.getCardNum())) {
					if ("1".equals(type)) {
						throw new BusinessException("����˹����ϵͳ�е����֤������ϵͳ�е����֤�Ų�һ�£�");
					} else {
						throw new BusinessException("�������˹����ϵͳ�е����֤������ϵͳ�е����֤�Ų�һ�£�");
					}

				}
				if (!emp.getEmpInfo().getName().equals(borrower.getBorrowerName())) {
					if ("��".equals(type)) {
						throw new BusinessException("����˹����ϵͳ�е����֤������ϵͳ�е�����һ�£�");
					} else {
						throw new BusinessException("�������˹����ϵͳ�е����֤������ϵͳ�е�����һ�£�");
					}

				}
			}
		} catch (BusinessException be) {
			be.printStackTrace();
			throw be;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public AssistantBorrower findAssistanBorrower_1(String id) throws BusinessException {
		// TODO Auto-generated method stub
		AssistantBorrower assistantBorrower = assistantBorrowerDAO.queryByBorrowerByEmpId(id);// ��ѯpl110
		try {

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assistantBorrower;
	}

	@Override
	public String findXieYINum(String office, String year) throws BusinessException {
		// TODO Auto-generated method stub
		String id = "";
		try {
			id = collLoanbackParaDAO.findXieYINum(office, year);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public String findContractExsit(String contractid) throws BusinessException {
		// TODO Auto-generated method stub
		Integer count = new Integer(0);
		try {
			count = fundloanInfoDAO.LoanreturnedbyfundTbCheck_wsh(contractid);
			if (count.intValue() > 0) {
				throw new BusinessException("ѡ��ĺ�ͬ�����ڰ����л����Ѿ���ǩ!");
			}
		} catch (BusinessException be) {
			throw be;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count + "";
	}

	@Override
	public void updateLoanreturnedbyfund_1(String contractId, String date_temp) throws Exception {
		try {
			fundloanInfoDAO.updateFundloanInfoStatus_1(contractId, date_temp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// ��ݺ�ͬ��Ų�ѯ���´�
	@Override
	public String findOffice(String contractId) throws BusinessException {
		// TODO Auto-generated method stub
		String id = "";
		try {
			id = fundloanInfoDAO.queryOfficeByContractId(contractId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public String find_pl201_CorpusInterest(String contractid, String biz_date) throws BusinessException {
		// TODO Auto-generated method stub
		String id = "";
		String pl111_date = "";
		try {
			pl111_date = fundloanInfoDAO.find_pl111_loan_day(contractid);
			if (new Integer(pl111_date).intValue() >= new Integer(biz_date.substring(6, 8)).intValue()) {
				id = fundloanInfoDAO.find_pl201_CorpusInterest(contractid, biz_date);
			} else {

				id = fundloanInfoDAO.find_pl201_CorpusInterest_nextmonth(contractid, biz_date);
			}
			if (Float.parseFloat(id) == 0)
				id = fundloanInfoDAO.find_pl201_CorpusInterest_nextmonth(contractid, biz_date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public String find_pl201_CorpusInterest_other(final String contractId, final String biz_date)
			throws BusinessException {
		// TODO Auto-generated method stub
		String id = "";
		try {
			id = fundloanInfoDAO.find_pl201_CorpusInterest_other(contractId, biz_date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public String[] findCount(Pagination pagination, SecurityInfo securityInfo) throws BusinessException {
		// TODO Auto-generated method stub
		String str[] = new String[2];
		try {
			String contractId = (String) pagination.getQueryCriterions().get("contractId");
			String borrowerName = (String) pagination.getQueryCriterions().get("borrowerName");
			String cardNum = (String) pagination.getQueryCriterions().get("cardNum");
			String xieYiNum = (String) pagination.getQueryCriterions().get("xieYiNum");
			String assiBorrowerName = (String) pagination.getQueryCriterions().get("assiBorrowerName");
			String assiBorrowerCardNum = (String) pagination.getQueryCriterions().get("assiBorrowerCardNum");
			String orgId = (String) pagination.getQueryCriterions().get("orgId");
			String empId = (String) pagination.getQueryCriterions().get("empId");
			String startDate = (String) pagination.getQueryCriterions().get("startDate");
			String endDate = (String) pagination.getQueryCriterions().get("endDate");
			String loanBankId = (String) pagination.getQueryCriterions().get("loanBankId");
			String orderBy = pagination.getOrderBy();
			String orderother = pagination.getOrderother();
			String sta = (String) pagination.getQueryCriterions().get("sta");
			String str1 = fundloanInfoDAO.findFundloanCountList_count(contractId, borrowerName, cardNum, xieYiNum,
					assiBorrowerName, assiBorrowerCardNum, orgId, empId, startDate, endDate, loanBankId, orderBy,
					orderother, sta, securityInfo);
			if (str1 != null) {
				str[0] = str1;
			}
			String str2 = fundloanInfoDAO.findFundloanCountList_count_1(contractId, borrowerName, cardNum, xieYiNum,
					assiBorrowerName, assiBorrowerCardNum, orgId, empId, startDate, endDate, loanBankId, orderBy,
					orderother, sta, securityInfo);
			if (str2 != null) {
				str[1] = str2;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public AssistantBorrower showLoanapplyInfoByAssiBorrowerEmpId(String assiborrowerEmpId) throws BusinessException {
		// TODO Auto-generated method stub
		AssistantBorrower assistantBorrower = new AssistantBorrower();
		String contarid = assistantBorrowerDAO.queryByBorrowerByEmpId_1(assiborrowerEmpId);// ��ѯpl110
		try {
			if (contarid == null || "".equals(contarid)) {
				throw new BusinessException("���˺�ͬ�����ڻ��ͬ״̬����ȷ��");
				// }
				// if (assistantBorrower.getEmpSt() == null) {
				// assistantBorrower.setEmpSt("");
				// } else {
				// assistantBorrower.setEmpSt(BusiTools.getBusiValue(Integer.parseInt(assistantBorrower
				// .getEmpSt()), BusiConst.OLDPAYMENTSTATE));
			} else {
				assistantBorrower.setContractId(contarid);
			}
		} catch (BusinessException be) {
			throw be;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assistantBorrower;
	}

	@Override
	public String find(String contractId) throws BusinessException {
		// TODO Auto-generated method stub
		return restoreLoanDAO.find(contractId);
	}

	public void setRestoreLoanDAO(RestoreLoanDAO restoreLoanDAO) {
		this.restoreLoanDAO = restoreLoanDAO;
	}

	@Override
	public String findHouseAddr(String contractid) throws BusinessException {

		String status = "";
		try {

			status = fundloanInfoDAO.findHouseAddr(contractid);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	@Override
	public String findBankName(String loanbankid) throws BusinessException {

		String status = "";
		try {

			CollBank dto = collBankDAO.getCollBankByCollBankid(loanbankid);
			status = dto.getCollBankName();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	public void setCollBankDAO(CollBankDAO collBankDAO) {
		this.collBankDAO = collBankDAO;
	}

	public void setXieYiNumDAO(XieYiNumDAO xieYiNumDAO) {
		this.xieYiNumDAO = xieYiNumDAO;
	}
}
