<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>

<%
	String path = request.getContextPath();
	String flag = "0";
	if (request.getAttribute("flag") != null) {
		flag = (String) request.getAttribute("flag");
	}
%>

<html:html>
<head>
	<title>人员变更-办理变更-添加</title>
	<meta http-equiv="Content-Type" content="text/html;" />
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript" type="text/javascript">

function toPop() {
	var orgID = document.forms[0].elements["orgID"].value.trim();
	//alert(orgID);
	open_findEmp('<%=path%>',orgID,'null','null','null','0');
}

function reportMessage(){
	var empid=document.forms[0].elements["chgPersonTail.empId"].value;
	if(empid!=""){
	document.forms[0].elements["chgPersonTail.empId"].value=format(document.forms[0].elements["chgPersonTail.empId"].value)+document.forms[0].elements["chgPersonTail.empId"].value;
	}
	
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>";
	alert(message);
	</logic:messagesPresent>

	document.forms[0].elements["chgPersonTail.temp_name"].value="";
}

function executeAjax(){
	var x = document.forms[0].elements["chgPersonTail.empId"].value.trim();
	if(x==""){
		toPop();
	}else if(isNaN(x)){
        	alert("请输入正确格式的编号！");
        	document.chgpersonDoListAF.elements["chgPersonTail.empId"].value.focus();
        	return false;
    }else{
	    var queryString = "chgpersonFindEmpAAC.do?";
	    queryString = queryString + "empID="+document.forms[0].elements["chgPersonTail.empId"].value.trim()+"&chgType="+document.forms[0].elements["chgMap_1"].value.trim();
	    findInfo(queryString);
    }
}


function reportErrors(message) {
	if(message!=""){
		alert(message);
	}
}

function changeType(){
	var para=document.forms[0].elements["chgMap_1"].value;
	if(para==1){
		kh_1.style.display="";
		kh_2.style.display="";
		kh_3.style.display="";
		kh_4.style.display="";
		kh_5.style.display="";
		kh_6.style.display="";
		kh_7.style.display="";
		qfc_1.style.display="none";
		qfc_2.style.display="none";
		qfc_3.style.display="none";
		qfc_4.style.display="none";
		qfc_5.style.display="none";
		qfc_6.style.display="none";
	}else{
		kh_1.style.display="none";
		kh_2.style.display="none";
		kh_3.style.display="none";
		kh_4.style.display="none";
		kh_5.style.display="none";
		kh_6.style.display="none";
		kh_7.style.display="none";
		qfc_1.style.display="";
		qfc_2.style.display="";
		qfc_3.style.display="";
		qfc_4.style.display="";
		qfc_5.style.display="";
		qfc_6.style.display="";
	}
  if(para!=1){//是启封或封存时
    document.forms[0].elements["chgPersonTail.temp_name"].readOnly=true;
    document.forms[0].elements["chgPersonTail.temp_cardNum"].readOnly=true;
	document.forms[0].elements["chgPersonTail.temp_oldPayStatus_qfc"].readOnly=true;
	document.forms[0].elements["orgPayMode"].readOnly=true;
	document.forms[0].elements["chgPersonTail.temp_salaryBase"].readOnly=true;
	document.forms[0].elements["chgPersonTail.temp_orgPay"].readOnly=true;
	document.forms[0].elements["chgPersonTail.temp_empPay"].readOnly=true;
	document.forms[0].elements["chgPersonTail.sumPay"].readOnly=true;
  }else{
    document.forms[0].elements["chgPersonTail.name"].readOnly=false;
    document.forms[0].elements["chgPersonTail.cardNum"].readOnly=false;
	document.forms[0].elements["chgPersonTail.salaryBase"].readOnly=false;
	document.forms[0].elements["chgPersonTail.orgPay"].readOnly=false;
	document.forms[0].elements["chgPersonTail.empPay"].readOnly=false;
  }
  
}

