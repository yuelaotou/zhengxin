<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.xpup.common.util.BSUtils" %>
<%@page import="org.xpup.paramsys.IParams" %>

<%
	IParams params = (IParams)BSUtils.getBusinessService("params",session.getServletContext());
	boolean used = params.getBoolean("useValidationCode", true);
	String display = "block";
	if(!used) display = "none";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="-1" />
<meta http-equiv="Cache-Control" content="no-cache" />
<title>用户登录</title>
<script type="text/javascript">
function init() {
  if(window.top!=window.self)
    window.top.location.href = "../";
  document.all["j_username"].focus();
}
function enterToTab(){
  if(event.srcElement.type != 'button' && event.srcElement.type != 'textarea' && event.keyCode == 13){
    event.keyCode = 9;
  }
}
</script>
</head>
<body style="vertical-align:middle; text-align:center; padding-top:160px" onload="init()">
<form name="loginForm" action="../j_security_check" method="post">
  <table width="296" height="120" border="0" style=" border: solid; border-width:1px; border-color:#000000; background-color:#a7fcfc">
    <tr>
      <td colspan="2" style="text-align:center; height:28;"><strong>请您在此处登录</strong></td>
    </tr>
    <tr>
      <td colspan="2" style="text-align:center; height:28; color:#ff0000; font-size:14px">
      <%
		if ("true".equals(request.getParameter("error"))) {
			String errors = "";
	
			net.sf.acegisecurity.AuthenticationException exception = (net.sf.acegisecurity.AuthenticationException) session
			.getAttribute(net.sf.acegisecurity.ui.AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY);

			if (exception instanceof net.sf.acegisecurity.BadCredentialsException) {
				errors = "用户名或密码错误，请重新输入！";
			} else {
				errors = exception.getMessage();
			}
			out.print(errors);
		} else {
			out.print("&nbsp;");
		}
		
		String j_username = (String) request.getSession().getAttribute(
				net.sf.acegisecurity.ui.webapp.AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY);
		session
				.removeAttribute(net.sf.acegisecurity.ui.webapp.AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY);
		if (j_username == null)
			j_username = "";
		%>
      </td>
    </tr>
    <tr>
      <td width="96" style="text-align:right; height:28;">用户名：</td>
      <td width="186" style="text-align:left; height:28;"><input type="text" name="j_username" value="<%=j_username%>" style="width:160px; height:18px" onkeydown="enterToTab()"/></td>
    </tr>
    <tr>
      <td style="text-align:right; height:28;">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
      <td style="text-align:left; height:28;"><input type="password" name="j_password" style="width:160px; height:18px" onkeydown="enterToTab()" /></td>
    </tr>
    <tr style="display:<%=display %>">
      <td style="text-align:right; height:28;">验证码：</td>
      <td style="text-align:left; height:28;"><input type="text" name="j_validatecode" style="width:160px; height:18px"  /></td>
    </tr>
    <tr style="display:<%=display %>">
      <td style="text-align:right; height:28;">&nbsp;</td>
      <td style="text-align:left; height:28;"><img src="../generateimage" style="width:166px; height:20px" /></td>
    </tr>
    <tr>
      <td style="text-align:center; height:28;" colspan="2"><input type="submit" name="Submit" value="登录" style="width:50px;" />
        <input type="button" name="close" value="退出" style="width:50px" onclick="window.close()" /></td>
    </tr>
  </table>
</form>
</body>
</html>
