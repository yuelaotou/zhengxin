<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanaccord.loanaccord.action.LoanaccordTbShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			LoanaccordTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>贷款发放</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	
	
	
	<script language="javascript" src="js/common.js">
</head>
<script language="javascript" ></script>
	<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var tr="tr0"; 
function loads(){
document.all.loanBankIdf.value="";
document.all.bizStf.value="";
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="删除"){
				s1=i;
			}
			if(document.all.item(i).value=="凭证打印"){
				s2=i;
			}
		}
	}
	document.all.item(s1).disabled="true";
	document.all.item(s2).disabled="true";
if(count!=0){
		update();
	}
}
function gettr(trindex) {
  tr=trindex;
  update();
}
function update() {	
  var status=document.getElementById(tr).childNodes[10].childNodes[0].innerHTML.trim();
     if(status=='确认'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
  	}else if(status=='复核'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
  	}else if(status=='入账'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
  	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function checkDelete(){
if(confirm("是否确定删除？")){
     return true;
	}else{
		return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();"
	onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="loanaccordTbShowAC.do" />
	</jsp:include>

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
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="<%=path%>/sysloan/loanaccordTaShowAC.do" class=a2>办理发放</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										发放维护
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
									<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">发放贷款</font><font color="00B5DB">&gt;</font><font
											color="00B5DB">发放贷款</font>
									</td>
									<td width="100" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="176">
										<b class="font14">查 询 条 件</b>
									</td>
									<td width="749" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/loanaccordTbFindAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1" algin="center">
								合同编号
							</td>
							<td width="33%">
								<html:text property="contractIdf" styleClass="input3"
									name="loanaccordShowAF" onkeydown="enterNextFocus1();"
									styleId="txtsearch">
								</html:text>
							</td>
							<td class="td1" width="17%" algin="center">
								借款人姓名
							</td>
							<td width="33%" colspan="3">
								<html:text property="borrowerNamef" styleClass="input3"
									name="loanaccordShowAF" onkeydown="enterNextFocus1();"
									styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								证件号码
							</td>
							<td width="33%">
								<html:text property="cardNumf" styleClass="input3"
									name="loanaccordShowAF" onkeydown="enterNextFocus1();"
									styleId="txtsearch">
								</html:text>
							</td>
							<td class="td1" width="17%" algin="center">
								放款银行
							</td>
							<td width="40%" colspan="3">
								<html:select property="loanBankIdf" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:options collection="banklist" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="td1" width="17%" algin="center">
								业务状态
							</td>
							<td width="33%">
								<html:select property="bizStf" styleClass="input4"
									onkeydown="enterNextFocus1()">
									<html:option value=""></html:option>
									<html:optionsCollection property="statusNewMap"
										name="loanaccordShowAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								发放日期
							</td>
							<td width="15%">
								<html:text name="loanaccordShowAF" property="loanStartDate"
									styleClass="input3" styleId="txtsearch"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="loanaccordShowAF" property="loanEndDate"
									styleClass="input3" styleId="txtsearch"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td colspan="6" align="right">
								<html:submit styleClass="buttona">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" align="center" border="0"
						cellspacing="0" cellpadding="0">
						<tr>
							<td class=h4>
								合计：贷款金额
								<u>：<bean:write name="loanaccordShowAF"
										property="sumloanMoney" />
								</u> 发放笔数
								<u>：<bean:write name="pagination" property="nrOfElements" />
								</u>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="126">
										<b class="font14">发放信息列表</b>
									</td>
									<td width="537" height="22" align="center"
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
				<html:form action="/loanaccordTbMaintainAC.do" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2">
								<a href="javascript:sort('borrower.contractId')">合同编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrower.contractId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('borrower.borrowerName')">借款人姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrower.borrowerName">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								证件号码
							</td>
							<td class="td2">
								<a href="javascript:sort('borrowerLoanInfo.loanMoney')">借款金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrowerLoanInfo.loanMoney">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('borrowerLoanInfo.loanTimeLimit')">借款期限（月）</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrowerLoanInfo.loanTimeLimit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								每月利率
							</td>
							<td class="td2">
								月还本息
							</td>
							<td class="td2">
								扣款账号
							</td>
							<td class="td2">
								<a href="javascript:sort('borrowerAcc.loanStartDate')">发放日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrowerAcc.loanStartDate">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								业务状态
							</td>
						</tr>
						<logic:notEmpty name="loanaccordShowAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>

							<logic:iterate name="loanaccordShowAF" property="list"
								id="element" indexId="i">
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
									class="<%=strClass%>" onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="flowHeadId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="contractId" />
										</p>
									</td>
									<td valign="top">
										<a href="#"
											onClick="window.open('<%=request.getContextPath()%>/sysloan/loanaccordFindAC.do?id=<bean:write name="element" property="flowHeadId"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');">
											<bean:write name="element" property="borrowerName" /> </a>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="cardNum" />

										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanMoney" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanTimeLimit" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="temploanMonthRate" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="corpusInterest" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanKouAcc" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanStartDate" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="bizStName" />
										</p>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="loanaccordShowAF" property="list">
							<tr>
								<td colspan="4" height="30" style="color:red">
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
												<jsp:param name="url" value="loanaccordTbShowAC.do" />
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
										<logic:empty name="loanaccordShowAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return checkDelete()" disabled="true">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print.doc" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttonb"
													disabled="true">
													<bean:message key="button.loanaccord.credence.printall" />
												</html:submit>
											</td>
										</logic:empty>
										<logic:notEmpty name="loanaccordShowAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return checkDelete()">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print.doc" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttonb">
													<bean:message key="button.loanaccord.credence.printall" />
												</html:submit>
											</td>
										</logic:notEmpty>
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
