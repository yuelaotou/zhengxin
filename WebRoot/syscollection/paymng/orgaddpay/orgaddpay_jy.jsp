<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.orgaddpay.action.OrgaddpayTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			OrgaddpayTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String statetype = (String) request.getAttribute("statetype");
%>
<html:html>
<head>
	<title>单位补缴</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
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
var statetype="<%=statetype%>";
function gotoOrg(status){
	gotoOrgpop(status,'<%=path%>','0');
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function executeAjax() {
        var queryString = "orgaddpayTaFindAAC.do?";       
        var id = document.all.orgid.value.trim();
        if(id == ""){
        	gotoOrg("2");
        }else{
	        if(isNaN(id)){
	        	alert("请输入正确格式的编号！");
	        	document.all.orgid.value="";
	        	document.all.orgid.focus();
	        	return false;
	        }else{
		        queryString = queryString + "id="+id; 	     
			    findInfo(queryString);
		    }
	    }
}
function displays(id,name){
  document.all.orgid.value=id;
  document.all.name.value=name;
  showlist() ;
}
function showlist() {
  document.mform.submit();
}
function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var count=document.all.listCount.value;
	var orgid=document.all.orgid.value;
	var month=document.all.payMonth.value;
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
		<security:orghave>
		    <security:orgcannot>
			if(document.all.item(i).value=="提取单位版数据"){
				s1=i;
			}
			</security:orgcannot>
		</security:orghave>
			if(document.all.item(i).value=="批量导出"){
				s2=i;
			}
			if(document.all.item(i).value=="批量导入"){
				s3=i;
			}
			if(document.all.item(i).value=="删除"){
				s4=i;
			}
			if(document.all.item(i).value=="全部删除"){
				s5=i;
			}
			if(document.all.item(i).value=="完成补缴登记"){
				s6=i;
			}
		<security:orghave>
			<security:orgcan>
			if(document.all.item(i).value=="提交数据"){
				s7=i;
			}
			if(document.all.item(i).value=="撤销提交数据"){
				s8=i;
			}
			</security:orgcan>
		</security:orghave>
		}
	}
	if(orgid==""){
		<security:orghave>
		<security:orgcannot>
		document.all.item(s1).disabled="true";
		</security:orgcannot>
		</security:orghave>
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		<security:orghave>
		<security:orgcan>
		document.all.item(s7).disabled="true";
		document.all.item(s8).disabled="true";
		</security:orgcan>
		</security:orghave>
	}  
	if(count==1){
		<security:orghave>
		<security:orgcannot>
		document.all.item(s1).disabled="true";
		</security:orgcannot>
		</security:orghave>
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		<security:orghave>
		if(statetype=="未提取"){
		      document.all.item(s7).disabled="true";
		      document.all.item(s8).disabled="";
	       }
	       if(statetype=="已提取"){
		      document.all.item(s7).disabled="true";
		      document.all.item(s8).disabled="true";
	       }
	       if(statetype=="未提交"){
		      document.all.item(s7).disabled="";
		      document.all.item(s8).disabled="true";
	       }
		</security:orghave>
		
	}else{
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s6).disabled="true";
		<security:orghave>
		<security:orgcan>
		document.all.item(s7).disabled="true";
		document.all.item(s8).disabled="true";
		</security:orgcan>
		</security:orghave>
	}
	if(orgid != ""){
		document.all.orgid.value=formatTen(orgid)+orgid;
	}
}
function gotoDelete(){
	if(!confirm("确定要删除该笔补缴业务吗？")){
		return false;
	}
}
function gotoDeleteAll(){
	if(!confirm("确定要删除本次补缴业务吗？")){
		return false;
	}
}
function gotoOveraddpay(){
	var noteNum=document.all.noteNum.value;
	var payWay = document.all.payWay.value;
	if(payWay==""){
		alert("请录入补缴方式！");
		return false;
	}
	document.all.payKinds.value=document.all.payKind.value;
	document.all.payWays.value=document.all.payWay.value;
	document.all.payment_bank_names.value=document.all.payment_bank_name.value;
	document.all.payment_bank_accs.value=document.all.payment_bank_acc.value;
	document.all.newNoteNum.value=noteNum;
	if(!confirm("确认要进行该次补缴登记吗？")){
		return false;
	}
<%--	if(confirm("是否打印汇补缴凭证？")){--%>
<%--		document.all.report.value="print";--%>
<%--	}else{--%>
		document.all.report.value="noprint";
<%--	}--%>
}
function gotoExport(){
	var noteNum=document.all.noteNum.value;
	document.all.newNoteNum.value=noteNum;
	var month=document.all.payMonth.value;
	document.all.month.value=month;
	var orgid=document.all.orgid.value;
	var orgsid=document.all.orgsid.value;
	orgsid=formatTen(orgsid)+orgsid;
	document.all.orgId.value=orgid;
	//修改--添加终止年月
	var startMonth=document.all.startPayMonth.value;
	document.all.startMonth.value=startMonth;
	if(orgsid != orgid){
		alert("您更改了单位编号！请从新查询！");
		document.all.orgid.focus();
		return false;
	}
	if(startMonth==""){
		alert("请输入起始时间！");
		document.all.startPayMonth.focus();
		return false;
	}
	if(month==""){
		alert("请输入终止时间！");
		document.all.payMonth.focus();
		return false;
	}
	var str1=checkMonth("startPayMonth");
	var str=checkMonth("payMonth");
	if(!str1){
		return false;
	}else if(!str){
		return false;
	}
	if(parseInt(month)<parseInt(startMonth)){
		alert("终止年月不能小于起始年月！");
		return false;
	}
}
function gotoImport(){
	var noteNum=document.all.noteNum.value;
	document.all.newNoteNum.value=noteNum;
	var month=document.all.payMonth.value;
	document.all.month.value=month;
	var orgid=document.all.orgid.value;
	document.all.orgId.value=orgid;
	var orgsid=document.all.orgsid.value;
	document.all.payKinds.value=document.all.payKind.value;
	document.all.payWays.value=document.all.payWay.value;
	document.all.payment_orgnames.value=document.all.payment_orgname.value;
	orgsid=formatTen(orgsid)+orgsid;
	//修改--添加终止年月
	var startMonth=document.all.startPayMonth.value;
	document.all.startMonth.value=startMonth;
	if(orgsid != orgid){
		alert("您更改了单位编号！请从新查询！");
		document.all.orgid.focus();
		return false;
	}
	if(orgid==""){
		alert("请输入单位编号！");
		return false;
	}
	if(startMonth==""){
		alert("请输入起始时间！");
		document.all.startPayMonth.focus();
		return false;
	}
	if(month==""){
		alert("请输入终止时间！");
		document.all.payMonth.focus();
		return false;
	}
	var str1=checkMonth("startPayMonth");
	var str=checkMonth("payMonth");
	if(!str1){
		return false;
	}else if(!str){
		return false;
	}
}
function exportArray(){
var k= gotoExport();
if(k==false)
	return false;
	 var orgId=document.all.orgid.value;;
	 var noteNum=document.all.noteNum.value;
	 var payMonth=document.all.payMonth.value;
	 var startPayMonth=document.all.startPayMonth.value;
	 var payKind=document.all.payKind.value;
	 var payWay=document.all.payWay.value;
	   	//这个留在备用，可以操作主页面   
	window.open ('<%=path%>/syscollection/paymng/orgaddpay/orgaddpayexport_array.jsp?orgId='+ orgId +'&noteNum='+noteNum+'&startPayMonth='+startPayMonth+'&payMonth='+payMonth+'&payWay='+payWay+'&payKind='+payKind,'newwindow','height=190,width=350,top='+(window.screen.availHeight-190)/2+',left='+(window.screen.availWidth-350)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	return false;
}
function check(){

}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return true">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="orgaddpayTaShowAC.do" />
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
										办理缴存
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="orgaddpayTbForwardUrlAC.do" class=a2>缴存维护</a>
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
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">缴存管理<font color="00B5DB">&gt;</font>单位补缴
										
									</td>
									<td class=font14>
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
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>


				<html:form action="/orgaddpayTaShowAC.do">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								单位编号：
							</td>
							<td width="22%" colspan="2">
								<logic:equal name="orgaddpayTaAF" property="type" value="0">
									<html:text name="orgaddpayTaAF" property="orgid"
										styleClass="input3" styleId="txtsearch" readonly="true" />
									<input type="hidden" name="types"
										value='<bean:write name="orgaddpayTaAF" property="type" />'>
								</logic:equal>
								<logic:notEqual name="orgaddpayTaAF" property="type" value="0">
									<html:text name="orgaddpayTaAF" property="orgid"
										styleClass="input3" onkeydown="gotoEnter();"
										styleId="txtsearch" ondblclick="executeAjax();" />
									<input type="hidden" name="types"
										value='<bean:write name="orgaddpayTaAF" property="type" />'>
								</logic:notEqual>
								<input type="hidden" name="orgsid"
									value="<bean:write name="orgaddpayTaAF" property="orgid"/>">
							</td>
							<td width="11%">
								<logic:equal name="orgaddpayTaAF" property="type" value="0">
									<input type="button" name="Submit5" value="..." class="buttona"
										disabled="disabled">
								</logic:equal>
								<logic:notEqual name="orgaddpayTaAF" property="type" value="0">
									<input type="button" name="Submit5" value="..." class="buttona"
										onclick="gotoOrg('2')">
								</logic:notEqual>
							</td>
							<td width="17%" class="td1">
								单位名称：
							</td>
							<td width="33%">
								<logic:equal name="orgaddpayTaAF" property="type" value="0">
									<html:text name="orgaddpayTaAF" property="name"
										styleClass="input3" styleId="txtsearch" readonly="true" />
								</logic:equal>
								<logic:notEqual name="orgaddpayTaAF" property="type" value="0">
									<html:text name="orgaddpayTaAF" property="name"
										styleClass="input3" styleId="txtsearch" readonly="true" />
								</logic:notEqual>

							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								补缴年月：
							</td>
							<td width="17%">
								<html:text name="orgaddpayTaAF" property="startPayMonth"
									styleClass="input3" styleId="txtsearch" maxlength="6"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%" align="center">
								至
							</td>
							<td width="15%">
								<html:text name="orgaddpayTaAF" property="payMonth"
									styleClass="input3" styleId="txtsearch" maxlength="6"
									onkeydown="enterNextFocus1();" />
							</td>
							<td class="td1" width="17%">
								结算号：
							</td>
							<td width="33%">
								<html:text name="orgaddpayTaAF" property="noteNum"
									styleClass="input3" styleId="txtsearch" maxlength="20" />
								<input type="hidden" name="listCount"
									value="<bean:write name="orgaddpayTaAF" property="listCount" />">
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								收款单位名称：
							</td>
							<td width="22%" colspan="3">
								<html:text name="orgaddpayTaAF" property="receivables_orgname"
									styleClass="input3" styleId="receivables_orgname"
									onkeydown="gotoEnter();">
								</html:text>
							<td class="td1" width="17%" algin="center">
								付款单位名称：
							</td>
							<td width="22%">
								<html:text name="orgaddpayTaAF" property="payment_orgname"
									styleClass="input3" styleId="payment_orgname"
									onkeydown="gotoEnter();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								收款单位归集银行名称：
							</td>
							<td width="22%" colspan="3">
								<logic:equal name="orgaddpayTaAF" property="receivables_bank_name" value="农行柳城支行">
									<html:text name="orgaddpayTaAF" property="receivables_bank_name" value="中行渤海支行"
										styleClass="input3" styleId="txtsearch" />
								</logic:equal>
								<logic:notEqual name="orgaddpayTaAF" property="receivables_bank_name" value="农行柳城支行">
									<html:text name="orgaddpayTaAF" property="receivables_bank_name"
										styleClass="input3" styleId="txtsearch" />
								</logic:notEqual>
							<td class="td1" width="17%" algin="center">
								付款单位开户银行名称：
							</td>
							<td width="22%">
								<html:text name="orgaddpayTaAF" property="payment_bank_name"
									styleClass="input3" styleId="payment_bank_name"
									onkeydown="gotoEnter();"></html:text>
							</td>
						</tr>
						<tr>

							<td width="17%" class="td1" algin="center">
								收款单位归集银行账号：
							</td>
							<td width="22%" colspan="3">
								<html:text name="orgaddpayTaAF" property="receivables_bank_acc"
									styleClass="input3" styleId="receivables_bank_acc"
									onkeydown="gotoEnter();">
								</html:text>
							<td class="td1" width="17%" algin="center">
								付款单位开户银行账号：
							</td>
							<td width="22%">
								<html:text name="orgaddpayTaAF" property="payment_bank_acc"
									styleClass="input3" styleId="payment_bank_acc"
									onkeydown="gotoEnter();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								缴存方式：
							</td>
							<td width="22%" colspan="3">
								<html:select name="orgaddpayTaAF" property="payWay"
									styleClass="input4">
									<html:optionsCollection property="pay_way" name="orgaddpayTaAF"
										label="value" value="key" />
								</html:select>
							<td class="td1" width="17%" algin="center">
								缴存类别：
							</td>
							<td width="22%">
								<html:select name="orgaddpayTaAF" property="payKind"
									styleClass="input4">
									<html:optionsCollection property="pay_kind"
										name="orgaddpayTaAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1" algin="center">
								挂账余额：
							</td>
							<td width="22%" colspan="3">
								<html:text name="orgaddpayTaAF" property="overPay"
									styleClass="input3" />
							</td>
							<td class="td1" width="17%" algin="center"></td>
							<td width="22%">
							</td>
						</tr>
					</table>

				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							合计： 补缴人数
							<bean:write name="pagination" property="nrOfElements" />
							单位补缴金额
							<bean:write name="orgaddpayTaAF"
								property="monthPaymentHead.orgSumpay" />
							个人补缴金额
							<bean:write name="orgaddpayTaAF"
								property="monthPaymentHead.empSumpay" />
							补缴金额
							<bean:write name="orgaddpayTaAF"
								property="monthPaymentHead.addpayMoney" />
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
										<b class="font14">单位补缴列表</b>
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
				<html:form action="/orgaddpayTaMaintainAC" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" height="23" bgcolor="C4F0FF">
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('monthPaymentTail.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="monthPaymentTail.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('monthPaymentTail.empName')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="monthPaymentTail.empName">
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
								<a href="javascript:sort('sum(monthPaymentTail.empRealPay)')">补缴金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="sum(monthPaymentTail.empRealPay)">
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
						</tr>
						<tr>
							<td>
								<input type="hidden" name="newNoteNum" value="">
								<input type="hidden" name="month" value="">
								<input type="hidden" name="startMonth" value="">
								<input type="hidden" name="orgId" value="">
								<input type="hidden" name="payKinds" value="">
								<input type="hidden" name="payWays" value="">
								<input type="hidden" name="report" value="">
								<input type="hidden" name="payment_orgnames" value="">
								<input type="hidden" name="payment_bank_names" value="">
								<input type="hidden" name="payment_bank_accs" value="">
								<logic:equal name="orgaddpayTaAF" property="type" value="0">
									<input type="hidden" name="types"
										value='<bean:write name="orgaddpayTaAF" property="type" />'>
								</logic:equal>
								<logic:equal name="orgaddpayTaAF" property="type" value="1">
									<input type="hidden" name="types"
										value='<bean:write name="orgaddpayTaAF" property="type" />'>
								</logic:equal>
							</td>
						</tr>
						<logic:notEmpty name="orgaddpayTaAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="orgaddpayTaAF" property="list" id="element"
								indexId="i">
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
											<bean:write name="element" property="empId" format="000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="orgAddPaySum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="empAddPaySum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="addPaySum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="beginMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="endMonth" />
										</p>
									</td>
								</tr>
								<html:hidden name="orgaddpayTaAF" property="monthPaymentHead.id"
									styleClass="input3" styleId="txtsearch" />

								<input type="hidden" name="monthPaymentHead_paymentHead_id"
									value='<bean:write name="orgaddpayTaAF" property="monthPaymentHead.paymentHead.id" />'>
								<input type="hidden" name="monthPaymentHead_paymentHead_org_id"
									value='<bean:write name="orgaddpayTaAF" property="monthPaymentHead.paymentHead.org.id" />'>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="orgaddpayTaAF" property="list">
							<tr>
								<td colspan="7" height="30" style="color:red">
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
												<jsp:param name="url" value="orgaddpayTaShowAC.do" />
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
												<td width="110">
													<html:submit property="method" styleClass="buttonc"
														onclick="return gotoImport();">
														<bean:message key="button.pickup.data" />
													</html:submit>
												</td>
											</security:orgcannot>
										</security:orghave>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return exportArray();">
												<bean:message key="button.exports" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return gotoImport();">
												<bean:message key="button.imports" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return gotoDelete();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="return gotoDeleteAll();">
												<bean:message key="button.deleteall" />
											</html:submit>
										</td>
										<td width="90">
											<html:submit property="method" styleClass="buttonb"
												onclick="return gotoOveraddpay();">
												<bean:message key="button.over.addpay" />
											</html:submit>
										</td>
										<security:orgcan>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.referring.data" />
												</html:submit>
											</td>
										</security:orgcan>
										<security:orgcan>
											<td width="90">
												<html:submit property="method" styleClass="buttonb">
													<bean:message key="button.pproval.data" />
												</html:submit>
											</td>
										</security:orgcan>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
	<form action="orgaddpayTaShowAC.do" method="POST" name="mform"
		id="Form1">
	</form>
</body>
</html:html>
