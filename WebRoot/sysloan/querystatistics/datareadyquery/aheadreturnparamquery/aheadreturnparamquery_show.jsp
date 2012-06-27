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
    <title>提前还款参数设置查询</title>
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
	else{
		<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
		</logic:messagesPresent>
	}
}
var s1="";
function onload(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="button"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		}
	}
	if(document.forms[0].elements["aheadReturnParamQueryDTO.row[0]"].checked==true||document.forms[0].elements["aheadReturnParamQueryDTO.row[1]"].checked==true||document.forms[0].elements["aheadReturnParamQueryDTO.row[2]"].checked==true
	||document.forms[0].elements["aheadReturnParamQueryDTO.row[3]"].checked==true||document.forms[0].elements["aheadReturnParamQueryDTO.row[4]"].checked==true||document.forms[0].elements["aheadReturnParamQueryDTO.row[5]"].checked==true
	||document.forms[0].elements["aheadReturnParamQueryDTO.row[6]"].checked==true){
		document.all.item(s1).disabled="";
	}else{
		document.all.item(s1).disabled="true";
	}
}
function verdictLoanBankId(){
	var loanBankId=document.forms[0].elements["aheadReturnParamQueryDTO.loanBankId"].value.trim();
	document.URL=('aheadReturnParamQueryShowAC.do?loanBankId='+loanBankId);
}
function toprint(){
	document.URL=('querystatistics/datareadyquery/aheadreturnparamquery/aheadreturnparamquery_print.jsp');
}
</script>  
  <body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="reportErrors();onload();">
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
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">数据准备查询信息&gt;贷款参数设置查询</font></td>
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
    <html:form action="/aheadReturnParamQueryShowAC.do">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="150"><b class="font14">提 前 还 款 参 数</b></td>
                <td width="706" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr id="tr1"> 
          <td class="td1">&nbsp;</td>
          <td class="td1">&nbsp;</td>
          <td class="td1"> 
            <html:select property="aheadReturnParamQueryDTO.loanBankId" styleClass="input4" name="aheadReturnParamQueryAF" style="width=30%;" onchange="verdictLoanBankId();">
          		<option value=""></option>
          		<html:options  collection="loanbankList1" property="value" labelProperty="label"/>
			</html:select>
          </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">1</td>
          <td class="td1"> 
            <html:multibox name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.row[0]" value="1" onclick="return false"></html:multibox>
          </td>
          <td class="td1">提前还款后　　 
            <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.aheadReturnAfter" value="1" disabled="true"/>
            保持原来月还款额　　 
            <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.aheadReturnAfter" value="2" disabled="true"/>
            保持剩余期限　　 
            <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.aheadReturnAfter" value="3" disabled="true"/>
            允许改变剩余期限</td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">2</td>
          <td class="td1"> 
            <html:multibox name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.row[1]" value="2" onclick="return false"></html:multibox>
          </td>
          <td class="td1">还款时间小于 
            <html:text name="aheadReturnParamQueryAF"  property="aheadReturnParamQueryDTO.partReturnMonthLT"  styleClass="input3" style="width=7%;" readonly="true"/>
            月，部分提前还款。 
            <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.isPartAheadReturn" value="1" disabled="true"/>
            不允许 <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.isPartAheadReturn" value="2" disabled="true"/>允许 
            </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">3</td>
          <td class="td1"> 
            <html:multibox name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.row[2]" value="3" onclick="return false"></html:multibox>
          </td>
          <td class="td1"> 还款时间小于 
            <html:text name="aheadReturnParamQueryAF"  property="aheadReturnParamQueryDTO.allReturnMonthLT"  styleClass="input3" style="width=7%;" readonly="true"/>
            月，一次性结清还款。 
            <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.isAllReturn" value="1" disabled="true"/>
            不允许 <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.isAllReturn" value="2" disabled="true"/>允许 
            
            </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">4</td>
          <td class="td1"> 
            <html:multibox name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.row[3]" value="4" onclick="return false"></html:multibox>
          </td>
          <td class="td1"> 提前还款最低还款金额为 
            <html:text name="aheadReturnParamQueryAF"  property="aheadReturnParamQueryDTO.leastReturnMoney"  styleClass="input3" style="width=7%;" readonly="true"/>
            元</td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">5</td>
          <td class="td1"> 
            <html:multibox name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.row[4]" value="5" onclick="return false"></html:multibox>
          </td>
          <td class="td1"> 年度内最多允许提前还款 
            <html:text name="aheadReturnParamQueryAF"  property="aheadReturnParamQueryDTO.mostAheadReturnYearTimes"  styleClass="input3" style="width=7%;" readonly="true"/>
            次 </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">6</td>
          <td class="td1"> 
            <html:multibox name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.row[5]" value="6" onclick="return false"></html:multibox>
          </td>
          <td class="td1"> 贷款期限内最多允许提前还款 
            <html:text name="aheadReturnParamQueryAF"  property="aheadReturnParamQueryDTO.mostAheadReturnTimes"  styleClass="input3" style="width=7%;" readonly="true"/>
            次</td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">7</td>
          <td class="td1"> 
            <html:multibox name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.row[6]" value="7" onclick="return false"></html:multibox>
          </td>
          <td class="td1">提前还款收取手续费 
            <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.isAccept" value="4" disabled="true"/>
            是 
            <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.isAccept" value="3" disabled="true"/>
            否， 
            <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.chargeMode" value="1" disabled="true"/>
            按提前还款额 
            <html:text name="aheadReturnParamQueryAF"  property="aheadReturnParamQueryDTO.aheadReturnMoneyPercent"  styleClass="input3" style="width=7%;" readonly="true"/>
            %收费 
            <html:radio name="aheadReturnParamQueryAF" property="aheadReturnParamQueryDTO.chargeMode" value="2" disabled="true"/>
            按额 
            <html:text name="aheadReturnParamQueryAF"  property="aheadReturnParamQueryDTO.money"  styleClass="input3" style="width=7%;" readonly="true"/>
            收费</td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
              	<td width="70"> 
                  <input type="button" name="Submit" value="打印" class="buttona" onclick="toprint();">
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      </html:form>
    </table></body>
</html:html>
    