<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page
	import="org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.action.OrgCollInfoShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();

	Object pagination = session
			.getAttribute(OrgCollInfoShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<html:html>
<head>

	<title>My JSP 'orgcollinfo_list.jsp' starting page</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="JavaScript">
	var oldColor="#ffffff";                            //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;//保存当前颜色
		oTD.style.backgroundColor=newColor;//改变表格颜色
		newColor=oldColor;                 //改变下次要变成的颜色
	}
	function check(){
	
		var officeCode = document.orgCollInfoFindAF.elements["dto.officecode"].value.trim();
		var collectionBankId = document.orgCollInfoFindAF.elements["dto.collectionBankId"].value.trim();
		var character = document.orgCollInfoFindAF.elements["dto.character"].value.trim();
		var deptInCharge = document.orgCollInfoFindAF.elements["dto.deptInCharge"].value.trim();
		var orgId = document.orgCollInfoFindAF.elements["dto.orgId"].value.trim();
		var orgName = document.orgCollInfoFindAF.elements["dto.orgName"].value.trim();
		var openStatus = document.orgCollInfoFindAF.elements["dto.openStatus"].value.trim();
		var payMode = document.orgCollInfoFindAF.elements["dto.payMode"].value.trim();
		var region = document.orgCollInfoFindAF.elements["dto.region"].value.trim();
		var oldOrgId = document.orgCollInfoFindAF.elements["dto.oldOrgId"].value.trim();
		var inspector = document.orgCollInfoFindAF.elements["dto.inspector"].value.trim();
		var payDate = document.orgCollInfoFindAF.elements["dto.payDate"].value.trim();
		var code = document.orgCollInfoFindAF.elements["dto.code"].value.trim();
		var openDateStart = document.orgCollInfoFindAF.elements["dto.openDateStart"].value.trim();
		var openDateTimeEnd = document.orgCollInfoFindAF.elements["dto.openDateTimeEnd"].value.trim();
		var paySumStart = document.orgCollInfoFindAF.elements["dto.paySumStart"].value.trim();
		var paySumEnd = document.orgCollInfoFindAF.elements["dto.paySumEnd"].value.trim();
		var balanceStart = document.orgCollInfoFindAF.elements["dto.balanceStart"].value.trim();
		var balanceEnd = document.orgCollInfoFindAF.elements["dto.balanceEnd"].value.trim();
		var overPayStart = document.orgCollInfoFindAF.elements["dto.overPayStart"].value.trim();
		var overPayEnd = document.orgCollInfoFindAF.elements["dto.overPayEnd"].value.trim();
		var orgPayMonthStart = document.orgCollInfoFindAF.elements["dto.orgPayMonthStart"].value.trim();
		var orgPayMonthEnd = document.orgCollInfoFindAF.elements["dto.orgPayMonthEnd"].value.trim();
		var empPayMonthStart = document.orgCollInfoFindAF.elements["dto.empPayMonthStart"].value.trim();
		var empPayMonthEnd = document.orgCollInfoFindAF.elements["dto.empPayMonthEnd"].value.trim();
		var empCountStart = document.orgCollInfoFindAF.elements["dto.empCountStart"].value.trim();
		var empCountEnd = document.orgCollInfoFindAF.elements["dto.empCountEnd"].value.trim();
		var salarySumStart = document.orgCollInfoFindAF.elements["dto.salarySumStart"].value.trim();
		var salarySumEnd = document.orgCollInfoFindAF.elements["dto.salarySumEnd"].value.trim();
		
		if(officeCode==""&&collectionBankId==""&&character==""&&deptInCharge==""&&orgId==""&&orgName==""
			+""&&openStatus==""&&payMode==""&&region==""&&oldOrgId==""&&inspector==""&&payDate==""&&code==""
			+""&&openDateStart==""&&openDateTimeEnd==""&&paySumStart==""&&paySumEnd==""&&balanceStart==""
			+""&&balanceEnd==""&&overPayStart==""&&overPayEnd==""&&orgPayMonthStart==""&&orgPayMonthEnd==""
			+""&&empPayMonthStart==""&&empPayMonthEnd==""&&empCountStart==""&&empCountEnd==""&&salarySumStart==""&&salarySumEnd==""){
			alert("最少要输入一个查询条件");
			return false;
		}else{
			return true;
		}
	}
	function skiporg() {
		var orgId = getCheckId();
    	var queryString = "orgCollInfoSkipOrgFlowAAC.do?orgid="+orgId;
		findInfo(queryString);
	}
	function skipemp() {
		var orgId = getCheckId();
    	var queryString = "orgCollInfoSkipEmpFlowAAC?orgid="+orgId;
		findInfo(queryString);
	}
	function displayOrgFlow(){
		window.open('<%=path%>/syscollection/querystatistics/operationflow/orgoperationflow/showOrgbusinessflowListAC.do','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',resizable,scrollbars=yes');
	}
	function displayEmpFlow(){
		var orgId = getCheckId();
		window.open('<%=path%>/syscollection/querystatistics/baseinfosearch/empbaseinfo/employeeInfoSearchAC.do?dto.orgId='+orgId,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',resizable,scrollbars=yes');
	}	
	function print_Info(){
		document.print_orgColl.submit();
	}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">

	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="orgCollInfoShowAC.do" />
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
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">统计查询&gt;单位归集信息</font>
									</td>
									<td class=font14>
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
				<html:form action="/orgCollInfoFindListAC">
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
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								办事处
							</td>
							<td width="33%" colspan="3">
								<html:select name="orgCollInfoFindAF" property="dto.officecode"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								归集银行
							</td>
							<td width="33%" colspan="3">
								<html:select name="orgCollInfoFindAF"
									property="dto.collectionBankId" styleClass="input4">
									<html:option value=""></html:option>
									<html:options collection="collBankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位性质
							</td>
							<td width="33%" colspan="3">
								<html:select name="orgCollInfoFindAF" property="dto.character"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="natureofunitsMap"
										name="orgCollInfoFindAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								主管部门
							</td>
							<td width="33%" colspan="3">
								<html:select name="orgCollInfoFindAF"
									property="dto.deptInCharge" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="authoritiesMap"
										name="orgCollInfoFindAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="orgCollInfoFindAF" property="dto.orgId"
									styleClass="input3" maxlength="50"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%" colspan="3">
								<html:text name="orgCollInfoFindAF" property="dto.orgName"
									styleClass="input3" maxlength="50"></html:text>
							</td>
						<tr>
							<td width="17%" class="td1" height="15">
								单位缴率
							</td>
							<td width="15%" height="15">
								<html:text name="orgCollInfoFindAF" property="dto.orgrateStart"
									styleClass="input3" maxlength="50"></html:text>
							</td>
							<td width="4%" height="15" align="center">
								至
							</td>
							<td width="14%" height="15">
								<html:text name="orgCollInfoFindAF" property="dto.orgrateEnd"
									styleClass="input3" maxlength="50"></html:text>
							</td>
							<td width="17%" class="td1" height="15">
								职工缴率
							</td>
							<td width="15%" height="15">
								<html:text name="orgCollInfoFindAF" property="dto.emprateStart"
									styleClass="input3" maxlength="50"></html:text>
							</td>
							<td width="4%" height="15" align="center">
								至
							</td>
							<td width="14%" height="15">
								<html:text name="orgCollInfoFindAF" property="dto.emprateEnd"
									styleClass="input3" maxlength="50"></html:text>
							</td>
						</tr>
						<tr id="gjtr" style="display:none">
							<td colspan="8">
								<table width="100%" border="0" align="center" cellpadding=0
									cellspacing=1 id="table1">
									<tr>
										<td width="17%" class="td1">
											单位状态
										</td>
										<td width="33%" align="center" colspan="3">
											<html:select name="orgCollInfoFindAF"
												property="dto.openStatus" styleClass="input4">
												<html:option value=""></html:option>
												<html:optionsCollection property="openstatusMap"
													name="orgCollInfoFindAF" label="value" value="key" />
											</html:select>
										</td>
										<td width="17%" class="td1">
											缴存方式
										</td>
										<td width="33%" align="center" colspan="3">
											<html:select name="orgCollInfoFindAF" property="dto.payMode"
												styleClass="input4">
												<html:option value=""></html:option>
												<html:optionsCollection property="paymodeMap"
													name="orgCollInfoFindAF" label="value" value="key" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											所在地区
										</td>
										<td width="33%" align="center" colspan="3">
											<html:select name="orgCollInfoFindAF" property="dto.region"
												styleClass="input4">
												<html:option value=""></html:option>
												<html:optionsCollection property="regionMap"
													name="orgCollInfoFindAF" label="value" value="key" />
											</html:select>
										</td>
										<td width="17%" class="td1">
											原单位编号
										</td>
										<td width="33%" align="center" colspan="3">
											<html:text name="orgCollInfoFindAF" property="dto.oldOrgId"
												styleClass="input3" maxlength="50"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											稽查员
										</td>
										<td width="33%" align="center" colspan="3">
											<html:text name="orgCollInfoFindAF" property="dto.inspector"
												styleClass="input3" maxlength="50"></html:text>
										</td>
										<td width="17%" class="td1">
											发薪日
										</td>
										<td width="33%" align="center" colspan="3">
											<html:text name="orgCollInfoFindAF" property="dto.payDate"
												styleClass="input3" maxlength="50"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1" height="15">
											组织机构代码
										</td>
										<td width="33%" height="15" colspan="3">
											<html:text name="orgCollInfoFindAF" property="dto.code"
												styleClass="input3" maxlength="50"></html:text>
										</td>
										<td width="17%" class="td1" height="15">
											开户日期
										</td>
										<td width="15%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.openDateStart" styleClass="input3"
												maxlength="50"></html:text>
										</td>
										<td width="4%" height="15" align="center">
											至
										</td>
										<td width="14%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.openDateTimeEnd" styleClass="input3"
												maxlength="50"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1" height="15">
											汇缴总额
										</td>
										<td width="15%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.paySumStart" styleClass="input3"
												maxlength="50"></html:text>
										</td>
										<td width="4%" height="15" align="center">
											至
										</td>
										<td width="14%" height="15">
											<html:text name="orgCollInfoFindAF" property="dto.paySumEnd"
												styleClass="input3" maxlength="50"></html:text>
										</td>
										<td width="17%" class="td1" height="15">
											公积金余额
										</td>
										<td width="15%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.balanceStart" styleClass="input3"
												maxlength="50"></html:text>
										</td>
										<td width="4%" height="15" align="center">
											至
										</td>
										<td width="14%" height="15">
											<html:text name="orgCollInfoFindAF" property="dto.balanceEnd"
												styleClass="input3" maxlength="50"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1" height="15">
											挂账余额
										</td>
										<td width="15%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.overPayStart" styleClass="input3"
												maxlength="50"></html:text>
										</td>
										<td width="4%" height="15" align="center">
											至
										</td>
										<td width="14%" height="15">
											<html:text name="orgCollInfoFindAF" property="dto.overPayEnd"
												styleClass="input3" maxlength="50"></html:text>
										</td>
										<td width="17%" class="td1" height="15">
											单位缴至年月
										</td>
										<td width="15%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.orgPayMonthStart" styleClass="input3"
												maxlength="50"></html:text>
										</td>
										<td width="4%" height="15" align="center">
											至
										</td>
										<td width="14%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.orgPayMonthEnd" styleClass="input3"
												maxlength="50"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1" height="15">
											个人缴至年月
										</td>
										<td width="15%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.empPayMonthStart" styleClass="input3"
												maxlength="50"></html:text>
										</td>
										<td width="4%" height="15" align="center">
											至
										</td>
										<td width="14%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.empPayMonthEnd" styleClass="input3"
												maxlength="50"></html:text>
										</td>
										<td width="17%" class="td1" height="15">
											缴存人数
										</td>
										<td width="15%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.empCountStart" styleClass="input3"
												maxlength="50"></html:text>
										</td>
										<td width="4%" height="15" align="center">
											至
										</td>
										<td width="14%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.empCountEnd" styleClass="input3"
												maxlength="50"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1" height="15">
											工资总额
										</td>
										<td width="15%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.salarySumStart" styleClass="input3"
												maxlength="50"></html:text>
										</td>
										<td width="4%" height="15" align="center">
											至
										</td>
										<td width="14%" height="15">
											<html:text name="orgCollInfoFindAF"
												property="dto.salarySumEnd" styleClass="input3"
												maxlength="50"></html:text>
										</td>
										<td width="17%" class="td1" height="15">
											&nbsp;
										</td>
										<td width="33%" height="15" colspan="3" class=td7>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<br>
								<input type="button" class=buttona name="submit1" value="高级>>"
									onClick="gotoGaJi();" />
								<input type="submit" name="submit1" class=buttona value="查询" />
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/orgCollInfoMaintainAC">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计： 总人数
								<u>：<bean:write name="orgCollInfoFindAF"
										property="orgCollinfoSumDTO.personCount" /> 单位数 <u>：<bean:write
											name="pagination" property="nrOfElements" /> 缴存人数 <u>：<bean:write
												name="orgCollInfoFindAF" property="orgCollinfoSumDTO.empSum" />
									</u> 工资基数 <u>：<bean:write name="orgCollInfoFindAF"
												property="orgCollinfoSumDTO.salarySum" format="0.00" /> </u>
										单位缴额 <u>：<bean:write name="orgCollInfoFindAF"
												property="orgCollinfoSumDTO.orgPay" format="0.00" /> </u> 职工缴额
										<u>：<bean:write name="orgCollInfoFindAF"
												property="orgCollinfoSumDTO.empPay" format="0.00" /> </u> 汇缴总额
										<u>：<bean:write name="orgCollInfoFindAF"
												property="orgCollinfoSumDTO.paySum" format="0.00" /> </u> 公积金余额
										<u>：<bean:write name="orgCollInfoFindAF"
												property="orgCollinfoSumDTO.balance" format="0.00" /> </u> 挂账余额
										<u>：<bean:write name="orgCollInfoFindAF"
												property="orgCollinfoSumDTO.overPay" format="0.00" /> </u> 账面余额
										<u>：<bean:write name="orgCollInfoFindAF"
												property="orgCollinfoSumDTO.paybalance" format="0.00" /> </u>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">单 位 列 表 </b>
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
					<table width="95%" border="0" cellspacing="1" cellpadding="3"
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
								<a href="javascript:sort('a1.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy" value="a1.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('b1.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="b1.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('b1.officecode')">办事处名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="b1.officecode">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('b1.collection_bank_id')">归集银行</a>
								<logic:equal name="pagination" property="orderBy"
									value="b1.collection_bank_id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								单位性质
							</td>
							<td align="center" class=td2>
								缴存人数
							</td>
							<td align="center" class=td2>
								总人数
							</td>
							<td align="center" class=td2>
								工资基数
							</td>
							<td align="center" class=td2>
								职工缴率
							</td>
							<td align="center" class=td2>
								单位缴率
							</td>
							<td align="center" class=td2>
								单位缴额
							</td>
							<td align="center" class=td2>
								职工缴额
							</td>
							<td align="center" class=td2>
								汇缴总额
							</td>
							<td align="center" class=td2>
								公积金余额
							</td>
							<td align="center" class=td2>
								挂账余额
							</td>
							<td align="center" class=td2>
								账面余额
							</td>
							<td align="center" class=td2>
								单位缴至年月
							</td>
							<td align="center" class=td2>
								个人缴至年月
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('b1.open_date')">开户日期</a>
								<logic:equal name="pagination" property="orderBy"
									value="b1.open_date">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
						</tr>
						<logic:notEmpty name="orgCollInfoFindAF">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="orgCollInfoFindAF" property="list"
								id="resultdto" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onClick='gotoClickpp("<%=i%>", idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass%>">
									<td valign="top">
										<p align="center">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="resultdto" property="orgId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %> />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="orgId"
												format="0000000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="orgName" />
										</p>
									</td>

									<td valign="top">
										<p>
											<bean:write name="resultdto" property="officecode" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="collectionBankId" />
										</p>
									</td>

									<td valign="top">
										<p>
											<bean:write name="resultdto" property="character" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="empSum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="personCount" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="salarySum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="empRate" format="0.00" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="orgRate" format="0.00" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="orgPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="empPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="paySum" />
										</p>
									</td>

									<td valign="top">
										<p>
											<bean:write name="resultdto" property="balance" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="overPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="paybalance" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="orgPayMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="empPayMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="resultdto" property="openDate" />
										</p>
									</td>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="orgCollInfoFindAF" property="list">
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
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url" value="orgCollInfoShowAC.do" />
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
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<logic:notEmpty name="orgCollInfoFindAF" property="list">
											<td width="70">
												<input type="button" name="Submit2" value="职工信息"
													class="buttona" onclick="displayEmpFlow();">
											</td>
											<td width="70">
												<input type="button" name="Submit3" value="单位流水"
													class="buttona" onclick="skiporg();">
											</td>
											<td width="70">
												<input type="button" class="buttona" value="打印"
													onclick="print_Info();">
											</td>
										</logic:notEmpty>
										<logic:empty name="orgCollInfoFindAF" property="list">
											<td width="70">
												<input type="button" name="Submit2" value="职工信息"
													class="buttona" disabled="true">
											</td>
											<td width="70">
												<input type="button" name="Submit3" value="单位流水"
													class="buttona" disabled="true">
											</td>
											<td width="70">
												<input type="button" class="buttona" value="打印"
													disabled="true">
											</td>
										</logic:empty>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>

			</td>
		</tr>
	</table>
	<form action="orgCollInfoPrintAC.do" name="print_orgColl" method="POST"
		id="print_orgColl">
	</form>
</body>
</html:html>
