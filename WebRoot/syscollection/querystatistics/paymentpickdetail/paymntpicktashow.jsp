<%@ page language="java" import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickdetail.action.PaymntPickTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			PaymntPickTaShowAC.PAGINATION_KEY);
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
function check(){
	var year = document.all.year.value.trim();
	var month = document.all.month.value.trim();
	if(year == "")
	{
		alert("请输入年份");
		return false;
	}
	if(month == "")
	{
		alert("请输入月份");
		return false;
	}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<table width="120%" border="0" cellspacing="0" cellpadding="0"
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
										<font color="00B5DB">统计查询&gt;公积金归集支取明细表</font>
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
				<html:form action="/paymntPickTaFindAC.do" style="margin:0">
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
							<td width="37%">
								<html:select name="paymntPickAF" property="office"
									styleClass="input4">
									<option value=""></option>
									<html:options collection="officeList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="13%" class="td1">
								日期
								<font color="red">*</font>
							</td>
							<td width="16%">
								<html:text name="paymntPickAF" property="year"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="3%" align="center">
								年
							</td>
							<td width="16%">
								<html:text name="paymntPickAF" property="month"
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
						<td class=h4>
							全地区归集公积金
							<u>:<bean:write name="paymntPickAF" property="payMoneySum"
									format="0.00" /> </u> 全地区支取公积金
							<u>：<bean:write name="paymntPickAF" property="pickMoneySum"
									format="0.00" /> </u>
						</td>
					</tr>
					<tr>
						<td class=h4>
							<logic:notEmpty name="paymntPickAF" property="payMoneySum_sbj">
							市本级归集公积金  
							<u>:<bean:write name="paymntPickAF"
										property="payMoneySum_sbj" format="0.00" /> </u>
							</logic:notEmpty>
							<logic:notEmpty name="paymntPickAF" property="pickMoneySum_sbj">
							市本级支取公积金
							<u>：<bean:write name="paymntPickAF"
									property="pickMoneySum_sbj" format="0.00" /> </u>
									</logic:notEmpty>
						</td>
					</tr>
					<tr>
						<td class=h4>
							<logic:notEmpty name="paymntPickAF" property="payMoneySum_xq">
							县区归集公积金  
							<u>:<bean:write name="paymntPickAF"
										property="payMoneySum_xq" format="0.00" /> </u>
							</logic:notEmpty>
							<logic:notEmpty name="paymntPickAF" property="pickMoneySum_xq">
							县区支取公积金
							<u>：<bean:write name="paymntPickAF"
									property="pickMoneySum_xq" format="0.00" /> </u>
									</logic:notEmpty>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="216">
										<b class="font14">公积金归集支取明细表</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="688">
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
				<html:form action="/paymntPickTaMaintainAC.do">
					<table width="95%" border="0" cellspacing="1" cellpadding="3"
						align="center" bgcolor="1BA5FF">
						<logic:notEmpty name="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="list">
								<%
								if (j == 0) {
								%>
								<tr height="20">
									<td rowspan="2" class="td1" align="center">
										日期
									</td>
									<logic:iterate id="element" name="e" property="list">
										<td colspan="2" align="center" class="td1">
											<bean:write name="element" property="collBankId" />
										</td>
									</logic:iterate>
								</tr>
								<tr height="20">
									<logic:iterate id="element" name="e" property="list">
										<td align="center" class="td1">
											归集额
										</td>
										<td align="center" class="td1">
											支取额
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr class="<%=strClass%>">
									<td>
										<bean:write name="e" property="date" />
									</td>
									<logic:iterate id="element" name="e" property="list">
										<td align="right">
											<bean:write name="element" property="payMoney"
												format="#,##0.00" />
										</td>
										<td align="right">
											<bean:write name="element" property="pickMoney"
												format="#,##0.00" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
						</logic:empty>
					</table>

				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
