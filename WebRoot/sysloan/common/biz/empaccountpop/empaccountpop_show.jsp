<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.common.biz.empaccountpop.action.*"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			EmpAccountPopShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>职工明细账</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js"></script>
</head>
<script language="javascript">

</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="showEmpAccountPopListAC.do" />
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
						<td background="<%=path%>/img/table_bg_line.gif" align="right">

						</td>
						<td width="1">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="241">
										<b class="font14">职工明细账查询条件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="679">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/findEmpAccountPopListAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">

						<tr>
							<td width="13%" class="td1" height="27">
								业务类型
							</td>
							<td width="18%" height="27">
								<html:select property="bizType" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="bstypeMap"
										name="empAccountPopAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="11%" class="td1" height="27">
								发生时间
							</td>
							<td width="21%" height="27">
								<html:text name="empAccountPopAF" property="settDateA"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="11%" class="td1" height="27">
								☞
							</td>
							<td width="21%" height="27">
								<html:text name="empAccountPopAF" property="settDateB"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr align="right">
							<td colspan="4">
								<html:submit property="method" styleClass="buttona">
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
							合计：借方发生额
							<u>：<bean:write name="empAccountPopAF" property="debitTotal" />
							</u> 贷方发生额
							<u>：<bean:write name="empAccountPopAF" property="creditTotal" />
							</u> 利息
							<u>：<bean:write name="empAccountPopAF"
									property="interestTotal" /> </u>
						</td>
					</tr>
				</table>
				<form name="idAF" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="143">
											<b class="font14">职工明细账</b>
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
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								凭证编号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('a101.biz_type')">业务类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="a101.biz_type">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								发生时间
							</td>
							<td align="center" class=td2>
								借方发生额
							</td>
							<td align="center" class=td2>
								贷方发额
							</td>
							<td align="center" class=td2>
								利息
							</td>
						</tr>
						<logic:notEmpty name="empAccountPopAF" property="list">
							<%
										int j = 0;
										String strClass = "";
							%>
							<logic:iterate id="elments" name="empAccountPopAF"
								property="list" indexId="i">
								<%
										j++;
										if (j % 2 == 0) {
											strClass = "td8";
										} else {
											strClass = "td9";
										}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>", idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass%>"
									onDblClick=''>
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="elments" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="elments" property="docNum" />
									</td>
									<td height="25">
										<bean:write name="elments" property="bizType" />
									</td>
									<td height="25">
										<bean:write name="elments" property="settDate" />
									</td>
									<td height="25">
										<bean:write name="elments" property="debit" />
									</td>
									<td height="25">
										<bean:write name="elments" property="credit" />
									</td>
									<td height="25">
										<bean:write name="elments" property="interest" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="empAccountPopAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
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
											<jsp:param name="url" value="showEmpAccountPopListAC.do" />
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
										<input type="button" name="Submit" value="关闭" class="buttona"
											onClick="window.close();">
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
</html:html>
