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
	<title>参数维护</title>
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
var loanVipCheck="";
var endorseLoan="";
function verdictLoanBankId(){
	var loanBankId=document.forms[0].elements["paramDTO.loanBankId"].value.trim();
	document.URL=('paramShowAC.do?loanBankId='+loanBankId);
}
function appValue(dateMode){
	document.forms[0].elements["decideDateMode"].value=dateMode.value.trim();
}
function appValue1(RateMode){
	document.forms[0].elements["punishInterestRateMode"].value=RateMode.value.trim();
}
function loads(){
	var v4=document.forms[0].elements["paramDTO.shifouqiyong"];
	if(v4[1].checked==true){
		document.forms[0].elements["paramDTO.commonMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.attentionMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.subMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.shadinessMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.lossMonthNum"].disabled="true";
		
	}
	
	if(v4[0].checked==true){
		document.forms[0].elements["paramDTO.commonMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.attentionMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.subMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.shadinessMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.lossMonthNum"].disabled="";
	}
	loanVipCheck=document.forms[0].elements["paramDTO.loanVipCheck"].value;
	endorseLoan=document.forms[0].elements["paramDTO.endorseLoan"].value;
	var vd=document.forms[0].elements["paramDTO.decideDateMode"];
	for(i=0;i<vd.length;i++){
       if(vd[i].checked){
       	document.forms[0].elements["decideDateMode"].value = vd[i].value;
       }
    }
    var vr=document.forms[0].elements["paramDTO.punishInterestRateMode"];
	for(i=0;i<vr.length;i++){
       if(vr[i].checked){
       	document.forms[0].elements["punishInterestRateMode"].value = vr[i].value;
       }
    }
}
function checkLoanFlow() {
	var queryString = "paramCheckLoanFlowAAC.do?";
    var loanVipCh = document.forms[0].elements["paramDTO.loanVipCheck"].value.trim();
    var endorseLo = document.forms[0].elements["paramDTO.endorseLoan"].value.trim();
    queryString = queryString + "loanVipCheck=" + loanVipCh + "&endorseLoan=" + endorseLo; 
	findInfo(queryString);
}
function show(){
	alert("第7条请输入正确贷款流程!");
	document.forms[0].elements["paramDTO.loanVipCheck"].value=loanVipCheck;
	document.forms[0].elements["paramDTO.endorseLoan"].value=endorseLoan;
}
function onCheck(){
	var decideDateMode=document.forms[0].elements["decideDateMode"].value.trim();
	var uniteDate=document.forms[0].elements["paramDTO.uniteDate"].value.trim();
	var punishInterestRateMode=document.forms[0].elements["punishInterestRateMode"].value.trim();
	var punishInterestDateRate=document.forms[0].elements["paramDTO.punishInterestDateRate"].value.trim();
	var contractDateRate=document.forms[0].elements["paramDTO.contractDateRate"].value.trim();
	var moneyDateInterest=document.forms[0].elements["paramDTO.moneyDateInterest"].value.trim();
	var permitDateNum=document.forms[0].elements["paramDTO.permitDateNum"].value.trim();
	var commonMonthNum=document.forms[0].elements["paramDTO.commonMonthNum"].value.trim();
	var attentionMonthNum=document.forms[0].elements["paramDTO.attentionMonthNum"].value.trim();
	var subMonthNum=document.forms[0].elements["paramDTO.subMonthNum"].value.trim();
	var shadinessMonthNum=document.forms[0].elements["paramDTO.shadinessMonthNum"].value.trim();
	var lossMonthNum=document.forms[0].elements["paramDTO.lossMonthNum"].value.trim();
<%--	var currentRate=document.forms[0].elements["paramDTO.currentRate"].value.trim();--%>
<%--	var terminalRate=document.forms[0].elements["paramDTO.terminalRate"].value.trim();--%>

	var corpus=document.forms[0].elements["paramDTO.corpus"].value.trim();
	
	var interest=document.forms[0].elements["paramDTO.interest"].value.trim();
	
	var overdueCorpus=document.forms[0].elements["paramDTO.overdueCorpus"].value.trim();
	
	var overdueInterest=document.forms[0].elements["paramDTO.overdueInterest"].value.trim();
	
	var punishInterest=document.forms[0].elements["paramDTO.punishInterest"].value.trim();
	
	var loanVipCh=document.forms[0].elements["paramDTO.loanVipCheck"].value.trim();
	
	var endorseLo=document.forms[0].elements["paramDTO.endorseLoan"].value.trim();
	
<%--	var punishInterestDateRate1=document.forms[0].elements["paramDTO.punishInterestDateRate1"].value.trim();--%>
<%--	var contractDateRate1=document.forms[0].elements["paramDTO.contractDateRate1"].value.trim();--%>
<%--	var moneyDateInterest1=document.forms[0].elements["paramDTO.moneyDateInterest1"].value.trim();--%>
	
	var temp_array=[];
	temp_array[0]=corpus;
	temp_array[1]=interest;
	temp_array[2]=overdueCorpus;
	temp_array[3]=overdueInterest;
	temp_array[4]=punishInterest;
	var v1=document.forms[0].elements["paramDTO.kouAccMode"];
	if(v1[0].checked==false&&v1[1].checked==false){
		alert('第1条必须二选一！');
		return false;
	}
	var v2=document.forms[0].elements["paramDTO.decideDateMode"];
	if(v2[0].checked==false&&v2[1].checked==false){
		alert('第2条必须二选一！');
		return false;
	}
	else if(decideDateMode==2){
		if(uniteDate==""){
			alert("第2条统一定日的日期必须输入！");
			document.forms[0].elements["paramDTO.uniteDate"].focus();
			return false;
		}
	}
	if(v1[0].checked==false){
		for(i=0;i<5;i++){
		for(j=0;j<5;j++){
			if(i!=j){
				if(temp_array[i]==temp_array[j]){
					alert("第3条五个输入值必须互不相同！");
					return false;
				}
			}
		}
	}
	}
	
	var v4=document.forms[0].elements["paramDTO.punishInterestRateMode"];
	if(v4[0].checked==false&&v4[1].checked==false&&v4[2].checked==false){
		alert('第4条必须三选一！');
		return false;
	}
	if(punishInterestRateMode==1){
		if(punishInterestDateRate==0||punishInterestDateRate==""){
			alert("第4条按罚息日利率值必须输入！");
			document.forms[0].elements["paramDTO.punishInterestDateRate"].focus();
			return false;
		}
	}
	else if(punishInterestRateMode==2){
		if(contractDateRate==0||contractDateRate==""){
			alert("第4条按合同日利率值必须输入！");
			document.forms[0].elements["paramDTO.contractDateRate"].focus();
			return false;
		}
	}
	else if(punishInterestRateMode==3){
		if(moneyDateInterest==0||moneyDateInterest==""){
			alert("第4条按额每日值必须输入！");
			document.forms[0].elements["paramDTO.moneyDateInterest"].focus();
			return false;
		}
	}
	if(punishInterestDateRate==""){
		document.forms[0].elements["paramDTO.punishInterestDateRate"].value="0";
	}else{
		document.forms[0].elements["paramDTO.punishInterestDateRate"].value=punishInterestDateRate;
	}
	if(contractDateRate==""){
		document.forms[0].elements["paramDTO.contractDateRate"].value="0";
	}else{
		document.forms[0].elements["paramDTO.contractDateRate"].value=contractDateRate;
	}
	if(moneyDateInterest==""){
		document.forms[0].elements["paramDTO.moneyDateInterest"].value="0";
	}else{
		document.forms[0].elements["paramDTO.moneyDateInterest"].value=moneyDateInterest;
	}
	var v5=document.forms[0].elements["paramDTO.isRecord"];
	if(v5[0].checked==false&&v5[1].checked==false){
		alert('第5条必须二选一！');
		return false;
	}
	if(permitDateNum==""){
		alert("第5条宽限天数必须输入！");
		document.forms[0].elements["paramDTO.permitDateNum"].focus();
		return false;
	}
	
	var v100=document.forms[0].elements["paramDTO.shifouqiyong"];
	if(v100[0].checked==true){
	if(commonMonthNum==""){
		alert("第6条正常天数必须输入！");
		document.forms[0].elements["paramDTO.commonMonthNum"].focus();
		return false;
	}
		if(attentionMonthNum==""){
		alert("第6条关注天数必须输入！");
		document.forms[0].elements["paramDTO.attentionMonthNum"].focus();
		return false;
	}
	if(subMonthNum==""){
		alert("第6条次级天数必须输入！");
		document.forms[0].elements["paramDTO.subMonthNum"].focus();
		return false;
	}
	if(shadinessMonthNum==""){
		alert("第6条可疑天数必须输入！");
		document.forms[0].elements["paramDTO.shadinessMonthNum"].focus();
		return false;
	}
	if(lossMonthNum==""){
		alert("第6条损失天数必须输入！");
		document.forms[0].elements["paramDTO.lossMonthNum"].focus();
		return false;
	}
	}
	
	if(loanVipCh==endorseLo&&(loanVipCh=='A'||loanVipCh=='B')){
		alert("第7条两个输入值不能相同！")
		return false;
	}
<%--	if(currentRate==0||currentRate==""){--%>
<%--		alert("第8条活期利率必须输入！");--%>
<%--		document.forms[0].elements["paramDTO.currentRate"].focus();--%>
<%--		return false;--%>
<%--	}else{--%>
<%--		document.forms[0].elements["paramDTO.currentRate"].value=currentRate;--%>
<%--	}--%>
<%--	if(terminalRate==0||terminalRate==""){--%>
<%--		alert("第8条定期利率必须输入！");--%>
<%--		document.forms[0].elements["paramDTO.terminalRate"].focus();--%>
<%--		return false;--%>
<%--	}else{--%>
<%--		document.forms[0].elements["paramDTO.terminalRate"].value=terminalRate;--%>
<%--	}--%>
	var v9=document.forms[0].elements["paramDTO.isAdopt"];
	if(v9[0].checked==false&&v9[1].checked==false){
		alert('第8条必须二选一！');
		return false;
	}
<%--	var v10=document.forms[0].elements["paramDTO.isAdopt1"];--%>
<%--	if(v10[0].checked==false&&v10[1].checked==false){--%>
<%--		alert('第10条必须二选一！');--%>
<%--		return false;--%>
<%--	}--%>
<%--	var v11=document.forms[0].elements["paramDTO.punishInterestRateMode1"];--%>
<%--	if(v11[0].checked==false&&v11[1].checked==false&&v11[2].checked==false){--%>
<%--		alert('第11条必须三选一！');--%>
<%--		return false;--%>
<%--	}--%>
<%--	if(v11[0].checked==true){--%>
<%--		if(punishInterestDateRate1==0||punishInterestDateRate1==""){--%>
<%--			alert("第11条按罚息日利率值必须输入！");--%>
<%--			document.forms[0].elements["paramDTO.punishInterestDateRate1"].focus();--%>
<%--			return false;--%>
<%--		}--%>
<%--	}--%>
<%--	else if(v11[1].checked==true){--%>
<%--		if(contractDateRate1==0||contractDateRate1==""){--%>
<%--			alert("第11条按合同日利率值必须输入！");--%>
<%--			document.forms[0].elements["paramDTO.contractDateRate1"].focus();--%>
<%--			return false;--%>
<%--		}--%>
<%--	}--%>
<%--	else if(v11[2].checked==true){--%>
<%--		if(moneyDateInterest1==0||moneyDateInterest1==""){--%>
<%--			alert("第11条按额每日值必须输入！");--%>
<%--			document.forms[0].elements["paramDTO.moneyDateInterest1"].focus();--%>
<%--			return false;--%>
<%--		}--%>
<%--	}--%>
<%--	if(punishInterestDateRate1==""){--%>
<%--		document.forms[0].elements["paramDTO.punishInterestDateRate1"].value="0";--%>
<%--	}else{--%>
<%--		document.forms[0].elements["paramDTO.punishInterestDateRate1"].value=punishInterestDateRate1;--%>
<%--	}--%>
<%--	if(contractDateRate1==""){--%>
<%--		document.forms[0].elements["paramDTO.contractDateRate1"].value="0";--%>
<%--	}else{--%>
<%--		document.forms[0].elements["paramDTO.contractDateRate1"].value=contractDateRate1;--%>
<%--	}--%>
<%--	if(moneyDateInterest1==""){--%>
<%--		document.forms[0].elements["paramDTO.moneyDateInterest1"].value="0";--%>
<%--	}else{--%>
<%--		document.forms[0].elements["paramDTO.moneyDateInterest1"].value=moneyDateInterest1;--%>
<%--	}--%>
<%--	var v12=document.forms[0].elements["paramDTO.isRecord1"];--%>
<%--	if(v12[0].checked==false&&v12[1].checked==false){--%>
<%--		alert('第12条必须二选一！');--%>
<%--		return false;--%>
<%--	}--%>
<%--	if(permitDateNum1==""){--%>
<%--		alert("第12条宽限天数必须输入！");--%>
<%--		document.forms[0].elements["paramDTO.permitDateNum1"].focus();--%>
<%--		return false;--%>
<%--	}--%>
	return true;
}
function hh(RateMode){
var v4=document.forms[0].elements["paramDTO.kouAccMode"];
	if(v4[0].checked==true){
		document.forms[0].elements["paramDTO.corpus"].disabled="true";
		document.forms[0].elements["paramDTO.interest"].disabled="true";
		document.forms[0].elements["paramDTO.overdueCorpus"].disabled="true";
		document.forms[0].elements["paramDTO.overdueInterest"].disabled="true";
		document.forms[0].elements["paramDTO.punishInterest"].disabled="true";
	}
	
	if(v4[1].checked==true){
		document.forms[0].elements["paramDTO.corpus"].disabled="";
		document.forms[0].elements["paramDTO.interest"].disabled="";
		document.forms[0].elements["paramDTO.overdueCorpus"].disabled="";
		document.forms[0].elements["paramDTO.overdueInterest"].disabled="";
		document.forms[0].elements["paramDTO.punishInterest"].disabled="";
	}
}
function ii(RateMode){
var v4=document.forms[0].elements["paramDTO.shifouqiyong"];
	if(v4[1].checked==true){
		document.forms[0].elements["paramDTO.commonMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.attentionMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.subMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.shadinessMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.lossMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.commonMonthNum"].value="0";
		document.forms[0].elements["paramDTO.attentionMonthNum"].value="0";
		document.forms[0].elements["paramDTO.subMonthNum"].value="0";
		document.forms[0].elements["paramDTO.shadinessMonthNum"].value="0";
		document.forms[0].elements["paramDTO.lossMonthNum"].value="0";
	}
	
	if(v4[0].checked==true){
		document.forms[0].elements["paramDTO.commonMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.attentionMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.subMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.shadinessMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.lossMonthNum"].disabled="";
	}
}
function jj(){
var v4=document.forms[0].elements["paramDTO.shifouqiyong"];
	if(v4[1].checked==true){
		document.forms[0].elements["paramDTO.commonMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.attentionMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.subMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.shadinessMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.lossMonthNum"].disabled="true";
		document.forms[0].elements["paramDTO.commonMonthNum"].value="0";
		document.forms[0].elements["paramDTO.attentionMonthNum"].value="0";
		document.forms[0].elements["paramDTO.subMonthNum"].value="0";
		document.forms[0].elements["paramDTO.shadinessMonthNum"].value="0";
		document.forms[0].elements["paramDTO.lossMonthNum"].value="0";
	}
	
	if(v4[0].checked==true){
		document.forms[0].elements["paramDTO.commonMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.attentionMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.subMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.shadinessMonthNum"].disabled="";
		document.forms[0].elements["paramDTO.lossMonthNum"].disabled="";
	}
	var v5=document.forms[0].elements["paramDTO.kouAccMode"];
	if(v5[0].checked==true){
		document.forms[0].elements["paramDTO.corpus"].disabled="true";
		document.forms[0].elements["paramDTO.interest"].disabled="true";
		document.forms[0].elements["paramDTO.overdueCorpus"].disabled="true";
		document.forms[0].elements["paramDTO.overdueInterest"].disabled="true";
		document.forms[0].elements["paramDTO.punishInterest"].disabled="true";
	}
	
	if(v5[1].checked==true){
		document.forms[0].elements["paramDTO.corpus"].disabled="";
		document.forms[0].elements["paramDTO.interest"].disabled="";
		document.forms[0].elements["paramDTO.overdueCorpus"].disabled="";
		document.forms[0].elements["paramDTO.overdueInterest"].disabled="";
		document.forms[0].elements["paramDTO.punishInterest"].disabled="";
	}
}
function transaction(){
	var punishInterestDateRate=document.forms[0].elements["paramDTO.punishInterestDateRate"].value.trim();
	var contractDateRate=document.forms[0].elements["paramDTO.contractDateRate"].value.trim();
	var moneyDateInterest=document.forms[0].elements["paramDTO.moneyDateInterest"].value.trim();
	var v4=document.forms[0].elements["paramDTO.punishInterestRateMode"];
	var v13=document.forms[0].elements["paramDTO.punishInterestRateMode_1"];
	if(v4[0].checked==true){
		v13[0].checked=true;
	}
	if(v4[1].checked==true){
		v13[1].checked=true;
	}
	if(v4[2].checked==true){
		v13[2].checked=true;
	}
	
	if(punishInterestDateRate==""){
		document.forms[0].elements["paramDTO.punishInterestDateRate"].value="0";
	}else{
		document.forms[0].elements["paramDTO.punishInterestDateRate"].value=punishInterestDateRate;
	}
	if(contractDateRate==""){
		document.forms[0].elements["paramDTO.contractDateRate"].value="0";
	}else{
		document.forms[0].elements["paramDTO.contractDateRate"].value=contractDateRate;
	}
	if(moneyDateInterest==""){
		document.forms[0].elements["paramDTO.moneyDateInterest"].value="0";
	}else{
		document.forms[0].elements["paramDTO.moneyDateInterest"].value=moneyDateInterest;
	}
	punishInterestDateRate=document.forms[0].elements["paramDTO.punishInterestDateRate"].value.trim();
	contractDateRate=document.forms[0].elements["paramDTO.contractDateRate"].value.trim();
	moneyDateInterest=document.forms[0].elements["paramDTO.moneyDateInterest"].value.trim();
	document.forms[0].elements["paramDTO.punishInterestDateRate_1"].value=punishInterestDateRate;
	document.forms[0].elements["paramDTO.contractDateRate_1"].value=contractDateRate;
	document.forms[0].elements["paramDTO.moneyDateInterest_1"].value=moneyDateInterest;
}

