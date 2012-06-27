<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.syscollection.paymng.monthpay.action.MonthpayTaShowAC"%>
<%@ page import="org.xpup.hafmis.orgstrct.dto.SecurityInfo"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			MonthpayTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	SecurityInfo securityInfo1 = (SecurityInfo) request.getSession(
			false).getAttribute("SecurityInfo");
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>缴存管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script>
<script type="text/javascript">
var s1="";
var s2="";
var type = "";
function executeAjax() {
        var queryString = "monthpayTaFindAAC.do?";
        var id = document.monthpayJYAF.elements["orgid"].value.trim();
        if(isNaN(id)){
        	alert("请输入正确格式的编号！");
        	document.monthpayJYAF.elements["orgid"].value="";
        	document.monthpayJYAF.elements["orgid"].focus();
        	return false;
        }
        if(id == ""){
        	gotoOrg("2");
        }else{
	        queryString = queryString + "id="+id; 	 
		    findInfo(queryString);
	    }
}
function display(id,name){
	document.monthpayJYAF.elements["orgid"].value=id;
	document.monthpayJYAF.elements["name"].value=name;
	showInfo();
}
function display1(flag){

	if(flag!='false'){
		document.monthpayJYAF.elements["type"].value="2";
		var noteNum = document.monthpayJYAF.elements["noteNum"].value;
		if(noteNum.length==0){
			type = '0';
		}else{
			type = '1';
<%--			if(confirm("是否打印汇缴凭证？")){--%>
<%--				document.all.report.value="print";--%>
<%--			}else{--%>
				document.all.report.value="noprint";
<%--			}--%>
		}
	}else{
		alert('存在未入账的挂账业务，不能进行正常汇缴业务！');
		type = '2'
	}
}
function showInfo(){
	document.Form1.submit();
}

function gotoOrg(status){
	gotoOrgpop(status,'<%=path%>','0');
}

