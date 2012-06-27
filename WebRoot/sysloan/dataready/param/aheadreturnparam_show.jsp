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
    <title>参数维护</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>
	
  </head>
 <script>
function verdictLoanBankId(){
	var loanBankId=document.forms[0].elements["aheadReturnParamDTO.loanBankId"].value.trim();
	document.URL=('aheadReturnParamShowAC.do?loanBankId='+loanBankId);
}
function appValue(accept){
	document.forms[0].elements["isAccept"].value=accept.value.trim();
}
function appValue1(chargeMode){
	document.forms[0].elements["chargeMode"].value=chargeMode.value.trim();
}
function loads(){
	var va=document.forms[0].elements["aheadReturnParamDTO.isAccept"];
	for(i=0;i<va.length;i++){
       if(va[i].checked){
       	document.forms[0].elements["isAccept"].value = va[i].value;
       }
    }
    var vb=document.forms[0].elements["aheadReturnParamDTO.chargeMode"];
	for(i=0;i<vb.length;i++){
       if(vb[i].checked){
       	document.forms[0].elements["chargeMode"].value = vb[i].value;
       }
    }
}
function clear1(){
	document.forms[0].elements["aheadReturnParamDTO.partReturnMonthLT"].value="";
}
function clear2(){
	document.forms[0].elements["aheadReturnParamDTO.allReturnMonthLT"].value="";
}
function onCheck(){
	var loanBankId=document.forms[0].elements["aheadReturnParamDTO.loanBankId"].value.trim();
	if(loanBankId==""){
		alert("请选择银行！");
		return false;
	}
	var v1=document.forms[0].elements["aheadReturnParamDTO.aheadReturnAfter"];
	if(v1[0].checked==false&&v1[1].checked==false&&v1[2].checked==false){
		alert('第1条必须三选一！');
		return false;
	}
	var partReturnMonthLT=document.forms[0].elements["aheadReturnParamDTO.partReturnMonthLT"].value.trim();
	var v2=document.forms[0].elements["aheadReturnParamDTO.isPartAheadReturn"];
	if(v2[0].checked==false&&v2[1].checked==false){
		alert('第2条必须二选一！');
		return false;
	}
	if(v2[0].checked==true){
		if(partReturnMonthLT==""||partReturnMonthLT==0){
			alert('第2条还款时间小于多少月必须输入！');
			document.forms[0].elements["aheadReturnParamDTO.partReturnMonthLT"].focus();
			return false;
		}else{
			if(!isNumber(partReturnMonthLT)){
				alert('请输入正确的数字格式！');
				document.forms[0].elements["aheadReturnParamDTO.partReturnMonthLT"].value="";
				document.forms[0].elements["aheadReturnParamDTO.partReturnMonthLT"].focus();
				return false;
			}
		}
	}
	var allReturnMonthLT=document.forms[0].elements["aheadReturnParamDTO.allReturnMonthLT"].value.trim();
	var v3=document.forms[0].elements["aheadReturnParamDTO.isAllReturn"];
	if(v3[0].checked==false&&v3[1].checked==false){
		alert('第3条必须二选一！');
		return false;
	}
	if(v3[0].checked==true){
		if(allReturnMonthLT==""||allReturnMonthLT==0){
			alert('第3条还款时间小于多少月必须输入！');
			document.forms[0].elements["aheadReturnParamDTO.allReturnMonthLT"].focus();
			return false;
		}else{
			if(!isNumber(allReturnMonthLT)){
				alert('请输入正确的数字格式！');
				document.forms[0].elements["aheadReturnParamDTO.allReturnMonthLT"].value="";
				document.forms[0].elements["aheadReturnParamDTO.allReturnMonthLT"].focus();
				return false;
			}
		}
	}
	var leastReturnMoney=document.forms[0].elements["aheadReturnParamDTO.leastReturnMoney"].value.trim();
	if(leastReturnMoney==""){
		alert('第4条最低还款金额必须输入！');
		document.forms[0].elements["aheadReturnParamDTO.leastReturnMoney"].focus();
		return false;
	}else{
		if(!checkMoney(leastReturnMoney)){
			document.forms[0].elements["aheadReturnParamDTO.leastReturnMoney"].focus();
			return false;
		}
	}
	var mostAheadReturnYearTimes=document.forms[0].elements["aheadReturnParamDTO.mostAheadReturnYearTimes"].value.trim();
	if(mostAheadReturnYearTimes==""){
		alert('第5条年度内最多允许提前还款次数必须输入！');
		document.forms[0].elements["aheadReturnParamDTO.mostAheadReturnYearTimes"].focus();
		return false;
	}else{
		if(!isNumber(mostAheadReturnYearTimes)){
			alert('请输入正确的数字格式！');
			document.forms[0].elements["aheadReturnParamDTO.mostAheadReturnYearTimes"].value="";
			document.forms[0].elements["aheadReturnParamDTO.mostAheadReturnYearTimes"].focus();
			return false;
		}
	}
	var mostAheadReturnTimes=document.forms[0].elements["aheadReturnParamDTO.mostAheadReturnTimes"].value.trim();
	if(mostAheadReturnTimes==""){
		alert('第6条贷款期限内最多允许提前还款次数必须输入！');
		document.forms[0].elements["aheadReturnParamDTO.mostAheadReturnTimes"].focus();
		return false;
	}else{
		if(!isNumber(mostAheadReturnTimes)){
			alert('请输入正确的数字格式！');
			document.forms[0].elements["aheadReturnParamDTO.mostAheadReturnTimes"].value="";
			document.forms[0].elements["aheadReturnParamDTO.mostAheadReturnTimes"].focus();
			return false;
		}
	}
	var isAccept=document.forms[0].elements["isAccept"].value.trim();
	var chargeMode=document.forms[0].elements["chargeMode"].value.trim();
	var aheadReturnMoneyPercent=document.forms[0].elements["aheadReturnParamDTO.aheadReturnMoneyPercent"].value.trim();
	var money=document.forms[0].elements["aheadReturnParamDTO.money"].value.trim();
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
			if(aheadReturnMoneyPercent==0||aheadReturnMoneyPercent==""){
				alert('第7条提前还款额必须输入！');
				document.forms[0].elements["aheadReturnParamDTO.aheadReturnMoneyPercent"].focus();
				return false;
			}else{
				if(!checkMoney(aheadReturnMoneyPercent)){
					document.forms[0].elements["aheadReturnParamDTO.aheadReturnMoneyPercent"].focus();
					return false;
				}
			}
		}
		if(chargeMode==2){
			if(money==0||money==""){
				alert('第7条按额的金额必须输入！');
				document.forms[0].elements["aheadReturnParamDTO.money"].focus();
				return false;
			}else{
				if(!checkMoney(money)){
					document.forms[0].elements["aheadReturnParamDTO.money"].focus();
					return false;
				}
			}
		}
	}
	var maleAge=document.forms[0].elements["aheadReturnParamDTO.maleAge"].value.trim();
	var femaleAge=document.forms[0].elements["aheadReturnParamDTO.femaleAge"].value.trim();
	var timeMax_1=document.forms[0].elements["aheadReturnParamDTO.timeMax_1"].value.trim();
	var timeMax_2=document.forms[0].elements["aheadReturnParamDTO.timeMax_2"].value.trim();
	var salaryRate=document.forms[0].elements["aheadReturnParamDTO.salaryRate"].value.trim();
	if(maleAge==""){
		alert('第8条贷款人实际年龄加贷款期限不超过的男性年龄必须输入！');
		document.forms[0].elements["aheadReturnParamDTO.maleAge"].focus();
		return false;
	}else{
		if(!isNumber(maleAge)){
			alert('请输入正确的数字格式！');
			document.forms[0].elements["aheadReturnParamDTO.maleAge"].value="";
			document.forms[0].elements["aheadReturnParamDTO.maleAge"].focus();
			return false;
		}
	}
	if(femaleAge==""){
		alert('第8条贷款人实际年龄加贷款期限不超过的女性年龄必须输入！');
		document.forms[0].elements["aheadReturnParamDTO.femaleAge"].focus();
		return false;
	}else{
		if(!isNumber(femaleAge)){
			alert('请输入正确的数字格式！');
			document.forms[0].elements["aheadReturnParamDTO.femaleAge"].value="";
			document.forms[0].elements["aheadReturnParamDTO.femaleAge"].focus();
			return false;
		}
	}
	if(timeMax_1==""){
		alert('第9条商品房贷款最高年限必须输入！');
		document.forms[0].elements["aheadReturnParamDTO.timeMax_1"].focus();
		return false;
	}else{
		if(!isNumber(timeMax_1)){
			alert('请输入正确的数字格式！');
			document.forms[0].elements["aheadReturnParamDTO.timeMax_1"].value="";
			document.forms[0].elements["aheadReturnParamDTO.timeMax_1"].focus();
			return false;
		}
	}
	if(timeMax_2==""){
		alert('第9条二手房贷款最高年限必须输入！');
		document.forms[0].elements["aheadReturnParamDTO.timeMax_2"].focus();
		return false;
	}else{
		if(!isNumber(timeMax_1)){
			alert('请输入正确的数字格式！');
			document.forms[0].elements["aheadReturnParamDTO.timeMax_2"].value="";
			document.forms[0].elements["aheadReturnParamDTO.timeMax_2"].focus();
			return false;
		}
	}
	if(salaryRate==""){
		alert('第10条二手房贷款最高年限必须输入！');
		document.forms[0].elements["aheadReturnParamDTO.salaryRate"].focus();
		return false;
	}else{
		if(!isNumber(timeMax_1)){
			alert('请输入正确的数字格式！');
			document.forms[0].elements["aheadReturnParamDTO.salaryRate"].value="";
			document.forms[0].elements["aheadReturnParamDTO.salaryRate"].focus();
			return false;
		}
	}
	document.forms[0].elements["aheadReturnParamDTO.leastReturnMoney"].value=document.forms[0].elements["aheadReturnParamDTO.leastReturnMoney"].value.trim();
	document.forms[0].elements["aheadReturnParamDTO.aheadReturnMoneyPercent"].value=document.forms[0].elements["aheadReturnParamDTO.aheadReturnMoneyPercent"].value.trim();
	document.forms[0].elements["aheadReturnParamDTO.money"].value=document.forms[0].elements["aheadReturnParamDTO.money"].value.trim();
	if(document.forms[0].elements["aheadReturnParamDTO.leastReturnMoney"].value==""){
		document.forms[0].elements["aheadReturnParamDTO.leastReturnMoney"].value='0';
	}
	if(document.forms[0].elements["aheadReturnParamDTO.aheadReturnMoneyPercent"].value==""){
		document.forms[0].elements["aheadReturnParamDTO.aheadReturnMoneyPercent"].value='0';
	}
	if(document.forms[0].elements["aheadReturnParamDTO.money"].value==""){
		document.forms[0].elements["aheadReturnParamDTO.money"].value='0';
	}
	return true;
}
 </script> 
  <body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="loads();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path%>/img/table_bg_line.gif">&nbsp;</td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>数据准备</a><font color="00B5DB">&gt;</font><a href="#" class=a1>参数维护</a></td>
                <td width="29" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
    <td class=td3> 
    <html:form action="/aheadReturnParamSaveAC.do">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="144"><b class="font14">提前还款参数设置</b></td>
                <td width="550" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
      	<input type="hidden" name="isAccept" value=""/>
      	<input type="hidden" name="chargeMode" value=""/>
        <tr id="tr1"> 
          <td class="td1">&nbsp;</td>
          <td class="td1"> 
            <html:select property="aheadReturnParamDTO.loanBankId" styleClass="input4" name="aheadReturnParamAF" style="width=30%;" onchange="verdictLoanBankId();" onkeydown="enterNextFocus1();">
