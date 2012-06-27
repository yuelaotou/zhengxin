<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.common.biz.queryflow.action.LoanFlowShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			LoanFlowShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

	<title>个贷业务流水</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<form name="idAF" action="" style="margin: 0">
		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="<%=path%>/img/table_left.gif" width="7" height="37">
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="65">
								&nbsp;
							</td>
							<td width="171" background="<%=path%>/img/table_bg_line.gif">

							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="163">
								<a href="账户处理_凭证录入_简约风格.htm"> </a>
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right"
								width="506">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">账户处理&gt;凭证录入</font>
										</td>
										<td width="100" class=font14>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="10">
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="130">
											<b class="font14">个 贷 业 务 列 表</b>
										</td>
										<td width="744" height="22" align="center"
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
						<tr>
							<td height="23" align="center" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								凭证编号
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('loankouacc')">扣款账号</a>
								<logic:equal name="pagination" property="orderBy"
									value="loankouacc">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('contractId')">合同编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="contractId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('borrowername')">借款人姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrowername">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('bizType')">业务类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="bizType">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								发放金额
							</td>
							<td align="center" class=td2>
								回收本金
							</td>
							<td align="center" class=td2>
								回收利息
							</td>
							<td align="center" class=td2>
								回收罚息
							</td>
							<td align="center" class=td2>
								呆账核销金额
							</td>
							<td align="center" class=td2>
								回收总金额
							</td>
							<td align="center" class=td2>
								挂账金额
							</td>
							<td align="center" class=td2>
								保证金
							</td>
							<td align="center" class=td2>
								保证金利息
							</td>
							<td align="center" class=td2>
								办理日期
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
							<td align="center" class=td2>
								制单人
							</td>
						</tr>
						<logic:notEmpty name="loanBusiFlowQueryAF" property="list">
							<%
										int j = 0;
										String strClass = "";
							%>
							<logic:iterate name="loanBusiFlowQueryAF" property="list"
								id="element" indexId="i">
								<%
										j++;
										if (j % 2 == 0) {
											strClass = "td8";
										} else {
											strClass = "td9";
										}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>",idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="element" property="flowHeadId"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="element" property="docNum" />
									</td>
									<td>
										<bean:write name="element" property="loanKouAcc" />
									</td>
									<td>
										<bean:write name="element" property="contractId" />
									</td>
									<td>
										<bean:write name="element" property="borrowerName" />
									</td>
									<td>
										<bean:write name="element" property="bizType" />
									</td>
									<td>
										<bean:write name="element" property="occurMoney" />
									</td>
									<td>
										<bean:write name="element" property="reclaimCorpus" />
									</td>
									<td>
										<bean:write name="element" property="reclaimAccrual" />
									</td>
									<td>
										<bean:write name="element" property="realPunishInterest" />
									</td>
									<td>
										<bean:write name="element" property="badDebt" />
									</td>
									<td>
										<bean:write name="element" property="reclaim" />
									</td>
									<td>
										<bean:write name="element" property="putUpMoney" />
									</td>
									<td>
										<bean:write name="element" property="bail" />
									</td>
									<td>
										<bean:write name="element" property="bailAccrual" />
									</td>
									<td>
										<bean:write name="element" property="bizDate" />
									</td>
									<td>
										<bean:write name="element" property="bizSt" />
									</td>
									<td>
										<bean:write name="element" property="makePerson" />
									</td>
									<td style="display:none">
										<bean:write name="element" property="originalitybizType" />
									</td>
									<td style="display:none">
										<bean:write name="element" property="flowHeadId" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="loanBusiFlowQueryAF" property="list">
							<tr>
								<td colspan="9" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="center">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
															<jsp:param name="url" value="loanFlowShowAC.do" />
														</jsp:include>
													</td>
												</tr>
											</table>
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
										<td width="90" align="right">
											<input type="button" name="Submit42" value="关闭"
												class="buttona" onClick="javascript:window.close();">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html:html>
