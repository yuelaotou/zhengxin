<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.orgstrct.dto.SecurityInfo"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	SecurityInfo security = (SecurityInfo) request.getSession()
			.getAttribute("SecurityInfo");
	String bizDate = security.getUserInfo().getBizDate()
			.substring(0, 6);
	String thisyear = bizDate.substring(0, 4);
%>
<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>

		<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
	</head>
	<script language="javascript">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}

function findEmp(){
	window.open('<%=path%>/sysloan/showEmployeesListAC.do?orgid='+''+'&st='+''+'&cardnum='+''+'&empname='+''+'&indexs='+0+'&objInput=empId','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,status=yes');
}
function findEmpa(){
	window.open('<%=path%>/sysloan/showEmployeesListAC.do?orgid='+''+'&st='+''+'&cardnum='+''+'&empname='+''+'&indexs='+12+'&objInput=spouseId','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,status=yes');
}

function executeAjax() {
   	var queryString = "<%=path%>/sysloan/beforeLoanApplyAAC.do?";
   	var empid = document.all.empId.value.trim();
    var orgid = document.all.org_Id.value.trim();
    document.all.emporg_Id.value=orgid;
    queryString = queryString +"empid="+empid+"&orgid="+orgid+"&type=1"; 	     
   	findInfo(queryString);
}
function executeAjaxIn() {
   	var queryString = "<%=path%>/sysloan/beforeLoanApplyAAC.do?";
    var spouseid = document.all.spouseId.value.trim();
    var orgid = document.all.org_Id.value.trim();
    document.all.spuorg_Id.value=orgid;
    queryString = queryString +"empid="+spouseid+"&orgid="+orgid+"&type=2"; 	     
   	findInfo(queryString);
}
function display(empId,empName,sex,cardKind,cardNum,birthday,age,salary,monthPay,balance,status,continus,balanceUse,matter,orgid,orgname,type){
	if(type=='1'){
		document.all.empId.value=format(empId)+empId;
		document.all.empname.value=empName;
		document.all.empSex.value=sex;
		document.all.empCardkind.value=cardKind;
		document.all.empCardnum.value=cardNum;
		document.all.empBirthDay.value=birthday;
		document.all.empAge.value=age;
		document.all.empSalaryBase.value=salary;
		document.all.empMonthPay.value=monthPay;
		document.all.empBalance.value=balance;
		document.all.empStatus.value=status;
		document.all.empContinus.value=continus;
		document.all.empBalanceUse.value=balanceUse;
		document.all.orgid.value=orgid;
		document.all.orgname.value=orgname;
		if(matter!=""){
			alert(matter);
		}
	}
	if(type=='2'){
		document.all.spouseId.value=format(empId)+empId;
		document.all.spouseName.value=empName;
		document.all.spouseSex.value=sex;
		document.all.spouseCardkind.value=cardKind;
		document.all.spouseCardnum.value=cardNum;
		document.all.spouseBirthDay.value=birthday;
		document.all.spouseAge.value=age;
		document.all.spouseSalaryBase.value=salary;
		document.all.spouseMonthPay.value=monthPay;
		document.all.spouseBalance.value=balance;
		document.all.spouseStatus.value=status;
		document.all.spouseContinus.value=continus;
		document.all.spouseBalanceUse.value=balanceUse;
		document.all.spouseorgid.value=orgid;
		document.all.spouseorgname.value=orgname;
		if(matter!=""){
			alert(matter);
		}
	}
}
function cacluate(){
	for(i=0;i<document.all.houseType.length;i++){
		if(document.all.houseType[i].checked){
			var houseType=document.all.houseType[i].value;
		}
	}
	var empAge=document.all.empAge.value;
	var empSex=document.all.empSex.value;
	empSex =encodeURI(empSex);
	var spouseSex=document.all.spouseSex.value;
	spouseSex =encodeURI(spouseSex);
	var spouseId=document.all.spouseId.value;//辅助借款人编号
	var sHouseYear=document.all.sHouseYear.value;//二手房房龄
	var houseTotalPrice=document.all.houseTotalPrice.value;//房屋总价
	var loanYear=document.all.loanYear.value;//贷款年限
	var empSalaryBase=document.all.empSalaryBase.value;//借款人工资基数
	var empBalance=document.all.empBalance.value;//配偶公积金余额
	var empMonthPay=document.all.empMonthPay.value;//配偶月缴存额
	var spouseSalaryBase=document.all.spouseSalaryBase.value;//辅助借款工资基数
	var spouseBalance=document.all.spouseBalance.value;//配偶公积金余额
	var spouseMonthPay=document.all.spouseMonthPay.value;//配偶月缴存额
	var spouseAge=document.all.spouseAge.value;//配偶当前年龄
	var empBalanceUse=document.all.empBalanceUse.value;
	var spouseBalanceUse=document.all.spouseBalanceUse.value;
	var queryString = "<%=path%>/sysloan/beforeLoanApplyAAAC.do?";
	queryString = queryString +"houseType="+houseType+"&empAge="+empAge+"&empSex="+empSex+"&spouseId="+spouseId; 
	queryString = queryString +"&sHouseYear="+sHouseYear+"&houseTotalPrice="+houseTotalPrice+"&loanYear="+loanYear+"&empBalance="+empBalance; 
	queryString = queryString +"&empSalaryBase="+empSalaryBase+"&spouseSalaryBase="+spouseSalaryBase+"&spouseBalance="+spouseBalance; 
	queryString = queryString +"&spouseMonthPay="+spouseMonthPay+"&spouseAge="+spouseAge+"&spouseSex="+spouseSex+"&empMonthPay="+empMonthPay; 
	queryString = queryString +"&empBalanceUse="+empBalanceUse+"&spouseBalanceUse="+spouseBalanceUse; 
	findInfo(queryString);
}
function sun_display(maxYear,maxMoney,fangKuan,gongZiEdu,tuiXiuEdu,fangKuana,gongZiEdua,tuiXiuEdua,maxyear_yg,empYue,spouseYue,monthRate,yearRate,minMoney,corpusInterest){
	document.all.maxGongZiEdu.value=gongZiEdua+"="+gongZiEdu;
	document.all.maxTuiXiuEdu.value=tuiXiuEdua+"="+tuiXiuEdu;
	document.all.maxFangKuan.value=fangKuana+"="+fangKuan;
	document.all.maxLoanMoney.value=maxMoney;
	document.all.maxLoanYear.value=maxYear;
	document.all.loanMonthRate.value=monthRate;
	document.all.loanYearRate.value=yearRate;
	document.all.uMaxLoanMoney.value=minMoney;
	document.all.monthBackMoney.value=corpusInterest;
	document.all.uMaxLoanYear.value=maxyear_yg;
	document.all.empMonthUse.value=empYue;
	document.all.spouseMonthUse.value=spouseYue;
	document.all.testme.disabled="";
}
function (){
	var uMaxLoanYear=document.all.uMaxLoanYear.value;//贷款年限
	var uMaxLoanMoney=document.all.uMaxLoanMoney.value;//您的公积金可用额度
	var empBalanceUse=document.all.empBalanceUse.value;
	var spouseBalanceUse=document.all.spouseBalanceUse.value;
	var queryString = "<%=path%>/sysloan/beforeLoanApplyAAAAC.do?";
	queryString = queryString +"uMaxLoanYear="+uMaxLoanYear+"&uMaxLoanMoney="+uMaxLoanMoney+"&empBalanceUse="+empBalanceUse+"&spouseBalanceUse="+spouseBalanceUse;
	findInfo(queryString);
}
function sun_display1(empYue,spouseYue,maxYear,monthRate,minMoney,corpusInterest,yearRate){
	document.all.loanMonthRate.value=monthRate;
	document.all.uMaxLoanMoney.value=minMoney;
	document.all.monthBackMoney.value=corpusInterest;
	document.all.uMaxLoanYear.value=maxYear;
	document.all.empMonthUse.value=empYue;
	document.all.spouseMonthUse.value=spouseYue;
	document.all.loanYearRate.value=yearRate;
	document.all.testme.disabled="";
}
function redirect(){
	var loanMonthRate=document.all.loanMonthRate.value;
	var uMaxLoanMoney=document.all.uMaxLoanMoney.value;
	var monthBackMoney=document.all.monthBackMoney.value;
	var uMaxLoanYear=document.all.uMaxLoanYear.value;
	document.URL="<%=path%>/sysloan/beforeLoanApplyShowAC.do?loanMonthRate="+loanMonthRate+"&uMaxLoanMoney="+uMaxLoanMoney
	+"&monthBackMoney="+monthBackMoney+"&uMaxLoanYear="+uMaxLoanYear+"&sun=a";
}
function checkspousebirth(){
	var year = "<%=thisyear%>";
	var spouseBirthDay=document.all.spouseBirthDay;
	if(checkDate(spouseBirthDay)){
		if(!isNaN(spouseBirthDay.value)){
			var spouseyear=spouseBirthDay.value.substring(0,4);
			var age=eval(year)-eval(spouseyear);
			document.all.spouseAge.value=age;
		}else{
			alert("生日格式不对!");
			document.all.spouseBirthDay.focus();
			return false;
		}
	}
}
function checkempbirth(){
	var year = "<%=thisyear%>";
	var empBirthDay=document.all.empBirthDay;
	if(checkDate(empBirthDay)){
		if(!isNaN(empBirthDay.value)){
			var empyear=empBirthDay.value.substring(0,4);
			var age=eval(year)-eval(empyear);
			document.all.empAge.value=age;
		}else{
			alert("生日格式不对!");
			document.all.empBirthDay.focus();
			return false;
		}
	}
}
function skip(){
		var orgId=document.all.emporg_Id.value.trim();
		var empId =document.all.empId.value.trim();
		if(orgId!=""&&empId!=""){
			window.open('<%=path%>/syscollection/querystatistics/operationflow/empoperationflow/empOperationFlowTaForwardURLAC.do?empId='+empId+'&orgId='+orgId,'','width=800,height=550,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',resizable,scrollbars=yes');
		}
}
function skip_assist(){
		var orgId=document.all.spuorg_Id.value.trim();
		var empId =document.all.spouseId.value.trim();
		if(orgId!=""&&empId!=""){
			window.open('<%=path%>/syscollection/querystatistics/operationflow/empoperationflow/empOperationFlowTaForwardURLAC.do?empId='+empId+'&orgId='+orgId,'','width=800,height=550,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',resizable,scrollbars=yes');
		}
}
function cacluatea(){
	var uMaxLoanYear=document.all.uMaxLoanYear.value;//贷款年限
	var uMaxLoanMoney=document.all.uMaxLoanMoney.value;//您的公积金可用额度
	var empBalanceUse=document.all.empBalanceUse.value;
	var spouseBalanceUse=document.all.spouseBalanceUse.value;
	var queryString = "<%=path%>/sysloan/beforeLoanApplyAAAAC.do?";
	queryString = queryString +"uMaxLoanYear="+uMaxLoanYear+"&uMaxLoanMoney="+uMaxLoanMoney+"&empBalanceUse="+empBalanceUse+"&spouseBalanceUse="+spouseBalanceUse;
	findInfo(queryString);
}
</script>

	<body bgcolor="#FFFFFF" text="#000000">
		<form method="post">
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
									&nbsp;
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
												<a href="#" class=a1>申请贷款</a><font color="00B5DB">&gt;</font><a
													href="#" class=a1>贷前咨询</a>
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
								<td class=td1 width="12%">
									职工编号
								</td>
								<td class="td4" width="12%">
									<input type="hidden" name="org_Id">
									<input type="hidden" name="emporg_Id">
									<input type="hidden" name="spuorg_Id">
									<input name="empId" type="text" class="input3"
										onkeydown="gotoEnter();" ondblclick="executeAjax()"
										readonly="readonly">
								</td>
								<td class="td4">
									<input type="button" name="Submit4" value="……" class="buttona"
										onclick="findEmp();">
								</td>
								<td class="td1" width="12%">
									职工姓名
								</td>
								<td class="td4" width="20%">
									<input name="empname" type="text" class="input3"
										readonly="readonly">
								</td>
								<td class=td1 width="12%">
									性别
								</td>
								<td class="td4" width="20%">
									<input name="empSex" type="text" class="input3"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td class=td1 width="12%">
									单位编号
								</td>
								<td class="td4" width="12%" colspan="2">
									<input name="orgid" type="text" class="input3"
										readonly="readonly">
								</td>
								<td class="td1" width="12%">
									单位名称
								</td>
								<td class="td4" width="20%">
									<input name="orgname" type="text" class="input3"
										readonly="readonly">
								</td>
								<td class=td1 width="12%">
									&nbsp;
								</td>
								<td class="td4" width="20%">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									证件类型
								</td>
								<td class="td4" colspan="2">
									<input name="empCardkind" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									证件号码
								</td>
								<td class="td4" width="20%">
									<input name="empCardnum" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									出生日期
								</td>
								<td class="td4" width="20%">
									<input name="empBirthDay" type="text" class="input3"
										onblur="checkempbirth();">
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									年龄
								</td>
								<td class="td4" colspan="2">
									<input name="empAge" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									工资基数
								</td>
								<td class="td4" width="20%">
									<input name="empSalaryBase" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									月缴存额
								</td>
								<td class="td4" width="20%">
									<input name="empMonthPay" type="text" class="input3"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									账户余额
								</td>
								<td class="td4" colspan="2">
									<input name="empBalance" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									账户状态
								</td>
								<td class="td4" width="20%">
									<input name="empStatus" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									连续缴存月数
								</td>
								<td class="td4" width="20%">
									<input name="empContinus" type="text" class="input3"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									可用还贷余额
								</td>
								<td class="td4" colspan="2">
									<input name="empBalanceUse" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									余额可以还款月数
								</td>
								<td class="td4" width="20%">
									<input name="empMonthUse" type="text" class="input3"
										readonly="readonly">
								</td>
								<td colspan="2" align="right">
									<input type="button" name="Submit2" value="查看职工明细账"
										class="buttonc" onclick="return skip();">
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
												<b class="font14">配偶基本信息</b>
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
								<td class=td1 width="12%">
									职工编号
								</td>
								<td class="td4" width="12%">
									<input name="spouseId" type="text" class="input3"
										readonly="readonly">
								</td>
								<td class="td4">
									<input type="button" name="Submit4" value="……" class="buttona"
										onclick="findEmpa();">
								</td>
								<td class="td1" width="12%">
									职工姓名
								</td>
								<td class="td4" width="20%">
									<input name="spouseName" type="text" class="input3"
										readonly="readonly">
								</td>
								<td class=td1 width="12%">
									性别
								</td>
								<td class="td4" width="20%">
									<input name="spouseSex" type="text" class="input3"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td class=td1 width="12%">
									单位编号
								</td>
								<td class="td4" width="12%" colspan="2">
									<input name="spouseorgid" type="text" class="input3"
										readonly="readonly">
								</td>
								<td class="td1" width="12%">
									单位名称
								</td>
								<td class="td4" width="20%">
									<input name="spouseorgname" type="text" class="input3"
										readonly="readonly">
								</td>
								<td class=td1 width="12%">
									&nbsp;
								</td>
								<td class="td4" width="20%">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									证件类型
								</td>
								<td class="td4" colspan="2">
									<input name="spouseCardkind" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									证件号码
								</td>
								<td class="td4" width="20%">
									<input name="spouseCardnum" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									出生日期
								</td>
								<td class="td4" width="20%">
									<input name="spouseBirthDay" type="text" class="input3"
										onblur="checkspousebirth();">
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									年龄
								</td>
								<td class="td4" colspan="2">
									<input name="spouseAge" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									工资基数
								</td>
								<td class="td4" width="20%">
									<input name="spouseSalaryBase" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									月缴存额
								</td>
								<td class="td4" width="20%">
									<input name="spouseMonthPay" type="text" class="input3"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									账户余额
								</td>
								<td class="td4" colspan="2">
									<input name="spouseBalance" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									账户状态
								</td>
								<td class="td4" width="20%">
									<input name="spouseStatus" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									连续缴存月数
								</td>
								<td class="td4" width="20%">
									<input name="spouseContinus" type="text" class="input3"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									可用还贷余额
								</td>
								<td class="td4" colspan="2">
									<input name="spouseBalanceUse" type="text" class="input3"
										readonly="readonly">
								</td>
								<td width="12%" class=td1>
									余额可以还款月数
								</td>
								<td class="td4" width="17%">
									<input name="spouseMonthUse" type="text" class="input3"
										readonly="readonly">
								</td>
								<td colspan="2" align="right">
									<input type="button" name="Submit2" value="查看职工明细账"
										class="buttonc" onclick="return skip_assist();">
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
												<strong class="font14">选择条件</strong>
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
									房屋类型
								</td>
								<td class="td4" colspan="2">
									<input type="radio" name="houseType" value="0"
										checked="checked">
									商品房
									<input type="radio" name="houseType" value="1">
									二手房
								</td>
								<td width="12%" class=td1>
									二手房房龄(年)
								</td>
								<td class="td4" width="12%">
									<input name="sHouseYear" type="text" class="input3"
										onkeydown="enterNextFocus1();">
								</td>
								<td width="12%" class=td1>
									房屋总价(元)
								</td>
								<td class="td4" width="12%">
									<input name="houseTotalPrice" type="text" class="input3"
										onkeydown="enterNextFocus1();">
								</td>
								<td class="td1" width="12%">
									贷款年限(年)
								</td>
								<td class="td4" width="12%">
									<input name="loanYear" type="text" class="input3"
										onkeydown="enterNextFocus1();">
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
												<input type="button" name="Submit" value="计算"
													class="buttona" onclick="cacluate();">
											</td>
										</tr>
									</table>
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
												<strong class="font14">计算公式</strong>
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
								<td width="17%" class=td1>
									按照还款能力（工资）确定的贷款限额(元)
								</td>
								<td class="td4" colspan="2" width="83%">
									<input name="maxGongZiEdu" type="text" class="input3"
										value="（借款人工资基数+辅助借款工资基数）×借款人月收入还款比例%×12个月×贷款年限"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									按借款家庭成员退休年龄内所交纳的住房公积金计算的贷款额度(元)
								</td>
								<td class="td4" colspan="2">
									<input name="maxTuiXiuEdu" type="text" class="input3"
										value="(（借款人公积金余额＋借款人月缴存额×（借款人退休年龄－借款人当前年龄)*12）＋（配偶公积金余额＋配偶月缴存额×(配偶退休年龄－配偶当前年龄)*12）)*n倍"
										readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									住房公积金贷款最高限额(元)
								</td>
								<td class="td4" colspan="2">
									<input name="maxFangKuan" type="text" class="input3"
										value="房款*%" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									住房公积金最高限额(元)
								</td>
								<td class="td4" colspan="2">
									<input name="maxLoanMoney" type="text" class="input3"
										onkeydown="enterNextFocus1();" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									最高年限为(年)
								</td>
								<td class="td4" colspan="2">
									<input name="maxLoanYear" type="text" class="input3"
										onkeydown="enterNextFocus1();" readonly="readonly">
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
												<strong class="font14">结果</strong>
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
								<td width="17%" class=td1>
									您的公积金月利率为
								</td>
								<td width="33%" class="td4">
									<input name="loanMonthRate" type="text" class="input3"
										onkeydown="enterNextFocus1();" readonly="readonly">
								</td>
								<td width="17%" class=td1>
									您的公积金年利率为
								</td>
								<td width="33%" class="td4">
									<input name="loanYearRate" type="text" class="input3"
										onkeydown="enterNextFocus1();" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									你的月还本息为(元)
								</td>
								<td class="td4" colspan="3">
									<input name="monthBackMoney" type="text" class="input3"
										onkeydown="enterNextFocus1();" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									&#24744;&#30340;&#20844;&#31215;&#37329;&#36151;&#27454;&#39069;&#24230;&#20026;(&#20803;)
								</td>
								<td class="td4" width="72%" colspan="2">
									<input name="uMaxLoanMoney" type="text" class="input3"
										onkeydown="enterNextFocus1();">
								</td>
								<td class="td4" width="11%">
									<input type="button" class="buttona" value="计算" name="button3"
										onClick="cacluatea();">
								</td>
							</tr>
							<tr>
								<td width="17%" class=td1>
									您的贷款年限为(年)
								</td>
								<td class="td4" width="72%" colspan="2">
									<input name="uMaxLoanYear" type="text" class="input3"
										onkeydown="enterNextFocus1();">
								</td>
								<td class="td4" width="11%">
									<input type="button" class="buttona" value="计算" name="button4"
										onClick="cacluatea();">
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
												<input type="button" name="testme" value="月还款试算"
													class="buttonc" onClick="redirect();" disabled="disabled">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

