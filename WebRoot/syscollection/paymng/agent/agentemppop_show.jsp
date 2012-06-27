<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.syscollection.paymng.agent.action.AgentEmppopShowAC" %>
<%
   Object pagination= request.getSession(false).getAttribute(AgentEmppopShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path = request.getContextPath();
 %>
<html>
  <head>
    <title>代扣变更-职工明细</title>
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
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();" onContextmenu="return false">
<jsp:include page="../../../inc/sort.jsp"><jsp:param name="url" value="agentEmppopShowAC.do"/></jsp:include>
  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
      <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
            <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td background="<%=path%>/img/table_bg_line.gif"></td>
            <td background="<%=path%>/img/table_bg_line.gif" align="right"><table width="300" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="184" align="center" valign="bottom" bgcolor="#FFFFFF" ><font color="#00B5DB" class="font14">生成变更&gt;职工明细</font></td>
                  <td width="79" class=font14>&nbsp;</td>
                </tr>
            </table></td>
            <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
          </tr>
      </table></td>
    </tr>
    <tr>
      <td class=td3>
      <html:form action="/agentEmppopFindAC" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="22" bgcolor="#CCCCCC" align="center" width="118"><b class="font14">查 询 条 件</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="549">&nbsp;</td>
                </tr>
            </table></td>
          </tr>
        </table>
          <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
            <tr>
              <td width="17%"   class="td1">职工编号</td>
              <td width="33%"  >
              	<html:text name="agentChgAF" property="agentEmppopId" styleClass="input3"  styleId="txtsearch" > </html:text>
              </td>
              <td width="17%"  class="td1">职工姓名</td>
              <td width="33%" >
              	<html:text name="agentChgAF" property="agentEmppopname" styleClass="input3"  styleId="txtsearch" > </html:text>
              </td>
            </tr>
            <tr>
              <td width="17%"   class="td1">职工代扣号</td>
              <td width="33%"  >
              	<html:text name="agentChgAF" property="agentEmppopkouCode" styleClass="input3"  styleId="txtsearch" > </html:text>
              </td>
              <td width="17%"  class="td1">职工身份证号</td>
              <td width="33%" >
              	<html:text name="agentChgAF" property="agentEmppopCardID" styleClass="input3"  styleId="txtsearch" > </html:text>
              </td>
            </tr>
		</table>
		  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right">
              <html:submit property="method" styleClass="buttona" onclick="search()"><bean:message key="button.search"/></html:submit>
          </td>
        </tr>
      </table>
      </html:form>
      <html:form action="/agentEmppopMaintainAC" style="margin: 0">
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
            <tr>
              <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="22" bgcolor="#CCCCCC" align="center" width="172"><b class="font14">财 政 代 扣 列 表</b></td>
                    <td width="768" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
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
              <td width="5%" class="td1">&nbsp;</td>
              <td width="10%" class="td2">职工编号</td>
              <td width="10%" class="td2">职工姓名</td>
              <td width="10%" class="td2">职工代扣号</td>
              <td width="10%" class="td2">身份证号</td>
              <td width="10%" class="td2">单位缴额</td>
              <td width="10%" class="td2">职工缴额</td>
              <td width="10%" class="td2">职工工资</td>
            </tr>
         <logic:notEmpty name="agentChgAF" property="emplist"> 
         <logic:iterate id="agentChgListDTO" name="agentChgAF" property="emplist" indexId="i">
            <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);gettr("tr<%=i%>");'  onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick="">
              <td><label>
                <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="agentChgListDTO" property="empAgentId"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
              </label></td>
              <td><bean:write name="agentChgListDTO" property="agentEmppopId"/></td>
              <td><bean:write name="agentChgListDTO" property="agentEmppopname"/></td>
              <td><bean:write name="agentChgListDTO" property="agentEmppopkouCode"/></td>
              <td><bean:write name="agentChgListDTO" property="agentEmppopCardID"/></td>
              <td><bean:write name="agentChgListDTO" property="agentEmppoporgpay"/></td>
              <td><bean:write name="agentChgListDTO" property="agentEmppopemppay"/></td>
              <td><bean:write name="agentChgListDTO" property="agentEmppopmonthsalary"/></td>
            </tr>
            <tr >
              <td colspan="10" class=td5></td>
            </tr>
            </logic:iterate>
            </logic:notEmpty>
        <logic:empty name="agentChgAF" property="emplist">
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
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="agentEmppopShowAC.do"/></jsp:include></td>
              </tr>
          </table></td>
	    </tr>
      </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
            <tr valign="bottom">
              <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"><table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                        <logic:notEmpty name="agentChgAF" property="emplist">
                        <logic:notEmpty name="agentChgAF" property="agentStatus">
                        <td width="70" align="center"><html:submit property="method" styleClass="buttona" onclick="return ondelete();"><bean:message key="button.delete"/></html:submit></td>
                        </logic:notEmpty>
                        <logic:empty name="agentChgAF" property="agentStatus">
                        <td width="70" align="center"><html:submit property="method" styleClass="buttona" onclick="return ondelete();" disabled="true"><bean:message key="button.delete"/></html:submit></td>
                        </logic:empty>
                        </logic:notEmpty>
                        <logic:empty name="agentChgAF" property="emplist">
                        <td width="70" align="center"><html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.delete"/></html:submit></td>
                        </logic:empty>
						  <td width="70" align="center"><html:submit property="method" styleClass="buttona"><bean:message key="button.back"/></html:submit></td>
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
</html>
