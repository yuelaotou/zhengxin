<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.accmng.subjectbalance.action.SubjectbalanceShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			SubjectbalanceShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String subjectLevel = (String) request.getAttribute("subjectLevel");
	if (subjectLevel == null || "".equals(subjectLevel)) {
		subjectLevel = "6";
	}
	String subjectParamValue_zl = (String) request.getAttribute("subjectParamValue_zl");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>科目余额表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js"></script>

<script>
function tosubjectStart(st){
	gotoSubjectpop('0','<%=path%>','2');
}
function tosubjectEnd(st){
	gotoSubjectpop('0','<%=path%>','3');
}
function executeAjaxIn(indexs){
	var subjectCodeStart=document.forms[0].elements["subjectCodeStart"].value.trim();
	var subjectCodeEnd=document.forms[0].elements["subjectCodeEnd"].value.trim();
	if(indexs=="3"){
		if(subjectCodeStart.length!=0){
			var queryString = "subjcetbalanceFindACC.do?";
			queryString = queryString + "subjectCode="+subjectCodeStart+"&index="+indexs;
	     	findInfo(queryString);
	   	}
	}else{
		if(subjectCodeEnd.length!=0){
			var queryString = "subjcetbalanceFindACC.do?";
			queryString = queryString + "subjectCode="+subjectCodeEnd+"&index="+indexs;
	     	findInfo(queryString);
	   	}
	}
}
function displays(error,index){
	if(error == "2"){
		alert("输入的科目代码必须是一级科目!");
		if(index=="3"){
			document.forms[0].elements["subjectCodeStart"].focus();
			document.forms[0].elements["subjectCodeStart"].value="";
		}else{
			document.forms[0].elements["subjectCodeEnd"].focus();
			document.forms[0].elements["subjectCodeEnd"].value="";
		}
		return false;
	}

}
function checkMonth_zl(months){
	if(!isNumber(months)){
		alert("会计期间必须是数字!");
		return false;
	}
	if(parseFloat(months)<1 ||parseFloat(months)>12){
		alert("会计期间期数要是,'(1--12之间)'！");
		return false;
	}else{
		return true;
	}
}
function tofocus(flag) //按回车置下一个位置
{
	if(flag=='1'){
		document.forms[0].elements["subjectCodeEnd"].focus();
		return false;
	}else{
		document.forms[0].elements["officeName"].focus();
		return false;
	}
} 
function checkDatas(){
	var credenceDateYear = document.forms[0].elements["credenceDateYear"].value.trim();
	var credenceDateStartMonths = document.forms[0].elements["credenceDateStartMonths"].value.trim();
	var subjectCodeStart = document.forms[0].elements["subjectCodeStart"].value.trim();
	var subjectCodeEnd = document.forms[0].elements["subjectCodeEnd"].value.trim();
	var subjectLevel = document.forms[0].elements["subjectLevel"].value.trim();
	if(credenceDateYear == null || credenceDateYear == ""){
		alert("会计期间年度必须填写!");
		return false;
	}else{
		if(!checkYear(credenceDateYear)){
			return false;
		}
	}
	if(credenceDateStartMonths == null || credenceDateStartMonths == ""){
		alert("会计月必须填写!");
		return false;
	}else{
		if(!checkMonth_zl(credenceDateStartMonths)){
			return false;
		}
	}
	
	if(subjectCodeStart == null || subjectCodeStart == ""){
		alert("科目代码必须填写!");
		return false;
	}
	if(subjectCodeEnd == null || subjectCodeEnd == ""){
		alert("科目代码必须填写!");
		return false;
	}
	if(subjectLevel == null || subjectLevel == ""){
		alert("科目级次必须填写!");
		return false;
	}
}
</script>