function init(){
	var para=1;
	if(para==1){
		kh_1.style.display="";
		kh_2.style.display="";
		kh_3.style.display="";
		kh_4.style.display="";
		kh_5.style.display="";
		kh_6.style.display="";
		kh_7.style.display="";
		qfc_1.style.display="none";
		qfc_2.style.display="none";
		qfc_3.style.display="none";
		qfc_4.style.display="none";
		qfc_5.style.display="none";
		qfc_6.style.display="none";
	}else{
		kh_1.style.display="none";
		kh_2.style.display="none";
		kh_3.style.display="none";
		kh_4.style.display="none";
		kh_5.style.display="none";
		kh_6.style.display="none";
		kh_7.style.display="none";
		qfc_1.style.display="";
		qfc_2.style.display="";
		qfc_3.style.display="";
		qfc_4.style.display="";
		qfc_5.style.display="";
		qfc_6.style.display="";
	}
	if(para!=1){//是启封或封存时
	    document.forms[0].elements["chgPersonTail.temp_name"].readOnly=true;
	    document.forms[0].elements["chgPersonTail.temp_cardNum"].readOnly=true;
		document.forms[0].elements["chgPersonTail.temp_oldPayStatus_qfc"].readOnly=true;
		document.forms[0].elements["orgPayMode"].readOnly=true;
		document.forms[0].elements["chgPersonTail.temp_salaryBase"].readOnly=true;
		document.forms[0].elements["chgPersonTail.temp_orgPay"].readOnly=true;
		document.forms[0].elements["chgPersonTail.temp_empPay"].readOnly=true;
		document.forms[0].elements["chgPersonTail.sumPay"].readOnly=true;
	}else{
	    document.forms[0].elements["chgPersonTail.name"].readOnly=false;
	    document.forms[0].elements["chgPersonTail.cardNum"].readOnly=false;
		document.forms[0].elements["chgPersonTail.salaryBase"].readOnly=false;
		document.forms[0].elements["chgPersonTail.orgPay"].readOnly=false;
		document.forms[0].elements["chgPersonTail.empPay"].readOnly=false;
	}
	if(<%=flag%>==1){
	  	var x=confirm("系统中存在与该用户相同身份证号不同姓名的职工，是否继续添加?");
		if(x){   
			document.forms[0].action="chgpersonSaveAC.do";
			document.forms[0].submit();
			return true;
		}else{
		  	return false;
		}
	}
}

function checkData(){
	var para=document.forms[0].elements["chgMap_1"].value;
	if(para==1){
	  //是开户提交的非空判断
	  //alert("==========提交后==========");
	  var empName=document.forms[0].elements["chgPersonTail.name"].value.trim();
      var cardNum=document.forms[0].elements["chgPersonTail.cardNum"].value.trim();
      var birthday=document.forms[0].elements["chgPersonTail.birthday"].value.trim();
      
      var salaryBase=document.forms[0].elements["chgPersonTail.salaryBase"].value.trim();
      document.forms[0].elements["chgPersonTail.salaryBase"].value=salaryBase;
      var orgPay=document.forms[0].elements["chgPersonTail.orgPay"].value.trim();
      document.forms[0].elements["chgPersonTail.orgPay"].value=orgPay;
      var empPay=document.forms[0].elements["chgPersonTail.empPay"].value.trim();
      document.forms[0].elements["chgPersonTail.empPay"].value=empPay;
      var monthIncome=document.forms[0].elements["chgPersonTail.monthIncome"].value.trim();
      document.forms[0].elements["chgPersonTail.monthIncome"].value=monthIncome;
      
      var cardKind = document.forms[0].elements["documentMap_1"].value.trim();
      var sex = document.forms[0].elements["sexMap_1"].value.trim();
      var partIn = document.forms[0].elements["partInMap_1"].value.trim();
      
    if(empName.length==0){
		alert("请录入变更的职工！！");
		document.forms[0].elements["chgPersonTail.name"].focus();
		return false;
	}else if(cardKind.length==0){
		alert("请录入证件类型！！");
		document.forms[0].elements["documentMap_1"].focus();
		return false;
	}else if(sex.length==0){
		alert("请录入性别！！");
		document.forms[0].elements["sexMap_1"].focus();
		return false;
	}else if(partIn.length==0){
		alert("请录入是否参与汇缴！！");
		document.forms[0].elements["partInMap_1"].focus();
		return false;
	}else if(cardNum.length==0){
		alert("请录入必填项！！！");
		document.forms[0].elements["chgPersonTail.cardNum"].focus();
		return false;
	}else if(birthday.length==0){
		alert("请录入必填项！！！");
		document.forms[0].elements["chgPersonTail.birthday"].focus();
		return false;
	}else if(isNaN(salaryBase)||salaryBase.length==0){
		alert("请正确录入工资基数项！！！");
		document.forms[0].elements["chgPersonTail.salaryBase"].focus();
		return false;
	}else if(orgPay==null||isNaN(orgPay)){
		alert("请录入单位缴额项！！！");
		document.forms[0].elements["chgPersonTail.orgPay"].focus();
		return false;
	}else if(empPay==null||isNaN(empPay)){
		alert("请录入职工缴额项！！！");
		document.forms[0].elements["chgPersonTail.empPay"].focus();
		return false;
	}else{
		executeAjax_bit();
	}
	}else{
		var empName=document.forms[0].elements["chgPersonTail.temp_name"].value.trim();
		if(empName==""){
			alert("请选择职工！！！");
			document.forms[0].elements["chgPersonTail.temp_name"].focus();
			return false;
		}
		executeAjax_bit();
	}
	
}