<%--          		<option value=""></option>--%>
          		<html:options  collection="loanbankList1" property="value" labelProperty="label"/>
			</html:select>
          </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">1</td>
          <td class="td1">提前还款后　　 
            <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.aheadReturnAfter" value="1" onkeydown="enterNextFocus1();"/>
            保持原来月还款额　　 
            <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.aheadReturnAfter" value="2" onkeydown="enterNextFocus1();"/>
            保持剩余期限　　 
            <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.aheadReturnAfter" value="3" onkeydown="enterNextFocus1();"/>
            允许改变剩余期限</td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">2</td>
          <td class="td1">还款时间小于 
            <html:text name="aheadReturnParamAF"  property="aheadReturnParamDTO.partReturnMonthLT"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            月，部分提前还款。 
            <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.isPartAheadReturn" value="1" onkeydown="enterNextFocus1();"/>
            不允许 <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.isPartAheadReturn" value="2" onclick="clear1()" onkeydown="enterNextFocus1();"/>允许 
            </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">3</td>
          <td class="td1"> 还款时间小于 
            <html:text name="aheadReturnParamAF"  property="aheadReturnParamDTO.allReturnMonthLT"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            月，一次性结清还款。 
            <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.isAllReturn" value="1" onkeydown="enterNextFocus1();"/>
            不允许 <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.isAllReturn" value="2" onclick="clear2()" onkeydown="enterNextFocus1();"/>允许 
            
            </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">4</td>
          <td class="td1"> 提前还款最低还款金额为 
            <html:text name="aheadReturnParamAF"  property="aheadReturnParamDTO.leastReturnMoney"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            元</td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">5</td>
          <td class="td1"> 年度内最多允许提前还款 
            <html:text name="aheadReturnParamAF"  property="aheadReturnParamDTO.mostAheadReturnYearTimes"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            次 </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">6</td>
          <td class="td1"> 贷款期限内最多允许提前还款 
            <html:text name="aheadReturnParamAF"  property="aheadReturnParamDTO.mostAheadReturnTimes"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            次</td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">7</td>
          <td class="td1">提前还款收取手续费 
            <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.isAccept" value="4" onclick="appValue(this)" onkeydown="enterNextFocus1();"/>
            是 
            <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.isAccept" value="3" onclick="appValue(this)" onkeydown="enterNextFocus1();"/>
            否， 
            <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.chargeMode" value="1" onclick="appValue1(this)" onkeydown="enterNextFocus1();"/>
            按提前还款额 
            <html:text name="aheadReturnParamAF"  property="aheadReturnParamDTO.aheadReturnMoneyPercent"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            %收费 
            <html:radio name="aheadReturnParamAF" property="aheadReturnParamDTO.chargeMode" value="2" onclick="appValue1(this)" onkeydown="enterNextFocus1();"/>
            按额 
            <html:text name="aheadReturnParamAF"  property="aheadReturnParamDTO.money"  styleClass="input3" style="width=7%;" onkeydown="enterNextFocus1();"/>
            收费</td>
        </tr>
        <tr id="tr1">
							<td class="td1">
								8
							</td>
							<td class="td1">
								贷款人实际年龄加贷款期限不超过
								<html:text name="aheadReturnParamAF"
									property="aheadReturnParamDTO.maleAge" styleClass="input3"
									style="width=7%;" onkeydown="enterNextFocus1();" />
								岁(男)或
								<html:text name="aheadReturnParamAF"
									property="aheadReturnParamDTO.femaleAge" styleClass="input3"
									style="width=7%;" onkeydown="enterNextFocus1();" />
								岁(女)
							</td>
							
						</tr>
						<tr>
							<td class="td1">
								9
							</td>
							<td class="td1">
								贷款最高年限：商品房
								<html:text name="aheadReturnParamAF"
									property="aheadReturnParamDTO.timeMax_1"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								年,二手房
								<html:text name="aheadReturnParamAF"
									property="aheadReturnParamDTO.timeMax_2"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								年
							
						</tr>
						<tr>
							<td class="td1">
								10
							</td>
							<td class="td1">
								借款人月收入还款比例
								<html:text name="aheadReturnParamAF"
									property="aheadReturnParamDTO.salaryRate"
									styleClass="input3" style="width=7%;"
									onkeydown="enterNextFocus1();" />
								%
							</td>
							
						</tr>
						<tr>
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
    </table>
</body>
</html:html>
