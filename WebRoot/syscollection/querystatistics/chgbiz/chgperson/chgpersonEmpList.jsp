<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action.ShowChgpersonEmpListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowChgpersonEmpListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询-变更信息-人员变更</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript">

function loads(){

	document.chgpersonEmpListAF.orgId.value="";
	document.chgpersonEmpListAF.orgName.value="";
	document.chgpersonEmpListAF.empId.value="";
	document.chgpersonEmpListAF.empName.value="";
	document.chgpersonEmpListAF.chgMonthStart.value="";
	document.chgpersonEmpListAF.chgMonthEnd.value="";
	document.chgpersonEmpListAF.chgDateStart.value="";
	document.chgpersonEmpListAF.chgDateEnd.value="";
	
}

function checkID(){
	var id=document.chgpersonEmpListAF.orgId.value.trim();
	if(isNaN(id)){
		alert("请录入单位编号!!");
		document.chgpersonEmpListAF.orgId.focus();
		return false;	
	}else{
		return true;
	}
}
function checkempID(){
	var id=document.chgpersonEmpListAF.empId.value.trim();
	if(isNaN(id)){
		alert("请录入职工编号!!");
		document.chgpersonEmpListAF.empId.focus();
		return false;	
	}else{
		return true;
	}
}


function checkData(){
	var orgId=document.chgpersonEmpListAF.orgId.value.trim();
	var orgName = document.chgpersonEmpListAF.orgName.value.trim();
	var empId=document.chgpersonEmpListAF.empId.value.trim();
	var empName=document.chgpersonEmpListAF.empName.value.trim();
	var chgMonthStart = document.chgpersonEmpListAF.chgMonthStart.value.trim();
	var chgMonthEnd=document.chgpersonEmpListAF.chgMonthEnd.value.trim();
	var chgDateStart=document.chgpersonEmpListAF.chgDateStart.value.trim();
	var chgDateEnd = document.chgpersonEmpListAF.chgDateEnd.value.trim();
	if(orgId.length==0&&orgName.length==0&&empId.length==0&&empName.length==0&&chgMonthStart.length==0&&chgMonthEnd.length==0&&chgDateStart.length==0&&chgDateEnd.length==0){
		alert("请至少录入一个条件进行查询!!");
		return false;
	}
	 if(chgMonthStart!=null&&chgMonthStart!=""){
		if(!checkYearMonth(chgMonthStart)){
			document.chgpersonEmpListAF.chgMonthStart.focus();
			return false;
		}
	}
	if(chgMonthEnd!=null&&chgMonthEnd!=""){
		if(!checkYearMonth(chgMonthEnd)){
			document.chgpersonEmpListAF.chgMonthEnd.focus();
			return false;
		}   
	}
	if(chgDateStart!=null&&chgDateStart!=""){
		if(!checkDate(chgDateStart)){
			document.chgpersonEmpListAF.chgDateStart.focus();
			return false;
		}
	}
	if(chgDateEnd!=null&&chgDateEnd!=""){
		if(!checkDate(chgDateEnd)){
			document.chgpersonEmpListAF.chgDateEnd.focus();
			return false;
		}
	}
		if(chgMonthStart!=null&&chgMonthStart!=""&&chgMonthEnd!=null&&chgMonthEnd!=""){
			if(!comparetoYM(chgMonthStart,chgMonthEnd)){
				document.chgpersonEmpListAF.chgMonthStart.focus();
				return false;
			}
		}
		return true;
	
}

