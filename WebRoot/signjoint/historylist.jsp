<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security" %>
<%@ page import="org.xpup.hafmis.signjoint.action.ShowSignHistoryAC" %>
<%
   String path = request.getContextPath();
%>
<%
   Object pagination= request.getSession(false).getAttribute(ShowSignHistoryAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
 %>
<html:html>
<head>
<title>批量签约</title>
<link rel="stylesheet" href="../css/index.css" type="text/css">
<script language="javascript" src="../js/common.js"></script>
</head>
<script language="javascript"  type="text/javascript" >
function toPop() {
	gotoOrgpop('','<%=path%>','0');
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function displays(id,name){
  document.signAF.elements["orgid"].value=id;
  document.signAF.elements["orgname"].value=name;
  showlist() 
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode=9;
		executeAjax();
	}
}
function showlist() {
  document.Form1.submit();
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()">
<jsp:include page="../inc/sort.jsp"><jsp:param name="url" value="showHistoryListAC.do"/></jsp:include>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>     
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="../img/table_left.gif" width="7" height="37"></td>
          <td background="../img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="../img/table_bg_line.gif"><br></td>
          <td background="../img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="../img/title_banner.gif" width="37" height="24"></td>
                <td width="189" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>联名卡</a><font color="00B5DB">&gt;</font><a href="#" class=a1>批量签约</a></td>
                <td width="74" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="../img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
    <td class=td3 >
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="190"><b class="font14">查 询 条 件</b></td>
                <td height="22" background="../img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>  
         
      <html:form  action="/showHistoryListAC.do"  style="margin: 0">  
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  algin="center">单位编号：</td>
             <td width="33%" >
              <html:text name="historyQueryAF" property="orgid"  styleClass="input3" onkeydown="gotoEnter();" styleId="txtsearch" />
            </td>
            <td class="td1" width="17%" algin="center">单位名称：</td>
            <td width="33%"  > 
              <html:text name="historyQueryAF" property="orgname"  styleClass="input3" styleId="txtsearch" readonly="true"/>
            </td>
          </tr>
           <tr> 
            <td width="17%" class="td1"  algin="center">职工编号：</td>
             <td width="33%" >
              <html:text name="historyQueryAF" property="empid"  styleClass="input3" onkeydown="gotoEnter();" styleId="txtsearch"/>
            </td>
            <td class="td1" width="17%" algin="center">职工姓名：</td>
            <td width="33%"  > 
              <html:text name="historyQueryAF" property="empname"  styleClass="input3" styleId="txtsearch" readonly="true"/>
            </td>
          </tr>
          </table>
          
          <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  algin="center">办理日期：</td>
             <td width="15%" >
              <html:text name="historyQueryAF" property="transactdatastart"  styleClass="input3" onkeydown="gotoEnter();" styleId="txtsearch" />
            </td>
             <td width="3%">至</td>
             <td width="15%" >
              <html:text name="historyQueryAF" property="transactdataend"  styleClass="input3" onkeydown="gotoEnter();" styleId="txtsearch" />
            </td>
            <td class="td1" width="17%" algin="center">银行确认时间：</td>
            <td width="15%" >
              <html:text name="historyQueryAF" property="affirmdatastart"  styleClass="input3" styleId="txtsearch" />
            </td>
            <td width="3%">至</td>
             <td width="15%"  > 
              <html:text name="historyQueryAF" property="affirmdataend"  styleClass="input3" styleId="txtsearch" />
            </td>
          </tr>
          </table>
          
          <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr>
            <td class="td1" width="17%" >是否成功：</td>
            <td colspan="3" width="33%" >
            <html:select property="isucceed" style="width:100%">
               <html:option value="2">全部</html:option>
               <html:option value="0">成功</html:option>
               <html:option value="1">失败</html:option>
            </html:select>
            </td>
            <td class="td1" width="17%" >&nbsp;</td>
            <td colspan="3" width="33%" >&nbsp;</td>
          </tr>
          </table>
          
          <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr >
			<td align="right"><html:submit  styleClass="buttona" >查询</html:submit></td>
	      </tr>
	      </table>
        </html:form>
        
       <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="154"><b class="font14">职 工 列 表</b></td>
                  <td width="750" height="22" align="center" background="../img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr> 
      </table> 
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF">
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
      
      
 <html:form  action="/signMaintainAC.do" style="margin: 0">
 
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr > 
          <td align="center" height="23" bgcolor="C4F0FF" ></td>
          <td align="center" class=td2 >
          	<a href="javascript:sort('orgid')">单位编号</a>
          	<logic:equal name="pagination" property="orderBy" value="orgid">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>
          <td align="center" class=td2 >
          	<a href="javascript:sort('orgname')">单位名称</a>
          	<logic:equal name="pagination" property="orderBy" value="orgname">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>
           <td align="center" class=td2 >
          	<a href="javascript:sort('empid')">职工编号</a>
          	<logic:equal name="pagination" property="orderBy" value="empid">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>   
           <td align="center" class=td2 >
          	<a href="javascript:sort('empname')">职工姓名</a>
          	<logic:equal name="pagination" property="orderBy" value="empname">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>             
          <td align="center" class=td2 >身份证号</td>  
          <td align="center" class=td2 >银行卡号</td>
          <td align="center" class=td2 >唯一标识</td>
          <td align="center" class=td2 >办理时间</td>
          <td align="center" class=td2 >办理人员</td>
          <td align="center" class=td2 >银行确认时间</td>
          <td align="center" class=td2 >是否成功</td>
        </tr>
		<logic:notEmpty name="historyQueryAF" property="list">
		<logic:iterate name="historyQueryAF" property="list" id="element" indexId="i">
	
        <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>",idAF);' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4 > 
  
          <td  >
            <p >
              <input id="s<%=i%>" type="radio" name="id" value="<bean:write name="element" property="id"/>"  <%if(new Integer(0).equals(i)) out.print("checked"); %>/>
            </p>
          </td >
          <td  align="center"><p><bean:write name="element" property="orgid" /></p></td>
          <td  align="center"><p><bean:write name="element" property="orgname" /></p></td>
          <td  align="center"><p><bean:write name="element" property="empid" /></p></td>
          <td  align="center"><p><bean:write name="element" property="empname" /></p></td>
          <td  align="center"><p><bean:write name="element" property="cardnum" /></p></td>
          <td  align="center"><p><bean:write name="element" property="bankcardid" /></p></td>
          <td  align="center"><p><bean:write name="element" property="sign" /></p></td>
          <td  align="center"><p><bean:write name="element" property="biz_date" /></p></td>
          <td  align="center"><p><bean:write name="element" property="operater" /></p></td>
          <td  align="center"><p><bean:write name="element" property="bank_sure_date" /></p></td>
          <td  align="center"><p><bean:write name="element" property="succ_fail" /></p></td>
        </tr>
        <tr> 
          <td colspan="15" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
      <logic:empty name="historyQueryAF" property="list">
        <tr> 
          <td colspan="9" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="9" class=td5 ></td>
        </tr>
        </logic:empty>  
      </table>

      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../inc/pagination.jsp"><jsp:param name="url" value="showHistoryListAC.do"/></jsp:include></td>
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
			    <td><html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.print"/></html:submit></td>
			  </tr>
            </table>
           </td>
        </tr>
      </table>
</html:form>
     <form action="showHistoryListAC.do" method="POST" name="Form1" id="Form1">
    </form>
    </td>
    </tr>
</table>
</html:html>
