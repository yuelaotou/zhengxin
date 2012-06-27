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
	<title>公积金还贷参数设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
function verdictOffice(){
	var office=document.forms[0].elements["collLoanbackParaDTO.office"].value.trim();
	document.URL=('collLoanbackParaShowAC.do?office='+office);
}
function onCheck(){
	var balance=document.forms[0].elements["collLoanbackParaDTO.balance"].value.trim();
	var monthMoney=document.forms[0].elements["collLoanbackParaDTO.monthMoney"].value.trim();
	var monthPayMoney=document.forms[0].elements["collLoanbackParaDTO.monthPayMoney"].value.trim();
	var v1=document.forms[0].elements["collLoanbackParaDTO.pickMoneyType"];
	if(v1[0].checked==false&&v1[1].checked==false&&v1[2].checked==false){
		alert('第1条必须三选一！');
		return false;
	}
	if(v1[0].checked==true){
		if(balance<0||balance==""){
			alert("留足余额必须输入！");
			document.forms[0].elements["collLoanbackParaDTO.balance"].focus();
			return false;
		}else{
			if(!checkMoney(balance)){
				document.forms[0].elements["collLoanbackParaDTO.balance"].focus();
				return false;
			}
		}
	}
	if(v1[1].checked==true){
		document.forms[0].elements["collLoanbackParaDTO.balance"].value="0";
		if(monthMoney==0||monthMoney==""){
			alert("留足月还本息必须输入！");
			document.forms[0].elements["collLoanbackParaDTO.monthMoney"].focus();
			return false;
		}
	}
	if(v1[2].checked==true){
		document.forms[0].elements["collLoanbackParaDTO.balance"].value="0";
		if(monthPayMoney==0||monthPayMoney==""){
			alert("留足月缴存额必须输入！");
			document.forms[0].elements["collLoanbackParaDTO.monthPayMoney"].focus();
			return false;
		}
	}
	var v2=document.forms[0].elements["collLoanbackParaDTO.isDeduct"];
	if(v2[0].checked==false&&v2[1].checked==false){
		alert('第2条必须二选一！');
		return false;
	}
	/*
	if(v2[0].checked==true){
		var v21=document.forms[0].elements["collLoanbackParaDTO.isOverPay"];
		if(v21[0].checked==false&&v21[1].checked==false){
			alert("第2条是否可挂账必须输入！");
			return false;
		}
	}
	*/
	var v3=document.forms[0].elements["collLoanbackParaDTO.isPreOnly"];
	if(v3[0].checked==false&&v3[1].checked==false){
		alert('第3条必须二选一！');
		return false;
	}
	var v4=document.forms[0].elements["collLoanbackParaDTO.isPickLessThanPay"];
	if(v4[0].checked==false&&v4[1].checked==false){
		alert('第4条必须二选一！');
		return false;
	}
	var v5=document.forms[0].elements["collLoanbackParaDTO.isOtherDeduct"];
	if(v5[0].checked==false&&v5[1].checked==false){
		alert('第5条必须二选一！');
		return false;
	}
	if(balance==""){
		document.forms[0].elements["collLoanbackParaDTO.balance"].value="0";
	}else{
		document.forms[0].elements["collLoanbackParaDTO.balance"].value=balance;
	}
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
										<font color="00B5DB">数据准备&gt;公积金还贷参数设置</font>
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
			<html:form action="/collLoanbackParaSaveAC.do">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="136">
										<b class="font14">公积金还贷参数设置</b>
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
							<html:select property="collLoanbackParaDTO.office"
								styleClass="input4" name="collLoanbackParaAF" style="width=30%;" onchange="verdictOffice();"
								onkeydown="enterNextFocus1();">
								<html:options collection="officeList1" property="value"
									labelProperty="label" />
							</html:select>
						</td>
					</tr>
					<tr id="tr1">
						<td width="3%" class="td1">
							1
						</td>
						<td width="97%" class="td1">
							<p>
								提取金额：
							</p>
							<p>
								<html:radio name="collLoanbackParaAF"
									property="collLoanbackParaDTO.pickMoneyType" value="A"
									onkeydown="enterNextFocus1();" />
								A.留足余额
								<html:text name="collLoanbackParaAF"
									property="collLoanbackParaDTO.balance" styleClass="input3"
									style="width=7%;" onkeydown="enterNextFocus1();" />
								元；
							</p>
							<p>
								<html:radio name="collLoanbackParaAF"
									property="collLoanbackParaDTO.pickMoneyType" value="B"
									onkeydown="enterNextFocus1();" />
								B.留足月还本息
								<html:text name="collLoanbackParaAF"
									property="collLoanbackParaDTO.monthMoney" styleClass="input3"
									style="width=7%;" onkeydown="enterNextFocus1();" />
								月；
							</p>
							<p>
								<html:radio name="collLoanbackParaAF"
									property="collLoanbackParaDTO.pickMoneyType" value="C"
									onkeydown="enterNextFocus1();" />
								C.留足月缴存额
								<html:text name="collLoanbackParaAF"
									property="collLoanbackParaDTO.monthPayMoney"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								月；
							</p>
						</td>
					</tr>
					<tr id="tr1">
						<td class="td1">
							2
						</td>
						<td class="td1">
							可扣公积金余额不足时是否扣款
							<html:radio name="collLoanbackParaAF" property="collLoanbackParaDTO.isDeduct" value="0" onkeydown="enterNextFocus1();"/>
							是
							<html:radio name="collLoanbackParaAF" property="collLoanbackParaDTO.isDeduct" value="1" onkeydown="enterNextFocus1();"/>
							否；
						</td>
					</tr>
					<tr id="tr1">
						<td width="3%" class="td1">
							3
						</td>
						<td width="97%" class="td1">
							<p>
								只扣往年余额
								<html:radio name="collLoanbackParaAF" property="collLoanbackParaDTO.isPreOnly" value="0" onkeydown="enterNextFocus1();"/>
								是
								<html:radio name="collLoanbackParaAF" property="collLoanbackParaDTO.isPreOnly" value="1" onkeydown="enterNextFocus1();"/>
								否；
							</p>
						</td>
					</tr>
					<tr id="tr1">
						<td class="td1">
							4
						</td>
						<td class="td1">
							月提取额不超过月缴存额 
							<html:radio name="collLoanbackParaAF" property="collLoanbackParaDTO.isPickLessThanPay" value="0" onkeydown="enterNextFocus1();"/>
							是 
							<html:radio name="collLoanbackParaAF" property="collLoanbackParaDTO.isPickLessThanPay" value="1" onkeydown="enterNextFocus1();"/>
							否；
						</td>
					</tr>
					<tr id="tr1">
						<td height="26" class="td1">
							5
						</td>
						<td class="td1">
							辅助借款人是否可以扣款
							<html:radio name="collLoanbackParaAF" property="collLoanbackParaDTO.isOtherDeduct" value="0" onkeydown="enterNextFocus1();"/>
								是
							<html:radio name="collLoanbackParaAF" property="collLoanbackParaDTO.isOtherDeduct" value="1" onkeydown="enterNextFocus1();"/>
							否；
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
