<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.action.ShowYearclearstatisticsListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowYearclearstatisticsListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>统计查询-结息信息-年终结息统计</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>

	<script type="text/javascript">
var s1="";
function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="凭证连打"){
				s1=i;
			}
		}
	}
	document.yearclearstatisticsListAF.officeCode.value="";
	document.yearclearstatisticsListAF.collectionBank.value="";
	document.yearclearstatisticsListAF.orgId.value="";
	document.yearclearstatisticsListAF.orgName.value="";
	document.yearclearstatisticsListAF.chgYearStart.value="";
	document.yearclearstatisticsListAF.isZero.value="";
}


function checkID(){
	var id=document.yearclearstatisticsListAF.orgId.value.trim();
	if(isNaN(id)){
		alert("请录入单位编号!!");
		document.yearclearstatisticsListAF.orgId.focus();
		return false;	
	}else{
		return true;
	}
}

function checkData(){    

	var officeCode=document.yearclearstatisticsListAF.officeCode.value.trim();
	var collectionBank=document.yearclearstatisticsListAF.collectionBank.value.trim();
	var orgId=document.yearclearstatisticsListAF.orgId.value.trim();
	var orgName = document.yearclearstatisticsListAF.orgName.value.trim();
	var chgDateStart=document.yearclearstatisticsListAF.chgYearStart.value.trim();
	var isZero = document.yearclearstatisticsListAF.isZero.value.trim();
	
   if(chgDateStart!=null&&chgDateStart!=""){
		if(!checkYear(chgDateStart)){
			document.yearclearstatisticsListAF.chgYearStart.focus();
			return false;
		}
	}
	
	
   if(chgDateStart.length==null || chgDateStart.length==0){
			alert("请录入结息年");
			document.yearclearstatisticsListAF.chgYearStart.focus();
			return false;
	}  
	
}
function continuum(){
	var c_sun = "<bean:write name="pagination" property="nrOfElements" />";
	if(eval(c_sun)>=255){
		var beg = document.all.beg.value;
		var end = document.all.end.value;
		if(beg!=null && end!=null && beg!="" && end!=""){
			return true;
		}else{
			window.open('<%=path%>/syscollection/querystatistics/clearinterestinfo/yearclearstatistics/yearclearstatisticsPop.jsp','','width=500,height=100,top=200,left=300,scrollbars=no');
			return false;
		}
	}else{
		return true;
	}
}
function submit_yg(){
	var beg = document.all.beg.value.trim();
	var end = document.all.end.value.trim();
	if(beg!="" && end!=""){
		document.all.item(s1).click();
	}else{
		return false;
	}
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" onload="return loads();">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="showYearclearstatisticsListAC.do" />
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
										<font color="00B5DB">统计查询&gt;年终结息</font>
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

				<html:form action="/findYearclearstatisticsListAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
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
								单位编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="yearclearstatisticsListAF" property="orgId"
									onblur="return checkID();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%">
								<html:text name="yearclearstatisticsListAF" property="orgName"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								发生年
							</td>
							<td width="15%" colspan="3">
								<html:text name="yearclearstatisticsListAF"
									property="chgYearStart" styleClass="input3" styleId="txtsearch" maxlength="4"></html:text>
							</td>
							<td width="11%" class="td1">
								余额大于0
							</td>
							<td width="21%" colspan="2">
								<html:select property="isZero" styleClass="input4"
									name="yearclearstatisticsListAF" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<option value="0">
										是
									</option>
									<option value="1">
										否
									</option>
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




				<html:form action="/maintainYearclearstatisticsListAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">

						<tr>
							<td class=h4>
								合计：单位数:
								<u><bean:write name="yearclearstatisticsHeadDTO"
										property="orgcounts" />&nbsp;</u> 人数:
								<u><bean:write name="yearclearstatisticsHeadDTO"
										property="empcounts" />&nbsp;</u> 结息前往年余额:
								<u><bean:write name="yearclearstatisticsHeadDTO"
										property="oldpreblance" />&nbsp;</u> 结息前本年余额:
								<u><bean:write name="yearclearstatisticsHeadDTO"
										property="oldcurblance" />&nbsp;</u> 往年利息:
								<u><bean:write name="yearclearstatisticsHeadDTO"
										property="preinterest" />&nbsp;</u> 本年利息:
								<u><bean:write name="yearclearstatisticsHeadDTO"
										property="curinterest" />&nbsp;</u> 往年余额:
								<u><bean:write name="yearclearstatisticsHeadDTO"
										property="preblance" />&nbsp;</u> 本年余额:
								<u><bean:write name="yearclearstatisticsHeadDTO"
										property="curblance" />&nbsp;</u> 余额:
								<u><bean:write name="yearclearstatisticsHeadDTO"
										property="blance" />&nbsp;</u>
							</td>
						</tr>
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="180">
											<b class="font14">单位结息列表</b>
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
					<input type="hidden" name="beg"/>
					<input type="hidden" name="end"/>
					<table width="95%" border="0" cellspacing="1" cellpadding="3"
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
								<a href="javascript:sort('aa001.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="aa001.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('ba001.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="ba001.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('count(aa318.id)')">人数</a>
								<logic:equal name="pagination" property="orderBy"
									value="count(aa318.id)">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								结息前往年余额
							</td>
							<td align="center" class=td2>
								结息前本年余额
							</td>
							<td align="center" class=td2>
								往年利息
							</td>
							<td align="center" class=td2>
								本年利息
							</td>
							<td align="center" class=td2>
								结息后往年余额
							</td>
							<td align="center" class=td2>
								结息后本年余额
							</td>
							<td align="center" class=td2>
								余额
							</td>
						</tr>

						<logic:notEmpty name="yearclearstatisticsListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>

							<logic:iterate id="element" name="yearclearstatisticsListAF"
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
											value="<bean:write name="element" property="set_headid"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="element" property="orgcode"
											format="0000000000" />
									</td>
									<td>
										<a
											href="yearclearEmpListForwardURLAC.do?headid=<bean:write name="element" property="set_headid" />&orgid=<bean:write name="element" property="orgcode" />&money=<bean:write name="element" property="blance" />"><bean:write
												name="element" property="orgname" /> </a>
									</td>
									<td>
										<bean:write name="element" property="empcounts" />
									</td>
									<td>
										<bean:write name="element" property="oldpreblance" />
									</td>
									<td>
										<bean:write name="element" property="oldcurblance" />
									</td>
									<td>
										<bean:write name="element" property="preinterest" />
									</td>
									<td>
										<bean:write name="element" property="curinterest" />
									</td>
									<td>
										<bean:write name="element" property="preblance" />
									</td>
									<td>
										<bean:write name="element" property="curblance" />
									</td>
									<td>
										<bean:write name="element" property="blance" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="yearclearstatisticsListAF" property="list">
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
												<jsp:param name="url"
													value="showYearclearstatisticsListAC.do" />
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

								<logic:notEmpty name="yearclearstatisticsListAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
												&nbsp;
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print.doc" />
												</html:submit>
												&nbsp;
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona" onclick="return continuum();">
													<bean:message key="button.continuum.print" />
												</html:submit>
												&nbsp;
											</td>
										</tr>
									</table>
								</logic:notEmpty>

								<logic:empty name="yearclearstatisticsListAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
												&nbsp;
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print.doc" />
												</html:submit>
												&nbsp;
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.continuum.print" />
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