function findEmployeeInfo(){
	var x=document.forms[0].elements["chgPersonTail.empId"].value.trim();
	if(isNaN(x)){
        	alert("请输入正确格式的编号！");
        	document.chgpersonDoListAF.elements["chgPersonTail.empId"].value.focus();
        	return false;
    }else{
	    var queryString = "chgpersonFindEmpAAC.do?";
	    queryString = queryString + "empID="+document.forms[0].elements["chgPersonTail.empId"].value.trim()+"&chgType="+document.forms[0].elements["chgMap_1"].value.trim();
	    findInfo(queryString);
    }
}

function displays(id,name,cardNum,payStatus,payMode,salaryBase,orgPay,empPay,sumPay,status) {

	if(status=="0"){
		document.forms[0].elements["chgPersonTail.empId"].value=id;
		document.forms[0].elements["chgPersonTail.temp_name"].value=name;
		document.forms[0].elements["chgPersonTail.temp_cardNum"].value=cardNum;
		document.forms[0].elements["chgPersonTail.temp_oldPayStatus_qfc"].value=payStatus;
		document.forms[0].elements["orgPayMode"].value=payMode;
		document.forms[0].elements["chgPersonTail.temp_salaryBase"].value=salaryBase;
		document.forms[0].elements["chgPersonTail.temp_orgPay"].value=orgPay;
		document.forms[0].elements["chgPersonTail.temp_empPay"].value=empPay;
		document.forms[0].elements["chgPersonTail.sumPay"].value=sumPay;
		
		if(id!=""){
			document.forms[0].elements["chgPersonTail.empId"].value=format(document.forms[0].elements["chgPersonTail.empId"].value)+document.forms[0].elements["chgPersonTail.empId"].value;
		}
    }else{
    	if(confirm("该职工账户里还有余额，是否继续？")){
    		document.forms[0].elements["chgPersonTail.empId"].value=id;
			document.forms[0].elements["chgPersonTail.temp_name"].value=name;
			document.forms[0].elements["chgPersonTail.temp_cardNum"].value=cardNum;
			document.forms[0].elements["chgPersonTail.temp_oldPayStatus_qfc"].value=payStatus;
			document.forms[0].elements["orgPayMode"].value=payMode;
			document.forms[0].elements["chgPersonTail.temp_salaryBase"].value=salaryBase;
			document.forms[0].elements["chgPersonTail.temp_orgPay"].value=orgPay;
			document.forms[0].elements["chgPersonTail.temp_empPay"].value=empPay;
			document.forms[0].elements["chgPersonTail.sumPay"].value=sumPay;
			
			if(id!=""){
				document.forms[0].elements["chgPersonTail.empId"].value=format(document.forms[0].elements["chgPersonTail.empId"].value)+document.forms[0].elements["chgPersonTail.empId"].value;
			}
    	}else{
    		document.forms[0].elements["chgPersonTail.empId"].value="";
			document.forms[0].elements["chgPersonTail.temp_name"].value="";
			document.forms[0].elements["chgPersonTail.temp_cardNum"].value="";
			document.forms[0].elements["chgPersonTail.temp_oldPayStatus_qfc"].value="";
			document.forms[0].elements["orgPayMode"].value="";
			document.forms[0].elements["chgPersonTail.temp_salaryBase"].value="0";
			document.forms[0].elements["chgPersonTail.temp_orgPay"].value="0";
			document.forms[0].elements["chgPersonTail.temp_empPay"].value="0";
			document.forms[0].elements["chgPersonTail.sumPay"].value="0";
    	}
    }
}

