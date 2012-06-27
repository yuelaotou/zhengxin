
<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickupreason.action.ShowPickupreasonListAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ShowPickupreasonListAC.PAGINATION_KEY);
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
function loads(){
var	orgId=document.pickupreasonAF.orgid.value.trim();
if(orgId!=null&&orgId!=''){
document.pickupreasonAF.orgid.value=(format(orgId)+""+orgId);
}
}
function checkNumber(){
var	orgId=document.pickupreasonAF.orgid.value.trim();
var salarybase = orgId.match(/^([0-9]{1,10})?$/)
if (salarybase==null)
			{	
        		alert("请正确录入单位编号！格式如：123456");
        		document.pickupreasonAF.orgid.focus();
				return false;
			}else{
				return true;
			}

} 
function checkdate(){
var date=document.pickupreasonAF.date.value.trim();
    if(date!=""){
        checkDate(document.pickupreasonAF.date);
      }	
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads(); reportErrors();"
	onContextmenu="return false">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="showPickupreasonListAC.do" />
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
													<img src="<%=path%>/img/title_banner.gif" width="35"
														height="24">
												</td>
												<td width="200" class=font14 bgcolor="#FFFFFF"
													align="center" valign="bottom">
													<font color="00B5DB">统计查询</font><font color="00B5DB">&gt;&gt;</font><font
														color="00B5DB">提取情况按原因统计</font>
												</td>
												<td width="60" class=font14>
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
							<html:form action="/findPickupreasonListAC.do">
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="117">
														<b class="font14">查 询 条 件</b>
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
										<td class="td1" width="17%" algin="center">
											办事处
										</td>
										<td width="33%">
											<html:select name="pickupreasonAF" property="officeid"
												styleClass="input4" onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:options collection="officelist1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
										<td class="td1" width="17%" algin="center">
											归集银行
										</td>
										<td width="33%">
											<html:select name="pickupreasonAF" property="bankid"
												styleClass="input4" onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:options collection="bankList1" property="value"
													labelProperty="label" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											单位编号
										</td>
										<td width="33%">
											<html:text name="pickupreasonAF" property="orgid"
												styleClass="input3" onkeydown="goEnter();"
												onblur="return checkNumber();" styleId="txtsearch"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											发生日期
										</td>
										<td width="33%">
											<html:text name="pickupreasonAF" property="date"
												styleClass="input3" onkeydown="goEnter();"
												onblur="return checkdate();" styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td align="right">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.search" />
											</html:submit>
										</td>
									</tr>
								</table>
							</html:form>
							<html:form action="/pickupreasonMaintainAC.do" style="margin: 0">
								<logic:notEmpty name="pickupreasonAF" property="list">
									<table border="0" width="95%" id="table1" align="center"
										border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class=h4>
												合计: 人数
												<u>：<bean:write name="pickupreasonAF"
														property="totalpeople" /> </u> 金额
												<u>：<bean:write name="pickupreasonAF"
														property="totalmoney" /> </u>

											</td>
										</tr>
									</table>
								</logic:notEmpty>
								<logic:empty name="pickupreasonAF" property="list">
									<table border="0" width="95%" id="table1" align="center"
										border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class=h4>
												合计: 人数
												<u>:0</u> 金额
												<u>:0</u>

											</td>
										</tr>
									</table>
								</logic:empty>
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="190">
														<b class="font14">提取情况按原因统计列表</b>
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

								<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
									cellpadding="3" align="center">
									<tr>
										<td align="center" height="23" bgcolor="C4F0FF">
											&nbsp;
										</td>

										<td align="center" class=td2>
											<a href="javascript:sort('pickreason')">提取原因</a>
											<logic:equal name="pagination" property="orderBy"
												value="pickreason">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('countpeople')">人数</a>
											<logic:equal name="pagination" property="orderBy"
												value="countpeople">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											人数比率
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('sumpick')">金额</a>
											<logic:equal name="pagination" property="orderBy"
												value="sumpick">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>

										</td>
										<td align="center" class=td2>
											金额比率
										</td>
									</tr>
									<logic:notEmpty name="pickupreasonAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="pickupreasonAF" property="list"
											id="element" indexId="i">
											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>
											<tr id="tr<%=i%>"
												onclick='gotoClickpp("<%=i%>",idAF);'
												onMouseOver='this.style.background="#eaeaea"'
												onMouseOut='gotoColorpp("<%=i%>", idAF);'
												class="<%=strClass %>" onDblClick='gettr("tr<%=i%>");'>
												<td valign="top">
													<p align="left">
														<input id="s<%=i%>" type="radio" name="id"
															value="<bean:write name="element" property="pickupreason"/>"
															<%if(new Integer(0).equals(i)) out.print("checked"); %>>
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="pickupreason" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="numberpeople" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="countpeople" />
														%
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="pickmoney" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="countmoney" />
														%
													</p>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="pickupreasonAF" property="list">
										<tr>
											<td colspan="4" height="30" style="color:red">
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
															<jsp:param name="url" value="showPickupreasonListAC.do" />
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
													<td>
														<logic:empty name="pickupreasonAF" property="list">
															<html:submit property="method" styleClass="buttona"
																disabled="true">
																<bean:message key="button.print" />
															</html:submit>
														</logic:empty>
														<logic:notEmpty name="pickupreasonAF" property="list">
															<html:submit property="method" styleClass="buttona">
																<bean:message key="button.print" />
															</html:submit>
														</logic:notEmpty>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</html:form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html:html>
