<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String settDate = request.getParameter("sett_date");
	String button = request.getParameter("button");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>打印位置</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>

</head>

<script>
function sure(){
	var beg = document.all.beg.value.trim();
	var end = document.all.end.value.trim();
	if(beg==""){
		alert("请输入日期");
		document.all.beg.focus();
		return false;
	}
	if(end==""){
		alert("请输入附单据");
		document.all.end.focus();
		return false;
	}
	//给父窗口的变量赋值
	window.opener.document.all.beg.value = beg;
	window.opener.document.all.end.value = end;
	window.opener.submit_yg();
	window.close();
	return false;
}

</script>
<body bgcolor="#FFFFFF" text="#000000" 
	onContextmenu="return false">
	<input type="hidden" name="settDate" value="<%=settDate%>" />
	<table width="95%" border="0" cellspacing="0" cellpadding="3"
		align="center">
		<tr bgcolor="1BA5FF">
			<td align="center" height="6" colspan="1"></td>
		</tr>
	</table>

	<table width="95%" border="0" cellspacing="0" cellpadding="3"
		align="center">

		<tr>
			<td class="td1" width="15%">
				打印位置
				<font color="red">*</font>
			</td>
			<td width="35%">
				<input type="text" name="beg"  
					class="input3" maxlength="4" />
			</td>
			<td class="td1" width="15%" align="center">
				至
				<font color="red">*</font>
			</td>
			<td width="35%">
				<input type="text" name="end" class="input3"
					maxlength="4" />
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr valign="bottom">
						<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td>
										<input type="button" name="confirm" onclick="return sure();"
											styleClass="buttona" value="确定" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>
