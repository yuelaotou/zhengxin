<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>

<%
	String path = request.getContextPath();
	String contractid = (String) request.getSession().getAttribute(
			"contractid");
	String loanType="";
	if(request.getAttribute("loanType_wsh")!=null){
		loanType=(String)request.getAttribute("loanType_wsh");
	}
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

var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
<%--var s6="";--%>
var tr='tr0';
function loads(){

	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="添加"){
				s1=i;
			}
			if(document.all.item(i).value=="确定"){
				s2=i;
			}
			if(document.all.item(i).value=="修改"){
				s3=i;
			}
			if(document.all.item(i).value=="删除"){
				s4=i;
			}
			if(document.all.item(i).value=="查看职工明细账"){
				s5=i;
			}
<%--			if(document.all.item(i).value=="扫描"){--%>
<%--				s6=i;--%>
<%--			}	--%>
		}
	} 
	//将职工编号和单位编号分别补0职工编号6位,单位编号10位
	var empId = document.loanapplytbnewAF.elements["empId"].value;
	document.loanapplytbnewAF.elements["empId"].value = format(empId)+empId;
	var orgId = document.loanapplytbnewAF.elements["orgId"].value;
	document.loanapplytbnewAF.elements["orgId"].value = formatTen(orgId)+orgId;
	
	var temp_types = document.loanapplytbnewAF.elements["temp_types"].value.trim();
	var coodm = document.loanapplytbnewAF.elements["coodm"].value.trim();
	var coodm_type = document.loanapplytbnewAF.elements["coodm_type"].value.trim();
	if(coodm_type==4){
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
<%--		document.all.item(s6).disabled="true";--%>
	}
	if(coodm_type==5){
		document.all.item(s1).disabled="true";
  		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
<%--		document.all.item(s6).disabled="true";--%>
	}
	if(temp_types==11){
		document.all.item(s1).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.buttonemp.disabled="true";
	}
 	var temp_tes = document.loanapplytbnewAF.elements["temp_tes"].value.trim();
	if(temp_tes==1){
		document.forms[0].elements["contractId"].readOnly="readOnly";
		document.forms[0].elements["borrowerName"].readOnly="readOnly";
		document.forms[0].elements["empId"].readOnly="readOnly";
		document.forms[0].elements["name"].readOnly="readOnly";
		//document.forms[0].elements["relation"].readOnly="readOnly";
		document.forms[0].elements["sex"].disabled="disabled";
		document.forms[0].elements["cardKind"].disabled="disabled";
		document.forms[0].elements["cardNum"].readOnly="readOnly";
		document.forms[0].elements["birthday"].readOnly="readOnly";
		document.forms[0].elements["age"].readOnly="readOnly";
		document.forms[0].elements["monthSalary"].readOnly="readOnly";
		
		document.forms[0].elements["orgId"].readOnly="readOnly";
		document.forms[0].elements["accBlnce"].readOnly="readOnly";
		document.forms[0].elements["monthPay"].readOnly="readOnly";
		document.forms[0].elements["empSt"].readOnly="readOnly";
		document.forms[0].elements["bgnDate"].readOnly="readOnly";
		document.forms[0].elements["endDate"].readOnly="readOnly";
	
	}
	if(temp_tes==2){
		//document.forms[0].elements["name"].readOnly="readOnly";
		//document.forms[0].elements["cardNum"].readOnly="readOnly";
	}
	if(temp_tes==3){
		document.all.item(s1).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		//document.forms[0].elements["name"].readOnly="readOnly";
		//document.forms[0].elements["cardKind"].disabled="disabled";
		//document.forms[0].elements["cardNum"].readOnly="readOnly";
		//document.forms[0].elements["sex"].disabled="disabled";
		//document.forms[0].elements["birthday"].readOnly="readOnly";
		//document.forms[0].elements["age"].readOnly="readOnly";
		//document.forms[0].elements["monthSalary"].readOnly="readOnly";
	}
	if(temp_tes==5){
		document.forms[0].elements["contractId"].readOnly="readOnly";
		document.forms[0].elements["borrowerName"].readOnly="readOnly";
		if(coodm_type==4){
			document.all.item(s3).disabled="true";
			document.all.item(s4).disabled="true";
		}
	}
	var count = document.loanapplytbnewAF.elements["count"].value.trim();

	if(count!='0'){
		var empid=document.getElementById(tr).childNodes[1].childNodes[0].innerHTML;
		if(empid==""){
			document.all.item(s5).disabled="true";
		}
	}
}

