<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.action.LoanDeskaccQueryTaShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			LoanDeskaccQueryTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<html>
	<head>
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js"></script>
	</head>

	<script type="text/javascript">
var s1="";
var s2="";
var s3="";
function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="查看个人流水"){
				s1=i;
			}
			if(document.all.item(i).value=="合同信息"){
				s2=i;
			}
			if(document.all.item(i).value=="打印"){
				s3=i;
			}
			
		}
	} 
	var count=document.all.count.value;
	if(count==0){
		document.all.item(s1).disabled="true";
  		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
	}
}
//开发商弹出框
function findorghouses(){

  	window.open("<%=path%>/sysloan/develepershowAC.do?indexs="+0+"&objInput=headname"+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=no"); 
 
}
//楼盘弹出框
function findBuilding(){
	var developerId = document.all.buyhouseorgid.value.trim();
	window.open("<%=path%>/sysloan/buildingPopShowAC.do?indexs="+6+"&developerId="+developerId+
		"&objInput=floorName"+"&qx=no","window","height=600,width=700,top="+(window.screen.availHeight-500)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=no"); 
}

function tocontrractInfo(){
	var contractIdHL=getCheckId();
	window.open("<%=path%>/sysloan/showLoanCheckTaAC.do?contractIdWY="+contractIdHL,"window","height=700,width=770,top="+(window.screen.availHeight-700)/2+",left="+(window.screen.availWidth-770)/2+",scrollbars=no,location=no,status=no");
	return false;
}
function gotoindividualflow(){
	var contractIdHL=getCheckId();
	window.open("<%=path%>/sysloan/loanDeskaccQueryTbForwardAC.do?contractIdHl="+contractIdHL,"window","height=700,width=900,top="+(window.screen.availHeight-700)/2+",left="+(window.screen.availWidth-900)/2+",scrollbars=yes,location=no,status=no");
	return false;
}

function toPopOrg_1(status,index) {
	window.open("<%=path%>/sysloan/orgpopTaFindAC.do?status="+status+"&indexs="+index+"&qx=yes","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}

function executeAjaxOrg_1(){
	var x = document.loanDeskaccQueryTaAF.elements["orgId"].value.trim();
		toPopOrg_1(12345,'17');
}


</script>
	<body bgcolor="#FFFFFF" text="#000000" onload="loads()">
		<table width="1200" border="0" cellspacing="0" cellpadding="0"
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
											<font color="00B5DB">统计查询&gt;贷款台账查询</font>
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
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="196">
														<b class="font14">查 询 条 件</b>
													</td>
													<td height="22" background="<%=path%>/img/bg2.gif"
														align="center" width="949">
														&nbsp;
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<html:form action="/loanDeskaccQueryTaFindAC" style="margin: 0">
									<table width="95%" border="0" cellpadding=0 cellspacing=1
										id="table1">
										<tr>
											<td width="17%" class="td1">
												办事处
											</td>
											<td width="33%" colspan="3">
												<html:select name="loanDeskaccQueryTaAF" property="offic"
													styleClass="input4" onkeydown="enterNextFocus1();">
													<html:option value=""></html:option>
													<html:options collection="officlist" property="value"
														labelProperty="label" />
												</html:select>
											</td>
											<td width="15%" class="td1">
												放款银行
											</td>
											<td align="center" colspan="3">
												<html:select name="loanDeskaccQueryTaAF" property="payBank"
													styleClass="input4" onkeydown="enterNextFocus1();">
													<html:option value=""></html:option>
													<html:options collection="paybanklist" property="value"
														labelProperty="label" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td width="17%" class="td1">
												开发商
											</td>
											<td align="center" colspan="2">
												<html:text property="developer" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
											<td align="left">
												<input type="button" name="submit12" class=buttona
													value="..." onclick="findorghouses();"
													onkeydown="enterNextFocus1();" />
											</td>
											<html:hidden property="buyhouseorgid"
												name="loanDeskaccQueryTaAF" />
											<html:hidden property="count" name="loanDeskaccQueryTaAF" />
											<td width="15%" class="td1">
												房屋类型
											</td>
											<td align="center" colspan="3">
												<html:select property="houseType" styleClass="input4">
													<html:option value=""></html:option>
													<html:optionsCollection property="houseTypeMap"
														name="loanDeskaccQueryTaAF" label="value" value="key" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td width="17%" class="td1">
												合同编号
											</td>
											<td align="center" colspan="3">
												<html:text property="contactid" name="loanDeskaccQueryTaAF"
													styleClass="input3" styleId="txtsearch" />
											</td>
											<td width="15%" class="td1">
												扣款账号
											</td>
											<td align="center" colspan="3">
												<html:text property="loankouacc" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
										</tr>
										<tr>
											<td width="17%" class="td1">
												担保公司
											</td>
											<td width="33%" colspan="3">
												<html:select name="loanDeskaccQueryTaAF"
													property="assistantorg" styleClass="input4"
													onkeydown="enterNextFocus1();">
													<html:option value=""></html:option>
													<html:options collection="assistantorglist"
														property="value" labelProperty="label" />
												</html:select>
											</td>
											<td width="15%" class="td1">
												合同状态
											</td>
											<td align="left" colspan="3">
												<html:select name="loanDeskaccQueryTaAF"
													property="contact_st" styleClass="input4"
													onkeydown="enterNextFocus1();">
													<html:option value=""></html:option>
													<html:optionsCollection property="contact_stMap"
														name="loanDeskaccQueryTaAF" label="value" value="key" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td width="17%" class="td1">
												借款人姓名
											</td>
											<td align="center" colspan="3">
												<html:text property="borrowerName"
													name="loanDeskaccQueryTaAF" styleClass="input3"
													styleId="txtsearch" />
											</td>
											<td width="15%" class="td1">
												证件号码
											</td>
											<td align="center" colspan="3">
												<html:text property="cardNum" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
										</tr>
										<tr>
											<td width="17%" class="td1">
												贷款金额（元）
											</td>
											<td width="15%" align="center">
												<html:text property="loanMoneyB" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
											<td width="3%" align="center">
												至
											</td>
											<td width="15%" align="center">
												<html:text property="loanMoneyE" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
											<td width="15%" class="td1">
												贷款期限（月）
											</td>
											<td align="center" width="15%">
												<html:text property="loanLimitB" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
											<td width="3%" align="center">
												至
											</td>
											<td align="center" width="15%">
												<html:text property="loanLimitE" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
										</tr>
										<tr>
											<td width="17%" class="td1">
												发放日期
											</td>
											<td width="15%" align="center">
												<html:text property="loanStartDateB"
													name="loanDeskaccQueryTaAF" styleClass="input3"
													onkeydown="enterNextFocus1();" />
											</td>
											<td width="3%" align="center">
												至
											</td>
											<td width="15%" align="center">
												<html:text property="loanStartDateE"
													name="loanDeskaccQueryTaAF" styleClass="input3"
													onkeydown="enterNextFocus1();" />
											</td>
											<td width="15%" class="td1">
												年龄
											</td>
											<td align="center" width="15%">
												<html:text property="ageB" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
											<td width="3%" align="center">
												至
											</td>
											<td align="center" width="15%">
												<html:text property="ageE" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
										</tr>
										<tr>
											<td width="17%" class="td1">
												单位编号
											</td>
											<td align="center" colspan="2">
												<html:text property="orgId" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
											<td align="left">
												<input type="button" name="submit12" class=buttona
													value="..." onclick="executeAjaxOrg_1();"
													onkeydown="enterNextFocus1();" />
											</td>
											<td width="15%" class="td1">
												住房面积（元/M
												<sup>
													2
												</sup>
												）
											</td>
											<td align="center" width="15%">
												<html:text property="areaB" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
											<td width="3%" align="center">
												至
											</td>
											<td align="center" width="15%">
												<html:text property="areaE" name="loanDeskaccQueryTaAF"
													styleClass="input3" onkeydown="enterNextFocus1();" />
											</td>
										</tr>
										<tr>
											<td width="17%" class="td1">
												异地贷款
											</td>
											<td align="center" colspan="3">
												<html:select property="loanType" name="loanDeskaccQueryTaAF"
													styleClass="input4">
													<html:option value=""></html:option>
													<html:optionsCollection property="loanTypeMap"
														name="loanDeskaccQueryTaAF" label="value" value="key" />
												</html:select>
											</td>
										</tr>
									</table>
									<table width="95%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td align="right">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.search" />
												</html:submit>
											</td>
										</tr>
									</table>
								</html:form>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								总计： 贷款户数
								<u>：<bean:write name="loanDeskaccQueryTaAF" property="hushu" />
								</u> 面积
								<u>：<bean:write name="loanDeskaccQueryTaAF"
										property="mianji" /> </u> 房屋总价
								<u>：<bean:write name="loanDeskaccQueryTaAF"
										property="fangwuzongjia" /> </u> 贷款金额
								<u>：<bean:write name="loanDeskaccQueryTaAF"
										property="tolloanMoney" /> </u> 贷款余额
								<u>：<bean:write name="loanDeskaccQueryTaAF" property="dkyue" />
								</u> 挂账余额
								<u>：<bean:write name="loanDeskaccQueryTaAF"
										property="tolovareloanrepay" /> </u> 月还款额
								<u>：<bean:write name="loanDeskaccQueryTaAF"
										property="tolmonthpay" /> </u> 总还本金
								<u>：<bean:write name="loanDeskaccQueryTaAF"
										property="tolrealcorpus" /> </u> 总还利息
								<u>：<bean:write name="loanDeskaccQueryTaAF"
										property="tolrealinterest" /> </u> 总还罚息利息
								<u>：<bean:write name="loanDeskaccQueryTaAF"
										property="tolrealpunishinterest" /> </u>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="189">
											<b class="font14">贷 款 列 表</b>
										</td>
										<td width="949" height="22" align="center"
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

					<html:form action="/loanDeskaccQueryTaMaintainAC" style="margin: 0">
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr align="center" bgcolor="C4F0FF">
								<td height="23" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td class="td2" align="center">
											<a href="javascript:sort('p11.contract_id')">合同编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="p11.contract_id">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
								<td align="center" class=td2>
									借款人姓名
								</td>
								<td align="center" class=td2>
									证件号码
								</td>
								<td align="center" class=td2>
									单位编号
								</td>
								<td align="center" class=td2>
									单位名称
								</td>
								<td align="center" class=td2>
									放款银行
								</td>
								<td align="center" class=td2>
									扣款账号
								</td>
								<td align="center" class=td2>
									贷款金额（元）
								</td>
								<td align="center" class=td2>
									贷款期限（月）
								</td>
								<td align="center" class=td2>
									发放日期
								</td>
								<td align="center" class=td2>
									合同状态
								</td>
								<td align="center" class=td2>
									贷款余额
								</td>
								<td align="center" class=td2>
									挂账余额
								</td>
								<td align="center" class=td2>
									月还款额
								</td>
								<td align="center" class=td2>
									总还本金
								</td>
								<td align="center" class=td2>
									总还利息
								</td>
								<td align="center" class=td2>
									总还罚息利息
								</td>
<%--								<td align="center" class=td2>--%>
<%--									逾期月数--%>
<%--								</td>--%>

								<td align="center" class=td2>
									住房面积（M
									<sup>
										2
									</sup>
									）
								</td>
								<td align="center" class=td2>
									公积金还贷
								</td>
							</tr>
							<logic:notEmpty name="loanDeskaccQueryTaAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="loanDeskaccQueryTaAF" property="list"
									id="elments" indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>", idAF);'
										onMouseOver='this.style.background="#eaeaea"'
										onMouseOut='gotoColorpp("<%=i%>", idAF);'
										class="<%=strClass%>">

										<td valign="top">
											<p align="left">
												<input id="s<%=i%>" type="radio" name="id"
													value="<bean:write name="elments" property="id"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>
										<td valign="top">
											<bean:write name="elments" property="contactid" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="borrowername" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="cardnum" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="orgId" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="orgName" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="paybank" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loankouacc" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanmoney" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanlimit" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanstartdate" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="contact_st" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loanBalance" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="ovareloanrepay" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="monthpay" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="realcorpus" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="realinterest" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="realpunishinterest" />
										</td>
<%--										<td valign="top">--%>
<%--											<bean:write name="elments" property="owemonth" />--%>
<%--										</td>--%>
										<td valign="top">
											<bean:write name="elments" property="areaFrist" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="isloanPay" />
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="loanDeskaccQueryTaAF" property="list">
								<tr>
									<td colspan="7" height="30" style="color:red">
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
																<jsp:param name="url"
																	value="loandeskaccquerytashowAC.do" />
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
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="110">
												<html:submit property="method" styleClass="buttonc"
													onclick="return gotoindividualflow()">
													<bean:message key="button.individual.flow" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return tocontrractInfo()">
													<bean:message key="button.contract.info" />
												</html:submit>
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
		</table>
	</body>
</html>

