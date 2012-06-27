<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.syscollection.paymng.monthpay.action.MonthpayTbWindowMXShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination= request.getSession(false).getAttribute(MonthpayTbWindowMXShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path=request.getContextPath();
 %>
<html:html>
<head>
<title>缴存管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">

</script>
<script type="text/javascript">
function exportArray(){

	   	//这个留在备用，可以操作主页面   
	window.open ('<%=path%>/syscollection/paymng/monthpay/monthpayexport_array.jsp','newwindow','height=190,width=350,top='+(window.screen.availHeight-190)/2+',left='+(window.screen.availWidth-350)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	return false;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
<jsp:include page="../../../inc/sort.jsp"><jsp:param name="url" value="monthpayTbWindowMXShowAC.do"/></jsp:include>
<html:form action="/monthpayTbWindowMXPrintAC.do" style="margin: 0">
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
            <td class=h4> 合计： 人数<u>：<bean:write name="pagination" property="nrOfElements"/> </u> 职工缴存金额<u>：<bean:write name="dto" property="emppay"/></u> 单位缴存金额<u>：<bean:write name="dto" property="orgpay"/></u> 
              缴存金额<u>：<bean:write name="dto" property="sumpay"/></u></td>
          </tr>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="148"><b class="font14">缴存明细列表 
                    </b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="762">
                
                  &nbsp;</td>
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
            <td align="center" class=td2 > <a href="javascript:sort('monthPaymentTail.empId')">职工编号</a>
          	<logic:equal name="pagination" property="orderBy" value="monthPaymentTail.empId">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td align="center" class=td2 ><a href="javascript:sort('monthPaymentTail.empName')">职工姓名</a>
          	<logic:equal name="pagination" property="orderBy" value="monthPaymentTail.empName">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td align="center" class=td2 >汇缴年月</td>
            <td align="center" class=td2 >单位缴存金额</td>
            <td align="center" class=td2 >职工缴存金额</td>
            <td align="center" class=td2 >缴存金额</td>
			 <td align="center" class=td2 > 职工状态</td>
          </tr>
          <logic:notEmpty name="list">
		<logic:iterate id="list" name="list" indexId="i">
        <tr id="tr1"  onMouseOver='this.style.background="#eaeaea"'  onMouseOut='this.style.background="#ffffff"' > 
          <td valign="top"><p><bean:write name="list" property="empid" format="000000"/></p></td>
          <td valign="top"><p><bean:write name="list" property="empName"/></p></td>
          <td valign="top"><p><bean:write name="list" property="payMonth"/></p></td>
          <td valign="top"><p><bean:write name="list" property="orgpay"/></p></td>
          <td valign="top"><p><bean:write name="list" property="emppay"/></p></td>
          <td valign="top"><p><bean:write name="list" property="sumpay"/></p></td>
          <td valign="top"><p><bean:write name="list" property="empStatus"/></p></td>
        </tr>
        <tr > 
          <td colspan="11" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="list">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	    </tr>
		<tr > 
          <td colspan="11" class=td5 ></td>
        </tr>
        </logic:empty>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
          <td align="center">
            <table width="400" border="0" cellspacing="0" cellpadding="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="monthpayTbWindowMXShowAC.do"/></jsp:include></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="10" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.print"/></html:submit>
                  </td>
                  <td width="70"> 
                    <input type="button" name="Submit42" value="导出" class="buttona" onClick="return exportArray();" >
                  </td>
                  <td width="70"> 
                    <input type="button" name="Submit42" value="关闭" class="buttona" onClick="javascript:window.close();" >   
                  </td>
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