<body bgcolor="#FFFFFF" onContextmenu="return false">
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
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<font color="00B5DB">账簿管理&gt;总账科目汇总表</font>
										</p>
									</td>
									<td width="74" class=font14>
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
				<html:form styleId="form1" action="/subjcetbalanceFindAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="120">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="755">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="13%" class="td1">
								会计期间
								<font color="#FF0000">*</font>
							</td>
							<td width="15%">
								<html:text name="subjectbalanceAF" property="credenceDateYear"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%" align="center">
								年
							</td>
							<td width="15%">
								<html:text name="subjectbalanceAF"
									property="credenceDateStartMonths" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%" align="center">
								月
							</td>
							<td width="13%" class="td1">
								科目代码
								<font color="#FF0000">*</font>
							</td>
							<td width="11%">
								<html:text name="subjectbalanceAF" property="subjectCodeStart"
									styleClass="input3" onblur="executeAjaxIn('3');"
									onkeydown="javascrip:if(window.event.keyCode==13){return tofocus('1')}" />
							</td>

							<td width="3%">
								<input type="button" name="Submit12" value="..." class="buttona"
									onclick="tosubjectStart('0');" />
							</td>
							<td width="3%" align="center">
								至
							</td>
							<td width="11%">
								<html:text name="subjectbalanceAF" property="subjectCodeEnd"
									styleClass="input3" onblur="executeAjaxIn('4');"
									onkeydown="javascrip:if(window.event.keyCode==13){return tofocus('2')}" />
							</td>
							<td width="3%">
								<input type="button" name="Submit12" value="..." class="buttona"
									onclick="tosubjectEnd('1');" />
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								所属办事处
								<font color="#FF0000">*</font>
							</td>
							<td colspan="4">
								<span class="td4"> <html:select property="officeName"
										styleClass="input4" name="subjectbalanceAF"
										onkeydown="enterNextFocus1();">
										<option value="全部">
											全部
										</option>
										<html:options collection="officeList1" property="value"
											labelProperty="label" />
									</html:select> </span>
							</td>
							<td width="13%" class="td1">
								科目级次
								<font color="#FF0000">*</font>
							</td>
							<td colspan="5">
								<span class="td4"> <html:select property="subjectLevel"
										styleClass="input4" name="subjectbalanceAF"
										onkeydown="enterNextFocus1();"
										value="<%=subjectParamValue_zl%>">
										<html:options collection="paramValue1" property="value"
											labelProperty="label" />
									</html:select> </span>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkDatas();">
									<bean:message key="button.search" />
								</html:submit>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="128">
										<b class="font14">总账科目汇总表</b>
									</td>
									<td width="746" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
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
						<td align="center" height="6" colspan="1"></td>
					</tr>
				</table>
				<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
					cellpadding="3" align="center">
					<tr align="center">
						<td class="td2" rowspan="2">
							科目代码
						</td>
						<td class="td2" rowspan="2">
							科目名称
						</td>
						<td class="td2" rowspan="2">
							年初余额
						</td>
						<td class="td2" rowspan="2">
							期初余额
						</td>
						<td class="td2" colspan="2">
							本期发生额
						</td>
						<td class="td2" colspan="2">
							本年累计发生额
						</td>
						<td class="td2" rowspan="2">
							期末余额
						</td>
					</tr>
					<tr align="center">
						<td class="td2">
							借方
						</td>
						<td class="td2">
							贷方
						</td>
						<td class="td2">
							借方
						</td>
						<td class="td2">
							贷方
						</td>
					</tr>
					<logic:notEmpty name="list">
						<%
									int j = 0;
									String strClass = "";
						%>
						<logic:iterate name="list" id="element" indexId="i">
							<%
									j++;
									if (j % 2 == 0) {
										strClass = "td8";
									} else {
										strClass = "td9";
									}
							%>
							<tr id="tr<%=i%>" class="<%=strClass%>">
								<td valign="top" align="left">
									<p>
										<bean:write name="element" property="subjectCode" />
									</p>
								</td>
								<td valign="top" align="left">
									<p>
										<bean:write name="element" property="subjectName" />
									</p>
								</td>
								<td valign="top" align="right">
									<p>
										<bean:write name="element" property="yearDebit"
											format="#,##0.00" />
									</p>
								</td>
								<td valign="top" align="right">
									<p>
										<bean:write name="element" property="firstRemainingDebit"
											format="#,##0.00" />
									</p>
								</td>
								<td valign="top" align="right">
									<p>
										<bean:write name="element" property="thisIssueDebit"
											format="#,##0.00" />
									</p>
								</td>
								<td valign="top" align="right">
									<p>
										<bean:write name="element" property="thisIssueCredit"
											format="#,##0.00" />
									</p>
								</td>
								<td valign="top" align="right">
									<p>
										<bean:write name="element" property="thisYearDebit"
											format="#,##0.00" />
									</p>
								</td>
								<td valign="top" align="right">
									<p>
										<bean:write name="element" property="thisYearCredit"
											format="#,##0.00" />
									</p>
								</td>
								<td valign="top" align="right">
									<p>
										<bean:write name="element" property="lastRemainingDebit"
											format="#,##0.00" />
									</p>
								</td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="list">
						<tr>
							<td colspan="11" height="30" style="color:red">
								没有找到与条件相符合的结果！
							</td>
						</tr>
					</logic:empty>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr class="td1">
						<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td align="left">
										共
										<bean:write name="pagination" property="nrOfElements" />
										项
									</td>
									<td align="right">
										<jsp:include page="../../../inc/pagination.jsp">
											<jsp:param name="url" value="subjectbalanceShowAC.do" />
										</jsp:include>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<html:form action="/subjectbalanceMaintainAC.do">
		<table width="95%" border="0" cellspacing="0" cellpadding="3"
			align="center">
			<tr valign="bottom">
				<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="69" align="right">
								<logic:empty name="list">
									<html:submit property="method" styleClass="buttona"
										disabled="true">
										<bean:message key="button.print" />
									</html:submit>
								</logic:empty>
								<logic:notEmpty name="list">
									<html:submit property="method" styleClass="buttona">
										<bean:message key="button.print" />
									</html:submit>
								</logic:notEmpty>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
