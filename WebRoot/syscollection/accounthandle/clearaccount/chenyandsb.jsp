<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action.OrgChgShowTbAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			OrgChgShowTbAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>结算单查询-日账查询</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>

<script language="javascript" type="text/javascript">

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);    
	</logic:messagesPresent>
}

</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="reportErrors();">
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
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">日账查询</font>
									</td>
									<td width="74" class=font14>
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
										<b class="font14">查 询 信 息</b>
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
				<html:form action="/chenYanDSBFindAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1" algin="center">
								查询年月
							</td>
							<td width="22%">
								<html:text name="chenYanDSBAF" property="yearmonth"
									styleClass="input3" styleId="txtsearch" maxlength="6">
								</html:text>
							</td>
							<td class="td1" width="17%">
								归集银行
							</td>
							<td width="33%">
								<html:select property="bank1" styleClass="input4">
									<html:option value=""></html:option>
									<html:options collection="bankList1" property="value"
										labelProperty="label" />
								</html:select>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">日账列表</b>
									</td>
									<td width="750" height="22" align="center"
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
				<html:form action="/chenYanDSBPrintAC.do" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" class=td2>
								业务日期
							</td>
							<td align="center" class=td2>
								汇缴
							</td>
							<td align="center" class=td2>
								支取
							</td>
							<td align="center" class=td2>
								转入
							</td>
							<td align="center" class=td2>
								转出
							</td>
							<td align="center" class=td2>
								调增
							</td>
							<td align="center" class=td2>
								调减
							</td>
							<td align="center" class=td2>
								按月还贷
							</td>
							<td align="center" class=td2>
								总余额
							</td>
						</tr>
						<logic:notEmpty name="chenYanDSBAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="chenYanDSBAF" property="list" id="element"
								indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr class="<%=strClass%>">
									<td valign="top" align="center">
											<bean:write name="element" property="bizdate" />
									</td>
									<td valign="top" align="right">
											<bean:write name="element" property="monthpay" />
									</td>
									<td valign="top" align="right">
											<bean:write name="element" property="pickup" />
									</td>
									<td valign="top" align="right">
											<bean:write name="element" property="tranin" />
									</td>
									<td valign="top" align="right">
											<bean:write name="element" property="tranout" />
									</td>
									<td valign="top" align="right">
											<bean:write name="element" property="chgup" />
									</td>
									<td valign="top" align="right">
											<bean:write name="element" property="chgdown" />
									</td>
									<td valign="top" align="right">
											<bean:write name="element" property="pickloan" />
									</td>
									<td valign="top" align="right">
											<bean:write name="element" property="balance" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="chenYanDSBAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
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
</html:html>
