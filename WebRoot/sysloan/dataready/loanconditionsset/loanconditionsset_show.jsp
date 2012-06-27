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
	<title>贷款前提条件设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
  function verdictoffice(){
	var office=document.forms[0].elements["loanConditionsSetDTO.office"].value.trim();
	document.URL=('loanConditionsSetShowAC.do?office='+office);
}
function onCheck(){
	var loanMoneyMax=document.forms[0].elements["loanConditionsSetDTO.loanMoneyMax"].value.trim();
	var otherLoanMoneyMax=document.forms[0].elements["loanConditionsSetDTO.otherLoanMoneyMax"].value.trim();
	var merchandiseRateMax=document.forms[0].elements["loanConditionsSetDTO.merchandiseRateMax"].value.trim();
	var secondhandRateMax=document.forms[0].elements["loanConditionsSetDTO.secondhandRateMax"].value.trim();
	var merchandiseMoneyMax=document.forms[0].elements["loanConditionsSetDTO.merchandiseMoneyMax"].value.trim();
	var secondhandMoneyMax=document.forms[0].elements["loanConditionsSetDTO.secondhandMoneyMax"].value.trim();
	
	
	if(loanMoneyMax!=""&&loanMoneyMax!=0){
		if(!checkMoney(loanMoneyMax)){
			document.forms[0].elements["loanConditionsSetDTO.loanMoneyMax"].focus();
			return false;
		}
	}
	
	if(otherLoanMoneyMax!=""&&otherLoanMoneyMax!=0){
		if(!checkMoney(otherLoanMoneyMax)){
			document.forms[0].elements["loanConditionsSetDTO.otherLoanMoneyMax"].focus();
			return false;
		}
	}
	
	if(merchandiseRateMax!=""&&merchandiseRateMax!=0){
		if(!checkMoney(merchandiseRateMax)){
			document.forms[0].elements["loanConditionsSetDTO.merchandiseRateMax"].focus();
			return false;
		}
	}
	
	if(secondhandRateMax!=""&&secondhandRateMax!=0){
		if(!checkMoney(secondhandRateMax)){
			document.forms[0].elements["loanConditionsSetDTO.secondhandRateMax"].focus();
			return false;
		}
	}
	
	if(merchandiseMoneyMax!=""&&merchandiseMoneyMax!=0){
		if(!checkMoney(merchandiseMoneyMax)){
			document.forms[0].elements["loanConditionsSetDTO.merchandiseMoneyMax"].focus();
			return false;
		}
	}
	
	if(secondhandMoneyMax!=""&&secondhandMoneyMax!=0){
		if(!checkMoney(secondhandMoneyMax)){
			document.forms[0].elements["loanConditionsSetDTO.secondhandMoneyMax"].focus();
			return false;
		}
	}
	
	if(loanMoneyMax==""){
		document.forms[0].elements["loanConditionsSetDTO.loanMoneyMax"].value="0";
	}else{
		document.forms[0].elements["loanConditionsSetDTO.loanMoneyMax"].value=loanMoneyMax;
	}
	
	if(otherLoanMoneyMax==""){
		document.forms[0].elements["loanConditionsSetDTO.otherLoanMoneyMax"].value="0";
	}else{
		document.forms[0].elements["loanConditionsSetDTO.otherLoanMoneyMax"].value=otherLoanMoneyMax;
	}
	
	if(merchandiseRateMax==""){
		document.forms[0].elements["loanConditionsSetDTO.merchandiseRateMax"].value="0";
	}else{
		document.forms[0].elements["loanConditionsSetDTO.merchandiseRateMax"].value=merchandiseRateMax;
	}
	
	if(secondhandRateMax==""){
		document.forms[0].elements["loanConditionsSetDTO.secondhandRateMax"].value="0";
	}else{
		document.forms[0].elements["loanConditionsSetDTO.secondhandRateMax"].value=secondhandRateMax;
	}
	
	if(merchandiseMoneyMax==""){
		document.forms[0].elements["loanConditionsSetDTO.merchandiseMoneyMax"].value="0";
	}else{
		document.forms[0].elements["loanConditionsSetDTO.merchandiseMoneyMax"].value=merchandiseMoneyMax;
	}
	
	if(secondhandMoneyMax==""){
		document.forms[0].elements["loanConditionsSetDTO.secondhandMoneyMax"].value="0";
	}else{
		document.forms[0].elements["loanConditionsSetDTO.secondhandMoneyMax"].value=secondhandMoneyMax;
	}
	
	if(secondhandMoneyMax==""){
		document.forms[0].elements["loanConditionsSetDTO.secondhandMoneyMax"].value="0";
	}else{
		document.forms[0].elements["loanConditionsSetDTO.secondhandMoneyMax"].value=secondhandMoneyMax;
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
										<a href="#" class=a1>数据准备</a><font color="00B5DB">&gt;</font><a
											href="#" class=a1>贷款前提条件设置</a>
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
										<b class="font14">贷款前提条件设置</b>
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
				<html:form action="/loanConditionsSetSaveAC.do" styleClass="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr id="tr1">
							<td class="td1">
								&nbsp;
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.office"
									styleClass="input4" name="loanConditionsSetAF"
									style="width=30%;" onchange="verdictoffice();"
									onkeydown="enterNextFocus1();">
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td class="td1" width="15%">
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1" width="3%">
								1
							</td>
							<td class="td1" width="80%">
								公积金个人账户状态是否正常
								<html:radio name="loanConditionsSetAF"
									property="loanConditionsSetDTO.isRegular" value="1"
									onkeydown="enterNextFocus1();" />
								是
								<html:radio name="loanConditionsSetAF"
									property="loanConditionsSetDTO.isRegular" value="0"
									onkeydown="enterNextFocus1();" />
								否
							</td>
							<td class="td1" width="15%">
								<html:select property="loanConditionsSetDTO.one"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1">
								2
							</td>
							<td class="td1">
								公积金连续汇缴月数大于
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.chgbizMonth" styleClass="input3"
									style="width=7%;" onkeydown="enterNextFocus1();" />
								月 且欠缴月数小于
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.qianJiaoMonth" styleClass="input3"
									style="width=7%;" onkeydown="enterNextFocus1();" />
								月
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.two"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1">
								3
							</td>
							<td class="td1">
								公积金开户时间大于
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.accountOpenMonth"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								月
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.three"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr id="tr1">
							<td class="td1">
								4
							</td>
							<td class="td1">
								贷款人实际年龄加贷款期限不超过
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.maleAge" styleClass="input3"
									style="width=7%;" onkeydown="enterNextFocus1();" />
								岁(男)或
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.femaleAge" styleClass="input3"
									style="width=7%;" onkeydown="enterNextFocus1();" />
								岁(女)
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.four"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>

						<tr id="tr1">
							<td class="td1">
								5
							</td>
							<td class="td1">
								贷款期限不低于
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.loanLimitMin"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								月,最高期限不超过
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.loanLimitMax"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								月
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.five"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								6
							</td>
							<td class="td1">
								信用情况,曾逾期不超过
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.overTimeMax" styleClass="input3"
									style="width=7%;" onkeydown="enterNextFocus1();" />
								月
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.six"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								7
							</td>
							<td class="td1">
								单方拥有公积金贷款金额不能超过
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.loanMoneyMax"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								元,有辅助贷款人的贷款金额不能超过
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.otherLoanMoneyMax"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								元
							<td class="td1">
								<html:select property="loanConditionsSetDTO.seven"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								8
							</td>
							<td class="td1">
								贷款金额不能超过商品房价的
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.merchandiseRateMax"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								%
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.eight"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								9
							</td>
							<td class="td1">
								贷款金额不能超过二手房价的（建筑年限1——5年）
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.secondhandRateMax"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								%
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.nine"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								
							</td>
							<td class="td1">
								贷款金额不能超过二手房价的（建筑年限6——10年）
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.secondhandRateMax_1"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								%
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.twive"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								
							</td>
							<td class="td1">
								贷款金额不能超过二手房价的（建筑年限11年以上）
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.secondhandRateMax_2"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								%
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.thirteen"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								10
							</td>
							<td class="td1">
								商品房贷款最高金额不超过
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.merchandiseMoneyMax"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								元
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.ten"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								11
							</td>
							<td class="td1">
								二手房贷款最高金额不超过
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.secondhandMoneyMax"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								元
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.eleven"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								12
							</td>
							<td class="td1">
								不得超过贷款家庭成员退休年龄内所交纳住房公积金数额
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.beiShu"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								倍
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.fourteen"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						
						<tr>
							<td class="td1">
								13
							</td>
							<td class="td1">
								贷款最高年限：商品房
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.timeMax_1"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								年,二手房
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.timeMax_2"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								年
							<td class="td1">
								<html:select property="loanConditionsSetDTO.fifteen"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								14
							</td>
							<td class="td1">
								借款人月收入还款比例
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.salaryRate"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								%
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.sixteen"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								15
							</td>
							<td class="td1" width="25%">
								贷款单位性质
								
									<html:select property="loanConditionsSetDTO.comNature_1" name="loanConditionsSetAF"
									onkeydown="enterNextFocus1();" styleClass="input4" style="width=32%;">
									<html:optionsCollection property="natureofunitsMap"
										name="loanConditionsSetAF" label="value" value="key" />
								</html:select>
								单位最少人数
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.personCount_1"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								单位应连续缴存
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.monthCount_1"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								月
							</td>
							
							<td class="td1">
								<html:select property="loanConditionsSetDTO.seventeen"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								
							</td>
							<td class="td1" width="25%">
								贷款单位性质
								<html:select property="loanConditionsSetDTO.comNature_2" name="loanConditionsSetAF"
									onkeydown="enterNextFocus1();" styleClass="input4" style="width=32%;">
									<html:optionsCollection property="natureofunitsMap"
										name="loanConditionsSetAF" label="value" value="key" />
								</html:select>
								单位最少人数
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.personCount_2"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								单位应连续缴存
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.monthCount_2"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								月
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.eighteen"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1">
								
							</td>
							<td class="td1" width="25%">
								贷款单位性质
								<html:select property="loanConditionsSetDTO.comNature_3" name="loanConditionsSetAF"
									onkeydown="enterNextFocus1();" styleClass="input4" style="width=32%;">
									<html:optionsCollection property="natureofunitsMap"
										name="loanConditionsSetAF" label="value" value="key" />
								</html:select>
								单位最少人数
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.personCount_3"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								单位应连续缴存
								<html:text name="loanConditionsSetAF"
									property="loanConditionsSetDTO.monthCount_3"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								月
							</td>
							<td class="td1">
								<html:select property="loanConditionsSetDTO.ninteen"
									name="loanConditionsSetAF" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="0">未启用</html:option>
									<html:option value="1">启用</html:option>
								</html:select>
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
											<html:submit property="method" styleClass="buttona"
												onclick="return onCheck();">
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