</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="return loads();">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="showChgpersonEmpListAC.do" />
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
						<td width="350"
							background="<%=request.getContextPath()%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										<a href="chppersonOrgForwardURLAC.do" class=a2>单位变更查询</a>
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttonbl.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										职工变更查询
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="chgpersonQueryForwardAC.do" class=a2>变更查询</a>
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
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询</font><font color="00B5DB">&gt;人员变更</font>
									</td>
									<td width="115" class=font14>
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
				<html:form action="/findChgpersonEmpMaintainListAC.do">
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
							<td width="33%" colspan="3">
								<html:text name="chgpersonEmpListAF" property="orgId"
									onblur="return checkID();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%" colspan="3">
								<html:text name="chgpersonEmpListAF" property="orgName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								职工编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="chgpersonEmpListAF" property="empId"
									onblur="return checkempID();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								职工名称
							</td>
							<td width="33%" colspan="3">
								<html:text name="chgpersonEmpListAF" property="empName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								变更年月
							</td>
							<td width="15%">
								<html:text name="chgpersonEmpListAF" property="chgMonthStart"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="4%">
								至
							</td>
							<td width="14%">
								<html:text name="chgpersonOrgListAF" property="chgMonthEnd"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								变更日期
							</td>
							<td width="14%">
								<html:text name="chgpersonEmpListAF" property="chgDateStart"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="4%">
								至
							</td>
							<td width="15%">
								<html:text name="chgpersonEmpListAF" property="chgDateEnd"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit styleId="submits" property="method"
									styleClass="buttona" onclick="return checkData();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>



				<html:form action="/maintainChgpersonEmpMaintainListAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">

						<tr>
							<td class=h4>
								合计：变更总人数:
								<u><bean:write name="chgpersonEmpHead" property="sumCount" />&nbsp;</u>
								增加:
								<u><bean:write name="chgpersonEmpHead" property="insCount" />&nbsp;</u>
								减少:
								<u><bean:write name="chgpersonEmpHead" property="mulCount" />&nbsp;</u>
								增加缴额:
								<u><bean:write name="chgpersonEmpHead" property="insPayment" />&nbsp;</u>
								减少缴额:
								<u><bean:write name="chgpersonEmpHead" property="mulPayment" />&nbsp;</u>
								单位缴额:
								<u><bean:write name="chgpersonEmpHead" property="orgPayment" />&nbsp;</u>
								职工缴额:
								<u><bean:write name="chgpersonEmpHead" property="empPayment" />&nbsp;</u>
								缴额合计:
								<u><bean:write name="chgpersonEmpHead" property="sumPayment" />&nbsp;</u>
							</td>
						</tr>

						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="180">
											<b class="font14">人员变更列表</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
											align="center" width="724">
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
						cellpadding="0" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonTail.chgPersonHead.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonTail.chgPersonHead.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a
									href="javascript:sort('chgPersonTail.chgPersonHead.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonTail.chgPersonHead.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonTail.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonTail.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonTail.name')">职工名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonTail.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								变更前职工状态
							</td>
							<td align="center" class=td2>
								变更后职工状态
							</td>
							<td align="center" class=td2>
								单位缴额
							</td>
							<td align="center" class=td2>
								职工缴额
							</td>
							<td align="center" class=td2>
								缴额合计
							</td>
							<td align="center" class=td2>
								<a
									href="javascript:sort('chgPersonTail.chgPersonHead.chngMonth')">变更年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonTail.chgPersonHead.chngMonth">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								变更日期
							</td>
							<td align="center" class=td2>
								变更原因
							</td>
						</tr>


						<logic:notEmpty name="chgpersonEmpListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="chgpersonEmpListAF"
								property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>", idAF);'
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
										<bean:write name="element" property="chgPersonHead.org.id"
											format="0000000000" />
									</td>
									<td>
										<bean:write name="element"
											property="chgPersonHead.org.orgInfo.name" />
									</td>
									<td>
										<bean:write name="element" property="empId" format="000000" />
									</td>
									<td>
										<bean:write name="element" property="name" />
									</td>
									<td>
										<bean:write name="element" property="temp_oldPayStatus" />
									</td>
									<td>
										<bean:write name="element" property="temp_newPayStatus" />
									</td>
									<td>
										<bean:write name="element" property="orgPay" />
									</td>
									<td>
										<bean:write name="element" property="empPay" />
									</td>
									<td>
										<bean:write name="element" property="sumPay" />
									</td>
									<td>
										<bean:write name="element" property="chgPersonHead.chngMonth" />
									</td>
									<td>
										<bean:write name="element" property="chgPersonHead.bizDate" />
									</td>
									<td>
										<bean:write name="element" property="temp_chgreason" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>


						<logic:empty name="chgpersonEmpListAF" property="list">
							<tr>
								<td colspan="14" height="30" style="color:red">
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
												<jsp:param name="url" value="showChgpersonEmpListAC.do" />
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

								<logic:notEmpty name="chgpersonEmpListAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
												&nbsp;
											</td>
										</tr>
									</table>
								</logic:notEmpty>

								<logic:empty name="chgpersonEmpListAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
												&nbsp;
											</td>
										</tr>
									</table>
								</logic:empty>

							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>

</body>
</html:html>
