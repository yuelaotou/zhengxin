<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List,java.util.ArrayList"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.adjustaccount.action.AdjustaccountTaShowAC"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.adjustaccount.form.AdjustaccountShowAF"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Object pagination = request.getSession(false).getAttribute(
			AdjustaccountTaShowAC.PAGINATION_KEY);
	request.setAttribute("URL", "adjustaccountForwardURLAC.do");
	request.setAttribute("pagination", pagination);
%>
<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="添加"){
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
				s2=i;
			}
			if(document.all.item(i).value=="完成调整"){
				s3=i;
			}
		}
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
    alert(message);
	</logic:messagesPresent>
document.adjustaccountShowAF.orgId.value=formatTen(document.adjustaccountShowAF.orgId.value)+document.adjustaccountShowAF.orgId.value;
if(document.adjustaccountShowAF.orgId.value==000000)
	document.adjustaccountShowAF.orgId.value="";
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax1();
	}
}
function docNumCheck(){
	 if(document.adjustaccountShowAF.date.value.trim()==""){
	 	alert("请输入日期！");
	 	return false;		
	 }else if(document.adjustaccountShowAF.bizDocNum.value.trim()==""){
	 	alert("请输入错账凭证编号！");
	 	return false;		
	 }else if(document.adjustaccountShowAF.orgId.value.trim()==""){
	 	alert("请输入单位编号！");
	 	return false;		
	 }else
		return true;		
}		
function executeAjax1() {
		if(docNumCheck()==true){
        var queryString = "adjustaccountTaFindAAC.do?"; 
  //    var bizDocNum = document.forms[0].elements["bizDocNum"].value.trim();
    //  var date = document.forms[0].elements["date"].value.trim();
     	var bizDocNum = document.adjustaccountShowAF.bizDocNum.value.trim();
 		var date = document.adjustaccountShowAF.date.value.trim();
 		var orgId= document.adjustaccountShowAF.orgId.value.trim();
        queryString = queryString + "bizDocNum="+bizDocNum+"&&date="+date+"&&orgId="+orgId ; 	        
	    findInfo(queryString);
	    }
}//输入凭证号是使用
function displays(date,bizDocNum,bizNoteNum,orgId,temp_type,orgName){

 // document.forms[0].elements["date"].value=date;
// document.forms[0].elements["bizDocNum"].value=bizDocNum;
 // if(bizNoteNum!=null)
 // document.forms[0].elements["bizNoteNum"].value=bizNoteNum;
 // document.forms[0].elements["orgId"].value=orgId; 
 // document.forms[0].elements["orgName"].value=orgName;
 	document.adjustaccountShowAF.date.value=date;
  	document.adjustaccountShowAF.bizDocNum.value=bizDocNum;
  	document.adjustaccountShowAF.bizNoteNum.value=bizNoteNum;
  	document.adjustaccountShowAF.orgId.value=orgId;
  	
  	document.adjustaccountShowAF.orgName.value=orgName;
  	if(temp_type!=""){
  	alert(temp_type);
  	}
  	showlist(temp_type); 
}//单位使用
function displayss(type){
//	alert(type);
}
function displayOrg(orgId,orgName,temp,typemassage){
  	document.adjustaccountShowAF.orgName.value=orgName;
  	document.adjustaccountShowAF.orgId.value=orgId;
  	if(typemassage!=""){
  	alert(typemassage);
  	}
	if(temp!="")
		alert(temp);
	else
  	showlist("") ;
}
function showlist(temp_type) {
  document.Form1.submit();
}
function gotoOrg(status){
	gotoOrgpop(status,'<%=path%>','0');
}
function docNumChecks(){
	 if(document.adjustaccountShowAF.orgId.value.trim()==""){
	 	alert("请输入单位编号查询！");
	 	return false;		
	 }else 
		return true;		
}
function executeAjax(){
		
		var queryString = "adjustaccountTaFindOrgAAC.do?"; 
 		var orgId = document.adjustaccountShowAF.date.value.trim();
 		var orgName = document.adjustaccountShowAF.orgName.value.trim();
 		
 	//	document.adjustaccountShowAF.date.value="";
 	//	document.adjustaccountShowAF.orgId.value=orgId;
 		queryString = queryString + "orgId="+orgId+"&&orgName="+orgName ; 
		if (orgId==""){	     
	   // alert("请输入单位编号查询！");
	   	  gotoOrg('2');
	    }else{
	    	findInfo(queryString);
	    }
}
function executeAjaxOrg(){
		var queryString = "adjustaccountTaFindOrgAAC.do?"; 
 		var orgId = document.adjustaccountShowAF.orgId.value.trim();
 		var orgName = document.adjustaccountShowAF.orgName.value.trim();
 		queryString = queryString + "orgId="+orgId+"&&orgName="+orgName ; 
		if (orgId==""){	     
	 //   alert("请输入单位编号查询！");
	 	  gotoOrg('2');
	    }else{
	    	findInfo(queryString);
	    }
}
function gotoEnters(){
	if(event.keyCode==13){
		event.keyCode = 9;
		if(document.adjustaccountShowAF.orgId.value.trim()==""){
	 	//alert("请输入单位编号查询！");
	 	gotoOrg('2');
	 	}
		else {
		var queryString = "adjustaccountTaFindOrgAAC.do?"; 
 		var orgId = document.adjustaccountShowAF.orgId.value.trim();
 		var orgName = document.adjustaccountShowAF.orgName.value.trim();
     	queryString = queryString + "orgId="+orgId+"&&orgName="+orgName ; 
    	findInfo(queryString);
		}	
	}
}
function confirmPrint(){
if(confirm('请确认是否要做完成调整')){
if(confirm('是否打印凭证')){
document.all.noteNumber.value=document.all.noteNum.value;
document.all.report.value="print";
}else{
document.all.noteNumber.value=document.all.noteNum.value;
document.all.report.value="noprint";
}
}else{
return false;
}

}
function gotoSave(){
document.all.noteNumber.value=document.all.noteNum.value;
}


