<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.chgbiz.chgorgrate.action.ShowChgorgrateMaintainListAC"%>

<%
	Object pagination = request.getSession(false).getAttribute(
	ShowChgorgrateMaintainListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>汇缴比例调整-变更维护</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>

<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="修改"){    
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
				s2=i;
			}
			if(document.all.item(i).value=="启用"){
				s3=i;
			}
			if(document.all.item(i).value=="撤消启用"){
				s4=i;
			}
			if(document.all.item(i).value=="打印"){
				s5=i;
			}
		}
	} 

  	var count = "<bean:write name="pagination" property="nrOfElements" />";
	if(count!=0){
		update();
	}else{
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
	}
}
var tr="tr0"; 
function gettr(trindex) {
  	tr=trindex;
  	update();
}
function update() {
  	var status=document.getElementById(tr).childNodes[9].childNodes[0].innerHTML.trim();
  	if(status=="未启用"){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
  	}
  	if(status=="启用"){	
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
  	}  
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);    
	</logic:messagesPresent>
}

function checkID(){
	var id=document.chgOrgRateMaintainListAF.id.value.trim();
	if(isNaN(id)){
		alert("请录入单位编号!!");
		document.chgOrgRateMaintainListAF.id.focus();
		return false;	
	}else{
		return true;
	}
}


function search(){
	var id=document.forms[0].elements["id"].value.trim();
	var name=document.forms[0].elements["name"].value.trim();
	//alert("dfdf:"+document.forms[0].elements["date"].value+":dfd");
	var date=document.forms[0].elements["date"].value.trim();
	document.URL='showChgorgrateMaintainListAC.do?id='+id+'&name='+name+'&date='+date+'';
}

function checkDetele(){
	var x=confirm("确定删除记录?");
		if(x){ 
		  	return true;
		}else{
		  return false;
		}
}

function checkUse(){
	var x=confirm("确定启用记录?");
	if(x){
		return true;
	}else{
	  	return false;
	}
}

function checkDelUse(){
   	var x=confirm("确定撤销启用该记录?");
   	if(x){
		return true;
   	}else{
   		return false;
   	}
}
function copy(){
   	var date=document.all.date.value;
   	document.all.endDate.value=date;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false"
	onload="reportErrors(); loads();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="showChgorgrateMaintainListAC.do" />
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
										<a href="showChgOrgRateDoAC.do" class=a2>办理变更</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										变更维护
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
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">变更业务</font><font color="00B5DB">&gt;汇缴比例调整</font>
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
										<b class="font14">查 询 信 息</b>
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
				<html:form action="/findChgOrgRateMaintainAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1" algin="center">
								单位编号
							</td>
							<td width="22%" colspan="3">
								<html:text name="chgOrgRateMaintainListAF" property="id"
									onblur="return checkID();" styleClass="input3"
									styleId="txtsearch">
								</html:text>
							</td>
							<td class="td1" width="17%" algin="center">
								单位名称
							</td>
							<td width="22%" colspan="2">
								<html:text name="chgOrgRateMaintainListAF" property="name"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								调整年月
							</td>

							<td width="15%">
								<html:text name="chgOrgRateMaintainListAF" property="date"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="chgOrgRateMaintainListAF" property="endDate"
									styleClass="input3" onkeydown="enterNextFocus1();" />
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
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="154">
										<b class="font14">汇缴比例调整列表</b>
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

				<html:form action="/maintainChgorgrateMaintainListAC.do"
					style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
								单选
							</td>
							<td align="center" height="23" bgcolor="C4F0FF">
								业务编号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgOrgRate.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgOrgRate.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgOrgRate.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgOrgRate.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								单位缴率
							</td>
							<td align="center" class=td2>
								职工缴率
							</td>
							<td align="center" class=td2>
								调整前应缴总额
							</td>
							<td align="center" class=td2>
								调整后应缴总额
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgOrgRate.chgMonth')">调整年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgOrgRate.chgMonth">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								状态
							</td>
						</tr>
						<logic:notEmpty name="chgOrgRateMaintainListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="chgOrgRateMaintainListAF" property="list"
								id="element" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);;gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="id" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="org.id" format="0000000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="org.orgInfo.name" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="orgRate" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empRate" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="preSumPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="sumPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="chgMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="temp_chgStatus" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="chgOrgRateMaintainListAF" property="list">
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
													value="showChgorgrateMaintainListAC.do" />
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
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.update" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDetele();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkUse();">
												<bean:message key="button.use" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDelUse();">
												<bean:message key="button.deluse" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</html:form>

				<form action="showChgorgrateMaintainListAC.do" method="POST"
					name="Form1" id="Form1">
				</form>
	</table>
</html:html>
