<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.sysloan.loanapply.preloanrefr.action.*"%>

<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			PreLoanRefrShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>查询条件，贷前咨询信息列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">

</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script language="javascript">
var s1="";

function loads(){
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		}
	}
  	//初始状态按钮全部禁用
  	if(count==0){
		document.all.item(s1).disabled="true";
	}
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function checkMoney(){
	var money = document.preLoanRefrShowAF.elements["loanMoney"].value.trim();
	if(money!=null && money!=""){
		if(!isNumberDot(money)){
			alert('贷款金额必须输入数字！');
			document.preLoanRefrShowAF.elements["loanMoney"].value="";
			document.preLoanRefrShowAF.elements["loanMoney"].focus();
			return false;
		}
	} else {
		alert('贷款金额不能为空！');
		document.preLoanRefrShowAF.elements["loanMoney"].focus();
		return false;
	}
}

function isNumberDot(String){
    var Letters = "1234567890.";
    var i;
    var c;
    var d=0;
    if(String.charAt( 0 )=='.')
 		return false;
    if( String.charAt( String.length - 1 ) == '-' )
    	return false;
    for( i = 0; i < String.length; i ++ ){
    	if(String.charAt( i )=='.'){
    		d++;
    	}
    	if(d>1){
    	return false;
    	}
	    c = String.charAt( i );
	   	if (Letters.indexOf( c ) < 0)
	    	return false;
	}
    return true;	
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads(); reportErrors();"
	onContextmenu="return false">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="3%" align="right" valign="middle">
				<table width="21" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
					<tr>
						<td height="112" align="center"></td>
					</tr>
				</table>
			</td>
			<td width="97%" align="left" valign="top">
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif" width="7" height="37">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="10">
							&nbsp;
						</td>
						<td width="695" background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37" align="center" valign="top"
										style="PADDING-top: 7px"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
								</tr>
							</table>

						</td>
						<td width="255">
							<table width="255" border="0" cellspacing="0" cellpadding="0"
								align="right">
								<tr>
									<td width="56">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24" align="right">
									</td>
									<td width="169" class=font14 bgcolor="#FFFFFF" align="center"
										valign="middle">
										<font color="00B5DB">申请贷款&gt;贷前咨询</font>
									</td>
									<td width="30">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="18">
							<img src="<%=path%>/img/table_bg_line.gif" width="18" height="37">
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
				<table width="98%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top" class=td3>
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="207">
													<b class="font14">查 询 条 件</b>
												</td>
												<td width="716" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<html:form action="/preLoanRefrFindAC.do" method="post"
								style="margin: 0">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=0 align="center">
									<tr>
										<td width="20%" class="td1">
											贷款金额（万元）
										</td>
										<td width="30%">
											<html:text name="preLoanRefrShowAF" property="loanMoney"
												styleClass="input3" onkeydown="enterNextFocus1();" />
										</td>
										<td width="20%" class="td1">
											&nbsp;
										</td>
										<td width="30%" class="td7">
											&nbsp;
										</td>
									</tr>
								</table>
								<table width="95%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkMoney()">
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
												<td height="22" bgcolor="#CCCCCC" align="center" width="216">
													<b class="font14">贷前咨询列表 </b>
												</td>
												<td height="22" background="<%=path%>/img/bg2.gif"
													align="center" width="688">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							<html:form action="/preLoanRefrMaintainAC.do" method="post">
								<table width="95%" border="0" cellspacing="1" cellpadding="3"
									align="center" bgcolor="1BA5FF">
									<tr align="center">
										<td align="center" height="23" class=td1>
											&nbsp;
										</td>
										<td align="center" class=td1 width="20%">
											贷款期限（年）
										</td>
										<td align="center" class=td1>
											月还款额
										</td>
										<td align="center" class=td1>
											还款总额
										</td>
										<td align="center" class=td1>
											利息总额
										</td>
										<td align="center" class=td1>
											年利率
										</td>
									</tr>
									<logic:notEmpty name="preLoanRefrShowAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="preLoanRefrShowAF" property="list"
											id="element" indexId="i">
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
													&nbsp;
												</td>
												<td>
													<bean:write name="element" property="yearlimit" />
												</td>
												<td>
													<bean:write name="element" property="corpusInterest" />
												</td>
												<td>
													<bean:write name="element" property="loanmoneyTotal" />
												</td>
												<td>
													<bean:write name="element" property="interestTotal" />
												</td>
												<td>
													<bean:write name="element" property="rate" />
												</td>
											</tr>

										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="preLoanRefrShowAF" property="list">
										<tr>
											<td class="td1" colspan="6" height="30" style="color:red">
												没有找到与条件相符合的结果！
											</td>
										</tr>

									</logic:empty>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr class="td1">
										<td align="center">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr align="center">
													<td align="left">
														试算方式为等额本息还款方式
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
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</html:form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>

