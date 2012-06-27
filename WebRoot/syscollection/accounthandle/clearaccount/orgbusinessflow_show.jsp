<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.clearaccount.action.ShowOrgbusinessflowListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowOrgbusinessflowListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询-业务流水-单位业务流水</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js"></script>

	<script type="text/javascript">

function loads(){
}
function checkID(){
	var orgid=document.orgbusinessflowAF.orgcode.value.trim();
	if(isNaN(orgid)){
		alert("请录入单位编号!!");
		document.orgbusinessflowAF.orgcode.focus();
		return false;	
	}else{
		return true;
	}
}
function gotoQuery(){
	var date1=document.all.setMonthStart.value.trim();
	var date2=document.all.setMonthEnd.value.trim();
	var money1=document.all.setMoneyStart.value.trim();
	var money2=document.all.setMoneyEnd.value.trim();
	if(date1=="" && date2==""){
		alert("必须输入查询日期！");
		document.all.setMonthStart.focus();
		return false;
	}else{
		if(date1!=""){
			if(!checkDate(document.all.setMonthStart)){
				document.all.setMonthStart.value="";
				return false;
			}
		}
		if(date2!=""){
			if(!checkDate(document.all.setMonthEnd)){
				document.all.setMonthEnd.value="";
				return false;
			}
		}
	    if(money1 != ""){
			var str=checkMoney(money1);
			if(!str){
				document.all.setMoneyStart.focus();
				return false;
			}
		}
		if(money2 != ""){
			var str=checkMoney(money2);
			if(!str){
				document.all.setMoneyEnd.focus();
				return false;
			}
		}
	}
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="return loads();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="showOrgbusinessflowListAC.do" />
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
										<font color="00B5DB">统计查询</font><font color="00B5DB">&gt;单位流水</font>
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
				<html:form action="/findOrgbusinessflowListAC.do">
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
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="orgbusinessflowAF" property="orgcode"
									onblur="return checkID();" styleClass="input3"
									styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%" colspan="3">
								<html:text name="orgbusinessflowAF" property="orgname"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								结算号
							</td>
							<td width="33%" colspan="3">
								<html:text name="orgbusinessflowAF" property="notenum"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								凭证编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="orgbusinessflowAF" property="docnum"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								发生日期
							</td>
							<td width="15%">
								<html:text name="orgbusinessflowAF" property="setMonthStart"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="4%">
								至
							</td>
							<td width="14%">
								<html:text name="orgbusinessflowAF" property="setMonthEnd"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
							<td width="17%" class="td1">
								业务类型
							</td>
							<td width="33%" colspan="3">
								<html:select property="bstype" styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="bstypeMap"
										name="orgbusinessflowAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="5"
						cellspacing="1">
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
				<html:form action="/maintainOrgbusinessflowListAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：人数:
								<u><bean:write name="orgbusinessflowHeadDTO"
										property="count" /> &nbsp;</u> 贷方发生额:
								<u><bean:write name="orgbusinessflowHeadDTO"
										property="desumPay" /> &nbsp;</u> 借方发生额:
								<u><bean:write name="orgbusinessflowHeadDTO"
										property="cdsumPay" /> &nbsp;</u> 利息:
								<u><bean:write name="orgbusinessflowHeadDTO"
										property="sumInterestPay" /> &nbsp;</u>
							</td>
						</tr>
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="180">
											<b class="font14">单位流水列表</b>
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
								<a href="javascript:sort('orgHAFAccountFlow.docNum')">凭证号</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgHAFAccountFlow.docNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgHAFAccountFlow.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgHAFAccountFlow.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgHAFAccountFlow.biz_Type')">业务类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgHAFAccountFlow.biz_Type">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgHAFAccountFlow.moneyTotal')">业务金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgHAFAccountFlow.moneyTotal">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								销户利息
							</td>
							<td align="center" class=td2>
								制单人
							</td>
							<td align="center" class=td2>
								操作员复核
							</td>
							<td align="center" class=td2>
								记账人
							</td>
						</tr>
						<logic:notEmpty name="orgbusinessflowAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="orgbusinessflowAF"
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
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<bean:write name="element" property="docNum" />
									</td>
									<td>
										<a href="#"
											onclick="window.open('<%=request.getContextPath()%>/syscollection/accounthandle/clearaccount/empOperationFlowTaForwardURLAC.do?orgbusinessflowHeadID=<bean:write name="element" property="id"/>','window','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,resizable=yes,location=no, status=no')"><bean:write
												name="element" property="org.orgInfo.name" /> </a>
									</td>
									<td>
										<bean:write name="element" property="bis_type" />
									</td>
									<td align="right">
										<bean:write name="element" property="moneyTotal" format="0.00" />
									</td>
									<td align="right">
										<bean:write name="element" property="interest" />
									</td>
									<td>
										<bean:write name="element" property="reserveaA_" />
									</td>
									<td>
										<bean:write name="element" property="checkPersonStr" />
									</td>
									<td>
										<bean:write name="element" property="clearPersonStr" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="orgbusinessflowAF" property="list">
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
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="showOrgbusinessflowListAC.do" />
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
								<logic:notEmpty name="orgbusinessflowAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</tr>
									</table>
								</logic:notEmpty>
								<logic:empty name="orgbusinessflowAF" property="list">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
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