</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="jj();return reportErrors();loads();">
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
							&nbsp;
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
										<a href="#" class=a1>数据准备</a><font color="00B5DB">&gt;</font><a
											href="#" class=a1>参数维护</a>
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
				<html:form action="/paramSaveAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">参数设置</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<input type="hidden" name="decideDateMode" value="" />
						<input type="hidden" name="punishInterestRateMode" value="" />
						<tr id="tr1">
							<td class="td1">
								&nbsp;
							</td>
							<td class="td1">
								<html:select property="paramDTO.loanBankId" styleClass="input4"
									name="paramAF" style="width=30%;"
									onchange="verdictLoanBankId();" onkeydown="enterNextFocus1();">
									<html:options collection="loanbankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1">
								1
							</td>
							<td class="td1">

								<html:radio name="paramAF" property="paramDTO.kouAccMode"
									value="1" onclick="hh(this)"  onkeydown="enterNextFocus1();"  /> 
								&#36275;&#39069;&#25187;&#27454;(&#25353;&#25972;&#26376;&#36824;&#27454;&#39069;&#25187;&#25910;) 
								<html:radio name="paramAF" property="paramDTO.kouAccMode"
									value="2" onclick="hh(this)" onkeydown="enterNextFocus1();"  /> 
								&#20840;&#39069;&#25187;&#27454;(&#21487;&#25353;&#19981;&#36275;&#25972;&#26376;&#36824;&#27454;&#39069;&#25187;&#25910;) 
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1">
								2
							</td>
							<td class="td1">

								<html:radio name="paramAF" property="paramDTO.decideDateMode"
									value="1" onclick="appValue(this)"
									onkeydown="enterNextFocus1();" />
								按户定日
								<html:radio name="paramAF" property="paramDTO.decideDateMode"
									value="2" onclick="appValue(this)"
									onkeydown="enterNextFocus1();" /> 
 
								&#32479;&#19968;&#23450;&#26085;&#65292;&#27599;&#26376; 
								<html:text name="paramAF" property="paramDTO.uniteDate"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								号
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1">
								3
							</td>
							<td class="td1">
								扣款顺序设置 本期本金
								<html:select property="paramDTO.corpus" name="paramAF"
									styleClass="input4" style="width=7%;"
									onkeydown="enterNextFocus1();">
									<html:option value="0"></html:option>
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
								</html:select>
								本期利息
								<html:select property="paramDTO.interest" name="paramAF"
									styleClass="input4" style="width=7%;"
									onkeydown="enterNextFocus1();">
									<html:option value="0"></html:option>
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
								</html:select>
								欠还本金
								<html:select property="paramDTO.overdueCorpus" name="paramAF"
									styleClass="input4" style="width=7%;"
									onkeydown="enterNextFocus1();">
									<html:option value="0"></html:option>
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
								</html:select>
								欠还利息
								<html:select property="paramDTO.overdueInterest" name="paramAF"
									styleClass="input4" style="width=7%;"
									onkeydown="enterNextFocus1();">
									<html:option value="0"></html:option>
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
								</html:select> 
								&#36926;&#26399;&#32602;&#24687; 
								<html:select property="paramDTO.punishInterest" name="paramAF"
									styleClass="input4" style="width=7%;"
									onkeydown="enterNextFocus1();">
									<html:option value="0"></html:option>
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
								</html:select>
							</td>
						</tr>

						<tr id="tr1">
							<td class="td1">
								4
							</td>
							<td class="td1"><span style="font-size: 10pt; font-family: 宋体;"></span>逾期罚息利率 
								<html:radio name="paramAF"
									property="paramDTO.punishInterestRateMode" value="1"
									onclick="appValue1(this);" onkeydown="enterNextFocus1();" /> 
								&#25353;&#36926;&#26399;&#32602;&#24687;&#26085;&#21033;&#29575; 
								<html:text name="paramAF"
									property="paramDTO.punishInterestDateRate" styleClass="input3"
									style="width=7%;" onkeydown="enterNextFocus1();" />
								计息
								<html:radio name="paramAF"
									property="paramDTO.punishInterestRateMode" value="2"
									onclick="appValue1(this)" onkeydown="enterNextFocus1();" />
								按合同日利率
								<html:text name="paramAF" property="paramDTO.contractDateRate"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								%计息
								<html:radio name="paramAF"
									property="paramDTO.punishInterestRateMode" value="3"
									onclick="appValue1(this)" onkeydown="enterNextFocus1();" /> 
								&#25353;&#27599;&#26085; 
								<html:text name="paramAF" property="paramDTO.moneyDateInterest"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								元计息
							</td>
						</tr>
						<tr>
							<td class="td1">
								5
							</td>
							<td class="td1">
								宽限天数
								<html:text name="paramAF" property="paramDTO.permitDateNum"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" /> 
								&#22825;, &#23485;&#38480;&#22825;&#25968;&#20869;&#26159;&#21542;&#35760;&#36926;&#26399;&#32602;&#24687; 
								<html:radio name="paramAF" property="paramDTO.isRecord"
									value="0" onkeydown="enterNextFocus1();" />
								是
								<html:radio name="paramAF" property="paramDTO.isRecord"
									value="1" onkeydown="enterNextFocus1();" />
								否
							</td>
						</tr>
						<tr>
							<td class="td1">
								6
							</td>
							<td class="td1">
								五级分类期限设置

								<html:radio name="paramAF" property="paramDTO.shifouqiyong"
									value="1" onclick="ii(this)"  onkeydown="enterNextFocus1();"  />
								启用
								<html:radio name="paramAF" property="paramDTO.shifouqiyong"
									value="2" onclick="ii(this)" onkeydown="enterNextFocus1();"  />
								不启用
							正常
								<html:text name="paramAF" property="paramDTO.commonMonthNum"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								天 关注
								<html:text name="paramAF" property="paramDTO.attentionMonthNum"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								天 次级
								<html:text name="paramAF" property="paramDTO.subMonthNum"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								天 可疑
								<html:text name="paramAF" property="paramDTO.shadinessMonthNum"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								天 损失
								<html:text name="paramAF" property="paramDTO.lossMonthNum"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								天
							</td>
						</tr>
						<tr>
							<td class="td1">
								7
							</td>
							<td class="td1">
								贷款流程 审批贷款
								<html:select property="paramDTO.loanVipCheck" name="paramAF"
									styleClass="input4" style="width=7%;"
									onkeydown="enterNextFocus1();">
									<html:option value="A">1</html:option>
									<html:option value="B">2</html:option>
								</html:select>
								签定贷款
								<html:select property="paramDTO.endorseLoan" name="paramAF"
									styleClass="input4" style="width=7%;"
									onchange="checkLoanFlow();" onkeydown="enterNextFocus1();">
									<html:option value="A">1</html:option>
									<html:option value="B">2</html:option>
								</html:select>
							</td>
						</tr>
