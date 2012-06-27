package org.xpup.security.wsf.acegiex;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.acegisecurity.AuthenticationException;
import net.sf.acegisecurity.context.ContextHolder;
import net.sf.acegisecurity.intercept.web.FilterInvocation;
import net.sf.acegisecurity.intercept.web.SecurityEnforcementFilter;

public class SecurityEnforcementFilterEx extends SecurityEnforcementFilter {

  protected void sendStartAuthentication(FilterInvocation fi,
      AuthenticationException reason) throws ServletException, IOException {
    HttpServletRequest request = (HttpServletRequest) fi.getRequest();
    String isAjax = request.getHeader("AJAX");
    if ("TRUE".equals(isAjax)) {
      ((HttpServletResponse) fi.getResponse()).sendError(
          HttpServletResponse.SC_UNAUTHORIZED, reason.getMessage()); // 401
    } else {
      ContextHolder.setContext(null);
      super.sendStartAuthentication(fi, reason);
    }
  }
}
