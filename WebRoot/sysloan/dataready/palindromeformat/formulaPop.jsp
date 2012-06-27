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
<title>回文公式录入</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/picture.js"></script>
</head>
<script language="javascript">
function add(){
 var add = "+";
 var hidden = document.all.hidden.value;
 if(hidden != "1"){//说明上一步不是点击运算符
 document.all.hidden.value = "1";
 var formula = document.all.formula.value;
 document.all.formula.value = formula+add;
 }else{
  alert("运算符不能相连");
 }
}

function subtration(){
 var subtration = "-";
 var hidden = document.all.hidden.value;
 if(hidden != "1"){//说明上一步不是点击运算符
 document.all.hidden.value = "1";
 var formula = document.all.formula.value;
 document.all.formula.value = formula+subtration;
 }else{
 alert("运算符不能相连");
 }
}

function change(){
 var number = document.all.number.value;
 var formula = document.all.formula.value;
 var hidden = document.all.hidden.value;
 if(hidden == "1"){ //点击过运算符
 document.all.formula.value = formula+number;
 document.all.hidden.value = "0";
 }else{
 document.all.formula.value = number;
 }
}

function reset(){
 document.all.formula.value = "";
}

function sure(){
 var hidden = document.all.hidden.value;
 if(hidden == "1"){//点击过运算符
  alert("公式不能以运算符结束!");
  return false;
 }
 var formula = document.all.formula.value;
 var property = document.all.property.value;
 var temp;
 eval("temp=window.opener.document.all."+property);
 temp.value=formula;//把formula文本框上的公式返回到父窗口中property相应的控件上
 window.close();
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td background="<%=path%>/img/table_bg_line.gif"><a href="开户销户_开户登记.htm"></a> <a href="开户销户_开户登记列表.htm"></a></td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">数据准备</font><font color="00B5DB">&gt;</font><font color="00B5DB">银行回文格式设置</font></td>
                <td width="115" class=font14>&nbsp;</td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">公式设置</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form action="/palindromeformatShowAC.do">
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr>
          <td width="18%"  ></td>
          <td width="13%" align="center"  class="td1" >公式：</td>
          <td width="11%" colspan="3" ><html:text name="palindromeFormulaPopAF" property="formula" styleClass="input3" readonly="true"/></td>
          <td  width="21%"  ><html:hidden name="palindromeFormulaPopAF" property="hidden" styleClass="input3"/></td>
        </tr>
        <tr>
          <td width="18%" ><html:hidden name="palindromeFormulaPopAF" property="property" styleClass="input3"/></td>
          <td width="13%"  >&nbsp;</td>
          <td width="11%" colspan="3" ><html:select name="palindromeFormulaPopAF" property="number"  styleClass="input4" onchange="change();">
             		<html:option value=""></html:option>
					<html:options  collection="list" name="palindromeFormulaPopAF" property="value" labelProperty="label"/></html:select></td>
          <td width="11%"  >
              <input type="button" name="Submit2" value="+" class="buttona" onclick="add();">
              <input type="button" name="Submit3" value="-" class="buttona" onclick="subtration();">
          </td>
        </tr>
        <tr id="trdis1"> </tr>
        <tr id="trdis2" style="display:none">
          <td   class="td1">&nbsp;</td>
          <td  >&nbsp;</td>
          <td class="td1" >&nbsp;</td>
          <td  >&nbsp;</td>
        </tr>
        <tr>
          <td   >&nbsp;</td>
          <td align="center"  >&nbsp;</td>
          <td colspan="3" ><input type="submit" name="Submit42" value="确定" class="buttona" onclick="return sure();">
          </td>
          <td  colspan="3" ><input type="reset" name="Submit4" value="重置" class="buttona" onclick="reset();"></td>
        </tr>
      </table>
      </html:form>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr align="right" valign="bottom"> 
          <td  bgcolor="#FFFFFF" height="30">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html:html>
