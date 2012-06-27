package org.xpup.hafmis.sysloan.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.Validate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.xpup.hafmis.common.util.BusiTools;
import org.xpup.hafmis.orgstrct.dto.SecurityInfo;
import org.xpup.hafmis.sysloan.common.domain.entity.FundloanInfo;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.EmpinfoDto;
import org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.LoanreturnedbyfundTbDTO;

/**
 * 公积金还贷信息表PL400
 * 
 * @author wsh 2007-12-19
 */
public class FundloanInfoDAO extends HibernateDaoSupport {

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	public FundloanInfo queryById(Serializable id) {
		Validate.notNull(id);
		return (FundloanInfo) getHibernateTemplate().get(FundloanInfo.class, id);
	}

	/**
	 * 插入记录
	 * 
	 * @param fundloanInfo
	 * @return
	 */
	public Serializable insert(FundloanInfo fundloanInfo) {
		Serializable id = null;
		try {
			Validate.notNull(fundloanInfo);
			id = getHibernateTemplate().save(fundloanInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 公积金还贷
	 * 
	 * @author 郭婧平 2007-12-19 根据pl400的贷款账号查询状态为正常的职工编号和还款顺序 查询条件：loanKouAcc
	 */
	public List queryEmpIdByLoanKouAcc(final String loanKouAcc) {
		List list = new ArrayList();
		list = getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select fundloanInfo.empId,fundloanInfo.seq,fundloanInfo.orgId,fundloanInfo.empName "
						+ "from FundloanInfo fundloanInfo "
						+ "where fundloanInfo.reserveaB=1 and fundloanInfo.loanKouAcc=? order by fundloanInfo.loanKouAcc,fundloanInfo.seq ";
				// order by 的顺序必须要有,不能改变,做为判断是主借款人或是辅助借款人扣款使用.
				Query query = session.createQuery(hql);
				query.setString(0, loanKouAcc);
				Iterator it = query.list().iterator();
				List temp_list = new ArrayList();
				Object obj[] = null;
				while (it.hasNext()) {
					obj = (Object[]) it.next();
					if (obj != null) {
						FundloanInfo fundloanInfo = new FundloanInfo();
						if (obj[0] != null) {
							fundloanInfo.setEmpId(obj[0].toString());
						}
						if (obj[1] != null) {
							fundloanInfo.setSeq(obj[1].toString());
						}
						if (obj[2] != null) {
							fundloanInfo.setOrgId(obj[2].toString());
						}

						if (obj[3] != null) {
							fundloanInfo.setEmpName(obj[3].toString());
						}

						temp_list.add(fundloanInfo);
					}
				}
				return temp_list;
			}
		});
		return list;
	}

