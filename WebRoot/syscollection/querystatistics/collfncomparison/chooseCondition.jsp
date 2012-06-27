<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
%>

<html:html>
<head>
	<title>单位账</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />	
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<script language="javascript">
</script>
<script type="text/javascript" language="javascript">
function sure(){
	var orgidst = document.forms[0].elements["orgidst"].value;
	var orgidend = document.forms[0].elements["orgidend"].value;
	var timeSt = document.forms[0].elements["timeSt"].value;
	var timeEnd = document.forms[0].elements["timeEnd"].value;
	if(timeSt==""){
		alert('请输入开始发生日期');
		return false;
	}else{
		if(!checkDate(document.forms[0].elements["timeSt"])){
			document.forms[0].elements["timeSt"].value="";
			return false;
		}
	}
	if(timeEnd==""){
		alert('请输入结束发生日期');
		return false;
	}else{
		if(!checkDate(document.forms[0].elements["timeEnd"])){
			document.forms[0].elements["timeEnd"].value="";
			return false;
		}
	}
	window.opener.document.URL="collFnComparisonOrgAccountPrintAC.do?orgidst="+orgidst+"&orgidend="+orgidend+"&timeSt="+timeSt+"&timeEnd="+timeEnd;
	window.close();
}
</script>
<body bgcolor="#FFFFFF" text="#000000"
	onContextmenu="return false">
	<html:form action="/collFnComparisonOrgAccountPrintAC.do">
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
											<font color="00B5DB">单位账</font>
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
								<html:text name="collFnComparisonOrgAccountShowAF"
									property="orgidst" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="collFnComparisonOrgAccountShowAF"
									property="orgidend" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							</tr>
							<tr>
							<td width="17%" class="td1">
								发生日期<font color="#FF0000">*</font>
							</td>
							<td width="15%" colspan="3">
								<html:text name="collFnComparisonOrgAccountShowAF"
									property="timeSt" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="collFnComparisonOrgAccountShowAF"
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
												onclick="return sure();">
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
</html:html>
