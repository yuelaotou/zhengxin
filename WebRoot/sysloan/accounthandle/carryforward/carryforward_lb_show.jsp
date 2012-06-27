<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.accounthandle.carryforward.action.CarryforwardTaShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			CarryforwardTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>特殊业务处理</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	
	
	
	<script language="javascript" src="js/common.js">
</head>
<script language="javascript" ></script>
	<script language="javascript" type="text/javascript">
function loads(){
	document.all.disp.disabled="true";
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function checkdate(){
	if(confirm("是否年终结转？")){
	return true;
	}else{
	return false;
	}
}
function checkloanBankId(temploanBankId){
	var loanBankId=temploanBankId.value.trim();
    var queryString = "carryforwardTbForwardUrlAC.do?";
    queryString = queryString + "loanBankId="+loanBankId; 	     
    findInfo(queryString);
}
function displays(massageinfo){
	if(massageinfo=='pass'){
	document.all.disp.disabled="";
	}else{
	document.all.disp.disabled="true";
		if(massageinfo!=''){
		alert(massageinfo);
		}
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();"
	onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="carryforwardTaShowAC.do" />
	</jsp:include>

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center" style="margin: 0">
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
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">年终结转 
									</td>
									<td width="29" class=font14>
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
					align="center" style="margin: 0">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="176">
										<b class="font14">查 询 条 件</b>
									</td>
									<td width="749" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/carryforwardTbFindAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center" style="margin: 0">
						<tr>
							<td class="td1" width="17%" algin="center">
								放款银行
							</td>
							<td width="33%">
								<html:select property="loanBankIdf" styleClass="input4"
								name="carryforwardShowAF" onkeydown="enterNextFocus1();" onchange="checkloanBankId(this)">
									<html:option value=""></html:option>
									<html:options collection="banklist" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td class="td1" width="50%" algin="center">
							<p align="center">
							<% String timenow=(String)request.getAttribute("timenow"); 
							if(timenow!=null)%>
							年终结转时间：<%=timenow %> 
							</p>
							</td>
							 <td align="right">
								<html:submit property="method" styleClass="buttona" onclick="return checkdate()" styleId="disp">
												<bean:message key="button.carry.forward" />
											</html:submit>
							</td>               
							
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
 