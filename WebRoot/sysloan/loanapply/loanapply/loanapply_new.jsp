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
	String contractid = (String) request.getSession().getAttribute(
			"contractid");
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
function findEmp(){
	window.open('<%=request.getContextPath()%>/sysloan/showEmployeesListAC.do?orgid='+''+'&st='+''+'&cardnum='+''+'&empname='+''+'&indexs='+1+'&objInput=empId','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,status=yes');
}
function executeAjax() {
   	var queryString = "<%=request.getContextPath()%>/sysloan/findLoanapplyAAC.do?";
    var empid = document.loanapplynewAF.elements["borrower.empId"].value.trim();
    var orgid = document.loanapplynewAF.elements["org_Id"].value.trim();
    var office = document.loanapplynewAF.elements["borrower.office"].value.trim();
    queryString = queryString +"empid="+empid+"&orgid="+orgid+"&office="+office; 	     
   	findInfo(queryString);	     
}
function executeAjaxIn_1() {
   	var queryString = "<%=request.getContextPath()%>/sysloan/findLoanapplyAAC.do?";
    var empid = document.loanapplynewAF.elements["empId"].value.trim();
    var orgid = document.loanapplynewAF.elements["org_Id"].value.trim();
    var office = document.loanapplynewAF.elements["borrower.office"].value.trim();
    queryString = queryString +"empid="+empid+"&orgid="+orgid+"&office="+office; 	     
   	findInfo(queryString);	     
}
function display1(me){
	alert(me)
	location='loanapplyForwardAC.do';
}
function display(empid,borrowerName,sex,cardKind,cardNum,birthday,orgId,orgName,orgTel,
	orgMail,accBlnce,orgAddr,monthSalary,monthPay,empSt,bgnDate,offices,endDate,zdjzqje,isPsnalAcc){
	var b=birthday.substr(0,4);
	var d= new Date();
	var year= d.getYear();
	var age=parseFloat(year)-parseFloat(b);
	document.loanapplynewAF.elements["cardkingc"].value=cardKind;
	document.loanapplynewAF.elements["sexc"].value=sex;
	document.loanapplynewAF.elements["borrower.empId"].value=empid;
	document.loanapplynewAF.elements["borrower.borrowerName"].value=borrowerName;
	document.loanapplynewAF.elements["borrower.sex"].value=sex;
	document.loanapplynewAF.elements["borrower.age"].value=age;
	document.loanapplynewAF.elements["borrower.cardKind"].value=cardKind;
	document.loanapplynewAF.elements["borrower.cardNum"].value=cardNum;
	if(cardNum.length==15){
		var date ="19"+cardNum.substr(6,6);
		document.forms[0].elements["borrower.birthday"].value=date;
		findage();
	}else{
		var date=cardNum.substr(6,8)
		document.forms[0].elements["borrower.birthday"].value=date;
		findage();
	}
	//document.loanapplynewAF.elements["borrower.birthday"].value=birthday;
	document.loanapplynewAF.elements["borrower.orgId"].value=orgId;
	document.loanapplynewAF.elements["borrower.orgName"].value=orgName;
	document.loanapplynewAF.elements["borrower.orgTel"].value=orgTel;
	document.loanapplynewAF.elements["borrower.orgMail"].value=orgMail;
	document.loanapplynewAF.elements["borrower.accBlnce"].value=accBlnce;
	document.loanapplynewAF.elements["borrower.orgAddr"].value=orgAddr;
	document.loanapplynewAF.elements["borrower.monthSalary"].value=monthSalary;
	document.loanapplynewAF.elements["borrower.monthPay"].value=monthPay;
	document.loanapplynewAF.elements["borrower.empSt_"].value=empSt;
	document.loanapplynewAF.elements["borrower.bgnDate"].value=bgnDate;
	document.loanapplynewAF.elements["borrower.endDate"].value=endDate;
	document.loanapplynewAF.elements["zdjzqje"].value=zdjzqje;//最大可支取金额
	document.forms[0].elements["borrower.borrowerName"].readOnly="readOnly";
	document.forms[0].elements["borrower.sex"].disabled="disabled";
	document.forms[0].elements["borrower.cardKind"].disabled="disabled";
	document.forms[0].elements["borrower.cardNum"].readOnly="readOnly";
	document.forms[0].elements["borrower.birthday"].readOnly="readOnly";
	document.forms[0].elements["borrower.age"].readOnly="readOnly";
	document.forms[0].elements["borrower.monthSalary"].readOnly="readOnly";
	document.forms[0].elements["isPsnalAcc"].value=isPsnalAcc;
	if(isPsnalAcc=="0")
		document.getElementById("bankInfo").style.display="";
}