function gotoPrint(){
	var status1=getCheckStatus();
	var status2=document.monthpayJYAF.status.value;
	var month1=document.monthpayJYAF.inceptMonth.value;
	var month2=document.monthpayJYAF.inceptmonth.value;
	var month3=document.monthpayJYAF.payMonth.value;
	var month4=document.monthpayJYAF.paymonth.value;
//	var year1=month1.substring(0,4);
//	var m1=month1.substring(4,month2.lenth);
	var year2=month3.substring(0,4);
	var m2=month3.substring(4,month3.lenth);
	var date="<%=securityInfo1.getUserInfo().getBizDate()%>";
	var y1=parseInt(date.substring(0,4))+1;
	var y2=parseInt(date.substring(0,4));
	var m=date.substring(4,6);
	var temp_m=m.substring(0,1);
	
	var sumPay = document.monthpayJYAF.elements["sumPay"].value;
	var realPay = document.monthpayJYAF.elements["realPay"].value;
	var overPay = document.monthpayJYAF.elements["overPay"].value;
	sumPay = parseFloat(sumPay);
	realPay = parseFloat(realPay);
	overPay = parseFloat(overPay);
	
	if(temp_m==0){
		m=m.substring(1,2);
	}
	var temp_m2=m2.substring(0,1);
	if(temp_m2==0){
		m2=m2.substring(1,2);
	}
	/*
	if(m>=7 && m<=12){
		if(parseInt(year2)==y1){
			if(m2>6){
				alert("缴至年月不能大于结息年度！");
				return false;
			}
			
		}if(parseInt(year2)>y1){
			alert("缴至年月不能大于结息年度！");
			return false;
		}
	}else if(m>=1 && m<=6){
		if(parseInt(year2)>y2){
			alert("缴至年月不能大于结息年度！");
			return false;
		}
		else if(parseInt(year2)==y2){
			if(m2>6){
				alert("缴至年月不能大于结息年度！");
				return false;   
			}
		}
	}
	*/
	var orgsid=document.monthpayJYAF.orgsid.value;
	orgsid=formatTen(orgsid)+orgsid;

	var orgid=document.monthpayJYAF.orgid.value;
	if(orgsid != orgid){
		alert("您更改了单位编号！请从新查询！");
		document.monthpayJYAF.orgid.focus();
		return false;
	}     
	if(document.monthpayJYAF.elements["orgid"].value==""){
		alert("请选择单位！");
		return false;
	}
	var money1=document.monthpayJYAF.sumPay.value;
	var money2=document.monthpayJYAF.realPay.value.trim();
	document.monthpayJYAF.realPay.value=money2;
	if(money1==0){
		alert("此单位下没有职工，暂不能进行汇缴！");
		return false;
	}
	var str=checkTimes("inceptMonth","payMonth");
	/*
	if(year1>year2){
		alert("输入年月有误！");
		return false;
	}else if(year1==year2){
		if(m1>m2){
			alert("输入年月有误！");
			return false;
		}
	}*/
	if(str){
		if(status1 != status2){
			alert("结算方式已经改变，请先核定！");
			return false;
		}
		if(month1 != month2 || month3 != month4){
			alert("汇缴年月已经改变，请先核定！");
			return false;
		}
		//if(parseInt(money1) != parseInt(money2) ){
			//alert("实缴金额必须等于应缴金额！");
			//document.monthpayJYAF.realPay.value="";
			//document.monthpayJYAF.realPay.focus();
			//return false;
		//}
		
		if(overPay==0&&(sumPay > realPay)){
			alert("缴存金额不足，不能进行正常汇缴业务！");
			return false;
		}
		var payWay = document.all.payWay.value;
		if(payWay == ""){
			alert("请录入缴存方式！");
			return false;
		}
		if(checkMoney1(realPay)){
			if(sumPay>(realPay+overPay)){
				alert('缴存金额不足，不能进行正常汇缴业务！');
				return false;
			}else if(sumPay==realPay){
				document.monthpayJYAF.elements["type"].value="1";
<%--				if(confirm("是否打印汇缴凭证？")){--%>
<%--					document.all.report.value="print";--%>
<%--				}else{--%>
					document.all.report.value="noprint";
<%--				}--%>
			}else{
				var x=confirm("本次实缴金额："+realPay+"  本次挂账金额："+(realPay-sumPay)+"是否继续!");
				if(x){ 
					var orgId = document.monthpayJYAF.elements["orgid"].value;
					var queryString = "monthpayAutoOverPayAAC.do?";
					queryString = queryString + "orgId="+orgId;
					findInfo(queryString);
					if(type=='0'){
						alert('请填写结算号！');
						return false;
					}else if(type=='2'){
						return false;
					}
				}else{
				  return false;
				}
			}
		}else{
			return false;
		}
	}else{
		return false;
	}
}
function gotoCheck(){
    var realPay=document.monthpayJYAF.realPay.value;
    if(realPay==""){
    	document.monthpayJYAF.realPay.value=0;
    }else if(isNaN(realPay)){
    	document.monthpayJYAF.realPay.value=0;
    }
    
	var status1=getCheckStatus();
	var status2=document.monthpayJYAF.status.value;
	var month=document.monthpayJYAF.untilmonth.value;
	var month1=document.monthpayJYAF.inceptMonth.value;
	var month2=document.monthpayJYAF.inceptmonth.value;
	var month3=document.monthpayJYAF.payMonth.value;
	
	var year2=month3.substring(0,4);
	var m2=month3.substring(4,month3.lenth);
	var date="<%=securityInfo1.getUserInfo().getBizDate()%>";
	var y1=parseInt(date.substring(0,4))+1;
	var y2=parseInt(date.substring(0,4));
	var m=date.substring(4,6);
	var temp_m=m.substring(0,1);
	if(temp_m==0){
		m=m.substring(1,2);
	}
	var temp_m2=m2.substring(0,1);
	if(temp_m2==0){
		m2=m2.substring(1,2);
	}
	/*
	if(m>=7 && m<=12){
		if(parseInt(year2)==y1){
			if(m2>6){
				alert("缴至年月不能大于结息年度！");
				return false;
			}
			
		}if(parseInt(year2)>y1){
			alert("缴至年月不能大于结息年度！");
			return false;
		}
	}else if(m>=1 && m<=6){
		if(parseInt(year2)>y2){
			alert("缴至年月不能大于结息年度！");
			return false;
		}
		else if(parseInt(year2)==y2){
			if(m2>6){
				alert("缴至年月不能大于结息年度！");
				return false;   
			}
		}
	}*/
	var orgsid=document.monthpayJYAF.orgsid.value;
	orgsid=formatTen(orgsid)+orgsid;
	var orgid=document.monthpayJYAF.orgid.value;
	if(orgsid != orgid){
		alert("您更改了单位编号！请从新查询！");
		document.monthpayJYAF.orgid.focus();
		return false;
	}
	if(month1==""){
		alert("请填写汇缴年月！");
		document.monthpayJYAF.inceptMonth.focus();
		return false;
	}
	if(month3==""){
		alert("请填写汇缴年月！");
		document.monthpayJYAF.payMonth.focus();
		return false;
	}
	var str=checkTimes("inceptMonth","payMonth");
	if(str){
		var year1=month1.substring(0,4);
		var m1=month1.substring(4,month2.lenth);
		var year2=month2.substring(0,4);
		var m2=month2.substring(4,month2.lenth);
		if(month1<month){
			if(month1!=month3){
				alert("只能汇缴一个月的欠缴！");
				return false;
			}
		}
	}else{
		return false;
	}
}
function getCheckStatus(){
	var status;
	var i=0;
	if (document.getElementsByName("payStatus").length!=1){
		for(i;i<document.getElementsByName("payStatus").length;i++){  
			if(document.all.payStatus[i].checked){
				status=document.all.payStatus[i].value;
			}
		}
	}else{
		status=document.all.payStatus.value;
	}
		return status;
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax();
	}
}

