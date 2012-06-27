<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.personaddpay.action.PersonAddPayTbShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			PersonAddPayTbShowAC.PAGINATION_KEY);
	request.getSession().setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String type = (String) request.getAttribute("type");
%>
<html:html>
<head>
	<title>个人补缴</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js"></script>

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
var s11="";
var tr="tr0";
var type=<%=type%>;// 版本
function gettr(trindex) {
  tr=trindex;
  update1();
}
function updateConfirm()
{
  if(confirm("要修改补缴登记吗?"))
  {
    document.getElementById("Form2").submit();
  }

}

function update1() {
  	var status=document.getElementById(tr).childNodes[9].childNodes[0].innerHTML.trim();
  	var noteNum=document.getElementById(tr).childNodes[1].innerHTML;
  	  	if(type!=2){
  	if(noteNum.trim()==""){
  		document.all.item(s8).disabled="true";
  	}
  	}
  	if(status=='登记'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="true";
		  	if(type!=2){
		if(noteNum.trim()==""){
  		document.all.item(s8).disabled="true";
  		}else{
		document.all.item(s8).disabled="";
		}
		}
  	}else if(status=='录入清册'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="";
		  	if(type!=2){
		document.all.item(s8).disabled="true";
		}
  	}else if(status=='确认'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="true";
		  	if(type!=2){
		if(noteNum.trim()==""){
  		document.all.item(s8).disabled="true";
  		}else{
		document.all.item(s8).disabled="";
		}
		}
  	}else if(status=='复核'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="true";
		  	if(type!=2){
		if(noteNum.trim()==""){
  		document.all.item(s8).disabled="true";
  		}else{
		document.all.item(s8).disabled="";
		}
		}
  	}else if(status=='入账'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="true";
		  	if(type!=2){
		if(noteNum.trim()==""){
  		document.all.item(s8).disabled="true";
  		}else{
		document.all.item(s8).disabled="";
		}
		}
  	}
  
}

function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="撤消补缴登记"){
				s1=i;
			}
			if(document.all.item(i).value=="修改"){
				s2=i;
			}
			if(document.all.item(i).value=="删除"){
				s3=i;
			}
			if(document.all.item(i).value=="打印"){
				s4=i;
			}
			if(document.all.item(i).value=="完成补缴登记"){
			
				s5=i;
			}
			  	if(type!=2){
			if(document.all.item(i).value=="提取数据"){
			
				s8=i;
			}
			if(document.all.item(i).value=="提取数据"){
			
				s8=i;
			}
			}
			if(document.all.item(i).value=="修改信息"){
				s11=i;
			}
			if(document.all.item(i).value=="打印列表"){
			
				s10=i;
			}
			if(type!=2){
				if(document.all.item(i).value=="提交数据"){
					s6=i;
				}
				if(document.all.item(i).value=="撤销提交数据"){
					s7=i;
				}
			}
		}
	}
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="button"){
			
			  	if(type!=2){
			if(document.all.item(i).value=="提取数据"){
				s8=i;
			}
			}
		}
	}
	
    var list = document.personAddPayMaintainAF.listCount.value;
      
    if(list!=1){
    	
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
		document.all.item(s9).disabled="true";
		document.all.item(s10).disabled="true";
		document.all.item(s11).disabled="true";
	
		if(type!=2){
			document.all.item(s8).disabled="true";
			document.all.item(s6).disabled="true";
			document.all.item(s7).disabled="true";
		}
    }else{
		update1();
    }
}

function gotoDelAddpay(){
	if(!confirm("确认要进行该次补缴登记撤销吗？")){
		return false;
	}

}
function gotoDelete(){
	if(!confirm("确定要删除该笔补缴业务吗？")){
		return false;
	}
}
function gotoShow(){
	return false;
}
function overpayconfirm(){
if (confirm("是否要进行补缴登记?")){
if (confirm("是否打印")){
document.all.printReport.value="print";
}else{
document.all.printReport.value="noprint";
}
return true;
}else{
return false;
}
}
function deleteconfirm(){
if (confirm("是否确定删除该笔业务?")){
return true;
}else{
return false;
}
}

