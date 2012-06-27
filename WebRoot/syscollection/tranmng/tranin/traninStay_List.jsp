<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.tranmng.tranin.action.ShowTraninStayListAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ShowTraninStayListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>

<html:html>
<head>
	<title>tranin</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">

	
	<script language="javascript"></script>
	<script language="javascript" type="text/javascript">
var s1="";
var tr="tr0"; 
function loads(){
var status=document.getElementById('loadsMassage').value.trim();

	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="完成转入"){
				s1=i;
			}
		}
	}
	if(status=='noMassage'){
  		document.all.item(s1).disabled="true";
  	}else{
var tranoutinorgids=document.getElementById(tr).childNodes[5].childNodes[0].innerHTML.trim();
var tranoutoutorgids=document.getElementById(tr).childNodes[3].childNodes[0].innerHTML.trim();
  	    document.all.item(s1).disabled="";
  	    document.all.tranoutinorgid.value=tranoutinorgids;
        document.all.tranoutoutorgid.value=tranoutoutorgids;
  	}


}
function gettr(trindex){
tr=trindex;
var tranoutinorgids=document.getElementById(tr).childNodes[5].childNodes[0].innerHTML.trim();
var tranoutoutorgids=document.getElementById(tr).childNodes[3].childNodes[0].innerHTML.trim();
document.all.tranoutinorgid.value=tranoutinorgids;
document.all.tranoutoutorgid.value=tranoutoutorgids;
}
function gotoPrint(){

if(confirm("是否要打印？")){
		document.all.report.value="print";
	}else{
		document.all.report.value="noprint";
	}

}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function tc3()
{
   return false;
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();"
	onContextmenu="return false">

	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="showTraninStayListAC.do" />
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
										<table border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="112" height="37"
													background="<%=path%>/img/buttonbl.gif" align="center"
													valign="top" style="PADDING-top: 7px">
													待转入登记
												</td>
												<td width="112" height="37"
													background="<%=path%>/img/buttong.gif" align="center"
													style="PADDING-top: 7px" valign="top">
													<a href="showTraninListURLAC.do" class=a2> 转入登记</a>
												</td>
												<td width="112" height="37"
													background="<%=path%>/img/buttong.gif" align="center"
													style="PADDING-top: 7px" valign="top">
													<a href="showTraninVidListURLAC.do" class=a2>转入维护</a>
												</td>
											</tr>
										</table>
									<td background="<%=path%>/img/table_bg_line.gif" align="right">
										<table width="300" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="37">
													<img src="<%=path%>/img/title_banner.gif" width="37"
														height="24">
												</td>
												<td width="189" class=font14 bgcolor="#FFFFFF"
													align="center" valign="bottom">
													<font color="00B5DB">转入信息</font><font color="00B5DB">&gt;&gt;</font><font
														color="00B5DB">办理转入</font>
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
							<html:form action="/findTraninStayMaintainListAC.do">
								<table width="95%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td height="35">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td height="22" bgcolor="#CCCCCC" align="center"
														width="117">
														<b class="font14">查询条件</b>
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
										<td width="17%" class="td1" algin="center">
											转出单位编号
										</td>
										<td width="22%">
											<html:text name="traninStayAF" property="outOrgId"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch">
											</html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转出单位名称
										</td>
										<td width="22%">
											<html:text name="traninStayAF" property="outOrgName"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1" algin="center">
											转入单位编号
										</td>
										<td width="22%">
											<html:text name="traninStayAF" property="inOrgId"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch">
											</html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转入单位名称
										</td>
										<td width="22%">
											<html:text name="traninStayAF" property="inOrgName"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td class="td1" width="17%" algin="center">
											转出结算号
										</td>
										<td width="33%">
											<html:text name="traninStayAF" property="noteNum"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
										</td>
										<td class="td1" width="17%" algin="center">
											转出凭证编号
										</td>
										<td width="33%">
											<html:text name="traninStayAF" property="docNum"
												styleClass="input3" onkeydown="goEnter();"
												styleId="txtsearch"></html:text>
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
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="154">
													<b class="font14">待转入列表</b>
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
							<html:form action="/traninStayMaintainAC.do" style="margin: 0">
								<input type=hidden name="loadsMassage" id="loadsMassage"
									value="<bean:write name="traninStayAF" property="loadsMassage" />">
								<html:hidden name="traninStayAF" property="inOrgId"></html:hidden>
								<input type=hidden name=tranoutoutorgid value="">
								<input type=hidden name=tranoutinorgid value="">
								<input type="hidden" name="report" value="">
								<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
									cellpadding="3" align="center">
									<tr>
										<td height="23" align="center" bgcolor="C4F0FF">
											&nbsp;
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranOutHead.noteNum')">转出结算号</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranOutHead.noteNum">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranOutHead.docNum')">转出凭证编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranOutHead.docNum">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranOutHead.tranOutOrg.id')">转出单位编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranOutHead.tranOutOrg.id">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a
												href="javascript:sort('tranOutHead.tranOutOrg.orgInfo.name')">转出单位名称</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranOutHead.tranOutOrg.orgInfo.name">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a href="javascript:sort('tranOutHead.tranInOrg.id')">转入单位编号</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranOutHead.tranInOrg.id">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											<a
												href="javascript:sort('tranOutHead.tranInOrg.orgInfo.name')">转入单位名称</a>
											<logic:equal name="pagination" property="orderBy"
												value="tranOutHead.tranInOrg.orgInfo.name">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>

										</td>
										<td align="center" class=td2>
											转出人数
										</td>
										<td align="center" class=td2>
											转出总额
										</td>
									</tr>
									<logic:notEmpty name="traninStayAF" property="list">
										<%
												int j = 0;
												String strClass = "";
										%>
										<logic:iterate name="traninStayAF" property="list"
											id="element" indexId="i">
											<%
														j++;
														if (j % 2 == 0) {
															strClass = "td8";
														} else {
															strClass = "td9";
														}
											%>
											<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);gettr("tr<%=i %>");'
												onMouseOver='this.style.background="#eaeaea"'
												onMouseOut='gotoColorpp("<%=i%>",idAF);'
												class="<%=strClass%>">
												
												<td valign="top">
													<p align="left">
														<input id="s<%=i%>" type="radio" name=id
															value="<bean:write name="element" property="tranOutHead.id"/>"
															<%if(new Integer(0).equals(i)) out.print("checked"); %>>
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="tranOutHead.noteNum" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="tranOutHead.docNum" />
													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element"
															property="tranOutHead.tranOutOrg.id" format="0000000000" />

													</p>
												</td>
												<td valign="top">
													<a href="#"
														onclick="window.open('<%=path%>/syscollection/tranmng/tranout/tranoutTcForwardURLAC.do?headid=<bean:write name="element"
															property="tranOutHead.id" />','window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,location=no, status=no');return tc3();">

														<bean:write name="element"
															property="tranOutHead.tranOutOrg.orgInfo.name" /> </a>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element"
															property="tranOutHead.tranInOrg.id" format="0000000000" />

													</p>
												</td>
												<td valign="top">
													<p>
														<bean:write name="element"
															property="tranOutHead.tranInOrg.orgInfo.name" />
													</p>
												</td>
												<td valign="top">
													<bean:write name="element"
														property="tranOutHead.countTranOutPeople" />
												</td>
												<td valign="top">
													<p>
														<bean:write name="element" property="tranOutAmount" />
													</p>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="traninStayAF" property="list">
										<tr>
											<td colspan="9" height="30" style="color:red">
												没有找到与条件相符合结果！
											</td>
										</tr>
										<tr>
											<td colspan="11"></td>
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
															<jsp:param name="url" value="showTraninStayListAC.do" />
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
														<html:submit property="method" styleClass="buttona"
															onclick="return  gotoPrint();">
															<bean:message key="button.over.tranin" />
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
