<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="org.xpup.hafmis.sysloan.dataready.bank.action.ShowBankAC"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>银行设置</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
<script src="<%=path%>/js/common.js"></script>
<script language="javascript" src="js/common.js">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}
</script>
<script>
  function reportErrors(){

	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
</script>
<script>
function checkData_YM(){
	if(document.all.office.value.trim()==""){
	   alert("办事处不许为空!");
	   return false;
	   }
	 if(document.all.bankName.value.trim()==""){
	   alert("归集银行不许为空!");
	   return false;
	   }
}
function verdictOffice(){
	var officecode=document.all.office.value.trim();
		document.URL=('bankOfficeAC.do?officecode='+officecode);
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onLoad="reportErrors()">
	<html:form action="/bankAddAC">
		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="<%=path%>/img/table_left.gif" width="7" height="37">
								<br>
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="55">
								&nbsp;
								<br>
							</td>
							<td width="235" background="<%=path%>/img/table_bg_line.gif">
								&nbsp;
								<br>
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
											<br>
										</td>
										<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<a href="#" class=a1>数据准备</a><font color="00B5DB">&gt;</font><a
												href="#" class=a1>银行维护</a>
											<br>
										</td>
										<td width="29" class=font14>
											&nbsp;
											<br>
										</td>
									</tr>
								</table>
								<br>
							</td>
							<td width="9">
								<img src="<%=path%>/img/table_right.gif" width="9" height="37">
								<br>
							</td>
						</tr>
					</table>
					<br>
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
											<b class="font14">银 行 新 增</b>
											<br>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center">
											&nbsp;
											<br>
										</td>
									</tr>
								</table>
								<br>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<html:hidden name="bankAF" property="id" />
						<logic:equal name="bankAF" property="type" value="修改">
							<tr>
								<td width="17%" class="td1" algin="center">
									办事处
									<font color="#FF0000">*</font>
								</td>
								<td width="22%">
									<html:select property="office" styleClass="input4"
										name="bankAF" disabled="true" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="officeList1" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td class="td1" width="17%" algin="center">
									归集银行
									<font color="#FF0000">*</font>
								</td>
								<td width="22%">
									<html:select property="bankName" styleClass="input4"
										name="bankAF" disabled="true" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="bankList1" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="bankAF" property="type" value="修改">
							<tr>
								<td width="17%" class="td1" algin="center">
									办事处
									<font color="#FF0000">*</font>
								</td>
								<td width="22%">
									<html:select property="office" styleClass="input4"
										name="bankAF" onchange="verdictOffice();"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="officeList1" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td class="td1" width="17%" algin="center">
									归集银行
									<font color="#FF0000">*</font>
								</td>
								<td width="22%">
									<html:select property="bankName" styleClass="input4"
										name="bankAF" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="bankList1" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
						</logic:notEqual>
						<tr id="tr1">
							<td width="19%" class="td1">
								中心委托贷款账号
								
							</td>
							<td width="31%">
								<html:text name="bankAF" property="loanAcc" styleClass="input3"
									maxlength="100" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="18%" class="td1">
								中心利息账号
							</td>
							<td width="32%" colspan="2">
								<html:text name="bankAF" property="interestAcc"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr id="tr1">
							<td width="19%" class="td1">
								划出账号
								
							</td>
							<td width="31%">
								<html:text name="bankAF" property="outAccount" styleClass="input3"
									maxlength="100" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="18%" class="td1">
								划入账号
							</td>
							<td width="32%" colspan="2">
								<html:text name="bankAF" property="inAccount"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr id="tr1">
							<td width="19%" class="td1">
								银行行长
							</td>
							<td width="31%">
								<html:text name="bankAF" property="bankPrisident"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="18%" class="td1">
								行长电话
							</td>
							<td>
								<html:text name="bankAF" property="bankPrisidentTel"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr id="tr1">
							<td width="19%" class="td1">
								联系人
							</td>
							<td width="31%">
								<html:text name="bankAF" property="contactPrsn"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="18%" class="td1">
								联系人电话
							</td>
							<td>
								<html:text name="bankAF" property="contactTel"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr id="tr1">
							<td width="19%" class="td1">
								联系人职务
							</td>
							<td width="31%">
								<html:text name="bankAF" property="business" styleClass="input3"
									maxlength="100" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="18%" class="td1">
								&nbsp;
							</td>
							<td colspan="2">
								&nbsp;
							</td>
						</tr>
						<html:hidden name="bankAF" property="type" />
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkData_YM();">
												<bean:message key="button.sure" />
											</html:submit>
											<br>
										</td>
										<td width="70">
											&nbsp;
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.back" />
											</html:submit>
											<br>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
		</table>
	</html:form>
</body>
</html:html>
