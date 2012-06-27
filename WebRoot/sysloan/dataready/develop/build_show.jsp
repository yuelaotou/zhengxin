<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@page import="org.xpup.hafmis.sysloan.dataready.develop.action.BuildShowAC"%>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(BuildShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
  
    <title>开发商维护</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
  </head>
  <script language="javascript" src="<%=path%>/js/common.js">
	
</script> 
<script>
function reportMessage(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
var tr="tr0"; 
	function gettr(trindex) {
	  tr=trindex;
	}
	function deleteCheck(){
	  var x=confirm("是否要删除记录?");
	  if(x){
		return true;
	  }else{
	    return false;
	  }
	}
</script>
  <body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="return reportMessage();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td width="235" background="<%=path%>/img/table_bg_line.gif"> 
            </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="165" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">数据准备&gt;开发商维护</font></td>
                  <td width="98" class=font14>&nbsp;</td>
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
      <html:form method="post" action="/buildFindAC">
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">查 询 条 件</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="17%"   class="td1">开发商编号</td>
            <td width="33%"  colspan="3" >
               <html:text name="buildFindAF" property="buildDTO.developerId" styleClass="input3" readonly="true" onkeydown="enterNextFocus1();" maxlength="100"></html:text>           </td>
            <td width="17%" class="td1" >开发商名称</td>
            <td width="33%" colspan="3"  >
              <html:text name="buildFindAF" property="buildDTO.developerName" styleClass="input3" readonly="true" onkeydown="enterNextFocus1();" maxlength="100"></html:text>            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">楼盘号</td>
            <td width="33%"  colspan="3" >
               <html:text name="buildFindAF" property="buildDTO.floorNum" styleClass="input3" readonly="true" onkeydown="enterNextFocus1();" maxlength="100"></html:text>           </td>
            <td width="17%" class="td1" >楼盘名称</td>
            <td width="33%" colspan="3"  >
              <html:text name="buildFindAF" property="buildDTO.floorName" styleClass="input3" readonly="true" onkeydown="enterNextFocus1();" maxlength="100"></html:text>            </td>
          </tr>
          <tr> 
          <%-- 
            <td width="17%"   class="td1">楼栋编号</td>
            <td width="33%"  colspan="3" >
               <html:text name="buildFindAF" property="buildDTO.buildId" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"></html:text>           </td>
          --%>
            <td width="17%" class="td1" >楼栋号</td>
            <td width="33%" colspan="3"  >
              <html:text name="buildFindAF" property="buildDTO.buildNum" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"></html:text>            </td>
          </tr>
        </table>
        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td align="right"><html:submit property="method" styleClass="buttona"><bean:message key="button.search"/></html:submit>
            </td>
          </tr>
        </table>
        </html:form>
        <html:form action="/buildMainTainAC">
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">楼 栋 列 表 </b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table></td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr bgcolor="1BA5FF" > 
            <td align="center" height="6" colspan="1" ></td>
          </tr>
        </table>
        <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
          <%--   <td align="center" class=td2 >楼栋编号</td> --%>
            <td align="center" class=td2 >楼栋号</td>
            <td align="center" class=td2 >楼栋地址</td>
            <td align="center" class=td2 >建筑面积</td>
            <td align="center" class=td2 >是否拨款</td>
            <td align="center" class=td2 >备注</td>
          </tr>
         <logic:notEmpty name="buildAF" property="list"> 
         
<% int j=0;
			String strClass="";
		%>
         <logic:iterate id="buildDTO" name="buildAF" property="list" indexId="i"> 
         <%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
          <tr id="tr<%=i%>" onclick='gotoClickpp("<%=i %>", buildMainTainAF);gettr("tr<%=i%>");' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", buildMainTainAF);' class="<%=strClass%>"  onDblClick="">
            <td > 
              <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="buildDTO" property="buildId"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>            </td>
           <%--    <td align="center"><p><bean:write name="buildDTO" property="buildId"/></p></td> --%>
            <td align="center"><p><bean:write name="buildDTO" property="buildNum"/></p></td>
            <td align="center"><p><bean:write name="buildDTO" property="buildAdd"/></p></td>
            <td align="center"><p><bean:write name="buildDTO" property="build_s"/></p></td>
            <td align="center"><p><bean:write name="buildDTO" property="fundStatus"/></p></td>
            <td align="center"><p><bean:write name="buildDTO" property="reserved"/></p></td>
          </tr>
          
		</logic:iterate>
        </logic:notEmpty>
		<logic:empty name="buildAF" property="list">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	    </tr>
		
        </logic:empty>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr class="td1"> 
            <td align="center"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr align="center"> 
                  <td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                	<td align="right">  
                 	<jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="buildShowAC.do"/></jsp:include>
                </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
              <logic:notEmpty name="buildAF" property="list">
                <tr>
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.add"/></html:submit>
                  </td> 
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.update"/></html:submit>
                  </td>
                  <td width="70">
                    <html:submit property="method" styleClass="buttona" onclick="return deleteCheck();"><bean:message key="button.delete"/></html:submit>
                  </td>
                  <td width="70">
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.export"/></html:submit>
                  </td>
                  <td width="70">
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.import"/></html:submit>
                  </td>
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.back"/></html:submit>
                  </td>
                </tr>
              </logic:notEmpty>
              <logic:empty name="buildAF" property="list">
                 <tr> 
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.add"/></html:submit>
                  </td> 
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.update"/></html:submit>
                  </td>
                  <td width="70">
                    <html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.delete"/></html:submit>
                  </td>
                  <td width="70">
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.export"/></html:submit>
                  </td>
                  <td width="70">
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.import"/></html:submit>
                  </td>
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.back"/></html:submit>
                  </td>
                </tr>
              
              </logic:empty>
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
