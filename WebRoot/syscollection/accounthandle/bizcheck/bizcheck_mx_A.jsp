<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.bizcheck.action.BizcheckTaWindowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Object pagination = request.getSession(false).getAttribute(
			BizcheckTaWindowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<script language="javascript" type="text/javascript">
function loads(){	
	var orgid=document.bizcheckDetailAF.traninId.value;
	orgid=formatTen(orgid)+orgid;
	document.bizcheckDetailAF.traninId.value=orgid;

}
function executeAjax() {
     var queryString = "bizcheckTaRemotePrintAAC.do";
	 findInfo(queryString);
}
function display(headid,url,type){
	document.URL='<%=path%>/syscollection/paymng/monthpay/monthpayTbWindowMaintainAC.do?headid='+headid+'&url=<%=path%>'+url+'';
}
</script>
<html>
	<head>
		<title>扎账</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	</head>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<body bgcolor="#FFFFFF" text="#000000" onload="loads()"
		onContextmenu="return false">

		<jsp:include page="../../../inc/sort.jsp">
			<jsp:param name="url" value="bizcheckTaWindowAC.do" />
		</jsp:include>
		<html:form action="/bizcheckTaWindowPrintAC.do">
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
								<td width="235" background="<%=path%>/img/table_bg_line.gif"></td>
								<td background="<%=path%>/img/table_bg_line.gif" align="right">
									<table width="300" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="37">
												<img src="<%=path%>/img/title_banner.gif" width="37"
													height="24">
											</td>
											<td class=font14 bgcolor="#FFFFFF" align="center"
												valign="middle">
												<font color="00B5DB">业务复核&gt;业务明细</font>
											</td>
											<td width="15" class=font14>
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

						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="117">
												<strong>扎账信息</strong>
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
								<html:hidden name="bizcheckDetailAF" property="headid" />
								<td width="17%" class="td1">
									结算号
								</td>
								<td width="33%">
									<html:text name="bizcheckDetailAF" property="noteNum"
										styleClass="input3" readonly="true" />
								<td width="17%" class="td1">
									凭证编号
								</td>
								<td width="33%">
									<html:text name="bizcheckDetailAF" property="docNum"
										styleClass="input3" readonly="true" />
							</tr>
							<tr>
								<td width="17%" class="td1">
									制单人
								</td>
								<td width="33%">
									<html:text name="bizcheckDetailAF" property="operator"
										styleClass="input3" readonly="true" />
								</td>
								<td width="17%" class=td1>
									归集银行
								</td>
								<td width="33%">
									<html:text name="bizcheckDetailAF" property="bank"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									单位编号
								</td>
								<td width="33%">
									<html:text name="bizcheckDetailAF" property="traninId"
										styleClass="input3" readonly="true" />
								</td>
								<td width="17%" class=td1>
									单位名称
								</td>
								<td width="33%">
									<html:text name="bizcheckDetailAF" property="traninName"
										styleClass="input3" readonly="true" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									业务类型
								</td>
								<td width="33%">
									<html:text name="bizcheckDetailAF" property="biz_type"
										styleClass="input3" readonly="true" />
								</td>
								<td width="17%" class="td1">
									缴存日期
								</td>
								<td width="33%">
									<html:text name="bizcheckDetailAF" property="settDate"
										styleClass="input3" readonly="true" />
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
												<b class="font14">职工信息列表 </b>
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


						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr bgcolor="1BA5FF">
								<td align="center" height="6" colspan="6"></td>
							</tr>
							<tr>
								<td height="23" align="center" bgcolor="C4F0FF">
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
									证件号码
								</td>
								<td align="center" class=td2>
									发生金额
								</td>
								<td align="center" class=td2>
									利息
								</td>
							</tr>

							<logic:notEmpty name="bizcheckDetailAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate name="bizcheckDetailAF" property="list"
									id="elements" indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr id="tr<%=i%>" class="<%=strClass%>">
										<td valign="top">
											<p>
												<bean:write name="elements" property="empId" format="000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="empName" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="emp.empInfo.cardNum" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="money" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elements" property="interest" />
											</p>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>

							<logic:empty name="bizcheckDetailAF" property="list">
								<tr>
									<td colspan="11" height="30" style="color:red">
										没有找到与条件相符合结果！
									</td>
								</tr>
								<tr>
									<td colspan="11" class=td5></td>
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
													<jsp:param name="url" value="bizcheckTaWindowAC.do" />
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
									<table width="156" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<!-- 
                 <td width="72">
			  		<input type="button" name="Submit42" value="打印" class="buttona" onclick="executeAjax();">                 
			  	 </td> -->
											<td width="99">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

		</html:form>
	</body>
</html>
