<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>

<%
String path = request.getContextPath();
String printInfo = (String) request.getAttribute("printInfo");
	Object destoryBackTaPrintDTO = request.getAttribute("destoryBackTaPrintDTO");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<title>核销收回</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script language="javascript">
var s1="";
function loads(){
for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="完成核销收回"){
				s1=i;
			}
		}
	} 
   <logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var printInfo='<%=printInfo%>';
	if(printInfo=='print') {
		var destoryBackTaPrintDTO='<%=destoryBackTaPrintDTO%>';
		if(confirm("是否打印核销收回凭证？")){
			showDoc();
  		}else {
  			return false;
  		}	
	}
}
function showDoc() {
  document.Form1.submit();
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax();
	}
}
function executeAjax() { 
    var queryString = "destoryBackTaFindAAC.do?";  
    var id=document.destoryBackTaAF.elements["loanKouAcc"].value;  
    if(id=="")
    {
     gotoloanKouAccPop();
	}
	else
	{
	 queryString = queryString + "loanKouAcc="+id;
	 findInfo(queryString);
	}
}

function display(contractId,borrowerName,cardKindName,cardNum,overplusLoanMoney,loanModeName,noBackMoney,message){
 	if(message!=""){
	document.destoryBackTaAF.elements["loanKouAcc"].focus();
	document.all.item(s1).disabled="true";
	document.forms[0].elements["contractId"].value="";
	document.forms[0].elements["borrowerName"].value="";
	document.forms[0].elements["cardKindName"].value="";
	document.forms[0].elements["cardNum"].value="";
	document.forms[0].elements["overplusLoanMoney"].value="";
	document.forms[0].elements["loanModeName"].value="";
	document.forms[0].elements["noBackMoney"].value="";
	document.forms[0].elements["backMoney"].value="";
	alert(message);
	document.destoryBackTaAF.elements["loanKouAcc"].value="";
	}
	else{

	document.all.item(s1).disabled="";
	document.forms[0].elements["contractId"].value=contractId;
	document.forms[0].elements["borrowerName"].value=borrowerName;
	document.forms[0].elements["cardKindName"].value=cardKindName;
	document.forms[0].elements["cardNum"].value=cardNum;
	document.forms[0].elements["overplusLoanMoney"].value=overplusLoanMoney;
	document.forms[0].elements["loanModeName"].value=loanModeName;
	document.forms[0].elements["noBackMoney"].value=noBackMoney;
	} 	
}
//担保公司弹出框
function gotoAssistantorgpop(path,indexs){

 window.open(path+"/sysloan/assistantorgpopFind.do?indexs="+indexs+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
 
}
function mode1(){
	
	document.destoryBackTaAF.elements["button2"].disabled="true";
	document.forms[0].elements["assistantOrgId"].value="";
	document.forms[0].elements["loanassistantorgId"].value="";
}

function mode2(){
	document.destoryBackTaAF.elements["button2"].disabled="";
}

function mode3(){
	document.destoryBackTaAF.elements["button2"].disabled="true";
	document.forms[0].elements["assistantOrgId"].value="";
	document.forms[0].elements["loanassistantorgId"].value="";
}
function gotoSure(){
   var backMoney = document.forms[0].elements["backMoney"].value;
   var noBackMoney=document.forms[0].elements["noBackMoney"].value;
   var aa=checkMoney(backMoney)
   if(!aa)
   {
    document.forms[0].elements["backMoney"].value="";
    return false;
   }
   if(backMoney==0)
   {
    alert("收回金额不能等于０!");
    document.forms[0].elements["backMoney"].value="";
    return false;
   }
   
  if(parseFloat(backMoney)>parseFloat(noBackMoney))
  {
   document.forms[0].elements["backMoney"].value="";
   alert("收回金额不能大于核销未收回金额!");
   return false;
  }
    if(confirm("是否完成核销收回？")){
		return true;
	} else {
		return false;
	}
 
}
function gotoloanKouAccPop(){
 gotoLoankouaccpop('','<%=path%>','0');
}
function enterToSubmit(){
  if(event.keyCode == 13){
  gotoLoankouaccpop('','<%=path%>','0');
  }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();" 
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
										办理核销收回
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="destoryBackTbForwardAC.do" class=a2>核销收回维护</a>
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
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										  <font color="00B5DB"> 回收贷款&gt;核销回收</font>
									</td>
									<td width="74" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="172">
										<b class="font14">借 款 人 信 息</b>
									</td>
									<td width="753" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;

									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/destoryBackTaSaveAC.do" style="margin: 0">
					<table width="95%" id="table9" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="17%" class=td1>
								贷款账号
								<br>
							</td>
							<td class="td4" width="20%">
								<html:text name="destoryBackTaAF" property="loanKouAcc"
									styleClass="input3" ondblclick="executeAjax();"
									onkeydown="gotoEnter()" />
							</td>
							<td class="td4" width="13%">
								<input type="button" class="buttona" value="..." onClick="gotoloanKouAccPop();" >
							</td>
							<td class="td1" width="17%">
								合同编号
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="destoryBackTaAF" property="contractId"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1 height="30">
								借款人姓名
							</td>
							<td class="td4" colspan="2" width="33%" height="30">
								<font color="#FF0000"> <html:text name="destoryBackTaAF"
										property="borrowerName" styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class=td1 height="30">
								证件类型
							</td>
							<td class="td4" width="33%" colspan="2" height="30">
								<html:text name="destoryBackTaAF" property="cardKindName"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								证件号码
							</td>
							<td class="td4" colspan="2">
								<font color="#FF0000"> <html:text name="destoryBackTaAF"
										property="cardNum" styleClass="input3" readonly="true" />
							</td>
							<td class=td1 width="17%">
								剩余本金
								<br>
							</td>
							<td class="td4" width="33%" colspan="2">
								<html:text name="destoryBackTaAF" property="overplusLoanMoney"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								还款方式
							</td>
							<td class="td4" colspan="2">
								<html:text name="destoryBackTaAF" property="loanModeName"
									styleClass="input3" readonly="true" />
							</td>
							<td class=td1 width="17%">
								&nbsp;
							</td>
							<td class="td4" width="33%" colspan="2">
								<input name="textfield3022112223" type="text"
									id="txtsearch45225" class="input3" readonly="true">
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%" height="26">
								收回单位
								<font color="#FF0000">*</font>
							</td>
							<td class="td7" colspan="2" height="26">
								<html:radio name="destoryBackTaAF" property="backUnit"
									value="中心" onclick="mode1();">中心</html:radio>
								<html:radio name="destoryBackTaAF" property="backUnit"
									value="担保公司" onclick="mode2();">担保公司</html:radio>
								<html:radio name="destoryBackTaAF" property="backUnit"
									value="其他" onclick="mode3();">其他</html:radio>
							</td>
							<td class=td1 width="17%" height="26">
								单位名称
							</td>
							<td class="td4" width="23%" height="26">
								<html:text name="destoryBackTaAF" property="assistantOrgId"
									styleClass="input3" readonly="true" />
							</td>
							<html:hidden name="destoryBackTaAF" property="loanassistantorgId" />
							<td class="td4" width="10%" height="26">
								<input type="button" class="buttona" value="..." name="button2"
									disabled="true"
									onclick="gotoAssistantorgpop('<%=path%>','10')">
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%" height="26">
								核销未收回金额
							</td>
							<td class="td4" colspan="2" height="26">
								<html:text name="destoryBackTaAF" property="noBackMoney"
									styleClass="input3" readonly="true" />
							</td>
							<td class=td1 width="17%" height="26">
								收回金额
								<font color="#FF0000">*</font>
							</td>
							<td class="td4" colspan="2" height="26">
								<html:text name="destoryBackTaAF" property="backMoney"
									styleClass="input3" />
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
											<html:submit property="method" styleClass="buttonb"
												disabled="true" onclick="return gotoSure();">
												<bean:message key="button.over.destoryback" />
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
	<form action="destoryBackTaPrintAC.do" method="POST" name="Form1"
		id="Form1">
	</form>
</body>
</html:html>
