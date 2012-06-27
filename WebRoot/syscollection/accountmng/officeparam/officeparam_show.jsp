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
	<title>办事处归集银行修改参数设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
function onCheck(){
	var v3=document.forms[0].elements["officeParaDTO.isBankModify"];
	if(v3[0].checked==false&&v3[1].checked==false){
		alert('必须二选一！');
		return false;
	}
}
function verdictLoanBankId(){
	var office=document.forms[0].elements["officeParaDTO.office"].value.trim();
	document.URL=('officeParaShowAC.do?office='+office);
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
										<font color="00B5DB">开户销户&gt;办事处银行修改参数设置</font>
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
			<html:form action="/officeParaSaveAC.do">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="136">
										<b class="font14">办事处银行修改参数设置</b>
									</td>
									<td width="710" height="22" align="center"
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
						<td class="td1">
							&nbsp;
						</td>
						<td colspan="2" class="td1">
							<html:select property="officeParaDTO.office"
								styleClass="input4" name="officeParaAF" style="width=30%;" 
								onchange="verdictLoanBankId();" onkeydown="enterNextFocus1();">
								<html:options collection="officeList1" property="value"
									labelProperty="label" />
							</html:select>
						</td>
					</tr>
					
					
					<tr id="tr1">
						<td width="3%" class="td1"> 
						<br></td>
						<td width="97%" class="td1">
							<p>
								可修改存款银行
								<html:radio name="officeParaAF" property="officeParaDTO.isBankModify" value="0" onkeydown="enterNextFocus1();"/>
								是
								<html:radio name="officeParaAF" property="officeParaDTO.isBankModify" value="1" onkeydown="enterNextFocus1();"/>
								否；
							</p>
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
										<html:submit property="method" styleClass="buttona" onclick="return onCheck();"><bean:message key="button.sure"/></html:submit>
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
