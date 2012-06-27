<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.action.ShowSearchLackInfoListAC_old"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			ShowSearchLackInfoListAC_old.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>统计查询-缴存提取统计-欠缴信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script language="JavaScript">
var tr = "tr0";
function gettr(index){
	tr=index;
}
function checkData(){

    var id=document.all.orgId_old_q.value.trim();
    var name=document.all.orgName_old_q.value.trim();
	var month=document.all.yearMonth_old_q.value.trim();
   
    if(id==""&&name==""&&month==""){
       alert("请选择条件查询!");
       return false;
    }
}
function exp(){
	var id = document.getElementById(tr).childNodes[6].innerHTML.trim();
	var orgid = document.getElementById(tr).childNodes[1].innerHTML.trim();
	var orgName = document.getElementById(tr).childNodes[2].innerHTML.trim();
	var addPayMonth = document.getElementById(tr).childNodes[3].innerHTML.trim();
	document.URL="<%=path%>/syscollection/querystatistics/paymentpickstatistics/"+
		"searchLackInfo/searchLackInfoListExpAC_old.do?id="+id+"&orgid="+orgid+
		"&orgName="+encodeURI(orgName)+"&addPayMonth="+addPayMonth;
	return false;
}
</script>

</head>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<body bgcolor="#FFFFFF" text="#000000">
	<jsp:include page="../../../../inc/sort.jsp">
		<jsp:param name="url" value="showSearchLackInfoListAC_old.do" />
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

				<html:form action="/findSearchLackInfoListAC_old.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td>
								<table border="0" width="100%" id="table1" align="left">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="180">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
											align="center" colspan="7">
											&nbsp;
										</td>
									</tr>

									<tr>
										<td width="17%" class="td1">
											单位编号
										</td>
										<td width="33%" colspan="3">
											<html:text name="searchLackInfoListAF" property="orgId_old_q"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td width="17%" class="td1">
											单位名称
										</td>
										<td width="33%">
											<html:text name="searchLackInfoListAF"
												property="orgName_old_q" styleClass="input3"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											欠缴年月
										</td>
										<td width="33%" colspan="3">
											<html:text name="searchLackInfoListAF"
												property="yearMonth_old_q" styleClass="input3"
												styleId="txtsearch" maxlength="6"></html:text>
										</td>
										<td width="17%"></td>
										<td width="33%" class="td7">

										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td>
								<table width="100%" border="0" align="left" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="right">

										</td>
										<td align="right">
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

				<html:form action="/searchLackInfoListExpAC_old.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">

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
						cellpadding="0" align="center">
						<tr>
							<td width="5%" height="23" align="center" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								单位编号
							</td>
							<td align="center" class=td2>
								单位名称
							</td>
							<td align="center" class=td2>
								欠缴年月
							</td>
							<td align="center" class=td2>
								欠缴金额
							</td>
							<td align="center" class=td2>
								欠缴类型
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
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>",idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="element" property="id"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td>
										<bean:write name="element" property="orgid_old"
											format="0000000000" />
									</td>
									<td>
										<bean:write name="element" property="orgname_old" />
									</td>
									<td>
										<bean:write name="element" property="lack_month_old" />
									</td>
									<td>
										<bean:write name="element" property="lack_pay_old" />
									</td>
									<td>
										<bean:write name="element" property="lack_status_old" />
									</td>
									<td style="display:none">
										<bean:write name="element" property="id" />
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
										<td align="right">
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url"
													value="showSearchLackInfoListAC_old.do" />
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
										<td width="70">
											<logic:empty name="searchLackInfoListAF" property="list">
												<input type="button" class="buttona" value="导出"
													disabled="disabled" />
											</logic:empty>
											<logic:notEmpty name="searchLackInfoListAF" property="list">
												<input type="button" class="buttona" value="导出"
													onclick="return exp()" />
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

</body>
</html:html>
