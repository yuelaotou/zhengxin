<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page import="org.xpup.hafmis.syscollection.paymng.agent.action.AgentOrgpopShowAC"%>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(AgentOrgpopShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
<title>公积金利率维护</title>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>
<script>
function reportErrors() {
		<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
		</logic:messagesPresent>
}
var tr="tr0"; 
	function gettr(trindex) {
		  tr=trindex;
}
function ondelete(){
  var x=confirm("是否要进行删除操作！");
  if(x){
	return true;
  }else{
    return false;
  }
}
function ondeleteAll(){
  var x=confirm("是否要进行全部删除操作！");
  if(x){
	return true;
  }else{
    return false;
  }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
            <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td background="<%=path%>/img/table_bg_line.gif"><a href="贷款申请_下达发放通知书_简约风格.html"></a></td>
            <td background="<%=path%>/img/table_bg_line.gif" align="right"><table width="300" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="184" align="center" valign="bottom" bgcolor="#FFFFFF" ><FONT class=font14 color=#00b5db>生成变更</FONT></td>
                  <td width="79" class=font14>&nbsp;</td>
                </tr>
            </table></td>
            <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
          </tr>
      </table></td>
    </tr>
    <tr>
      <td class=td3>
      <html:form action="/agentOrgpopFindAC" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="22" bgcolor="#CCCCCC" align="center" width="118"><b class="font14">财 政 代 扣</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="549">&nbsp;</td>
                </tr>
            </table></td>
          </tr>
        </table>
          <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
            <tr>
              <td width="14%" class="td1">单位编号</td>
              <td width="29%"  ><html:text name="agentChgAF" property="agentOrgpopId" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="20"></html:text></td>
              <td width="13%" class="td1"  >单位代扣号</td>
              <td width="37%" ><html:text name="agentChgAF" property="agentOrgpopkouCode" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="20"></html:text></td>
            </tr>
			<tr>
              <td colspan="3">&nbsp;</td>
              <td width="7%" align="right"><input type="submit" name="submit1"  class=buttona value="查询"/></td>
			</tr>
          </table>
		 </html:form>
		 <html:form action="/agentOrgpopMaintainAC" style="margin: 0">
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td class=h4>合计：单位数<u>：<bean:write name="agentChgAF" property="orgCount"/></u>
            				单位缴额<u> ：<bean:write name="agentChgAF" property="sumAgentOrgPay"/> </u>
            				职工缴额<u> ：<bean:write name="agentChgAF" property="sumAgentEmpPay"/> </u>
            				工资总额<u> ：<bean:write name="agentChgAF" property="sumAgentEmpSalary"/></u>
            </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
            <tr>
              <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="22" bgcolor="#CCCCCC" align="center" width="165"><b class="font14">财 政 代 扣 列 表</b></td>
                  <td width="775" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
                </tr>
              </table></td>
            </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
            <tr bgcolor="1BA5FF" >
              <td align="center" height="6" colspan="1" ></td>
            </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
            <tr align="center" >
              <td width="4%" class="td1" >&nbsp;</td>
              <td width="14%" class="td2">
              <a href="javascript:sort('a001.id')">单位编号</a>
	          	<logic:equal name="pagination" property="orderBy" value="a001.id">
	          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
	          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
	          	</logic:equal></td>
              </td>
              <td width="14%" class="td2">
              <a href="javascript:sort('e002.org_agent_num')">单位代扣号</a>
	          	<logic:equal name="pagination" property="orderBy" value="e002.org_agent_num">
	          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
	          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
	          	</logic:equal></td>
              </td>
              <td width="23%" class="td2">单位名称</td>
              <td width="5%" class="td2">人数</td>
              <td width="12%" class="td2">单位总缴额</td>
              <td width="14%" class="td2">职工总缴额</td>
              <td width="14%" class="td2">职工总工资</td>
            </tr>
          <logic:notEmpty name="agentChgAF" property="list"> 
         <logic:iterate id="agentInfoDTO" name="agentChgAF" property="list" indexId="i">
            <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);gettr("tr<%=i%>");'  onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick="">
              <td>
              <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="agentInfoDTO" property="orgAgentId"/>,<bean:write name="agentChgAF" property="agentHeadId"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
              </td>
              <td><bean:write name="agentInfoDTO" property="orgId"/></td>
              <td><bean:write name="agentInfoDTO" property="orgAgentNum"/></td>
              <td><a href='<%=path%>/syscollection/paymng/agent/agentEmppopForwardAC.do?orgAgentId=<bean:write name="agentInfoDTO" property="orgAgentId"/>&orgId=<bean:write name="agentInfoDTO" property="orgId"/>&payMode=<bean:write name="agentInfoDTO" property="payMode"/>'><bean:write name="agentInfoDTO" property="orgName"/></a></td>
              <td><bean:write name="agentInfoDTO" property="countEmpId"/></td>
              <td><bean:write name="agentInfoDTO" property="sumAgentOrgPay"/></td>
              <td><bean:write name="agentInfoDTO" property="sumAgentEmpPay"/></td>
              <td><bean:write name="agentInfoDTO" property="sumAgentEmpSalary"/></td>
            </tr>
            <tr >
              <td colspan="8" class=td5></td>
            </tr>
            </logic:iterate>
            </logic:notEmpty>
            <logic:empty name="agentChgAF" property="list">
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
          <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                	<td align="right">  
                 	<jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="agentOrgpopShowAC.do"/></jsp:include>
                </td>
                </tr>
              </table>
          </td>
        </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
            <tr valign="bottom">
              <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"><table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                        <logic:notEmpty name="agentChgAF" property="list">
                        <logic:notEmpty name="agentChgAF" property="agentStatus">
                          <td width="70"><html:submit property="method" styleClass="buttona" onclick="return ondeleteAll();"><bean:message key="button.delete"/></html:submit></td>
                          <td width="70"><html:submit property="method" styleClass="buttona" onclick="return ondelete();"><bean:message key="button.deleteall"/></html:submit></td>
                         </logic:notEmpty>
                         <logic:empty name="agentChgAF" property="agentStatus">
                          <td width="70"><html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.delete"/></html:submit></td>
                          <td width="70"><html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.deleteall"/></html:submit></td>
                         </logic:empty>
						  </logic:notEmpty>
						  <logic:empty name="agentChgAF" property="list">
						  <td width="70"><html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.delete"/></html:submit></td>
                          <td width="70"><html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.deleteall"/></html:submit></td>
						  </logic:empty>
						  <td width="70"><html:submit property="method" styleClass="buttona"><bean:message key="button.back"/></html:submit></td>
                        </tr>
                    </table></td>
                  </tr>
              </table></td>
            </tr>
        </table>
        </html:form>
        </td>
    </tr>
  </table>
</body>
</html:html>