	/**
	 * 公积金还贷
	 * 
	 * @author 郭婧平 2007-12-19 根据pl400的贷款账号联合aa306和aa307表查询aa306的提取状态和单位编号
	 *         查询条件：loanKouAcc
	 */
	public Object[] queryByLoanKouAcc(final String empId, final String batchNum) {
		Object[] obj = new Object[2];
		obj = (Object[]) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select aa306.pick_satatus,aa306.org_id " + "from AA306 aa306,AA307 aa307 "
						+ "where aa307.pickhead_id=aa306.id " + "and aa307.emp_id=? and aa306.batch_num=? ";
				Query query = session.createSQLQuery(hql);
				query.setString(0, empId);
				query.setString(1, batchNum);
				return query.uniqueResult();
			}
		});
		return obj;
	}

	/**
	 * 王硕 公积金还贷签订合同
	 * 
	 * @param orderBy
	 * @param orderother
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List findFundloanList(final String cotractId, final String borrowerName, final String cardNum,
			final String orderBy, final String orderother, final int start, final int pageSize, final int page,
			final String xieYiNum, final String assiBorrowerName, final String assiBorrowerCardNum, final String orgId,
			final String empId, final String startDate, final String endDate, final String loanBankId,
			final String sta, final SecurityInfo securityInfo, final String begstop, final String endstop) {
		List list = null;
		try {
			list = getHibernateTemplate().executeFind(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "";
					Vector parameters = new Vector();
					String criterion = "";
					if ((assiBorrowerName == null || "".equals(assiBorrowerName))
							&& (assiBorrowerCardNum == null || "".equals(assiBorrowerCardNum))) {
						if (cotractId != null && !"".equals(cotractId)) {
							criterion += " a.contract_id like ? and ";
							parameters.add("%" + cotractId + "%");
						}
						if (borrowerName != null && !"".equals(borrowerName)) {
							criterion += " a.emp_name like ? and ";
							parameters.add("%" + borrowerName + "%");
						}
						if (cardNum != null && !"".equals(cardNum)) {
							criterion += " a.card_num like ? and ";
							parameters.add("%" + cardNum + "%");
						}
						if (xieYiNum != null && !"".equals(xieYiNum)) {
							criterion += " a.reservea_c like ? and ";
							parameters.add("%" + xieYiNum + "%");
						}
						if (orgId != null && !"".equals(orgId)) {
							criterion += " a.org_id= ? and ";
							parameters.add(new Integer(orgId));
						}
						if (empId != null && !"".equals(empId)) {
							criterion += " a.emp_id= ? and ";
							parameters.add(new Integer(empId));
						}
						if (sta != null && !"".equals(sta)) {
							criterion += " a.reservea_b= ? and ";
							parameters.add(sta);
						}
						if (startDate != null && !"".equals(startDate.trim()) && endDate != null
								&& !"".equals(endDate.trim())) {// 有开始日期结束日期
							criterion += " to_number(a.reservea_a) >=? and to_number(a.reservea_a) <=? and ";
							parameters.add(new Integer(startDate.trim()));
							parameters.add(new Integer(endDate.trim()));
						}
						if (startDate != null && !"".equals(startDate.trim())
								&& (endDate == null || "".equals(endDate.trim()))) {// 有开始日期无结束日期
							criterion += " to_number(a.reservea_a) >= ? and ";
							parameters.add(new Integer(startDate.trim()));
						}
						if (endDate != null && !"".equals(endDate.trim())
								&& (startDate == null || "".equals(startDate.trim()))) {// 无开始日期有结束日期
							criterion += " to_number(a.reservea_a) <= ? and ";
							parameters.add(new Integer(endDate.trim()));
						}
						if (begstop != null && !"".equals(begstop.trim())) {
							criterion += " a.date_stop >= ? and ";
							parameters.add(begstop);
						}
						if (endstop != null && !"".equals(endstop.trim())) {
							criterion += " a.date_stop <= ? and ";
							parameters.add(endstop);
						}
						if (loanBankId != null && !"".equals(loanBankId)) {
							criterion += " p111.loan_bank_id = ? and ";
							parameters.add(loanBankId);
						}
						hql = "select a.contract_id contractid, a.emp_name empname, a.card_num cardnum,a.org_id orgid,c.name orgname,a.seq s,a.reservea_c xieyinum,a.reservea_a riqi,a.reservea_b sta,a.id,a.date_stop "
								+ "from pl400 a, pl110 p110, pl111 p111, aa001 b, ba001 c "
								+ "where a.org_id = b.id and b.orginfo_id = c.id and a.status = '0' and a.reservea_b<>'3'  "
								+ "and p110.contract_id = p111.contract_id "
								+ "and p110.contract_id = a.contract_id and p111.loan_bank_id "
								+ securityInfo.getDkSecuritySQL();
					} else {
						hql = "select a.contract_id contractid, a.emp_name empname, a.card_num cardnum,a.org_id orgid,c.name orgname,a.seq s,a.reservea_c xieyinum,a.reservea_a riqi,a.reservea_b sta,a.id,a.date_stop "
								+ "from pl400 a, pl110 p110, pl111 p111, aa001 b, ba001 c ,pl113 p113 "
								+ "where a.org_id = b.id and b.orginfo_id = c.id and p113.contract_id=a.contract_id and a.status = '0' and a.reservea_b<>'3' "
								+ "and p110.contract_id = p111.contract_id "
								+ "and p110.contract_id = a.contract_id and p111.loan_bank_id "
								+ securityInfo.getDkSecuritySQL();
						if (cotractId != null && !"".equals(cotractId)) {
							criterion += " a.contract_id like ? and ";
							parameters.add("%" + cotractId + "%");
						}
						if (assiBorrowerName != null && !"".equals(assiBorrowerName)) {
							criterion += " a.emp_name like ? and a.seq!=0 and ";
							parameters.add("%" + assiBorrowerName + "%");
						}
						if (assiBorrowerCardNum != null && !"".equals(assiBorrowerCardNum)) {
							criterion += " a.card_num like ? and a.seq!=0 and ";
							parameters.add("%" + assiBorrowerCardNum + "%");
						}
						if (borrowerName != null && !"".equals(borrowerName)) {
							criterion += " a.emp_name like ? and ";
							parameters.add("%" + borrowerName + "%");
						}
						if (cardNum != null && !"".equals(cardNum)) {
							criterion += " a.card_num like ? and ";
							parameters.add("%" + cardNum + "%");
						}
						if (xieYiNum != null && !"".equals(xieYiNum)) {
							criterion += " a.reservea_c like ? and ";
							parameters.add("%" + xieYiNum + "%");
						}
						if (orgId != null && !"".equals(orgId)) {
							criterion += " a.org_id= ? and ";
							parameters.add(new Integer(orgId));
						}
						if (empId != null && !"".equals(empId)) {
							criterion += " a.emp_id= ? and ";
							parameters.add(new Integer(empId));
						}
						if (sta != null && !"".equals(sta)) {
							criterion += " a.reservea_b= ? and ";
							parameters.add(sta);
						}
						if (startDate != null && !"".equals(startDate.trim()) && endDate != null
								&& !"".equals(endDate.trim())) {// 有开始日期结束日期
							criterion += " to_number(a.reservea_a) >=? and to_number(a.reservea_a) <=? and ";
							parameters.add(new Integer(startDate.trim()));
							parameters.add(new Integer(endDate.trim()));
						}
						if (startDate != null && !"".equals(startDate.trim())
								&& (endDate == null || "".equals(endDate.trim()))) {// 有开始日期无结束日期
							criterion += " to_number(a.reservea_a) >= ? and ";
							parameters.add(new Integer(startDate.trim()));
						}
						if (endDate != null && !"".equals(endDate.trim())
								&& (startDate == null || "".equals(startDate.trim()))) {// 无开始日期有结束日期
							criterion += " to_number(a.reservea_a) <= ? and ";
							parameters.add(new Integer(endDate.trim()));
						}
						if (begstop != null && !"".equals(begstop.trim())) {
							criterion += " a.date_stop >= ? and ";
							parameters.add(begstop);
						}
						if (endstop != null && !"".equals(endstop.trim())) {
							criterion += " a.date_stop <= ? and ";
							parameters.add(endstop);
						}
						if (loanBankId != null && !"".equals(loanBankId)) {
							criterion += " p111.loan_bank_id = ? and ";
							parameters.add(loanBankId);
						}
					}

					if (criterion.length() != 0)
						criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));
					String ob = orderBy;
					if (ob == null)
						ob = " a.reservea_a  DESC,a.reservea_c ";

					String od = orderother;
					if (od == null)
						od = "desc";
					hql = hql + criterion + " order by " + ob + " " + od;
					Query query = session.createSQLQuery(hql);
					for (int i = 0; i < parameters.size(); i++) {
						query.setParameter(i, parameters.get(i));
					}
					query.setFirstResult(start);
					if (pageSize > 0)
						query.setMaxResults(pageSize);
					List temp_list1 = new ArrayList();
					temp_list1 = query.list();
					if (temp_list1 == null || temp_list1.size() == 0) {
						query.setFirstResult(pageSize * (page - 2));
						if (pageSize > 0)
							query.setMaxResults(pageSize);
						temp_list1 = query.list();
					}
					Iterator it = temp_list1.iterator();

					List temp_list = new ArrayList();
					Object obj[] = null;
					while (it.hasNext()) {
						obj = (Object[]) it.next();
						if (obj != null) {
							LoanreturnedbyfundTbDTO loanreturnedbyfundTbDTO = new LoanreturnedbyfundTbDTO();
							if (obj[0] != null) {
								loanreturnedbyfundTbDTO.setContractId(obj[0].toString());
							}
							if (obj[1] != null) {
								loanreturnedbyfundTbDTO.setBorrowerName(obj[1].toString());
							}

							if (obj[2] != null) {
								loanreturnedbyfundTbDTO.setCardNum(obj[2].toString());
							}
							if (obj[3] != null) {
								loanreturnedbyfundTbDTO.setOrgId(BusiTools.convertTenNumber(obj[3].toString()));
							}
							if (obj[4] != null) {
								loanreturnedbyfundTbDTO.setOrgName(obj[4].toString());
							}
							if (obj[5] != null) {
								if (obj[5].toString().equals("0")) {
									loanreturnedbyfundTbDTO.setSeq("借款人");
								} else {
									loanreturnedbyfundTbDTO.setSeq("共同借款人");
								}
							}
							if (obj[6] != null) {
								loanreturnedbyfundTbDTO.setXieYuNum(obj[6].toString());
							}
							if (obj[7] != null) {
								loanreturnedbyfundTbDTO.setRiQi(obj[7].toString());
							}
							if (obj[8] != null) {
								if ("1".equals((obj[8].toString()))) {
									loanreturnedbyfundTbDTO.setStatus("正常");
								} else {
									if ("2".equals((obj[8].toString()))) {
										loanreturnedbyfundTbDTO.setStatus("未启用");
									} else {
										loanreturnedbyfundTbDTO.setStatus("撤消");
									}
								}
							}
							if (obj[9] != null) {
								loanreturnedbyfundTbDTO.setId(obj[9].toString());
							}
							if (obj[10] != null) {
								loanreturnedbyfundTbDTO.setRiQia(obj[10].toString());
							}
							temp_list.add(loanreturnedbyfundTbDTO);

						}

					}
					return temp_list;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 王硕 公积金还贷签订合同
	 * 
	 * @param orderBy
	 * @param orderother
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List findFundloanCountList(final String cotractId, final String borrowerName, final String cardNum,
			final String xieYiNum, final String assiBorrowerName, final String assiBorrowerCardNum, final String orgId,
			final String empId, final String startDate, final String endDate, final String loanBankId,
			final String orderBy, final String orderother, final String sta, final SecurityInfo securityInfo,
			final String begstop, final String endstop) {
		List list = null;
		try {
			list = getHibernateTemplate().executeFind(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "";
					Vector parameters = new Vector();
					String criterion = "";
					if ((assiBorrowerName == null || "".equals(assiBorrowerName))
							&& (assiBorrowerCardNum == null || "".equals(assiBorrowerCardNum))) {
						if (cotractId != null && !"".equals(cotractId)) {
							criterion += " a.contract_id like ? and ";
							parameters.add("%" + cotractId + "%");
						}
						if (borrowerName != null && !"".equals(borrowerName)) {
							criterion += " a.emp_name like ? and ";
							parameters.add("%" + borrowerName + "%");
						}
						if (cardNum != null && !"".equals(cardNum)) {
							criterion += " a.card_num like ? and ";
							parameters.add("%" + cardNum + "%");
						}
						if (xieYiNum != null && !"".equals(xieYiNum)) {
							criterion += " a.reservea_c like ? and ";
							parameters.add("%" + xieYiNum + "%");
						}
						if (orgId != null && !"".equals(orgId)) {
							criterion += " a.org_id= ? and ";
							parameters.add(new Integer(orgId));
						}
						if (empId != null && !"".equals(empId)) {
							criterion += " a.emp_id= ? and ";
							parameters.add(new Integer(empId));
						}
						if (sta != null && !"".equals(sta)) {
							criterion += " a.reservea_b= ? and ";
							parameters.add(sta);
						}
						if (startDate != null && !"".equals(startDate.trim()) && endDate != null
								&& !"".equals(endDate.trim())) {// 有开始日期结束日期
							criterion += " to_number(a.reservea_a) >=? and to_number(a.reservea_a) <=? and ";
							parameters.add(new Integer(startDate.trim()));
							parameters.add(new Integer(endDate.trim()));
						}
						if (startDate != null && !"".equals(startDate.trim())
								&& (endDate == null || "".equals(endDate.trim()))) {// 有开始日期无结束日期
							criterion += " to_number(a.reservea_a) >= ? and ";
							parameters.add(new Integer(startDate.trim()));
						}
						if (endDate != null && !"".equals(endDate.trim())
								&& (startDate == null || "".equals(startDate.trim()))) {// 无开始日期有结束日期
							criterion += " to_number(a.reservea_a) <= ? and ";
							parameters.add(new Integer(endDate.trim()));
						}
						if (endDate != null && !"".equals(endDate.trim())
								&& (startDate == null || "".equals(startDate.trim()))) {// 无开始日期有结束日期
							criterion += " to_number(a.reservea_a) <= ? and ";
							parameters.add(new Integer(endDate.trim()));
						}
						if (begstop != null && !"".equals(begstop.trim())) {
							criterion += " a.date_stop >= ? and ";
							parameters.add(begstop);
						}
						if (endstop != null && !"".equals(endstop.trim())) {
							criterion += " a.date_stop <= ? and ";
							parameters.add(endstop);
						}
						if (loanBankId != null && !"".equals(loanBankId)) {
							criterion += " p111.loan_bank_id = ? and ";
							parameters.add(loanBankId);
						}
						hql = "select a.contract_id contractid, a.emp_name empname, a.card_num cardnum,a.org_id orgid,c.name orgname,a.seq s,a.reservea_c xieyinum,a.reservea_a riqi,a.reservea_b sta "
								+ "from pl400 a,pl110 p110, pl111 p111, aa001 b, ba001 c "
								+ "where a.org_id = b.id and b.orginfo_id = c.id and a.status = '0' and a.reservea_b<>'3' "
								+ "and p110.contract_id = p111.contract_id "
								+ "and p110.contract_id = a.contract_id and p111.loan_bank_id "
								+ securityInfo.getDkSecuritySQL();
					} else {
						hql = "select a.contract_id contractid, a.emp_name empname, a.card_num cardnum,a.org_id orgid,c.name orgname,a.seq s,a.reservea_c xieyinum,a.reservea_a riqi,a.reservea_b sta "
								+ "from pl400 a, pl110 p110, pl111 p111, aa001 b, ba001 c ,pl113 p113 "
								+ "where a.org_id = b.id and b.orginfo_id = c.id and p113.contract_id=a.contract_id and a.status = '0' and a.reservea_b<>'3' "
								+ "and p110.contract_id = p111.contract_id "
								+ "and p110.contract_id = a.contract_id and p111.loan_bank_id "
								+ securityInfo.getDkSecuritySQL();
						if (cotractId != null && !"".equals(cotractId)) {
							criterion += " a.contract_id= ? and ";
							parameters.add(cotractId);
						}
						if (assiBorrowerName != null && !"".equals(assiBorrowerName)) {
							criterion += " a.emp_name= ? and a.seq!=0 and ";
							parameters.add(assiBorrowerName);
						}
						if (assiBorrowerCardNum != null && !"".equals(assiBorrowerCardNum)) {
							criterion += " a.card_num= ? and a.seq!=0 and ";
							parameters.add(assiBorrowerCardNum);
						}
						if (borrowerName != null && !"".equals(borrowerName)) {
							criterion += " a.emp_name= ? and ";
							parameters.add(borrowerName);
						}
						if (cardNum != null && !"".equals(cardNum)) {
							criterion += " a.card_num= ? and ";
							parameters.add(cardNum);
						}
						if (xieYiNum != null && !"".equals(xieYiNum)) {
							criterion += " a.reservea_c= ? and ";
							parameters.add(xieYiNum);
						}
						if (orgId != null && !"".equals(orgId)) {
							criterion += " a.org_id= ? and ";
							parameters.add(orgId);
						}
						if (empId != null && !"".equals(empId)) {
							criterion += " a.emp_id= ? and ";
							parameters.add(empId);
						}
						if (sta != null && !"".equals(sta)) {
							criterion += " a.reservea_b= ? and ";
							parameters.add(sta);
						}
						if (startDate != null && !"".equals(startDate.trim()) && endDate != null
								&& !"".equals(endDate.trim())) {// 有开始日期结束日期
							criterion += " to_number(a.reservea_a) >=? and to_number(a.reservea_a) <=? and ";
							parameters.add(new Integer(startDate.trim()));
							parameters.add(new Integer(endDate.trim()));
						}
						if (startDate != null && !"".equals(startDate.trim())
								&& (endDate == null || "".equals(endDate.trim()))) {// 有开始日期无结束日期
							criterion += " to_number(a.reservea_a) >= ? and ";
							parameters.add(new Integer(startDate.trim()));
						}
						if (endDate != null && !"".equals(endDate.trim())
								&& (startDate == null || "".equals(startDate.trim()))) {// 无开始日期有结束日期
							criterion += " to_number(a.reservea_a) <= ? and ";
							parameters.add(new Integer(endDate.trim()));
						}
						if (begstop != null && !"".equals(begstop.trim())) {
							criterion += " a.date_stop >= ? and ";
							parameters.add(begstop);
						}
						if (endstop != null && !"".equals(endstop.trim())) {
							criterion += " a.date_stop <= ? and ";
							parameters.add(endstop);
						}
						if (loanBankId != null && !"".equals(loanBankId)) {
							criterion += " p111.loan_bank_id = ? and ";
							parameters.add(loanBankId);
						}
					}

					if (criterion.length() != 0)
						criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));
					String ob = orderBy;
					if (ob == null)
						ob = " a.id ";

					String od = orderother;
					if (od == null)
						od = "desc";
					hql = hql + criterion + " order by " + ob + " " + od;
					Query query = session.createSQLQuery(hql);
					for (int i = 0; i < parameters.size(); i++) {
						query.setParameter(i, parameters.get(i));
					}
					Iterator it = query.list().iterator();
					List temp_list = new ArrayList();
					Object obj[] = null;
					while (it.hasNext()) {
						obj = (Object[]) it.next();
						if (obj != null) {
							LoanreturnedbyfundTbDTO loanreturnedbyfundTbDTO = new LoanreturnedbyfundTbDTO();
							if (obj[0] != null) {
								loanreturnedbyfundTbDTO.setContractId(obj[0].toString());
							}
							if (obj[1] != null) {
								loanreturnedbyfundTbDTO.setBorrowerName(obj[1].toString());
							}

							if (obj[2] != null) {
								loanreturnedbyfundTbDTO.setCardNum(obj[2].toString());
							}
							if (obj[3] != null) {
								loanreturnedbyfundTbDTO.setOrgId(BusiTools.convertTenNumber(obj[3].toString()));
							}
							if (obj[4] != null) {
								loanreturnedbyfundTbDTO.setOrgName(obj[4].toString());
							}
							if (obj[5] != null) {
								if (obj[5].toString().equals("0")) {

									loanreturnedbyfundTbDTO.setSeq("借款人");
								} else {

									loanreturnedbyfundTbDTO.setSeq("共同借款人");
								}
							}
							if (obj[6] != null) {
								loanreturnedbyfundTbDTO.setXieYuNum(obj[6].toString());
							}
							if (obj[7] != null) {
								loanreturnedbyfundTbDTO.setRiQi(obj[7].toString());
							}
							if (obj[8] != null) {
								if ("1".equals((obj[8].toString()))) {
									loanreturnedbyfundTbDTO.setStatus("正常");
								} else {
									if ("2".equals((obj[8].toString()))) {
										loanreturnedbyfundTbDTO.setStatus("未启用");
									} else {
										loanreturnedbyfundTbDTO.setStatus("撤销");
									}
								}
							}
							temp_list.add(loanreturnedbyfundTbDTO);
						}

					}
					return temp_list;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public String findFundloanCountList_count(final String cotractId, final String borrowerName, final String cardNum,
			final String xieYiNum, final String assiBorrowerName, final String assiBorrowerCardNum, final String orgId,
			final String empId, final String startDate, final String endDate, final String loanBankId,
			final String orderBy, final String orderother, final String sta, final SecurityInfo securityInfo) {
		String str = null;
		try {
			str = (String) getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "";
					Vector parameters = new Vector();
					String criterion = "";
					if ((assiBorrowerName == null || !"".equals(assiBorrowerName))
							&& (assiBorrowerCardNum == null || !"".equals(assiBorrowerCardNum))) {
						if (cotractId != null && !"".equals(cotractId)) {
							criterion += " a.contract_id like ? and ";
							parameters.add("%" + cotractId + "%");
						}
						if (borrowerName != null && !"".equals(borrowerName)) {
							criterion += " a.emp_name like ? and ";
							parameters.add("%" + borrowerName + "%");
						}
						if (cardNum != null && !"".equals(cardNum)) {
							criterion += " a.card_num like ? and ";
							parameters.add("%" + cardNum + "%");
						}
						if (xieYiNum != null && !"".equals(xieYiNum)) {
							criterion += " a.reservea_c like ? and ";
							parameters.add("%" + xieYiNum + "%");
						}
						if (orgId != null && !"".equals(orgId)) {
							criterion += " a.org_id= ? and ";
							parameters.add(new Integer(orgId));
						}
						if (empId != null && !"".equals(empId)) {
							criterion += " a.emp_id= ? and ";
							parameters.add(new Integer(empId));
						}
						if (sta != null && !"".equals(sta)) {
							criterion += " a.reservea_b= ? and ";
							parameters.add(sta);
						}
						if (startDate != null && !"".equals(startDate.trim()) && endDate != null
								&& !"".equals(endDate.trim())) {// 有开始日期结束日期
							criterion += " to_number(a.reservea_a) >=? and to_number(a.reservea_a) <=? and ";
							parameters.add(new Integer(startDate.trim()));
							parameters.add(new Integer(endDate.trim()));
						}
						if (startDate != null && !"".equals(startDate.trim())
								&& (endDate == null || "".equals(endDate.trim()))) {// 有开始日期无结束日期
							criterion += " to_number(a.reservea_a) >= ? and ";
							parameters.add(new Integer(startDate.trim()));
						}
						if (endDate != null && !"".equals(endDate.trim())
								&& (startDate == null || "".equals(startDate.trim()))) {// 无开始日期有结束日期
							criterion += " to_number(a.reservea_a) <= ? and ";
							parameters.add(new Integer(endDate.trim()));
						}
						if (loanBankId != null && !"".equals(loanBankId)) {
							criterion += " p111.loan_bank_id = ? and ";
							parameters.add(loanBankId);
						}
						hql = "select count(distinct a.contract_id) "
								+ "from pl400 a, pl110 p110, pl111 p111, aa001 b, ba001 c "
								+ "where a.org_id = b.id and b.orginfo_id = c.id and a.status = '0' and a.reservea_b='1' "
								+ "and p110.contract_id = p111.contract_id "
								+ "and p110.contract_id = a.contract_id and p110.office "
								+ securityInfo.getOfficeSecuritySQL();
					} else {
						hql = "select count(distinct a.contract_id) "
								+ "from pl400 a, pl110 p110, pl111 p111, aa001 b, ba001 c ,pl113 p113 "
								+ "where a.org_id = b.id and b.orginfo_id = c.id and p113.contract_id=a.contract_id and a.status = '0' and a.reservea_b='1' "
								+ "and p110.contract_id = p111.contract_id "
								+ "and p110.contract_id = a.contract_id and p110.office "
								+ securityInfo.getOfficeSecuritySQL();
						if (cotractId != null && !"".equals(cotractId)) {
							criterion += " a.contract_id= ? and ";
							parameters.add(cotractId);
						}
						if (borrowerName != null && !"".equals(borrowerName)) {
							criterion += " a.emp_name= ? and ";
							parameters.add(borrowerName);
						}
						if (cardNum != null && !"".equals(cardNum)) {
							criterion += " a.card_num= ? and ";
							parameters.add(cardNum);
						}
						if (xieYiNum != null && !"".equals(xieYiNum)) {
							criterion += " a.reservea_c= ? and ";
							parameters.add(xieYiNum);
						}
						if (orgId != null && !"".equals(orgId)) {
							criterion += " a.org_id= ? and ";
							parameters.add(orgId);
						}
						if (empId != null && !"".equals(empId)) {
							criterion += " a.emp_id= ? and ";
							parameters.add(empId);
						}
						if (sta != null && !"".equals(sta)) {
							criterion += " a.reservea_b= ? and ";
							parameters.add(sta);
						}
						if (startDate != null && !"".equals(startDate.trim()) && endDate != null
								&& !"".equals(endDate.trim())) {// 有开始日期结束日期
							criterion += " to_number(a.reservea_a) >=? and to_number(a.reservea_a) <=? and ";
							parameters.add(new Integer(startDate.trim()));
							parameters.add(new Integer(endDate.trim()));
						}
						if (startDate != null && !"".equals(startDate.trim())
								&& (endDate == null || "".equals(endDate.trim()))) {// 有开始日期无结束日期
							criterion += " to_number(a.reservea_a) >= ? and ";
							parameters.add(new Integer(startDate.trim()));
						}
						if (endDate != null && !"".equals(endDate.trim())
								&& (startDate == null || "".equals(startDate.trim()))) {// 无开始日期有结束日期
							criterion += " to_number(a.reservea_a) <= ? and ";
							parameters.add(new Integer(endDate.trim()));
						}
						if (loanBankId != null && !"".equals(loanBankId)) {
							criterion += " p111.loan_bank_id = ? and ";
							parameters.add(loanBankId);
						}
					}

					if (criterion.length() != 0)
						criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));
					String ob = orderBy;
					if (ob == null)
						ob = " a.id ";

					String od = orderother;
					if (od == null)
						od = "desc";
					hql = hql + criterion + " order by " + ob + " " + od;
					Query query = session.createSQLQuery(hql);
					for (int i = 0; i < parameters.size(); i++) {
						query.setParameter(i, parameters.get(i));
					}

					return query.uniqueResult().toString();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	public String findFundloanCountList_count_1(final String cotractId, final String borrowerName,
			final String cardNum, final String xieYiNum, final String assiBorrowerName,
			final String assiBorrowerCardNum, final String orgId, final String empId, final String startDate,
			final String endDate, final String loanBankId, final String orderBy, final String orderother,
			final String sta, final SecurityInfo securityInfo) {
		String str = null;
		try {
			str = (String) getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "";
					Vector parameters = new Vector();
					String criterion = "";
					//
					if ((assiBorrowerName == null || !"".equals(assiBorrowerName))
							&& (assiBorrowerCardNum == null || !"".equals(assiBorrowerCardNum))) {
						if (cotractId != null && !"".equals(cotractId)) {
							criterion += " a.contract_id like ? and ";
							parameters.add("%" + cotractId + "%");
						}
						if (borrowerName != null && !"".equals(borrowerName)) {
							criterion += " a.emp_name like ? and ";
							parameters.add("%" + borrowerName + "%");
						}
						if (cardNum != null && !"".equals(cardNum)) {
							criterion += " a.card_num like ? and ";
							parameters.add("%" + cardNum + "%");
						}
						if (xieYiNum != null && !"".equals(xieYiNum)) {
							criterion += " a.reservea_c like ? and ";
							parameters.add("%" + xieYiNum + "%");
						}
						if (orgId != null && !"".equals(orgId)) {
							criterion += " a.org_id= ? and ";
							parameters.add(new Integer(orgId));
						}
						if (empId != null && !"".equals(empId)) {
							criterion += " a.emp_id= ? and ";
							parameters.add(new Integer(empId));
						}
						if (sta != null && !"".equals(sta)) {
							criterion += " a.reservea_b= ? and ";
							parameters.add(sta);
						}
						if (startDate != null && !"".equals(startDate.trim()) && endDate != null
								&& !"".equals(endDate.trim())) {// 有开始日期结束日期
							criterion += " to_number(a.reservea_a) >=? and to_number(a.reservea_a) <=? and ";
							parameters.add(new Integer(startDate.trim()));
							parameters.add(new Integer(endDate.trim()));
						}
						if (startDate != null && !"".equals(startDate.trim())
								&& (endDate == null || "".equals(endDate.trim()))) {// 有开始日期无结束日期
							criterion += " to_number(a.reservea_a) >= ? and ";
							parameters.add(new Integer(startDate.trim()));
						}
						if (endDate != null && !"".equals(endDate.trim())
								&& (startDate == null || "".equals(startDate.trim()))) {// 无开始日期有结束日期
							criterion += " to_number(a.reservea_a) <= ? and ";
							parameters.add(new Integer(endDate.trim()));
						}
						if (loanBankId != null && !"".equals(loanBankId)) {
							criterion += " p111.loan_bank_id = ? and ";
							parameters.add(loanBankId);
						}
						hql = "select count(distinct a.emp_id) "
								+ "from pl400 a, pl110 p110, pl111 p111, aa001 b, ba001 c "
								+ "where a.org_id = b.id and b.orginfo_id = c.id and a.status = '0' and a.reservea_b='1' "
								+ "and p110.contract_id = p111.contract_id "
								+ "and p110.contract_id = a.contract_id and p110.office "
								+ securityInfo.getOfficeSecuritySQL();
					} else {
						hql = "select count(distinct a.contract_id) "
								+ "from pl400 a, pl110 p110, pl111 p111, aa001 b, ba001 c ,pl113 p113 "
								+ "where a.org_id = b.id and b.orginfo_id = c.id and p113.contract_id=a.contract_id and a.status = '0' and a.reservea_b='1' "
								+ "and p110.contract_id = p111.contract_id "
								+ "and p110.contract_id = a.contract_id and p110.office "
								+ securityInfo.getOfficeSecuritySQL();
						if (cotractId != null && !"".equals(cotractId)) {
							criterion += " a.contract_id= ? and ";
							parameters.add(cotractId);
						}
						if (borrowerName != null && !"".equals(borrowerName)) {
							criterion += " a.emp_name= ? and ";
							parameters.add(borrowerName);
						}
						if (cardNum != null && !"".equals(cardNum)) {
							criterion += " a.card_num= ? and ";
							parameters.add(cardNum);
						}
						if (xieYiNum != null && !"".equals(xieYiNum)) {
							criterion += " a.reservea_c= ? and ";
							parameters.add(xieYiNum);
						}
						if (orgId != null && !"".equals(orgId)) {
							criterion += " a.org_id= ? and ";
							parameters.add(orgId);
						}
						if (empId != null && !"".equals(empId)) {
							criterion += " a.emp_id= ? and ";
							parameters.add(empId);
						}
						if (sta != null && !"".equals(sta)) {
							criterion += " a.reservea_b= ? and ";
							parameters.add(sta);
						}
						if (startDate != null && !"".equals(startDate.trim()) && endDate != null
								&& !"".equals(endDate.trim())) {// 有开始日期结束日期
							criterion += " to_number(a.reservea_a) >=? and to_number(a.reservea_a) <=? and ";
							parameters.add(new Integer(startDate.trim()));
							parameters.add(new Integer(endDate.trim()));
						}
						if (startDate != null && !"".equals(startDate.trim())
								&& (endDate == null || "".equals(endDate.trim()))) {// 有开始日期无结束日期
							criterion += " to_number(a.reservea_a) >= ? and ";
							parameters.add(new Integer(startDate.trim()));
						}
						if (endDate != null && !"".equals(endDate.trim())
								&& (startDate == null || "".equals(startDate.trim()))) {// 无开始日期有结束日期
							criterion += " to_number(a.reservea_a) <= ? and ";
							parameters.add(new Integer(endDate.trim()));
						}
						if (loanBankId != null && !"".equals(loanBankId)) {
							criterion += " p111.loan_bank_id = ? and ";
							parameters.add(loanBankId);
						}
					}

					if (criterion.length() != 0)
						criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));
					String ob = orderBy;
					if (ob == null)
						ob = " a.id ";

					String od = orderother;
					if (od == null)
						od = "desc";
					hql = hql + criterion + " order by " + ob + " " + od;
					Query query = session.createSQLQuery(hql);
					for (int i = 0; i < parameters.size(); i++) {
						query.setParameter(i, parameters.get(i));
					}

					return query.uniqueResult().toString();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}

	/**
	 * 删除pl400根据合同编号删除
	 */
	public void deleteLoanreturnedbyfundTbInfo(final String contractid, final String id) {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {

					String sql = "delete FundloanInfo fundloanInfo where fundloanInfo.contractId=? and fundloanInfo.reserveaC="
							+ id;
					if ("".equals(id)) {
						sql = "delete FundloanInfo fundloanInfo where fundloanInfo.contractId=? and fundloanInfo.reserveaC is null";
					}
					Query query = session.createQuery(sql);
					query.setParameter(0, contractid);
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteLoanreturnedbyfundTbInfo_wsh(final String contractid, final String id) {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {

					String sql = "delete FundloanInfo fundloanInfo where  fundloanInfo.reserveaC='2'";
					Query query = session.createQuery(sql);
					query.setParameter(0, contractid);
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//
	public void useLoanreturnedbyfundTbInfo(final String contractid, final String xieyi_id) {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {

					String sql = "update FundloanInfo fundloanInfo  set fundloanInfo.reserveaB='1',fundloanInfo.reserveaC=? where fundloanInfo.contractId=? and fundloanInfo.reserveaB='2'";
					Query query = session.createQuery(sql);
					query.setParameter(0, xieyi_id);
					query.setParameter(1, contractid);
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add_f_p_num(final String office_id, final String year) {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {

					String sql = "update XieYiNum a set a.xieyiNum_id = a.xieyiNum_id + 1 where a.officeid= ? and a.year = ?";
					Query query = session.createQuery(sql);
					query.setParameter(0, office_id);
					query.setParameter(1, year);
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String find_xieyi_id(final String contractId, final String year) {
		String xieyi_num = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select distinct a.year || a.office_id || substr(a.xieyi_num,2,4) as xieyi_id from pl110 t, f_p_num a, pl111 b"
						+ " where t.office = a.office and t.contract_id = b.contract_id and b.contract_st = '11'"
						+ " and a.year = '" + year + "' and t.contract_id = ?";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId);
				return query.uniqueResult().toString();
			}
		});
		return xieyi_num;
	}

	public String find_print_status(final String contractId) {
		String status = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select nvl(max(t.reservea_b),0) from pl400 t  where t.contract_id=?";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId);
				return query.uniqueResult().toString();
			}
		});
		return status;
	}

	public String find_xieyiNum(final String contractId, final String id) {
		String xieyiNum = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select  distinct a.reservea_c||','||t.office from pl110 t, pl400 a where t.contract_id = a.contract_id and t.contract_id =? and a.id = "
						+ id + " ";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId);
				return query.uniqueResult().toString();
			}
		});
		return xieyiNum;
	}

	/**
	 * 删除pl400根据条件全部删除
	 */
	public void deleteAllLoanreturnedbyfundTbInfo(final String cotractId, final String borrowerName,
			final String cardNum) {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Vector parameters = new Vector();
					String criterion = "";
					String sql = "delete from FundloanInfo fundloanInfo ";
					if (cotractId != null && !"".equals(cotractId)) {
						criterion += " fundloanInfo.contractId= ? and ";
						parameters.add(cotractId);
					}
					if (borrowerName != null && !"".equals(borrowerName)) {
						criterion += " fundloanInfo.empName= ? and ";
						parameters.add(borrowerName);
					}
					if (cardNum != null && !"".equals(cardNum)) {
						criterion += " fundloanInfo.cardNum= ? and ";
						parameters.add(cardNum);
					}

					if (criterion.length() != 0)
						criterion = "where " + criterion.substring(0, criterion.lastIndexOf("and"));
					Query query = session.createQuery(sql);
					sql = sql + criterion;
					for (int i = 0; i < parameters.size(); i++) {
						query.setParameter(i, parameters.get(i));
					}
					return new Integer(query.executeUpdate());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 王硕 公积金还贷签订合同
	 * 
	 * @param orderBy
	 * @param orderother
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List LoanreturnedbyfundTbCountListByContractId(final String cotractId) {
		List list = null;
		try {
			list = getHibernateTemplate().executeFind(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {

					Vector parameters = new Vector();
					String criterion = "";

					if (cotractId != null && !"".equals(cotractId)) {
						criterion += " a.contract_id= ? and ";
						parameters.add(cotractId);
					}
					String hql = "select a.contract_id contractid, a.emp_name empname, a.card_num cardnum,a.org_id orgid,c.name orgname,a.seq s,a.emp_id empid from pl400 a, aa001 b, ba001 c where a.org_id = b.id and b.orginfo_id = c.id and a.seq !='0' and a.status='0' ";

					if (criterion.length() != 0)
						criterion = " and " + criterion.substring(0, criterion.lastIndexOf("and"));

					hql = hql + criterion;
					Query query = session.createSQLQuery(hql);
					for (int i = 0; i < parameters.size(); i++) {
						query.setParameter(i, parameters.get(i));
					}
					Iterator it = query.list().iterator();
					List temp_list = new ArrayList();
					Object obj[] = null;
					while (it.hasNext()) {
						obj = (Object[]) it.next();
						if (obj != null) {
							LoanreturnedbyfundTbDTO loanreturnedbyfundTbDTO = new LoanreturnedbyfundTbDTO();
							if (obj[0] != null) {
								loanreturnedbyfundTbDTO.setContractId(obj[0].toString());
							}
							if (obj[1] != null) {
								loanreturnedbyfundTbDTO.setBorrowerName(obj[1].toString());
							}

							if (obj[2] != null) {
								loanreturnedbyfundTbDTO.setCardNum(obj[2].toString());
							}
							if (obj[3] != null) {
								loanreturnedbyfundTbDTO.setOrgId(obj[3].toString());
							}
							if (obj[4] != null) {
								loanreturnedbyfundTbDTO.setOrgName(obj[4].toString());
							}
							if (obj[5] != null) {
								loanreturnedbyfundTbDTO.setSeq(obj[5].toString());
							}
							if (obj[6] != null) {
								loanreturnedbyfundTbDTO.setEmpId(obj[6].toString());
							}
							temp_list.add(loanreturnedbyfundTbDTO);
						}

					}
					return temp_list;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * author wsh 查询新扣款帐号是否存在
	 * 
	 * @param newLoanKouAcc
	 * @return
	 */
	public Integer LoanreturnedbyfundTbCheck_wsh(final String contractId) {
		Integer count = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select count(a.id) from pl400 a where a.contract_id= ? and (a.reservea_b='1' or a.reservea_b='2') ";
				Vector parameters = new Vector();
				if (contractId != null) {
					parameters.add(contractId);
				}
				Query query = session.createSQLQuery(hql);
				for (int i = 0; i < parameters.size(); i++) {
					query.setParameter(i, parameters.get(i));
				}
				Object obj = null;
				obj = query.uniqueResult();
				Integer temp_count = new Integer(0);
				if (obj != null) {
					temp_count = new Integer(obj.toString());
				}

				return temp_count;
			}
		});
		return count;
	}

	/**
	 * author wsh 通过合同编号查询pl110的办事处
	 * 
	 * @param newLoanKouAcc
	 * @return
	 */
	public String queryOfficeByContractId(final String contractId) {
		String office = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select a.office from pl110 a where a.contract_id= ? ";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId);
				return query.uniqueResult().toString();
			}
		});
		return office;
	}

	public String find_pl111_loan_day(final String contractId) {
		String CorpusInterest = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select nvl(avg(t.loan_repay_day),0) from pl111 t where t.contract_id =? and t.contract_st='11'";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId.trim());
				return query.uniqueResult().toString();
			}
		});
		return CorpusInterest;
	}

	public String find_pl201_CorpusInterest(final String contractId, final String biz_date) {
		String CorpusInterest = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select nvl(sum(t.should_corpus+t.should_interest),0) as CorpusInterest from pl201 t where t.contract_id=? and t.loan_kou_yearmonth=? ";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId.trim());
				query.setParameter(1, biz_date.trim().substring(0, 6));
				return query.uniqueResult().toString();
			}
		});
		return CorpusInterest;
	}

	public String find_pl201_CorpusInterest_nextmonth(final String contractId, final String biz_date) {
		String CorpusInterest = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select nvl(sum(t.should_corpus+t.should_interest),0) as CorpusInterest from pl201 t where t.contract_id=? and t.loan_kou_yearmonth=to_char(add_months(to_date(?,'yyyymm'),1),'yyyymm')";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId.trim());
				query.setParameter(1, biz_date.trim().substring(0, 6));
				return query.uniqueResult().toString();
			}
		});
		return CorpusInterest;
	}

	public String find_pl201_CorpusInterest_other(final String contractId, final String biz_date) {
		String CorpusInterest = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select nvl(t.should_corpus+t.should_interest,0) as CorpusInterest from pl201 t where t.contract_id=? and t.loan_kou_yearmonth in"
						+ " (select min(t.loan_kou_yearmonth) from pl201 t where t.contract_id = ? and t.real_corpus = '0' and t.real_interest = '0') ";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId.trim());
				query.setParameter(1, contractId.trim());
				return query.uniqueResult().toString();
			}
		});
		return CorpusInterest;
	}

	/**
	 * author wsh 通过合同编号查询协议号
	 * 
	 * @param newLoanKouAcc
	 * @return
	 */
	public String queryXieYiNumByContractId(final String contractId) {
		String office = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select max(to_number(t.reservea_c)) from pl400 t where t.contract_id= ? ";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId);
				return query.uniqueResult().toString();
			}
		});
		return office;
	}

	public String get_print_money(final String orgid, final String empid) {
		String office = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String a = "";

				String hql = "select a.pay_oldyear,a.pre_balance + a.cur_balance-a.pay_oldyear*12 as bal, "
						+ "a.pre_balance + a.cur_balance-(a.emp_pay + a.org_pay)*12 as balance from aa002 a where a.org_id=? and a.id=?";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, orgid);
				query.setParameter(1, empid);
				System.out.println("query=" + query.list());
				Iterator it = query.list().iterator();
				// Object obj[] =new Object[3] ;
				Object obj[] = null;
				while (it.hasNext()) {
					obj = (Object[]) it.next();

					if (obj[0] != null && obj[0].toString() != "0") {

						a = obj[1].toString();

					} else {

						a = obj[2].toString();
					}

				}
				return a;
				// obj=(Object[]) query.uniqueResult();
				// System.out.println("obj=="+obj[0]);
			}
		});
		return office;
	}

	/**
	 * 公积金还贷签订合同
	 * 
	 * @author 王硕 查询条件：contractId
	 */
	public void updateFundloanInfoStatus(final String contractId) throws Exception {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = " update FundloanInfo fundloanInfo set fundloanInfo.status='1' "
							+ "where  fundloanInfo.contractId=? ";
					session.createQuery(hql).setString(0, contractId).executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 公积金还贷签订合同
	 * 
	 * @author 王硕 查询条件：contractId
	 */
	public void updateFundloanInfoStatus_1(final String contractId, final String date_temp) throws Exception {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = " update FundloanInfo fundloanInfo set fundloanInfo.reserveaB='0',fundloanInfo.datestop=? "
							+ "where  fundloanInfo.contractId=?  and fundloanInfo.reserveaB='1'";
					Query query = session.createQuery(hql);
					query.setParameter(0, date_temp);
					query.setParameter(1, contractId);
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List get_loanbyfund_printlist(final String cotractId, final String status) {
		List list = null;
		try {
			list = getHibernateTemplate().executeFind(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {

					Vector parameters = new Vector();
					String criterion = "";

					if (cotractId != null && !"".equals(cotractId)) {
						criterion += " t.contract_id=? and t.status='0' and t.seq='0' and  ";
						parameters.add(cotractId);
					}
					if (status != null && !"".equals(status)) {
						criterion += " t.reservea_b=? and  ";
						parameters.add(status);
					}

					String hql = "select t.reservea_c,t.reservea_a,t.loan_kou_acc,t.emp_id,t.org_id,t.emp_name,t.card_num,t.date_stop from pl400 t where  ";

					if (criterion.length() != 0)
						criterion = criterion.substring(0, criterion.lastIndexOf("and"));

					hql = hql + criterion;
					Query query = session.createSQLQuery(hql);
					for (int i = 0; i < parameters.size(); i++) {
						query.setParameter(i, parameters.get(i));
					}
					Iterator it = query.list().iterator();
					List temp_list = new ArrayList();
					Object obj[] = null;
					while (it.hasNext()) {
						obj = (Object[]) it.next();
						if (obj != null) {
							EmpinfoDto dto = new EmpinfoDto();
							if (obj[0] != null) {
								dto.setXieyi_id(obj[0].toString());
							}
							if (obj[1] != null) {
								dto.setDate(obj[1].toString());
							}

							if (obj[2] != null) {
								dto.setLoan_kou_acc(obj[2].toString());
							}
							if (obj[3] != null) {
								dto.setEmpid(obj[3].toString());
							}
							if (obj[4] != null) {
								dto.setOrgid(obj[4].toString());
							}
							if (obj[5] != null) {
								dto.setEmp_name(obj[5].toString());
							}
							if (obj[6] != null) {
								dto.setEmp_card_num(obj[6].toString());
							}
							if (obj[7] != null) {
								dto.setDate_stop(obj[7].toString());
							}
							temp_list.add(dto);
						}

					}
					return temp_list;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List get_loanbyfund_printlist_s(final String cotractId, final String status) {
		List list = null;
		try {
			list = getHibernateTemplate().executeFind(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {

					Vector parameters = new Vector();
					String criterion = "";

					if (cotractId != null && !"".equals(cotractId)) {
						criterion += " t.contract_id=? and t.status='0' and t.seq!='0' and ";
						parameters.add(cotractId);
					}
					if (status != null && !"".equals(status)) {
						criterion += " t.reservea_b=? and  ";
						parameters.add(status);
					}

					String hql = "select t.reservea_c,t.reservea_a,t.loan_kou_acc,t.emp_id,t.org_id,t.emp_name,t.card_num,t.DATE_STOP,t.seq from pl400 t where  ";

					if (criterion.length() != 0)
						criterion = criterion.substring(0, criterion.lastIndexOf("and"));

					hql = hql + criterion;
					Query query = session.createSQLQuery(hql);
					for (int i = 0; i < parameters.size(); i++) {
						query.setParameter(i, parameters.get(i));
					}
					Iterator it = query.list().iterator();
					List temp_list = new ArrayList();
					Object obj[] = null;
					while (it.hasNext()) {
						obj = (Object[]) it.next();
						if (obj != null) {
							EmpinfoDto dto = new EmpinfoDto();
							if (obj[0] != null) {
								dto.setXieyi_id(obj[0].toString());
							}
							if (obj[1] != null) {
								dto.setDate(obj[1].toString());
							}

							if (obj[2] != null) {
								dto.setLoan_kou_acc(obj[2].toString());
							}
							if (obj[3] != null) {
								dto.setEmpid_s(obj[3].toString());
							}
							if (obj[4] != null) {
								dto.setOrgid_s(obj[4].toString());
							}
							if (obj[5] != null) {
								dto.setEmp_name_s(obj[5].toString());
							}
							if (obj[6] != null) {
								dto.setEmp_card_num_s(obj[6].toString());
							}
							if (obj[7] != null) {
								dto.setDate_stop(obj[7].toString());
							}
							if (obj[8] != null) {
								dto.setPl400_seq(obj[8].toString());
							}
							temp_list.add(dto);
						}

					}
					return temp_list;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public EmpinfoDto findBorrowerInfoByContractId(final String contractId) {
		EmpinfoDto empinfoDto = (EmpinfoDto) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				EmpinfoDto dto = new EmpinfoDto();
				String hql = "select t.borrower_name,t.card_num,t.emp_id,t.org_id from pl110 t where t.contract_id = ? ";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId);
				Object[] obj = (Object[]) query.list().iterator().next();
				dto.setEmp_name(obj[0] == null ? "" : obj[0].toString());
				dto.setEmp_card_num(obj[1] == null ? "" : obj[1].toString());
				dto.setEmpid(obj[2] == null ? "" : obj[2].toString());
				dto.setOrgid(obj[3] == null ? "" : obj[3].toString());
				return dto;
			}
		});
		return empinfoDto;
	}

	public String findHouseAddr(final String contractId) {
		String status = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select case when pl114.house_type='01' then pl114.house_addr else pl114.bargainor_house_addr end from pl114 where pl114.contract_id=?";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId);
				return query.uniqueResult().toString();
			}
		});
		return status;
	}

	public String find_xieyi_zuofei_id(final String contractId, final String year) {
		String xieyi_num = (String) getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select max(a.year) || max(a.office_id) || substr(min(to_number(a.xieyi_num)), 2, 4) as xieyi_id from pl110 t, f_p_num a, pl111 b"
						+ " where t.office = a.office and t.contract_id = b.contract_id and b.contract_st = '11' and a.type= '1'"
						+ " and a.year = '" + year + "' and t.contract_id = ?";
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, contractId);
				if (query.uniqueResult() != null) {
					return query.uniqueResult().toString();
				} else {
					return "";
				}
			}
		});
		return xieyi_num;
	}

	public void delete_f_p_num(final String contractId, final String year) {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {

				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {

					String sql = "delete  from XieYiNum xieYiNum where xieYiNum.office =(select  borrower.office from Borrower borrower where  borrower.contractId=?) and xieYiNum.xieyiNum_id=?";
					Query query = session.createQuery(sql);
					query.setParameter(0, contractId);
					query.setParameter(1, year);
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
