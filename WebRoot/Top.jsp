<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.orgstrct.dto.SecurityInfo"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%
	HttpSession se = request.getSession(false);
	String username = "";
	String bizDate = "";
	SecurityInfo securityInfo = (SecurityInfo) se
			.getAttribute("SecurityInfo");
	if (securityInfo == null) {
		String path = request.getContextPath();
%>
<script language="JavaScript">
      parent.window.location="<%=path%>/login.jsp";
    </script>
<%
	}
	username = securityInfo.getUserInfo().getUsername();
	bizDate = securityInfo.getBizDate();
%>
<html:html>
<head>
	<script>
function diversation(){
	setTimeout("locations()",50000);
	locations();
}

function locations(){
	window.parent.location="./Main.jsp"; 
}

</script>
	<title>金软科技公积金系统</title>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<link rel="stylesheet" href="./css/index.css" type="text/css">
</head>

<body bgcolor="#CEE7F4" text="#000000" leftmargin="0" topmargin="0">
	<html:form action="/loginAction">

		<table border="0" cellspacing="0" cellpadding="0" align="right">
			<tr>

				<td>
					<strong>登录身份:</strong>
					<bean:write name="loginActionForm" property="userName"></bean:write>
				</td>
				<td width="10"></td>
				<logic:notEqual name="loginActionForm" property="opSystemType"
					value="3">
				<td>
					<strong>业务日期:</strong>
					<bean:write name="loginActionForm" property="bizdate"></bean:write>
				</td>
				<td width="10"></td>
				</logic:notEqual>
				<logic:equal name="loginActionForm" property="opSystemType"
					value="3">
					<td>
						<strong><font color="red">账套名称:</font> </strong>
						<bean:write name="loginActionForm" property="bookName"></bean:write>
					</td>
					<td width="10"></td>
				</logic:equal>
				<td>
					<strong>
					</strong>
				</td>
			</tr>
		</table>
	</html:form>
</body>

</html:html>
