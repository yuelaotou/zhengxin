<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String contractId = request.getParameter("contractId");
	String buttonMethod = request.getParameter("buttonMethod");
	String isFinished = (String) request.getAttribute("isFinished");
%>
<html lang="true">
	<head>
		<title>审批贷款</title>
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js">
<script language="javascript"></script>
<script language="javascript" type="text/javascript">
function returnValue(){
    document.loanvipcheckReasonAF.submit();
}
</script>
</head>
<script language="javascript">
function reportErrors() {
	var flag = "<%=isFinished%>";
	if(flag=="1"){
		window.close();
		window.opener.document.Form1.submit();
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	window.opener.alert(message);
	</logic:messagesPresent>
}
</script>
	<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();"
		onContextmenu="return false">
		<form
			action="<%=path%>/sysloan/loanvipcheckReasonAC.do?buttonMethod=<%=buttonMethod%>"
			name="loanvipcheckReasonAF" method="POST">
			<input type="hidden" name="contractId" value="<%=contractId%>" />
			<table width="95%" border="0" cellspacing="0" cellpadding="3"
				align="center">
				<tr bgcolor="1BA5FF">
					<td align="center" height="6" colspan="1"></td>
				</tr>
			</table>
			<table width="95%" border="0" cellspacing="0" cellpadding="3"
				align="center">
				<tr>
					<td class="td1" align="center" valign="middle">
						请录入审批不通过原因：
					</td>
					<td class="td1" align="center" valign="middle">
						<input type="text" name="reason" styleClass="input3" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<input type="button" name="confirm"
													onclick="return returnValue();" styleClass="buttona"
													value="确定" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
