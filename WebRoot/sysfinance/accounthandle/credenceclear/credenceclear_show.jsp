<%@ page language="java" import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.accounthandle.credenceclear.action.CredenceclearShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			CredenceclearShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>财务核算</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript"></script>
<script language="javascript" type="text/javascript">
function backErrors(id){
    alert(id);
}

var flag=false;
function reportErrors(message) {
	document.forms[0].elements["credencecheckFindDTO.credenceDateSt"].value="";
	document.forms[0].elements["credencecheckFindDTO.credenceDateEnd"].value="";
	document.forms[0].elements["credencecheckFindDTO.settDateSt"].value="";
	document.forms[0].elements["credencecheckFindDTO.settDateEnd"].value="";
	document.forms[0].elements["credencecheckFindDTO.subjectCode"].value="";
	document.forms[0].elements["credencecheckFindDTO.credenceNumSt"].value="";
	document.forms[0].elements["credencecheckFindDTO.credenceNumEnd"].value="";
	document.forms[0].elements["credencecheckFindDTO.moneySt"].value="";
	document.forms[0].elements["credencecheckFindDTO.moneyEnd"].value="";
	document.forms[0].elements["credencecheckFindDTO.settType"].value="";
	document.forms[0].elements["credencecheckFindDTO.settNum"].value="";
	document.forms[0].elements["credencecheckFindDTO.credenceSt"].value="";
	document.forms[0].elements["credencecheckFindDTO.subjectName"].value="";
	if(message!=null){
		alert(message);
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var x= document.getElementsByName("rowArray");
	for(var i=0;i<x.length;i++){
		x[i].checked=flag;
	}
	flag=(!flag);
	return false;
}
function seachSujectCode(){
	gotoSubjectpop('0','<%=path%>','4');
}
function check(button){
	return confirm("是否"+button.value+"?");
}
function onclear(){
	var x= document.getElementsByName("rowArray");
	var y=true;
	for(var i=0;i<x.length;i++){
		if(x[i].checked){
			y=false;
		}
	}
	if(y){
		alert(' 至少选择一条记录！');
		return false;
	}else{
		return confirm("是否确认记账?");
		return true;
	}
}
function gotoShow(){
	return false;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors();" onContextmenu="return false">

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
						<td background="<%=path%>/img/table_bg_line.gif">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">账户处理&gt;凭证记账</font>
									</td>
									<td width="100" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="127">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="777">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/credenceclearFindAC.do" styleClass="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="15%" class="td1">
								日期
								<br>
							</td>
							<td width="15%">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.credenceDateSt"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.credenceDateEnd"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1">
								结算日期
							</td>
							<td width="15%">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.settDateSt" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.settDateEnd" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								科目代码
							</td>
							<td width="20%" colspan="2">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.subjectCode" styleClass="input3"
									 />
							</td>
							<td width="15%">
								<input type="button" name="Submit12" value="..." class="buttona"
									onclick="seachSujectCode();">
							</td>
							<td width="15%" class="td1">
								科目名称
							</td>
							<td width="35%" colspan="3">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.subjectName" styleClass="input3"
									 onkeydown="enterNextFocus1();"/>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								凭证号
							</td>
							<td width="15%">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.credenceNumSt"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.credenceNumEnd"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1">
								金额
							</td>
							<td width="15%">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.moneySt" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.moneyEnd" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								结算方式
							</td>
							<td width="35%" colspan="3">
								<html:select property="credencecheckFindDTO.settType"
									styleClass="input4" name="credenceclearAF"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="settTypeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="15%" class="td1">
								结算号
							</td>
							<td width="35%" colspan="3">
								<html:text name="credenceclearAF"
									property="credencecheckFindDTO.settNum" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								状态
							</td>
							<td width="35%" colspan="3">
								<html:select name="credenceclearAF" property="credencecheckFindDTO.credenceSt"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="credenceStMap"
										name="credenceclearAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="15%" class="td1">
							</td>
							<td width="35%" colspan="3" class=td7>
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona" >
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>				
				<html:form action="/credenceclearMaintainAC.do" styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="124">
											<b class="font14">凭 证 列 表</b>
										</td>
										<td width="734" height="22" align="center"
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
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" 
						align="center">
						<tr align="center">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.credence_date')">日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.credence_date">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.credence_num')">凭证字号</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.credence_num">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.debit')">借方金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.debit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.credit')">贷方金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.credit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								制单人
							</td>
							<td class="td2">
								复核人
							</td>
							<td class="td2">
								状态
							</td>
						</tr>
						<logic:notEmpty name="credenceclearAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="credenceclearAF" property="list"
								indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" class="<%=strClass%>">
									<td>										
									<html:multibox property="rowArray">
											<bean:write name="e" property="credenceId" />
										</html:multibox>		
									</td>
									<td>
										<bean:write name="e" property="credenceDate" />
									</td>
									<td>
										<a href="#" onClick="window.open('credencePopShowAC.do?docNum=<bean:write name="e" property="credenceNum"/>&credenceDate=<bean:write name="e" property="credenceDate"/>&office=<bean:write name="e" property="office"/>','','width=900,height=550,top='+(window.screen.availHeight-550)/2+',left='+(window.screen.availWidth-900)/2+',scrollbars=yes');return gotoShow();"><bean:write name="e" property="credenceChaNum"/></a>
									</td>
									<td align="right">
										<bean:write name="e" property="debit" format="#,##0.00"/>
									</td>
									<td align="right">
										<bean:write name="e" property="credit" format="#,##0.00"/>
									</td>
									<td>
										<bean:write name="e" property="makeBill" />
									</td>
									<td>
										<bean:write name="e" property="checkpsn" />
									</td>
									<td>
										<bean:write name="e" property="credenceSt" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="credenceclearAF" property="list">
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
												<jsp:param name="url" value="credenceclearShowAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
							<logic:equal name="credencecheckFindDTO" property="buttonPromise1" value="1">
							<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="93" align="right">
												<html:submit property="method" styleClass="buttona" onclick="return onclear();">
													<bean:message key="button.credenceclear" />
												</html:submit>
											</td>
											<td width="93" align="right">
												<html:submit property="method" styleClass="buttonb" onclick="return check(this);">
													<bean:message key="button.credenceclear.all" />
												</html:submit>
											</td>										
										</tr>
									</table>
							</logic:equal>
							<logic:notEqual name="credencecheckFindDTO" property="buttonPromise1" value="1">
							<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="93" align="right">
												<html:submit property="method" styleClass="buttona" disabled="true">
													<bean:message key="button.credenceclear" />
												</html:submit>
											</td>
											<td width="93" align="right">
												<html:submit property="method" styleClass="buttonb" disabled="true">
													<bean:message key="button.credenceclear.all" />
												</html:submit>
											</td>										
										</tr>
									</table>
							</logic:notEqual>
							</td>
						</tr>
					</table>
				</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>


