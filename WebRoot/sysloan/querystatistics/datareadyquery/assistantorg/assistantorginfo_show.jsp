<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.action.AssistantorgQueryShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			AssistantorgQueryShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>统计查询</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js"></script>
<script>
function onpback(){
window.close();
}
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
							&nbsp;
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
										<font color="00B5DB">统计查询&gt;代理机构</font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="150">
										<b class="font14">代 理 机 构</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="742">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/assistantOrgInfoPrintAC.do" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td class="td1" width="17%">
								代理机构名称
							</td>
							<td class="td4">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.assistantOrgName"
									styleClass="input3" readonly="true" />
							</td>
							<td class="td1" width="17%">
								法人代表
							</td>
							<td class="td4">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.artfclprsn"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								组织机构代码
							</td>
							<td>
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.code" styleClass="input3"
									readonly="true" />
							</td>
							<td class="td1">
								代理机构地址
							</td>
							<td>
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.assistantOrgAddr"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								成立日期
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.basedDate" styleClass="input3"
									readonly="true" />
							</td>
							<td width="17%" class="td1">
								法人证件类型
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.artfclprsnCardKind"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								法人证件号码
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.artfclprsnCardNum"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								批准机关
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.allowDept" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								批准文号
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.allowId" styleClass="input3"
									readonly="true" />
							</td>
							<td width="17%" class="td1">
								协议签订日期
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.agreementStartDate"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								协议到期日期
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.agreementEndDate"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								开户银行
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.paybank" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								开户行账号
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.payacc" styleClass="input3"
									readonly="true" />
							</td>
							<td width="17%" class="td1">
								联系人
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.contactPrsn"
									styleClass="input3" readonly="true" />
							</td>
						</tr>

						<tr id="tr1">
							<td width="17%" class="td1">
								联系电话
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.contactTel"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								职务
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.business" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr id="tr1">
							<td width="17%" class="td1">
								注册资金
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.registerFund"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class="td1">
								备注
							</td>
							<td width="33%">
								<html:text name="assistantorgQueryTbAF"
									property="assistantorgQueryTbDTO.remark" styleClass="input3"
									readonly="true" />
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
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return onpback();">
												<bean:message key="button.back" />
											</html:submit>
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

