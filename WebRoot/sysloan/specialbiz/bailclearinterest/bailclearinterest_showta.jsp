<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.specialbiz.bailclearinterest.action.*"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			BailClearInterestTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>查询条件，办理保证金结息列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script>

function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function gotoClear() {
	if(confirm("是否全部结息？")){
		return true;
	} else {
		return false;
	}
}


</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">
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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="bailClearInterestTaForwardAC.do" class=a2>办理保证金结息</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="bailClearInterestTbForwardAC.do" class=a2>保证金结息维护</a>
									</td>
								</tr>
							</table>
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
										<font color="00B5DB">特殊业务处理&gt;保证金结息</font>
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
				<html:form action="/bailClearInterestTaFindAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="205">
											<b class="font14">查 询 条 件</b>
										</td>
										<td width="705" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>

						</tr>
						<tr>
							<td width="13%" class="td1">
								放款银行
							</td>
							<td width="18%">
								<logic:notEmpty name="loanBankNameList">
									<html:select name="bailClearInterestTaAF"
										property="loanBankName" styleClass="input3">
										<html:option value=""></html:option>
										<html:options collection="loanBankNameList" property="value"
											labelProperty="label" />
									</html:select>
								</logic:notEmpty>
							</td>
							<td width="11%" class="td1">
								&nbsp;
							</td>
							<td width="21%" class="td7">
								&nbsp;
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
				<html:form action="/bailClearInterestTaMaintainAC.do"
					style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：结息前保证金
								<u>：<bean:write name="bailClearInterestTaAF"
										property="bailBalanceTotle" /> </u>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="202">
											<b class="font14">保证金结息列表</b>
										</td>
										<td width="708" height="22" align="center"
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
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td align="center" bgcolor="C4F0FF">
								放款银行
							</td>
							<td align="center" class=td2>
								定期利率
							</td>
							<td align="center" class=td2>
								活期利率
							</td>
							<td align="center" class=td2>
								贷款账号
							</td>
							<td align="center" class=td2>
								合同编号
							</td>
							<td align="center" class=td2>
								借款人姓名
							</td>
							<td align="center" class=td2>
								证件号码
							</td>
							<td align="center" class=td2>
								结息前保证金
							</td>
						</tr>
						<logic:notEmpty name="bailClearInterestTaAF" property="list">
							<logic:iterate name="bailClearInterestTaAF" property="list"
								id="element" indexId="i">
								<tr class=td4>
									<td>
										<bean:write name="element" property="loanBankName" />
									</td>
									<td>
										<bean:write name="element" property="paramExplainInterestD" />
									</td>
									<td>
										<bean:write name="element" property="paramExplainInterestL" />
									</td>
									<td>
										<bean:write name="element" property="loanKouAcc" />
									</td>
									<td>
										<bean:write name="element" property="contractId" />
									</td>
									<td>
										<bean:write name="element" property="borrowerName" />
									</td>
									<td>
										<bean:write name="element" property="cardNum" />
									</td>
									<td>
										<bean:write name="element" property="bailBalance" />
									</td>
								</tr>
								<tr>
									<td colspan="12" class=td5></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="bailClearInterestTaAF" property="list">
							<tr>
								<td colspan="4" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>
							<tr>
								<td colspan="7"></td>
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
												<jsp:param name="url" value="bailClearInterestTaShowAC.do" />
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
								<logic:notEmpty name="bailClearInterestTaAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<html:submit property="method" styleClass="buttona"
													onclick="return gotoClear();">
													<bean:message key="button.in.clearaccountall" />
												</html:submit>
											</td>
										</tr>
									</table>
								</logic:notEmpty>
								<logic:empty name="bailClearInterestTaAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.in.clearaccountall" />
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
</body>
</html:html>
