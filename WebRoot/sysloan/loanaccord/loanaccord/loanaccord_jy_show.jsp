<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanaccord.loanaccord.form.LoanaccordNewAF"%>
<%
String path = request.getContextPath();
request.setAttribute("URL", "loanaccordTaShowAC.do");
%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>贷款发放</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	<script language="javascript" src="js/common.js">
</head>
<script language="javascript" ></script>
	<script language="javascript" type="text/javascript">
function loads(){

	document.all.disp.disabled="true";
	document.all.dispEmp.disabled="true";
	document.all.bankNumber.disabled="true";
	document.all.startDay.disabled="true";
	var contractId=document.loanaccordNewAF.elements["loanaccordDTO.contractId"].value.trim();
	if(contractId!=''){
	chickButton(document.loanaccordNewAF.elements["loanaccordDTO.contractId"]);
	}
	var empId = document.loanaccordNewAF.elements["loanaccordDTO.empId"].value.trim();
	if(empId=="" || empId=="0"){
		document.all.dispEmp.disabled="true";
	}
}
function checkContractId(){
 	gotoContractpop('9','<%=path%>','0');
}
function chickButton(tempContractId){
var contractId=tempContractId.value.trim();
	if(contractId==''){
	document.all.disp.disabled="true";
	document.all.dispEmp.disabled="true";
	}else{
	var cardNum=document.loanaccordNewAF.elements["loanaccordDTO.cardNum"].value.trim();
	   if(cardNum!=''){
	   	   document.all.disp.disabled="";
	   	   document.all.bankNumber.disabled="";
	       document.all.startDay.disabled="";
	       document.all.dispEmp.disabled="";
	   }
	}
}
function executeAjax() {
   
	var contractId=document.loanaccordNewAF.elements["loanaccordDTO.contractId"].value.trim();
	location.href='<%=path%>/sysloan/loanaccordTaShowAC.do?contractId='+contractId;
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	if(message=='pass'){
		if(confirm("发放成功，是否打印发放凭证？")){
         location.href='<%=path%>/sysloan/loanaccordTaPrintAC.do'
		}
	 }else{
	 	alert(message);
	}
	</logic:messagesPresent>
}
function changeOverTime(loanstartTime){
	if(!checkDate(loanstartTime)){
		loanstartTime.focus();
	}else{
		//发放日
		var changeOverTime=loanstartTime.value.trim();
		//期限
		var tempLoanTimeLimit=document.loanaccordNewAF.elements["loanaccordDTO.loanTimeLimit"].value.trim();
		//剩余金额
		var loanMoney=document.loanaccordNewAF.elements["loanaccordDTO.loanMoney"].value.trim();
		var queryString = "loanaccordInforAAC.do?";
		//按户定日还是统一定日
		var loanRepayDayInfo=document.loanaccordNewAF.elements["loanaccordDTO.loanRepayDayInfo"].value.trim();
		//新月利率
		var loanMonthRate=document.loanaccordNewAF.elements["loanaccordDTO.loanMonthRate"].value.trim();
		//新月还本息
		var corpusInterest=document.loanaccordNewAF.elements["loanaccordDTO.corpusInterest"].value.trim();
		//还款方式
		var loanMode=document.loanaccordNewAF.elements["loanaccordDTO.loanMode"].value.trim();
		//当是统一定日的时候应把统一定日的时间传过去用于计算首月还款金额
		var temploanRepayDay=document.loanaccordNewAF.elements["loanaccordDTO.loanRepayDay"].value.trim();
		queryString = queryString + "changeOverTime="+changeOverTime+"&tempLoanTimeLimit="+tempLoanTimeLimit+"&loanRepayDayInfo="+loanRepayDayInfo+"&loanMoney="+loanMoney+"&loanMonthRate="+loanMonthRate+"&corpusInterest="+corpusInterest+"&loanMode="+loanMode+"&temploanRepayDay="+temploanRepayDay; 	     
		findInfo(queryString);
	}
}
function displays(overTime,loanRepayDay,firstLoanMoney) {

	document.loanaccordNewAF.elements["loanaccordDTO.overTime"].value=overTime;
	if(loanRepayDay!=''){
	document.loanaccordNewAF.elements["loanaccordDTO.loanRepayDay"].value=loanRepayDay;
	}
	document.loanaccordNewAF.elements["loanaccordDTO.firstLoanMoney"].value=firstLoanMoney;
}
function checkLoanKouAcc() {
	var loanKouAcc = document.loanaccordNewAF.elements["loanaccordDTO.loanKouAcc"].value.trim();
	if(!isNumLetter(loanKouAcc)){
		document.loanaccordNewAF.elements["loanaccordDTO.loanKouAcc"].focus();
		alert("请输入正确的扣款账号!");
		return false;
	}
}
function enterToSubmit(){
  if(event.keyCode == 13){
   var contractId=document.loanaccordNewAF.elements["loanaccordDTO.contractId"].value.trim();
	location.href='<%=path%>/sysloan/loanaccordTaShowAC.do?contractId='+contractId;
  }
}
function checkdata(){
	var loanKouAcc = document.loanaccordNewAF.elements["loanaccordDTO.loanKouAcc"].value.trim();
	if(loanKouAcc==''){
	   document.loanaccordNewAF.elements["loanaccordDTO.loanKouAcc"].focus();
		alert("扣款账号不能为空!");
		return false;
	}
}
function gotoEmpAccount(){
	var empId = document.loanaccordNewAF.elements["loanaccordDTO.empId"].value.trim();
	var orgId = document.loanaccordNewAF.elements["loanaccordDTO.orgId"].value.trim();
	var borrowerName=document.loanaccordNewAF.elements["loanaccordDTO.borrowerName"].value.trim();
	borrowerName =encodeURI(borrowerName);
	var cardNum=document.loanaccordNewAF.elements["loanaccordDTO.cardNum"].value.trim();
	window.open('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'newwindow','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=no,location=no,status=no');
	return false;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();"
	onContextmenu="return false">
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
										办理发放
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="<%=path%>/sysloan/loanaccordTbForwardUrlAC.do"
											class=a2>发放维护</a>
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
									<td width="143" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<font color="00B5DB">发放贷款</font><font color="00B5DB">&gt;</font><font
												color="00B5DB">发放贷款</font>
										</p>
									</td>
									<td width="120" class=font14>
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
							<table width="94%" border="0" cellspacing="0" cellpadding="0"
								align="center">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="152">
										<b class="font14">办 理 发 放</b>
									</td>
									<td width="773" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/loanaccordTaNewAC.do" style="margin: 0">
					<html:hidden name="loanaccordNewAF" property="loanaccordDTO.office"></html:hidden>
					<html:hidden name="loanaccordNewAF"
						property="loanaccordDTO.loanBankId"></html:hidden>
					<html:hidden name="loanaccordNewAF"
						property="loanaccordDTO.loanMode"></html:hidden>
					<html:hidden name="loanaccordNewAF"
						property="loanaccordDTO.cardKind"></html:hidden>
					<html:hidden name="loanaccordNewAF"
						property="loanaccordDTO.loanRateType"></html:hidden>
					<html:hidden name="loanaccordNewAF"
						property="loanaccordDTO.loanRepayDayInfo"></html:hidden>
					<html:hidden name="loanaccordNewAF"
						property="loanaccordDTO.loanMonthRate"></html:hidden>
					<html:hidden name="loanaccordNewAF"
						property="loanaccordDTO.empId"></html:hidden>
					<html:hidden name="loanaccordNewAF"
						property="loanaccordDTO.orgId"></html:hidden>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" width="95%" id="table1" cellspacing=1
									cellpadding=3 align="center">
									<tr>
										<td width="17%" class="td1">
											合同编号
										</td>
										<td width="22%">
											<html:text onkeydown="enterToSubmit();"
												name="loanaccordNewAF" onblur="chickButton(this);"
												property="loanaccordDTO.contractId" styleClass="input3"
												ondblclick="executeAjax();" />
										<td width="11%">
											<input type="button" class="buttona" value="..."
												onClick="checkContractId()">
										</td>
										<td width="17%" class="td1">
											借款人姓名
										</td>
										<td width="33%">
											<html:text 
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.borrowerName" styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											证件类型
										</td>
										<td width="33%" colspan="2">
											<html:text 
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.cardKindName" styleClass="input3" />
										</td>
										<td width="17%" class="td1">
											证件号码
										</td>
										<td width="33%">
											<html:text 
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.cardNum" styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											放款银行
										</td>
										<td width="33%" colspan="2">
											<html:text 
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.loanBankName" styleClass="input3" />
										</td>
										<td width="17%" class="td1">
											借款金额
										</td>
										<td width="33%">
											<html:text 
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.loanMoney" styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											借款期限（月）
										</td>
										<td width="33%" colspan="2">
											<html:text 
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.loanTimeLimit" styleClass="input3" />
										</td>
										<td width="17%" class="td1">
											每月利率
										</td>
										<td width="33%">
											<html:text 
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.temploanMonthRate" styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											还款方式
										</td>
										<td width="33%" colspan="2">
											<html:text 
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.loanModeName" styleClass="input3" />
										</td>
										<logic:empty name="loanaccordNewAF" property="loanaccordDTO.isEntire">
										<td width="17%" class="td1">
											月还本息
										</td>
										</logic:empty>
										<logic:notEmpty name="loanaccordNewAF" property="loanaccordDTO.isEntire">
										<td width="17%" class="td1">
											整年期总还款额
										</td>
										</logic:notEmpty>
										<td width="33%">
											<html:text 
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.corpusInterest" styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											扣款账号
									
										</td>
										<td width="33%" colspan="2">
											<html:text onkeydown="enterNextFocus1();" styleId="bankNumber"
												name="loanaccordNewAF" property="loanaccordDTO.loanKouAcc"
												styleClass="input3" readonly="true"/>
										</td>
										<td width="17%" class="td1">
											发放日期
											<font color="#FF0000">*</font>
										</td>
										<td width="33%">
											<html:text onkeydown="enterNextFocus1();" styleId="startDay"
												name="loanaccordNewAF" onblur="return changeOverTime(this);"
												property="loanaccordDTO.loanStartDate" styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											到期日期
										</td>
										<td width="33%" colspan="2">
											<html:text onkeydown="enterNextFocus1();"
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.overTime" styleClass="input3" />
										</td>
										<td width="17%" class="td1">
											还款日
										</td>
										<td width="33%">
											<html:text onkeydown="enterNextFocus1();"
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.loanRepayDay" styleClass="input3" />
										</td>
									</tr>
									<tr>
										<td width="17%" class="td1">
											首次还款金额
										</td>
										<logic:empty name="loanaccordNewAF" property="loanaccordDTO.isEntire">
										<td width="33%" colspan="2">
											<html:text onkeydown="enterNextFocus1();"
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.firstLoanMoney" styleClass="input3" />
										</td>
										</logic:empty>
										<logic:notEmpty name="loanaccordNewAF" property="loanaccordDTO.isEntire">
										<td width="33%" colspan="2">
											<html:text onkeydown="enterNextFocus1();"
												name="loanaccordNewAF" readonly="true"
												property="loanaccordDTO.firstLoanMoney" value="0" styleClass="input3" />
										</td>
										</logic:notEmpty>
										<td width="17%" class="td1">
										</td>
										<td width="33%">
										</td>
									</tr>
								</table>
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit styleClass="buttona" styleId="disp"
												onclick="return checkdata();">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
										<td width="110">
												<html:submit property="method" styleClass="buttonc" styleId="dispEmp" onclick="return gotoEmpAccount();">
													<bean:message key="button.look.empinfo"/>
												</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
