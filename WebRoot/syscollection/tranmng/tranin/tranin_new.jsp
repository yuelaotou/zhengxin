<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="java.math.BigDecimal"%>
<%
String path = request.getContextPath();
	String flag = "0";
   if(request.getAttribute("flag")!=null){
   	flag = (String)request.getAttribute("flag");
   }
%>

<html:html>
<html:base />
<head>
	<title>tranin添加</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	
	<script language="javascript"></script>
	<script language="javascript" type="text/javascript">
function checkMoney_monthIncome(){//判断录入的金额是否正确
      var salary=document.forms[0].elements["tranInTail.monthIncome"].value.trim();
      var salarybase = salary.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/); 
      	if (salarybase==null)
			{
        		alert("请录入月收入！格式如：1087.23");
        		document.forms[0].elements["tranInTail.monthIncome"].focus();
				return false;
			}else{
				return true;
			}
}
function checkMoney_curInterest(){//判断录入的金额是否正确
      var salary=document.forms[0].elements["tranInTail.curInterest"].value.trim();
      var salarybase = salary.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/); 
      	if (salarybase==null)
			{
        		alert("请录入利息！");
        		document.forms[0].elements["tranInTail.curInterest"].focus();
				return false;
			}else{
				return true;
			}
}
function checkMoney_curBalance(){//判断录入的金额是否正确
      var salary=document.forms[0].elements["tranInTail.curBalance"].value.trim();
      var salarybase = salary.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/); 
      	if (salarybase==null)
			{
        		alert("请录入本年余额！格式如：1087.23");
        		document.forms[0].elements["tranInTail.curBalance"].focus();
				return false;
			}else{
				return true;
			}
}
function checkMoney_preBalance(){//判断录入的金额是否正确
      var salary=document.forms[0].elements["tranInTail.preBalance"].value.trim();
      var salarybase = salary.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/); 
      	if (salarybase==null)
			{
        		alert("请录入往年余额！格式如：1087.23");
        		document.forms[0].elements["tranInTail.preBalance"].focus();
				return false;
			}else{
				return true;
			}
}

function checkMoney_orgPay(){//判断录入的金额是否正确
      var orgPay=document.forms[0].elements["tranInTail.orgPay"].value.trim();
      var orgAmount = orgPay.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/); 
      	if (orgAmount==null)
			{
        		alert("请录入单位缴额！格式如：1087.23");
        		document.forms[0].elements["tranInTail.orgPay"].focus();
				return false;
			}else{
				return true;
			}
}

