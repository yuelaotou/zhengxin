<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path=request.getContextPath();
	String flag = (String)request.getAttribute("flag");	
%>
<html:html>
<head>
	<title>开户销户&gt;&gt;单位开户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />	
</head>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%> 
<script language="javascript">
</script>
<script>
function init(){
	document.forms[0].elements["method"].disabled="true";
	if(<%=flag%>=="1"){
		document.forms[0].elements["method"].disabled="";
	}	
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function check(){
  if(document.forms[0].elements["orgChgLog.chgType"].value.trim()==""){  
  	  
  	alert("请录入变更类型！");
  	return false;
  }
}

function findOrgid(){
gotoOrgpop(234,'<%=path%>','0');
}
function executeAjax(){
	var queryString = "orgchgFindInforAAC.do?";
    var id = document.forms[0].elements["org.id"].value.trim();
    queryString = queryString + "id="+id; 	     
    findInfo(queryString);

}
function findEmployeeInfo(){
	if(document.forms[0].elements["org.id"].value==""){
		document.forms[0].elements["Submit322"].click();
	}else{
		var queryString = "orgchgFindInforAAC.do?";
        var id = document.forms[0].elements["org.id"].value.trim();
        queryString = queryString + "id="+id; 	     
	    findInfo(queryString);
	}
}
function displays(orgid,name,officecode,code,taxRegNum,artificialPerson,character,industry,deptInCharge,
address,postalcode,tel,region,paybankName,paybankAccountNum,paydate,collectionBankId,transactorname,email,telephone,mobileTelephone,inspector,
payMode,hiddenpayMode,orgRate,empRate,firstPayMonth,payPrecision,openStatus,flag){

if(flag.length!=0){
	alert(flag);
}

  document.forms[0].elements["org.id"].value=orgid;
  document.forms[0].elements["org.orgInfo.name"].value=name;
  document.forms[0].elements["org.orgInfo.officecode"].value=officecode;
  if(code=="null"){
  	document.forms[0].elements["org.orgInfo.code"].value="";
  }else{
  	document.forms[0].elements["org.orgInfo.code"].value=code;
  }
  if(taxRegNum=="null"){
  	document.forms[0].elements["org.orgInfo.taxRegNum"].value="";
  }else{
  	document.forms[0].elements["org.orgInfo.taxRegNum"].value=taxRegNum;
  }
  if(artificialPerson=="null"){
  	document.forms[0].elements["org.orgInfo.artificialPerson"].value="";
  }else{
  	document.forms[0].elements["org.orgInfo.artificialPerson"].value=artificialPerson;
  }
  if(character=="null"){
  document.forms[0].elements["org.orgInfo.character"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.character"].value=character;
  }
  if(industry=="null"){
  document.forms[0].elements["org.orgInfo.industry"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.industry"].value=industry;
  }
  if(deptInCharge=="null"){
  document.forms[0].elements["org.orgInfo.deptInCharge"].value="";
  }else{

  document.forms[0].elements["org.orgInfo.deptInCharge"].value=deptInCharge;
  }
  if(address=="null"){
  	document.forms[0].elements["org.orgInfo.address"].value="";
  }else{
  	document.forms[0].elements["org.orgInfo.address"].value=address;
  }
  if(postalcode=="null"){
  document.forms[0].elements["org.orgInfo.postalcode"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.postalcode"].value=postalcode;
  }
  if(tel=="null"){
  	document.forms[0].elements["org.orgInfo.tel"].value="";
  }else{
  	document.forms[0].elements["org.orgInfo.tel"].value=tel;
  }
  if(region=="null"){
  document.forms[0].elements["org.orgInfo.region"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.region"].value=region;
  }
  if(paybankName=="null"){
  document.forms[0].elements["org.orgInfo.payBank.name"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.payBank.name"].value=paybankName;
  }
  if(paybankAccountNum=="null"){
  document.forms[0].elements["org.orgInfo.payBank.accountNum"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.payBank.accountNum"].value=paybankAccountNum;
  }
  if(paydate=="null"){
  document.forms[0].elements["org.orgInfo.paydate"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.paydate"].value=paydate;
  }
    
  if(collectionBankId=="null"){
  document.forms[0].elements["org.orgInfo.collectionBankId"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.collectionBankId"].value=collectionBankId;
  }
  if(transactorname==""){

  document.forms[0].elements["org.orgInfo.transactor.name"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.transactor.name"].value=transactorname;
  }
  if(email=="null"){
  document.forms[0].elements["org.orgInfo.transactor.email"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.transactor.email"].value=email;
  }
  if(telephone=="null"){
  document.forms[0].elements["org.orgInfo.transactor.telephone"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.transactor.telephone"].value=telephone;
  }
  if(mobileTelephone=="null"){
  document.forms[0].elements["org.orgInfo.transactor.mobileTelephone"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.transactor.mobileTelephone"].value=mobileTelephone;
  }
  if(inspector=="null"){
  document.forms[0].elements["org.orgInfo.inspector"].value="";
  }else{
  document.forms[0].elements["org.orgInfo.inspector"].value=inspector;
  }
  if(payMode=="null"){
  document.forms[0].elements["payMode"].value="";
  }else{
  document.forms[0].elements["payMode"].value=payMode;
  }
  if(hiddenpayMode==""){
  document.forms[0].elements["org.payMode"].value="";
  }else{
  document.forms[0].elements["org.payMode"].value=hiddenpayMode;
  }
  if(orgRate=="null"){
  document.forms[0].elements["org.orgRate"].value="";
  }else{
  document.forms[0].elements["org.orgRate"].value=orgRate;
  }
  if(empRate=="null"){
  document.forms[0].elements["org.empRate"].value="";
  }else{
  document.forms[0].elements["org.empRate"].value=empRate;
  }
  if(firstPayMonth=="null"){
  document.forms[0].elements["org.firstPayMonth"].value=firstPayMonth;
  }else{
  document.forms[0].elements["org.firstPayMonth"].value=firstPayMonth;
  }
  if(payPrecision=="null"){
  document.forms[0].elements["org.payPrecision"].value="";
  }else{
  document.forms[0].elements["org.payPrecision"].value=payPrecision;
  }
    if(openStatus==""){
  document.forms[0].elements["orgChgLog.chgType"].value="";
  }else{
  document.forms[0].elements["orgChgLog.chgType"].value=openStatus;
  }
	if(flag==""){
		document.forms[0].elements["method"].disabled="";
	}else{
		document.forms[0].elements["method"].disabled="true";
	}


}

</script>
<body bgcolor="#FFFFFF" text="#000000" onLoad="reportErrors(); init();">
	<html:form action="/orgInfo_chg_save" method="post">
		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					 <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path %>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path %>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path %>/img/table_bg_line.gif">
            <table border="0" cellspacing="0" cellpadding="0">    
              <tr> 
 <td width="112" height="37" background="<%=path %>/img/buttonbl.gif" align="center" valign="top"  style="PADDING-top: 7px">办理变更</td>
<td width="112" height="37" background="<%=path %>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="orgchashowlistAC.do" class=a2>变更日志</a></td>
              </tr>  
            </table>
            
           <td background="<%=path %>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path %>/img/title_banner.gif" width="37" height="24"></td>
                <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">开户销户&gt;单位变更</font></td>
                <td width="115" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path %>/img/table_right.gif" width="9" height="37"></td>
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
										<td height="22" background="<%=path %>/img/bg2.gif" align="center">&nbsp;
											
									  </td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						        <tr>        
          <td width="14%" class="td1"  >单位编号</td>
          <td width="28%" colspan="1"> 
           <html:text   property="org.id"  styleClass="input3"  ondblclick="findEmployeeInfo();"  onkeydown="goEnter();" styleId="txtsearch"></html:text>          </td>
          <td width="13%"><input type="button" name="Submit322" value="..." class="buttona" onClick="return findOrgid();" ></td>
          <td width="21%"  class="td1">变更类型 </td>          
        <td width="24%" >
       
        <html:select property="orgChgLog.chgType" styleClass="input4">
        <html:option value=""></html:option>
        <html:optionsCollection property="orgchgtypeMap" name="orgChgAF" label="value" value="key"/></html:select></td>
		</tr>					
			<tr>
				<td width="14%" class="td1">
				单位名称				</td>
				<td colspan="2">
				<html:text property="org.orgInfo.name" styleClass="input3"
					onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
							<td width="21%" class="td1">
								办事处名称							</td>
							<td width="24%">
			  <html:text property="org.orgInfo.officecode" styleClass="input3" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
					  </tr>
						<tr>
							<td width="14%" class="td1">
								组织机构代码							</td>
							<td colspan="2">
								<html:text property="org.orgInfo.code" styleClass="input3"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
							<td width="21%" class="td1">
								税务登记号							</td>
							<td>
								<html:text property="org.orgInfo.taxRegNum" styleClass="input3"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
						</tr>
						<tr>
							<td width="14%" class="td1">
								单位法人							</td>
							<td colspan="2">
								<html:text property="org.orgInfo.artificialPerson"
									styleClass="input3" 
									onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
							<td width="21%" class="td1">
								单位性质							</td>
							<td width="24%">
							
<html:text property="org.orgInfo.character" styleClass="input3"
									maxlength="25" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
						</tr>
						<tr>
							<td width="14%" class="td1">
								所属行业							</td>
							<td colspan="2">
								
<html:text property="org.orgInfo.industry" styleClass="input3"
									maxlength="25" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
							<td width="21%" class="td1">
								主管部门							</td>
							<td width="24%">
<html:text property="org.orgInfo.deptInCharge" styleClass="input3"
									maxlength="25" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
						</tr>
						<tr>
							<td width="14%" class="td1">
								单位地址							</td>
							<td colspan="2">
								<html:text property="org.orgInfo.address" styleClass="input3"
									maxlength="25" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>

							<td width="21%" class="td1">
								邮政编码							</td>
							<td width="24%">
						  <html:text property="org.orgInfo.postalcode" styleClass="input3"
									maxlength="6" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
						</tr>
						<tr>
							<td class="td1">
								单位电话							</td>
							<td colspan="2">
								<html:text property="org.orgInfo.tel" styleClass="input3"
									maxlength="20" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
							<td class="td1">
								所在地区							</td>
							<td>
<html:text property="org.orgInfo.region" styleClass="input3"
									maxlength="2" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
						</tr>
				<tr>
							<td class="td1">
								单位发薪银行							</td>
							<td colspan="2">
								<html:text property="org.orgInfo.payBank.name" styleClass="input3"
									maxlength="2" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
							<td class="td1">
								发薪行账号							</td>
							<td>
							<html:text property="org.orgInfo.payBank.accountNum" styleClass="input3"
									maxlength="2" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
						</tr>
						<tr>
							<td class="td1">
								发薪日							</td>
							<td colspan="2">
								<html:text property="org.orgInfo.paydate" styleClass="input3"
									maxlength="2" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
							<td class="td1">
								归集银行							</td>
							<td>
							<html:text property="org.orgInfo.collectionBankId" styleClass="input3"
									maxlength="2" onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
						</tr>
						<tr>
							<td class="td1">
								单位经办人							</td>
							<td colspan="2">
								<html:text property="org.orgInfo.transactor.name"
									styleClass="input3"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
							<td class="td1">
								经办人E-mial							</td>
							<td>
								<html:text property="org.orgInfo.transactor.email"
									styleClass="input3" maxlength="20"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
						</tr>
						<tr>
							<td width="14%" class="td1">
								经办人电话							</td>
							<td colspan="2">
								<html:text property="org.orgInfo.transactor.telephone"
									styleClass="input3" maxlength="20"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
							<td width="21%" class="td1">
								经办人移动电话							</td>
							<td width="24%">
						  <html:text property="org.orgInfo.transactor.mobileTelephone"
									styleClass="input3" maxlength="20"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>							</td>
						</tr>
						<tr>
						<td width="14%" class="td1">
							稽查员						</td>
						<td colspan="2">
							<html:text property="org.orgInfo.inspector" styleClass="input3"
								maxlength="20" onkeydown="enterNextFocus1();" readonly="true"></html:text>						</td>
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
										<td height="22" background="<%=path %>/img/bg2.gif" align="center">&nbsp;
											
									  </td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
<tr>
							<td width="19%" class="td1">
								缴存方式
							</td>
							<td width="37%">
<html:text property="payMode" styleClass="input3"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>
									<html:hidden property="org.payMode"/>
							</td>
						</tr>
						<tr id="trdis1">
							<td width="19%" class="td1">
								单位缴率
							</td>
							<td width="26%">
								<html:text property="org.orgRate" styleClass="input3"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>
							</td>
							<td width="18%" class="td1">
								职工缴率
							</td>
							<td width="37%">
								<html:text property="org.empRate" styleClass="input3"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>
							</td>
						</tr>

						<tr>
							<td width="19%" class="td1">
								初缴年月
							</td>
							<td width="37%">
								<html:text property="org.firstPayMonth" styleClass="input3"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>
							</td>
							<td width="19%" class="td1">
								缴存精度
							</td>
							<td width="37%">
<html:text property="org.payPrecision" styleClass="input3"
									onkeydown="enterNextFocus1();" readonly="true"></html:text>
							</td>
						</tr>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
<td><html:submit property="method" styleClass="buttona" onclick="return check();"><bean:message key="button.sure"/></html:submit></td>

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
