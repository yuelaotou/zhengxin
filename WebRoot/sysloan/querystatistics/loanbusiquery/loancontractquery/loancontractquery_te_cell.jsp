<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>

<%
String path = request.getContextPath();
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
		<script src="<%=path%>/js/common.js" />		
		
		
		
		<script language="JavaScript">

</script>
	</head>
	<script language="javascript">

</script>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<html:form action="/loancontractqueryTePrintAC">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="3%" align="right" valign="middle">
					<table width="21" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="112" background="<%=path%>/img/buttonbl1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryTeShowAC.do" class=a2>借款合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryTfShowAC.do" class=a2>抵押合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryTgShowAC.do" class=a2>质押合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/loancontractqueryThShowAC.do" class=a2>保证人信息</a>
							</td>
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
							<td background="<%=path%>/img/table_bg_line.gif">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											<a href="<%=path%>/sysloan/loancontractqueryTaShowAC.do" class=a2>借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loancontractqueryTbShowAC.do" class=a2>共同借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loancontractqueryTcShowAC.do" class=a2>购房信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loancontractqueryTdShowAC.do" class=a2>借款人额度信息</a>
										</td>
										<td width="112" height="37" align="center"
											style="PADDING-top: 7px" valign="top"></td>
									</tr>
								</table>
							</td>
								<td width="9">
									<img src="<%=path%>/img/table_right.gif" width="9" height="37">
								</td>
							</tr>
						</table>
						<table width="98%" border="0" cellspacing="0" cellpadding="0"
							height="500">
							<tr>
								<td valign="top" class=td3>
									<table width="95%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td height="35">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="22" bgcolor="#CCCCCC" align="center"
															width="166" class="font14">
															<b>借款合同信息</b>
														</td>
														<td height="22" background="<%=path%>/img/bg2.gif"
															align="center" width="738">
															&nbsp;
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<table width="95%" id="table9" cellspacing=1 cellpadding=3
										align="center">
										<tr>
											<td width="21%" class=td1>
												合同编号
											</td>
											<td class="td4">
												<html:text name="endorsecontractTaAF" property="contractId"
													styleClass="input3" readonly="true" />
											</td>
											<td class="td1" width="20%">
												委托方(甲方)
											</td>
											<td class="td4" width="20%">
												<html:text name="endorsecontractTaAF" property="entruster"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="21%" class=td1>
												受托方(乙方)
											</td>
											<td class="td4">
												<html:text name="endorsecontractTaAF" property="bankName"
													styleClass="input3" readonly="true" />
											</td>
											<td width="20%" class=td1>
												保证方
											</td>
											<td class="td4" width="20%">
												<font color="#FF0000"> <html:text
														name="endorsecontractTaAF" property="assurer"
														styleClass="input3" readonly="true" /> </font>
											</td>
										</tr>

										<tr>
											<td width="21%" class=td1>
												借款方(丙方)
											</td>
											<td class="td4">
												<html:text name="endorsecontractTaAF" property="debitter"
													styleClass="input3" readonly="true" />
											</td>
											<td width="20%" class=td1>
												证件类型
											</td>
											<td class="td4" width="20%">
												<font color="#FF0000"> <html:text
														name="endorsecontractTaAF" property="certificateType"
														styleClass="input3" readonly="true" /> </font>
											</td>
										</tr>
										<tr>
											<td width="21%" class=td1>
												证件号码
											</td>
											<td class="td4">
												<html:text name="endorsecontractTaAF"
													property="certificateNum" styleClass="input3"
													readonly="true" />
											</td>
											<td width="20%" class=td1>
												借款金额
											</td>
											<td class="td4" width="20%">
												<font color="#FF0000"> <html:text
														name="endorsecontractTaAF" property="debitMoney"
														styleClass="input3" readonly="true" /> </font>
											</td>
										</tr>
										<tr>
											<td class=td1 width="21%"> 
												&#20511;&#27454;&#26399;&#38480;&#65288;&#26376;&#65289; 
											</td>
											<td class="td4">
												<html:text name="endorsecontractTaAF" property="term"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1 width="20%">
												每月利率
											</td>
											<td class="td4" width="20%">
												<html:text name="endorsecontractTaAF"
													property="monthInterest" styleClass="input3"
													readonly="true" />
											</td>
										</tr>
										<tr>
											<td class=td1 width="21%" height="26">
												还款方式
											</td>
											<td class="td4" height="26">
												<html:text name="endorsecontractTaAF" property="creditType"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1 width="20%" height="26">
												合同签订日期
											</td>
											<td class="td4" width="20%" height="26">
												<html:text name="endorsecontractTaAF"
													property="contractSureDate" styleClass="input3"
													readonly="true" />
											</td>
										</tr>

										<tr>
											<td class=td1 width="21%">
												借款起始日期
											</td>
											<td class="td4">
												<font color="#FF0000"><html:text
														name="endorsecontractTaAF" property="debitMoneyStaDate"
														styleClass="input3" readonly="true" /> </font>
											</td>
											<td class=td1 width="20%">
												借款终止日期
											</td>
											<td class="td4" width="20%">
												<html:text name="endorsecontractTaAF"
													property="debitMoneyEndDate" styleClass="input3"
													readonly="true" />
											</td>
										</tr>
										<tr>
											<td class=td1 width="21%">
												月还本息
											</td>
											<td class="td4">
												<font color="#FF0000"><html:text
														name="endorsecontractTaAF" property="corpusInterest"
														styleClass="input3" readonly="true" /> </font>
											</td>
											<td class=td1 width="20%">
												扣款账号
											</td>
											<td class="td4" width="20%">
												<html:text name="endorsecontractTaAF"
													property="loanKouAcc" styleClass="input3"
													readonly="true" />
											</td>
										</tr>
									</table>
									<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr valign="bottom">
										<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="73" align="center" valign="middle">
												<html:submit styleClass="buttona" onclick="gotoSubmit();">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="73" align="center" valign="middle">
												<input type="button" name="Submit3" value="关闭" class="buttona" onclick="window.close()"/>
											</td>
										</tr>
											</table>
										</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
</html:form>
	</body>
</html>

