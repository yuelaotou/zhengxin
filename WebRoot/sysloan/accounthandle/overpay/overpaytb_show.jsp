<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ page
	import="org.xpup.hafmis.sysloan.accounthandle.overpay.action.OverPayTbShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			OverPayTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>挂账维护</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
function reportErrors(message) {
	if(message!=null){
		alert(message);
	}
	else{
		<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
		</logic:messagesPresent>
	}
}
var s1="";
var s2="";
function onload(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="删除"){
				s1=i;
			}
			if(document.all.item(i).value=="打印"){
				s2=i;
			}
		}
	}
}
var tr="tr0"; 
var contractId;
function gettr(trindex) {
  tr=trindex;
  contractId=document.getElementById(tr).childNodes[3].innerHTML;
  update();
}
function ondelete(){
  var x;
  x=confirm("是否删除该信息！");
  if(x){
  	if(tr=='tr0'){
  		document.all.contractid.value=document.getElementById(tr).childNodes[3].innerHTML;
  		showlist();
  	}
  	else{
  		document.all.contractid.value=contractId;
  		showlist();
  	}
  }else
  {
    return false;
  }
}
function showlist() {
  document.Form1.submit();
}	
function update() {
	if(document.getElementById(tr)!=null){
		var status=document.getElementById(tr).childNodes[7].innerHTML;
		if(status.trim()=='确认'){
			document.all.item(s1).disabled=false;
			document.all.item(s2).disabled=false;
		}else if(status.trim()=='入账'){
			document.all.item(s1).disabled=true;
			document.all.item(s2).disabled=true;
		}else{
			document.all.item(s1).disabled=true;
			document.all.item(s2).disabled=false;
		}
	}
}
function onsearch(){
	var cardNum = document.forms[0].elements["overPayTbFindDTO.cardNum"].value.trim();
	if(cardNum.trim()!=""){
		return isIdCardNo(cardNum);
	}
	return true;
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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="overPayTaShowAC.do" class=a2>办理挂账</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										挂账维护
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
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<a href="#" class=a1>账务处理</a><font color="00B5DB">&gt;</font><a
											href="#" class=a1>挂账</a>
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
				<html:form action="/overPayTbFindAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="182">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="728">
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
								扣款账号
								<br>
							</td>
							<td width="33%">
								<html:text name="overPayTbAF"
									property="overPayTbFindDTO.loankouacc" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%">
								<html:text name="overPayTbAF"
									property="overPayTbFindDTO.contractId" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%" align="center">
								<html:text name="overPayTbAF"
									property="overPayTbFindDTO.borrowerName" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td align="center">
								<html:text name="overPayTbAF"
									property="overPayTbFindDTO.cardNum" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								凭证编号
							</td>
							<td width="33%" align="center">
								<html:text name="overPayTbAF" property="overPayTbFindDTO.docNum"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td align="center">
								<html:select property="overPayTbFindDTO.loanBankId"
									styleClass="input4" name="overPayTbAF"
									onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="loanbankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								业务状态
							</td>
							<td width="33%" align="center">
								<span class="td4"> <html:select style="input4"
										property="overPayTbFindDTO.bizSt" styleClass="input4"
										name="overPayTbAF" onkeydown="enterNextFocus1();">
										<option value=""></option>
										<html:optionsCollection property="overPayTbFindDTO.bizStMap"
											name="overPayTbAF" label="value" value="key" />
									</html:select> </span>
							</td>
							<td width="17%" class="td1">
								&nbsp;
							</td>
							<td align="center">
								<input name="txtsearch452215222" type="text"
									id="txtsearch4522152" class="input3" readonly="true">
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<input type="submit" name="submit12" class=buttona value="查询"
									onclick="return onsearch();" />
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/overPayTbMaintainAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：挂账发生额
								<u>：<bean:write name="overPayTbAF" property="occurMoneySum"
										format="0.00" />
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="185">
											<b class="font14">挂 账 列 表</b>
										</td>
										<td width="725" height="22" align="center"
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
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								扣款账号
							</td>
							<td align="center" class=td2>
								凭证编号
							</td>
							<td align="center" class=td2>
								合同编号
							</td>
							<td align="center" class=td2>
								借款人姓名
							</td>
							<td align="center" class=td2>
								证件号码
							</td>
							<td align="center" class=td2>
								挂账发生额
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
						</tr>
						<input type="hidden" name="contractid" value="">
						<logic:notEmpty name="overPayTbAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="e" name="overPayTbAF" property="list"
								indexId="i">
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
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="e" property="flowHeadId"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="e" property="loankouacc" />
									</td>
									<td>
										<bean:write name="e" property="docNum" />
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
										<bean:write name="e" property="occurMoney" format="0.00" />
									</td>
									<td>
										<bean:write name="e" property="temp_bizSt" />
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="overPayTbAF" property="list">
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
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr align="center">
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="overPayTbShowAC.do" />
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
										<logic:notEmpty name="overPayTbAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return ondelete();">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</logic:notEmpty>
										<logic:empty name="overPayTbAF" property="list">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</logic:empty>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<form action="overPayTbMaintainAC.do" method="POST" name="Form1"
					id="Form1">
				</form>
	</table>
</body>
</html:html>
