<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgperson.action.ShowChgpersonOrgListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowChgpersonOrgListAC.PAGINATION_KEY);
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

	document.chgpersonOrgListAF.orgId.value="";
	document.chgpersonOrgListAF.orgName.value="";
	document.chgpersonOrgListAF.officeCode.value="";
	document.chgpersonOrgListAF.collectionBank.value="";
	document.chgpersonOrgListAF.chgMonthStart.value="";
	document.chgpersonOrgListAF.chgMonthEnd.value="";
	document.chgpersonOrgListAF.chgDateStart.value="";
	document.chgpersonOrgListAF.chgDateEnd.value="";
	document.chgpersonOrgListAF.chgStatus.value="";

}


function checkID(){
	var id=document.chgpersonOrgListAF.orgId.value.trim();
	if(isNaN(id)){
		alert("请录入单位编号!!");
		document.chgpersonOrgListAF.orgId.focus();
		return false;	
	}else{
		return true;
	}
}


function checkData(){
	var orgId=document.chgpersonOrgListAF.orgId.value.trim();
	var orgName = document.chgpersonOrgListAF.orgName.value.trim();
	var officeCode=document.chgpersonOrgListAF.officeCode.value.trim();
	var collectionBank=document.chgpersonOrgListAF.collectionBank.value.trim();
	var chgMonthStart = document.chgpersonOrgListAF.chgMonthStart.value.trim();
	var chgMonthEnd=document.chgpersonOrgListAF.chgMonthEnd.value.trim();
	var chgDateStart=document.chgpersonOrgListAF.chgDateStart.value.trim();
	var chgDateEnd = document.chgpersonOrgListAF.chgDateEnd.value.trim();
	var chgStatus = document.chgpersonOrgListAF.chgStatus.value.trim();
	if(orgId.length==0&&orgName.length==0&&officeCode.length==0&&collectionBank.length==0&&chgMonthStart.length==0&&chgMonthEnd.length==0&&chgDateStart.length==0&&chgDateEnd.length==0&&chgStatus.length==0){
		alert("请至少录入一个条件进行查询!!");
		return false;
	}
	
   if(chgMonthStart!=null&&chgMonthStart!=""){
		if(!checkYearMonth(chgMonthStart)){
			document.chgpersonOrgListAF.chgMonthStart.focus();
			return false;
		}
	}
	if(chgMonthEnd!=null&&chgMonthEnd!=""){
		if(!checkYearMonth(chgMonthEnd)){
			document.chgpersonOrgListAF.chgMonthEnd.focus();
			return false;
		}   
	}
	if(chgDateStart!=null&&chgDateStart!=""){
		if(!checkDate(chgDateStart)){
			document.chgpersonOrgListAF.chgDateStart.focus();
			return false;
		}
	}
	if(chgDateEnd!=null&&chgDateEnd!=""){
		if(!checkDate(chgDateEnd)){
			document.chgpersonOrgListAF.chgDateEnd.focus();
			return false;
		}
	}
	
		if(chgMonthStart!=null&&chgMonthStart!=""&&chgMonthEnd!=null&&chgMonthEnd!=""){
			if(!comparetoYM(chgMonthStart,chgMonthEnd)){
				document.chgpersonOrgListAF.chgMonthStart.focus();
				return false;
			}
		}
	
}

</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="return loads();">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="showChgpersonOrgListAC.do" />
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
										background="<%=request.getContextPath()%>/img/buttonbl.gif"
										align="center" valign="top" style="PADDING-top: 7px">
										单位变更查询
									</td>
									<td width="112" height="37"
										background="<%=request.getContextPath()%>/img/buttong.gif"
										align="center" style="PADDING-top: 7px" valign="top">
										<a href="chppersonEmpForwardURLAC.do" class=a2>职工变更查询</a>
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
				<html:form action="/findChgpersonOrgMaintainListAC.do">
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
							<td width="33%" colspan="3">
								<html:select property="officeCode" styleClass="input4">
									<html:option value=""></html:option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td width="33%" colspan="3">
								<html:select property="collectionBank" styleClass="input4">
									<html:option value=""></html:option>
									<html:options collection="bankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="chgpersonOrgListAF" property="orgId"
									onblur="return checkID();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%" colspan="3">
								<html:text name="chgpersonOrgListAF" property="orgName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								变更年月
							</td>
							<td width="15%">
								<html:text name="chgpersonOrgListAF" property="chgMonthStart"
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
								<html:text name="chgpersonOrgListAF" property="chgDateStart"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="4%">
								至
							</td>
							<td width="15%">
								<html:text name="chgpersonOrgListAF" property="chgDateEnd"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								状态
							</td>
							<td width="33%" colspan="3">
								<html:select property="chgStatus" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="map"
										name="chgpersonOrgListAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkData();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>


				<html:form action="/maintainChgpersonOrgMaintainListAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">

						<tr>
							<td class=h4>
								合计：变更笔数:
								<u><bean:write name="chgpersonOrgHeadDTO"
										property="orgCount" />&nbsp;</u> 变更前应缴总额:
								<u><bean:write name="chgpersonOrgHeadDTO"
										property="preSumPay" />&nbsp;</u> 变更后应缴总额:
								<u><bean:write name="chgpersonOrgHeadDTO" property="sumPay" />&nbsp;</u>
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
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								<a
									href="javascript:sort('chgPersonHead.org.orgInfo.officecode')">办事处</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonHead.org.orgInfo.officecode">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a
									href="javascript:sort('chgPersonHead.org.orgInfo.collectionBankId')">归集银行</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonHead.org.orgInfo.collectionBankId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonHead.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonHead.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonHead.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonHead.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								变更人数
							</td>
							<td align="center" class=td2>
								变更前应缴总额
							</td>
							<td align="center" class=td2>
								变更后应缴总额
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonHead.chngMonth')">变更年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonHead.chngMonth">
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
								状态
							</td>
							<td align="center" class=td2>
								比率
							</td>
						</tr>

						<logic:notEmpty name="chgpersonOrgListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="chgpersonOrgListAF"
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
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass%>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<bean:write name="element"
											property="org.orgInfo.temp_officename" />
									</td>
									<td>
										<bean:write name="element"
											property="org.orgInfo.temp_collectionBankname" />
									</td>
									<td>
										<bean:write name="element" property="org.id"
											format="0000000000" />
									</td>
									<td>
										<a href="#"
											onclick="window.open('chppersonEmpForwardURLAC.do?chgpersonHeadID=<bean:write name="element" property="id"/>','window','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,resizable=yes,location=no, status=no')"><bean:write
												name="element" property="org.orgInfo.name" /> </a>
									</td>
									<td>
										<bean:write name="element" property="sumChgPerson" />
									</td>
									<td>
										<bean:write name="element" property="oldOldPayment" />
									</td>
									<td>
										<bean:write name="element" property="newOldPayment" />
									</td>
									<td>
										<bean:write name="element" property="chngMonth" />
									</td>
									<td>
										<bean:write name="element" property="bizDate" />
									</td>
									<td>
										<bean:write name="element" property="temp_chgStatus" />
									</td>
									<td>
										<bean:write name="element" property="temp_rate" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="chgpersonOrgListAF" property="list">
							<tr>
								<td colspan="12" height="30" style="color:red">
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
												<jsp:param name="url" value="showChgpersonOrgListAC.do" />
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

								<logic:notEmpty name="chgpersonOrgListAF" property="list">
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

								<logic:empty name="chgpersonOrgListAF" property="list">
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
