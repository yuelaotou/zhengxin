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
	String contractid=(String)request.getSession().getAttribute("contractid");
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
	window.open('<%=request.getContextPath()%>/sysloan/showEmployeesListAC.do?orgid='+''+'&st='+''+'&cardnum='+''+'&empname='+''+'&indexs='+1000+'&objInput=empId','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,status=yes');
}
function executeAjaxIn_wsh() {
   	var queryString = "<%=request.getContextPath()%>/sysloan/findLoanapplywshAAC.do?";
    var empid = document.all.empId.value.trim();
    var orgid = document.all.org_Id.value.trim();
    var office = document.othersLoanAF.elements["othersLoan.OFFICE"].value.trim();
    queryString = queryString +"empid="+empid+"&orgid="+orgid+"&office="+office; 	     
   	findInfo(queryString);	     
}
function display(empid,borrowerName,sex,cardKind,cardNum,birthday,orgId,orgName,orgTel,orgMail,accBlnce,orgAddr,monthSalary,monthPay,empSt,bgnDate,offices,endDate,zdjzqje){
	var b=birthday.substr(0,4);
	var d= new Date();
	var year= d.getYear();
	var age=parseFloat(year)-parseFloat(b);
	document.othersLoanAF.elements["othersLoan.borrowerCardtype"].value=cardKind;
	document.othersLoanAF.elements["othersLoan.BORROWERSEX"].value=sex;
	document.othersLoanAF.elements["othersLoan.borrowerEmpId"].value=empid;
	document.othersLoanAF.elements["othersLoan.borrowerName"].value=borrowerName;
	document.othersLoanAF.elements["othersLoan.BORROWERAGE"].value=age;
	document.othersLoanAF.elements["othersLoan.BORROWERCARDNUM"].value=cardNum;
	if(cardNum.length==15){
		var date ="19"+cardNum.substr(6,6);
		document.othersLoanAF.elements["othersLoan.BORROWERBIRTHDAY"].value=date;
		findage();
	}else{
		var date=cardNum.substr(6,8)
		document.othersLoanAF.elements["othersLoan.BORROWERBIRTHDAY"].value=date;
		findage();
	}
	//document.loanapplynewAF.elements["borrower.birthday"].value=birthday;
	document.othersLoanAF.elements["othersLoan.BORROWERORGID"].value=orgId;
	document.othersLoanAF.elements["othersLoan.BORROWERORGNAME"].value=orgName;
	document.othersLoanAF.elements["othersLoan.BORROWERORGTEL"].value=orgTel;
	document.othersLoanAF.elements["othersLoan.BORROWERBALANCE"].value=accBlnce;
	document.othersLoanAF.elements["othersLoan.BORROWERORGADD"].value=orgAddr;
	document.othersLoanAF.elements["othersLoan.BORROWERSALARYBASE"].value=monthSalary;
	document.othersLoanAF.elements["othersLoan.BORROWERMONTHPAY"].value=monthPay;
	document.othersLoanAF.elements["othersLoan.BORROWEREMPST"].value=empSt;
	document.othersLoanAF.elements["othersLoan.BORROWERBINDATE"].value=bgnDate;
	document.othersLoanAF.elements["othersLoan.BORROWERENDDATE"].value=endDate;
	
	document.othersLoanAF.elements["othersLoan.BORROWERTIQU"].value=zdjzqje;//最大可支取金额
	document.othersLoanAF.elements["othersLoan.borrowerName"].readOnly="readOnly";
}
function display1(me){
	alert(me);
}	
function findEmp_fuzhujiekuanren(){
	window.open('<%=request.getContextPath()%>/sysloan/showEmployeesListAC.do?orgid='+''+'&st='+''+'&cardnum='+''+'&empname='+''+'&indexs='+3+'&objInput=assisEmpId','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,status=yes');
}
function findage(){
	var birthday = document.othersLoanAF.elements["othersLoan.BORROWERBIRTHDAY"].value.trim();
	if(birthday==""){
		alert("请输入正确格式的出生年月,例如：20070101");
		document.othersLoanAF.elements["othersLoan.BORROWERBIRTHDAY"].value="";
		return false;	
	}
	if(!isNumber(birthday)){
		alert("请输入正确格式的出生年月,例如：20070101");
		document.othersLoanAF.elements["othersLoan.BORROWERBIRTHDAY"].value="";
		return false;	
	}
	var b=birthday.substr(0,4);
	var d= new Date();
	var year= d.getYear();
	var age=parseFloat(year)-parseFloat(b);
	document.othersLoanAF.elements["othersLoan.BORROWERAGE"].value=age;

}
function executeAjaxIn() {
   	var queryString = "<%=request.getContextPath()%>/sysloan/otherLoanapplyAAC.do?";
    var empid = document.all.assisEmpId.value.trim();
    var orgid = document.all.org_Id.value.trim();
    var office = document.othersLoanAF.elements["othersLoan.OFFICE"].value.trim();
    queryString = queryString +"empid="+empid+"&orgid="+orgid+"&office="+office; 	     
   	findInfo(queryString);	     
}
function display2(empid,borrowerName,sex,cardKind,cardNum,birthday,orgId,orgName,orgTel,orgMail,accBlnce,orgAddr,monthSalary,monthPay,empSt,bgnDate,offices,endDate,zdjzqje){
	var b=birthday.substr(0,4);
	var d= new Date();
	var year= d.getYear();
	var age=parseFloat(year)-parseFloat(b);
	document.othersLoanAF.elements["othersLoan.ASSISCARDTYPE"].value=cardKind;
	document.othersLoanAF.elements["othersLoan.ASSISSEX"].value=sex;
	document.othersLoanAF.elements["othersLoan.ASSISEMPID"].value=empid;
	document.othersLoanAF.elements["othersLoan.ASSISNAME"].value=borrowerName;
	document.othersLoanAF.elements["othersLoan.ASSISAGE"].value=age;
	document.othersLoanAF.elements["othersLoan.ASSISCARDNUM"].value=cardNum;
	if(cardNum.length==15){
		var date ="19"+cardNum.substr(6,6);
		document.othersLoanAF.elements["othersLoan.ASSISBIRTHDAY"].value=date;
		findageFuZhu();
	}else{
		var date=cardNum.substr(6,8)
		document.othersLoanAF.elements["othersLoan.ASSISBIRTHDAY"].value=date;
		findageFuZhu();
	}
	//document.loanapplynewAF.elements["borrower.birthday"].value=birthday;
	document.othersLoanAF.elements["othersLoan.ASSISORGID"].value=orgId;
	document.othersLoanAF.elements["othersLoan.ASSISORGNAME"].value=orgName;
	document.othersLoanAF.elements["othersLoan.ASSISORGTEL"].value=orgTel;
	document.othersLoanAF.elements["othersLoan.ASSISBALANCE"].value=accBlnce;
	document.othersLoanAF.elements["othersLoan.ASSISORGADDR"].value=orgAddr;
	document.othersLoanAF.elements["othersLoan.ASSISSALARYBASE"].value=monthSalary;
	document.othersLoanAF.elements["othersLoan.ASSISMONTHPAY"].value=monthPay;
	document.othersLoanAF.elements["othersLoan.ASSISEMPST"].value=empSt;
	document.othersLoanAF.elements["othersLoan.ASSISBINDATE"].value=bgnDate;
	document.othersLoanAF.elements["othersLoan.ASSISENDDATE"].value=endDate;
	
	document.othersLoanAF.elements["othersLoan.ASSISTIQU"].value=zdjzqje;//最大可支取金额
	//document.othersLoanAF.elements["othersLoan.borrowerName"].readOnly="readOnly";
}
function findageFuZhu(){
	var birthday = document.othersLoanAF.elements["othersLoan.BORROWERBIRTHDAY"].value.trim();
	if(birthday==""){
		alert("请输入正确格式的出生年月,例如：20070101");
		document.othersLoanAF.elements["othersLoan.ASSISBIRTHDAY"].value="";
		return false;	
	}
	if(!isNumber(birthday)){
		alert("请输入正确格式的出生年月,例如：20070101");
		document.othersLoanAF.elements["othersLoan.ASSISBIRTHDAY"].value="";
		return false;	
	}
	var b=birthday.substr(0,4);
	var d= new Date();
	var year= d.getYear();
	var age=parseFloat(year)-parseFloat(b);
	document.othersLoanAF.elements["othersLoan.ASSISAGE"].value=age;

}
function acounthouseTotle(){
	var houseTotlePrice = document.othersLoanAF.elements["othersLoan.HOUSETOTALPRICE"].value.trim();
	if(!checkmonths(houseTotlePrice)){
		document.othersLoanAF.elements["othersLoan.HOUSETOTALPRICE"].value="";
		alert("请输入正确金额,例如1200.36");
		return false;
	}
	var ahouseArea = document.othersLoanAF.elements["othersLoan.HOUSEAREA"].value.trim();
	if(ahouseArea!=""&&houseTotlePrice!=""){
		acounthouseArea();
	}
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
function acounthouseArea(){
	var houseTotlePrice = document.othersLoanAF.elements["othersLoan.HOUSETOTALPRICE"].value.trim();
	var houseArea = document.othersLoanAF.elements["othersLoan.HOUSEAREA"].value.trim();
	if(!checkmonths(houseArea)){
		document.othersLoanAF.elements["othersLoan.HOUSEAREA"].value="";
		alert("请输入正确建筑面积,例如120.36");
		return false;
	}
 	if(houseArea!=""&&houseTotlePrice!=""){
 		var unitprice = parseFloat(houseTotlePrice)/parseFloat(houseArea)+"";
 		var indx = unitprice.indexOf(".",0);
 		if(indx!=-1){
			unitprice = unitprice.substring(0,indx+3);
 		}
		document.othersLoanAF.elements["othersLoan.price"].value = parseFloat(unitprice);
 	}
}
function ischekcard(){
	var borrowerName = document.othersLoanAF.elements["othersLoan.borrowerName"].value.trim();
	var houseTotlePrice = document.othersLoanAF.elements["othersLoan.HOUSETOTALPRICE"].value.trim();
	var houseArea = document.othersLoanAF.elements["othersLoan.HOUSEAREA"].value.trim();
	var salary = document.othersLoanAF.elements["othersLoan.BORROWERSALARYBASE"].value.trim();
	var sex = document.othersLoanAF.elements["othersLoan.BORROWERSEX"].value.trim();
	var borrowerCardNum = document.othersLoanAF.elements["othersLoan.BORROWERCARDNUM"].value.trim();
	var borrowerBirthday=document.othersLoanAF.elements["othersLoan.BORROWERBIRTHDAY"].value.trim();
	var borrowerAge=document.othersLoanAF.elements["othersLoan.BORROWERAGE"].value.trim();
	if(borrowerName!=""){
		
		
		if(sex==""){
		 	alert('请输入借款人性别');
		 	return false;
		}
		if(borrowerCardNum==""){
		 	alert('请输入借款人身份证号');
		 	return false;
		}
		if(borrowerBirthday==""){
		 	alert('请输入借款人出生日期');
		 	return false;
		}
		if(borrowerAge==""){
		 	alert('请输入借款人年龄');
		 	return false;
		}
		if(salary==""){
			alert('请输入借款人工资基数');
		 	return false;
		}
	}
	if(houseArea==""){
	 	alert('请输入房屋面积');
	 	return false;
	}
	if(houseTotlePrice==""){
	 	alert('请输入房屋总价');
	 	return false;
	}
	
	
	
	var assisName = document.othersLoanAF.elements["othersLoan.ASSISNAME"].value.trim();
	var assisSalary = document.othersLoanAF.elements["othersLoan.ASSISSALARYBASE"].value.trim();
	var assisSex = document.othersLoanAF.elements["othersLoan.ASSISSEX"].value.trim();
	var assisCardNum = document.othersLoanAF.elements["othersLoan.ASSISCARDNUM"].value.trim();
	var assisBirthday=document.othersLoanAF.elements["othersLoan.ASSISBIRTHDAY"].value.trim();
	var assisAge=document.othersLoanAF.elements["othersLoan.ASSISAGE"].value.trim();
	if(assisName.trim()!=""){
		
		
		if(assisSex==""){
		 	alert('请输入共同借款人性别');
		 	return false;
		}
		if(assisCardNum==""){
		 	alert('请输入共同借款人身份证号');
		 	return false;
		}
		if(assisBirthday==""){
		 	alert('请输入共同借款人出生日期');
		 	return false;
		}
		if(assisAge==""){
		 	alert('请输入共同借款人年龄');
		 	return false;
		}
		if(assisSalary==""){
			alert('请输入共同借款人工资基数');
		 	return false;
		}
	}
	var loanCity = document.othersLoanAF.elements["othersLoan.LOANCITY"].value.trim();
	var loanMoney=document.othersLoanAF.elements["othersLoan.LOANMONEY"].value.trim();
	var loanTime=document.othersLoanAF.elements["othersLoan.LOANTIME"].value.trim();
	if(loanCity==""){
		 	alert('请输入贷款城市');
		 	return false;
		}
		if(loanMoney==""){
		 	alert('请输入贷款金额');
		 	return false;
		}
		if(loanTime==""){
		 	alert('请输入贷款期限');
		 	return false;
		}
}
//身份证焦点离开时算出年龄及出生日期
function displayBirAge(){
	var cardNum = document.othersLoanAF.elements["othersLoan.BORROWERCARDNUM"].value.trim();
	var date;
	if(cardNum!=""){
		if(cardNum.length==15){
			date = "19"+cardNum.substr(6,6);
		}else if(cardNum.length==18){
			date = cardNum.substr(6,8);
		}
		document.othersLoanAF.elements["othersLoan.BORROWERBIRTHDAY"].value = date;
		var d= new Date();
		var year = d.getYear();
		var age = parseFloat(year)-parseFloat(date.substr(0,4));
		document.othersLoanAF.elements["othersLoan.BORROWERAGE"].value = age;
	}
}
function displayBirAge1(){
	var cardNum = document.othersLoanAF.elements["othersLoan.ASSISCARDNUM"].value.trim();
	var date;
	if(cardNum!=""){
		if(cardNum.length==15){
			date = "19"+cardNum.substr(6,6);
		}else if(cardNum.length==18){
			date = cardNum.substr(6,8);
		}
		document.othersLoanAF.elements["othersLoan.ASSISBIRTHDAY"].value = date;
		var d= new Date();
		var year = d.getYear();
		var age = parseFloat(year)-parseFloat(date.substr(0,4));
		document.othersLoanAF.elements["othersLoan.ASSISAGE"].value = age;
	}
}
</script>
	<body bgcolor="#FFFFFF" text="#000000" >
		<html:form action="/othersLoanMaintainAC.do">
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				align="center">
				<html:hidden name="othersLoanAF" property="org_Id" />
				<html:hidden name="othersLoanAF" property="empId" />
				<html:hidden name="othersLoanAF" property="assisEmpId" />
				<html:hidden name="othersLoanAF" property="othersLoan.id" />
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
												<a href="" class=a2>异地贷款信息</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a href="<%=path%>/sysloan/othersLoanTbForwardURLAC.do" class=a2>异地贷款信息维护</a>
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
												<font color="00B5DB">申请贷款&gt;申请异地贷款</font>
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
									<font color="#ff0000"><span class="STYLE1">*</span></font>
								</td>
								<td width="28%" class="td4">
									<html:select name="othersLoanAF" property="othersLoan.OFFICE"
										styleClass="input4">
							
										<html:options collection="officeList" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td width="12%" >
									
								</td>
								<td width="28%" class="td4">
									
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
								<td class=td1 width="12%">
									职工编号
								</td>
								<td class="td4" width="8%" colspan="1">
									<html:text property="othersLoan.borrowerEmpId" name="othersLoanAF"
										styleClass="input3" ondblclick="executeAjax();" readonly="true"/>
								</td>
								<td class="td4" colspan="1">
										<input type="button" class="buttona" value="..." name="button"
											onClick="findEmp();">
								</td>
								<td width="12%" class=td1>
									借款人姓名
									<font color="#ff0000"><span class="STYLE1">*</span></font>
								</td>
								<td class="td4" width="17%">
									<html:text property="othersLoan.borrowerName"
										name="othersLoanAF" styleClass="input3" maxlength="8" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="12%" class=td1>
									性别
									<font color="#ff0000"><span class="STYLE1">*</span></font>
								</td>
								<td class="td4" width="30%">
									<html:select name="othersLoanAF" property="othersLoan.BORROWERSEX"
										styleClass="input4" onchange="changeSex()" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="sexMap"
											name="othersLoanAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									证件类型
								</td>
								<td class="td4" colspan="2">
									<html:select name="othersLoanAF" property="othersLoan.borrowerCardtype"
										styleClass="input4" onchange="changecardkingc()" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="cardkingMap"
											name="othersLoanAF" label="value" value="key" />
									</html:select>
								</td>
								<td width="12%" class=td1>
									证件号码
									<font color="#ff0000"><span class="STYLE1">*</span></font>
								</td>
								<td class="td4" width="17%">
									<html:text property="othersLoan.BORROWERCARDNUM" name="othersLoanAF" maxlength="18"
										styleClass="input3"  onkeydown="enterNextFocus1();" onblur="displayBirAge()" />
								</td>
								<td width="12%" class=td1>
									出生日期
									<font color="#ff0000"><span class="STYLE1">*</span></font>
								</td>
								<td class="td4" width="30%">
									<html:text property="othersLoan.BORROWERBIRTHDAY" name="othersLoanAF"
										styleClass="input3" maxlength="8" onblur="findage()" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									年龄
									<font color="#ff0000"><span class="STYLE1">*</span></font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="othersLoan.BORROWERAGE" name="othersLoanAF"
										styleClass="input3" maxlength="3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="12%" class=td1>
									家庭住址
								</td>
								<td class="td4" width="17%">
									<html:text property="othersLoan.BORROWERHOMEADDR" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="12%" class=td1>
									电话
								</td>
								<td class="td4" width="17%">
									<html:text property="othersLoan.BORROWERHOMETEL" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
						</table>
						<table>
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
									<html:text property="othersLoan.BORROWERORGID" name="othersLoanAF"
										styleClass="input3" readonly="true" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="12%" class=td1>
									单位名称
								</td>
								<td class="td4" width="17%" colspan="3">
									<html:text property="othersLoan.BORROWERORGNAME" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
							<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="othersLoan.BORROWERORGTEL" name="othersLoanAF"
										styleClass="input3" maxlength="20" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="12%" class=td1>
									月工资额<font color="#ff0000">*</font>
								</td>
								<td class="td4" width="17%">
										<html:text property="othersLoan.BORROWERSALARYBASE" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="12%" class=td1>
									账户状态
								</td>
								<td class="td4" width="30%">
									<html:text property="othersLoan.BORROWEREMPST" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
							<td width="12%" class=td1>
									单位地址
								</td>
								<td class="td4" colspan="6">
									<html:text property="othersLoan.BORROWERORGADD" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									月缴存额
								</td>
								<td class="td4" colspan="2">
									<html:text property="othersLoan.BORROWERMONTHPAY" name="othersLoanAF"
										styleClass="input3" maxlength="20" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="12%" class=td1>
									账户余额
								</td>
								<td class="td4" width="17%">
									<html:text property="othersLoan.BORROWERBALANCE" name="othersLoanAF"
										styleClass="input3" maxlength="20" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="12%" class=td1>
									最大可支取金额
								</td>
								<td class="td4" width="30%">
									<html:text property="othersLoan.BORROWERTIQU" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" readonly="true"/>
								</td>
							</tr>						
							<tr>
								<td width="12%" class=td1>
									初缴年月
								</td>
								<td class="td4" colspan="2">
									<html:text property="othersLoan.BORROWERBINDATE" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
								</td>
								<td width="12%" class=td1>
									缴至年月
								</td>
								<td class="td4" >
									<html:text property="othersLoan.BORROWERENDDATE" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
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
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							  <tr>
								<td class=td1 width="12%">
									职工编号
								</td>
								<td class="td4" width="8%" colspan="1">
									<html:text property="othersLoan.ASSISEMPID" name="othersLoanAF"
										styleClass="input3" readonly="true"  />
								</td>
								<td class="td4" colspan="1">
									<input type="button" class="buttona" value="..."
										name="buttonemp" onclick="findEmp_fuzhujiekuanren()">
								</td>
								<td class="td1" width="12%">
									职工姓名
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" width="17%">
									<html:text property="othersLoan.ASSISNAME" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td class=td1 width="12%">
									与借款人关系
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" width="30%">
									<html:select name="othersLoanAF" property="othersLoan.ASSISRELATION"
										styleClass="input4" 
										onkeydown="enterNextFocus1();">
										<html:optionsCollection property="relationMap"
											name="othersLoanAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td class=td1>
									性别
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:select name="othersLoanAF" property="othersLoan.ASSISSEX"
										styleClass="input4" onchange="tosex()"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="sexMap"
											name="othersLoanAF" label="value" value="key" />
									</html:select>
								</td>
								<td class=td1>
									证件类型
								</td>
								<td class="td4">
									<html:select name="othersLoanAF" property="othersLoan.ASSISCARDTYPE"
										styleClass="input4" onchange="tocardking()"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="cardkingMap"
											name="othersLoanAF" label="value" value="key" />
									</html:select>
								</td>
								<td class=td1>
									证件号码
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4">
									<html:text property="othersLoan.ASSISCARDNUM" name="othersLoanAF"
										styleClass="input3"
										onkeydown="enterNextFocus1();" onblur="displayBirAge1()"/>
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									出生日期
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="othersLoan.ASSISBIRTHDAY" name="othersLoanAF"
										styleClass="input3" onblur="accountage();"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									年龄
									<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" width="17%">
									<html:text property="othersLoan.ASSISAGE" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									电话
								</td>
								<td class="td4" width="30%">
									<html:text property="othersLoan.ASSISMOBILE" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								
							</tr>
							<tr>
								<td width="12%" class=td1>
									家庭住址
								</td>
								<td class="td4" colspan="2">
									<html:text property="othersLoan.ASSISHOMEADDR" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
								</td>
								<td class="td4" width="17%">
								</td>
								<td width="12%" class=td1>
								</td>
								<td class="td4" width="30%">
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
								<td width="12%" class=td1>
									单位编号
								</td>
								<td class="td4" colspan="2">
									<html:text property="othersLoan.ASSISORGID" name="othersLoanAF"
										styleClass="input3" readonly="true" />
								</td>
								<td width="12%" class=td1>
									单位名称
								</td>
								<td class="td4" width="17%" colspan="5">
									<html:text property="othersLoan.ASSISORGNAME" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									单位电话
								</td>
								<td class="td4" colspan="2">
									<html:text property="othersLoan.ASSISORGTEL" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
										账户状态
								</td>
								<td class="td4" width="17%" colspan="2">
									<html:text property="othersLoan.ASSISEMPST" name="othersLoanAF"
										styleClass="input3" readonly="true" />
								</td>
								<td width="12%" class=td1>
								</td>
								<td class="td4" width="30%" colspan="3">
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									单位地址
								</td>
								<td class="td4" colspan="5">
									<html:text property="othersLoan.ASSISORGADDR" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									月工资额
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="30%" colspan="2">
									<html:text property="othersLoan.ASSISSALARYBASE" name="othersLoanAF"
										styleClass="input3" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									月缴存额
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="2">
									<html:text property="othersLoan.ASSISMONTHPAY" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="12%" class=td1>
									账户余额
								</td>
								<td class="td4" width="17%"  colspan="2">
									<html:text property="othersLoan.ASSISBALANCE" name="othersLoanAF"
										styleClass="input3"  />
								</td>
								<td width="12%" class=td1>
									最大可支取金额
								</td>
								<td class="td4" width="30%"  colspan="2">
									<html:text property="othersLoan.ASSISTIQU" name="othersLoanAF"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									初缴年月
								</td>
								<td class="td4" colspan="2">
									<html:text property="othersLoan.ASSISBINDATE" name="othersLoanAF"
										styleClass="input3"  />
								</td>
								<td width="12%" class=td1>
									缴至年月
								</td>
								<td class="td4" width="17%"  colspan="2">
									<html:text property="othersLoan.ASSISENDDATE" name="othersLoanAF"
										styleClass="input3"  />
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
												<b class="font14">购房信息</b>
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
								<td width="12%" class=td1>
									房屋面积
									<font color="#FF0000">*</font>
								</td>
								<td class="td4" colspan="3">
									<html:text property="othersLoan.HOUSEAREA" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" onblur="return acounthouseArea();"/>
								</td>
								<td width="12%" class=td1>
									房屋总价<font color="#FF0000">*</font>
								</td>
								<td class="td4" width="17%"  colspan="2">
									<html:text property="othersLoan.HOUSETOTALPRICE" name="othersLoanAF"
										styleClass="input3"   onblur="return acounthouseTotle();"/>
								</td>
								<td width="12%" class=td1>
								  房屋单价（元/m<sup>
												2
											</sup>）
								</td>
								<td class="td4" width="30%" colspan="2">
									<html:text property="othersLoan.price" name="othersLoanAF"
										styleClass="input3"  />
								</td>
							</tr>
							<tr>
								<td width="12%" class=td1>
									房屋地址
								</td>
								<td class="td4" colspan="4">
									<html:text property="othersLoan.HOUSEADDR" name="othersLoanAF"
										styleClass="input3"  />
								</td>
								<td width="12%">
								</td>
								<td class="td4" width="17%"  colspan="2">
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
												<b class="font14">额度信息</b>
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
								<td width="16%" class=td1>
								  贷款城市<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" colspan="2" width="16%">
									<html:text property="othersLoan.LOANCITY" name="othersLoanAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="16%" class=td1>
								   贷款金额（元）<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" width="16%" colspan="2">
									<html:text property="othersLoan.LOANMONEY" name="othersLoanAF"
										styleClass="input3"  />
								</td>
								<td width="16%" class=td1>
								   贷款年限（年）<font color="#ff0000"><span class="STYLE1">*</span> </font>
								</td>
								<td class="td4" width="16%" colspan="1">
								<html:text property="othersLoan.LOANTIME" name="othersLoanAF"
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
											<html:submit property="method" styleClass="buttona" onclick="return ischekcard();return checkCardNum();">
												<bean:message key="button.sure" />
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
</html>

