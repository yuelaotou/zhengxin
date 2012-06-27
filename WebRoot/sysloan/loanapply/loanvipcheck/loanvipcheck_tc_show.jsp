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
		<script src="<%=path%>/js/common.js">
		</script>

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
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="3%" align="right" valign="middle">
					<table width="21" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
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
											background="<%=path%>/img/buttonbl.gif" align="center"
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

					<table width="98%" border="0" cellspacing="0" cellpadding="0">
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
														width="209">
														<b class="font14">购 房 信 息</b>
													</td>
													<td height="22" background="<%=path%>/img/bg2.gif"
														align="center" width="651">
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
										<td rowspan="11" class=td1 width="11%">
											商品房
										</td>
										<td width="21%" class=td1>
											合同编号
										</td>
										<td class="td4" width="16%">
											<html:text property="contractId" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>

										<td class="td1" width="20%">
											借款人姓名
										</td>
										<td class="td4" width="20%">
											<html:text property="borrowerName" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="21%" class=td1>
											售房单位
										</td>
										<td class="td4" width="16%">
											<html:text property="orgName" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>

										<td class="td1" width="20%">
											联系电话
										</td>
										<td class="td4" width="20%">
											<html:text property="developerTel" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="21%" class=td1>
											售房单位售房款开户行
										</td>
										<td class="td4" width="20%">
											<html:text property="developerPaybank"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
										<td width="20%" class=td1>
											售房款开户账号
										</td>
										<td class="td4" width="20%">
											<html:text property="developerPaybankAAcc"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="21%">
											所在楼盘
										</td>
										<td class="td4" width="20%">
											<html:text property="floorId" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>

										<td class=td1 width="20%">
											楼盘号
										</td>
										<td class="td4" width="20%">
											<html:text property="floorNum" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>

									</tr>
									<tr>
										<td class=td1 width="21%">
											层次室号
										</td>
										<td class="td4">
											<html:text property="roomNum" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td width="20%" class=td1>
											是否现房
										</td>
										<td height="31" class="td4">
											<html:text property="isNowhouse" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="21%">
											房屋总价（元）
										</td>
										<td class="td4">
											<html:text property="houseTotlePrice" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="20%">
											建筑面积（M
											<sup>
												2
											</sup>
											）
										</td>
										<td class="td4" width="20%">
											<html:text property="houseArea" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td class=td1 width="21%">
											房屋单价（元/ M
											<sup>
												2）
											</sup>
										</td>
										<td class="td4">
											<html:text property="housePrice" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td class=td1 width="20%">
											购房合同编号
										</td>
										<td class="td4" width="20%">
											<html:text property="buyHouseContractId"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="21%" class=td1>
											竣工日期
										</td>
										<td height="31" class="td4">
											<html:text property="overDate" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td width="20%" class=td1>
											产权证号码
										</td>
										<td height="31" class="td4" width="20%">
											<html:text property="buildRightNum" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="21%" class=td1>
											购房合同签字日期
										</td>
										<td height="31" class="td4">
											<html:text property="agreementDate" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td width="20%" class=td1>
											&nbsp;
										</td>
										<td height="31" class="td4" width="20%">
											<input name="textfield3022112283" type="text"
												id="txtsearch45225" class="input3" readonly="true">
										</td>
									</tr>
									<tr>
										<td width="21%" class="td1">
											购房地址
										</td>
										<td colspan="4" class="td4">
											<html:text property="houseAddr" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="21%" class="td1">
											备注
										</td>
										<td colspan="4" class="td4">
											<html:text property="remark1" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<html:hidden name="loanapplytcnewAF"
											property="houseTypehidden" />

										<td width="11%" rowspan="8" bgcolor="#D1EDD7">
											二手房
										</td>
										<td width="21%" bgcolor="#D1EDD7">
											售房人姓名
										</td>
										<td class="td4">
											<html:text property="bargainorName" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td bgcolor="#D1EDD7" width="20%">
											售房人证件类型
										</td>
										<td class="td4" colspan="2">
											<html:text property="bargainorCardKind"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>

									</tr>
									<tr>
										<td width="21%" bgcolor="#D1EDD7">
											售房人证件号码
										</td>
										<td class="td4">
											<html:text property="bargainorCardNum"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
										<td bgcolor="#D1EDD7" width="20%">
											固定电话
										</td>
										<td class="td4" width="20%">
											<html:text property="bargainorTel" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="21%" bgcolor="#D1EDD7">
											原房产权编号
										</td>
										<td class="td4">
											<html:text property="bargainorHousepropertyCode"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
										<td bgcolor="#D1EDD7" width="20%">
											移动电话
										</td>
										<td class="td4" width="20%">
											<html:text property="bargainorMobile" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="21%" bgcolor="#D1EDD7">
											房屋坐落
										</td>
										<td colspan="4">
											<html:text property="bargainorHouseAddr"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="21%" bgcolor="#D1EDD7">
											售房人收款银行
										</td>
										<td class="td4">
											<html:text property="bargainorPaybank"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
										<td width="20%" bgcolor="#D1EDD7">
											售房人收款银行账号
										</td>
										<td class="td4" width="20%">
											<html:text property="bargainorPaybankAcc"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="21%" bgcolor="#D1EDD7">
											建筑面积（M
											<sup>
												2
											</sup>
											）
										</td>
										<td class="td4">
											<html:text property="bargainorHouseArea"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
										<td width="20%" bgcolor="#D1EDD7">
											评估价值（元）
										</td>
										<td class="td4" width="20%">
											<html:text property="bargainorTotlePrice"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td height="31" width="21%" bgcolor="#D1EDD7">
											房龄（年）
										</td>
										<td class="td4" height="31">
											<html:text property="bargainorHouseAge"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
										<td bgcolor="#D1EDD7" height="31" width="20%">
											协议签订日期
										</td>
										<td class="td4" height="31" width="20%">
											<html:text property="bargainorAgreementDate"
												name="loanapplytcnewAF" styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="21%" bgcolor="#D1EDD7">
											备注
										</td>
										<td class="td4" colspan="4">
											<html:text property="remark2" name="loanapplytcnewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
								</table>
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
<%--													<td width="110">--%>
<%--														<html:submit property="look" styleClass="buttonc"--%>
<%--															onclick="gotoAssist();">--%>
<%--															<bean:message key="button.borrower.assist.certificate" />--%>
<%--														</html:submit>--%>
<%--													</td>--%>
<%--													<td width="110">--%>
<%--														<html:submit property="look" styleClass="buttonc"--%>
<%--															onclick="gotoBuyhouse();">--%>
<%--															<bean:message key="button.buyhouse.certificate" />--%>
<%--														</html:submit>--%>
<%--													</td>--%>
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
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>

