<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action.ShowSearchLackInfoListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowSearchLackInfoListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询-缴存提取统计-欠缴信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js"></script>

	<script type="text/javascript">
function loads(){

	document.searchLackInfoListAF.officeCode.value="";
	document.searchLackInfoListAF.collectionBank.value="";
	document.searchLackInfoListAF.natureOfUnits.value="";
	document.searchLackInfoListAF.authorities.value="";
	document.searchLackInfoListAF.orgId.value="";
	document.searchLackInfoListAF.orgName.value="";
	document.searchLackInfoListAF.chgMonthStart.value="";
	document.searchLackInfoListAF.chgMonthEnd.value="";

}

function gotoCreat(){
	document.location.href='createSearchLackInfoAC.do';
}
function checkID(){
	var id=document.searchLackInfoListAF.orgId.value.trim();
	if(isNaN(id)){
		alert("请录入单位编号!!");
		document.searchLackInfoListAF.orgId.focus();
		return false;	
	}else{
		return true;
	}
}
function checkData(){
	var officeCode=document.searchLackInfoListAF.officeCode.value.trim();
	var collectionBank=document.searchLackInfoListAF.collectionBank.value.trim();
	var chgMonthStart = document.searchLackInfoListAF.natureOfUnits.value.trim();
	var chgMonthEnd=document.searchLackInfoListAF.authorities.value.trim();
	var orgId=document.searchLackInfoListAF.orgId.value.trim();
	var orgName = document.searchLackInfoListAF.orgName.value.trim();
	var chgDateStart=document.searchLackInfoListAF.chgMonthStart.value.trim();
	var chgDateEnd = document.searchLackInfoListAF.chgMonthEnd.value.trim();
	
   if(chgDateStart!=null&&chgDateStart!=""){
		if(isNaN(chgDateStart)){
			alert("欠缴月数录入不正确!!");
			document.searchLackInfoListAF.chgMonthStart.focus();
			return false;
		}
	}
	
   if(chgDateEnd!=null&&chgDateEnd!=""){
		if(isNaN(chgDateEnd)){
			alert("欠缴月数录入不正确!!");
			document.searchLackInfoListAF.chgMonthEnd.focus();
			return false;
		}
	}    
	
   if((chgDateStart.length!=0&&chgDateEnd.length==0)||(chgDateStart.length==0&&chgDateEnd.length!=0)){
			alert("欠缴月数缺少录入项!!");
			if(chgDateEnd==null&&chgDateEnd==""){
				document.searchLackInfoListAF.chgMonthEnd.focus();
			}else{
				document.searchLackInfoListAF.chgMonthStart.focus();
			}
			return false;
	}
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="return loads();">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="showSearchLackInfoListAC.do" />
	</jsp:include>

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>

				<table width="1800" border="0" cellspacing="0" cellpadding="0">
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
										<font color="00B5DB">统计查询&gt;欠缴信息</font>
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

				<html:form action="/findSearchLackInfoListAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td>
								<table border="0" width="55%" id="table1" cellspacing=1
									cellpadding=3 align="left">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
											align="center" colspan="5">
											&nbsp;
										</td>
									</tr>
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
										<td width="33%">
											<html:select property="collectionBank" styleClass="input4">
												<html:option value=""></html:option>
												<html:options collection="bankList1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											单位性质
										</td>
										<td width="33%" colspan="3">
											<html:select property="natureOfUnits" styleClass="input4"
												onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:optionsCollection property="natureOfUnits_1"
													name="searchLackInfoListAF" label="value" value="key" />
											</html:select>
										</td>
										<td width="17%" class="td1">
											主管部门
										</td>
										<td width="33%">
											<html:select property="authorities" styleClass="input4"
												onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:optionsCollection property="authorities_1"
													name="searchLackInfoListAF" label="value" value="key" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											欠缴月数
										</td>
										<td width="15%">
											<html:text name="searchLackInfoListAF"
												property="chgMonthStart" styleClass="input3"
												styleId="txtsearch"></html:text>
										</td>
										<td width="4%">
											至
										</td>
										<td width="14%">
											<html:text name="searchLackInfoListAF" property="chgMonthEnd"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td width="17%" class="td1">
											单位编号
										</td>
										<td width="33%" colspan="3">
											<html:text name="searchLackInfoListAF" property="orgId"
												onblur="return checkID();" styleClass="input3"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											单位缴率
										</td>
										<td width="15%">
											<html:text name="searchLackInfoListAF" property="orgratebeg"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td width="4%">
											至
										</td>
										<td width="14%">
											<html:text name="searchLackInfoListAF" property="orgrateend"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td width="17%" class="td1">
											单位名称
										</td>
										<td width="33%">
											<html:text name="searchLackInfoListAF" property="orgName"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											职工缴率
										</td>
										<td width="15%">
											<html:text name="searchLackInfoListAF" property="empratebeg"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td width="4%">
											至
										</td>
										<td width="14%">
											<html:text name="searchLackInfoListAF" property="emprateend"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td width="17%" class="td1">
											欠缴年月
										</td>
										<td width="33%" colspan="3">
											<html:text name="searchLackInfoListAF" property="yearMonth"
												styleClass="input3" styleId="txtsearch" maxlength="6"></html:text>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td>
								<table width="55%" border="0" align="left" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="right">

										</td>
										<td align="right">
											<input type="button" class="buttonb" onclick="gotoCreat();"
												value="生成欠缴数据">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkData();">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>

				<html:form action="/maintainSearchLackInfoListAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">

						<tr>
							<td class=h4>
								合计：欠单位缴额:
								<u><bean:write name="searchLackInfoHeadDTO"
										property="orgpayLack" />&nbsp;</u> 欠职工缴额:
								<u><bean:write name="searchLackInfoHeadDTO"
										property="emppayLack" />&nbsp;</u> 欠缴金额:
								<u><bean:write name="searchLackInfoHeadDTO"
										property="sumpayLack" />&nbsp;</u>
							</td>
						</tr>
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="180">
											<b class="font14">欠缴信息列表</b>
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
								<a href="javascript:sort('org2_.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="aa305.org_id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orginfo3_.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="ba001.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								欠缴人数
							</td>
							<td align="center" class=td2>
								单位缴存比例
							</td>
							<td align="center" class=td2>
								职工缴存比例
							</td>
							<td align="center" class=td2>
								工资基数
							</td>
							<td align="center" class=td2>
								单位欠缴额
							</td>
							<td align="center" class=td2>
								职工欠缴额
							</td>
							<td align="center" class=td2>
								欠缴金额
							</td>
							<td align="center" class=td2>
								单位欠缴月数
							</td>
							<td align="center" class=td2>
								职工欠缴月数
							</td>
							<td align="center" class=td2>
								单位缴至年月
							</td>
							<td align="center" class=td2>
								职工缴至年月
							</td>
							<td align="center" class=td2>
								单位余额
							</td>
							<td align="center" class=td2>
								月缴存额
							</td>
							<td align="center" class=td2>
								单位性质
							</td>
							<td align="center" class=td2>
								单位经办人
							</td>
							<td align="center" class=td2>
								单位电话
							</td>
							<td align="center" class=td2>
								单位地址
							</td>
						</tr>

						<logic:notEmpty name="searchLackInfoListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="searchLackInfoListAF"
								property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="element" property="orgcode"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="element" property="orgcode"
											format="0000000000" />
									</td>
									<td>
										<bean:write name="element" property="orgname" />
									</td>
									<td>
										<bean:write name="element" property="zcjcrs" />
									</td>
									<td>
										<bean:write name="element" property="orgRate" />
									</td>
									<td>
										<bean:write name="element" property="empRate" />
									</td>
									<td>
										<bean:write name="element" property="salaryBase" />
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
										<bean:write name="element" property="orgMonth" />
									</td>
									<td>
										<bean:write name="element" property="empMonth" />
									</td>
									<td>
										<bean:write name="element" property="orgPayMonth" />
									</td>
									<td>
										<bean:write name="element" property="empPayMonth" />
									</td>
									<td>
										<bean:write name="element" property="orgBalance" />
									</td>
									<td>
										<bean:write name="element" property="sumPay_1" />
									</td>
									<td>
										<bean:write name="element" property="character" />
									</td>
									<td>
										<bean:write name="element" property="transactor_name" />
									</td>
									<td>
										<bean:write name="element" property="tel" />
									</td>
									<td>
										<bean:write name="element" property="address" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="searchLackInfoListAF" property="list">
							<tr>
								<td colspan="19" height="30" style="color:red">
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
										<td align="left">
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url" value="showSearchLackInfoListAC.do" />
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

								<logic:notEmpty name="searchLackInfoListAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70" align="left">
												<html:submit property="method" styleClass="buttonc">
													<bean:message key="button.print.tzd" />
												</html:submit>
												&nbsp;
											</td>
											<td width="70" align="left">
												<html:submit property="method" styleClass="buttonb">
													<bean:message key="button.printList" />
												</html:submit>
												&nbsp;
											</td>
										</tr>
									</table>
								</logic:notEmpty>

								<logic:empty name="searchLackInfoListAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttonc"
													disabled="true">
													<bean:message key="button.print.tzd" />
												</html:submit>
												&nbsp;
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttonb"
													disabled="true">
													<bean:message key="button.printList" />
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
