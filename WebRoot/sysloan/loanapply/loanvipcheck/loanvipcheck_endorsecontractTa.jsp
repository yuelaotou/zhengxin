<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	List photoURLList = (List) request.getSession().getAttribute(
			"photoURLList");
	String photo_urlAssist = photoURLList.get(0) + "";
	String photo_urlBuyhouse = photoURLList.get(1) + "";
	String photo_urlLimit = photoURLList.get(2) + "";
	String photo_urlContract = photoURLList.get(3) + "";
	String photo_urlMortgage = photoURLList.get(4) + "";
	String photo_urlBorrower = photoURLList.get(5) + "";
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
		<script src="<%=path%>/js/common.js" />		
		
		
		
		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
		<script language="JavaScript">
	function gotoBorrower(){
		javascript:excHz("<%=photo_urlBorrower%>");
	}
	function gotoAssist(){
		javascript:excHz("<%=photo_urlAssist%>");
	}
	function gotoBuyhouse(){
		javascript:excHz("<%=photo_urlBuyhouse%>");
	}
	function gotoLimit(){
		javascript:excHz("<%=photo_urlLimit%>");
	}
	function gotoContract(){
		javascript:excHz("<%=photo_urlContract%>");
	}
	function gotoMortgage(){
		javascript:excHz("<%=photo_urlMortgage%>");
	}
</script>
		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
	</head>
	<script language="javascript">

