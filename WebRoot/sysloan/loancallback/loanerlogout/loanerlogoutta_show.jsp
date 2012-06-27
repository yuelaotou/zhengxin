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
		<title>个贷管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	</head>
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script language="javascript"></script>

	<script language="javascript" type="text/javascript">
function seachContractId(){
	gotoLoankouaccpop('7,8,11,12','<%=path%>','0');
}
function executeAjax()
{
	if(document.all.loadKouAcc.value.trim()==""){
	   alert("录入扣款账号!");
	   return false;
	}	 
    var loadKouAcc=document.all.loadKouAcc.value;
   
    var queryString = "loanerlogoutTaFindAAC.do?";  
    queryString = queryString + "loanKouAcc="+loadKouAcc;  
     findInfo(queryString);
}
function sure()
{
	var x=confirm("确定是否注销!");
  if(x){
	return true;
  }else{
    return false;
  } 
}
function reportErrors() {
		var id=document.all.loadKouAcc.value.trim();
 		if(id==""){
 			document.all.loadKouAcc.focus();
 		}
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	document.all.loadKouAcc.value="";
		document.all.contractId.value="";
		document.all.borrowerName.value="";
		document.all.cardKind.value="";
		document.all.cardNum.value="";
		document.all.overplusLoanMoney.value="";
		document.all.noBackMoney.value="";
		document.all.ovaerLoanRepay.value="";
		document.all.bailBalance.value="";
		document.all.loanMode.value="";
	alert(message);
	</logic:messagesPresent>
	}
function displays(contractId) {
   showlist();
}
function backErrors(id){
		document.all.loadKouAcc.value="";
		document.all.contractId.value="";
		document.all.borrowerName.value="";
		document.all.cardKind.value="";
		document.all.cardNum.value="";
		document.all.overplusLoanMoney.value="";
		document.all.noBackMoney.value="";
		document.all.ovaerLoanRepay.value="";
		document.all.bailBalance.value="";
		document.all.loanMode.value="";
    alert(id);
    var s1="";
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="确定"){
				s1=i;
			}
		}
	}
	document.all.item(s1).disabled="true";   
}

function showlist() {
  document.Form1.submit();
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function clear() {	
<%
    		String save=(String)request.getAttribute("save");
	%>	
	var save="<%=save%>";
	if(save=="save"){	
	document.all.loadKouAcc.value="";
		document.all.contractId.value="";
		document.all.borrowerName.value="";
		document.all.cardKind.value="";
		document.all.cardNum.value="";
		document.all.overplusLoanMoney.value="";
		document.all.noBackMoney.value="";
		document.all.ovaerLoanRepay.value="";
		document.all.bailBalance.value="";
		document.all.loanMode.value="";
		alert("注销成功！");
	}
}
</script>
 <body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="reportErrors();clear();">
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
                <td width="112" height="37" background="<%=path%>/img/buttonbl.gif" align="center" valign="top"  style="PADDING-top: 7px">&#19994;&#21153;&#21150;&#29702;</td>
                <td width="112" height="37" background="<%=path%>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="loanerlogoutTbForwardAC.do" class=a2>&#19994;&#21153;&#32500;&#25252;</a></td>
              </tr>
            </table>
          </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="#00b5db">&#36151;&#27454;</font><font color="#00b5db">&#22238;&#25910;</font><font color="#00b5db">&gt;&#36151;&#27454;&#25143;&#27880;&#38144;</font></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="185"><b class="font14">&#36151;&#27454;&#25143;&#27880;&#38144;&#20449;&#24687;</b></td>
                <td width="740" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form action="/loanerlogoutTaSaveAC.do"  style="margin: 0">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  >扣款账号</td>
            <td width="22%"   > 
              <html:text name="loanerlogoutTaAF"  property="loadKouAcc"  styleClass="input3" onkeydown="gotoEnter();" ondblclick="executeAjax()" onkeydown="enterNextFocus1();" /> 
            </td>
            <td width="11%"   > 
              <input type="button" class="buttona" value="..." onClick="seachContractId()" >
            </td>
            <td class="td1" width="17%" >合同编号</td>
            <td width="33%" > 
              <html:text name="loanerlogoutTaAF"  property="contractId"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class="td1"  >借款人姓名</td>
            <td colspan="2"  > 
              <html:text name="loanerlogoutTaAF"  property="borrowerName"  styleClass="input3" readonly="true"/>
            </td>
            <td  width="17%" class="td1" >证件类型</td>
            <td  width="33%" > 
              <html:text name="loanerlogoutTaAF"  property="cardKind"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class="td1"  >证件号码</td>
            <td colspan="2"  > 
              <html:text name="loanerlogoutTaAF"  property="cardNum"  styleClass="input3" readonly="true"/>
            </td>
            <td  width="17%" class="td1" ><font face="宋体">剩余本金</font></td>
            <td  width="33%" >
              <html:text name="loanerlogoutTaAF"  property="overplusLoanMoney"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class="td1"  ><font face="宋体">核销未收回金额</font></td>
            <td colspan="2"  > 
              <html:text name="loanerlogoutTaAF"  property="noBackMoney"  styleClass="input3" readonly="true"/>
            </td>
            <td  width="17%" class="td1" >挂账余额</td>
            <td  width="33%" > 
              <html:text name="loanerlogoutTaAF"  property="ovaerLoanRepay"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%" class="td1"  >保证金余额</td>
            <td colspan="2"  > 
              <html:text name="loanerlogoutTaAF"  property="bailBalance"  styleClass="input3" readonly="true"/>
            </td>
            <td  width="17%" class="td1" >还款方式</td>
            <td  width="33%" > 
              <html:text name="loanerlogoutTaAF"  property="loanMode"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
        </table>  
     <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
										<logic:equal name="loanerlogoutTaAF" property="type" value="1">
											<html:submit property="method" styleClass="buttona" disabled="true" onclick="return sure();"><bean:message key="button.sure"/></html:submit>&nbsp;
										</logic:equal>
										<logic:notEqual name="loanerlogoutTaAF" property="type" value="1">
										<html:submit property="method" styleClass="buttona"  onclick="return sure();"><bean:message key="button.sure"/></html:submit>&nbsp;
										</logic:notEqual>
										</td>
									</tr>
								</table>        
          </td>
        </tr>
      </table>
      </html:form>
      <form action="loanerlogoutTaShowAC.do" method="POST" name="Form1"
						id="Form1">
					</form>
    </td>
  </tr>
</table>
</body>
</html:html>
