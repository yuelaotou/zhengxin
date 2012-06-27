<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security" %>
<%@ page import="org.xpup.hafmis.syscollection.pickupmng.pickup.action.PageSkipAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination= request.getSession().getAttribute(PageSkipAC.PAGINATION_KEY);
    request.setAttribute("pagination",pagination);
%>
<script type="text/javascript" >
<!--
	function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
	function loads() {
		document.employeePickInfoAF.pickTime.value="";
		document.employeePickInfoAF.checkReason.value="";
	}
//-->
</script>
<link rel="stylesheet" href="<%=path %>/css/index.css" type="text/css">
<html:html lang="true">
  <head>
    <html:base />
    <title>查询提取信息</title>
   </head>
 <body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="loads();">
 <jsp:include page="../../../inc/sort.jsp"><jsp:param name="url" value="pageSkipAC.do" /></jsp:include>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path %>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path %>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path %>/img/table_bg_line.gif"><a href="提取管理_单位提取办理_简约风格.html"></a></td>
          <td background="<%=path %>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="190" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">职工提取情况</font></td>
                <td width="73" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path %>/img/table_right.gif" width="9" height="37"></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="127"><b class="font14">查询条件</b></td>
                <td width="816" height="22" align="right" background="<%=path%>/img/bg2.gif">&nbsp; 
                </td>
              </tr>
            </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
          </td>
        </tr>
      </table>
      <html:form action="/pageSkipAC.do">
	  <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center">
        <tr> 
          <td width="17%" height="35" class="td1">提取时间</td>
          <td width="33%">
          <html:text property="pickTime" name="employeeInfo" styleClass="input3"></html:text>
          </td>
          <td width="17%" class	="td1">提取原因</td>
          <td width="33%">
          	<html:select style="input4" property="checkReason" styleClass="input4" name="employeeInfo">
          	<option></option>
          		<html:optionsCollection property="map" name="employeeInfo" label="value" value="key" />
  			</html:select>		
          </td>
        </tr>
		 <tr> 
          <td height="35" colspan="3">&nbsp;</td>
          <td width="33%" align="right">
          	<html:submit styleClass="buttona">查询</html:submit>
          </td>
		 </tr>
      </table>
  		</html:form>								 
	  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td class=h4>合计：提取金额<u>:
            	<bean:write name="employeeInfo" property="pickBalance"/>
            </u><security:orgcannot> 销户利息<u>:<bean:write name="employeeInfo" property="pickInterest"/></u> 提取总额<u>:<bean:write name="employeeInfo" property="pickTotal"/></u></security:orgcannot></td>
          </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="22" bgcolor="#CCCCCC" align="center" width="127"><b class="font14">提取信息</b></td>
          <td width="816" height="22" align="right" background="<%=path%>/img/bg2.gif">&nbsp;</td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr > 
            <td align="center" bgcolor="C4F0FF" >
            <a href="javascript:sort('t.pickSalary')">提取金额</a>
            <logic:equal name="pagination" property="orderBy" value="t.pickSalary">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
            </td>
            <security:orgcannot>  <td align="center" class=td2 >销户利息</td>
            <td align="center" class=td2 >提取总额
            </td>
            </security:orgcannot>
            <td align="center" class=td2 >提取类型</td>
            <td align="center" class=td2 >
            	<a href="javascript:sort('t.pickReason')">提取原因</a>
          	<logic:equal name="pagination" property="orderBy" value="t.pickReason">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
            </td>
            <td align="center" class=td2 >提取时间</td>
          </tr>
          <logic:notEmpty name="employeeInfo" property="list">
          	<logic:iterate id="e" name="employeeInfo" property="list">
	          <tr id="tr1"  onMouseOver="this.style.background='#eaeaea'"  onMouseOut="this.style.background='#ffffff'" > 
	            <td valign="top">
	            	<bean:write name="e" property="pickSalary"/>
	            </td>
	             <security:orgcannot>
	            <td valign="top">
	            	<bean:write name="e" property="pickInterest"/>
	            </td>
	            <td valign="top">
	            	<bean:write name="e" property="total"/>
	            </td>
	            </security:orgcannot>
	            <td valign="top">
	            	<bean:write name="e" property="temp_PickType"/>
	            </td>
	            <td valign="top">
	            	<bean:write name="e" property="reason"/>
	            </td>
	            <td valign="top">
	            	<bean:write name="e" property="time"/>
	            </td>
	          </tr>
	          <tr > 
	            <td colspan="7" class=td5 ></td>
	          </tr>
	          </logic:iterate>
          </logic:notEmpty>
          <logic:empty name="employeeInfo" property="list">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合结果！<br></td>
	    </tr>
        </logic:empty>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
		   <table width="380" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr align="center"> 
			  	<td>
			  	 共<bean:write name="pagination" property="nrOfElements"/> 项
			  	<br></td>
                <td>
                	<jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="pageSkipAC.do" /></jsp:include>
                <br></td>
              </tr>
          </table>
		  </td>
	    </tr>
      </table>
      <table width="75%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td align="center"><input type="submit" name="Submit22" value="关闭" class="buttona" onClick="javascript:window.close();"></td>
        </tr>
      </table>
    </table>
    </body>
</html:html>
