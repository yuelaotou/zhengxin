<%@ page language="java"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@page
	import="org.xpup.hafmis.syscollection.pickupmng.specialpickup.action.SpePickShowListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = session
			.getAttribute(SpePickShowListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>提取特权维护&gt;&gt;提取特权维护</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js"></script>
</head>
<script language="javascript">
var s1="";
var tr="tr0";
function loads(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="删除"){
				s1=i;
			}
		}
	}
	var status=document.getElementById(tr);
	if(status!=null){
		update();
	}else{
		document.all.item(s1).disabled="true";
	}
}
function gettr(trindex) {
  tr=trindex;
  update();
}
function update() {
  	var status=document.getElementById(tr).childNodes[10].innerHTML.trim();
  	if(status=="未使用"){
		document.all.item(s1).disabled="";
	}
  	if(status=="已使用"){
		document.all.item(s1).disabled="true";
	}  
}
function checkDetele(){
	var x=confirm("确定删除记录?");
		if(x){ 
		  	return true;
		}else{
		  return false;
		}
}
function checkYMD1(){
	var ymd=document.spePickListAF.operateTime1.value.trim();
	if(ymd.length!=0){
		var temp_date=ymd.match(/^([0-9]{8})$/); 
		if (temp_date==null){
	        alert("请正确录入起始操作日期信息！格式如：20010101");
			document.spePickListAF.operateTime1.focus();
			return false;
		}else{
			return true;
		}
	}
	return true;
}
function checkYMD2(){
	var ymd=document.spePickListAF.operateTime2.value.trim();
	if(ymd.length!=0){
		var temp_date=ymd.match(/^([0-9]{8})$/); 
		if (temp_date==null){
	        alert("请正确录入终止操作日期信息！格式如：20010101");
			document.spePickListAF.operateTime2.focus();
			return false;
		}else{
			return true;
		}
	}
	return true;
}
function search(){
	var id=document.spePickListAF.id.value.trim();
	var name=document.spePickListAF.name.value.trim();
	var collectionBank=document.spePickListAF.collectionBank.value.trim();
	var officecode=document.spePickListAF.officecode.value.trim();
	var operateTime1=document.spePickListAF.operateTime1.value.trim();
	var operateTime2=document.spePickListAF.operateTime2.value.trim();
	document.URL='spePickFindListAC.do?id='+id+'&name='+name+'&collectionBank='+collectionBank+'&officecode='+officecode+'&operateTime1='+operateTime1+'&operateTime2='+operateTime2+'';
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function a(){
	var p = "<%=path%>";
	officeAjax(p);
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="spePickShowListAC.do" />
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
										<a href="spepickshowAC.do" class=a2>办理提取审批
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										提取审批维护
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
									<td width="216" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB"> 提取管理 &gt;提取审批维护 </font>
									</td>
									<td width="47" class=font14>
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
				<html:form action="/spePickFindListAC.do">
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
							<td colspan="2">
								<html:text property="id" styleClass="input3"
									onkeydown="enterNextFocus1();" styleId="txtsearch" />
							</td>
							<td width="15%" class="td1">
								单位名称
							</td>
							<td width="35%">
								<html:text property="name" styleClass="input3"
									onkeydown="enterNextFocus1();" styleId="txtsearch" />
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								归集银行
							</td>
							<td colspan="2">
								<html:select property="collectionBankId" styleClass="input4">
									<option value=""></option>
									<html:options collection="loanbankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="15%" class="td1">
								办事处
							</td>
							<td width="35%">
								<html:select property="officeCode" styleClass="input4"
									onchange="a();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="15%" class="td1">
								操作日期
							</td>
							<td colspan="2">
								<html:text property="operateTime1" onblur="return checkYMD1();"
									styleClass="input3" onkeydown="enterNextFocus1();"
									styleId="txtsearch" />
							</td>
							<td width="15%" class="td1">
								至
							</td>
							<td width="35%">
								<html:text property="operateTime2" onblur="return checkYMD2();"
									styleClass="input3" onkeydown="enterNextFocus1();"
									styleId="txtsearch" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="search();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>

				<html:form action="/spepickmaintainac" styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：提取人数
								<u>：<bean:write name="specialPick1" property="pickPeopleNum" />
								</u> 提取金额
								<u>：<bean:write name="specialPick1" property="pickCorpusSum"
										format="0.00" />
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="115">
											<b class="font14">提取审批列表</b>
										</td>
										<td width="552" height="22" align="center"
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
							<td width="4%" height="23" align="center" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td width="9%" align="center" class=td2>
								<a href="javascript:sort('specialPick.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="specialPick.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td width="9%" align="center" class=td2>
								<a href="javascript:sort('specialPick.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="specialPick.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td width="9%" align="center" class=td2>
								<a href="javascript:sort('specialPick.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="specialPick.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td width="9%" align="center" class=td2>
								<a href="javascript:sort('specialPick.empName')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="specialPick.empName">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td width="10%" align="center" class=td2>
								办事处
							</td>
							<td width="10%" align="center" class=td2>
								归集银行
							</td>
							<td width="9%" align="center" class=td2>
								<a href="javascript:sort('specialPick.pickCorpus')">提取金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="specialPick.pickCorpus">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td width="9%" align="center" class=td2>
								操作日期
							</td>
							<td width="8%" align="center" class=td2>
								办理人员
							</td>
							<td width="8%" align="center" class=td2>
								提取状态
							</td>
						</tr>
						<logic:notEmpty name="specialPick">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="specialPick" name="specialPick" indexId="i">
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
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="specialPick" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<bean:write name="specialPick" property="org.id"
											format="0000000000" />
									</td>
									<td valign="top">
										<bean:write name="specialPick" property="org.orgInfo.name" />
									</td>
									<td valign="top">
										<bean:write name="specialPick" property="empId"
											format="000000" />
									</td>
									<td valign="top">
										<bean:write name="specialPick" property="empName" />
									</td>
									<td valign="top">
										<bean:write name="specialPick"
											property="org.orgInfo.temp_officename" />
									</td>
									<td valign="top">
										<bean:write name="specialPick"
											property="org.orgInfo.temp_collectionBankname" />
									</td>
									<td valign="top">
										<bean:write name="specialPick" property="pickCorpus"
											format="0.00" />
									</td>
									<td valign="top">
										<bean:write name="specialPick" property="temp_operateTime" />
									</td>
									<td valign="top">
										<bean:write name="specialPick" property="operator" />
									</td>
									<td valign="top">
										<bean:write name="specialPick" property="temp_isPick" />
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="specialPick">
							<tr>
								<td colspan="11" height="30" style="color:red">
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
												<jsp:param name="url" value="spePickShowListAC.do" />
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
										<td width="75">
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDetele();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<form action="spePickShowListAC.do" method="POST" name="Form1"
					id="Form1">
				</form>
	</table>
</body>
</html:html>


