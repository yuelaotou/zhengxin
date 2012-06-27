<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>开户销户</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="css/index.css" type="text/css">
	</head>
	<script language="javascript" src="js/common.js">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}



</script>


	<script>
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>


	<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()">

		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="<%=path%>/img/table_left.gif" width="7" height="37">
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="55">
								&nbsp;
							</td>
							<td width="235" background="<%=path%>/img/table_bg_line.gif">
								&nbsp;
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>

										<td width="47" class=font14>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="9">
								<img src="<%=path%>/img/table_right.gif" width="9" height="37">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class=td3>


					<html:form action="/toupdatepasswordAC.do">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="117">
												<b class="font14">修改密码</b>
											</td>
											<td height="22" background="img/bg2.gif" align="center">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table border="0" width="50%" id="table1" cellspacing=1
							cellpadding=3 align="center">

							<tr>
								<td width="26%" class="td1">
									用户名
								</td>
								<td colspan="2">
									<html:text name="loginActionForm" property="userName"
										readonly="true" styleClass="input3">
									</html:text>
								</td>
								<td width="35%">
								</td>
							</tr>
							<tr>
								<td width="16%" class="td1">
									原始密码
								</td>
								<td colspan="2">
									<html:text name="loginActionForm" property="userPassword"
										styleClass="input3">
									</html:text>
								</td>
							</tr>
							<tr>
								<td width="16%" class="td1">
									新密码
								</td>
								<td colspan="2">
									<html:text name="loginActionForm" property="newPassword"
										styleClass="input3">
									</html:text>
								</td>
							</tr>
							<tr>
								<td width="16%" class="td1">
									新密码确认
								</td>
								<td colspan="2">
									<html:text name="loginActionForm" property="snewPassword"
										styleClass="input3">
									</html:text>

								</td>
							</tr>
						</table>

						<table width="90%" border="0" cellspacing="0" cellpadding="3">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">修改</html:submit>
												<br>
											</td>
										</tr>

									</table>
								</td>
							</tr>
						</table>
					</html:form>
				</td>
			</tr>
		</table>

	</body>
</html>
