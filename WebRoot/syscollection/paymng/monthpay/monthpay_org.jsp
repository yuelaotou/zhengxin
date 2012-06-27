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
<title>缴存管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script >
<script type="text/javascript">
function gotoOpen(){
	var paymentid=document.all.paymentid.value;
	window.open('monthpayTbWindowMXForwardURLAC.do?paymentid='+paymentid,'','width=700,height=500,top=200,left=300,scrollbars=yes');
}
function loads(){
var id=document.all.orgid.value;
document.all.orgid.value=formatTen(id)+id;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="loads();">
<html:form action="/monthpayTbWindowMaintainAC.do" style="margin: 0">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path%>/img/table_bg_line.gif">&nbsp; </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">缴存管理<font color="00B5DB">&gt;正常汇缴</font></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">缴 存 信 息</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr> 
          <td width="17%" class="td1"  >单位编码</td>
          <td   > 
            <html:text name="monthpayJYAF" property="orgid"  styleClass="input3"  styleId="txtsearch" readonly="true"/> 
          </td>
          <td class="td1" width="17%"   >单位名称</td>
          <td width="33%"   > 
            <html:text name="monthpayJYAF" property="name"  styleClass="input3"  styleId="txtsearch" readonly="true"/> 
          </td>
        </tr>
        <tr> 
          <td width="17%"   class="td1"> 结算号</td>
          <td > 
            <html:text name="monthpayJYAF" property="noteNum"  styleClass="input3"  styleId="txtsearch" readonly="true" />
          </td>
          <td class="td1" width="17%" >结算方式</td>
          <td width="33%"> 
            <logic:equal name="monthpayJYAF" property="payStatus" value="1">
            <input name="txtsearch45221222" type="text" id="txtsearch" class="input3" value="均缴"  readonly="true" ></logic:equal>
            <logic:equal name="monthpayJYAF" property="payStatus" value="2">
            <input name="txtsearch45221222" type="text" id="txtsearch" class="input3" value="只缴单位" readonly="true" ></logic:equal>
            <logic:equal name="monthpayJYAF" property="payStatus" value="3">
            <input name="txtsearch45221222" type="text" id="txtsearch" class="input3" value="只缴职工" readonly="true" ></logic:equal>
          </td>
        </tr>
        <tr> 
          <td width="17%"   class="td1"> 汇缴月份</td>
          <td  > 
            <html:text name="monthpayJYAF" property="inceptMonth"  styleClass="input3"  styleId="txtsearch"  readonly="true" />
          </td>
          <td width="17%" class="td1"  > 至</td>
          <td width="33%"  > 
            <html:text name="monthpayJYAF" property="payMonth"  styleClass="input3"  styleId="txtsearch" readonly="true"  />
          </td>
        </tr>
      </table>
      <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
          <td height="5"><html:hidden name="monthpayJYAF" property="paymentid"/></td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="1" cellpadding="3" align="center" bgcolor="#B9E2F7">
        <tr bgcolor="#FFFFFF" > 
          <td align="center" colspan="4" height="20" >应缴人数</td>
          <td align="center" colspan="4" height="20" >应缴金额</td>
        </tr>
          <tr bgcolor="#FFFFFF" > 
            <td align="center" >上月</td>
            <td align="center" >增加</td>
            <td align="center" >减少</td>
            <td align="center" >本月</td>
            <td align="center" >上月</td>
            <td align="center" >增加</td>
            <td align="center" >减少</td>
            <td align="center" >本月</td>
          </tr>
          <tr class=td7 bgcolor="#FFFFFF"> 
            <td align="center" valign="top"><bean:write name="monthpayJYAF" property="ultimoPersonCounts"/>&nbsp;</td>
            <td align="center" valign="top" ><bean:write name="monthpayJYAF" property="personCountsAdd"/>&nbsp;</td>
            <td align="center" valign="top" ><bean:write name="monthpayJYAF" property="personCountsLess"/>&nbsp;</td>
            <td align="center" valign="top"><bean:write name="monthpayJYAF" property="personCounts"/>&nbsp;</td>
            <td align="center" valign="top" ><bean:write name="monthpayJYAF" property="ultimoPayMoney"/>&nbsp;</td>
            <td align="center" valign="top"><bean:write name="monthpayJYAF" property="payMoneyAdd"/>&nbsp;</td>
            <td align="center" valign="top" ><bean:write name="monthpayJYAF" property="payMoneyLess"/>&nbsp;</td>
            <td align="center" valign="top"><bean:write name="monthpayJYAF" property="payMoney"/>&nbsp;</td>
          </tr>
      </table>
      <br>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr> 
          <td width="17%"   class="td1">应缴总额</td>
          <td colspan="2"  > 
            <html:text name="monthpayJYAF" property="sumPay"  styleClass="input3"  styleId="sumPay" readonly="true"/>
          </td>
          <td width="17%" class="td1"  >实缴金额<font color="#FF0000">*</font></td>
          <td width="33%"  >
            <html:text name="monthpayJYAF" property="realPay"  styleClass="input3"  styleId="shouldPay"  readonly="true" />
          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="36"> 
            <table height="20" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td width="70"> 
                  <input type="button" name="Submit3" class="buttona" value="缴存明细" onClick="gotoOpen();" >
                </td>
<%--                <td width="70">--%>
<%--                  <input type="submit" name="Submit322" class="buttona" value="打印" >--%>
<%--                </td>--%>
                <td width="70"> 
                  <input type="button" name="Submit32" class="buttona" value="关闭" onClick="javascript:window.close();" >
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
