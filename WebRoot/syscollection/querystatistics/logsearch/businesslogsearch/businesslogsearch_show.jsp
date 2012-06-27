<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.logsearch.businesslogsearch.action.BusinesslogsearchShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			BusinesslogsearchShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询-变更信息-人员变更</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js"></script>

	<script type="text/javascript">
function loads(){

	document.chgpersonOrgListAF.orgId.value="";
	document.chgpersonOrgListAF.orgName.value="";
	document.chgpersonOrgListAF.officeCode.value="";
	document.chgpersonOrgListAF.collectionBank.value="";
	document.chgpersonOrgListAF.chgMonthStart.value="";
	document.chgpersonOrgListAF.chgMonthEnd.value="";
	document.chgpersonOrgListAF.chgDateStart.value="";
	document.chgpersonOrgListAF.chgDateEnd.value="";
	document.chgpersonOrgListAF.chgStatus.value="";

}


function checkID(){
	var id=document.chgpersonOrgListAF.orgId.value.trim();
	if(isNaN(id)){
		alert("请录入单位编号!!");
		document.chgpersonOrgListAF.orgId.focus();
		return false;	
	}else{
		return true;
	}
}


function checkedData(){
   
	var startMonth=checkDate(document.businesslogsearchAF.beginMonth);
	if(startMonth==true){
	checkDate(document.businesslogsearchAF.endMonth);
	}else{
	document.businesslogsearchAF.beginMonth.focus();
	}
}

</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="businesslogsearchShowAC.do" />
	</jsp:include>

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=request.getContextPath()%>/img/table_left.gif"
								width="7" height="37">
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							width="55">
							&nbsp;
						</td>
						<td width="235"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif">
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询</font><font color="00B5DB">&gt;业务活动日志查询</font>
									</td>
									<td width="115" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=request.getContextPath()%>/img/table_right.gif"
								width="9" height="37">
						</td>
					</tr>
				</table>

			</td>
		</tr>
		<tr>
			<td class=td3>
				<html:form action="/businesslogsearchFindAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14"> 查 询 条 件</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
											align="center">
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
							<td width="17%" class="td1">
								业务类型
							</td>
							<td width="33%" colspan="3">
								<html:select name="businesslogsearchAF" property="businessType"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizType"
										name="businesslogsearchAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								操作员
							</td>
							<td width="33%" colspan="3">
								<html:select name="businesslogsearchAF" property="operator"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection name="businesslogsearchAF"
										property="userLists" label="username" value="username" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								起止日期
								<font color="#FF0000">*</font>
							</td>
							<td width="15%">
								<html:text name="businesslogsearchAF" property="beginMonth"
									styleClass="input3" styleId="txtsearch" maxlength="8"></html:text>
							</td>
							<td width="4%">
								至
							</td>
							<td width="14%">
								<html:text name="businesslogsearchAF" property="endMonth"
									styleClass="input3" styleId="txtsearch" maxlength="8"></html:text>
							</td>
							<td width="17%" class="td1">
								业务状态
							</td>
							<td width="33%" colspan="3">
								<html:select name="businesslogsearchAF" property="payStatus"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="status"
										name="businesslogsearchAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkedData();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>


				<html:form action="/businesslogsearchMaintainAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="180">
											<b class="font14">日志信息列表</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
											align="center" width="724">
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
						cellpadding="0" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('a319.bizid')">业务编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="a319.bizid">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('a319.type')">业务类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="a319.type">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('a319.action')">业务状态</a>
								<logic:equal name="pagination" property="orderBy"
									value="a319.action">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('a319.op_time')">操作时间</a>
								<logic:equal name="pagination" property="orderBy"
									value="a319.op_time">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('a319.operator')">操作员</a>
								<logic:equal name="pagination" property="orderBy"
									value="a319.operator">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
						</tr>

						<logic:notEmpty name="businesslogsearchAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="businesslogsearchAF"
								property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr class="<%=strClass %>">
									<td valign="top">

									</td>
									<td>
										<a href="#"
											onclick="window.open('logSearchTaWindowAC.do?headid=<bean:write name="element" property="bizId"/>&bizType=<bean:write name="element" property="bizType_temp"/>','window','scrollbars=yes,resizable=yes,location=no, status=no')"><bean:write
												name="element" property="bizId" />
										</a>
									</td>

									<td>
										<bean:write name="element" property="bizType" />
									</td>
									<td>
										<bean:write name="element" property="payStatus" />
									</td>
									<td>
										<bean:write name="element" property="operatorTime" />
									</td>
									<td>
										<bean:write name="element" property="operator" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="businesslogsearchAF" property="list">
							<tr>
								<td colspan="12" height="30" style="color:red">
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
												<jsp:param name="url" value="businesslogsearchShowAC.do" />
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

								<logic:notEmpty name="businesslogsearchAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
												&nbsp;
											</td>
										</tr>
									</table>
								</logic:notEmpty>

								<logic:empty name="businesslogsearchAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
												&nbsp;
											</td>
										</tr>
									</table>
								</logic:empty>

							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>

</body>
</html:html>
