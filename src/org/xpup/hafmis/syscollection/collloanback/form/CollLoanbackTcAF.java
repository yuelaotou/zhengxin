/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.syscollection.collloanback.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 06-25-2008
 * 
 * XDoclet definition:
 * @struts.form name="collLoanbackTcAFForm"
 */
public class CollLoanbackTcAF extends ActionForm {
	/*
	 * Generated fields
	 */

	/**
   * 
   */
  private static final long serialVersionUID = 1L;

  /** officeName property */
	private String officeName = "";

	/** orgId property */
	private String orgId = "";

	/** empId property */
	private String empId = "";

	/** bizDate property */
	private String bizDate = "";

	/** empName property */
	private String empName = "";

	/** bankName property */
	private String bankName = "";

	/** contractId property */
	private String contractId = "";

	/** orgName property */
	private String orgName = "";
  
  private int personSum = 0;
  
  private BigDecimal checkMoneySum = new BigDecimal("0.00");

  private List list = new ArrayList();
  
  private List listCount = new ArrayList();
	/*
	 * Generated Methods
	 */

	public List getListCount() {
    return listCount;
  }

  public void setListCount(List listCount) {
    this.listCount = listCount;
  }

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

	/** 
	 * Returns the officeName.
	 * @return String
	 */
	public String getOfficeName() {
		return officeName;
	}

	/** 
	 * Set the officeName.
	 * @param officeName The officeName to set
	 */
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	/** 
	 * Returns the orgId.
	 * @return String
	 */
	public String getOrgId() {
		return orgId;
	}

	/** 
	 * Set the orgId.
	 * @param orgId The orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/** 
	 * Returns the empId.
	 * @return String
	 */
	public String getEmpId() {
		return empId;
	}

	/** 
	 * Set the empId.
	 * @param empId The empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}

	/** 
	 * Returns the bizDate.
	 * @return String
	 */
	public String getBizDate() {
		return bizDate;
	}

	/** 
	 * Set the bizDate.
	 * @param bizDate The bizDate to set
	 */
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	/** 
	 * Returns the empName.
	 * @return String
	 */
	public String getEmpName() {
		return empName;
	}

	/** 
	 * Set the empName.
	 * @param empName The empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/** 
	 * Returns the bankName.
	 * @return String
	 */
	public String getBankName() {
		return bankName;
	}

	/** 
	 * Set the bankName.
	 * @param bankName The bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/** 
	 * Returns the contractId.
	 * @return String
	 */
	public String getContractId() {
		return contractId;
	}

	/** 
	 * Set the contractId.
	 * @param contractId The contractId to set
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/** 
	 * Returns the orgName.
	 * @return String
	 */
	public String getOrgName() {
		return orgName;
	}

	/** 
	 * Set the orgName.
	 * @param orgName The orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

  public List getList() {
    return list;
  }

  public void setList(List list) {
    this.list = list;
  }

  public BigDecimal getCheckMoneySum() {
    return checkMoneySum;
  }

  public void setCheckMoneySum(BigDecimal checkMoneySum) {
    this.checkMoneySum = checkMoneySum;
  }

  public int getPersonSum() {
    return personSum;
  }

  public void setPersonSum(int personSum) {
    this.personSum = personSum;
  }

}