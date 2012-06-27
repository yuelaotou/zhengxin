<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.action.EmpOperationFlowTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			EmpOperationFlowTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>业务流水>>职工业务流水</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
</head>
<script type="text/javascript">
var s1="";
var s2="";

function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	for(i=0;i<document.all.length;i++){//获得所有form
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		}
	}
    var count = "<bean:write name="pagination" property="nrOfElements"/>";
    if(count=="0"){
  		document.all.item(s1).disabled="true";
    }else{
  		document.all.item(s1).disabled="";
    }
}

function gotoQuery(){
	var orgid=document.all.orgid.value;
	//var orgname=document.all.orgname.value;
	var empid=document.all.empid.value;
	//var empname=document.all.empname.value;
	//var noteNum=document.all.noteNum.value;
	//var docNum=document.all.docNum.value;
	//var opStatus=document.all.opStatus.value;
	//var opType=document.all.opType.value;
	var date1=document.all.inOpDate.value;
	var date2=document.all.opDate.value;
	//var opDirection=document.all.opDirection.value;
	var money1=document.all.inOpMoney.value;
	var money2=document.all.opMoney.value;
	if(money1 != ""){
		var str=checkMoney(money1);
		if(!str){
			document.all.inOpMoney.value="";
			document.all.inOpMoney.focus();
			return false;
		}
	}
	if(money2 != ""){
		var str=checkMoney(money2);
		if(!str){
			document.all.opMoney.value="";
			document.all.opMoney.focus();
			return false;
		}
	}
	
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="empOperationFlowTaShowAC.do" />
	</jsp:include>

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
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询<font color="00B5DB">&gt;</font>职工流水</font>
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
				<html:form action="/empOperationFlowTaFindAC">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">基 本 信 息</b>
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
								单位编号
							</td>
							<td colspan="3">
								<html:text property="orgid" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%" colspan="3">
								<html:text property="orgname" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								职工编号
							</td>
							<td width="33%" colspan="3">
								<html:text property="empid" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								职工姓名
							</td>
							<td width="33%" colspan="3">
								<html:text property="empname" styleClass="input3" maxlength="20"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								结算号
							</td>
							<td width="33%" colspan="3">
								<html:text property="noteNum" styleClass="input3" maxlength="20"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								凭证编号
							</td>
							<td width="33%" colspan="3">
								<html:text property="docNum" styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								业务状态
							</td>
							<td width="33%" colspan="3">
								<html:select property="opStatus" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="opStatusMap"
										name="empOperationFlowAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								业务类型
							</td>
							<td width="33%" colspan="3">
								<html:select property="opType" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="opTypeMap"
										name="empOperationFlowAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								发生日期
							</td>
							<td width="15%">
								<html:text property="inOpDate" styleClass="input3" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text property="opDate" styleClass="input3" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								发生金额
							</td>
							<td width="15%">
								<html:text property="inOpMoney" styleClass="input3"
									maxlength="18" onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text property="opMoney" styleClass="input3" maxlength="18"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								发生方向
							</td>
							<td width="33%" colspan="3">
								<html:select property="opDirection" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:option value="0">借</html:option>
									<html:option value="1">贷</html:option>
								</html:select>
							</td>
							<td width="17%" class="td1"></td>
							<td colspan="3" class="td7">
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return gotoQuery();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/empOperationFlowTaMaintainAC">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<logic:notEmpty name="totallist">
								<td class=h4>
									人数合计
									<u>：<bean:write name="totalDTO" property="counts" /> </u>
									发生额合计
									<u>：<bean:write name="totalDTO" property="sumMoney" /> </u>
									利息合计
									<u>：<bean:write name="totalDTO" property="sumInterest" />
									</u>
								</td>
							</logic:notEmpty>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="142">
											<b class="font14">职工流水列表 </b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="762">
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
						<tr>
							<td align="center" class=td2>
								&nbsp;
							</td>
							<td align="center" class=td2 height="23" bgcolor="C4F0FF">
								<a
									href="javascript:sort('empHAFAccountFlow.orgHAFAccountFlow.noteNum')">结算号</a>
								<logic:equal name="pagination" property="orderBy"
									value="empHAFAccountFlow.orgHAFAccountFlow.noteNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2 height="23" bgcolor="C4F0FF">
								<a
									href="javascript:sort('empHAFAccountFlow.orgHAFAccountFlow.docNum')">凭证编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="empHAFAccountFlow.orgHAFAccountFlow.docNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a
									href="javascript:sort('empHAFAccountFlow.orgHAFAccountFlow.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="empHAFAccountFlow.orgHAFAccountFlow.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a
									href="javascript:sort('empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="empHAFAccountFlow.orgHAFAccountFlow.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empHAFAccountFlow.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="empHAFAccountFlow.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empHAFAccountFlow.empName')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="empHAFAccountFlow.empName">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a
									href="javascript:sort('empHAFAccountFlow.orgHAFAccountFlow.biz_Type')">业务类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="empHAFAccountFlow.orgHAFAccountFlow.biz_Type">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								发生日期
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empHAFAccountFlow.empMoney')">发生金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="empHAFAccountFlow.empMoney">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								发生利息
							</td>
							<td align="center" class=td2>
								发生方向
							</td>
							<td align="center" class=td2>
								<a
									href="javascript:sort('empHAFAccountFlow.orgHAFAccountFlow.bizStatus')">业务状态</a>
								<logic:equal name="pagination" property="orderBy"
									value="empHAFAccountFlow.orgHAFAccountFlow.bizStatus">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								提取原因
							</td>
							<td align="center" class=td2>
								操作员
							</td>
							<td align="center" class=td2>
								复核人
							</td>
						</tr>
						<logic:notEmpty name="empflowlist">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="empflowlist" id="element" indexId="i">
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
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<bean:write name="element" property="noteNum" />
									</td>
									<td>
										<bean:write name="element" property="docNum" />
									</td>
									<td>
										<bean:write name="element" property="orgid"
											format="0000000000" />
									</td>
									<td>
										<bean:write name="element" property="orgname" />
									</td>
									<td>
										<bean:write name="element" property="empid" format="000000" />
									</td>
									<td>
										<bean:write name="element" property="empname" />
									</td>
									<td>
										<bean:write name="element" property="opType" />
									</td>
									<td>
										<bean:write name="element" property="opDate" />
									</td>
									<td>
										<bean:write name="element" property="opMoney" />
									</td>
									<td>
										<bean:write name="element" property="opInterest" />
									</td>
									<td>
										<bean:write name="element" property="opDirection" />
									</td>
									<td>
										<bean:write name="element" property="opStatus" />
									</td>
									<td>
										<bean:write name="element" property="reason" />
									</td>
									<td>
										<bean:write name="element" property="reserveaA" />
									</td>
									<td>
										<bean:write name="element" property="checkPerson" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="empflowlist">
							<tr>
								<td colspan="13" height="30" style="color:red">
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
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url" value="empOperationFlowTaShowAC.do" />
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
							<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="">
												<bean:message key="button.print" />
											</html:submit>
										</td>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
