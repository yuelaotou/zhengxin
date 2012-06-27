<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			PersonAddPayTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String type = (String) request.getAttribute("type");
	String statetype = (String) request.getAttribute("statetype");
%>
<html:html>
<head>
	<title>个人补缴</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
var s6="";
var s7="";
var s8="";
var s9="";
var s10="";
var type="<%=type%>";// 版本
var statetype="<%=statetype%>"
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
  
function executeAjax() {
	doRequestUsingGET();
}


var xmlHttp;
function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}
function doRequestUsingGET() {
	
 	createXMLHttpRequest();
 
    var queryString = "<%=path%>/syscollection/paymng/personaddpay/personAddPayTaFindInforAAC.do?";
    var orgId = document.getElementById("orgId").value.trim();   
    queryString = queryString + "orgId="+orgId; 
    if(orgId!=""){
	    xmlHttp.onreadystatechange = handleStateChange;
	    xmlHttp.open("GET", queryString, true);
	    xmlHttp.send(null);   
    }else if(orgId==""){
    	gotoSelect();
    }
}
function handleStateChange() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
        var x=xmlHttp.responseText;
      if(x.length>0)//查不到数据为了跳过此方法作的判断
      {
      	eval(x);
      }
      else
      {
      	alert("该单位不在权限范围内");
	      document.personAddPayAF.orgId.value="";
	      document.personAddPayAF.unitName.value="";
	      document.all.item(s1).disabled="true";
	      document.all.item(s2).disabled="true";
	      document.all.item(s3).disabled="true";
	      document.all.item(s4).disabled="true";
	      document.all.item(s5).disabled="true";
	      document.all.item(s6).disabled="true";
	      document.all.item(s7).disabled="true";
	      document.personAddPayAF.orgId.focus();
	      //showlist();
      }
         
      }
   }
}
function showlist() {
  document.Form1.submit();
}
function displays(orgStatus,orgid,unitName,docNumber,receivables_orgname,receivables_bank_acc,receivables_bank_name
    ,payment_orgname,payment_bank_acc,payment_bank_name){
  document.getElementById("orgId").value=orgid;
  document.getElementById("unitName").value=unitName;
  document.getElementById("docNumber").value=docNumber;
  document.getElementById("receivables_orgname").value=receivables_orgname;
  document.getElementById("receivables_bank_acc").value=receivables_bank_acc;
  if(receivables_bank_name=="农行柳城支行"){
  	document.getElementById("receivables_bank_name").value="中行渤海支行";
  }else{
  	document.getElementById("receivables_bank_name").value=receivables_bank_name;
  }
  document.getElementById("payment_orgname").value=payment_orgname;
  document.getElementById("payment_bank_acc").value=payment_bank_acc;
  document.getElementById("payment_bank_name").value=payment_bank_name;
  if(orgStatus=="abnormal"){
  alert("目前该单位的状态不正常,暂时不能办理业务!");
  }else{
  showlist();
  } 
}

function gotoSelect(){
gotoOrgpop("2" ,'<%=path%>','0');
}

