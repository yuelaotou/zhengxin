<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.fivelevelquery.action.*"%>
<%@ page import="org.xpup.common.util.Pagination"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			FiveLevelQueryShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String mode = (String) ((Pagination) pagination)
			.getQueryCriterions().get("mode");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>查询列表，五级分类查询统计列表</title>
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

function findorghouse() {
  window.open("<%=path%>/sysloan/develepershowAC.do?indexs="+0+"&objInput=developerName"+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=no, status=no"); 
}

function findFloor() {
  window.open("<%=path%>/sysloan/floorPOPShowAC.do?indexs="+8+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=no, status=no"); 
}

function mode1(){
	//document.fiveLevelQueryAF.elements["office"].value="all";
	document.fiveLevelQueryAF.elements["loanBankName"].value="all";
	document.fiveLevelQueryAF.elements["developerName"].value="";
	document.fiveLevelQueryAF.elements["buyhouseorgid"].value="";
	document.fiveLevelQueryAF.elements["floorId"].value="";
	document.fiveLevelQueryAF.elements["floorid"].value="";
	document.fiveLevelQueryAF.elements["assistantOrgName"].value="all";
	document.fiveLevelQueryAF.elements["office"].disabled="";
	document.fiveLevelQueryAF.elements["loanBankName"].disabled="true";
	document.fiveLevelQueryAF.elements["button1"].disabled="true";
	document.fiveLevelQueryAF.elements["button2"].disabled="true";
	document.fiveLevelQueryAF.elements["assistantOrgName"].disabled="true";
	//document.fiveLevelQueryAF.elements["developerName"].disabled="true";
	//document.fiveLevelQueryAF.elements["floorId"].disabled="true";
}

function mode2(){
	document.fiveLevelQueryAF.elements["office"].value="all";
	//document.fiveLevelQueryAF.elements["loanBankName"].value="all";
	document.fiveLevelQueryAF.elements["developerName"].value="";
	document.fiveLevelQueryAF.elements["buyhouseorgid"].value="";
	document.fiveLevelQueryAF.elements["floorId"].value="";
	document.fiveLevelQueryAF.elements["floorid"].value="";
	document.fiveLevelQueryAF.elements["assistantOrgName"].value="all";
	document.fiveLevelQueryAF.elements["office"].disabled="true";
	document.fiveLevelQueryAF.elements["loanBankName"].disabled="";
	document.fiveLevelQueryAF.elements["button1"].disabled="true";
	document.fiveLevelQueryAF.elements["button2"].disabled="true";
	document.fiveLevelQueryAF.elements["assistantOrgName"].disabled="true";
	//document.fiveLevelQueryAF.elements["developerName"].disabled="true";
	//document.fiveLevelQueryAF.elements["floorId"].disabled="true";
}

function mode3(){
	document.fiveLevelQueryAF.elements["office"].value="all";
	document.fiveLevelQueryAF.elements["loanBankName"].value="all";
	//document.fiveLevelQueryAF.elements["developerName"].value="";
	//document.fiveLevelQueryAF.elements["buyhouseorgid"].value="";
	document.fiveLevelQueryAF.elements["floorId"].value="";
	document.fiveLevelQueryAF.elements["floorid"].value="";
	document.fiveLevelQueryAF.elements["assistantOrgName"].value="all";
	document.fiveLevelQueryAF.elements["office"].disabled="true";
	document.fiveLevelQueryAF.elements["loanBankName"].disabled="true";
	//document.fiveLevelQueryAF.elements["developerName"].disabled="";
	document.fiveLevelQueryAF.elements["button1"].disabled="";
	document.fiveLevelQueryAF.elements["button2"].disabled="true";
	document.fiveLevelQueryAF.elements["assistantOrgName"].disabled="true";
	//document.fiveLevelQueryAF.elements["floorId"].disabled="true";
}

function mode4(){
	document.fiveLevelQueryAF.elements["office"].value="all";
	document.fiveLevelQueryAF.elements["loanBankName"].value="all";
	document.fiveLevelQueryAF.elements["developerName"].value="";
	document.fiveLevelQueryAF.elements["buyhouseorgid"].value="";
	//document.fiveLevelQueryAF.elements["floorId"].value="";
	//document.fiveLevelQueryAF.elements["floorid"].value="";
	document.fiveLevelQueryAF.elements["assistantOrgName"].value="all";
	document.fiveLevelQueryAF.elements["office"].disabled="true";
	document.fiveLevelQueryAF.elements["loanBankName"].disabled="true";
	document.fiveLevelQueryAF.elements["button1"].disabled="true";
	//document.fiveLevelQueryAF.elements["floorId"].disabled="";
	document.fiveLevelQueryAF.elements["button2"].disabled="";
	document.fiveLevelQueryAF.elements["assistantOrgName"].disabled="true";
	//document.fiveLevelQueryAF.elements["developerName"].disabled="true";
	
}

function mode5(){
	document.fiveLevelQueryAF.elements["office"].value="all";
	document.fiveLevelQueryAF.elements["loanBankName"].value="all";
	document.fiveLevelQueryAF.elements["developerName"].value="";
	document.fiveLevelQueryAF.elements["buyhouseorgid"].value="";
	document.fiveLevelQueryAF.elements["floorId"].value="";
	document.fiveLevelQueryAF.elements["floorid"].value="";
	//document.fiveLevelQueryAF.elements["assistantOrgName"].value="all";
	document.fiveLevelQueryAF.elements["office"].disabled="true";
	document.fiveLevelQueryAF.elements["loanBankName"].disabled="true";
	document.fiveLevelQueryAF.elements["button1"].disabled="true";
	document.fiveLevelQueryAF.elements["button2"].disabled="true";
	document.fiveLevelQueryAF.elements["assistantOrgName"].disabled="";
	//document.fiveLevelQueryAF.elements["developerName"].disabled="true";
	//document.fiveLevelQueryAF.elements["floorId"].disabled="true";
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
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
										<font color="00B5DB">统计查询&gt;五级分类查询统计</font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="226">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="1102">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/fiveLevelQueryFindAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td class="td1" colspan="8">
								<html:radio name="fiveLevelQueryAF" property="mode" value="1"
									onclick="mode1();">办事处</html:radio>
								<html:radio name="fiveLevelQueryAF" property="mode" value="2"
									onclick="mode2();">放款银行</html:radio>
								<html:radio name="fiveLevelQueryAF" property="mode" value="3"
									onclick="mode3();">开发商</html:radio>
								<html:radio name="fiveLevelQueryAF" property="mode" value="4"
									onclick="mode4();">楼盘</html:radio>
								<html:radio name="fiveLevelQueryAF" property="mode" value="5"
									onclick="mode5();">担保公司</html:radio>
							</td>
						</tr>
						<tr>
							<td width="16%" class="td1">
								办事处
							</td>
							<td colspan="2">
								<logic:notEmpty name="officeList">
									<html:select name="fiveLevelQueryAF" property="office"
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
									<html:select name="fiveLevelQueryAF" property="loanBankName"
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
								<html:text name="fiveLevelQueryAF" property="developerName"
									styleClass="input3" readonly="true"/>
							</td>
							<html:hidden name="fiveLevelQueryAF" property="buyhouseorgid" />
							<td width="9%">
								<input type="button" class="buttona" value="..." name="button1"
									onclick="findorghouse();">
							</td>
							<td width="16%" class="td1">
								楼盘
							</td>
							<td width="25%">
								<html:text name="fiveLevelQueryAF" property="floorId"
									styleClass="input3" readonly="true"/>
							</td>
							<html:hidden name="fiveLevelQueryAF" property="floorid" />
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
									<html:select name="fiveLevelQueryAF"
										property="assistantOrgName" styleClass="input3">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="222">
										<b class="font14">五级分类统计列表</b>
									</td>
									<td width="1106" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/fiveLevelQueryMaintainAC" style="margin: 0">
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
								正常
							</td>
							<td align="center" class=td2 colspan="2">
								关注
							</td>
							<td align="center" class=td2 colspan="2">
								次级
							</td>
							<td align="center" class=td2 colspan="2">
								可疑
							</td>
							<td align="center" class=td2 colspan="2">
								损失
							</td>
							<td align="center" class=td2 colspan="2">
								合计
							</td>
							<td align="center" class=td2 colspan="2">
								不良率
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
						<logic:notEmpty name="fiveLevelQueryAF" property="list">
						<% int j=0;
			String strClass="";
		%>
							<logic:iterate name="fiveLevelQueryAF" property="list"
								id="element" indexId="i">
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
												value="<bean:write name="fiveLevelQueryAF" property="id"/>"
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
										<bean:write name="element" property="floorId" />
									</td>
									<td valign="top">
										<bean:write name="element" property="assistantOrgName" />
									</td>
									<td valign="top">
										<bean:write name="element" property="normalCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="normalValue" />
									</td>
									<td valign="top">
										<bean:write name="element" property="attentionCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="attentionValue" />
									</td>
									<td valign="top">
										<bean:write name="element" property="secondaryCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="secondaryValue" />
									</td>
									<td valign="top">
										<bean:write name="element" property="shadinessCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="shadinessValue" />
									</td>
									<td valign="top">
										<bean:write name="element" property="damnifyCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="damnifyValue" />
									</td>
									<td valign="top">
										<bean:write name="element" property="totalCount" />
									</td>
									<td valign="top">
										<bean:write name="element" property="totalValue" />
									</td>
									<td valign="top">
										<bean:write name="element" property="badRateCount" />
										%
									</td>
									<td valign="top">
										<bean:write name="element" property="badRateValue" />
										%
									</td>
								</tr>
								
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="fiveLevelQueryAF" property="list">
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
								<logic:notEmpty name="fiveLevelQueryAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</tr>
									</table>
								</logic:notEmpty>
								<logic:empty name="fiveLevelQueryAF" property="list">
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

