<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%
String path = request.getContextPath();
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript"></script>
<script language="javascript" type="text/javascript">
function seachContractId(){
	gotoContractpop('1,2,3,4,5,6,9,10','<%=path%>','0','');
}
function executeAjax()
{
	if(document.all.contractId.value.trim()==""){
	   alert("录入合同编号!");
	   return false;
	}	 
    var contractId=document.all.contractId.value;
   
    var queryString = "giveaccTaFindAAC.do?";  
    queryString = queryString + "contractId="+contractId;  
     findInfo(queryString);
}
function sure()
{
	if(document.all.newSellerPayBank.value.trim()==""){
	   alert("请录入新划款银行!");
	   return false;
	}
	if(document.all.newPayBankAcc.value.trim()==""){
	   alert("请录入新划款银行帐号!");
	   return false;
	}		 
}
  function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
function displays(contractId) {
   showlist();
}
function backErrors(id){
document.all.contractId.value="";
document.all.borrowerName.value="";
document.all.cardKind.value="";
document.all.cardNum.value="";
document.all.oldSellerPayBank.value="";
document.all.oldPayBankAcc.value="";
document.all.sellerName.value="";
document.all.remark.value="";
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
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<html:form action="/giveaccTaModifyAC.do" style="margin: 0">
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
											划款账号修改
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/houseForwardURLAC.do" class=a2>划款账号维护</a>
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
											<font color="00B5DB">申请贷款&gt;划款账号修改</font>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="161">
											<b class="font14">划款账号修改</b>
										</td>
										<td width="743" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr id="tr1">
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="22%">
								<html:text name="giveaccModifyAF" property="contractId"
									styleClass="input3" onkeydown="gotoEnter();"
									ondblclick="return executeAjax();" />
							</td>
							<td width="11%">
								<input type="button" class="buttona" value="..." name="button32"
									onClick="seachContractId();">
							</td>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%">
								<html:text name="giveaccModifyAF" property="borrowerName"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								证件类型
							</td>
							<td width="26%" colspan="2">
								<html:text name="giveaccModifyAF" property="cardKind"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%">
								<html:text name="giveaccModifyAF" property="cardNum"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								原划款银行
							</td>
							<td width="26%" colspan="2">
								<html:text name="giveaccModifyAF" property="oldSellerPayBank"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								原划款银行账号
							</td>
							<td width="33%">
								<html:text name="giveaccModifyAF" property="oldPayBankAcc"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								新划款银行
								<font color="#FF0000">*</font>
							</td>
							<td width="26%" colspan="2">
								<html:text name="giveaccModifyAF" property="newSellerPayBank"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								新划款银行账号
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="giveaccModifyAF" property="newPayBankAcc"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								售房单位（售房人姓名）
							</td>
							<td width="26%" colspan="2">
								<html:text name="giveaccModifyAF" property="sellerName"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								备注
							</td>
							<td width="33%">
								<html:text name="giveaccModifyAF" property="remark"
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
										<td width="70">
											<logic:equal name="giveaccModifyAF" property="type" value="1">
												<html:submit property="method" styleClass="buttona"
													disabled="true" onclick="return sure();">
													<bean:message key="button.sure" />
												</html:submit>&nbsp;
										</logic:equal>
											<logic:notEqual name="giveaccModifyAF" property="type"
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
		<form action="giveaccTaShowAC.do" method="POST" name="Form1"
			id="Form1">
		</form>
	</table>
</body>
</html:html>


