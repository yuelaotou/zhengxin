<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loancallback.consultation.action.ConsultationTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	Object pagination = request.getSession(false).getAttribute(
			ConsultationTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script>
<script type="text/javascript">
function executeAjax() {
        var queryString = "<%=path%>/sysloan/consultationTaFindAAC.do?";
        var id = document.loancallbackTaAF.elements["borrowerInfoDTO.contractId"].value.trim();
        if(id == ""){
        	gotoSearch();
        }else{
	        queryString = queryString + "id="+id; 	 
		    findInfo(queryString);
	    }
}
function gotoSearch(){
	gotoContractpop('11','<%=path%>','0','0');
	//gotoLoankouaccpop('11','<%=path%>','0');
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax();
	}
}
function display(id){
	showInfo();
}
function showInfo(){
	document.Form1.submit();
}
function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
</logic:messagesPresent>
	var bizType=document.forms[0].elements["biztype"].value;
	var name = document.loancallbackTaAF.elements["borrowerInfoDTO.borrowerName"].value;
	var isAmend = document.loancallbackTaAF.isAmend.value;
	var isAmendLine = document.loancallbackTaAF.isAmendLine.value;
	if(isAmend=="0"){
		document.forms[0].elements["realMoney"].readOnly="readonly";
	}
	if(bizType=="2"){
		document.forms[0].elements["aheadCorpus"].disabled="disabled";
		document.forms[0].elements["days"].disabled="disabled";
		document.forms[0].elements["aheadInterest"].disabled="disabled";
		document.forms[0].elements["loanPoundageMoney"].disabled="disabled";
		document.forms[0].elements["deadLine"].disabled="disabled";
		document.forms[0].elements["corpusInterest"].disabled="disabled";
	}else if(bizType=="3"){
		if(isAmendLine=="0"){
			document.forms[0].elements["deadLine"].readOnly="readonly";
		}
		document.forms[0].elements["monthYear"].disabled="disabled";
		document.forms[0].elements["aheadCorpus"].focus();
	}else if(bizType=="4"){
		document.forms[0].elements["monthYear"].disabled="disabled";
		document.forms[0].elements["aheadCorpus"].readOnly="readonly";
		document.forms[0].elements["deadLine"].readOnly="readonly";
		document.forms[0].elements["realMoney"].readOnly="readonly";
	}
	var loanMode=document.forms[0].elements["borrowerInfoDTO.loanMode"].value.trim();
	if(loanMode=="等额本金"){
		document.forms[0].elements["corpusInterest"].value="";
	}
	if(loanMode=="一年期"||loanMode=="两年期"){
		document.all.radio2.disabled="true";
		document.all.radio3.disabled="true";
	}
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	if(count=="0"){
		document.forms[0].elements["monthYear"].value="";
	}
}
function gotoMonth(){
	var month=document.forms[0].elements["monthYear"].value.trim();
	var ovaerLoanRepay=document.forms[0].elements["ovaerLoanRepay"].value.trim();
	var contractId=document.forms[0].elements["borrowerInfoDTO.contractId"].value.trim();
	location.href="consultationTaChangeMonthAC.do?month="+month+"&ovaerLoanRepay="+ovaerLoanRepay+"&contractId="+contractId;
}
function gotoType1(){
	var bizType=document.forms[0].elements["biztype"].value;
	if(bizType!="2"){
		location.href="consultationTaShowAC.do?bizType=2";
	}
}
function gotoType2(){
	var bizType=document.forms[0].elements["biztype"].value;
	if(bizType != "3"){
		location.href="consultationTaShowAC.do?bizType=3";
	}
}
function gotoType3(){
	var bizType=document.forms[0].elements["biztype"].value;
	if(bizType != "4"){
		location.href="consultationTaShowAC.do?bizType=4";
	}
}
function clear(){
		document.forms[0].elements["aheadCorpus"].value="";
		document.forms[0].elements["days"].value="";
		document.forms[0].elements["aheadInterest"].value="";
		document.forms[0].elements["loanPoundageMoney"].value="";
		document.forms[0].elements["deadLine"].value="";
		document.forms[0].elements["corpusInterest"].value="";
		document.forms[0].elements["sumCorpus"].value="";
		document.forms[0].elements["sumInterest"].value="";
		document.forms[0].elements["ovaerLoanRepay"].value="";
		document.forms[0].elements["realMoney"].value="";
		document.forms[0].elements["overOccurMoney"].value="";
		document.forms[0].elements["sumMoney"].value="";

}
function gotoEnter1(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax1();
	}

}
function gotoEnterLine(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjaxLine();
	}

}
function executeAjax1(){
	var queryString = "<%=path%>/sysloan/consultationTaAheadAAC.do?";
        var aheadMoney = document.loancallbackTaAF.elements["aheadCorpus"].value.trim();
	var overplusCorpus = document.all.overplusCorpus.value;
        if(aheadMoney == ""){
        	alert("请输入提前还款金额！");
        	return false;
        }else if(parseFloat(aheadMoney)>parseFloat(overplusCorpus)){
	    	alert("提前还款金额不能大于剩余本金！");
	    	return false;
	    }
        if(isNaN(aheadMoney)){
        	alert("请输入正确金额！");
        	return false;
        }
        if(parseFloat(aheadMoney)<0){
        	alert("请输入正确金额！");
        	return false;
        }
        else{
	        queryString = queryString + "aheadMoney="+aheadMoney; 	 
		    findInfo(queryString);
	    }
}
function executeAjaxLine(){
	var queryString = "<%=path%>/sysloan/consultationTaAheadAAC.do?";
        var aheadMoney = document.loancallbackTaAF.elements["aheadCorpus"].value.trim();
        var line = document.loancallbackTaAF.elements["deadLine"].value.trim();
        var cIMoney = document.loancallbackTaAF.elements["sumMoney"].value.trim();
        
           var monthYear = document.loancallbackTaAF.elements["monthYear"].value.trim();
          var contractId = document.loancallbackTaAF.elements["borrowerInfoDTO.contractId"].value.trim();
          var sumCorpus = document.loancallbackTaAF.elements["sumCorpus"].value.trim();
            var sumInterest = document.loancallbackTaAF.elements["sumInterest"].value.trim();
        
        
		queryString = queryString + "aheadMoney="+aheadMoney+"&deadLine="+line+"&cIMoney="+cIMoney
		+"&monthYear="+monthYear+"&contractId="+contractId+
		"&sumCorpus="+sumCorpus+"&sumInterest="+sumInterest; 	  
	    if(line==""){
			alert("请输入期限！");
	    	return false;
	    }
	    if(isNaN(line)){
	    	alert("请输入正确期限！");
	    	return false;
	    }
        if(parseFloat(line)<0){
        	alert("请输入正确期限！");
        	return false;
        }
	    else{
	    	findInfo(queryString);
	    }
}
function displays(aheadCorpus,days,aheadInterest,loanPoundageMoney,deadLine,corpusInterest,
	sumCorpus,sumInterest,sumMoney,ovaerLoanRepay,realMoney,overplusInterestAll,interestAll,overOccurMoney){
	var loanMode=document.forms[0].elements["borrowerInfoDTO.loanMode"].value.trim();
	if(loanMode=="等额本金"){
		document.forms[0].elements["corpusInterest"].value="";
	}else{
		document.forms[0].elements["corpusInterest"].value=corpusInterest;
	}
		document.forms[0].elements["aheadCorpus"].value=aheadCorpus;
		document.forms[0].elements["days"].value=days;
		document.forms[0].elements["aheadInterest"].value=aheadInterest;
		document.forms[0].elements["loanPoundageMoney"].value=loanPoundageMoney;
		document.forms[0].elements["deadLine"].value=deadLine;
		
		document.forms[0].elements["sumCorpus"].value=sumCorpus;
		document.forms[0].elements["sumInterest"].value=sumInterest;
		document.forms[0].elements["ovaerLoanRepay"].value=ovaerLoanRepay;
		document.forms[0].elements["realMoney"].value=realMoney;
		document.forms[0].elements["overOccurMoney"].value=overOccurMoney;

		document.forms[0].elements["overplusInterestAll"].value=overplusInterestAll;
		document.forms[0].elements["interestAll"].value=interestAll;
		
		document.forms[0].elements["sumMoney"].value=sumMoney;
}
function display1(corpusInterest,overplusInterestAll,interestAll){
	var loanMode=document.forms[0].elements["borrowerInfoDTO.loanMode"].value.trim();
	if(loanMode=="等额本金"){
		document.forms[0].elements["corpusInterest"].value="";
	}else{
		document.forms[0].elements["corpusInterest"].value=corpusInterest;
	}
	
		document.forms[0].elements["overplusInterestAll"].value=overplusInterestAll;
		document.forms[0].elements["interestAll"].value=interestAll;
	
}

