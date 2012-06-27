<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String openstatus = request.getSession().getAttribute("openstatus")
			.toString();
	String paymode = request.getSession().getAttribute("org.paymode")
			.toString();
	String flag = (String) request.getAttribute("flag");
%>
<html:html>
<head>
	<title>开户销户&gt;&gt;职工开户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />	

</head>
<script language="javascript">
</script>
<script>
var flag1=0;
var message1="";
function reportsErrors(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	//var x=confirm(message);
	alert(message);
	</logic:messagesPresent>
}
function isIdCardNo(num)
      {
      if (!isNumberX(num)) {alert("身份证输入错误！"); return false;}
        var len = num.length, re;
        if (len == 15)
          re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
        else if (len == 18)
          re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
        else {alert("输入的数字位数不对！"); return false;}
        var a = num.match(re);
        if (a != null)
        {
          if (len==15)
          {
            var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
            var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
          }
          else
          {
            var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);
            var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
          }
          if (!B) {alert("输入的身份证号 "+ a[0] +" 里出生日期不对！"); return false;}
        }
        return true;
      }

// 根据身份证计算出时间与性别
function  checkCardNum(){
	var num=document.forms[0].elements["emp.empInfo.cardNum"].value;
	var numType=document.forms[0].elements["emp.empInfo.cardKind"].value;;
	if(numType==0){
		if(num==null||num==""){
			return true;
		}else if(num!=null&&!isIdCardNo(num)){
			document.forms[0].elements["emp.empInfo.cardNum"].focus();
		}else{
			if(num.length==15){
				var date ="19"+num.substr(6,6);
				document.forms[0].elements["emp.empInfo.birthday"].value=date;
				var sex = num.substr(14);
				if(sex%2==0||sex=='X'){
					document.forms[0].elements["emp.empInfo.sex"].value='2'
				}else{
					document.forms[0].elements["emp.empInfo.sex"].value='1'
				}
			}else{
				var date=num.substr(6,8)
				document.forms[0].elements["emp.empInfo.birthday"].value=date;
				var sex = num.substr(16,1);
				if(sex%2==0||sex=='X'){
					document.forms[0].elements["emp.empInfo.sex"].value='2'
				}else{
					document.forms[0].elements["emp.empInfo.sex"].value='1'
				}
			}
			return true;
		}
      }

}
// 判断是否为数字
function isNumber(String)
{ 
    var Letters = "1234567890-"; //可以自己增加可输入值
    var i;
    var c;
      if(String.charAt( 0 )=='-')
 return false;
      if( String.charAt( String.length - 1 ) == '-' )
          return false;
     for( i = 0; i < String.length; i ++ )
     {
          c = String.charAt( i );
   if (Letters.indexOf( c ) < 0)
          return false;
}
     return true;
}
// 判断身份证
function isNumberX(String)
{ 
    var Letters = "1234567890xX-"; //可以自己增加可输入值
    var i;
    var c;
      if(String.charAt( 0 )=='-')
 return false;
      if( String.charAt( String.length - 1 ) == '-' )
          return false;
     for( i = 0; i < String.length; i ++ )
     {
          c = String.charAt( i );
   if (Letters.indexOf( c ) < 0)
          return false;
}
     return true;
}
//判断金额
function checkMoney1(money){
	var salarybase = money.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/);
	if (salarybase==null)
	{	
		return false;
	}else{
		return true;
	}

}
function check(){
	var Department=document.forms[0].elements["emp.empInfo.department"].value.trim();
		document.forms[0].elements["emp.empInfo.department"].value=Department;
	var Tel=document.forms[0].elements["emp.empInfo.tel"].value.trim();
		document.forms[0].elements["emp.empInfo.tel"].value=Tel;
	var MobileTle=document.forms[0].elements["emp.empInfo.mobileTle"].value.trim();
		document.forms[0].elements["emp.empInfo.mobileTle"].value=MobileTle;
	

	if(document.forms[0].elements["emp.empInfo.name"].value.trim()!=""){  
		document.forms[0].elements["emp.empInfo.name"].value=document.forms[0].elements["emp.empInfo.name"].value.trim();
  		var	cardNum=document.forms[0].elements["emp.empInfo.cardNum"].value.trim();
  		document.forms[0].elements["emp.empInfo.cardNum"].value=cardNum;
      	if (cardNum==null||cardNum==0){
      	      	alert("请正确录入证件号码！");
				return false;
		}else{ 	  
			var birthday=document.forms[0].elements["emp.empInfo.birthday"].value.trim();
			if (birthday==null||birthday==0||!isNumber(birthday)){
       			alert("请录入出生日期！格式如：19821013");
			    return false;
			}else{		
				var monthIncome=document.forms[0].elements["emp.empInfo.monthIncome"].value.trim();
				document.forms[0].elements["emp.empInfo.monthIncome"].value=monthIncome;
				if(!checkMoney1(monthIncome)){
					alert("请正确录入职工月收入!格式如：1087.23");
					return false;
				}else{
					var salaryBase=document.forms[0].elements["emp.salaryBase"].value.trim();
					document.forms[0].elements["emp.salaryBase"].value=salaryBase;
					if (salaryBase==null||!checkMoney1(salaryBase)){
		       			alert("请正确录入工资基数！格式如：1087.23");
						return false;
					}else{				 
						var orgPay=document.forms[0].elements["emp.orgPay"].value.trim();
						var empPay=document.forms[0].elements["emp.empPay"].value.trim();
						document.forms[0].elements["emp.orgPay"].value=orgPay;
						document.forms[0].elements["emp.empPay"].value=empPay;
						if (orgPay==null||empPay==null||!checkMoney1(orgPay)||!checkMoney1(empPay)){
			       			alert("请正确录入缴额！格式如：1087.23");
							return false;
						}else{
							return true;
						}
					}
				} 	
			}
		}	
	}else{
	  	alert("请录入职工姓名！");
	  	return false;
	}
}
function init(){
	document.empKhCriteronsAF.elements["emp.empInfo.name"].focus();
	if(<%=openstatus%>!=1){
		document.forms[0].elements["emp.salaryBase"].readOnly="true";
		document.forms[0].elements["emp.org.payModeStr"].readOnly="true";
		document.forms[0].elements["emp.orgPay"].readOnly="true";
		document.forms[0].elements["emp.empPay"].readOnly="true";
	}
	if(<%=flag%>==1){
		var x=confirm("系统中存在与该用户相同身份证号不同姓名的职工，是否继续添加?");
		if(x){    
			document.forms[0].action="empSaveAC.do";
			document.forms[0].submit();
			return true;
		}else{
		  return false;
		}
	}else if(<%=flag%>==2){
		var x=confirm("系统中存在与该用户相同身份证号不同姓名的职工，是否继续添加?");
		if(x){    
			document.forms[0].action="empUpdateAC.do";
			document.forms[0].submit();
			return true;
		}else{
		  return false;
		}
	}
		
}
function displays(orgPay,empPay) {
	document.empKhCriteronsAF.elements["emp.orgPay"].value=orgPay;
	document.empKhCriteronsAF.elements["emp.empPay"].value=empPay;
    document.empKhCriteronsAF.elements["emp.empPay"].focus();
}
function findpay() {
   	if(<%=openstatus%>==1){
		if(<%=paymode%>==1){
		   	var salaryBase=document.empKhCriteronsAF.elements["emp.salaryBase"].value.trim();
		   	var orgRate=document.empKhCriteronsAF.elements["orgrate"].value.trim();
		   	var empRate=document.empKhCriteronsAF.elements["emprate"].value.trim();
		   	var payPrecision=document.empKhCriteronsAF.elements["payPrecision"].value.trim();
		   	var queryString = "empOpenPayFindACC.do?";    
		    queryString = queryString + "salaryBase="+salaryBase+'&orgRate='+orgRate+'&empRate='+empRate+'&payPrecision='+payPrecision;
		    findInfo(queryString);
     	}
   	}
}
function checkBirthday(){
    var birthday=document.forms[0].elements["emp.empInfo.birthday"].value.trim();
    var birthday_ = birthday.match(/^((((19)|(20))[0-9][0-9])(1[0-2]|0?[1-9])(3[0,1]|[1,2][0-9]|0?[1-9]))$/); 
    if(birthday==null||birthday==""){
    	return true;
    }
    if(birthday_==null){
    	alert("请正确录入出生日期信息！格式如：19560502");
    	document.forms[0].elements["emp.empInfo.birthday"].focus();
    	return false;
    }
}
function  checkCardNum1(){
	var num=document.forms[0].elements["emp.empInfo.cardNum"].value;
	var numType=document.forms[0].elements["emp.empInfo.cardKind"].value;
	if(numType==0){
		isIdCardNo(num);
		if(isIdCardNo(num)){
			 if(checkCardNum()){
			 	return true;
			 }else{
			 	return false;
			 }
		}else{
			document.forms[0].elements["emp.empInfo.cardNum"].focus();
			return false;
		}
	}
}
function checkback(){
	document.forms[0].elements["emp.empInfo.monthIncome"].value=0.00;
	document.forms[0].elements["emp.salaryBase"].value=0.00;
	document.forms[0].elements["emp.orgPay"].value=0.00;
	document.forms[0].elements["emp.empPay"].value=0.00;
}
function executeAjax(){
	var queryString = "isCardNumSameAAC.do?";
    var empName = document.forms[0].elements["emp.empInfo.name"].value.trim();
    var cardNum = document.forms[0].elements["emp.empInfo.cardNum"].value.trim();
    queryString = queryString + "empName="+empName+"&cardNum="+cardNum; 	     
    findInfo(queryString);
}
function display_FYF(flag,message){
	flag1=flag;
	message1=message;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onLoad="init();reportsErrors();"
	　onContextmenu="return false">
	<html:form action="/employee_kh_save" method="post">
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
								<br>
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">开户销户&gt;职工开户</font>
										</td>
										<td width="115" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">自 然 信 息</b>
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
							<td></td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								职工姓名
								<font color="#FF0000">*</font>
							</td>
							<td>
								<html:text property="emp.empInfo.name" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="20"></html:text>
							</td>
							<td width="13%" class="td1">
								证件类型
								<font color="#FF0000">*</font>
							</td>
							<td>
								<html:select property="emp.empInfo.cardKind"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:optionsCollection property="cardKindMap"
										name="empKhCriteronsAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>

							<td width="13%" class="td1">
								证件号码
								<font color="#FF0000">*</font>
							</td>
							<td>
								<html:text property="emp.empInfo.cardNum" styleClass="input3"
									onkeydown="enterNextFocus1();" onblur="return checkCardNum();"
									maxlength="20"></html:text>
							</td>
							<td width="12%" class="td1">
								出生日期
								<font color="#FF0000">*</font>
							</td>
							<td width="25%">
								<html:text property="emp.empInfo.birthday" styleClass="input3"
									onkeydown="enterNextFocus1();"
									onblur="return checkBirthday(); " maxlength="8"></html:text>
							</td>
						</tr>
						<tr>

							<td width="12%" class="td1">
								性别
								<font color="#FF0000">*</font>
							</td>
							<td width="25%">
								<html:select property="emp.empInfo.sex"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:optionsCollection property="sexMap"
										name="empKhCriteronsAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="13%" class="td1">
								所在部门
							</td>
							<td width="18%">
								<html:text property="emp.empInfo.department" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<td width="11%" class="td1">
								家庭电话
							</td>
							<td width="21%">
								<html:text property="emp.empInfo.tel" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="20"></html:text>
							</td>
							<td width="13%" class="td1">
								移动电话
							</td>
							<td width="18%">
								<html:text property="emp.empInfo.mobileTle" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="20"></html:text>
							</td>

						</tr>
						<tr>
							<td class="td1" width="17%">
								职工月收入
							</td>
							<td width="33%">
								<html:text property="emp.empInfo.monthIncome"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="18"></html:text>
							</td>
							<td class="td1" width="17%">
								&nbsp;
							</td>
							<td width="33%">
								&nbsp;
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
											<b class="font14">归 集 信 息</b>
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
							<td width="18%" class="td1">
								工资基数
								<font color="#FF0000">*</font>
							</td>
							<td>
								<html:text property="emp.salaryBase" styleClass="input3"
									onkeydown="enterNextFocus1();" onblur="findpay();"
									maxlength="18"></html:text>
								<html:hidden name="empKhCriteronsAF" property="orgrate"
									styleClass="input3" styleId="txtsearch"></html:hidden>
								<html:hidden name="empKhCriteronsAF" property="emprate"
									styleClass="input3" styleId="txtsearch"></html:hidden>
								<html:hidden name="empKhCriteronsAF" property="payPrecision"
									styleClass="input3" styleId="txtsearch"></html:hidden>
							</td>
							<td width="20%" class="td1">
								缴存方式
							</td>
							<td>
								<input name="emp.org.payModeStr" type="text"
									id="emp.org.payModeStr" class="input3" readonly="readonly"
									onkeydown="enterNextFocus1();"
									value='<bean:write name="org.payModeStr"/>'>
							</td>
						</tr>
						<tr>
							<td width="18%" class="td1">
								单位缴额
								<font color="#FF0000">*</font>
							</td>
							<td width="31%">
								<html:text property="emp.orgPay" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="18"></html:text>
							</td>
							<td width="20%" class="td1">
								职工缴额
								<font color="#FF0000">*</font>
							</td>
							<td width="31%">
								<html:text property="emp.empPay" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="18"></html:text>
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
											<input name="hidden" type="hidden"
												value='<bean:write name="empKhCriteronsAF" property="emp.id"/>' />
											<logic:equal name="empKhCriteronsAF" property="type"
												value="1">
												<html:submit property="method" styleClass="buttona"
													onclick="return check();">
													<bean:message key="button.add" />
												</html:submit>
											</logic:equal>
											<logic:equal name="empKhCriteronsAF" property="type"
												value="2">
												<html:submit property="method" styleClass="buttona"
													onclick="return check();">
													<bean:message key="button.update" />
												</html:submit>
											</logic:equal>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="checkback();">
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
