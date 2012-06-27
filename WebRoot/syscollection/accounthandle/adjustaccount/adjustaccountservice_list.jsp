<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountServiceTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Object pagination = request.getSession(false).getAttribute(
			AdjustaccountServiceTaShowAC.PAGINATION_KEY);
	request.setAttribute("URL", "adjustaccountServiceForwardURLAC.do");
	request.setAttribute("pagination", pagination);
	String print = (String) request.getAttribute("PRINT");
%>
<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";

function loads(){
	
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="完成调整"){
				s1=i;
			}

			if(document.all.item(i).value=="修改"){
				s3=i;
			}
			if(document.all.item(i).value=="删除"){
				s4=i;
			}
			if(document.all.item(i).value=="机打凭证"){
				s5=i;
			}
		}
		if(document.all.item(i).type=="button"){
			if(document.all.item(i).value=="撤消调整"){
				s2=i;
			}
		}
	}    
	 	if(document.all.listCount.value==0){
			document.all.item(s1).disabled="true";
			document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="true";
			document.all.item(s4).disabled="true";
			document.all.item(s5).disabled="true";
		}else{
		update();
    	}
}
var tr="tr0"; 
function gettr(trindex) {
  tr=trindex;
  update();
}
function update() {
  	var status=document.getElementById(tr).childNodes[8].childNodes[0].innerHTML.trim();
  	
  	if(status=="确认"){
		document.all.item(s1).disabled="true";
  		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="";
  	}
  	if(status=="录入清册"){
  		document.all.item(s1).disabled="";
  		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="";
  	}  	
  	if(status=="登记"){
		document.all.item(s1).disabled="true";
  		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="";
  	}
  	if(status=="复核"){
		document.all.item(s1).disabled="true";
  		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="";
  	}
  	if(status=="入账"){
  		document.all.item(s1).disabled="true";
  		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="";
  	}  	
}
function excuteAjax(){
var id=getCheckId();
var queryString = "findAdjustaccountServiceTaAAC.do?"; 
     	queryString = queryString + "id="+id; 	     
	    findInfo(queryString);
}
function display(info){
if(info=="ok"){
	if(confirm('你确定要撤消调整吗')){
		var id=getCheckId();
		document.URL="adjustAdjustaccountServiceAC.do?id="+id;   
	}
	}
}
function reportErrors() {
var print="<%=print%>";
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	loads();
	
	<%
			List list=(List)request.getAttribute("printlist");
			if(list!=null){
	%>
	if(print=="PRINT"){
			if(confirm('是否要打印凭证！')){
				document.URL="printAdjustaccountServiceAC.do";   
			}
		}
	<%
			}
	%>
}
function confirmPrint(){
if(confirm('请确认是否要做完成调整')){

document.all.report.value="noprint";

}else{
return false;
}

}
</script>
<html>
	<head>
		<title>账务处理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	</head>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors();loads()"
		onContextmenu="return false">
		<form method="post">
			<jsp:include page="../../../inc/sort.jsp">
				<jsp:param name="url" value="showAdjustaccountAC.do" />
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
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												valign="top" style="PADDING-top: 7px">
												<a
													href="<%=path%>/syscollection/accounthandle/adjustaccount/adjustaccountForwardURLAC.do"
													class=a2>办理错账调整</a>
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttonbl.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												错账调整维护
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
											<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
												valign="bottom">
												<font color="00B5DB">账务处理</font><font color="00B5DB">&gt;&gt;</font><font
													color="00B5DB">错账调整</font>
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
						<html:form action="/findAdjustaccountServiceAC"
							styleClass="margin: 0">
							<table border="0" width="95%" id="table1" cellspacing=1
								cellpadding=0 align="center">
								<tr>
									<td class="td1" width="17%">
										单位编号
									</td>
									<td width="33%">
										<html:text name="adjustaccountServiceShowAF" property="orgId"
											styleClass="input3" styleId="txtsearch" />
									</td>
									<td class="td1" width="17%">
										单位名称
									</td>
									<td width="33%" colspan="3">
										<html:text name="adjustaccountServiceShowAF"
											property="orgName" styleClass="input3" styleId="txtsearch" />
									</td>
								</tr>
								<tr>
									<td class="td1" width="17%">
										凭证编号
									</td>
									<td width="33%">
										<html:text name="adjustaccountServiceShowAF"
											property="bizDocNum" styleClass="input3" styleId="txtsearch" />
									</td>
									<td class="td1" width="17%">
										调整日期
									</td>
									<td width="15%">
										<html:text name="adjustaccountServiceShowAF" property="date"
											styleClass="input3" styleId="txtsearch" maxlength="8" />
									</td>
									<td width="3%">
										至
									</td>
									<td width="15%">
										<html:text name="adjustaccountServiceShowAF" property="date1"
											styleClass="input3" styleId="txtsearch" maxlength="8" />
									</td>
								</tr>
								<tr>
									<td class="td1" width="17%">
										业务状态
									</td>
									<td width="33%">
										<html:select property="bis_Status" styleClass="input4">
											<html:option value=""></html:option>
											<html:optionsCollection property="bisMap"
												name="adjustaccountServiceShowAF" label="value" value="key" />
										</html:select>
									</td>
									<td width="17%">
										&nbsp;
									</td>
									<td width="33%" colspan="3">
										&nbsp;
									</td>
								</tr>
							</table>
							<table width="95%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td align="right">
										<html:submit property="action" styleClass="buttona">
											<bean:message key="button.search" />
										</html:submit>
									</td>
								</tr>
							</table>
						</html:form>

						<html:form action="/adjustaccountServiceMaintainAC"
							styleClass="margin: 0">
							<input type="hidden" name="report" value="">
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td class=h4>
										合计：调整人数
										<u>：<bean:write name="adjustaccountServiceShowAF"
												property="person" /> </u>调整金额
										<u>：<bean:write name="adjustaccountServiceShowAF"
												property="total" />
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
												<td height="22" bgcolor="#CCCCCC" align="center" width="117">
													<b class="font14">错账调整列表</b>
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
							<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
								cellpadding="3" align="center">
								<tr>
									<td align="center" height="23" bgcolor="C4F0FF">
									</td>
									<td align="center" class=td2>
										结算号
									</td>
									<td align="center" class=td2>
										<a href="javascript:sort('adjustWrongAccountHead.docNum')">凭证编号</a>
										<logic:equal name="pagination" property="orderBy"
											value="adjustWrongAccountHead.docNum">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										<a href="javascript:sort('adjustWrongAccountHead.org.id')">单位编号</a>
										<logic:equal name="pagination" property="orderBy"
											value="adjustWrongAccountHead.org.id">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										<a href="javascript:sort('adjustWrongAccountHead.orgName')">单位名称</a>
										<logic:equal name="pagination" property="orderBy"
											value="adjustWrongAccountHead.orgName">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										调整人数
									</td>
									<td align="center" class=td2>
										<a href="javascript:sort('total')">调整金额</a>
										<logic:equal name="pagination" property="orderBy"
											value="total">
											<logic:equal name="pagination" property="orderother"
												value="ASC">▲</logic:equal>
											<logic:equal name="pagination" property="orderother"
												value="DESC">▼</logic:equal>
										</logic:equal>
									</td>
									<td align="center" class=td2>
										调整日期
									</td>
									<td align="center" class=td2>
										业务状态
									</td>
								</tr>
								<html:hidden name="adjustaccountServiceShowAF"
									property="listCount" />
								<logic:notEmpty name="adjustaccountServiceShowAF"
									property="list">
									<%
												int j = 0;
												String strClass = "";
									%>
									<logic:iterate name="adjustaccountServiceShowAF"
										property="list" id="elements" indexId="i">
										<%
												j++;
												if (j % 2 == 0) {
													strClass = "td8";
												} else {
													strClass = "td9";
												}
										%>
										<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);gettr("tr<%=i%>");'
											onMouseOver='this.style.background="#eaeaea"'
											onMouseOut='gotoColorpp("<%=i%>",idAF);'
											class="<%=strClass%>">
											<td valign="top">
												<p align="left">
													<input id="s<%=i%>" type="radio" name="id"
														value="<bean:write name="elements" property="id"/>"
														<%if(new Integer(0).equals(i)) out.print("checked"); %>>
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="reserveaC" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="docNum" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="org.id"
														format="0000000000" />
												</p>
											</td>
											<td valign="top">
												<a href="#"
													onclick="window.open('adjustaccountServiceTaShowTailAC.do?adjustWrongAccountHeadTempId=<bean:write name="elements"
															property="id" />','window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,location=no, status=no');">

													<bean:write name="elements" property="org.orgInfo.name" />
												</a>
											</td>


											<td valign="top">
												<p>
													<bean:write name="elements" property="personTotal" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="total" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="bizDate" />
												</p>
											</td>
											<td valign="top">
												<p>
													<bean:write name="elements" property="status" />
												</p>
											</td>
										</tr>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="adjustaccountServiceShowAF" property="list">
									<tr>
										<td colspan="11" height="30" style="color:red">
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
														<jsp:param name="url"
															value="showAdjustaccountServiceAC.do" />
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
										<table border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<html:submit property="method" styleClass="buttona"
														disabled="true" onclick="return confirmPrint();">
														<bean:message key="button.over.adjust" />
													</html:submit>
												</td>
												<td>
													<html:button property="method" styleClass="buttona"
														disabled="true" onclick="return excuteAjax() ">
														<bean:message key="button.del.adjust" />
													</html:button>
												</td>
												<td>
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.update" />
													</html:submit>
												</td>
												<td>
													<html:submit property="method" styleClass="buttona"
														disabled="true" onclick="return confirm('是否删除');">
														<bean:message key="button.delete" />
													</html:submit>
												</td>
												<td>
													<html:submit property="method" styleClass="buttonb"
														disabled="true">
														<bean:message key="button.print.machine" />
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
			<form action="showAdjustaccountAC.do" method="POST" name="Form1"
				id="Form1">
				<input type="hidden" name="temp_type" id="temp_type" value="" />
			</form>
	</body>
</html>