function display(messages){
	alert(messages);
	if(messages=="该借款人已经录入额度信息，不能添加辅助借款人信息！"){
		document.all.item(s1).disabled="true";
  		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
<%--		document.all.item(s6).disabled="true";--%>
	}
	location='loanapplytbshowAC.do';
}
function displaycontractId(contractId){
	document.loanapplytbnewAF.elements["contractId"].value=contractId;
	showlist();
}
function showlist() {
  	document.Form1.submit();
}

function executeAjax() {
	var contractId = document.loanapplytbnewAF.elements["contractId"].value.trim();
   	var queryString = "loanapplytbfindAAC.do?";
    queryString = queryString + "contractId="+contractId;    
   	findInfo(queryString);	     
}

</script>

<script>
function executeAjaxIn() {
	var empId = document.loanapplytbnewAF.elements["empId"].value.trim();
	var orgId = document.loanapplytbnewAF.elements["org_Id"].value.trim();//取出隐藏域里的值
	var contractaId = document.loanapplytbnewAF.elements["contractId"].value.trim();
   	var queryString = "loanapplytbfindempinfoAAC.do?";
    queryString = queryString + "empId="+empId+"&orgId="+orgId+"&contractaId="+contractaId;    
   	findInfo(queryString);	     
}

function displayIn(empId,name,relation,sex,cardKind,cardNum,birthday,nation,nativePlace,business,title,marriageSt,degree,homeAddr,homeMail,homeMobile,houseTel,orgId,orgName,orgTel,orgAddr,orgMail,accBlnce,monthSalary,monthPay,empSt,bgnDate,endDate,zdjzqje){
		var b=birthday.substr(0,4);
		var d= new Date();
		var year= d.getYear();
		var age=parseFloat(year)-parseFloat(b);
		document.loanapplytbnewAF.elements["sexhidden"].value=sex;
		document.loanapplytbnewAF.elements["cardKindhidden"].value=cardKind;
		
		document.loanapplytbnewAF.elements["empId"].value=empId;
		document.loanapplytbnewAF.elements["name"].value=name;
		//document.loanapplytbnewAF.elements["relation"].value=relation;
		document.loanapplytbnewAF.elements["sex"].value=sex;
		document.loanapplytbnewAF.elements["cardKind"].value=cardKind;
		document.loanapplytbnewAF.elements["cardNum"].value=cardNum;
		var date,birthday_;
		if(cardNum.length==15){
			birthday_ ="19"+cardNum.substr(6,6);
			document.loanapplytbnewAF.elements["birthday"].value=birthday_;
		}else{
			birthday_=cardNum.substr(6,8)
			document.loanapplytbnewAF.elements["birthday"].value=birthday_;
		}
		date = new Date();
		var year = date.getYear();
		document.forms[0].elements["age"].value=parseInt(year)-parseInt(birthday_.substr(0,4));
		document.loanapplytbnewAF.elements["nation"].value=nation
		document.loanapplytbnewAF.elements["nativePlace"].value=nativePlace;
		document.loanapplytbnewAF.elements["business"].value=business;
		document.loanapplytbnewAF.elements["title"].value=title;
		//document.loanapplytbnewAF.elements["marriageSt"].value=marriageSt;
		document.loanapplytbnewAF.elements["degree"].value=degree;
		document.loanapplytbnewAF.elements["homeAddr"].value=homeAddr;
		document.loanapplytbnewAF.elements["homeMail"].value=homeMail;
		document.loanapplytbnewAF.elements["homeMobile"].value=homeMobile;
		document.loanapplytbnewAF.elements["houseTel"].value=houseTel;
		document.loanapplytbnewAF.elements["orgId"].value=orgId;
		document.loanapplytbnewAF.elements["orgName"].value=orgName;
		document.loanapplytbnewAF.elements["orgTel"].value=orgTel;
		document.loanapplytbnewAF.elements["orgAddr"].value=orgAddr;
		document.loanapplytbnewAF.elements["orgMail"].value=orgMail;
		document.loanapplytbnewAF.elements["accBlnce"].value=accBlnce;
		document.loanapplytbnewAF.elements["monthSalary"].value=monthSalary;
		document.loanapplytbnewAF.elements["monthPay"].value=monthPay;
		document.loanapplytbnewAF.elements["empSt"].value=empSt;
		document.loanapplytbnewAF.elements["bgnDate"].value=bgnDate;
		document.loanapplytbnewAF.elements["endDate"].value=endDate;
		document.loanapplytbnewAF.elements["zdjzqje"].value=zdjzqje;
	
		document.forms[0].elements["empId"].readOnly="readOnly";
		document.forms[0].elements["name"].readOnly="readOnly";
		//document.forms[0].elements["relation"].readOnly="";
		document.forms[0].elements["sex"].disabled="disabled";
		document.forms[0].elements["cardKind"].disabled="disabled";
		document.forms[0].elements["cardNum"].readOnly="readOnly";
		document.forms[0].elements["birthday"].readOnly="readOnly";
		document.forms[0].elements["age"].readOnly="readOnly";
		document.forms[0].elements["monthSalary"].readOnly="readOnly";
		
		document.forms[0].elements["orgId"].readOnly="readOnly";
		document.forms[0].elements["accBlnce"].readOnly="readOnly";
		document.forms[0].elements["monthPay"].readOnly="readOnly";
		document.forms[0].elements["empSt"].readOnly="readOnly";
		document.forms[0].elements["bgnDate"].readOnly="readOnly";
		document.forms[0].elements["endDate"].readOnly="readOnly";
		
}
function displayInerror(errmes){
	alert(errmes);
	location='loanapplytbshowAC.do';
}

