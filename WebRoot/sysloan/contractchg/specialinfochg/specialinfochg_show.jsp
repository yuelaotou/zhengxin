<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>特殊信息变更</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>
	
  </head>
<script>
function reportErrors(message) {
	if(message!=null){
		alert(message);
	}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
var s1="";
function onload(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="确定"){
				s1=i;
			}
		}
	}
	document.all.item(s1).disabled="true";
}
function executeAjax() {
	var queryString = "specialInfoChgFindAAC.do?";
    var contractId = document.forms[0].elements["specialInfoChgDTO.contractId"].value.trim();
    if(contractId==""){
	alert("请你输入合同编号");
	}
	else if(isNaN(contractId) || contractId.indexOf(".")>0){
	alert("请你输入合法的整数数字");
	}
	else{
		queryString = queryString + "contractId="+contractId; 
		findInfo(queryString);
    }
}
function goEnter(){
	document.all.item(s1).disabled="true";
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function toContractpop(){
	gotoContractpop('','<%=path%>','0');
}
function displays(borrowerName,loanMoney,loanTimeLimit,loanMonthRate,temp_loanMode,corpusInterest,firstLoanMoney,loanPoundage,aheadReturnAfter,
					partReturnMonthLT,isPartAheadReturn,allReturnMonthLT,isAllReturn,leastReturnMoney,mostAheadReturnYearTimes,mostAheadReturnTimes,
						isAccept,chargeMode,aheadReturnMoneyPercent,money,loanBankId){
						
	document.forms[0].elements["specialInfoChgDTO.borrowerName"].value=borrowerName;
	document.forms[0].elements["specialInfoChgDTO.loanMoney"].value=loanMoney;
	var loanMoneyCH=ChangeToBig(loanMoney);
	document.forms[0].elements["loanMoneyCH"].value=loanMoneyCH;
	document.forms[0].elements["specialInfoChgDTO.loanTimeLimit"].value=loanTimeLimit;
	document.forms[0].elements["specialInfoChgDTO.loanMonthRate"].value=loanMonthRate;
	document.forms[0].elements["specialInfoChgDTO.temp_loanMode"].value=temp_loanMode;
	document.forms[0].elements["specialInfoChgDTO.corpusInterest"].value=corpusInterest;
	document.forms[0].elements["specialInfoChgDTO.firstLoanMoney"].value=firstLoanMoney;
	document.forms[0].elements["specialInfoChgDTO.loanPoundage"].value=loanPoundage;
	var v1=document.forms[0].elements["specialInfoChgDTO.aheadReturnAfter"];
	if(aheadReturnAfter!=""){
		if(aheadReturnAfter==1){
			v1[0].checked=true;
		}
		if(aheadReturnAfter==2){
			v1[1].checked=true;
		}
		if(aheadReturnAfter==3){
			v1[2].checked=true;
		}
	}
	document.forms[0].elements["specialInfoChgDTO.partReturnMonthLT"].value=partReturnMonthLT;
	var v2=document.forms[0].elements["specialInfoChgDTO.isPartAheadReturn"];
	if(isPartAheadReturn!=""){
		if(isPartAheadReturn==1){
			v2[0].checked=true;
		}
		if(isPartAheadReturn==2){
			v2[1].checked=true;
		}
	}
	document.forms[0].elements["specialInfoChgDTO.allReturnMonthLT"].value=allReturnMonthLT;
	var v3=document.forms[0].elements["specialInfoChgDTO.isAllReturn"];
	if(isAllReturn!=""){
		if(isAllReturn==1){
			v3[0].checked=true;
		}
		if(isAllReturn==2){
			v3[1].checked=true;
		}
	}
	document.forms[0].elements["specialInfoChgDTO.leastReturnMoney"].value=leastReturnMoney;
	document.forms[0].elements["specialInfoChgDTO.mostAheadReturnYearTimes"].value=mostAheadReturnYearTimes;
	document.forms[0].elements["specialInfoChgDTO.mostAheadReturnTimes"].value=mostAheadReturnTimes;
	var v4=document.forms[0].elements["specialInfoChgDTO.isAccept"];
	if(isAccept!=""){
		v4[1].checked=true;
	}
	var v5=document.forms[0].elements["specialInfoChgDTO.chargeMode"];
	if(chargeMode!=""){
		v4[0].checked=true;
		if(chargeMode==1){
			v5[0].checked=true;
		}
		if(chargeMode==2){
			v5[1].checked=true;
		}
	}
	document.forms[0].elements["specialInfoChgDTO.aheadReturnMoneyPercent"].value=aheadReturnMoneyPercent;
	document.forms[0].elements["specialInfoChgDTO.money"].value=money;
	document.forms[0].elements["specialInfoChgDTO.loanBankId"].value=loanBankId;
	var va=document.forms[0].elements["specialInfoChgDTO.isAccept"];
	for(i=0;i<va.length;i++){
       if(va[i].checked){
       	document.forms[0].elements["isAccept"].value = va[i].value;
       }
    }
    var vb=document.forms[0].elements["specialInfoChgDTO.chargeMode"];
	for(i=0;i<vb.length;i++){
       if(vb[i].checked){
       	document.forms[0].elements["chargeMode"].value = vb[i].value;
       }
    }
	document.all.item(s1).disabled="";
}
function appValue(accept){
	document.forms[0].elements["isAccept"].value=accept.value.trim();
}
function appValue1(chargeMode){
	document.forms[0].elements["chargeMode"].value=chargeMode.value.trim();
}
function clear1(){
	document.forms[0].elements["specialInfoChgDTO.partReturnMonthLT"].value="";
}
function clear2(){
	document.forms[0].elements["specialInfoChgDTO.allReturnMonthLT"].value="";
}
function onCheck(){
	var v1=document.forms[0].elements["specialInfoChgDTO.aheadReturnAfter"];
	if(v1[0].checked==false&&v1[1].checked==false&&v1[2].checked==false){
		alert('第1条必须三选一！');
		return false;
	}
	var partReturnMonthLT=document.forms[0].elements["specialInfoChgDTO.partReturnMonthLT"].value.trim();
	var v2=document.forms[0].elements["specialInfoChgDTO.isPartAheadReturn"];
	if(v2[0].checked==false&&v2[1].checked==false){
		alert('第2条必须二选一！');
		return false;
	}
	if(v2[0].checked==true){
		if(partReturnMonthLT==""||partReturnMonthLT==0){
			alert('第2条还款时间小于多少月必须输入！');
			document.forms[0].elements["specialInfoChgDTO.partReturnMonthLT"].focus();
			return false;
		}else if(isNaN(partReturnMonthLT) || partReturnMonthLT.indexOf(".")>0){
					alert("第2条还款时间小于多少月，请输入合法的整数数字");
					return false;
				}
	}
	var allReturnMonthLT=document.forms[0].elements["specialInfoChgDTO.allReturnMonthLT"].value.trim();
	var v3=document.forms[0].elements["specialInfoChgDTO.isAllReturn"];
	if(v3[0].checked==false&&v3[1].checked==false){
		alert('第3条必须二选一！');
		return false;
	}
	if(v3[0].checked==true){
		if(allReturnMonthLT==""||allReturnMonthLT==0){
			alert('第3条还款时间小于多少月必须输入！');
			document.forms[0].elements["specialInfoChgDTO.allReturnMonthLT"].focus();
			return false;
		}else if(isNaN(allReturnMonthLT) || allReturnMonthLT.indexOf(".")>0){
					alert("第3条还款时间小于多少月，请输入合法的整数数字");
					return false;
				}
	}
	var leastReturnMoney=document.forms[0].elements["specialInfoChgDTO.leastReturnMoney"].value.trim();
	if(leastReturnMoney==""){
		alert('第4条最低还款金额必须输入！');
		document.forms[0].elements["specialInfoChgDTO.leastReturnMoney"].focus();
		return false;
	}else{
		if(!checkMoney(leastReturnMoney)){
			document.forms[0].elements["specialInfoChgDTO.leastReturnMoney"].value="";
			document.forms[0].elements["specialInfoChgDTO.leastReturnMoney"].focus();
			return false;
		}
	}
	var mostAheadReturnYearTimes=document.forms[0].elements["specialInfoChgDTO.mostAheadReturnYearTimes"].value.trim();
	if(mostAheadReturnYearTimes==""){
		alert('第5条年度内最多允许提前还款次数必须输入！');
		document.forms[0].elements["specialInfoChgDTO.mostAheadReturnYearTimes"].focus();
		return false;
	}else{
		if(!isNumber(mostAheadReturnYearTimes)){
			alert('请输入正确的数字格式！');
			document.forms[0].elements["specialInfoChgDTO.mostAheadReturnYearTimes"].value="";
			document.forms[0].elements["specialInfoChgDTO.mostAheadReturnYearTimes"].focus();
			return false;
		}
	}
	var mostAheadReturnTimes=document.forms[0].elements["specialInfoChgDTO.mostAheadReturnTimes"].value.trim();
	if(mostAheadReturnTimes==""){
		alert('第6条贷款期限内最多允许提前还款次数必须输入！');
		document.forms[0].elements["specialInfoChgDTO.mostAheadReturnTimes"].focus();
		return false;
	}else{
		if(!isNumber(mostAheadReturnTimes)){
			alert('请输入正确的数字格式！');
			document.forms[0].elements["specialInfoChgDTO.mostAheadReturnTimes"].value="";
			document.forms[0].elements["specialInfoChgDTO.mostAheadReturnTimes"].focus();
			return false;
		}
	}
	var isAccept=document.forms[0].elements["isAccept"].value.trim();
	var chargeMode=document.forms[0].elements["chargeMode"].value.trim();
	var aheadReturnMoneyPercent=document.forms[0].elements["specialInfoChgDTO.aheadReturnMoneyPercent"].value.trim();
	var money=document.forms[0].elements["specialInfoChgDTO.money"].value.trim();
	if(isAccept==""){
		alert('第7条提前还款是否收取手续费必须二选一！');
		return false;
	}
	if(isAccept==4){
		if(chargeMode==""){
			alert('第7条收费方式必须二选一！');
			return false;
		}
		if(chargeMode==1){
			if(aheadReturnMoneyPercent==0){
				alert('第7条提前还款额必须输入！');
				document.forms[0].elements["specialInfoChgDTO.aheadReturnMoneyPercent"].focus();
				return false;
			}else{
				if(!checkMoney(aheadReturnMoneyPercent)){
					document.forms[0].elements["specialInfoChgDTO.aheadReturnMoneyPercent"].value="";
					document.forms[0].elements["specialInfoChgDTO.aheadReturnMoneyPercent"].focus();
					return false;
				}
			}
		}
		if(chargeMode==2){
			if(money==0){
				alert('第7条按额的金额必须输入！');
				document.forms[0].elements["specialInfoChgDTO.money"].focus();
				return false;
			}else{
				if(!checkMoney(money)){
					document.forms[0].elements["specialInfoChgDTO.money"].value="";
					document.forms[0].elements["specialInfoChgDTO.money"].focus();
					return false;
				}
			}
		}
	}
	return true;
}
</script> 
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="reportErrors();onload();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
            <td background="<%=path%>/img/table_bg_line.gif" width="10">&nbsp;</td>
            <td width="604" background="<%=path%>/img/table_bg_line.gif" valign="top">&nbsp; 
            </td>
            <td background="<%=path%>/img/table_bg_line.gif" align="right" width="313"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="228" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>合同变更</a><font color="00B5DB">&gt;</font><a href="#" class=a1>特殊信息变更</a></td>
                  <td width="35" class=font14>&nbsp;</td>
                </tr>
              </table>
            </td>
            <td width="10"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
      <td class=td3> 
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="185"><b class="font14">借款人额度信息</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="719">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <html:form action="/specialInfoChgSaveAC.do"  style="margin: 0">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center"  style="line-height:150%">
          <html:hidden name="specialInfoChgAF" property="specialInfoChgDTO.loanBankId"/>
          <input type="hidden" name="isAccept" value=""/>
      	  <input type="hidden" name="chargeMode" value=""/>
          <tr> 
            <td width="17%" class=td1>合同编号</td>
            <td class="td4" width="10%"> 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.contractId"  styleClass="input3"  onkeydown="goEnter();" ondblclick="executeAjax()"/>
            </td>
            <td class="td4" width="10%">
              <input type="button" name="Submit4" value="..." class="buttona"  onClick="toContractpop();">
            </td>
            <td width="15%"class=td1 > 借款人姓名</td>
            <td class="td4" width="20%"> 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.borrowerName"  styleClass="input3" readonly="true"/>
            </td>
            <td width="15%" class=td1>贷款金额</td>
            <td class="td4" width="19%"> 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.loanMoney"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class=td1>贷款金额（大写）</td>
            <td class="td4" width="20%" colspan="2"> 
              <input name="loanMoneyCH" type="text" id="txtsearch45225" class="input3" readonly="true">
            </td>
            <td width="15%"class=td1 >&#36151;&#27454;&#26399;&#38480;&#65288;&#26376;&#65289;</td>
            <td class="td4" width="20%"> 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.loanTimeLimit"  styleClass="input3" readonly="true"/>
            </td>
            <td width="15%" class=td1> 每月利率</td>
            <td class="td4" width="19%"> 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.loanMonthRate"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class=td1>还款方式</td>
            <td class="td4" width="20%" colspan="2"> 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.temp_loanMode"  styleClass="input3" readonly="true"/>
            </td>
            <td width="15%"class=td1 >月还本息</td>
            <td class="td4" width="19%"> 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.corpusInterest"  styleClass="input3" readonly="true"/>
            </td>
            <td width="15%" class=td1>首次还款金额</td>
            <td class="td4" width="19%"> 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.firstLoanMoney"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class=td1>手续费率</td>
            <td class="td4" width="20%" colspan="2"> 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.loanPoundage"  styleClass="input3" readonly="true"/>
            </td>
            <td width="15%"class=td1 >&nbsp;</td>
            <td class="td7" width="19%"> 
              &nbsp;
            </td>
            <td width="15%" class=td1>&nbsp;</td>
            <td class="td7" width="19%"> 
              &nbsp;
            </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="185"><b class="font14">提前还款参数变更</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="719">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr id="tr1"> 
            <td class="td1">1</td>
            <td class="td1">提前还款后　　 
            <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.aheadReturnAfter" value="1" onkeydown="enterNextFocus1();"/>
            保持原来月还款额　　 
            <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.aheadReturnAfter" value="2" onkeydown="enterNextFocus1();"/>
            保持剩余期限　　 
            <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.aheadReturnAfter" value="3" onkeydown="enterNextFocus1();"/>
            允许改变剩余期限</td>
          </tr>
          <tr id="tr1"> 
            <td class="td1">2</td>
            <td class="td1">还款时间小于 
            <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.partReturnMonthLT"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            月，部分提前还款。 
            <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.isPartAheadReturn" value="1" onkeydown="enterNextFocus1();"/>
            不允许 <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.isPartAheadReturn" value="2" onclick="clear1()" onkeydown="enterNextFocus1();"/>允许 
            </td>
          </tr>
          <tr id="tr1"> 
            <td class="td1">3</td>
            <td class="td1"> 还款时间小于 
            <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.allReturnMonthLT"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            月，一次性结清还款。 
            <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.isAllReturn" value="1" onkeydown="enterNextFocus1();"/>
            不允许 <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.isAllReturn" value="2" onclick="clear2()" onkeydown="enterNextFocus1();"/>允许 
            
            </td>
          </tr>
          <tr id="tr1"> 
            <td class="td1">4</td>
            <td class="td1"> 提前还款最低还款金额为 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.leastReturnMoney"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
              元</td>
          </tr>
          <tr id="tr1"> 
            <td class="td1">5</td>
            <td class="td1"> 年度内最多允许提前还款 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.mostAheadReturnYearTimes"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
              次 </td>
          </tr>
          <tr id="tr1"> 
            <td class="td1">6</td>
            <td class="td1"> 贷款期限内最多允许提前还款 
              <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.mostAheadReturnTimes"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
              次</td>
          </tr>
          <tr id="tr1"> 
            <td class="td1">7</td>
            <td class="td1">提前还款收取手续费 
            <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.isAccept" value="4" onclick="appValue(this)" onkeydown="enterNextFocus1();"/>
            是 
            <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.isAccept" value="3" onclick="appValue(this)" onkeydown="enterNextFocus1();"/>
            否， 
            <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.chargeMode" value="1" onclick="appValue1(this)" onkeydown="enterNextFocus1();"/>
            按提前还款额 
            <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.aheadReturnMoneyPercent"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            %收费 
            <html:radio name="specialInfoChgAF" property="specialInfoChgDTO.chargeMode" value="2" onclick="appValue1(this)" onkeydown="enterNextFocus1();"/>
            按额 
            <html:text name="specialInfoChgAF"  property="specialInfoChgDTO.money"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            收费</td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona" onclick="return onCheck();"><bean:message key="button.sure"/></html:submit>
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
