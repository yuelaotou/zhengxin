<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.action.*"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			LoanBusiFlowQueryBailClearShowWindowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script>
function gotoCell(){
location.href='<%=path%>/sysloan/querystatistics/loanbusiquery/loanbusiflowquery/loanbusiflowquerybailclearwindow_cell.jsp';
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="204">
										<b class="font14">银 行 信 息</b>
									</td>
									<td width="706" height="22" align="center"
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
						<td width="20%" class="td1">
							放款银行
						</td>
						<td width="30%">
							<html:text name="loanBusiFlowQueryBailClearAF"
								property="loanBankName" styleClass="input3" readonly="true" />
						</td>
						<td width="20%" class="td1"></td>
						<td width="30%" class="td7">
							&nbsp;
						</td>
					</tr>

				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							合计：结息前总保证金
							<u>：<bean:write name="loanBusiFlowQueryBailClearAF"
									property="bailBalanceTotle" /> </u> 总结息 利息
							<u>：<bean:write name="loanBusiFlowQueryBailClearAF"
									property="occurMoneyTotle" /> </u> 结息后总保证金
							<u>：<bean:write name="loanBusiFlowQueryBailClearAF"
									property="lastBalanceTotle" /> </u>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="206">
										<b class="font14">保证金结息列表</b>
									</td>
									<td width="704" height="22" align="center"
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
				<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
						<td align="center" class=td2>
							结息年度
						</td>
						<td align="center" class=td2>
							放款银行
						</td>
						<td align="center" class=td2>
							扣款账号
						</td>
						<td align="center" class=td2>
							借款人姓名
						</td>
						<td align="center" class=td2>
							结息前保证金
						</td>
						<td align="center" class=td2>
							结息利息
						</td>
						<td align="center" class=td2>
							结息后保证金
						</td>
					</tr>
					<logic:notEmpty name="loanBusiFlowQueryBailClearAF" property="list">
					<% int j=0;
			String strClass="";
		%>
						<logic:iterate name="loanBusiFlowQueryBailClearAF" property="list"
							id="element" indexId="i">
							<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
							<tr  class="<%=strClass%>">
								<td>
									<bean:write name="element" property="bizYear" />
								</td>
								<td>
									<bean:write name="element" property="loanBankName" />
								</td>
								<td>
									<bean:write name="element" property="loanKouAcc" />
								</td>
								<td>
									<bean:write name="element" property="borrowerName" />
								</td>
								<td>
									<bean:write name="element" property="bailBalance" />
								</td>
								<td>
									<bean:write name="element" property="occurMoney" />
								</td>
								<td>
									<bean:write name="element" property="lastBalance" />
								</td>
							</tr>
							
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="loanBusiFlowQueryBailClearAF" property="list">
						<tr>
							<td colspan="4" height="30" style="color:red">
								没有找到与条件相符合的结果！
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
												value="loanBusiFlowQueryBailClearShowWindowAC.do" />
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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="70">
										<logic:notEmpty name="loanBusiFlowQueryBailClearAF"
											property="list">
											<html:submit property="print" styleClass="buttona"
												onclick="gotoCell();">
												<bean:message key="button.print" />
											</html:submit>
										</logic:notEmpty>
										<logic:empty name="loanBusiFlowQueryBailClearAF"
											property="list">
											<html:submit property="method" styleClass="buttona"
												disabled="true">
												<bean:message key="button.print" />
											</html:submit>
										</logic:empty>
									</td>
									<td width="70">
										<input type="submit" name="Submit42" value="关闭"
											class="buttona" onClick="window.close();">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>
