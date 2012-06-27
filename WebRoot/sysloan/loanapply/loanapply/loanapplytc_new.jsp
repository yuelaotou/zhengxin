<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.struts.util.LabelValueBean"%>
<%
String path = request.getContextPath();
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">

function reportsErrors(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>	
	<logic:equal name="loanapplytcnewAF" property="isNeedDel" value="1">
 	 	del();
 	</logic:equal>
}   

function gotoType1(){
	document.loanapplytcnewAF.elements["houseTypehidden"].value="01";	
}
function gotoType2(){
	document.loanapplytcnewAF.elements["houseTypehidden"].value="02";
}
	
function executeAjax() {
   	var queryString = "loanapplytcfindAAC.do?";
    var contractId = document.loanapplytcnewAF.elements["contractId"].value.trim();
   	queryString = queryString + "contractId="+contractId;	     
   	findInfo(queryString);	     
}
function executeAjaxIn() {
   	var buyhouseorgid = document.loanapplytcnewAF.elements["buyhouseorgid"].value.trim();
   	location.href='loanapplytcshowAC.do?buyhouseorgid='+buyhouseorgid;    
}

function showlist() {
  	document.Form1.submit();
}
var s1="";
<%--var s2="";--%>

function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="确定"){
				s1=i;
			}
<%--			if(document.all.item(i).value=="扫描"){--%>
<%--				s2=i;--%>
<%--			}--%>
		}
	} 
	var buyhouseorgid = document.loanapplytcnewAF.elements["buyhouseorgid"].value.trim();
	var coodm = document.loanapplytcnewAF.elements["coodm"].value.trim();
	if(coodm==15){
		document.all.item(s1).disabled="true";
	}
	var houseTypehidden = document.loanapplytcnewAF.elements["houseTypehidden"].value.trim();
	if(houseTypehidden=="01"){
		var selectObj = document.getElementById("select1");
		if(selectObj.length != 0){
			var floorNum = selectObj.options[selectObj.selectedIndex].text;
			document.loanapplytcnewAF.elements["floorNumber"].value = floorNum;
		}
		document.all.radio2.disabled="disabled";
	}
	if(houseTypehidden=="02"){
		document.all.radio1.disabled="disabled";
	}
	var houseTypetemp = document.loanapplytcnewAF.elements["houseTypetemp"].value.trim();
<%--	if(houseTypetemp==""){--%>
<%--		document.all.item(s2).disabled="true";--%>
<%--	}--%>
	var temp_type_remind= document.loanapplytcnewAF.elements["temp_type_remind"].value.trim();
 	var temp_type = document.loanapplytcnewAF.elements["temp_type"].value.trim();
 	if(temp_type=="5"){
		//document.forms[0].elements["firstPay"].readOnly="true";
		//document.forms[0].elements["houseTotlePrice"].readOnly="true";
		document.forms[0].elements["houseArea"].readOnly="true";
		document.forms[0].elements["housePrice"].readOnly="true";
		//document.forms[0].elements["firstTol"].readOnly="true";
 	}
 	if(temp_type=="6"){
 		document.forms[0].elements["bargainorName"].readOnly="true";
		document.forms[0].elements["bargainorHouseArea"].readOnly="true";
		document.forms[0].elements["bargainorTotlePrice"].readOnly="true";
		document.forms[0].elements["bargainorHouseAge"].readOnly="true";
 	}
  	if(temp_type_remind=="8"){
  		document.forms[0].elements["contractId"].readOnly="true";
 		document.forms[0].elements["button8"].disabled="true";
 	}
	//loadSelect();
}

