<%@ page language="java"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			EndorsecontractTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>个贷管理</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js"></script>
	<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
</head>

<script type="text/javascript">
var xmlHttpTemp;

function createXMLHttpRequestTemp() {
    if (window.ActiveXObject) {
        xmlHttpTemp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttpTemp = new XMLHttpRequest();
    }
} 

var s1="";
var s2="";
var s3="";

function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function synchronization(){
 	var date1 = document.forms[0].elements["contractSureDate"].value;
 	document.forms[0].elements["debitMoneyStaDate"].value=date1;
}
function account(){
    var queryString = "endorsecontractTaAccountAAC.do?";
    var term = document.forms[0].elements["term"].value.trim();
    var debitMoneyStaDate = document.forms[0].elements["debitMoneyStaDate"].value.trim();
    if(isNaN(term)){
    	alert("请输入正确格式的借款期限！");
    	document.forms[0].elements["term"].value="";
    	document.forms[0].elements["term"].focus();
    	return false;
    }
   
    queryString = queryString + "term="+term+"&debitMoneyStaDate="+debitMoneyStaDate; 	 
  	findInfo(queryString);
}

function checkParamV(){
  	var paramvalue = document.forms[0].paramV.value;
  	if(paramvalue == 'AB'){
   		//gotoContractpop('4','<%=path%>','0','1');
   		gotoContractpop('15,1002','<%=path%>','0','1');
  	}else{
   		//gotoContractpop('2','<%=path%>','0','1');
   		gotoContractpop('15,1002','<%=path%>','0','1');
  	}
}

function gotoAssistantor(){
 	gotoAssistantorgpop('<%=path%>','19');
}

function gotoSure(){
  	var contractId = document.forms[0].elements["contractId"].value;
  	var values = document.forms[0].elements["beentruster"].value;
  	var date1 = document.forms[0].elements["contractSureDate"].value;
  	var date2 = document.forms[0].elements["debitMoneyStaDate"].value;
  	var loanKouAcc = document.endorsecontractTaAF.elements["loanKouAcc"].value.trim();
   	if(date1 != "" ){
   		var aa =checkDate0('contractSureDate');
    	if(!aa){
    		document.forms[0].elements["contractSureDate"].value="";
    		return false;
    	}   
   	}
    if(date2 != "" ){
   		var ab =checkDate0('debitMoneyStaDate');
    	if(!ab){
    		document.forms[0].elements["debitMoneyStaDate"].value="";
    		return false;
    	}   
   	}
  	if(contractId == ""){
   		alert("请点击[...]按钮选择合同编号!");
   		return false;
  	}else{
	   	if(values == ""){
	    	alert("受托方(乙方)不能为空!");
	    	return false;
	   	}   
  	}
  	if(!isNumLetter(loanKouAcc)){
		document.endorsecontractTaAF.elements["loanKouAcc"].focus();
		alert("请输入正确的扣款账号!");
		return false;
	}
}
/*---用来判断日期YYYYMMDD---*/
function checkDate0(date)
{
   var tempDate1; 
   eval("tempDate1=document.all."+date);
   var strDate = tempDate1.value;
  if(strDate.length!=8)
  {
    alert("请输入八位的日期格式，例如20070101！");
    return false;
  }
  if(strDate.substring(0,4)<1900){
    alert("年份应该大于1900！");
    return false;
  }
  if(strDate.substring(4,6)>12 || strDate.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    return false;
  }
  var tempStrDate=strDate.substring(6,8);
  var tempStrMonth=strDate.substring(4,6);
 if(tempStrMonth==2&&tempStrDate>29)
  {
    alert("日期不能大于29！");
    return false;
  }else if((tempStrMonth==1||tempStrMonth==3||tempStrMonth==5||tempStrMonth==7||tempStrMonth==8||tempStrMonth==10||tempStrMonth==12)&&tempStrDate>31){
    alert("日期不能大于31！");
    return false;
  }else if((tempStrMonth==2||tempStrMonth==4||tempStrMonth==6||tempStrMonth==9||tempStrMonth==11)&&tempStrDate>30){
    alert("日期不能大于30！");
    return false;
  }
  return true;
}
function executeAjax() {
	
    var queryString = "endorsecontractTaFindAAC.do?";
    var contractId = document.forms[0].elements["contractId"].value.trim();
    if(isNaN(contractId)){
      	alert("请输入正确格式的编号！");
      	document.forms[0].elements["contractId"].value="";
      	document.forms[0].elements["contractId"].focus();
      	return false;
   	}
    queryString = queryString + "contractId="+contractId; 		         
    //findInfoTemp(queryString);
    findInfo(queryString);
    gotoXml();
}
function gotoXml(){
  document.all.childNode.style.display="";
  document.all.bankid.style.display="none";
  var queryString = "endorsecontractTaXMLFindAAC.do?";
  var contractId = document.forms[0].elements["contractId"].value.trim();
  queryString = queryString + "contractId="+contractId;
  findInfoTemp(queryString); 
}
function findInfoTemp(url) {
 	createXMLHttpRequestTemp();  
    xmlHttpTemp.onreadystatechange = handleStateChangeTemp;
    xmlHttpTemp.open("GET", url, true);
    xmlHttpTemp.send(null);   
}

