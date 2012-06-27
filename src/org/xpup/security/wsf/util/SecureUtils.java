package org.xpup.security.wsf.util;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.acegisecurity.Authentication;
import net.sf.acegisecurity.context.security.SecureContext;
import net.sf.acegisecurity.context.security.SecureContextUtils;
import net.sf.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import net.sf.acegisecurity.ui.AbstractProcessingFilter;
import net.sf.acegisecurity.ui.rememberme.TokenBasedRememberMeServices;

/**
 * 安全子系统工具类
 * 
 * @author $Author: wangyh $
 * @version $Revision: 1.2 $,$Date: 2006/05/07 01:23:19 $
 */
public final class SecureUtils {
  /**
   * 更新ThreadLocal中的Authentication。
   * 
   * @param username 用户名
   * @param rawPassword 未经加密的密码
   */
  public static void updateAuthentication(String username, String rawPassword) {
    SecureContext sc = SecureContextUtils.getSecureContext();
    // 此处使用未经加密的密码。
    Authentication auth = new UsernamePasswordAuthenticationToken(username,
        rawPassword);

    sc.setAuthentication(auth);
  }

  public static void successfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, String username, String rawPassword,
      String defaultTargetUrl) throws IOException {

    SecureContext sc = SecureContextUtils.getSecureContext();
    Authentication auth = new UsernamePasswordAuthenticationToken(username,
        rawPassword);
    sc.setAuthentication(auth);

    String targetUrl = (String) request.getSession().getAttribute(
        AbstractProcessingFilter.ACEGI_SECURITY_TARGET_URL_KEY);
    request.getSession().removeAttribute(
        AbstractProcessingFilter.ACEGI_SECURITY_TARGET_URL_KEY);

    if (targetUrl == null) {
      targetUrl = request.getContextPath() + defaultTargetUrl;
    }

    response.sendRedirect(response.encodeRedirectURL(targetUrl));
  }

  public static void cancelCookie(HttpServletResponse response) {
    Cookie terminate = new Cookie(
        TokenBasedRememberMeServices.ACEGI_SECURITY_HASHED_REMEMBER_ME_COOKIE_KEY,
        null);
    terminate.setMaxAge(0);
    response.addCookie(terminate);
  }
}
