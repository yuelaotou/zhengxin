<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymntmonthreport.action.PaymntMonthReportShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			PaymntMonthReportShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>个人贷款计算器</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
</head>

<script language="javascript" type="text/javascript">
function check(){
    var loanTimeLimit = document.all.loanTimeLimit.value.trim();
    var loanMoney = document.all.loanMoney.value.trim();
    var loanRate = document.all.loanRate.value.trim();
    if(loanMoney==""){
    	alert("请输入贷款金额");
    	return false;
    }
    if(loanRate==""){
    	alert("请输入年利率");
    	return false;
    }
    if(loanTimeLimit==""){
    	alert("请输入贷款期限");
    	return false;
    }
}
</script>

<body bgcolor="#FFFFFF" text="#000000">
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
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">个人贷款计算器</font>
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
				<html:form action="/personalloancalcFindAC.do" style="margin:0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="174">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="746">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td width="13%" class="td1">
								贷款金额
								<font color="red">*</font>
							</td>
							<td width="18%">
								<html:text name="af" property="loanMoney" styleClass="input3"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td align="center" width="13%">
								利率(年)
								<font color="red">*</font>
							</td>
							<td width="16%">
								<html:text name="af" property="loanRate" styleClass="input3"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td>
								%
							</td>
							<td align="center" width="13%">
								期限(月)
								<font color="red">*</font>
							</td>
							<td width="18%">
								<html:text name="af" property="loanTimeLimit"
									styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
							</td>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="216">
										<b class="font14">个人贷款计算器</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="688">
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
				<html:form action="/personalloancalcMaintainAC.do">
					<table width="95%" border="0" cellspacing="1" cellpadding="3"
						align="center" bgcolor="1BA5FF">
						<logic:notEmpty name="list">
							<tr align="center">
								<td align="center" class="td1">
									期数
								</td>
								<td align="center" class="td1">
									应还本金
								</td>
								<td align="center" class="td1">
									应还利息
								</td>
								<td align="center" class="td1">
									月还款额
								</td>
								<td align="center" class="td1">
									贷款余额
								</td>
							</tr>
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr class="<%=strClass%>" align="center">
									<td>
										<bean:write name="e" property="loanTimeLimit" />
									</td>
									<td>
										<bean:write name="e" property="corpus" />
									</td>
									<td>
										<bean:write name="e" property="interest" />
									</td>
									<td>
										<bean:write name="e" property="corpusInterest" />
									</td>
									<td>
										<bean:write name="e" property="overplusLoanMoney" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<logic:empty name="list">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</logic:empty>
											<logic:notEmpty name="list">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</logic:notEmpty>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
