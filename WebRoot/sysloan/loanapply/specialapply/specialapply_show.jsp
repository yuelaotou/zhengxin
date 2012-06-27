<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>

<%
String path = request.getContextPath();
%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.specialapply.action.SpecialapplyTbShowAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			SpecialapplyTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>特殊申请维护</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">

	</head>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script type="text/javascript">
var tr = "tr0";
var s = "<bean:write name="pagination" property="nrOfElements" />";

function gettr(trindex) {
  	tr=trindex;
  	update1();
}
function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="删除"){
				s1=i;
			}
			if(document.all.item(i).value=="修改"){
				s2=i;
			}
		}
	}
	document.all.item(s1).disabled="true";
	document.all.item(s2).disabled="true";
	if(s!=0){
		gettr('tr0');
	}
}
function update1() {
	if(s!=0){
		var status = document.getElementById(tr).childNodes[8].childNodes[0].innerHTML.trim();
		if(status=='未启用'){
			document.all.item(s1).disabled="";
			document.all.item(s2).disabled="";
		}else{
			document.all.item(s1).disabled="";
			document.all.item(s2).disabled="true";
		}
	}
}

function checkDelete(){
	var x = confirm("是否删除此记录?");
	if(x){
<%--		var empId = document.getElementById(tr).childNodes[1].childNodes[0].innerHTML.trim();--%>
<%--		var borrowerName= document.getElementById(tr).childNodes[2].childNodes[0].innerHTML.trim();--%>
<%--		var cardNum= document.getElementById(tr).childNodes[3].childNodes[0].innerHTML.trim();--%>
<%--		if(empId==""){--%>
<%--			location.href="specialapplyTbDeleteAC.do?borrowerName="+borrowerName+"&cardNum="+cardNum;--%>
<%--			return false;--%>
<%--		}else{--%>
			return true;
<%--		}--%>
	}
	return false;
}

function isNumber1(String){ 
    var Letters = "1234567890-"; //可以自己增加可输入值
    var i;
    var c;
    if(String.charAt( 0 )=='-')
 		return false;
    if( String.charAt( String.length - 1 ) == '-' )
    	return false;
    for( i = 0; i < String.length; i ++ ){
	    c = String.charAt( i );
	   	if (Letters.indexOf( c ) < 0)
	    	return false;
	}
    return true;
}

function checkNumber(){
	var number = document.forms[0].elements["empId"].value.trim();
	if(isNumber1(number)){
		return true;
	}else{
		alert("职工编号不能有空格或字母!");
		return false;
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>

	<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();loads();"
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
							<td width="235" background="<%=path%>/img/table_bg_line.gif">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											<a href="<%=path%>/sysloan/specialapplyTaShowAC.do" class=a2>&#21150;&#29702;&#30003;&#35831;</a>
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttonbl.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											申请维护
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
											<font color="00B5DB">申请贷款&gt;贷款预审</font>
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
					<html:form action="/specialapplyTbFindAC.do">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="164">
												<b class="font14">查 询 条 件</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="776">
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
									借款人职工编号
								</td>
								<td width="33%">
									<html:text name="specialapplyTbAF" property="empId"
										styleClass="input3" styleId="txtsearch"
										onkeydown="enterNextFocus1();"></html:text>
								</td>
								<td width="17%" class="td1">
									借款人姓名
								</td>
								<td width="33%">
									<html:text name="specialapplyTbAF" property="borrowerName"
										styleClass="input3" styleId="txtsearch"
										onkeydown="enterNextFocus1();"></html:text>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									证件号码
								</td>
								<td width="33%" align="center">
									<html:text name="specialapplyTbAF" property="cardNum"
										styleClass="input3" styleId="txtsearch"
										onkeydown="enterNextFocus1();"></html:text>
								</td>
								<td width="17%" class="td1">
									&nbsp;
								</td>
								<td align="center">
									<input name="txtsearch452215222" type="text"
										id="txtsearch4522152" class="input3" disabled="disabled">
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<logic:notEmpty name="specialapplyTbFind">
										<html:submit property="method" styleClass="buttona"
											onclick="return checkNumber();">
											<bean:message key="button.search" />
										</html:submit>
									</logic:notEmpty>
									<logic:empty name="specialapplyTbFind">
										<html:submit property="method" styleClass="buttona"
											disabled="true">
											<bean:message key="button.search" />
										</html:submit>
									</logic:empty>
								</td>
							</tr>
						</table>
					</html:form>
					<html:form action="/specialapplyTbModifyAC.do">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="163">
												<b class="font14">特殊申请列表</b>
											</td>
											<td width="777" height="22" align="center"
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
									借款人职工编号
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p2.borrower_name')">借款人姓名 </a>
									<logic:equal name="pagination" property="orderBy"
										value="p2.borrower_name">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									证件号码
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p2.loan_money')">贷款金额 </a>
									<logic:equal name="pagination" property="orderBy"
										value="p2.loan_money">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p2.loan_time_limit')">贷款期限（月） </a>
									<logic:equal name="pagination" property="orderBy"
										value="p2.loan_time_limit">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									办理人员
								</td>
								<td align="center" class=td2>
									办理日期
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p2.status')">状态 </a>
									<logic:equal name="pagination" property="orderBy"
										value="p2.status">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									备注
								</td>
							</tr>
							<logic:notEmpty name="specialapplyTbAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="specialapplyTbAF" property="list"
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
													value="<bean:write name="element" property="id"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %> />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="element" property="empId" format="000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="element" property="borrowerName" />
											</p>
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
												<bean:write name="element" property="operator" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="element" property="opTime" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="element" property="status" />
											</p>
										</td>
										<td valign="top">
											<bean:write name="element" property="remark" />
										</td>
									</tr>

								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="specialapplyTbAF" property="list">
								<tr>
									<td colspan="11" height="30" style="color:red">
										没有找到与条件相符合的结果！
									</td>
								</tr>

							</logic:empty>
							<tr>
								<td colspan="9"></td>
							</tr>
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
													<jsp:param name="url" value="specialapplyTbShowAC.do" />
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
											<td>
												<logic:notEmpty name="specialapplyTbAF" property="list">
													<html:submit property="method" styleClass="buttona"
														onclick="return checkDelete();">
														<bean:message key="button.delete" />
													</html:submit>
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.update" />
													</html:submit>
												</logic:notEmpty>
												<logic:empty name="specialapplyTbAF" property="list">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.delete" />
													</html:submit>
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.update" />
													</html:submit>
												</logic:empty>
											</td>
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
</html>