function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var id = document.monthpayJYAF.elements["orgid"].value;
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="核定"){
				s1=i;
			}
			if(document.all.item(i).value=="确定"){
				s2=i;
			}
		}
	}
	if(document.monthpayJYAF.elements["orgid"].value==""){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
	}
	if(id != ""){
		document.monthpayJYAF.elements["orgid"].value=formatTen(id)+id;
	}
	var payStatus_chg = document.all.payStatus_chg.value;
	if(payStatus_chg == "1"){
		alert("存在职工状态为3.销户 4.转出的职工，请先进行人员变更！");
	}
}
//判断金额
function checkMoney1(money){	
	var patrn=/^([0-9]{1,10})(\.[0-9]{1,2})?$/;
	if (!patrn.exec(money)) {
		alert('请正确录入金额！格式如：1087.23');
		return false
	}else{
		return true
	}
}
function enterNextFocus_fyf(){
	if(event.keyCode==13){
		document.all.item(s2).focus();
		return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();">

	<html:form action="/monthpayTaMaintainAC.do" focus="orgid">
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
											<a href="monthpayTbForwardUrlAC.do" class=a2>缴存维护</a>
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
											<font color="00B5DB">缴存管理<font color="00B5DB">&gt;
													办理汇缴</font>
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
						<tr>
							<td class=td6>
								欠缴信息：该单位共欠缴
								<font color="red"><bean:write name="monthpayJYAF"
										property="lackMonths" />
								</font>个月 该单位共欠缴
								<font color="red"><bean:write name="monthpayJYAF"
										property="orgLackMoney" />
								</font>元
							</td>
						</tr>
						<tr>
							<td class=td6>
								缴存信息：单位缴存至
								<font color="red"><bean:write name="monthpayJYAF"
										property="orgPayMonth" />
								</font>月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								职工缴存至
								<font color="red"><bean:write name="monthpayJYAF"
										property="empPayMonth" />
								</font>月
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>

					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								单位编号
							</td>
							<td width="23%">
								<html:text name="monthpayJYAF" property="orgid"
									ondblclick="return executeAjax();" onkeydown="gotoEnter();"
									styleClass="input3" styleId="txtsearch" />
								<input type="hidden" name="orgsid"
									value="<bean:write name="monthpayJYAF" property="orgid"/>">
							</td>
							<td width="10%">
								<input type="button" name="Submit4" value="..." class="buttona"
									onclick="gotoOrg('2');" onkeydown="gotoEnter();">
							</td>
							<td class="td1" width="17%">
								单位名称
							</td>
							<td width="33%" colspan="2">
								<html:text name="monthpayJYAF" property="name"
									styleClass="input3" styleId="txtsearch" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								收款单位名称
							</td>
							<td colspan="2">
								<html:text name="monthpayJYAF" property="receivables_orgname"
									styleClass="input3" styleId="txtsearch" />
							</td>

							<td class="td1" width="17%">
								付款单位名称
							</td>
							<td width="33%" colspan="2">
								<html:text name="monthpayJYAF" property="payment_orgname"
									styleClass="input3" styleId="txtsearch" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								收款单位归集银行名称
							</td>
							<td colspan="2">
								<logic:equal name="monthpayJYAF" property="receivables_bank_name" value="农行柳城支行">
									<html:text name="monthpayJYAF" property="receivables_bank_name" value="中行渤海支行"
										styleClass="input3" styleId="txtsearch" />
								</logic:equal>
								<logic:notEqual name="monthpayJYAF" property="receivables_bank_name" value="农行柳城支行">
									<html:text name="monthpayJYAF" property="receivables_bank_name"
										styleClass="input3" styleId="txtsearch" />
								</logic:notEqual>
							</td>
							<td class="td1" width="17%">
								付款单位开户银行名称
							</td>
							<td width="33%" colspan="2">
								<html:text name="monthpayJYAF" property="payment_bank_name"
									styleClass="input3" styleId="txtsearch" />

							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								收款单位归集银行账号
							</td>
							<td colspan="2">
								<html:text name="monthpayJYAF" property="receivables_bank_acc"
									styleClass="input3" styleId="txtsearch" />
							</td>
							<td class="td1" width="17%">
								付款单位开户银行账号
							</td>
							<td width="33%" colspan="2">
								<html:text name="monthpayJYAF" property="payment_bank_acc"
									styleClass="input3" styleId="txtsearch" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								结算号
							</td>
							<td colspan="2">
								<html:text name="monthpayJYAF" property="noteNum"
									styleClass="input3" styleId="txtsearch" maxlength="20" />
							</td>
							<td class="td1" width="17%">
								结算方式
							</td>
							<td width="33%" colspan="2">
								<html:radio property="payStatus" value="1">均缴</html:radio>
								<html:radio property="payStatus" value="2">只缴单位</html:radio>
								<html:radio property="payStatus" value="3">只缴职工</html:radio>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								汇缴月份
								<font color="#FF0000">*</font>
							</td>
							<td colspan="2">
								<html:text name="monthpayJYAF" property="inceptMonth"
									styleClass="input3" styleId="txtsearch" maxlength="6" />
							</td>
							<td width="17%" class="td1">
								至
								<font color="#FF0000">*</font>
							</td>
							<td width="30%">
								<html:text name="monthpayJYAF" property="payMonth"
									styleClass="input3" styleId="txtsearch" maxlength="6" />
							</td>
							<td width="3%">
								<html:submit property="method" styleClass="buttona"
									onclick="return gotoCheck();">
									<bean:message key="button.check" />
								</html:submit>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								缴存方式
							</td>
							<td colspan="2">
								<html:select property="payWay" styleClass="input4">
									<html:optionsCollection property="pay_way" name="monthpayJYAF"
										label="value" value="key" />
								</html:select>
							</td>

							<td class="td1" width="17%">
								缴存类别
							</td>
							<td width="33%" colspan="2">
								<html:select property="payKind" styleClass="input4">
									<html:optionsCollection property="pay_kind" name="monthpayJYAF"
										label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>
					<table border="0" cellspacing="0" cellpadding="0" width="100%">
						<tr>
							<td height="5">
								<input type="hidden" name="report" value="">
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="1" cellpadding="3"
						align="center" bgcolor="#B9E2F7">
						<tr bgcolor="#FFFFFF">
							<td align="center" colspan="4" height="20">
								应缴人数
							</td>
							<td align="center" colspan="4" height="20">
								应缴金额
							</td>
						</tr>
						<tr bgcolor="#FFFFFF">
							<td align="center">
								上月
							</td>
							<td align="center">
								增加
							</td>
							<td align="center">
								减少
							</td>
							<td align="center">
								本月
							</td>
							<td align="center">
								上月
							</td>
							<td align="center">
								增加
							</td>
							<td align="center">
								减少
							</td>
							<td align="center">
								本月
							</td>
						</tr>
						<logic:notEmpty name="monthpayJYAF">
							<tr class=td7 bgcolor="#FFFFFF">
								<td align="center" valign="top">
									<bean:write name="monthpayJYAF" property="ultimoPersonCounts" />
									&nbsp;
								</td>
								<td align="center" valign="top">
									<bean:write name="monthpayJYAF" property="personCountsAdd" />
									&nbsp;
								</td>
								<td align="center" valign="top">
									<bean:write name="monthpayJYAF" property="personCountsLess" />
									&nbsp;
								</td>
								<td align="center" valign="top">
									<bean:write name="monthpayJYAF" property="personCounts" />
									&nbsp;
								</td>
								<td align="center" valign="top">
									<bean:write name="monthpayJYAF" property="ultimoPayMoney" />
									&nbsp;
								</td>
								<td align="center" valign="top">
									<bean:write name="monthpayJYAF" property="payMoneyAdd" />
									&nbsp;
								</td>
								<td align="center" valign="top">
									<bean:write name="monthpayJYAF" property="payMoneyLess" />
									&nbsp;
								</td>
								<td align="center" valign="top">
									<bean:write name="monthpayJYAF" property="payMoney" />
									&nbsp;
								</td>
							</tr>
						</logic:notEmpty>
					</table>
					<br>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td width="17%" class="td1">
								应缴总额
							</td>
							<td colspan="2">
								<html:text name="monthpayJYAF" property="sumPay"
									styleClass="input3" styleId="sumPay" readonly="true" />
							</td>
							<td width="17%" class="td1">
								实缴金额
								<font color="#FF0000">*</font>
							</td>
							<td width="33%">
								<html:text name="monthpayJYAF" property="realPay"
									styleClass="input3" styleId="shouldPay"
									onkeydown="return enterNextFocus_fyf();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								挂账余额
							</td>
							<td colspan="2">
								<html:text name="monthpayJYAF" property="overPay"
									styleClass="input3" styleId="overPay" readonly="true" />
							</td>
							<td width="17%" class="td1"></td>
							<td width="33%">
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table height="20" border="0" cellpadding="0" cellspacing="0">

									<logic:notEmpty name="monthpayJYAF">
										<tr>
											<td width="120">
												<input type="hidden" name="status"
													value="<bean:write name="monthpayJYAF" property="payStatus"/>">
												<input type="hidden" name="inceptmonth"
													value="<bean:write name="monthpayJYAF" property="inceptMonth"/>">
												<input type="hidden" name="untilmonth"
													value="<bean:write name="monthpayJYAF" property="untilMonth"/>">
												<input type="hidden" name="paymonth"
													value="<bean:write name="monthpayJYAF" property="payMonth"/>">
												<input type="hidden" name="dwid"
													value="<bean:write name="monthpayJYAF" property="orgid"/>">
												<input type="hidden" name="ultimoPersonCounts"
													value="<bean:write name="monthpayJYAF" property="ultimoPersonCounts"/>">
												<input type="hidden" name="personCountsAdd"
													value="<bean:write name="monthpayJYAF" property="personCountsAdd"/>">
												<input type="hidden" name="personCountsLess"
													value="<bean:write name="monthpayJYAF" property="personCountsLess"/>">
												<input type="hidden" name="personCounts"
													value="<bean:write name="monthpayJYAF" property="personCounts"/>">
												<input type="hidden" name="ultimoPayMoney"
													value="<bean:write name="monthpayJYAF" property="ultimoPayMoney"/>">
												<input type="hidden" name="payMoneyAdd"
													value="<bean:write name="monthpayJYAF" property="payMoneyAdd"/>">
												<input type="hidden" name="payMoneyLess"
													value="<bean:write name="monthpayJYAF" property="payMoneyLess"/>">
												<input type="hidden" name="payMoney"
													value="<bean:write name="monthpayJYAF" property="payMoney"/>">
												<input type="hidden" name="payMoney"
													value="<bean:write name="monthpayJYAF" property="sumPay"/>">
												<input type="hidden" name="payMoney"
													value="<bean:write name="monthpayJYAF" property="payMonthCount"/>">
												<html:hidden name="monthpayJYAF" property="type" />
												<html:hidden name="monthpayJYAF" property="payStatus_chg" />
												<html:submit property="method" styleClass="buttona"
													onclick="return gotoPrint();">
													<bean:message key="button.sure" />
												</html:submit>
											</td>
										</tr>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<form action="monthpayTaShowAC.do" method="POST" name="Form1"
		id="Form1">
	</form>
</body>
</html:html>
