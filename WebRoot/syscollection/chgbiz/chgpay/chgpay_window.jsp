<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security" %>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.syscollection.chgbiz.chgpay.action.ChgpayTaWindowForwardAC" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
   Object pagination= request.getSession(false).getAttribute(ChgpayTaWindowForwardAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'chgpay_window.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
  </head>
  <script language="javascript" src="<%=request.getContextPath()%>/js/common.js">
  <body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()" onContextmenu="return false">
	<jsp:include page="/inc/sort.jsp"><jsp:param name="url" value="chgpayTaWindowForwardAC.do"/>	</jsp:include>
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
                  
                </tr>
              </table>
            </td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=request.getContextPath()%>/img/title_banner.gif" width="37" height="24"></td>
                  <td class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"> <font color="00B5DB">变更业务 &gt; 缴额调整 </font> </td>
                  <td class=font14>&nbsp;</td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">单 位 信 息</b></td>
                <td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form  action="/chgpayTaSouwAC.do" >  
      
      <html:hidden  name="chgpayListAF" property="listCount"  styleClass="input3"    styleId="txtsearch"></html:hidden>

        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
         <tr> 
            <td width="17%" class="td1"  >单位编号</td>
            <td width="33%"   > 
              <html:text name="chgpayListAF" property="org.id"  styleClass="input3" styleId="txtsearch" readonly="true"></html:text>
            
            </td>

            <td > 
            </td>

            <td class="td1" width="17%" >单位名称 </td>
            <td width="33%" > 
              <html:text  name="chgpayListAF" property="org.orgInfo.name" styleClass="input3"  readonly="true" />
            </td>
          </tr>
        
          <tr> 
            <td width="17%" class="td1"  >调整年月<font color="#FF0000">*</font></td>
            <td width="26%" colspan="2"   > 
              <html:text name="chgpayListAF" property="chgMonth" styleClass="input3"  styleId="txtsearch" readonly="true"></html:text>
            </td>
           <td width="17%" class="td1"  ></td>
            <td width="26%" colspan="2"   > 
              
            </td>
          </tr> 
        </table>  
        </html:form>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
      
           <tr> 
            <td class=h4>合计：调整人数<u>：<bean:write name="chgpayListAF" property="oldPaymenSum" /></u>
             调整前单位缴额<u>：<bean:write name="chgpayListAF" property="oldOrgPaySum" /></u> 
            调整后单位缴额<u>：<bean:write name="chgpayListAF" property="orgPaySum" /></u> 
             调整前职工缴额<u>：<bean:write name="chgpayListAF" property="oldEmpPaySum" /></u> 
            调整后职工缴额<u>：<bean:write name="chgpayListAF" property="empPaySum" /></u> 
           调整前应汇缴总额<u>：<bean:write name="chgpayListAF" property="oldPayment" /></u> 
             调整后应汇缴额<u>：<bean:write name="chgpayListAF" property="paySum" /></u></td>
          </tr>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="154"><b class="font14">缴额调整列表 </b></td>
                  <td width="750" height="22" align="center" background="<%=request.getContextPath()%>/img/bg2.gif">&nbsp;</td>
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

    <html:form  action="/chgpayTaMaintainAC" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr > 
          <td align="center" height="23" bgcolor="C4F0FF" >&nbsp;</td>
                 <td align="center" class=td2 >
          	<a href="javascript:sort('chgPaymentTail.empId')">职工编号</a>
          	<logic:equal name="pagination" property="orderBy" value="chgPaymentTail.empId">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>
      
                 <td align="center" class=td2 >
          	<a href="javascript:sort('chgPaymentTail.empName')">职工姓名 </a>
          	<logic:equal name="pagination" property="orderBy" value="chgPaymentTail.empName">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>     
          <td align="center" class=td2 >证件号码</td>
          <td align="center" class=td2 >工资基数</td>
           <td align="center" class=td2 >调整前单位缴额</td>
            <td align="center" class=td2 >调整后单位缴额</td>
          <td align="center" class=td2 >调整前职工缴额</td>
          <td align="center" class=td2 >调整后职工缴额</td>   
          <td align="center" class=td2  >缴额合计</td>
        </tr>
    	<logic:notEmpty name="chgpayListAF" property="list">
		<logic:iterate name="chgpayListAF" property="list" id="element" indexId="i">
        <tr id="tr<%=i%>" onClick='gotoClick("tr<%=i %>","s<%=i%>", idAF);' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick=""> 
           <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="hidden" name="id" value="<bean:write name="element" property="id"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td>
          <td valign="top"><p><bean:write name="element" property="empId" format="000000"/></p></td>
            <td valign="top"><p><bean:write name="element" property="emp.empInfo.name" /></p></td>
          <td valign="top"><p><bean:write name="element" property="emp.empInfo.cardNum" /></p></td>
          <td valign="top"><p><bean:write name="element" property="salaryBase" /></p></td>
          <td valign="top"><p><bean:write name="element" property="oldOrgPay" /></p></td>
          <td valign="top"><p><bean:write name="element" property="orgPay" /></p></td>         
          <td valign="top"><p><bean:write name="element" property="oldEmpPay" /></p></td>
          <td valign="top"><p><bean:write name="element" property="empPay" /></p></td>
          <td valign="top"><p><bean:write name="element" property="oldPaySum" /></p></td>
           </tr>
        <tr > 
          <td colspan="11" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="chgpayListAF"  property="list">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="11" ></td>
        </tr>
        </logic:empty>
        <tr > 
          <td colspan="9"  ></td>
        </tr>
      </table>

      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="chgpayTaWindowForwardAC.do"/></jsp:include></td>
              </tr>
          </table></td>
	    </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr > 
          <td align="center" height="6" colspan="1" >
             <input type="hidden" name="chgMonth2" value="" >
                <input type="Hidden" name="PKID" value="<bean:write name="chgpayListAF" property="id"/>"> 
                <input type="Hidden" name="org.id" value="<bean:write name="chgpayListAF" property="org.id"/>"> 
                <input type="Hidden" name="nrOfElements" value="<bean:write name="pagination" property="nrOfElements"/>"> 
             <input type="Hidden" name="orgRate" value="<bean:write name="chgpayListAF" property="org.orgRate"/>"> 
                <input type="Hidden" name="empRate" value="<bean:write name="chgpayListAF" property="org.empRate"/>"> 
                 <input type="Hidden" name="org.orgInfo.name" value="<bean:write name="chgpayListAF" property="org.orgInfo.name"/>"> 
               
          </td>
        </tr>
      </table>
    
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>  
              <input type="button" name="Submit42" value="关闭" class="buttona" onClick="javascript:window.close();" >
			 </tr> 
            </table>
    </td>
  </tr>

</table>
 </html:form>
     <form action="chgpayTaSouwAC.do" method="POST" name="Form1" id="Form1">
    </form>
</table>
  </body>
</html:html>
