<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<html:html>
<html:base />
<head>
	<title>变更业务>工资基数调整</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
</head>
<script language="javascript"
	src="<%=request.getContextPath()%>/js/common.js">


<script language="javascript"></script>

<script language="javascript" type="text/javascript">
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	   var empid=document.chgslarybaseNewAF.elements["chgPaymentTail.empId"].value;
	   if(empid!=''){
	   	document.chgslarybaseNewAF.elements["chgPaymentTail.empId"].value=format(document.chgslarybaseNewAF.elements["chgPaymentTail.empId"].value)+document.chgslarybaseNewAF.elements["chgPaymentTail.empId"].value;
	   }
}

function backErrors(errors){
   alert(errors);
}
/*查询使用*/
function toPop(status){
    var orgId=document.chgslarybaseNewAF.elements["orgId"].value;
	open_findEmp('<%=request.getContextPath()%>',orgId,'134','null','null','0');
}
function executeAjax(){
	var empid=document.chgslarybaseNewAF.elements["chgPaymentTail.empId"].value;
    if(empid==""){
    var orgId=document.chgslarybaseNewAF.elements["orgId"].value;
	open_findEmp('<%=request.getContextPath()%>',orgId,'134','null','null','0');
	   return false;
	}
    var queryString = "chgslarybaseEmpTaFindAAC.do?";    
    queryString = queryString + "chgPaymentTail.empId="+empid;
    findInfo(queryString);
}
function findEmployeeInfo(){
	var empid=document.chgslarybaseNewAF.elements["chgPaymentTail.empId"].value;
     if(empid==""){
	    var orgId=document.chgslarybaseNewAF.elements["orgId"].value;
	open_findEmp('<%=request.getContextPath()%>',orgId,'134','null','null','0');
	   return false;
	}
    var queryString = "chgslarybaseEmpTaFindAAC.do?";    
    queryString = queryString + "chgPaymentTail.empId="+empid;
     findInfo(queryString);
} 
function getEnter()
{
   if(event.keyCode==13)
   {
    event.keyCode=9;
   	findEmployeeInfo();
  }
}
function displays(employee_name,card_kind,card_number,birthday,sex,oldorgpay,pldemppay,oldsalarybase) {
document.chgslarybaseNewAF.elements["chgPaymentTail.empId"].value=format(document.chgslarybaseNewAF.elements["chgPaymentTail.empId"].value)+document.chgslarybaseNewAF.elements["chgPaymentTail.empId"].value;
	document.chgslarybaseNewAF.elements["chgPaymentTail.emp.empInfo.name"].value=employee_name;
    document.chgslarybaseNewAF.elements["chgPaymentTail.emp.empInfo.cardKind"].value=card_kind;
	document.chgslarybaseNewAF.elements["chgPaymentTail.emp.empInfo.cardNum"].value=card_number;
    document.chgslarybaseNewAF.elements["chgPaymentTail.emp.empInfo.birthday"].value=birthday;
	document.chgslarybaseNewAF.elements["chgPaymentTail.emp.empInfo.sex"].value=sex;
	document.chgslarybaseNewAF.elements["chgPaymentTail.oldOrgPay"].value=oldorgpay;
	document.chgslarybaseNewAF.elements["chgPaymentTail.oldEmpPay"].value=pldemppay;
	document.chgslarybaseNewAF.elements["chgPaymentTail.oldSalaryBase"].value=oldsalarybase;
    document.chgslarybaseNewAF.elements["chgPaymentTail.salaryBase"].focus();
}

function displays2(orgPay,empPay) {
	document.chgslarybaseNewAF.elements["chgPaymentTail.orgPay"].value=orgPay;
	document.chgslarybaseNewAF.elements["chgPaymentTail.empPay"].value=empPay;
    document.chgslarybaseNewAF.elements["chgPaymentTail.orgPay"].focus();
}



