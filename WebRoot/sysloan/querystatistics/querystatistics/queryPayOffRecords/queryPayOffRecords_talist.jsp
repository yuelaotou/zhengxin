<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.action.QueryPayOffRecordsTaShowAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			QueryPayOffRecordsTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>还清档案统计查询</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
</head>
<script language="javascript"
	src="<%=request.getContextPath()%>/js/common.js">




<script language="javascript"></script>
<script language="javascript" type="text/javascript">
var s1="";

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>

}

function backErrors(errors){
   alert(errors);
}

function executeAjax()
  {
 
  }
  function showlist() {
   document.Form1.submit();
}
function gotoShow(){
	return false;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()"
	onContextmenu="return false">

	<jsp:include page="/inc/sort.jsp">
		<jsp:param name="url" value="queryPayOffRecordsTaShowAC.do" />
	</jsp:include>

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=request.getContextPath()%>/img/table_left.gif"
								width="7" height="37">
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							width="55">
							&nbsp;
						</td>

						<td width="235"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttonbl.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										还清档案统计查询
									</td>

								</tr>
							</table>
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB"> 统计查询 &gt;还清档案统计 </font>
									</td>
									<td class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=request.getContextPath()%>/img/table_right.gif"
								width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<html:form action="/queryPayOffRecordsTaFindAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
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
							<td width="17%" class="td1">
								办事处
							</td>
							<td width="33%" align="center" colspan="3">

								<html:select name="queryPayOffRecordsTaShowAF" property="office"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>

							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td align="center" colspan="3">

								<html:select name="queryPayOffRecordsTaShowAF"
									property="loanBankName" styleClass="input4">
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
							<td width="33%" align="center" colspan="3">
								<html:text name="queryPayOffRecordsTaShowAF"
									property="contractId" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								姓名
							</td>
							<td align="center" colspan="3">
								<html:text name="queryPayOffRecordsTaShowAF"
									property="borrowerName" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								发放日期
							</td>
							<td width="15%" align="center">
								<html:text name="queryPayOffRecordsTaShowAF"
									property="loanStartDate" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%" align="center">
								<html:text name="queryPayOffRecordsTaShowAF"
									property="loanEndDate" styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								身份证号
							</td>
							<td width="33%" align="center" colspan="3">
								<html:text name="queryPayOffRecordsTaShowAF" property="cardNum"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								还清日期
							</td>
							<td width="15%" align="center">
								<html:text name="queryPayOffRecordsTaShowAF"
									property="loanPayOffDate" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%" align="center">
								<html:text name="queryPayOffRecordsTaShowAF"
									property="loanPayOffEndDate" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								房屋类型
							</td>
							<td width="33%" align="center">
								<html:select name="queryPayOffRecordsTaShowAF"
									property="houseType" styleClass="input4"
									onkeydown="enterNextFocus1();" onchange="changes();">
									<html:option value=""></html:option>
									<html:optionsCollection property="houseTypeMap"
										name="queryPayOffRecordsTaShowAF" label="value" value="key" />
								</html:select>
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
						<td class=h4>
							合计：人数
							<u>：<bean:write name="queryPayOffRecordsTaShowAF"
									property="peopleNumSum" /> </u> 贷款金额
							<u>：<bean:write name="queryPayOffRecordsTaShowAF"
									property="loanMoneySum" /> </u> 利息总额，
							<u>：<bean:write name="queryPayOffRecordsTaShowAF"
									property="interestSum" /> </u>还款总额，
							<u>：<bean:write name="queryPayOffRecordsTaShowAF"
									property="corpusSum" /> </u>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">还清档案列表</b>
									</td>
									<td width="750" height="22" align="center"
										background="<%=request.getContextPath()%>/img/bg2.gif">
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

				<html:form action="/queryPayOffRecordsTaMaintainAC"
					style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2" align="center">
								<a href="javascript:sort('pl111.contract_id')">合同编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="pl111.contract_id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								姓名
							</td>
							<td align="center" class=td2>
								贷款金额
							</td>
							<td align="center" class=td2>
								贷款期限
							</td>

							<td align="center" class=td2>
								利息总额
							</td>
							<td align="center" class=td2>
								还款总额
							</td>

							<td align="center" class=td2>
								发放日期
							</td>
							<td align="center" class=td2>
								到期日期
							</td>
							<td class="td2" align="center">
								<a href="javascript:sort('pl111.PAYOFFDATE')">还清日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="pl111.PAYOFFDATE">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
						</tr>


						<logic:notEmpty name="queryPayOffRecordsTaShowAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="queryPayOffRecordsTaShowAF"
								property="list" indexId="i">
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
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass%>"
									onDblClick="">
									<td>
										<div align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="contractId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</div>
									</td>


									<td>
										<div align="left">
											<bean:write name="element" property="contractId" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="borrowerName" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="loanMoney" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="loanTimeLimit" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="interest" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="corpus" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="loanStartDate" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="loanRepayDay" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="loanPayOffDate" />
										</div>
									</td>

								</tr>
							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="queryPayOffRecordsTaShowAF" property="list">
							<tr>
								<td colspan="20" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
						</logic:empty>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="/inc/pagination.jsp">
												<jsp:param name="url" value="queryPayOffRecordsTaShowAC.do" />
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
									<logic:empty name="queryPayOffRecordsTaShowAF" property="list">
										<tr>
											<td width="72">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="queryPayOffRecordsTaShowAF"
										property="list">
										<tr>
											<td width="72">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</tr>
									</logic:notEmpty>
								</table>
							</td>
						</tr>

					</table>
				</html:form>
				<form action="queryPayOffRecordsTaShowAC.do" method="POST"
					name="Form1" id="Form1">
				</form>
	</table>
</body>
</html:html>

