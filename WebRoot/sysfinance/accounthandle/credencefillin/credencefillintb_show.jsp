<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page
	import="org.xpup.hafmis.sysfinance.accounthandle.credencefillin.action.CredenceFillinTbShowAC"%>
<%@page import="java.math.BigDecimal"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = session
			.getAttribute(CredenceFillinTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	List selectArray = (List) request.getAttribute("selectedArray");
	BigDecimal moneyall = (BigDecimal) request.getAttribute("moneyall");
	if (moneyall == null) {
		moneyall = new BigDecimal(0.00);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>自动转帐</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>

</head>

<script>
var s1="";
var s2="";
function reportErrors() {
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="全部生成凭证"){
				s1=i;
			}
			if(document.all.item(i).value=="生成凭证"){
				s2=i;
			}	
		}
	}
	var indexs = document.getElementsByName("settNum");
	document.all.rowArrayHid.value="<bean:write name="ids"/>";
	for(var i=0;i<indexs.length;i++){
		<%
			for(int j=0;j<selectArray.size();j++) {
				String str = (String)selectArray.get(j);
		%>
				if(indexs[i].value=="<%=str%>"){
					indexs[i].checked=true;
				}
		<%
			}
		%>
		
	}
	
}

//从弹出窗口调回的
function submit_ws(button){
	var credenceDate = document.all.sett_date.value.trim();
	var oldCreNum = document.all.oldCreNum.value.trim();
	if(credenceDate!=""&&oldCreNum!=""){
		if(button == "s1"){
			document.all.item(s1).click();
		}
		if(button == "s2"){
			document.all.item(s2).click();
		}
	}
}

function onCreatecredenceAll(param){
	var credenceDate = document.all.sett_date.value.trim();
	if(credenceDate==""){
	  	var x=confirm("是否要进行全部转账！");
	  	if(x){
			popWindow(param);
			return false;
	  	}else{
	    	return false;
	  	}
	}
}
function checkDate(){
	
	var bankId = document.forms[0].elements["credenceFillinTbFindDTO.bankId"].value.trim();
	/*if(bizType==""){
		alert("请选择业务类型!");
		document.forms[0].elements["credenceFillinTbFindDTO.bizType"].focus();
		return false;
	}*/
	if(bankId==""){
		alert("请选择归集银行!");
		document.forms[0].elements["credenceFillinTbFindDTO.bankId"].focus();
		return false;
	}
}
function popWindow(param){
	var str = "";
	var credenceDate = document.all.sett_date.value.trim();
	var oldCreNum = document.all.oldCreNum.value.trim();
	if(param.value=="全部生成凭证"){
		str = "s1";
	}
	if(param.value=="生成凭证"){
		str = "s2";
	}
	if(credenceDate==""&&oldCreNum==""){
		var settDateStart = document.forms[0].elements["credenceFillinTbFindDTO.settDateStart"].value.trim();
		window.open('<%=path%>/sysfinance/accounthandle/credencefillin/credencefillinTbPopShow.jsp?sett_date='+settDateStart+
		'&button='+str,'','width=500,height=100,top=200,left=300,scrollbars=no');
		return false;
	}
}
function showIncomeExpense(incomeOrExpense){
	window.open('<%=path%>/sysfinance/credenceFillinShowIncomeExpenseAC.do?incomeOrExpense='+incomeOrExpense
		,'','width=800,height=500,top=200,left=300,scrollbars=yes');
	return false;
}

