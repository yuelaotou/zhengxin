<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page import="org.xpup.hafmis.orgstrct.action.AdminSetNullShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = session
			.getAttribute(AdminSetNullShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<html:html>

<head>
	<title>密码置空</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" ></script>
	
</head>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return true">

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
										<font color="00B5DB">管理员置空密码</font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="202">
										<b class="font14">查 询 条 件</b>
									</td>
									<td width="723" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/adminSetNullFindAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="10%" class="td1">
							<center><b>用户名</b></center>	
							</td>
							<td width="18%">
								<input type="text" name="username" />
							</td>
							<td width="18%">
								<html:submit  styleClass="buttonb" >查询</html:submit>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="205">
										<b class="font14">用户列表</b>
									</td>
									<td width="723" height="22" align="center"
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
				<html:form action="/adminSetNullAction">
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr align="left">
						<td height="23" bgcolor="C4F0FF">
							&nbsp;
						</td>
						<td class="td2">
				<b>用户名</b>
						</td>
					</tr>
					<logic:notEmpty name="list">
						<logic:iterate id="e" name="list" indexId="i">
							<tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>",idAF);' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>",idAF);' class=td4  onDblClick=""> 
								<td valign="top">
									<p align="center">
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="e" property="id"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</p>
								</td>
								<td>
									<bean:write name="e" property="username" />
								</td>
							<tr>
								<td colspan="9" class=td5></td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="list">
						<tr>
							<td colspan="11" height="30" style="color:red">
								没有找到与条件相符合结果!
								<br>
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
										<jsp:include page="../inc/pagination.jsp">
											<jsp:param name="url" value="adminSetNullShowAC.do" />
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
									<td width="65">
										<html:submit styleClass="buttonb"> 密码置空</html:submit>
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
