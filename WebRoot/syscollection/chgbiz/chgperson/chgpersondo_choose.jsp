<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>

<%@ page import="org.xpup.hafmis.syscollection.chgbiz.chgperson.action.ShowChgpersonDoListAC" %>

<%
   Object pagination= request.getSession(false).getAttribute(ShowChgpersonDoListAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path = request.getContextPath();
 %>
<html:html>
<head>
<title>人员变更-办理变更</title>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>

<body bgcolor="#FFFFFF" text="#000000"  onContextmenu="return false" >

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>     
    
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path %>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path %>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path %>/img/table_bg_line.gif">
            
           <td background="<%=path %>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path %>/img/title_banner.gif" width="37" height="24"></td>
                <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">变更业务</font><font color="00B5DB">&gt;人员变更</font></td>
                <td width="115" class=font14>&nbsp;</td>
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
      
     		
   		 	<html:form  action="/saveChgpersonDoAC.do" style="margin: 0">

      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">职工列表</b></td>
                <td height="22" background="<%=path %>/img/bg2.gif" align="center">&nbsp;</td>
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
          <td align="center" height="23" bgcolor="C4F0FF" >单选</td>
          <td align="center" class=td2 >职工编号</td>    
          <td align="center" class=td2 >职工姓名</td>          
          <td align="center" class=td2 >原单位名称</td>
        </tr>   
		<logic:notEmpty name="chgpersonDoIdAF" property="list" >
		<logic:iterate name="chgpersonDoIdAF" property="list"  id="element" indexId="i">
	
        <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>",chgpersonDoIdAF);' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>",chgpersonDoIdAF);' class=td4  onDblClick=""> 
        
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="element" property="id"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td>
          <td valign="top"><p><bean:write name="element" property="empId" format="000000" /></p></td>
          <td valign="top"><p><bean:write name="element" property="empInfo.name" /></p></td>
          <td valign="top"><p><bean:write name="element" property="org.orgInfo.name" /></p></td>
        </tr>
        <tr > 
          <td colspan="9" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
        <tr> 
          <td colspan="11" height="30" style="color:red">该职工在其他单位已经存在账户，请选择一个编号！</td>
	    </tr>
      </table>


   	  <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><html:submit property="method" styleClass="buttona"><bean:message key="button.add"/></html:submit></td>    
			  	<td><html:submit property="method" styleClass="buttona"><bean:message key="button.back"/></html:submit></td>    
			  </tr>
            </table>    
      	  </td>
  		</tr>
      </table> 
           </html:form>
      

</table>
</html:html>
