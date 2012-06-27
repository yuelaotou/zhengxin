<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page import="org.xpup.hafmis.sysfinance.systemmaintain.queryoperationlog.action.QueryOperationLogShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(QueryOperationLogShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>业务日志查询</title>
    <link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
    <script src="<%=path%>/js/common.js"></script>
  </head>
  <script>
  var tr="tr0";
  function gettr(trindex) {
		  tr=trindex;
  }
  function gotoShow(){
	return false;
  }
  function check(){
	  var credenceDateStart = document.queryOperationLogAF.elements["queryOperationLogFindDTO.credenceDateStart"].value.trim();
	  var credenceDateEnd = document.queryOperationLogAF.elements["queryOperationLogFindDTO.credenceDateEnd"].value.trim();
	  if(credenceDateStart==''||credenceDateEnd==''){
	  	alert('请输入查询的日期段！');
	  	return false;
	  }else{
	  	if(checkDate(document.queryOperationLogAF.elements["queryOperationLogFindDTO.credenceDateStart"])&&checkDate(document.queryOperationLogAF.elements["queryOperationLogFindDTO.credenceDateEnd"])){
	  	return true;
	  	}else{
	  	return false;
	  	}
	  }
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
            <td background="<%=path%>/img/table_bg_line.gif"></td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="184" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>统计查询</a><font color="00B5DB">&gt;</font><a href="#" class=a1>业务活动日志查询</a></td>
                <td width="79" class=font14>&nbsp;</td>
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
    <html:form action="/queryOperationLogFindAC">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="153"><b class="font14">业务活动日志查询信息</b></td>
                <td width="447" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
	  <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
		  <tr> 
            <td width="17%"   class="td1">业务类型</td>
            <td width="33%" colspan="3" >  
            <html:select name="queryOperationLogAF" property="queryOperationLogFindDTO.credenceType" onkeydown="enterNextFocus1();" styleClass="input4">
            	<html:option value=""></html:option>
            	<html:optionsCollection property="credenceTypeMap" name="queryOperationLogAF" label="value" value="key"/>
            </html:select>     
             </td>
           <td width="17%" class="td1" >操作员</td>
            <td width="33%" colspan="3"  > 
             <html:select name="queryOperationLogAF" property="queryOperationLogFindDTO.operator" styleClass="input4" onkeydown="enterNextFocus1();">
			<html:option value=""></html:option>
              <html:options  collection="operList1" property="value" labelProperty="label"/>
            </html:select>	           </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">起止日期<font color="#FF0000d" >*</font></td>
            <td width="15%"  > 
              <html:text name="queryOperationLogAF" property="queryOperationLogFindDTO.credenceDateStart" styleClass="input3" maxlength="8" onkeydown="enterNextFocus1();"/>            </td>
            <td width="3%" align="center"  >至</td>
            <td width="15%"  >
              <html:text name="queryOperationLogAF" property="queryOperationLogFindDTO.credenceDateEnd" styleClass="input3" maxlength="8" onkeydown="enterNextFocus1();"/>            </td>
            <td width="17%" class="td1" >动作</td>
            <td width="33%" colspan="3" > 
             <html:select name="queryOperationLogAF" property="queryOperationLogFindDTO.action" onkeydown="enterNextFocus1();" styleClass="input4">
            	<html:option value=""></html:option>
            	<html:optionsCollection property="actionMap" name="queryOperationLogAF" label="value" value="key"/>
            </html:select>            </td>
          </tr>
         
        </table>
      
	  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right"><input type="submit" name="submit1"  class=buttona value="查询" onclick="return check();"/></td>
        </tr>
      </table>
      </html:form>
      <html:form action="/queryOperationLogMainTainAC">
       <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="193"><b class="font14">日志信息列表</b></td>
                <td width="407" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
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
        <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" 
        	align="center">
          <tr align="center" > 
            <td class="td2">
            	<a href="javascript:sort('f310.bizactivity_log_id')">业务编号</a>
				<logic:equal name="pagination" property="orderBy"
					value="f310.bizactivity_log_id">
					<logic:equal name="pagination" property="orderother"
						value="ASC">▲</logic:equal>
					<logic:equal name="pagination" property="orderother"
						value="DESC">▼</logic:equal>
				</logic:equal>
            </td>
	   		<td class="td2">
	   		<a href="javascript:sort('f310.credence_type')">业务类型</a>
				<logic:equal name="pagination" property="orderBy"
					value="f310.credence_type">
					<logic:equal name="pagination" property="orderother"
						value="ASC">▲</logic:equal>
					<logic:equal name="pagination" property="orderother"
						value="DESC">▼</logic:equal>
				</logic:equal>
	   		</td>
            <td class="td2">
				<a href="javascript:sort('f310.action')">动作</a>
				<logic:equal name="pagination" property="orderBy"
					value="f310.action">
					<logic:equal name="pagination" property="orderother"
						value="ASC">▲</logic:equal>
					<logic:equal name="pagination" property="orderother"
						value="DESC">▼</logic:equal>
				</logic:equal>
			</td>
            <td class="td2">操作时间</td>
            <td class="td2">
			<a href="javascript:sort('f310.operator')">操作员</a>
				<logic:equal name="pagination" property="orderBy"
					value="f310.operator">
					<logic:equal name="pagination" property="orderother"
						value="ASC">▲</logic:equal>
					<logic:equal name="pagination" property="orderother"
						value="DESC">▼</logic:equal>
				</logic:equal>
			</td>
          	</tr>
	        <logic:notEmpty name="queryOperationLogAF" property="list">
	        <%
	  		int j=0;
	  		String strClass="";
	  		%>
			<logic:iterate name="queryOperationLogAF" property="list" id="e" indexId="i">
			<%	j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }%>
          	<tr class="<%=strClass %>"> 
            <td valign="top"><p><bean:write name="e" property="bizId" /></p></td>
            <td valign="top">
            <p><a href="#" onClick="window.open('credencePopShowAC.do?docNum=<bean:write name="e" property="credenceCharacterNum"/>&credenceDate=<bean:write name="e" property="credenceDate"/>&office=<bean:write name="e" property="office"/>','','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return gotoShow();"><bean:write name="e" property="bizType"/></a></p>
            </td>
            <td valign="top"><p><bean:write name="e" property="action" /></p></td>
            <td valign="top"><p><bean:write name="e" property="opTime" /></p></td>
            <td valign="top"><p><bean:write name="e" property="operator" /></p></td>
          </tr>
		</logic:iterate>
		</logic:notEmpty>
		<logic:empty name="queryOperationLogAF" property="list">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	    </tr>
        </logic:empty>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
          <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                	<td align="right">  
                 	<jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="queryOperationLogShowAC.do"/></jsp:include>
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
              <tr> 
                <td><html:submit property="method" styleClass="buttona">
						<bean:message key="button.print" />
					</html:submit></td>
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
