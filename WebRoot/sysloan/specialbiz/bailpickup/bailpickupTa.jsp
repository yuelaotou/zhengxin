<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.specialbiz.bailpickup.action.BailpickupTaShowAC" %>
<%@ include file="/checkUrl.jsp"%>     
<%
   String path = request.getContextPath();
   Object pagination= request.getSession(false).getAttribute(BailpickupTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
 %>
<html:html>
<head>
<title>特殊业务处理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>
<script type="text/javascript">
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function gotoloanKouAccPop(){
 gotoLoankouaccpop('','<%=path%>','0');
}
function executeAjax() {
      var loankouacc = document.forms[0].elements["loanKouAcc"].value.trim();
      location.href='bailpickupTaShowAC.do?loanKouAcc='+loankouacc;
}
function isprint(){
  var v0 = document.forms[0].elements["type"].value.trim();
  var v1 = document.forms[0].elements["overplusLoanMoney"].value.trim();
  var v2 = document.forms[0].elements["noBackMoney"].value.trim();
  var v3 = document.forms[0].elements["ovaerLoanRepay"].value.trim();
  var v4 = document.forms[0].elements["bailBalance"].value.trim();
  if(v0=='0'){
    alert("该贷款账号下存在未记账的保证金业务,不可以提取!");
    document.all.sure.disabled="true";// 确定按钮禁用
    document.forms[0].elements["loanKouAcc"].value="";
	document.forms[0].elements["contractId"].value="";
	document.forms[0].elements["borrowerName"].value="";
	document.forms[0].elements["cardNum"].value="";
	document.forms[0].elements["bailBalance"].value="";
	document.forms[0].elements["pickUpInterest"].value="";
	document.forms[0].elements["pickSumMoney"].value="";
	document.forms[0].elements["overplusLoanMoney"].value="";
	document.forms[0].elements["noBackMoney"].value="";
	document.forms[0].elements["ovaerLoanRepay"].value="";
   return false;
   
  }
  if(v1!='0' && v2!='0' && v3!='0'){
   if(!confirm("该贷款账号下的 贷款余额、呆账未收回金额、挂账余额不为0! 是否继续提取?")){
    document.forms[0].elements["loanKouAcc"].value="";
	document.forms[0].elements["contractId"].value="";
	document.forms[0].elements["borrowerName"].value="";
	document.forms[0].elements["cardNum"].value="";
	document.forms[0].elements["bailBalance"].value="";
	document.forms[0].elements["pickUpInterest"].value="";
	document.forms[0].elements["pickSumMoney"].value="";
	document.forms[0].elements["overplusLoanMoney"].value="";
	document.forms[0].elements["noBackMoney"].value="";
	document.forms[0].elements["ovaerLoanRepay"].value="";
    return false;
   }
  }
  if(v1!='0' && v2!='0' && v3=='0'){
   if(!confirm("该贷款账号下的 贷款余额、呆账未收回金额不为0! 是否继续提取?")){
    document.forms[0].elements["loanKouAcc"].value="";
	document.forms[0].elements["contractId"].value="";
	document.forms[0].elements["borrowerName"].value="";
	document.forms[0].elements["cardNum"].value="";
	document.forms[0].elements["bailBalance"].value="";
	document.forms[0].elements["pickUpInterest"].value="";
	document.forms[0].elements["pickSumMoney"].value="";
	document.forms[0].elements["overplusLoanMoney"].value="";
	document.forms[0].elements["noBackMoney"].value="";
	document.forms[0].elements["ovaerLoanRepay"].value="";
    return false;
   }
  }
  if(v1!='0' && v3!='0' && v2=='0'){
   if(!confirm("该贷款账号下的 贷款余额、挂账余额不为0! 是否继续提取?")){
    document.forms[0].elements["loanKouAcc"].value="";
	document.forms[0].elements["contractId"].value="";
	document.forms[0].elements["borrowerName"].value="";
	document.forms[0].elements["cardNum"].value="";
	document.forms[0].elements["bailBalance"].value="";
	document.forms[0].elements["pickUpInterest"].value="";
	document.forms[0].elements["pickSumMoney"].value="";
	document.forms[0].elements["overplusLoanMoney"].value="";
	document.forms[0].elements["noBackMoney"].value="";
	document.forms[0].elements["ovaerLoanRepay"].value="";
    return false;
   }
  }
  if(v2!='0' && v3!='0' && v1=='0'){
   if(!confirm("该贷款账号下的 呆账未收回金额、挂账余额不为0! 是否继续提取?")){
    document.forms[0].elements["loanKouAcc"].value="";
	document.forms[0].elements["contractId"].value="";
	document.forms[0].elements["borrowerName"].value="";
	document.forms[0].elements["cardNum"].value="";
	document.forms[0].elements["bailBalance"].value="";
	document.forms[0].elements["pickUpInterest"].value="";
	document.forms[0].elements["pickSumMoney"].value="";
	document.forms[0].elements["overplusLoanMoney"].value="";
	document.forms[0].elements["noBackMoney"].value="";
	document.forms[0].elements["ovaerLoanRepay"].value="";
    return false;
   }
  }
  if(v1!='0' && v2=='0' && v3=='0'){
   if(!confirm("该贷款账号下的 贷款余额不为0! 是否继续提取?")){
    document.forms[0].elements["loanKouAcc"].value="";
	document.forms[0].elements["contractId"].value="";
	document.forms[0].elements["borrowerName"].value="";
	document.forms[0].elements["cardNum"].value="";
	document.forms[0].elements["bailBalance"].value="";
	document.forms[0].elements["pickUpInterest"].value="";
	document.forms[0].elements["pickSumMoney"].value="";
	document.forms[0].elements["overplusLoanMoney"].value="";
	document.forms[0].elements["noBackMoney"].value="";
	document.forms[0].elements["ovaerLoanRepay"].value="";
    return false;
   }
  }
  if(v2!='0' && v1=='0' && v3=='0'){
   if(!confirm("该贷款账号下的 呆账未收回金额不为0! 是否继续提取?")){
    document.forms[0].elements["loanKouAcc"].value="";
	document.forms[0].elements["contractId"].value="";
	document.forms[0].elements["borrowerName"].value="";
	document.forms[0].elements["cardNum"].value="";
	document.forms[0].elements["bailBalance"].value="";
	document.forms[0].elements["pickUpInterest"].value="";
	document.forms[0].elements["pickSumMoney"].value="";
	document.forms[0].elements["overplusLoanMoney"].value="";
	document.forms[0].elements["noBackMoney"].value="";
	document.forms[0].elements["ovaerLoanRepay"].value="";
    return false;
   }
  }
  if(v3!='0' && v1=='0' && v2=='0'){
   if(!confirm("该贷款账号下的 挂账余额不为0! 是否继续提取?")){
    document.forms[0].elements["loanKouAcc"].value="";
	document.forms[0].elements["contractId"].value="";
	document.forms[0].elements["borrowerName"].value="";
	document.forms[0].elements["cardNum"].value="";
	document.forms[0].elements["bailBalance"].value="";
	document.forms[0].elements["pickUpInterest"].value="";
	document.forms[0].elements["pickSumMoney"].value="";
	document.forms[0].elements["overplusLoanMoney"].value="";
	document.forms[0].elements["noBackMoney"].value="";
	document.forms[0].elements["ovaerLoanRepay"].value="";
    return false;
   }
  }
  if(v4=='0'){
    alert("该贷款账号下的保证金已经提取")
    return false;
   }
 if(confirm("是否打印保证金提取凭证？")){
	document.all.report.value="print";
  }else {
  	document.all.report.value="noprint";
  }
}
function loads(){
    <logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	if(message == "该贷款账号下存在未记账的保证金业务,不可以提取!" || message == "该贷款账号下的保证金已经提取!" || message == "会计日期为6月30日，不能提取!"){
	alert(message);
	document.forms[0].elements["loanKouAcc"].value="";
	document.forms[0].elements["contractId"].value="";
	document.forms[0].elements["borrowerName"].value="";
	document.forms[0].elements["cardNum"].value="";
	document.forms[0].elements["bailBalance"].value="";
	document.forms[0].elements["pickUpInterest"].value="";
	document.forms[0].elements["pickSumMoney"].value="";
	document.forms[0].elements["overplusLoanMoney"].value="";
	document.forms[0].elements["noBackMoney"].value="";
	document.forms[0].elements["ovaerLoanRepay"].value="";
	location.href='bailpickupTaShowAC.do';
	}else{
	alert(message);
	}
	</logic:messagesPresent>
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="loads();">
<html:form action="/bailpickupTaNewAC.do">
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
                <td width="112" height="37" background="<%=request.getContextPath()%>/img/buttonbl.gif" align="center" valign="top"  style="PADDING-top: 7px">办理保证金提取</td>
                <td width="112" height="37" background="<%=request.getContextPath()%>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="bailpickupTbForwardURLAC.do" class=a2>保证金提取维护</a></td>
              </tr>
            </table>
          </td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=request.getContextPath()%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">特殊业务处理&gt;保证金提取</font></td>
                <td width="29" class=font14>&nbsp;</td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="162"><b class="font14">提取保证金信息</b></td>
                <td width="763" height="22" align="center" background="<%=request.getContextPath()%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  >贷款账号</td>
            <td width="22%"   > 
              <html:text name="theBailpickupTaDTO" property="loanKouAcc" onkeydown="gotoEnter();"  ondblclick="return executeAjax();" styleClass="input3"/>
            </td>
            <td width="11%"   > 
              <input type="button" class="buttona" value="..." onClick="gotoloanKouAccPop();" >
            </td>
            <td class="td1" width="17%" >合同编号</td>
            <td width="33%" > 
              <html:text name="theBailpickupTaDTO" property="contractId" onkeydown="enterNextFocus1();" readonly="true" styleClass="input3"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class="td1"  >借款人姓名</td>
            <td colspan="2"  > 
              <html:text name="theBailpickupTaDTO" property="borrowerName" onkeydown="enterNextFocus1();" readonly="true" styleClass="input3"/>
            </td>
            <td  width="17%" class="td1" >证件号码</td>
            <td  width="33%" > 
              <html:text name="theBailpickupTaDTO" property="cardNum" onkeydown="enterNextFocus1();" readonly="true" styleClass="input3"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class="td1"  >保证金余额</td>
            <td colspan="2"  > 
              <html:text name="theBailpickupTaDTO" property="bailBalance" onkeydown="enterNextFocus1();" readonly="true" styleClass="input3"/>
            </td>
            <td  width="17%" class="td1" >提取利息</td>
            <td  width="33%" > 
             <html:text name="theBailpickupTaDTO" property="pickUpInterest" onkeydown="enterNextFocus1();" readonly="true" styleClass="input3"/>
            </td>
            <html:hidden name="theBailpickupTaDTO" property="type"/>
          </tr>
		  <tr> 
            <td width="17%" class="td1"  >提取总金额</td>
            <td colspan="2"  > 
              <html:text name="theBailpickupTaDTO" property="pickSumMoney" onkeydown="enterNextFocus1();" readonly="true" styleClass="input3"/>
            </td>
            <td  width="17%" class="td1" >贷款余额</td>
            <td  width="33%" >
			  <html:text name="theBailpickupTaDTO" property="overplusLoanMoney" onkeydown="enterNextFocus1();" readonly="true" styleClass="input3"/>
			</td>
          </tr>
		  <tr> 
            <td width="17%" class="td1"  >呆账未收回金额</td>
            <td colspan="2"  > 
              <html:text name="theBailpickupTaDTO" property="noBackMoney" onkeydown="enterNextFocus1();" readonly="true" styleClass="input3"/>
            </td>
            <td  width="17%" class="td1" >挂账余额</td>
            <td  width="33%" >
            <html:text name="theBailpickupTaDTO" property="ovaerLoanRepay" onkeydown="enterNextFocus1();" readonly="true" styleClass="input3"/>
            <html:hidden name="bailpickupTaAF" property="report"/>
			</td>
          </tr>
        </table>  
     <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
              <logic:equal name="theBailpickupTaDTO" property="isDisabled" value="0">
              <td width="70"><html:submit property="sure" styleClass="buttona" disabled="true"><bean:message key="button.sure" /></html:submit></td>
              </logic:equal>
              <logic:equal name="theBailpickupTaDTO" property="isDisabled" value="1">
              <td width="70"><html:submit property="sure" styleClass="buttona" disabled=""  onclick="return isprint();"><bean:message key="button.sure" /></html:submit></td>
              </logic:equal>  
              </tr>
            </table>
           </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>
