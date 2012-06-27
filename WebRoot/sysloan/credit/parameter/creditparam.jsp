<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>征信系统-参数设置</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" ></script>	
</head>
<body bgcolor="#FFFFFF" text="#000000">
	<html:form method="post" action="/creditParamSaveAC">
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
											background="<%=path%>/img/buttonbl.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											参数设置
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
										<td width="165" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">征信系统&gt;参数设置</font>
										</td>
										<td width="98" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="166">
											<b class="font14">参数设置</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="738">
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
							<td width="13%" class="td1">
								数据格式版本号
								<font color="#FF0000">*</font>
							</td>
							<td width="20%" height="31">
								<html:text name="creditParamAF" property="sjgsbbh"
									styleClass="input3" maxlength="3"></html:text>
							</td>
							<td width="13%" class="td1">
								金融机构代码
								<font color="#FF0000">*</font>
							</td>
							<td width="20%" height="31">
								<html:text name="creditParamAF" property="jrjgdm"
									styleClass="input3" maxlength="14"></html:text>
							</td>
							<td width="13%" class="td1">
								上传报文版本号
								<font color="#FF0000">*</font>
							</td>
							<td width="20%" height="31">
								<html:text name="creditParamAF" property="scbwbbh"
									styleClass="input3" maxlength="3"></html:text>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								发生地点
								<font color="#FF0000">*</font>
							</td>
							<td width="20%" height="31">
								<html:text name="creditParamAF" property="fsdd"
									styleClass="input3" maxlength="6"></html:text>
							</td>
							<td width="13%" class="td1">
								币种
								<font color="#FF0000">*</font>
							</td>
							<td width="20%" height="31">
								<html:text name="creditParamAF" property="bz"
									styleClass="input3" maxlength="3"></html:text>
							</td>
							<td width="13%" class="td1">
								联系人
								<font color="#FF0000">*</font>
							</td>
							<td width="20%" height="31">
								<html:text name="creditParamAF" property="lxr"
									styleClass="input3" maxlength="30"></html:text>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								联系电话
								<font color="#FF0000">*</font>
							</td>
							<td width="20%" height="31">
								<html:text name="creditParamAF" property="lxdh"
									styleClass="input3" maxlength="25"></html:text>
							</td>
							<td width="13%" class="td1">
								&nbsp;
							</td>
							<td width="20%" height="31">
								&nbsp;
							</td>
							<td width="13%" class="td1">
								&nbsp;
							</td>
							<td width="20%" height="31">
								&nbsp;
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
												<bean:message key="button.sure" />
											</html:submit>
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
