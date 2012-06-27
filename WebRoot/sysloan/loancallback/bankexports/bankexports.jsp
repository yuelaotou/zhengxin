<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loancallback.bankexports.action.BankExportsTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			BankExportsTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String deletema = (String) request.getAttribute("deletema");
	String path = request.getContextPath();
%>
<html:html>
<head>
	<html:base />
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script>

<script type="text/javascript">


function loads(){
	var message="";
	<logic:messagesPresent>
		message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
	document.forms[1].elements["loanKouAcc"].value="";
	document.forms[1].elements["contractId"].value="";
	document.forms[1].elements["borrowerName"].value="";
	document.forms[1].elements["cardNum"].value="";
	var count="<bean:write name="pagination" property="nrOfElements"/>";
	if(count==0){
		document.all.disp2.disabled="true";
		document.all.disp4.disabled="true";
		document.all.disp5.disabled="true";
	}else{
<%--	  	var loanBankId=document.forms[0].elements["loanBankId"].value;--%>
<%--	  	var batchNum=document.forms[0].elements["batchNum"].value;--%>
<%--	  	if(loanBankId!=""&&batchNum!=""){--%>
<%--	  		document.all.disp1.disabled="true";--%>
<%--	  	}else if(loanBankId!=""){--%>
<%--	  		document.all.disp6.disabled="true";--%>
<%--	  	}--%>
	}
  	document.forms[0].elements["day"].value=document.all.temp_day.value;
<%--  	if(message==""){--%>
<%--	  	var loanBankId=document.forms[0].elements["loanBankId"].value;--%>
<%--	  	var batchNum=document.forms[0].elements["batchNum"].value;--%>
<%--	  	if(loanBankId!=""&&batchNum!=""){--%>
<%--	  		if("<%=deletema%>"=='yes'){--%>
<%--	  			document.forms[0].elements["batchNum"].value="";--%>
<%--	  		}--%>
<%--	  		document.all.disp1.disabled="true";--%>
<%--	  	}else if(loanBankId!=""){--%>
<%--	  		document.all.disp6.disabled="true";--%>
<%--	  	}--%>
<%--  	}--%>
}


function reloadList(){
  
  	getChildOption();
  	var selectObj = document.getElementById("childNode");
  	var day=document.forms[0].elements["day"].value;     
   	var colls = [];
  	if(day!=""){         
        colls = selectObj.options; //获取引用    
        colls.value=day;	  
  	}
}
function gotoDelete(){
	if(!confirm("是否确定删除记录！")){
		return false;
	}
}
function gotoDeleteAll(){
	if(!confirm("是否确定删除全部记录！")){
		return false;
	}
}
</script>
<script language="JavaScript">
var xmlHttpTemp;

function createXMLHttpRequestTemp() {
    if (window.ActiveXObject) {
        xmlHttpTemp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttpTemp = new XMLHttpRequest();
    }
} 

function findInfoTemp(fatherValue,url) {
 	createXMLHttpRequestTemp();  
    xmlHttpTemp.onreadystatechange = handleStateChangeTemp;
    xmlHttpTemp.open("GET", url, true);
    xmlHttpTemp.send(null);   
}

function handleStateChangeTemp() {
  if(xmlHttpTemp.readyState == 4) {
      if(xmlHttpTemp.status == 200) {
        var xmlDoc = xmlHttpTemp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		var texts  = xmlDoc.getElementsByTagName("text");

		var selectObj = document.getElementById("childNode");
		selectObj.length = 0;
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
		}
      }
      }
   }

function getChildOption() {
	var fatherValue = document.forms[0].elements["monthYear"].value;
	var yearMonth = document.all.yearMonth.value;
	if(fatherValue != yearMonth){
		document.forms[0].elements["day"].value=1;
	}
	document.all.childNode.style.display="";
	document.all.selectDay.style.display="none";
	var url = "<%=path%>/sysloan/getDayListAAC.do?fatherValue="+fatherValue;
	findInfoTemp(fatherValue, url);
}

