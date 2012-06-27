<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.action.OverdueQueryTaShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			OverdueQueryTaShowAC.PAGINATION_KEY);
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
	function check(){
		/*var bank = document.overdueQueryTaAF.elements["bankId"].value.trim();
		if(bank==""){
			alert("请选择银行!");
			document.overdueQueryTaAF.elements["bankId"].focus();
			return false;
		}*/
		return true;
	}
	function viewContractInfo(id) {
		window.open('<%=path%>/sysloan/showLoanVIPCheckTaAC.do?contractIdWY='+id,'window','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,location=no,status=no');
	}
	function viewOverdueDetail(id) {
		window.open('<%=path%>/sysloan/overdueQueryTbShowAC.do?contractId='+id,'window','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,location=no,status=no');
	}
	</script>
	<body bgcolor="#FFFFFF" text="#000000">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			align="center" align="center">
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
											<font color="00B5DB">统计查询&gt;逾期催还查询</font>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="205">
											<b class="font14">查 询 条 件</b>
										</td>
										<td width="720" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/overdueQueryTaFindAC" style="margin: 0">
						<table width="95%" border="0" cellpadding=0 align="center">
							<tr>
								<td width="17%" class="td1">
									办事处
								</td>
								<td align="center" colspan="3">
									<html:select onkeydown="enterNextFocus1();"
										name="overdueQueryTaAF" property="officeCode"
										styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="officeList" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td width="17%" class="td1">
									放款银行
								</td>
								<td width="33%" align="center" colspan="3">
									<html:select onkeydown="enterNextFocus1();"
										name="overdueQueryTaAF" property="bankId" styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="loanBankNameList" property="value"
											labelProperty="label" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									合同编号
								</td>
								<td align="center" colspan="3">
									<html:text onkeydown="enterNextFocus1();" property="contractId"
										name="overdueQueryTaAF" styleClass="input3" />
								</td>
								<td width="17%" class="td1">
									借款人姓名
								</td>
								<td align="center" colspan="3">
									<html:text onkeydown="enterNextFocus1();"
										property="borrowerName" name="overdueQueryTaAF"
										styleClass="input3" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									身份证号码
								</td>
								<td align="center" colspan="3">
									<html:text onkeydown="enterNextFocus1();" property="cardNum"
										name="overdueQueryTaAF" styleClass="input3" />
								</td>
								<td width="17%" class="td1">
									扣款账号
								</td>
								<td align="center" colspan="3">
									<html:text onkeydown="enterNextFocus1();" property="loanKouAcc"
										name="overdueQueryTaAF" styleClass="input3" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									逾期月数
								</td>
								<td width="15%" align="center" colspan="1">
									<html:text onkeydown="enterNextFocus1();"
										property="overdueMonthSt" name="overdueQueryTaAF"
										styleClass="input3" />
								</td>
								<td width="3%" align="center">
									至
								</td>
								<td width="15%" align="center" colspan="1">
									<html:text onkeydown="enterNextFocus1();"
										property="overdueMonthEnd" name="overdueQueryTaAF"
										styleClass="input3" />
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<html:submit property="method" styleClass="buttona"
										onclick="return check();">
										<bean:message key="button.search" />
									</html:submit>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td class=h4>
									合计：户数
									<u>：<bean:write name="pagination" property="nrOfElements" />
									</u> 欠还本金总额
									<u>：<bean:write name="overdueQueryTaAF"
											property="shouldCorpusSum" /> </u> 欠还利息总额
									<u>：<bean:write name="overdueQueryTaAF"
											property="shouldInterestSum" /> </u> 逾期率
									<u>：<bean:write name="overdueQueryTaAF"
											property="overdueRate" /> </u>逾期率(金额)
									<u>：<bean:write name="overdueQueryTaAF"
											property="overdueMoneyRate" /> </u>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="204">
											<b class="font14">逾 期 催 还 列 表</b>
										</td>
										<td width="721" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/overdueQueryTaMaintainAC.do" style="margin: 0">
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
									合同编号
								</td>
								<td align="center" class=td2>
									借款人姓名
								</td>
								<td align="center" class=td2>
									单位名称
								</td>
								<td align="center" class=td2>
									扣款账号
								</td>
								<td align="center" class=td2>
									住宅电话
								</td>
								<td align="center" class=td2>
									移动电话
								</td>
								<td align="center" class=td2>
									配偶姓名
								</td>
								<td align="center" class=td2>
									配偶工作单位
								</td>
								<td align="center" class=td2>
									配偶电话
								</td>
								<td align="center" class=td2>
									借款金额
								</td>
								<td align="center" class=td2>
									本金余额
								</td>
								<td align="center" class=td2>
									发放日期
								</td>
								<td align="center" class=td2>
									还至年月
								</td>
								<td align="center" class=td2>
									逾期月数
								</td>
								<td align="center" class=td2>
									应还本金
								</td>
								<td align="center" class=td2>
									应还利息
								</td>
								<td align="center" class=td2>
									本息合计
								</td>
								<td align="center" class=td2>
									逾期利息
								</td>
								<td align="center" class=td2>
									查看明细
								</td>
							</tr>
							<logic:notEmpty name="overdueQueryTaAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="overdueQueryTaAF" property="list"
									id="elements" indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr id="tr<%=i%>" class="<%=strClass%>">
										<td>
											<bean:write name="elements" property="contractId" />
										</td>
										<td>
											<a href="#"
												onclick="viewContractInfo('<bean:write name="elements" property="contractId" />')">
												<bean:write name="elements" property="borrowerName" /> </a>
										</td>
										<td>
											<bean:write name="elements" property="borrowerOrgName" />
										</td>
										<td>
											<bean:write name="elements" property="loanKouAcc" />
										</td>
										<td>
											<bean:write name="elements" property="borrowerTel" />
										</td>
										<td>
											<bean:write name="elements" property="borrowerMobile" />
										</td>
										<td>
											<bean:write name="elements" property="astBorrowerName" />
										</td>
										<td>
											<bean:write name="elements" property="astBorrowerOrgName" />
										</td>
										<td>
											<bean:write name="elements" property="astBorrowerMobile" />
										</td>
										<td>
											<bean:write name="elements" property="loanMoney" />
										</td>
										<td>
											<bean:write name="elements" property="balance" />
										</td>
										<td>
											<bean:write name="elements" property="loanStartDate" />
										</td>
										<td>
											<bean:write name="elements" property="repayMonth" />
										</td>
										<td>
											<bean:write name="elements" property="overdueMonths" />
										</td>
										<td>
											<bean:write name="elements" property="corpus" />
										</td>
										<td>
											<bean:write name="elements" property="interest" />
										</td>
										<td>
											<bean:write name="elements" property="shouldPayMoney" />
										</td>
										<td>
											<bean:write name="elements" property="punishInterest" />
										</td>
										<td>
											<a class="a2" href="#"
												onclick="viewOverdueDetail('<bean:write name="elements" property="contractId" />')">
												<img src="<%=path%>/img/mx.gif" width="30" height="30"
													style="border-color:white"> </a>
										</td>
									</tr>

								</logic:iterate>

							</logic:notEmpty>
							<logic:empty name="overdueQueryTaAF" property="list">
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
											<td align="left">
												共
												<bean:write name="pagination" property="nrOfElements" />
												项
											</td>
											<td align="right">
												<jsp:include page="/inc/pagination.jsp">
													<jsp:param name="url" value="overdueQueryTaShowAC.do" />
												</jsp:include>
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
										<logic:notEmpty name="overdueQueryTaAF" property="list">
											<tr>
												<td>
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.print" />
													</html:submit>
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.export" />
													</html:submit>
												</td>
											</tr>
										</logic:notEmpty>
										<logic:empty name="overdueQueryTaAF" property="list">
											<tr>
												<td>
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.print" />
													</html:submit>
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.export" />
													</html:submit>
												</td>
											</tr>
										</logic:empty>
									</table>
								</td>
							</tr>
						</table>
					</html:form>
		</table>
	</body>
</html>


