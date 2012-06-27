<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>贷款申请</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript"></script>

<script language="javascript" type="text/javascript">
function seachContractId(){

	gotoContractpop('14','<%=path%>','0','');
}
function checkData() {
   	var chgMonth=document.all.bizDate.value.trim();
   	var loanKouAcc = document.issuenoticeTaAF.elements["loanKouAcc"].value.trim();
	if(!isNumLetter(loanKouAcc)){
		document.issuenoticeTaAF.elements["loanKouAcc"].focus();
		alert("请输入正确的扣款账号!");
		return false;
	}
	if(document.all.bizDate.value.trim()==""){
	   alert("录入拨款日期!");
	   return false;
	}
	var st=isNumberX(document.all.bizDate.value);
	if(!st){
		alert('日期应为八位数字！如：20071010');
    	return false;
    }
	   var str=checkDate(document.all.bizDate);
    if(!str){
    	return false;
    }
}
function checksData() {
   	var chgMonth=document.all.bizDate.value.trim();
   	var loanKouAcc = document.issuenoticeTaAF.elements["loanKouAcc"].value.trim();
	if(document.all.bizDate.value.trim()==""){
	   alert("录入拨款日期!");
	   return false;
	}
	var st=isNumberX(document.all.bizDate.value);
	if(!st){
		alert('日期应为八位数字！如：20071010');
    	return false;
    }
	var str=checkDate(document.all.bizDate);
    if(!str){
    	return false;
    }
}
function backErrors(id){
	document.all.contractId.value="";
	document.all.borrowerName.value="";
	document.all.cardKind.value="";
	document.all.cardNum.value="";
	document.all.loanMoney.value="";
	document.all.loanTimeLimit.value="";
	document.all.temp_loanMonthRate.value="";
	document.all.loanBankId.value="";
	document.all.corpusInterest.value="";
	document.all.loanMode.value="";
	document.all.sellerPayBank.value="";
	document.all.sellerPayBankAcc.value="";
	document.all.bizDate.value="";
	document.all.houseTotalPrice.value="";
	document.all.houseArea.value="";
	document.all.houseAddr.value="";
	document.all.loanKouAcc.value="";
    alert(id);
}
function executeAjax()
{
	if(document.all.contractId.value.trim()==""){
	   alert("录入合同编号!");
	   return false;
	}	 
    var contractId=document.all.contractId.value;
   
    var queryString = "issuenoticeTaFindAAC.do?";  
    queryString = queryString + "contractId="+contractId;  
   	findInfo(queryString);
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	document.all.contractId.value="";
	document.all.borrowerName.value="";
	document.all.cardKind.value="";
	document.all.cardNum.value="";
	document.all.loanMoney.value="";
	document.all.loanTimeLimit.value="";
	document.all.temp_loanMonthRate.value="";
	document.all.loanBankId.value="";
	document.all.corpusInterest.value="";
	document.all.loanMode.value="";
	document.all.sellerPayBank.value="";
	document.all.sellerPayBankAcc.value="";
	document.all.bizDate.value="";
	document.all.loanKouAcc.value="";
	document.all.houseTotalPrice.value="";
	document.all.houseArea.value="";
	document.all.houseAddr.value="";
	alert(message);
	</logic:messagesPresent>
	var type = "<bean:write name='issuenoticeTaAF' property='type' />";
	var buttonObj = document.getElementById("btn");
	if(type==""){
		buttonObj.disabled = true;
	}else if(type=="2"){
		buttonObj.style.display="none";
	}
	if(document.all.isPrint.value.trim()=="1"){
		var x=confirm("是否打印贷款借据!");
	 	if(x){
		 	document.all.isPrint.value='2';
			document.location="issuenoticeTaPrintAC.do?print=1";
	  	}else{
    		document.location="issuenoticeTaPrintAC.do?print=2";
  		} 
  	}
}
function displays(contractId) {
  	showlist();
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
function clear() {	
	<%
    		String save=(String)request.getAttribute("save");
	%>	
	var save="<%=save%>";
	if(save=="save"){	
		document.all.contractId.value="";
		document.all.borrowerName.value="";
		document.all.cardKind.value="";
		document.all.cardNum.value="";
		document.all.loanMoney.value="";
		document.all.loanTimeLimit.value="";
		document.all.temp_loanMonthRate.value="";
		document.all.loanBankId.value="";
		document.all.corpusInterest.value="";
		document.all.loanMode.value="";
		document.all.sellerPayBank.value="";
		document.all.sellerPayBankAcc.value="";
		document.all.bizDate.value="";	
		document.all.loanKouAcc.value="";
		document.all.houseTotalPrice.value="";
		document.all.houseArea.value="";
		document.all.houseAddr.value="";
		alert("操作成功！");
	}
	var id=document.all.contractId.value.trim();
 	if(id==""){
 		document.all.contractId.focus();
 	}else{
 		document.all.bizDate.focus();
 	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="reportErrors();clear();">
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
									<logic:notEqual name="issuenoticeTaAF" property="type"
										value="2">
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											下达发放通知书
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/issuenoticeTbForwardAC.do"
												class=a2>发放通知书维护</a>
										</td>
									</logic:notEqual>
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
											<font color="00B5DB">申请贷款&gt;下达发放通知书</font>
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">发放通知书信息</b>
									</td>
									<td width="786" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/issuenoticeTaSaveAC.do" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="22%">
								<html:text name="issuenoticeTaAF" property="contractId"
									styleClass="input3" onkeydown="gotoEnter();"
									ondblclick="executeAjax()" />
							</td>
							<td width="11%">
								<logic:notEqual name="issuenoticeTaAF" property="type" value="2">
									<input type="button" class="buttona" value="..."
										onClick="seachContractId()">
								</logic:notEqual>
							</td>
							<td class="td1" width="17%">
								借款人姓名
							</td>
							<td width="33%">
								<html:text name="issuenoticeTaAF" property="borrowerName"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								证件类型
							</td>
							<td colspan="2">
								<html:text name="issuenoticeTaAF" property="cardKind"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%">
								<html:text name="issuenoticeTaAF" property="cardNum"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td colspan="2">
								<html:text name="issuenoticeTaAF" property="loanBankId"
									styleClass="input3" readonly="true"/>
							</td>
							<td width="17%" class="td1">
								<font face="宋体">借款金额</font>
							</td>
							<td width="33%">
								<html:text name="issuenoticeTaAF" property="loanMoney"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款期限（年）
							</td>
							<td colspan="2">
								<html:text name="issuenoticeTaAF" property="loanTimeLimit"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								<font face="宋体">每月利率</font>
							</td>
							<td width="33%">
								<html:text name="issuenoticeTaAF" property="temp_loanMonthRate"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								月还本息
							</td>
							<td colspan="2">
								<html:text name="issuenoticeTaAF" property="corpusInterest"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								<font face="宋体">还款方式</font>
							</td>
							<td width="33%">
								<html:text name="issuenoticeTaAF" property="loanMode"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								<font face="宋体">划款银行</font>
							</td>
							<td colspan="2">
								<html:text name="issuenoticeTaAF" property="sellerPayBank"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								划款银行账号
							</td>
							<td width="33%">
								<html:text name="issuenoticeTaAF" property="sellerPayBankAcc"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								<font face="宋体">拨款日期</font>
							</td>
							<td colspan="2">
								<html:text name="issuenoticeTaAF" property="bizDate" onblur=""
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								扣款账号
							</td>
							<td width="33%">
								<html:text name="issuenoticeTaAF" property="loanKouAcc"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								<font face="宋体">房屋总价</font>
							</td>
							<td colspan="2">
								<html:text name="issuenoticeTaAF" property="houseTotalPrice"
									styleClass="input3" readonly="true" />

							</td>
							<td width="17%" class="td1">
								建筑面积
							</td>
							<td width="33%">
								<html:text name="issuenoticeTaAF" property="houseArea"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								<font face="宋体">购房地址</font>
							</td>
							<td colspan="2">
								<html:text name="issuenoticeTaAF" property="houseAddr"
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
											<html:submit property="method" styleClass="buttona"
												styleId="btn" onclick="return checksData();">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:hidden name="issuenoticeTaAF" property="isPrint" />
					<html:hidden name="issuenoticeTaAF" property="type" />
				</html:form>
				<form action="issuenoticeTaShowAC.do" method="POST" name="Form1"
					id="Form1">
				</form>
			</td>
		</tr>
	</table>
</body>
</html:html>