</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();"
	onContextmenu="return false">
	<html:form action="/consultationTaMaintainAC">
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
										<td width="112" height="37" background="" valign="top"
											style="PADDING-top: 7px"></td>
										<td width="112" height="37" background=""
											style="PADDING-top: 7px" valign="top"></td>
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
										<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">回收贷款&gt;回收咨询</font>
										</td>
										<td width="29" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="176">
											<b class="font14">借 款 人 信 息</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="734">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" id="table9" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="17%" class=td1>
								合同编号
								<br>
							</td>
							<td class="td4" width="20%">
								<html:text property="borrowerInfoDTO.contractId"
									ondblclick="executeAjax();" onkeydown="gotoEnter();"
									styleClass="input3" />
							</td>
							<td class="td4">
								<input type="button" class="buttona" value="..." name="button32"
									onclick="gotoSearch();">
							</td>
							<td class="td1" width="17%">
								扣款账号
							</td>
							<td class="td4" width="33%">
								<html:text property="borrowerInfoDTO.loanKouAcc"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								借款人姓名
							</td>
							<td class="td4" colspan="2">
								<html:text property="borrowerInfoDTO.borrowerName"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class=td1>
								证件类型
							</td>
							<td class="td4" width="33%">
								<html:text property="borrowerInfoDTO.cardKind"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								证件号码
							</td>
							<td class="td4" colspan="2">
								<html:text property="borrowerInfoDTO.cardNum"
									styleClass="input3" readonly="true" />
							</td>
							<td class=td1 width="17%">
								剩余本金
							</td>
							<td class="td4" width="33%">
								<html:text property="borrowerInfoDTO.overplusLoanMoney"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								还款方式
							</td>
							<td class="td4" colspan="2">
								<html:text property="borrowerInfoDTO.loanMode"
									styleClass="input3" readonly="true" />
							</td>
							<td class=td1 width="17%">
								还至年月
							</td>
							<td class="td4" width="33%">
								<html:select property="monthYear" styleClass="input4"
									onchange="gotoMonth();" styleId="select1">
									<html:option value=""></html:option>
									<html:options collection="monthYearList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="174">
											<b class="font14">应还款信息列表</b>
										</td>
										<td width="736" height="22" align="center"
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
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td align="center" class=td1>
								还款年月
							</td>
							<td align="center" class=td2>
								还款类型
							</td>
							<td align="center" class=td2>
								应还本金
							</td>
							<td align="center" class=td2>
								应还利息
							</td>
							<td align="center" class=td2>
								逾期天数
							</td>
							<td align="center" class=td2>
								逾期利息
							</td>
							<td align="center" class=td2>
								应还本息合计
							</td>
							<td align="center" class=td2>
								每月利率
							</td>
						</tr>

						<logic:notEmpty name="loancallbackTaAF" property="shouldBackList">
							<logic:iterate name="loancallbackTaAF" property="shouldBackList"
								id="element">
								<tr id="tr1" class=td4 onDblClick="">
									<td valign="top">
										<bean:write name="element" property="loanKouYearmonth" />
									</td>
									<td valign="top">
										<bean:write name="element" property="loanKouType" />
									</td>
									<td valign="top">
										<bean:write name="element" property="shouldCorpus" />
									</td>
									<td valign="top">
										<bean:write name="element" property="shouldInterest" />
									</td>
									<td valign="top">
										<bean:write name="element" property="days" />
									</td>
									<td valign="top">
										<bean:write name="element" property="punishInterest" />
									</td>
									<td valign="top">
										<bean:write name="element" property="ciMoney" />
									</td>
									<td valign="top">
										<bean:write name="element" property="show_loanRate" />
									</td>
								</tr>
								<tr>
									<td colspan="9" class=td5></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="loancallbackTaAF" property="shouldBackList">
							<tr>
								<td colspan="9" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>
							<tr>
								<td colspan="9" class=td5></td>
							</tr>
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="172">
											<b class="font14"> 还 款 信 息</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="738">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" id="table9" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="17%" class=td1>
								业务类型
							</td>
							<td class="td4">
								<html:radio property="bizType" value="2" onclick="gotoType1();"
									styleId="radio1" />
								单笔回收
								<html:radio property="bizType" value="3" onclick="gotoType2();"
									styleId="radio2" />
								部分提前还款
								<html:radio property="bizType" value="4" onclick="gotoType3();"
									styleId="radio3" />
								一次性清还
							</td>
							<td class="td1" width="17%">
								提前还款类型
								<input type="hidden" name="biztype"
									value="<bean:write name="loancallbackTaAF" property="bizType"/>">
								<input type="hidden" name="balance"
									value="<bean:write name="loancallbackTaAF" property="loanBalance"/>">
								<html:hidden property="borrowerInfoDTO.loanBankId" />
								<html:hidden property="borrowerInfoDTO.officeCode" />
								<html:hidden property="isAmend" />
								<html:hidden property="isAmendLine" />
							</td>
							<td width="33%">
								<html:text property="aheadTypeS" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								提前还款本金
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td class="td4">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="aheadCorpus" styleClass="input3"
										disabled="true" />
								</logic:equal>
								<html:hidden property="overplusCorpus" />
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="aheadCorpus" styleClass="input3"
										onkeydown="return gotoEnter1();" />
								</logic:notEqual>
							</td>
							<td class="td1" width="17%">
								占用天数
							</td>
							<td class="td4" width="33%">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="days" styleClass="input3" disabled="true" />
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="days" styleClass="input3" readonly="true" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								提前还款利息
							</td>
							<td class="td4">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="aheadInterest" styleClass="input3"
										disabled="true" />
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="aheadInterest" styleClass="input3"
										readonly="true" />
								</logic:notEqual>
							</td>
							<td width="17%" class=td1>
								手续费金额
							</td>
							<td class="td4" width="33%">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="loanPoundageMoney" styleClass="input3"
										disabled="true" />
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="loanPoundageMoney" styleClass="input3"
										readonly="true" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								提前还款后剩余期限
								<font color="#FF0000">*</font>
							</td>
							<td class="td4">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="deadLine" styleClass="input3"
										disabled="true" />
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="deadLine" styleClass="input3"
										onkeydown="return gotoEnterLine();" />
								</logic:notEqual>
							</td>
							<td class=td1 width="17%">
								提前还款后月还本息
							</td>
							<td class="td4" width="33%">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="corpusInterest" styleClass="input3"
										disabled="true" />
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="corpusInterest" styleClass="input3"
										readonly="true" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								本次总还款本金
							</td>
							<td class="td4">
								<html:text property="sumCorpus" styleClass="input3"
									readonly="true" />
							</td>
							<td class=td1 width="17%">
								本次总还款利息
							</td>
							<td class="td4" width="33%">
								<html:text property="sumInterest" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								本次总还款金额
							</td>
							<td class="td4">
								<html:text property="sumMoney" styleClass="input3"
									readonly="true" />
							</td>
							<td class=td1 width="17%">
								挂账余额
							</td>
							<td class="td4" width="33%">
								<html:text property="ovaerLoanRepay" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								本次实收金额
								<font color="#FF0000">*</font>
							</td>
							<td class="td4">
								<html:text property="realMoney" styleClass="input3" />
								<input type="hidden" name="realMoney1"
									value="<bean:write name="loancallbackTaAF" property="realMoney"/>">
							</td>
							<td class=td1 width="17%">
								挂账发生额
							</td>
							<td width="33%">
								<html:text property="overOccurMoney" styleClass="input3"
									readonly="true" />
							</td>
						</tr>

					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">

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
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												styleId="disp1" onclick="">
												<bean:message key="button.print" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
		</table>
	</html:form>
	<form action="<%=path%>/sysloan/consultationTaShowAC.do" method="POST"
		name="Form1" id="Form1">
	</form>
</body>

</html:html>

