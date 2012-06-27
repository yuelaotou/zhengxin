<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>

<%
	String path = request.getContextPath();
%>
<%@ page import="org.xpup.hafmis.syscollection.paymng.agent.action.AgentInfoQueryTaShowAC" %>

<%
   Object pagination= request.getSession(false).getAttribute(AgentInfoQueryTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>代扣信息查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path %>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path %>/js/common.js"></script>

<script type="text/javascript">
	function checkQuery(){
		document.getElementById("method").value="queryOrgInfo";
		document.forms[1].submit();
	}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
		
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
			                  <td width="184" align="center" valign="bottom" bgcolor="#FFFFFF" ><font color="#00B5DB" class="font14">代扣信息查询</font></td>
			                  <td width="79" class=font14>&nbsp;</td>
			                </tr>
			            </table></td>
			            <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
			          </tr>
			      </table></td>
			    </tr>
			    <tr>
			      <td class=td3><table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
			          <tr>
			            <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			                <tr>
			                  <td height="22" bgcolor="#CCCCCC" align="center" width="118"><b class="font14">查 询 条 件</b></td>
			                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="549">&nbsp;</td>
			                </tr>
			            </table></td>
			          </tr>
			        </table>
			        
			      <html:form action="/agentInfoQueryTaFindAC.do" style="margin: 0">
			          <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
			            <tr>
			              <td width="17%"   class="td1">代扣批号</td>
			              <td>
			              <html:text name="agentInfoQueryTaAF" property="payId"  styleClass="input3" styleId="txtsearch" onkeydown="enterNextFocus1();"></html:text>
			              </td>
			              <td width="17%"  class="td1">汇缴年月</td>
			              <td>
			              <html:text name="agentInfoQueryTaAF" property="payMonth"  styleClass="input3" styleId="txtsearch" onkeydown="enterNextFocus1();"></html:text>
			              </td>
			            </tr>
					</table>
					  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
			        <tr>
			          <td align="right">
			          	<html:submit styleClass="buttona"><bean:message key="button.search" /></html:submit>
			          </td>
			        </tr>
			      </table>
			      </html:form>
			      <html:form action="/agentInfoQueryTaModifyAC.do" style="margin: 0">
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
			              <td width="4%" class="td1">&nbsp;</td>
			              <td width="13%" class="td2">代扣批号</td>
			              <td width="13%" class="td2">
			              		<a href="javascript:sort('min(a301.note_num)')">结算号</a>
			              		<logic:equal name="pagination" property="orderBy" value="min(a301.note_num)">
					          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
					          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
					          	</logic:equal>
			              </td>
			              <td width="13%" class="td2">
			              		<a href="javascript:sort('min(a302.pay_month)')">汇缴年月</a>
			              		<logic:equal name="pagination" property="orderBy" value="min(a302.pay_month)">
					          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
					          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
					          	</logic:equal>
			              </td>
			              <td width="8%" class="td2">单位数</td>
			              <td width="13%" class="td2">职工数</td>
			              <td width="13%" class="td2">单位缴额</td>
			              <td width="13%" class="td2">职工缴额</td>
			              <td width="13%" class="td2">结算方式</td>
			            </tr>
			            <logic:notEmpty name="agentInfoQueryTaAF" property="list">
						<logic:iterate name="agentInfoQueryTaAF" property="list" id="element" indexId="i">
				        <tr id="tr<%=i%>" onClick='gotoClick("tr<%=i %>","s<%=i%>", idAF);' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick=""> 
				           <td valign="middle">
				          <p align="center">
				            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="element" property="payId"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %> />
				          </p>
				          </td>
				          <td valign="middle"><p align="center"><bean:write name="element" property="payId" format="000000"/></p></td>
				          <td valign="middle"><p align="center"><bean:write name="element" property="noteNum" /></p></td>
				          <td valign="middle"><p align="center"><bean:write name="element" property="payMonth" /></p></td>
				          <td valign="middle"><p align="center"><bean:write name="element" property="orgIdSum" /></p></td>
				          <td valign="middle"><p align="center"><bean:write name="element" property="empIdSum" /></p></td>
				          <td valign="middle"><p align="center"><bean:write name="element" property="orgRealPaySum" /></p></td>         
				          <td valign="middle"><p align="center"><bean:write name="element" property="empRealPaySum" /></p></td>
				          <td valign="middle"><p align="center"><bean:write name="element" property="payMode" /></p></td>
				           </tr>
				        <tr > 
				          <td colspan="11" class=td5 ></td>
				        </tr>
				        </logic:iterate>
				        </logic:notEmpty>
				        <logic:empty name="agentInfoQueryTaAF"  property="list">
				        <tr> 
				          <td colspan="11" height="30" style="color:red">没有找到与条件相符合的结果！</td>
					    </tr>
						<tr > 
				          <td colspan="11" ></td>
				        </tr>
				        </logic:empty>
				        <tr > 
				          <td colspan="9"  ></td>
				        </tr>
			        </table>
			        
			        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
				        <tr class="td1"> 
						  <td>
				            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				              <tr> 
							  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
				                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="agentInfoQueryTaShowAC.do"/></jsp:include></td>
				              </tr>
				          </table></td>
					    </tr>
				      </table>
			        
			        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
			         <input type="hidden" name="method" id="method" value="" />
			            <tr valign="bottom">
			              <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"><table border="0" cellspacing="0" cellpadding="0">
			                  <tr>
			                    <td><table border="0" cellpadding="0" cellspacing="0">
			                        <tr>
			                          <td>
							               <logic:notEmpty name="agentInfoQueryTaAF" property="list">
							              		 <html:button property="method" styleClass="buttonc"  onclick="return checkQuery();">单位信息查询</html:button>
							               </logic:notEmpty>
							               <logic:empty name="agentInfoQueryTaAF" property="list">
							               		<html:button property="method" styleClass="buttonc" disabled="true">单位信息查询</html:button>
							               </logic:empty>							               
						               </td> 
			                        </tr>
			                    </table></td>
			                  </tr>
			              </table></td>
			            </tr>
			        </table></html:form></td>
			    </tr>
			  </table>

</body>

</html>