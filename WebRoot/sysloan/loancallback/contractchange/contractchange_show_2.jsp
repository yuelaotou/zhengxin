<%@ page language="java"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.contractchange.action.ContractchangeShowAC" %>
<%@ include file="/checkUrl.jsp"%>     
<%
   String path = request.getContextPath();
   Object pagination= request.getSession(false).getAttribute(ContractchangeShowAC.PAGINATION_KEY);
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
function gotoPrint(){
}
</script>

<body bgcolor="#FFFFFF" text="#000000">
<jsp:include page="../../../inc/sort.jsp"><jsp:param name="url" value="to_contractchangeShowAC.do"/></jsp:include>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=request.getContextPath()%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td width="235" background="<%=request.getContextPath()%>/img/table_bg_line.gif">&nbsp; </td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=request.getContextPath()%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="165" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>贷款回收</a><font color="00B5DB">&gt;</font><a href="#" class=a1>合同变更</a></td>
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
        <html:form action="/contractchangeFindAC.do">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="12%"   class="td1">借款人合同编号</td>
            <td width="24%"  > 
              <html:text name="contractchangeAF" property="contractId" onkeydown="enterNextFocus1();" styleClass="input3"/>            </td>
            <td width="12%" class="td1" >借款人姓名</td>
            <td colspan="3"  > 
              <html:text name="contractchangeAF" property="borrowerName" onkeydown="enterNextFocus1();" styleClass="input3"/>            </td>
          </tr>
          <tr> 
            <td width="12%"   class="td1">证件号码</td>
            <td width="24%" > 
              <html:text name="contractchangeAF" property="cardNum" onkeydown="enterNextFocus1();" styleClass="input3"/>            </td>
            <td width="12%" class="td1">办理日期</td>
			<td width="10%" > 
              <html:text name="contractchangeAF" property="startDate" onkeydown="enterNextFocus1();" styleClass="input3" maxlength="8"/> 
            </td>
            <td width="4%" class="td1"><div align="center">至</div></td>
            <td width="10%" ><html:text name="contractchangeAF" property="endDate" onkeydown="enterNextFocus1();" styleClass="input3" maxlength="8"/></td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="131"><b class="font14">合同变更列表</b></td>
                  <td width="776" height="22" align="center" background="<%=request.getContextPath()%>/img/bg2.gif">&nbsp;</td>
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
        <html:form action="/contractchangeMaintainAC.do" method="post" style="margin: 0">
        <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" ></td>
            <td  align="center" class=td2 >借款人合同编号</td>
            <td  align="center" class=td2 >
             <a href="javascript:sort('t2.borrower_name')">借款人姓名</a>
             <logic:equal name="pagination" property="orderBy" value="t2.borrower_name">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
            </td>
            <td  align="center" class=td2 > 证件号码</td>
            <td  align="center" class=td2 > 
             <a href="javascript:sort('to_number(t1.deadline)')">新剩余期限(月)</a>
             <logic:equal name="pagination" property="orderBy" value="to_number(t1.deadline)">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
            </td>
            <td  align="center" class=td2 >类型</td>
            <td  align="center" class=td2 >提前还款金额</td>
            <td  align="center" class=td2 >剩余本金</td>
            <td  align="center" class=td2 >是否正常</td>
            <td  align="center" class=td2 >办理日期</td>
          </tr>
          <logic:notEmpty name="contractchangeAF" property="list">
          <% int j=0;
			String strClass="";
		%>
            <logic:iterate id="element" name="contractchangeAF" property="list" indexId="i">
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
            <td ><a href="#" onClick="window.open('loancallbackTbForwardURLWindowAC.do?contractId=<bean:write name="element" property="contractId" />&headId=<bean:write name="element" property="flowid"/>&type=3','','width=1000,height=600,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,location=no, status=no');">
          <bean:write name="element" property="contractId" /></a></td>
            <td><bean:write name="element" property="borrowerName"/></td>
            <td><bean:write name="element" property="cardNum"/></td>
            <td><bean:write name="element" property="new_term"/></td>
            <td><bean:write name="element" property="type"/></td>
            <td><bean:write name="element" property="money_1"/></td>
            <td><bean:write name="element" property="money_2"/></td>
            <td><bean:write name="element" property="mark_a"/></td>
            <td><bean:write name="element" property="date"/></td> 
            </tr>
           
            </logic:iterate>
          </logic:notEmpty>
         <logic:empty name="contractchangeAF" property="list">
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
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="to_contractchangeShowAC.do"/></jsp:include></td>
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
                <logic:notEmpty name="contractchangeAF" property="list">
                 <td width="70"> 
                    <html:submit property="method" styleClass="buttonb"><bean:message key="button.approval.pass"/></html:submit>
                  </td>
                </logic:notEmpty>
                <logic:empty name="contractchangeAF" property="list">
                 <td width="70"> 
                    <html:submit property="method" styleClass="buttonb" disabled="true"><bean:message key="button.approval.pass"/></html:submit>
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

