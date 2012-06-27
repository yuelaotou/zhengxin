<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninListAAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ShowTraninListAAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<html:html>
<head>
	<title>tranin</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	<script language="javascript" src="js/common.js">
</head>
<script language="javascript" ></script>
	<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
var s6="";
var s7="";
function loads(){
	var orgid=document.all.inOrgId.value;
	if(orgid!=""){
	document.all.inOrgId.value=format(document.all.inOrgId.value)+document.all.inOrgId.value;
	}
	var outorgId=document.all.outOrgId.value;
	if(outorgId!=""){
	document.all.outOrgId.value=format(document.all.outOrgId.value)+document.all.outOrgId.value;
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	return true;
}
function gotoPrint(){
		document.all.report.value="print";
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors(); "
	onContextmenu="return true">

	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="showTraninListAAC.do" />
	</jsp:include>
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
									<td width="350" background="<%=path%>/img/table_bg_line.gif">
									<td background="<%=path%>/img/table_bg_line.gif" align="right">
										<table width="300" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="37">
													<img src="<%=path%>/img/title_banner.gif" width="37"
														height="24">
												</td>
												<td width="189" class=font14 bgcolor="#FFFFFF"
													align="center" valign="bottom">
													<font color="00B5DB">转入信息</font><font color="00B5DB">&gt;&gt;</font><font color="00B5DB">办理转入列表</font>
												</td>
												<td width="74" class=font14>
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
													<b class="font14">转 入 信 息</b>
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
											<td width="14%" class="td1" algin="center">
												转入单位编号
											</td>
											<td width="18%">
												<html:text name="traninAF" property="inOrgId"
													styleClass="input3" styleId="txtsearch" 
													readonly="true">
												</html:text>
											</td>
											<td class="td1" width="17%" algin="center">
												转入单位名称
											</td>
											<td width="22%">
												<html:text name="traninAF" property="inOrgName"
													styleClass="input3" styleId="txtsearch" readonly="true"></html:text>
											</td>
										</tr>
										<tr>
											<td class="td1" width="17%" algin="center">
												结算号
											</td>
											<td width="22%">
												<html:text name="traninAF" property="noteNum"
												 styleClass="input3" readonly="true"
													styleId="txtsearch"></html:text>
											</td>
											<td class="td1" width="17%" algin="center">
												凭证编号
											</td>
											<td width="22%">
												<html:text name="traninAF" property="docNum"
												 styleClass="input3" readonly="true"
													styleId="txtsearch"></html:text>
											</td>
										</tr>
										<tr>
											<td width="14%" class="td1" algin="center">
												转出单位编号
											</td>
											<td width="18%">
												<html:text name="traninAF" property="outOrgId"
													styleClass="input3" styleId="txtsearch" 
													readonly="true">
												</html:text>
											</td>
											<td class="td1" width="17%" algin="center">
												转出单位名称
											</td>
											<td width="22%">
												<html:text name="traninAF" property="outOrgName"
													styleClass="input3" styleId="txtsearch" readonly="true"></html:text>
											</td>
										</tr>
									</table>
								<table border="0" width="95%" id="table1" align="center"
									border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class=h4>
											合计：转入人数
											<u>：<bean:write name="traninAF" property="traninPeople" />
											</u> 转入金额
											<u>：<bean:write name="traninAF" property="sumBalanceAll" />
											</u> 利息
											<u>：<bean:write name="traninAF" property="sumInterestAll" />
											</u>转入总额
											<u>：<bean:write name="traninAF" property="sumTraninAll" />
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
													<b class="font14">办理转入列表</b>
												</td>
												<td width="750" height="22" align="center"
													background="<%=path%>/img/bg2.gif">
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
							<html:form action="/showTraninListAAC.do" style="margin: 0">		
								<input type="hidden" name="report" value="">
								<table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr>
										<td height="23" align="center" bgcolor="C4F0FF">
											&nbsp;
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranInTail.empId')">职工编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranInTail.empId">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranInTail.name')">职工姓名</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranInTail.name">
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
											转入金额
										</td>
										<td align="center" class=td2>
											利息
										</td>
										<td align="center" class=td2>
											转入总额
										</td>
										<td align="center" class=td2>
											转出职工编号
										</td>
									</tr>
									<logic:notEmpty name="traninAF" property="list">
										<logic:iterate name="traninAF" property="list" id="element"
											indexId="i">

											<tr id="tr<%=i%>"
												onclick='gotoClick("tr<%=i%>","s<%=i%>", traninAF);'
												onMouseOver='this.style.background="#eaeaea"'
												onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", traninAF);'
												class=td4 onDblClick="">
												<td valign="top">
													<p align="left">
														<input id="s<%=i%>" type="radio" name="id"
															value="<bean:write name="element" property="id"/>"
															<%if(new Integer(0).equals(i)) out.print("checked"); %>>
													</p>
												</td>
												<td valign="top">
													
													<logic:equal name="element" property="empId" value="0">
														<p></p>
													</logic:equal>
													<logic:notEqual name="element" property="empId" value="0">
														<p><bean:write name="element" property="empId" format="000000"
															/></p>
													</logic:notEqual>
													
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="name" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="cardNum" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="sumBalance" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="sumInterest" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="traninAmount" />
													</p>
												</td>
												<td valign="top">
													<logic:equal name="element" property="tranOutEmpId" value="0">
														<p><bean:write name="element" property="tranOutEmpId"
															/></p>
													</logic:equal>
													<logic:notEqual name="element" property="tranOutEmpId" value="0">
														<p><bean:write name="element" property="tranOutEmpId" format="000000"
															/></p>
													</logic:notEqual>
												</td>
											</tr>
											<tr>
												<td colspan="8" class=td5></td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="traninAF" property="list">
										<tr>
											<td colspan="7" height="30" style="color:red">
												没有找到与条件相符合结果！
											</td>
										</tr>
										<tr>
											<td colspan="7"></td>
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
															<jsp:param name="url" value="showTraninListAAC.do" />
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
												<!--  
												<td><html:submit property="method" styleClass="buttona" onclick="gotoPrint()">
															<bean:message key="button.print" />
														</html:submit></td>
														&nbsp;&nbsp;-->
                                                <td><html:button  styleClass="buttona" property="button1" onclick="javascript:window.close();" value="关闭"></html:button>
												</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</html:form>
							<form action="showTraninListAAC.do" method="POST" name="Form1"
								id="Form1">
							</form>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>
