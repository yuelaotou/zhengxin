<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>
<script type="text/javascript">
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	document.empBaseExportsAF.orgid.value="";
	document.empBaseExportsAF.orgid.focus();
}
function checkOrgId(){
	var orgid = document.empBaseExportsAF.orgid.value;
	if(orgid==""){
		alert("单位编号为空！！！");
		document.empBaseExportsAF.orgid.value="";
		document.empBaseExportsAF.orgid.focus();
		return false;
	}else if(isNaN(orgid)){
		alert("单位编号为数字！！！");
		document.empBaseExportsAF.orgid.value="";
		document.empBaseExportsAF.orgid.focus();
		return false;
	}else{
		return true;
	}
}
</script>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<html:html lang="true">
<head>
	<title>职工基本信息</title>
</head>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();">
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
									<td width="183" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询&gt;职工基本信息导出</font>
									</td>
									<td width="80" class=font14>
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="147">
										<b class="font14">职工基本信息导出</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/empBaseExportsExportsAC.do">
					<table border="0" width="50%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="13%" class="td1">
								单位编号
								<font style="color: red">*</font>
							</td>
							<td width="20%">
								<html:text property="orgid" styleClass="input3"
									name="empBaseExportsAF"></html:text>
							</td>
						</tr>
					</table>
					<table width="50%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="center">
								<html:submit styleClass="buttona" onclick="return checkOrgId();">
									<bean:message key="button.export" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
