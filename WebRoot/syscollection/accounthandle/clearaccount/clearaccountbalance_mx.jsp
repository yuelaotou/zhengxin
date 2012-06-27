<%@page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.action.ShowOrgbusinessflowListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			ShowOrgbusinessflowListAC.PAGINATION_KEY);
	request.getSession().setAttribute(
			ShowOrgbusinessflowListAC.PAGINATION_KEY, pagination);
%>
<html>
	<head>
		<title>金软科技住房公积金管理系统V7.0</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	</head>
	<script type="text/javascript">
function loads(){

	window.open('<%=path%>/syscollection/querystatistics/operationflow/orgoperationflow/orgbusinessflowForwardURLAC.do','','width=700,height=500,top=200,left=300,scrollbars=yes');
}
</script>
	<body bgcolor="#CEE7F4" text="#000000" onload="loads();">

	</body>
</html>