function handleStateChangeTemp() {
  if(xmlHttpTemp.readyState == 4) {
      if(xmlHttpTemp.status == 200) {
        var xmlDoc = xmlHttpTemp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		var texts  = xmlDoc.getElementsByTagName("text");

		var selectObj = document.getElementById("childNode");
		selectObj.length = 0;
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
		document.forms[0].elements["beentruster"].value=selectObj.options[0].value;
		}
		 //var x=xmlHttpTemp.responseText;
		 //alert(x);
         //eval(x);
      }
   }
}
function getChildValue(){
 	var selectObj = document.getElementById("childNode");
 	var bankId=selectObj.options[selectObj.selectedIndex].value;
 	document.forms[0].elements["beentruster"].value=bankId; //银行id
 	var term=document.forms[0].elements["term"].value;      //期限
 	term = parseInt(term)*12;
 	var realMonthInt=document.forms[0].elements["realMonthInt"].value;      //每月利率
 	var loanMode=document.forms[0].elements["hiddenloanMode"].value;      //还款方式
 	var loanMoney=document.forms[0].elements["debitMoney"].value;      //贷款金额
 	var queryString = "endorsecontractBankIdTaFindAAC.do?";
 	queryString = queryString + "bankId="+bankId+"&term="+term+"&realMonthInt="+realMonthInt+"&loanMode="+loanMode+"&loanMoney="+loanMoney;
 	findInfo(queryString);
}
function displays3(loanMonthRate,displayLoanMonthRate,corpusInterest){
  	document.forms[0].elements["realMonthInt"].value=loanMonthRate;
  	document.forms[0].elements["monthInterest"].value=displayLoanMonthRate;
  	if(corpusInterest!=''){
  		document.forms[0].elements["corpusInterest"].value=corpusInterest;
  	}
}
function gotoGetBank(){
 	var bankId=document.all.temp_beentruster.value;
 	document.forms[0].elements["beentruster"].value=bankId;
 	var term=document.forms[0].elements["term"].value*12;      //期限
 	var realMonthInt=document.forms[0].elements["realMonthInt"].value;      //每月利率
 	var loanMode=document.forms[0].elements["hiddenloanMode"].value;      //还款方式
 	var loanMoney=document.forms[0].elements["debitMoney"].value;      //贷款金额
 	var queryString = "endorsecontractBankIdTaFindAAC.do?";
 	queryString = queryString + "bankId="+bankId+"&term="+term+"&realMonthInt="+realMonthInt+"&loanMode="+loanMode+"&loanMoney="+loanMoney;
 	findInfo(queryString);
}
function display2(debitMoneyEndDate){
  document.forms[0].elements["debitMoneyEndDate"].value=debitMoneyEndDate;
}

