<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>

<%@ page
	import="org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.action.QueryReportShowAC"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.form.QueryReportAF"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			QueryReportShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	QueryReportAF queryReportAF = (QueryReportAF) request
			.getAttribute("queryReportAF");
%>
<html:html>
<head>
	<title>财务核算-查询报表</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
function loads(){
	document.all.office.value="全部";
	document.all.bizyear.focus();
}
function checkSearchData(){
    var bizyear=document.all.bizyear.value.trim();
    var starperiod=document.all.starperiod.value.trim();
    var endperiod=document.all.endperiod.value.trim();
    if(bizyear.length==0){
    	alert("请录入会计期间的年!!");
    	document.all.bizyear.focus();
		return false;
    }else if(starperiod.length==0){
    	alert("请录入会计期间的起始期!!");
    	document.all.starperiod.focus();
		return false;
    }else if(endperiod.length==0){
    	alert("请录入会计期间的终止期!!");
    	document.all.endperiod.focus();
		return false;
 	}else if(!checkYear(bizyear)){
    	document.all.bizyear.focus();
		return false;
 	}else if(isNaN(starperiod)){
    	alert("请正确录入会计期间的起始期!!");
    	document.all.starperiod.focus();
		return false;
 	}else if(isNaN(endperiod)){
    	alert("请正确录入会计期间的终止期!!");
    	document.all.endperiod.focus();
		return false;
 	}else{
 	return true;
 	}
}
function fmt(value){
	return value;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="loads();">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="left">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="132">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="742">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/queryReportFindAC">
					<table border="0" width="95%" cellspacing=1 cellpadding=0>
						<tr>
							<td width="13%" class="td1">
								会计期间
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td width="10%">
								<html:text name="queryReportAF" property="bizyear"
									onkeydown="enterNextFocus1();" styleClass="input3"></html:text>
							</td>
							<td width="3%" align="center">
								年
							</td>
							<td width="7%">
								<html:text name="queryReportAF" property="starperiod"
									onkeydown="enterNextFocus1();" styleClass="input3"></html:text>
							</td>
							<td width="3%" align="center">
								期
							</td>
							<td width="3%" align="center">
								至
							</td>
							<td width="8%">
								<html:text name="queryReportAF" property="endperiod"
									onkeydown="enterNextFocus1();" styleClass="input3"></html:text>
							</td>
							<td width="3%" align="center">
								期
							</td>
							<td width="15%" class="td1">
								所属办事处
								<font color="#FF0000">*</font>
							</td>
							<td width="35%">
								<html:select property="office" onkeydown="enterNextFocus1();"
									styleClass="input4">
									<html:option value="全部"></html:option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="right">
								<html:submit styleClass="buttona"
									onclick="return checkSearchData()">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>

				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="95%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="10%">
										<b class="font14"> <%
											String col = queryReportAF.getCol();
											String row = queryReportAF.getRow();
											if (!col.equals("")) {
											%> <%=queryReportAF.getTablename()%> <%
											} else {
											%> <%="报表"%> <%
											}
											%> </b>
									</td>
									<td height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<html:form action="/queryReportMaintainAC">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table border="0" cellspacing="1" cellpadding="0" align="left"
						bgcolor="1BA5FF" width="95%">
						<%
						if (!col.equals("")) {
						%>
						<tr align="center" valign="middle" bgcolor="1BA5FF">
							<td height="2" width="0"></td>
						</tr>
						<%
						for (int i = 1; i < (Integer.parseInt(col) + 1); i++) {
						%>
						<tr align="center" valign="middle" bgcolor="#FFFFFF">
							<%
									for (int j = 1; j < (Integer.parseInt(row) + 1); j++) {
									String flag = "" + i + "_" + j + "";
							%>
							<td height="22">
								<%=queryReportAF.getValue(flag)%>
							</td>
							<%
									}
							}
										} else {
							%>

						</tr>
						<tr align="center" valign="middle" bgcolor="1BA5FF">
							<td height="2" width="0"></td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td height="30" style="color:red" width="700">
								没有找到与条件相符合的结果！
							</td>
						</tr>

						<%
						}
						%>


					</table>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="left">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="69" align="right">
											<%
											if (!col.equals("")) {
											%>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
											</html:submit>
											<%
											} else {
											%>
											<html:submit styleClass="buttona" disabled="true">
												<bean:message key="button.print" />
											</html:submit>
											<%
											}
											%>
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
