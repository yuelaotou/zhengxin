<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

<%
	String orderBy = request.getParameter("orderBy");
	String column = request.getParameter("column");
%>
<a href="javascript:sort('<%=orderBy%>')"><%=column%></a>
<logic:equal name="pagination" property="orderBy" value="<%=orderBy%>">
	<logic:equal name="pagination" property="order.name" value="ASC">▲</logic:equal>
	<logic:equal name="pagination" property="order.name" value="DESC">▼</logic:equal>
</logic:equal>
