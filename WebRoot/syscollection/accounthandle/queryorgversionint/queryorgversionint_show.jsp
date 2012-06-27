<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.queryorgversionint.action.QueryorgversionintShowAC"%>
<%
Object pagination = request.getSession(false).getAttribute(
			QueryorgversionintShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
String path = request.getContextPath();
%>
<html:html>
<head>
<title>单位版利息查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>
<script type="text/javascript">
function check(){
}
function load(){
 document.all.clearInterestType.value="";
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="load();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td background="<%=path%>/img/table_bg_line.gif"></td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"><table width="300" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
              <td width="184" align="center" valign="bottom" bgcolor="#FFFFFF" ><span class="font14"><font color="#00B5DB">利息查询</font></span></td>
              <td width="79" class=font14>&nbsp;</td>
            </tr>
          </table></td>
          <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="118"><b class="font14">查 询 条 件</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="549">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <html:form action="/queryorgversionintFindAC.do" style="margin: 0">
		<table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
		   <tr> 
            <td width="17%"   class="td1">单位编号</td>
            <td width="33%"  ><html:text name="queryorgversionintAF" property="orgId" styleClass="input3" styleId="txtsearch" /></td>
            <td width="17%" class="td1">单位名称</td>
            <td width="33%" ><html:text name="queryorgversionintAF" property="orgName" styleClass="input3" styleId="txtsearch" /></td>
          </tr>
		  <tr> 
            <td width="17%"   class="td1">职工编号</td>
            <td width="33%"  ><html:text name="queryorgversionintAF" property="empId" styleClass="input3" styleId="txtsearch" /></td>
            <td width="17%" class="td1">职工姓名</td>
            <td width="33%" ><html:text name="queryorgversionintAF" property="empName" styleClass="input3" styleId="txtsearch" /></td>
          </tr>
		  <tr> 
            <td width="17%"   class="td1">结息类型</td>
            <td width="33%"  ><html:select property="clearInterestType" styleClass="input4" >
                    <html:option value=""></html:option>
                    <html:optionsCollection property="map"  name="queryorgversionintAF" label="value" value="key"/>
                   </html:select></td>
            <td width="17%">&nbsp;</td>
            <td width="33%" >&nbsp;</td>
		  </tr>
        </table>
        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td align="right"> 
            <html:submit property="method" styleClass="buttona" onclick="return check();">
            <bean:message key="button.search"/>
            </html:submit>
          </td>
        </tr>
      </table>
        </html:form>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="158"><strong class="font14">结息情况列表</strong></td>
                <td width="766" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>          </td>
        </tr>
      </table>
      <html:form action="/queryorgversionintMaintainAC.do" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" >  
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          
          <tr align="center" >
            <td width="5%" class="td2">&nbsp;</td>
            <td width="15%" class="td2">职工编号</td>
            <td width="15%" class="td2">职工姓名</td>
            <td width="15%" class="td2">结息类型</td>
            <td width="15%" class="td2">金额</td>
            <td width="15%" class="td2">利息</td>
            <td width="15%" class="td2">总额</td>
          </tr>
          
          <logic:notEmpty name="queryorgversionintAF" property="list">
						<logic:iterate name="queryorgversionintAF" property="list"
											id="element" indexId="i">
											<tr align="left" id="tr<%=i%>"
											onclick='gotoClick("tr<%=i%>","s<%=i%>", idAF);'
												onMouseOver='this.style.background="#eaeaea"'
												onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", idAF);'
												class=td4 onDblClick="">
												<td>
													<p align="left">
														<input id="s<%=i%>" type="radio" name="id"
															value="<bean:write name="element" property="empId"/>">
													</p>
									</td>
									<td valign="top">
										<bean:write name="element" property="empId" />
									</td>
									<td valign="top">
										<bean:write name="element" property="empName" />
									</td>
									<td valign="top">
										<bean:write name="element" property="clearInterestType" />
									</td>
									<td valign="top">
										<bean:write name="element" property="money" />
									</td>
									<td valign="top">
										<bean:write name="element" property="interest" />
									</td>
									<td valign="top">
									    <bean:write name="element" property="sumMoney" />
									</td>
							  </tr>
							    <tr > 
                                  <td colspan="20" class=td5 ></td>
                                </tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="queryorgversionintAF" property="list">
							<tr>
								<td colspan="4" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>
							<tr>
								<td colspan="7"></td>
							</tr>
						</logic:empty>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
          <td align="center">
            <table width="585" height="19" border="0" cellpadding="0" cellspacing="0">
          
                <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="to_queryorgversionintShowAC.do"/></jsp:include></td>
               </tr> 
                     
            </table>
          </td>
        </tr>
      </table>
      </html:form>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td>
				
			  </td>
                </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html:html>

