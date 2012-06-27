<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.common.biz.queryflow.action.CollectionFlowShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			CollectionFlowShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>归集流水账</title>
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
												<b class="font14">归 集 业 务 列 表</b>
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
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td class=h4>
									合计： 借方金额
									<u>:<bean:write name="orgbusinessflowAF"
											property="debitTotal" format="0.00" /> </u> 贷方金额
									<u>：<bean:write name="orgbusinessflowAF"
											property="creditTotal" format="0.00" /> </u>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr bgcolor="1BA5FF">
								<td align="center" height="6" colspan="20"></td>
							</tr>
							<tr>
								<td align="center" height="23" bgcolor="C4F0FF">
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('orgHAFAccountFlow.noteNum')">结算号</a>
									<logic:equal name="pagination" property="orderBy"
										value="orgHAFAccountFlow.noteNum">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('orgHAFAccountFlow.docNum')">凭证编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="orgHAFAccountFlow.docNum">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('orgHAFAccountFlow.org.id')">单位编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="orgHAFAccountFlow.org.id">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('orgHAFAccountFlow.org.orgInfo.name')">单位名称</a>
									<logic:equal name="pagination" property="orderBy"
										value="orgHAFAccountFlow.org.orgInfo.name">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('orgHAFAccountFlow.biz_Type')">业务类型</a>
									<logic:equal name="pagination" property="orderBy"
										value="orgHAFAccountFlow.biz_Type">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('orgHAFAccountFlow.settDate')">发生日期</a>
									<logic:equal name="pagination" property="orderBy"
										value="orgHAFAccountFlow.settDate">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('orgHAFAccountFlow.personTotal')">人数</a>
									<logic:equal name="pagination" property="orderBy"
										value="orgHAFAccountFlow.personTotal">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('orgHAFAccountFlow.moneyTotal')">发生金额</a>
									<logic:equal name="pagination" property="orderBy"
										value="orgHAFAccountFlow.moneyTotal">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									发生利息
								</td>
								<td align="center" class=td2>
									发生方向
								</td>
								<td align="center" class=td2>
									业务状态
								</td>
							</tr>

							<logic:notEmpty name="orgbusinessflowAF" property="list">
								<%
										int j = 0;
										String strClass = "";
								%>
								<logic:iterate id="element" name="orgbusinessflowAF"
									property="list" indexId="i">
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
										<td valign="top">
											<p align="left">
												<input id="s<%=i%>" type="radio" name="id"
													value="<bean:write name="element" property="id"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>
										<td>
											<bean:write name="element" property="noteNum" />
										</td>
										<td>
											<bean:write name="element" property="docNum" />
										</td>
										<td>
											<bean:write name="element" property="org.id" format="000000" />
										</td>
										<td>
											<bean:write name="element" property="org.orgInfo.name" />
										</td>
										<td>
											<bean:write name="element" property="bis_type" />
										</td>
										<td>
											<bean:write name="element" property="settDate" />
										</td>
										<td>
											<bean:write name="element" property="personTotal" />
										</td>
										<td>
											<bean:write name="element" property="moneyTotal" />
										</td>
										<td>
											<bean:write name="element" property="interest" />
										</td>
										<td>
											<bean:write name="element" property="setdirection" />
										</td>
										<td>
											<bean:write name="element" property="status" />
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>

							<logic:empty name="orgbusinessflowAF" property="list">
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
								<td align="center">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr class="td1">
											<td>
												<table width="100%" border="0" align="center"
													cellpadding="0" cellspacing="0">
													<tr>
														<td align="left">
															共
															<bean:write name="pagination" property="nrOfElements" />
															项
														</td>
														<td align="right">
															<jsp:include page="../../../../inc/pagination.jsp">
																<jsp:param name="url" value="collectionFlowShowAC.do" />
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
											<td width="69" align="right">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
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
</html>
