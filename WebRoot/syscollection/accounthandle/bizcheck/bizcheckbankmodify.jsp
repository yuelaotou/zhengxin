<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="java.util.*"%>
<%@page import="org.xpup.security.common.domain.Userslogincollbank"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="org.xpup.security.common.domain.Userslogincollbank"%>
<%@ page import="org.xpup.hafmis.orgstrct.domain.CollBank"%>
<%@ page import="java.util.Iterator"%>

<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();

	String openstatus = null;
	if (request.getAttribute("openstatus") == null) {
		openstatus = "1";
	} else {
		openstatus = request.getAttribute("openstatus").toString();
	}

	String payMode = (String) request.getAttribute("payMode");

	String state = null;
	if (request.getAttribute("state") == null) {
		state = "2";
	} else {
		state = request.getAttribute("state").toString();
	}
	String orgid = null;
	if (request.getAttribute("orgid") != null) {
		orgid = (String) request.getAttribute("orgid");
	}
%>

<html:html>
<head>
	<title>财务处理&gt;&gt;业务复核</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />	


</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<script language="javascript">
</script>
<script type="text/javascript" language="javascript">
function verdictOffice(){
	var officecode=document.forms[0].elements["org.orgInfo.officecode"].value;
		document.URL=('bizCheckCheckAC.do?officecode='+officecode);
}
function executeAjax1(){
	var officeCode = document.forms[0].elements["org.orgInfo.officecode"].value;
	var url = "bizCheckCheckFindAAC.do?officeCode="+officeCode;
	findInfo123(url);
}
function findInfo123(url) {
 createXMLHttpRequest();
	    xmlHttp.onreadystatechange = handleStateChange123;
	    xmlHttp.open("GET", url, true);
	    xmlHttp.send(null);   
}
function handleStateChange123() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {        
        var xmlDoc = xmlHttp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		 
		var texts  = xmlDoc.getElementsByTagName("text");		
		var selectObj = document.getElementById("childNode");
		selectObj.length = 0;
		
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
			if(i==0){
			document.all.moneyType.value=values[i].firstChild.data;
			}
		}	
      }
   }
}
function getChildValue(){
 var selectObj = document.getElementById("childNode");
  document.all.moneyType.value=selectObj.options[selectObj.selectedIndex].value;
}
function displays2(subjectCode,subjectName,subjectDirection,bizType,moneyType,summray) {
	document.all.moneyType.value=moneyType;
	document.all.summray.value=summray;	
         var value="";
         var direction=document.all.subjectDirection;
         if(subjectDirection.trim()==0){
         	direction[0].checked=true;
         	appValue(direction[0]);
         }else{
         direction[1].checked=true;
         appValue(direction[1]);
         }
		var selectObj = document.getElementById("childNode");
		selectObj.length = 0;
			var childOption = new Option(value,moneyType);
			selectObj.options[selectObj.length++] = childOption;
			document.all.moneyType.value=moneyType;
}
function executeAjax(){
	var moneyType = document.forms[0].elements["moneyType"].value;
	var url = "bizCheckCheckModifyAAC.do?moneyType="+moneyType;
	findInfo(url);
}
function displays3(moneyType) {
	window.close();
}
function sure(){
	
	var moneyType = document.forms[0].elements["moneyType"].value;
	var officeCode= document.forms[0].elements["org.orgInfo.officecode"].value;
	window.opener.document.URL="<%=path%>/syscollection/accounthandle/bizcheck/bizcheckModifyBankAC.do?moneyType="+moneyType+"&officeCode="+officeCode;
	window.close();
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return executeAjax1();"
	onContextmenu="return false">
	<html:form action="/bizcheckModifyBankAC.do">
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
											修改存款银行
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
										<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">财务处理&gt;业务复核</font>
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
											<b class="font14">修改存款银行</b>
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
							<td width="12%" class="td1">
								办事处名称
								<font color="#FF0000">*</font>
							</td>
							<td width="25%">
								<html:select property="org.orgInfo.officecode"
									styleClass="input4" onkeydown="enterNextFocus();"
									onchange="executeAjax1();">
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td class="td1">
								归集银行
							</td>
							<td>
							<td width="35%" height="18" colspan="2" align="center">
								<html:hidden property="moneyType" name="orgkhAF" />


								<select class="input4" id="childNode" onChange="getChildValue()">

								</select>
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
											<html:button property="method" styleClass="buttona"
												onclick="sure();">
												<bean:message key="button.sure" />
											</html:button>
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
