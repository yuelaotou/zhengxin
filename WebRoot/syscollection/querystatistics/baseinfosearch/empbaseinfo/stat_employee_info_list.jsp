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
<!--
	function convertOrgId(){	
	var id = document.getElementById("org.id").value;
	//alert("id-->"+id);
	if(id != ""){//把单位编号转换成6位数的样子
	    document.getElementById("org.id").value=format(id)+id;
	}
}
//-->
</script>
<html:html lang="true">
<head>
	<html:base />
	<title>职工信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="convertOrgId()">
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
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询&gt;职工信息</font>
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
										<b class="font14">自 然 信 息</b>
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
							所在单位编号
						</td>
						<td width="33%">
							<html:text property="org.id" name="employee" styleClass="input3"
								readonly="true"></html:text>
						</td>
						<td width="17%" class="td1">
							所在单位名称
						</td>
						<td width="33%" align="center">
							<html:text property="org.orgInfo.name" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td width="17%" class="td1">
							职工姓名
							<font color="#FF0000">*</font>
						</td>
						<td width="33%">
							<html:text property="empInfo.name" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
						<td width="17%" class="td1">
							证件类型
							<font color="#FF0000">*</font>
						</td>
						<td width="33%" align="center">
							<html:text property="empInfo.conCardType" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td width="17%" class="td1">
							证件号码
							<font color="#FF0000">*</font>
						</td>
						<td width="33%">
							<html:text property="empInfo.cardNum" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
						<td width="17%" class="td1">
							<span class="STYLE1">出生日期</span><font color="#FF0000"><font
								color="#FF0000">*</font>
							</font>
						</td>
						<td width="33%">
							<html:text property="empInfo.birthday" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td width="17%" class="td1">
							性别
							<font color="#FF0000">*</font>
						</td>
						<td width="33%">
							<html:text property="empInfo.conSex" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
						<td class="td1" width="17%">
							职工月收入
						</td>
						<td width="33%">
							<html:text property="empInfo.monthIncome" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td width="17%" class="td1">
							家庭电话
						</td>
						<td width="33%" align="center">
							<html:text property="empInfo.tel" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
						<td width="17%" class="td1">
							移动电话
						</td>
						<td width="33%">
							<html:text property="empInfo.mobileTle" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td class="td1" width="17%">
							&nbsp;
						</td>
						<td width="33%">
							&nbsp;
						</td>
						<td class="td1" width="17%">
							&nbsp;
						</td>
						<td width="33%">
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">归 集 信 息</b>
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
					<tr id="tr1">
						<td width="17%" class="td1">
							工资基数
							<font color="#FF0000">*</font>
						</td>
						<td width="33%">
							<html:text property="salaryBase" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
						<td width="17%" class="td1">
							缴存方式
						</td>
						<td width="33%">
							<html:text property="org.conPayMode" name="employee"
								styleClass="input3" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<td width="17%" class="td1">
							单位缴额
							<font color="#FF0000">*</font>
						</td>
						<td width="33%">
							<html:text property="orgPay" name="employee" styleClass="input3"
								readonly="true"></html:text>
						</td>
						<td width="17%" class="td1">
							职工缴额
							<font color="#FF0000">*</font>
						</td>
						<td width="33%">
							<html:text property="empPay" name="employee" styleClass="input3"
								readonly="true"></html:text>
						</td>
					</tr>
				</table>

				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr valign="bottom">
						<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="70">
										<input type="button" name="Submit" value="关闭" class="buttona"
											onClick="javascript:window.close();">
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
