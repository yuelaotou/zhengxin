<%@ page contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.issuenotice.action.IssuenoticeTdShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			IssuenoticeTdShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	List selectArray = (List) request.getAttribute("selectedArray");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>贷款申请</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js"></script>
<script>
var s1="";

function reportErrors() {
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="删除"){
				s1=i;
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
function ondelete(){
var x= document.getElementsByName("settNum");
var y=true;
for(var i=0;i<x.length;i++){
		if(x[i].checked){
		y=false;
		}
	}
	if(y){
	alert(' 至少选择一条记录！');
	return false;
	}else{
	return true;
	}
}
function onprint(){
	var x= document.getElementsByName("settNum");
	var y=true;
	for(var i=0;i<x.length;i++){
		if(x[i].checked){
			y=false;
		}
	}
	if(y){
	alert(' 至少选择一条记录！');
	return false;
	}else{
	return true;
	}
}
var tr=0;
function gettr(tr){
var a=document.getElementById(tr).childNodes[7].innerHTML;	
	var b="下达发放通知书";
	if(b==a.trim()){
	document.all.item(s1).disabled=true;
	}else{
	document.all.item(s1).disabled=false;
	}
}
var old_temp="tr0";
function gotoClick123(id1,id2,form){
	var temp1;
	var temp2;
	var temp3;
	eval("temp1="+id1);
	eval("temp3="+old_temp);
	eval("temp2=form."+id2);	
	temp3.style.background="#ffffff";
	temp1.style.background="#EEFBFF";
	old_temp=id1;
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();">
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

									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										财务划款打印
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
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">财务划款打印</font>
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="201">
										<b class="font14">查 询 条 件</b>
									</td>
									<td width="739" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/issuenoticeTdFindAC.do" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="11%" class="td1">
								合同编号
							</td>
							<td width="21%">
								<html:text name="issuenoticeTbAF" property="contractId"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="13%" class="td1">
								借款人姓名
							</td>
							<td width="18%">
								<html:text name="issuenoticeTbAF" property="borrowerName"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="11%" class="td1">
								证件号码
							</td>
							<td width="21%">
								<html:text name="issuenoticeTbAF" property="cardNum"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="11%" class="td1">
								放款银行
							</td>
							<td width="21%">
								<html:select property="loanBankId" styleClass="input4"
									name="issuenoticeTbAF" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="loanbankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="11%" class="td1">
								是否已经打印
							</td>
							<td width="21%" class=td7>
								<html:select property="isPrint" styleClass="input4"
									name="issuenoticeTbAF" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<option value="0">
										否
									</option>
									<option value="1">
										是
									</option>
								</html:select>
							</td>
							<td width="11%" class="td1">

							</td>
							<td width="21%" colspan="2" class=td7>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit styleClass="buttona">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/issuenoticeTdMaintainAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="200">
											<b class="font14">划款列表</b>
										</td>
										<td width="740" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：借款金额
								<u>：<bean:write name="issuenoticeTbAF"
										property="loanMoneySum" /> </u>
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
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td class="td2">
								<a href="javascript:sort('contractid')">合同编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="contractid">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('borrowername')">姓名 </a>
								<logic:equal name="pagination" property="orderBy"
									value="borrowername">
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
								银行
							</td>
							<td class="td2">
								<a href="javascript:sort('loanmoney')">贷款金额 </a>
								<logic:equal name="pagination" property="orderBy"
									value="loanmoney">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								<a href="javascript:sort('loantimelimit')">年限（月） </a>
								<logic:equal name="pagination" property="orderBy"
									value="loantimelimit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td class="td2">
								房屋地址
							</td>
							<td class="td2">
								合同状态
							</td>
							<td class="td2">
								是否已打印
							</td>
						</tr>
						<logic:notEmpty name="issuenoticeTbAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="issuenoticeTbAF" property="list"
								indexId="i">
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
										<input id="settNum" type="checkbox" name="settNum"
											onclick="saveId(this);"
											value="<bean:write name="e" property="contractId" />">
									</td>
									<td>
										<bean:write name="e" property="contractId" />
									</td>
									<td>
										<bean:write name="e" property="borrowerName" />
									</td>
									<td>
										<bean:write name="e" property="cardNum" />
									</td>
									<td>
										<bean:write name="e" property="loanBankId" />
									</td>
									<td>
										<bean:write name="e" property="loanMoney" />
									</td>
									<td>
										<bean:write name="e" property="loanTimeLimit" />
									</td>
									<td>
										<bean:write name="e" property="houseAddr" />
									</td>
									<td>
										<bean:write name="e" property="contractSt" />
									</td>
									<td>
										<bean:write name="e" property="isPrint" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="issuenoticeTbAF" property="list">
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
							<td align="center">

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
															<jsp:param name="url" value="issuenoticeTdShowAC.do" />
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
										<logic:empty name="issuenoticeTbAF" property="list">
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													onclick="return ondelete();" disabled="true">
													<bean:message key="button.revert" />
												</html:submit>
											</td>
										</logic:empty>
										<logic:notEmpty name="issuenoticeTbAF" property="list">
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													onclick="return ondelete();">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													onclick="return ondelete();">
													<bean:message key="button.revert" />
												</html:submit>
											</td>
										</logic:notEmpty>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:hidden name="issuenoticeTbAF" property="type" />
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>

