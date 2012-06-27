<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>

<%@ page
	import="org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonDoListAC"%>

<%
			Object pagination = request.getSession(false).getAttribute(
			ShowChgpersonDoListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String typetem = (String) request.getAttribute("typetem");
	String statetype = (String) request.getAttribute("statetype");
%>
<html:html>
<head>
	<title>人员变更-办理变更</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>

<script type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
var s6="";
var s7="";
var s8="";
var s9="";
var tr="tr0";

var typetem="<%=typetem%>";
var statetype="<%=statetype%>";

var typetem="<%=typetem%>";

function loads(){
	var orgid=document.chgpersonDoListAF.elements["id"].value;
	if(orgid!=""){
		document.chgpersonDoListAF.elements["id"].value=format(document.chgpersonDoListAF.elements["id"].value)+document.chgpersonDoListAF.elements["id"].value;
	}

    
    var pdpb1=0;
    var pdpb2=0;
    
    
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="批量导出"){
				s1=i;
			}
			if(document.all.item(i).value=="批量导入"){
				s2=i;
			}
			if(document.all.item(i).value=="添加"){
				s3=i;
			}
			if(document.all.item(i).value=="删除"){
				s4=i;
			}
			if(document.all.item(i).value=="全部删除"){
				s5=i;
			}
			if(document.all.item(i).value=="启用"){
				s6=i;
			}
			if(typetem==1){
				if(document.all.item(i).value=="提交数据"){
					s7=i;
				}
				if(document.all.item(i).value=="撤销提交数据"){
	
					s8=i;		
				}
				pdpb1=11;
			}
			if(typetem==2){
				if(document.all.item(i).value=="提取单位版数据"){
					s9=i;	
					pdpb2=11;	
				}
				
			}
			if(document.all.item(i).value=="自动启封"){
				s10=i;
			}
		}
	}
	
	var types=document.chgpersonDoListAF.type.value;
	if(types==1){
	  
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		if(typetem==1){
		if(pdpb1=='11'){
				document.all.item(s7).disabled="true";
		        document.all.item(s8).disabled="true";
		}

		}
		if(typetem==2){
		if(pdpb2=='11'){
		document.all.item(s9).disabled="true";
		}
		
		}
	}
	if(types==2){
	
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		if(typetem==1){
		if(pdpb1=='11'){
				document.all.item(s7).disabled="true";
		        document.all.item(s8).disabled="true";}

		}
		if(typetem==2){
		if(pdpb2=='11'){
				document.all.item(s9).disabled="";}

		}
	}
	if(types==3){

		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="";
		document.all.item(s6).disabled="";
		if(typetem==1){
		if(pdpb1=='11'){
		 if(statetype=="未提取"){
		   document.all.item(s7).disabled="true";
		   document.all.item(s8).disabled="";}
		   if(statetype=="已提取"){
		   document.all.item(s7).disabled="true";
		   document.all.item(s8).disabled="true";}
		   if(statetype=="未提交"){
		   document.all.item(s7).disabled="";
		   document.all.item(s8).disabled="true";}
		   }
		  
		//document.all.item(s7).disabled="";
		//document.all.item(s8).disabled="";
		}
		if(typetem==2){
		if(pdpb2=='11'){
			document.all.item(s9).disabled="true";
			}
	
		}
	}

}