function checktextread(){
	//将职工编号和单位编号分别补0职工编号6位,单位编号10位
	var empId = document.forms[0].elements["borrower.empId"].value;
	var str;
	if(empId!="0"&&empId!=""){
		document.forms[0].elements["borrower.empId"].value = format(empId) + empId;
	}
	var orgId = document.forms[0].elements["borrower.orgId"].value;
	if(orgId!="0"){
		document.forms[0].elements["borrower.orgId"].value = formatTen(orgId)+orgId;
	}
	document.forms[0].elements["borrower.orgId"].readOnly="readOnly";
	document.forms[0].elements["borrower.accBlnce"].readOnly="readOnly";
	var loanTypes = document.forms[0].elements["borrower.loanType"].value;
	if(loanTypes=="1"){
		document.forms[0].elements["borrower.monthPay"].readOnly="";
	}else{
		document.forms[0].elements["borrower.monthPay"].readOnly="readOnly";
	}
	document.forms[0].elements["borrower.empSt_"].readOnly="readOnly";
	document.forms[0].elements["borrower.bgnDate"].readOnly="readOnly";
	document.forms[0].elements["borrower.endDate"].readOnly="readOnly";
    var type = document.loanapplynewAF.elements["type"].value.trim();
    var remaind = document.loanapplynewAF.elements["remaind"].value.trim();
	if(type==0){
		document.forms[0].elements["borrower.office"].disabled="disabled";
		document.forms[0].elements["houseType"].disabled="disabled";
		document.forms[0].elements["borrower.loanType"].disabled="disabled";
		document.forms[0].elements["borrower.fundCity"].disabled="disabled";
		var type1 = document.loanapplynewAF.elements["type1"].value.trim();
		if(type1==1){
			document.forms[0].elements["borrower.borrowerName"].readOnly="readOnly";
			document.forms[0].elements["borrower.sex"].disabled="disabled";
			document.forms[0].elements["borrower.cardKind"].disabled="disabled";
			document.forms[0].elements["borrower.cardNum"].readOnly="readOnly";
			document.forms[0].elements["borrower.birthday"].readOnly="readOnly";
			document.forms[0].elements["borrower.monthSalary"].readOnly="readOnly";
			document.forms[0].elements["borrower.age"].readOnly="readOnly";
		}
		if(type1==3){
			document.forms[0].elements["borrower.borrowerName"].readOnly="readOnly";
			document.forms[0].elements["borrower.sex"].disabled="disabled";
			document.forms[0].elements["borrower.cardKind"].disabled="disabled";
			document.forms[0].elements["borrower.cardNum"].readOnly="readOnly";
			document.forms[0].elements["borrower.birthday"].readOnly="readOnly";
			document.forms[0].elements["borrower.monthSalary"].readOnly="readOnly";
			document.forms[0].elements["borrower.age"].readOnly="readOnly";
		}
	}
	if(remaind=='a'){
		document.forms[0].elements["borrower.office"].disabled="disabled";
		document.forms[0].elements["houseType"].disabled="disabled";
		document.forms[0].elements["borrower.loanType"].disabled="disabled";
		var type1 = document.loanapplynewAF.elements["type1"].value.trim();
		
		if(type1==1){
			document.forms[0].elements["borrower.borrowerName"].readOnly="readOnly";
			document.forms[0].elements["borrower.sex"].disabled="disabled";
			document.forms[0].elements["borrower.cardKind"].disabled="disabled";
			document.forms[0].elements["borrower.cardNum"].readOnly="readOnly";
			document.forms[0].elements["borrower.birthday"].readOnly="readOnly";
			document.forms[0].elements["borrower.monthSalary"].readOnly="readOnly";
			document.forms[0].elements["borrower.age"].readOnly="readOnly";
		}
		
		if(type1==3){
			document.forms[0].elements["borrower.borrowerName"].readOnly="readOnly";
			document.forms[0].elements["borrower.sex"].disabled="disabled";
			document.forms[0].elements["borrower.cardKind"].disabled="disabled";
			document.forms[0].elements["borrower.cardNum"].readOnly="readOnly";
			document.forms[0].elements["borrower.birthday"].readOnly="readOnly";
			document.forms[0].elements["borrower.monthSalary"].readOnly="readOnly";
			document.forms[0].elements["borrower.age"].readOnly="readOnly";
		}
		if(type1==4){
			 //document.forms[0].elements["borrower.borrowerName"].readOnly="readOnly";
			 //document.forms[0].elements["borrower.cardNum"].readOnly="readOnly";
		}
	}
	
}
//身份证焦点离开时算出年龄及出生日期
function displayBirAge(){
	var cardNum = document.loanapplynewAF.elements["borrower.cardNum"].value.trim();
	var date;
	if(cardNum!=""){
		if(cardNum.length==15){
			date = "19"+cardNum.substr(6,6);
		}else if(cardNum.length==18){
			date = cardNum.substr(6,8);
		}
		document.loanapplynewAF.elements["borrower.birthday"].value = date;
		var d= new Date();
		var year = d.getYear();
		var age = parseFloat(year)-parseFloat(date.substr(0,4));
		document.loanapplynewAF.elements["borrower.age"].value = age;
	}
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
function checkMonthSix(month)
{
  var tempMonth;
  eval("tempMonth=document.loanapplynewAF.elements.["+month+"]")
  var strMonth = tempMonth.value.trim();
  if(strMonth.length!=6)
  {
    alert("请输入六位的年月，格式为200410！");
    tempMonth.value="";
    tempMonth.focus();
    return false;
  }
  if(strMonth.substring(0,4)<1900){
    alert("年份应该大于1900！");
    tempMonth.value="";
    tempMonth.focus();
    return false;
  }
  if(strMonth.substring(4,6)>12 || strMonth.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    tempMonth.value="";
    tempMonth.focus();
    return false;
  }

  return true;
}
function ischekcard(){
	var contractid=<%=contractid%>;
	var empid = document.loanapplynewAF.elements["borrower.empId"].value.trim();
	if(empid==""){
		document.loanapplynewAF.elements["borrower.empId"].value=0;
	}
	var borrowerName = document.loanapplynewAF.elements["borrower.borrowerName"].value.trim();
	var sexc = document.loanapplynewAF.elements["sexc"].value.trim();
	var cardkingc = document.loanapplynewAF.elements["cardkingc"].value.trim();
	var cardNum = document.loanapplynewAF.elements["borrower.cardNum"].value.trim();
	var birthday = document.loanapplynewAF.elements["borrower.birthday"].value.trim();
	var monthSalary = document.loanapplynewAF.elements["borrower.monthSalary"].value.trim();
	var age = document.loanapplynewAF.elements["borrower.age"].value.trim();
	var office = document.loanapplynewAF.elements["borrower.office"].value.trim();
	var houseType = document.loanapplynewAF.elements["houseType"].value.trim();
	//异地贷款要进行判断的数据
	var loanType=document.forms[0].elements["borrower.loanType"].value;
	if(loanType==1){
		var monthPay = document.loanapplynewAF.elements["borrower.monthPay"].value.trim();
		var fundCity = document.loanapplynewAF.elements["borrower.fundCity"].value.trim();
		var accBlnce = document.loanapplynewAF.elements["borrower.accBlnce"].value.trim();
		if(fundCity==""){
			alert("请输入公积金城市");
			return false;
		}
    }
	
	if(houseType==""){
		alert("请选择房屋类型");
		document.loanapplynewAF.elements["houseType"].focus();
		return false;
	}
	if(borrowerName==""){
		alert("请输入借款人姓名");
		return false;
	}	
	if(sexc==""){
		alert("请输入性别");
		return false;
	}
	if(cardkingc==""){
		alert("请输入证件类型");
		return false;
	}
	if(cardNum==""){
		alert("请输入证件号码");
		return false;
	}
	
	var isPsnalAcc = document.loanapplynewAF.elements["isPsnalAcc"].value.trim();
	if(isPsnalAcc=="0"){
		var payBank = document.loanapplynewAF.elements["payBank"].value.trim();
		var payBankAcc = document.loanapplynewAF.elements["payBankAcc"].value.trim();
		if(payBank==""){
			alert("请输入银行");
			document.loanapplynewAF.elements["payBank"].focus();
			return false;
		}
		if(payBankAcc==""){
			alert("请输入银行账号");
			document.loanapplynewAF.elements["payBankAcc"].focus();
			return false;
		}
	}
}

function findage(){
	var birthday = document.loanapplynewAF.elements["borrower.birthday"].value.trim();
	if(birthday==""){
		alert("请输入正确格式的出生年月,例如：20070101");
		document.loanapplynewAF.elements["borrower.birthday"].value="";
		return false;	
	}
	if(!isNumber(birthday)){
		alert("请输入正确格式的出生年月,例如：20070101");
		document.loanapplynewAF.elements["borrower.birthday"].value="";
		return false;	
	}
	var b=birthday.substr(0,4);
	var d= new Date();
	var year= d.getYear();
	var age=parseFloat(year)-parseFloat(b);
	document.loanapplynewAF.elements["borrower.age"].value=age;

}
var s1="";
var s2="";
var s3="";
function reportsErrors(){
	document.forms[0].elements["borrower.fundCity"].readOnly="true";
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>	
	<logic:equal name="loanapplynewAF" property="isNeedDel" value="1">
 	 	del();
 	</logic:equal>
	
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="确定"){
				s1=i;
			}
			if(document.all.item(i).value=="查看职工明细账"){
				s2=i;
			}
			if(document.all.item(i).value=="扫描基本信息"){
				s3=i;
			}
		}
	} 
	var borroweName=document.loanapplynewAF.elements["borrower.borrowerName"].value.trim();
	if(borroweName==""){
		document.all.item(s3).disabled="true";
	}
	
	var type = document.loanapplynewAF.elements["type"].value.trim();
	if(type==15){
		document.all.item(s1).disabled="true";
	}
 	var empid = document.loanapplynewAF.elements["borrower.empId"].value.trim();
 	if(empid=="0"||empid==""){
		document.all.item(s2).disabled="true";
 	}
 	var isPsnalAcc = document.loanapplynewAF.elements["isPsnalAcc"].value.trim();
 	if(isPsnalAcc=="0")
		document.getElementById("bankInfo").style.display="";
 	addempid();
}   
function changeSex(){
	var sex = document.loanapplynewAF.elements["borrower.sex"].value.trim();
	document.loanapplynewAF.elements["sexc"].value=sex;
}
function changecardkingc(){
	var cardKind = document.loanapplynewAF.elements["borrower.cardKind"].value.trim();
	document.loanapplynewAF.elements["cardkingc"].value=cardKind;
}
// 根据身份证计算出时间与性别
function  checkCardNum(){
	var num=document.forms[0].elements["borrower.cardNum"].value.trim();
	var numType=document.forms[0].elements["cardkingc"].value;
	if(numType==0){
		if(num==null||num==""){
			return true;
		}else if(num!=null&&!isIdCardNo(num)){
			document.forms[0].elements["borrower.cardNum"].focus();
		}else{
			if(num.length==15){
				var date ="19"+num.substr(6,6);
				document.forms[0].elements["borrower.birthday"].value=date;
				findage();
			}else{
				var date=num.substr(6,8)
				document.forms[0].elements["borrower.birthday"].value=date;
				findage();
			}
			return true;
		}
     }
}
function gotoEmpAccount(){
	var cardNum = document.forms[0].elements["borrower.cardNum"].value.trim();
	var borrowerName = document.forms[0].elements["borrower.borrowerName"].value.trim();
	borrowerName =encodeURI(borrowerName);
	window.open('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'newwindow','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=no,location=no,status=no');
	return false;
}
function addempid(){
	var empId = document.forms[0].elements["borrower.empId"].value.trim();
	if(empId==""){
		document.forms[0].elements["borrower.empId"].value='0';
	}
}
function changloantype(){
	var loanType=document.forms[0].elements["borrower.loanType"].value;
	if(loanType==1){
		document.forms[0].elements["borrower.monthPay"].readOnly="";
		document.forms[0].elements["borrower.accBlnce"].readOnly="";
		document.forms[0].elements["borrower.bgnDate"].readOnly="";
		document.forms[0].elements["borrower.endDate"].readOnly="";
		document.forms[0].elements["borrower.fundCity"].readOnly="";
	}
	if(loanType==0){
		document.forms[0].elements["borrower.monthPay"].readOnly="true";
		document.forms[0].elements["borrower.accBlnce"].readOnly="true";
		document.forms[0].elements["borrower.bgnDate"].readOnly="true";
		document.forms[0].elements["borrower.endDate"].readOnly="true";
		document.forms[0].elements["borrower.fundCity"].readOnly="true";
	}
}
</script>
	<body bgcolor="#FFFFFF" text="#000000"
		onload="checktextread();reportsErrors();">
		<jsp:include page="/syscommon/picture/progressbar.jsp" />
		<html:form action="/loanapplyTaMainTianAC.do">
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
								<td background="<%=path%>/img/table_bg_line.gif">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="112" height="37"
												background="<%=path%>/img/buttonbl.gif" align="center"
												valign="top" style="PADDING-top: 7px">
												<a href="<%=path%>/sysloan/showLoanapplyAC.do" class=a2>借款人信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
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
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="12%" class=td1>
									所属办事处
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td width="28%" class="td4">
									<html:select name="loanapplynewAF" property="borrower.office"
										styleClass="input4">

										<html:options collection="officeList" property="value"
											labelProperty="label" />
									</html:select>
								</td>

								<td width="12%" class=td1>
									房屋类型
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td width="28%" class="td4">
									<html:select name="loanapplynewAF" property="houseType"
										styleClass="input4">
										<html:option value=""></html:option>
										<html:optionsCollection property="houseTypeMap"
											name="loanapplynewAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									贷款类型
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td width="28%" class="td4">
									<html:select name="loanapplynewAF" property="borrower.loanType"
										styleClass="input4" onchange="changloantype()">
										<html:optionsCollection property="loanWhereTypeMap"
											name="loanapplynewAF" label="value" value="key" />
									</html:select>
								</td>

								<td width="12%" class=td1>
									公积金城市
								</td>
								<td width="28%">
									<html:text property="borrower.fundCity" name="loanapplynewAF"
										styleClass="input3" maxlength="8"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="176">
												<b class="font14">借款人基本信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="734">
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
								<html:hidden name="loanapplynewAF" property="remaind" />
								<html:hidden name="loanapplynewAF" property="type1" />
								<html:hidden name="loanapplynewAF" property="type" />
								<html:hidden name="loanapplynewAF" property="org_Id" />
								<html:hidden name="loanapplynewAF" property="ofic" />
								<html:hidden name="loanapplynewAF" property="sexc" />
								<html:hidden name="loanapplynewAF" property="cardkingc" />
								<html:hidden name="loanapplynewAF"
									property="borrower.contractId" />
								<html:hidden name="loanapplynewAF" property="empId" />
								<html:hidden name="loanapplynewAF" property="isPsnalAcc" />
								<td class=td1 width="12%">
									职工编号
								</td>
								<td class="td4" width="8%">
									<html:text property="borrower.empId" name="loanapplynewAF"
										styleClass="input3" ondblclick="executeAjax();"
										readonly="true" />
								</td>
								<logic:equal value="15" name="loanapplynewAF" property="type">
									<td class="td4">
										<input type="button" class="buttona" value="..." name="button"
											onClick="findEmp();">
									</td>
								</logic:equal>

								<logic:equal value="0" name="loanapplynewAF" property="type">
									<td class="td4">
										<input type="button" class="buttona" value="..." name="button"
											onClick="findEmp();" disabled>
									</td>
								</logic:equal>
								<logic:equal value="1" name="loanapplynewAF" property="type">
									<td class="td4">
										<input type="button" class="buttona" value="..." name="button"
											onClick="findEmp();">
									</td>
								</logic:equal>

								<td class="td1" width="12%">
									借款人姓名
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.borrowerName"
										name="loanapplynewAF" styleClass="input3" maxlength="8"
										onkeydown="enterNextFocus1();" />
								</td>
								<td class=td1 width="12%">
									性别
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" width="30%">
									<html:select name="loanapplynewAF" property="borrower.sex"
										styleClass="input4" onchange="changeSex()"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="sexMap"
											name="loanapplynewAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									证件类型
								</td>
								<td class="td4" colspan="2">
									<html:select name="loanapplynewAF" property="borrower.cardKind"
										styleClass="input4" onchange="changecardkingc()"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="cardkingMap"
											name="loanapplynewAF" label="value" value="key" />
									</html:select>
								</td>
								<td width="12%" class=td1>
									证件号码
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.cardNum" name="loanapplynewAF"
										maxlength="18" styleClass="input3" onblur="displayBirAge()"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									出生日期
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.birthday" name="loanapplynewAF"
										styleClass="input3" maxlength="8" onblur="findage()"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									年龄
									<font color="#ff0000"><span class="STYLE1">*</span>
									</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.age" name="loanapplynewAF"
										styleClass="input3" maxlength="3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									职务
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.business" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									职称
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.title" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									民族
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.nation" name="loanapplynewAF"
										styleClass="input3" maxlength="8"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									户籍所在地
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.nativePlace"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									婚姻状况
								</td>
								<td class="td4" width="30%">
									<html:select name="loanapplynewAF"
										property="borrower.marriageSt" styleClass="input4"
										onkeydown="enterNextFocus1();">

										<html:option value="已婚"></html:option>
										<html:option value="未婚"></html:option>
										<html:option value="离婚"></html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									文化程度
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.degree" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									家庭住址
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.homeAddr" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									邮政编码
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.homeMail" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									住宅电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.houseTel" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									移动电话
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.homeMobile" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									备注
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.remark" name="loanapplynewAF"
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
											<td height="22" bgcolor="#CCCCCC" align="center" width="175">
												<b class="font14">借款人公积金归集信息</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="735">
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
								<td width="12%" class=td1>
									单位编号
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.orgId" name="loanapplynewAF"
										styleClass="input3" readonly="true"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									单位名称
								</td>
								<td class="td4" width="17%" colspan="3">
									<html:text property="borrower.orgName" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>

							</tr>
							<tr>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.orgTel" name="loanapplynewAF"
										styleClass="input3" maxlength="20"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									邮政编码
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.orgMail" name="loanapplynewAF"
										styleClass="input3" maxlength="8"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									账户状态
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.empSt_" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>



							</tr>
							<tr>
								<td width="12%" class=td1>
									单位地址
								</td>
								<td class="td4" colspan="4">
									<html:text property="borrower.orgAddr" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									月工资额
									<font color="#ff0000">*</font>
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.monthSalary"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>


							</tr>
							<tr>
								<td width="12%" class=td1>
									月缴存额
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.monthPay" name="loanapplynewAF"
										styleClass="input3" maxlength="20"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									账户余额
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.accBlnce" name="loanapplynewAF"
										styleClass="input3" maxlength="20"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									最大可支取金额
								</td>
								<td class="td4" width="30%">
									<html:text property="zdjzqje" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									初缴年月
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.bgnDate" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									缴至年月
								</td>
								<td class="td4">
									<html:text property="borrower.endDate" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr id="bankInfo" style="display:none">
								<td width="12%" class=td1>
									银行名称
									<font color="red">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="payBank" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									银行账号
									<font color="red">*</font>
								</td>
								<td class="td4">
									<html:text property="payBankAcc" name="loanapplynewAF"
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
											<td height="22" bgcolor="#CCCCCC" align="center" width="174">
												<strong class="font14">其他联系人信息</strong>
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
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="12%" class=td1>
									姓名
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.contactAName"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									与借款人关系
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.relationA" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									工作单位
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.contactAOrgName"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.contactAOrgTel"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									住宅电话
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.contactAHouseTel"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									移动电话
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.contactAMobile"
										name="loanapplynewAF" styleClass="input3" maxlength="11"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
						</table>
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="12%" class=td1>
									姓名
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.contactBName"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									与借款人关系
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.relationB" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									工作单位
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.contactBOrgName"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.contactBOrgTel"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									住宅电话
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.contactBHouseTel"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									移动电话
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.contactBMobile"
										name="loanapplynewAF" styleClass="input3" maxlength="11"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
						</table>
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="12%" class=td1>
									姓名
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.contactCName"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									与借款人关系
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.relationC" name="loanapplynewAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									工作单位
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.contactCOrgName"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="borrower.contactCOrgTel"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									住宅电话
								</td>
								<td class="td4" width="17%">
									<html:text property="borrower.contactCHouseTel"
										name="loanapplynewAF" styleClass="input3"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									移动电话
								</td>
								<td class="td4" width="30%">
									<html:text property="borrower.contactCMobile"
										name="loanapplynewAF" styleClass="input3" maxlength="11"
										onkeydown="enterNextFocus1();" />
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
												<html:submit property="method" styleClass="buttona"
													onclick="return ischekcard();return checkCardNum();">
													<bean:message key="button.sure" />
												</html:submit>
											</td>
											<td width="110">
												<html:submit property="method" styleClass="buttonc">
													<bean:message key="button.scan.baseinfo" />
												</html:submit>
											</td>
											<%--										<td width="70">--%>
											<%--												<html:submit property="method" styleClass="buttona" >--%>
											<%--													<bean:message key="button.scan" />--%>
											<%--												</html:submit>--%>
											<%--											</td>--%>
											<td width="33%" colspan="2">
												<a
													href='javascript:excHz("<bean:write name="loanapplynewAF" property="borrower.photoUrl"/>");'>浏览证书</a>
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

</html>

