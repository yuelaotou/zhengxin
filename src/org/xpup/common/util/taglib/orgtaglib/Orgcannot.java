package org.xpup.common.util.taglib.orgtaglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.xpup.common.util.BSUtils;
import org.xpup.security.wsf.bizsrvc.IOpControlBS;

public class Orgcannot extends TagSupport {

  private static final long serialVersionUID = 7787067399264068765L;

  private String hlorgcannot = null;

  private String service = "securityControlBS";

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getHlorgcannot() {
    return hlorgcannot;
  }

  public void setHlorgcannot(String hlorgcannot) {
    this.hlorgcannot = hlorgcannot;
  }

  public int doEndTag() throws JspException {
    return (EVAL_PAGE);
  }

  public int doStartTag() throws JspException {
    if (condition())
      return (EVAL_BODY_INCLUDE);
    else
      return (SKIP_BODY);
  }

  public void release() {
    super.release();
    hlorgcannot = null;
  }

  private boolean condition() {
    boolean res = true;
    try {
      IOpControlBS opControlBS = (IOpControlBS) BSUtils.getBusinessService(service, pageContext.getServletContext());
      res=opControlBS.checkIsCenter();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return res;
  }

}
