<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.accmng.listacc.action.ListaccShowAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ListaccShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>账簿管理-明细账</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>

<script type="text/javascript">

function checkSearchData(){
      var bizyear=document.all.bizyear.value.trim();
      var starperiod=document.all.starperiod.value.trim();
      var endperiod=document.all.endperiod.value.trim();
      var starsubject=document.all.starsubject.value.trim();
      if(bizyear.length==0){
      	alert("请录入会计期间的年!!");
      	document.all.bizyear.focus();
		return false;
      }else if(starperiod.length==0){
      	alert("请录入会计期间的起始期!!");
      	document.all.starperiod.focus();
		return false;
      }else if(endperiod.length==0){
      	alert("请录入会计期间的中止期!!");
      	document.all.endperiod.focus();
		return false;
      }else if(starsubject.length==0){
      	alert("请录入会计科目!!");
      	document.all.starsubject.focus();
		return false;
      }else if(!checkYear(bizyear)){
      	document.all.bizyear.focus();
		return false;
	  }else if(isNaN(starperiod)){
      	alert("请正确录入会计期间的起始期!!");
      	document.all.starperiod.focus();
		return false;
	  }else if(isNaN(endperiod)){
      	alert("请正确录入会计期间的中止期!!");
      	document.all.endperiod.focus();
		return false;
	  }
}

function tosubject(){
	gotoSubjectpop('0','<%=path%>','1');
}
function executeAjaxIn(flag){
	var starsubject=document.all.starsubject.value.trim();
	if(starsubject.length!=0){
	    var queryString = "checkSubjectcodeAAC.do?";
	    queryString = queryString + "subject="+starsubject;
	    findInfo(queryString);
	}
}
function display(message){
	if(message.length!=0){
		alert(message);
		document.all.starsubject.focus();
		document.all.starsubject.value="";
		return false;
	}else{
		document.all.bizyear.focus();
		return false;
	}

}
function tofocus(flag) //按回车置下一个位置
{
	if(flag=='1'){
		document.all.endsubject.focus();
		return false;
	}else{
		document.all.bizyear.focus();
		return false;
	}
} 
</script>
<body bgcolor="#FFFFFF" onContextmenu="return false">

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
						<td width="235" background="<%=path%>/img/table_bg_line.gif"></td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">账簿管理&gt;明细账</font>
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
				<html:form action="/listaccFindAC.do">
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
											align="center" width="731">
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
							<td width="13%" class="td1">
								所属办事处
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td colspan="7">
								<span class="td4"> <html:select property="office"
										styleClass="input4">
										<html:option value="全部"></html:option>
										<html:options collection="officeList1" property="value"
											labelProperty="label" />
									</html:select> </span>
							</td>
							<td width="13%" class="td1">
								科目代码
								<font color="#FF0000">*</font>
							</td>
							<td width="34%" colspan="3">
								<html:hidden property="temp_flag" />
								<html:text name="listaccAF" property="starsubject"
									onkeydown="javascrip:if(window.event.keyCode==13){return tofocus('2')}"
									styleClass="input3" onblur="executeAjaxIn('1');"></html:text>
							</td>
							<td width="3%">
								<input type="button" class=buttona value="..."
									onclick="tosubject();" />
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								会计期间
								<font color="#FF0000">*</font>
							</td>
							<td>
								<html:text name="listaccAF" property="bizyear"
									onkeydown="enterNextFocus1();" styleClass="input3"></html:text>
							</td>
							<td width="3%" align="center">
								年
							</td>
							<td width="7%">
								<html:text name="listaccAF" property="starperiod"
									onkeydown="enterNextFocus1();" styleClass="input3"></html:text>
							</td>
							<td width="3%" align="center">
								月
							</td>
							<td width="3%" align="center">
								至
							</td>
							<td width="8%">
								<html:text name="listaccAF" property="endperiod"
									onkeydown="enterNextFocus1();" styleClass="input3"></html:text>
							</td>
							<td width="3%" align="center">
								月
							</td>
							<%-- 	<html:hidden name="listaccAF" property="temp_year" />
								<html:hidden name="listaccAF" property="temp_month" /> --%>
						</tr>
					</table>

					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit styleClass="buttona"
									onclick="return checkSearchData()">
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
								<tr bgcolor="1BA5FF">
									<td align="center" height="6" colspan="2"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="128">
										<b class="font14">明 细 账</b>
									</td>
									<td height="22">
										<logic:notEmpty name="subjectName">
											<b><bean:write name="subjectName" />
											</b>
										</logic:notEmpty>
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
					<tr align="center">
						<td class="td2">
							日期
						</td>
						<td class="td2">
							凭证号
						</td>
						<td class="td2">
							摘要
						</td>
						<td class="td2">
							借方金额
						</td>
						<td class="td2">
							贷方金额
						</td>
						<td class="td2">
							方向
						</td>
						<td class="td2">
							余额
						</td>
					</tr>
					<logic:notEmpty name="list">
						<%
									int j = 0;
									String strClass = "";
						%>
						<logic:iterate name="list" id="element" indexId="i">
							<%
									j++;
									if (j % 2 == 0) {
										strClass = "td8";
									} else {
										strClass = "td9";
									}
							%>
							<tr id="tr<%=i%>" class="<%=strClass%>">
								<td valign="top" align="left">
									<p>
										<bean:write name="element" property="credenceDate" />
									</p>
								</td>
								<td valign="top" align="left">
									<a href="#"
										onClick="window.open('credencePopShowAC.do?docNum=<bean:write name="element" property="credenceNum"/>'+
										'&credenceDate=<bean:write name="element" property="credenceDate"/>'+
										'&credenceId=<bean:write name="element" property="credenceId"/>'+
										'&office=<bean:write name="element" property="office"/>','','width=900,height=500,top=150,left=150,scrollbars=yes');">
										<bean:write name="element" property="credenceNum" /> </a>
								</td>
								<td valign="top" align="left">
									<p>
										<bean:write name="element" property="summay" />
									</p>
								</td>
								<td valign="top" align="right">
									<p>
										<bean:write name="element" property="debit" format="#,##0.00" />
									</p>
								</td>
								<td valign="top" align="right">
									<p>
										<bean:write name="element" property="credit" format="#,##0.00" />
									</p>
								</td>
								<td valign="top" align="center">
									<p>
										<bean:write name="element" property="dirtection" />
									</p>
								</td>
								<td valign="top" align="right">
									<p>
										<bean:write name="element" property="money" format="#,##0.00" />
									</p>
								</td>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="list">
						<tr>
							<td colspan="11" height="30" style="color:red">
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
											<jsp:param name="url" value="listaccShowAC.do" />
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
	<html:form action="/listaccMaintainAC.do">
		<table width="95%" border="0" cellspacing="0" cellpadding="3"
			align="center">
			<tr valign="bottom">
				<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="69" align="right">
								<logic:empty name="list">
									<html:submit property="method" styleClass="buttona"
										disabled="true">
										<bean:message key="button.print" />
									</html:submit>
								</logic:empty>
								<logic:notEmpty name="list">
									<html:submit property="method" styleClass="buttona">
										<bean:message key="button.print" />
									</html:submit>
								</logic:notEmpty>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
