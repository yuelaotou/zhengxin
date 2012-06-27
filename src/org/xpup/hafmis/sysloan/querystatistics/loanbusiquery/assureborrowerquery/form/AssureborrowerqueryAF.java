package org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 09-21-2007
 * 
 * XDoclet definition:
 * @struts.form name="bankListAFForm"
 */
public class AssureborrowerqueryAF extends ActionForm {
  private static final long serialVersionUID = 1L;
  private List list=new ArrayList();
  private List list1=new ArrayList();
  /*
   * 职工编号 
   */
  private String empId="";
  /*
   * 职工姓名
   */
  private String empName="";
  /*
   * 合同编号
   */
  private String contract="";
  /*
   * 贷款帐号
   */
  private String loanKouAcc="";
  /*
   * 单位编号
   */
  private String orgId="";
  /*单位名称
   * 
   */
  private String orgName="";
	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public String getContract() {
    return contract;
  }

  public void setContract(String contract) {
    this.contract = contract;
  }

  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  public String getLoanKouAcc() {
    return loanKouAcc;
  }

  public void setLoanKouAcc(String loanKouAcc) {
    this.loanKouAcc = loanKouAcc;
  }

  public List getList1() {
    return list1;
  }

  public void setList1(List list1) {
    this.list1 = list1;
  }

  public String getOrgId() {
    return orgId;
  }

  public void setOrgId(String orgId) {
    this.orgId = orgId;
  }

  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
}