/*焦点离开时使用*/
function mathsalaryBasee() {
   var salaryBase=document.chgslarybaseNewAF.elements["chgPaymentTail.salaryBase"].value;
   if(checkMoney(salaryBase)==false){
   return false;
   }
   var orgRate=document.chgslarybaseNewAF.elements["orgRate"].value;
   var empRate=document.chgslarybaseNewAF.elements["empRate"].value;
   var payPrecision=document.chgslarybaseNewAF.elements["payPrecision"].value;
   var queryString = "chgslarybaseTaCountAAC.do?";    
    queryString = queryString + "salaryBase="+salaryBase+'&orgRate='+orgRate+'&empRate='+empRate+'&payPrecision='+payPrecision;
     findInfo(queryString);
}
function mathsalaryBasee2() {
   var orgPay=document.chgslarybaseNewAF.elements["chgPaymentTail.orgPay"].value;
   if(checkMoney(orgPay)==false){
   return false;
   }
   document.chgslarybaseNewAF.elements["chgPaymentTail.empPay"].focus();
}
function mathsalaryBasee3() {
   var empPay=document.chgslarybaseNewAF.elements["chgPaymentTail.empPay"].value;
   if(checkMoney(empPay)==false){
   return false;
   }
}
/*回车时使用*/
function mathsalaryBase() {
 if(event.keyCode==13){
	event.keyCode=9;
	mathsalaryBasee();
 }
}
function mathsalaryBase2() {
if(event.keyCode==13){
	event.keyCode=9;
   mathsalaryBasee2();
   }
}
function mathsalaryBase3() {
   if(event.keyCode==13){
	event.keyCode=9;
   mathsalaryBasee3();
   }
}
function checkData() {
	var empid=document.chgslarybaseNewAF.elements["chgPaymentTail.empId"].value;
    if(empid==""){
	   alert("请录入职工编号");
	   return false;
	}
	var salaryBase=document.chgslarybaseNewAF.elements["chgPaymentTail.salaryBase"].value;
    if(checkMoney(salaryBase)==false){
       return false;
    }
    if(salaryBase=="" || salaryBase<0){
	   alert("新工资基数是否填入，新工资基数要〉=0");
	   return false;
	}
	var orgPay=document.chgslarybaseNewAF.elements["chgPaymentTail.orgPay"].value;
	if(checkMoney(orgPay)==false){
       return false;
    }
    if(orgPay=="" || orgPay<0){
	   alert("新单位缴额是否填入，新单位缴额要〉=0");
	   return false;
	}	   
	var empPay=document.chgslarybaseNewAF.elements["chgPaymentTail.empPay"].value;
	if(checkMoney(empPay)==false){
       return false;
    }
    if(empPay=="" || empPay<0){
	   alert("新职工缴额是否填入，新职工缴额要〉=0");
	   return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()" onContextmenu="return false">
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=request.getContextPath()%>/img/table_left.gif"
								width="7" height="37">
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							width="55">
							&nbsp;
						</td>
						<td width="235"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif">
							&nbsp;
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td width="216" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB"> 变更业务&gt;工资基数调整 </font>
									</td>
									<td width="47" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=request.getContextPath()%>/img/table_right.gif"
								width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<html:form action="/chgslarybaseTaSaveAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">基 本 信 息</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
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
								职工编号
							</td>
							<logic:equal name="chgslarybaseNewAF" property="type" value="1">
								<td width="22%">
									<html:text name="chgslarybaseNewAF"
										property="chgPaymentTail.empId" styleClass="input3"
										onkeydown="getEnter()" ondblclick="executeAjax();"
										styleId="txtsearch"></html:text>
								</td>
								<td width="11%">
									<input type="button" name="Submit3222" value="..."
										class="buttona" onclick="toPop(2)">
								</td>
							</logic:equal>
							<logic:equal name="chgslarybaseNewAF" property="type" value="2">
								<td width="22%">
									<html:text name="chgslarybaseNewAF"
										property="chgPaymentTail.empId" styleClass="input3"
										readonly="true" styleId="txtsearch"></html:text>

								</td>
								<td width="11%">
								</td>
							</logic:equal>
							<td width="17%" class="td1">
								职工姓名
							</td>
							<td width="33%" colspan="2">
								<html:text name="chgslarybaseNewAF"
									property="chgPaymentTail.emp.empInfo.name" readonly="true"
									styleClass="input3" />
								<html:hidden name="chgslarybaseNewAF" property="orgRate"
									styleClass="input3" styleId="txtsearch"></html:hidden>
								<html:hidden name="chgslarybaseNewAF" property="empRate"
									styleClass="input3" styleId="txtsearch"></html:hidden>
								<html:hidden name="chgslarybaseNewAF" property="payPrecision"
									styleClass="input3" styleId="txtsearch"></html:hidden>
								<html:hidden name="chgslarybaseNewAF" property="orgId"
									styleClass="input3" styleId="txtsearch"></html:hidden>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								证件类型
								 
							</td>
							<td width="33%" colspan="2">
								<html:select name="chgslarybaseNewAF"
									property="chgPaymentTail.emp.empInfo.cardKind" styleClass="input4">
									<html:optionsCollection property="documentsstateMap"
										name="chgslarybaseNewAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								证件号码
							 
							</td>
							<td width="33%">
								<html:text name="chgslarybaseNewAF"
									property="chgPaymentTail.emp.empInfo.cardNum" readonly="true"
									styleClass="input3" />
							</td>
						</tr>

						<tr>
							<td width="17%" class="td1"> 
								&#20986;&#29983;&#26085;&#26399;	<br></td>
							<td width="33%" colspan="2">
								<html:text name="chgslarybaseNewAF"
									property="chgPaymentTail.emp.empInfo.birthday" readonly="true"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								性别
						 
							</td>
							<td width="33%">
								<html:select name="chgslarybaseNewAF"
									property="chgPaymentTail.emp.empInfo.sex" styleClass="input4">
									<html:optionsCollection property="sexMap"
										name="chgslarybaseNewAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								原单位缴额
						 
							</td>
							<td width="33%" colspan="2">
								<html:text name="chgslarybaseNewAF"
									property="chgPaymentTail.oldOrgPay" readonly="true"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								原职工缴额
							 
							</td>
							<td width="33%">
								<html:text name="chgslarybaseNewAF"
									property="chgPaymentTail.oldEmpPay" readonly="true"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								原工资基数
						 
							</td>
							<td width="33%" colspan="2">
								<html:text name="chgslarybaseNewAF"
									property="chgPaymentTail.oldSalaryBase" readonly="true"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								新工资基数
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="chgslarybaseNewAF"
									property="chgPaymentTail.salaryBase" onkeydown="mathsalaryBase()" onblur="mathsalaryBasee()"
									styleClass="input3" maxlength="10" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								新单位缴额		<font color="#FF0000">*</font>
							</td>
							<td width="33%" colspan="2">
								<html:text name="chgslarybaseNewAF"
									property="chgPaymentTail.orgPay"  onkeydown="mathsalaryBase2()"  styleClass="input3"  maxlength="13"  />
							</td>
							<td width="17%" class="td1">
								新职工缴额		<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="chgslarybaseNewAF"
									property="chgPaymentTail.empPay" onkeydown="mathsalaryBase3()" styleClass="input3"
									styleId="txtsearch"  maxlength="13" />
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
											<html:hidden name="chgslarybaseNewAF"
												property="chgPaymentTail.id" />
										</td>
										<td>
											<logic:equal name="chgslarybaseNewAF" property="type"
												value="1">
												<html:submit property="method" styleClass="buttona"
													onclick="return checkData()">
													<bean:message key="button.add" />
												</html:submit>
											</logic:equal>
										</td>
										<td>
											<logic:equal name="chgslarybaseNewAF" property="type"
												value="2">
												<html:submit property="method" styleClass="buttona"
													onclick="return checkData()">
													<bean:message key="button.update" />
												</html:submit>
											</logic:equal>
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
</body>
</html:html>
