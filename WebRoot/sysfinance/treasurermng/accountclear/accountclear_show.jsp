<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.treasurermng.accountclear.action.AccountClearShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			AccountClearShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>出纳扎账</title>
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
function tosubject(){
	gotoSubjectpop('0','<%=path%>','4');
}
var s1="";
var s2="";
function onload(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="扎账"){
				s1=i;
			}
			if(document.all.item(i).value=="全部扎账"){
				s2=i;
			}
		}
	}
}
var tr="tr0"; 
function update() {
	if(document.getElementById(tr)!=null){
		var status=document.getElementById(tr).childNodes[12].innerHTML;
		if(status.trim()=='确认'){
			document.all.item(s1).disabled=false;
			document.all.item(s2).disabled=false;
		}else{
			document.all.item(s1).disabled=true;
			document.all.item(s2).disabled=true;
		}
	}
}
function onsearch(){
	var credenceDateSt=document.forms[0].elements["cashDayClearTcFindDTO.credenceDateSt"].value.trim();
	var credenceDateEnd=document.forms[0].elements["cashDayClearTcFindDTO.credenceDateEnd"].value.trim();
	var moneySt=document.forms[0].elements["cashDayClearTcFindDTO.moneySt"].value.trim();
	var moneyEnd=document.forms[0].elements["cashDayClearTcFindDTO.moneyEnd"].value.trim();
	if(credenceDateSt!=""){
		if(!checkDate(document.forms[0].elements["cashDayClearTcFindDTO.credenceDateSt"])){
			document.forms[0].elements["cashDayClearTcFindDTO.credenceDateSt"].value="";
			return false;
		}
	}
	if(credenceDateEnd!=""){
		if(!checkDate(document.forms[0].elements["cashDayClearTcFindDTO.credenceDateEnd"])){
			document.forms[0].elements["cashDayClearTcFindDTO.credenceDateEnd"].value="";
			return false;
		}
	}
	if(moneySt!=""){
		if(!checkMoney(moneySt)){
			document.forms[0].elements["cashDayClearTcFindDTO.moneySt"].value="";
			return false;
		}
	}
	if(moneyEnd!=""){
		if(!checkMoney(moneyEnd)){
			document.forms[0].elements["cashDayClearTcFindDTO.moneyEnd"].value="";
			return false;
		}
	}
	return true;
}
function onaccount(){
  var x;
  var arr= document.getElementsByName("rowArray");
  var temp=0;
  for(var i=0;i<arr.length;i++){
	if(arr[i].checked==true){
		temp=temp+1;
	}
  }
  if(temp==0){
	alert("请选择扎账的记录！");
	return false;
  }else{
	  x=confirm("是否扎账！");
	  if(x){
	  	return true;
	  }else
	  {	
	    return false;
	  }
   }
}
function onaccountall(){
  var x;
  x=confirm("是否全部扎账！");
  if(x){
  	return true;
  }else
  {	
    return false;
  }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="reportErrors();onload();update();">

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

						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<font color="00B5DB">出纳管理&gt;出纳扎账</font>
										</p>
									</td>
									<td width="74" class=font14>
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
				<html:form action="/accountClearFindAC" styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="148">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="772">
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
								日期
								<br>
							</td>
							<td width="15%">
								<html:text name="cashDayClearTcAF"
									property="cashDayClearTcFindDTO.credenceDateSt"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="cashDayClearTcAF"
									property="cashDayClearTcFindDTO.credenceDateEnd"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1">
								凭证号
							</td>
							<td width="15%">
								<html:text name="cashDayClearTcAF"
									property="cashDayClearTcFindDTO.credenceNumSt"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="cashDayClearTcAF"
									property="cashDayClearTcFindDTO.credenceNumEnd"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								摘要
							</td>
							<td width="35%" colspan="3">
								<html:select property="cashDayClearTcFindDTO.summary"
									styleClass="input4" name="cashDayClearTcAF"
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
								<html:text name="cashDayClearTcAF"
									property="cashDayClearTcFindDTO.subjectCode"
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
								<html:text name="cashDayClearTcAF"
									property="cashDayClearTcFindDTO.subjectName"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="15%" class="td1">
								金额
								<br>
							</td>
							<td width="15%">
								<html:text name="cashDayClearTcAF"
									property="cashDayClearTcFindDTO.moneySt" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="cashDayClearTcAF"
									property="cashDayClearTcFindDTO.moneyEnd" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="15" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								状态
							</td>
							<td width="35%" colspan="3">
								<html:select style="input4"
									property="cashDayClearTcFindDTO.credenceSt" styleClass="input4"
									name="cashDayClearTcAF">
									<option value=""></option>
									<html:optionsCollection
										property="cashDayClearTcFindDTO.credenceStMap"
										name="cashDayClearTcAF" label="value" value="key" />
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
									onclick="return onsearch()">
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
							<u>：<bean:write name="cashDayClearTcAF"
									property="cashDayClearTcFindDTO.debitSum" format="0.00" /> </u>；贷方金额
							<u>：<bean:write name="cashDayClearTcAF"
									property="cashDayClearTcFindDTO.creditSum" format="0.00" /> </u>
						</td>
					</tr>
				</table>
				<html:form action="/accountClearMaintainAC.do"
					styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="157">
											<b class="font14">扎 账 列 表</b>
										</td>
										<td width="701" height="22" align="center"
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
								<a href="javascript:sort('fn210.credence_date')">日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn210.credence_date">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('fn210.credence_character')">凭证字号</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn210.credence_character">
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
								<a href="javascript:sort('fn210.subject_code')">科目</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn210.subject_code">
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
								<a href="javascript:sort('fn210.debit')">借方金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn210.debit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('fn210.credit')">贷方金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="fn210.credit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								结算方式
							</td>
							<td class="td2">
								结算号
							</td>
							<td class="td2">
								经手人
							</td>
							<td class="td2">
								制单人
							</td>
							<td class="td2">
								状态
							</td>
						</tr>
						<logic:notEmpty name="cashDayClearTcAF" property="list">
								<%
								int j=0;
								String strClass="";
							 	%>
							<logic:iterate id="e" name="cashDayClearTcAF" property="list"
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
									<logic:equal name="e" property="acredenceId" value="">
										<td>
											<bean:write name="e" property="credenceChaNum" />
										</td>
									</logic:equal>
									<logic:notEqual name="e" property="acredenceId" value="">
										<td>
											<a href="#"
											onClick="window.open('credencePopShowAC.do?docNum=<bean:write name="e" property="temp_credenceChaNum"/>&credenceDate=<bean:write name="e" property="credenceDate"/>&office=<bean:write name="e" property="office"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return false;"><bean:write
												name="e" property="credenceChaNum" />
											</a>
										</td>
									</logic:notEqual>
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
										<bean:write name="e" property="temp_settType" />
									</td>
									<td>
										<bean:write name="e" property="settNum" />
									</td>
									<td>
										<bean:write name="e" property="dopsn" />
									</td>
									<td>
										<bean:write name="e" property="makebill" />
									</td>
									<td>
										<bean:write name="e" property="credenceSt" />
									</td>
								</tr>
								<tr>
									<td colspan="18" class=td5></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="cashDayClearTcAF" property="list">
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
												<jsp:param name="url" value="accountClearShowAC.do" />
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
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
									<logic:notEmpty name="cashDayClearTcAF" property="list">
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona" onclick="return onaccount();">
												<bean:message key="button.in.account" />
											</html:submit>
										</td>
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona" onclick="return onaccountall();">
												<bean:message key="button.in.accountall" />
											</html:submit>
										</td>
										</logic:notEmpty>
										<logic:empty name="cashDayClearTcAF" property="list">
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona" disabled="true">
												<bean:message key="button.in.account" />
											</html:submit>
										</td>
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona" disabled="true">
												<bean:message key="button.in.accountall" />
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
