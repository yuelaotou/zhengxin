<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>



<%@ page
	import="org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonMaintainListAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ShowChgpersonMaintainListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String type = (String) request.getAttribute("type");
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>人员变更-变更维护</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>

<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
var s6="";
var tr="tr0"; 
var type=<%=type%>
function loads(){

	var orgid=document.chgpersonMaintainListAF.id.value;
	if(orgid!=""){
	document.chgpersonMaintainListAF.id.value=format(document.chgpersonMaintainListAF.id.value)+document.chgpersonMaintainListAF.id.value;
	}
	
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="修改"){    
				s1=i;
			}
			if(document.all.item(i).value=="打印"){    
				s6=i;
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
			if(type!=2){
			if(document.all.item(i).value=="提交数据"){
				s5=i;
			}
			if(document.all.item(i).value=="撤销提交数据"){
				s6=i;
			}
			}
		}
	} 
  	var status=document.getElementById(tr);
	if(status!=null){
		update();
	}else{
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s6).disabled="true";
<%--		document.all.item(s5).disabled="true";--%>
<%--		document.all.item(s6).disabled="true";--%>
	}
	
}
function gettr(trindex) {
  tr=trindex;
  update();
}
function update() {
  	var status=document.getElementById(tr).childNodes[8].childNodes[0].innerHTML.trim();
  	if(status=="未启用"){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
		document.all.item(s6).disabled="true";
  	}
  	if(status=="启用"){
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
		document.all.item(s6).disabled="";
  	}  
  	if(type!=2){
	var picktypes=document.getElementById(tr).childNodes[10].childNodes[0].innerHTML.trim();
	if(picktypes=="未提取"){
<%--		document.all.item(s5).disabled="true";--%>
<%--		document.all.item(s6).disabled="";--%>
	}
	if(picktypes=="已提取"){
<%--		document.all.item(s5).disabled="true";--%>
<%--		document.all.item(s6).disabled="true";--%>
	}
	if(picktypes=="未提交"){
<%--		document.all.item(s5).disabled="";--%>
<%--		document.all.item(s6).disabled="true";--%>
	}
	}  
  		
}

function reportErrors() {    
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}


function search(){
	var id=document.chgpersonMaintainListAF.id.value.trim();
	var name=document.chgpersonMaintainListAF.name.value.trim();
	var date=document.chgpersonMaintainListAF.date.value.trim();
	document.URL='findChgpersonMaintainListAC.do?id='+id+'&name='+name+'&date='+date+'';
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

function checkID(){
	var id=document.chgpersonMaintainListAF.id.value.trim();
	if(isNaN(id)){
		alert("请录入单位编号!!");
		document.chgpersonMaintainListAF.id.focus();
		return false;	
	}else{
		return true;
	}
}
function checkYM(){
	var ym=document.chgpersonMaintainListAF.date.value.trim();
	if(ym.length!=0){
		var temp_date=ym.match(/^([2][0]\d{2}([0]\d|[1][0-2]))$/); 
		if (temp_date==null){
	        alert("请录入变更年月！格式如：200706");
			document.chgpersonMaintainListAF.date.focus();
			return false;
		}else{
			return true;
		}
	}
	return true;
}
function checkYM_1(){
	var ym=document.chgpersonMaintainListAF.endDate.value.trim();
	if(ym.length!=0){
		var temp_date=ym.match(/^([2][0]\d{2}([0]\d|[1][0-2]))$/); 
		if (temp_date==null){
	        alert("请录入变更年月！格式如：200706");
			document.chgpersonMaintainListAF.endDate.focus();
			return false;
		}else{
			return true;
		}
	}
	return true;
}
function checkDataReferring() {
  
	 var x=confirm("是否确定提交该笔业务?");
	if(!x){	 
	   return false;
	}
}
function checkDatapproval() {
  
	 var x=confirm("是否确定撤消提交该笔业务?");
	if(!x){	 
	   return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="showChgpersonMaintainListAC.do" />
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
										<a href="chppersonForwardURLAC.do" class=a2>办理变更</a>
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
										<font color="00B5DB">变更业务</font><font color="00B5DB">&gt;人员变更</font>
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
				<html:form action="/findChgpersonMaintainListAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1" algin="center">
								单位编号
							</td>
							<td width="22%" colspan="3">
								<html:text name="chgpersonMaintainListAF" property="id"
									onblur="return checkID();" styleClass="input3"
									styleId="txtsearch">
								</html:text>
							</td>
							<td class="td1" width="17%" algin="center">
								单位名称
							</td>
							<td width="22%" colspan="2">
								<html:text name="chgpersonMaintainListAF" property="name"
									styleClass="input3" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								变更年月
							</td>



							<td width="15%">
								<html:text name="chgpersonMaintainListAF" property="date"
									styleClass="input3" styleId="txtsearch"
									onblur="return checkYM();"></html:text>
							</td>
							<td width="5%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="chgpersonMaintainListAF" property="endDate"
									styleClass="input3" styleId="txtsearch"
									onblur="return checkYM_1();"></html:text>
							</td>
						</tr>


					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="search()">
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
										<b class="font14">人员变更列表</b>
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




				<html:form action="/maintainChgpersonMaintainListAC.do"
					style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF"></td>
							<td align="center" class=td2>
								业务编号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonHead.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonHead.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonHead.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonHead.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								变更人数
							</td>
							<td align="center" class=td2>
								变更前应缴总额
							</td>
							<td align="center" class=td2>
								变更后应缴总额
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonHead.chngMonth')">变更年月</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonHead.chngMonth">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								变更状态
							</td>
							<security:orgcan>
								<td width="10%" align="center" class=td2>
									提取状态
								</td>
							</security:orgcan>
						</tr>
						<logic:notEmpty name="chgpersonMaintainListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="chgpersonMaintainListAF" property="list"
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
											<a href="#"
												onclick="window.open('chgpersonWindowForwardURLAC.do?headid=<bean:write name="element" property="id" />&orgId=<bean:write name="element" property="org.id" />','window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes,resizable=yes,location=no, status=no');"><bean:write
													name="element" property="org.orgInfo.name" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="sumChgPerson" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="oldOldPayment" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="sumSumPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="chngMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="temp_chgStatus" />
										</p>
									</td>
									<html:hidden name="chgpersonMaintainListAF" property="type" />
									<security:orgcan>
										<td valign="top">
											<p>
												<bean:write name="element" property="temp_pick" />
											</p>
										</td>
									</security:orgcan>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="chgpersonMaintainListAF" property="list">
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
												<jsp:param name="url" value="showChgpersonMaintainListAC.do" />
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
										<td>
											<security:orgcan>
												<html:submit property="method" styleClass="buttona"
													onclick="return checkDataReferring()">
													<bean:message key="button.referring.data" />
												</html:submit>
											</security:orgcan>
										</td>
										<td>
											<security:orgcan>
												<html:submit property="method" styleClass="buttonc"
													onclick="return checkDatapproval()">
													<bean:message key="button.pproval.data" />
												</html:submit>
											</security:orgcan>
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</html:form>

				<form action="showChgpersonMaintainListAC.do" method="POST"
					name="Form1" id="Form1">
				</form>
	</table>
</html:html>