function loadSelect() {
	var selectObj = document.getElementById("select1");
	selectObj.length = 0;
	<%
	   	List floorOListnum=(List)request.getAttribute("floorOListnum");
		for ( int i=0; i < floorOListnum.size(); i++ ) {
			LabelValueBean labelValueBean=(LabelValueBean)floorOListnum.get(i);
			String key=labelValueBean.getLabel();
			String value=labelValueBean.getValue();
	%>
		var childOption = new Option('<%=key%>','<%=value%>');
    	selectObj.options[selectObj.length++] = childOption;
    <%
      	}
    %>
}
function display(contractId,borrowerName){
	document.loanapplytcnewAF.elements["contractId"].value=contractId;
	document.loanapplytcnewAF.elements["borrowerName"].value=borrowerName;
}
function displayerror(mes){
	alert(mes);
	if(mes!="该合同编号不存在或该合同正在办理中！"){
			document.all.item(s1).disabled="true";
			document.forms[0].elements["button9"].disabled="true";
			
	}
	location='loanapplytcshowAC.do';
}	
function ischekcard(){
	var contractId = document.loanapplytcnewAF.elements["contractId"].value.trim();
	var houseTypehidden = document.loanapplytcnewAF.elements["houseTypehidden"].value.trim();
	var orgName = document.loanapplytcnewAF.elements["orgName"].value.trim();
	var developerPaybank = document.loanapplytcnewAF.elements["developerPaybank"].value.trim();
	var developerPaybankAAcc = document.loanapplytcnewAF.elements["developerPaybankAAcc"].value.trim();
	var floorId = document.loanapplytcnewAF.elements["floorId"].value.trim();
	var floorNum = document.loanapplytcnewAF.elements["floorNum"].value.trim();
	var roomNum = document.loanapplytcnewAF.elements["roomNum"].value.trim();
	//var firstPay = document.loanapplytcnewAF.elements["firstPay"].value.trim();
	var houseTotlePrice = document.loanapplytcnewAF.elements["houseTotlePrice"].value.trim();
	var houseArea = document.loanapplytcnewAF.elements["houseArea"].value.trim();
	var housePrice = document.loanapplytcnewAF.elements["housePrice"].value.trim();
	var overDate = document.loanapplytcnewAF.elements["overDate"].value.trim();
	var agreementDate = document.loanapplytcnewAF.elements["agreementDate"].value.trim();
	
	var bargainorName= document.loanapplytcnewAF.elements["bargainorName"].value.trim();
	var bargainorHouseAddr = document.loanapplytcnewAF.elements["bargainorHouseAddr"].value.trim();
	var bargainorPaybank = document.loanapplytcnewAF.elements["bargainorPaybank"].value.trim();
	var bargainorPaybankAcc = document.loanapplytcnewAF.elements["bargainorPaybankAcc"].value.trim();
	var bargainorHouseArea = document.loanapplytcnewAF.elements["bargainorHouseArea"].value.trim();
	var bargainorTotlePrice= document.loanapplytcnewAF.elements["bargainorTotlePrice"].value.trim();
	var bargainorHouseAge = document.loanapplytcnewAF.elements["bargainorHouseAge"].value.trim();
	var bargainorCardKind= document.loanapplytcnewAF.elements["bargainorCardKind"].value.trim();
	var bargainorCardNum= document.loanapplytcnewAF.elements["bargainorCardNum"].value.trim();
	var bargainorAgreementDate= document.loanapplytcnewAF.elements["bargainorAgreementDate"].value.trim();
	if(contractId==""){
		alert('请输入合同编号');
		return false;
	}
	if(houseTypehidden==""){
		alert('请选择购房类型');
		return false;
	}
	if(houseTypehidden=="01"){
		if(orgName==""){
			alert('请输入售房单位名称');
			return false;
		}
		if(developerPaybank==""){
		 	alert('请输入银行名称');
		 	return false;
		}
		if(developerPaybankAAcc==""){
		 	alert('请输入银行帐号');
		 	return false;
		}
		if(floorId==""){
		 	alert('请输入楼盘');
		 	return false;
		}
		if(floorNum==""||floorNum=="all"){
		 	alert('请输入楼号');
		 	return false;
		}
		if(roomNum==""){
		 	alert('请输入层次室号');
		 	return false;
		}
		if(houseTotlePrice==""){
		 	alert('请输入房屋总价');
		 	return false;
		}
		if(houseArea==""){
		 	alert('请输入房屋面积');
		 	return false;
		}
		if(housePrice==""){
		 	alert('请输入房屋单价');
		 	return false;
		}
		if(overDate!=""){
			if(!isNumber(overDate)){
				alert("请输入正确的竣工日期,例如：20070101");
				return false;
			}
			var a=checkDate(document.all.overDate);
			
			if(!a){
				return false;
			}
		}
		if(agreementDate!=""){
			if(!isNumber(agreementDate)){
				alert("请输入正确的购房合同签字日期,例如：20070101");
				return false;
			}
			var b=checkDate(document.all.agreementDate);
			if(!b){
				return false;
			}
		}
	}else{
		if(bargainorName==""){
		 alert('请输入售房人姓名');
		 return false;
		}
		if(bargainorHouseAddr==""){
		 alert('请输入房屋坐落');
		 return false;
		}
		if(bargainorPaybank==""){
		 alert('请输入售房人收款银行');
		 return false;
		}
		if(bargainorPaybankAcc==""){
		 alert('请输入售房人收款银行账号 ');
		 return false;
		}
		if(bargainorHouseArea==""){
		 alert('请输入建筑面积');
		 return false;
		}
		if(bargainorTotlePrice==""){
		 alert('请输入评估总价');
		 return false;
		}
		if(bargainorHouseAge==""){
		 	alert('请输入房龄');
		 	return false;
		}
		if(bargainorCardKind=='0'){
			return isIdCardNo(bargainorCardNum);
		}
		if(bargainorAgreementDate!=""){
		if(!isNumber(bargainorAgreementDate)){
			alert("请输入正确的协议签订日期,例如：20070101");
			return false;
		}
		var c=checkDate(document.all.bargainorAgreementDate);
			if(!c){
				return false;
			}
		}
	}
}
//保存选中的楼盘号的文本
function setFloorNumber(){
	var selectObj = document.getElementById("select1");
	var floorNum = selectObj.options[selectObj.selectedIndex].text;
	document.loanapplytcnewAF.elements["floorNumber"].value = floorNum;
	displayHouseAdd();
}
function getBankAcc(){
	var developerPaybank = document.loanapplytcnewAF.elements["developerPaybank"].value.trim();
  	var queryString = "loanapplytcfindbankAAC.do?";
    queryString = queryString + "developerPaybank="+developerPaybank;	     
	findInfo(queryString);	     
}
function dispplayAcc(banName,developerPaybankAAcc){
	document.loanapplytcnewAF.elements["developerPaybankAAcc"].value=developerPaybankAAcc;
	document.loanapplytcnewAF.elements["banNamehidden"].value=banName;
	
}
function checkmonths(String){ 
    var Letters = "1234567890."; 
    var i;
    var c;
    if(String.charAt( 0 )=='.')
 		return false;
    if( String.charAt( String.length - 1 ) == '.' )
    	return false;
    for( i = 0; i < String.length; i ++ ){
	    c = String.charAt( i );
	   	if (Letters.indexOf( c ) < 0)
	    	return false;
	}
    return true;
}


