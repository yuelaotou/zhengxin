<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<head>
		<title>添加补缴信息界面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	</head>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script language="javascript"></script>

	<script language="javascript" type="text/javascript">
function seachContractId(){

	gotoContractpop('7,8,11,12,13','<%=path%>','0','');
}

function executeAjax()
{
	if(document.all.contractId.value.trim()==""){
	   alert("录入合同编号!");
	   return false;
	}	 
    var contractId=document.all.contractId.value;
   
    var queryString = "receiveaccTaFindAAC.do?";  
    queryString = queryString + "contractId="+contractId;  
     findInfo(queryString);
}
function sure()
{
if(document.all.contractId.value.trim()==""){
	   alert("录入合同编号!");
	   return false;
	}	
	if(document.all.newLoanKouAcc.value.trim()==""){
	   alert("请录入新扣款银行帐号!");
	   return false;
	}	 
}
  function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	if(message=="该合同编号下存在未记账的业务不可以进行扣款账号修改！"){
	document.all.contractId.value="";
	document.all.borrowerName.value="";
	document.all.cardKind.value="";
	document.all.cardNum.value="";
	document.all.orgName.value="";
	document.all.loanankId.value="";
	document.all.oldLoanKouAcc.value="";
	document.all.newLoanKouAcc.value="";
}	
	alert(message);
	</logic:messagesPresent>
	}
function displays(contractId) {
   showlist();
}
function backErrors(id){
if(id=="合同编号不存在或不在用户权限下！"){
	document.all.contractId.value="";
	document.all.borrowerName.value="";
	document.all.cardKind.value="";
	document.all.cardNum.value="";
	document.all.orgName.value="";
	document.all.loanankId.value="";
	document.all.oldLoanKouAcc.value="";
	document.all.newLoanKouAcc.value="";
	}
    alert(id);
}
function showlist() {
  document.Form1.submit();
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function dd(){
 	var id=document.all.contractId.value.trim();
	if(id==""){
 		document.all.contractId.focus();
 	}else{
 		document.all.newLoanKouAcc.focus();
 	}
}
</script>
	<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();dd();"
		onContextmenu="return false">
		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<html:form action="/receiveaccTaModifyAC.do" style="margin: 0">
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
												扣款账号修改
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/gathingAccForwardURLAC.do"
													class=a2>扣款账号维护</a>
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
											<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
												valign="bottom">
												<font color="00B5DB">申请贷款&gt;扣款账号修改</font>
											</td>
											<td width="29" class=font14>
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
											<td height="22" bgcolor="#CCCCCC" align="center" width="160">
												<b class="font14">扣款账号修改</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="780">
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
									<br>
								</td>
								<td class="td4" width="20%">
									<html:text name="receiveaccModifyAF" property="contractId"
										styleClass="input3" onkeydown="gotoEnter();"
										ondblclick="return executeAjax();" />
								</td>
								<td class="td4">
									<input type="button" class="buttona" value="..."
										name="button32" onClick="seachContractId();">
								</td>
								<td class="td1" width="17%">
									借款人姓名
								</td>
								<td class="td4" width="33%">
									<html:text name="receiveaccModifyAF" property="borrowerName"
										styleClass="input3" readonly="true" />
								</td>
							</tr>

							<tr>
								<td class=td1 width="17%">
									证件类型
									<br>
								</td>
								<td class="td4" colspan="2">
									<html:text name="receiveaccModifyAF" property="cardKind"
										styleClass="input3" readonly="true" />
								<td class=td1 width="17%">
									证件号码
								</td>
								<td class="td4" width="33%">
									<font color="#FF0000"> <html:text
											name="receiveaccModifyAF" property="cardNum"
											styleClass="input3" readonly="true" /> </font>
								</td>
							</tr>
							<tr>
								<td class=td1 width="17%">
									单位名称
								</td>
								<td class="td4" colspan="2">
									<html:text name="receiveaccModifyAF" property="orgName"
										styleClass="input3" readonly="true" />
								</td>
								<td class=td1 width="17%">

								</td>
								<td width="33%">

								</td>
							</tr>
							<tr>
								<td class=td1 width="17%">
									原扣款银行
								</td>
								<td class="td4" colspan="2">
									<html:text name="receiveaccModifyAF" property="loanankId"
										styleClass="input3" readonly="true" />
								</td>
								<td class=td1 width="17%">
									新扣款银行
								</td>
								<td class="td4" width="33%">

									<html:select name="receiveaccModifyAF"
										property="newloanBankName" styleClass="input3"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="loanBankNameList" property="value"
											labelProperty="label" />
									</html:select>

								</td>
							</tr>
							<tr>
								<td class=td1 width="17%" height="26">
									原扣款银行账号
								</td>
								<td class="td4" colspan="2" height="26">
									<html:text name="receiveaccModifyAF" property="oldLoanKouAcc"
										styleClass="input3" readonly="true" />
								</td>
								<td class=td1 width="17%" height="26">
									&#26032;&#25187;&#27454;&#38134;&#34892;&#36134;&#21495;
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="33%" height="26">
									<html:text name="receiveaccModifyAF" property="newLoanKouAcc"
										styleClass="input3" />
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
												<logic:equal name="receiveaccModifyAF" property="type"
													value="1">
													<html:submit property="method" styleClass="buttona"
														disabled="true" onclick="return sure();">
														<bean:message key="button.sure" />
													</html:submit>&nbsp;
										</logic:equal>
												<logic:notEqual name="receiveaccModifyAF" property="type"
													value="1">
													<html:submit property="method" styleClass="buttona"
														onclick="return sure();">
														<bean:message key="button.sure" />
													</html:submit>&nbsp;
										</logic:notEqual>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
			</html:form>
			<form action="receiveaccTaShowAC.do" method="POST" name="Form1"
				id="Form1">
			</form>
		</table>
	</body>
</html>


