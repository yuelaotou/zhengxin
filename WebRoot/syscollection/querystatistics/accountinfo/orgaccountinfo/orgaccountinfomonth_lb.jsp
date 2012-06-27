<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.accountinfo.orgaccountinfo.action.OrgAccountInfoTbShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			OrgAccountInfoTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>统计查询>>单位账</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
	<script type="text/javascript">
var tr="tr0"; 

function loads(){	
	
//	document.orgAccountInfoAF.inOpDate.value="";
//	document.orgAccountInfoAF.opDate.value="";
	document.orgAccountInfoAF.officecode.value="";
	document.orgAccountInfoAF.collBankId.value="";
	document.orgAccountInfoAF.orgid.value="";
	document.orgAccountInfoAF.orgname.value="";
		
}
function gotoSearch(){
	var d1=document.orgAccountInfoAF.inOpDate.value;
	var d2=document.orgAccountInfoAF.opDate.value;

	if(d1=="" || d2==""){
		alert("请输入发生日期!");
		document.orgAccountInfoAF.inOpDate.focus();
		return false;
	}
}
function update1() {
  	var status=document.getElementById(tr).childNodes[14].childNodes[0].innerHTML;
	return status;
}
function gotoSearchMonth(trindex){
  	tr=trindex;
	var id=getCheckId();
	var opTime=update1();
	var mode=document.all.mode.value;
	document.location.href="orgAccountInfoTcForwardURLAC.do?id="+id+"&mode="+mode+"&opTime="+opTime;
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="loads();">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="orgAccountInfoTbShowAC.do" />
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
										<font color="00B5DB">统计查询<font color="00B5DB">&gt;</font>单位账</font>
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
				<html:form action="/orgAccountInfoTaFindAC">
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
								查询方式
							</td>
							<td width="33%" colspan="2" class=td7>
								<html:radio name="orgAccountInfoAF" property="mode" value="1">办事处</html:radio>
								<html:radio name="orgAccountInfoAF" property="mode" value="2">存款银行</html:radio>
								<html:radio name="orgAccountInfoAF" property="mode" value="3">单位</html:radio>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								办事处
							</td>
							<td width="33%" colspan="3">
								<html:select property="officecode" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:options collection="officelist1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								存款银行
							</td>
							<td width="33%" colspan="2">
								<html:select property="collBankId" styleClass="input4"
									onkeydown="enterNextFocus1();">
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
								<html:text property="orgid" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								单位名称
							</td>
							<td width="33%" colspan="2">
								<html:text property="orgname" styleClass="input3" maxlength="50" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return gotoSearch()">
									<bean:message key="button.search" />
								</html:submit>
						</tr>
					</table>
				</html:form>
				<html:form action="/orgAccountInfoTbMaintainAC">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计： 发生额
								<u>：<bean:write name="orgAccountInfoTotalDTO"
										property="sumMoney" />
								</u> 利息
								<u>：<bean:write name="orgAccountInfoTotalDTO"
										property="sumInterest" />
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="142">
											<b class="font14">总账查询</b>
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
							<td align="center" height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class="td2">
								办事处
							</td>
							<td align="center" class="td2">
								存款银行
							</td>
							<td align="center" class="td2">
								单位编号
							</td>
							<td align="center" class="td2">
								单位名称
							</td>
							<td align="center" class=td2>
								期初余额
							</td>
							<td align="center" class=td2>
								本期借方发生额
							</td>
							<td align="center" class=td2>
								本期贷方发生额
							</td>
							<td align="center" class=td2>
								本期借方笔数
							</td>
							<td align="center" class=td2>
								本期贷方笔数
							</td>
							<td align="center" class=td2>
								挂账金额
							</td>
							<td align="center" class=td2>
								期末余额
							</td>
							<td align="center" class=td2>
								挂账余额
							</td>
							<td align="center" class=td2>
								账面余额
							</td>
							<td align="center" class=td2>
								查询时间
							</td>
						</tr>
						<logic:notEmpty name="LIST">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="LIST" id="element" indexId="i">

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
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass %>"
									onDblClick="gotoSearchMonth('tr<%=i%>');">

									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="officename" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="collbankname" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="orgid"
												format="0000000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="orgname" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="prebalance" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="temp_debit" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="temp_credit" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="countDebit" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="countCredit" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="orgOverMoney" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="curbalance" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="orgOverPaybalance" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="balance" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="opTime" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="LIST">
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
												<jsp:param name="url" value="orgAccountInfoTbShowAC.do" />
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

											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
											</html:submit>
										</td>

										<td width="70">
											<input type="button" name="Submit4" value="返回"
												class="buttona"
												onclick="location.href='orgAccountInfoTaShowAC.do'">
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
