<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.collbyfund.action.CollByFundShowAC"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			CollByFundShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>查询条件，业务流水信息列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">

</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script language="javascript" type="text/javascript">
function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
<body bgcolor="#FFFFFF" text="#000000" >
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="3%" align="right" valign="middle">
				<table width="21" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
				</table>
			</td>
			<td width="97%" align="left" valign="top">
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif" width="7" height="37">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="10">
							&nbsp;
						</td>
						<td width="695" background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37" align="center" valign="top"
										style="PADDING-top: 7px"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
								</tr>
							</table>

						</td>
						<td width="300">
							<table width="300" border="0" cellspacing="0" cellpadding="0"
								align="right">
								<tr>
									<td width="56">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24" align="right">
									</td>
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">归集-公积金还贷查询</font>
									</td>
									<td class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="18">
							<img src="<%=path%>/img/table_bg_line.gif" width="18" height="37">
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top" class=td3>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="207">
													<b class="font14">查 询 条 件</b>
												</td>
												<td width="716" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<html:form action="/collByFundFindAC" style="margin: 0">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=0 align="center">
									<tr>
										<td width="17%" class="td1">
											办事处
										</td>
										<td width="33%" colspan="3">
											<html:select name="collByFundAF" property="officeCode"
												styleClass="input4" onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:options collection="officeList" property="value"
													labelProperty="label" />
											</html:select>
										</td>
										<td width="17%" class="td1">
											归集银行
										</td>
										<td width="33%">
											<html:select name="collByFundAF" property="collBankId"
												styleClass="input3" onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:options collection="collBankNameList" property="value"
													labelProperty="label" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											合同编号
										</td>
										<td width="33%" align="center" colspan="3">
											<html:text name="collByFundAF" property="contractId"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="17%" class="td1">
											姓名
										</td>
										<td align="center">
											<html:text name="collByFundAF" property="empName"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											职工编号
										</td>
										<td width="33%" align="center" colspan="3">
											<html:text name="collByFundAF" property="empId"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="17%" class="td1">
											单位名称
										</td>
										<td align="center">
											<html:text name="collByFundAF" property="orgName"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											单位编号
										</td>
										<td width="33%" align="center" colspan="3">
											<html:text name="collByFundAF" property="orgId"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="17%" class="td1">
											身份证号
										</td>
										<td align="center">
											<html:text name="collByFundAF" property="cardNum"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
									</tr>
									<tr>
										<td width="15%" class="td1">
											扣款日期
										</td>
										<td width="15%">
											<html:text name="collByFundAF" property="begDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="5%" align="center">
											至
										</td>
										<td width="15%">
											<html:text name="collByFundAF" property="endDate"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="15%" class="td1">
											批次号
										</td>
										<td width="33%" align="center" colspan="3">
											<html:text name="collByFundAF" property="batchNum"
												styleClass="input3" onkeydown="enterNextFocus1();" />
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
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td class=h4>
											合计：扣款总额
											<u>：<bean:write name="collByFundAF" property="money" format="#,##0.00"/> </u>
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
												<td height="22" bgcolor="#CCCCCC" align="center" width="213">
													<b class="font14">公积金还贷列表</b>
												</td>
												<td width="710" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<html:form action="/collByFundMaintainAC" style="margin: 0">
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
											单位编号
										</td>
										<td align="center" class=td2>
											单位名称
										</td>
										<td align="center" class=td2>
											合同编号
										</td>
										<td align="center" class=td2>
											还贷年月
										</td>
										<td align="center" class=td2>
											扣款总额
										</td>
										<td align="center" class=td2>
											职工编号
										</td>
										<td align="center" class=td2>
											职工姓名
										</td>
										<td align="center" class=td2>
											扣款日期
										</td>
										<td align="center" class=td2>
											批次号
										</td>
									</tr>
									<logic:notEmpty name="collByFundAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="collByFundAF" property="list" id="element"
											indexId="i">
											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>
											<tr align="left" class="<%=strClass%>" >
												<td>
													<p>
														<bean:write name="element" property="orgId" format="0000000000"/>
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="orgName" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="contractId" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="yearMonth" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="money" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="empId" format="000000"/>
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="empName" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="kouDate" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="batchNum" />
													</p>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="collByFundAF" property="list">
										<tr>
											<td colspan="9" height="30" style="color:red">
												没有找到与条件相符合的结果！
											</td>
										</tr>
									</logic:empty>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr class="td1">
										<td align="center">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr align="center">
													<td align="left">
														共
														<bean:write name="pagination" property="nrOfElements" />
														项
													</td>
													<td align="right">
														<jsp:include page="../../../inc/pagination.jsp">
															<jsp:param name="url" value="collByFundShowAC.do" />
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
											<logic:notEmpty name="collByFundAF" property="list">
												<table border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td>
															<html:submit property="method" styleClass="buttona">
																<bean:message key="button.print" />
															</html:submit>
															<html:submit property="method" styleClass="buttona">
																<bean:message key="button.print.bank" />
															</html:submit>
														</td>
													</tr>
												</table>
											</logic:notEmpty>
											<logic:empty name="collByFundAF" property="list">
												<table border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td>
															<html:submit property="method" styleClass="buttona"
																disabled="true">
																<bean:message key="button.print" />
															</html:submit>
															<html:submit property="method" styleClass="buttona"
																disabled="true">
																<bean:message key="button.print.bank" />
															</html:submit>
														</td>
													</tr>
												</table>
											</logic:empty>
										</td>
									</tr>
								</table>
							</html:form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>

