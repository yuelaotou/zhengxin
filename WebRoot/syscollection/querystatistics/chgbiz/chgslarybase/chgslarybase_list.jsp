<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgslarybase.action.ChgslarybaseTaShowAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ChgslarybaseTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>工资基数调整</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
</head>
<script language="javascript"
	src="<%=request.getContextPath()%>/js/common.js">




<script language="javascript"></script>
<script language="javascript" type="text/javascript">
var s1="";

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
    var count=document.chgslarybaseListAF.listCount.value.trim();
	
		for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		}
	}
	if(count==""){
		document.all.item(s1).disabled="true";	
	}else
	if(count=="1"){
		document.all.item(s1).disabled="";	
	}
	
}

function backErrors(errors){
   alert(errors);
}

function executeAjax()
  {
  var queryString = "chgslarybaseTaFindAAC.do?";  
    var officecode=document.chgslarybaseListAF.elements["officecode"].value;
    var collectionBankId=document.chgslarybaseListAF.elements["collectionBankId"].value;
    var orgId=document.chgslarybaseListAF.elements["orgId"].value;   
    var orgName=document.chgslarybaseListAF.elements["orgName"].value;
    var startChgMonth=document.chgslarybaseListAF.elements["startChgMonth"].value;
     var endChgMonth=document.chgslarybaseListAF.elements["endChgMonth"].value; 
    var startBizDate=document.chgslarybaseListAF.elements["startBizDate"].value;
    var endBizDate=document.chgslarybaseListAF.elements["endBizDate"].value;
     var chgStatus=document.chgslarybaseListAF.elements["chgStatus"].value;
       var type=document.chgslarybaseListAF.elements["type"].value;
  if(officecode=="" && collectionBankId=="" && orgId=="" 
  
  && orgName=="" && startChgMonth=="" && endChgMonth==""&& startBizDate==""&& endBizDate==""&& chgStatus==""){
     alert("必须输入至少一项查询条件");
    return false;
  } 
    
    queryString = queryString + "officecode="+officecode+"&collectionBankId="+collectionBankId+"&orgId="+orgId+ 
    "&orgName="+orgName+"&startChgMonth="+startChgMonth+"&endChgMonth="+endChgMonth+"&endChgMonth="+endChgMonth
   + "&startBizDate="+startBizDate+"&endBizDate="+endBizDate+"&chgStatus="+chgStatus+"&type="+type;

    findInfo(queryString);
  }
  function showlist() {
   document.Form1.submit();
}
function gotoShow(){
	return false;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()"
	onContextmenu="return false">

	<jsp:include page="/inc/sort.jsp">
		<jsp:param name="url" value="chgslarybaseTaShowAC.do" />
	</jsp:include>

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=request.getContextPath()%>/img/table_left.gif"
								width="7" height="37">
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							width="55">
							&nbsp;
						</td>

						<td width="235"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttonbl.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										单位调整查询
									</td>

								</tr>
							</table>
						</td>
						<td
							background="<%=request.getContextPath()%>/img/table_bg_line.gif"
							align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=request.getContextPath()%>/img/title_banner.gif"
											width="37" height="24">
									</td>
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB"> 统计查询 &gt;工资基数调整 </font>
									</td>
									<td class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=request.getContextPath()%>/img/table_right.gif"
								width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<html:form action="/chgslarybaseTaShowAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
											align="center">
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
								办事处
							</td>
							<td width="33%" align="center" colspan="3">
								<logic:notEmpty name="chgslarybaseListAF" property="officeList1">
									<html:select name="chgslarybaseListAF" property="officecode"
										styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="officeList1" property="value"
											labelProperty="label" />
									</html:select>
								</logic:notEmpty>
							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td align="center" colspan="3">
								<logic:notEmpty name="chgslarybaseListAF" property="bankList1">
									<html:select name="chgslarybaseListAF"
										property="collectionBankId" styleClass="input4">
										<html:option value=""></html:option>
										<html:options collection="bankList1" property="value"
											labelProperty="label" />
									</html:select>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="33%" align="center" colspan="3">
								<html:text name="chgslarybaseListAF" property="orgId"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td align="center" colspan="3">
								<html:text name="chgslarybaseListAF" property="orgName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>

							<td width="17%" class="td1">
								调整年月
							</td>
							<td align="center" width="15%">
								<html:text name="chgslarybaseListAF" property="startChgMonth"
									styleClass="input3" styleId="txtsearch"></html:text>

							</td>
							<td align="center" width="3%">
								至
							</td>
							<td align="center" width="15%">
								<html:text name="chgslarybaseListAF" property="endChgMonth"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								调整日期
							</td>
							<td align="center" width="15%">
								<html:text name="chgslarybaseListAF" property="startBizDate"
									styleClass="input3" styleId="txtsearch"></html:text>

							</td>
							<td align="center" width="3%">
								至
							</td>
							<td align="center" width="15%">
								<html:text name="chgslarybaseListAF" property="endBizDate"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								业务状态
							</td>
							<td width="33%" align="center" colspan="3">

								<html:select name="chgslarybaseListAF" property="chgStatus"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="map"
										name="chgslarybaseListAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								<html:hidden name="chgslarybaseListAF" property="type"></html:hidden>
								<html:hidden name="chgslarybaseListAF" property="listCount"
									styleClass="input3" styleId="txtsearch"></html:hidden>
							</td>

						</tr>

					</table>


					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">

								<html:submit property="method" styleClass="buttona"
									onclick="executeAjax();">
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
							合计：调整单位数
							<u>：<bean:write name="chgslarybaseListAF"
									property="oldPaymentOrg" /> </u> 调整前应缴总额
							<u>：<bean:write name="chgslarybaseListAF"
									property="totalOldPayment" /> </u> 调整后应缴额总额
							<u>：<bean:write name="chgslarybaseListAF"
									property="totalPaySum" /> </u>
						</td>
					</tr>
				</table>

				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">工资基数调整列表</b>
									</td>
									<td width="750" height="22" align="center"
										background="<%=request.getContextPath()%>/img/bg2.gif">
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

				<html:form action="/chgslarybaseTaMaintainAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td height="23" align="center" bgcolor="C4F0FF"></td>

							<td align="center" class=td2>
								<a href="javascript:sort('a1.id')">单位账号</a>
								<logic:equal name="pagination" property="orderBy"
									value="a1.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a
									href="javascript:sort('b1.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="b1.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								人数
							</td>
							<td align="center" class=td2>
								单位缴存比例
							</td>
							<td align="center" class=td2>
								职工缴存比例
							</td>
							<td align="center" class=td2>
								调整前工资基数
							</td>
							<td align="center" class=td2>
								调整后工资基数
							</td>
							<td align="center" class=td2>
								调整前汇缴额
							</td>
							<td align="center" class=td2>
								调整后汇缴额
							</td>
							<td align="center" class=td2>
								汇缴额差额
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('a2.chg_month')">调整年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="a2.chg_month">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								调整日期
							</td>

						</tr>


						<logic:notEmpty name="chgslarybaseListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="chgslarybaseListAF"
								property="list" indexId="i">
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
									onMouseOut='gotoColorpp("<%=i%>", idAF);'
									class="<%=strClass%>">
									<td>
										<div align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</div>
									</td>

									<td>
										<div align="left">
											<bean:write name="element" property="orgid"
												format="0000000000" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="orgname" />
<%--											<a href="#"--%>
<%--												onClick="window.open('chgslarybaseTbShowAC.do?id=<bean:write name="element" property="id"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',resizable,scrollbars=yes');return gotoShow();"><bean:write--%>
<%--													name="element" property="orgname" /> </a>--%>
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="personCount" />
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="orgrate" />
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="emprate" />
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="preSalary" />
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="curSalary" />
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="prePay" />
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="curPay" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="payless" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="chgMonth" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="bizdate" />
										</div>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="chgslarybaseListAF" property="list">
							<tr>
								<td colspan="20" height="30" style="color:red">
									没有找到与条件相符合结果！
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
											<jsp:include page="/inc/pagination.jsp">
												<jsp:param name="url" value="chgslarybaseTaShowAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td align="center" height="6" colspan="1">
								<input type="hidden" name="chgMonth2" value="">
							</td>
						</tr>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="72">
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
				<form action="chgslarybaseTaShowAC.do" method="POST" name="Form1"
					id="Form1">
				</form>
	</table>
</body>
</html:html>

