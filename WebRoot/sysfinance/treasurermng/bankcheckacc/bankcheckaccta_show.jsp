<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>银行对账单</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
function reportErrors(message) {
	if(message!=null){
		alert(message);
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function initWindow(){
	var bookSt=document.forms[0].elements["bookSt"].value;
	if(bookSt==0){
		alert('该账套未启用！');
		document.URL="<%=path%>/toindex.jsp";
	}else{
		var type = document.forms[0].elements["bankCheckAccTaDTO.type"].value;
		document.forms[0].elements["bankCheckAccTaDTO.subjectCode"].focus();
		if(type=='1'){
			document.forms[0].elements["bankCheckAccTaDTO.office"].disabled=true;
		}
	}
}
function onChange(){
	document.forms[0].elements["bankCheckAccTaDTO.subjectCode"].focus();
}
function checkdate(){
	var settDate=document.forms[0].elements["bankCheckAccTaDTO.settDate"].value.trim();
	if(settDate!=""){
		if(!checkDate(document.forms[0].elements["bankCheckAccTaDTO.settDate"])){
			document.forms[0].elements["bankCheckAccTaDTO.settDate"].value="";
			return false;
		}
	}
	return true;
}
var flag="";
function onblur11(){
	var type = document.forms[0].elements["bankCheckAccTaDTO.type"].value;
	var subjectCode = document.forms[0].elements["bankCheckAccTaDTO.subjectCode"].value.trim();
	if(flag!=""){
		return true;
	}
	else if(type!=1){
		executeAjaxIn();
	}
}
function tofocus() //按回车置下一个位置
{
	document.forms[0].elements["bankCheckAccTaDTO.summary"].focus();
	return false;
} 

function executeAjaxIn() {
	
	var queryString = "checkSubiectCodeAAC.do?";
    var subjectCode = document.forms[0].elements["bankCheckAccTaDTO.subjectCode"].value.trim();
    flag=subjectCode;
    queryString = queryString + "subjectCode=" + subjectCode; 
	findInfo(queryString);
}
function show(subjectId){
	if(document.forms[0].elements["bankCheckAccTaDTO.subjectCode"].value!=""){
 		if(subjectId==null){
			alert('该科目输入不正确');
			document.forms[0].elements["bankCheckAccTaDTO.subjectCode"].value="";
			document.forms[0].elements["bankCheckAccTaDTO.subjectCode"].focus();
			flag="";
		}else{
			document.forms[0].elements["bankCheckAccTaDTO.summary"].focus();
			flag="";
		}
 	}
}
function onsure(){
	var office = document.forms[0].elements["bankCheckAccTaDTO.office"].value.trim();
	var subjectCode = document.forms[0].elements["bankCheckAccTaDTO.subjectCode"].value.trim();
	var settDate = document.forms[0].elements["bankCheckAccTaDTO.settDate"].value.trim();
	var debit = document.forms[0].elements["bankCheckAccTaDTO.debit"].value.trim(); 
	var credit = document.forms[0].elements["bankCheckAccTaDTO.credit"].value.trim(); 
	if(office==""){
		alert('请选择所属办事处！');
		return false;
	}
	if(subjectCode==""){
		alert('请输入科目！');
		return false;
	}
	if(debit!="0"){
		if(!checkMoney(debit)){
		    document.forms[0].elements["bankCheckAccTaDTO.debit"].value="0";
			document.forms[0].elements["bankCheckAccTaDTO.debit"].focus();
			return false;
		}
		document.forms[0].elements["bankCheckAccTaDTO.debit"].value=document.forms[0].elements["bankCheckAccTaDTO.debit"].value.trim();
	}
	if(credit!="0"){
		if(!checkMoney(credit)){
			document.forms[0].elements["bankCheckAccTaDTO.credit"].value="0";
			document.forms[0].elements["bankCheckAccTaDTO.credit"].focus();
			return false;
		}
		document.forms[0].elements["bankCheckAccTaDTO.credit"].value=document.forms[0].elements["bankCheckAccTaDTO.credit"].value.trim();
	}
	if(debit=="0"&&credit=="0"){
		alert('银行借方金额或者银行贷方金额必须输入一项！');
		return false;
	}
	if(debit!="0"&&credit!="0"){
		alert('银行借方金额或者银行贷方金额只能输入一项！');
		return false;
	}
	if(settDate==""){
		alert('请输入结算日期！');
		return false;
	}
	var type = document.forms[0].elements["bankCheckAccTaDTO.type"].value;
	if(type==null||type==''){
		document.getElementById("method").value="save";
		return true;
	}else{
		document.getElementById("method").value="modify";
		return true;
	}
}
function tosubject(){
	gotoSubjectpop('0','<%=path%>','1');
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="reportErrors();initWindow();">
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
										银行对账单
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="bankCheckAccTbForwardAC.do" class=a2>银行对账单维护</a>
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
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<a href="#" class=a1></a><font color="00B5DB">出纳管理&gt;银行对账单<a
												href="#" class="a1"></a> </font>
										</p>
									</td>
									<td width="74" class=font14>
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
				<html:form action="/bankCheckAccTaMaintainAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr align="center">
							<td colspan="8" class="td1" align="left">
								<table width="100%" border="0" cellspacing="3" cellpadding="0">
									<tr>
										<td class=td1 width="9%">
											所属办事处
											<font color="#FF0000">*</font>
										</td>
										<td class="td4" width="31%">
											<html:select property="bankCheckAccTaDTO.office"
											    name="bankCheckAccTaAF"
												styleClass="input4" onkeydown="enterNextFocus1();" onchange="onChange();">
												<option value="">全部</option>
												<html:options collection="officeList1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
										<td width="8%">
											&nbsp;
										</td>
										<td class="td4" width="10%">
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
							</td>
						</tr>
						<tr>
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="131">
											<b class="font14">银 行 对 账 单</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="743">
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
							<td width="15%" class="td1">
								科目
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td width="17%">
								<html:text name="bankCheckAccTaAF"
									property="bankCheckAccTaDTO.subjectCode" styleClass="input3"
									 onblur="onblur11();" onkeydown="javascrip:if(window.event.keyCode==13){return tofocus()}"/>
							</td>
							<td width="18%">
								<input type="button" name="Submit12" value="..." class="buttona"
									onclick="tosubject();">
							</td>
							<td width="15%" class="td1">
								摘要
							</td>
							<td width="35%">
								<span class="td4"> <html:select
										property="bankCheckAccTaDTO.summary" styleClass="input4"
										name="bankCheckAccTaAF" onkeydown="enterNextFocus1();">
										<option value=""></option>
										<html:options collection="summrayList1" property="value"
											labelProperty="label" />
									</html:select> </span>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1" height="18">
								银行借方金额
								<font color="#FF0000">*</font>
							</td>
							<td width="35%" height="18" colspan="2" align="center">
								<html:text name="bankCheckAccTaAF"
									property="bankCheckAccTaDTO.debit" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
							<td width="15%" class="td1" height="18">
								银行贷方金额
								<font color="#FF0000">*</font>
							</td>
							<td width="35%" height="18">
								<html:text name="bankCheckAccTaAF"
									property="bankCheckAccTaDTO.credit" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1" height="18">
								结算方式
							</td>
							<td width="35%" height="18" colspan="2" align="center">
								<span class="td4"> <html:select
										property="bankCheckAccTaDTO.settType" styleClass="input4"
										name="bankCheckAccTaAF" onkeydown="enterNextFocus1();">
										<option value=""></option>
										<html:options collection="settTypeList1" property="value"
											labelProperty="label" />
									</html:select> </span>
							</td>
							<td width="15%" class="td1" height="18">
								结算号
							</td>
							<td width="35%" height="18">
								<html:text name="bankCheckAccTaAF"
									property="bankCheckAccTaDTO.settNum" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1" height="18">
								经手人
							</td>
							<td width="35%" height="18" colspan="2" align="center">
								<html:text name="bankCheckAccTaAF"
									property="bankCheckAccTaDTO.dopsn" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1" height="18">
								结算日期
								<font color="#FF0000">*</font>
							</td>
							<td width="35%" height="18">
								<html:text name="bankCheckAccTaAF"
									property="bankCheckAccTaDTO.settDate" styleClass="input3"
									onblur="return checkdate();" onkeydown="enterNextFocus1();" />
							</td>
							<html:hidden name="bankCheckAccTaAF"
								property="bankCheckAccTaDTO.type" />
								<html:hidden name="bankCheckAccTaAF"
								property="bankCheckAccTaDTO.temp_office" />
								<html:hidden name="bankCheckAccTaAF"
								property="bankCheckAccTaDTO.credenceId" />
								<html:hidden name="bankCheckAccTaAF"
							property="bookSt" />
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<logic:empty name="bankCheckAccTaAF"
									property="bankCheckAccTaDTO.type">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return onsure();">
													<bean:message key="button.sure" />
												</html:submit>
											</td>
										</tr>
									</table>
								</logic:empty>
								<logic:notEmpty name="bankCheckAccTaAF"
									property="bankCheckAccTaDTO.type">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return onsure();">
													<bean:message key="button.confirm" />
												</html:submit>
											</td>
										</tr>
									</table>
								</logic:notEmpty>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
