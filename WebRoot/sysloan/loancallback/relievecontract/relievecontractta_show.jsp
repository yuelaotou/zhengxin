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
    <title>解除抵押质押</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>
	
  </head>
 <script language="javascript" >
function reportErrors(message) {
	if(message!=null){
		alert(message);
		document.forms[0].elements["relieveContractTaDTO.loadKouAcc"].value="";
		document.forms[0].elements["relieveContractTaDTO.contractId"].value="";
		document.forms[0].elements["relieveContractTaDTO.borrowerName"].value="";
		document.forms[0].elements["relieveContractTaDTO.temp_cardKind"].value="";
		document.forms[0].elements["relieveContractTaDTO.cardNum"].value="";
		document.forms[0].elements["relieveContractTaDTO.overplusLoanMoney"].value="";
		document.forms[0].elements["relieveContractTaDTO.noBackMoney"].value="";
		document.forms[0].elements["relieveContractTaDTO.ovaerLoanRepay"].value="";
		document.forms[0].elements["relieveContractTaDTO.bailBalance"].value="";
		document.forms[0].elements["relieveContractTaDTO.temp_loanMode"].value="";
	}
	else{
		<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
		document.forms[0].elements["relieveContractTaDTO.loadKouAcc"].value="";
		document.forms[0].elements["relieveContractTaDTO.contractId"].value="";
		document.forms[0].elements["relieveContractTaDTO.borrowerName"].value="";
		document.forms[0].elements["relieveContractTaDTO.temp_cardKind"].value="";
		document.forms[0].elements["relieveContractTaDTO.cardNum"].value="";
		document.forms[0].elements["relieveContractTaDTO.overplusLoanMoney"].value="";
		document.forms[0].elements["relieveContractTaDTO.noBackMoney"].value="";
		document.forms[0].elements["relieveContractTaDTO.ovaerLoanRepay"].value="";
		document.forms[0].elements["relieveContractTaDTO.bailBalance"].value="";
		document.forms[0].elements["relieveContractTaDTO.temp_loanMode"].value="";
		</logic:messagesPresent>
	}
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
	var queryString = "relieveContractTaFindAAC.do?";
    var loadKouAcc = document.forms[0].elements["relieveContractTaDTO.loadKouAcc"].value.trim();
    if(loadKouAcc==""){
	alert("请你输入扣款账号");
	}
	else{
		queryString = queryString + "loadKouAcc="+loadKouAcc; 
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
function displays(contractId,borrowerName,temp_cardKind,cardNum,overplusLoanMoney,noBackMoney,ovaerLoanRepay,bailBalance,temp_loanMode) {
	document.forms[0].elements["relieveContractTaDTO.contractId"].value=contractId;
	document.forms[0].elements["relieveContractTaDTO.borrowerName"].value=borrowerName;
	document.forms[0].elements["relieveContractTaDTO.temp_cardKind"].value=temp_cardKind;
	document.forms[0].elements["relieveContractTaDTO.cardNum"].value=cardNum;
	document.forms[0].elements["relieveContractTaDTO.overplusLoanMoney"].value=overplusLoanMoney;
	document.forms[0].elements["relieveContractTaDTO.noBackMoney"].value=noBackMoney;
	document.forms[0].elements["relieveContractTaDTO.ovaerLoanRepay"].value=ovaerLoanRepay;
	document.forms[0].elements["relieveContractTaDTO.bailBalance"].value=bailBalance;
	document.forms[0].elements["relieveContractTaDTO.temp_loanMode"].value=temp_loanMode;
	document.all.item(s1).disabled="";
}
function onsure(){
  var x=true;
  var overplusLoanMoney=document.forms[0].elements["relieveContractTaDTO.overplusLoanMoney"].value.trim();
  var noBackMoney=document.forms[0].elements["relieveContractTaDTO.noBackMoney"].value.trim();
  var ovaerLoanRepay=document.forms[0].elements["relieveContractTaDTO.ovaerLoanRepay"].value.trim();
  
  if(overplusLoanMoney!="0"){
  	x=confirm("剩余本金不为0，是否继续！");
  }
  if(noBackMoney!="0"){
  	x=confirm("核销未收回金额不为0，是否继续！");
  }
  if(ovaerLoanRepay!="0"){
  	x=confirm("挂账金额不为0，是否继续！");
  }
  if(x){
    return true;
  }else
  {
    return false;
  }
  return true;
}
function toLoankouaccpop(){
	gotoLoankouaccpop('','<%=path%>','0');
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
          <td width="235" background="<%=path%>/img/table_bg_line.gif"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="112" height="37" background="<%=path%>/img/buttonbl.gif" align="center" valign="top"  style="PADDING-top: 7px">办理抵押质押解除</td>
                <td width="112" height="37" background="<%=path%>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="relieveContractTbForwardAC.do" class=a2>抵押质押解除维护</a></td>
              </tr>
            </table>
          </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">回收贷款&gt;抵押质押解除</font></td>
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
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="185"><b class="font14">抵押（质押）解除信息</b></td>
                <td width="740" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form action="/relieveContractTaSaveAC.do"  style="margin: 0">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  >扣款账号</td>
            <td width="22%"   > 
              <html:text name="relieveContractTaAF"  property="relieveContractTaDTO.loadKouAcc"  styleClass="input3" onkeydown="goEnter();" ondblclick="executeAjax()"/> 
            </td>
            <td width="11%"   > 
              <input type="button" class="buttona" value="..." onClick="toLoankouaccpop()" >
            </td>
            <td class="td1" width="17%" >合同编号</td>
            <td width="33%" > 
              <html:text name="relieveContractTaAF"  property="relieveContractTaDTO.contractId"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class="td1"  >借款人姓名</td>
            <td colspan="2"  > 
              <html:text name="relieveContractTaAF"  property="relieveContractTaDTO.borrowerName"  styleClass="input3" readonly="true"/>
            </td>
            <td  width="17%" class="td1" >证件类型</td>
            <td  width="33%" > 
              <html:text name="relieveContractTaAF"  property="relieveContractTaDTO.temp_cardKind"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class="td1"  >证件号码</td>
            <td colspan="2"  > 
              <html:text name="relieveContractTaAF"  property="relieveContractTaDTO.cardNum"  styleClass="input3" readonly="true"/>
            </td>
            <td  width="17%" class="td1" ><font face="宋体">剩余本金</font></td>
            <td  width="33%" >
              <html:text name="relieveContractTaAF"  property="relieveContractTaDTO.overplusLoanMoney"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class="td1"  ><font face="宋体">核销未收回金额</font></td>
            <td colspan="2"  > 
              <html:text name="relieveContractTaAF"  property="relieveContractTaDTO.noBackMoney"  styleClass="input3" readonly="true"/>
            </td>
            <td  width="17%" class="td1" >挂账余额</td>
            <td  width="33%" > 
              <html:text name="relieveContractTaAF"  property="relieveContractTaDTO.ovaerLoanRepay"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class="td1"  >保证金余额</td>
            <td colspan="2"  > 
              <html:text name="relieveContractTaAF"  property="relieveContractTaDTO.bailBalance"  styleClass="input3" readonly="true"/>
            </td>
            <td  width="17%" class="td1" >还款方式</td>
            <td  width="33%" > 
              <html:text name="relieveContractTaAF"  property="relieveContractTaDTO.temp_loanMode"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
        </table>  
     <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70">
                  <html:submit property="method" styleClass="buttona" onclick="return onsure();"><bean:message key="button.sure"/></html:submit>
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
