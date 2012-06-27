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

<html>
	<head>
		<title>合同变更</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>

	<script type="text/javascript">
var s1="";
var s2="";
var s3="";
function loads(){

 	<logic:equal name="loanapplynewAF" property="isNeedDel" value="1">
	 	del();
	</logic:equal>
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="确定"){
				s1=i;
			}
<%--			if(document.all.item(i).value=="扫描"){--%>
<%--				s2=i;--%>
<%--			}--%>
			if(document.all.item(i).value=="查看职工明细账"){
				s3=i;
			}
		}
	} 
	var empids = document.forms[0].elements["borrower.empId"].value.trim();
	var orgid = document.forms[0].elements["borrower.orgId"].value.trim();
	if(empids==""){
		document.forms[0].elements["borrower.empId"].value="0";
		document.all.item(s3).disabled="true";
	}else{
		document.forms[0].elements["borrower.empId"].value=format(empids)+empids;
	}
	if(orgid==""){
		document.forms[0].elements["borrower.orgId"].value="0";
	}else{
		document.forms[0].elements["borrower.orgId"].value=formatTen(orgid)+orgid;
	}
	var type = document.loanapplynewAF.elements["type"].value.trim();
    if(type=="1"){
 		document.all.item(s1).disabled="true";
<%-- 			document.all.item(s2).disabled="true";--%>
   	}
}
function acountempid(){
	document.loanapplynewAF.elements["borrower.empId"].value=0;
}
	
function addempid(){
	var empId = document.forms[0].elements["borrower.empId"].value.trim();
	if(empId==""){
		document.forms[0].elements["borrower.empId"].value='0';
	}
}	

function gotoEmpAccount(){
	var cardNum = document.forms[0].elements["borrower.cardNum"].value.trim();
	var borrowerName = document.forms[0].elements["borrower.borrowerName"].value.trim();
	borrowerName =encodeURI(borrowerName);
	window.open('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'newwindow','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=no,location=no,status=no');
	return false;
}
</script>

	<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
		<jsp:include page="/syscommon/picture/progressbar.jsp" />
		<html:form action="/baseinfochgtbmaintainAC.do">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				align="center">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/baseinfochgtashowAC.do" class=a2>基本信息变更维护</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttonbl.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/baseinfochgtbshowAC.do" class=a2>借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/baseinfochgtcshowAC.do" class=a2>共同借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/baseinfochgtdshowAC.do" class=a2>购房信息</a>
											</td>
										</tr>
									</table>
								</td>
								<td background="<%=path%>/img/table_bg_line.gif" align="right">
									<table width="200" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="37">
												<img src="<%=path%>/img/title_banner.gif" width="37"
													height="24">
											</td>
											<td width="228" class=font14 bgcolor="#FFFFFF" align="center"
												valign="bottom">
												<font color="00B5DB">申请贷款&gt;申请贷款</font>
											</td>
											<td width="35" class=font14>
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
											<td height="22" bgcolor="#CCCCCC" align="center" width="176">
												<b class="font14">借款人基本信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="734">
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
								<html:hidden property="type" name="loanapplynewAF" />
								<html:hidden name="loanapplynewAF"
									property="borrower.contractId" />
								<td class=td1 width="12%">
									职工编号
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.empId" name="loanapplynewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td class="td1" width="12%">
									借款人姓名
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.borrowerName"
										name="loanapplynewAF" styleClass="input3" maxlength="8"
										readonly="true" />
								</td>
								<td class=td1 width="12%">
									性别
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" width="30%">
									<html:select name="loanapplynewAF" property="borrower.sex"
										styleClass="input4" disabled="true">
										<html:option value=""></html:option>
										<html:optionsCollection property="sexMap"
											name="loanapplynewAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									证件类型
								</td>
								<td class="td4" colspan="2">
									<html:select name="loanapplynewAF" property="borrower.cardKind"
										styleClass="input4" disabled="true">
										<html:option value=""></html:option>
										<html:optionsCollection property="cardkingMap"
											name="loanapplynewAF" label="value" value="key" />
									</html:select>
								</td>
								<td width="12%" class=td1>
									证件号码
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.cardNum" name="loanapplynewAF"
										styleClass="input3" maxlength="18" />
								</td>
								<td width="12%" class=td1>
									出生日期
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.birthday" name="loanapplynewAF"
										styleClass="input3" maxlength="8" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									年龄
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.age" name="loanapplynewAF"
										styleClass="input3" maxlength="3" readonly="true" />
								</td>
								<td width="12%" class=td1>
									职务
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.business" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									职称
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.title" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									民族
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.nation" name="loanapplynewAF"
										styleClass="input3" maxlength="8"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									户籍所在地
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.nativePlace"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									婚姻状况
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.marriageSt" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									文化程度
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.degree" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									家庭住址
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.homeAddr" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									邮政编码
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.homeMail" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									住宅电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.houseTel" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									移动电话
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.homeMobile" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									备注
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.remark" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="175">
												<b class="font14">借款人公积金归集信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="735">
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
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.orgTel" name="loanapplynewAF"
										styleClass="input3" maxlength="20"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									邮政编码
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.orgMail" name="loanapplynewAF"
										styleClass="input3" maxlength="8"
										onkeydown="enterNextFocus1();" />
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
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									月工资额
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.monthSalary"
										name="loanapplynewAF" styleClass="input3" maxlength="7" />
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
									其他欠款
								</td>
								<td class="td4" width="17%">
									<html:select name="loanapplynewAF"
										property="borrower.otherArrearage" styleClass="input4">
										<html:optionsCollection property="yesNoMap"
											name="loanapplynewAF" label="value" value="key" />
									</html:select>
								</td>

							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="174">
												<strong class="font14">其他联系人信息</strong>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="736">
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
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									与借款人关系
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.relationA" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									工作单位
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.contactAOrgName"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.contactAOrgTel"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									住宅电话
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.contactAHouseTel"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									移动电话
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.contactAMobile"
										name="loanapplynewAF" styleClass="input3" maxlength="11"
										onkeydown="enterNextFocus1();" />
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
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									与借款人关系
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.relationB" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									工作单位
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.contactBOrgName"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.contactBOrgTel"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									住宅电话
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.contactBHouseTel"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									移动电话
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.contactBMobile"
										name="loanapplynewAF" styleClass="input3" maxlength="11"
										onkeydown="enterNextFocus1();" />
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
												<html:submit property="method" styleClass="buttonc"
													onclick="return gotoEmpAccount();">
													<bean:message key="button.look.empinfo" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="acountempid();">
													<bean:message key="button.sure" />
												</html:submit>
											</td>
<%--											<td width="70">--%>
<%--												<html:submit property="method" styleClass="buttona">--%>
<%--													<bean:message key="button.scan" />--%>
<%--												</html:submit>--%>
<%--											</td>--%>
											<td width="33%" colspan="2">
												<a
													href='javascript:excHz("<bean:write name="loanapplynewAF" property="borrower.photoUrl"/>");'>浏览证书</a>
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

