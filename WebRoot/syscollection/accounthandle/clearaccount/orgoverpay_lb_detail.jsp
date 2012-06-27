<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
 %>
<html:html>
<head>
<title>扎账</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script src="<%=path%>/js/common.js">
</script>
<script language="javascript"  type="text/javascript" >
function loads(){	
	var orgid=document.clearAccountDetailAF.traninId.value;
	orgid=format(orgid)+orgid;
	document.clearAccountDetailAF.traninId.value=orgid;

}
function executeAjax() {
     var queryString = "clearAccountMXFindAAC.do";
	 findInfo(queryString);
}
function display(headid,url,type){
	document.URL='<%=path%>/syscollection/paymng/orgoverpay/orgoverpayTbMXPrintAC.do?paymentid='+headid+'&url=<%=path %>'+url+'';
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"  onContextmenu="return false">
<jsp:include page="../../../inc/sort.jsp"><jsp:param name="url" value="OrgoverpayTaShowAC.do"/></jsp:include>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td width="235" background="<%=path%>/img/table_bg_line.gif"> 
              <table border="0" cellspacing="0" cellpadding="0">

              </table>
            </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="136" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">扎账&gt;业务明细</font></td>
                  <td class=font14>&nbsp;</td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">单位挂账信息</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
	  

	 <html:form action="/clearAccountOrgoverPrintAC.do">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  >单位编号：</td>
            <td width="22%"   > 
              <html:text name="clearAccountDetailAF" property="traninId"  styleClass="input3" onkeydown="gotoEnter();" styleId="txtsearch" readonly="true" /> 
            </td>
            <td width="17%" class="td1"  > 单位名称： </td>
            <td  width="33%"  > 
              <html:text name="clearAccountDetailAF" property="traninName"  styleClass="input3" styleId="unitName" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">结算号：</td>
            <td width="33%" > 
              <html:text name="clearAccountDetailAF" property="noteNum"  styleClass="input3" styleId="noteNum" readonly="true"/>
            </td>
            <td class="td1" width="17%" >挂账余额：</td>
            <td width="33%"  >
              <html:text name="clearAccountDetailAF" property="banlance"  styleClass="input3" styleId="banlance" readonly="true"/>
            </td>
          </tr>
          <tr>
         <td class="td1" width="17%" >挂账金额：</td>
            <td width="33%"  >
              <html:text name="clearAccountDetailAF" property="amount"  styleClass="input3" styleId="amount" readonly="true"/>
            </td>
          </tr>
           <tr>
           <td class="td1" width="17%" rowspan="3" >挂账原因：</td>
           <td colspan="4" width="50%" rowspan="3" >
           <html:textarea name="clearAccountDetailAF" property="reason" cols="50" rows="4" styleId="reason" readonly="true" ></html:textarea>
           </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr><!-- 
                  <td width="70"> 
                    <html:button property="method" styleClass="buttona" onclick="executeAjax();"><bean:message key="button.print"/></html:button></td>
                  --> <td width="70"> 
                    <input type="button" name="Submit42" value="关闭" class="buttona" onClick="javascript:window.close();" ></td>
              </table>
    </td>
  </tr>
</table>

        </html:form>
        </td>
        </tr>
        </table>
        </html:html>