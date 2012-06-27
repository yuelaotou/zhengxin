<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action.ChgpersonQueryShowAC"%>
<%@ include file="/checkUrl.jsp"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ChgpersonQueryShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>人员变更-变更查询</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>
<body bgcolor="#FFFFFF" text="#000000">

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
						<td width="350" background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										<a href="chppersonOrgForwardURLAC.do" class=a2>单位变更查询</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="chppersonEmpForwardURLAC.do" class=a2>职工变更查询</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										变更查询
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
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">变更业务</font><font color="00B5DB">&gt;变更查询</font>
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">查 询 信 息</b>
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
				<html:form action="/chgpersonQueryFindAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1" algin="center">
								办事处
							</td>
							<td width="22%" colspan="3">
								<html:select name="chgpersonQueryAF" property="office"
									styleClass="input4">
									<option value=""></option>
									<html:options collection="officeList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td class="td1" width="17%" algin="center">
								归集银行
							</td>
							<td width="22%" colspan="2">
								<html:select name="chgpersonQueryAF" property="bankid"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:options collection="bankList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								变更年月
							</td>
							<td width="15%">
								<html:text name="chgpersonQueryAF" property="begDate"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="chgpersonQueryAF" property="endDate"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td class="td1" width="17%" algin="center">
								变更类型
							</td>
							<td width="22%" colspan="2">
								<html:select name="chgpersonQueryAF" property="type"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:option value="12">开户</html:option>
									<html:option value="4">封存</html:option>
									<html:option value="3">启封</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								单位编号
							</td>
							<td width="15%" colspan="3">
								<html:text name="chgpersonQueryAF" property="orgid"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td class="td1" width="17%" algin="center">
								&nbsp;
							</td>
							<td width="22%" colspan="2">
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="search()">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>

				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							合计：变更总人数:
							<u><bean:write name="pagination" property="nrOfElements" />&nbsp;</u>
						</td>
					</tr>
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">人员变更列表</b>
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
				<html:form action="/chgpersonQueryShowAC.do" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" class=td2>
								单位编号
							</td>
							<td align="center" class=td2>
								单位名称
							</td>
							<td align="center" class=td2>
								职工编号
							</td>
							<td align="center" class=td2>
								职工姓名
							</td>
							<td align="center" class=td2>
								变更类型
							</td>
							<td align="center" class=td2>
								变更年月
							</td>
							<td align="center" class=td2>
								变更状态
							</td>
						</tr>
						<logic:notEmpty name="chgpersonQueryAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="chgpersonQueryAF" property="list"
								id="element" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr class="<%=strClass%>">
									<td valign="top">
										<p>
											<bean:write name="element" property="orgid"
												format="0000000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="orgname" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empid" format="000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empname" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="type" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="bizDate" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="status" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="chgpersonQueryAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
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
												<jsp:param name="url" value="chgpersonQueryShowAC.do" />
											</jsp:include>
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
</html:html>