function cancleconfirm(){
if (confirm("要撤销该次补缴登记吗?")){
return true;
}else{
return false;
}
}

function printconfirm(){
if (confirm("是否打印?")){
return true;
}else{
return false;
}
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
function gotoDelete(){
    var addPayAmount = document.personAddPayMaintainAF.addPayAmount.value;
    if(addPayAmount!=""){
    	 document.personAddPayMaintainAF.addPayAmount.value=addPayAmount.trim();
    }

}
function towindow(){
	var paymentHeadId=document.getElementById(tr).childNodes[1].innerHTML;
	var orgId=document.getElementById(tr).childNodes[3].innerHTML;
    var noteNum=document.getElementById(tr).childNodes[1].innerHTML; 
	window.open("<%=path%>/syscollection/paymng/personaddpay/personaddpayTbPickupdataWindowShowAC.do?paymentHeadId="+paymentHeadId+"&orgId="+orgId+"&noteNum="+noteNum,"window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes");       
}
function executeAjax()
{
	var orgId=document.getElementById(tr).childNodes[3].innerHTML;
    var queryString = "personaddpayTbPickupdataWindowAAC.do?";  
    queryString = queryString + "orgId="+orgId;
     findInfo(queryString);
}
function display(type){
	if(type=="2"){
		towindow();
	}else{
		alert("该单位存在未完成的补缴清册，不能提取数据！");
	}
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="personAddPayTbShowAC.do" />
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
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="personaddpayTaForwardUrlAC.do" class=a2>办理缴存</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										缴存维护
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
										<font color="00B5DB">缴存管理<font color="00B5DB">&gt;</font>个人补缴
										
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
										<b class="font14">查 询 条 件</b>
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
				<html:form action="/empaddpayTbFindAC.do" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="11%" class="td1">
								单位编号
							</td>
							<td width="25%" colspan="3">
								<html:text name="personAddPayMaintainAF" property="orgId"
									styleClass="input3" onchange="doRequestUsingGET();"
									onkeydown="goEnter();" styleId="orgId"
									ondblclick="doRequestUsingGET();" />
							</td>
							<td width="11%" class="td1">
								单位名称
							</td>
							<td width="25%" colspan="3">
								<html:text name="personAddPayMaintainAF" property="unitName"
									styleClass="input3" onkeydown="goEnter();" styleId="unitName" />
							</td>
						<tr>
							<td width="11%" class="td1">
								补缴金额
							</td>
							<td width="25%" colspan="3">
								<html:text name="personAddPayMaintainAF" property="addPayAmount"
									styleClass="input3" onkeydown="goEnter();" styleId="docNumber" />
							</td>
							<td width="11%" class="td1">
								业务状态
							</td>
							<td width="25%" align="center" colspan="3">
								<html:select name="personAddPayMaintainAF" property="bizStatus"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizStatusMap"
										name="personAddPayMaintainAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								办理日期
							</td>
							<td width="15%">
								<html:text name="personAddPayMaintainAF" property="opTime"
									styleClass="input3" onkeydown="goEnter();" maxlength="8" />
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="personAddPayMaintainAF" property="opTime1"
									styleClass="input3" onkeydown="goEnter();" maxlength="8" />
							</td>
							<td width="14%" class="td1">
								补缴日期
							</td>
							<td width="15%">
								<html:text name="personAddPayMaintainAF" property="payMonth"
									styleClass="input3" onkeydown="goEnter();" maxlength="8" />
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="personAddPayMaintainAF" property="payMonth1"
									styleClass="input3" onkeydown="goEnter();" maxlength="8" />
							</td>
						</tr>
						<tr>
						<tr>
							<td width="11%" class="td1">
								归集银行
							</td>
							<td width="25%" colspan="3">
								<html:select name="personAddPayMaintainAF" property="collBankId"
									styleClass="input4">
									<html:option value=""></html:option>
									<html:options collection="collBankList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="11%" class="td1"></td>
							<td width="25%" class="td7" colspan="3">
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="14%">
								<input type="hidden" name="listCount"
									value="<bean:write name="personAddPayMaintainAF" property="listCount"/>">
							</td>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="gotoDelete()">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td class=h4>
							总计：补缴金额
							<u>：<bean:write name="personAddPayMaintainAF"
									property="money" /> </u>
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
				<html:form action="/empAddPayTbMaintainAC.do" style="margin: 0">
					<input type="hidden" name="printReport" value="" />
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empAddPay.noteNum')">结算号</a>
								<logic:equal name="pagination" property="orderBy"
									value="empAddPay.noteNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empAddPay.docNum')">凭证编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="empAddPay.docNum">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('empAddPay.org.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="empAddPay.org.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empAddPay.org.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="empAddPay.org.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								补缴人数
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('empAddPay.payMoney')">补缴金额</a>
								<logic:equal name="pagination" property="orderBy"
									value="empAddPay.payMoney">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								办理日期
							</td>
							<td align="center" class=td2>
								补缴日期
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
							<security:orgcan>
								<td align="center" class=td2>
									提取状态
								</td>
							</security:orgcan>
						</tr>
						<logic:notEmpty name="empaddpayList">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="empaddpayList" id="element" indexId="i">
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
										<bean:write name="element" property="noteNum" />
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="docNum" />
										</p>
									</td>
									<td valign="top">
										<bean:write name="element" property="orgId"
											format="0000000000" />
									</td>
									<td valign="top">
										<p>
											<a href="#"
												onClick="window.open('personaddpayTbWindowForwardAC.do?paymentid=<bean:write name="element" property="id"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return gotoShow();"><bean:write
													name="element" property="orgName" />
											</a>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="personCounts" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="pay" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="opTime" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payMonth" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="payStatus" />
										</p>
									</td>
									<td style="display:none">
										<bean:write name="element" property="id" />
									</td>
									<security:orgcan>
										<td valign="top">
											<bean:write name="element" property="tempPickType" />
										</td>
									</security:orgcan>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="empaddpayList">
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
												<jsp:param name="url" value="personAddPayTbShowAC.do" />
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
											<security:authorize operation="define_org"></security:authorize>
										</td>
										<td>
											<html:submit property="method" styleClass="buttonb"
												onclick="return overpayconfirm();">
												<bean:message key="button.over.addpay" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttonb"
												onclick="return cancleconfirm();">
												<bean:message key="button.del.addpay" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.update" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return deleteconfirm(); ">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td style="display:none">
											<html:submit property="method" styleClass="buttona"
												onclick="return printconfirm();">
												<bean:message key="button.print" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.printDoc" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.printList" />
											</html:submit>
										</td>
										<security:orgcan>
											<td>
												<html:button property="method" styleClass="buttona"
													onclick="executeAjax();">
													<bean:message key="button.pickupbynum.data" />
												</html:button>
											</td>
										</security:orgcan>

										<security:orgcan>
											<td>
												<html:submit property="method" styleClass="buttona"
													onclick="return referConfirm(); ">
													<bean:message key="button.referring.data" />
												</html:submit>
											</td>
										</security:orgcan>
										<security:orgcan>
											<td>
												<html:submit property="method" styleClass="buttonc"
													onclick="return pprovalConfirm(); ">
													<bean:message key="button.pproval.data" />
												</html:submit>
											</td>
										</security:orgcan>
										<td>
											<html:submit property="method" styleClass="buttonb">
												<bean:message key="button.reupdate" />
											</html:submit>
										</td>
										
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</html:form>
				<form
					action="<%=path%>/syscollection/paymng/personaddpay/personAddPayTbShowAC.do"
					method="POST" name="Form1" id="Form1">
				</form>

				<form
					action="<%=path%>/syscollection/paymng/personaddpay/personAddPayTaShowAC.do"
					method="POST" name="Form2" id="Form2">
				</form>
			</td>
	</table>
</body>

</html:html>
