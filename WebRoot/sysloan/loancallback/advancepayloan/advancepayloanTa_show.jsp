<%@ page language="java"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.advancepayloan.action.AdvancepayloanTaShowAC" %>
<%@ include file="/checkUrl.jsp"%>     
<%
   String path = request.getContextPath();
   Object pagination= request.getSession(false).getAttribute(AdvancepayloanTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
 %>
<html:html>
<head>
<title>个贷管理</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/picture.js"></script>
</head>

<script type="text/javascript">
var s1="";
function onload(){

	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="确定"){
				s1=i;
			}
		}
	}
	document.all.item(s1).disabled="true";
}
function toLoankouaccpop(){
	gotoLoankouaccpop('11','<%=path%>','0');
}
function executeAjax() {
	var queryString = "advancepayloanTaFindAAC.do?";
    var loadKouAcc = document.all.loanKouAcc.value;
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
function displays(loanKouAcc,contractId,borrowerName,cardKind,cardNum,corpusInterest,overplusLoanMoney,creditType,pre_term) {
	if(corpusInterest=="0"){
		document.all.loanKouAcc.value="";
	
		alert("请先年结！");
	}else{
		document.all.loanKouAcc.value=loanKouAcc;
		document.all.contractId.value=contractId;
		document.all.borrowerName.value=borrowerName;
		document.all.cardKind.value=cardKind;
		document.all.cardNum.value=cardNum;
		document.all.corpusInterest.value=corpusInterest;
		document.all.overplusLoanMoney.value=overplusLoanMoney;
		document.all.creditType.value=creditType;
		document.all.pre_term.value=pre_term;
		document.all.item(s1).disabled="";
	}
	
}
function onsure(){
    var loadKouAcc = document.all.loanKouAcc.value;
    if(loadKouAcc==""){
	alert("请输入扣款账号");
	return false;
	}
	
	var new_term = document.all.new_term.value;
    if(new_term == ""){
    alert("请输入新剩余期限");
	return false;
    }
	var type = document.all.type.value;
	if(type == ""){
    alert("请选择类型");
	return false;
    }
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="onload();">
<html:form action="/advancepayloanTaMainTainAC.do" method="post" style="margin: 0">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=request.getContextPath()%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td width="235" background="<%=request.getContextPath()%>/img/table_bg_line.gif"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="112" height="37" background="<%=request.getContextPath()%>/img/buttonbl.gif" align="center" valign="top"  style="PADDING-top: 7px">业务办理</td>
                  <td width="112" height="37" background="<%=request.getContextPath()%>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="advancepayloanTbShowAC.do" class=a2>业务维护</a></td>
                </tr>
              </table>
            </td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=request.getContextPath()%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="165" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">申请贷款&gt;特殊申请</font></td>
                  <td width="98" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=request.getContextPath()%>/img/table_right.gif" width="9" height="37"></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="246"><b class="font14">借款人信息</b></td>
                <td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center" width="658">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table  width="95%" id="table9" cellspacing=1  cellpadding=3 align="center" >
        <tr> 
          <td width="17%"class=td1>扣款账号<br>
          </td>
          <td class="td4" width="20%"> 
            <html:text name="advancepayloanTaAF" property="loanKouAcc" onkeydown="gotoEnter();" ondblclick="return executeAjax();"  styleClass="input3"/>
          </td>
          <td class="td4"> 
            <input type="button" class="buttona" value="..." onClick="toLoankouaccpop()" >
          </td>
          <td class="td1" width="17%">合同编号 </td>
          <td class="td4" width="33%"> 
            <html:text name="advancepayloanTaAF" property="contractId" onkeydown="enterNextFocus1();" styleClass="input3" readonly="true"/>
          </td>
        </tr>
        <tr> 
          <td width="17%"class=td1 height="31"> 借款人姓名</td>
          <td class="td4" colspan="2" height="31"> <font color="#FF0000"> 
            <html:text name="advancepayloanTaAF" property="borrowerName" onkeydown="enterNextFocus1();" styleClass="input3" readonly="true"/>
            </font> </td>
          <td width="17%"class=td1 height="31">证件类型</td>
          <td  class="td4" width="33%" height="31"> <font color="#FF0000"> 
             <html:text name="advancepayloanTaAF" property="cardKind" onkeydown="enterNextFocus1();" styleClass="input3" readonly="true"/>
            </font></td>
        </tr>
        <tr> 
          <td class=td1 width="17%">证件号码 </td>
          <td class="td4" colspan="2"> <font color="#FF0000"> 
            <html:text name="advancepayloanTaAF" property="cardNum" onkeydown="enterNextFocus1();" styleClass="input3" readonly="true"/>
            </font></td>
          <td class=td1 width="17%"> 月还本息<br>
          </td>
          <td class="td4" width="33%"> <font color="#FF0000"> 
            <html:text name="advancepayloanTaAF" property="corpusInterest" onkeydown="enterNextFocus1();" styleClass="input3" readonly="true"/>
            </font></td>
        </tr>
        <tr> 
          <td class=td1 width="17%"> 剩余本金</td>
          <td class="td4" colspan="2"> 
            <html:text name="advancepayloanTaAF" property="overplusLoanMoney" onkeydown="enterNextFocus1();" styleClass="input3" readonly="true"/>
          </td>
          <td class=td1 width="17%"> 还款方式</td>
          <td  class="td4" width="33%"> 
            <html:text name="advancepayloanTaAF" property="creditType" onkeydown="enterNextFocus1();" styleClass="input3" readonly="true"/>
          </td>
        </tr>
        <tr> 
          <td class=td1 width="17%" height="26">原剩余期限(月) </td>
          <td class="td4" colspan="2" height="26"> 
            <html:text name="advancepayloanTaAF" property="pre_term" onkeydown="enterNextFocus1();" styleClass="input3" readonly="true"/>
          </td>
          <td class=td1 width="17%" height="26">新剩余期限(月)<font color="#ff0000">*</font></td>
          <td  class="td4" width="33%" height="26"> 
            <html:text name="advancepayloanTaAF" property="new_term" onkeydown="enterNextFocus1();" styleClass="input3"/>
          </td>
        </tr>
        <tr>
          <td class=td1 width="17%" height="26">类型<font color="#ff0000">*</font></td>
          <td class="td4" colspan="2" height="26">
            <select name="type">
              <option value=""></option>
              <option value="1">延长</option>
              <option value="2">缩短</option>
            </select>
          </td>
          <td class=td1 width="17%" height="26">&nbsp;</td>
          <td  class="td4" width="33%" height="26">&nbsp;</td>
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
      <p>&nbsp;</p>
    </table>
    </html:form>
    </body>
</html:html>

