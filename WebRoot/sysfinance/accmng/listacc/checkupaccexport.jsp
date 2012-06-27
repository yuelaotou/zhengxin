<%@ page language="java"%>
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
<html:html>
<head>
	<title>财务对账导出</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function tosubject(){
	gotoSubjectpop('0','<%=path%>','1');
}
function executeAjax(){
	var starsubject=document.all.starsubject.value.trim();
	if(starsubject.length!=0){
	    var queryString = "checkSubjectcodeAAC.do?";
	    queryString = queryString + "subject="+starsubject;
	    findInfo(queryString);
	}
}
function display(message){
	if(message.length!=0){
		alert(message);
		document.all.starsubject.focus();
		document.all.starsubject.value="";
		return false;
	}else{
		document.all.bizyear.focus();
		return false;
	}
}
function check(){
	
}
</script>
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
										<font color="00B5DB">统计查询&gt;财务对账导出</font>
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
										<b class="font14">财务对账导出</b>
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
				<html:form action="/checkupAccExportAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="13%" class="td1">
								科目代码
								<font color="#FF0000">*</font>
							</td>
							<td width="34%" colspan="3">
								<html:text name="listaccAF" property="starsubject"
									styleClass="input3" onblur="executeAjax();"></html:text>
							</td>
							<td width="3%">
								<input type="button" class=buttona value="..."
									onclick="tosubject();" />
							</td>
							<td width="13%" class="td1">
								会计期间
								<font color="#FF0000">*</font>
							</td>
							<td width="20%">
								<html:text name="listaccAF" property="bizyear"
									onkeydown="enterNextFocus1();" styleClass="input3"></html:text>
							</td>
							<td width="3%" align="center">
								年
							</td>
							<td width="20%">
								<html:text name="listaccAF" property="starperiod"
									onkeydown="enterNextFocus1();" styleClass="input3"></html:text>
							</td>
							<td width="3%" align="center">
								月
							</td>
						</tr>
					</table>
					<table width="50%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="center">
								<html:submit styleClass="buttona" onclick="return check();">
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
