<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.operationflow.orgoperationflow.action.EmpOperationFlowTaShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination= request.getSession(false).getAttribute(EmpOperationFlowTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path=request.getContextPath();
   String type = (String)request.getAttribute("type");
 %>
<html:html>
<head>
<title>业务流水>>职工业务流水</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script src="<%=path%>/js/common.js">
</script>
</head>
<script type="text/javascript">
function load(){
	var type = "<%=type%>";
	alert(type);
	if(type=="A"){
		document.location.href='<%=path%>/syscollection/paymng/monthpay/monthpay_mx_cell.jsp';
	}else if(type=="M"){
		document.location.href='<%=path%>/syscollection/paymng/orgaddpay/orgaddpay_mx_cell.jsp';
	}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="load();">
</body>
</html:html>
