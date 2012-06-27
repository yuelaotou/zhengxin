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
	String printInfo = (String) request.getAttribute("printInfo");
	Object bailenRolTaPrintDTO = request.getAttribute("bailenRolTaPrintDTO");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>保证金登记信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script language="javascript">
function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	var printInfo='<%=printInfo%>';
	if(printInfo=='print') {
		document.all.sure.disabled="true";// 确定按钮禁用
		var bailenRolTaPrintDTO='<%=bailenRolTaPrintDTO%>';
		if(confirm("是否打印保证金登记凭证？")){
			showDoc();
  		}else {
  			return false;
  		}	
	}
	
	var contractId = document.bailenRolTaAF.elements["contractId"].value;
	if(contractId==""){
		document.all.sure.disabled="true";
	}
}

function showDoc() {
  document.Form1.submit();
}

function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax();
	}
}

function executeAjax() { 
    var queryString = "bailenRolTaFindAAC.do?";  
    var id = document.bailenRolTaAF.elements["contractId"].value;   
    if(id==""){
       gotoContractpop('','<%=path%>','0');
    }else {
    	queryString = queryString + "contractId="+id;
		findInfo(queryString); 
    }
     	
}

function display(contractId,borrowerName,cardKind,cardNum,loanBankName,loanKouAcc,loanKouAccHidden,bizDate,message){
	if(message!=""){
		alert(message);
		document.all.sure.disabled="true";
	}
	if(contractId!=""){ 
		document.all.sure.disabled="";
	}
	document.forms[0].elements["contractId"].value=contractId;
	document.forms[0].elements["borrowerName"].value=borrowerName;
	document.forms[0].elements["cardKind"].value=cardKind;
	document.forms[0].elements["cardNum"].value=cardNum;
	document.forms[0].elements["loanBankName"].value=loanBankName;
	document.forms[0].elements["loanBankId"].value=loanBankName;
	document.forms[0].elements["loanKouAcc"].value=loanKouAcc;
	document.forms[0].elements["loanKouAccHidden"].value=loanKouAccHidden;
	document.forms[0].elements["bizDate"].value=bizDate;
}

function gotoSure(){
  var contractId = document.forms[0].elements["contractId"].value;
  if(contractId == ""){
   document.bailenRolTaAF.elements["contractId"].focus();
   alert("合同编号不能为空!");
   return false;
  }
  var bizDate = document.forms[0].elements["bizDate"].value;
  if(bizDate == ""){
   document.bailenRolTaAF.elements["bizDate"].focus();
   alert("收取日期不能为空!");
   return false;
  }else if(checkDate(document.forms[0].elements["bizDate"])==false){
    document.bailenRolTaAF.elements["bizDate"].focus();
	return false;
  }
  var occurMoney = document.forms[0].elements["occurMoney"].value;
  if(occurMoney == ""){
   document.bailenRolTaAF.elements["occurMoney"].focus();
   alert("保证金金额不能为空!");
   return false;
  }else if(checkMoney(occurMoney)==false){
  	document.bailenRolTaAF.elements["occurMoney"].focus();
	return false;
  }else if(occurMoney<=0){
  	document.bailenRolTaAF.elements["occurMoney"].focus();
  	alert("保证金金额必须大于0!");
	return false;
  }
}

function getBankAcc(){
	var loanBankIdACC = document.bailenRolTaAF.elements["loanBankName"].value.trim();
  	var queryString = "bailenRolTaFindBankAccAAC.do?";
    queryString = queryString + "loanBankIdACC="+loanBankIdACC;	     
	findInfo(queryString);	     
}
function displayAcc(bankAcc){
	document.bailenRolTaAF.elements["loanKouAcc"].value=bankAcc;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">
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
										<a href="bailenRolTaShowAC.do" class=a2>办理保证金登记</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="bailenRolTbForwardAC.do" class=a2>保证金登记维护</a>
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
										<font color="00B5DB">特殊业务处理&gt;保证金登记</font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="172">
										<b class="font14">保证金登记信息</b>
									</td>
									<td width="753" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/bailenRolTaSaveAC.do" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="22%">
								<html:text name="bailenRolTaAF" property="contractId"
									styleClass="input3" ondblclick="executeAjax();"
									onkeydown="gotoEnter()" />
							</td>
							<td width="11%">
								<input type="button" class="buttona" value="..."
									onClick="gotoContractpop('','<%=path%>','0');">
							</td>
							<td class="td1" width="17%">
								借款人姓名
							</td>
							<td width="33%">
								<html:text name="bailenRolTaAF" property="borrowerName"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								证件类型
							</td>
							<td colspan="2">
								<html:text name="bailenRolTaAF" property="cardKind"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%">
								<html:text name="bailenRolTaAF" property="cardNum"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								收款银行
							</td>
							<td colspan="2">
								<logic:notEmpty name="loanBankNameList">
									<html:select name="bailenRolTaAF" property="loanBankName"
										styleClass="input3" onchange="getBankAcc()" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="loanBankNameList" property="value"
											labelProperty="label" />
									</html:select>
								</logic:notEmpty>
							</td>
							<html:hidden name="bailenRolTaAF" property="loanBankId" ></html:hidden>
							<td width="17%" class="td1">
								收款银行账号
							</td>
							<html:hidden name="bailenRolTaAF" property="loanKouAccHidden" ></html:hidden>
							<td width="33%">
								<html:text name="bailenRolTaAF" property="loanKouAcc"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>				
						</tr>
						<tr>
							<td width="17%" class="td1">
								收取日期
								<font color="#FF0000">*</font>
							</td>
							<td colspan="2">
								<html:text name="bailenRolTaAF" property="bizDate"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								保证金金额
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="bailenRolTaAF" property="occurMoney"
									styleClass="input3" onkeydown="enterNextFocus1();" />
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
											<html:submit property="sure" styleClass="buttona"
												onclick="return gotoSure();">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
	<form action="bailenRolTaPrintAC.do" method="POST" name="Form1"
		id="Form1">
	</form>
</body>
</html:html>
