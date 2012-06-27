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
	<title>自动转帐</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>

</head>

<script>
function loads(){
	document.all.date.value = "<%=settDate%>";
}
function sure(){
	var date = document.all.date.value.trim();
	var oldCredenceNum = document.all.oldCredenceNum.value.trim();
	if(date==""){
		alert("请输入日期");
		document.all.date.focus();
		return false;
	}
	if(oldCredenceNum==""){
		alert("请输入附单据");
		document.all.oldCredenceNum.focus();
		return false;
	}
	//给父窗口的变量赋值
	window.opener.document.all.sett_date.value = date;
	window.opener.document.all.oldCreNum.value = oldCredenceNum;
	window.opener.submit_ws("<%=button%>");
	window.close();
	return false;
}

//修改日期后，动态重新生成凭证号
function executeAjaxNumByDate(){
	var date = document.all.date.value.trim();
	var office="";
	var button="";
	if(date.length!=8){
		alert("日期格式不正确");
		document.all.date.focus();
	}else{
		var queryString = "<%=path%>/sysfinance/credenceFillinGetNumByDateAAC.do?";
		queryString = queryString + "office="+office+"&credenceDate="+date+"&button="+button;
	    findInfo(queryString);
	}
}
//给凭证号附值
function displaysNumByDate(credenceNum,error){
	if(error!=""){
		alert(error);
		document.all.date.focus();
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
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
				凭证日期
				<font color="red">*</font>
			</td>
			<td width="35%">
				<input type="text" name="date" onblur="return executeAjaxNumByDate();" 
					class="input3" maxlength="8" />
			</td>
			<td class="td1" width="15%">
				附单据
				<font color="red">*</font>
			</td>
			<td width="35%">
				<input type="text" name="oldCredenceNum" class="input3"
					maxlength="8" />
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
