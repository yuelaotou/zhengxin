<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.paysure.action.ShowPaymentHeadAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	Object pagination = request.getSession(false).getAttribute(
	ShowPaymentHeadAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>缴存管理>>缴存登记>>正常汇缴</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/index.css" type="text/css">
	<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>

<script language="javascript" type="text/javascript">
var goin="";
var s1="";
var s2="";
var s3="";
var s8="";
function reportErrors() {
}
  
function findName() {
   	var queryString = "paymentTaFindACC.do?";
    var orgId = document.paymentAF.elements["orgId"].value;
    queryString = queryString + "orgId="+orgId; 	  
    findInfo(queryString);      
}

var tr="tr0"; 
function gettr(trindex) {
  	tr=trindex;
  	update1();
}
function gettr2(trindex) {
  	tr=trindex;
  	update1();
  	sureType();
}
function update1() {
  	var status=document.getElementById(tr).childNodes[10].childNodes[0].innerHTML.trim();
  	if(status=='登记'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
  	}else if(status=='确认'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
  	}else {
  		document.all.item(s1).disabled="false";
		document.all.item(s2).disabled="false";
  	}
}

function sureType(){
   	var status=document.getElementById(tr).childNodes[8].childNodes[0].innerHTML.trim();
 	if(status=='汇缴'){
  	 	var id=document.getElementById(tr).childNodes[11].childNodes[0].innerHTML;
		window.open('<%=request.getContextPath()%>/syscollection/paymng/monthpay/monthpayTbWindowShowAC.do?paymentid='+id+"&type=1",'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return false;
 	}
 	if(status=='单位补缴'){
  	 	var id=document.getElementById(tr).childNodes[11].childNodes[0].innerHTML;
  		window.open('<%=request.getContextPath()%>/syscollection/paymng/orgaddpay/orgaddpayTbWindowForwardURLAC.do?paymentid='+id+"&type=1",'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return false;
 	}
 	if(status=='个人补缴'){
  	 	var id=document.getElementById(tr).childNodes[11].childNodes[0].innerHTML;
  		window.open('<%=request.getContextPath()%>/syscollection/paymng/personaddpay/personaddpayTbWindowForwardAC.do?paymentid='+id+"&type=1",'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return false;
 	}
 	if(status=='单位挂账'){
  	 	var id=document.getElementById(tr).childNodes[11].childNodes[0].innerHTML;
  		window.open('<%=request.getContextPath()%>/syscollection/paymng/orgoverpay/orgoverpayTbWindowShowAC.do?paymentid='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return false;
 	}
 }
function loads(){
  	document.paymentAF.elements["orgId"].value="";
  	document.paymentAF.elements["orgName"].value="";
  	document.paymentAF.elements["noteNum"].value="";
  	document.paymentAF.elements["docNum"].value="";
  	document.paymentAF.elements["payMoney"].value="";
  	document.paymentAF.elements["settDate"].value="";
<%--  	document.paymentAF.elements["payStatus"].value="";--%>
  	document.paymentAF.elements["payType"].value="";
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
		if(document.all.item(i).value=="查询"){
				s8=i;
			}
			if(document.all.item(i).value=="到账确认"){
				s1=i;
			}
			if(document.all.item(i).value=="撤消到账确认"){
				s2=i;
			}
		}
	}
  	var list = document.all.listCount.value;
  
    if(list.length==2){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
    }else{
		update1();
    }
}

function gotoSure(){
	if(!confirm("确认要进行该次到账确认吗？")){
		return false;
	}else{
		var id = document.getElementById(tr).childNodes[11].childNodes[0].innerHTML.trim();
 		var queryString = "paymentPaySureACC.do?id="+id;
	    findInfo(queryString);
	    if(goin == 'yes'){
	    	if(confirm("此汇缴有变更，是否继续？")){
				return true;
			}else{
				return false;
			}
	    }else{
	    	return true;
	    }
	}
}
function display_yes(){
	goin = "yes";
}
function display_no(){
	goin = "no";
}
function gotoDelete(){
	if(!confirm("确定要进行该次撤消到账确认吗？")){
		return false;
	}
}
function gotocheck(){
   	var orgname=document.all.orgName.value.trim();
   	document.all.orgName.value=orgname;
   	var month=document.all.settDate.value.trim();
   	document.all.settDate.value=month;
   	var orgid=document.all.orgId.value.trim();
   	document.all.orgId.value=orgid;
   	var notenum=document.all.noteNum.value.trim();
   	document.all.noteNum.value=notenum;
   	var docnum=document.all.docNum.value.trim();
   	document.all.docNum.value=docnum;
  	var paymoney=document.all.payMoney.value.trim();
   	document.all.payMoney.value=paymoney;
   
   	if(orgid != ""){
   		if(isNaN(orgid)){
     		alert("请输入正确格式的单位编号！");
     		return false;
   		}
   	}
    if(docnum != ""){
      	if(isNaN(docnum)){
       	 	alert("请输入正确格式的凭证编号！");
       	 	return false;
      	}
   	}
   
   	if(paymoney != ""){
      	if(isNaN(paymoney)){
        	alert("请输入正确格式的缴存金额！");
        	return false;
      	}
   	}
   
   
   	if(month != ""){
   		if(month.length!=8)
  		{
    		alert("请输入八位的日期格式，例如20070101！");
    		return false;
  		}
    }
}
function keyvalue()
{
   	if(event.keyCode==13)
  	{
     	//event.keyCode=0;
      	document.all.item(s8).focus();
     	//document.getElementById("buttona").focus();
    	return false;
 	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads()"
	>

	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="to_paySuershoworg.do" />
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
							&nbsp;
						</td>
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
										valign="middle">
										<font color="00B5DB">缴存管理</font><font color="00B5DB">&gt;&gt;</font><font
											color="00B5DB">到账确认</font>
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
				<html:form action="/paySuerTaFindOrgAC.do" method="post"
					focus="orgId">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="162">
											<b class="font14"> 查 询 条 件</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/img/bg2.gif"
											align="center" width="758">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">

						<tr id="gjtr">
							<td colspan="4">
								<table width="100%" border="0" align="center" cellpadding=0
									cellspacing=1 id="table1">
									<tr>
										<td width="13%" class="td1">
											单位编号
										</td>
										<td width="18%">
											<html:text name="paymentAF" property="orgId"
												styleClass="input3" styleId="txtsearch"
												onkeydown="return keyvalue();"></html:text>
										</td>
										<td width="11%" class="td1">
											单位名称
										</td>
										<td width="21%" colspan="3">
											<html:text name="paymentAF" property="orgName"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td width="13%" class="td1">
											结算号
										</td>
										<td width="18%">
											<html:text name="paymentAF" property="noteNum"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
										<td width="11%" class="td1">
											凭证编号
										</td>
										<td width="21%" colspan="3">
											<html:text name="paymentAF" property="docNum"
												styleClass="input3" styleId="txtsearch"></html:text>
										</td>
									</tr>
									<tr>
										<td width="13%" class="td1">
											缴存金额
										</td>
										<td width="18%">
											<html:text name="paymentAF" property="payMoney"
												styleClass="input3"></html:text>
										</td>
										<td width="11%" class="td1">
											业务日期
										</td>
										<td width="9%">
											<html:text name="paymentAF" property="settDate"
												styleClass="input3" styleId="txtsearch" maxlength="8" />
										</td>
										<td width="3%">
											至
										</td>
										<td width="9%">
											<html:text name="paymentAF" property="settDate1"
												styleClass="input3" styleId="txtsearch" maxlength="8" />
										</td>
									</tr>
									<tr>
										<td width="13%" class="td1">
											业务类型
										</td>
										<td width="18%">
											<html:select property="payType" styleClass="input4">
												<html:option value=""></html:option>
												<html:optionsCollection property="other_map"
													name="thepaymentAF" label="value" value="key" />
											</html:select>
										</td>

										<td width="11%" class="td1">
											业务状态
										</td>
										<td width="21%" colspan="3">
											<html:select property="payStatus" styleClass="input4" name="thepaymentAF">
												<html:option value=""></html:option>
												<html:optionsCollection property="map" name="thepaymentAF"
													label="value" value="key" />
											</html:select>
									</tr>

								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return gotocheck();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>

				<html:form action="/paySuerTaOrgPayAccountListAC.do" method="post">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：缴存金额
								<u>:<bean:write name="thepaymentAF" property="sumPayMoney" />
								</u> 缴存人数
								<u>:<bean:write name="thepaymentAF" property="count" />
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="132">
											<b class="font14">缴存到账确认列表 </b>
										</td>
										<td width="542" height="22" align="center"
											background="<%=request.getContextPath()%>/img/bg2.gif">
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
								选项
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('paymentHead.noteNum')">结算号</a>
								<logic:equal name="pagination" property="orderBy"
									value="paymentHead.noteNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('paymentHead.docNum')">凭证编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="paymentHead.docNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy" value="org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								缴存日期
							</td>
							<td align="center" class=td2>
								缴存人数
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('paymentHead.payMoney')">缴存金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="paymentHead.payMoney">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								业务类型
							</td>
							<td align="center" class=td2>
								业务日期
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
						</tr>
						<td>
							<input type="hidden" name="listCount"
								value="<bean:write name="thepaymentAF" property="list"/>">
						</td>
						<logic:notEmpty name="thepaymentAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="element" name="thepaymentAF" property="list"
								indexId="i">
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
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="noteNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="docNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="org.id"
												format="0000000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<a href="#" onClick="gettr2('tr<%=i%>');"><bean:write
													name="element" property="org.orgInfo.name" />
											</a>
										</p>
									</td>
									<td valign="top" align="center">
										<p>
											<bean:write name="element" property="minMaxMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empCount" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payMoney" format="0.00" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payType" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="settDate" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payStatus_" />
										</p>
									</td>
									<td valign="top" style="display:none">
										<p>
											<bean:write name="element" property="id" />
										</p>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="thepaymentAF" property="list">
							<tr>
								<td colspan="13" height="30" style="color:red">
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
												<jsp:param name="url" value="to_paySuershoworg.do" />
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
									<td width="65">
										<html:submit property="method" styleClass="buttonb"
											onclick="return gotoSure(); ">
											<bean:message key="button.sure.account" />
										</html:submit>
										&nbsp;&nbsp;
									</td>
									<td width="65">
										<html:submit property="method" styleClass="buttonb"
											onclick="return gotoDelete(); ">
											<bean:message key="button.del.account" />
										</html:submit>
										&nbsp;
									</td>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>

	<table>
		<form action="paySuerShowOrgAC.do" method="POST" name="Form1"
			id="Form1">
		</form>
	</table>
</body>
</html:html>
