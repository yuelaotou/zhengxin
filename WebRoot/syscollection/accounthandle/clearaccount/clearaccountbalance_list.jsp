<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ClearAccountTaBalanceShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			ClearAccountTaBalanceShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html>
	<head>
		<title>账务处理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
		<script language="JavaScript">
function loads(){
	document.clearAccountBalanceShowAF.orgId.value="";
	document.clearAccountBalanceShowAF.orgName.value="";
	document.clearAccountBalanceShowAF.bis_type1.value="";
	document.clearAccountBalanceShowAF.bank1.value="";
	document.clearAccountBalanceShowAF.startday.value="";
	document.clearAccountBalanceShowAF.untilday.value="";
}
function gotoSearch(){
	var month1=document.clearAccountBalanceShowAF.startday.value.trim();;
	var month2=document.clearAccountBalanceShowAF.untilday.value.trim();;
	var bank1=document.clearAccountBalanceShowAF.bank1.value.trim();;
	if(bank1==null||bank1==""){
		alert("请选择归集银行！");
		return false;
		}
		
	if(month1==""&&month2==""){
		alert("请输入查询日期！");
		document.clearAccountBalanceShowAF.startday.focus();
		return false;
	}
}
function executeAjax() {
     var queryString = "clearAccountBalanceMXFindAAC.do";
	 findInfo(queryString);
}
function display(){
	window.open('<%=path%>/syscollection/accounthandle/clearaccount/showOrgbusinessflowListAC.do','','width=700,height=500,top=200,left=300,scrollbars=yes');
}
function blurday(){
	var day = document.clearAccountBalanceShowAF.startday.value.trim();
	if(day.length==6){
		document.all.untilday.disabled="true";
	}else{
		document.all.untilday.disabled="";
	}
}
</script>
	</head>
	<body bgcolor="#FFFFFF" text="#000000" onload="loads();">

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
										<!--  <td width="112" height="37" background="<%=path%>/img/buttong.gif" align="center" valign="top"  style="PADDING-top: 7px"><a href="clearaccountsForwardURLAC.do" class=a2>扎账</a></td> -->
										<td width="112" height="37" align="center" valign="top"
											background="<%=path%>/img/buttonbl.gif"
											style="PADDING-top: 7px">
											日结单查询
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
										<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">账务处理</font><font color="00B5DB">&gt;日结单查询</font>
										</td>
										<td width="115" class=font14>
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
					<html:form action="/findClearAccountBalanceAC"
						styleClass="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="117">
												<b class="font14">查 询 条 件</b>
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
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr>
								<td class="td1">
									单位编号
								</td>
								<td colspan="3">
									<html:text name="clearAccountBalanceShowAF" property="orgId"
										styleClass="input3" />
								</td>
								<td class="td1">
									单位名称
								</td>
								<td width="34%">
									<html:text name="clearAccountBalanceShowAF" property="orgName"
										maxlength="50" styleClass="input3" />
								</td>
							</tr>
							<tr>
								<td class="td1">
									业务类型
								</td>
								<td colspan="3">
									<html:select property="bis_type1" styleClass="input4">
										<html:option value=""></html:option>
										<html:optionsCollection property="bis_type"
											name="clearAccountBalanceShowAF" label="value" value="key" />
									</html:select>
								</td>
								<td class="td1">
									归集银行
								</td>
								<td width="34%">
									<html:select property="bank1" styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="bankList1" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="14%" class="td1">
									查询日期
								</td>
								<td width="16%">
									<html:text name="clearAccountBalanceShowAF" property="startday"
										maxlength="8" styleClass="input3" onblur="blurday()" />
								</td>
								<td width="4%">
									至
								</td>
								<td width="19%">
									<html:text name="clearAccountBalanceShowAF" property="untilday"
										maxlength="8" styleClass="input3" />
								</td>
								<td width="13%" class="td1"></td>
								<td width="34%">

								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<html:submit property="method" styleClass="buttona"
										onclick="return gotoSearch();">
										<bean:message key="button.search" />
									</html:submit>
								</td>
							</tr>
						</table>
					</html:form>
					<html:form action="/maintainClearAccountBalanceAC"
						styleClass="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="117">
												<b class="font14">日 结 单</b>
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
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr align="center">
								<td class="td1" colspan="3">
									本期贷方
								</td>
								<td class="td1" colspan="3">
									本期借方
								</td>
							</tr>
							<tr align="center">
								<td class="td1">
									项目
								</td>
								<td class="td1">
									笔数
								</td>
								<td class="td1">
									发生额
								</td>
								<td class="td1">
									项目
								</td>
								<td class="td1">
									笔数
								</td>
								<td class="td1">
									发生额
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									单位汇缴
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="org_payment_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="org_payment_balance" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%">
									部分提取
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm" property="pick_count"
										styleClass="input3" readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="pick_balance" styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									单位补缴
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="org_add_payment_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="org_add_payment_balance" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%">
									按月还贷
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="pick_payload_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="pick_payload_balance" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									个人补缴
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="emp_add_payment_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="emp_add_payment_balance" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%">
									贷款清还
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="pick_payload_count_ld" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="pick_payload_balance_ld" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									挂 账
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="org_over_pay_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="org_over_paybalance" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%">
									销户提取
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="pick_count_xiaohu" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="pick_balance_xiaohu" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									小 计
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="xiaoji1_credit_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="xiaoji1_credit_paybalance" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%">
									小 计
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="debit_count_xiaoji" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="debit_paybalance_xiaoji" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									错账调增
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="adjustaccout_credit_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="adjustaccout_credit_paybalance" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%">
									错帐调减
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="adjustaccout_debit_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="adjustaccout_debit_paybalance" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									单位转入
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="org_tranin_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="org_tranin_paybalance" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%">
									单位转出
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="org_tranout_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="org_tranout_balance" styleClass="input3"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									小 计
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="debit_count_xiaoji_1" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="debit_paybalance_xiaoji_1" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%"></td>
								<td width="15%">
								</td>
								<td width="15%">
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									定期利息
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="clearinteres_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="clearinteres_paybalance" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%"></td>
								<td width="15%">
								</td>
								<td width="15%">
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									活期利息
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="clearinteres_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="clearinteres_paybalance_1" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%"></td>
								<td width="15%">
								</td>
								<td width="15%">
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									小 计
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="clearinteres_count" styleClass="input3"
										readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="xiaoji3_credit_paybalance" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%"></td>
								<td width="15%">
								</td>
								<td width="15%">
								</td>
							</tr>
							<tr>
								<td class="td1" width="20%">
									合 计
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="credit_count" styleClass="input3" readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="credit_paybalance" styleClass="input3"
										readonly="true" />
								</td>
								<td class="td1" width="20%">
									&#21512;&#12288;&#12288;&#35745;
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="debit_count" styleClass="input3" readonly="true" />
								</td>
								<td width="15%">
									<html:text name="clearAccountBalanceForm"
										property="debit_paybalance" styleClass="input3"
										readonly="true" />
								</td>
							</tr>

							<tr>
								<td class="td1" width="20%">
									前日余额
								</td>
								<td width="15%" colspan="2">
									<html:text name="clearAccountBalanceForm"
										property="pre_rest_paybalance" styleClass="input3"
										readonly="true" />
								</td>

								<td class="td1" width="20%">
									本日余额
								</td>
								<td width="15%" colspan="2">
									<html:text name="clearAccountBalanceForm"
										property="pre_debit_paybalance" styleClass="input3"
										readonly="true" />
								</td>

							</tr>
							<tr>
								<td class="td1" width="20%">
									公积金余额
								</td>
								<td width="15%" colspan="2">

									<html:text name="clearAccountBalanceForm" property="gjjYuE"
										styleClass="input3" readonly="true" />
								</td>

								<td class="td1" width="20%">
									销户利息
								</td>
								<td width="15%" colspan="2">
									<html:text name="clearAccountBalanceForm"
										property="debit_interest_paybalance" styleClass="input3"
										readonly="true" />
								</td>

							</tr>
							<tr>
								<td class="td1" width="20%">
									挂账余额
								</td>
								<td width="15%" colspan="2">
									<html:text name="clearAccountBalanceForm"
										property="org_overpay_sum" styleClass="input3" readonly="true" />
								</td>

								<td class="td1" width="20%">
									总余额
								</td>
								<td width="15%" colspan="2">
									<html:text name="clearAccountBalanceForm"
										property="cur_rest_paybalance" styleClass="input3"
										readonly="true" />
								</td>

							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="70">
												<html:button property="method" styleClass="buttona"
													onclick="executeAjax();">
													<bean:message key="button.infor" />
												</html:button>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
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
</html>