</script>
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();"
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
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">账户处理&gt;自动转账</font>
									</td>
									<td class=font14>
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
				<html:form action="/credenceFillinTbFindAC">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="130">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="744">
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
							<td width="15%" class="td1">
								结算日期
								<br>
							</td>
							<td width="15%">
								<html:text name="credenceFillinTbAF"
									property="credenceFillinTbFindDTO.settDateStart"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="8"></html:text>
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="credenceFillinTbAF"
									property="credenceFillinTbFindDTO.settDateEnd"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="8"></html:text>
							</td>
							<td width="15%" class="td1">
								结算号
							</td>
							<td width="35%">
								<html:text name="credenceFillinTbAF"
									property="credenceFillinTbFindDTO.settNum" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								业务状态
							</td>
							<td width="35%" colspan="3">
								<html:select name="credenceFillinTbAF"
									property="credenceFillinTbFindDTO.bizSt" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizStMap"
										name="credenceFillinTbAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="15%" class="td1">
								业务类型
								<br>
							</td>
							<td width="35%">
								<html:select name="credenceFillinTbAF"
									property="credenceFillinTbFindDTO.bizType" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizTypeMap"
										name="credenceFillinTbAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								归集银行
							</td>
							<td width="35%" colspan="3">
								<html:select name="credenceFillinTbAF"
									property="credenceFillinTbFindDTO.bankId" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:options collection="bankList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="15%" class="td1"></td>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkDate();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/credenceFillinTbMainTainAC">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="left" width="330">
											<b class="font14">自动转账列表 金额合计: 
												<bean:write name="credenceFillinTbAF" property="moneyall" format="#,##0.00"/>
											</b>
										</td>
										<td width="544" height="22" align="left"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<input type="hidden" name="sett_date" value="" />
					<input type="hidden" name="oldCreNum" value="" />
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2">
								<a href="javascript:sort('res.fn120type')">业务类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="res.fn120type">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('res.settdate')">结算日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="res.settdate">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('res.notenum')">结算号</a>
								<logic:equal name="pagination" property="orderBy"
									value="res.notenum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								发生总金额
							</td>
						</tr>
						<logic:notEmpty name="credenceFillinTbAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="credenceFillinTbListDTO"
								name="credenceFillinTbAF" property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr class="<%=strClass%>">
									<td>
										<input id="settNum" type="checkbox" name="settNum"
											onclick="saveId(this);"
											value="<bean:write name="credenceFillinTbListDTO" property="settNum"/>,<bean:write name="credenceFillinTbListDTO" property="numBizType"/>">
									</td>
									<td>
										<p>
											<bean:write name="credenceFillinTbListDTO" property="bizType" />
										</p>
									</td>
									<td>
										<p>
											<bean:write name="credenceFillinTbListDTO"
												property="settDate" />
										</p>
									</td>
									<td>
										<p>
											<a href="#"
												onClick="window.open('queryFlowShowAC.do?settNum=<bean:write 
													name="credenceFillinTbListDTO" property="settNum"/>','','width=900,height=400,top='+(window.screen.availHeight-400)/2+',left='+(window.screen.availWidth-900)/2+',scrollbars=yes');">
												<bean:write name="credenceFillinTbListDTO"
													property="settNum" /> </a>
										</p>
									</td>
									<td align="right">
										<p>
											<bean:write name="credenceFillinTbListDTO"
												property="sumOccurMoney" format="#,##0.00" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="credenceFillinTbAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
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
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="credenceFillinTbShowAC.do" />
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
									<tr>
										<logic:notEmpty name="credenceFillinTbAF" property="list">
											<td width="69" align="center">
												<html:submit property="method" styleClass="buttona"
													onclick="return showIncomeExpense('income')">
													<bean:message key="button.income" />
												</html:submit>
											</td>
											<td width="69" align="center">
												<html:submit property="method" styleClass="buttona"
													onclick="return showIncomeExpense('expense')">
													<bean:message key="button.expense" />
												</html:submit>
											</td>
											<td width="90" align="center">
												<html:submit property="method" styleClass="buttona"
													styleClass="buttonb"
													onclick="return onCreatecredenceAll(this);">
													<bean:message key="button.createcredence.all" />
												</html:submit>
											</td>
											<td width="69" align="center">
												<html:submit property="method" styleClass="buttona"
													onclick="return popWindow(this);">
													<bean:message key="button.createcredence" />
												</html:submit>
											</td>
										</logic:notEmpty>
										<logic:empty name="credenceFillinTbAF" property="list">
											<td width="69" align="center">
												<html:submit property="method" styleClass="buttona"
													styleClass="buttona" disabled="true">
													<bean:message key="button.income" />
												</html:submit>
											</td>
											<td width="69" align="center">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.expense" />
												</html:submit>
											</td>
											<td width="90" align="center">
												<html:submit property="method" styleClass="buttona"
													styleClass="buttonb" disabled="true">
													<bean:message key="button.createcredence.all" />
												</html:submit>
											</td>
											<td width="69" align="center">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.createcredence" />
												</html:submit>
											</td>
										</logic:empty>
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
