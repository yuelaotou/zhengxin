package org.xpup.hafmis.sysloan.credit.report.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.xpup.hafmis.sysloan.common.domain.entity.Credit;

public class CreditAF extends ActionForm {

	private static final long serialVersionUID = 2531807195056023196L;

	private String shujutiquriqi = null;

	private String baowenshengchengriqi = null;

	private String officeCode = null;// 办事处

	private String loanBankName = null;// 放款银行

	private String yewuhao = null;// 合同账号

	private String jiluzhuangtai = null;// 数据状态，0正常，1删除

	private Credit credit = new Credit();

	private List list = new ArrayList();

	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getBaowenshengchengriqi() {
		return baowenshengchengriqi;
	}

	public void setBaowenshengchengriqi(String baowenshengchengriqi) {
		this.baowenshengchengriqi = baowenshengchengriqi;
	}

	public String getShujutiquriqi() {
		return shujutiquriqi;
	}

	public void setShujutiquriqi(String shujutiquriqi) {
		this.shujutiquriqi = shujutiquriqi;
	}

	public String getLoanBankName() {
		return loanBankName;
	}

	public void setLoanBankName(String loanBankName) {
		this.loanBankName = loanBankName;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getYewuhao() {
		return yewuhao;
	}

	public void setYewuhao(String yewuhao) {
		this.yewuhao = yewuhao;
	}

	public String getJiluzhuangtai() {
		return jiluzhuangtai;
	}

	public void setJiluzhuangtai(String jiluzhuangtai) {
		this.jiluzhuangtai = jiluzhuangtai;
	}
}
