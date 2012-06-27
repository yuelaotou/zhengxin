package org.xpup.security.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts.util.ModuleUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.ContextLoaderPlugIn;
import org.xpup.common.util.BSUtils;

public abstract class SecurityTagBase extends BodyTagSupport {
  protected Object getServiceAccordingToPlugIn(String serviceName)
      throws JspException {

    String prefix = ModuleUtils.getInstance().getModuleName(
        (HttpServletRequest) pageContext.getRequest(),
        pageContext.getServletContext());
    String attrName = ContextLoaderPlugIn.SERVLET_CONTEXT_PREFIX + prefix;
    WebApplicationContext wac = (WebApplicationContext) pageContext
        .getServletContext().getAttribute(attrName);
    if (wac == null) {
      throw new JspException(
          "Could not find ContextLoaderPlugIn's WebApplicationContext as ServletContext attribute ["
              + attrName
              + "] - did you register ["
              + ContextLoaderPlugIn.class.getName() + "]?");
    }
    Object service = wac.getBean(serviceName);
    if (service == null) {
      throw new JspException("没有找到名称为‘" + serviceName + "’的服务对象");
    }
    return service;
  }

  protected Object getService(String serviceName) throws JspException {
    Object service = BSUtils.getBusinessService(serviceName, pageContext
        .getServletContext());
    if (service == null) {
      throw new JspException("没有找到名称为‘" + serviceName + "’的服务对象");
    }
    return service;
  }

  protected String getUsername() {
    HttpServletRequest rq = (HttpServletRequest) pageContext.getRequest();
    return rq.getRemoteUser();
  }
}
