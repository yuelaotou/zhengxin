<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orgpaymentstatistics.action.EmpListShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = session
			.getAttribute(EmpListShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String indexs = (String) session.getAttribute("indexs");
%>
<html>
	<head>
		<title>开户销户>单位列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script src="<%=path%>/js/common.js"></script>
		<script type="text/javascript">
</script>
	</head>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
		<jsp:include page="../../../../inc/sort.jsp">
			<jsp:param name="url" value="empListShowAC.do" />
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
							<td background="<%=path%>/img/table_bg_line.gif" width="555">
								&nbsp;
							</td>
							<td width="635" background="<%=path%>/img/table_bg_line.gif"></td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right"></td>
							<td width="1">
								<img src="<%=path%>/img/table_right.gif" width="9" height="37">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class=td3>
					<html:form action="/empListFindAC" styleClass="margin: 0"
						target="_self">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="13%" class="td1">
									职工编号：
								</td>
								<td width="18%">
									<html:text property="id" styleClass="input3"
										onkeydown="enterNextFocus1();" styleId="txtsearch"
										ondblclick="findName();" />
								</td>
								<td width="13%" class="td1">
									职工姓名：
								</td>
								<td width="18%">
									<html:text property="name" styleClass="input3"
										onkeydown="enterNextFocus1();" styleId="txtsearch"
										ondblclick="findName();" />
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<html:submit styleClass="buttona">查询</html:submit>
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
											<b class="font14">职工列表 </b>
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
					<form name="idAF" action="" style="margin: 0">
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3"
							align="center">
							<tr>
								<td align="center" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td align="center" class=td2>
									职工编号
								</td>
								<td align="center" class=td2>
									职工信息编号
								</td>
								<td align="center" class=td2>
									职工姓名
								</td>
								<td align="center" class=td2>
									单位编号
								</td>
								<td align="center" class=td2>
									单位名称
								</td>
								<td align="center" class=td2>
									原职工编号
								</td>
								<td align="center" class=td2>
									职工状态
								</td>
							</tr>
							<logic:notEmpty name="employees">
								<%
										int j = 0;
										String strClass = "";
								%>
								<logic:iterate id="employees" name="employees" indexId="i">
									<%
												j++;
												if (j % 2 == 0) {
											strClass = "td8";
												} else {
											strClass = "td9";
												}
									%>
									<tr id="tr<%=i%>" onClick='gotoClickpp("<%=i%>", idAF);'
										onMouseOver='this.style.background="#eaeaea"'
										onMouseOut='gotoColorpp("<%=i%>", idAF);'
										class="<%=strClass%>" onDblClick="qdValues('<%=indexs%>');">
										<td valign="top">
											<p align="left">
												<input id="s<%=i%>" type="radio" name="id"
													value="<bean:write name="employees" property="empId"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>

										<td valign="top">
											<p>
												<bean:write name="employees" property="empId"
													format="000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="employees" property="empInfo.id"
													format="000000" />
											</p>
										</td>

										<td valign="top">
											<p>
												<bean:write name="employees" property="empInfo.name" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="employees" property="org.id"
													format="000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="employees" property="org.orgInfo.name" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="employees" property="oldEmpID"
													format="000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="employees" property="payStatusStr" />
											</p>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="employees">
								<tr>
									<td colspan="11" height="30" style="color:red">
										没有找到与条件相符合结果！
									</td>
								</tr>
							</logic:empty>
						</table>
					</form>


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
												<jsp:param name="url" value="empListShowAC.do" />
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
											<input type="button" value="确 定" class="buttona"
												onclick='qdValues("<%=indexs%>");'>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
