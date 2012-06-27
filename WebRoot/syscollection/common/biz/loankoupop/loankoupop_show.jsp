<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page
	import="org.xpup.hafmis.syscollection.common.biz.loankoupop.action.LoanKouPopShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			LoanKouPopShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>查询扣款账号</title>
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script src="<%=path%>/js/common.js"></script>
	</head>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
		<jsp:include page="../../../../inc/sort.jsp">
			<jsp:param name="url" value="loanKouPopShowAC.do" />
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
							<td background="<%=path%>/img/table_bg_line.gif" width="90">
								&nbsp;
							</td>
							<td width="235" background="<%=path%>/img/table_bg_line.gif"></td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right"></td>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="300">
											<b class="font14">银行:<bean:write name="loanKouPopAF"
													property="loanBankID" /> 批次号: <bean:write
													name="loanKouPopAF" property="batchNum" />
											</b>
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
					<form name="idAF" action="" style="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr>
								<td align="center" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td align="center" class=td2>
									扣款账号
								</td>
								<td align="center" class=td2>
									合同编号
								</td>
								<td align="center" class=td2>
									职工编号
								</td>
								<td align="center" class=td2>
									单位编号
								</td>
								<%-- 
								<td align="center" class=td2>
									应扣金额
								</td>
								
								<td align="center" class=td2>
									扣往年金额
								</td>
								<td align="center" class=td2>
									扣本年余额
								</td>
								--%>
								<td align="center" class=td2>
									扣款金额
								</td>
							</tr>
							<logic:notEmpty name="loanKouPopAF" property="list">
								<logic:iterate id="e" name="loanKouPopAF" property="list"
									indexId="i">
									<tr id="tr<%=i%>"
										onclick='gotoClick("tr<%=i%>","s<%=i%>", idAF);'
										onMouseOver='this.style.background="#eaeaea"'
										onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", idAF);' class=td4>

										<td valign="top">
											<p align="left">
												<input id="s<%=i%>" type="radio" name="id"
													value="<bean:write name="e" property="loanKouAcc"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>
										<td>
											<bean:write name="e" property="loanKouAcc" />
										</td>
										<td>
											<bean:write name="e" property="contractId" />
										</td>
										<td>
											<bean:write name="e" property="empId" />
										</td>
										<td>
											0<bean:write name="e" property="orgId" />
										</td>
										<%-- 
										<td>
											<bean:write name="e" property="shouldKouBalance" />
										</td>
										
										<td>
											<bean:write name="e" property="kouPreBalance" />
										</td>
										<td>
											<bean:write name="e" property="kouCurBalance" />
										</td>
										--%>
										<td>
											<bean:write name="e" property="kouBalance" />
										</td>
									</tr>
									<tr>
										<td colspan="11" class=td5></td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="loanKouPopAF" property="list">
								<tr>
									<td colspan="11" height="30" style="color:red">
										没有找到与条件相符合结果！
									</td>
								</tr>
								<tr>
									<td colspan="11" class=td5></td>
								</tr>
							</logic:empty>
						</table>
					</form>
					<form name="form1" style="margin: 0">
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
													<jsp:param name="url" value="loanKouPopShowAC.do" />
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
												<input type="button" name="Submit" value="关闭"
													class="buttona" onClick="javascript:window.close();">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>
