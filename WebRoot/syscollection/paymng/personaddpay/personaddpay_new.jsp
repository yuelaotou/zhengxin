<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			PersonAddPayTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String type = request.getParameter("type");
%>
<html:html>
<head>
	<title>个人补缴</title>
	<link rel="stylesheet" href="../../../css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">

</head>
<script language="javascript"></script>

<script language="javascript" type="text/javascript">


function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>	
}
  
function toPop(status){
    var orgId=document.chgslarybaseNewAF.elements["orgId"].value;
	open_findEmp('<%=path%>',orgId,'1','null','null');
}
function executeAjax()
  {
    var empid=document.personAddPayAF.elements["empId"].value;
     if(empid==""){
	   alert("请录入职工编号");
	   return false;
	}
    var queryString = "personaddpayEmpTaFindAAC.do?";    
     queryString = queryString + "empid="+empid;
     findInfo(queryString);
  }
  
function getEnter()
{
   if(event.keyCode==13)
   {
    event.keyCode=9
    var empid=document.personAddPayAF.elements["empId"].value;
    if(empid==""){	 
	document.personAddPayAF.elements["addPayTail.emp.empInfo.name"].value="";
    document.personAddPayAF.elements["addPayTail.emp.empInfo.TEMP_cardNum"].value="";
	document.personAddPayAF.elements["addPayTail.emp.empInfo.cardNum"].value="";
    document.personAddPayAF.elements["addPayTail.emp.empInfo.birthday"].value="";
	document.personAddPayAF.elements["sex"].value="";
	document.personAddPayAF.elements["addPayTail.emp.salaryBase"].value=0;
	document.personAddPayAF.elements["addPayTail.emp.orgPay"].value=0;
	document.personAddPayAF.elements["addPayTail.emp.empPay"].value=0;
    document.personAddPayAF.elements["addPayTail.emp.paySum"].value=0;   
    document.personAddPayAF.elements["empId"].focus();
    alert("请录入职工编号");   
	return false;	
	}
    var queryString = "personaddpayEmpTaFindAAC.do?";    
    queryString = queryString + "empid="+empid;
     findInfo(queryString);
  }
}
function displays(empId,empname,card_kind,card_number,birthday,sex,salarybase,orgpay,emppay,total) {
    
    if(empname==""){
    document.personAddPayAF.elements["empId"].focus();
    document.personAddPayAF.elements["addPayTail.emp.empInfo.name"].value="";
    document.personAddPayAF.elements["addPayTail.emp.empInfo.TEMP_cardNum"].value="";
	document.personAddPayAF.elements["addPayTail.emp.empInfo.cardNum"].value="";
    document.personAddPayAF.elements["addPayTail.emp.empInfo.birthday"].value="";
	document.personAddPayAF.elements["sex"].value="";
	document.personAddPayAF.elements["addPayTail.emp.salaryBase"].value=0;
	document.personAddPayAF.elements["addPayTail.emp.orgPay"].value=0;
	document.personAddPayAF.elements["addPayTail.emp.empPay"].value=0;
    document.personAddPayAF.elements["addPayTail.emp.paySum"].value=0;
    alert("对不起，不存在该职工");  
    }else{
    document.personAddPayAF.elements["empId"].value=empId;
    document.personAddPayAF.elements["addPayTail.emp.empInfo.name"].value=empname;
    document.personAddPayAF.elements["addPayTail.emp.empInfo.TEMP_cardNum"].value=card_kind;
	document.personAddPayAF.elements["addPayTail.emp.empInfo.cardNum"].value=card_number;
    document.personAddPayAF.elements["addPayTail.emp.empInfo.birthday"].value=birthday;
	document.personAddPayAF.elements["sex"].value=sex;
	document.personAddPayAF.elements["addPayTail.emp.salaryBase"].value=salarybase;
	document.personAddPayAF.elements["addPayTail.emp.orgPay"].value=orgpay;
	document.personAddPayAF.elements["addPayTail.emp.empPay"].value=emppay;
    document.personAddPayAF.elements["addPayTail.emp.paySum"].value=total;   
    document.personAddPayAF.elements["addPayTail.orgAddPaySum"].focus();
    }
}


