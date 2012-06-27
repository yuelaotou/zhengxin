<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.pickupmng.pickup.action.PickCheckShowAC"%>
<!DOCTYPE HTML  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
			Object pagination = request.getSession(false).getAttribute(
			PickCheckShowAC.PAGINACTION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<script type="text/javascript">
var oldColor="#ffffff";
var newColor="#E8FCFD";
var s1="";
var s2="";
var tr="tr0";
function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="审批通过"){
				s1=i;
			}
			if(document.all.item(i).value=="撤销审批"){
				s2=i;
			}
		}
	}
	var n = "<bean:write name="pagination" property="nrOfElements" />";
	if(n!=0){
		update();
	}else{
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
	}
}

function gettr(trindex) {
  	tr=trindex;
  	update();
}
function update() {
	var status=document.getElementById(tr).cells[11].innerHTML.trim();
	if(status=='审批通过'){
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
	}else if(status=='审批未通过'){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
<html:html lang="true">
<head>
	<html:base />
	<title>提取审批</title>
</head>
<body bgcolor="#FFFFFF" text="#000000"
	onload="loads();return reportErrors();">
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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										提取审批
									</td>
								</tr>
							</table>
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="190" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB"> 提取审批 </font>
									</td>
									<td width="73" class=font14>
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
				<html:form action="/pickCheckFindAC.do">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
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
						cellpadding=0 align="center">
						<tr>
							<td width="15%" class="td1">
								单位编号
							</td>
							<td colspan="3">
								<html:text name="pickCheckAF" property="orgid"
									styleClass="input3"></html:text>
							</td>
							<td width="14%" class="td1">
								单位名称
							</td>
							<td colspan="3">
								<html:text name="pickCheckAF" property="orgname"
									styleClass="input3"></html:text>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								提取日期
							</td>
							<td width="15%">
								<html:text name="pickCheckAF" property="begdate"
									styleClass="input3"></html:text>
							</td>
							<td width="4%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="pickCheckAF" property="enddate"
									styleClass="input3"></html:text>
							</td>
							<td width="15%" class="td1">
								审批日期
							</td>
							<td width="15%">
								<html:text name="pickCheckAF" property="checkbegdate"
									styleClass="input3"></html:text>
							</td>
							<td width="4%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="pickCheckAF" property="checkenddate"
									styleClass="input3"></html:text>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								是否审批
							</td>
							<td colspan="3">
								<html:select style="input4" name="pickCheckAF"
									property="ischecked" styleClass="input4">
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
								<html:submit property="method" styleClass="buttona">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							总计：提取人数
							<u>: <bean:write name="pickCheckAF" property="person" /> </u>
							提取金额
							<u>: <bean:write name="pickCheckAF" property="corpus"
									format="0.00" /> </u> 销户利息
							<u>: <bean:write name="pickCheckAF" property="interest"
									format="0.00" /> </u> 提取总额
							<u>: <bean:write name="pickCheckAF" property="corpusInterest"
									format="0.00" /> </u>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="115">
										提取审批列表
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
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr bgcolor="1BA5FF">
						<td align="center" height="6" colspan="1"></td>
					</tr>
				</table>
				<html:form action="/pickCheckMaintainAC.do">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td width="2%" height="23" align="center" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td width="7%" align="center" class=td2>
								单位编号
							</td>
							<td width="22%" align="center" class=td2>
								单位名称
							</td>
							<td width="5%" align="center" class=td2>
								提取人数
							</td>
							<td width="5%" align="center" class=td2>
								提取金额
							</td>
							<td width="5%" align="center" class=td2>
								销户利息
							</td>
							<td width="6%" align="center" class=td2>
								提取总额
							</td>
							<td width="6%" align="center" class=td2>
								提取日期
							</td>
							<td width="6%" align="center" class=td2>
								审批日期
							</td>
							<td width="5%" align="center" class=td2>
								业务状态
							</td>
							<td width="7%" align="center" class=td2>
								审核状态
							</td>
							<td width="7%" align="center" class=td2>
								审批状态
							</td>
							<td width="6%" align="center" class=td2>
								制单人
							</td>
						</tr>
						<logic:notEmpty name="pickCheckAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="pickCheckDTO" name="pickCheckAF"
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
											value="<bean:write name="pickCheckDTO" property="id"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td valign="top" align="center">
										<bean:write name="pickCheckDTO" property="orgid"
											format="0000000000" />
									</td>
									<td valign="top" align="left">
										<a href="#"
											onclick="window.open('../pickupwh/wehuwindowshowAC.do?id=<bean:write name="pickCheckDTO" property="id"/>','','width=750,height=550,scrollbars=yes');return false;">
											<bean:write name="pickCheckDTO" property="orgname" /> </a>
									</td>
									<td valign="top" align="center">
										<bean:write name="pickCheckDTO" property="personcount" />
									</td>
									<td valign="top" align="right">
										<bean:write name="pickCheckDTO" property="corpus"
											format="0.00" />
									</td>
									<td valign="top" align="right">
										<bean:write name="pickCheckDTO" property="interest"
											format="0.00" />
									</td>
									<td valign="top" align="right">
										<bean:write name="pickCheckDTO" property="corpusInterest"
											format="0.00" />
									</td>
									<td valign="top" align="center">
										<bean:write name="pickCheckDTO" property="pickdate" />
									</td>
									<td valign="top" align="center">
										<bean:write name="pickCheckDTO" property="checkdate" />
									</td>
									<td valign="top" align="left">
										<bean:write name="pickCheckDTO" property="biztype" />
									</td>
									<td valign="top" align="left">
										<bean:write name="pickCheckDTO" property="hestatus" />
									</td>
									<td valign="top" align="left">
										<bean:write name="pickCheckDTO" property="pistatus" />
									</td>
									<td valign="top" align="left">
										<bean:write name="pickCheckDTO" property="operator" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="pickCheckAF" property="list">
							<tr>
								<td colspan="13" height="30" style="color:red">
									没有找到与条件相符合结果!
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
												<jsp:param name="url" value="pickCheckShowAC.do" />
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
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.approval.pass" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.approval.delete" />
											</html:submit>
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
