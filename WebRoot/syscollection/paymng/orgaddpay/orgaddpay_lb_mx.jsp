<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.orgaddpay.action.OrgaddpayTbWindowShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			OrgaddpayTbWindowShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>单位补缴清册</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script>
<script type="text/javascript">
function gotoShow(){
	return false;
}
function loads(){
	var id=document.all.orgid.value;
	document.all.orgid.value=formatTen(id)+id;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="loads();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="orgaddpayTbWindowShowAC.do" />
	</jsp:include>
	<html:form action="/orgaddpayTbMXPrintAC" style="margin: 0">
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
								&nbsp;
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="133" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">缴存管理<font color="00B5DB">&gt;</font>单位补缴
											
										</td>
										<td width="130" class=font14>
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
											<b class="font14">缴 存 信 息</b>
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



					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								单位编号：
							</td>
							<td>
								<html:text name="orgaddpayTaAF" property="orgid"
									styleClass="input3" styleId="txtsearch" readonly="true" />
							</td>
							<td width="17%" class="td1">
								单位名称：
							</td>
							<td width="33%">
								<html:text name="orgaddpayTaAF" property="name"
									styleClass="input3" styleId="txtsearch" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								补缴起始年月：
							</td>
							<td>
								<html:text name="orgaddpayTaAF" property="startPayMonth"
									styleClass="input3" styleId="txtsearch" readonly="true" />
							</td>
							<td class="td1" width="17%">
								补缴终止年月：
							</td>
							<td width="33%">
								<html:text name="orgaddpayTaAF" property="payMonth"
									styleClass="input3" styleId="txtsearch" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								结算号：
							</td>
							<td>
								<html:text name="orgaddpayTaAF" property="noteNum"
									styleClass="input3" styleId="txtsearch" readonly="true" />
							</td>
							<td class="td1" width="17%">
								凭证编号：
							</td>
							<td width="33%">
								<html:text name="orgaddpayTaAF" property="docNum"
									styleClass="input3" styleId="txtsearch" readonly="true" />
							</td>
						</tr>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								总计： 补缴人数
								<bean:write name="orgaddpayTaAF"
									property="monthPaymentHead.personCount" />
								单位补缴金额
								<bean:write name="orgaddpayTaAF"
									property="monthPaymentHead.orgSumpay" />
								个人补缴金额
								<bean:write name="orgaddpayTaAF"
									property="monthPaymentHead.empSumpay" />
								补缴金额
								<bean:write name="orgaddpayTaAF"
									property="monthPaymentHead.addpayMoney" />
							</td>
						</tr>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">单位补缴列表</b>
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
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td align="center" class=td2>
								<a href="javascript:sort('monthPaymentTail.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="monthPaymentTail.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('monthPaymentTail.empName')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="monthPaymentTail.empName">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								补缴年月
							</td>
							<td align="center" class=td2>
								单位补缴金额
							</td>
							<td align="center" class=td2>
								个人补缴金额
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('monthPaymentTail.sumPay')">补缴金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="monthPaymentTail.sumPay">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td>
								<input type="hidden" name="newNoteNum" value="">
								<input type="hidden" name="month" value="">
								<input type="hidden" name="orgId" value="">
							</td>
						</tr>
						<logic:notEmpty name="orgaddpayTaAF" property="list">
							<logic:iterate name="orgaddpayTaAF" property="list" id="element"
								indexId="i">


								<tr id="tr1" onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='this.style.background="#ffffff"'>

									<td valign="top">
										<p>
											<bean:write name="element" property="emp.empId"
												format="000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="emp.empInfo.name" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element"
												property="monthPaymentHead.payMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="orgRealPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empRealPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="sumPay" />
										</p>
									</td>
								</tr>
								<tr>
									<td colspan="6" class=td5>
										<html:hidden name="orgaddpayTaAF"
											property="monthPaymentHead.id" styleClass="input3"
											styleId="txtsearch" />

										<input type="hidden" name="paymentid"
											value='<bean:write name="orgaddpayTaAF" property="monthPaymentHead.paymentHead.id" />'>
										<input type="hidden"
											name="monthPaymentHead_paymentHead_org_id"
											value='<bean:write name="orgaddpayTaAF" property="monthPaymentHead.paymentHead.org.id" />'>

									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="orgaddpayTaAF" property="list">
							<tr>
								<td colspan="6" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
							<tr>
								<td colspan="6" class=td5></td>
							</tr>
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="center">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr align="center">
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="orgaddpayTbWindowShowAC.do" />
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
<%--										<td width="70">--%>
<%--											<html:submit property="method" styleClass="buttona">--%>
<%--												<bean:message key="button.print" />--%>
<%--											</html:submit>--%>
<%--										</td>--%>
										<td width="70">
											<input name="button" type="button" class="buttona" value="关闭"
												onClick="javascript:window.close();">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
