<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Integer plLoanReturnType = new Integer(0);
	plLoanReturnType = (Integer) request.getSession().getAttribute(
			"plLoanReturnType");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

	<title>办理错账调整</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />
  
</head>
<script language="javascript">
	var oldColor="#ffffff";                     //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;		//保存当前颜色
		oTD.style.backgroundColor=newColor;		//改变表格颜色
		newColor=oldColor;                 		//改变下次要变成的颜色
	}
</script>
<script>

var old_putOutMoney; // 原有的发生金额
var old_corpus;          // 单笔调整时，原有的本金
var old_interest;          // 单笔调整时，原有的利息
var old_punishInterest;    // 单笔调整时，原有罚息
var is_print; // 是否进行打印

function init(){
		if(<%=plLoanReturnType%>==2){
			document.forms[0].elements["adjustAccountTaInfoDTO.autoOverPay"].disabled=true;
		}
		document.forms[0].elements["adjustAccountTaInfoDTO.docNum"].value="";
		document.forms[0].elements["adjustAccountTaInfoDTO.strBizType"].value="";
		document.forms[0].elements["adjustAccountTaInfoDTO.putOutMoney"].value="";
		document.forms[0].elements["adjustAccountTaInfoDTO.callbackMoney"].value="";
		document.forms[0].elements["adjustAccountTaInfoDTO.callbackInterest"].value="";
		document.forms[0].elements["adjustAccountTaInfoDTO.punishInterest"].value="";
		document.forms[0].elements["adjustAccountTaInfoDTO.borrowerName"].value="";
		document.forms[0].elements["adjustAccountTaInfoDTO.makePerson"].value="";
		document.forms[0].elements["adjustAccountTaInfoDTO.yearMonth"].value="";
		
		document.forms[0].elements["adjustAccountTaInfoDTO.yearMonth"].disabled=true;
}

