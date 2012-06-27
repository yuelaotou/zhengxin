\
<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action.PickMonthReportBankPopShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			PickMonthReportBankPopShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询--缴存提取统计--公积金提取统计月报表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
</head>

<script language="javascript" type="text/javascript">
function checkdate(){
    var date=document.all.date.value.trim();
    if(date==""){
    	alert("请输入日期");
    	document.all.date.focus();
    	return false;
    }else{
       var temp_ym = date.match(/^(([1][9]|[2][0])\d{2}([0][1-9]|[1][0-2]))$/); 
	   if(temp_ym==null){
       		alert("请正确录入年月！格式如：200707");
	    	return false;
	   }else{
		 	return true;
	   }
     }
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
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
										<font color="00B5DB">统计查询&gt;公积金缴存情况月报表&gt;银行提取明细</font>
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
				<html:form action="/pickMonthReportBankPopFindAC.do"
					style="margin:0">
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
							<td width="17%" class="td1">
								单位编号
							</td>
							<td>
								<html:text name="pickMonthReportBankPopAF" property="orgId"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td>
								<html:text name="pickMonthReportBankPopAF" property="orgName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								职工编号
							</td>
							<td>
								<html:text name="pickMonthReportBankPopAF" property="empId"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								职工名称
							</td>
							<td>
								<html:text name="pickMonthReportBankPopAF" property="empName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								提取原因
								<font color="red">&nbsp;</font>
							</td>
							<td width="33%">
								<html:select property="pickReason" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="map"
										name="pickMonthReportBankPopAF" label="value" value="key" />
								</html:select>
							</td>
							<td>
								&nbsp;
							</td>
							<td width="33%">
								&nbsp;

							</td>
							<td colspan="2" align="right">
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
										<b class="font14">银行提取明细</b>
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
				<html:form action="/pickMonthReportMaintainAC.do">
					<table width="95%" border="0" cellspacing="1" cellpadding="3"
						align="center" bgcolor="1BA5FF">
						<tr align="center">
							<td align="center" class="td1" colspan="3">
								单位编号
							</td>
							<td align="center" class="td1" colspan="3">
								单位名称
							</td>
							<td align="center" class="td1" colspan="3">
								职工编号
							</td>
							<td align="center" class="td1" colspan="3">
								职工名称
							</td>
							<td align="center" class="td1" colspan="3">
								提取金额
							</td>
							<td align="center" class="td1" colspan="3">
								提取利息
							</td>
							<td align="center" class="td1" colspan="3">
								提取总额
							</td>
							<td align="center" class="td1" colspan="3">
								原因
							</td>
						</tr>
						<logic:notEmpty name="bankinfoList">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="bankinfoList" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr class="<%=strClass%>" align="center">
									<td align="center" colspan="3">
										<bean:write name="e" format="0000000000" property="orgId" />
									</td>
									<td align="center" colspan="3">
										<bean:write name="e" property="orgName" />
									</td>
									<td align="center" colspan="3">
										<bean:write name="e" property="empId" />
									</td>
									<td align="center" colspan="3">
										<bean:write name="e" property="empName" />
									</td>
									<td align="center" colspan="3">
										<bean:write name="e" format="0.00" property="pickMoney" />
									</td>
									<td align="center" colspan="3">
										<bean:write name="e" format="0.00" property="pickInterest" />
									</td>
									<td align="center" colspan="3">
										<bean:write name="e" format="0.00" property="sumPickMoney" />
									</td>
									<td align="center" colspan="3">
										<bean:write name="e" property="pickReason" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="bankinfoList">
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
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url"
													value="pickMonthReportBankPopShowAC.do" />
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
							<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="window.close()">
												<bean:message key="button.close" />
											</html:submit>
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
