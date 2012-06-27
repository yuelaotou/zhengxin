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
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script src="<%=path%>/js/common.js">
		</script>


	</head>
			<script  language="javascript">
			var s1="";
	function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="查看职工明细账"){
				s1=i;
			}
		}
	} 
	 var empid = document.loanapplynewAF.elements["borrower.empId"].value.trim();
		 if(empid=="0"||empid==""){
			document.all.item(s1).disabled="true";
		 }
	}			
	function gotoSubmit(){
	document.loanapplynewAF.elements["borrower.empId"].value='0';
	}
	
	function gotoEmpAccount(){
	var empId = document.forms[0].elements["borrower.empId"].value.trim();
	var orgId = document.forms[0].elements["borrower.orgId"].value.trim();
	window.open('<%=path%>/sysloan/showEmpAccountPopListAC.do?empId='+empId+'&orgId='+orgId,'newwindow','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=no,location=no,status=no');
	return false;
}
</script>
	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="loads();">
	<html:form action="/loancontractqueryTaPrintAC">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
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
											<a href="<%=path%>/sysloan/loanDeskaccQueryContractInfoTaShowAC.do" class=a2>借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loanDeskaccQueryContractInfoTbShowAC.do" class=a2>辅助借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loanDeskaccQueryContractInfoTcShowAC.do" class=a2>购房信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loanDeskaccQueryContractInfoTdShowAC.do" class=a2>借款人额度信息</a>
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
											<html:text name="loanapplynewAF" property="borrower.cardKind"
												styleClass="input3" readonly="true" />
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
											<html:text property="borrower.birthday" name="loanapplynewAF"
												styleClass="input3" maxlength="8" readonly="true" />
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
											<html:text property="borrower.business" name="loanapplynewAF"
												styleClass="input3" readonly="true" />
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
											<html:text property="borrower.homeAddr" name="loanapplynewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td width="12%" class=td1>
											邮政编码
										</td>
										<td class="td4" width="30%">
											<html:text property="borrower.homeMail" name="loanapplynewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="12%" class=td1>
											住宅电话
										</td>
										<td class="td4">
											<html:text property="borrower.houseTel" name="loanapplynewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td width="12%" class=td1>
											移动电话
										</td>
										<td class="td4" width="17%">
											<html:text property="borrower.homeMobile"
												name="loanapplynewAF" styleClass="input3" readonly="true" />
										</td>
										<td width="12%" class=td1>
											&nbsp;
										</td>
										<td class="td4" width="30%">
											<input type="text" class="input3" readonly="true">
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
												styleClass="input3" readonly="true" />
										</td>
										<td width="12%" class=td1>
											单位名称
										</td>
										<td class="td4" width="17%">
											<html:text property="borrower.orgName" name="loanapplynewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td width="12%" class=td1>
											单位电话
										</td>
										<td class="td4" width="30%">
											<html:text property="borrower.orgTel" name="loanapplynewAF"
												styleClass="input3" maxlength="20" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="12%" class=td1>
											邮政编码
										</td>
										<td class="td4" colspan="2">
											<html:text property="borrower.orgMail" name="loanapplynewAF"
												styleClass="input3" maxlength="8" readonly="true" />
										</td>
										<td width="12%" class=td1>
											账户余额
										</td>
										<td class="td4" width="17%">
											<html:text property="borrower.accBlnce" name="loanapplynewAF"
												styleClass="input3" maxlength="20" readonly="true" />
										</td>
										<td width="12%" class=td1>
											单位地址
										</td>
										<td class="td4" width="30%">
											<html:text property="borrower.orgAddr" name="loanapplynewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="12%" class=td1>
											月工资额
											<span class="STYLE1">*</span>
										</td>
										<td class="td4" colspan="2">
											<html:text property="borrower.monthSalary"
												name="loanapplynewAF" styleClass="input3" maxlength="18"
												readonly="true" />
										</td>
										<td width="12%" class=td1>
											月缴存额
										</td>
										<td class="td4" width="17%">
											<html:text property="borrower.monthPay" name="loanapplynewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td width="12%" class=td1>
											账户状态
										</td>
										<td class="td4" width="30%">
											<html:text property="borrower.empSt_" name="loanapplynewAF"
												styleClass="input3" readonly="true" />
										</td>
									</tr>
									<tr>
										<td width="12%" class=td1>
											初缴年月
										</td>
										<td class="td4" colspan="2">
											<html:text property="borrower.bgnDate" name="loanapplynewAF"
												styleClass="input3" readonly="true" />
										</td>
										<td width="12%" class=td1>
											缴至年月
										</td>
										<td class="td4" width="17%">
											<html:text property="borrower.endDate" name="loanapplynewAF"
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
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr valign="bottom">
										<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
											<table border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="110" align="center" valign="middle">
												<html:submit styleClass="buttonc" onclick="return gotoEmpAccount();">
													<bean:message key="button.look.empinfo" />
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