function empAddPay(){
	document.getElementById("types").value="add";
	document.getElementById("noteNumber").value=document.personAddPayAF.docNumber.value;
	document.getElementById("unitNumber").value=document.personAddPayAF.orgId.value;
	
	document.getElementById("r_orgname").value=document.personAddPayAF.orgId.receivables_orgname;
	document.getElementById("r_bank_name").value=document.personAddPayAF.receivables_bank_name.value;
	document.getElementById("r_bank_acc").value=document.personAddPayAF.receivables_bank_acc.value;
	document.getElementById("p_orgname").value=document.personAddPayAF.payment_orgname.value;
	document.getElementById("p_bank_acc").value=document.personAddPayAF.payment_bank_acc.value;
	document.getElementById("p_bank_name").value=document.personAddPayAF.payment_bank_name.value;
	document.getElementById("p_Way").value=document.personAddPayAF.payWay.value;
	document.getElementById("p_Kind").value=document.personAddPayAF.payKind.value;
	document.all.payment_orgnames.value=document.personAddPayAF.payment_orgname.value;
}
function empAddPayUpdate(){
document.getElementById("types").value="update";
}
function loads(){
    var count=document.all.listCount.value;
	var orgid=document.all.orgId.value;	
	if(orgid != ""){
		document.all.orgId.value=formatTen(orgid)+orgid;
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
			if(document.all.item(i).value=="删除"){
				s3=i;
			}
			if(document.all.item(i).value=="全部删除"){
				s4=i;
			}
			if(document.all.item(i).value=="完成补缴登记"){
				s5=i;
			}
		    if(document.all.item(i).value=="添加"){
				s6=i;
			}
		    if(document.all.item(i).value=="修改"){
				s7=i;
			}
			<security:orghave>
			if(type!=2){
				if(document.all.item(i).value=="提交数据"){
					s8=i;
				}
			    if(document.all.item(i).value=="撤销提交数据"){
					s9=i;
				}
				pdpb1=11;
			}
			if(type!=1){
				if(document.all.item(i).value=="提取单位版数据"){
					s10=i;
				}
				pdpb2=11;
			}
			</security:orghave>
		}
	}
		if(orgid==""){
		<security:orghave>
		if(type!=1){
		if(pdpb2=='11'){
			document.all.item(s10).disabled="true";
		}
		}
		</security:orghave>
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		document.all.item(s7).disabled="true";
		<security:orghave>
		if(type!=2){
		if(pdpb1=='11'){
			document.all.item(s8).disabled="true";
			document.all.item(s9).disabled="true";
		}
		}
		</security:orghave>
	}
	if(count=="1"){
	<security:orghave>
	 	if(type!=1){
	 	if(pdpb2=='11'){
			document.all.item(s10).disabled="true";
		}
		}
		</security:orghave>
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
	    document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="";
		document.all.item(s6).disabled="";
		document.all.item(s7).disabled="";
		<security:orghave>
		if(type!=2){
		if(pdpb1=='11'){	
		if(statetype=="未提取"){
		      document.all.item(s8).disabled="true";
		      document.all.item(s9).disabled="";
	       }
	       if(statetype=="已提取"){
		      document.all.item(s8).disabled="true";
		      document.all.item(s9).disabled="true";
	       }
	       if(statetype=="未提交"){
		      document.all.item(s8).disabled="";
		      document.all.item(s9).disabled="true";
	       }
		}
		}
		</security:orghave>
		
	}else if(count=="3"){
	<security:orghave>
	    if(type!=1){
	    if(pdpb2=='11'){
			document.all.item(s10).disabled="";
		}   
		}
		</security:orghave>
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="";
		document.all.item(s7).disabled="true";
		<security:orghave>
		if(type!=2){
		if(pdpb1=='11'){
			document.all.item(s8).disabled="true";
			document.all.item(s9).disabled="true";
		}
		}
		</security:orghave>
	}else{
	<security:orghave>
	  	if(type!=1){
	  	if(pdpb2=='11'){
			document.all.item(s10).disabled="true";
		}
		}
		</security:orghave>
		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		document.all.item(s7).disabled="true";
		<security:orghave>
		if(type!=2){
		if(pdpb1=='11'){
			document.all.item(s8).disabled="true";
			document.all.item(s9).disabled="true";
		}
		}
		</security:orghave>
	}	
	
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
function deleteConfirm(){
if(confirm("是否确定删除该条记录?")){
return true;
}
else{
return false;
}
}
function deleteAllConfirm(){
	if(confirm("是否确定删除全部记录?")){
		return true;
	} else {
		return false;
	}
}

function addpayConfirm(){
var payWay = document.all.payWay.value;
	document.getElementById("p_bank_acc").value=document.personAddPayAF.payment_bank_acc.value;
	document.getElementById("p_bank_name").value=document.personAddPayAF.payment_bank_name.value;
if(payWay == ""){
	alert("请录入缴存方式！");
	return false;
}
document.getElementById("p_Way").value=document.personAddPayAF.payWay.value;
document.getElementById("p_Kind").value=document.personAddPayAF.payKind.value;
if(confirm("确认要进行该次补缴登记吗?")){
document.all.noteNumber.value=document.personAddPayAF.elements["docNumber"].value;
<%--if(confirm("是否打印")){--%>
<%--document.all.printReport.value="isPrint";--%>
<%--}--%>
<%--else{--%>
document.all.printReport.value="noPrint";
<%--}--%>

return true;
}
else{
return false;
}

}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function exportconfirm(){
document.getElementById("unitNumber").value=document.all.personAddPayAF.unitName.value;
document.getElementById("noteNumber").value=document.all.personAddPayAF.docNumber.value;
}

function referConfirm(){
	if(confirm("是否确定提交该笔业务?")){
		return true;
	} else {
		return false;
	}
}

function pprovalConfirm(){
	if(confirm("是否确定撤销提交该笔业务?")){
		return true;
	} else {
		return false;
	}
	}
	//导出排序
