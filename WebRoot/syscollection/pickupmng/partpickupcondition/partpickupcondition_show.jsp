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
	<title>部分提取前提录入</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
function onCheck(){
	var pickMoneyMax=document.forms[0].elements["partPickupConditionDTO.pickMoneyMax"].value.trim();
	var pickTimeMax=document.forms[0].elements["partPickupConditionDTO.pickTimeMax"].value.trim();
	var leavingsBalance=document.forms[0].elements["partPickupConditionDTO.leavingsBalance"].value.trim();
	var multiple=document.forms[0].elements["partPickupConditionDTO.multiple"].value.trim();
	if(pickMoneyMax!=""&&pickMoneyMax!=0){
		if(!checkMoney(pickMoneyMax)){
			document.forms[0].elements["partPickupConditionDTO.pickMoneyMax"].value="";
			document.forms[0].elements["partPickupConditionDTO.pickMoneyMax"].focus();
			return false;
		}
	}else{
		alert("最高提取限额必须输入!");
		document.forms[0].elements["partPickupConditionDTO.pickMoneyMax"].focus();
		return false;
	}
	
	if(pickTimeMax!=""&&pickTimeMax!=0){
		if(!isNumber(pickTimeMax)){
				alert('请输入正确的数字格式！');
				document.forms[0].elements["partPickupConditionDTO.pickTimeMax"].value="";
				document.forms[0].elements["partPickupConditionDTO.pickTimeMax"].focus();
				return false;
			}
	}else{
		alert("最大提取次数必须输入!");
		document.forms[0].elements["partPickupConditionDTO.pickTimeMax"].focus();
		return false;
	}
	
	if(leavingsBalance!=""&&leavingsBalance!=0){
		if(!checkMoney(leavingsBalance)){
			document.forms[0].elements["partPickupConditionDTO.leavingsBalance"].value="";
			document.forms[0].elements["partPickupConditionDTO.leavingsBalance"].focus();
			return false;
		}
	}else{
		alert("留足余额必须输入!");
		document.forms[0].elements["partPickupConditionDTO.leavingsBalance"].focus();
		return false;
	}
	if(multiple!=""&&multiple!=0){
		if(!isNumber(multiple)){
		    alert('请输入正确的数字格式！');
			document.forms[0].elements["partPickupConditionDTO.multiple"].value="";
			document.forms[0].elements["partPickupConditionDTO.multiple"].focus();
			return false;
		}
	}else{
		alert("留足余额月缴额倍数必须录入!");
		document.forms[0].elements["partPickupConditionDTO.leavingsBalance"].focus();
		return false;
	}
	if(pickMoneyMax==""){
		document.forms[0].elements["partPickupConditionDTO.pickMoneyMax"].value="0";
	}else{
		document.forms[0].elements["partPickupConditionDTO.pickMoneyMax"].value=pickMoneyMax;
	}
	
	if(pickTimeMax==""){
		document.forms[0].elements["partPickupConditionDTO.pickTimeMax"].value="0";
	}else{
		document.forms[0].elements["partPickupConditionDTO.pickTimeMax"].value=pickTimeMax;
	}
	
	if(leavingsBalance==""){
		document.forms[0].elements["partPickupConditionDTO.leavingsBalance"].value="0";
	}else{
		document.forms[0].elements["partPickupConditionDTO.leavingsBalance"].value=leavingsBalance;
	}
	if(multiple==""){
		document.forms[0].elements["partPickupConditionDTO.multiple"].value="0";
	}else{
		document.forms[0].elements["partPickupConditionDTO.multiple"].value=multiple;
	}
	return true;
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
										<a href="#" class=a1>提取管理</a><font color="00B5DB">&gt;</font><a
											href="#" class=a1>部分提取前提录入</a>
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
			<html:form action="/partPickupConditionSaveAC.do" styleClass="margin: 0">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="130">
										<b class="font14">部分提取前提录入</b>
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
				
				<table border="0" width="95%" id="table1" cellspacing=2
					cellpadding=3 align="center">
					<tr id="tr1">
						<td width="15%" class="td1">
							最高提取限额
						</td>
						<td width="85%" class="td1">
							<html:text name="partPickupConditionAF"
									property="partPickupConditionDTO.pickMoneyMax" styleClass="input3"
									style="width=20%;" onkeydown="enterNextFocus1();" />
						</td>
					</tr>
					<tr id="tr1">
						<td width="15%" class="td1">

							最大提取次数

						</td>
						<td width="85%" class="td1">
							<html:text name="partPickupConditionAF"
									property="partPickupConditionDTO.pickTimeMax" styleClass="input3"
									style="width=20%;" onkeydown="enterNextFocus1();" />
						</td>
					</tr>
					<%--
					<tr id="tr1">
						<td width="15%" class="td1">
							留足余额

						</td>
						<td width="85%" class="td1">
							<html:text name="partPickupConditionAF"
									property="partPickupConditionDTO.leavingsBalance" styleClass="input3"
									style="width=20%;" onkeydown="enterNextFocus1();" />
						</td>
					</tr>
					--%>
                  	<tr id="tr1">
						<td width="15%" class="td1">
							留足余额=余额-公积金上年度月缴额×

						</td>
						<td width="85%" class="td1">
							<html:text name="partPickupConditionAF"
									property="partPickupConditionDTO.multiple" styleClass="input3"
									style="width=20%;" onkeydown="enterNextFocus1();" />
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
										<html:submit property="method" styleClass="buttona" onclick="return onCheck();">
												<bean:message key="button.sure" />
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
				