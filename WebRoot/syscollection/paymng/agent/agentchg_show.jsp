<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page import="org.xpup.hafmis.syscollection.paymng.agent.action.AgentChgShowAC"%>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(AgentChgShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>代扣变更</title>
    <link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
  </head>
<script language="javascript">
	var oldColor="#ffffff";                     //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;		//保存当前颜色
		oTD.style.backgroundColor=newColor;		//改变表格颜色
		newColor=oldColor;                 		//改变下次要变成的颜色
	}
</script>
<script>
function init(){
  	for(i=0;i<document.all.length;i++){//固定写法
		if(document.all.item(i).type=="submit"){//如果按钮是提交
			if(document.all.item(i).value=="生成变更"){
				s1=i;
			}
			if(document.all.item(i).value=="撤销变更"){
				s2=i;
			}
			if(document.all.item(i).value=="删除"){
				s3=i;
			}
		}
	}
	var agentSt=document.getElementById(tr).childNodes[9].innerHTML;
	if(agentSt=='已使用'){
  		document.all.item(s1).disabled=true;
  		document.all.item(s2).disabled=false;
  		document.all.item(s3).disabled=true;
	}else{
  		document.all.item(s1).disabled=false;
  		document.all.item(s2).disabled=true;
  		document.all.item(s3).disabled=false;
	}
}
function reportErrors() {
		<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
		</logic:messagesPresent>
}
var tr="tr0"; 
	function gettr(trindex) {
		  tr=trindex;
		  init();
}
function ondelete(){
  var x=confirm("是否要进行删除操作！");
  if(x){
	return true;
  }else{
    return false;
  }
}
function onChg(){
  var x=confirm("是否要进行变更操作！");
  if(x){
	return true;
  }else{
    return false;
  }
}
function onBackChg(){
  var x=confirm("是否要进行撤销变更操作！");
  if(x){
	return true;
  }else{
    return false;
  }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();init();"  onContextmenu="return false">
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
                  <td width="184" align="center" valign="bottom" bgcolor="#FFFFFF" ><font color="#00B5DB" class="font14">生成变更</font></td>
                  <td width="79" class=font14>&nbsp;</td>
                </tr>
            </table></td>
            <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
          </tr>
      </table></td>
    </tr>
    <tr>
      <td class=td3>
      <html:form action="/agentChgFindAC" style="margin: 0">
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
              <td width="17%"   class="td1">代扣批号</td>
              <td width="33%"  ><html:text name="agentChgAF" property="agentHeadId" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="20"></html:text>
              </td>
              <td width="17%"  class="td1">汇缴年月</td>
              <td width="33%" ><html:text name="agentChgAF" property="agentYearMonth" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="6"></html:text></td>
            </tr>
		</table>
		  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right"><input type="submit" name="submit1"  class=buttona value="查询"/></td>
        </tr>
      </table>
      </html:form>
      <html:form action="/agentChgMaintainAC" style="margin: 0">
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
              <td width="10%" class="td2">代扣批号</td>
              <td width="10%" class="td2">
              <a href="javascript:sort('e001.id')">结算号</a>
	          	<logic:equal name="pagination" property="orderBy" value="e001.id">
	          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
	          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
	          	</logic:equal></td>
              </td>
              <td width="10%" class="td2">
              <a href="javascript:sort('e001.agent_year_month')">汇缴年月</a>
	          	<logic:equal name="pagination" property="orderBy" value="e001.agent_year_month">
	          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
	          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
	          	</logic:equal></td>
              </td>
              <td width="7%" class="td2">单位数</td>
              <td width="10%" class="td2">职工数</td>
              <td width="10%" class="td2">单位缴额</td>
              <td width="10%" class="td2">职工缴额</td>
              <td width="10%" class="td2">职工工资</td>
              <td width="10%" class="td2">状态</td>
              <td width="10%" class="td2">结算方式</td>
            </tr>
         <logic:notEmpty name="agentChgAF" property="list"> 
         <logic:iterate id="agentChgListDTO" name="agentChgAF" property="list" indexId="i">
            <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);gettr("tr<%=i%>");'  onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick="">
              <td><label>
                <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="agentChgListDTO" property="agentHeadId"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
              </label></td>
              <td><bean:write name="agentChgListDTO" property="agentHeadId"/></td>
              <td><bean:write name="agentChgListDTO" property="noteNum"/></td>
              <td><a href='<%=path%>/syscollection/paymng/agent/agentOrgpopForwardAC.do?agentHeadId=<bean:write name="agentChgListDTO" property="agentHeadId"/>&agentStatus=<bean:write name="agentChgListDTO" property="status"/>'><bean:write name="agentChgListDTO" property="agentYearMonth"/></a></td>
              <td><bean:write name="agentChgListDTO" property="countOrg"/></td>
              <td><bean:write name="agentChgListDTO" property="countEmp"/></td>
              <td><bean:write name="agentChgListDTO" property="sumAgentOrgPay"/></td>
              <td><bean:write name="agentChgListDTO" property="sumAgentEmpPay"/></td>
              <td><bean:write name="agentChgListDTO" property="sumAgentEmpSalary"/></td>
              <td><bean:write name="agentChgListDTO" property="strStatus"/></td>
              <td><bean:write name="agentChgListDTO" property="payMode"/></td>
            </tr>
            <tr >
              <td colspan="11" class=td5></td>
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
                 	<jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="agentChgShowAC.do"/></jsp:include>
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
                          <td width="70" align="center"><html:submit property="method" styleClass="buttona" onclick="return onChg();"><bean:message key="button.createchange"/></html:submit></td>
						  <td width="70" align="center"><html:submit property="method" styleClass="buttona" onclick="return onBackChg();"><bean:message key="button.backchange"/></html:submit></td>
						  <td width="70" align="center"><html:submit property="method" styleClass="buttona" onclick="return ondelete();"><bean:message key="button.delete"/></html:submit></td>
						</logic:notEmpty>
						<logic:empty name="agentChgAF" property="list">
						  <td width="70" align="center"><html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.createchange"/></html:submit></td>
						  <td width="70" align="center"><html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.backchange"/></html:submit></td>
						  <td width="70" align="center"><html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.delete"/></html:submit></td>
						</logic:empty>
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
