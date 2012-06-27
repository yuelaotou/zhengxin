<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.orgstrct.action.CollBankTaShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination= request.getSession(false).getAttribute(CollBankTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path=request.getContextPath();
 %>
<html:html>
<head>
<title>权限管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script>
<script type="text/javascript">
function gotoEdit(){
	if(document.all.officecode.value==""){
		alert("请选择办事处！");
		document.all.officecode.focus();
		return false;
	}
	if(document.all.bankname.value==""){
		alert("请添写归集银行名称！");
		document.all.bankname.focus();
		return false;
	}
	if(document.all.collectionbankacc.value==""){
		alert("请添写归集银行账号！");
		document.all.collectionbankacc.focus();
		return false;
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000"  onContextmenu="return false">
    <html:form action="/collBankTaEditMaintainAC">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img//table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img//table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path%>/img//table_bg_line.gif"></td>
          <td background="<%=path%>/img//table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img//title_banner.gif" width="37" height="24"></td>
                <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>权限管理</a><font color="00B5DB">&gt;</font><a href="#" class=a1>归集银行维护</a></td>
                <td width="115" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path%>/img//table_right.gif" width="9" height="37"></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="93"><b class="font14">归集银行信息</b></td>
                <td width="601" height="22" align="center" background="<%=path%>/img//bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="1" cellpadding="0" align="center">
        <tr>
          <td width="17%" class="td1">办事处</td>
          <td   align="center" width="33%" > 
              <html:select property="officecode" styleClass="input4" onkeydown="enterNextFocus1();">
              	<html:option value=""></html:option>
              	<logic:notEmpty name="collBankTaAF"  property="officelist">
              	<logic:iterate name="collBankTaAF" id="element" property="officelist">
              	<option value="<bean:write name="element" property="officeCode"/>"><bean:write name="element" property="officeName"/></option>
              	</logic:iterate>
              	</logic:notEmpty>
              </html:select>
          </td>
          <td width="17%" class=td1>归集银行<font color="#FF0000">*</font></td>
          <td width="33%">
          <html:text name="collBankTaAF" property="bankname"  styleClass="input3" onkeydown="enterNextFocus1();" />
          <html:hidden name="collBankTaAF" property="bankid"/>
          </td>
        </tr>
        <tr>
        <td width="17%" class=td1>归集银行账号<font color="#FF0000">*</font></td>
          <td width="33%">
          <html:text name="collBankTaAF" property="collectionbankacc"  styleClass="input3" onkeydown="enterNextFocus1();" />
          </td>
          <td width="17%" class=td1>联系人</td>
          <td width="33%">
          <html:text name="collBankTaAF" property="contactprsn"  styleClass="input3" onkeydown="enterNextFocus1();" />
          </td>
        </tr>
        <tr>
        <td width="17%" class=td1>联系电话</td>
          <td width="33%">
          <html:text name="collBankTaAF" property="contacttel"  styleClass="input3" onkeydown="enterNextFocus1();" />
          </td>
          <td width="17%" class=td1>中心名称</td>
          <td width="33%">
          <html:text name="collBankTaAF" property="centername"  styleClass="input3" onkeydown="enterNextFocus1();" />
          </td>
        </tr>
      </table>
	    
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" >
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
			<table border="0" cellspacing="0" cellpadding="3" align="center" >
               <tr> 
                   <td width="70" align="right">
                   <logic:equal name="collBankTaAF" property="type" value="1">
                   <html:submit property="method" styleClass="buttona" onclick="return gotoEdit();"><bean:message key="button.sure" bundle="orgstrct"/></html:submit>
                   </logic:equal>
                   <logic:equal name="collBankTaAF" property="type" value="2">
                   <html:submit property="method" styleClass="buttona" onclick="return gotoEdit();"><bean:message key="button.edit" bundle="orgstrct"/></html:submit>
                   </logic:equal>
                   </td>
                   <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.back" bundle="orgstrct"/></html:submit>
                   </td>
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