<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.action.LoanDeskaccQueryTbShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			LoanDeskaccQueryTbShowAC.PAGINATION_KEY);
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

function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="打印"){
				s1=i;
			}
			
		}
	} 
	var count=document.all.count.value
	if(count==0){
		document.all.item(s1).disabled="true";
	}
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
							<td width="235" background="<%=path%>/img/table_bg_line.gif"
								valign="top">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="1"></td>
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
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="196">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="663">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/loanDeskaccQueryTbFindAC" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr>
								<td width="17%" class="td1" height="27">
									凭证编号
								</td>
								<td width="33%" colspan="3" height="27">
									<html:text property="docnum" name="loanDeskaccQueryTbAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="17%" class="td1" height="27">
									业务类型
								</td>
								<td width="33%" height="27">
									<html:select name="loanDeskaccQueryTbAF" property="biztype"
										styleClass="input4" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="biztypeMap"
											name="loanDeskaccQueryTbAF" label="value" value="key"
											styleClass="input3" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									业务日期
								</td>
								<td width="14%" align="center">
									<html:text property="bizdateB" name="loanDeskaccQueryTbAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="4%" align="center">
									至
								</td>
								<td width="15%" align="center">
									<html:text property="bizdateE" name="loanDeskaccQueryTbAF"
										styleClass="input3" onkeydown="enterNextFocus1();" />
								</td>
								<td width="17%" class="td1"></td>
								<td width="33%">
								</td>
							</tr>
						</table>

						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<html:submit property="method" styleClass="buttona">
										<bean:message key="button.search" />
									</html:submit>
								</td>
							</tr>
						</table>
					</html:form>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="134">
											<b class="font14">借款人信息</b>
										</td>
										<td width="451" height="22" align="center"
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
						<tr>
							<td width="17%" class="td1">
								合同编号
								<br>
							</td>
							<td>
								<html:text property="contractid" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
							<html:hidden property="count" name="loanDeskaccQueryTbAF" />
							<td class="td1" width="17%">
								扣款账号
								<br>
							</td>
							<td width="33%">
								<html:text property="loankouacc" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td>
								<html:text property="borrowername" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%">
								<html:text property="cardnum" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td>
								<html:text property="loanbank" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								贷款金额
							</td>
							<td width="33%">
								<html:text property="loanmoney" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								贷款期限（月）
							</td>
							<td>
								<html:text property="loanlimit" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								还款方式
							</td>
							<td colspan="2">
								<html:text property="loanmode" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								贷款余额
							</td>
							<td>
								<html:text property="overplusloanmoney"
									name="loanDeskaccQueryTbAF" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								挂账余额
							</td>
							<td colspan="2">
								<html:text property="oveaerloanrepay"
									name="loanDeskaccQueryTbAF" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								剩余期限
							</td>
							<td>
								<html:text property="overlimited" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								其他欠款
							</td>
							<td colspan="2">
								<html:text property="otherArrearage" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								总还本金
							</td>
							<td>
								<html:text property="srealcorpus" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								总还利息
							</td>
							<td colspan="2">
								<html:text property="srealinterest" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								总还逾期利息
							</td>
							<td>
								<html:text property="srealpunishinterest"
									name="loanDeskaccQueryTbAF" styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								欠还本金
							</td>
							<td colspan="2">
								<html:text property="owercorpus" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								欠还利息
							</td>
							<td>
								<html:text property="oweinterest" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								欠还逾期利息
							</td>
							<td colspan="2">
								<html:text property="owepunishinterest"
									name="loanDeskaccQueryTbAF" styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								月还本息
							</td>
							<td>
								<html:text property="corputInterest" name="loanDeskaccQueryTbAF"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								下月应还款额
							</td>
							<td colspan="2">
								<html:text property="shouldCorputInterest"
									name="loanDeskaccQueryTbAF" styleClass="input3" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								总计：总发放金额
								<u>：<bean:write name="loanDeskaccQueryTbAF"
										property="tolaccordmoney" /> </u> 总挂账金额
								<u>：<bean:write name="loanDeskaccQueryTbAF"
										property="toloverloanmoney" /> </u> 总实还本金
								<u>：<bean:write name="loanDeskaccQueryTbAF"
										property="tolrealcorpus" /> </u> 总实还利息
								<u>：<bean:write name="loanDeskaccQueryTbAF"
										property="tolrealinterest" /> </u> 总实还逾期利息
								<u>：<bean:write name="loanDeskaccQueryTbAF"
										property="tolrealpunishinterest" /> </u> 总回收金额
								<u>：<bean:write name="loanDeskaccQueryTbAF" property="sumr" />
								</u>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="202">
											<b class="font14">个人流水</b>
										</td>
										<td width="703" height="22" align="center"
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

					<html:form action="/loanDeskaccQueryTbPrintAC" style="margin: 0">
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr>
								<td width="5%" height="23" align="center" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td align="center" bgcolor="C4F0FF">
									<a href="javascript:sort('p202.biz_date')">业务日期</a>
									<logic:equal name="pagination" property="orderBy"
										value="p202.biz_date">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									还款年月
								</td>
								<td align="center" class=td2>
									凭证编号
								</td>
								<td align="center" class=td2>
									业务类型
								</td>

								<td align="center" class=td2>
									发放金额
								</td>
								<td align="center" class=td2>
									挂账金额
								</td>
								<td align="center" class=td2>
									实还本金
								</td>
								<td align="center" class=td2>
									实还利息
								</td>
								<td align="center" class=td2>
									实还逾期利息
								</td>
								<td align="center" class=td2>
									实还本息合计
								</td>
								<td align="center" class=td2>
									还款类型
								</td>
								<td align="center" class=td2>
									是否为公积金还贷
								</td>
							</tr>
							<logic:notEmpty name="loanDeskaccQueryTbAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="loanDeskaccQueryTbAF" property="list"
									id="elments" indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr id="tr<%=i%>"
										onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
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
											<bean:write name="elments" property="bizdate" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="yearmonth" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="docnum" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="biztype" />
										</td>

										<td valign="top">
											<bean:write name="elments" property="accordmoney" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="overloanmoney" />
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
										<td valign="top">
											<bean:write name="elments" property="sumCorpusInterest" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="loantype" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="batchNum" />
										</td>
									</tr>
								</logic:iterate>

							</logic:notEmpty>
							<logic:empty name="loanDeskaccQueryTbAF" property="list">
								<tr>
									<td colspan="8" height="30" style="color:red">
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
																	value="loanDeskaccQueryTbShowAC.do" />
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
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="70">
												<input type="button" name="Submit3" value="关闭"
													class="buttona" onclick="window.close()" />
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

