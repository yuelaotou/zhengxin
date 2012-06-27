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
	<title>统计查询--缴存提取统计--公积金缴存统计月报表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
</head>

<script language="javascript" type="text/javascript">
function checkdate(){
    var year = document.all.year.value.trim();
    var month = document.all.month.value.trim();
    if(year==""){
    	alert("请输入年份");
    	return false;
    }
    if(month==""){
    	alert("请输入月份");
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
										<font color="00B5DB">统计查询&gt;公积金缴存情况年报表</font>
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
				<html:form action="/paymntMonthReportFindAC.do" style="margin:0">
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
								办事处
							</td>
							<td width="35%" colspan="4">
								<html:select name="af" property="officeCode" styleClass="input4">
									<option value=""></option>
									<html:options collection="officeList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="13%" class="td1">
								归集银行
							</td>
							<td width="35%">
								<html:select name="af" property="collBank" styleClass="input4">
									<option value=""></option>
									<html:options collection="bankList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								日期
								<font color="red">*</font>
							</td>
							<td width="15%">
								<html:text name="af" property="year" maxlength="8"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td align="center" width="3%">
								年
							</td>
							<td width="15%">
								<html:text name="af" property="month" maxlength="8"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="3%">
								月
							</td>
							<td>
								<html:submit property="method" styleClass="buttona"
									onclick="return checkdate();">
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
										<b class="font14">公积金缴存统计月报表 </b>
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
				<html:form action="/paymntMonthReportMaintainAC.do">
					<table width="95%" border="0" cellspacing="1" cellpadding="3"
						align="center" bgcolor="1BA5FF">
						<tr align="center">
							<td class="td1">
								&nbsp;
							</td>
							<td align="center" class="td1">
								上月末余额
							</td>
							<td align="center" class="td1">
								本月汇缴
							</td>
							<td align="center" class="td1">
								本月补缴
							</td>
							<td align="center" class="td1">
								本月挂账
							</td>
							<td align="center" class="td1">
								本月合计
							</td>
							<td align="center" class="td1">
								本月挂账累计余额
							</td>
							<td align="center" class="td1">
								当年累计
							</td>
							<td align="center" class="td1">
								当月汇缴户数
							</td>
							<td align="center" class="td1">
								当月汇缴人数
							</td>
						</tr>
						<logic:notEmpty name="infoList">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="infoList" indexId="i">
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
										<bean:write name="e" format="0.00"
											property="collBank" />
									</td>
									<td>
										<bean:write name="e" property="prevMonBalance" />
									</td>
									<td>
										<bean:write name="e" property="curMonthPay" />
									</td>
									<td>
										<bean:write name="e" property="curAddPay" />
									</td>
									<td>
										<bean:write name="e" property="curOverPay" />
									</td>
									<td>
										<bean:write name="e" property="curSumPay" />
									</td>
									<td>
										<bean:write name="e" property="overPayBalance" />
									</td>
									<td>
										<bean:write name="e" property="curYearSumPay" />
									</td>
									<td>
										<bean:write name="e" property="curMonthOrgCount" />
									</td>
									<td>
										<bean:write name="e" property="curMonthPsnCount" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="infoList">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
									<br>
								</td>
							</tr>
						</logic:empty>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<logic:empty name="infoList">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</logic:empty>
											<logic:notEmpty name="infoList">
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
