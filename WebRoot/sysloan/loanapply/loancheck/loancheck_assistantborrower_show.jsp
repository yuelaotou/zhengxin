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
var tr='tr0';

function loads(){
	tr='tr0';
	var count = document.loanapplytbnewAF.elements["count"].value.trim();
	if(count!='0'){
		var empid=document.getElementById(tr).childNodes[1].childNodes[0].innerHTML;
		if(empid==""){
			document.all.look.disabled="true";
		}
	}else{
		document.all.look.disabled="true";
	}
}

function executeAjaxIn(trindex) {
	tr=trindex;
	var assitId=getCheckId();
	var queryString = "findLoanCheckTbEmpInfoAAC.do?";
    queryString = queryString + "assitId="+assitId;    
	findInfo(queryString);	  
}

function displayIn(empId,name,relation,sex,cardKind,cardNum,birthday,age,nation,nativePlace,business,title,marriageSt,degree,homeAddr,homeMail,homeMobile,houseTel,orgId,orgName,orgTel,orgAddr,orgMail,accBlnce,monthSalary,monthPay,empSt,bgnDate,endDate){		
				
		document.loanapplytbnewAF.elements["empId"].value=empId;
		document.loanapplytbnewAF.elements["name"].value=name;
		document.loanapplytbnewAF.elements["relation"].value=relation;
		document.loanapplytbnewAF.elements["sex"].value=sex;
		document.loanapplytbnewAF.elements["cardKind"].value=cardKind;
		document.loanapplytbnewAF.elements["cardNum"].value=cardNum;
		document.loanapplytbnewAF.elements["birthday"].value=birthday;
		document.loanapplytbnewAF.elements["age"].value=age;
		document.loanapplytbnewAF.elements["nation"].value=nation
		document.loanapplytbnewAF.elements["nativePlace"].value=nativePlace;
		document.loanapplytbnewAF.elements["business"].value=business;
		document.loanapplytbnewAF.elements["title"].value=title;
		document.loanapplytbnewAF.elements["marriageSt"].value=marriageSt;
		document.loanapplytbnewAF.elements["degree"].value=degree;
		document.loanapplytbnewAF.elements["homeAddr"].value=homeAddr;
		document.loanapplytbnewAF.elements["homeMail"].value=homeMail;
		document.loanapplytbnewAF.elements["homeMobile"].value=homeMobile;
		document.loanapplytbnewAF.elements["houseTel"].value=houseTel;
		document.loanapplytbnewAF.elements["orgId"].value=orgId;
		document.loanapplytbnewAF.elements["orgName"].value=orgName;
		document.loanapplytbnewAF.elements["orgTel"].value=orgTel;
		document.loanapplytbnewAF.elements["orgAddr"].value=orgAddr;
		document.loanapplytbnewAF.elements["orgMail"].value=orgMail;
		document.loanapplytbnewAF.elements["accBlnce"].value=accBlnce;
		document.loanapplytbnewAF.elements["monthSalary"].value=monthSalary;
		document.loanapplytbnewAF.elements["monthPay"].value=monthPay;
		document.loanapplytbnewAF.elements["empSt"].value=empSt;
		document.loanapplytbnewAF.elements["bgnDate"].value=bgnDate;
		document.loanapplytbnewAF.elements["endDate"].value=endDate;	
}

