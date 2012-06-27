package org.xpup.security.wsf.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.xpup.common.exception.BusinessException;
import org.xpup.common.util.BSUtils;
import org.xpup.security.wsf.bizsrvc.IOpControlBS;

public class AuthorizeTag extends TagSupport {

  private static final long serialVersionUID = 7787067399264068765L;

  private String operation = null;

  private String service = "securityControlBS";

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
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
    operation = null;
  }

  private boolean condition() {
    boolean res = true;
    IOpControlBS opControlBS = (IOpControlBS) BSUtils.getBusinessService(
        service, pageContext.getServletContext());
    try {
      HttpServletRequest rq = (HttpServletRequest) pageContext.getRequest();
      opControlBS.decide(rq.getRemoteUser(), operation);
    } catch (BusinessException ex) {
      res = false;
    }
    return res;
  }

}
