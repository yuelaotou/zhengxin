<%@ page language="java"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.loanapply.endorsecontract.action.EndorsecontractTaShowAC" %>
<%@ include file="/checkUrl.jsp"%> 
<%
String path = request.getContextPath();
%> 
<html:html>
<head>
<title>回文格式对应设置</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/picture.js"></script>
</head>
<script language="javascript">
var s1="";
function gotocheck(){
}

function gotoSure(){
}
function gotoloanId(){//日期弹出框
 var bankId = document.all.bankId.value;
 window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=loanId&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotoname(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=name&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotopayDate(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=payDate&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotorealCorpus(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=realCorpus&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotorealOverdueCorpus(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=realOverdueCorpus&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotorealInterest(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=realInterest&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotorealOverdueInterest(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=realOverdueInterest&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotorealPunishInterest(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=realPunishInterest&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotonobackCorpus(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=nobackCorpus&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotonobackOverdueCorpus(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=nobackOverdueCorpus&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotonobackInterest(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=nobackInterest&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotonobackOverdueInterest(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=nobackOverdueInterest&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotonobackPunishInterest(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=nobackPunishInterest&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function gotodeadLine(){
var bankId = document.all.bankId.value;
window.open("<%=path%>/sysloan/palindromeFormulaPopShowAC.do?property=deadLine&bankId="+bankId+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
}
function loads(){
  	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
	 for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="确定"){
				s1=i;
			}
		}
	}
	var bankId = document.all.bankId.value;
	if(bankId==""){
	 document.all.item(s1).disabled="true";
	}
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
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>数据准备</a><font color="00B5DB">&gt;</font><a href="#" class=a1>银行回文格式设置</a></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="167"><b class="font14">银行回文格式设置</b></td>
                <td width="737" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
	  
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr > 
            <td height="23" colspan="10" align="center" bgcolor="C4F0FF" >&nbsp;</td>
          </tr>
		  <tr  class=td4 > 
            <td width="22%" >&nbsp;</td>
            <td width="6%" >&nbsp;</td>
            <td colspan="3" >&nbsp;</td>
            <td width="11%" align="center" >&nbsp;</td>
            <td width="10%" >&nbsp;</td>
            <td width="8%" >&nbsp;</td>
          </tr>
          <html:form action="/palindromeformatFindAC" style="margin: 0">
          <tr  class=td4 > 
            <td width="22%" >&nbsp;</td>
            <td width="6%"colspan="2" class="td1"><center>银行</center></td>
            <td colspan="2" ><html:select name="palindromeformatAF" property="bankId"  styleClass="input4">
             		<html:option value=""></html:option>
					<html:options collection="loanBankNameList" property="value"
								labelProperty="label" />
			 </html:select></td>
            <td width="11%" align="center" ><html:submit property="method" styleClass="buttona"  onclick="return gotocheck();"><bean:message key="button.search" /></html:submit></td>
            <td width="10%" >&nbsp;</td>
            <td width="8%" >&nbsp;</td>
          </tr>
          </html:form>
          <tr  class=td4 > 
            <td >&nbsp;</td>
            <td >&nbsp;</td>
            <td width="8%" >&nbsp;</td>
            <td width="8%" >&nbsp;</td>
            <td width="27%" >&nbsp;</td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
          <html:form action="/palindromeformatSaveAC" style="margin: 0">
          <tr  class=td4 > 
            <td align="right">1</td>
            <td colspan="2" align="center" class="td1" >贷款帐号</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="loanId" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotoloanId();">
              <input type="button" name="Submit4223" value="浏览" class="buttona" >
            </a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
		 <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table id="gjts" style="display:none"  width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
		 <tr  class=td4 > 
            <td align="right">2</td>
            <td colspan="2" align="center" class="td1" >姓名</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="name" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotoname();"><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" ><table width="100%" height="100%">
              <tr>              </tr>
            </table></td>
            <td >&nbsp;</td>
         </tr>
		 <tr  class=td4 > 
            <td align="right">3</td>
            <td colspan="2" align="center" class="td1" >还款年月</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="payDate" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotopayDate();"><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
         <tr  class=td4 > 
            <td align="right">4</td>
            <td colspan="2" align="center" class="td1" >实扣正常本金</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="realCorpus" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotorealCorpus();"><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
         <tr  class=td4 > 
            <td align="right">5</td>
            <td colspan="2" align="center" class="td1" >实扣逾期本金</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="realOverdueCorpus" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotorealOverdueCorpus();"><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
          <tr  class=td4 > 
            <td align="right">6</td>
            <td colspan="2" align="center" class="td1" >实扣正常利息</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="realInterest" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotorealInterest();"><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
         <tr  class=td4 > 
            <td align="right">7</td>
            <td colspan="2" align="center" class="td1" >实扣逾期利息</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="realOverdueInterest" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotorealOverdueInterest();"><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
         <tr  class=td4 > 
            <td align="right">8</td>
            <td colspan="2" align="center" class="td1" >实扣罚息</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="realPunishInterest" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotorealPunishInterest();" ><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
         <tr  class=td4 > 
            <td align="right">9</td>
            <td colspan="2" align="center" class="td1" >未还正常本金</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="nobackCorpus" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotonobackCorpus();" ><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
         <tr  class=td4 > 
            <td align="right">10</td>
            <td colspan="2" align="center" class="td1" >未还逾期本金</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="nobackOverdueCorpus" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotonobackOverdueCorpus();" ><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
         <tr  class=td4 > 
            <td align="right">11</td>
            <td colspan="2" align="center" class="td1" >未还正常利息</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="nobackInterest" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotonobackInterest();" ><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
         <tr  class=td4 > 
            <td align="right">12</td>
            <td colspan="2" align="center" class="td1" >未还逾期利息</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="nobackOverdueInterest" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotonobackOverdueInterest();" ><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
         <tr  class=td4 > 
            <td align="right">13</td>
            <td colspan="2" align="center" class="td1" >未还罚息</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="nobackPunishInterest" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotonobackPunishInterest();" ><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
         <tr  class=td4 > 
            <td align="right">14</td>
            <td colspan="2" align="center" class="td1" >提前还款后剩余期限</td>
            <td colspan="2" ><html:text name="palindromeformatAF" property="deadLine" styleClass="input3" readonly="true"/></td>
            <td  align="center"><a href="#"onClick="gotodeadLine();" ><input type="button" name="Submit422" value="浏览" class="buttona" ></a></td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
         <tr  class=td4 > 
            <td >&nbsp;</td>
            <td colspan="6" >
			<table width="100%" height="100%">
			  <tr>			  </tr>
			</table>			</td>
            <td >&nbsp;</td>
         </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr > 
		  <td>&nbsp; </td>
	    </tr>
      </table>
       
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70"> 
                  <html:submit property="method" styleClass="buttonb" onclick="return gotoSure(); "><bean:message key="button.sure"/></html:submit>
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