function display(contractId,debitter,certificateType,certificateNum,beentruster,debitMoney,term,
entruster,monthInterest,creditType,contractSureDate,debitMoneyStaDate,debitMoneyEndDate,assurer,writeType,realMonthInt,corpusInterest,hiddenloanMode,message,iscontactid){
	if(message!=""){
		alert(message);
	}
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="确定"){
				s1=i;
			}
			if(document.all.item(i).value=="扫描房屋信息"){
				s2=i;
			}
		}
	}
	document.forms[0].elements["contractId"].value=contractId;
	document.forms[0].elements["debitter"].value=debitter;
	document.forms[0].elements["certificateType"].value=certificateType;
	document.forms[0].elements["certificateNum"].value=certificateNum;
	document.forms[0].elements["beentruster"].value=beentruster;
	document.forms[0].elements["debitMoney"].value=debitMoney;
	document.forms[0].elements["term"].value=term;
	document.forms[0].elements["entruster"].value=entruster;
	document.forms[0].elements["monthInterest"].value=monthInterest;
	document.forms[0].elements["creditType"].value=creditType;
	document.forms[0].elements["contractSureDate"].value=contractSureDate;
	document.forms[0].elements["debitMoneyStaDate"].value=debitMoneyStaDate;
	document.forms[0].elements["debitMoneyEndDate"].value=debitMoneyEndDate;
	document.forms[0].elements["assurer"].value=assurer;
	document.forms[0].elements["realMonthInt"].value=realMonthInt;
	
	document.forms[0].elements["corpusInterest"].value=corpusInterest;
	document.forms[0].elements["hiddenloanMode"].value=hiddenloanMode;
	 
    if(writeType == '0'){
     	document.forms[0].elements["contractId"].readOnly="readonly";
     	document.forms[0].elements["entruster"].readOnly="readonly";
     	document.forms[0].elements["beentruster"].readOnly="";
     	document.forms[0].elements["assurer"].readOnly="";
     	document.forms[0].elements["debitter"].readOnly="readonly";
     	document.forms[0].elements["certificateType"].readOnly="readonly";
     	document.forms[0].elements["certificateNum"].readOnly="readonly";
     	document.forms[0].elements["debitMoney"].readOnly="readonly";
     	document.forms[0].elements["term"].readOnly="readonly";
     	document.forms[0].elements["monthInterest"].readOnly="readonly";
     	document.forms[0].elements["creditType"].readOnly="readonly";
   		document.forms[0].elements["contractSureDate"].readOnly="";
     	document.forms[0].elements["debitMoneyStaDate"].readOnly="";
     	document.forms[0].elements["debitMoneyEndDate"].readOnly="readonly";
     	document.forms[0].elements["assistantOrgId"].readOnly="";
   
    }else{
    
     //document.forms[0].elements["contractId"].readOnly="readonly";//合同编号
     //document.forms[0].elements["entruster"].readOnly="readonly";//委托方(甲方)
     //document.forms[0].elements["beentruster"].readOnly="readonly";//受托方(乙方)
     //document.forms[0].elements["assurer"].readOnly="";//保证方
     //document.forms[0].elements["debitter"].readOnly="readonly";//借款方(丙方)
     //document.forms[0].elements["certificateType"].readOnly="readonly";//证件类型
     //document.forms[0].elements["certificateNum"].readOnly="readonly";//证件编号
     //document.forms[0].elements["debitMoney"].readOnly="readonly";//借款金额
     //document.forms[0].elements["term"].readOnly="readonly";//借款期限
     //document.forms[0].elements["monthInterest"].readOnly="readonly";//每月利率
     //document.forms[0].elements["creditType"].readOnly="readonly";//还款方式
     //document.forms[0].elements["contractSureDate"].readOnly="";//合同签订日期
     //document.forms[0].elements["debitMoneyStaDate"].readOnly="";//借款起始日期
     //document.forms[0].elements["debitMoneyEndDate"].readOnly="readonly";//借款终止日期
     //document.forms[0].elements["assistantOrgId"].readOnly="readonly";
    }
  
	document.forms[0].elements["assistantOrgId"].value="";//
	document.forms[0].elements["assistantOrgId"].readonly="true";//
	document.forms[0].iscontactid.value=iscontactid;
	var iscontactid = document.forms[0].iscontactid.value;
	if(iscontactid=="2"){
		tdContract.style.display="none";
	}
	if(iscontactid=="1"){
		tdContract.style.display="";
	}
}
function loads(){
    var creditType=document.forms[0].elements["creditType"].value;
	document.forms[0].elements["assistantOrgId"].readonly="readonly";//
  	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	<logic:equal name="endorsecontractTaAF" property="isNeedDel" value="1">
		del();
	</logic:equal>
	document.forms[0].elements["beentruster"].value=document.forms[0].elements["temp_beentruster"].value;
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="确定"){
				s1=i;
			}
			if(document.all.item(i).value=="扫描房屋信息"){
				s2=i;
			}
		}
	}
	var contractIdhl = document.forms[0].elements["contractId"].value.trim();
	if(contractIdhl==""){
		document.all.item(s2).disabled="true";
	}
	var iscome = document.forms[0].isComefrom.value;
	if(iscome == "0"){//从维护过来,合同弹出框禁用
	 	//document.all.submit4.disabled="true";
	 	document.forms[0].elements["submit41"].disabled="true";
	 	document.forms[0].elements["submit4"].disabled="true";
	}
	
	var isview = document.forms[0].isview.value;
	if(isview == "0"){  // 判断是否签订，（0未签订）：保证方,放款银行，合同签订日期，借款起始日期可修改，其他只读
	  	document.all.item(s1).disabled="";
	  	document.all.item(s2).disabled="";
	  
	 	document.forms[0].elements["contractId"].readOnly="readonly";
     	document.forms[0].elements["entruster"].readOnly="readonly";
     	document.forms[0].elements["beentruster"].readOnly="";//受托方(乙方)放款银行
     	document.forms[0].elements["assurer"].readOnly="readonly";
     	document.forms[0].elements["debitter"].readOnly="readonly";
     	document.forms[0].elements["certificateType"].readOnly="readonly";
     	document.forms[0].elements["certificateNum"].readOnly="readonly";
     	document.forms[0].elements["debitMoney"].readOnly="readonly";
     	document.forms[0].elements["term"].readOnly="readonly";
     	document.forms[0].elements["monthInterest"].readOnly="readonly";
     	document.forms[0].elements["creditType"].readOnly="readonly";
     	document.forms[0].elements["contractSureDate"].readOnly="";//合同签订日期
     	document.forms[0].elements["debitMoneyStaDate"].readOnly="";//借款起始日期
     	document.forms[0].elements["debitMoneyEndDate"].readOnly="readonly";
     	document.forms[0].elements["assistantOrgId"].readOnly="readonly";
     
	}else if(isview == "1"){ // 否则（1已签订）全部只读，确定按钮禁用
	 	document.all.item(s1).disabled="true";
	 	document.all.item(s2).disabled="true";
	 
	 	document.forms[0].elements["contractId"].readOnly="readonly";
     	document.forms[0].elements["entruster"].readOnly="readonly";
     	document.forms[0].elements["beentruster"].readOnly="readonly";
     	document.forms[0].elements["assurer"].readOnly="readonly";
     	document.forms[0].elements["debitter"].readOnly="readonly";
     	document.forms[0].elements["certificateType"].readOnly="readonly";
     	document.forms[0].elements["certificateNum"].readOnly="readonly";
     	document.forms[0].elements["debitMoney"].readOnly="readonly";
     	document.forms[0].elements["term"].readOnly="readonly";
     	document.forms[0].elements["monthInterest"].readOnly="readonly";
     	document.forms[0].elements["creditType"].readOnly="readonly";
     	document.forms[0].elements["contractSureDate"].readOnly="readonly";
     	document.forms[0].elements["debitMoneyStaDate"].readOnly="readonly";
     	document.forms[0].elements["debitMoneyEndDate"].readOnly="readonly";
     	document.forms[0].elements["assistantOrgId"].readOnly="readonly";
	}
} 
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return true"
	onload="loads();">
	<jsp:include page="/syscommon/picture/progressbar.jsp" />
	<html:form action="/endorsecontractTaMaintainAC.do">
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
							<td background="<%=path%>/img/table_bg_line.gif" valign="top">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											借款合同信息
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/endorsecontractTbShowAC.do"
												class=a2>抵押合同信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/endorsecontractTeShowAC.do"
												class=a2>签订合同维护</a>
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
											<font color="00B5DB">申请贷款&gt;签订合同</font>
										</td>
										<td width="35" class=font14>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="10">
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="166"
											class="font14">
											<b>借款合同信息</b>
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
					<table width="95%" id="table9" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="21%" class=td1>
								合同编号
							</td>
							<td class="td4" width="16%">
								<html:text name="endorsecontractTaAF" property="contractId"
									onkeydown="gotoEnter();" ondblclick="return executeAjax();"
									styleClass="input3" />
								<input type="hidden" name="paramV"
									value="<bean:write name="endorsecontractTaAF" property="paramValue"/>">
								<input type="hidden" name="isComefrom"
									value="<bean:write name="endorsecontractTaAF" property="isComeFromT5"/>">
								<input type="hidden" name="isview"
									value="<bean:write name="endorsecontractTaAF" property="isview"/>">
							</td>
							<td class="td4" width="12%">
								<input type="button" name="submit41" value="..." class="buttona"
									onclick="checkParamV();">
							</td>
							<td class="td1" width="20%">
								委托方(甲方)
							</td>
							<td class="td4" width="20%">
								<html:text name="endorsecontractTaAF" property="entruster"
									styleClass="input3" onkeydown="enterNextFocus1();"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="21%" class=td1>
								受托方(乙方)
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" colspan="2">
								<html:select name="endorsecontractTaAF"
									property="temp_beentruster" onchange="gotoGetBank();"
									onkeydown="enterNextFocus1();" styleId="bankid"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:options collection="loanBankNameList" property="value"
										labelProperty="label" />
								</html:select>
								<html:hidden name="endorsecontractTaAF" property="beentruster" />
								<select id="childNode" name="select" onchange="getChildValue();"
									style="display:none;width:256px;">
								</select>
							</td>
							<td width="20%" class=td1>
								保证方
							</td>
							<td class="td4" width="20%">
								<font color="#FF0000"> <html:text
										name="endorsecontractTaAF" property="assurer"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
						</tr>

						<tr>
							<td width="21%" class=td1>
								借款方(丙方)
							</td>
							<td class="td4" colspan="2">
								<html:text name="endorsecontractTaAF" property="debitter"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="20%" class=td1>
								证件类型
							</td>
							<td class="td4" width="20%">
								<font color="#FF0000"> <html:text
										name="endorsecontractTaAF" property="certificateType"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
						</tr>
						<tr>
							<td width="21%" class=td1>
								证件号码
							</td>
							<td class="td4" colspan="2">
								<html:text name="endorsecontractTaAF" property="certificateNum"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td width="20%" class=td1>
								借款金额
							</td>
							<td class="td4" width="20%">
								<font color="#FF0000"> <html:text
										name="endorsecontractTaAF" property="debitMoney"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
						</tr>
						<tr>
							<td class=td1 width="21%">
								&#20511;&#27454;&#26399;&#38480;(&#24180;)
							</td>
							<td class="td4" colspan="2">
								<html:text name="endorsecontractTaAF" property="term"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td class=td1 width="20%">
								每月利率
							</td>
							<td class="td4" width="20%">
								<html:text name="endorsecontractTaAF" property="monthInterest"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<html:hidden name="endorsecontractTaAF" property="realMonthInt" />
						</tr>
						<tr>
							<td class=td1 width="21%" height="26">
								还款方式
							</td>
							<td class="td4" colspan="2" height="26">
								<html:text name="endorsecontractTaAF" property="creditType"
									styleClass="input3" />
							</td>
							<td class=td1 width="20%" height="26">
								合同签订日期
							</td>
							<td class="td4" width="20%" height="26">
								<html:text name="endorsecontractTaAF"
									onkeydown="enterNextFocus1();" property="contractSureDate"
									styleClass="input3" />
							</td>
						</tr>

						<tr>
							<td class=td1 width="21%">
								借款起始日期
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="endorsecontractTaAF" property="debitMoneyStaDate"
										onblur="account();" onkeydown="enterNextFocus1();"
										styleClass="input3" /> </font>
							</td>
							<td class=td1 width="20%">
								借款终止日期
							</td>
							<td class="td4" width="20%">
								<html:text name="endorsecontractTaAF"
									property="debitMoneyEndDate" onkeydown="enterNextFocus1();"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="21%" class=td1>
								担保公司名称
							</td>
							<td class="td4" width="16%">
								<html:text name="endorsecontractTaAF" property="assistantOrgId"
									onkeydown="enterNextFocus1();" styleClass="input3"
									readonly="true" />
								<html:hidden name="endorsecontractTaAF"
									property="loanassistantorgId" />
							</td>
							<td class="td4" width="12%">
								<input type="button" name="submit4" value="..." class="buttona"
									onclick="gotoAssistantor();">
							</td>
							<td width="17%" class="td1">
								月还本息
							</td>
							<td height="31" class="td4" width="33%" colspan="2">
								<html:text name="endorsecontractTaAF" property="corpusInterest"
									onkeydown="enterNextFocus1();" styleClass="input3"
									readonly="true" />
								<html:hidden name="endorsecontractTaAF"
									property="hiddenloanMode" />
								<html:hidden name="endorsecontractTaAF" property="iscontactid" />
							</td>
						</tr>
						<tr>
							<td width="21%" class=td1>
								扣款账号
								<font color="red">*</font>
							</td>
							<td class="td4" colspan="2">
								<html:text name="endorsecontractTaAF" property="loanKouAcc"
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
											<html:submit property="method" styleClass="buttonb"
												onclick="return gotoSure(); ">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
										<td width="110">
											<html:submit property="method" styleClass="buttonc">
												<bean:message key="button.scan.houseinfo" />
											</html:submit>
										</td>
										<td width="70" align="center">
											<a
												href='javascript:excHz("<bean:write name="endorsecontractTaAF" property="photoUrl"/>");'>浏览证书</a>
										</td>
										<td width="70" colspan="1" id="tdContract"
											style="display:none">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.disable.contract" />
											</html:submit>
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
</html:html>


