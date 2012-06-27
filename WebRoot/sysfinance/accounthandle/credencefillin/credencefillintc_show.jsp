<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page
	import="org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action.CredenceFillinTcShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = session
			.getAttribute(CredenceFillinTcShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>损益结转</title>
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	<script>
  function reportErrors() {
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
  }
  function onSettleIncAndDec(){
	  var x=confirm("是否要进行全部结转！");
	  if(x){
		return true;
	  }else{
	    return false;
	  }
  }
  </script>
	<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();"
		onContextmenu="return false">
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
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">账户处理&gt;损益结转</font>
										</td>
										<td class=font14>
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
					<html:form action="/credenceFillinTcFindAC">
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
												align="center" width="731">
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
								<td width="15%" class="td1">
									科目代码
								</td>
								<td width="35%" colspan="3">
									<html:text name="credenceFillinTcAF"
										property="credenceFillinTcFindDTO.subjectcode"
										styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
								</td>
								<td width="15%" class="td1">
									科目名称
								</td>
								<td width="35%">
									<html:text name="credenceFillinTcAF"
										property="credenceFillinTcFindDTO.subjectName"
										styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
								</td>
							</tr>
							<tr>
								<td class="td1" width="15%">
									凭证日期
									<br>
								</td>
								<td width="15%">
									<html:text name="credenceFillinTcAF"
										property="credenceFillinTcFindDTO.credenceDateStart"
										styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
								</td>
								<td width="5%" align="center">
									至
								</td>
								<td width="15%">
									<html:text name="credenceFillinTcAF"
										property="credenceFillinTcFindDTO.credenceDateEnd"
										styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
								</td>
								<td width="15%" class="td1">
									办事处
								</td>
								<td>
									<html:select name="credenceFillinTcAF"
										property="credenceFillinTcFindDTO.office" styleClass="input4"
										onkeydown="enterNextFocus1();">
										<html:option value="">全部</html:option>
										<html:options collection="officeList1" property="value"
											labelProperty="label" />
									</html:select>
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
					<html:form action="/credenceFillinTcMainTainAC">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td class=h4>
									合计： 借方金额
									<u>：<bean:write name="credenceFillinTcAF"
											property="sumDebitMoney" />
									</u>贷方金额
									<u>：<bean:write name="credenceFillinTcAF"
											property="sumCreditMoney" />
									</u>
								</td>
							</tr>
						</table>
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
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr align="center">
								<td height="23" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td class="td2">
									<a href="javascript:sort('f201.subject_code')">科目代码</a>
									<logic:equal name="pagination" property="orderBy"
										value="f201.subject_code">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td class="td2">
									科目名称
								</td>
								<td class="td2">
									<a
										href="javascript:sort('nvl(sum(f201.credit),0)-nvl(sum(f201.debit),0)')">借方金额</a>
									<logic:equal name="pagination" property="orderBy"
										value="nvl(sum(f201.credit),0)-nvl(sum(f201.debit),0)">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td class="td2">
									<a
										href="javascript:sort('nvl(sum(f201.debit),0)-nvl(sum(f201.credit),0)')">贷方金额</a>
									<logic:equal name="pagination" property="orderBy"
										value="nvl(sum(f201.debit),0)-nvl(sum(f201.credit),0)">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td class="td2">
									办事处
								</td>
							</tr>
							<logic:notEmpty name="credenceFillinTcAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate id="credenceFillinTcListDTO"
									name="credenceFillinTcAF" property="list" indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr class="<%=strClass%>">
										<td>
											<input id="checkbox" type="checkbox" name="checkbox"
												value="<bean:write name="credenceFillinTcListDTO" property="subjectcode"/>,<bean:write name="credenceFillinTcListDTO" property="office"/>,<bean:write name="credenceFillinTcListDTO" property="debit"/>,<bean:write name="credenceFillinTcListDTO" property="credit"/>,<bean:write name="credenceFillinTcListDTO" property="bySubjectDirection"/>">
										</td>
										<td>
											<p>
												<bean:write name="credenceFillinTcListDTO"
													property="subjectcode" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="credenceFillinTcListDTO"
													property="subjectName" />
											</p>
										</td>
										<td align="right">
											<p>
												<bean:write name="credenceFillinTcListDTO" property="debit"
													format="#,##0.00" />
											</p>
										</td>
										<td align="right">
											<p>
												<bean:write name="credenceFillinTcListDTO" property="credit"
													format="#,##0.00" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="credenceFillinTcListDTO"
													property="strOffice" />
											</p>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="credenceFillinTcAF" property="list">
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
													<jsp:param name="url" value="credenceFillinTcShowAC.do" />
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
											<logic:notEmpty name="credenceFillinTcAF" property="list">
												<td width="69" align="center">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.SettleIncAndDecInfo" />
													</html:submit>
												</td>
												<td width="69" align="center">
													<html:submit property="method" styleClass="buttona"
														styleClass="buttonb" onclick="return onSettleIncAndDec();">
														<bean:message key="button.SettleIncAndDecInfo.all" />
													</html:submit>
												</td>
											</logic:notEmpty>
											<logic:empty name="credenceFillinTcAF" property="list">
												<td width="69" align="center">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.SettleIncAndDecInfo" />
													</html:submit>
												</td>
												<td width="69" align="center">
													<html:submit property="method" styleClass="buttona"
														styleClass="buttonb" disabled="true">
														<bean:message key="button.SettleIncAndDecInfo.all" />
													</html:submit>
												</td>
											</logic:empty>
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
</html>
