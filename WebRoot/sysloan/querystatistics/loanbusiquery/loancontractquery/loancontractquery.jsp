<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loancontractquery.action.LoancontractqueryShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			LoancontractqueryShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html>
	<head>
		<title>统计查询</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">


var s1="";
function loads(){

	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		}
	}
	var count = document.all.count.value.trim();
	if(count=='0'){
	 	document.all.item(s1).disabled="true";
	}
	 document.all.contactid.focus();
}

function reportsErrors(){
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>	
}
//弹出窗口回调的方法
function findFloorNum(headId,floorName){
	document.all.buyhouseorgid.value = headId;
	document.all.floorName.value = floorName;
}

//*********************************************
//这个方法不能没有
function executeAjaxIn(){}

function findorghouses(){
  	window.open("<%=path%>/sysloan/develepershowAC.do?indexs="+5+"&objInput=headname"+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=no"); 
}
//楼盘弹出框
function findBuilding(){
	var developerId = document.all.buyhouseorgid.value.trim();
	window.open("<%=path%>/sysloan/buildingPopShowAC.do?indexs="+6+"&developerId="+developerId+
		"&objInput=floorName"+"&qx=no","window","height=600,width=700,top="+(window.screen.availHeight-500)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=no"); 
}
</script>

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
							<td background="<%=path%>/img/table_bg_line.gif"></td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="235" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">贷款业务查询&gt;贷款合同查询</font>
										</td>
										<td width="28" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="181">
											<b class="font14">查 询 条 件</b>
										</td>
										<td width="678" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/loancontractqueryfindAC" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr>
								<html:hidden property="count" name="loancontractqueryAF" />
								<td width="17%" class="td1">
									合同编号
								</td>
								<td width="33%" colspan="3">
									<html:text property="contactid" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
								<td width="17%" class="td1">
									借款人姓名
								</td>
								<td width="33%" colspan="3">
									<html:text property="borrowername" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									证件号码
								</td>
								<td width="33%" colspan="3">
									<html:text property="cardnum" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
								<td width="17%" class="td1">
									借款人职工编号
								</td>
								<td width="33%" colspan="3">
									<html:text property="empid" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									配偶姓名
								</td>
								<td width="33%" colspan="3">
									<html:text property="consortname" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
								<td width="17%" class="td1">
									配偶身份证号
								</td>
								<td width="33%" colspan="3">
									<html:text property="consortcardnum" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									配偶职工编号
								</td>
								<td width="33%" colspan="3">
									<html:text property="consortempid" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
								<td width="17%" class="td1">
									&nbsp;
								</td>
								<td width="33%" colspan="3">
									&nbsp;
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									办事处
								</td>
								<td width="33%" colspan="3">
									<html:select name="loancontractqueryAF" property="office"
										styleClass="input4" styleId="txtsearch" >
										<html:option value=""></html:option>
										<html:options collection="officelist" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td width="17%" class="td1">
									放款银行
								</td>
								<td width="33%" colspan="3">
									<html:select name="loancontractqueryAF" property="loanbank"
										styleClass="input4" styleId="txtsearch" >
										<html:option value=""></html:option>
										<html:options collection="dkbanklist" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									开发商
								</td>
								<td width="30%" colspan="2">
									<html:text property="headname" name="loancontractqueryAF"
										styleClass="input3" readonly="true"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="3%">
									<input type="button" name="submit12" class="buttona"
										value="..." onclick="findorghouses();"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="17%" class="td1">
									楼盘
								</td>
								<td width="30%" colspan="2">
									<html:text property="floorName" name="loancontractqueryAF"
										styleClass="input3" readonly="true"
										styleId="txtsearch"  />
								</td>
								<td width="3%">
									<input type="button" name="submit12" class="buttona"
										value="..." onclick="findBuilding();"
										onkeydown="enterNextFocus1();" />
								</td>
								<td>
									<html:hidden property="buyhouseorgid"
										name="loancontractqueryAF" />
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									贷款金额
								</td>
								<td width="14%">
									<html:text property="loanMoneySt" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
								<td width="1%">
									至
								</td>
								<td width="14%">
									<html:text property="loanMoneyEnd" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch" />
								</td>
								<td width="17%" class="td1">
									贷款期限
								</td>
								<td width="14%">
									<html:text property="loanTimeLimitSt"
										name="loancontractqueryAF" styleClass="input3"
										styleId="txtsearch" />
								</td>
								<td width="1%">
									至
								</td>
								<td width="14%">
									<html:text property="loanTimeLimitEnd"
										name="loancontractqueryAF" styleClass="input3"
										styleId="txtsearch" />
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									面积
								</td>
								<td width="14%">
									<html:text property="houseAreaSt" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
								<td width="1%">
									至
								</td>
								<td width="14%">
									<html:text property="houseAreaEnd" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
								<td width="17%" class="td1">
									房屋类型
								</td>
								<td width="33%" colspan="3">
									<html:select name="loancontractqueryAF" property="housetype"
										styleClass="input4" styleId="txtsearch" >
										<html:option value=""></html:option>
										<html:optionsCollection property="housetypemap"
											name="loancontractqueryAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									发放日期
								</td>
								<td width="14%">
									<html:text property="agreementDateSt"
										name="loancontractqueryAF" styleClass="input3"
										styleId="txtsearch"  />
								</td>
								<td width="1%">
									至
								</td>
								<td width="14%">
									<html:text property="agreementDateEnd"
										name="loancontractqueryAF" styleClass="input3"
										styleId="txtsearch" />
								</td>
								<td width="17%" class="td1">
									担保公司
								</td>
								<td width="33%" colspan="3">
									<html:select name="loancontractqueryAF"
										property="assistantorgid" styleClass="input4"
										styleId="txtsearch" >
										<html:option value=""></html:option>
										<html:options collection="assistantorglist" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									合同状态
								</td>
								<td width="33%" colspan="3">
									<html:select name="loancontractqueryAF" property="contact_st"
										styleClass="input4" styleId="txtsearch" >
										<html:option value=""></html:option>
										<html:optionsCollection property="contact_stmap"
											name="loancontractqueryAF" label="value" value="key" />
									</html:select>
								</td>
								<td width="17%" class="td1">
									公积金协议
								</td>
								<td width="33%" colspan="3">
									<html:select name="loancontractqueryAF"
										property="isSignAgreement" styleClass="input4"
										styleId="txtsearch" >
										<html:option value=""></html:option>
										<html:optionsCollection property="yesNoMap"
											name="loancontractqueryAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									公积金协议签订日期
								</td>
								<td width="14%">
									<html:text property="signAgreementDateStart" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
								<td width="1%">
									至
								</td>
								<td width="14%">
									<html:text property="signAgreementDateEnd" name="loancontractqueryAF"
										styleClass="input3" styleId="txtsearch"  />
								</td>
								<td width="17%" class="td1">
									异地贷款
								</td>
								<td width="33%" colspan="3">
									<html:select name="loancontractqueryAF"
										property="loanType" styleClass="input4"
										styleId="txtsearch" >
										<html:option value=""></html:option>
										<html:optionsCollection property="yesNoMap"
											name="loancontractqueryAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr id="gjtr">
								<td width="17%" class="td1">
									还清日期
								</td>
								<td width="14%">
									<html:text property="recoverClearDateSt"
										name="loancontractqueryAF" styleClass="input3"
										styleId="txtsearch"  />
								</td>
								<td width="1%">
									至
								</td>
								<td width="14%">
									<html:text property="recoverClearDateEnd"
										name="loancontractqueryAF" styleClass="input3"
										styleId="txtsearch" />
								</td>
								<td width="17%" class="td1">
									是否已还清
								</td>
								<td width="33%" colspan="3">
									<html:select name="loancontractqueryAF"
										property="isRecoverClear" styleClass="input4"
										styleId="txtsearch" >
										<html:option value=""></html:option>
										<html:optionsCollection property="yesNoMap"
											name="loancontractqueryAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<html:submit property="method" styleClass="buttona"
										onclick="giveValues();">
										<bean:message key="button.search" />
									</html:submit>
								</td>
							</tr>
						</table>
					</html:form>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								总计：户数
								<u>：<bean:write name="loancontractqueryAF" property="count" />
								</u> 房屋面积
								<u>：<bean:write name="loancontractqueryAF"
										property="housearea" />
								</u> 房屋总价
								<u>：<bean:write name="loancontractqueryAF"
										property="housetolprice" />
								</u> 贷款金额
								<u>：<bean:write name="loancontractqueryAF"
										property="loanmoney" />
								</u> 贷款余额
								<u>：<bean:write name="loancontractqueryAF"
										property="loanBalance" />
								</u>
							</td>
						</tr>
					</table>
					<html:form action="/loancontractqueryPrintAC" style="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="179">
												<b class="font14">贷款合同查询信息列表</b>
											</td>
											<td width="680" height="22" align="center"
												background="<%=path%>/img/bg2.gif">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr bgcolor="1BA5FF">
								<td align="center" height="6" colspan="1"></td>
							</tr>
						</table>
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr align="center" bgcolor="C4F0FF">

								<td align="center" class=td2>
									<a href="javascript:sort('p10.contract_id')">合同编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="p10.contract_id">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p10.borrower_name')">借款人姓名</a>
									<logic:equal name="pagination" property="orderBy"
										value="p10.borrower_name">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td width="13%" align="center" class=td2>
									证件号码
								</td>
								<td align="center" class=td2>
									放款银行
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p14.house_type')">购房类型</a>
									<logic:equal name="pagination" property="orderBy"
										value="p14.house_type">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									房屋总价
								</td>
								<td align="center" class=td2>
									房屋面积 （M
									<sup>
										2
									</sup>
									）
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p15.loan_money')">贷款金额</a>
									<logic:equal name="pagination" property="orderBy"
										value="p15.loan_money">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									贷款余额
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p15.loan_month_rate')">贷款期限（月）</a>
									<logic:equal name="pagination" property="orderBy"
										value="p15.loan_month_rate">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									每月利率
								</td>
								<td align="center" class=td2>
									发放日期
								</td>
								<td align="center" class=td2>
									合同状态
								</td>
								<td align="center" class=td2>
									公积金协议
								</td>
								<td align="center" class=td2>
									还款方式
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p11.payoffdate')">还清日期</a>
									<logic:equal name="pagination" property="orderBy"
										value="p11.payoffdate">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
							</tr>
							<logic:notEmpty name="loancontractqueryAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="loancontractqueryAF" property="list"
									id="elments" indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr id="tr1" class="<%=strClass%>"
										onMouseOver='this.style.background="#eaeaea"'
										onMouseOut='this.style.background="#ffffff"'>
										<td valign="top"><a href="#"
														onclick="window.open('<%=path%>/sysloan/showLoanCheckTaAC.do?contractIdWY=<bean:write 
													name="elments" property="contactid" />','window','height=650,width=1000,top='+(window.screen.availHeight-700)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,location=no,status=no');">
												
											<bean:write name="elments" property="contactid" /></a>
										</td>
										<td valign="top">
											<a href="#"
												onclick="window.open('<%=path%>/sysloan/loancontractqueryTaShowAC.do?contractIdHl=<bean:write name="elments" property="contactid"/>','window','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,location=no,status=no');">
												<bean:write name="elments" property="borrowername" /> </a>
										</td>
										<td valign="top">
											<bean:write name="elments" property="cardnum" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanBank" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="huosetype" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="housetolprice" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="housearea" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanmoney" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanBalance" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanlimit" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="monthrate" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="agreementDate" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="contractSt" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="isSignAgreement" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="paymood" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="clearDate" />
										</td>
									</tr>

								</logic:iterate>

							</logic:notEmpty>
							<logic:empty name="loancontractqueryAF" property="list">
								<tr>
									<td colspan="14" height="30" style="color:red">
										没有找到与条件相符合的结果！
									</td>
								</tr>

							</logic:empty>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr class="td1">
								<td align="center">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr align="center">
											<td align="center">
												<table width="95%" border="0" cellspacing="0"
													cellpadding="3" align="center">
													<tr class="td1">

														<td align="left">
															共
															<bean:write name="pagination" property="nrOfElements" />
															条记录
														</td>
														<td align="right">
															<jsp:include page="../../../../inc/pagination.jsp">
																<jsp:param name="url" value="loancontractqueryshowAC.do" />
															</jsp:include>

														</td>
													</tr>
												</table>

											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table width="184" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="73" align="center" valign="middle">
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
