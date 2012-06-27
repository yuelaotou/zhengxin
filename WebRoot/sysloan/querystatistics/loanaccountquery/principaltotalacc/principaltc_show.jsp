<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.principaltotalacc.action.PrincipalTcShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			PrincipalTcShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>
<script type="text/javascript">
var tr="tr0";
function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function check(){
	var bankId = document.principalTaAF.elements["loanBankId"].value.trim();
	var year = document.principalTaAF.elements["startYear"].value.trim();
	var month = document.principalTaAF.elements["month"].value.trim();
	if(bankId == ""){
		alert("请选择银行");
		return false;
	}
	if(year == ""){
		alert("请选择年份");
		return false;
	}
	if(month == ""){
		alert("请输入月份");
		return false;
	}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="loads();">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
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
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">贷款账查询&gt;本金日账</font>
									</td>
									<td width="29" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="188">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="671">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/principalTcFindAC.do" style="margin:0">
					<table border="0" width="95%" cellspacing=1 cellpadding=0
						align="center">
						<tr>
							<td width="13%" class="td1">
								银行
							</td>
							<td width="37%">
								<html:select name="principalTaAF" property="loanBankId"
									onkeydown="enterNextFocus1();" styleClass="input3">
									<html:option value=""></html:option>
									<html:options collection="loanBankNameList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="13%" class="td1">
								日期
							</td>
							<td width="16%">
								<html:text name="principalTaAF" property="startYear"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="3%" align="center">
								年
							</td>
							<td width="16%">
								<html:text name="principalTaAF" property="month"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="3%" align="center">
								月
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return check();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="202">
										<b class="font14">本金总账列表</b>
									</td>
									<td width="703" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr bgcolor="1BA5FF">
						<td align="center" height="6" colspan="1"></td>
					</tr>
				</table>

				<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
					cellpadding="3" align="center">
					<tr align="center" bgcolor="C4F0FF">
						<td align="center" class=td2>
							发生日期
						</td>
						<td align="center" class=td2>
							期初余额
						</td>
						<td align="center" class=td2>
							本期借方
						</td>
						<td align="center" class=td2>
							本期贷方
						</td>
						<td align="center" class=td2>
							期末余额
						</td>
						<td align="center" class=td2>
							本期借方笔数
						</td>
						<td align="center" class=td2>
							本期贷方笔数
						</td>
						<td align="center" class=td2>
							累计借方
						</td>
						<td align="center" class=td2>
							累计贷方
						</td>
						<td align="center" class=td2>
							累计借方笔数
						</td>
						<td align="center" class=td2>
							累计贷方笔数
						</td>
						<td align="center" class=td2>
							放款银行
						</td>
					</tr>
					<logic:notEmpty name="principalTaAF" property="list">
						<%
									int j = 0;
									String strClass = "";
						%>
						<logic:iterate name="principalTaAF" property="list" id="element"
							indexId="i">
							<%
									j++;
									if (j % 2 == 0) {
										strClass = "td8";
									} else {
										strClass = "td9";
									}
							%>
							<tr id="tr<%=i%>" class="<%=strClass%>">
								<td>
									<bean:write name="element" property="date" />
								</td>
								<td>
									<bean:write name="element" property="bgnBalance" />
								</td>
								<td>
									<bean:write name="element" property="thisDebitMoney" />
								</td>
								<td>
									<bean:write name="element" property="thisCreditMoney" />
								</td>
								<td>
									<bean:write name="element" property="endBalance" />
								</td>
								<td>
									<bean:write name="element" property="thisDebitCount" />
								</td>
								<td>
									<bean:write name="element" property="thisCreditCount" />
								</td>
								<td>
									<bean:write name="element" property="totalDebitMoney" />
								</td>
								<td>
									<bean:write name="element" property="totalCreditMoney" />
								</td>
								<td>
									<bean:write name="element" property="totalDebitCount" />
								</td>
								<td>
									<bean:write name="element" property="totalCreditCount" />
								</td>
								<td>
									<bean:write name="element" property="loanBank" />
								</td>
							</tr>

						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="principalTaAF" property="list">
						<tr>
							<td colspan="4" height="30" style="color:red">
								没有找到与条件相符合的结果！
							</td>
						</tr>
					</logic:empty>

				</table>

				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr class="td1">
						<td align="center">
							<table width="100%" height="19" border="0" cellpadding="0"
								cellspacing="0">


								<tr>
									<td align="left">
										共
										<bean:write name="pagination" property="nrOfElements" />
										项
									</td>
									<td align="right">
										<jsp:include page="../../../../inc/pagination.jsp">
											<jsp:param name="url" value="principalTcShowAC.do" />
										</jsp:include>
									</td>
								</tr>

							</table>
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
										<logic:notEmpty name="principalTaAF" property="list">
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td>
														<html:submit styleClass="buttona">
															<bean:message key="button.print" />
														</html:submit>
													</td>
												</tr>
											</table>
										</logic:notEmpty>
										<logic:empty name="principalTaAF" property="list">
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td>
														<html:submit styleClass="buttona" disabled="true">
															<bean:message key="button.print" />
														</html:submit>
													</td>
												</tr>
											</table>
										</logic:empty>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
</body>
</html:html>

