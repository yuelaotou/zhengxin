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
	<title>办理挂账</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script language="javascript">
 function reportErrors(message) {
	if(message!=null){
		alert(message);
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
var s1="";
function onload(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="确定"){
				s1=i;
			}
		}
	}
	document.all.item(s1).disabled="true";
}
function executeAjax() {
	var queryString = "overPayTaFindAAC.do?";
    var loadkouacc = document.forms[0].elements["overPayTaDTO.loankouacc"].value.trim();
    if(loadkouacc==""){
	alert("请你输入扣款账号");
	}
	else{
		queryString = queryString + "loadkouacc="+loadkouacc; 
		findInfo(queryString);
    }
}
function goEnter(){
	document.all.item(s1).disabled="true";
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function toLoankouaccpop(){
	gotoLoankouaccpop('','<%=path%>','0');
}
function displays(contractId,borrowerName,temp_cardKind,cardNum,temp_loanMode,ovaerLoanRepay,loanBankId,officecode) {
	document.forms[0].elements["overPayTaDTO.contractId"].value=contractId;
	document.forms[0].elements["overPayTaDTO.borrowerName"].value=borrowerName;
	document.forms[0].elements["overPayTaDTO.temp_cardKind"].value=temp_cardKind;
	document.forms[0].elements["overPayTaDTO.cardNum"].value=cardNum;
	document.forms[0].elements["overPayTaDTO.temp_loanMode"].value=temp_loanMode;
	document.forms[0].elements["overPayTaDTO.ovaerLoanRepay"].value=ovaerLoanRepay;
	document.forms[0].elements["overPayTaDTO.loanBankId"].value=loanBankId;
	document.forms[0].elements["overPayTaDTO.officecode"].value=officecode;
	document.forms[0].elements["overPayTaDTO.overpayMoney"].value="";
	document.forms[0].elements["overPayTaDTO.overpayMoney"].focus();
	document.all.item(s1).disabled="";
}
function checkMoney1(money){
	if(money==0){
	   return true;
	}
	var salarybase = money.match(/^(([0-9]{1})|(-[0-9]{1}))([0-9]{0,10})(\.[0-9]{1,2})?$/)
	if (salarybase==null){	
		return false;
	}else{
		return true;
	}

}
function oncheck(){
	var overpayMoney=document.forms[0].elements["overPayTaDTO.overpayMoney"].value;
	var ovaerLoanRepay=document.forms[0].elements["overPayTaDTO.ovaerLoanRepay"].value;
	if(overpayMoney==""||overpayMoney==0){
		alert("请你输入挂账金额！");
		return false;
	}else{
		if(isNaN(overpayMoney)){
			alert("请你输入合法的数字");
			document.forms[0].elements["overPayTaDTO.overpayMoney"].value="";
			return false;
		}
		if(!checkMoney1(overpayMoney)){
			document.forms[0].elements["overPayTaDTO.overpayMoney"].value="";
			return false;
		}
		if(parseFloat(overpayMoney)+parseFloat(ovaerLoanRepay)<parseFloat(0)){
			alert("挂账余额不能小于0，请重新输入挂账金额");
			document.forms[0].elements["overPayTaDTO.overpayMoney"].value="";
			return false;
		}
	 }
	return true;
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="reportErrors();onload();">
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
										办理挂账
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="overPayTbForwardAC.do" class=a2>挂账维护</a>
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
										<font color="00B5DB">账务处理&gt;挂账</font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="167">
										<b class="font14">办理挂账</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="758">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/overPayTaSaveAC.do" style="margin: 0">
					<table width="95%" id="table9" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="17%" class=td1>
								扣款账号
								<br>
							</td>
							<td class="td4" width="20%">
								<html:text name="overPayTaAF" property="overPayTaDTO.loankouacc"
									styleClass="input3" onkeydown="goEnter();"
									ondblclick="executeAjax()" />
							</td>
							<td class="td4">
								<input type="button" class="buttona" value="..." name="button32"
									onClick="toLoankouaccpop()">
							</td>
							<td class="td1" width="17%">
								合同编号
							</td>
							<td class="td4" width="33%">
								<html:text name="overPayTaAF" property="overPayTaDTO.contractId"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								借款人姓名
							</td>
							<td class="td4" colspan="2">
								<html:text name="overPayTaAF"
									property="overPayTaDTO.borrowerName" styleClass="input3"
									readonly="true" />
							</td>
							<td width="17%" class=td1>
								证件类型
							</td>
							<td class="td4" width="33%">
								<html:text name="overPayTaAF"
									property="overPayTaDTO.temp_cardKind" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								证件号码
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text name="overPayTaAF"
										property="overPayTaDTO.cardNum" styleClass="input3"
										readonly="true" /> </font>
							</td>
							<td class=td1 width="17%">
								还款方式
							</td>
							<td class="td4" width="33%">
								<html:text name="overPayTaAF"
									property="overPayTaDTO.temp_loanMode" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								挂账余额
							</td>
							<td class="td4" colspan="2">
								<html:text name="overPayTaAF"
									property="overPayTaDTO.ovaerLoanRepay" styleClass="input3"
									readonly="true" />
							</td>
							<td class=td1 width="17%">
								挂账金额
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" width="33%">
								<html:text name="overPayTaAF"
									property="overPayTaDTO.overpayMoney" styleClass="input3"
									maxlength="15" />
							</td>
							<html:hidden name="overPayTaAF"
								property="overPayTaDTO.loanBankId"></html:hidden>
							<html:hidden name="overPayTaAF"
								property="overPayTaDTO.officecode"></html:hidden>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return oncheck();">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
	</table>
</body>
</html:html>
