<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>职工账打印选项</title>
    <link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>	
  </head>
<script type="text/javascript" language="javascript">
function sure(){
	var orgidst = document.forms[0].elements["orgidst"].value;
	var orgidend = document.forms[0].elements["orgidend"].value;
	
	var empidst = document.forms[0].elements["empidst"].value;
	var empidend = document.forms[0].elements["empidend"].value;
	
	var timeSt = document.forms[0].elements["timeSt"].value;
	var timeEnd = document.forms[0].elements["timeEnd"].value;
	
	if(timeSt==""){
		alert('请输入开始发生日期');
		return false;
	}
	if(timeEnd==""){
		alert('请输入结束发生日期');
		return false;
	}
	
	window.opener.document.URL="empAccountPrintAC.do?orgidst="+orgidst+"&orgidend="+orgidend+"&timeSt="+timeSt+"&timeEnd="+timeEnd+"&empidst="+empidst+"&empidend="+empidend;
	window.close();
}
</script>
<body bgcolor="#FFFFFF" text="#000000">
	<html:form action="/empAccountPrintAC.do">
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
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											打印查询条件
										</td>
									</tr>
								</table>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">职工账</font>
										</td>
										<td width="115" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">查询条件</b>
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
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="15%" colspan="3">
								<html:text name="empAccountPrintPopAF"
									property="orgidst" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="empAccountPrintPopAF"
									property="orgidend" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								职工编号
							</td>
							<td width="15%" colspan="3">
								<html:text name="empAccountPrintPopAF"
									property="empidst" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="empAccountPrintPopAF"
									property="empidend" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								发生日期<font color="#FF0000">*</font>
							</td>
							<td width="15%" colspan="3">
								<html:text name="empAccountPrintPopAF"
									property="timeSt" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="empAccountPrintPopAF"
									property="timeEnd" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<html:button property="method" styleClass="buttona"
												onclick="sure();">
												<bean:message key="button.sure" />
											</html:button>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html>
