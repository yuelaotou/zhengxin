<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.treasurermng.cashdayclear.action.CashDayClearTbShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			CashDayClearTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String type = (String) request.getAttribute("type");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>现金日记账</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
var flag=false;
function reportErrors(message) {
	if(message!=null){
		alert(message);
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var x= document.getElementsByName("rowArray");
	for(var i=0;i<x.length;i++){
		x[i].checked=flag;
	}
	flag=(!flag);
	return false;
}
function onsearch(){
	var credenceDateSt=document.forms[0].elements["cashDayClearTbFindDTO.credenceDateSt"].value.trim();
	var credenceDateEnd=document.forms[0].elements["cashDayClearTbFindDTO.credenceDateEnd"].value.trim();
	var settDateSt=document.forms[0].elements["cashDayClearTbFindDTO.settDateSt"].value.trim();
	var settDateEnd=document.forms[0].elements["cashDayClearTbFindDTO.settDateEnd"].value.trim();
	var moneySt=document.forms[0].elements["cashDayClearTbFindDTO.moneySt"].value.trim();
	var moneyEnd=document.forms[0].elements["cashDayClearTbFindDTO.moneyEnd"].value.trim();
	if(credenceDateSt!=""){
		if(!checkDate(document.forms[0].elements["cashDayClearTbFindDTO.credenceDateSt"])){
			document.forms[0].elements["cashDayClearTbFindDTO.credenceDateSt"].value="";
			return false;
		}
	}
	if(credenceDateEnd!=""){
		if(!checkDate(document.forms[0].elements["cashDayClearTbFindDTO.credenceDateEnd"])){
			document.forms[0].elements["cashDayClearTbFindDTO.credenceDateEnd"].value="";
			return false;
		}
	}
	if(settDateSt!=""){
		if(!checkDate(document.forms[0].elements["cashDayClearTbFindDTO.settDateSt"])){
			document.forms[0].elements["cashDayClearTbFindDTO.settDateSt"].value="";
			return false;
		}
	}
	if(settDateEnd!=""){
		if(!checkDate(document.forms[0].elements["cashDayClearTbFindDTO.settDateEnd"])){
			document.forms[0].elements["cashDayClearTbFindDTO.settDateEnd"].value="";
			return false;
		}
	}
	if(moneySt!=""){
		if(!checkMoney(moneySt)){
			document.forms[0].elements["cashDayClearTbFindDTO.moneySt"].value="";
			return false;
		}
	}
	if(moneyEnd!=""){
		if(!checkMoney(moneyEnd)){
			document.forms[0].elements["cashDayClearTbFindDTO.moneyEnd"].value="";
			return false;
		}
	}
	return true;
}
function ontransfers(){
  var x;
  var arr= document.getElementsByName("rowArray");
  var temp=0;
  for(var i=0;i<arr.length;i++){
	if(arr[i].checked==true){
		temp=temp+1;
	}
  }
  if(temp==0){
	alert("请选择转账的记录！");
	return false;
  }else{
	  x=confirm("是否转账！");
	  if(x){
	  	return true;
	  }else
	  {	
	    return false;
	  }
   }
}
function ontransfersall(){
  var x;
  x=confirm("是否全部转账！");
  if(x){
  	return true;
  }else
  {	
    return false;
  }
}
function tosubject(){
	gotoSubjectpop('0','<%=path%>','5');
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="return reportErrors();">

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
						<td background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<%
									if (type.equals("0")) {
									%>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="cashDayClearTaForwardAC.do" class=a2>现金日记账</a>
									</td>
									<%
									}
									%>
									<%
									if (type.equals("1")) {
									%>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="bankDayClearTaForwardAC.do" class=a2>银行存款日记账</a>
									</td>
									<%
									}
									%>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										自动转账
									</td>
									<%
									if (type.equals("0")) {
									%>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="cashDayClearTcForwardAC.do" class=a2>现金日记账维护</a>
									</td>
									<%
									}
									%>
									<%
									if (type.equals("1")) {
									%>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="bankDayClearTcForwardAC.do" class=a2>银行存款日记账维护</a>
									</td>
									<%
									}
									%>
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
									<%
									if (type.equals("0")) {
									%>
									<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">出纳管理&gt;现金日记账</font>
									</td>
									<%
									}
									%>
									<%
									if (type.equals("1")) {
									%>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<font color="00B5DB">出纳管理&gt;银行存款日记账</font>
										</p>
									</td>
									<%
									}
									%>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="127">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="777">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/cashDayClearTbFindAC.do" styleClass="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="15%" class="td1">
								日期
								<br>
							</td>
							<td width="15%">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.credenceDateSt"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.credenceDateEnd"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1">
								结算日期
							</td>
							<td width="15%">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.settDateSt" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.settDateEnd"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								摘要
							</td>
							<td width="35%" colspan="3">
								<html:select property="cashDayClearTbFindDTO.summary"
									styleClass="input4" name="cashDayClearTbAF"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="summrayList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="15%" class="td1">
								科目
							</td>
							<td width="20%" colspan="2">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.subjectCode"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%">
								<input type="button" name="Submit12" value="..." class="buttona"
									onclick="tosubject();">
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								科目名称
							</td>
							<td width="35%" colspan="3">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.subjectName"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1">
								金额
								<br>
							</td>
							<td width="15%">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.moneySt" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.moneyEnd" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								凭证号
							</td>
							<td width="15%">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.credenceNumSt"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.credenceNumEnd"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1">
								凭证字
							</td>
							<td width="35%" colspan="3">
								<html:select property="cashDayClearTbFindDTO.credenceCharacter"
									styleClass="input4" name="cashDayClearTbAF"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="credenceCharacterList1"
										property="value" labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								结算方式
							</td>
							<td width="35%" colspan="3">
								<html:select property="cashDayClearTbFindDTO.settType"
									styleClass="input4" name="cashDayClearTbAF"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="settTypeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="15%" class="td1">
								结算号
							</td>
							<td width="35%" colspan="3">
								<html:text name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.settNum" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								办事处
							</td>
							<td width="35%" colspan="3">
								<html:select property="cashDayClearTbFindDTO.office"
									styleClass="input4" name="cashDayClearTbAF"
									onchange="findCredenceDate();" onkeydown="enterNextFocus1();">
									<option value="">
										全部
									</option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="15%" class="td1">
							</td>
							<td width="35%" colspan="3" class=td7>
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return onsearch();">
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
							合计： 借方金额
							<u>：<bean:write name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.debitSum" format="0.00" /> </u>；贷方金额
							<u>：<bean:write name="cashDayClearTbAF"
									property="cashDayClearTbFindDTO.creditSum" format="0.00" /> </u>
						</td>
					</tr>
				</table>
				<html:form action="/cashDayClearTbMaintainAC.do"
					styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="124">
											<b class="font14">凭 证 列 表</b>
										</td>
										<td width="734" height="22" align="center"
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
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3"
						align="center">
						<tr align="center">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.credence_date')">日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.credence_date">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.credence_character')">凭证字号</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.credence_character">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								摘要
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.subject_code')">科目</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.subject_code">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								科目名称
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.debit')">借方金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.debit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.credit')">贷方金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.credit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.sett_num')">结算号</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.sett_num">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('fn201.sett_date')">结算日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn201.sett_date">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
						</tr>
						<logic:notEmpty name="cashDayClearTbAF" property="list">
							<%
								int j=0;
								String strClass="";
							 %>
							<logic:iterate id="e" name="cashDayClearTbAF" property="list"
								indexId="i">
								<%	j++;
								if (j%2==0) {strClass = "td8";
								}
							    else {strClass = "td9";
							    }%>
								<tr id="tr<%=i%>" class="<%=strClass %>">
									<td>
										<html:multibox property="rowArray">
											<bean:write name="e" property="credenceId" />
										</html:multibox>
									</td>
									<td>
										<bean:write name="e" property="credenceDate" />
									</td>
									<td>
										<a href="#"
											onClick="window.open('credencePopShowAC.do?docNum=<bean:write name="e" property="temp_credenceChaNum"/>&credenceDate=<bean:write name="e" property="credenceDate"/>&office=<bean:write name="e" property="office"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return flase;"><bean:write
												name="e" property="credenceChaNum" />
										</a>
									</td>
									<td>
										<bean:write name="e" property="temp_summary" />
									</td>
									<td>
										<bean:write name="e" property="subjectCode" />
									</td>
									<td>
										<bean:write name="e" property="subjectName" />
									</td>
									<td>
										<bean:write name="e" property="debit" />
									</td>
									<td>
										<bean:write name="e" property="credit" />
									</td>
									<td>
										<bean:write name="e" property="settNum" />
									</td>
									<td>
										<bean:write name="e" property="settDate" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="cashDayClearTbAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
									<br>
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
											<br>
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="cashDayClearTbShowAC.do" />
											</jsp:include>
											<br>
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
								<logic:notEmpty name="cashDayClearTbAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													onclick="return ontransfers();">
													<bean:message key="button.transfers" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													onclick="return ontransfersall();">
													<bean:message key="button.transfers.all" />
												</html:submit>
											</td>
										</tr>
									</table>
								</logic:notEmpty>
								<logic:empty name="cashDayClearTbAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.transfers" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.transfers.all" />
												</html:submit>
											</td>
										</tr>
									</table>
								</logic:empty>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
