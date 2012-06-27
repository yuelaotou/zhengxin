<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgpay.action.ChgpayTbShowAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ChgpayTbShowAC.PAGINATION_KEY);
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
   var count=document.chgpayTbListAF.listCount.value.trim();
	
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
  var queryString = "chgpayTbFindAAC.do?";  

    var empId=document.chgpayTbListAF.elements["empId"].value;
    var empName=document.chgpayTbListAF.elements["empName"].value;
    var orgId=document.chgpayTbListAF.elements["orgId"].value;   
    var orgName=document.chgpayTbListAF.elements["orgName"].value;
    var startChgMonth=document.chgpayTbListAF.elements["startChgMonth"].value;
     var endChgMonth=document.chgpayTbListAF.elements["endChgMonth"].value; 
    var startBizDate=document.chgpayTbListAF.elements["startBizDate"].value;
    var endBizDate=document.chgpayTbListAF.elements["endBizDate"].value;
    var type=document.chgpayTbListAF.elements["type"].value;
  
    if(empId=="" && empName=="" && orgId=="" 
        && orgName=="" && startChgMonth=="" && endChgMonth==""&& startBizDate==""&& endBizDate=="" ){
       alert("必须输入至少一项查询条件");
       return false;
  } 
    queryString = queryString + "empId="+empId+"&empName="+empName+"&orgId="+orgId+ 
    "&orgName="+orgName+"&startChgMonth="+startChgMonth+"&endChgMonth="+endChgMonth+
     "&startBizDate="+startBizDate+"&endBizDate="+endBizDate+"&type="+type;
    findInfo(queryString);
  }
  function showlist() {
   document.Form1.submit();
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()"
	onContextmenu="return false">

	<jsp:include page="/inc/sort.jsp">
		<jsp:param name="url" value="chgpayTbShowAC.do" />
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
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										<a href="chgpayTaForwardURLAC.do" class=a2>单位调整查询</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttonbl.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										职工调整查询
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
										<font color="00B5DB"> 统计查询&gt;缴额调整</font>
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
				<html:form action="/chgpayTbShowAC.do">
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
								单位编号
							</td>
							<td width="33%" align="center" colspan="3">
								<html:text name="chgpayTbListAF" property="orgId"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td align="center" colspan="3">
								<html:text name="chgpayTbListAF" property="orgName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>

						<tr>
							<td width="17%" class="td1">
								职工编号
							</td>
							<td width="33%" align="center" colspan="3">
								<html:text name="chgpayTbListAF" property="empId"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								职工名称
							</td>
							<td align="center" colspan="3">
								<html:text name="chgpayTbListAF" property="empName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>


						<tr>

							<td width="17%" class="td1">
								调整年月
							</td>
							<td align="center" width="15%">
								<html:text name="chgpayTbListAF" property="startChgMonth"
									styleClass="input3" styleId="txtsearch"></html:text>

							</td>
							<td align="center" width="3%">
								至
							</td>
							<td align="center" width="15%">
								<html:text name="chgpayTbListAF" property="endChgMonth"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								调整日期
							</td>
							<td align="center" width="15%">
								<html:text name="chgpayTbListAF" property="startBizDate"
									styleClass="input3" styleId="txtsearch"></html:text>

							</td>
							<td align="center" width="3%">
								至
							</td>
							<td align="center" width="15%">
								<html:text name="chgpayTbListAF" property="endBizDate"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>

						<tr>
							<td>
								<html:hidden name="chgpayTbListAF" property="type"></html:hidden>
								<html:hidden name="chgpayTbListAF" property="listCount"
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
							合计：调整职工数
							<u>：<bean:write name="chgpayTbListAF"
									property="oldPaymentOrg" /> </u> 调整前应缴总额
							<u>：<bean:write name="chgpayTbListAF"
									property="totalOldPayment" /> </u> 调整后应缴额总额
							<u>：<bean:write name="chgpayTbListAF" property="totalPaySum" />
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">缴额调整列表</b>
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

				<html:form action="/chgpayTbMaintainAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td width="4%" height="23" align="center" bgcolor="C4F0FF"></td>
							<td width="9%" align="center" class=td2>
								<a href="javascript:sort('chgPaymentTail.chgPaymentHead.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPaymentTail.chgPaymentHead.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td width="9%" align="center" class=td2>
								<a
									href="javascript:sort('chgPaymentTail.chgPaymentHead.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPaymentTail.chgPaymentHead.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>


							<td width="9%" align="center" class=td2>
								<a href="javascript:sort('chgPaymentTail.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPaymentTail.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td width="9%" align="center" class=td2>
								<a href="javascript:sort('chgPaymentTail.empName')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPaymentTail.empName">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>


							<td width="10%" align="center" class=td2>
								调整前工资额
							</td>
							<td width="11%" align="center" class=td2>
								调整后工资额
							</td>
							<td width="10%" align="center" class=td2>
								调整前应缴额
							</td>
							<td width="9%" align="center" class=td2>
								调整后应缴额
							</td>

							<td width="9%" align="center" class=td2>
								<a
									href="javascript:sort('chgPaymentTail.chgPaymentHead.chgMonth')">调整年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPaymentTail.chgPaymentHead.chgMonth">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td width="9%" align="center" class=td2>
								调整日期
							</td>

						</tr>


						<logic:notEmpty name="chgpayTbListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="chgpayTbListAF" property="list"
								indexId="i">
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
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass%>">
									<td>
										<div align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="chgPaymentHead.org.id"
												format="0000000000" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element"
												property="chgPaymentHead.org.orgInfo.name" />
										</div>
									</td>
									<td valign="top">
										<div align="left">
											<bean:write name="element" property="empId" format="000000" />
										</div>
									</td>
									<td valign="top">
										<div align="left">
											<bean:write name="element" property="emp.empInfo.name" />
										</div>
									</td>
									<td valign="top">
										<div align="left">
											<bean:write name="element" property="oldSalaryBase" />
										</div>
									</td>
									<td valign="top">
										<div align="left">
											<bean:write name="element" property="salaryBase" />
										</div>
									</td>
									<td valign="top">
										<div align="left">
											<bean:write name="element" property="oldOrgEmpPaySum" />
										</div>
									</td>
									<td valign="top">
										<div align="left">
											<bean:write name="element" property="orgEmpPaySum" />
										</div>
									</td>

									<td>
										<div align="left">
											<bean:write name="element" property="chgPaymentHead.chgMonth" />
										</div>
									</td>
									<td>
										<div align="left">
											<bean:write name="element" property="chgPaymentHead.bizDate" />
										</div>
									</td>

								</tr>

							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="chgpayTbListAF" property="list">
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
												<jsp:param name="url" value="chgpayTbShowAC.do" />
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
				<form action="chgpayTbShowAC.do" method="POST" name="Form1"
					id="Form1">
				</form>
	</table>
</body>
</html:html>

