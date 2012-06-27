<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.interesttotalacc.action.InterestTaShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			InterestTaShowAC.PAGINATION_KEY);
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
function viewMonthAcc(trindex){
	tr = trindex;
	var year = document.getElementById(tr).childNodes[0].childNodes[0].innerHTML.trim();
	var id = document.getElementById(tr).childNodes[8].innerHTML.trim();
	location="interestTbForwardURLAC.do?loanBankId="+id+"&year="+year;
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
										<font color="00B5DB">贷款账查询&gt;利息总账</font>
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
				<html:form action="/interestTaFindAC.do" style="margin:0">
					<table border="0" width="95%" cellspacing=1 cellpadding=0
						align="center">
						<tr>
							<td width="13%" class="td1">
								银行
							</td>
							<td width="37%">
								<html:select name="interestTaAF" property="loanBankId"
									onkeydown="enterNextFocus1();" styleClass="input3">
									<html:option value=""></html:option>
									<html:options collection="loanBankNameList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="13%" class="td1">
								年份
							</td>
							<td width="17%">
								<html:text name="interestTaAF" property="startYear"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="3%" align="center">
								至
							</td>
							<td width="17%">
								<html:text name="interestTaAF" property="endYear"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona">
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
										<b class="font14">利息总账列表</b>
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
							发生年份
						</td>
						<td align="center" class=td2>
							期初利息
						</td>
						<td align="center" class=td2>
							期初逾期利息
						</td>
						<td align="center" class=td2>
							本期利息
						</td>
						<td align="center" class=td2>
							本期逾期利息
						</td>
						<td align="center" class=td2>
							期末利息
						</td>
						<td align="center" class=td2>
							期末逾期利息
						</td>
						<td align="center" class=td2>
							放款银行
						</td>
					</tr>
					<logic:notEmpty name="interestTaAF" property="list">
						<%
									int j = 0;
									String strClass = "";
						%>
						<logic:iterate name="interestTaAF" property="list" id="element"
							indexId="i">
							<%
									j++;
									if (j % 2 == 0) {
										strClass = "td8";
									} else {
										strClass = "td9";
									}
							%>
							<tr id="tr<%=i%>" class="<%=strClass %>">
								<td>
									<a href="#" onClick="viewMonthAcc('tr<%=i%>');"><bean:write
											name="element" property="year" />
									</a>
								</td>
								<td>
									<bean:write name="element" property="bgnInterest" />
								</td>
								<td>
									<bean:write name="element" property="bgnOverdueInterest" />
								</td>
								<td>
									<bean:write name="element" property="realInterest" />
								</td>
								<td>
									<bean:write name="element" property="realOverdueInterest" />
								</td>
								<td>
									<bean:write name="element" property="endInterest" />
								</td>
								<td>
									<bean:write name="element" property="endOverdueInterest" />
								</td>
								<td>
									<bean:write name="element" property="loanBank" />
								</td>
								<td style="display:none">
									<bean:write name="element" property="loanBankId" />
								</td>
							</tr>

						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="interestTaAF" property="list">
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
											<jsp:param name="url" value="interestTaShowAC.do" />
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
										<logic:notEmpty name="interestTaAF" property="list">
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
										<logic:empty name="interestTaAF" property="list">
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