function exportArray(){
var k= gotoExport();
if(k==false)
	return false;
 exportconfirm();
	 var orgId=document.getElementById("orgId").value
	  var noteNum=document.getElementById("noteNumber").value
	   var payKind=document.all.payKind.value;
	 var payWay=document.all.payWay.value;
	window.open ('<%=path%>/syscollection/paymng/personaddpay/personaddpayexport_array.jsp?orgId='+ orgId +'&noteNum='+noteNum+'&payWay='+payWay+'&payKind='+payKind,'newwindow','height=190,width=350,top='+(window.screen.availHeight-190)/2+',left='+(window.screen.availWidth-350)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	return false;
}
function gotoExport(){
	var orgid=document.all.orgId.value;
	var orgname=document.all.unitName.value;
	if(orgname == ""){
		alert("请输入系统中存在的单位编号后在导出！");
		document.all.orgId.focus();
		return false;
	}
}
function gotoImport(){

document.all.payment_orgnames.value=document.personAddPayAF.payment_orgname.value;
document.getElementById("p_Way").value=document.personAddPayAF.payWay.value;
document.getElementById("p_Kind").value=document.personAddPayAF.payKind.value;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="personAddPayTaShowAC.do" />
	</jsp:include>
	<input type="hidden" name="types" id="types" value="" />

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
										办理缴存
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a
											href="<%=path%>/syscollection/paymng/personaddpay/personaddpayTbForwardUrlAC.do"
											class="a2">缴存维护</a>
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
									<td width="136" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">缴存管理&gt;个人补缴</font>
									</td>
									<td width="127" class=font14>
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
										<b class="font14">缴 存 信 息</b>
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
				<html:form action="/personAddPayTaShowAC.do" style="margin: 0"
					focus="orgId">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1" algin="center">
								单位编号
							</td>
							<td width="22%">
								<html:text name="personAddPayAF" property="orgId"
									styleClass="input3" onkeydown="gotoEnter();" styleId="orgId"
									ondblclick="doRequestUsingGET();">
								</html:text>

								<input type="hidden" name="orgsid"
									value="<bean:write name="personAddPayAF" property="orgId"/>">
							<td width="8%">
								<input type="button" class="buttona" value="..."
									onClick="gotoSelect();" name="button" />
							</td>
							<td class="td1" width="17%" algin="center">
								单位名称
							</td>
							<td width="22%">
								<html:text name="personAddPayAF" property="unitName"
									styleClass="input3" onkeydown="goEnter();" styleId="unitName"
									readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								收款单位名称
							</td>
							<td width="22%">
								<html:text name="personAddPayAF" property="receivables_orgname"
									styleClass="input3" styleId="receivables_orgname"
									onkeydown="gotoEnter();">
								</html:text>
							<td width="8%">
							</td>
							<td class="td1" width="17%" algin="center">
								付款单位名称
							</td>
							<td width="22%">
								<html:text name="personAddPayAF" property="payment_orgname"
									styleClass="input3" styleId="payment_orgname"
									onkeydown="gotoEnter();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								收款单位归集银行名称
							</td>
							<td width="22%">
								<logic:equal name="personAddPayAF" property="receivables_bank_name" value="农行柳城支行">
									<html:text name="personAddPayAF" property="receivables_bank_name" value="中行渤海支行"
										styleClass="input3" styleId="txtsearch" />
								</logic:equal>
								<logic:notEqual name="personAddPayAF" property="receivables_bank_name" value="农行柳城支行">
									<html:text name="personAddPayAF" property="receivables_bank_name"
										styleClass="input3" styleId="txtsearch" />
								</logic:notEqual>
							<td width="8%">
							</td>
							<td class="td1" width="17%" algin="center">
								付款单位开户银行名称
							</td>
							<td width="22%">
								<html:text name="personAddPayAF" property="payment_bank_name"
									styleClass="input3" styleId="payment_bank_name"
									onkeydown="gotoEnter();"></html:text>
							</td>
						</tr>
						<tr>

							<td width="17%" class="td1" algin="center">
								收款单位归集银行账号
							</td>
							<td width="22%">
								<html:text name="personAddPayAF" property="receivables_bank_acc"
									styleClass="input3" styleId="receivables_bank_acc"
									onkeydown="gotoEnter();">
								</html:text>
							<td width="8%">
							</td>
							<td class="td1" width="17%" algin="center">
								付款单位开户银行账号
							</td>
							<td width="22%">
								<html:text name="personAddPayAF" property="payment_bank_acc"
									styleClass="input3" styleId="payment_bank_acc"
									onkeydown="gotoEnter();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								缴存方式
							</td>
							<td width="22%">
								<html:select property="payWay" styleClass="input4">
									<html:optionsCollection property="pay_way"
										name="personAddPayAF" label="value" value="key" />
								</html:select>
							<td width="8%">
							</td>
							<td class="td1" width="17%" algin="center">
								缴存类别
							</td>
							<td width="22%">
								<html:select property="payKind" styleClass="input4">
									<html:optionsCollection property="pay_kind"
										name="personAddPayAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								结算号
							</td>
							<td width="22%">
								<html:text name="personAddPayAF" property="docNumber"
									styleClass="input3" onkeydown="goEnter();" styleId="docNumber">
								</html:text>
							</td>
							<td width="8%">
								<input type="hidden" name="listCount"
									value="<bean:write name="personAddPayAF" property="listCount" />">
							</td>
							<td class="td1" width="17%" algin="center">
								挂账余额
							</td>
							<td width="22%">
								<html:text name="personAddPayAF" property="overPay"
									styleClass="input3" />
							</td>
						</tr>

					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								总计： 补缴人数
								<u>：<bean:write name="personAddPayAF" property="personSum" />
								</u> 单位补缴金额
								<u>：<bean:write name="personAddPayAF" property="orgPaySum" />
								</u> 个人补缴金额
								<u>：<bean:write name="personAddPayAF" property="empPaySum" />
								</u> 补缴金额
								<u>：<bean:write name="personAddPayAF" property="paySum" />
								</u>
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
										<b class="font14">个人补缴列表</b>
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
				<html:form action="/personAddPayTaMaintainAC.do" style="margin: 0">

					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
								<input type="hidden" name="printReport" value="" />
								<input type="hidden" name="noteNumber" value="">
								<input type="hidden" name="unitNumber" value="">
								<input type="hidden" name="r_orgname" value="">
								<input type="hidden" name="r_bank_name" value="">
								<input type="hidden" name="r_bank_acc" value="">
								<input type="hidden" name="p_orgname" value="">
								<input type="hidden" name="p_bank_name" value="">
								<input type="hidden" name="p_bank_acc" value="">
								<input type="hidden" name="p_Way" value="">
								<input type="hidden" name="p_Kind" value="">
								<input type="hidden" name="payment_orgnames" value="">

							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('addPayTail.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="addPayTail.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('addPayTail.empName')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="addPayTail.empName">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								单位补缴金额
							</td>
							<td align="center" class=td2>
								个人补缴金额
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('addPayTail.addPaySum')">补缴金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="addPayTail.addPaySum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								补缴起始年月
							</td>
							<td align="center" class=td2>
								补缴终止年月
							</td>
							<td align="center" class=td2>
								补缴原因
							</td>
							<td align="center" class=td2>
								补缴类型
							</td>
						</tr>
						<logic:notEmpty name="personAddPayAF" property="personAddPayList">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="personAddPayAF" property="personAddPayList"
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
												value="<bean:write name="element" property="empId"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>

									<td valign="top">
										<p>
											<bean:write name="element" property="empId" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="unitAddPayAmount" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="personAddPayAmount" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="addPayAmount" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="addPayBeginYearMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="addPayEndYearMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="addPayReason" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payMode" />
										</p>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="personAddPayAF" property="personAddPayList">
							<tr>
								<td colspan="12" height="30" style="color:red">
									没有找到与条件相符合的结果！
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
												<jsp:param name="url" value="personAddPayTaShowAC.do" />
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
												onclick="return exportArray();">
												<bean:message key="button.exports" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="gotoImport();">
												<bean:message key="button.imports" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="empAddPay();">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.update" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return deleteConfirm(); ">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return deleteAllConfirm(); ">
												<bean:message key="button.deleteall" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttonb"
												onclick="return addpayConfirm();">
												<bean:message key="button.over.addpay" />
											</html:submit>
										</td>
										<security:orghave>
											<security:orgcan>
												<td>
													<html:submit property="method" styleClass="buttona"
														onclick="return referConfirm(); ">
														<bean:message key="button.referring.data" />
													</html:submit>
												</td>
											</security:orgcan>
										</security:orghave>
										<security:orghave>
											<security:orgcan>
												<td>
													<html:submit property="method" styleClass="buttonc"
														onclick="return pprovalConfirm(); ">
														<bean:message key="button.pproval.data" />
													</html:submit>
												</td>
											</security:orgcan>
										</security:orghave>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</html:form>
				<form
					action="<%=path%>/syscollection/paymng/personaddpay/personAddPayTaShowAC.do"
					method="POST" name="Form1" id="Form1">
				</form>

				<form
					action="<%=path%>/syscollection/paymng/personaddpay/orgpopTaShowAC.do"
					method="POST" name="Form2" id="Form2">
				</form>
	</table>
</html:html>