function empAddPay(){
document.getElementById("type").value="add";
}

function empAddPayUpdate(){
document.getElementById("type").value="update";
}

function submitConfirm()
{
 var oldSalary=document.forms[0].elements["empPaymentChangement.empPaymentAgreement.salaryBase"].value;
 var newSalary=document.forms[0].elements["empPaymentChangement.salarybase"].value;
 var oldUnit=document.forms[0].elements["empPaymentChangement.empPaymentAgreement.sumPayableOfOrg"].value;
 var newUnit=document.forms[0].elements["empPaymentChangement.sumPayableOfOrg"].value;
 var oldEmp=document.forms[0].elements["empPaymentChangement.empPaymentAgreement.sumPayableOfEmp"].value;
 var newEmp=document.forms[0].elements["empPaymentChangement.sumPayableOfEmp"].value;
}

function save() { 
var addPayType=document.personAddPayAF.elements["addPayType"].value;
 if(addPayType == ""){
   alert("请选择类型！");
   return false;
 }
	var empId=document.personAddPayAF.elements["empId"].value;
	var emp_Name=document.personAddPayAF.elements["addPayTail.emp.empInfo.name"].value;
  var tempMonth1=document.personAddPayAF.elements["addPayTail.beginMonth"].value;
  var tempMonth2=document.personAddPayAF.elements["addPayTail.endMonth"].value;
  var orgPay=document.personAddPayAF.elements["addPayTail.orgAddPaySum"].value;
  var empPay=document.personAddPayAF.elements["addPayTail.empAddPaySum"].value;
  var orgPayMoney=parseFloat(orgPay)*100;
  var empPayMoney=parseFloat(empPay)*100;
  var beginYear=tempMonth1.substr(0,4);
  var endYear=tempMonth2.substr(0,4);
  var beginMonth=tempMonth1.substr(4,6);
  var endMonth=tempMonth2.substr(4,6);
  var months=(parseInt(endYear)-parseInt(beginYear))*12+parseInt(endMonth)-parseInt(beginMonth)+1;
  if(empId==''||emp_Name==''){
  	alert("请选择职工！");
  	return false;
  }
  if(tempMonth1.length!=6)
  {
    alert("请输入六位的年月，格式为200410！");
    document.personAddPayAF.elements["addPayTail.beginMonth"].value="";
    document.personAddPayAF.elements["addPayTail.beginMonth"].focus();
    return false;
  }
  if(tempMonth2.length!=6)
  {
    alert("请输入六位的年月，格式为200410！");
    document.personAddPayAF.elements["addPayTail.endMonth"].value="";
    document.personAddPayAF.elements["addPayTail.endMonth"].focus();
    return false;
  }
  if(tempMonth1.substring(0,4)<1900){
    alert("年份应该大于1900！");
    document.personAddPayAF.elements["addPayTail.beginMonth"].value="";
    document.personAddPayAF.elements["addPayTail.beginMonth"].focus();
    return false;
  }
  if(tempMonth2.substring(0,4)<1900){
    alert("年份应该大于1900！");
     document.personAddPayAF.elements["addPayTail.endMonth"].value="";
     document.personAddPayAF.elements["addPayTail.endMonth"].focus();
    return false;
  }
  if(tempMonth1.substring(4,6)>12 || tempMonth1.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    document.personAddPayAF.elements["addPayTail.beginMonth"].value="";
    document.personAddPayAF.elements["addPayTail.beginMonth"].focus();
    return false;
  }
  if(tempMonth2.substring(4,6)>12 || tempMonth2.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    document.personAddPayAF.elements["addPayTail.endMonth"].value="";
    document.personAddPayAF.elements["addPayTail.endMonth"].focus();
    return false;
  }
  if(tempMonth1>tempMonth2)
  {
    alert("起始年月应该不大于终止年月！");
    document.personAddPayAF.elements["addPayTail.endMonth"].value="";
    document.personAddPayAF.elements["addPayTail.endMonth"].focus();
    return false;
  }
  //if(orgPayMoney%months!=0){
    //alert("输入的单位补缴金额不能被整除");
    //return false;
  //}
   //if(empPayMoney%months!=0){
    //alert("输入的个人补缴金额不能被整除");
    //return false;
  //}
    return true;
 document.forms[0].method.value="save";
 if(confirm("是否继续录入职工补缴信息"))
 {
   document.getElementById("insertnext").value="next";   
 }else{
   document.getElementById("insertnext").value="stop"; 
 }
}
function modify() {
  document.forms[0].method.value="modify";
  return submitConfirm();
}
function cancle() {
  document.URL='personAddPayTaShowAC.do';
}

