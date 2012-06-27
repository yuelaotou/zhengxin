<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.form.LoanapplyNewAF"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	List photoURLList = (List) request.getSession().getAttribute(
			"photoURLList");
	String photo_urlAssist = photoURLList.get(0) + "";
	String photo_urlBuyhouse = photoURLList.get(1) + "";
	String photo_urlLimit = photoURLList.get(2) + "";//pl114他项权利证--
	String photo_urlContract = photoURLList.get(3) + "";//pl120签订合同-扫描的是房屋信息
	String photo_urlMortgage = photoURLList.get(4) + "";//pl115其他
	String photo_urlBorrower = photoURLList.get(5) + "";//pl110基本信息
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script src="<%=path%>/js/common.js" />
	

		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
		<script language="JavaScript">
	function gotoBorrower(){
		javascript:excHz("<%=photo_urlBorrower%>");
	}
	function gotoContract(){
		javascript:excHz("<%=photo_urlContract%>");
	}
	function gotoLimit(){
		javascript:excHz("<%=photo_urlLimit%>");
	}
	function gotoMortgage(){
		javascript:excHz("<%=photo_urlMortgage%>");
	}
</script>
		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
	</head>
	<script language="javascript">
function loads(){
	var empId = document.forms[0].elements["borrower.empId"].value.trim();
	if(empId==null||empId==""){
		document.all.look.disabled="true";
	}
	var str;
	if(empId!="0"&&empId!=""){
		str = "";
		for(var i=0;i<6-empId.length;i++){
			str += "0";
		}
		document.forms[0].elements["borrower.empId"].value = str+empId;
	}
	var orgId = document.forms[0].elements["borrower.orgId"].value;
	if(orgId!="0"){
		str = "";
		for(var i=0;i<10-orgId.length;i++){
			str += "0";
		}
		document.forms[0].elements["borrower.orgId"].value = str+orgId;
	}
}
function gotoEmpAccount(){
	var borrowerName = document.forms[0].elements["borrower.borrowerName"].value.trim();
	var cardNum = document.forms[0].elements["borrower.cardNum"].value.trim();
	borrowerName =encodeURI(borrowerName);
	window.open('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'newwindow','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=no,location=no,status=no');
}
</script>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
		onload="loads();">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="3%" align="right" valign="middle">
					<table width="21" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/to_LoanCheckTaShowAC.do" class=a2>借款合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/to_LoanCheckTbShowAC.do" class=a2>抵押合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/to_LoanCheckTcShowAC.do" class=a2>质押合同信息</a>
							</td>
						</tr>
						<tr>
							<td height="112" background="<%=path%>/img/buttong1.GIF"
								align="center">
								<a href="<%=path%>/sysloan/to_LoanCheckTdShowAC.do" class=a2>保证人信息</a>
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
											background="<%=path%>/img/buttonbl.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											<a href="<%=path%>/sysloan/showLoanCheckTaAC.do" class=a2>借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/showLoanCheckTbAC.do" class=a2>共同借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/showLoanCheckTcAC.do" class=a2>购房信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/showLoanCheckTdAC.do" class=a2>借款人额度信息</a>
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
								<form style="margin: 0">
									<table width="95%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td height="35">
												<table width="100%" border="0" cellspacing="3"
													cellpadding="0">
													<tr>
														<td width="12%" class=td1>
															所属办事处
														</td>
														<td width="28%" class="td4">
															<html:text name="loanapplynewAF"
																property="borrower.office" styleClass="input3"
																readonly="true" />
														</td>
														<td width="1%">
															&nbsp;
														</td>
														<td class="td4" width="17%">
															&nbsp;
														</td>
														<td width="12%">
															&nbsp;
														</td>
														<td class="td4" width="30%">
															&nbsp;
														</td>
													</tr>
												</table>
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="22" bgcolor="#CCCCCC" align="center"
															width="166">
															<b class="font14">借 款 人 基 本 信 息</b>
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

									<table border="0" width="95%" id="table1" cellspacing=1
										cellpadding=3 align="center">
										<tr>
											<td class=td1 width="12%">
												职工编号
											</td>
											<td class="td4">
												<html:text property="borrower.empId" name="loanapplynewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td class="td1" width="12%">
												借款人姓名
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.borrowerName"
													name="loanapplynewAF" styleClass="input3" maxlength="8"
													readonly="true" />
											</td>
											<td class=td1 width="12%">
												性别
											</td>
											<td class="td4" width="30%">
												<html:text name="loanapplynewAF" property="borrower.sex"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												证件类型
											</td>
											<td class="td4">
												<html:text name="loanapplynewAF"
													property="borrower.cardKind" styleClass="input3"
													readonly="true" />
											</td>
											<td width="12%" class=td1>
												证件号码
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.cardNum" name="loanapplynewAF"
													styleClass="input3" maxlength="18" readonly="true" />
											</td>
											<td width="12%" class=td1>
												出生日期
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.birthday"
													name="loanapplynewAF" styleClass="input3" maxlength="8"
													readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												年龄
											</td>
											<td class="td4">
												<html:text property="borrower.age" name="loanapplynewAF"
													styleClass="input3" maxlength="3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												职务
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.business"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												职称
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.title" name="loanapplynewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												民族
											</td>
											<td class="td4">
												<html:text property="borrower.nation" name="loanapplynewAF"
													styleClass="input3" maxlength="8" readonly="true" />
											</td>
											<td width="12%" class=td1>
												户籍所在地
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.nativePlace"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												婚姻状况
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.marriageSt"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												文化程度
											</td>
											<td class="td4">
												<html:text property="borrower.degree" name="loanapplynewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												家庭住址
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.homeAddr"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												邮政编码
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.homeMail"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												住宅电话
											</td>
											<td class="td4">
												<html:text property="borrower.houseTel"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												移动电话
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.homeMobile"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td class="td4" colspan="2">
												<html:submit property="look" styleClass="buttonc"
													onclick="gotoEmpAccount();">
													<bean:message key="button.look.empinfo" />
												</html:submit>
											</td>
										</tr>
									</table>
									<table width="95%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td height="35">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="22" bgcolor="#CCCCCC" align="center"
															width="224">
															<b class="font14">借 款 人 公 积 金 归 集 信 息</b>
														</td>
														<td height="22" background="<%=path%>/img/bg2.gif"
															align="center" width="636">
															&nbsp;
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<table border="0" width="95%" id="table1" cellspacing=1
										cellpadding=3 align="center">
										<tr>
											<td width="12%" class=td1>
												单位编号
											</td>
											<td class="td4" colspan="2">
												<html:text property="borrower.orgId" name="loanapplynewAF"
													styleClass="input3" readonly="true"
													onkeydown="enterNextFocus1();" />
											</td>
											<td width="12%" class=td1>
												单位名称
											</td>
											<td class="td4" width="17%" colspan="3">
												<html:text property="borrower.orgName" name="loanapplynewAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>

										</tr>
										<tr>
											<td width="12%" class=td1>
												单位电话
											</td>
											<td class="td4" colspan="2">
												<html:text property="borrower.orgTel" name="loanapplynewAF"
													styleClass="input3" maxlength="20"
													onkeydown="enterNextFocus1();" />
											</td>
											<td width="12%" class=td1>
												邮政编码
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.orgMail" name="loanapplynewAF"
													styleClass="input3" maxlength="8"
													onkeydown="enterNextFocus1();" />
											</td>
											<td width="12%" class=td1>
												账户状态
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.empSt_" name="loanapplynewAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>



										</tr>
										<tr>
											<td width="12%" class=td1>
												单位地址
											</td>
											<td class="td4" colspan="4">
												<html:text property="borrower.orgAddr" name="loanapplynewAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
											<td width="12%" class=td1>
												月工资额
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.monthSalary"
													name="loanapplynewAF" styleClass="input3"
													onkeydown="enterNextFocus1();" />
											</td>


										</tr>
										<tr>
											<td width="12%" class=td1>
												月缴存额
											</td>
											<td class="td4" colspan="2">
												<html:text property="borrower.monthPay"
													name="loanapplynewAF" styleClass="input3" maxlength="20"
													onkeydown="enterNextFocus1();" />
											</td>
											<td width="12%" class=td1>
												账户余额
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.accBlnce"
													name="loanapplynewAF" styleClass="input3" maxlength="20"
													onkeydown="enterNextFocus1();" />
											</td>
											<td width="12%" class=td1>
												其他欠款
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.otherArrearage_"
													name="loanapplynewAF" styleClass="input3"
													onkeydown="enterNextFocus1();" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												初缴年月
											</td>
											<td class="td4" colspan="2">
												<html:text property="borrower.bgnDate" name="loanapplynewAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
											<td width="12%" class=td1>
												缴至年月
											</td>
											<td class="td4">
												<html:text property="borrower.endDate" name="loanapplynewAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
										</tr>
									</table>
									<table width="95%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td height="35">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="22" bgcolor="#CCCCCC" align="center"
															width="172">
															<strong class="font14">其 他 联 系 人 信 息</strong>
														</td>
														<td height="22" background="<%=path%>/img/bg2.gif"
															align="center" width="732">
															&nbsp;
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
									<table border="0" width="95%" id="table1" cellspacing=1
										cellpadding=3 align="center">
										<tr>
											<td width="12%" class=td1>
												姓名
											</td>
											<td class="td4" colspan="2">
												<html:text property="borrower.contactAName"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												与借款人关系
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.relationA"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												工作单位
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.contactAOrgName"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												单位电话
											</td>
											<td class="td4" colspan="2">
												<html:text property="borrower.contactAOrgTel"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												住宅电话
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.contactAHouseTel"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												移动电话
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.contactAMobile"
													name="loanapplynewAF" styleClass="input3" maxlength="11"
													readonly="true" />
											</td>
										</tr>
									</table>
									<table border="0" width="95%" id="table1" cellspacing=1
										cellpadding=3 align="center">
										<tr>
											<td width="12%" class=td1>
												姓名
											</td>
											<td class="td4" colspan="2">
												<html:text property="borrower.contactBName"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												与借款人关系
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.relationB"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												工作单位
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.contactBOrgName"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												单位电话
											</td>
											<td class="td4" colspan="2">
												<html:text property="borrower.contactBOrgTel"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												住宅电话
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.contactBHouseTel"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												移动电话
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.contactBMobile"
													name="loanapplynewAF" styleClass="input3" maxlength="11"
													readonly="true" />
											</td>
										</tr>
									</table>
									<table border="0" width="95%" id="table1" cellspacing=1
										cellpadding=3 align="center">
										<tr>
											<td width="12%" class=td1>
												姓名
											</td>
											<td class="td4" colspan="2">
												<html:text property="borrower.contactCName"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												与借款人关系
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.relationC"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												工作单位
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.contactCOrgName"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												单位电话
											</td>
											<td class="td4" colspan="2">
												<html:text property="borrower.contactCOrgTel"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												住宅电话
											</td>
											<td class="td4" width="17%">
												<html:text property="borrower.contactCHouseTel"
													name="loanapplynewAF" styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												移动电话
											</td>
											<td class="td4" width="30%">
												<html:text property="borrower.contactCMobile"
													name="loanapplynewAF" styleClass="input3" maxlength="11"
													readonly="true" />
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

