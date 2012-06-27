<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action.CredenceFillinShowIncomeExpenseAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			CredenceFillinShowIncomeExpenseAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html>
	<head>
		<title>
			<logic:equal name="credenceFillinIncomeExpenseAF"
					property="incomeOrExpense" value="income">
					收入
			</logic:equal> <logic:notEqual name="credenceFillinIncomeExpenseAF"
					property="incomeOrExpense" value="income">
					支出
			</logic:notEqual>
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	</head>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
		<html:form action="/credenceFillinIncomeExpenseShowAC.do">
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
								<td width="235" background="<%=path%>/img/table_bg_line.gif"></td>
								<td background="<%=path%>/img/table_bg_line.gif" align="right">
									<table width="300" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="37">
												<img src="<%=path%>/img/title_banner.gif" width="37"
													height="24">
											</td>
											<td class=font14 bgcolor="#FFFFFF" align="center"
												valign="middle">
												<font color="00B5DB">凭证查询&gt; <logic:equal
														name="credenceFillinIncomeExpenseAF"
														property="incomeOrExpense" value="income">
														收入
													</logic:equal> <logic:notEqual name="credenceFillinIncomeExpenseAF"
														property="incomeOrExpense" value="income">
														支出
													</logic:notEqual> </font>
											</td>
											<td width="15" class=font14>
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
								<td class=h4>
									合计： 借方金额
									<u>:<bean:write name="credenceFillinIncomeExpenseAF"
											property="sum_debit" format="0.00" /> </u> 贷方金额
									<u>：<bean:write name="credenceFillinIncomeExpenseAF"
											property="sum_credit" format="0.00" /> </u>
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
												<b class="font14"> <logic:equal
														name="credenceFillinIncomeExpenseAF"
														property="incomeOrExpense" value="income">
														收入
													</logic:equal> <logic:notEqual name="credenceFillinIncomeExpenseAF"
														property="incomeOrExpense" value="income">
														支出
													</logic:notEqual> 列表 </b>
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
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr bgcolor="1BA5FF">
								<td align="center" height="6" colspan="9"></td>
							</tr>
							<tr>
								<td height="23" align="center" bgcolor="C4F0FF">
									单位编号
								</td>
								<td align="center" class=td2>
									单位名称
								</td>
								<td align="center" class=td2>
									归集银行
								</td>
								<td align="center" class=td2>
									借方金额
								</td>
								<td align="center" class=td2>
									贷方金额
								</td>
								<td align="center" class=td2>
									利息
								</td>
								<td align="center" class=td2>
									结算日期
								</td>
								<td align="center" class=td2>
									结算号
								</td>
								<td align="center" class=td2>
									业务类型
								</td>
							</tr>
							<logic:notEmpty name="credenceFillinIncomeExpenseAF"
								property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="credenceFillinIncomeExpenseAF"
									property="list" id="elements" indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr id="tr<%=i%>" class="<%=strClass%>">
										<td valign="top">
											<p>
												<bean:write name="elements" property="orgId"
													format="0000000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="orgName" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="moneyBank" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="debit" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="credit" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="interest" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="sett_date" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="note_num" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="biz_type" />
											</p>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="credenceFillinIncomeExpenseAF" property="list">
								<tr>
									<td colspan="11" height="30" style="color:red">
										没有找到与条件相符合结果！
									</td>
								</tr>
							</logic:empty>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr class="td1">
								<td>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td align="left">
												共
												<bean:write name="pagination" property="nrOfElements" />
												项
											</td>
											<td align="right">
												<jsp:include page="../../../inc/pagination.jsp">
													<jsp:param name="url"
														value="credenceFillinIncomeExpenseShowAC.do" />
												</jsp:include>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom" align="center">
								<td bgcolor="#FFFFFF" align="center" height="30">
									<table width="156" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr align="center">
											<td width="99" align="center">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
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