<%--						<tr>--%>
<%--							<td class="td1">--%>
<%--								8--%>
<%--							</td>--%>
<%--							<td class="td1">--%>
<%--								保证金 活期利率--%>
<%--								<html:text name="paramAF" property="paramDTO.currentRate"--%>
<%--									styleClass="input3" style="width=7%;"--%>
<%--									onkeydown="enterNextFocus1();" />--%>
<%--								% 定期利率--%>
<%--								<html:text name="paramAF" property="paramDTO.terminalRate"--%>
<%--									styleClass="input3" style="width=7%;"--%>
<%--									onkeydown="enterNextFocus1();" />--%>
<%--								%--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<td class="td1">
								8
							</td>
							<td class="td1">
								利率调整时，申请中的合同是否采用新利率：
								<html:radio name="paramAF" property="paramDTO.isAdopt" value="0"
									onkeydown="enterNextFocus1();" />
								是
								<html:radio name="paramAF" property="paramDTO.isAdopt" value="1"
									onkeydown="enterNextFocus1();" />
								否
							</td>
						</tr>
<%--						<tr>--%>
<%--							<td class="td1">--%>
<%--								10--%>
<%--							</td>--%>
<%--							<td class="td1">--%>
<%--								利率启用时，启用日期前的整年期贷款是否使用新利率：--%>
<%--								<html:radio name="paramAF" property="paramDTO.isAdopt1" value="0"--%>
<%--									onkeydown="enterNextFocus1();" />--%>
<%--								是--%>
<%--								<html:radio name="paramAF" property="paramDTO.isAdopt1" value="1"--%>
<%--									onkeydown="enterNextFocus1();" />--%>
<%--								否--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td class="td1">--%>
<%--								11--%>
<%--							</td>--%>
<%--							<td class="td1">--%>
<%--								整年期贷款逾期利率：--%>
<%--								<html:radio name="paramAF"--%>
<%--									property="paramDTO.punishInterestRateMode1" value="1"--%>
<%--									onkeydown="enterNextFocus1();" />--%>
<%--								按罚息日利率--%>
<%--								<html:text name="paramAF"--%>
<%--									property="paramDTO.punishInterestDateRate1" styleClass="input3"--%>
<%--									style="width=7%;" onkeydown="enterNextFocus1();" />--%>
<%--								计息--%>
<%--								<html:radio name="paramAF"--%>
<%--									property="paramDTO.punishInterestRateMode1" value="2"--%>
<%--									onkeydown="enterNextFocus1();" />--%>
<%--								按合同日利率--%>
<%--								<html:text name="paramAF" property="paramDTO.contractDateRate1"--%>
<%--									styleClass="input3" style="width=7%;"--%>
<%--									onkeydown="enterNextFocus1();" />--%>
<%--								%计息--%>
<%--								<html:radio name="paramAF"--%>
<%--									property="paramDTO.punishInterestRateMode1" value="3"--%>
<%--									onkeydown="enterNextFocus1();" />--%>
<%--								按额每日--%>
<%--								<html:text name="paramAF" property="paramDTO.moneyDateInterest1"--%>
<%--									styleClass="input3" style="width=7%;"--%>
<%--									onkeydown="enterNextFocus1();" />--%>
<%--								元计息--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td class="td1">--%>
<%--								12--%>
<%--							</td>--%>
<%--							<td class="td1">--%>
<%--								整年期贷款宽限天数：--%>
<%--								<html:text name="paramAF" property="paramDTO.permitDateNum1"--%>
<%--									styleClass="input3" style="width=7%;"--%>
<%--									onkeydown="enterNextFocus1();" />--%>
<%--								天, 宽限天数内是否记罚息--%>
<%--								<html:radio name="paramAF" property="paramDTO.isRecord1"--%>
<%--									value="0" onkeydown="enterNextFocus1();" />--%>
<%--								是--%>
<%--								<html:radio name="paramAF" property="paramDTO.isRecord1"--%>
<%--									value="1" onkeydown="enterNextFocus1();" />--%>
<%--								否--%>
<%--							</td>--%>
<%--						</tr>--%>
						
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return onCheck();">
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
