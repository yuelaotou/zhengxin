<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>

<%
String path = request.getContextPath();
%>

<html:html>
<head>
	<title>汇缴比例调整-办理变更</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
	<script src="<%=path%>/js/common.js"></script>
	<script type="text/javascript">
var new_orgrate="";//新单位缴率
var new_emprate="";//新职工缴率

function toPop() {
	gotoOrgpop(2,'<%=path%>','0');
}

function executeAjax(){
	var x = document.forms[0].elements["chgOrgRate.org.sid"].value.trim();
	if(x==""){
		toPop();
	}else if(isNaN(x)){
        	alert("请输入正确格式的编号！");
        	 document.forms[0].elements["chgOrgRate.org.sid"].value.focus();
        	return false;
    }else{
	    var queryString = "chgorgrateFindOrg_AjaxAAC.do?";
	    queryString = queryString + "orgID="+ document.forms[0].elements["chgOrgRate.org.sid"].value.trim();
		findInfo(queryString);
    }
}

function reportErrors() {
    var orgEdition = document.forms[0].elements["orgEdition"].value;
  	if(orgEdition!=""){
      document.forms[0].elements["chgOrgRate.orgRate"].readOnly=true;
      document.forms[0].elements["chgOrgRate.empRate"].readOnly=true;
  	}
  	var orgid=document.forms[0].elements["chgOrgRate.org.sid"].value;
	if(orgid!=""){
		document.forms[0].elements["chgOrgRate.org.sid"].value=format(document.forms[0].elements["chgOrgRate.org.sid"].value)+document.forms[0].elements["chgOrgRate.org.sid"].value;
	}
	
	var name = document.forms[0].elements["chgOrgRate.org.orgInfo.name"].value;
	if(name!=""){
  		document.all.but1.disabled="";
  		document.all.but2.disabled="";
	}else{
  		document.all.but1.disabled="true";
  		document.all.but2.disabled="true";
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function displays(headID,id,name,payMode,chgMonth,preOrgRate,preEmpRate,orgRate,empRate,preSumPay,salarybase,sumSumPay,orgEdition){
  	document.forms[0].elements["chgOrgRate.org.sid"].value=id;

  	var orgid=document.forms[0].elements["chgOrgRate.org.sid"].value;
	if(orgid!=""){
		document.forms[0].elements["chgOrgRate.org.sid"].value=format(document.forms[0].elements["chgOrgRate.org.sid"].value)+document.forms[0].elements["chgOrgRate.org.sid"].value;
	}
  
  	document.forms[0].elements["chgOrgRate.org.orgInfo.name"].value=name;   
  	document.forms[0].elements["chgOrgRate.org.temp_payMode"].value=payMode;
  	document.forms[0].elements["chgOrgRate.chgMonth"].value=chgMonth;
  	document.forms[0].elements["chgOrgRate.preOrgRate"].value=preOrgRate;
  	document.forms[0].elements["chgOrgRate.preEmpRate"].value=preEmpRate;
  	document.forms[0].elements["chgOrgRate.orgRate"].value=orgRate;
  	document.forms[0].elements["chgOrgRate.empRate"].value=empRate;
  	document.forms[0].elements["chgOrgRate.preSumPay"].value=preSumPay;
  	document.forms[0].elements["chgOrgRate.salarybase"].value=salarybase;
  	document.forms[0].elements["chgOrgRate.sumPay"].value=sumSumPay;
  
  	document.forms[0].elements["new_orgrate"].value=document.forms[0].elements["chgOrgRate.orgRate"].value.trim();
  	document.forms[0].elements["new_emprate"].value=document.forms[0].elements["chgOrgRate.empRate"].value.trim();
  
  	document.forms[0].elements["chgOrgRate.org.id"].value=id;
  
  	if(headID!=""){
  		document.all.but1.disabled="";
  		document.all.but2.disabled="";
  	}else{
  		document.all.but1.disabled="";
  		document.all.but2.disabled="true";
  	}
  	if(orgEdition!=""){
      	document.forms[0].elements["chgOrgRate.orgRate"].readOnly=true;
      	document.forms[0].elements["chgOrgRate.empRate"].readOnly=true;
  	}
}

function findEmployeeInfo(){
	var id=document.forms[0].elements["chgOrgRate.org.sid"].value;
	if(id==""){
		toPop();
	}else{
		if(isNaN(id)){
        	alert("请输入正确格式的编号！");
        	document.forms[0].elements["chgOrgRate.org.sid"].value.focus();
        	return false;
	    }else{
			var queryString = "chgorgrateFindOrg_AjaxAAC.do?";
	    	queryString = queryString + "orgID="+ document.forms[0].elements["chgOrgRate.org.sid"].value.trim();
	    	findInfo(queryString);
		}
	
	}
}

function goDBClick(){
	var id=document.forms[0].elements["chgOrgRate.org.sid"].value;
	if(id==""){
		toPop();
	}else{
		var queryString = "chgorgrateFindOrg_AjaxAAC.do?";
    	queryString = queryString + "orgID="+ document.forms[0].elements["chgOrgRate.org.sid"].value.trim();
    	findInfo(queryString);
	}
}

function backErrors(message){
	alert(message);
  	document.forms[0].elements["chgOrgRate.org.orgInfo.name"].value="";   
  	document.forms[0].elements["chgOrgRate.org.temp_payMode"].value="";
  	document.forms[0].elements["chgOrgRate.chgMonth"].value="";
  	document.forms[0].elements["chgOrgRate.preOrgRate"].value="";
  	document.forms[0].elements["chgOrgRate.preEmpRate"].value="";
  	document.forms[0].elements["chgOrgRate.orgRate"].value="";
  	document.forms[0].elements["chgOrgRate.empRate"].value="";
  	document.forms[0].elements["chgOrgRate.preSumPay"].value="";
  	document.forms[0].elements["chgOrgRate.salarybase"].value="";
 	document.forms[0].elements["chgOrgRate.sumPay"].value="0";
 	  
  	document.all.but1.disabled="true";
  	document.all.but2.disabled="true";
}

function checkUse(){
	var x=confirm("确定启用记录?");
	if(x){ 	
		return true;
	}else{
		return false;
	}
}

function checkData(){

	var orgname=document.forms[0].elements["chgOrgRate.org.orgInfo.name"].value.trim();
	var chgdate=document.forms[0].elements["chgOrgRate.chgMonth"].value.trim();
	var orgrate=document.forms[0].elements["chgOrgRate.orgRate"].value.trim();
	var emprate=document.forms[0].elements["chgOrgRate.empRate"].value.trim();
	if(orgname.length==0){
		alert("请选择要调整的单位！！");
		document.forms[0].elements["chgOrgRate.org.sid"].focus();
		return false;
	}else if(chgdate.length==0){
		alert("请录入必填项！！！");
		document.forms[0].elements["chgOrgRate.chgMonth"].focus();
		return false;
	}else if(eval(orgrate)=="0"){
		alert("请录入必填项！！！");
		document.forms[0].elements["chgOrgRate.orgRate"].focus();
		return false;
	}else if(eval(emprate)=="0"){
		alert("请录入必填项！！！");
		document.forms[0].elements["chgOrgRate.empRate"].focus();
		return false;
	}

	new_orgrate=document.forms[0].elements["new_orgrate"].value;
	new_emprate=document.forms[0].elements["new_emprate"].value;
	
	var temp_new_orgrate=document.forms[0].elements["chgOrgRate.orgRate"].value.trim();//新填写的单位缴率
	var temp_new_emprate=document.forms[0].elements["chgOrgRate.empRate"].value.trim();//新填写的职工缴率
	
	
	var orgID = document.forms[0].elements["chgOrgRate.org.sid"].value;
	var new_orgrate1 = document.forms[0].elements["chgOrgRate.orgRate"].value;
	var new_emprate1 = document.forms[0].elements["chgOrgRate.empRate"].value;
	var salarybase = document.forms[0].elements["chgOrgRate.salarybase"].value;

	var queryString= 'accountNewSumPayAC.do?orgID='+orgID+'&new_orgrate='+new_orgrate1+'&new_emprate='+new_emprate1+'&salarybase='+salarybase+'';
    findInfo(queryString);
	
	document.forms[0].elements["new_orgrate"].value=document.forms[0].elements["chgOrgRate.orgRate"].value.trim();
	document.forms[0].elements["new_emprate"].value=document.forms[0].elements["chgOrgRate.empRate"].value.trim();

	return true;
	
}

function checkChgMonth(){
	var chgdate=document.forms[0].elements["chgOrgRate.chgMonth"].value.trim();
	var temp_date = chgdate.match(/^([2][0]\d{2}([0][1-9]|[1][0-2]))$/);  
	if(temp_date==null){
     	alert("请正确录入调整年月！！格式如：200706");
		document.forms[0].elements["chgOrgRate.chgMonth"].focus();
		return false;
	}
	return true;
}

function checkOrgRate(){
	if(!checkRate(document.forms[0].elements["chgOrgRate.orgRate"].value.trim())){
	 	alert("单位缴率输入格式错误!必须小于1,小数点保留两位.");
	 	document.forms[0].elements["chgOrgRate.orgRate"].focus();
	 	return false;
	}
}

function checkEmpRate(){	
	if(!checkRate(document.forms[0].elements["chgOrgRate.empRate"].value.trim())){
	 	alert("职工缴率输入格式错误!必须小于1,小数点保留两位.");
	 	document.forms[0].elements["chgOrgRate.empRate"].focus();
	 	return false;
	}
}

function display(newSumPay){
	document.forms[0].elements["chgOrgRate.sumPay"].value=newSumPay;
}
</script>
<body bgcolor="#FFFFFF" onContextmenu="return false" text="#000000"
	onload="return reportErrors();">

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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										办理变更
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="chgorgrateMaintainForwardURLAC.do" class=a2>变更维护</a>
									</td>
								</tr>
							</table>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="216" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">变更业务</font><font color="00B5DB">&gt;汇缴比例调整</font>
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
				<html:form action="/maintainChgorgrateDoAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">单 位 信 息</b>
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
								单位编号
							</td>
							<td width="22%">
								<input type="Hidden" name="orgEdition"
									value="<bean:write name="chgOrgRateDoAF" property="chgOrgRate.orgEdition"/>">
								<logic:equal name="chgOrgRateDoAF" property="type" value="3">
									<html:text property="chgOrgRate.org.sid" onkeydown="goEnter();"
										ondblclick="goDBClick();" maxlength="20" styleClass="input3"
										styleId="txtsearch"></html:text>
								</logic:equal>

								<logic:equal name="chgOrgRateDoAF" property="type" value="2">
									<html:text property="chgOrgRate.org.sid" readonly="true"
										maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
								</logic:equal>
								<logic:equal name="chgOrgRateDoAF" property="type" value="1">
									<html:text property="chgOrgRate.org.sid" onkeydown="goEnter();"
										ondblclick="goDBClick();" maxlength="20" styleClass="input3"
										styleId="txtsearch"></html:text>
								</logic:equal>
								<html:hidden property="chgOrgRate.org.id" />
							</td>
							<td width="22%">
								<logic:equal name="chgOrgRateDoAF" property="type" value="3">
									<input type="button" class="buttona" onclick="toPop()"
										value="..." />
								</logic:equal>

								<logic:equal name="chgOrgRateDoAF" property="type" value="2">
									<input type="button" class="buttona" disabled="disabled"
										value="..." />
								</logic:equal>
								<logic:equal name="chgOrgRateDoAF" property="type" value="1">
									<input type="button" class="buttona" onclick="toPop()"
										value="..." />
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="22%">
								<html:text property="chgOrgRate.org.orgInfo.name"
									readonly="true" maxlength="20" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								缴存方式
							</td>
							<td width="22%">
								<html:text property="chgOrgRate.org.temp_payMode"
									readonly="true" maxlength="20" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								调整年月
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:text property="chgOrgRate.chgMonth"
									onblur="return checkChgMonth();" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								原单位缴率
							</td>
							<td width="22%">
								<html:text property="chgOrgRate.preOrgRate" readonly="true"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								原职工缴率
							</td>
							<td width="22%">
								<html:text property="chgOrgRate.preEmpRate" readonly="true"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								新单位缴率
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:text property="chgOrgRate.orgRate"
									onkeydown="enterNextFocus1();" onblur="return checkOrgRate();"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
								<input type="hidden" name="new_orgrate">
							</td>
							<td width="17%" class="td1">
								新职工缴率
								<font color="#FF0000">*</font>
							</td>
							<td width="22%">
								<html:text property="chgOrgRate.empRate"
									onkeydown="enterNextFocus1();" onblur="checkEmpRate();"
									maxlength="20" styleClass="input3" styleId="txtsearch"></html:text>
								<input type="hidden" name="new_emprate">
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								调整前应缴总额
							</td>
							<td width="22%">
								<html:text property="chgOrgRate.preSumPay"
									onkeydown="enterNextFocus1();" readonly="true" maxlength="20"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								调整后应缴总额
							</td>
							<td width="22%">
								<input type="hidden" name="temp_sumpay" value="">
								<html:hidden property="chgOrgRate.salarybase" />
								<html:text property="chgOrgRate.sumPay"
									onkeydown="enterNextFocus1();" readonly="true" maxlength="20"
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
											<logic:equal name="chgOrgRateDoAF" property="type" value="1">
												<html:submit styleId="but1" property="method"
													styleClass="buttona" onclick="return checkData();">
													<bean:message key="button.sure" />
												</html:submit>
											</logic:equal>
											<logic:equal name="chgOrgRateDoAF" property="type" value="2">
												<html:submit styleId="but1" property="method"
													styleClass="buttona" onclick="return checkData();">
													<bean:message key="button.edit" />
												</html:submit>
											</logic:equal>
										</td>
										<td>
											<logic:equal name="chgOrgRateDoAF" property="type" value="1">
												<html:submit styleId="but2" property="method"
													styleClass="buttona" onclick="return checkUse();">
													<bean:message key="button.use" />
												</html:submit>
											</logic:equal>
											<logic:equal name="chgOrgRateDoAF" property="type" value="2">
												<html:submit styleId="but2" property="method"
													styleClass="buttona" disabled=""
													onclick="return checkUse();">
													<bean:message key="button.use" />
												</html:submit>
											</logic:equal>

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
