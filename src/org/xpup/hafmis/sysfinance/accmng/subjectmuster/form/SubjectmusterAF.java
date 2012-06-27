/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package org.xpup.hafmis.sysfinance.accmng.subjectmuster.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 11-06-2007
 * Copy Right Information : 凭证汇总
 * Project : 文件名
 * @Version : 1.0
 * @author : 张列 生成日期 : 11-06-2007
 * XDoclet definition:
 * @struts.form name="subjectmusterAF"
 */
public class SubjectmusterAF extends ActionForm {

  private static final long serialVersionUID = 1L;

  /** credenceDateEndMonths property */
	private String credenceDateEndMonths = "";

	/** bookId property */
	private String bookId = "";

	/** officeName property */
	private String officeName = "";

	/** credenceNumStart property */
	private String credenceNumStart = "";

	/** credenceDateStartMonths property */
	private String credenceDateStartMonths = "";

	/** credenceDateYear property */
	private String credenceDateYear = "";

	/** credenceNumEnd property */
	private String credenceNumEnd = "";
  
  private String subjectLevel = "";

	/*
	 * Generated Methods
	 */

	public String getSubjectLevel() {
    return subjectLevel;
  }

  public void setSubjectLevel(String subjectLevel) {
    this.subjectLevel = subjectLevel;
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
	 * Returns the credenceDateEndMonths.
	 * @return String
	 */
	public String getCredenceDateEndMonths() {
		return credenceDateEndMonths;
	}

	/** 
	 * Set the credenceDateEndMonths.
	 * @param credenceDateEndMonths The credenceDateEndMonths to set
	 */
	public void setCredenceDateEndMonths(String credenceDateEndMonths) {
		this.credenceDateEndMonths = credenceDateEndMonths;
	}

	/** 
	 * Returns the bookId.
	 * @return String
	 */
	public String getBookId() {
		return bookId;
	}

	/** 
	 * Set the bookId.
	 * @param bookId The bookId to set
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
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
	 * Returns the credenceNumStart.
	 * @return String
	 */
	public String getCredenceNumStart() {
		return credenceNumStart;
	}

	/** 
	 * Set the credenceNumStart.
	 * @param credenceNumStart The credenceNumStart to set
	 */
	public void setCredenceNumStart(String credenceNumStart) {
		this.credenceNumStart = credenceNumStart;
	}

	/** 
	 * Returns the credenceDateStartMonths.
	 * @return String
	 */
	public String getCredenceDateStartMonths() {
		return credenceDateStartMonths;
	}

	/** 
	 * Set the credenceDateStartMonths.
	 * @param credenceDateStartMonths The credenceDateStartMonths to set
	 */
	public void setCredenceDateStartMonths(String credenceDateStartMonths) {
		this.credenceDateStartMonths = credenceDateStartMonths;
	}

	/** 
	 * Returns the credenceDateYear.
	 * @return String
	 */
	public String getCredenceDateYear() {
		return credenceDateYear;
	}

	/** 
	 * Set the credenceDateYear.
	 * @param credenceDateYear The credenceDateYear to set
	 */
	public void setCredenceDateYear(String credenceDateYear) {
		this.credenceDateYear = credenceDateYear;
	}

	/** 
	 * Returns the credenceNumEnd.
	 * @return String
	 */
	public String getCredenceNumEnd() {
		return credenceNumEnd;
	}

	/** 
	 * Set the credenceNumEnd.
	 * @param credenceNumEnd The credenceNumEnd to set
	 */
	public void setCredenceNumEnd(String credenceNumEnd) {
		this.credenceNumEnd = credenceNumEnd;
	}
}