function  checkCardNum(){
	var num=document.forms[0].elements["chgPersonTail.cardNum"].value;
	var numType=document.forms[0].elements["documentMap_1"].value;;
	if(numType==0){
		if(num==null||num==""){
			return true;
		}else if(num!=null&&!isIdCardNo(num)){
			document.forms[0].elements["chgPersonTail.cardNum"].focus();
		}else{
			if(num.length==15){
				var date ="19"+num.substr(6,6);
				document.forms[0].elements["chgPersonTail.birthday"].value=date;
				var sex = num.substr(14);
				if(sex%2==0||sex=='X'){
					document.forms[0].elements["sexMap_1"].value='2'
				}else{
					document.forms[0].elements["sexMap_1"].value='1'
				}
			}else{
				var date=num.substr(6,8)
				document.forms[0].elements["chgPersonTail.birthday"].value=date;
				var sex = num.substr(16,1);
				if(sex%2==0||sex=='X'){
					document.forms[0].elements["sexMap_1"].value='2'
				}else{
					document.forms[0].elements["sexMap_1"].value='1'
				}
			}
			return true;
		}
      }else{
      	return true;
      }
}

function checkBirthday(){
    var birthday=document.forms[0].elements["chgPersonTail.birthday"].value.trim();
    var birthday_ = birthday.match(/^((((19)|(20))[0-9][0-9])(1[0-2]|0?[1-9])(3[0,1]|[1,2][0-9]|0?[1-9]))$/); 
    if(birthday==null||birthday==""){
    	return true;
    }
    if(birthday_==null){
    	alert("请正确录入出生日期信息！格式如：19560502");
    	document.forms[0].elements["chgPersonTail.birthday"].focus();
    	return false;
    }
}

function checkMoney_salary(){
      //判断录入的金额是否正确
     // alert("==================");
      var salary=document.forms[0].elements["chgPersonTail.salaryBase"].value.trim();
      if(!checkMoney(salary)){
        document.forms[0].elements["chgPersonTail.salaryBase"].focus();
		return false;
      }else{
		var orgID =document.forms[0].elements["orgID"].value.trim();
		var queryString= 'accountNewSumPayAAC.do?orgID='+orgID+'&salarybase='+salary+'';
    	findInfo(queryString);
      }
}

function display(newOrgPay,newEmpPay){
	document.forms[0].elements["chgPersonTail.orgPay"].value=newOrgPay;
	document.forms[0].elements["chgPersonTail.empPay"].value=newEmpPay;
}

function checkMoney_orgPay(){
      //判断录入的金额是否正确
     // alert("==========111==========");
      var orgPay=document.forms[0].elements["chgPersonTail.orgPay"].value.trim();
      if(!checkMoney(orgPay)){
        		document.forms[0].elements["chgPersonTail.orgPay"].focus();
				return false;
      }else{
				return true;
      }
}

