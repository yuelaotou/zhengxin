<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action.ShowYearclearEmpAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowYearclearEmpAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询-结息信息-年终结息统计</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js"></script>
</head>
<script type="text/javascript">
function loads(){
	var orgid=document.yearclearstatisticsListAF.orgId.value;
	if(orgid!=""){
	document.yearclearstatisticsListAF.orgId.value=format(document.yearclearstatisticsListAF.orgId.value)+document.yearclearstatisticsListAF.orgId.value;
	}
	
	var empid=document.yearclearstatisticsListAF.empId.value;
	if(empid!=""){
	document.yearclearstatisticsListAF.empId.value=format(document.yearclearstatisticsListAF.empId.value)+document.yearclearstatisticsListAF.empId.value;
	}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="return loads();"
	onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="yearclearEmpAC.do" />
	</jsp:include>

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=request.getContextPath()%>/img/table_left.gif"
								width="7" height="37">
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							width="55">
							&nbsp;
						</td>
						<td width="235"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif">
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询&gt;年终结息</font>
									</td>
									<td width="115" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=request.getContextPath()%>/img/table_right.gif"
								width="9" height="37">
						</td>
					</tr>
				</table>

			</td>
		</tr>
		<tr>
			<td class=td3>

				<html:form action="/yearclearEmpAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td height="22" bgcolor="#CCCCCC" align="center" width="117">
								<b class="font14">职工结息信息</b>
							</td>
							<td height="22"
								background="<%=request.getContextPath()%>/img/bg2.gif"
								align="center" colspan="5">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="33%">
								<html:text name="yearclearstatisticsListAF" property="orgId"
									onblur="return checkID();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%">
								<html:text name="yearclearstatisticsListAF" property="orgName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								职工编号
							</td>
							<td width="33%">
								<html:text name="yearclearstatisticsListAF" property="empId"
									onblur="return checkEmpID();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								职工姓名
							</td>
							<td width="33%">
								<html:text name="yearclearstatisticsListAF" property="empName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								结息前余额
							</td>
							<td width="33%">
								<html:text name="yearclearstatisticsListAF" property="oldblance"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								利息
							</td>
							<td width="33%">
								<html:text name="yearclearstatisticsListAF" property="interest"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								结息后余额
							</td>
							<td width="33%">
								<html:text name="yearclearstatisticsListAF" property="blance"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1"></td>
							<td width="33%"></td>
						</tr>
					</table>
				</html:form>


				<html:form action="/maintainYearclearEmpAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">

						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="180">
											<b class="font14">职工结息列表</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
											align="center" width="724">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="1" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="0" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								往年积数
							</td>
							<td align="center" class=td2>
								本年积数
							</td>
							<td align="center" class=td2>
								往年利率
							</td>
							<td align="center" class=td2>
								本年利率
							</td>
							<td align="center" class=td2>
								往年利息
							</td>
							<td align="center" class=td2>
								本年利息
							</td>
						</tr>

						<logic:notEmpty name="yearclearstatisticsListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="yearclearstatisticsListAF"
								property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>" >
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="element" property="aa318id"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="element" property="preIntegral" />
									</td>
									<td>
										<bean:write name="element" property="curIntegral" />
									</td>
									<td>
										<bean:write name="element" property="preRate" />
									</td>
									<td>
										<bean:write name="element" property="curRate" />
									</td>
									<td>
										<bean:write name="element" property="preinterest" />
									</td>
									<td>
										<bean:write name="element" property="curinterest" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="yearclearstatisticsListAF" property="list">
							<tr>
								<td colspan="12" height="30" style="color:red">
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
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url" value="yearclearEmpAC.do" />
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

								<logic:notEmpty name="yearclearstatisticsListAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
												&nbsp;
											</td>
											<td width="70">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
											</td>
										</tr>
									</table>
								</logic:notEmpty>

								<logic:empty name="yearclearstatisticsListAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
												&nbsp;
											</td>
											<td width="70">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
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

</body>
</html:html>