function acounthouseTotle(){
	var houseTotlePrice = document.loanapplytcnewAF.elements["houseTotlePrice"].value.trim();
	if(!checkmonths(houseTotlePrice)){
		document.loanapplytcnewAF.elements["houseTotlePrice"].value="";
		alert("请输入正确金额,例如1200.36");
		return false;
	}
	var ahouseArea = document.loanapplytcnewAF.elements["houseArea"].value.trim();
	if(ahouseArea!=""&&houseTotlePrice!=""){
		acounthouseArea();
	}
}
function acounthouseArea(){
	var houseTotlePrice = document.loanapplytcnewAF.elements["houseTotlePrice"].value.trim();
	var houseArea = document.loanapplytcnewAF.elements["houseArea"].value.trim();
	if(!checkmonths(houseArea)){
		document.loanapplytcnewAF.elements["houseArea"].value="";
		alert("请输入正确建筑面积,例如120.36");
		return false;
	}
 	if(houseArea!=""&&houseTotlePrice!=""){
 		var unitprice = parseFloat(houseTotlePrice)/parseFloat(houseArea)+"";
 		var indx = unitprice.indexOf(".",0);
 		if(indx!=-1){
			unitprice = unitprice.substring(0,indx+3);
 		}
		document.loanapplytcnewAF.elements["housePrice"].value = parseFloat(unitprice);
 	}
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
//********************************************************
function findInfo12(url) {
 createXMLHttpRequest();  
	    xmlHttp.onreadystatechange = handleStateChange1;
	    xmlHttp.open("GET", url, true);
	    xmlHttp.send(null);   
}

function handleStateChange1() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
        var xmlDoc = xmlHttp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		var texts  = xmlDoc.getElementsByTagName("text");
		var selectObj = document.getElementById("select1");
		selectObj.length = 0;
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
			if(i==0){
				document.all.floorNum.value=values[i].firstChild.data;
			}
		}
      }
   }
}
//楼盘下拉发生改变触发
function getChildOption() {
	var floorId = document.loanapplytcnewAF.elements["floorId"].value.trim();
	var buyhouseorgid = document.loanapplytcnewAF.elements["buyhouseorgid"].value.trim();
	var url = "loanapplytcfindfloorAAC.do?floorId="+encodeURI(floorId)+"&buyhouseorgid="+buyhouseorgid;
	findInfo12(url);
}
function findorghouse(){
	var contractId = document.loanapplytcnewAF.elements["contractId"].value.trim();
	if(contractId==""){
		alert("请输入合同编号");
		return false;
	}
  	window.open("<%=path%>/sysloan/develepershowAC.do?indexs="+6+"&contractId="+contractId+"&objInput=orgName"+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=no"); 
}
//显示房屋地址
function displayHouseAdd(){
	var floorName = document.loanapplytcnewAF.elements["floorId"].value.trim();
	var selectObj = document.getElementById("select1");
	// floorNumber是楼盘号下拉显示的不是value
	var floorNumber = selectObj.options[selectObj.selectedIndex].text;
	var roomNum = document.loanapplytcnewAF.elements["roomNum"].value.trim();
	if(floorName!=""&&floorNumber!=""&&roomNum!=""){
		document.loanapplytcnewAF.elements["houseAddr"].value = floorName +"-"+ floorNumber + roomNum;
	}
}
</script>
	<body bgcolor="#FFFFFF" text="#000000"
		onload="loads();reportsErrors();">
		<jsp:include page="/syscommon/picture/progressbar.jsp" />
		<html:form action="/loanapplytcmaintianAC.do">
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
												background="<%=path%>/img/buttong.gif" align="center"
												valign="top" style="PADDING-top: 7px">
												<a href="<%=path%>/sysloan/showLoanapplyAC.do" class=a2>借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/loanapplytbshowAC.do" class=a2>共同借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttonbl.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/loanapplytcshowAC.do" class=a2>购房信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/loanapplytdshowAC.do" class=a2>借款人额度信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/loanapplyteshowAC.do" class=a2>申请贷款维护</a>
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
											<td height="22" bgcolor="#CCCCCC" align="center" width="122"
												class="font14">
												<b>购 房 信 息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="802">
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
								<td rowspan="12" class=td1 width="11%">
									<html:radio name="loanapplytcnewAF" property="houseType"
										value="01" onclick="gotoType1();" styleId="radio1" />
									商品房
								</td>
								<td width="21%" class=td1>
									合同编号
								</td>
								<td class="td4" width="16%">
									<html:text property="contractId" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="gotoEnter();" />
								</td>
								<td width="8%">
									<input type="button" class="buttona" value="..." name="button8"
										onClick="gotoContractpop('','<%=path%>','0','1');">
								</td>
								<html:hidden name="loanapplytcnewAF" property="banNamehidden" />
								<html:hidden name="loanapplytcnewAF" property="temp_type_remind" />
								<html:hidden name="loanapplytcnewAF" property="temp_type" />
								<html:hidden name="loanapplytcnewAF" property="coodm" />
								<html:hidden name="loanapplytcnewAF" property="houseTypetemp" />
								<html:hidden name="loanapplytcnewAF" property="speAppFlag" />
								<td class="td1" width="20%">
									借款人姓名
								</td>
								<td class="td4" width="20%">
									<html:text property="borrowerName" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="21%" class=td1>
									售房单位
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="16%">
									<html:text property="orgName" name="loanapplytcnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<html:hidden name="loanapplytcnewAF" property="buyhouseorgid" />
								<td class="td4" width="8%">
									<input type="button" class="buttona" value="..." name="button9"
										onclick="findorghouse();">
								</td>
								<td class="td1" width="20%">
									联系电话
								</td>
								<td class="td4" width="20%">
									<html:text property="developerTel" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" class=td1>
									售房单位售房款开户行
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:select name="loanapplytcnewAF"
										property="developerPaybank" styleClass="input4"
										onchange="getBankAcc()" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="bankOList" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td width="20%" class=td1>
									售房款开户账号
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="developerPaybankAAcc"
										name="loanapplytcnewAF" styleClass="input3" readonly="true"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td class=td1 width="21%">
									所在楼盘
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:select name="loanapplytcnewAF" property="floorId"
										styleClass="input4" onchange="getChildOption()"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="floorOList" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td class=td1 width="20%">
									楼盘号
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%" id="td1">
									<html:select name="loanapplytcnewAF" property="floorNum"
										styleClass="input4" styleId="select1"
										onkeydown="enterNextFocus1();" onchange="setFloorNumber();">
										<html:options collection="floorOListnum" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<html:hidden name="loanapplytcnewAF" property="floorNumber" />
							</tr>
							<tr>
								<td class=td1 width="21%">
									层次室号
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:text name="loanapplytcnewAF" property="roomNum"
										onkeydown="enterNextFocus1();" onblur="displayHouseAdd();" 
										styleClass="input3">
									</html:text>
								</td>
								<td class=td1 width="20%">
									是否现房
								</td>
								<td class="td7" width="20%">
									<html:select name="loanapplytcnewAF" property="isNowhouse"
										styleClass="input4" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="isNowhouseMap"
											name="loanapplytcnewAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td class=td1 width="21%">
									房屋总价（元）
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="houseTotlePrice" name="loanapplytcnewAF"
										styleClass="input3" onblur="return acounthouseTotle();"
										onkeydown="enterNextFocus1();" />
								</td>
								<td class=td1 width="20%">
									建筑面积（M
									<sup>
										2
									</sup>
									）
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="houseArea" name="loanapplytcnewAF"
										styleClass="input3" onblur="return acounthouseArea();"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td class=td1 width="21%">
									房屋单价（元/M
									<sup>
										2）
									</sup>
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="housePrice" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td class=td1 width="20%">
									购房合同编号
								</td>
								<td class="td4" width="20%">
									<html:text property="buyHouseContractId"
										name="loanapplytcnewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr style="display:none">
								<td width="21%" class=td1>
									拨款开户行
								</td>
								<td height="31" class="td4" colspan="2">
									<html:text property="fundBank" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="20%" class=td1>
									拨款开户账号
								</td>
								<td height="31" class="td4" width="20%">
									<html:text property="fundBankAcc" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" class=td1>
									竣工日期
								</td>
								<td height="31" class="td4" colspan="2">
									<html:text property="overDate" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="20%" class=td1>
									产权证号码
								</td>
								<td height="31" class="td4" width="20%">
									<html:text property="buildRightNum" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" class=td1>
									购房合同签字日期
								</td>
								<td height="31" class="td4" colspan="2">
									<html:text property="agreementDate" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="20%" class=td1>
									&nbsp;
								</td>
								<td align="center" class="td7">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td width="21%" class="td1">
									购房地址
								</td>
								<td colspan="4" class="td4">
									<html:text property="houseAddr" name="loanapplytcnewAF"
										readonly="true" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" class="td1">
									备注
								</td>
								<td colspan="4" class="td4">
									<html:text property="remark1" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<html:hidden name="loanapplytcnewAF" property="houseTypehidden" />
								<td width="11%" rowspan="8" bgcolor="#D1EDD7">
									<html:radio name="loanapplytcnewAF" property="houseType"
										value="02" onclick="gotoType2();" styleId="radio2" />
									二手房
								</td>
								<td width="21%" bgcolor="#D1EDD7">
									售房人姓名
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="bargainorName" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td bgcolor="#D1EDD7" width="20%">
									售房人证件类型
								</td>
								<td class="td4" width="20%">
									<html:select name="loanapplytcnewAF"
										property="bargainorCardKind" styleClass="input4"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="bargainorCardKindMap"
											name="loanapplytcnewAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									售房人证件号码
								</td>
								<td class="td4" colspan="2">
									<html:text property="bargainorCardNum" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td bgcolor="#D1EDD7" width="20%">
									固定电话
								</td>
								<td class="td4" width="20%">
									<html:text property="bargainorTel" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									原房产权编号
								</td>
								<td colspan="2" class="td4">
									<html:text property="bargainorHousepropertyCode"
										name="loanapplytcnewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td bgcolor="#D1EDD7" width="20%">
									移动电话
								</td>
								<td class="td4" width="20%">
									<html:text property="bargainorMobile" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									房屋坐落
									<font color="#FF0000">*</font>
								</td>
								<td colspan="4" class="td4">
									<html:text property="bargainorHouseAddr"
										name="loanapplytcnewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									售房人收款银行
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="bargainorPaybank" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="20%" bgcolor="#D1EDD7">
									售房人收款银行账号
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="bargainorPaybankAcc"
										name="loanapplytcnewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									建筑面积（M
									<sup>
										2
									</sup>
									）
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="bargainorHouseArea"
										name="loanapplytcnewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="20%" bgcolor="#D1EDD7">
									评估价值（元）
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="20%">
									<html:text property="bargainorTotlePrice"
										name="loanapplytcnewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td height="31" width="21%" bgcolor="#D1EDD7">
									建筑年限（年）
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" height="31" colspan="2">
									<html:text property="bargainorHouseAge" name="loanapplytcnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td bgcolor="#D1EDD7" height="31" width="20%">
									协议签订日期
								</td>
								<td class="td4" height="31" width="20%">
									<html:text property="bargainorAgreementDate"
										name="loanapplytcnewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="21%" bgcolor="#D1EDD7">
									备注
								</td>
								<td colspan="4" class="td4">
									<html:text property="remark2" name="loanapplytcnewAF"
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
												<html:submit property="method" styleClass="buttona"
													onclick="return ischekcard();">
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
													href='javascript:excHz("<bean:write name="loanapplytcnewAF" property="photoUrl"/>");'>浏览证书</a>
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
		<form action="loanapplytcshowAC.do" method="POST" name="Form1"
			id="Form1">
		</form>
	</body>
</html>