function checkMoney_empPay(){
     //判断录入的金额是否正确
    // alert("=========222=========");
      var empPay=document.forms[0].elements["chgPersonTail.empPay"].value.trim();
      if(!checkMoney(empPay)){
        		document.forms[0].elements["chgPersonTail.empPay"].focus();
				return false;
      }else{
				return true;
      }
}
function executeAjax_bit(){
	var queryString = "chgpersonAAC.do?";
	var orgId = document.forms[0].elements["orgID"].value.trim();
	var empName = document.forms[0].elements["chgPersonTail.name"].value.trim();
	empName=encodeURI(empName);
    var cardNum = document.forms[0].elements["chgPersonTail.cardNum"].value.trim();
    queryString = queryString + "orgId="+orgId+"&empName="+empName+"&cardNum="+cardNum; 	     
    findInfo(queryString);
}
function display_bit(flag,message){
	if(flag=="exist"){
		var x=confirm(message);
		if(x){
			return true;
		}else{	
			document.chgpersonEmpAF.temp.value="0";
		  	return false;
		}
	}
	
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="init();reportMessage();">

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
									<td width="216" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">变更业务</font><font color="00B5DB">&gt;人员变更</font>
									</td>
									<td width="47" class=font14>
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
				<html:form action="/chgpersonDOSaveAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">职 工 信 息</b>
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
						<tr>
							<td width="17%" class="td1">
								&#21464;&#26356;&#31867;&#22411;
							</td>
							<td width="22%">
								<html:select property="chgMap_1" onchange="changeType();"
									styleClass="input4">
									<html:optionsCollection property="chgMap" name="chgpersonEmpAF"
										label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr id="qfc_1">
							<td width="17%" class="td1">
								职工编号
							</td>
							<td width="23%">
								<html:text property="chgPersonTail.empId" onkeydown="goEnter();"
									ondblclick="executeAjax();" maxlength="20" styleClass="input3"
									styleId="txtsearch"></html:text>
								<html:hidden property="orgID" />
							</td>
							<td width="10%">
								<html:button property="method" styleClass="buttona"
									onclick="toPop()" value="..."></html:button>
							</td>
						</tr>
						<tr id="qfc_6">
							<td width="17%" class="td1">
								职工姓名
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.temp_name" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								变更原因
							</td>
							<td width="22%">
								<html:select property="chgreasonMap_1" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:optionsCollection property="chgreasonMap"
										name="chgpersonEmpAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr id="kh_1">
							<td width="17%" class="td1">
								职工姓名
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.name" maxlength="20"
									onkeydown="enterNextFocus1();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								变更原因
							</td>
							<td width="22%">
								<html:select property="chgreason_2" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:optionsCollection property="chgreasonMap_2"
										name="chgpersonEmpAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr id="kh_2">
							<td width="17%" class="td1">
								证件类型
								<font color="#FF0000">*</font>
							</td>
							<td width="22%" col="2">
								<html:select property="documentMap_1" styleClass="input4"
									onkeydown="enterNextFocus1();" value="0">
									<html:option value=""></html:option>
									<html:optionsCollection property="documentMap"
										name="chgpersonEmpAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								证件号码
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.cardNum"
									onblur="return checkCardNum()" onkeydown="enterNextFocus1();"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr id="qfc_2">
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="22%" col="2">
								<html:text property="chgPersonTail.temp_cardNum" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								缴存状态
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.temp_oldPayStatus_qfc"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr id="kh_3">
							<td width="17%" class="td1">
								出生日期
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.birthday"
									onblur="return checkBirthday();" onkeydown="enterNextFocus1();"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								性别
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:select property="sexMap_1" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="sexMap" name="chgpersonEmpAF"
										label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr id="kh_4">
							<td width="17%" class="td1">
								所在部门
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.dept"
									onkeydown="enterNextFocus1();" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								移动电话
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.mobileTel"
									onkeydown="enterNextFocus1();" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr id="kh_7">
							<td width="17%" class="td1">
								家庭电话
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.tel"
									onkeydown="enterNextFocus1();" maxlength="10"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								职工月收入
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.monthIncome"
									onkeydown="enterNextFocus1();" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr id="qfc_3">
							<td width="17%" class="td1">
								缴存方式
							</td>
							<td width="22%">
								<html:text property="orgPayMode" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								工资基数
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.temp_salaryBase"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr id="kh_5">
							<td width="17%" class="td1">
								工资基数
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.salaryBase"
									onkeydown="enterNextFocus1();"
									onblur="return checkMoney_salary()" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								是否参与汇缴
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:select property="partInMap_1" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:optionsCollection property="partInMap"
										name="chgpersonEmpAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr id="kh_6">
							<td width="17%" class="td1">
								单位缴额
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.orgPay"
									onkeydown="enterNextFocus1();"
									onblur="return checkMoney_orgPay()" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								职工缴额
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.empPay"
									onkeydown="enterNextFocus1();"
									onblur="return checkMoney_empPay()" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr id="qfc_4">
							<td width="17%" class="td1">
								单位缴额
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.temp_orgPay" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								职工缴额
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.temp_empPay" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr id="qfc_5">
							<td width="17%" class="td1">
								缴额合计
							</td>
							<td width="22%">
								<html:text property="chgPersonTail.sumPay" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>

										<td>
											<input type="hidden" name="temp" vaule="1">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkData()">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.back" />
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
</body>
</html:html>