function gettr(indexs){
tr=indexs;
var empid=document.getElementById(tr).childNodes[1].childNodes[0].innerHTML;
	if(empid==""){
	document.all.look.disabled="true";
	}else{
	document.all.look.disabled="";
	}
}
function gotoEmpAccount(){
	var borrowerName = document.getElementById(tr).childNodes[2].childNodes[0].innerHTML.trim();
	borrowerName =encodeURI(borrowerName);
	var cardNum = document.getElementById(tr).childNodes[3].childNodes[0].innerHTML.trim();
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
											background="<%=path%>/img/buttong.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											<a href="<%=path%>/sysloan/showLoanCheckTaAC.do" class=a2>借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
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
								<form name="loanapplytbnewAF" style="margin: 0">
									<table width="95%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td height="35">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="22" bgcolor="#CCCCCC" align="center"
															width="209">
															<b class="font14">共 同 借 款 人 基 本 信 息</b>
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
									<table border="0" width="95%" id="table1" cellspacing=1
										cellpadding=3 align="center">
										<tr>
											<td class=td1 width="12%">
												合同编号
											</td>
											<td class="td4">
												<html:text property="contractId" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td class="td1" width="12%">
												借款人姓名
											</td>
											<td class="td4" width="17%">
												<html:text property="borrowerName" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1 width="12%">
												共同借款人状态
											</td>
											<td class="td4" width="30%">
												<html:text property="relationStatus" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td class=td1 width="12%">
												职工编号
											</td>
											<td class="td4">
												<html:text property="empId" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td class="td1" width="12%">
												职工姓名
											</td>
											<td class="td4" width="17%">
												<html:text property="name" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1 width="12%">
												与借款人关系
											</td>
											<td class="td4" width="30%">
												<html:select name="loanapplytbnewAF" property="relation" disabled="true"
													styleClass="input4" 
													onkeydown="enterNextFocus1();">
													<html:optionsCollection property="relationMap"
														name="loanapplytbnewAF" label="value" value="key" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td class=td1>
												性别
											</td>
											<td class="td4">
												<html:text name="loanapplytbnewAF" property="sex"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1>
												证件类型
											</td>
											<td class="td4">
												<html:text name="loanapplytbnewAF" property="cardKind"
													styleClass="input3" readonly="true" />
											</td>
											<td class=td1>
												证件号码
											</td>
											<td class="td4">
												<html:text property="cardNum" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												出生日期
											</td>
											<td class="td4">
												<html:text property="birthday" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												年龄
											</td>
											<td class="td4" width="17%">
												<html:text property="age" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												民族
											</td>
											<td class="td4" width="30%">
												<html:text property="nation" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												户籍所在地
											</td>
											<td class="td4">
												<html:text property="nativePlace" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												职务
											</td>
											<td class="td4" width="17%">
												<html:text property="business" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												职称
											</td>
											<td class="td4" width="30%">
												<html:text property="title" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												婚姻状况
											</td>
											<td class="td4">
												<html:text property="marriageSt" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												文化程度
											</td>
											<td class="td4" width="17%">
												<html:text property="degree" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												家庭住址
											</td>
											<td class="td4" width="30%">
												<html:text property="homeAddr" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												邮政编码
											</td>
											<td class="td4">
												<html:text property="homeMail" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												移动电话
											</td>
											<td class="td4" width="17%">
												<html:text property="homeMobile" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												住宅电话
											</td>
											<td class="td4" width="30%">
												<html:text property="houseTel" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
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
															width="279">
															<b class="font14">共 同 借 款 人 公 积 金 归 集 信 息</b>
														</td>
														<td height="22" background="<%=path%>/img/bg2.gif"
															align="center" width="581">
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
											<html:hidden name="loanapplytbnewAF" property="count" />
											<td width="12%" class=td1>
												单位编号
											</td>
											<td class="td4" colspan="2">
												<html:text property="orgId" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												单位名称
											</td>
											<td class="td4" width="17%">
												<html:text property="orgName" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												单位电话
											</td>
											<td class="td4" width="30%">
												<html:text property="orgTel" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												单位地址
											</td>
											<td class="td4" colspan="2">
												<html:text property="orgAddr" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												邮政编码
											</td>
											<td class="td4" width="17%">
												<html:text property="orgMail" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												账户余额
											</td>
											<td class="td4" width="30%">
												<html:text property="accBlnce" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												月工资额
											</td>
											<td class="td4" colspan="2">
												<html:text property="monthSalary" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												月缴存额
											</td>
											<td class="td4" width="17%">
												<html:text property="monthPay" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												账户状态
											</td>
											<td class="td4" width="30%">
												<html:text property="empSt" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
										</tr>
										<tr>
											<td width="12%" class=td1>
												初缴年月
											</td>
											<td class="td4" colspan="2">
												<html:text property="bgnDate" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												缴至年月
											</td>
											<td class="td4" width="17%">
												<html:text property="endDate" name="loanapplytbnewAF"
													styleClass="input3" readonly="true" />
											</td>
											<td width="12%" class=td1>
												&nbsp;
											</td>
											<td class="td4" width="30%">
												<input type="text" class="input3" readonly="true">
											</td>
										</tr>
									</table>
								</form>
								<form name="idAF" style="margin: 0">
									<table width="95%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td height="35">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="22" bgcolor="#CCCCCC" align="center"
															width="202">
															<b class="font14">辅助借款人信息列表</b>
														</td>
														<td width="703" height="22" align="center"
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
									<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
										cellpadding="3" align="center">
										<tr align="center" bgcolor="C4F0FF">
											<td height="23" bgcolor="C4F0FF">
												&nbsp;
											</td>
											<td align="center" class=td2>
												职工编号
											</td>
											<td align="center" class=td2>
												职工姓名
											</td>
											<td align="center" class=td2>
												证件号码
											</td>
											<td align="center" class=td2>
												账户状态
											</td>
											<td align="center" class=td2>
												账户余额
											</td>
											<td align="center" class=td2>
												月工资额
											</td>
											<td align="center" class=td2>
												月缴存额
											</td>
											<td align="center" class=td2>
												单位名称
											</td>
											<td align="center" class=td2>
												借款人状态
											</td>
											<td align="center" class=td2>
												浏览附件
											</td>
										</tr>
										<logic:notEmpty name="loanapplytbnewAF" property="list">
											<%
													int j = 0;
													String strClass = "";
											%>
											<logic:iterate name="loanapplytbnewAF" property="list"
												id="tail" indexId="i">
												<%
															j++;
															if (j % 2 == 0) {
														strClass = "td8";
															} else {
														strClass = "td9";
															}
												%>
												<tr id="tr<%=i%>"
													onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
													onMouseOver='this.style.background="#eaeaea"'
													onMouseOut='gotoColorpp("<%=i%>", idAF);'
													class="<%=strClass%>"
													ondblclick='executeAjaxIn("tr<%=i%>");'>
													<td valign="top">
														<p align="left">
															<input id="s<%=i%>" type="radio" name="id"
																value="<bean:write name="tail" property="id"/>"
																<%if(new Integer(0).equals(i)) out.print("checked"); %> />
														</p>
													</td>
													<td valign="top">
														<P>
															<bean:write name="tail" property="empid" />
														</P>
													</td>
													<td valign="top">
														<P>
															<bean:write name="tail" property="empname" />
														</P>
													</td>
													<td valign="top">
														<P>
															<bean:write name="tail" property="cardnum" />
														</P>
													</td>
													<td valign="top">
														<bean:write name="tail" property="empst" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="accblnce" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="monthsalary" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="monthpay" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="orgname" />
													</td>
													<td valign="top">
														<bean:write name="tail" property="status" />
													</td>
													<td>
														<a
															href='javascript:excHz("<bean:write name="tail" property="photoUrl"/>");'><img
																src="<%=path%>/img/lookinfo.gif" width="37" height="24">
														</a>
													</td>
												</tr>

											</logic:iterate>
										</logic:notEmpty>
										<logic:empty name="loanapplytbnewAF" property="list">
											<tr>
												<td colspan="16" height="30" style="color:red">
													没有找到与条件相符合的结果！
												</td>
											</tr>

										</logic:empty>
									</table>
								</form>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr class="td1">
										<td>
											&nbsp;
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
													<td width="110">
														<html:submit property="look" styleClass="buttonc"
															onclick="gotoEmpAccount();">
															<bean:message key="button.look.empinfo" />
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

