<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.contractchg.assurepledgechg.action.AssurepledgechgTdShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			AssurepledgechgTdShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/picture.js"></script>

</head>
<script type="text/javascript">

var s1="";
var s2="";
var s3="";
var s4="";
var tr="tr0"; 
function gettr2(indexs){
tr=indexs;
var empid=document.getElementById(tr).childNodes[1].childNodes[0].innerHTML;
	if(empid==""){
	document.all.Submit32.disabled="true";
	}else{
	document.all.Submit32.disabled="";
	}
}

function gotoEmpAccount(){
	var borrowerName = document.getElementById(tr).childNodes[1].innerHTML;
	borrowerName =encodeURI(borrowerName);
	var cardNum = document.getElementById(tr).childNodes[2].innerHTML;
	window.open('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'newwindow','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=no,location=no,status=no');
}
function gettr(trindex) {
  tr=trindex;
  update1();
}
function update1() {
  	var status=document.getElementById(tr).childNodes[7].innerHTML;
  	if(status=='作废'){
		document.all.item(s2).disabled="true";
  	}
  	if(status=='正常'){
  	document.all.item(s2).disabled="";
  	}
}

function gotoEmppop(){
  window.open('<%=request.getContextPath()%>/sysloan/showEmployeesListAC.do?indexs='+6,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return gotoShow();
}

function gotoEmpInfo(){
  window.open('<%=request.getContextPath()%>/syscollection/querystatistics/accountinfo/empaccountinfo/findEmpAccountListAC.do','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return gotoShow();
}
function gotoEmpAccount(){
	var borrowerName = document.getElementById(tr).childNodes[1].childNodes[0].innerHTML;
	var cardNum = document.getElementById(tr).childNodes[2].childNodes[0].innerHTML;
	window.open('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'newwindow','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=no,location=no,status=no');
	return false;
}
function checkCard(){
 var cardKind = document.forms[0].elements["cardKind"].value.trim();
 if(cardKind == "0"){
 var num = document.forms[0].elements["carNum"].value.trim();
 if(num!=""){
  isIdCardNo(num);
 }
 }
}
function checkbirthday(){
 var birthday = document.forms[0].elements["birthday"].value;
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
function executeAjax() {

        var contractId = document.forms[0].elements["contractId"].value;
        var debitter = document.forms[0].elements["debitter"].value;
        var empId = document.forms[0].elements["empId"].value;
        var orgId = document.forms[0].elements["org_Id"].value;

	    if(empId != ""){
	     location.href='assurepledgechgTdShowAC.do?empId='+empId+'&orgId='+orgId+'&contractId='+contractId+'&debitter='+debitter;
	    }
	
}

function gotoEdit(){

     var empId = document.forms[0].elements["empId"].value.trim();
     var v1 = document.forms[0].elements["cardKind"].value.trim();
     var v2 = document.forms[0].elements["empName"].value;
     var v3 = document.forms[0].elements["cardNum"].value.trim();
     var v4 = document.forms[0].elements["salary"].value;
     var salary = document.forms[0].elements["salary"].value;
     var tel = document.forms[0].elements["tel"].value.trim();
     var mobile=document.forms[0].elements["mobile"].value.trim();
     var homeTel = document.forms[0].elements["homeTel"].value.trim();
     var orgTel = document.forms[0].elements["orgTel"].value.trim();
     var homeMai = document.forms[0].elements["homeMai"].value.trim();
     var orgMail = document.forms[0].elements["orgMail"].value.trim();
     var balance = document.forms[0].elements["balance"].value.trim();
     var monthPay = document.forms[0].elements["monthPay"].value.trim();
     if(!(v1 != "" && v2 != "" && v3 != "" && v4 != "")){
       alert('请输入必添项！');
       return false;
     }
     //if(v3 != ""){//身份证有可能带字母，不用验证
     //if(isNaN(v3)){
     //alert("请输入正确格式的证件号码！");
     //return false;
    //}
    //}
    if(empId != ""){
      if(isNaN(empId)){
       alert("请输入正确格式的员工编号 ！");
       document.forms[0].elements["empId"].value="";
       return false;
      }
    }
     if(v1==0){
       return isIdCardNo(v3);
     }
      if(salary != ""){
       if(isNaN(salary)){
       alert("请输入正确格式的月工资额 ！");
       document.forms[0].elements["salary"].value="";
       return false;
      }
     }
      if(tel != ""){
       if(isNaN(tel)){
       alert("请输入正确格式的电话号码 ！");
       document.forms[0].elements["tel"].value="";
       return false;
      }
     }
     if(mobile != ""){
       if(isNaN(mobile)){
       alert("请输入正确格式的移动电话号码 ！");
       document.forms[0].elements["mobile"].value="";
       return false;
      }
     }
     if(homeTel != ""){
       if(isNaN(homeTel)){
       alert("请输入正确格式的家庭电话号码 ！");
       document.forms[0].elements["homeTel"].value="";
       return false;
      }
     }
     if(orgTel != ""){
       if(isNaN(orgTel)){
       alert("请输入正确格式的单位电话号码 ！");
       document.forms[0].elements["orgTel"].value="";
       return false;
      }
     }
      if(homeMai != ""){
       if(isNaN(homeMai)){
       alert("请输入正确格式的家庭邮政编码 ！");
       document.forms[0].elements["homeMai"].value="";
       return false;
      }
     }
      if(orgMail != ""){
       if(isNaN(orgMail)){
       alert("请输入正确格式的单位邮政编码 ！");
       document.forms[0].elements["orgMail"].value="";
       return false;
      }
     }
       if(balance != ""){
       if(isNaN(balance)){
       alert("请输入正确格式的账户余额 ！");
       document.forms[0].elements["balance"].value="";
       return false;
      }
     }
      if(monthPay != ""){
       if(isNaN(monthPay)){
       alert("请输入正确格式的月缴存额！");
       document.forms[0].elements["monthPay"].value="";
       return false;
      }
     }
}

function gotoDelete(){
	if(!confirm("确定要进行该次作废吗？")){
		return false;
	}

}
function loads(){
  
  	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	
		 <logic:equal name="theEndorsecontractTdAF" property="isNeedDel" value="1">
 	 	del();
 	 </logic:equal>
	
	var empid = document.forms[0].elements["empId"].value;
if(!(empid != "")){
      document.forms[1].elements["Submit32"].disabled="true";
    }
	var contractId = document.forms[0].elements["contractId"].value;
	var isreadonly = document.forms[0].elements["isReadOnly"].value;
	//if(contractId != "" && isreadonly != ""){
	  //document.forms[0].elements["contractId"].readOnly="readonly";//合同编号
	  //document.forms[0].elements["debitter"].readOnly="readonly";//
	//}else if(contractId != "" && isreadonly == ""){
	  //document.forms[0].elements["contractId"].readOnly="readonly";//合同编号
	  //document.forms[0].elements["debitter"].readOnly="readonly";//
	  //document.forms[0].elements["pledgePerson"].readOnly="readonly";//
	  //document.forms[0].elements["office"].readOnly="readonly";//
	  //document.forms[0].elements["submit4"].disabled="disabled"
	//}
	if(isreadonly == "0"){//添加大部分可修改 月缴存额、帐户余额、账户状态、单位编号不可以填写
	  document.forms[0].elements["contractId"].readOnly="readonly";//合同ID
      document.forms[0].elements["debitter"].readOnly="readonly";//借款人姓名 PL110 
      document.forms[0].elements["empId"].readOnly="";//职工编号
      document.forms[0].elements["empName"].readOnly="";//职工姓名
      document.forms[0].elements["cardKind"].readOnly="";//证件类型
      document.forms[0].elements["cardNum"].readOnly="";//证件号码
      document.forms[0].elements["sex"].readOnly="";//性别
      document.forms[0].elements["birthday"].readOnly="";//出生日期
      document.forms[0].elements["salary"].readOnly="";//月工资额
      document.forms[0].elements["monthPay"].readOnly="";//月缴存额
      document.forms[0].elements["balance"].readOnly="";//账户余额
      document.forms[0].elements["empSt"].readOnly="";//账户状态
      document.forms[0].elements["tel"].readOnly="";//固定电话
      document.forms[0].elements["mobile"].readOnly="";//行动电话
      document.forms[0].elements["homeTel"].readOnly="";//家庭电话
      document.forms[0].elements["homeAddr"].readOnly="";//家庭住址
      document.forms[0].elements["homeMai"].readOnly="";//家庭邮编
      document.forms[0].elements["orgId"].readOnly="readonly";//单位编号
      document.forms[0].elements["orgName"].readOnly="";//单位名称
      document.forms[0].elements["orgAddr"].readOnly="";//单位地址
      document.forms[0].elements["orgTel"].readOnly="";//单位电话
      document.forms[0].elements["orgMail"].readOnly="";//单位邮政编号
	}
     if(isreadonly == "1"){//修改 大部分只读固定电话，移动电话，家庭电话，家庭地址，邮政编码，单位名称，单位地址，单位邮政编码，单位电话 其余只读
	  document.forms[0].elements["contractId"].readOnly="readonly";//合同ID
      document.forms[0].elements["debitter"].readOnly="readonly";//借款人姓名 PL110 
      document.forms[0].elements["empId"].readOnly="readonly";//职工编号
      document.forms[0].elements["empName"].readOnly="readonly";//职工姓名
      document.forms[0].elements["cardKind"].disabled="readonly";//证件类型
      document.forms[0].elements["cardNum"].readOnly="readonly";//证件号码
      document.forms[0].elements["sex"].disabled="readonly";//性别
      document.forms[0].elements["birthday"].readOnly="readonly";//出生日期
      document.forms[0].elements["salary"].readOnly="readonly";//月工资额
      document.forms[0].elements["monthPay"].readOnly="readonly";//月缴存额
      document.forms[0].elements["balance"].readOnly="readonly";//账户余额
      document.forms[0].elements["empSt"].readOnly="readonly";//账户状态
      document.forms[0].elements["tel"].readOnly="";//固定电话
      document.forms[0].elements["mobile"].readOnly="";//行动电话
      document.forms[0].elements["homeTel"].readOnly="";//家庭电话
      document.forms[0].elements["homeAddr"].readOnly="";//家庭住址
      document.forms[0].elements["homeMai"].readOnly="";//家庭邮编
      document.forms[0].elements["orgId"].readOnly="readonly";//单位编号
      document.forms[0].elements["orgName"].readOnly="";//单位名称
      document.forms[0].elements["orgAddr"].readOnly="";//单位地址
      document.forms[0].elements["orgTel"].readOnly="";//单位电话
      document.forms[0].elements["orgMail"].readOnly="";//单位邮政编号
	}
	if(isreadonly == "2"){//修改 大部分只读固定电话，移动电话，家庭电话，家庭地址，邮政编码，单位名称，单位地址，单位邮政编码，单位电话 其余只读
	  document.forms[0].elements["contractId"].readOnly="";//合同ID
      document.forms[0].elements["debitter"].readOnly="";//借款人姓名 PL110 
      document.forms[0].elements["empId"].readOnly="";//职工编号
      document.forms[0].elements["empName"].readOnly="";//职工姓名
      document.forms[0].elements["cardKind"].disabled="";//证件类型
      document.forms[0].elements["cardNum"].readOnly="";//证件号码
      document.forms[0].elements["sex"].disabled="";//性别
      document.forms[0].elements["birthday"].readOnly="";//出生日期
      document.forms[0].elements["salary"].readOnly="";//月工资额
      document.forms[0].elements["monthPay"].readOnly="";//月缴存额
      document.forms[0].elements["balance"].readOnly="";//账户余额
      document.forms[0].elements["empSt"].readOnly="";//账户状态
      document.forms[0].elements["tel"].readOnly="";//固定电话
      document.forms[0].elements["mobile"].readOnly="";//行动电话
      document.forms[0].elements["homeTel"].readOnly="";//家庭电话
      document.forms[0].elements["homeAddr"].readOnly="";//家庭住址
      document.forms[0].elements["homeMai"].readOnly="";//家庭邮编
      document.forms[0].elements["orgId"].readOnly="";//单位编号
      document.forms[0].elements["orgName"].readOnly="";//单位名称
      document.forms[0].elements["orgAddr"].readOnly="";//单位地址
      document.forms[0].elements["orgTel"].readOnly="";//单位电话
      document.forms[0].elements["orgMail"].readOnly="";//单位邮政编号
	}
	 for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="修改"){
				s1=i;
			}
			if(document.all.item(i).value=="作废"){
				s2=i;
			}
<%--			if(document.all.item(i).value=="扫描"){--%>
<%--				s3=i;--%>
<%--			}--%>
		}
	}
	
	
	 
	var islist = document.all.isList.value;
	if(islist == '[]'){
	  document.all.item(s1).disabled="true";
	  document.all.item(s2).disabled="true";
<%--	  document.all.item(s3).disabled="true";--%>
	  document.forms[1].elements["Submit32"].disabled="true";
	}else{
	update1();
	}
} 
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="loads();">
	<jsp:include page="/syscommon/picture/progressbar.jsp" />
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
							width="10">
							&nbsp;
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							valign="top">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										<a href="<%=path%>/sysloan/assurepledgechgTaShowAC.do"
											class=a2>&#25285;&#20445;&#25269;&#25276;&#21464;&#26356;&#32500;&#25252;</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/assurepledgechgTbShowAC.do"
											class=a2>抵押合同信息</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/assurepledgechgTcShowAC.do"
											class=a2>质押合同信息</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttonbl.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										保证人信息
									</td>
								</tr>
							</table>
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="200" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td width="228" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">合同变更&gt;基本信息变更</font>
									</td>
									<td width="35" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="10">
							<img src="<%=request.getContextPath()%>/img/table_right.gif"
								width="9" height="37">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="166"
										class="font14">
										<b>保证人信息</b>
									</td>
									<td height="22"
										background="<%=request.getContextPath()%>/img/bg2.gif"
										align="center" width="738">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/assurepledgechgTdTopMaintainAC.do" method="post"
					style="margin: 0">
					<table width="95%" id="table9" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="17%" class=td1>
								合同编号
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTdAF" property="contractId"
									onkeydown="enterNextFocus1();" styleClass="input3" />
								<input type="hidden" name="paramV"
									value="<bean:write name="theEndorsecontractTdAF" property="paramValue"/>">
								<input type="hidden" name="isReadOnly"
									value="<bean:write name="theEndorsecontractTdAF" property="isReadOnly"/>">
							</td>
							<td class="td1" width="17%">
								借款人姓名
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="theEndorsecontractTdAF" property="debitter"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								职工编号
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTdAF" property="empId"
									ondblclick="return executeAjaxIn();"
									onkeydown="enterNextFocus1();" onkeydown="gotoEnter2();"
									styleClass="input3" />
								<html:hidden name="theEndorsecontractTdAF" property="org_Id" />
							</td>
							<td width="17%" class=td1>
								职工姓名
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" width="33%">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="empName"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								证件类型
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td class="td4" colspan="2">
								<html:select name="theEndorsecontractTdAF" property="cardKind"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="cardMap"
										name="theEndorsecontractTdAF" label="value" value="key" />
								</html:select>
							</td>
							<td class=td1 width="17%">
								证件号码
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" width="33%">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="cardNum"
										onkeydown="enterNextFocus1();" styleClass="input3"
										onblur="checkCard();" /> </font>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								性别
							</td>
							<td class="td4" colspan="2">
								<html:select name="theEndorsecontractTdAF" property="sex"
									onkeydown="enterNextFocus1();" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="sexMap"
										name="theEndorsecontractTdAF" label="value" value="key" />
								</html:select>
							</td>
							<td class=td1 width="17%">
								出生日期
							</td>
							<td class="td4" width="33%">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="birthday"
										onkeydown="enterNextFocus1();" styleClass="input3"
										onblur="checkbirthday();" /> </font>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								月工资额
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" colspan="2">
								<html:text name="theEndorsecontractTdAF" property="salary"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td class=td1 width="17%">
								月缴存额
							</td>
							<td class="td4" width="33%">
								<html:text name="theEndorsecontractTdAF" property="monthPay"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%" height="26">
								账户余额
							</td>
							<td class="td4" colspan="2" height="26">
								<html:text name="theEndorsecontractTdAF" property="balance"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
							<td class=td1 width="17%" height="26">
								账户状态
							</td>
							<td class="td4" width="33%" height="26">
								<html:text name="theEndorsecontractTdAF" property="empSt"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								固定电话
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="tel"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
							<td class=td1 width="17%">
								移动电话
							</td>
							<td class="td4" width="33%">
								<html:text name="theEndorsecontractTdAF" property="mobile"
									onkeydown="enterNextFocus1();" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								家庭电话
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="homeTel"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
							<td class=td1 width="17%">
								家庭住址
							</td>
							<td class="td4" width="33%">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="homeAddr"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								邮政编码
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="homeMai"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
							<td class=td1 width="17%">
								单位编号
							</td>
							<td class="td4" width="33%">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="orgId"
										onkeydown="enterNextFocus1();" readonly="true"
										styleClass="input3" /> </font>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								单位名称
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="orgName"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
							<td class=td1 width="17%">
								单位地址
							</td>
							<td class="td4" width="33%">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="orgAddr"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								单位电话
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="orgTel"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
							</td>
							<td class=td1 width="17%">
								邮政编码
							</td>
							<td class="td4" width="33%">
								<font color="#FF0000"> <html:text
										name="theEndorsecontractTdAF" property="orgMail"
										onkeydown="enterNextFocus1();" styleClass="input3" /> </font>
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
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.add" />
											</html:submit>
											&nbsp;
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb"
												onclick="return gotoEdit(); ">
												<bean:message key="button.edit" />
											</html:submit>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="202">
										<b class="font14">保证人信息列表</b>
									</td>
									<td width="703" height="22" align="center"
										background="<%=request.getContextPath()%>/img/bg2.gif">
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
						<td align="center" height="7" colspan="1"></td>
					</tr>





				</table>
				<html:form action="/assurepledgechgTdDownmaintainAC.do"
					method="post" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								职工姓名
								<br>
							</td>
							<td align="center" class=td2>
								证件号码
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
								状态
							</td>
							<td align="center" class=td2>
								浏览附件
							</td>
						</tr>
						<logic:notEmpty name="theEndorsecontractTdAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="theEndorsecontractTdAF"
								property="list" indexId="i">

								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");gettr2("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass%>"
									onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<p>
											<bean:write name="element" property="empName" />
										</p>
									</td>
									<td>
										<p>
											<bean:write name="element" property="cardNum" />
										</p>
									</td>
									<td>
										<bean:write name="element" property="balance" />
									</td>
									<td>
										<bean:write name="element" property="salary" />
									</td>
									<td>
										<bean:write name="element" property="monthPay" />
									</td>
									<td>
										<bean:write name="element" property="orgName" />
									</td>
									<td>
										<bean:write name="element" property="status" />
									</td>
									<td>
										<a
											href='javascript:excHz("<bean:write name="element" property="photoUrl"/>");'><img
												src="<%=path%>/img/lookinfo.gif" width="37" height="24">
										</a>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>

						<input type="hidden" name="isList"
							value="<bean:write name="theEndorsecontractTdAF" property="list"/>">
						<logic:empty name="theEndorsecontractTdAF" property="list">
							<tr>
								<td colspan="16" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>

						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td>
								&nbsp;
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
											<input type="button" name="Submit32" value="查看保证人明细账"
												class="buttonc" onclick="gotoEmpAccount();">
											&nbsp;
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.update" />
											</html:submit>
											&nbsp;
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttonb"
												onclick="return gotoDelete(); ">
												<bean:message key="button.canceled" />
											</html:submit>
											&nbsp;
										</td>
<%--										<td width="70">--%>
<%--											<html:submit property="method" styleClass="buttonb">--%>
<%--												<bean:message key="button.scan" />--%>
<%--											</html:submit>--%>
<%--											&nbsp;--%>
<%--										</td>--%>
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

