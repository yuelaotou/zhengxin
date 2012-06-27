<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>

<html:html lang="true">
<head>
	<title>邮件系统管理</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	<script language="javascript" src="js/common.js">
  </head>
<script language="javascript" ></script>
<script language="javascript" type="text/javascript">
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function verifyAddress(checkemail){  
　　　　　　var email = checkemail;
　　　　　　var pattern = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_-])+.([a-zA-Z0-9_-])+/;  
　　　　　　flag = pattern.test(email);  
　　　　　　if(flag)  
　　　　　　{  
　　　　　　　return true;  
　　　　　　}  
　　　　　　else  
　　　　　　　{  
　　　　　　　　return false;  
　　　　　　　 }  
 }  
function checkinfo(){
	var addresserMail=document.all.addresserMail.value.trim();
	var addresserPassword=document.all.addresserPassword.value.trim();
	var addresseeA=document.all.addresseeA.value.trim();
	var addresseeB=document.all.addresseeB.value.trim();
	var subjectName=document.all.subjectName.value.trim();
		if(!verifyAddress(addresserMail)){
		document.all.addresserMail.focus();
		alert('发件人邮箱录入不正确！');
		return false;
		}
		if(addresserPassword==''){
		document.all.addresserPassword.focus();
		alert('密码不能为空！');
		return false;
		}
		if(!verifyAddress(addresseeA)){
		document.all.addresseeA.focus();
		alert('收件人邮箱录入不正确！');
		return false;
		}
		if(addresseeB!=''){
		if(!verifyAddress(addresseeB)){
		document.all.addresseeB.focus();
		alert('收件人备用邮箱录入不正确！');
		return false;
		}
		if(subjectName==''){
		document.all.subjectName.focus();
		alert('发送题目不能为空！');
		return false;
		}
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors();" onContextmenu="return false">
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
						<td width="350" background="<%=path%>/img/table_bg_line.gif">
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">邮件基本信息</font><font color="00B5DB">
									</td>
									<td width="74" class=font14>
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
							<table width="94%" border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">基本信息</b>
									</td>
									<td width="750" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/mailinfoTaNewAC.do" style="margin: 0">
				<html:hidden name="mailinfoAF" property="mailId"/>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=3 align="center">
									<tr id="tr1">
										<td width="17%" class="td1">
											发件人信箱
											<font color="#FF0000">*</font>
										</td>
										<td width="33%">
										<html:text  onkeydown="enterNextFocus1();" name="mailinfoAF"
													property="addresserMail" styleClass="input3" maxlength="50"/>
										</td>
										<td width="17%" class="td1">
											密码
											<font color="#FF0000">*</font>
										</td>
										<td width="33%">
									   <html:password  onkeydown="enterNextFocus1();" name="mailinfoAF"
													property="addresserPassword" styleClass="input3" maxlength="50"/>
										</td>
									</tr>
									<tr id="tr1">
										<td width="17%" class="td1">
											收件人信箱
											<font color="#FF0000">*</font>
										</td>
										<td width="33%">
										<html:text  onkeydown="enterNextFocus1();" name="mailinfoAF"
													property="addresseeA" styleClass="input3" maxlength="50"/>
										</td>
										<td width="17%" class="td1">
											收件人信箱（备用）
										</td>
										<td width="33%">
										<html:text  onkeydown="enterNextFocus1();" name="mailinfoAF"
													property="addresseeB" styleClass="input3" maxlength="50"/>
										</td>
									</tr>
									<tr id="tr1">
										<td width="17%" class="td1">
											主题
											<font color="#FF0000">*</font>
										</td>
										<td width="33%">
										<html:text  onkeydown="enterNextFocus1();" name="mailinfoAF"
													property="subjectName" styleClass="input3" maxlength="50"/>
										</td>
										<td></td><td></td>
									</tr>
								</table>
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit  styleClass="buttona" onclick="return checkinfo()">
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
