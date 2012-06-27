<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.accountinfo.empaccountinfo.action.ShowEmpAccountMonthListAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ShowEmpAccountMonthListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<html:html>
<head>
	<title>tranin</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	
	
	
	<script language="javascript" src="js/common.js">
</head>
<script language="javascript" ></script>
	<script language="javascript" type="text/javascript">
var tr="tr0"; 
function loads(){
var	orgId=document.empAccountAF.orgIdaa101.value.trim();
if(orgId!=null&&orgId!=''){
	var startDate=document.empAccountAF.startDate.value.trim();
	var lastDate = document.empAccountAF.lastDate.value.trim();
document.empAccountAF.orgIdaa101.value=(format(orgId)+""+orgId);
}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function gettr(trindex) {
 tr=trindex; 
var temp_empid=document.getElementById(tr).childNodes[3].childNodes[0].innerHTML.trim();
var temp_time=document.getElementById(tr).childNodes[11].innerHTML.trim();
var queryString = "showEmpAccountDayListAC.do?";
var orgIdaa101=document.empAccountAF.orgIdaa101.value.trim();
var nameba001=document.empAccountAF.nameba001.value.trim();
var nameba002=document.empAccountAF.nameba002.value.trim();
var startDate=document.empAccountAF.startDate.value.trim();
var lastDate = document.empAccountAF.lastDate.value.trim();
        queryString = queryString +"temp_time="+temp_time + "&temp_empid="+temp_empid+"&orgIdaa101="+orgIdaa101+ "&nameba001="+nameba001+ "&nameba002="+nameba002+ "&startDate="+startDate+ "&lastDate="+lastDate; 	
        window.open(queryString,'_self','');     
}
function goBack(){
    document.Form2.submit();
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads(); reportErrors();"
	onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="showEmpAccountMonthListAC.do" />
	</jsp:include>
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
									<td width="350" background="<%=path%>/img/table_bg_line.gif">
									<td background="<%=path%>/img/table_bg_line.gif" align="right">
										<table width="300" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="37">
													<img src="<%=path%>/img/title_banner.gif" width="37"
														height="24">
												</td>
												<td width="189" class=font14 bgcolor="#FFFFFF"
													align="center" valign="bottom">
													<font color="00B5DB">统计查询</font><font color="00B5DB">&gt;&gt;</font><font
														color="00B5DB">职工账</font>
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
							<html:form action="/showEmpAccountListAC.do">
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="117">
														<b class="font14">查 询 条 件</b>
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
										<td width="17%" class="td1" algin="center">
											发生日期
										</td>
										<td width="15%">

											<html:text name="empAccountAF" property="startDate"
												styleClass="input3" styleId="txtsearch"
												onkeydown="goEnter();" readonly="true"></html:text>
										</td>
										<td width="3%" algin="center">
											至
										</td>
										<td width="15%" algin="center">

											<html:text name="empAccountAF" property="lastDate"
												styleClass="input3" styleId="txtsearch"
												onkeydown="goEnter();" readonly="true"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
										</td>
										<td width="33%" class="input3">
											<html:hidden name="empAccountAF" property="loadsMassage"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch" />
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											单位编号
										</td>
										<td width="33%" colspan="3">
											<html:text name="empAccountAF" property="orgIdaa101"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch" readonly="true"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											单位名称
										</td>
										<td width="33%">
											<html:text name="empAccountAF" property="nameba001"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch" readonly="true"></html:text>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											职工编号
										</td>
										<td width="33%" colspan="3">
											<html:text name="empAccountAF" property="empIdaa102"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch" readonly="true"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											职工姓名
										</td>
										<td width="33%">
											<html:text name="empAccountAF" property="nameba002"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch" readonly="true"></html:text>
										</td>
									</tr>
								</table>

								<table width="95%" border="0" align="center" cellpadding="5"
									cellspacing="1">
									<tr>
										<td align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkData()" disabled="true">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
							</html:form>
							<html:form action="/empAccountMaintainAC.do" style="margin: 0">

								<table border="0" width="95%" id="table1" align="center"
									border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class=h4>
											本期贷方发生总额
											<u>：<bean:write name="empAccountAF"
													property="temp_credit" /> </u> 本期借方发生总额
											<u>：<bean:write name="empAccountAF" property="temp_debit" />
											</u> 利息合计
											<u>：<bean:write name="empAccountAF"
													property="curInterest" /> </u>
										</td>
									</tr>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="154">
														<b class="font14">总 账 查 询</b>
													</td>
													<td width="750" height="22" align="center"
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

									<tr>
										<td align="center" height="23" bgcolor="C4F0FF">
											&nbsp;
										</td>
										<td align="center" height="23" bgcolor="C4F0FF">
											单位编号
										</td>
										<td align="center" height="23" bgcolor="C4F0FF">
											单位名称
										</td>
										<td align="center" class=td2>
											职工编号
										</td>
										<td align="center" class=td2>
											职工姓名
										</td>
										<td align="center" class=td2>
											期初余额
										</td>
										<td align="center" class=td2>
											本期贷方发生额
										</td>
										<td align="center" class=td2>
											本期借方发生额
										</td>
										<td align="center" class=td2>
											本期贷方笔数
										</td>
										<td align="center" class=td2>
											本期借方笔数
										</td>
										<td align="center" class=td2>
											期末余额
										</td>
										<td align="center" class=td2>
											查询时间
										</td>
									</tr>
									<logic:notEmpty name="empAccountAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="empAccountAF" property="list"
											id="element" indexId="i">
											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>
											<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);'
												onMouseOver='this.style.background="#eaeaea"'
												onMouseOut='gotoColorpp("<%=i%>", idAF);'
												class="<%=strClass%>" onDblClick='gettr("tr<%=i%>");'>
												<td valign="top">
													<p align="left">
														<input id="s<%=i%>" type="radio" name="id"
															value="<bean:write name="element" property="id"/>"
															<%if(new Integer(0).equals(i)) out.print("checked"); %>>
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="org.id"
															format="0000000000" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="org.orgInfo.name" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="empId"
															format="000000" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="empName" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="prebalance" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="temp_credit" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="temp_debit" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="countCredit" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="countDebit" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="curbalance" />
													</p>
												</td>
												<td valign="top">
													<bean:write name="element" property="displayTme" />
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="empAccountAF" property="list">
										<tr>
											<td colspan="4" height="30" style="color:red">
												没有找到与条件相符合结果！
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
														<jsp:include page="../../../../inc/pagination.jsp">
															<jsp:param name="url"
																value="showEmpAccountMonthListAC.do" />
														</jsp:include>
													</td>
												</tr>
											</table>
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
														<html:submit property="method" styleClass="buttona">
															<bean:message key="button.print" />
														</html:submit>
													</td>
													<td>
														<input type='button' class="buttona"
															onclick="return goBack()"
															value="<bean:message key="button.back"/>">
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
			</td>
		</tr>
	</table>
	<form
		action="<%=path%>/syscollection/querystatistics/accountinfo/empaccountinfo/showEmpAccountListAC.do"
		method="POST" name="Form2" id="Form2">
	</form>
</body>
</html:html>
