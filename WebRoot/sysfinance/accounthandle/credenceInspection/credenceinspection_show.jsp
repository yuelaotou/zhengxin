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
	<title>财务核算</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript"></script>
<script language="javascript" type="text/javascript">
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	 document.forms[0].elements["credenceInspectionFindDTO.credenceDateSt"].value="";
	 document.forms[0].elements["credenceInspectionFindDTO.credenceDateEnd"].value="";
	 document.forms[0].elements["credenceInspectionFindDTO.credenceNumSt"].value="";
	 document.forms[0].elements["credenceInspectionFindDTO.credenceNumEnd"].value="";
	 document.forms[0].elements["credenceInspectionFindDTO.credenceCharacter"].value="";
	 document.forms[0].elements["credenceInspectionFindDTO.office"].value="";
	alert(message);
	</logic:messagesPresent>
}

function search(){
    var count=0;
    var credenceDateSt = document.forms[0].elements["credenceInspectionFindDTO.credenceDateSt"].value.trim();
	var credenceDateEnd = document.forms[0].elements["credenceInspectionFindDTO.credenceDateEnd"].value.trim();	
		if(credenceDateSt!=""){
		if(!checkDate(document.forms[0].elements["credenceInspectionFindDTO.credenceDateSt"])){
			document.forms[0].elements["credenceInspectionFindDTO.credenceDateSt"].value="";
			return false;
		}
		count=count+1;
	}
		if(credenceDateEnd!=""){
		if(!checkDate(document.forms[0].elements["credenceInspectionFindDTO.credenceDateEnd"])){
			document.forms[0].elements["credenceInspectionFindDTO.credenceDateEnd"].value="";
			return false;
		}
		count=count+1;
	}
	if(count<=0){
		alert('起始日期和结束日期至少输入一项！');
		return false;
	}
return true;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors();"
	onContextmenu="return false">
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
						<td background="<%=path%>/img/table_bg_line.gif">
							<a href="账户处理_凭证录入_简约风格.htm"> </a>
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">账户处理&gt;凭证检查</font>
									</td>
									<td width="100" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="127">
										<b class="font14">凭 证 查 询</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="777">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/credenceInspectionFindAC.do" styleClass="margin: 0">
				<table border="0" width="95%" id="table1" cellspacing=1
					cellpadding=0 align="center">
					<tr>
						<td width="15%" class="td1">
							日期<font color="#FF0000">*</font>
							<br>
						</td>
						<td width="15%">
							<html:text name="credenceInspectionAF"
								property="credenceInspectionFindDTO.credenceDateSt"
								styleClass="input3" onkeydown="enterNextFocus1();" />
						</td>
						<td width="5%" align="center">
							至
						</td>
						<td width="15%">
							<html:text name="credenceInspectionAF"
								property="credenceInspectionFindDTO.credenceDateEnd"
								styleClass="input3" onkeydown="enterNextFocus1();" />
						</td>
						<td width="15%" class="td1">
							凭证号
						</td>
						<td width="15%">
							<html:text name="credenceInspectionAF"
								property="credenceInspectionFindDTO.credenceNumSt"
								styleClass="input3" onkeydown="enterNextFocus1();" />
						</td>
						<td width="5%" align="center">
							至
						</td>
						<td width="15%">
							<html:text name="credenceInspectionAF"
								property="credenceInspectionFindDTO.credenceNumEnd"
								styleClass="input3" onkeydown="enterNextFocus1();" />
						</td>
					</tr>
					<tr>
						<td width="15%" class="td1">
							凭证字
						</td>
						<td width="35%" colspan="3" class="td4">

							<html:select
								property="credenceInspectionFindDTO.credenceCharacter"
								styleClass="input4" name="credenceInspectionAF"
								onkeydown="enterNextFocus1();">
								<option value=""></option>
								<html:options collection="credenceCharacterList1"
									property="value" labelProperty="label" />
							</html:select>

						</td>
						<td width="15%" class="td1">
							办事处
						</td>
						<td width="35%" colspan="3" class="td4">
							<html:select property="credenceInspectionFindDTO.office"
								styleClass="input4" name="credenceInspectionAF"
								onkeydown="enterNextFocus1();">
								<option value=""></option>
								<html:options collection="officeList1" property="value"
									labelProperty="label" />
							</html:select>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td align="right">
							<html:submit property="method" styleClass="buttona" onclick="return search();">
								<bean:message key="button.search" />
							</html:submit>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center">
										<b class="font14">共计<bean:write
												name="credenceInspectionFindDTO" property="count" />张凭证
											借方金额：<bean:write name="credenceInspectionFindDTO"
												property="debitSum" /> 贷方金额：<bean:write
												name="credenceInspectionFindDTO" property="creditSum" />
										</b>
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