function checkmonthpay(String){ 
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


function ischeckall(){
var contractId = document.loanapplytbnewAF.elements["contractId"].value.trim();
var name = document.loanapplytbnewAF.elements["name"].value.trim();
//var relation = document.loanapplytbnewAF.elements["relation"].value.trim();
var sex = document.loanapplytbnewAF.elements["sexhidden"].value.trim();

var cardKind = document.loanapplytbnewAF.elements["cardKindhidden"].value.trim();
var cardNum = document.loanapplytbnewAF.elements["cardNum"].value.trim();
var birthday = document.loanapplytbnewAF.elements["birthday"].value.trim();
var age = document.loanapplytbnewAF.elements["age"].value.trim();
var monthSalary = document.loanapplytbnewAF.elements["monthSalary"].value.trim();

var bgnDate = document.loanapplytbnewAF.elements["bgnDate"].value.trim();
var endDate = document.loanapplytbnewAF.elements["endDate"].value.trim();
var loanType="<%=loanType%>";
<%--	if(loanType==1){--%>
<%--		var accBlnce = document.loanapplytbnewAF.elements["accBlnce"].value.trim();--%>
<%--		if(accBlnce==0){--%>
<%--			alert("请输入余额");--%>
<%--			return false;--%>
<%--		}--%>
<%--		if(!checkmonthpay(accBlnce)){--%>
<%--			alert("请输入正确余额，例如：1500.27");--%>
<%--			return false;--%>
<%--		}--%>
<%--		var strMonth = document.loanapplytbnewAF.elements["bgnDate"].value.trim();--%>
<%--		  if(strMonth.length!=6)--%>
<%--		  {--%>
<%--		    alert("请输入六位的初缴年月，格式为200410！");--%>
<%--		    return false;--%>
<%--		  }--%>
<%--		  if(strMonth.substring(0,4)<1900){--%>
<%--		    alert("年份应该大于1900！");--%>
<%--		    return false;--%>
<%--		  }--%>
<%--		  if(strMonth.substring(4,6)>12 || strMonth.substring(4,6)<1)--%>
<%--		  {--%>
<%--		    alert("月份应该在1-12月之间！");--%>
<%--		    return false;--%>
<%--		  }--%>
<%--		  var strMonthend = document.loanapplytbnewAF.elements["endDate"].value.trim();--%>
<%--		  if(strMonthend.length!=6)--%>
<%--		  {--%>
<%--		    alert("请输入六位的缴至年月，格式为200410！");--%>
<%--		    return false;--%>
<%--		  }--%>
<%--		  if(strMonthend.substring(0,4)<1900){--%>
<%--		    alert("年份应该大于1900！");--%>
<%--		    return false;--%>
<%--		  }--%>
<%--		  if(strMonthend.substring(4,6)>12 || strMonth.substring(4,6)<1)--%>
<%--		  {--%>
<%--		    alert("月份应该在1-12月之间！");--%>
<%--		    return false;--%>
<%--		  }--%>
<%--    }--%>
	if(contractId==0){
		alert("请输入合同编号");
		return false;
	}
	if(name==0){
		alert("请输入员工姓名");
		return false;
	}							
	
	if(sex==""){
		alert("请输入性别");
		return false;
	}
	if(cardKind==""){
		alert("请输入证件类型");
		return false;
	}
	if(cardNum==""){
		alert("请输入证件号码");
		return false;
	}

	if(birthday==""){
		alert("请输入出生年月日");
		return false;
	}
	if(!isNumber(birthday)){
		alert("请输入出生年月,例如：20070101");
		return false;
	}
	if(age==""){
	alert("请输入年龄");
	return false;
	}
	if(!isNumber(age)){
		alert("请输入正确格式的年龄");
		return false;
	}
	
	if(monthSalary==""){
		alert("请输入月工资额");
		return false;
	}
	if(!checkmonthpay(monthSalary)){
		alert("请输入正确格式的月工资额,例如：1500.27");
	return false;
	}
	if(cardKind==0){
		var cardnum = document.loanapplytbnewAF.elements["cardNum"].value.trim();
		return isIdCardNo(cardnum);
	}
	if(!isNumber(bgnDate)){
		alert("请输入正确的初缴年月,例如：20070101");
	return false;
	}
	
	if(!isNumber(endDate)){
		alert("请输入正确的缴至年月,例如：20070101");
	return false;
	}
	

}
function accountage(){

var birthday = document.loanapplytbnewAF.elements["birthday"].value.trim();
if(birthday==""){
	alert("请输入正确格式的出生年月,例如：20070101");
	document.loanapplytbnewAF.elements["birthday"].value="";
	return false;	
}
if(!isNumber(birthday)){
alert("请输入正确格式的出生年月,例如：20070101");
document.loanapplytbnewAF.elements["birthday"].value="";
return false;	
}
var b=birthday.substr(0,4);

var d= new Date();
var year= d.getYear();
var age=parseFloat(year)-parseFloat(b);
document.loanapplytbnewAF.elements["age"].value=age;
}

function reportsErrors(){
<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"

		alert(message);
	</logic:messagesPresent>	
	 <logic:equal name="loanapplytbnewAF" property="isNeedDel" value="1">
 	 	del();
 	 </logic:equal>
}   

function findEmps(){
	window.open('<%=request.getContextPath()%>/sysloan/showEmployeesListAC.do?orgid='+''+'&st='+''+'&cardnum='+''+'&empname='+''+'&indexs='+9+'&objInput=empId','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,status=no');
	}
function tosex(){
document.loanapplytbnewAF.elements["sexhidden"].value=document.loanapplytbnewAF.elements["sex"].value.trim();
		
}
function tocardking(){

		document.loanapplytbnewAF.elements["cardKindhidden"].value = document.loanapplytbnewAF.elements["cardKind"].value.trim();
}
// 根据身份证计算出时间与性别
function  checkCardNum(){
	var num=document.forms[0].elements["cardNum"].value.trim();
	var numType=document.forms[0].elements["cardKindhidden"].value;;
	if(numType==0){
		if(num!=""){
			if(num.length==15){
				var date ="19"+num.substr(6,6);
				document.forms[0].elements["birthday"].value=date;
				accountage();
			}else{
				var date=num.substr(6,8)
				document.forms[0].elements["birthday"].value=date;
				accountage();
			}
			return true;
		}
     }
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}

function gettr(indexs){
tr=indexs;
var empid=document.getElementById(tr).childNodes[1].childNodes[0].innerHTML;
	if(empid==""){
	document.all.item(s5).disabled="true";
	}else{
	document.all.item(s5).disabled="";
	}

}

function gotoEmpAccount(){
	var borrowerName =document.getElementById(tr).childNodes[2].childNodes[0].innerHTML;
	borrowerName =encodeURI(borrowerName);
	var cardNum =document.getElementById(tr).childNodes[3].childNodes[0].innerHTML;
	window.open('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'newwindow','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=no,location=no,status=no');
	return false;
}
</script>
	<body bgcolor="#FFFFFF" text="#000000"
		onload="loads();reportsErrors();">
		<jsp:include page="/syscommon/picture/progressbar.jsp" />
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
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/showLoanapplyAC.do" class=a2>借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											<a href="<%=path%>/sysloan/loanapplytbshowAC.do" class=a2>共同借款人信息</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="188">
											<b class="font14">共同借款人基本信息</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="736">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/loanapplytbtopmaintianAC.do" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td class=td1 width="12%">
									合同编号
								</td>
								<logic:equal value="2" name="loanapplytbnewAF"
									property="typenum">
									<td class="td4" width="8%">
										<html:text property="contractId" name="loanapplytbnewAF"
											styleClass="input3" onkeydown="gotoEnter();" />
									</td>
								</logic:equal>

								<logic:equal value="1" name="loanapplytbnewAF"
									property="typenum">
									<td class="td4" width="8%">
										<html:text property="contractId" name="loanapplytbnewAF"
											styleClass="input3" readonly="true" />
									</td>
								</logic:equal>


								<logic:equal value="2" name="loanapplytbnewAF"
									property="typenum">
									<td width="11%">
										<input type="button" class="buttona" value="..."
											onClick="gotoContractpop('','<%=path%>','0','1');">
									</td>
								</logic:equal>

								<logic:equal value="1" name="loanapplytbnewAF"
									property="typenum">
									<td width="11%">
										<input type="button" class="buttona" value="..."
											onClick="gotoContractpop('','<%=path%>','0','1');" disabled>
									</td>
								</logic:equal>
								<td class="td1" width="12%">
									借款人姓名
								</td>
								<td class="td4" width="17%">
									<html:text property="borrowerName" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();"
										readonly="true" />
								</td>
								<td class=td1 width="12%">
									共同借款人状态
								</td>
								<td class="td4" width="30%">
									<html:text property="relationStatus" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td class=td1 width="12%">
									职工编号
								</td>
								<td class="td4" width="8%">
									<html:text property="empId" name="loanapplytbnewAF"
										styleClass="input3" readonly="true" />
								</td>

								<td class="td4">
									<input type="button" class="buttona" value="..."
										name="buttonemp" onclick="findEmps();">
								</td>
								<td class="td1" width="12%">
									职工姓名
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" width="17%">
									<html:text property="name" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<html:hidden name="loanapplytbnewAF" property="temp_tes" />

								<html:hidden name="loanapplytbnewAF" property="temp_types" />

								<td class=td1 width="12%">
									与借款人关系
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" width="30%">
									<html:select name="loanapplytbnewAF" property="relation"
										styleClass="input4" 
										onkeydown="enterNextFocus1();">
										<html:optionsCollection property="relationMap"
											name="loanapplytbnewAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td class=td1>
									性别
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:select name="loanapplytbnewAF" property="sex"
										styleClass="input4" onchange="tosex()"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="sexMap"
											name="loanapplytbnewAF" label="value" value="key" />
									</html:select>
								</td>
								<td class=td1>
									证件类型
								</td>
								<td class="td4">
									<html:select name="loanapplytbnewAF" property="cardKind"
										styleClass="input4" onchange="tocardking()"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="cardkingMap"
											name="loanapplytbnewAF" label="value" value="key" />
									</html:select>
								</td>
								<td class=td1>
									证件号码
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4">
									<html:text property="cardNum" name="loanapplytbnewAF"
										styleClass="input3" onblur="return checkCardNum();"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									出生日期
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="birthday" name="loanapplytbnewAF"
										styleClass="input3" onblur="accountage();"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									年龄
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" width="17%">
									<html:text property="age" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									民族
								</td>
								<td class="td4" width="30%">
									<html:text property="nation" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									户籍所在地
								</td>
								<td class="td4" colspan="2">
									<html:text property="nativePlace" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									职务
								</td>
								<td class="td4" width="17%">
									<html:text property="business" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									职称
								</td>
								<td class="td4" width="30%">
									<html:text property="title" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>

								</td>
								<td class="td4" colspan="2">

								</td>
								<td width="12%" class=td1>
									文化程度
								</td>
								<td class="td4" width="17%">
									<html:text property="degree" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									家庭住址
								</td>
								<td class="td4" width="30%">
									<html:text property="homeAddr" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									邮政编码
								</td>
								<td class="td4" colspan="2">
									<html:text property="homeMail" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									移动电话
								</td>
								<td class="td4" width="17%">
									<html:text property="homeMobile" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									住宅电话
								</td>
								<td class="td4" width="30%">
									<html:text property="houseTel" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="188">
												<b class="font14">共同借款人公积金归集信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="722">
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
								<html:hidden name="loanapplytbnewAF" property="org_Id" />
								<html:hidden name="loanapplytbnewAF" property="coodm_type" />
								<html:hidden name="loanapplytbnewAF" property="sexhidden" />
								<html:hidden name="loanapplytbnewAF" property="cardKindhidden" />
								<html:hidden name="loanapplytbnewAF" property="count" />
								<td width="12%" class=td1>
									单位编号
								</td>
								<td class="td4" colspan="2">
									<html:text property="orgId" name="loanapplytbnewAF"
										styleClass="input3" readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位名称
								</td>
								<td class="td4" width="17%" colspan="4">
									<html:text property="orgName" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>

							</tr>
							<tr>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="orgTel" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									邮政编码
								</td>
								<td class="td4" width="17%">
									<html:text property="orgMail" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									账户状态
								</td>
								<td class="td4" width="30%">
									<html:text property="empSt" name="loanapplytbnewAF"
										styleClass="input3" readonly="true" />
								</td>


							</tr>
							<tr>
								<td width="12%" class=td1>
									单位地址
								</td>
								<td class="td4" colspan="4">
									<html:text property="orgAddr" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>


								<html:hidden name="loanapplytbnewAF" property="coodm" />

								<td width="12%" class=td1>
									月工资额
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="30%">
									<html:text property="monthSalary" name="loanapplytbnewAF"
										styleClass="input3" />
								</td>

							</tr>
							<tr>
								<td width="12%" class=td1>
									月缴存额
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="monthPay" name="loanapplytbnewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									账户余额
								</td>
								<td class="td4" width="17%">
									<html:text property="accBlnce" name="loanapplytbnewAF"
										styleClass="input3"  />
								</td>
								<td width="12%" class=td1>
									最大可支取金额
								</td>
								<td class="td4" width="30%">
									<html:text property="zdjzqje" name="loanapplytbnewAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									初缴年月
								</td>
								<td class="td4" colspan="2">
									<html:text property="bgnDate" name="loanapplytbnewAF"
										styleClass="input3"  />
								</td>
								<td width="12%" class=td1>
									缴至年月
								</td>
								<td class="td4" width="17%">
									<html:text property="endDate" name="loanapplytbnewAF"
										styleClass="input3"  />
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
													onclick="return ischeckall();">
													<bean:message key="button.sure" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.add" />
												</html:submit>
											</td>

										</tr>
									</table>
								</td>
							</tr>
						</table>
					</html:form>
					<html:form action="/loanapplytbdownmaintianAC.do" style="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="188">
												<b class="font14">共同借款人列表</b>
											</td>
											<td width="703" height="22" align="center"
												background="<%=path%>/img/bg2.gif">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr bgcolor="1BA5FF">
								<td align="center" height="6" colspan="1"></td>
							</tr>
						</table>
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr align="center" bgcolor="C4F0FF">
								<td height="23" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td align="center" class=td2>
									职工编号
									<br>
								</td>
								<td align="center" class=td2>
									职工姓名
								</td>
								<td align="center" class=td2>
									证件号码
								</td>
								<td align="center" class=td2>
									账户状态
								</td>
								<td align="center" class=td2>
									账户余额
								</td>
								<td align="center" class=td2>
									月工资额
								</td>
								<td align="center" class=td2>
									月缴存额
								</td>
								<td align="center" class=td2>
									单位名称
								</td>
								<td align="center" class=td2>
									浏览附件
								</td>
							</tr>
							<logic:notEmpty name="loanapplytbnewAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="loanapplytbnewAF" property="list" id="tail"
									indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr id="tr<%=i%>"
										onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
										onMouseOver='this.style.background="#eaeaea"'
										onMouseOut='gotoColorpp("<%=i%>", idAF);'
										class="<%=strClass%>" onDblClick="">
										<td valign="top">
											<p align="left">
												<input id="s<%=i%>" type="radio" name="id"
													value="<bean:write name="tail" property="id"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>

										<td valign="top">
											<P>
												<bean:write name="tail" property="empid" />
											</P>
										</td>
										<td valign="top">
											<p>
												<bean:write name="tail" property="empname" />
											</p>
										</td>
										<td valign="top">
											<P>
												<bean:write name="tail" property="cardnum" />
											</P>
										</td>
										<td valign="top">
											<bean:write name="tail" property="empst" />
										</td>
										<td valign="top">
											<bean:write name="tail" property="accblnce" />
										</td>
										<td valign="top">
											<bean:write name="tail" property="monthsalary" />
										</td>
										<td valign="top">
											<bean:write name="tail" property="monthpay" />
										</td>
										<td valign="top">
											<bean:write name="tail" property="orgname" />
										</td>
										<td>
											<a
												href='javascript:excHz("<bean:write name="tail" property="photoUrl"/>");'><img
													src="<%=path%>/img/lookinfo.gif" width="37" height="24">
											</a>
										</td>
									</tr>


								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="loanapplytbnewAF" property="list">
								<tr>
									<td colspan="7" height="30" style="color:red">
										没有找到与条件相符合的结果！
									</td>
								</tr>

							</logic:empty>
						</table>

						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr class="td1">
								<td align="center">


								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>

											<td width="110">
												<html:submit property="method" styleClass="buttonc"
													onclick="return gotoEmpAccount();">
													<bean:message key="button.look.empinfo" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.update" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return confirm('是否删除该条记录?');">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
<%--											<td width="70">--%>
<%--												<html:submit property="method" styleClass="buttona">--%>
<%--													<bean:message key="button.scan" />--%>
<%--												</html:submit>--%>
<%--											</td>--%>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</html:form>
					<form action="<%=path%>/sysloan/loanapplytbshowAC.do" method="POST"
						name="Form1" id="Form1">
					</form>
				</td>
			</tr>
		</table>

	</body>

</html>

