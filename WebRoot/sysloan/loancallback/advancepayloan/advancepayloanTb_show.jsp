<%@ page language="java"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.advancepayloan.action.AdvancepayloanTbShowAC" %>
<%@ include file="/checkUrl.jsp"%>     
<%
   String path = request.getContextPath();
   Object pagination= request.getSession(false).getAttribute(AdvancepayloanTbShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
 %>
<html:html>
<head>
<title>个贷管理</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/picture.js"></script>
</head>

<script type="text/javascript">
function gotoDelete(){
  if(!confirm("确定要删除该信息吗？")){
   return false;
 }
}
function onload(){

	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="onload();">
<jsp:include page="../../../inc/sort.jsp"><jsp:param name="url" value="to_advancepayloanTbShowAC.do"/></jsp:include>
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
                  <td width="112" height="37" background="<%=request.getContextPath()%>/img/buttong.gif" align="center" valign="top"  style="PADDING-top: 7px"><a href="advancepayloanTaShowAC.do" class=a2>业务办理</a></td>
                  <td width="112" height="37" background="<%=request.getContextPath()%>/img/buttonbl.gif" align="center"   style="PADDING-top: 7px" valign="top">业务维护</td>
                </tr>
              </table>
            </td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=request.getContextPath()%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="165" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">申请贷款&gt;特殊申请</font></td>
                  <td width="98" class=font14>&nbsp;</td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="131"><b class="font14">查 询 条 件</b></td>
                  <td width="774" height="22" align="center" background="<%=request.getContextPath()%>/img/bg2.gif">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <html:form action="/advancepayloanTbFindAC.do">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="17%"   class="td1">借款人合同编号</td>
            <td width="33%"  > 
              <html:text name="advancepayloanTbAF" property="contractId" onkeydown="enterNextFocus1();" styleClass="input3"/>
            </td>
            <td width="17%" class="td1" >借款人姓名</td>
            <td width="33%"  > 
              <html:text name="advancepayloanTbAF" property="borrowerName" onkeydown="enterNextFocus1();" styleClass="input3"/>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">证件号码</td>
            <td width="33%" > 
              <html:text name="advancepayloanTbAF" property="cardNum" onkeydown="enterNextFocus1();" styleClass="input3"/>
            </td>
            <td width="17%" class="td1" >状态</td>
            <td width="33%"  > 
              <select name="status" style="width:256px;">
              <option value=""></option>
              <option value="0">未启用</option>
              <option value="1">已启用</option>
            </select>
            </td>
          </tr>
        </table>
        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td align="right"> 
             <html:submit property="method" styleClass="buttona"><bean:message key="button.search" /></html:submit>
            </td>
          </tr>
        </table>
        </html:form>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="131"><b class="font14">特殊申请列表 </b></td>
                  <td width="776" height="22" align="center" background="<%=request.getContextPath()%>/img/bg2.gif">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <html:form action="/advancepayloanTbMainTainAC.do" method="post" style="margin: 0">
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr bgcolor="1BA5FF" > 
            <td align="center" height="6" colspan="1" ></td>
          </tr>
        </table>
        <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
            <td width="13%" align="center" class=td2 >借款人合同编号</td>
            <td width="11%" align="center" class=td2 >
             <a href="javascript:sort('t2.borrower_name')">借款人姓名</a>
             <logic:equal name="pagination" property="orderBy" value="t2.borrower_name">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
            </td>
            <td width="10%" align="center" class=td2 > 证件号码</td>
            <td width="13%" align="center" class=td2 > 
            <a href="javascript:sort('to_number(t1.overplus_limite)')">新剩余期限(月)</a>
             <logic:equal name="pagination" property="orderBy" value="to_number(t1.overplus_limite)">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
            </td>
            <td width="13%" align="center" class=td2 >类型</td>
            <td width="13%" align="center" class=td2 >办理人员</td>
            <td width="13%" align="center" class=td2 > 办理日期</td>
            <td width="8%" align="center" class=td2 >
            <a href="javascript:sort('t1.status')">状态</a>
             <logic:equal name="pagination" property="orderBy" value="t1.status">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
            </td>
          </tr>
           <logic:notEmpty name="advancepayloanTbAF" property="list">
           <% int j=0;
			String strClass="";
		%>
            <logic:iterate id="element" name="advancepayloanTbAF" property="list" indexId="i">
            <%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
                <tr id="tr<%=i%>" onclick='gotoClickpp("<%=i %>", idAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"  onDblClick=""> 
             <td valign="top" >
               <p align="left">
               <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="element" property="id"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
              </p>
            </td>  
            <td><bean:write name="element" property="contractId"/></td>
            <td><bean:write name="element" property="borrowerName"/></td>
            <td><bean:write name="element" property="cardNum"/></td>
            <td><bean:write name="element" property="new_term"/></td>
            <td><bean:write name="element" property="type"/></td>
            <td><bean:write name="element" property="operator"/></td>
            <td><bean:write name="element" property="date"/></td>
            <td><bean:write name="element" property="status"/></td> 
            </tr>
            
            </logic:iterate>
          </logic:notEmpty>
         <logic:empty name="advancepayloanTbAF" property="list">
            <tr> 
             <td colspan="16" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	       </tr>
		  
         </logic:empty>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
          <td align="center">
            <table width="100%" height="19" border="0" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="to_advancepayloanTbShowAC.do"/></jsp:include></td>
              </tr>      
            </table>
          </td>
        </tr>
      </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                <logic:notEmpty name="advancepayloanTbAF" property="list">
                 <td width="70"> 
                    <html:submit property="method" styleClass="buttonb" onclick="return gotoDelete(); "><bean:message key="button.delete"/></html:submit>
                  </td>
                </logic:notEmpty>
                <logic:empty name="advancepayloanTbAF" property="list">
                 <td width="70"> 
                    <html:submit property="method" styleClass="buttonb" disabled="true"><bean:message key="button.delete"/></html:submit>
                  </td>
                </logic:empty>
                 
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

