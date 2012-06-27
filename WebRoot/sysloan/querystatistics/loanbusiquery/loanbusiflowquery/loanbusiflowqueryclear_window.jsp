<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>

<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">	

</head>
<script language="javascript">


</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<html:form action="/loanBusiFlowQueryClearPrintAC" style="margin: 0">
		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="<%=path%>/img/table_left.gif" width="7" height="37">
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="555">
								&nbsp;
							</td>
							<td width="635" background="<%=path%>/img/table_bg_line.gif"></td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">

							</td>
							<td width="1">
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="152">
											<strong>结 转 余 额</strong>
										</td>
										<td width="773" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<logic:notEmpty name="loanBusiFlowQueryClearAF" >
							<tr>
								<td width="17%" class="td1">
									合同编号
								</td>
								<td>
									<html:text name="loanBusiFlowQueryClearAF"
										property="loanBusiFlowQueryClearDTO.contractId" styleClass="input3" readonly="true" />
								</td>
								<td class="td1" width="17%">
									借款人姓名
								</td>
								<td width="33%">
									<html:text name="loanBusiFlowQueryClearAF"
										property="loanBusiFlowQueryClearDTO.borrowerName" styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									证件类型
								</td>
								<td>
									<html:text name="loanBusiFlowQueryClearAF"
										property="loanBusiFlowQueryClearDTO.cardKindName" styleClass="input3" readonly="true" />
								</td>
								<td width="17%" class="td1">
									证件号码
								</td>
								<td width="33%">
									<html:text name="loanBusiFlowQueryClearAF" property="loanBusiFlowQueryClearDTO.cardNum"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									放款银行
								</td>
								<td>
									<html:text name="loanBusiFlowQueryClearAF"
										property="loanBusiFlowQueryClearDTO.loanBankName" styleClass="input3" readonly="true" />
								</td>
								<td width="17%" class="td1">
									扣款账号
								</td>
								<td width="33%">
									<html:text name="loanBusiFlowQueryClearAF"
										property="loanBusiFlowQueryClearDTO.loanKouAcc" styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									借款金额
								</td>
								<td>
									<html:text name="loanBusiFlowQueryClearAF"
										property="loanBusiFlowQueryClearDTO.loanMoney" styleClass="input3" readonly="true" />
								</td>
								<td width="17%" class="td1">
									剩余本金
								</td>
								<td width="33%">
									<html:text name="loanBusiFlowQueryClearAF"
										property="loanBusiFlowQueryClearDTO.surplusCorpus" styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" height="141" class="td1">
									借款期限
								</td>
								<td>
									<html:text name="loanBusiFlowQueryClearAF"
										property="loanBusiFlowQueryClearDTO.loanTimeLimit" styleClass="input3" readonly="true" />
								</td>
								<td width="17%" class="td1">
									月利率
								</td>
								<td width="33%">
									<html:text name="loanBusiFlowQueryClearAF"
										property="loanBusiFlowQueryClearDTO.loanMonthRate" styleClass="input3" readonly="true" />
								</td>
							</tr>
						</logic:notEmpty>
					</table>
					<br>
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
								<html:submit property="print" styleClass="buttona" >
									<bean:message key="button.print" />
								</html:submit>
							</td>
							<td width="70">
								<input type="submit" name="Submit42" value="关闭" class="buttona"
									onClick="window.close();">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
