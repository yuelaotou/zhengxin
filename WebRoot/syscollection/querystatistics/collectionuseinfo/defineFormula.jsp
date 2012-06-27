<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>参数维护</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script language="javascript">
function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="loads();">
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
										valign="bottom"><font color="00B5DB">公式定义&gt;参数维护</font>
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
				<html:form action="/defineFormulaSaveAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="144">
											<b class="font14">参数设置</b>
										</td>
										<td width="550" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr id="tr1">
							<td class="td1" align="center">
								账套
							</td>
							<td class="td1">
								<html:select property="defineFormulaDto.BOOK_ID"
									styleClass="input4" name="defineFormulaFindAF"
									style="width=20%">
									<html:options collection="accountList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1" align="center">
								201
							</td>
							<td class="td1">
								公式定义：
								<html:text name="defineFormulaFindAF"
									property="defineFormulaDto.code_201" style="width=25%;"
									styleClass="input3" styleId="txtsearch"></html:text>
								&nbsp;本年业务收入&nbsp;
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1" align="center">
								202
							</td>
							<td class="td1">
								公式定义：
								<html:text name="defineFormulaFindAF"
									property="defineFormulaDto.code_202" style="width=25%;"
									styleClass="input3" styleId="txtsearch"></html:text>
								&nbsp;本年业务支出&nbsp;
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1" align="center">
								203
							</td>
							<td class="td1">
								公式定义：
								<html:text name="defineFormulaFindAF"
									property="defineFormulaDto.code_203" style="width=25%;"
									styleClass="input3" styleId="txtsearch"></html:text>
								&nbsp;本年增值收益&nbsp;
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1" align="center">
								204
							</td>
							<td class="td1">
								公式定义：
								<html:text name="defineFormulaFindAF"
									property="defineFormulaDto.code_204" style="width=25%;"
									styleClass="input3" styleId="txtsearch"></html:text>
								&nbsp;本年提取管理费用&nbsp;
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1" align="center">
								205
							</td>
							<td class="td1">
								公式定义：
								<html:text name="defineFormulaFindAF"
									property="defineFormulaDto.code_205" style="width=25%;"
									styleClass="input3" styleId="txtsearch"></html:text>
								&nbsp;本年末风险准备金总额&nbsp;
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1" align="center">
								206
							</td>
							<td class="td1">
								公式定义：
								<html:text name="defineFormulaFindAF"
									property="defineFormulaDto.code_206" style="width=25%;"
									styleClass="input3" styleId="txtsearch"></html:text>
								&nbsp;本年末风险准备金余额&nbsp;
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1" align="center">
								207
							</td>
							<td class="td1">
								公式定义：
								<html:text name="defineFormulaFindAF"
									property="defineFormulaDto.code_207" style="width=25%;"
									styleClass="input3" styleId="txtsearch"></html:text>
								&nbsp;本年末划转廉租住房补充资金&nbsp;
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1" align="center">
								208
							</td>
							<td class="td1">
								公式定义：
								<html:text name="defineFormulaFindAF"
									property="defineFormulaDto.code_208" style="width=25%;"
									styleClass="input3" styleId="txtsearch"></html:text>
								&nbsp;本年末廉租住房补充资金总额&nbsp;
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1" align="center">
								209
							</td>
							<td class="td1">
								公式定义：
								<html:text name="defineFormulaFindAF"
									property="defineFormulaDto.code_209" style="width=25%;"
									styleClass="input3" styleId="txtsearch"></html:text>
								&nbsp;本年末廉租住房补充资金余额&nbsp;
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
											<html:submit styleClass="buttona">
												<bean:message key="button.sure" />
											</html:submit>
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
