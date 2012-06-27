<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.assuremode.action.*"%>
<%@ page import="org.xpup.common.util.Pagination"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			AssureModeShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String mode = (String) ((Pagination) pagination)
			.getQueryCriterions().get("mode");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>查询列表，担保方式统计列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script>

function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var mode = <%=mode%>;
	if(mode==1) {
		mode1();
	} else if(mode==2) {
		mode2();
	} else if(mode==3) {
		mode3();
	} else if(mode==4) {
		mode4();
	} else if(mode==5) {
		mode5();
	} 
}

function gotoClear() {
	if(confirm("是否打印列表信息?")){
		return true;
	} else {
		return false;
	}
}
	
function findorghouse() {
  window.open("<%=path%>/sysloan/develepershowAC.do?indexs="+0+"&objInput=developerName"+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=no, status=no"); 
}

function findFloor() {
  window.open("<%=path%>/sysloan/floorPOPShowAC.do?indexs="+8+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=no, status=no"); 
}

function mode1(){
	document.assureModeAF.elements["developerName"].value="";
	document.assureModeAF.elements["buyhouseorgid"].value="";
	document.assureModeAF.elements["floorNum"].value="";
	document.assureModeAF.elements["floorid"].value="";
	//document.assureModeAF.elements["office"].value="all";
	document.assureModeAF.elements["loanBankName"].value="all";
	document.assureModeAF.elements["assistantOrgName"].value="all";
	document.assureModeAF.elements["office"].disabled="";
	document.assureModeAF.elements["loanBankName"].disabled="true";
	document.assureModeAF.elements["button1"].disabled="true";
	document.assureModeAF.elements["button2"].disabled="true";
	document.assureModeAF.elements["assistantOrgName"].disabled="true";
	//document.assureModeAF.elements["developerName"].disabled="true";
	//document.assureModeAF.elements["floorNum"].disabled="true";
}

function mode2(){
	document.assureModeAF.elements["developerName"].value="";
	document.assureModeAF.elements["buyhouseorgid"].value="";
	document.assureModeAF.elements["floorNum"].value="";
	document.assureModeAF.elements["floorid"].value="";
	document.assureModeAF.elements["office"].value="all";
	//document.assureModeAF.elements["loanBankName"].value="all";
	document.assureModeAF.elements["assistantOrgName"].value="all";
	document.assureModeAF.elements["office"].disabled="true";
	document.assureModeAF.elements["loanBankName"].disabled="";
	document.assureModeAF.elements["button1"].disabled="true";
	document.assureModeAF.elements["button2"].disabled="true";
	document.assureModeAF.elements["assistantOrgName"].disabled="true";
	//document.assureModeAF.elements["developerName"].disabled="true";
	//document.assureModeAF.elements["floorNum"].disabled="true";
}

function mode3(){
	//document.assureModeAF.elements["developerName"].value="";
	//document.assureModeAF.elements["buyhouseorgid"].value="";
	document.assureModeAF.elements["floorNum"].value="";
	document.assureModeAF.elements["floorid"].value="";
	document.assureModeAF.elements["office"].value="all";
	document.assureModeAF.elements["loanBankName"].value="all";
	document.assureModeAF.elements["assistantOrgName"].value="all";
	document.assureModeAF.elements["office"].disabled="true";
	document.assureModeAF.elements["loanBankName"].disabled="true";
	//document.assureModeAF.elements["developerName"].disabled="";
	document.assureModeAF.elements["button1"].disabled="";
	document.assureModeAF.elements["button2"].disabled="true";
	document.assureModeAF.elements["assistantOrgName"].disabled="true";
	//document.assureModeAF.elements["floorNum"].disabled="true";
}

function mode4(){
	document.assureModeAF.elements["developerName"].value="";
	document.assureModeAF.elements["buyhouseorgid"].value="";
	document.assureModeAF.elements["office"].value="all";
	document.assureModeAF.elements["loanBankName"].value="all";
	document.assureModeAF.elements["assistantOrgName"].value="all";
	document.assureModeAF.elements["office"].disabled="true";
	document.assureModeAF.elements["loanBankName"].disabled="true";
	document.assureModeAF.elements["button1"].disabled="true";
	//document.assureModeAF.elements["floorNum"].disabled="";
	document.assureModeAF.elements["button2"].disabled="";
	document.assureModeAF.elements["assistantOrgName"].disabled="true";
}

function mode5(){
	document.assureModeAF.elements["developerName"].value="";
	document.assureModeAF.elements["buyhouseorgid"].value="";
	document.assureModeAF.elements["floorNum"].value="";
	document.assureModeAF.elements["floorid"].value="";
	document.assureModeAF.elements["office"].value="all";
	document.assureModeAF.elements["loanBankName"].value="all";
	//document.assureModeAF.elements["assistantOrgName"].value="all";
	document.assureModeAF.elements["office"].disabled="true";
	document.assureModeAF.elements["loanBankName"].disabled="true";
	document.assureModeAF.elements["button1"].disabled="true";
	document.assureModeAF.elements["button2"].disabled="true";
	document.assureModeAF.elements["assistantOrgName"].disabled="";
	//document.assureModeAF.elements["developerName"].disabled="true";
	//document.assureModeAF.elements["floorNum"].disabled="true";
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return true">
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
							&nbsp;
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
										<font color="00B5DB">统计查询&gt;担保方式查询统计</font>
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="246">
										<b class="font14">查询条件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="658">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/assureModeFindAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td class="td1" colspan="8">
								<html:radio name="assureModeAF" property="mode" value="1"
									onclick="mode1();">办事处</html:radio>
								<html:radio name="assureModeAF" property="mode" value="2"
									onclick="mode2();">放款银行</html:radio>
								<html:radio name="assureModeAF" property="mode" value="3"
									onclick="mode3();">开发商</html:radio>
								<html:radio name="assureModeAF" property="mode" value="4"
									onclick="mode4();">楼盘</html:radio>
								<html:radio name="assureModeAF" property="mode" value="5"
									onclick="mode5();">担保公司</html:radio>
							</td>
						</tr>
						<tr>
							<td width="16%" class="td1">
								办事处
							</td>
							<td colspan="2">
								<logic:notEmpty name="officeList">
									<html:select name="assureModeAF" property="office"
										styleClass="input4">
										<html:option value="all">全部</html:option>
										<html:options collection="officeList" property="value"
											labelProperty="label" />
									</html:select>
								</logic:notEmpty>
							</td>
							<td width="16%" class="td1">
								放款银行
							</td>
							<td colspan="2">
								<logic:notEmpty name="loanBankNameList">
									<html:select name="assureModeAF" property="loanBankName"
										styleClass="input3">
										<html:option value="all">全部</html:option>
										<html:options collection="loanBankNameList" property="value"
											labelProperty="label" />
									</html:select>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td width="16%" class="td1">
								开发商
							</td>
							<td width="23%">
								<html:text name="assureModeAF" property="developerName"
									styleClass="input3" readonly="true"/>
							</td>
							<html:hidden name="assureModeAF" property="buyhouseorgid" />
							<td width="9%">
								<input type="button" class="buttona" value="..." name="button1"
									onclick="findorghouse();">
							</td>
							<td width="16%" class="td1">
								楼盘
							</td>
							<td width="25%">
								<html:text name="assureModeAF" property="floorNum"
									styleClass="input3" readonly="true"/>
							</td>
							<html:hidden name="assureModeAF" property="floorid" />
							<td width="15%">
								<input type="button" class="buttona" value="..." name="button2"
									onclick="findFloor();">
							</td>
						</tr>
						<tr>
							<td width="16%" class="td1">
								担保公司
							</td>
							<td colspan="2">
								<logic:notEmpty name="assistantOrgNameList">
									<html:select name="assureModeAF" property="assistantOrgName"
										styleClass="input3">
										<html:option value="all">全部</html:option>
										<html:options collection="assistantOrgNameList"
											property="value" labelProperty="label" />
									</html:select>
								</logic:notEmpty>
							</td>
							<td width="16%" class="td1">
								&nbsp;
							</td>
							<td colspan="2" align="center">
								<input type="text" class="input3" readonly="true">
							</td>
						</tr>
						<tr>
							<td align="right" colspan="6">
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
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="202">
										<b class="font14">担保方式统计列表</b>
									</td>
									<td width="703" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/assureModeMaintainAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>

					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
							<td align="center" class=td2 rowspan="2">
								办事处
								<br>
							</td>
							<td align="center" class=td2 rowspan="2">
								放款银行
							</td>
							<td align="center" class=td2 rowspan="2">
								开发商
							</td>
							<td align="center" class=td2 rowspan="2">
								楼盘
							</td>
							<td align="center" class=td2 rowspan="2">
								担保公司
							</td>
							<td align="center" class=td2 colspan="2">
								抵押
							</td>
							<td align="center" class=td2 colspan="2">
								质押
							</td>
							<td align="center" class=td2 colspan="2">
								抵押+保证
							</td>
							<td align="center" class=td2 colspan="2">
								质押+保证
							</td>
							<td align="center" class=td2 colspan="2">
								合计
							</td>
						</tr>
						<tr>
							<td align="center" class=td2>
								户数
							</td>
							<td align="center" class=td2>
								金额
							</td>
							<td align="center" class=td2>
								户数
							</td>
							<td align="center" class=td2>
								金额
							</td>
							<td align="center" class=td2>
								户数
							</td>
							<td align="center" class=td2>
								金额
							</td>
							<td align="center" class=td2>
								户数
							</td>
							<td align="center" class=td2>
								金额
							</td>
							<td align="center" class=td2>
								户数
							</td>
							<td align="center" class=td2>
								金额
							</td>
						</tr>
						<logic:notEmpty name="assureModeAF" property="list">
						<% int j=0;
			String strClass="";
		%>
							<logic:iterate name="assureModeAF" property="list" id="element"
								indexId="i">
								<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
								<tr id="tr<%=i%>"
									

onclick='gotoClickpp("<%=i %>", idAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"
									onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="assureModeAF" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<bean:write name="element" property="office" />
									</td>
									<td valign="top">
										<bean:write name="element" property="loanBankName" />
									</td>
									<td valign="top">
										<bean:write name="element" property="developerName" />
									</td>
									<td valign="top">
										<bean:write name="element" property="floorNum" />
									</td>
									<td valign="top">
										<bean:write name="element" property="assistantOrgName" />
									</td>
									<td valign="top">
										<bean:write name="element" property="pledgeCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="pledgeValue" />
									</td>
									<td valign="top">
										<bean:write name="element" property="impawnCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="impawnValue" />
									</td>
									<td valign="top">
										<bean:write name="element" property="pledgeAssurerCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="pledgeAssurerValue" />
									</td>
									<td valign="top">
										<bean:write name="element" property="impawnAssurerCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="impawnAssurerValue" />
									</td>
									<td valign="top">
										<bean:write name="element" property="totalCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="totalValue" />
									</td>
								</tr>
								
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="assureModeAF" property="list">
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
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<logic:notEmpty name="assureModeAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<html:submit property="method" styleClass="buttona"
													onclick="">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</tr>
									</table>
								</logic:notEmpty>
								<logic:empty name="assureModeAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</tr>
									</table>
								</logic:empty>
							</td>
						</tr>
					</table>
				</html:form>
	</table>
</body>
</html:html>

