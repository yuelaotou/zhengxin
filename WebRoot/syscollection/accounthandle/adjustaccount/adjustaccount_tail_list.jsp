<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List,java.util.ArrayList"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountServiceTaShowTailAC"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountShowAF"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Object pagination = request.getSession(false).getAttribute(
			AdjustaccountServiceTaShowTailAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
    alert(message);
	</logic:messagesPresent>
document.all.orgId.value=formatTen(document.all.orgId.value)+document.all.orgId.value;
if(document.all.orgId.value==0000000000)
	document.all.orgId.value="";
	

}
//输入凭证号是使用
function displays(date,bizDocNum,bizNoteNum,orgId,temp_type,orgName){

 // document.forms[0].elements["date"].value=date;
// document.forms[0].elements["bizDocNum"].value=bizDocNum;
 // if(bizNoteNum!=null)
 // document.forms[0].elements["bizNoteNum"].value=bizNoteNum;
 // document.forms[0].elements["orgId"].value=orgId; 
 // document.forms[0].elements["orgName"].value=orgName;
 	document.adjustaccountShowAF.date.value=date;
  	document.adjustaccountShowAF.bizDocNum.value=bizDocNum;
  	document.adjustaccountShowAF.bizNoteNum.value=bizNoteNum;
  	document.adjustaccountShowAF.orgId.value=orgId;
  	
  	document.adjustaccountShowAF.orgName.value=orgName;
  	if(temp_type!=""){
  	alert(temp_type);
  	}
}//单位使用
function gotoPrint(){
		document.all.report.value="print";
}
</script>
<html>
	<head>
		<title>账务处理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	</head>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()"
		onContextmenu="return false">
		<jsp:include page="../../../inc/sort.jsp">
			<jsp:param name="url" value="adjustaccountServiceTaShowTailAC.do" />
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

							</td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">账务处理</font><font color="00B5DB">&gt;&gt;</font><font
												color="00B5DB">错账调整</font>
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
											<b class="font14">错账调整信息</b>
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
							<td width="11%" class=td1>
								日期
								<br>
							</td>
							<td class="td4" width="12%">
								<html:text name="adjustaccountShowAF" property="date"
									styleClass="input3" styleId="txtsearch" disabled="true" />
							</td>
							<td class="td1" width="11%">
								错账凭证编号
							</td>
							<td class="td4" colspan="2">
								<html:text name="adjustaccountShowAF" property="bizDocNum"
									styleClass="input3" styleId="txtsearch"
									ondblclick="executeAjax1();" onkeydown="gotoEnter();"
									disabled="true" />
							</td>
							<td class="td1" width="17%">
								错账结算号
							</td>
							<td class="td4" width="33%">
								<html:text name="adjustaccountShowAF" property="bizNoteNum"
									styleClass="input3" styleId="txtsearch" disabled="true" />
							</td>
						</tr>
						<tr>
							<td class="td1" width="11%">
								单位编号
							</td>
							<td colspan="4" class="td4">
								<html:text name="adjustaccountShowAF" property="orgId"
									onkeydown="gotoEnters()" ondblclick="executeAjaxOrg();"
									styleClass="input3" styleId="txtsearch" disabled="true" />
							</td>

							<td class="td1" width="17%">
								单位名称
							</td>
							<td width="33%">
								<html:text name="adjustaccountShowAF" property="orgName"
									styleClass="input3" styleId="txtsearch" disabled="true" />
							</td>
						</tr>
					</table>
					<html:form action="/adjustaccountServiceTaShowTailAC.do"
						styleClass="margin: 0">
						<input type="hidden" name="report" value="">
						<logic:notEmpty name="adjustaccountShowAF"
							property="adjustAccountlist">
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0"
											align="center">
											<tr>
												<td class=h4>
													合计：调整人数
													<u>：<bean:write name="pagination"
															property="nrOfElements" /> </u>调整金额
													<u>：<bean:write name="adjustaccountShowAF"
															property="total" />
													</u>
												</td>
											</tr>
										</table>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="117">
													<b class="font14">错帐调整列表</b>
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
									</td>
									<td align="center" class=td2>
										凭证编号
									</td>
									<td align="center" class=td2>
										<a href="javascript:sort('adjustWrongAccountTail.empId')">职工编号</a>
										<logic:equal name="pagination" property="orderBy"
											value="adjustWrongAccountTail.empId">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										<a href="javascript:sort('adjustWrongAccountTail.empName')">职工姓名</a>
										<logic:equal name="pagination" property="orderBy"
											value="adjustWrongAccountTail.empName">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										证件号码
									</td>
									<td align="center" class=td2>
										<a
											href="javascript:sort('adjustWrongAccountTail.adjustMoney')">调整金额</a>
										<logic:equal name="pagination" property="orderBy"
											value="adjustWrongAccountTail.adjustMoney">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										错账日期
									</td>
									<td align="center" class=td2>
										调整业务类型
									</td>
								</tr>
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="adjustaccountShowAF"
									property="adjustAccountlist" id="elements" indexId="i">
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
										onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
										<td valign="top">
											<p align="left">
												<input id="s<%=i%>" type="radio" name="id"
													value="<bean:write name="elements" property="id"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements"
													property="adjustWrongAccountHead.docNum" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="empId" format="000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="emp.empInfo.name" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="emp.empInfo.cardNum" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="adjustMoney" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="settDate" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements"
													property="adjustWrongAccountHead.bizType" />
											</p>
										</td>
									</tr>
								</logic:iterate>
							</table>

							<table width="95%" border="0" cellspacing="0" cellpadding="3"
								align="center">
								<tr class="td1">
									<td align="center">
										<table width="585" height="19" border="0" cellpadding="0"
											cellspacing="0">
											<tr align="center">
												<td width="59">
													共
													<bean:write name="pagination" property="nrOfElements" />
													项
												</td>
												<td width="51"></td>
												<td>
													<jsp:include page="../../../inc/pagination.jsp">
														<jsp:param name="url"
															value="adjustaccountServiceTaShowTailAC.do" />
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
													<html:submit property="method" styleClass="buttona"
														onclick="gotoPrint()">
														<bean:message key="button.print" />
													</html:submit>
												</td>
												&nbsp;&nbsp;
												<td>
													<html:button styleClass="buttona" property="button1"
														onclick="javascript:window.close();" value="关闭"></html:button>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</logic:notEmpty>
		</table>

		</html:form>
		</td>
		</tr>
		</table>
	</body>
</html>

