<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page
	import="org.xpup.hafmis.syscollection.accountmng.accountchg.action.OrgChgShowListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = session
			.getAttribute(OrgChgShowListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	request.getSession().getAttribute("orgkhCriteronsAF");
	String printid = (String) request.getAttribute("printid");
%>

<html:html lang="true">
<head>
	<title>开户销户&gt;&gt;单位开户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />

</head>
<script language="javascript">
	</script>
<script language="javascript">
		function onload(){
			document.all.printid.value = "<%=printid%>";
		}
	</script>
<body bgcolor="#FFFFFF" text="#000000" onload="onload();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="orgchashowlistAC.do" />
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
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="orgchgforwardURLAC.do" class=a2>办理变更 
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										变更日志
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
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">开户销户&gt;单位变更</font>
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

				<html:form action="/find_organizations_chg" styleClass="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="13%" class="td1">
								单位编号
							</td>
							<td width="18%">
								<html:text property="id" styleClass="input3"
									onkeydown="enterNextFocus1();" styleId="txtsearch"></html:text>
							</td>
							<td width="11%" class="td1">
								单位名称
							</td>
							<td width="21%">
								<html:text property="name" styleClass="input3"
									onkeydown="enterNextFocus1();" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								变更类型
							</td>
							<td width="18%">
								<html:select property="chgType" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="orgchgtypeMap"
										name="orgChgListAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="11%" class="td1"></td>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit styleClass="buttona">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">单 位 列 表</b>
									</td>
									<td width="826" height="22" align="center"
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
				<html:form action="/orgChgPrintInfoAC" styleClass="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy" value="org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								联系人
							</td>
							<td align="center" class=td2>
								联系电话
							</td>
							<td align="center" class=td2>
								变更类型
							</td>
							<td align="center" class=td2>
								变更日期
							</td>
						</tr>
						<logic:notEmpty name="orgChgListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="orgChgListAF" property="list" indexId="i"
								id="orgChgLog">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" class="<%=strClass%>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="radioId"
												onclick="javascript:document.all.printid.value=<bean:write name="orgChgLog" property="id"/>"
												value="<bean:write name="orgChgLog" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="orgChgLog" property="org.id"
												format="0000000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="orgChgLog" property="org.orgInfo.name" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="orgChgLog"
												property="org.orgInfo.transactor.name" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="orgChgLog"
												property="org.orgInfo.transactor.telephone" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="orgChgLog" property="temp_type" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="orgChgLog" property="opTime" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="orgChgListAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td>
								<input type="Hidden" name="printid" value="empty">
							</td>
						</tr>
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
												<jsp:param name="url" value="orgchashowlistAC.do" />
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
										<td width="70">
											<html:submit styleClass="buttona" onclick="return confirm('是否打印？')">
												<bean:message key="button.print" />
											</html:submit>
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
</body>
</html:html>
