<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.orgoverpay.action.OrgoverpayTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	Object pagination = request.getSession(false).getAttribute(
		OrgoverpayTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String str = null;
	if (request.getAttribute("type") != null) {
		str = (String) request.getAttribute("type");
	} else {
		str = "2";
	}
%>
<html:html>
<head>
	<title>单位挂账</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
</head>
<script type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
function executeAjax() {
	doRequestUsingGET();
}

var xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function doRequestUsingGET() {
   	var queryString = "orgoverpayTaFindAAC.do?";
    
    var orgId = document.getElementById("orgId").value.trim();   
   
    queryString = queryString + "orgId="+orgId; 
    if(orgId!=""){
       	findInfo(queryString);
    }else{
    	gotoSelect();
    }
}

function handleStateChange() {
  	if(xmlHttp.readyState == 4) {
      	if(xmlHttp.status == 200) {     
         	var x=xmlHttp.responseText;
         	eval(x);
      	}
   	}
}
function showlist() {
	// document.Form1.submit();
	ocument.all.confirmButton.disabled="";
}

function displays(unitNum,unitName,banlance,orgexcess,a,recievecom,recbank,recbanknum,givebank,givebanknum,noteNum){
	if(a!="a"){
		alert('该单位所在的归集银行日结日期与登陆日期不符，不允许做业务！');
		document.orgoverpayAF.elements["orgId"].value="";
		return false;
  	}
  	if(unitName=="")
  	{
   		alert("不存在该单位");
	   	document.getElementById("unitName").value="";
	   	document.getElementById("orgId").value="";
	   	document.getElementById("banlance").value="";
	   	document.all.confirmButton.disabled="true";
  	}else{
	  	document.getElementById("orgId").value=unitNum;
	  	document.getElementById("unitName").value=unitName;
	  	document.getElementById("banlance").value=banlance;
   		document.getElementById("orgName1").value=recievecom;
  		document.getElementById("orgBank1").value=recbank;
  		document.getElementById("orgBankNum1").value=recbanknum;
  
     	document.getElementById("orgName2").value=unitName;
  		document.getElementById("orgBank2").value=givebank;
  		document.getElementById("orgBankNum2").value=givebanknum;
  		document.getElementById("noteNum").value=noteNum;
  		document.all.confirmButton.disabled=""; 
  	}
  	if(orgexcess=="not"){
  		alert("该单位存在未到账的挂账业务，不能再次办理挂账");
  		document.getElementById("unitName").value="";
  		document.getElementById("orgId").value="";
  		document.getElementById("banlance").value="";
  		document.all.confirmButton.disabled="true";
  	}
 
}
function gotoSelect(){

	gotoOrgpop("2" ,'<%=path%>','0');
}


function loads(){
	var orgd=document.getElementById("orgId").value;
	if(orgd.length<=0){
	 	document.getElementById("orgName1").value="";
	  	document.getElementById("orgBank1").value="";
	  	document.getElementById("orgBankNum1").value="";
  
     	document.getElementById("orgName2").value="";
	  	document.getElementById("orgBank2").value="";
	  	document.getElementById("orgBankNum2").value="";
   
	}
  
	var s=document.all.updatesign.value;
	if(s=="update"){
		document.all.confirmButton.disabled="";
	}else{
		document.all.confirmButton.disabled="true";
	}
	if(<%=str%>=="1"){
		document.all.orgId.disabled="true";
		document.all.button.disabled="true";
	}
}

function validateBanlance(){
	var money=document.getElementById("amount").value;
	var banlance=document.getElementById("banlance").value;  
	
	var orgOverPay=document.getElementById("type").value;
	var noteNum=document.getElementById("noteNum").value;
	if(orgOverPay.length==0||orgOverPay==""){
		alert("请选择挂账类型！");
		return false;
	}
	if(orgOverPay==1){
	<%--	if(noteNum==""){--%>
	<%--		alert("请输入结算号！");--%>
	<%--		return false;--%>
	<%--	}--%>
	}
	if(money==0||money==""||money.length==0){
		alert("请输入挂账金额！");
		return false;
	}


//var total=parseFloat(money)+parseFloat(banlance);
//var orgMoney=parseFloat(money);
//if(orgMoney!=0){ 
//if(total>=0){
//return true;
//}else{
//alert("挂账金额小于0,请重新输入挂账金额");
//return false;
//}
//}else{
//alert("请输入挂账金额");
//return false;
//}
}

function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
</script>
<script>
  function reportErrors(){

	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();"
	onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="orgoverpayTaShowAC.do" />
	</jsp:include>

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
										办理挂账
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a
											href="<%=path%>/syscollection/paymng/orgoverpay/orgoverpayTbForwardUrlAC.do"
											class=a2>挂账维护</a>
									</td>
								</tr>
							</table>
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="136" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">缴存管理&gt;单位挂账</font>
									</td>
									<td class=font14>
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
										<b class="font14">缴 存 信 息</b>
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


				<html:form action="/orgoverpayConfirmAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								单位编号：
								<font color="#FF0000">*</font>
							</td>
							<td width="25%">
								<html:text name="orgoverpayAF" property="orgId"
									styleClass="input3" styleId="orgId" onkeydown="gotoEnter();"
									ondblclick="executeAjax();" />
							</td>
							<td width="8%">
								<input type="button" class="buttona" value="..."
									onClick="gotoSelect();" name="button" />

							</td>
							<td width="17%" class="td1">
								单位名称：
							</td>
							<td width="33%">
								<html:text name="orgoverpayAF" property="unitName"
									styleClass="input3" styleId="unitName" readonly="true" />

							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								结算号：
							</td>
							<td colspan="2" width="33%">
								<html:text name="orgoverpayAF" property="noteNum"
									styleClass="input3" styleId="noteNum" maxlength="50" />
							</td>
							<td class="td1" width="17%">
								挂账余额：
							</td>
							<td width="33%">
								<html:text name="orgoverpayAF" property="banlance"
									styleClass="input3" styleId="banlance" readonly="true" />
							</td>
						</tr>


						<tr>
							<td width="17%" class="td1">
								收款单位名称
							</td>
							<td colspan="2" width="33%">
								<html:text name="orgoverpayAF" property="orgName1"
									styleClass="input3" styleId="noteNum" maxlength="50"
									readonly="true" />
							</td>
							<td class="td1" width="17%">
								付款单位名称
							</td>
							<td width="33%">
								<html:text name="orgoverpayAF" property="orgName2"
									styleClass="input3" styleId="banlance" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								收款单位银行
							</td>
							<td colspan="2" width="33%">
								<html:text name="orgoverpayAF" property="orgBank1"
									styleClass="input3" styleId="noteNum" maxlength="50"
									readonly="true" />
							</td>
							<td class="td1" width="17%">
								付款单位银行
							</td>
							<td width="33%">
								<html:text name="orgoverpayAF" property="orgBank2"
									styleClass="input3" styleId="banlance" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								收款单位账号
							</td>
							<td colspan="2" width="33%">
								<html:text name="orgoverpayAF" property="orgBankNum1"
									styleClass="input3" styleId="noteNum" maxlength="50"
									readonly="true" />
							</td>
							<td class="td1" width="17%">
								付款单位账号
							</td>
							<td width="33%">
								<html:text name="orgoverpayAF" property="orgBankNum2"
									styleClass="input3" styleId="banlance" readonly="true" />
							</td>
						</tr>





						<tr>
							<td class="td1" width="17%">
								挂账金额：
								<font color="#FF0000">*</font>
							</td>
							<td width="33%" colspan="2">
								<html:text name="orgoverpayAF" property="amount"
									styleClass="input3" styleId="amount" />
								<input type="hidden" name="updatesign"
									value='<bean:write name="orgoverpayAF" property="sign"/>' />
							</td>
							<td class="td1" width="17%">
								挂账类型
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:select name="orgoverpayAF" property="type"
									styleClass="input4" style="bizType">
									<html:option value=""></html:option>
									<html:optionsCollection property="payTypeMap"
										name="orgoverpayAF" label="value" value="key" />
								</html:select>

							</td>
						</tr>
						<tr>
							<td class="td1" width="17%" rowspan="3">
								挂账原因：
							</td>
							<td colspan="4" width="70%" rowspan="3">
								<html:textarea name="orgoverpayAF" property="reason" cols="78"
									rows="4" styleId="reason"></html:textarea>
							</td>

							<logic:notEmpty name="orgExcessPayment">
								<input type="hidden" name="orgoverpayid"
									value='<bean:write name="orgExcessPayment" property="id"/>' />
							</logic:notEmpty>

						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<security:authorize operation="define_org"></security:authorize>
										</td>
										<td></td>
										<td></td>
										<html:submit styleClass="buttona" styleId="confirmButton"
											onclick="return validateBanlance();">
											<bean:message key="button.sure" />
										</html:submit>
										<td></td>
										<td></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

				</html:form>
			</td>
		</tr>
	</table>
</html:html>
