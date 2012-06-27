<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String type = (String) request.getAttribute("type");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>现金日记账</title>
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
		document.forms[0].elements["cashDayClearTaDTO.subjectCode"].focus();
		var type = document.forms[0].elements["cashDayClearTaDTO.type"].value;
		if(type=='1'){
			document.forms[0].elements["cashDayClearTaDTO.office"].disabled=true;
		}
	}
}
function findCredenceDate() {
	var queryString = "cashDayClearTaFindAAC.do?";
    var office = document.forms[0].elements["cashDayClearTaDTO.office"].value.trim();
    queryString = queryString + "office=" + office; 
	findInfo(queryString);
}
function tofocus() //按回车置下一个位置
{
	document.forms[0].elements["cashDayClearTaDTO.credenceDate"].focus();
	return false;
} 
var flag="";
function onblur11(){
	var type = document.forms[0].elements["cashDayClearTaDTO.type"].value;
	var subjectCode = document.forms[0].elements["cashDayClearTaDTO.subjectCode"].value.trim();
	if(flag!=""){
		return true;
	}
	else if(type!=1){
		executeAjaxIn();
	}
}
function executeAjaxIn() {
	var queryString = "checkSubiectCodeAAC.do?";
    var subjectCode = document.forms[0].elements["cashDayClearTaDTO.subjectCode"].value.trim();
    flag=subjectCode;
    queryString = queryString + "subjectCode=" + subjectCode; 
	findInfo(queryString);
}
function show(subjectId){
 	if(document.forms[0].elements["cashDayClearTaDTO.subjectCode"].value!=""){
 		if(subjectId==null){
			alert('该科目输入不正确');
			document.forms[0].elements["cashDayClearTaDTO.subjectCode"].value="";
			document.forms[0].elements["cashDayClearTaDTO.subjectCode"].focus();
			flag="";
		}else{
			document.forms[0].elements["cashDayClearTaDTO.credenceDate"].focus();
			flag="";
		}
 	}
}
function display(credenceDate){
	document.forms[0].elements["cashDayClearTaDTO.subjectCode"].focus();
	document.forms[0].elements["cashDayClearTaDTO.credenceDate"].value=credenceDate;
	document.forms[0].elements["cashDayClearTaDTO.subjectCode"].value="";
	document.forms[0].elements["cashDayClearTaDTO.credenceCharacter"].value="";
	document.forms[0].elements["cashDayClearTaDTO.summray"].value="";
	document.forms[0].elements["cashDayClearTaDTO.debit"].value="0";
	document.forms[0].elements["cashDayClearTaDTO.credit"].value="0";
	document.forms[0].elements["cashDayClearTaDTO.settType"].value="";
	document.forms[0].elements["cashDayClearTaDTO.settNum"].value="";
	document.forms[0].elements["cashDayClearTaDTO.dopsn"].value="";
	document.forms[0].elements["cashDayClearTaDTO.settDate"].value="";
}
function checkdate(){
	var credenceDate=document.forms[0].elements["cashDayClearTaDTO.credenceDate"].value.trim();
	var settDate=document.forms[0].elements["cashDayClearTaDTO.settDate"].value.trim();
	if(credenceDate!=""){
		if(!checkDate(document.forms[0].elements["cashDayClearTaDTO.credenceDate"])){
			document.forms[0].elements["cashDayClearTaDTO.credenceDate"].value="";
			return false;
		}
	}
	if(settDate!=""){
		if(!checkDate(document.forms[0].elements["cashDayClearTaDTO.settDate"])){
			document.forms[0].elements["cashDayClearTaDTO.settDate"].value="";
			return false;
		}
	}
	return true;
}
function onsure(){
	var office = document.forms[0].elements["cashDayClearTaDTO.office"].value.trim();
	var subjectCode = document.forms[0].elements["cashDayClearTaDTO.subjectCode"].value.trim();
	var credenceDate = document.forms[0].elements["cashDayClearTaDTO.credenceDate"].value.trim();
	var debit = document.forms[0].elements["cashDayClearTaDTO.debit"].value.trim(); 
	var credit = document.forms[0].elements["cashDayClearTaDTO.credit"].value.trim(); 
	var summray = document.forms[0].elements["cashDayClearTaDTO.summray"].value.trim(); 
	if(office==""){
		alert('请选择所属办事处！');
		return false;
	}
	if(subjectCode==""){
		alert('请输入科目！');
		return false;
	}
	if(credenceDate==""){
		alert('请输入凭证日期！');
		return false;
	}
	if(summray==""){
		alert('请选择摘要！');
		return false;
	}
	if(debit!="0"){
		if(!checkMoney(debit)){
		    document.forms[0].elements["cashDayClearTaDTO.debit"].value="0";
			document.forms[0].elements["cashDayClearTaDTO.debit"].focus();
			return false;
		}
		document.forms[0].elements["cashDayClearTaDTO.debit"].value=document.forms[0].elements["cashDayClearTaDTO.debit"].value.trim();
	}
	if(credit!="0"){
		if(!checkMoney(credit)){
			document.forms[0].elements["cashDayClearTaDTO.credit"].value="0";
			document.forms[0].elements["cashDayClearTaDTO.credit"].focus();
			return false;
		}
		document.forms[0].elements["cashDayClearTaDTO.credit"].value=document.forms[0].elements["cashDayClearTaDTO.credit"].value.trim();
	}
	if(debit=="0"&&credit=="0"){
		alert('借方金额或者贷方金额必须输入一项！');
		return false;
	}
	if(debit!="0"&&credit!="0"){
		alert('借方金额或者贷方金额只能输入一项！');
		return false;
	}
	var type = document.forms[0].elements["cashDayClearTaDTO.type"].value;
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
							<table width="338" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<%
									if (type.equals("0")) {
									%>
									<td width="113" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										现金日记账
									</td>
									<%
									}
									%>
									<%
									if (type.equals("1")) {
									%>
									<td width="113" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										银行存款日记账
									</td>
									<%
									}
									%>
									<%
									if (type.equals("0")) {
									%>
									<td width="113" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="cashDayClearTbForwardAC.do" class=a2>自动转账</a>
									</td>
									<%
									}
									%>
									<%
									if (type.equals("1")) {
									%>
									<td width="113" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="bankDayClearTbForwardAC.do" class=a2>自动转账</a>
									</td>
									<%
									}
									%>
									<%
									if (type.equals("0")) {
									%>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="cashDayClearTcForwardAC.do" class=a2>现金日记账维护</a>
									</td>
									<%
									}
									%>
									<%
									if (type.equals("1")) {
									%>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="bankDayClearTcForwardAC.do" class=a2>银行存款日记账维护</a>
									</td>
									<%
									}
									%>
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
									<%
									if (type.equals("0")) {
									%>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<font color="00B5DB">出纳管理&gt;现金日记账</font>
										</p>
									</td>
									<%
									}
									%>
									<%
									if (type.equals("1")) {
									%>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<font color="00B5DB">出纳管理&gt;银行存款日记账</font>
										</p>
									</td>
									<%
									}
									%>
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
				<html:form action="/cashDayClearTaMaintainAC.do" style="margin: 0">
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
											<html:select property="cashDayClearTaDTO.office"
												styleClass="input4" name="cashDayClearTaAF"
												onchange="findCredenceDate();"
												onkeydown="enterNextFocus1();">
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

										<%
										if (type.equals("0")) {
										%>
										<td height="22" bgcolor="#CCCCCC" align="center" width="132">
											<b class="font14">现 金 日 记 账</b>
										</td>
										<%
										}
										%>
										<%
										if (type.equals("1")) {
										%>
										<td height="22" bgcolor="#CCCCCC" align="center" width="132">
											<b class="font14">银 行 日 记 账</b>
										</td>
										<%
										}
										%>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="742">
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
								<html:text name="cashDayClearTaAF"
									property="cashDayClearTaDTO.subjectCode" styleClass="input3"
									onblur="onblur11();" onkeydown="javascrip:if(window.event.keyCode==13){return tofocus()}" />
							</td>
							<td width="18%">
								<input type="button" name="Submit12" value="..." class="buttona"
									onclick="tosubject();">
							</td>
							<td width="15%" class="td1">
								凭证日期
								<font color="#FF0000">*</font>
							</td>
							<td width="35%">
								<html:text name="cashDayClearTaAF"
									property="cashDayClearTaDTO.credenceDate" styleClass="input3"
									onblur="return checkdate();" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<!-- <td width="15%" class="td1" height="18">
								凭证字
							</td>
							<td width="35%" height="18" colspan="2" align="center">
								<span class="td4"> <html:select
										property="cashDayClearTaDTO.credenceCharacter"
										styleClass="input4" name="cashDayClearTaAF"
										onkeydown="enterNextFocus1();">
										<option value=""></option>
										<html:options collection="credenceCharacterList1"
											property="value" labelProperty="label" />
									</html:select> </span>
							</td> -->
							<td width="15%" class="td1" height="18">
								摘要
								<font color="#FF0000">*</font>
							</td>
							<td width="35%" height="18" colspan="2">
								<span class="td4"> <html:select
										property="cashDayClearTaDTO.summray" styleClass="input4"
										name="cashDayClearTaAF" onkeydown="enterNextFocus1();">
										<option value=""></option>
										<html:options collection="summrayList1" property="value"
											labelProperty="label" />
									</html:select> </span>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1" height="18">
								借方金额
								<font color="#FF0000">*</font>
							</td>
							<td width="35%" height="18" colspan="2" align="center">
								<html:text name="cashDayClearTaAF"
									property="cashDayClearTaDTO.debit" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
							<td width="15%" class="td1" height="18">
								贷方金额
								<font color="#FF0000">*</font>
							</td>
							<td width="35%" height="18">
								<html:text name="cashDayClearTaAF"
									property="cashDayClearTaDTO.credit" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1" height="18">
								结算方式
							</td>
							<td width="35%" height="18" colspan="2" align="center">
								<span class="td4"> <html:select
										property="cashDayClearTaDTO.settType" styleClass="input4"
										name="cashDayClearTaAF" onkeydown="enterNextFocus1();">
										<option value=""></option>
										<html:options collection="settTypeList1" property="value"
											labelProperty="label" />
									</html:select> </span>
							</td>
							<td width="15%" class="td1" height="18">
								结算号
							</td>
							<td width="35%" height="18">
								<html:text name="cashDayClearTaAF"
									property="cashDayClearTaDTO.settNum" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1" height="18">
								经手人
							</td>
							<td width="35%" height="18" colspan="2" align="center">
								<html:text name="cashDayClearTaAF"
									property="cashDayClearTaDTO.dopsn" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1" height="18">
								结算日期
							</td>
							<td width="35%" height="18">
								<html:text name="cashDayClearTaAF"
									property="cashDayClearTaDTO.settDate" styleClass="input3"
									onblur="return checkdate();" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<html:hidden name="cashDayClearTaAF"
							property="cashDayClearTaDTO.type" />
						<html:hidden name="cashDayClearTaAF"
							property="bookSt" />
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<logic:empty name="cashDayClearTaAF"
									property="cashDayClearTaDTO.type">
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
								<logic:notEmpty name="cashDayClearTaAF"
									property="cashDayClearTaDTO.type">
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
