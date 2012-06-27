package org.xpup.hafmis.sysloan.dataready.bank.form;

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
public class BankListAF extends ActionForm {
  private static final long serialVersionUID = 1L;
  private List list=null;
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
}