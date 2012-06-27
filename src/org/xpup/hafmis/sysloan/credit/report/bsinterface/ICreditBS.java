package org.xpup.hafmis.sysloan.credit.report.bsinterface;

import java.sql.ResultSet;
import java.util.List;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.Pagination;

public interface ICreditBS {
	/**
	 * 杨光
	 */
	public List getCredit(Pagination pagination) throws Exception,
			BusinessException;

	public void createCredit(String shujutiquriqi) throws Exception,
			BusinessException;

	public void deleteCredit(String shujutiquriqi) throws Exception,
			BusinessException;

	public ResultSet exportNormal(Pagination pagination) throws Exception,
			BusinessException;

	public ResultSet exportDelete(Pagination pagination) throws Exception,
			BusinessException;

	public void dealWithContract(Pagination pagination) throws Exception,
			BusinessException;

}