function reportErrors(message) {
	if(message!=null){
		alert(message);
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function findEmployeeInfo(){
	var x = document.chgpersonDoListAF.elements["id"].value.trim();
	if(x==""){
		toPop();
	}else if(isNaN(x)){
        	alert("请输入正确格式的编号！");
        	document.chgpersonDoListAF.elements["id"].focus();
        	return false;
    }else{
	    var queryString = "chgpersonFindEmpListAAC.do?";
	    queryString = queryString + "orgID="+document.chgpersonDoListAF.elements["id"].value;
	    findInfo(queryString);
	}
}

function showlist() {
  	document.Form1.submit();
}

function toPop() {
	gotoOrgpop(2,'<%=path%>','0');
}

function executeAjax(){
	var x = document.chgpersonDoListAF.elements["id"].value.trim();
	if(x==""){
		toPop();
	}else if(isNaN(x)){
       	alert("请输入正确格式的编号！");
       	document.chgpersonDoListAF.elements["id"].focus();
       	return false;
    }
	else{
	    var queryString = "chgpersonFindEmpList_AjaxAAC.do?";
	    queryString = queryString + "orgID="+document.chgpersonDoListAF.elements["id"].value;     
		findInfo(queryString);
	}
}

function displays(id,name){
  	document.chgpersonDoListAF.elements["id"].value=id;
  	document.chgpersonDoListAF.elements["name"].value=name;
	window.close();
  	var queryString = "autoChangeAAC.do?";
 	queryString = queryString + "orgID="+id;     
  	findInfo(queryString);
  
}
function display_autoChange(flag,ifs){
	if(flag==1){
		showlist();
		window.open('<%=path%>/syscollection/chgbiz/chgperson/autoChangePopShowAC.do?type=1&orgID='+document.chgpersonDoListAF.elements["id"].value,"window1","height=450,width=700,top=300,left=300,scrollbars=yes, status=yes"); 
	}else{
		showlist();
	}
}
function display_automess(){
	window.close();
  	var queryString = "autoChangeAAC.do?";
  	queryString = queryString + "orgID="+document.chgpersonDoListAF.elements["id"].value;     
  	findInfo(queryString);
}
function checkAdd(){  
	var x=document.chgpersonDoListAF.elements["date"].value.trim();
	var temp_date=x.match(/^(([2][0]|[1][9])\d{2}([0][1-9]|[1][0-2]))$/); 
	if (temp_date==null){  
        alert("请录入变更年月！格式如：200706");
		document.chgpersonDoListAF.elements["date"].focus();
		return false;
	}
	document.chgpersonDoIdAF.elements["chgDate"].value=x;
	return true;  
}

function checkDetele(){
	var x=confirm("确定删除记录?");
		if(x){ 
		  	return true;
		}else{
		  return false;
		}
}

function checkDeteleAll(){
	var x=confirm("确定全部删除记录?");
		if(x){ 
		  	return true;	
		}else{
		  return false;
		}
}

function checkChgDate(){
	var chgDate = document.chgpersonDoListAF.elements["date"].value;
	if(chgDate==""||chgDate.length==0){
		alert("请录入调整年月");
		document.chgpersonDoListAF.elements["date"].focus();
		return false;
	}else{
		 var x=confirm("确定启用记录?");
		  if(x){ 	
		  		var x=document.chgpersonDoListAF.elements["date"].value.trim();
				document.chgpersonDoIdAF.elements["chgDate"].value=x;
		  		return true;
		  }else{
		  	return false;
		  }
	}
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

function exportArray(){
	//	update();
	var orgId=document.chgpersonDoListAF.elements["id"].value;
	var chgMonth=document.chgpersonDoListAF.elements["date"].value;
	//这个留在备用，可以操作主页面  									
	window.open ('<%=path%>/syscollection/chgbiz/chgperson/chgpersonexport_array.jsp?orgId='+ orgId +'&chgMonth='+chgMonth,'newwindow','height=190,width=350,top='+(window.screen.availHeight-190)/2+',left='+(window.screen.availWidth-350)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	//看来这个留着备用把，因为使用他确定后，会弹出一个页面，在这个页面里出现导出的对话框。
	//window.showModalDialog('<%=path%>/syscollection/chgbiz/chgpay/chgpayexport_array.jsp?orgId='+ orgId +'&orgName='+orgName+'&chgMonth='+chgMonth,'newwindow','height=800,width=800,top=800,left=800,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	return false;
}
function autoopen(){
	var orgId=document.chgpersonDoListAF.elements["id"].value;
	var chgMonth=document.chgpersonDoListAF.elements["date"].value;
	 //这个留在备用，可以操作主页面  									
	window.open ('<%=path%>/syscollection/chgbiz/chgperson/chgPersonAutoopenShowAC.do?type=1&orgId='+ orgId +'&chgMonth='+chgMonth,'newwindow',"height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
	return false;
}
function childFunction(){
 	document.location.href="autoChangeSaveALLAC.do";
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads(); reportErrors();">
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
										background="<%=path%>/img/buttonbl.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										办理变更
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="chgpersonMaintainForwardURLAC.do" class=a2>变更维护</a>
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
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">变更业务</font><font color="00B5DB">&gt;人员变更</font>
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


				<html:form action="/showChgpersonDoListAC.do">

					<input type="hidden" name="type"
						value="<bean:write name="chgpersonDoListAF" property="type"/>">

					<logic:notEqual name="flag" value="2">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="17%" class="td1" algin="center">
									单位编号
								</td>
								<td width="22%">
									<html:text name="chgpersonDoListAF" property="id"
										styleClass="input3" onkeydown="goEnter();" styleId="txtsearch"
										ondblclick="executeAjax();">
									</html:text>

								</td>
								<td width="11%">
									<input type="button" name="searchbutton" value="..."
										onclick="toPop()" class="buttona" />
								</td>
								<td class="td1" width="17%" algin="center">
									单位名称
								</td>
								<td width="33%">
									<html:text name="chgpersonDoListAF" property="name"
										styleClass="input3" styleId="txtsearch" readonly="true"></html:text>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									变更年月
								</td>
								<td width="33%" colspan="2">
									<html:text name="chgpersonDoListAF" property="date"
										styleClass="input3" onkeydown="" styleId="txtsearch"></html:text>
								</td>
								<td width="17%">
									&nbsp;
								</td>
							</tr>
						</table>
					</logic:notEqual>

					<logic:equal name="flag" value="2">

						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="17%" class="td1" algin="center">
									单位编号
								</td>
								<td width="22%">
									<html:text name="chgpersonDoListAF" property="id"
										styleClass="input3" styleId="txtsearch" readonly="true">
									</html:text>

								</td>
								<td width="11%">
									<input type="button" name="searchbutton" value="..."
										onclick="toPop()" class="buttona" disabled="disabled" />
								</td>
								<td class="td1" width="17%" algin="center">
									单位名称
								</td>
								<td width="33%">
									<html:text name="chgpersonDoListAF" property="name"
										styleClass="input3" styleId="txtsearch" readonly="true"></html:text>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									变更年月
								</td>
								<td width="33%" colspan="2">
									<html:text name="chgpersonDoListAF" property="date"
										styleClass="input3" styleId="txtsearch"></html:text>
								</td>
								<td width="17%">
									&nbsp;
								</td>
							</tr>
						</table>
					</logic:equal>
				</html:form>


				<html:form action="/maintainChgpersonDoListAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>

							<logic:notEmpty name="chgpersonAF">
								<td align="left" height="33" class="h4">
									合计:
									<br>
									调整前：汇缴人数
									<u><bean:write name="chgpersonAF"
											property="beforeChgperson" /> </u> 汇缴金额:
									<u><bean:write name="chgpersonAF" property="oldOldPayment" />
									</u>
									<br>
									调整后：汇缴人数
									<u><bean:write name="chgpersonAF" property="sumChgPerson" />
									</u> 汇缴金额:
									<u><bean:write name="chgpersonAF" property="newOldPayment" />
									</u> 增加人数:
									<u><bean:write name="chgpersonAF" property="insChgperson" />
									</u> 增加缴额:
									<u><bean:write name="chgpersonAF" property="insPayment" />
									</u> 减少人数:
									<u><bean:write name="chgpersonAF" property="mulChgperson" />
									</u> 减少缴额:
									<u><bean:write name="chgpersonAF" property="mulPayment" />
									</u>

								</td>
							</logic:notEmpty>

							<logic:empty name="chgpersonAF">
								<td align="left" height="33" class="h4">
									合计:
									<br>
									调整前：汇缴人数
									<u>0 </u> 汇缴金额:
									<u>0.0 </u>
									<br>
									调整后：汇缴人数
									<u>0 </u> 汇缴金额:
									<u>0</u>.0 增加人数:
									<u>0</u> 增加缴额:
									<u>0.0 </u> 减少人数:0
									<u> 减少缴额:</u>
									<u>0.0</u>
								</td>
							</logic:empty>

						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">人员变更列表</b>
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
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonTail.chgType')">变更类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonTail.chgType">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonTail.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonTail.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('chgPersonTail.name')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="chgPersonTail.name">
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
								变更后职工状态
							</td>
							<td align="center" class=td2>
								工资基数
							</td>
							<td align="center" class=td2>
								单位缴额
							</td>
							<td align="center" class=td2>
								职工缴额
							</td>
							<td align="center" class=td2>
								缴额合计
							</td>
							<td align="center" class=td2>
								变更原因
							</td>
						</tr>
						<logic:notEmpty name="chgpersonDoListAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="chgpersonDoListAF" property="list"
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
									onclick='gotoClickpp("<%=i%>", chgpersonDoIdAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", chgpersonDoIdAF);'
									class="<%=strClass%>" onDblClick="">

									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="chgType" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empId" format="000000" />
										</p>
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
											<bean:write name="element" property="temp_oldPayStatus" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="salaryBase" />
										</p>
									</td>

									<td valign="top">
										<p>
											<bean:write name="element" property="orgPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="sumPay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="temp_chgreason" />
										</p>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="chgpersonDoListAF" property="list">
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
												<jsp:param name="url" value="showChgpersonDoListAC.do" />
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
							<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<html:hidden name="chgpersonDoIdAF" property="chgDate" />
										<security:orghave>
											<security:orgcannot>
												<td>
													<html:submit property="method" styleClass="buttonc"
														disabled="true">
														<bean:message key="button.pickup.data" />
													</html:submit>
												</td>
											</security:orgcannot>
										</security:orghave>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return exportArray()">
												<bean:message key="button.exports" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.imports" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkAdd()">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDetele()">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkDeteleAll()">
												<bean:message key="button.deleteall" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return checkChgDate()">
												<bean:message key="button.use" />
											</html:submit>
										</td>
										<td>
											<security:orghave>
												<security:orgcan>
													<html:submit property="method" styleClass="buttona"
														disabled="true" onclick="return checkDataReferring()">
														<bean:message key="button.referring.data" />
													</html:submit>
												</security:orgcan>
											</security:orghave>
										</td>
										<td>
											<security:orghave>
												<security:orgcan>
													<html:submit property="method" styleClass="buttonc"
														disabled="true" onclick="return checkDatapproval()">
														<bean:message key="button.pproval.data" />
													</html:submit>
												</security:orgcan>
											</security:orghave>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return autoopen()">
												<bean:message key="button.autoopen" />
											</html:submit>
										</td>
										<logic:empty name="chgpersonDoListAF" property="list">
											<td>
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.database.exports" />
												</html:submit>
											</td>
										</logic:empty>
										<logic:notEmpty name="chgpersonDoListAF" property="list">
											<td>
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.database.exports" />
												</html:submit>
											</td>
										</logic:notEmpty>

									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>

				<form action="showChgpersonDoListAC.do" method="POST" name="Form1"
					id="Form1">
				</form>
	</table>
</body>
</html:html>
