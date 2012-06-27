<%@ page language="java" import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.DepositCheckAccWindowBaseDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>银行存款余额调节表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>

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
									<td width="200" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<font color="00B5DB">出纳管理&gt;银行存款余额调节表</font>
										</p>
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
			<html:form action="/depositCheckAccWindowPrintAC.do"
					styleClass="margin: 0">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="170">
										<b class="font14">银行存款余额调节表</b>
									</td>
									<td width="753" height="22" align="center"
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
				<table width="95%" border="0" cellspacing="1" cellpadding="6"
					align="center" bgcolor="1BA5FF">
					<tr align="center">
						<td class="td1">
							项目
						</td>
						<td class="td1">
							金额
						</td>
						<td class="td1">
							项目
						</td>
						<td class="td1">
							金额
						</td>
					</tr>
					<tr id="tr1" align="left" bgcolor="#FFFFFF">
						<td height="22">
							银行日记账余额
						</td>
						<td height="22" align="right">
							<bean:write name="depositCheckAccWindowAF"
								property="depositCheckAccWindowDTO.bdcBalanceSum" />
						</td>
						<td height="22">
							银行对账单余额
						</td>
						<td height="22" align="right">
							<bean:write name="depositCheckAccWindowAF"
								property="depositCheckAccWindowDTO.bcaBalanceSum" />
						</td>
					</tr>
					<tr bgcolor="#FFFFFF" align="left">
						<td height="22">
							&nbsp;&nbsp;&nbsp;&nbsp;加：银行已收中心未收
						</td>
						<td height="22" align="right">
							<bean:write name="depositCheckAccWindowAF"
								property="depositCheckAccWindowDTO.bankMoneySum" />
						</td>
						<td height="22">
							&nbsp;&nbsp;&nbsp;&nbsp;加：中心已收银行未收
						</td>
						<td height="22" align="right">
							<bean:write name="depositCheckAccWindowAF"
								property="depositCheckAccWindowDTO.officeMoneySum" />
						</td>
					</tr>
					<%
							List bankList = (List) request.getAttribute("bankList");
							List officeList = (List) request.getAttribute("officeList");
							int sizeFI = 0;
							if (bankList.size() > officeList.size()) {
								sizeFI = bankList.size();
							} else {
								sizeFI = officeList.size();
							}
					%>
					<%
							int j = 0;
							int z = 0;
					%>
					<%
					for (int i = 0; i < sizeFI; i++) {
					%>
					<tr bgcolor="#FFFFFF" align="left">
						<%
						if (j < bankList.size()) {
						%>
						<%
										DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO = (DepositCheckAccWindowBaseDTO) bankList
										.get(j);
						%>
						<td height="22">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<%=i + 1 + ".结算日期："
								+ depositCheckAccWindowBaseDTO.getSettDate()
								+ " 结算号："
								+ depositCheckAccWindowBaseDTO.getSettNum()%>
						</td>
						<td height="22" align="right">
							<%=depositCheckAccWindowBaseDTO.getMoney()%>
						</td>
						<%
									j++;
									} else {
						%>
						<td height="22"></td>
						<td height="22"></td>
						<%
						}
						%>
						<%
						if (z < officeList.size()) {
						%>
						<%
										DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO1 = (DepositCheckAccWindowBaseDTO) officeList
										.get(z);
						%>
						<td height="22">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<%=i + 1 + ".结算日期："
								+ depositCheckAccWindowBaseDTO1.getSettDate()
								+ " 结算号："
								+ depositCheckAccWindowBaseDTO1.getSettNum()%>
						</td>
						<td height="22" align="right">
							<%=depositCheckAccWindowBaseDTO1
												.getMoney()%>
						</td>
						<%
									z++;
									} else {
						%>
						<td height="22"></td>
						<td height="22"></td>
						<%
						}
						%>
					</tr>
					<%
					}
					%>
					<tr bgcolor="#FFFFFF" align="left">
						<td height="22">
							&nbsp;&nbsp;&nbsp;&nbsp;减：银行已付中心未付
						</td>
						<td height="22" align="right">
							<bean:write name="depositCheckAccWindowAF"
								property="depositCheckAccWindowDTO.bankOutMoneySum" />
						</td>
						<td height="22">
							&nbsp;&nbsp;&nbsp;&nbsp;减：中心已付银行未付
						</td>
						<td height="22" align="right">
							<bean:write name="depositCheckAccWindowAF"
								property="depositCheckAccWindowDTO.officeOutMoneySum" />
						</td>
					</tr>
					<%
							List bankOutList = (List) request.getAttribute("bankOutList");
							List officeOutList = (List) request
							.getAttribute("officeOutList");
							int sizeSE = 0;
							if (bankOutList.size() > officeOutList.size()) {
								sizeSE = bankOutList.size();
							} else {
								sizeSE = officeOutList.size();
							}
					%>
					<%
							int m = 0;
							int n = 0;
					%>
					<%
					for (int i = 0; i < sizeSE; i++) {
					%>
					<tr bgcolor="#FFFFFF" align="left">
						<%
						if (m < bankOutList.size()) {
						%>
						<%
										DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO = (DepositCheckAccWindowBaseDTO) bankOutList
										.get(m);
						%>
						<td height="22">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<%=i + 1 + ".结算日期："
								+ depositCheckAccWindowBaseDTO.getSettDate()
								+ " 结算号："
								+ depositCheckAccWindowBaseDTO.getSettNum()%>
						</td>
						<td height="22" align="right">
							<%=depositCheckAccWindowBaseDTO.getMoney()%>
						</td>
						<%
									m++;
									} else {
						%>
						<td height="22"></td>
						<td height="22"></td>
						<%
						}
						%>
						<%
						if (n < officeOutList.size()) {
						%>
						<%
										DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO1 = (DepositCheckAccWindowBaseDTO) officeOutList
										.get(n);
						%>
						<td height="22">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<%=i + 1 + ".结算日期："
								+ depositCheckAccWindowBaseDTO1.getSettDate()
								+ " 结算号："
								+ depositCheckAccWindowBaseDTO1.getSettNum()%>
						</td>
						<td height="22" align="right">
							<%=depositCheckAccWindowBaseDTO1
												.getMoney()%>
						</td>
						<%
									n++;
									} else {
						%>
						<td height="22"></td>
						<td height="22"></td>
						<%
						}
						%>
					</tr>
					<%
					}
					%>
					<tr bgcolor="#FFFFFF" align="left">
						<td height="22" class="td1">
							调节后余额（中心）
						</td>
						<td height="22" class="td1" align="right">
							<bean:write name="depositCheckAccWindowAF"
								property="depositCheckAccWindowDTO.adjustOfficeBalance" />
						</td>
						<td height="22" class="td1">
							调节后余额（银行）
						</td>
						<td height="22" class="td1" align="right">
							<bean:write name="depositCheckAccWindowAF"
								property="depositCheckAccWindowDTO.adjustBankBalance" />
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr valign="bottom">
						<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="65" align="right">
									<INPUT id="Button3" onclick="location.href='depositCheckAccShowAC.do'" type="button" class="buttona" value=" 返回 ">	
									</td>
									<td width="65" align="right">
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
</body>
</html:html>