function checkMoney_empPay(){//判断录入的金额是否正确
      var empPay=document.forms[0].elements["tranInTail.empPay"].value.trim();
      var empAmount = empPay.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/); 
      	if (empAmount==null)
			{
        		alert("请录入职工缴额！格式如：1087.23");
        		document.forms[0].elements["tranInTail.empPay"].focus();
				return false;
			}else{
				return true;
			}
}
function checkBirthday(){
    var birthday=document.forms[0].elements["tranInTail.birthday"].value.trim();
    var birthday_ = birthday.match(/^([0-9]{8})$/); 
    if(birthday_==null){
    	alert("请正确录入出生日期信息！格式如：19560502");
    	document.forms[0].elements["tranInTail.birthday"].focus();
    	return false;
    }
}
function  checkCardNum(){
var num=document.forms[0].elements["tranInTail.cardNum"].value;
var numType=document.forms[0].elements["tranInTail.cardKind"].value;

	if(numType==0){
		if(!isIdCardNo(num)){
			document.forms[0].elements["tranInTail.cardNum"].focus();
			return false;
		}else{
			if(num.length==15){
				var date ="19"+num.substr(6,6);
				document.forms[0].elements["tranInTail.birthday"].value=date;
				var sex = num.substr(14);
				if(sex%2==0||sex=='X'){
					document.forms[0].elements["traninTailsex"].value='2'
				}else{
					document.forms[0].elements["traninTailsex"].value='1'
				}
			}else{
				var date=num.substr(6,8)
				document.forms[0].elements["tranInTail.birthday"].value=date;
				var sex = num.substr(16,1);
				if(sex%2==0||sex=='X'){
					document.forms[0].elements["traninTailsex"].value='2'
				}else{
					document.forms[0].elements["traninTailsex"].value='1'
				}
			}
			return true;
		}
      }
}
function checkData(){
    var num=document.forms[0].elements["tranInTail.cardNum"].value;
	var numType=document.forms[0].elements["tranInTail.cardKind"].value;
	if(document.traninAddAF.elements["tranInTail.name"].value.trim()==""){
	   alert("姓名不能为空!");
	   return false;
	}else if(document.traninAddAF.elements["tranInTail.cardKind"].value.trim()==""){
	   alert("请选择证件类型!");
	   return false;
	}else if(document.traninAddAF.elements["tranInTail.salaryBase"].value.trim()=="" || document.traninAddAF.elements["tranInTail.salaryBase"].value.trim()=="0"){
	   alert("请添写工资基数!");
	   return false;
	}
	else if(document.traninAddAF.elements["tranInTail.cardNum"].value.trim()==""){
	   alert("请填写证件号码!");
	   return false;
	}else if(document.traninAddAF.elements["traninTailsex"].value.trim()==""){
	   alert("请选择性别!");
	   return false;
	}else if(document.traninAddAF.elements["tranInTail.cardNum"].value.trim()==""){
	   alert("输入身份证号码!");
	   return false;
	}
}
function mathsalaryBase() {
var salary=document.forms[0].elements["tranInTail.salaryBase"].value.trim();
var salarybase = salary.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/); 
var salaryBase=document.traninAddAF.elements["tranInTail.salaryBase"].value.trim()
var empRate=document.traninAddAF.elements["empRate"].value.trim();
var orgRate=document.traninAddAF.elements["orgRate"].value.trim();
var payPrecision=document.traninAddAF.elements["payPrecision"].value;
if (salarybase==null)
			{
        		alert("请录入工资基数！格式如：1087.23");
        		document.forms[0].elements["tranInTail.salaryBase"].focus();
				return false;
			}else{
   var queryString = "traninBaseTaCountAAC.do?";    
   queryString = queryString + "salaryBase="+salaryBase+'&orgRate='+orgRate+'&empRate='+empRate+'&payPrecision='+payPrecision;
     findInfo(queryString);
			}
  
}
function displays2(orgPay,empPay) {
	document.traninAddAF.elements["tranInTail.orgPay"].value=orgPay;
	document.traninAddAF.elements["tranInTail.empPay"].value=empPay;
    document.traninAddAF.elements["tranInTail.empPay"].focus();
}
function reportErrors() {
<logic:messagesPresent>
var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
alert(message);
</logic:messagesPresent>
}
function init(){
	if(<%=flag%>==1){
		var x=confirm("系统中存在与该用户相同身份证号不同姓名的职工，是否继续添加?");
		if(x){    
			document.forms[0].action="traninSaveAC.do";
			document.forms[0].submit();
			return true;
		}else{
		  	return false;
		}
	}else if(<%=flag%>==2){
		var x=confirm("系统中存在与该用户相同身份证号不同姓名的职工，是否继续添加?");
		if(x){    
			document.forms[0].action="traninUpdateAC.do";
			document.forms[0].submit();
			return true;
		}else{
		  	return false;
		}
	}
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" onload="init();return reportErrors();" onContextmenu="return false">
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
										<font color="00B5DB">转入登记</font><font color="00B5DB">&gt;&gt;</font><font color="00B5DB">
										<logic:equal name="traninAddAF" property="type" value="1">添加</logic:equal>
										<logic:equal name="traninAddAF" property="type" value="2">修改</logic:equal>
									   </font>
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
				<html:form action="/addTraninMaintainAC.do" style="margin: 0" focus="tranInTail.name">
					<html:hidden name="traninAddAF" property="tranInTail.id"></html:hidden>
					<html:hidden name="traninAddAF" property="inOrgId"></html:hidden>
					<html:hidden name="traninAddAF" property="tranInHeadId"></html:hidden>
					<html:hidden name="traninAddAF" property="tranInTail.empId"></html:hidden>
					<html:hidden name="traninAddAF" property="empRate"></html:hidden>
					<html:hidden name="traninAddAF" property="orgRate"></html:hidden>
					<html:hidden name="traninAddAF" property="noteNum"></html:hidden>
					<html:hidden name="traninAddAF" property="payPrecision"></html:hidden>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">基 本 信 息</b>
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
								姓名
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.name"
									styleClass="input3" onkeydown="enterNextFocus1();"/>
							</td>
							<td width="17%" class="td1">
								证件类型
								<font color="#FF0000">*</font>
							</td>
							<td width="33%" styleClass="input3" styleClass="input3" >
								<html:select property="tranInTail.cardKind" onkeydown="enterNextFocus1();" styleClass="input4">
									<html:optionsCollection property="documentsstateMap"
										name="traninAddAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" styleClass="input3">
								证件号码
								<font color="#FF0000">*</font>
							</td>
							<td width="33%" styleClass="input3">
								<html:text name="traninAddAF" property="tranInTail.cardNum"
									styleClass="input3" onkeydown="enterNextFocus1();" onblur="return checkCardNum()"/>
							</td>
							<td width="17%" class="td1">
								出生日期
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.birthday"
									styleClass="input3" onkeydown="enterNextFocus1();"/>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" styleClass="input3">
								性别
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:select name="traninAddAF" property="traninTailsex" onkeydown="enterNextFocus1();" styleClass="input4">
								<html:option value=""></html:option>
									<html:optionsCollection property="sexMap" name="traninAddAF"
										label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								家庭电话
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.tel"
									styleClass="input3" onkeydown="goEnter()"/>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								移动电话
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.mobileTel"
									styleClass="input3" onkeydown="enterNextFocus1();"/>
							</td>
							<td width="17%" class="td1">
								工资基数
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.salaryBase"
									onblur="return mathsalaryBase()"
									maxlength="20" styleClass="input3" onkeydown="enterNextFocus1();"/>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位缴额
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.orgPay"
									onblur="return checkMoney_orgPay()" styleClass="input3" onkeydown="enterNextFocus1();"/>
							</td>
							<td width="17%" class="td1">
								职工缴额
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.empPay"
									onblur="return checkMoney_empPay()" styleClass="input3" onkeydown="enterNextFocus1();"/>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								往年余额
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.preBalance"
									onblur="return checkMoney_preBalance()" styleClass="input3" onkeydown="enterNextFocus1();"/>
							</td>
							<td width="17%" class="td1">
								本年余额
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.curBalance"
									onblur="return checkMoney_curBalance()" styleClass="input3" onkeydown="enterNextFocus1();"/>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								利息
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.curInterest"
									onblur="return checkMoney_curInterest()" styleClass="input3" onkeydown="enterNextFocus1();"/>
							</td>
							<td width="17%" class="td1">
								职工月收入
							</td>
							<td width="33%">
								<html:text name="traninAddAF" property="tranInTail.monthIncome"
									onblur="return checkMoney_monthIncome()" styleClass="input3" onkeydown="enterNextFocus1();"/>
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
											<logic:equal name="traninAddAF" property="type" value="1">
												<html:submit property="method" styleClass="buttona"
													onclick="return checkData();">
													<bean:message key="button.sure" />
												</html:submit>
											</logic:equal>
										</td>
										<td>
											<logic:equal name="traninAddAF" property="type" value="2">
												<html:submit property="method" styleClass="buttona"
													onclick="return checkData();">
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
			</td>
		</tr>
	</table>
</body>
</html:html>
