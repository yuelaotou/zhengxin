<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>

<%
String path = request.getContextPath();
%>
<html>
	<head>
		<title>个贷</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	
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
							<td background="<%=path%>/img/table_bg_line.gif" width="10">
								&nbsp;
							</td>
							<td background="<%=path%>/img/table_bg_line.gif">
								
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="200" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="228" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
										</td>
										<td width="35" class=font14>
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
												<b class="font14">未通过原因</b>
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
							cellpadding=3 align="center" style="line-height:150%">
							<tr>
								
								<td class="td4" width="20%">
								<textarea rows="5" cols="32">
								<bean:write name="reason"/>
								</textarea>
									
								</td>
							
							</tr>
						</table>

		</td></tr></table>
	</body>

</html>