</script>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
		<form method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="3%" align="right" valign="middle">
						<table width="21" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="112" background="<%=path%>/img/buttonbl1.GIF"
									align="center">
									<a href="<%=path%>/sysloan/to_LoanVIPCheckTaShowAC.do" class=a2>借款合同信息</a>
								</td>
							</tr>
							<tr>
								<td height="112" background="<%=path%>/img/buttong1.GIF"
									align="center">
									<a href="<%=path%>/sysloan/to_LoanVIPCheckTbShowAC.do" class=a2>抵押合同信息</a>
								</td>
							</tr>
							<tr>
								<td height="112" background="<%=path%>/img/buttong1.GIF"
									align="center">
									<a href="<%=path%>/sysloan/to_LoanVIPCheckTcShowAC.do" class=a2>质押合同信息</a>
								</td>
							</tr>
							<tr>
								<td height="112" background="<%=path%>/img/buttong1.GIF"
									align="center">
									<a href="<%=path%>/sysloan/to_LoanVIPCheckTdShowAC.do" class=a2>保证人信息</a>
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
												<a href="<%=path%>/sysloan/showLoanVIPCheckTaAC.do" class=a2>借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/showLoanVIPCheckTbAC.do" class=a2>共同借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/showLoanVIPCheckTcAC.do" class=a2>购房信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/showLoanVIPCheckTdAC.do" class=a2>借款人额度信息</a>
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
											<td width="17%" class=td1>
												合同编号
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF" property="contractId"
													styleClass="input3" readonly="true" />
											</td>
											<td class="td1" width="17%">
												委托方(甲方)
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF" property="entruster"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="17%" class=td1>
												受托方(乙方)
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF" property="bankName"
													styleClass="input3" readonly="true" />
											</td>
											<td width="17%" class=td1>
												保证方
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF" property="assurer"
													styleClass="input3" readonly="true" />
											</td>
										</tr>

										<tr>
											<td width="17%" class=td1>
												借款方(丙方)
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF" property="debitter"
													styleClass="input3" readonly="true" />
											</td>
											<td width="17%" class=td1>
												证件类型
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF"
													property="certificateType" styleClass="input3"
													readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="17%" class=td1>
												证件号码
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF"
													property="certificateNum" styleClass="input3"
													readonly="true" />
											</td>
											<td width="17%" class=td1>
												借款金额
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF" property="debitMoney"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td class=td1 width="17%">
												借款期限（月）
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF" property="term"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1 width="17%">
												每月利率
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF"
													property="monthInterest" styleClass="input3"
													readonly="true" />
											</td>
										</tr>
										<tr>
											<td class=td1 width="17%">
												还款方式
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF" property="creditType"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1 width="17%">
												合同签订日期
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF"
													property="contractSureDate" styleClass="input3"
													readonly="true" />
											</td>
										</tr>

										<tr>
											<td class=td1 width="17%">
												借款起始日期
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF"
													property="debitMoneyStaDate" styleClass="input3"
													readonly="true" />
											</td>
											<td class=td1 width="17%">
												借款终止日期
											</td>
											<td class="td4" width="33%">
												<html:text name="endorsecontractTaAF"
													property="debitMoneyEndDate" styleClass="input3"
													readonly="true" />
											</td>
										</tr>
									</table>
									<%--									<table width="95%" border="0" cellspacing="0" cellpadding="3"--%>
									<%--										align="center">--%>
									<%--										<tr valign="bottom">--%>
									<%--											<td width="110">--%>
									<%--												<html:submit property="look" styleClass="buttonc"--%>
									<%--													onclick="return gotoBorrower();">--%>
									<%--													<bean:message key="button.borrower.certificate" />--%>
									<%--												</html:submit>--%>
									<%--											</td>--%>
									<%--											<td width="110">--%>
									<%--												<html:submit property="look" styleClass="buttonc"--%>
									<%--													onclick="return gotoAssist();">--%>
									<%--													<bean:message key="button.borrower.assist.certificate" />--%>
									<%--												</html:submit>--%>
									<%--											</td>--%>
									<%--											<td width="110">--%>
									<%--												<html:submit property="look" styleClass="buttonc"--%>
									<%--													onclick="return gotoBuyhouse();">--%>
									<%--													<bean:message key="button.buyhouse.certificate" />--%>
									<%--												</html:submit>--%>
									<%--											</td>--%>
									<%--											<td width="110">--%>
									<%--												<html:submit property="look" styleClass="buttonc"--%>
									<%--													onclick="return gotoLimit();">--%>
									<%--													<bean:message key="button.borrower.limit.certificate" />--%>
									<%--												</html:submit>--%>
									<%--											</td>--%>
									<%--											<td width="110">--%>
									<%--												<html:submit property="look" styleClass="buttonc"--%>
									<%--													onclick="return gotoContract();">--%>
									<%--													<bean:message key="button.borrower.contract.certificate" />--%>
									<%--												</html:submit>--%>
									<%--											</td>--%>
									<%--											<td width="110">--%>
									<%--												<html:submit property="look" styleClass="buttonc"--%>
									<%--													onclick="return gotoMortgage();">--%>
									<%--													<bean:message key="button.borrower.mortgage.certificate" />--%>
									<%--												</html:submit>--%>
									<%--											</td>--%>
									<%--									</table>--%>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
		<table width="95%" border="0" cellspacing="0" cellpadding="3"
			align="center">
			<tr valign="bottom">
				<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="110">
								<html:submit property="look" styleClass="buttonc"
									onclick="gotoBorrower();">
									<bean:message key="button.borrower.certificate" />
								</html:submit>
							</td>
<%--							<td width="110">--%>
<%--								<html:submit property="look" styleClass="buttonc"--%>
<%--									onclick="gotoAssist();">--%>
<%--									<bean:message key="button.borrower.assist.certificate" />--%>
<%--								</html:submit>--%>
<%--							</td>--%>
<%--							<td width="110">--%>
<%--								<html:submit property="look" styleClass="buttonc"--%>
<%--									onclick="gotoBuyhouse();">--%>
<%--									<bean:message key="button.buyhouse.certificate" />--%>
<%--								</html:submit>--%>
<%--							</td>--%>
							<td width="110">
								<html:submit property="look" styleClass="buttonc"
									onclick="gotoContract();">
									<bean:message key="button.borrower.contract.certificate" />
								</html:submit>
							</td>
							<td width="110">
								<html:submit property="look" styleClass="buttonc"
									onclick="gotoLimit();">
									<bean:message key="button.borrower.limit.certificate" />
								</html:submit>
							</td>
							<td width="110">
								<html:submit property="look" styleClass="buttonc"
									onclick="gotoMortgage();">
									<bean:message key="button.borrower.mortgage.certificate" />
								</html:submit>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

