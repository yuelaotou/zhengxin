<%@ page contentType="text/html; charset=UTF-8" import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>

<%
	String path = request.getContextPath();
%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.receiveacc.action.GatheringAccTbShowAC"%>

<%
	Object pagination = request.getSession(false).getAttribute(
			GatheringAccTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">

</head>
<script language="javascript" src="<%=path%>/js/common.js">

</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="<%=path%>/sysloan/recieveAccForwardURLAC.do" class=a2>扣款账号修改</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										扣款账号维护
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
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">申请贷款&gt;扣款账号修改</font>
									</td>
									<td width="29" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="164">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="776">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/gatheringAccTbFindAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%">
								<html:text name="gatheringAccAF" property="contractId"
									styleClass="input3" styleId="txtsearch"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%">
								<html:text name="gatheringAccAF" property="borrowerName"
									styleClass="input3" styleId="txtsearch"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%" align="center">
								<html:text name="gatheringAccAF" property="cardNum"
									styleClass="input3" styleId="txtsearch"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="4%" class="td1">
								&nbsp;
							</td>
							<td width="9%" class=td7>
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
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
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="163">
										<b class="font14">扣款账号修改列表</b>
									</td>
									<td width="777" height="22" align="center"
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
				<html:form action="/gatheringAccPrintAC.do" style="margin: 0">

					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('contractid')">合同编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="contractid">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('borrowerName')">借款人姓名 </a>
								<logic:equal name="pagination" property="orderBy"
									value="borrowerName">
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
								扣款银行
							</td>
							<td align="center" class=td2>
								原扣款银行账号
							</td>
							<td align="center" class=td2>
								新扣款银行账号
							</td>
							<td align="center" class=td2>
								账号修改日期
							</td>
							<td align="center" class=td2>
								修改人
							</td>

						</tr>
						<logic:notEmpty name="gatheringAccAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="gatheringAccAF" property="list" id="element"
								indexId="i">
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
									onMouseOut='gotoColorpp("<%=i%>", idAF);'
									class="<%=strClass%>" onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="receiveBankModifyId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="contractId"
												format="000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="borrowerName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="cardNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanBankId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="oldBankAcc" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="newBankAcc" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="modifyDate" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="oprator" />
										</p>
									</td>

								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="gatheringAccAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>

						</logic:empty>
						<tr>
							<td colspan="9"></td>
						</tr>
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
												<jsp:param name="url" value="gatheringAccTbShowAC.do" />
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
											<logic:notEmpty name="gatheringAccAF" property="list">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</logic:notEmpty>
											<logic:empty name="gatheringAccAF" property="list">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</logic:empty>
										</td>


									</tr>
								</table>
							</td>
						</tr>

					</table>

				</html:form>
	</table>
</body>
</html:html>