function getChildValue(){
  	var selectObj = document.getElementById("childNode");
  	document.forms[0].elements["day"].value=selectObj.options[selectObj.selectedIndex].value;
}
function gotoCreat(){
	var yearMonth=document.forms[0].elements["monthYear"].value;
	var day=document.forms[0].elements["day"].value;
	var loanBankId=document.forms[0].elements["loanBankId"].value;
	if(loanBankId==""){
		alert("请选择银行！");
		return false;
	}
	document.forms[0].elements["batchNum"].value="";
	document.all.type_gjp.value='bank';
}
function onsubmit111(){
	var day=document.forms[0].elements["day"].value;
	var loanBankId=document.forms[0].elements["loanBankId"].value;
	if(loanBankId==""){
		alert("请选择银行！");
		return false;
	}
	document.all.type_gjp.value='gjj';
	document.Form1.submit();
}
function gotoGetDay(){
	document.forms[0].elements["day"].value=document.all.temp_day.value;
}
function gotoChange(){
	document.all.bankId.value=document.forms[0].elements["loanBankId"].value;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">

	<jsp:include flush="true" page="/waiting.inc" />
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
									<td width="112" height="37" align="center" valign="top"
										style="PADDING-top: 7px"></td>
									<td width="112" height="37" align="center"
										style="PADDING-top: 7px" valign="top"></td>
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
									<td width="163" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">回收贷款&gt;银行代扣导出</font>
									</td>
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
				<html:form action="/bankExportsTaCreatDataAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="193">
											<b class="font14">回 收 银 行 信 息</b>
										</td>
										<td width="732" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
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
								回收银行
							</td>
							<td width="33%">
								<logic:notEmpty name="banklist">
									<html:select name="bankExportsTaAF" property="loanBankId"
										styleClass="input4" onchange="gotoChange();">
										<html:option value=""></html:option>
										<html:options collection="banklist" property="value"
											labelProperty="label" />
									</html:select>
								</logic:notEmpty>
							</td>
							<td align="left" class=td1 width="17%">
								还至年月
							</td>
							<td align="left" width="20%">
								<logic:notEmpty name="yearMonthlist">
									<html:select name="bankExportsTaAF" property="monthYear"
										onchange="getChildOption()" styleId="select1">
										<html:option value=""></html:option>
										<html:options collection="yearMonthlist" property="value"
											labelProperty="label" />
									</html:select>
								</logic:notEmpty>
								<select id="childNode" onchange="getChildValue()"
									style="display:none">
								</select>
								<logic:notEmpty name="daylist">
									<html:select name="bankExportsTaAF" property="temp_day"
										styleId="selectDay" onchange="gotoGetDay();">
										<html:option value=""></html:option>
										<html:options collection="daylist" property="value"
											labelProperty="label" />
									</html:select>
									<input type="hidden" name="yearMonth"
										value="<bean:write name="yearMonth" />">
								</logic:notEmpty>
								<html:hidden property="day" />
							</td>

							<td align="left">
								<html:submit property="method" styleClass="buttonc"
									styleId="disp1" onclick="return gotoCreat();">
									<bean:message key="button.bank.kou.data" />
								</html:submit>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								公积金还贷批次号
							</td>
							<td width="33%">
								<html:text name="bankExportsTaAF" property="batchNum"
									disabled="true" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td align="left">
								<input type="hidden" name="type_gjp" value="">
								<input type="hidden" name="type_yg" value="s">
								<html:submit property="method" styleClass="buttonc"
									styleId="disp6" onclick="return onsubmit111();">
									<bean:message key="button.syscollection.repay.data" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/bankExportsTaFindAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="194">
											<b class="font14">查 询 条 件 </b>
										</td>
										<td width="731" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
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
							</td>
							<td width="33%">
								<html:text property="loanKouAcc" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%">
								<html:text property="contractId" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%">
								<html:text property="borrowerName" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td width="33%">
								<html:text property="cardNum" styleClass="input3"
									onkeydown="enterNextFocus1();" />
								<input type="hidden" name="bankId" value="">
							</td>
						</tr>
						<%-- 
<tr>
					<td width="17%" class="td1">
							是否公积金还贷
						</td>
						<td width="33%">
							<logic:notEmpty name="banklist">
								<html:select name="bankExportsTaAF" property="fund_st"
									styleClass="input4" >
									<html:option value="">否</html:option>
									<html:option value="0">是</html:option>
								
								</html:select>
							</logic:notEmpty>
						</td>
					</tr>
					--%>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona" onclick="">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/bankExportsTaMaintainAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：人数
								<u>：<bean:write name="DTO" property="shouldCount"/> </u> 应还本金
								<u>：<bean:write name="DTO" property="shouldCorpus" /> </u> 应还利息
								<u>：<bean:write name="DTO" property="shouldInterest" /> </u>逾期利息
								<u>：<bean:write name="DTO" property="punishInterest" /> </u>总还款额
								<u>：<bean:write name="DTO" property="sumMoney" /> </u>挂账发生额
								<u>：<bean:write name="DTO" property="occurMoney" /> </u>实收金额
								<u>：<bean:write name="DTO" property="realMoney" /> </u>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="197">
											<b class="font14">银行代扣数据导出信息列表</b>
										</td>
										<td width="728" height="22" align="center"
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
							<td class="td2">
								扣款账号
							</td>
							<td class="td2">
								合同编号
							</td>
							<td class="td2">
								<a href="javascript:sort('borrower.borrowerName')">借款人姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrower.borrowerName">
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
								还款类型
							</td>
							<td class="td2">
								还款年月
							</td>
							<td class="td2">
								应还本金
							</td>
							<td class="td2">
								应还利息
							</td>
							<td class="td2">
								逾期利息
							</td>
							<td class="td2">
								逾期天数
							</td>
							<td class="td2">
								总还款额
							</td>
							<td class="td2">
								挂账发生额
							</td>
							<td class="td2">
								实收金额
							</td>
						</tr>
						<logic:notEmpty name="callbacklist">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="callbacklist" id="element" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>", idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass%>"
									onDblClick="">

									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanKouAcc" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="contractId" />
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
											<bean:write name="element" property="loanType" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="loanKouYearmonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="shouldCorpus" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="shouldInterest" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="punishInterest" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="days" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="sumMoney" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="occurMoney" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="realMoney" />
										</p>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="callbacklist">
							<tr>
								<td colspan="15" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>

						</logic:empty>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="left">
								共
								<bean:write name="pagination" property="nrOfElements" />
								项
							</td>
							<td align="right">
								<jsp:include page="../../../inc/pagination.jsp">
									<jsp:param name="url" value="bankExportsTaShowAC.do" />
								</jsp:include>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="65">
											<html:submit property="method" styleClass="buttona"
												styleId="disp2" onclick="">
												<bean:message key="button.database.exports" />
											</html:submit>
										</td>
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												styleId="disp4" onclick="return gotoDeleteAll();">
												<bean:message key="button.deleteall" />
											</html:submit>
										</td>
										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												styleId="disp5" onclick="">
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
	<form action="bankExportsTaCreatDataAC.do" method="POST" name="Form1"
		id="Form1">
	</form>
</body>
</html:html>