function validateMonth()
{
  var tempMonth=document.personAddPayAF.elements["addPayTail.beginMonth"].value; 
  if(tempMonth.length!=6)
  {
    alert("请输入六位的年月，格式为200410！");
    tempMonth.value="";
    document.personAddPayAF.elements["addPayTail.beginMonth"].focus();
    return false;
  }
  if(tempMonth.substring(0,4)<1900){
    alert("年份应该大于1900！");
    tempMonth.value="";
    document.personAddPayAF.elements["addPayTail.beginMonth"].focus();
    return false;
  }
  if(tempMonth.substring(4,6)>12 || tempMonth.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    tempMonth.value="";
    document.personAddPayAF.elements["addPayTail.beginMonth"].focus();
    return false;
  }

  return true;
}
function validateTimes()
{

  var tempMonth1=document.personAddPayAF.elements["addPayTail.beginMonth"].value;
  var tempMonth2=document.personAddPayAF.elements["addPayTail.endMonth"].value;
  var orgPay=document.personAddPayAF.elements["addPayTail.orgAddPaySum"].value;
  var empPay=document.personAddPayAF.elements["addPayTail.empAddPaySum"].value;
  var orgPayMoney=parseFloat(orgPay)*100;
  var empPayMoney=parseFloat(empPay)*100;
  var beginYear=tempMonth1.substr(0,4);
  var endYear=tempMonth2.substr(0,4);
  var beginMonth=tempMonth1.substr(4,6);
  var endMonth=tempMonth2.substr(4,6);
  var months=(parseInt(endYear)-parseInt(beginYear))*12+parseInt(endMonth)-parseInt(beginMonth)+1;
  if(tempMonth1.length!=6)
  {
    alert("请输入六位的年月，格式为200410！");
    document.personAddPayAF.elements["addPayTail.beginMonth"].value="";
    document.personAddPayAF.elements["addPayTail.beginMonth"].focus();
    return false;
  }
  if(tempMonth2.length!=6)
  {
    alert("请输入六位的年月，格式为200410！");
    document.personAddPayAF.elements["addPayTail.endMonth"].value="";
    document.personAddPayAF.elements["addPayTail.endMonth"].focus();
    return false;
  }
  if(tempMonth1.substring(0,4)<1900){
    alert("年份应该大于1900！");
    document.personAddPayAF.elements["addPayTail.beginMonth"].value="";
    document.personAddPayAF.elements["addPayTail.beginMonth"].focus();
    return false;
  }
  if(tempMonth2.substring(0,4)<1900){
    alert("年份应该大于1900！");
     document.personAddPayAF.elements["addPayTail.endMonth"].value="";
     document.personAddPayAF.elements["addPayTail.endMonth"].focus();
    return false;
  }
  if(tempMonth1.substring(4,6)>12 || tempMonth1.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    document.personAddPayAF.elements["addPayTail.beginMonth"].value="";
    document.personAddPayAF.elements["addPayTail.beginMonth"].focus();
    return false;
  }
  if(tempMonth2.substring(4,6)>12 || tempMonth2.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    document.personAddPayAF.elements["addPayTail.endMonth"].value="";
    document.personAddPayAF.elements["addPayTail.endMonth"].focus();
    return false;
  }
  if(tempMonth1>tempMonth2)
  {
    alert("起始年月应该不大于终止年月！");
    document.personAddPayAF.elements["addPayTail.endMonth"].value="";
    document.personAddPayAF.elements["addPayTail.endMonth"].focus();
    return false;
  }
  //if(orgPayMoney%months!=0){
    //alert("输入的单位补缴金额不能被整除");
   // return false;
  //}
   //if(empPayMoney%months!=0){
   // alert("输入的个人补缴金额不能被整除");
   // return false;
  //}
    return true;
}
function getPaySum()
{
  
  var orgPay=document.personAddPayAF.elements["addPayTail.orgAddPaySum"].value;
  var empPay=document.personAddPayAF.elements["addPayTail.empAddPaySum"].value;
  if(checkMoney(orgPay)&&checkMoney(empPay))
  {
    var paySum=parseFloat(orgPay)+parseFloat(empPay);
    document.personAddPayAF.elements["paySum"].value=paySum;
  }
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();"
	onContextmenu="return false">

	<html:form action="/personAddPayTaAddAC.do">
		<input type="hidden" name="insertnext" id="insertnext" value="" />
		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="../../../img/table_left.gif" width="7" height="37">
							</td>
							<td background="../../../img/table_bg_line.gif" width="55">
								&nbsp;
							</td>

							<td background="../../../img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="../../../img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">缴存管理&gt;个人补缴</font>
										</td>
										<td width="74" class=font14>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="9">
								<img src="<%=request.getContextPath()%>/img/table_right.gif"
									width="10" height="37">
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14"> 职 工 信 息</b>
										</td>
										<td width="750" height="22" align="center"
											background="../../../img/bg2.gif">
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
							<td width="13%" class="td1">
								职工编号
							</td>
							<td width="25%">
								<html:text name="personAddPayAF" property="empId"
									styleClass="input3" styleId="empId" onkeydown="getEnter()"
									ondblclick="executeAjax();" />
							<td width="12%">
								<logic:equal name="personAddPayAF" property="type" value="add">

									<input type="button" class="buttona" value="..."
										onClick="open_findEmp('<%=path%>','<bean:write name='personAddPayAF' property='orgId'/>',null,null,null,'0');" />

								</logic:equal>
							</td>
							<td width="13%" class="td1">
								职工姓名
							</td>
							<td colspan="2">
								<html:text name="personAddPayAF"
									property="addPayTail.emp.empInfo.name" styleClass="input3"
									styleId="empName" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								证件类型
							</td>
							<td colspan="2">
								<html:text name="personAddPayAF"
									property="addPayTail.emp.empInfo.TEMP_cardNum"
									styleClass="input3" styleId="cardType" readonly="true" />
							</td>
							<td width="13%" class="td1">
								证件号码
							</td>
							<td width="37%">
								<html:text name="personAddPayAF"
									property="addPayTail.emp.empInfo.cardNum" styleClass="input3"
									styleId="cardNumber" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								出生日期
							</td>
							<td colspan="2">
								<html:text name="personAddPayAF"
									property="addPayTail.emp.empInfo.birthday" styleClass="input3"
									styleId="birthday" readonly="true" />
							</td>
							<td width="13%" class="td1">
								性别
							</td>
							<td width="37%">
								<html:text name="personAddPayAF" property="sex"
									styleClass="input3" styleId="sex" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								工资基数
							</td>
							<td width="18%" colspan="2">
								<html:text name="personAddPayAF"
									property="addPayTail.emp.salaryBase" styleClass="input3"
									styleId="salaryBase" readonly="true" />
							</td>
							<td width="13%" class="td1">
								单位缴额
							</td>
							<td width="37%">
								<html:text name="personAddPayAF"
									property="addPayTail.emp.orgPay" styleClass="input3"
									styleId="orgPayment" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								职工缴额
							</td>
							<td width="18%" colspan="2">
								<html:text name="personAddPayAF"
									property="addPayTail.emp.empPay" styleClass="input3"
									styleId="empPayment" readonly="true" />
							</td>
							<td width="13%" class="td1">
								缴额合计
							</td>
							<td width="18%" colspan="2">
								<html:text name="personAddPayAF"
									property="addPayTail.emp.paySum" styleClass="input3"
									styleId="totalPayment" readonly="true" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14"> 补 缴 信 息</b>
										</td>
										<td width="750" height="22" align="center"
											background="../../../img/bg2.gif">
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
							<td class="td1" width="13%">
								单位补缴金额
								<font color="#FF0000">*</font>
							</td>
							<td colspan="2">
								<html:text name="personAddPayAF"
									property="addPayTail.orgAddPaySum" styleClass="input3"
									styleId="orgAddPay" onkeydown="enterNextFocus1();"
									onblur="getPaySum();" maxlength="18" />
							</td>
							<td class="td1" width="13%">
								个人补缴金额
								<font color="#FF0000">*</font>
							</td>
							<td width="37%">
								<html:text name="personAddPayAF"
									property="addPayTail.empAddPaySum" styleClass="input3"
									styleId="empAddPay" onkeydown="enterNextFocus1();"
									onblur="getPaySum();" maxlength="18" />
							</td>
						</tr>
						<tr>
							<td class="td1" width="13%">
								补缴起始年月
								<font color="#FF0000">*</font>
							</td>
							<td colspan="2">
								<html:text name="personAddPayAF"
									property="addPayTail.beginMonth" maxlength="6"
									onkeydown="enterNextFocus1();" styleClass="input3"
									styleId="beginMonth" onkeydown="enterNextFocus1();" />
							<td class="td1" width="13%">
								补缴终止年月
								<font color="#FF0000">*</font>
							</td>
							<td width="37%">
								<html:text name="personAddPayAF" property="addPayTail.endMonth"
									maxlength="6" onkeydown="enterNextFocus1();"
									styleClass="input3" styleId="endMonth"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td class="td1" width="13%">
								补缴原因
							</td>
							<td colspan="2">
								<html:text name="personAddPayAF" property="addPayTail.addReason"
									onkeydown="enterNextFocus1();" styleClass="input3"
									styleId="addPayReason" maxlength="25" />
							</td>
							<html:hidden name="personAddPayAF" property="payWay" />
							<html:hidden name="personAddPayAF" property="payKind" />
							<html:hidden name="personAddPayAF" property="payment_orgname" />
							<td class="td1" width="13%">
								补缴金额合计
								<font color="#FF0000">*</font>
							</td>
							<td width="37%">
								<html:text name="personAddPayAF" property="paySum"
									onkeydown="enterNextFocus1();" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="td1" width="13%">
								补缴类型
								<font color="#FF0000">*</font>
							</td>
							<td colspan="2">
								<html:select name="personAddPayAF" property="addPayType"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="addPayTypeMap"
										name="personAddPayAF" label="value" value="key" />
								</html:select>
							</td>
							<td class="td1" width="13%">
								&nbsp;
							</td>
							<td width="37%">
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<logic:equal name="personAddPayAF" property="type"
												value="add">
												<html:submit property="method" styleClass="buttona"
													onclick="return save();">
													<bean:message key="button.sure" />
												</html:submit>
											</logic:equal>
											<logic:equal name="personAddPayAF" property="type"
												value="update">
												<html:submit property="method" styleClass="buttona"
													onclick="return validateTimes();">
													<bean:message key="button.update" />
												</html:submit>
											</logic:equal>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.back" />
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