function gotoAssistantorgpop(path,indexs){
	window.open(path+"/sysloan/adjustAccountPopShowAC.do?indexs="+indexs+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function checkMoney1(money){
	if(money==0){
	   return true;
	}
	var salarybase = money.match(/^(([0-9]{1})|(-[0-9]{1}))([0-9]{0,10})(\.[0-9]{1,2})?$/)
	if (salarybase==null){	
		return false;
	}else{
		return true;
	}

}
function gotoPop(){
	gotoAssistantorgpop("<%=path%>",0);
}

function gotoLoanKouAccgPop(){
	gotoLoankouaccpop('',"<%=path%>",4);
}

function executeAjax(){
	var queryString = "adjustAccountDocNumShowAAC.do?";
    var flowHeadId = document.forms[0].elements["adjustAccountTaInfoDTO.flowHeadId"].value.trim();
    queryString = queryString + "id="+flowHeadId; 	     
    findInfo(queryString);
}
function executeAjaxIn(){
	var queryString = "adjustAccountLoanKouAccShowAAC.do?";
    var loanKouAcc = document.forms[0].elements["adjustAccountTaInfoDTO.loanKouAcc"].value.trim();
    queryString = queryString + "id="+loanKouAcc; 	     
    findInfo(queryString);
}
function reportErrors() {
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
}

function executeAjaxYear(){
	var yearMonth = document.forms[0].elements["adjustAccountTaInfoDTO.yearMonth"].value
	var loanKouAcc = document.forms[0].elements["adjustAccountTaInfoDTO.loanKouAcc"].value

	if(loanKouAcc==null||loanKouAcc==''){
		alert('请填写扣款账号!');
		return false;
	}
	if(yearMonth==null||yearMonth==''){
		alert('请填写还款年月!');
		return false;
	}
	if(!checkMonth(document.forms[0].elements["adjustAccountTaInfoDTO.yearMonth"])){
		return false;
	}
	var queryString = "adjustAccountYearMonthAAC.do?";

    queryString = queryString + "yearMonth="+yearMonth+"&loanKouAcc="+loanKouAcc; 	     
    findInfo(queryString);
}

/*---用来判断年月YYYYMM---*/
function checkMonth(month){
  var strMonth = month.value;
  if(strMonth.length!=6){
    alert("请输入六位的年月，格式为200410！");
    return false;
  }
  if(strMonth.substring(0,4)<1900){
    alert("年份应该大于1900！");
    return false;
  }
  if(strMonth.substring(4,6)>12 || strMonth.substring(4,6)<1){
    alert("月份应该在1-12月之间！");
    return false;
  }
  return true;
}
function displays(resFlowHeadId,bizType,strBizType,callbackMoney,putOutMoney,callbackInterest,punishInterest,borrowerName,makePerson,error,loanKouAcc){
	if(error.length!=0){
		document.forms[0].elements["method"].disabled=true;
		init();
		alert(error);
	}else{
		if(bizType=='2'||bizType=='5'||bizType=='6'||bizType=='7'||bizType=='1'){
			document.forms[0].elements["adjustAccountTaInfoDTO.strBizType"].readOnly=true;
			if(bizType=='1'){
				document.forms[0].elements["adjustAccountTaInfoDTO.putOutMoney"].readOnly=false;
				document.forms[0].elements["adjustAccountTaInfoDTO.autoOverPay"].disabled=true;
			}else{
				document.forms[0].elements["adjustAccountTaInfoDTO.putOutMoney"].readOnly=true;
				document.forms[0].elements["adjustAccountTaInfoDTO.autoOverPay"].disabled=false;
			}
			document.forms[0].elements["adjustAccountTaInfoDTO.callbackMoney"].readOnly=true;
			document.forms[0].elements["adjustAccountTaInfoDTO.callbackInterest"].readOnly=true;
			document.forms[0].elements["adjustAccountTaInfoDTO.punishInterest"].readOnly=true;
			document.forms[0].elements["adjustAccountTaInfoDTO.borrowerName"].readOnly=true;
			document.forms[0].elements["adjustAccountTaInfoDTO.makePerson"].readOnly=true;
			if(bizType=='6'||bizType=='7'){
				document.forms[0].elements["adjustAccountTaInfoDTO.autoOverPay"].disabled=true;
			}
		}
		//document.forms[0].elements["adjustAccountTaInfoDTO.docNum"].value=resFlowHeadId;
		document.forms[0].elements["adjustAccountTaInfoDTO.strBizType"].value=strBizType;
		document.forms[0].elements["adjustAccountTaInfoDTO.putOutMoney"].value=putOutMoney;
		document.forms[0].elements["adjustAccountTaInfoDTO.callbackMoney"].value=callbackMoney;
		document.forms[0].elements["adjustAccountTaInfoDTO.callbackInterest"].value=callbackInterest;
		document.forms[0].elements["adjustAccountTaInfoDTO.punishInterest"].value=punishInterest;
		document.forms[0].elements["adjustAccountTaInfoDTO.borrowerName"].value=borrowerName;
		document.forms[0].elements["adjustAccountTaInfoDTO.makePerson"].value=makePerson;
		document.forms[0].elements["adjustAccountTaInfoDTO.bizType"].value=bizType;
		document.forms[0].elements["adjustAccountTaInfoDTO.loanKouAcc"].value="";
		document.forms[0].elements["loanKouAcc"].value=loanKouAcc;
		old_putOutMoney = document.forms[0].elements["adjustAccountTaInfoDTO.putOutMoney"].value;
		document.forms[0].elements["method"].disabled=false;
		document.forms[0].elements["adjustAccountTaInfoDTO.yearMonth"].disabled=true;
		if(<%=plLoanReturnType%>==2){
			document.forms[0].elements["adjustAccountTaInfoDTO.autoOverPay"].disabled=true;
		}
	}
}

function displaysIn(resFlowHeadId,bizType,strBizType,callbackMoney,putOutMoney,callbackInterest,punishInterest,borrowerName,makePerson,error){
	if(error.length!=0){
		document.forms[0].elements["method"].disabled=true;
		init();
		alert(error);
	}else{
	
		document.forms[0].elements["adjustAccountTaInfoDTO.callbackMoney"].readOnly=true;
		document.forms[0].elements["adjustAccountTaInfoDTO.callbackInterest"].readOnly=false;
		document.forms[0].elements["adjustAccountTaInfoDTO.punishInterest"].readOnly=false;
			
		document.forms[0].elements["adjustAccountTaInfoDTO.putOutMoney"].readOnly=true;
		document.forms[0].elements["adjustAccountTaInfoDTO.borrowerName"].readOnly=true;
		document.forms[0].elements["adjustAccountTaInfoDTO.makePerson"].readOnly=true;
		document.forms[0].elements["adjustAccountTaInfoDTO.yearMonth"].disabled=false;
		document.forms[0].elements["adjustAccountTaInfoDTO.autoOverPay"].disabled=true;
		document.forms[0].elements["adjustAccountTaInfoDTO.strBizType"].readOnly=true;
		
		//document.forms[0].elements["adjustAccountTaInfoDTO.docNum"].value=resFlowHeadId;
		document.forms[0].elements["adjustAccountTaInfoDTO.callbackMoney"].value=callbackMoney;
		document.forms[0].elements["adjustAccountTaInfoDTO.callbackInterest"].value=callbackInterest;
		document.forms[0].elements["adjustAccountTaInfoDTO.punishInterest"].value=punishInterest;
		document.forms[0].elements["adjustAccountTaInfoDTO.borrowerName"].value=borrowerName;
		document.forms[0].elements["adjustAccountTaInfoDTO.strBizType"].value="";
		document.forms[0].elements["adjustAccountTaInfoDTO.makePerson"].value="";
		document.forms[0].elements["adjustAccountTaInfoDTO.putOutMoney"].value="";
		
		document.forms[0].elements["method"].disabled=true;
	}
}
function displaysYear(error,corpus,interest,punishInterest){
	if(error.length!=0){
		document.forms[0].elements["method"].disabled=true;
		alert(error);
	}else{
		document.forms[0].elements["method"].disabled=false;
		old_corpus=corpus;
		old_interest=interest;
		old_punishInterest=punishInterest;
		document.forms[0].elements["method"].disabled=false;
	}
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjaxIn();
	}
}
function check(){

	var putOutMoney = document.forms[0].elements["adjustAccountTaInfoDTO.putOutMoney"].value;
	var callbackMoney = document.forms[0].elements["adjustAccountTaInfoDTO.callbackMoney"].value;
	var callbackInterest = document.forms[0].elements["adjustAccountTaInfoDTO.callbackInterest"].value;
	var punishInterest = document.forms[0].elements["adjustAccountTaInfoDTO.punishInterest"].value;
	var bizType = document.forms[0].elements["adjustAccountTaInfoDTO.bizType"].value;
	
	if(bizType!=null&&bizType!=''){
	//批量调整
		if(!checkMoney1(putOutMoney)){
			alert("请正确格式的放款金额！格式如：1087.23");
			return false;
		}
		if(Math.abs(old_putOutMoney)+parseFloat(putOutMoney)<0){
			alert("输入的放款金额不正确！");
			return false;
		}
	}else{
	// 单笔调整
		if(!checkMoney1(callbackMoney)){
			alert("请正确格式的回收本金！格式如：1087.23");
			return false;
		}
		if(!checkMoney1(callbackInterest)){
			alert("请正确格式的回收利息！格式如：1087.23");
			return false;
		}
		if(!checkMoney1(punishInterest)){
			alert("请正确格式的罚息利息！格式如：1087.23");
			return false;
		}
		
		if(parseFloat(old_corpus)+parseFloat(callbackMoney)<0){
			alert("输入的回收本金不正确！");
			return false;
		}
		if(parseFloat(old_interest)+parseFloat(callbackInterest)<0){
			alert("输入的回收利息不正确！");
			return false;
		}
		if(parseFloat(old_punishInterest)+parseFloat(punishInterest)<0){
			alert("输入的罚息利息不正确！");
			return false;
		}
	}
	
	// 保证确定的时候金额为空不报错
	if(putOutMoney==null||putOutMoney==''){
		document.forms[0].elements["adjustAccountTaInfoDTO.putOutMoney"].value=0.00;
	}
	if(callbackMoney==null||callbackMoney==''){
		document.forms[0].elements["adjustAccountTaInfoDTO.callbackMoney"].value=0.00;
	}
	if(callbackInterest==null||callbackInterest==''){
		document.forms[0].elements["adjustAccountTaInfoDTO.callbackInterest"].value=0.00;
	}
	if(punishInterest==null||punishInterest==''){
		document.forms[0].elements["adjustAccountTaInfoDTO.punishInterest"].value=0.00;
	}
	isPrint();
}
function isPrint(){
  var x=confirm("提示是否打印凭证！");
  if(x){
	is_print="print";
  }else{
    is_print="noPrint";
  }
  document.forms[0].elements["isPrint"].value=is_print;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();init();"
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
										办理错账调整
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="adjustAccountTbForwardAC.do" class=a2>错账调整维护</a>
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
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">账务处理&gt;错账调整</font>
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
				<html:form action="/adjustAccountTaNewAC">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="161">
											<b class="font14">错 账 调 整</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="764">
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
								错账凭证编号
							</td>
							<td class="td4" width="20%">
								<html:text name="adjustAccountAF"
									property="adjustAccountTaInfoDTO.docNum" styleClass="input3"
									maxlength="100" readonly="true"></html:text>
							</td>
							<td class="td4">
								<input type="button" class="buttona" value="..." name="button32"
									onClick="gotoPop();">
							</td>
							<td class="td1" width="17%">
								&nbsp;
							</td>
							<td class="td4" width="33%">
								<input name="textfield302232233" type="text" id="txtsearch45225"
									class="input3" readonly="true">
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								扣款账号
							</td>
							<td class="td4">
								<html:text name="adjustAccountAF"
									property="adjustAccountTaInfoDTO.loanKouAcc"
									styleClass="input3" maxlength="100"
									ondblclick="executeAjaxIn();" onkeydown="gotoEnter();"></html:text>
							</td>
							<td class="td4">
								<input type="button" class="buttona" value="..."
									name="button322" onClick="gotoLoanKouAccgPop();">
							</td>
							<td width="17%" class=td1>
								还款年月
							</td>
							<td class="td4" width="33%">
								<html:text name="adjustAccountAF"
									property="adjustAccountTaInfoDTO.yearMonth" styleClass="input3"
									maxlength="6" onblur="return executeAjaxYear();"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								发放金额
							</td>
							<td class="td4" colspan="2">
								<html:text name="adjustAccountAF"
									property="adjustAccountTaInfoDTO.putOutMoney"
									styleClass="input3" maxlength="15"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td class=td1 width="17%">
								回收本金
							</td>
							<td class="td4" width="33%">
								<font color="#FF0000"> <html:text name="adjustAccountAF"
										property="adjustAccountTaInfoDTO.callbackMoney"
										styleClass="input3" maxlength="15"
										onkeydown="enterNextFocus1();"></html:text> </font>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								回收利息
							</td>
							<td class="td4" colspan="2">
								<html:text name="adjustAccountAF"
									property="adjustAccountTaInfoDTO.callbackInterest"
									styleClass="input3" maxlength="15"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td class=td1 width="17%">
								逾期利息
							</td>
							<td class="td4" width="33%">
								<html:text name="adjustAccountAF"
									property="adjustAccountTaInfoDTO.punishInterest"
									styleClass="input3" maxlength="15"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%" height="26">
								借款人姓名
							</td>
							<td class="td4" colspan="2" height="26">
								<html:text name="adjustAccountAF"
									property="adjustAccountTaInfoDTO.borrowerName"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td class=td1 width="17%" height="26">
								制单人
							</td>
							<td class="td4" width="33%" height="26">
								<html:text name="adjustAccountAF"
									property="adjustAccountTaInfoDTO.makePerson"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								自动挂账
							</td>
							<td class="td4" colspan="2">
								<html:select name="adjustAccountAF"
									property="adjustAccountTaInfoDTO.autoOverPay"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<html:optionsCollection property="autoOverPayMap"
										name="adjustAccountAF" label="value" value="key" />
								</html:select>
							</td>
							<td class=td1 width="17%">
								业务类型
							</td>
							<td class="td4" width="33%">
								<html:text name="adjustAccountAF"
									property="adjustAccountTaInfoDTO.strBizType"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<html:hidden name="adjustAccountAF"
								property="adjustAccountTaInfoDTO.bizType" />
							<html:hidden name="adjustAccountAF"
								property="adjustAccountTaInfoDTO.flowHeadId" />
							<input name="isPrint" type="hidden" id="isPrint" class="input3">
							<input name="loanKouAcc" type="hidden" id="loanKouAcc"
								class="input3">
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											&nbsp;
											<html:submit property="method" styleClass="buttona"
												disabled="true" onclick="return check();">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
	</table>
</body>
</html:html>
