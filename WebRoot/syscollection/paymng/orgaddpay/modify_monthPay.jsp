<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.monthpay.action.MonthpayTaShowAC"%>
<%@ page import="org.xpup.hafmis.orgstrct.dto.SecurityInfo"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			MonthpayTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	SecurityInfo securityInfo1 = (SecurityInfo) request.getSession(
			false).getAttribute("SecurityInfo");
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>缴存管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script>
<script type="text/javascript">

</script>
<body bgcolor="#FFFFFF" text="#000000" >

	<html:form action="/monthpayModifyAC.do" focus="orgid">
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
											<font color="00B5DB">缴存管理<font color="00B5DB">&gt;
													修改信息</font>
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
											<b class="font14">缴 存 信 息</b>
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
							<td colspan="2">
								<html:text name="monthpayJYAF" property="orgid"
									styleClass="input3" styleId="txtsearch" disabled="true"/>
								
							</td>
							<td class="td1" width="17%">
								单位名称
							</td>
							<td width="33%" colspan="2">
								<html:text name="monthpayJYAF" property="name"
									styleClass="input3" styleId="txtsearch" readonly="true" disabled="true"/>
									<input type="hidden" name="monthpayHeadId"
													value="<bean:write name="monthpayJYAF" property="monthpayHeadId"/>">
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								付款单位开户银行名称
							</td>
							<td colspan="2">
								<html:text name="monthpayJYAF" property="payment_bank_name"
									styleClass="input3" styleId="txtsearch" />
							</td>
							<td class="td1" width="17%">
								付款单位开户银行账号
							</td>
							<td width="33%" colspan="2">
								<html:text name="monthpayJYAF" property="payment_bank_acc"
									styleClass="input3" styleId="txtsearch" />
							</td>
						</tr>
						
						
						
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellpadding="0" cellspacing="0" width="280">
									<tr>
										<td width="80" align="right">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.update" />
											</html:submit>
										</td>
										<td>
											<html:button property="method" styleClass="buttonb" onclick="javascript:history.back();">
												<bean:message key="button.back" />
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