</script>
<html>
	<head>
		<title>账务处理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	</head>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()"
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
												background="<%=path%>/img/buttonbl.gif" align="center"
												valign="top" style="PADDING-top: 7px">
												办理错账调整
											</td>
											<td width="112" height="37"
												background="<%=path%>/img/buttong.gif" align="center"
												style="PADDING-top: 7px" valign="top">
												<a
													href="<%=path%>/syscollection/accounthandle/adjustaccount/adjustaccountServiceForwardURLAC.do"
													class=a2>错账调整维护</a>
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
						<html:form action="/showAdjustaccountAC" styleClass="margin: 0">
							<table width="95%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="35">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="22" bgcolor="#CCCCCC" align="center" width="117">
													<b class="font14">错账调整信息</b>
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
								<logic:equal name="adjustaccountShowAF" property="type"
									value="4">
									<tr>
										<td width="11%" class=td1>
											日期
											<br>
										</td>
										<td class="td4" width="12%">
											<html:text name="adjustaccountShowAF" property="date"
												styleClass="input3" styleId="txtsearch" disabled="true" />
										</td>
										<td class="td1" width="11%">
											错账凭证编号
										</td>
										<td class="td4" colspan="2">
											<html:text name="adjustaccountShowAF" property="bizDocNum"
												styleClass="input3" styleId="txtsearch"
												ondblclick="executeAjax1();" onkeydown="gotoEnter();"
												disabled="true" />
										</td>
										<td class="td1" width="17%">
											错账结算号
										</td>
										<td class="td4" width="33%">
											<html:text name="adjustaccountShowAF" property="bizNoteNum"
												styleClass="input3" styleId="txtsearch" disabled="true" />
										</td>
									</tr>
									<tr>
										<td class="td1" width="11%">
											单位编号
										</td>
										<td colspan="3">
											<html:text name="adjustaccountShowAF" property="orgId"
												styleClass="input3" styleId="txtsearch"
												onkeydown="gotoEnters()" ondblclick="executeAjaxOrg();"
												disabled="true" />
										</td>
										<td width="10%">
											<input type="button" class="buttona" value="..."
												name="button322" onclick="gotoOrg('2')" disabled="true">
										</td>
										<td class="td1" width="17%">
											单位名称
										</td>
										<td width="33%">
											<html:text name="adjustaccountShowAF" property="orgName"
												styleClass="input3" styleId="txtsearch" disabled="true" />
										</td>
									<tr>
										<td class="td1" width="11%">
											结算号
										</td>
										<td colspan="3">
											<html:text name="adjustaccountShowAF" property="noteNum"
												styleClass="input3" styleId="txtsearch" />
										</td>
										<td width="10%">
											<input type="button" class="buttona" value="..."
												name="button322" onclick="gotoOrg('2')">
										</td>
										<td width="17%"></td>
										<td width="33%">

										</td>
									</tr>
									</tr>
							</table>
							</logic:equal>
							<logic:notEqual name="adjustaccountShowAF" property="type"
								value="4">
								<tr>
									<td width="11%" class=td1>
										日期
										<br>
									</td>
									<td class="td4" width="12%">
										<html:text name="adjustaccountShowAF" property="date"
											styleClass="input3" styleId="txtsearch" />
									</td>
									<td class="td1" width="11%">
										错账凭证编号
									</td>
									<td class="td4" colspan="2">
										<html:text name="adjustaccountShowAF" property="bizDocNum"
											styleClass="input3" styleId="txtsearch"
											ondblclick="executeAjax1();" onkeydown="gotoEnter();" />
									</td>
									<td class="td1" width="17%">
										错账结算号
									</td>
									<td class="td4" width="33%">
										<html:text name="adjustaccountShowAF" property="bizNoteNum"
											styleClass="input3" styleId="txtsearch" />
									</td>
								</tr>
								<tr>
									<td class="td1" width="11%">
										单位编号
									</td>
									<td colspan="3">
										<html:text name="adjustaccountShowAF" property="orgId"
											onkeydown="gotoEnters()" ondblclick="executeAjaxOrg();"
											styleClass="input3" styleId="txtsearch" />
									</td>
									<td width="10%">
										<input type="button" class="buttona" value="..."
											name="button322" onclick="gotoOrg('2')">
									</td>
									<td class="td1" width="17%">
										单位名称
									</td>
									<td width="33%">
										<html:text name="adjustaccountShowAF" property="orgName"
											styleClass="input3" styleId="txtsearch" />
									</td>
								</tr>
								<tr>
									<td class="td1" width="11%">
										结算号
									</td>
									<td colspan="3">
										<html:text name="adjustaccountShowAF" property="noteNum"
											styleClass="input3" styleId="txtsearch" />
									</td>
									<td width="10%">
									</td>
									<td width="17%"></td>
									<td width="33%">

									</td>
								</tr>
			</table>
			</logic:notEqual>

			</html:form>



			<html:form action="/adjustaccountMaintainAC" styleClass="margin: 0">
				<input type="hidden" name="report" value="">
				<input type="hidden" name="noteNumber" value="">
				<logic:notEmpty name="adjustaccountShowAF" property="list">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td class=h4>
											合计：调整人数
											<u>：<bean:write name="pagination" property="nrOfElements" />
											</u>调整金额
											<u>：-<bean:write name="adjustaccountShowAF"
													property="total" />
											</u>
										</td>
									</tr>
								</table>
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
								错账凭证号
							</td>
							<td align="center" class=td2>
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
								<a href="javascript:sort('empHAFAccountFlow.credit')">调整金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="empHAFAccountFlow.credit">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								错账日期
							</td>
							<td align="center" class=td2>
								调整业务类型
							</td>
						</tr>
						<%
									int j = 0;
									String strClass = "";
						%>
						<logic:iterate name="adjustaccountShowAF" property="list"
							id="element" indexId="i">
							<%
									j++;
									if (j % 2 == 0) {
										strClass = "td8";
									} else {
										strClass = "td9";
									}
							%>
							<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);'
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
										<bean:write name="element" property="orgHAFAccountFlow.docNum" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="element" property="empId" format="000000" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="element" property="emp.empInfo.name" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="element" property="emp.empInfo.cardNum" />
									</p>
								</td>
								<td valign="top">
									<p>
										-
										<bean:write name="element" property="credit" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="element"
											property="orgHAFAccountFlow.settDate" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="element"
											property="orgHAFAccountFlow.bizType" />
									</p>
								</td>

							</tr>
						</logic:iterate>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="center">
								<table width="585" height="19" border="0" cellpadding="0"
									cellspacing="0">
									<tr align="center">
										<td width="59">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td width="51"></td>
										<td>
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="showAdjustaccountAC.do" />
											</jsp:include>
										</td>


									</tr>
								</table>
							</td>
						</tr>
					</table>
				</logic:notEmpty>






				<logic:notEmpty name="adjustaccountShowAF"
					property="adjustAccountlist">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0"
									align="center">
									<tr>
										<td class=td6>
											合计：调整人数
											<u>：<bean:write name="pagination" property="nrOfElements" />
											</u>调整金额
											<u>：<bean:write name="adjustaccountShowAF"
													property="total" />
											</u>
										</td>
									</tr>
								</table>
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


					<table width="95%" border="0"  bgcolor="#76BEE9" cellspacing="1" cellpadding="3"
						align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								错账凭证号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('adjustWrongAccountTail.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="adjustWrongAccountTail.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('adjustWrongAccountTail.empName')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="adjustWrongAccountTail.empName">
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
								<a href="javascript:sort('adjustWrongAccountTail.adjustMoney')">调整金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="adjustWrongAccountTail.adjustMoney">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								错账日期
							</td>
							<td align="center" class=td2>
								调整业务类型
							</td>
						</tr><%
									int j = 0;
									String strClass = "";
							%>
						<logic:iterate name="adjustaccountShowAF"
							property="adjustAccountlist" id="elements" indexId="i">
							<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">

								<td valign="top">
									<p align="left">
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="elements" property="id"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</p>
								</td>
								<td valign="top">
									<p></p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="elements" property="empId" format="000000" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="elements" property="emp.empInfo.name" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="elements" property="emp.empInfo.cardNum" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="elements" property="adjustMoney" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="elements" property="settDate" />
									</p>
								</td>
								<td valign="top">
									<p>
										<bean:write name="elements"
											property="adjustWrongAccountHead.bizType" />
									</p>
								</td>
							</tr>
						</logic:iterate>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="center">
								<table width="585" height="19" border="0" cellpadding="0"
									cellspacing="0">
									<tr align="center">
										<td width="59">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td width="51"></td>
										<td>
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="showAdjustaccountAC.do" />
											</jsp:include>
										</td>


									</tr>
								</table>
							</td>
						</tr>
					</table>


				</logic:notEmpty>


				<logic:empty name="adjustaccountShowAF" property="adjustAccountlist">

					<logic:empty name="adjustaccountShowAF" property="list">

						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
											<td class=td6>
												合计：调整人数
												<u>：0</u>调整金额
												<u>：<bean:write name="adjustaccountShowAF"
														property="total" />
												</u>
											</td>
										</tr>
									</table>
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


						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr>
								<td align="center" height="23" bgcolor="C4F0FF">
								</td>
								<td align="center" class=td2>
									错账凭证号
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('')">职工编号</a>
									<logic:equal name="pagination" property="orderBy" value="">
										<logic:equal name="pagination" property="order" value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="order" value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('')">职工姓名</a>
									<logic:equal name="pagination" property="orderBy" value="">
										<logic:equal name="pagination" property="order" value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="order" value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									证件号码
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('')">调整金额</a>
									<logic:equal name="pagination" property="orderBy" value="">
										<logic:equal name="pagination" property="order" value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="order" value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									错账日期
								</td>
								<td align="center" class=td2>
									调整业务类型
								</td>
							</tr>
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
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
													<jsp:param name="url" value="showAdjustaccountAC.do" />
												</jsp:include>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>

					</logic:empty>


				</logic:empty>




				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr valign="bottom">
						<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
							<table height="20" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<logic:equal name="adjustaccountShowAF" property="type"
										value="">
										<td>
											<html:submit property="method" styleClass="buttona"
												disabled="true" onclick="gotoSave();">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												disabled="true">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												disabled="true" onclick="return confirmPrint();">
												<bean:message key="button.over.adjust" />
											</html:submit>
										</td>
									</logic:equal>
									<logic:equal name="adjustaccountShowAF" property="type"
										value="1">
										<td>
											<html:submit property="method" styleClass="buttona"
												disabled="true" onclick="gotoSave();">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												disabled="true">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return confirmPrint();">
												<bean:message key="button.over.adjust" />
											</html:submit>
										</td>
									</logic:equal>
									<logic:equal name="adjustaccountShowAF" property="type"
										value="2">
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="gotoSave();">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return confirm('请确认是否要删除所选择的记录');">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return confirmPrint();">
												<bean:message key="button.over.adjust" />
											</html:submit>
										</td>
									</logic:equal>
									<logic:equal name="adjustaccountShowAF" property="type"
										value="3">
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="gotoSave();">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												disabled="true" onclick="return confirm('请确认是否要删除所选择的记录');">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												disabled="true" onclick="return confirmPrint();">
												<bean:message key="button.over.adjust" />
											</html:submit>
										</td>
									</logic:equal>
									<logic:equal name="adjustaccountShowAF" property="type"
										value="4">
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="gotoSave();">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return confirm('请确认是否要删除所选择的记录');">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return confirmPrint();">
												<bean:message key="button.over.adjust" />
											</html:submit>
										</td>
									</logic:equal>
								</tr>
							</table>
						</td>
					</tr>
				</table>

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

