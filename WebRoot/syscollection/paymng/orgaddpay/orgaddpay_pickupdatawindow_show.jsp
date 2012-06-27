<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();

%>

<html:html>
<head>
	<title>缴存管理&gt;&gt;单位补缴</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />	

</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		request.setAttribute("type","0");
%>
<script language="javascript">
</script>
<script type="text/javascript" language="javascript">
function sure(){
    var addpayDateSt = document.all.addpayDateSt.value.trim();
    var addpayDateEnd = document.all.addpayDateEnd.value.trim();
    var noteNumB = document.all.noteNumB.value.trim();
    if(addpayDateSt==""){
		alert("请输入起始时间！");
		document.all.addpayDateSt.focus();
		return false;
	}
	if(addpayDateEnd==""){
		alert("请输入终止时间！");
		document.all.addpayDateEnd.focus();
		return false;
	}
	var str1=checkMonth("addpayDateSt");
	var str=checkMonth("addpayDateEnd");
	if(!str1){
		return false;
	}else if(!str){
		return false;
	}
	if(parseInt(addpayDateEnd)<parseInt(addpayDateSt)){
		alert("终止年月不能小于起始年月！");
		return false;
	}
	window.opener.document.URL="<%=path%>/syscollection/paymng/orgaddpay/orgaddpayTbPickupdataWindowSureAC.do?addpayDateSt="+addpayDateSt+"&addpayDateEnd="+addpayDateEnd+"&noteNumB="+noteNumB;
	window.close();
	return true;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
	<html:form action="orgaddpayTbPickupdataWindowSureAC.do">
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
											background="<%=path%>/img/buttonbl.gif" align="center"
											valign="top" style="PADDING-top: 7px">
											录入补缴年月
										</td>
									</tr>
								</table>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="300" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">缴存管理&gt;单位补缴</font>
										</td>
										<td width="115" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">录入补缴年月</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center">
											&nbsp;

										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								录入补缴年月：
							</td>
							<td width="17%">
								<html:text property="addpayDateSt" styleClass="input3"
									styleId="txtsearch" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%" align="center">
								至
							</td>
							<td width="15%">
								<html:text property="addpayDateEnd" styleClass="input3"
									styleId="txtsearch" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								结算号：
							</td>
							<td width="17%">
								<html:text property="noteNumB" styleClass="input3"
									styleId="txtsearch"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<html:hidden name="orgaddpayTbPickupdataWindowAF" property="noteNum"/>
						<html:hidden name="orgaddpayTbPickupdataWindowAF" property="orgId"/>
						<html:hidden name="orgaddpayTbPickupdataWindowAF" property="orgName"/>
					</table>

			<table width="95%" border="0" cellspacing="0" cellpadding="3"
				align="center">
				<tr valign="bottom">
					<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<html:button property="method" styleClass="buttona"
										onclick="return sure();">
										<bean:message key="button.sure" />
									</html:button>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</table>
	</html:form>
</body>
</html:html>
