<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.orgstrct.dto.SecurityInfo" %>
<%
SecurityInfo securityInfo=(SecurityInfo)request.getSession().getAttribute("SecurityInfo");
String path = request.getContextPath();
String rateid=request.getParameter("rateid");
%>
<html:html lang="true">
<head>
	<title>启用日期选择界面</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	<script language="javascript"></script>
	<script language="javascript" type="text/javascript">
	function returnValue(){
	document.all.rateId.value='<%=rateid%>';
	var tempappdate=document.all.appReadDate.value.trim();
	if(tempappdate==''){
    alert('没有选择启用时间');
    return false;
    }else{
    document.rateUseAF.submit();
    }
	}
	function appDateValue(appdate){
	document.all.appReadDate.value=appdate.value.trim();
	}
	function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	window.opener.location.href="rateTaShowAC.do";
	window.close();
	</logic:messagesPresent>
   }
	</script>
</head>

<body onload="reportErrors()" onContextmenu="return false">
	<form action="<%=path%>/sysloan/rateTaUseAC.do" method="POST" name="rateUseAF">
		<input type="hidden" name="rateId" value=""/>
		<input type="hidden" name="appReadDate" value=""/>
		<table width="95%" border="0" cellspacing="0" cellpadding="3"
			align="center">
			<tr bgcolor="1BA5FF">
				<td align="center" height="6" colspan="1"></td>
			</tr>
		</table>

		<table width="95%" border="0" cellspacing="0" cellpadding="3"
			align="center">
			<tr>
				<td class="td1" align="center" valign="middle">
					<input type="radio" name="appMode" value="0"
						onclick="appDateValue(this)" />
					<%=securityInfo.getUserInfo().getPlbizDate().substring(0,6)+""+"01"%>
				</td>
				<td class="td1" align="center" valign="middle">
					<input type="radio" name="appMode" value="1"
						onclick="appDateValue(this)" />
						<%
						String nextdate="";
						String bizDate=securityInfo.getUserInfo().getPlbizDate();
						if(bizDate.substring(4,6).equals("12")){
						nextdate=(Integer.parseInt(bizDate.substring(0,4))+1)+""+"01"+"01";
						%>
						<%=nextdate%>
						<%
						}else{
						nextdate=(Integer.parseInt(bizDate.substring(0,6))+1)+"01";
						%>
						<%=nextdate%>
						<%
						}
						%>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<input type="button" name="button" onclick="return returnValue()"
											styleClass="buttona" value="确认">
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html:html>
